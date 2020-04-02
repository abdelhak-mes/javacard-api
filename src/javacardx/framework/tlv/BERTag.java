/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.tlv;

/**
 * The abstract <code>BERTag</code> class encapsulates a BER TLV tag. The
 * rules on the allowed encoding of the Tag field are based on the ASN.1 BER
 * encoding rules of ISO/IEC 8825-1:2002.
 * <p>
 * The <code>BERTag</code> class and the subclasses
 * <code>ConstructedBERTag</code> and <code>PrimitiveBERTag</code>, also
 * provide static methods to parse or edit a BER Tag structure representation in
 * a byte array.
 *
 * @since 2.2.2
 */
public abstract class BERTag {

  /**
   * Constant for BER Tag Class Universal
   */
  public static final byte BER_TAG_CLASS_MASK_UNIVERSAL = (byte)0;

  /**
   * Constant for BER Tag Class Application
   */
  public static final byte BER_TAG_CLASS_MASK_APPLICATION = (byte)1;

  /**
   * Constant for BER Tag Class Context-Specific
   */
  public static final byte BER_TAG_CLASS_MASK_CONTEXT_SPECIFIC = (byte)2;

  /**
   * Constant for BER Tag Class Private
   */
  public static final byte BER_TAG_CLASS_MASK_PRIVATE = (byte)3;

  /**
   * Constant for constructed BER Tag type
   */
  public static final boolean BER_TAG_TYPE_CONSTRUCTED = true;

  /**
   * Constant for primitive BER Tag type
   */
  public static final boolean BER_TAG_TYPE_PRIMITIVE = false;

  /**
   * Constructor creates an empty <code>BERTLV</code> Tag object capable of
   * encapsulating a BER TLV Tag. All implementations must support at least 3
   * byte Tags which can encode tag numbers up to 0x3FFF.
   */
  protected BERTag() {}

  /**
   * Abstract init method. (Re-)Initialize
   * <code>this</code> <code>BERTag</code> object from the binary
   * representation in the byte array. All implementations must support tag
   * numbers up to 0x3FFF.
   *
   * @param bArray
   *            the byte array containing the binary representation
   * @param bOff
   *            the offset within bArray where the tag binary begins
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the tag
   *                number requested is larger than the supported maximum size
   *                <li><code>TLVException.MALFORMED_TAG</code> if tag
   *                representation in the byte array is malformed
   *                </ul>
   */
  public abstract void init(byte[] bArray, short bOff) throws TLVException;

  /**
   * Create a <code>BERTLV</code> Tag object from the binary representation
   * in the byte array. All implementations must support tag numbers up to
   * 0x3FFF. Note that the returned <code>BERTag</code> must be cast to the
   * correct subclass: <code>PrimitiveBERTag</code> or
   * <code>ConstructedBERTag</code> to access their specialized API.
   *
   * @param bArray
   *            the byte array containing the binary representation
   * @param bOff
   *            the offset within bArray where the tag binary begins
   * @return BERTag value
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the tag
   *                number requested is larger than the supported maximum size
   *                <li><code>TLVException.MALFORMED_TAG</code> if tag
   *                representation in the byte array is malformed.
   *                </ul>
   */
  public static BERTag getInstance(byte[] bArray, short bOff)
      throws TLVException {
    // TODO: Not yet implemented
    return null;
  }

  /**
   * Returns the byte size required to represent <code>this</code> tag
   * structure
   *
   * @return size of BER Tag in bytes
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TAG_SIZE_GREATER_THAN_127</code>
   *                if the size of the BER Tag is &gt; 127.
   *                <li><code>TLVException.EMPTY_TAG</code> if the BER Tag
   *                is empty.
   *                </ul>
   */
  public byte size() throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Writes the representation of <code>this</code> BER tag structure to the
   * byte array
   *
   * @param outBuf
   *            the byteArray where the BER tag is written
   * @param bOffset
   *            offset within outBuf where BER tag value starts
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the output array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>outBuf</code> is <code>null</code>
   * @return size of BER Tag in bytes
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.EMPTY_TAG</code> if the BER Tag
   *                is empty.
   *                </ul>
   */
  public short toBytes(byte[] outBuf, short bOffset) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns the tag number part of <code>this</code> BER Tag structure
   *
   * @return the BER Tag tag number
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.TAG_NUMBER_GREATER_THAN_32767</code>
   *                if the tag number is &gt; 32767.
   *                <li><code>TLVException.EMPTY_TAG</code> if the BER Tag
   *                is empty.
   *                </ul>
   */
  public short tagNumber() throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Used to query if <code>this</code> BER tag structure is constructed
   *
   * @return <code>true</code> if constructed, <code>false</code> if
   *         primitive
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.EMPTY_TAG</code> if the BER Tag
   *                is empty.
   *                </ul>
   */
  public boolean isConstructed() {
    // TODO: Not yet implemented
    return false;
  }

