/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.util;

import javacard.framework.CardRuntimeException;

/**
 * <code>UtilException</code> represents a util related exception.
 * <p>
 * The API classes throw Java Card runtime environment-owned instances of
 * <code>UtilException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables, instance variables, or array components.
 *
 * @since 2.2.2
 */
public class UtilException extends CardRuntimeException {

  /**
   * This reason code is used to indicate that one or more input parameters is
   * not the correct type or is out of allowed bounds.
   */
  public static final short ILLEGAL_VALUE = 1;

  /**
   * This reason code is used to indicate that input parameters are not the
   * same type.
   */
  public static final short TYPE_MISMATCHED = 2;

  /**
   * Constructs a <code>UtilException</code> with the specified reason. To
   * conserve on resources use <code>throwIt()</code> to use the Java Card
   * runtime environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public UtilException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>UtilException</code> with the specified reason.
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
   * @exception UtilException
   *                always
   */
  public static void throwIt(short reason) {
    UtilException ue = new UtilException(reason);
    throw ue;
  }
}
