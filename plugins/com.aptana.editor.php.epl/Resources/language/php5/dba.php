<?php

// Start of dba v.

/**
 * Open database
 * @link http://www.php.net/manual/en/function.dba-open.php
 * @param path string <p>
 * Commonly a regular path in your filesystem.
 * </p>
 * @param mode string <p>
 * It is r for read access, w for
 * read/write access to an already existing database, c
 * for read/write access and database creation if it doesn't currently exist,
 * and n for create, truncate and read/write access.
 * The database is created in BTree mode, other modes (like Hash or Queue)
 * are not supported.
 * </p>
 * <p>
 * Additionally you can set the database lock method with the next char. 
 * Use l to lock the database with a .lck
 * file or d to lock the databasefile itself. It is 
 * important that all of your applications do this consistently.
 * </p>
 * <p>
 * If you want to test the access and do not want to wait for the lock 
 * you can add t as third character. When you are 
 * absolutely sure that you do not require database locking you can do 
 * so by using - instead of l or
 * d. When none of d, 
 * l or - is used, dba will lock
 * on the database file as it would with d.
 * </p>
 * <p>
 * There can only be one writer for one database file. When you use dba on 
 * a web server and more than one request requires write operations they can
 * only be done one after another. Also read during write is not allowed.
 * The dba extension uses locks to prevent this. See the following table:
 * <table>
 * DBA locking
 * <tr valign="top">
 * <td>already open</td>
 * <td>mode = "rl"</td>
 * <td>mode = "rlt"</td>
 * <td>mode = "wl"</td>
 * <td>mode = "wlt"</td>
 * <td>mode = "rd"</td>
 * <td>mode = "rdt"</td>
 * <td>mode = "wd"</td>
 * <td>mode = "wdt"</td>
 * </tr>
 * <tr valign="top">
 * <td>not open</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>ok</td>
 * </tr>
 * <tr valign="top">
 * <td>mode = "rl"</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>wait</td>
 * <td>false</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * </tr>
 * <tr valign="top">
 * <td>mode = "wl"</td>
 * <td>wait</td>
 * <td>false</td>
 * <td>wait</td>
 * <td>false</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * </tr>
 * <tr valign="top">
 * <td>mode = "rd"</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>ok</td>
 * <td>ok</td>
 * <td>wait</td>
 * <td>false</td>
 * </tr>
 * <tr valign="top">
 * <td>mode = "wd"</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>illegal</td>
 * <td>wait</td>
 * <td>false</td>
 * <td>wait</td>
 * <td>false</td>
 * </tr>
 * </table>
 * ok: the second call will be successfull.
 * wait: the second call waits until dba_close is called for the first.
 * false: the second call returns false.
 * illegal: you must not mix "l" and "d" modifiers for mode parameter.
 * </p>
 * @param handler string[optional] <p>
 * The name of the handler which
 * shall be used for accessing path. It is passed 
 * all optional parameters given to dba_open and 
 * can act on behalf of them.
 * </p>
 * @param _ mixed[optional] 
 * @return resource a positive handle on success&return.falseforfailure;.
 */
function dba_open ($path, $mode, $handler = null, $_ = null) {}

/**
 * Open database persistently
 * @link http://www.php.net/manual/en/function.dba-popen.php
 * @param path string <p>
 * Commonly a regular path in your filesystem.
 * </p>
 * @param mode string <p>
 * It is r for read access, w for
 * read/write access to an already existing database, c
 * for read/write access and database creation if it doesn't currently exist,
 * and n for create, truncate and read/write access.
 * </p>
 * @param handler string[optional] <p>
 * The name of the handler which
 * shall be used for accessing path. It is passed 
 * all optional parameters given to dba_popen and 
 * can act on behalf of them.
 * </p>
 * @param _ mixed[optional] 
 * @return resource a positive handle on success&return.falseforfailure;.
 */
function dba_popen ($path, $mode, $handler = null, $_ = null) {}

