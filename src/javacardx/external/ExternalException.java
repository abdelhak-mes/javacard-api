/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.external;

import javacard.framework.CardRuntimeException;

/**
 * <code>ExternalException</code> represents an external subsystem related
 * exception.
 * <p>
 * The API classes throw Java Card runtime environment-owned instances of
 * <code>ExternalException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components.
 *
 * @since 2.2.2
 */
public class ExternalException extends CardRuntimeException {

  /**
   * This reason code is used to indicate that specified external subsystem is
   * not available.
   */
  public static final short NO_SUCH_SUBSYSTEM = 1;

  /**
   * This reason code is used to indicate that an input parameter is invalid.
   */
  public static final short INVALID_PARAM = 2;

  /**
   * This reason code is used to indicate that an unrecoverable external
   * access error occurred.
   */
  public static final short INTERNAL_ERROR = 3;

  /**
   * Constructs a <code>ExternalException</code> with the specified reason.
   * To conserve on resources use <code>throwIt()</code> to use the Java
   * Card runtime environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public ExternalException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>ExternalException</code> with the specified reason.
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
   * @exception ExternalException
   *                always
   */
  public static void throwIt(short reason) {
    ExternalException ee = new ExternalException(reason);
    throw ee;
  }
}
