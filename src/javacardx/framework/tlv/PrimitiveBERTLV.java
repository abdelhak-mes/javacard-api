/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.tlv;

import javacard.framework.JCSystem;
import javacard.framework.Util;

/**
 * The <code>PrimitiveBERTLV</code> class encapsulates a primitive BER TLV
 * structure. It extends the generic <code>BERTLV</code> class. The rules on
 * the allowed encoding of the Tag, length and value fields is based on the
 * ASN.1 BER encoding rules ISO/IEC 8825-1:2002.
 * <p>
 * The <code>PrimitiveBERTLV</code> class only supports encoding of the
 * length(L) octets in definite form. The value(V) field which encodes the
 * contents octets are merely viewed as a series of bytes.
 * <p>
 * Every <code>PrimitiveBERTLV</code> has a capacity which represents the
 * allocated internal buffer to represent the Value of <code>this</code> TLV
 * object. As long as the number of bytes required to represent the Value of the
 * TLV object does not exceed the capacity, it is not necessary to allocate
 * additional internal buffer space. If the internal buffer overflows, and the
 * implementation supports automatic expansion which might require new data
 * allocation and possibly old data/object deletion, it is automatically made
 * larger. Otherwise a <code>TLVException</code> is thrown.
 * <p>
 * The <code>BERTLV</code> class and the subclasses
 * <code>ConstructedBERTLV</code> and <code>PrimitiveBERTLV</code>, also
 * provide static methods to parse or edit a TLV structure representation in a
 * byte array.
 *
 * @since 2.2.2
 */
public class PrimitiveBERTLV extends BERTLV {

  /**
   * Constructor creates an empty <code>PrimitiveBERTLV</code> object
   * capable of encapsulating a Primitive BER TLV structure.
   * <p>
   * The initial capacity is specified by the numValueBytes argument.
   *
   * @param numValueBytes
   *            is the number of Value bytes to allocate
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INVALID_PARAM</code> if
   *                numValueBytes parameter is negative or larger than the
   *                maximum capacity supported by the implementation.
   *                </ul>
   */
  public PrimitiveBERTLV(short numValueBytes) {}

  /**
   * (Re-)Initializes <code>this</code> <code>PrimitiveBERTLV</code> using
   * the input byte data.
   * <p>
   * The capacity of <code>this</code> <code>PrimitiveBERTLV</code> is
   * increased, if required and supported, to the byte length of the Value
   * represented in the primitive TLV structure of the input byte array. <p>
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
   *                input data is not a well-formed primitive BER TLV
   *                structure.
   *                </ul>
   */
  public short init(byte[] bArray, short bOff, short bLen) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * (Re-)Initializes <code>this</code> <code>PrimitiveBERTLV</code>
   * object with the input tag, length and data. Note that a reference to the
   * BER Tag object is retained by <code>this</code> object. A change in the
   * BER Tag object contents affects <code>this</code> TLV instance.
   * <p>
   * If <code>this</code> primitive TLV object is empty, the initial
   * capacity of <code>this</code>
   * <code>PrimitiveBERTLV</code> is set to
   * the value of the vLen argument.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>vOff+vLen</code><em> is greater than
   * </em><code>vArray.length</code><em>, the length of the
   * </em><code>vArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param tag
   *            a <code>BERTag</code> object
   * @param vArray
   *            the byte array containing length bytes of TLV value
   * @param vOff
   *            offset within the <code>vArray</code> byte array where data
   *            begins
   * @param vLen
   *            byte length of the value data in <code>vArray</code>
   * @return the resulting size of <code>this</code> TLV if represented in
   *         bytes
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset or array
   *                length parameter is negative
   * @exception java.lang.NullPointerException
   *                if either <code>tag</code> or <code>vArray</code>
   *                parameter is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INSUFFICIENT_STORAGE</code> if
   *                the required capacity is not available and the
   *                implementation does not support automatic expansion.
   *                </ul>
   */
  public short init(PrimitiveBERTag tag, byte[] vArray, short vOff, short vLen)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Code shared by both init routines.
   *
   * @param vArray
   *            the byte array containing length bytes of TLV value
   * @param vOff
   *            offset within the <code>vArray</code> byte array where data
   *            begins
   * @param vLen
   *            byte length of the value data in <code>vArray</code>
   */
  private void sharedValueInit(byte[] vArray, short vOff, short vLen) {
    // TODO: Not yet implemented
  }

