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
 * The <code>ConstructedBERTLV</code> class encapsulates a constructed BER TLV
 * structure. It extends the generic BER TLV class. The rules on the allowed
 * encoding of the Tag, length and value fields is based on the ASN.1 BER
 * encoding rules ISO/IEC 8825-1:2002.
 * <p>
 * The <code>ConstructedBERTLV</code> class only supports encoding of the
 * length(L) octets in definite form. The value(V) field which encodes the
 * contents octets are merely viewed as a set of other BERTLVs.
 * <p>
 * Every <code>ConstructedBERTLV</code> has a capacity which represents the
 * size of the allocated internal data structures to reference all the contained
 * BER TLV objects. As long as the number of contained BER TLV objects of the
 * <code>ConstructedBERTLV</code> does not exceed the capacity, it is not
 * necessary to allocate new internal data. If the internal buffer overflows,
 * and the implementation supports automatic expansion which might require new
 * data allocation and possibly old data/object deletion, it is automatically
 * made larger. Otherwise a TLVException is thrown.
 * <p>
 * The BERTLV class and the subclasses <code>ConstructedBERTLV</code> and
 * <code>PrimitiveBERTLV</code>, also provide static methods to parse or edit
 * a TLV structure representation in a byte array.
 *
 * @since 2.2.2
 */

public final class ConstructedBERTLV extends BERTLV {

  /**
   * Constructor creates an empty <code>ConstructedBERTLV</code> object
   * capable of encapsulating a <code>ConstructedBERTLV</code> structure.
   * <p>
   * The initial capacity is specified by the numTLVs argument.
   *
   * @param numTLVs
   *            is the number of contained TLVs to allocate
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INVALID_PARAM</code> if numTLVs
   *                parameter is negative or larger than the maximum capacity
   *                supported by the implementation.
   *                </ul>
   */
  public ConstructedBERTLV(short numTLVs) {}

  /**
   * (Re-)Initializes <code>this</code> <code>ConstructedBERTLV</code>
   * using the input byte data.
   * <p>
   * If <code>this</code> <code>ConstructedBERTLV</code> is not empty,
   * internal references to the previously contained BER TLV objects is
   * removed.
   * <p>
   * Each contained <code>BERTLV</code> is constructed and initialized using
   * this init method. The initial capacity of each of the contained
   * <code>ConstructedBERTLV</code> objects is set to the number of TLVs
   * contained at the top level of that TLV structure in the byte array.
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
   *                <li><code>TLVException.ILLEGAL_SIZE</code> if the
   *                required capacity is not available and the implementation
   *                does not support automatic expansion.
   *                <li><code>TLVException.MALFORMED_TLV</code> if the
   *                input data is not a well-formed constructed BER TLV
   *                structure.
   *                </ul>
   */
  public short init(byte[] bArray, short bOff, short bLen) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * (Re-)Initializes <code>this</code> <code>ConstructedBERTLV</code>
   * object with the input tag and TLV parameter. Note that a reference to the
   * BER Tag object parameter is retained by <code>this</code> object. If
   * the input BER Tag object is modified, the TLV structure encapsulated by
   * <code>this</code> TLV instance is also modified. Similarly, a reference
   * to the BER TLV object parameter is also retained by <code>this</code>
   * object. If the input BER TLV object is modified, the TLV structure
   * encapsulated by <code>this</code> TLV instance is also modified.
   *
   * @param tag
   *            a <code>BERTag</code> object
   * @param aTLV
   *            to use to initialize as the value of <code>this</code> TLV
   * @return the resulting size of <code>this</code> TLV if represented in
   *         bytes
   * @exception java.lang.NullPointerException
   *                if either <code>tag</code> or <code>aTLV</code> is
   *                <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INSUFFICIENT_STORAGE</code> if
   *                the required capacity is not available and the
   *                implementation does not support automatic expansion
   *                <li><code>TLVException.INVALID_PARAM</code> if
   *                <code>aTLV</code> is <code>this</code> or
   *                <code>this</code> TLV object is contained in any of the
   *                constructed TLV objects in the hierarchy of the
   *                <code>aTLV</code> object.
   *                </ul>
   */
  public short init(ConstructedBERTag tag, BERTLV aTLV) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * (Re-)Initializes <code>this</code> <code>ConstructedBERTLV</code>
   * object with the input tag and specified data as value of the object. Note
   * that a reference to the BER Tag object is retained by <code>this</code>
   * object. If the input BER Tag object is modified, the TLV structure
   * encapsulated by <code>this</code> TLV instance is also modified.
   * <p>
   * Each contained <code>BERTLV</code> is constructed and initialized using
   * this init method. The initial capacity of each of the contained
   * <code>ConstructedBERTLV</code> objects is set to the number of TLVs
   * contained at the top level of that TLV structure in the byte array.
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
   *            a BERTag object
   * @param vArray
   *            the byte array containing <code>vLen</code> bytes of TLV
   *            Value
   * @param vOff
   *            offset within the vArray byte array where data begins
   * @param vLen
   *            byte length of the value data in vArray
   * @return the resulting size of <code>this</code> TLV if represented in
   *         bytes
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds, or if the array offset or array
   *                length parameter is negative
   * @exception java.lang.NullPointerException
   *                if either <code>tag</code> or <code>vArray</code> is
   *                <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INSUFFICIENT_STORAGE</code> or
   *                if the required capacity is not available and the
   *                implementation does not support automatic expansion.
   *                </ul>
   */
  public short init(ConstructedBERTag tag, byte[] vArray, short vOff,
                    short vLen) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Append the specified TLV to the end of <code>ConstructedBERTLV</code>.
   * Note that a reference to the BER TLV object parameter is retained by
   * <code>this</code> object. A change in the BER TLV object contents
   * affects <code>this</code> TLV instance.
   *
   * @param aTLV
   *            a BER TLV object
   * @return the resulting size of <code>this</code> TLV if represented in
   *         bytes
   * @exception java.lang.NullPointerException
   *                if <code>aTLV</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INSUFFICIENT_STORAGE</code> if
   *                the required capacity is not available and the
   *                implementation does not support automatic expansion.
   *                <li><code>TLVException.INVALID_PARAM</code> if
   *                <code>aTLV</code> is <code>this</code> or
   *                <code>this</code> TLV object is contained in any of the
   *                constructed TLV objects in the hierarchy of the
   *                <code>aTLV</code> object.
   *                </ul>
   */
  public short append(BERTLV aTLV) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Delete the specified occurrence of the specified BER TLV from
   * <code>this</code> <code>ConstructedBERTLV</code>. The internal
   * reference at the specified occurrence to the specified BER TLV object is
   * removed.
   *
   * @exception java.lang.NullPointerException
   *                if <code>aTLV</code> is <code>null</code>
   * @param aTLV
   *            the BER TLV object to delete from <code>this</code>
   * @param occurrenceNum
   *            specifies which occurrence of <code>aTLV</code> within
   *            <code>this</code> BER TLV to use
   * @return the resulting size of <code>this</code> TLV if represented in
   *         bytes
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INVALID_PARAM</code> if the
   *                specified BER TLV object parameter is not an element of
   *                <code>this</code> or occurs less than
   *                <code>occurrenceNum</code> times in <code>this</code>
   *                or <code>occurrenceNum</code> is 0 or negative.
   *                </ul>
   */
  public short delete(BERTLV aTLV, short occurrenceNum)throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Find the contained <code>BERTLV</code> within
   * <code>this</code> <code>ConstructedBERTLV</code> object that matches
   * the specified BER Tag. If the tag parameter is <code>null</code>, the
   * first contained BER TLV object is returned.
   *
   * @param tag
   *            the <code>BERTag</code> to be found
   * @return TLV object matching the indicated tag or null if none found.
   */
  public BERTLV find(BERTag tag) {
    // TODO: Not yet implemented
    return null;
  }

