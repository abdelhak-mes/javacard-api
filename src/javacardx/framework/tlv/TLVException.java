/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.tlv;

import javacard.framework.CardRuntimeException;
import javacard.framework.JCSystem;

/**
 * <code>TLVException</code> represents a TLV-related exception.
 * <p>
 * The API classes throw Java Card runtime environment-owned instances of
 * <code>TLVException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables, instance variables, or array components.
 *
 * @since 2.2.2
 */
public class TLVException extends CardRuntimeException {

  /**
   * This reason code is used to indicate that one or more input parameters is
   * invalid.
   */
  public static final short INVALID_PARAM = 1;

  /**
   * This reason code is used to indicate that the size of a TLV or Tag
   * representation in the input parameter is greater than the supported size
   * or will result in in a TLV structure of greater than supported size
   */
  public static final short ILLEGAL_SIZE = 2;

  /**
   * This reason code is used to indicate that the Tag object is empty
   */
  public static final short EMPTY_TAG = 3;

  /**
   * This reason code is used to indicate that the TLV object is empty
   */
  public static final short EMPTY_TLV = 4;

  /**
   * This reason code is used to indicate that the tag representation is not a
   * well-formed BER Tag
   */
  public static final short MALFORMED_TAG = 5;

  /**
   * This reason code is used to indicate that the TLV representation is not a
   * well-formed BER TLV
   */
  public static final short MALFORMED_TLV = 6;

  /**
   * This reason code is used to indicate that the configured storage capacity
   * of the object will be exceeded
   */
  public static final short INSUFFICIENT_STORAGE = 7;

  /**
   * This reason code is used to indicate that the size of the tag
   * representation is greater than 127 bytes
   */
  public static final short TAG_SIZE_GREATER_THAN_127 = 8;

  /**
   * This reason code is used to indicate that the tag number value greater
   * than 32767
   */
  public static final short TAG_NUMBER_GREATER_THAN_32767 = 9;

  /**
   * This reason code is used to indicate that the TLV requires more that
   * 32767 bytes to represent
   */
  public static final short TLV_SIZE_GREATER_THAN_32767 = 10;

  /**
   * This reason code is used to indicate that the Length component value in
   * the TLV is greater than 32767
   */
  public static final short TLV_LENGTH_GREATER_THAN_32767 = 11;

  /**
   * Constructs a <code>TLVException</code> with the specified reason. To
   * conserve on resources use <code>throwIt()</code> to use the Java Card
   * runtime environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public TLVException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>TLVException</code> with the specified reason.
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
   * @exception TLVException
   *                always
   */
  public static void throwIt(short reason) {
    TLVException tlve = new TLVException(reason);
    throw tlve;
  }
}
