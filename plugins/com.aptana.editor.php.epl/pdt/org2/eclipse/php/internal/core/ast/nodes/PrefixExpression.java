/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org2.eclipse.php.internal.core.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org2.eclipse.php.internal.core.PHPVersion;
import org2.eclipse.php.internal.core.ast.match.ASTMatcher;
import org2.eclipse.php.internal.core.ast.visitor.Visitor;

/**
 * Represents a prefix expression
 * <pre>e.g.<pre> --$a,
 * --foo()
 */
public class PrefixExpression extends Expression implements IOperationNode {

	// '++'
	public static final int OP_INC = 0;
	// '--'
	public static final int OP_DEC = 1;

	private VariableBase variable;
	private int operator;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor VARIABLE_PROPERTY = new ChildPropertyDescriptor(PrefixExpression.class, "variable", VariableBase.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor OPERATOR_PROPERTY = new SimplePropertyDescriptor(PrefixExpression.class, "operator", Integer.class, MANDATORY); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type: 
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<StructuralPropertyDescriptor>(2);
		propertyList.add(VARIABLE_PROPERTY);
		propertyList.add(OPERATOR_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public PrefixExpression(AST ast) {
		super(ast);
	}

	public PrefixExpression(int start, int end, AST ast, VariableBase variable, int operator) {
		super(start, end, ast);

		if (variable == null) {
			throw new IllegalArgumentException();
		}

		setVariable(variable);
		setOperator(operator);
	}

	public void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);
	}

	public void childrenAccept(Visitor visitor) {
		variable.accept(visitor);
	}

	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		variable.traverseTopDown(visitor);
	}

	public void traverseBottomUp(Visitor visitor) {
		variable.traverseBottomUp(visitor);
		accept(visitor);
	}

	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<PrefixExpression"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" operator='").append(getOperator(operator)).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		variable.toString(buffer, TAB + tab);
		buffer.append("\n").append(tab).append("</PrefixExpression>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static String getOperator(int operator) {
		switch (operator) {
			case OP_DEC:
				return "--"; //$NON-NLS-1$
			case OP_INC:
				return "++"; //$NON-NLS-1$
			default:
				throw new IllegalArgumentException();
		}
	}

	public int getType() {
		return ASTNode.PREFIX_EXPRESSION;
	}

	/**
	 * Returns the operator of this prefix expression.
	 * 
	 * @return the prefix operator
	 */
	public int getOperator() {
		return this.operator;
	}

	/*
	 * (non-Javadoc)
	 * @see org2.eclipse.php.internal.core.ast.nodes.IOperationNode#getOperationString()
	 */
	public String getOperationString() {
		return getOperator(this.getOperator());
	}

	/* (non-Javadoc)
	 * @see org2.eclipse.php.internal.core.ast.nodes.IOperationNode#getOperationString(int)
	 */
	public String getOperationString(int op) {
		return getOperator(op);
	}

	/**
	 * Sets the operator of this prefix expression.
	 * 
	 * @param operator the prefix operator
	 * @exception IllegalArgumentException if the argument is incorrect
	 */
	public void setOperator(int operator) {
		if (getOperator(operator) == null) {
			throw new IllegalArgumentException();
		}
		preValueChange(OPERATOR_PROPERTY);
		this.operator = operator;
		postValueChange(OPERATOR_PROPERTY);
	}

	final int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == OPERATOR_PROPERTY) {
			if (get) {
				return getOperator();
			} else {
				setOperator(value);
				return 0;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetIntProperty(property, get, value);
	}

	/**
	 * Returns the variable in the prefix expression.
	 * 
	 * @return the expression node
	 */
	public VariableBase getVariable() {
		return variable;
	}

	/**
	 * Sets the expression of this prefix expression.
	 * 
	 * @param variable the new expression node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */
	public void setVariable(VariableBase variable) {
		if (variable == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.variable;
		preReplaceChild(oldChild, variable, VARIABLE_PROPERTY);
		this.variable = variable;
		postReplaceChild(oldChild, variable, VARIABLE_PROPERTY);
	}

	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == VARIABLE_PROPERTY) {
			if (get) {
				return getVariable();
			} else {
				setVariable((VariableBase) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/* 
	 * Method declared on ASTNode.
	 */
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	@Override
	ASTNode clone0(AST target) {
		final VariableBase variable = ASTNode.copySubtree(target, this.getVariable());
		final PrefixExpression result = new PrefixExpression(this.getStart(), this.getEnd(), target, variable, this.getOperator());
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

}