  /**
   * Find the next contained <code>BERTLV</code> within
   * <code>this</code> <code>ConstructedBERTLV</code> object that matches
   * the specified BER Tag. The search must be started from the TLV position
   * following the specified occurrence of the specified BER TLV object
   * parameter. If the tag parameter is null, the next contained BER TLV
   * object is returned.
   *
   * @param tag
   *            the BERTag to be found
   * @param aTLV
   *            tlv object contained within <code>this</code> BER TLV
   *            following which the search begins
   * @param occurrenceNum
   *            specifies which occurrence of <code>aTLV</code> within
   *            <code>this</code> BER TLV to use
   * @return TLV object matching the indicated tag or null if none found.
   * @exception java.lang.NullPointerException
   *                if <code>aTLV</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.INVALID_PARAM</code> if the
   *                specified BER TLV object parameter is not an element of
   *                <code>this</code> or occurs less than
   *                <code>occurrenceNum</code> times in <code>this</code>
   *                or if <code>occurrenceNum</code> is 0 or negative.
   *                </ul>
   */
  public BERTLV findNext(BERTag tag, BERTLV aTLV, short occurrenceNum) {
    // TODO: Not yet implemented
    return null;
  }

  /**
   * Append the TLV representation in the specified byte array to the
   * constructed BER tlv representation in the specified output byte array.
   *
   * @param berTLVInArray
   *            input byte array
   * @param bTLVInOff
   *            offset within byte array containing the tlv data
   * @param berTLVOutArray
   *            output TLV byte array
   * @param bTLVOutOff
   *            offset within byte array where output begins
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input or output array would cause access
   *                of data outside array bounds, or if either array offset
   *                parameter is negative
   * @exception java.lang.NullPointerException
   *                if either <code>berTLVInArray</code> or
   *                <code>berTLVOutArray</code> is <code>null</code>
   * @return the size of the resulting output TLV
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.MALFORMED_TLV</code> if the TLV
   *                representation in the input byte array is not a
   *                well-formed constructed BER TLV.
   *                </ul>
   */
  public static short append(byte[] berTLVInArray, short bTLVInOff,
                             byte[] berTLVOutArray, short bTLVOutOff)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Find the offset of the contained TLV representation at the top level
   * within the TLV structure representation in the specified byte array that
   * matches the specified tag representation in the specified byte array If
   * the tag array parameter is null, the offset of the first contained TLV is
   * returned.
   *
   * @param berTLVArray
   *            input byte array
   * @param bTLVOff
   *            offset within byte array containing the tlv data
   * @param berTagArray
   *            byte array containing the Tag to be searched
   * @param bTagOff
   *            offset within <code>berTagArray</code> byte array where tag
   *            data begins
   * @return offset into <code>berTLVArray</code> where the indicated tag
   *         was found or -1 if none found.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input arrays would cause access of data
   *                outside array bounds, or if either array offset parameter
   *                is negative
   * @exception java.lang.NullPointerException
   *                if <code>berTLVArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.MALFORMED_TLV</code> if the TLV
   *                representation in the specified byte array is not a
   *                well-formed constructed BER TLV structure.
   *                <li><code>TLVException.MALFORMED_TAG</code> if tag
   *                representation in the specified byte array is is not a
   *                well-formed BER Tag structure.
   *                </ul>
   */
  public static short find(byte[] berTLVArray, short bTLVOff,
                           byte[] berTagArray, short bTagOff)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Find the offset of the next contained TLV representation at the top level
   * within the TLV structure representation in the specified byte array that
   * matches the specified tag representation in the specified byte array. The
   * search must be started from the TLV position following the specified
   * <code>startOffset</code> parameter where a contained TLV exists at the
   * top level. If the tag array parameter - <code>berTagArray</code> - is
   * null, the offset of the next contained TLV representation at the top
   * level is returned.
   *
   * @param berTLVArray
   *            input byte array
   * @param bTLVOff
   *            offset within byte array containing the TLV data
   * @param startOffset
   *            offset within the input <code>berTLVArray</code> to begin
   *            the search
   * @param berTagArray
   *            byte array containing the Tag to be searched
   * @param bTagOff
   *            offset within <code>berTagArray</code> byte array where tag
   *            data begins
   * @return offset into <code>berTLVArray</code> where the indicated tag
   *         was found or -1 if none found.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input arrays would cause access of data
   *                outside array bounds, or if any of the array offset
   *                parameters is negative
   * @exception java.lang.NullPointerException
   *                if <code>berTLVArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.MALFORMED_TLV</code> if the TLV
   *                representation in the specified byte array is not a
   *                well-formed constructed BER TLV structure.
   *                <li><code>TLVException.MALFORMED_TAG</code> if the tag
   *                representation in the specified byte array is not a
   *                well-formed BER Tag structure.
   *                <li><code>TLVException.INVALID_PARAM</code> if the
   *                <code>berTLVArray</code> array does not contain a top
   *                level contained TLV element at the specified
   *                <code>startOffset</code> offset.
   *                </ul>
   */
  public static short findNext(byte[] berTLVArray, short bTLVOff,
                               short startOffset, byte[] berTagArray,
                               short bTagOff) throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * The private internal method for find/findNext. Parameters and returns are
   * the same as for findNext except for an additional paramter to indicate if
   * this method is being called by find or findNext. The code for find and
   * findNext is very similar except for very small differences. The
   * additional parameter is calledFromMethod.
   *
   * @param berTLVArray
   *            input byte array
   * @param bTLVOff
   *            offset within byte array containing the TLV data
   * @param startOffset
   *            offset within the input <code>berTLVArray</code> to begin
   *            the search
   * @param berTagArray
   *            byte array containing the Tag to be searched
   * @param bTagOff
   *            offset within <code>berTagArray</code> byte array where tag
   *            data begins
   * @param calledFromMethod
   *            As indicated above, this is the only different parameter from
   *            findNext and indicates what method is calling this method.
   * @return offset into <code>berTLVArray</code> where the indicated tag
   *         was found or -1 if none found.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input arrays would cause access of data
   *                outside array bounds, or if any of the array offset
   *                parameters is negative
   * @exception java.lang.NullPointerException
   *                if <code>berTLVArray</code> is <code>null</code>
   * @exception TLVException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TLVException.MALFORMED_TLV</code> if the TLV
   *                representation in the specified byte array is not a
   *                well-formed constructed BER TLV structure.
   *                <li><code>TLVException.MALFORMED_TAG</code> if the tag
   *                representation in the specified byte array is not a
   *                well-formed BER Tag structure.
   *                <li><code>TLVException.INVALID_PARAM</code> if the
   *                <code>berTLVArray</code> array does not contain a top
   *                level contained TLV element at the specified
   *                <code>startOffset</code> offset.
   */

  private static short findInternal(byte[] berTLVArray, short bTLVOff,
                                    short startOffset, byte[] berTagArray,
                                    short bTagOff, short calledFromMethod)
      throws TLVException {
    // TODO: Not yet implemented
    return 0;
  }
}
