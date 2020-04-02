/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.tlv;

import javacard.framework.Util;

/**
 * The abstract <code>BERTLV</code> class encapsulates a BER TLV structure.
 * The rules on the allowed encoding of the Tag, length and value fields are
 * based on the ASN.1 BER encoding rules ISO/IEC 8825-1:2002.
 * <p>
 * The <code>BERTLV</code> class and the subclasses -
 * <code>ConstructedBERTLV</code> and <code>PrimitiveBERTLV</code> only
 * support encoding of the length(L) octets in definite form. These classes do
 * not provide support for the encoding rules of the contents octets of the
 * value(V) field as described in ISO/IEC 8825-1:2002.
 * <p>
 * The <code>BERTLV</code> class and the subclasses -
 * <code>ConstructedBERTLV</code> and <code>PrimitiveBERTLV</code> also
 * provide static methods to parse/edit a TLV structure representation in a byte
 * array.
 *
 * @since 2.2.2
 */
public abstract class BERTLV {

  /**
   * Constructor creates an empty <code>BERTLV</code> object capable of
   * encapsulating a BER TLV structure.
   */
  protected BERTLV() {}

  /**
   * Abstract init method. (Re-)Initializes
   * <code>this</code> <code>BERTLV</code> using the input byte data.
   * <p>
   * The capacity of <code>this</code> <code>BERTLV</code> is increased,
   * if required and supported, based on the size of the
   * input TLV data structure.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff+bLen</code><em> is greater than
   * </em><code>bArray.length</code><em>, the length of the
   * </em><code>bArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param bArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing the TLV data
   * @param bLen
   *            byte length of input data
   * @return the resulting size of <code>this</code> TLV if represented in
   *         bytes
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset or array
   *                length parameter is negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INSUFFICIENT_STORAGE</code> if
   *                the required capacity is not available and the
   *                implementation does not support automatic expansion.
   *                <li><code>TLVException.MALFORMED_TLV</code> if the
   *                input data is not a well-formed BER TLV or the input data
   *                represents a primitive BER TLV structure and
   *                <code>this</code> is a <code>ConstructedBERTLV</code>
   *                object or the input data represents a constructed BER TLV
   *                structure and <code>this</code> is a
   *                <code>PrimiitveBERTLV</code> object.
   *                </ul>
   */
  public abstract short init(byte[] bArray, short bOff, short bLen)
      throws TLVException;

  /**
   * Creates the <code>BERTLV</code> using the input binary data. The
   * resulting BER TLV object may be a primitive or a constructed TLV object.
   * The object must be cast to the correct sub-class:
   * <code>ConstructedBERTLV</code> or <code>PrimitiveBERTLV</code> to
   * access the specialized API. The
   * <code>init( byte[] bArray, short bOff, short bLen )</code> methods of
   * the appropriate <code>BERTLV</code> classes will be used to initialize
   * the created TLV object.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff+bLen</code><em> is greater than
   * </em><code>bArray.length</code><em>, the length of the
   * </em><code>bArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param bArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing the tlv data
   * @param bLen
   *            byte length of input data
   * @return BERTLV object
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset or array
   *                length parameter is negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the TLV
   *                structure requested is larger than the supported maximum
   *                size
   *                <li><code>TLVException.MALFORMED_TLV</code> if the
   *                input data is not a well-formed BER TLV.
   *                </ul>
   */
  public static BERTLV getInstance(byte[] bArray, short bOff, short bLen)
      throws TLVException {
    // TODO: Not yet implemented
    return null;
  }