/**
 * Close a DBA database
 * @link http://www.php.net/manual/en/function.dba-close.php
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return void 
 */
function dba_close ($handle) {}

/**
 * Delete DBA entry specified by key
 * @link http://www.php.net/manual/en/function.dba-delete.php
 * @param key string <p>
 * The key of the entry which is deleted.
 * </p>
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return bool Returns true on success or false on failure.
 */
function dba_delete ($key, $handle) {}

/**
 * Check whether key exists
 * @link http://www.php.net/manual/en/function.dba-exists.php
 * @param key string <p>
 * The key the check is performed for.
 * </p>
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return bool true if the key exists, false otherwise.
 */
function dba_exists ($key, $handle) {}

/**
 * Fetch data specified by key
 * @link http://www.php.net/manual/en/function.dba-fetch.php
 * @param key string <p>
 * The key the data is specified by.
 * </p>
 * <p>
 * When working with inifiles this function accepts arrays as keys
 * where index 0 is the group and index 1 is the value name. See:
 * dba_key_split.
 * </p>
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return string the associated string if the key/data pair is found, false 
 * otherwise.
 */
function dba_fetch ($key, $handle) {}

/**
 * Insert entry
 * @link http://www.php.net/manual/en/function.dba-insert.php
 * @param key string <p>
 * The key of the entry to be inserted. If this key already exist in the 
 * database, this function will fail. Use dba_replace
 * if you need to replace an existent key.
 * </p>
 * @param value string <p>
 * The value to be inserted.
 * </p>
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return bool Returns true on success or false on failure.
 */
function dba_insert ($key, $value, $handle) {}

/**
 * Replace or insert entry
 * @link http://www.php.net/manual/en/function.dba-replace.php
 * @param key string <p>
 * The key of the entry to be replaced.
 * </p>
 * @param value string <p>
 * The value to be replaced.
 * </p>
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return bool Returns true on success or false on failure.
 */
function dba_replace ($key, $value, $handle) {}

/**
 * Fetch first key
 * @link http://www.php.net/manual/en/function.dba-firstkey.php
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return string the key on success&return.falseforfailure;.
 */
function dba_firstkey ($handle) {}

/**
 * Fetch next key
 * @link http://www.php.net/manual/en/function.dba-nextkey.php
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return string the key on success&return.falseforfailure;.
 */
function dba_nextkey ($handle) {}

/**
 * Optimize database
 * @link http://www.php.net/manual/en/function.dba-optimize.php
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return bool Returns true on success or false on failure.
 */
function dba_optimize ($handle) {}

/**
 * Synchronize database
 * @link http://www.php.net/manual/en/function.dba-sync.php
 * @param handle resource <p>
 * The database handler, returned by dba_open or
 * dba_popen.
 * </p>
 * @return bool Returns true on success or false on failure.
 */
function dba_sync ($handle) {}

/**
 * List all the handlers available
 * @link http://www.php.net/manual/en/function.dba-handlers.php
 * @param full_info bool[optional] <p>
 * Turns on/off full information display in the result.
 * </p>
 * @return array an array of database handlers. If full_info
 * is set to true, the array will be associative with the handlers names as
 * keys, and their version information as value. Otherwise, the result will be
 * an indexed array of handlers names. 
 * </p>
 * <p>
 * When the internal cdb library is used you will see 
 * cdb and cdb_make.
 */
function dba_handlers ($full_info = null) {}

/**
 * List all open database files
 * @link http://www.php.net/manual/en/function.dba-list.php
 * @return array An associative array, in the form resourceid =&gt; filename.
 */
function dba_list () {}

/**
 * Splits a key in string representation into array representation
 * @link http://www.php.net/manual/en/function.dba-key-split.php
 * @param key mixed <p>
 * The key in string representation.
 * </p>
 * @return mixed an array of the form array(0 =&gt; group, 1 =&gt; 
 * value_name). This function will return false if 
 * key is &null; or false.
 */
function dba_key_split ($key) {}

// End of dba v.
?>
