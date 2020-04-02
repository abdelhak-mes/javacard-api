/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * <code>ISOException</code> class encapsulates an ISO 7816-4 response status
 * word as its <code>reason</code> code.
 * <p>
 * The <code>APDU</code> class throws Java Card runtime environment-owned
 * instances of <code>ISOException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details.
 */

public class ISOException extends CardRuntimeException {

  /**
   * Constructs an ISOException instance with the specified status word. To
   * conserve on resources use <code>throwIt()</code> to employ the Java
   * Card runtime environment-owned instance of this class.
   *
   * @param sw
   *            the ISO 7816-4 defined status word
   */
  public ISOException(short sw) { super(sw); }

  /**
   * Throws the Java Card runtime environment-owned instance of the
   * ISOException class with the specified status word.
   * <p>
   * Java Card runtime environment-owned instances of exception classes are
   * temporary Java Card runtime environment Entry Point Objects and can be
   * accessed from any applet context. References to these temporary objects
   * cannot be stored in class variables or instance variables or array
   * components. See
   * <em>Runtime Environment Specification, Java Card Platform, Classic
   * Edition</em>, section 6.2.1 for details.
   *
   * @param sw
   *            ISO 7816-4 defined status word
   * @exception ISOException
   *                always
   */
  public static void throwIt(short sw) {
    ISOException exception = new ISOException(sw);
    throw exception;
  }
}