  /**
   * Returns the tag class part of <code>this</code> BER Tag structure
   *
   * @return the BER Tag class. One of the <code>BER_TAG_CLASS_MASK_*</code>..
   *         constants defined above, for example
   *         {@link #BER_TAG_CLASS_MASK_APPLICATION
   * BER_TAG_CLASS_MASK_APPLICATION}.
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.EMPTY_TAG</code> if the BER Tag
   *                is empty.
   *                </ul>
   */
  public byte tagClass() {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Compares <code>this</code> BER Tag with another. Note that this method
   * does not throw exceptions. If the parameter <code>otherTag</code> is
   * <code>null</code>, the method returns <code>false</code>
   *
   * @param otherTag value to compare
   *
   * @return <code>true</code> if the tag data encapsulated are equal,
   *         <code>false</code> otherwise
   */
  public boolean equals(BERTag otherTag) {
    // TODO: Not yet implemented
    return false;
  }

  @Override
  public boolean equals(Object otherTag) {
    return false;
  }

  /**
   * Writes the BER Tag bytes representing the specified tag class,
   * constructed flag and the tag number as a BER Tag representation in the
   * specified byte array
   *
   * @param tagClass
   *            encodes the tag class. Valid codes are the
   *            <code>BER_TAG_CLASS_MASK_*</code> constants defined above, for
   * example
   *            {@link #BER_TAG_CLASS_MASK_APPLICATION
   * BER_TAG_CLASS_MASK_APPLICATION}.
   * @param isConstructed
   *            true if the tag is constructed, false if primitive
   * @param tagNumber
   *            is the tag number.
   * @param outArray
   *            output byte array
   * @param bOff
   *            offset within byte array containing first byte
   * @return size of BER Tag output bytes
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the output array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>outArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the tag
   *                size is larger than the supported maximum size or 32767
   *                <li><code>TLVException.INVALID_PARAM</code> if
   *                <code>tagClass</code> parameter is invalid or if the
   *                <code>tagNumber</code> parameter is negative
   *                </ul>
   */
  public static short toBytes(short tagClass, boolean isConstructed,
                              short tagNumber, byte[] outArray, short bOff) {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns the byte size required to represent the BER Tag from its
   * representation in the specified byte array
   *
   * @param berTagArray
   *            input byte array containing the BER Tag representation
   * @param bOff
   *            offset within byte array containing first byte
   * @return size of BER Tag in bytes
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>berTagArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the size
   *                of the BER Tag is greater than the maximum Tag size
   *                supported
   *                <li><code>TLVException.TAG_SIZE_GREATER_THAN_127</code>
   *                if the size of the BER Tag is &gt; 127.
   *                <li><code>TLVException.MALFORMED_TAG</code> if tag
   *                representation in the byte array is malformed
   *                </ul>
   */
  public static byte size(byte[] berTagArray, short bOff) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns the tag number part of the BER Tag from its representation in the
   * specified byte array
   *
   * @param berTagArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte
   * @return the BER Tag tag number
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>berTagArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the size
   *                of the BER Tag is greater than the maximum Tag size
   *                supported
   *                <li><code>TLVException.TAG_NUMBER_GREATER_THAN_32767</code>
   *                if the tag number is &gt; 32767.
   *                <li><code>TLVException.MALFORMED_TAG</code> if tag
   *                representation in the byte array is malformed.
   *                </ul>
   */
  public static short tagNumber(byte[] berTagArray, short bOff)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns the constructed flag part of the BER Tag from its representation
   * in the specified byte array
   *
   * @param berTagArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte
   * @return <code>true</code> if constructed, <code>false</code> if
   *         primitive
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>berTagArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.MALFORMED_TAG</code> if tag
   *                representation in the byte array is malformed.
   *                </ul>
   */
  public static boolean isConstructed(byte[] berTagArray, short bOff) {
    // TODO: Not yet implemented
    return false;
  }

  /**
   * Returns the tag class part of the BER Tag from its representation in the
   * specified byte array
   *
   * @param berTagArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte
   * @return the BER Tag class. One of the <code>BER_TAG_CLASS_MASK_*</code>..
   *         constants defined above, for example
   *         {@link #BER_TAG_CLASS_MASK_APPLICATION
   * BER_TAG_CLASS_MASK_APPLICATION}.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>berTagArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.MALFORMED_TAG</code> if tag
   *                representation in the byte array is malformed.
   *                </ul>
   */
  public static byte tagClass(byte[] berTagArray, short bOff) {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Checks if the input data is a well-formed BER Tag representation
   *
   * @param berTagArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte
   * @return <code>true</code> if input data is a well formed BER Tag
   *         structure of tag size equal to or less than the supported maximum
   *         size, <code>false</code> otherwise
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset parameter is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>berTagArray</code> is <code>null</code>
   */
  public static boolean verifyFormat(byte[] berTagArray, short bOff) {
    // TODO: Not yet implemented
    return false;
  }
}
