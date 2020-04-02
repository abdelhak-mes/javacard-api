/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry1toN;

/**
 * The {@code Bio1toNException} class encapsulates specific exceptions which
 * can be thrown by the methods of the {@code javacardx.biometry1toN} package
 * in case of error.
 *
 * @since 3.0.5
 */
public class Bio1toNException extends javacard.framework.CardRuntimeException {

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
  public static final short UNSUPPORTED_BIO_TYPE = (short)3;
  /**
   * This reason code is used to indicate that no reference biometric template
   * data is available for matching, or that the {@code BioTemplateData} is
   * uninitialized.
   */
  public static final short NO_BIO_TEMPLATE_ENROLLED = (short)4;
  /**
   * This reason code is used to indicate that the method should not be
   * invoked based on the current state of the card.
   */
  public static final short ILLEGAL_USE = (short)5;
  /**
   * This reason code is used to indicate that the maximum number of {@code
   * BioTemplateData} requested exceeds the supported capacity.
   */
  public static final short BIO_TEMPLATE_DATA_CAPACITY_EXCEEDED = (short)6;
  /**
   * This reason code is used to indicate the biometric type of a {@code
   * BioTemplateData} does not match that of the {@code BioMatcher} for
   * enrollment.
   */
  public static final short MISMATCHED_BIO_TYPE = (short)7;

  /**
   * Constructs a new {@code Bio1toNException} exception using a provided reason
   * code. To conserve on resources use {@link #throwIt(short) throwIt()} to use
   * the Java Card runtime environment instance of this class.
   *
   * @param reason
   *            the reason code for this exception.
   */
  public Bio1toNException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment owned instance of {@code
   * Bio1toNException} with the specified reason. Java Card runtime environment
   * owned instances of exception classes are temporary Java Card runtime
   * environment Entry Point Objects and can be accessed from any applet
   * context. References to these objects cannot be stored in class variables or
   * instance variables or array components.
   *
   * @param reason
   *            the reason for the exception.
   * @throws Bio1toNException
   *             always.
   */
  public static void throwIt(short reason) throws Bio1toNException {
    Bio1toNException bne = new Bio1toNException(reason);
    throw bne;
  }
}
