/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry;

import javacard.framework.JCSystem;

/**
 * The <code>BioException</code> class encapsulates specific exceptions which
 * can be thrown by the methods of the <code>javacardx.biometry</code> package
 * in case of error.
 *
 * @since 2.2.2
 */
public class BioException extends javacard.framework.CardRuntimeException {

  /**
   * This reason code is used to indicate that one or more input parameters is
   * out of allowed bounds.
   */
  public static final short ILLEGAL_VALUE = (short)1;
  /**
   * This reason code is used to indicate that the data the system encountered
   * is illegible.
   */
  public static final short INVALID_DATA = (short)2;
  /**
   * This reason code is used to indicate that the provided bio template type
   * is not supported by the template builder.
   */
  public static final short NO_SUCH_BIO_TEMPLATE = (short)3;
  /**
   * This reason code is used to indicate that no reference template is
   * available for matching, or that the reference template is uninitialized.
   */
  public static final short NO_TEMPLATES_ENROLLED = (short)4;
  /**
   * This reason code is used to indicate that the method should not be
   * invoked based on the current state of the card.
   */
  public static final short ILLEGAL_USE = (short)5;

  /**
   * Construct a new biometric exception using a provided reason code. To
   * conserve on resources use <code>throwIt()</code> to use the Java Card
   * runtime environment instance of this class.
   *
   * @param reason
   *            the reason code for this exception.
   */
  public BioException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment owned instance of BioException
   * with the specified reason. Java Card runtime environment owned instances
   * of exception classes are temporary Java Card runtime environment Entry
   * Point Objects and can be accessed from any applet context. References to
   * these objects cannot be stored in class variables or instance variables
   * or array components.
   *
   * @param reason
   *            the reason for the exception.
   * @throws BioException
   *             always.
   */
  public static void throwIt(short reason) throws BioException {
    BioException be = new BioException(reason);
    throw be;
  }
}
