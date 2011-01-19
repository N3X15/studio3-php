/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.php.internal.parser;

import java.io.IOException;

import beaver.Symbol;

import com.aptana.editor.common.parsing.CompositeParser;
import com.aptana.editor.html.parsing.IHTMLParserConstants;
import com.aptana.parsing.IParseState;
import com.aptana.parsing.ast.IParseNode;
import com.aptana.parsing.ast.ParseNode;
import com.aptana.parsing.ast.ParseRootNode;

public class PHTMLParser extends CompositeParser
{

	public PHTMLParser()
	{
		super(new PHTMLParserScanner(), IHTMLParserConstants.LANGUAGE);
	}

	@Override
	protected IParseNode processEmbeddedlanguage(IParseState parseState) throws Exception
	{
		String source = new String(parseState.getSource());
		int startingOffset = parseState.getStartingOffset();
		IParseNode root = null;

		advance();
		short id = getCurrentSymbol().getId();
		while (id != PHTMLTokens.EOF)
		{
			// only cares about ruby tokens
			switch (id)
			{
				case PHTMLTokens.PHP:
					if (root == null)
					{
						root = new ParseRootNode(PHPMimeType.MIME_TYPE, new ParseNode[0], startingOffset,
								startingOffset + source.length());
					}
					processPHPBlock(root);
					break;
			}
			advance();
			id = getCurrentSymbol().getId();
		}
		return root;
	}

	private void processPHPBlock(IParseNode root) throws IOException, Exception
	{
		Symbol startTag = getCurrentSymbol();
		advance();

		// finds the entire php block
		int start = getCurrentSymbol().getStart();
		int end = start;
		short id = getCurrentSymbol().getId();
		while (id != PHTMLTokens.PHP_END && id != PHTMLTokens.EOF)
		{
			end = getCurrentSymbol().getEnd();
			advance();
			id = getCurrentSymbol().getId();
		}

		IParseNode result = getParseResult(PHPMimeType.MIME_TYPE, start, end);
		if (result != null)
		{
			Symbol endTag = getCurrentSymbol();
			ParseNode phpNode = new ParseNode(PHPMimeType.MIME_TYPE);
			phpNode.setLocation(startTag.getStart(), endTag.getEnd());
			root.addChild(phpNode);
		}
	}
}