  /**
   * Writes <code>this</code> TLV structure to the specified byte array.
   *
   * @param outBuf
   *            output byte array
   * @param bOff
   *            offset within byte array output data begins
   * @return the byte length written to the output array
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the output array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>outBuf</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_SIZE_GREATER_THAN_32767</code>
   *                if the size of the BER TLV is &gt; 32767.
   *                <li><code>TLVException.EMPTY_TLV</code> if the
   *                <code>BERTLV</code> object is empty.
   *                </ul>
   */
  public short toBytes(byte[] outBuf, short bOff) {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns <code>this</code> value of the TLV object's Tag component
   *
   * @return the Tag for <code>this</code> <code>BERTLV</code> object
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.EMPTY_TLV</code> if the
   *                <code>BERTLV</code> object is empty.
   *                </ul>
   */
  public BERTag getTag() throws TLVException {
    // TODO: Not yet implemented
    return null;
  }

  /**
   * Returns the value of <code>this</code> TLV object's Length component
   *
   * @return the value of TLV object's length compoent
   *
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_LENGTH_GREATER_THAN_32767</code>
   *                if the value of the Length component is &gt; 32767.
   *                <li><code>TLVException.EMPTY_TLV</code> if the
   *                <code>BERTLV</code> object is empty.
   *                </ul>
   */
  public short getLength() throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns the number of bytes required to represent <code>this</code> TLV
   * structure
   *
   * @return the byte length of the TLV
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_SIZE_GREATER_THAN_32767</code>
   *                if the size of TLV structure is &gt; 32767.
   *                <li><code>TLVException.EMPTY_TLV</code> if the
   *                <code>BERTLV</code> object is empty.
   *                </ul>
   */
  public short size() {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Checks if the input data is a well-formed BER TLV representation.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff+bLen</code><em> is greater than
   * </em><code>berTlvArray.length</code><em>, the length of the
   * </em><code>berTlvArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param berTlvArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte
   * @param bLen
   *            byte length of input BER TLV data
   * @return <code>true</code> if input data is a well formed BER TLV
   *         structure, <code>false</code> otherwise
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset or array
   *                length parameter is negative
   * @exception java.lang.NullPointerException
   *                if <code>berTlvArray</code> is <code>null</code>
   */
  public static boolean verifyFormat(byte[] berTlvArray, short bOff,
                                     short bLen) {
    // TODO: Not yet implemented
    return false;
  }

  /*
   * An internal verifyFormat that will return the number of contained TLVs if
   * <code>this</code> is a constructed TLV, or will return 1 less than the
   * number of TLVs contained in an array of TLVs to be used to initialize a
   * constructed TLV. This is wrapped by the public version, since the public
   * version returns a boolean true or false depending on if the TLV is well
   * formed or not. Internally, it is usefull to return the number of TLVs
   * contained at the highest level of a constructed TLV. Will return
   * MALFORMED_TAG_VALUE if malformed tag, ILLEGAL_SIZE_VALUE if illegal length,
   * MALFORMED_LENGTH malformed length.
   */
  static short verifyFormatInternal(byte[] berTlvArray, short bOff,
                                    short bLen) {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Copies the tag component in the TLV representation in the specified input
   * byte array to the specified output byte array
   *
   * @param berTLVArray
   *            input byte array
   * @param bTLVOff
   *            offset within byte array containing the tlv data
   * @param berTagArray
   *            output Tag byte array
   * @param bTagOff
   *            offset within byte array where output begins
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input or output array would cause access
   *                of data outside array bounds, or if either array offset
   *                parameter is negative
   * @exception java.lang.NullPointerException
   *                if either <code>berTLVArray</code> or
   *                <code>berTagArray</code> is <code>null</code>
   * @return the size of the output BER Tag
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the size
   *                of the Tag component is &gt; 32767.
   *                <li><code>TLVException.MALFORMED_TLV</code> if the
   *                input data is not a well-formed BER TLV.
   *                </ul>
   */
  public static short getTag(byte[] berTLVArray, short bTLVOff,
                             byte[] berTagArray, short bTagOff)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns the value of the TLV Structure's Length component in the
   * specified input byte array
   *
   * @param berTLVArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing the tlv data
   * @return the length value in the TLV representation in the specified byte
   *         array
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>berTLVArray</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_LENGTH_GREATER_THAN_32767</code>
   *                if the length element(L) &gt; 32767.
   *                <li><code>TLVException.MALFORMED_TLV</code> if the
   *                input data is not a well-formed BER TLV.
   *                </ul>
   */
  public static short getLength(byte[] berTLVArray, short bOff)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }
}
