/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework.service;

import javacard.framework.CardRuntimeException;
import javacard.framework.JCSystem;

/**
 * <code>ServiceException</code> represents a service framework-related
 * exception.
 * <p>
 * The service framework classes throw Java Card runtime environment-owned
 * instances of <code>ServiceException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details.
 *
 * @version 1.0
 */
public class ServiceException extends CardRuntimeException {

  /**
   * This reason code is used to indicate that an input parameter is not
   * allowed.
   */
  public static final short ILLEGAL_PARAM = 1;

  /**
   * This reason code is used to indicate that a dispatch table is full.
   */
  public static final short DISPATCH_TABLE_FULL = 2;

  /**
   * This reason code is used to indicate that the incoming data for a command
   * in the <code>APDU</code> object does not fit in the APDU buffer.
   */
  public static final short COMMAND_DATA_TOO_LONG = 3;

  /**
   * This reason code is used to indicate that the command in the
   * <code>APDU</code> object cannot be accessed for input processing.
   */
  public static final short CANNOT_ACCESS_IN_COMMAND = 4;

  /**
   * This reason code is used to indicate that the command in the
   * <code>APDU</code> object cannot be accessed for output processing.
   */
  public static final short CANNOT_ACCESS_OUT_COMMAND = 5;

  /**
   * This reason code is used to indicate that the command in the
   * <code>APDU</code> object has been completely processed.
   */
  public static final short COMMAND_IS_FINISHED = 6;

  /**
   * This reason code is used by RMIService to indicate that the remote method
   * returned a remote object which has not been exported.
   */
  public static final short REMOTE_OBJECT_NOT_EXPORTED = 7;

  /**
   * Constructs a <code>ServiceException</code>. To conserve on resources
   * use <code>throwIt()</code> to use the Java Card runtime
   * environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public ServiceException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>ServiceException</code> with the specified reason.
   * <p>
   * Java Card runtime environment-owned instances of exception classes are
   * temporary Java Card runtime environment Entry Point Objects and can be
   * accessed from any applet context. References to these temporary objects
   * cannot be stored in class variables or instance variables or array
   * components. See
   * <em>Runtime Environment Specification, Java Card Platform, Classic
   * Edition</em>, section 6.2.1 for details.
   *
   * @param reason
   *            the reason for the exception
   * @exception ServiceException
   *                always
   */
  public static void throwIt(short reason) throws ServiceException {
    ServiceException se = new ServiceException(reason);
    throw se;
  }
}
