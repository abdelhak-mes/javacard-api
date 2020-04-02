/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

import javacard.framework.CardRuntimeException;

/**
 * <code>CryptoException</code> represents a cryptography-related exception.
 * <p>
 * The API classes throw Java Card runtime environment-owned instances of
 * <code>CryptoException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components.
 *
 * @see javacard.security.KeyBuilder KeyBuilder
 * @see javacard.security.MessageDigest MessageDigest
 * @see javacard.security.Signature Signature
 * @see javacard.security.RandomData RandomData
 * @see javacardx.crypto.Cipher javacardx.crypto.Cipher
 */
public class CryptoException extends CardRuntimeException {

  /**
   * This reason code is used to indicate that one or more input parameters is
   * out of allowed bounds.
   */
  public static final short ILLEGAL_VALUE = 1;

  /**
   * This reason code is used to indicate that the key is uninitialized.
   */
  public static final short UNINITIALIZED_KEY = 2;

  /**
   * This reason code is used to indicate that the requested algorithm or key
   * type is not supported.
   */
  public static final short NO_SUCH_ALGORITHM = 3;

  /**
   * This reason code is used to indicate that the signature or cipher object
   * has not been correctly initialized for the requested operation.
   */
  public static final short INVALID_INIT = 4;

  /**
   * This reason code is used to indicate that the signature or cipher
   * algorithm does not pad the incoming message and the input message is not
   * block aligned. This reason code is also used to indicate the method
   * cannot be used because of the configured algorithm or because a
   * consistency check failed.
   */
  public static final short ILLEGAL_USE = 5;

  /**
   * Constructs a <code>CryptoException</code> with the specified reason. To
   * conserve on resources use <code>throwIt()</code> to use the Java Card
   * runtime environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public CryptoException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>CryptoException</code> with the specified reason.
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
   * @exception CryptoException
   *                always
   */
  public static void throwIt(short reason) {
    CryptoException ce = new CryptoException(reason);
    throw ce;
  }
}
