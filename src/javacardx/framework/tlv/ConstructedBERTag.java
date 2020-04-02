/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.tlv;

/**
 * The <code>ConstructedBERTag</code> class encapsulates a constructed BER TLV
 * tag. The rules on the allowed encoding of the Tag field is based on the ASN.1
 * BER encoding rules of ISO/IEC 8825-1:2002.
 * <p>
 * The <code>BERTag</code> class and the subclasses
 * <code>ConstructedBERTag</code> and <code>PrimitiveBERTag</code>, also
 * provide static methods to parse or edit a BER Tag structure representation in
 * a byte array.
 *
 * @since 2.2.2
 */
public final class ConstructedBERTag extends BERTag {

  /**
   * Constructor creates an empty constructed BERTLV Tag object capable of
   * encapsulating a constructed BER TLV Tag. All implementations must support
   * at least 3 byte Tags which can encode tag numbers up to 0x3FFF.
   */
  public ConstructedBERTag() {}

  /**
   * (Re-)Initialize <code>this</code> <code>ConstructedBERTag</code>
   * object with the specified tag class, and tag number. All implementations
   * must support tag numbers up to 0x3FFF.
   *
   * @param tagClass
   *            encodes the tag class. Valid codes listed in BER_TAG_CLASS_..
   *            constants.
   * @see javacardx.framework.tlv.BERTag BERTag
   * @param tagNumber
   *            is the tag number.
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the tag
   *                number requested is larger than the supported maximum size
   *                <li><code>TLVException.INVALID_PARAM</code> if tag
   *                class parameter is invalid or if the tag number parameter
   *                is negative.
   *                </ul>
   */
  public void init(byte tagClass, short tagNumber) throws TLVException {
    // TODO: Not yet implemented
  }

  /**
   * (Re-)Initialize <code>this</code> <code>ConstructedBERTag</code>
   * object from the binary representation in the byte array. All
   * implementations must support tag numbers up to 0x3FFF.
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
   *                representation in the byte array is malformed or is a
   *                primitive array tag
   *                </ul>
   */
  public void init(byte[] bArray, short bOff) throws TLVException {
    // TODO: Not yet implemented
  }
}
