/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.string;

import javacard.framework.CardRuntimeException;

/**
 * <code>StringException</code> represents a <code>StringUtil</code> class
 * related exception. <p> The API classes throw Java Card runtime
 * environment-owned instances of <code>StringException</code>. <p> Java Card
 * runtime environment-owned instances of exception classes are temporary Java
 * Card runtime environment Entry Point Objects and can be accessed from any
 * applet context. References to these temporary objects cannot be stored in
 * class variables, instance variables, or array components.
 *
 * @since Java Card 3.0.4
 */
public class StringException extends CardRuntimeException {

  /**
   * This reason code is used to indicate that the requested character encoding
   * is not supported.
   */
  public static final short UNSUPPORTED_ENCODING = 1;

  /**
   * This reason code is used to indicate that the character sequence to be
   * converted to a number does not have the appropriate format.
   */
  public static final short ILLEGAL_NUMBER_FORMAT = 2;

  /**
   * This reason code is used to indicate that an invalid byte sequence was
   * encountered when encoding to or from UTF-8.
   */
  public static final short INVALID_BYTE_SEQUENCE = 3;

  /**
   * Constructs a <code>StringException</code> with the specified reason. To
   * conserve on resources use <code>throwIt()</code> to use the Java Card
   * runtime environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public StringException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>StringException</code> with the specified reason.
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
   * @exception StringException
   *                always
   */
  public static void throwIt(short reason) {
    throw new StringException(reason);
  }
}
