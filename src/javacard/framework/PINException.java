/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * <code>PINException</code> represents a <code>OwnerPIN</code> class or {@code
 * OwnerPINx} -implementing class access-related exception. <p> The
 * <code>OwnerPIN</code> class and {@code OwnerPINx}-implementing class throws
 * Java Card runtime environment-owned instances of <code>PINException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details.
 *
 * @see OwnerPIN OwnerPIN
 * @see OwnerPINx OwnerPINx
 * @see OwnerPINxWithPredecrement OwnerPINxWithPredecrement
 */

public class PINException extends CardRuntimeException {

  // PINException reason codes
  /**
   * This reason code is used to indicate that one or more input parameters is
   * out of allowed bounds.
   */
  public static final short ILLEGAL_VALUE = 1;
  /**
   * This reason code is used to indicate a method has been invoked at an
   * illegal or inappropriate time.
   */
  public static final short ILLEGAL_STATE = 2;

  /**
   * Constructs a PINException. To conserve on resources use
   * <code>throwIt()</code> to employ the Java Card runtime
   * environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public PINException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>PINException</code> with the specified reason.
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
   * @exception PINException
   *                always
   */
  public static void throwIt(short reason) {
    PINException instance = new PINException(reason);
    throw instance;
  }
}