  /**
   * Appends the specified data to the end of <code>this</code> Primitive
   * BER TLV object.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>vOff+vLen</code><em> is greater than
   * </em><code>vArray.length</code><em>, the length of the
   * </em><code>vArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param vArray
   *            the byte array containing length bytes of TLV value
   * @param vOff
   *            offset within the <code>vArray</code> byte array where data
   *            begins
   * @param vLen
   *            the byte length of the value in the input <code>vArray</code>
   * @return the resulting size of <code>this</code> if represented in bytes
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset or length
   *                parameter is negative
   * @exception java.lang.NullPointerException
   *                if <code>vArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INSUFFICIENT_STORAGE</code> if
   *                the required capacity is not available and the
   *                implementation does not support automatic expansion
   *                <li><code>TLVException.EMPTY_TLV</code> if
   *                <code>this</code> <code>PrimitiveBERTLV</code> object
   *                is empty.
   *                </ul>
   */
  public short appendValue(byte[] vArray, short vOff, short vLen)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Replaces the specified data in place of the current value of
   * <code>this</code> Primitive BER TLV object.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>vOff+vLen</code><em> is greater than
   * </em><code>vArray.length</code><em>, the length of the
   * </em><code>vArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param vArray
   *            the byte array containing length bytes of TLV value
   * @param vOff
   *            offset within the <code>vArray</code> byte array where data
   *            begins
   * @param vLen
   *            the byte length of the value in the input <code>vArray</code>
   * @return the resulting size of <code>this</code> if represented in bytes
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset or length
   *                parameter is negative
   * @exception java.lang.NullPointerException
   *                if <code>vArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INSUFFICIENT_STORAGE</code> if
   *                the required capacity is not available and the
   *                implementation does not support automatic expansion
   *                <li><code>TLVException.EMPTY_TLV</code> if
   *                <code>this</code> <code>PrimitiveBERTLV</code> object
   *                is empty.
   *                </ul>
   */
  public short replaceValue(byte[] vArray, short vOff, short vLen)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /*
   * code shared between appendValue and replaceValue. This has been done
   * because other than the treatment of the length of the value and the offset,
   * the logic is identical.
   * @param vArray the byte array containing length bytes of TLV value
   * @param vOff offset within the <code>vArray</code> byte array where data
   *     begins
   * @param vLen the byte length of the value in the input <code>vArray</code>
   * @param replaceOrAppend If equal APPEND, the value will be appended,
   *     otherwise
   * it will be replaced.
   * @return the resulting size of <code>this</code> if represented in bytes
   * @exception TLVException with the following reason code:<ul>
   * <li><code>TLVException.EMPTY_TLV</code> if <code>this</code>
   * <code>PrimitiveBERTLV</code> object is empty.
   */
  private short setValue(byte[] vArray, short vOff, short vLen,
                         byte replaceOrAppend) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Writes the value (V) part of <code>this</code> Primitive BER TLV object
   * into the output buffer. Returns the length of data written to tlvValue
   * output array
   *
   * @param tlvValue
   *            the output byte array
   * @param tOff
   *            offset within the <code>tlvValue</code> byte array where
   *            output data begins
   * @return the byte length of data written to tlvValue output array
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the output array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>tlvValue</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_SIZE_GREATER_THAN_32767</code>
   *                if the size of the Primitive BER TLV is &gt; 32767
   *                <li><code>TLVException.EMPTY_TLV</code> if
   *                <code>this</code> <code>PrimitiveBERTLV</code> object
   *                is empty.
   *                </ul>
   */
  public short getValue(byte[] tlvValue, short tOff) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns the offset into the specified input byte array of the value (V)
   * part of the BER TLV structure representation in the input array.
   *
   * @param berTLVArray
   *            input byte array
   * @param bTLVOff
   *            offset within byte array containing the TLV data
   * @return the offset into the specified input byte array of the value (V)
   *         part
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>tlvValue</code> or <code>berTLVArray</code>
   *                is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_SIZE_GREATER_THAN_32767</code>
   *                if the size of the Primitive BER TLV is &gt; 32767.
   *                <li><code>TLVException.MALFORMED_TLV</code> if the TLV
   *                representation in the input byte array is not a
   *                well-formed primitive BER TLV structure.
   *                </ul>
   */
  public static short getValueOffset(byte[] berTLVArray, short bTLVOff)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Writes a primitive TLV representation to the specified byte array using
   * as input a Primitive BER tag representation in a byte array and a value
   * representation in another byte array.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>vOff+vLen</code><em> is greater than
   * </em><code>valueArray.length</code><em>, the length of the
   * </em><code>valueArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param berTagArray
   *            input byte array
   * @param berTagOff
   *            offset within byte array containing first byte of tag
   * @param valueArray
   *            input byte array containing primitive value
   * @param vOff
   *            offset within byte array containing the first byte of value
   * @param vLen
   *            length in bytes of the value component of the TLV
   * @param outBuf
   *            output byte array
   * @param bOff
   *            offset within byte array output data begins
   * @return the byte length written to the output array
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input or output arrays would cause access
   *                of data outside array bounds, or if any of the array
   *                offset or array length parameters is negative
   * @exception java.lang.NullPointerException
   *                if <code>berTagArray</code> or <code>valueArray</code>
   *                or <code>outBuf</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_SIZE_GREATER_THAN_32767</code>
   *                if the size of the resulting Primitive BER TLV is &gt;
   * 32767. <li><code>TLVException.MALFORMED_TAG</code> if the tag
   *                representation in the byte array is not a well-formed
   *                constructed array tag.
   *                </ul>
   */
  public static short toBytes(byte[] berTagArray, short berTagOff,
                              byte[] valueArray, short vOff, short vLen,
                              byte[] outBuf, short bOff) {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Appends the specified data to the end of the Primitive TLV representation
   * in the specified byte array. Note that this method is only applicable to
   * a primitive TLV representation, otherwise an exception is thrown.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>vOff+vLen</code><em> is greater than
   * </em><code>vArray.length</code><em>, the length of the
   * </em><code>vArray</code><em> array, an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @param berTLVArray
   *            input byte array
   * @param bTLVOff
   *            offset within byte array containing the TLV data
   * @param vArray
   *            the byte array containing value to be appended
   * @param vOff
   *            offset within the <code>vArray</code> byte array where the
   *            data begins
   * @param vLen
   *            the byte length of the value in the input <code>vArray</code>
   * @return
   * <ul>
   * <li>the resulting size of <code>this</code> if represented in bytes
   * <li>the byte length written to the output array
   * </ul>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input arrays would cause access of data
   *                outside array bounds, or if any of the array offset or
   *                array length parameters is negative
   * @exception java.lang.NullPointerException
   *                if <code>berTLVArray</code> or <code>vArray</code> is
   *                <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TLV_SIZE_GREATER_THAN_32767</code>
   *                if the size of the resulting Primitive BER TLV is &gt;
   * 32767. <li><code>TLVException.MALFORMED_TLV</code> if the TLV
   *                representation in the input byte array is not a
   *                well-formed primitive BER TLV structure
   *                </ul>
   */
  public static short appendValue(byte[] berTLVArray, short bTLVOff,
                                  byte[] vArray, short vOff, short vLen)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }
}
