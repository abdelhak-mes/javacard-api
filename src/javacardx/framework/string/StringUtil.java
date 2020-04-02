/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.string;

import javacard.framework.Util;

/**
 * This class provides methods for handling UTF-8 encoded character sequences
 * (strings). This class also provides methods to convert UTF-8 encoded
 * character strings to and from other character encodings, such as UCS-2, the
 * GSM 7-bit character set and UTF-16. Support for character encodings other
 * than UTF-8 is optional. Proprietary extensions to the supported character
 * encodings must be identified starting from {@link #PROP_ENCODING_EXT}. An
 * implementation must throw a {@link StringException} with reason {@link
 * StringException#UNSUPPORTED_ENCODING} if a requested character encoding is
 * not supported.
 * <p>
 * UTF-8 encodes each of the code points in the Unicode character set using one
 * to four 8-bit bytes. Unicode code points are the numerical values that make
 * up the Unicode code space. The Unicode Standard, version 4.0 is  available
 * from the Unicode Consortium at <a
 * href="http://www.unicode.org">http://www.unicode.org</a>. The UTF-8
 * transformation format is specified by <a
 * href="http://tools.ietf.org/rfc/rfc3629.txt">RFC 3629</a>.
 * <p>
 * The encoded character sequences handled by this class are stored in byte
 * arrays. Each string or character sequence is designated by a byte array, an
 * offset in the byte array indicating the start of the character sequence and a
 * length indicating the length in bytes of the character sequence. If a
 * designated character sequence is outside the bounds of its containing array
 * an {@link ArrayIndexOutOfBoundsException} is thrown (note: for readablity
 * reasons, these exceptions are assumed and not systematically documented in
 * the methods of this class).
 * <br>
 * An index of a character or substring within a string is always relative to
 * the begining of the string; that is relative to the offset of the string
 * within the containing byte array. If a provided index of a character or
 * substring within a designated  character sequence is outside the bounds of
 * the designated character sequence an {@link IndexOutOfBoundsException} is
 * thrown.
 * <p>
 * This class provides two categories of methods:
 * <dl>
 * <dt>Methods for dealing with plain byte sequences</dt>
 * <dd>
 * These methods do not check the (UTF-8) well-formedness of the
 * byte sequences passed as parameters; in particular, they do not check whether
 * the byte at a provided offset is the first byte of a valid UTF-8 byte
 * sequence or if a provided length is equal or greater than the length of the
 * UTF-8 byte sequence starting at the provided offset (as can be determined by
 * its first byte). They only treat UTF-8 byte sequences as plain byte sequences
 * for the purpose of comparison, copying, truncating... As an example of such
 * methods, see the {@link #indexOf(byte[], short, short, byte[], short, short)
 * indexOf} method.
 * </dd>
 * <dt>Methods for dealing with Unicode code points (i.e., Unicode
 * characters)</dt> <dd> These methods check the well-formedness of UTF-8 byte
 * sequences. They either throw a {@link StringException} with reason {@link
 * StringException#INVALID_BYTE_SEQUENCE} when encountering an ill-formed UTF-8
 * byte sequence; as an example of such methods, see the {@link
 * #convertTo(byte[], short, short, byte[], short, byte) convertTo} method.
 * <br>
 * Or they consider any byte sequence that is not well-formed (eg. incomplete)
 * within a designated text range as a code point for the purpose of counting,
 * comparing or returning; as an example of such methods, see the {@link
 * #codePointCount(byte[], short, short) codePointCount} method.
 * </dd>
 * </dl>
 * To ensure that a character or character sequence is a valid UTF-8 character
 * or character sequence, the {@link #check(byte[], short, short) check} method
 * should be used. <p> Because Unicode case conversion may require
 * locale-sensitive mappings, context-sensitive mappings, and 1:M character
 * mappings, and in order to limit footprint case conversion supported by the
 * methods {@link #toLowerCase(byte[], short, short, byte[], short)
 * toLowerCase},
 * {@link #toUpperCase(byte[], short, short, byte[], short) toUpperCase} and
 * {@link #compare(boolean, byte[], short, short, byte[], short, short) compare}
 * is only available by default for the Basic Latin Unicode block (US-ASCII
 * character set: U+0000 - U+007F). Other character blocks may be supported.
 *
 * @since Java Card 3.0.4
 */
public final class StringUtil {

  /**
   * The UTF-8 character encoding. This character encoding is used for internal
   * representation and handling of character strings.
   */
  public static final byte UTF_8 = (byte)0x01;

  /**
   * The UTF-16 character encoding. This character encoding may be optionally
   * supported for conversion to and from UTF-8 when doing input or output.
   */
  public static final byte UTF_16 = (byte)0x02;

  /**
   * The UTF-16LE (Little Endian) character encoding. This character encoding
   * may be optionally supported for conversion to and from UTF-8 when doing
   * input or output.
   */
  public static final byte UTF_16_LE = (byte)0x03;

  /**
   * The UTF-16BE (Big Endian) character encoding. This character encoding may
   * be optionally supported for conversion to and from UTF-8 when doing input
   * or output.
   */
  public static final byte UTF_16_BE = (byte)0x04;

  /**
   * The UCS-2 character encoding. This character encoding may be optionally
   * supported for conversion to and from UTF-8 when doing input or output.
   */
  public static final byte UCS_2 = (byte)0x05;

  /**
   * The GSM Septet character encoding. This character encoding may be
   * optionally supported for conversion to and from UTF-8 when doing input or
   * output.
   */
  public static final byte GSM_7 = (byte)0x06;

  /**
   * The ISO 8859-1 (Latin-1) character encoding. This character encoding may be
   * optionally supported for conversion to and from UTF-8 when doing input or
   * output.
   */
  public static final byte ISO_8859_1 = (byte)0x07;

  /**
   * Start of proprietary character encoding numbering.
   */
  public static final byte PROP_ENCODING_EXT = (byte)0x40;

  /**
   * Returns the number of characters (Unicode code points) in the UTF-8
   * encoded character sequence designated by <code>aString</code>,
   * <code>offset</code> and <code>length</code>. Ill-formed or incomplete byte
   * sequences within the text range count as one code point each.
   *
   * @param aString the byte array containing the UTF-8 encoded character
   *     sequence.
   * @param offset the starting offset of the character sequence in the byte
   *     array.
   * @param length the length (in bytes) of the contained character sequence.
   * @return the number of characters (Unicode code points) in the designated
   *     UTF-8 encoded character sequence.
   * @throws NullPointerException
   *                if <code>aString</code> is <code>null</code>.
   */
  public static short codePointCount(byte[] aString, short offset,
                                     short length) {
    // TODO: Not yet implemented !!!
    return 0;
  }

  /**
   * Copies to the destination buffer the character (Unicode code point) at the
   * specified index in the UTF-8 encoded character sequence designated by
   * <code>aString</code>, <code>offset</code> and <code>length</code>.
   * <code>index</code> is an index in the byte array relative to the offset of
   * the designated character sequence within the byte array (that is relative
   * to <code>offset</code>). The resulting code point copied to the destination
   * array is UTF-8 encoded and is therefore from one to four byte long.
   * Ill-formed or incomplete byte sequences within the text range counting as
   * one code point each, they are returned as-is.
   *
   * @param aString the byte array containing the UTF-8 encoded reference
   *     character sequence.
   * @param offset the starting offset of the reference character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the reference character sequence.
   * @param index the byte index (relative to <code>offset</code>) of the
   *     character to be returned.
   * @param dstBuffer the byte array for copying the resulting character.
   * @param dstOffset the starting offset in <code>dstBuffer</code> for copying
   *     the UTF-8 byte
   * sequence of the character (Unicode code point) at the specified index.
   * @return the number of bytes copied.
   * @throws IndexOutOfBoundsException if <code>index</code> is negative or not
   *     less than the length of <code>aString</code>.
   * @throws NullPointerException
   *                if <code>aString</code> or <code>dstBuffer</code> is
   * <code>null</code>.
   */
  public static short codePointAt(byte[] aString, short offset, short length,
                                  short index, byte[] dstBuffer,
                                  short dstOffset) {
    // TODO: Not yet implemented !!!
    return 0;
  }

  /**
   * Copies to the destination buffer the character (Unicode code point) before
   * the specified index in the UTF-8 encoded character sequence designated by
   * <code>aString</code>, <code>offset</code> and <code>length</code>.
   * <code>index</code> is an index in the byte array relative to the offset of
   * the designated character sequence within the byte array (that is relative
   * to <code>offset</code>). The resulting code point copied to the destination
   * array is UTF-8 encoded and is therefore from one to four byte long.
   * Ill-formed or incomplete byte sequences within the text range counting as
   * one code point each, they are returned as-is.
   *
   * @param aString the byte array containing the UTF-8 encoded character
   *     sequence.
   * @param offset the starting offset of the reference character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the reference character sequence.
   * @param index the byte index (relative to <code>offset</code>) following the
   *     character to be returned.
   * @param dstBuffer the byte array for copying the resulting character.
   * @param dstOffset the starting offset in <code>dstBuffer</code> for copying
   *     the UTF-8 byte sequence of the character (Unicode code point) before
   *     the specified index.
   * @return the number of bytes copied.
   * @throws IndexOutOfBoundsException if <code>index</code> is less than 1 or
   *     greater than the length of <code>aString</code>.
   * @throws NullPointerException
   *                if <code>aString</code> or <code>dstBuffer</code> is
   * <code>null</code>.
   */
  public static short codePointBefore(byte[] aString, short offset,
                                      short length, short index,
                                      byte[] dstBuffer, short dstOffset) {
    // TODO: Not yet implemented !!!
    return 0;
  }

  /**
   * Returns the byte index within the UTF-8 encoded character sequence
   * designated by <code>aString</code>, <code>offset</code> and
   * <code>length</code> that is offset from the given <code>index</code> by
   * <code>codePointOffset</code> code points. Ill-formed or incomplete UTF-8
   * byte sequences within the text range given by <code>index</code> and
   * <code>codePointOffset</code> count as one code point each. <p> This method
   * can be used to extract a substring from a string. For example, to copy to
   * <code>buffer</code> the substring of the string <code>aString</code> that
   * begins at <code>codePointBeginIndex</code> and extends to the character at
   * index <code>codePointEndIndex - 1</code>, one can call: <pre> short
   * beginOffset = StringUtil.offsetByCodePoints(aString, offset, length,
   * (short) 0, codePointBeginIndex); short endOffset =
   * StringUtil.offsetByCodePoints(aString, offset, length, (short) 0,
   * codePointEndIndex); short l = Util.arrayCopy(aString, beginOffset, buffer,
   * 0, (short) (endOffset - beginOffset));
   * </pre>
   * The copied substring thus has a length in codepoints (that is a codepoint
   * count) equal to <code>codePointEndIndex - codePointbeginIndex</code>.
   *
   * @param aString the byte array containing the UTF-8 encoded character
   *     sequence.
   * @param offset the starting offset of the reference character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the reference character sequence.
   * @param index the byte index to be offset (relative to <code>offset</code>).
   * @param codePointOffset the offset in code points.
   * @return the index within <code>aString</code> relative to the begining of
   *     the string contained in <code>aString</code>
   * that is, relative to <code>offset</code>.
   * @throws IndexOutOfBoundsException if <code>index</code> is negative or
   *     larger than the length of <code>aString</code>,
   * or if <code>codePointOffset</code> is positive and the substring starting
   * with <code>index</code> has fewer than <code>codePointOffset</code> code
   * points, or if <code>codePointOffset</code> is negative and the substring
   * before <code>index</code> has fewer than the absolute value of
   * <code>codePointOffset</code> code points.
   * @throws NullPointerException
   *                if <code>aString</code> is <code>null</code>.
   */
  public static short offsetByCodePoints(byte[] aString, short offset,
                                         short length, short index,
                                         short codePointOffset) {
    // TODO: Not yet implemented !!!
    return 0;
  }

  /**
   * Compares two strings lexicographically, optionally ignoring case
   * considerations. The comparison is based on the UTF-8-encoded Unicode value
   * of each character in the strings. The character sequence designated by
   * <code>aString</code>, <code>offset</code> and <code>length</code> is
   * compared lexicographically to the character sequence designated by
   * <code>anotherString</code>, <code>ooffset</code> and <code>olength</code>.
   * The result is a negative number if the character sequence contained in
   * <code>aString</code> lexicographically precedes the character sequence
   * contained in <code>anotherString</code>. The result is a positive number if
   * the character sequence contained in <code>aString</code> lexicographically
   * follows the character sequence contained in <code>anotherString</code>. The
   * result is zero if the two character sequences are equal. <p> This is the
   * definition of lexicographic ordering. If two strings are different, then
   * either they have different characters at some index that is a valid index
   * for both strings, or their lengths are different, or both. If they have
   * different characters at one or more index positions, let <i>k</i> be the
   * smallest such index; then the string whose character at position <i>k</i>
   * has the smaller value, as determined by using the &lt; operator,
   * lexicographically precedes the other string. In this case,
   * <code>compare</code> returns the difference of the first mismatching byte
   * of the UTF-8 encode representation of the two character at position
   * <i>k</i> in the two strings. If there is no index position at which they
   * differ, then the shorter string lexicographically precedes the longer
   * string. In this case, <code>compare</code> returns the difference of the
   * lengths of the strings. <p> When ignoring case considerations, this method
   * behaves as if comparing (using the same algorithm as described above)
   * normalized versions of the strings where case differences have been
   * eliminated by calling <code>toLowerCase(toUpperCase(string))</code> on both
   * argument strings.
   *
   * @param ignoreCase whether case must be ignored.
   * @param aString the byte array containing the reference UTF-8 encoded
   *     character sequence.
   * @param offset the starting offset of the reference character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the reference character sequence.
   * @param anotherString the byte array containing the UTF-8 encoded character
   *     sequence to be compared.
   * @param ooffset the starting offset in <code>anotherString</code> of the
   *     character sequence to be compared.
   * @param olength the length (in bytes) of the character sequence to be
   *     compared.
   * @return the value <code>0</code> if the character sequence contained in
   *     <code>anotherString</code> is equal to
   *         the character sequence contained in <code>aString</code>; a value
   * less than <code>0</code> if the character sequence contained in
   * <code>aString</code> is lexicographically less than the character sequence
   * contained in <code>anotherString</code>; and a value greater than
   * <code>0</code> if the character sequence contained in <code>aString</code>
   * is lexicographically greater than the character sequence contained in
   * <code>anotherString</code>, optionally ignoring case considerations..
   * @throws NullPointerException
   *                if <code>aString</code> or <code>anotherString</code> is
   * <code>null</code>.
   */
  public static short compare(boolean ignoreCase, byte[] aString, short offset,
                              short length, byte[] anotherString, short ooffset,
                              short olength) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Returns the index within the provided UTF-8 encoded character string of the
   * first occurrence of the specified substring. The number returned is the
   * smallest value <i>k</i> for which: <blockquote>
   *
   * <pre>
   *  compare(false, aString, offset + <i>k</i>, slength, substring, soffset,
   * slength) == 0
   * </pre>
   *
   * </blockquote> If no such value of <i>k</i> exists, then -1 is returned.
   *
   * @param aString the byte array containing the reference UTF-8 encoded
   *     character sequence.
   * @param offset the starting offset of the reference character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the reference character sequence.
   * @param subString the byte array containing the UTF-8 encoded character
   *     sequence of the substring.
   * @param soffset the starting offset in <code>subString</code> of the
   *     substring's character sequence.
   * @param slength the length (in bytes) of the substring's character sequence.
   * @return if the substring designated by <code>subString</code>,
   *     <code>soffset</code> and <code>slength</code>
   * occurs as a substring within the string designated by <code>aString</code>,
   * <code>offset</code> and <code>length</code>, then the index (relative to
   * <code>offset</code>) of the first byte of the first such substring is
   * returned; if it does not occur as a substring, <code>-1</code> is returned.
   * @throws java.lang.NullPointerException
   *                if <code>aString</code> or <code>subString</code> is
   * <code>null</code>.
   *
   */
  public static short indexOf(byte[] aString, short offset, short length,
                              byte[] subString, short soffset, short slength) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Copies to the destination byte array the string resulting from replacing
   * all occurrences of the old substring in the provided source string with the
   * new substring. <p> If the character sequence (substring) designated by
   * <code>oldSubstring</code>, <code>oOffset</code> and <code>oLength</code>
   * does not occur in the source character sequence
   * designated by <code>srcString</code>, <code>srcOffset</code> and
   * <code>srcLength</code>, then the source character sequence is copied as is
   * to <code>dstString</code>, starting at <code>dstOffset</code>. Otherwise, a
   * character sequence identical to the character sequence designated by
   * <code>srcString</code>, <code>srcOffset</code> and <code>srcLength</code>
   * is copied to <code>dstString</code>, starting at <code>dstOffset</code>,
   * except that every occurrence of the substring designated by
   * <code>oldSubstring</code>, <code>oOffset</code> and <code>oLength</code> is
   * replaced by an occurrence of the substring designated by
   * <code>newSubstring</code>, <code>nOffset</code> and <code>nLength</code>.
   * The replacement proceeds from the beginning of the source string to the
   * end.
   *
   * @param srcString the byte array containing the source UTF-8 encoded
   *     character sequence.
   * @param srcOffset the starting offset of the source character sequence in
   *     <code>srcString</code>.
   * @param srcLength the length (in bytes) of the source character sequence.
   * @param oldSubstring the byte array containing the UTF-8 encoded character
   *     sequence to be replaced.
   * @param oOffset the starting offset of the replaced character sequence in
   *     <code>oldSubstring</code>.
   * @param oLength the length (in bytes) of the replaced character sequence.
   * @param newSubstring the byte array containing the replacement UTF-8 encoded
   *     character sequence.
   * @param nOffset the starting offset of the replacement character sequence in
   *     <code>newSubstring</code>.
   * @param nLength the length (in bytes) of the replacement character sequence.
   * @param dstString the byte array for copying the resulting character
   *     sequence.
   * @param dstOffset the starting offset in <code>dstString</code> for copying
   *     the resulting character sequence.
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>srcString</code>, <code>oldSubstring</code>,
   * <code>newSubstring</code> or <code>dstString</code> is <code>null</code>.
   */
  public static short replace(byte[] srcString, short srcOffset,
                              short srcLength, byte[] oldSubstring,
                              short oOffset, short oLength, byte[] newSubstring,
                              short nOffset, short nLength, byte[] dstString,
                              short dstOffset) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Converts to lower case and copies all of the characters from the provided
   * source UTF-8 encoded character string to the provided destination array,
   * starting at the provided <code>dstOffset</code>. This method skips/ignores
   * any unrecognized (ill-formed or incomplete) byte sequence.
   *
   * @param srcString the byte array containing the source UTF-8 encoded
   *     character sequence.
   * @param srcOffset the starting offset of the source character sequence in
   *     <code>srcString</code>.
   * @param srcLength the length (in bytes) of the source character sequence.
   * @param dstString the byte array for copying the resulting character
   *     sequence.
   * @param dstOffset the starting offset in <code>dstString</code> for copying
   *     the resulting character sequence.
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>srcString</code> or <code>dstString</code> is
   * <code>null</code>.
   * @see StringUtil#toUpperCase
   */
  public static short toLowerCase(byte[] srcString, short srcOffset,
                                  short srcLength, byte[] dstString,
                                  short dstOffset) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Converts to upper case and copies all of the characters from the provided
   * source UTF-8 encoded character string to the provided destination array,
   * starting at the provided <code>dstOffset</code>. This method skips/ignores
   * any unrecognized (ill-formed or incomplete) byte sequence.
   *
   * @param srcString the byte array containing the source UTF-8 encoded
   *     character sequence.
   * @param srcOffset the starting offset of the source character sequence in
   *     <code>srcString</code>.
   * @param srcLength the length (in bytes) of the source character sequence.
   * @param dstString the byte array for copying the resulting character
   *     sequence.
   * @param dstOffset the starting offset in <code>dstString</code> for copying
   *     the resulting character sequence.
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>srcString</code> or <code>dstString</code> is
   * <code>null</code>.
   * @see StringUtil#toLowerCase
   */
  public static short toUpperCase(byte[] srcString, short srcOffset,
                                  short srcLength, byte[] dstString,
                                  short dstOffset) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Removes white space from both ends of the provided
   * UTF-8 encoded character string and copies the resulting character sequence
   * to the provided destination array, starting at the provided
   * <code>dstOffset</code>. <p> If the source string designated by
   * <code>srcString</code>, <code>srcOffset</code> and <code>srcLength</code>
   * represents an empty character sequence, or the first and last characters of
   * character sequence of the source string both have codes greater than
   * <code>'&#92;u0020'</code> (the space character), then the source string is
   * copied as is to <code>dstString</code>, starting at <code>dstOffset</code>.
   * <p>
   * Otherwise, if there is no character with a code greater than
   * <code>'&#92;u0020'</code> in the source string, then no character
   * is copied and 0 is returned.
   * <p>
   * Otherwise, let <i>k</i> be the index of the first character in the
   * source string whose code is greater than <code>'&#92;u0020'</code>, and let
   * <i>m</i> be the index of the last character in the source string whose code
   * is greater than <code>'&#92;u0020'</code>. The substring of the source
   * string that begins with the character at index <i>k</i> and ends with the
   * character at index <i>m</i> is copied to <code>dstString</code>, starting
   * at <code>dstOffset</code>. <p> This method may be used to trim whitespace
   * from the beginning and end of a string; in fact, it trims all ASCII control
   * characters as well. <p> Illegal byte sequences are considered as non-white
   * spaces.
   *
   * @param srcString the byte array containing the source UTF-8 encoded
   *     character sequence.
   * @param srcOffset the starting offset of the source character sequence in
   *     <code>srcString</code>.
   * @param srcLength the length (in bytes) of the source character sequence.
   * @param dstString the byte array for copying the resulting character
   *     sequence.
   * @param dstOffset the starting offset in <code>dstString</code> for copying
   *     the resulting character sequence.
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>srcString</code> or <code>dstString</code> is
   * <code>null</code>.
   */
  public static short trim(byte[] srcString, short srcOffset, short srcLength,
                           byte[] dstString, short dstOffset) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Copies the UTF-8 encoded character string representation of the
   * <code>boolean</code> argument into the provided destination array, starting
   * at the provided offset. If the argument is <code>true</code>, a string
   * equal to  <code>"true"</code> is copied; otherwise, a string equal to
   * <code>"false"</code> is copied.
   *
   * @param b a <code>boolean</code>.
   * @param dstString the destination UTF-8 encoded character string, as a byte
   *     array
   * @param dstOffset the starting offset in the destination array
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>dstString</code> is <code>null</code>.
   */
  public static short valueOf(boolean b, byte[] dstString, short dstOffset) {
    if (b) {                       /* the string "true" is copied" */
      dstString[dstOffset] = 0x74; // 't'
      dstString[(short)(dstOffset + 1)] = 0x72; // 'r'
      dstString[(short)(dstOffset + 2)] = 0x75; // 'u'
      dstString[(short)(dstOffset + 3)] = 0x65; // e
      return (short)4;
    } else {                       /* the string "false" is copied */
      dstString[dstOffset] = 0x66; // 'f'
      dstString[(short)(dstOffset + 1)] = 0x61; // 'a'
      dstString[(short)(dstOffset + 2)] = 0x6C; // 'l'
      dstString[(short)(dstOffset + 3)] = 0x73; // s
      dstString[(short)(dstOffset + 4)] = 0x65; // 'e'
      return (short)5;
    }
  }

  /**
   * Parses the string argument as a boolean.  The <code>boolean</code>
   * returned represents the value <code>true</code> if the string argument
   * is not <code>null</code> and is equal, ignoring case, to the string
   * {@code "true"}.
   *
   * @param aString the byte array containing the UTF-8 encoded character
   *     sequence to be parsed.
   * @param offset the starting offset of the character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the character sequence to be parsed.
   * @return the boolean value represented by the designated character sequence.
   * @throws NullPointerException
   *                if <code>aString</code> is <code>null</code>.
   */
  public static boolean parseBoolean(byte[] aString, short offset,
                                     short length) {

    if (aString == null) {
      return false;
    }

    if (aString.length != 4) {
      return false;
    }

    return ((aString[offset] == 0x74 /* 't' */ ||
             aString[offset] == 0x54 /* 'T' */) &&
            (aString[(short)(offset + 1)] == 0x72 /* 'r' */ ||
             aString[(short)(offset + 1)] == 0x52 /* 'R' */) &&
            (aString[(short)(offset + 2)] == 0x75 /* 'u'*/ ||
             aString[(short)(offset + 2)] == 0x55 /* 'U' */) &&
            (aString[(short)(offset + 3)] == 0x65 /* 'e' */ ||
             aString[(short)(offset + 3)] == 0x45 /* 'E' */));
  }

  /**
   * Copies the UTF-8 encoded, signed decimal string representation of the
   * the (up-to) 16 bits long signed (<code>short</code>) argument into
   * the provided destination array, starting at the provided offset.
   *
   * @param i a <code>short</code>.
   * @param dstString the destination UTF-8 encoded character string, as a byte
   *     array
   * @param dstOffset the starting offset in the destination array
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>dstString</code> is <code>null</code>.
   */
  public static short valueOf(short i, byte[] dstString, short dstOffset) {
    // TODO: Not yet implemented!!!
    return -1;
  }

  /**
   * Parses the provided UTF-8 encoded character sequence into the (up-to) 16
   * bits long signed (<code>short</code>) integer. Accepts decimal and
   * hexadecimal numbers given by the following grammar:
   *
   * <blockquote>
   * <dl>
   * <dt><i>DecodableString:</i>
   * <dd><i>Sign<sub>opt</sub> DecimalNumeral</i>
   * <dd><i>Sign<sub>opt</sub></i> <code>0x</code> <i>HexDigits</i>
   * <dd><i>Sign<sub>opt</sub></i> <code>0X</code> <i>HexDigits</i>
   * <dd><i>Sign<sub>opt</sub></i> <code>#</code> <i>HexDigits</i>
   * <dt><i>Sign:</i>
   * <dd><code>-</code>
   * </dl>
   * </blockquote>
   *
   * <i>DecimalNumeral</i> and <i>HexDigits</i>
   * are defined in <a
   * href="http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#48282">&sect;3.10.1</a>
   * of the <a href="http://java.sun.com/docs/books/jls/html/">Java
   * Language Specification</a>.
   * <p>
   * The sequence of characters following an (optional) negative
   * sign and/or radix specifier (&quot;<code>0x</code>&quot;,
   * &quot;<code>0X</code>&quot;, &quot;<code>#</code>&quot;, or
   * leading zero) is parsed as a (<code>short</code>) integer in the specified
   * radix (10, or 16). This sequence of characters must represent a positive
   * value or a {@link StringException} will be thrown with reason {@link
   * StringException#ILLEGAL_NUMBER_FORMAT}. The result is negated if first
   * character of the specified character string is the minus sign.  No
   * whitespace characters are permitted in the character string.
   *
   * @param aString the byte array containing the UTF-8 encoded character
   *     sequence to be parsed.
   * @param offset the starting offset of the character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the character sequence to be parsed.
   * @return the <code>short</code> integer value represented by the designated
   *     character sequence.
   * @throws StringException  if the designated character sequence does not
   *     contain a parsable (<code>short</code>) integer.
   * @throws NullPointerException
   *                if <code>aString</code> is <code>null</code>.
   */
  public static short parseShortInteger(byte[] aString, short offset,
                                        short length) {
    // TODO: Not yet implemented!!!
    return -1;
  }

  /**
   * Copies the UTF-8 encoded, signed decimal string representation of the
   * (up-to) 64 bits long signed integer argument provided as an array of
   * <code>short</code> integers, into the provided destination array, starting
   * at the provided offset.
   *
   * @param l an array of <code>short</code> integers representing up to a
   *     64bits signed long integer;
   * the most significant <code>short</code> integer is at index <code>0</code>.
   * @param dstString the destination UTF-8 encoded character string, as a byte
   *     array
   * @param dstOffset the starting offset in the destination array
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>l</code> or <code>dstString</code> is
   * <code>null</code>.
   */
  public static short valueOf(short[] l, byte[] dstString, short dstOffset) {
    // TODO: Not yet implemented!!!
    return -1;
  }

  /**
   * Parses the provided UTF-8 encoded character sequence into a (up-to) 64 bits
   * long signed integer. Accepts decimal and hexadecimal numbers given by the
   * following grammar:
   *
   * <blockquote>
   * <dl>
   * <dt><i>DecodableString:</i>
   * <dd><i>Sign<sub>opt</sub> DecimalNumeral</i>
   * <dd><i>Sign<sub>opt</sub></i> <code>0x</code> <i>HexDigits</i>
   * <dd><i>Sign<sub>opt</sub></i> <code>0X</code> <i>HexDigits</i>
   * <dd><i>Sign<sub>opt</sub></i> <code>#</code> <i>HexDigits</i>
   * <dt><i>Sign:</i>
   * <dd><code>-</code>
   * </dl>
   * </blockquote>
   *
   * <i>DecimalNumeral</i> and <i>HexDigits</i>
   * are defined in <a
   * href="http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#48282">&sect;3.10.1</a>
   * of the <a href="http://java.sun.com/docs/books/jls/html/">Java
   * Language Specification</a>.
   * <p>
   * The sequence of characters following an (optional) negative
   * sign and/or radix specifier (&quot;<code>0x</code>&quot;,
   * &quot;<code>0X</code>&quot;, &quot;<code>#</code>&quot;, or
   * leading zero) is parsed as a (long) integer in the specified radix (10 or
   * 16). This sequence of characters must represent a positive value or a
   * {@link StringException} will be thrown with reason {@link
   * StringException#ILLEGAL_NUMBER_FORMAT}. The result is negated if first
   * character of the specified character string is the minus sign.  No
   * whitespace characters are permitted in the character string.
   *
   * @param aString the byte array containing the UTF-8 encoded character
   *     sequence to be parsed.
   * @param offset the starting offset of the character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the character sequence to be parsed.
   * @param integer the array of <code>short</code> integers to contained the
   *     value represented
   * by the designated character sequence; the most significant
   * <code>short</code> integer is at index <code>0</code>.
   * @param ioffset the starting offset in <code>integer</code> for copying the
   *     resulting short sequence.
   * @return the number of <code>short</code> integers written into the array,
   *     ignoring leading zeroshort values.
   * @throws StringException  the designated character sequence does not contain
   *     a parsable (long) integer.
   * @throws NullPointerException
   *                if <code>aString</code> or <code>integer</code> is
   * <code>null</code>.
   */
  public static short parseLongInteger(byte[] aString, short offset,
                                       short length, short[] integer,
                                       short ioffset) {
    // TODO: Not yet implemented!!!
    return -1;
  }

  /**
   * Converts to the specified character encoding all of the characters from the
   * provided source UTF-8 encoded character string and copies them to the
   * provided destination array, starting at the provided
   * <code>dstOffset</code>.
   *
   * @param srcString the byte array containing the source UTF-8 encoded
   *     character sequence.
   * @param srcOffset the starting offset of the source character sequence in
   *     <code>srcString</code>.
   * @param srcLength the length (in bytes) of the source character sequence.
   * @param dstString the byte array for copying the resulting character
   *     sequence.
   * @param dstOffset the starting offset in <code>dstString</code> for copying
   *     the resulting character sequence.
   * @param encoding the character encoding to be used.
   * @return the number of bytes copied.
   * @throws StringException with reason {@link
   *     StringException#UNSUPPORTED_ENCODING} if the requested character
   *     encoding is not supported.
   * @throws StringException with reason {@link
   *     StringException#INVALID_BYTE_SEQUENCE} if an invalid byte sequence is
   *     encountered.
   * @see StringUtil#convertFrom
   * @throws NullPointerException
   *                if <code>srcString</code> or <code>dstString</code> is
   * <code>null</code>.
   */
  public static short convertTo(byte[] srcString, short srcOffset,
                                short srcLength, byte[] dstString,
                                short dstOffset, byte encoding) {
    // TODO: Not yet implemented!!!
    return -1;
  }

  /**
   * Converts from the specified character encoding to the UTF-8 character
   * encoding all of the characters from the provided source character string
   * and copies them to the provided destination array, starting at the provided
   * <code>dstOffset</code>.
   *
   * @param srcString the byte array containing the source character sequence
   *     encoded in the character encoding designated by <code>encoding</code>.
   * @param srcOffset the starting offset of the source character sequence in
   *     <code>srcString</code>.
   * @param srcLength the length (in bytes) of the source character sequence.
   * @param dstString the byte array for copying the UTF-8 encoded resulting
   *     character sequence.
   * @param dstOffset the starting offset in <code>dstString</code> for copying
   *     the resulting character sequence.
   * @param encoding the character encoding of the source character string.
   * @return the number of bytes copied.
   * @throws StringException with reason {@link
   *     StringException#UNSUPPORTED_ENCODING} if the requested character
   *     encoding is not supported.
   * @throws StringException with reason {@link
   *     StringException#INVALID_BYTE_SEQUENCE} if an invalid byte sequence is
   *     encountered.
   * @throws NullPointerException
   *                if <code>srcString</code> or <code>dstString</code> is
   * <code>null</code>.
   * @see StringUtil#convertTo
   */
  public static short convertFrom(byte[] srcString, short srcOffset,
                                  short srcLength, byte[] dstString,
                                  short dstOffset, byte encoding) {
    // TODO: Not yet implemented!!!
    return -1;
  }

  /**
   * Checks if the provided byte array contains a valid UTF-8 encoded character
   * or character sequence.
   * As per UTF-8, a byte with a leading '0' bit is a single-byte code;
   * a byte with leading '1' bits is the first byte of
   * a multi-byte sequence whose length is equals to number of leading '1' bits;
   * finally, a byte with a leading '10' bit sequence is a continuation byte of
   * a multi-byte sequence.
   *
   * @param aString the byte array containing the UTF-8 encoded character
   *     sequence to be checked.
   * @param offset the starting offset of the character sequence in
   *     <code>srcString</code>.
   * @param length the length (in bytes) of the character sequence to be
   *     checked.
   * @return true, if the byte sequence corresponds to a valid UTF-8 encoded
   *     character
   * or character sequence, false otherwise.
   * @throws NullPointerException
   *                if <code>aString</code> is <code>null</code>.
   */
  public static boolean check(byte[] aString, short offset, short length) {
    // TODO: Not yet implemented!!!
    return false;
  }

  /**
   * Tests if the UTF-8 encoded character sequence
   * designated by <code>aString</code>, <code>offset</code> and
   * <code>length</code> starts with the first <code>codePointCount</code>
   * characters of the character sequence designated by <code>prefix</code>,
   * <code>poffset</code> and <code>plength</code>. <p> If
   * <code>codePointCount</code> is negative, the whole prefix character
   * sequence is considered; in which case calling this method is equivalent to
   * calling
   * {@link Util#arrayCompare(byte[], short, byte[], short, short) arrayCompare}
   * as follows: <pre> return length &ge; plength &amp;&amp;
   * arrayCompare(aString, offset, prefix, poffset, plength) == 0;
   * </pre>
   * Otherwise if <code>codePointCount</code> is positive, calling this method
   * is equivalent to calling {@link Util#arrayCompare(byte[], short, byte[],
   * short, short) arrayCompare} as follows: <pre> short endOffset =
   * StringUtil.offsetByCodePoints(prefix, poffset, plength, 0, codePointCount);
   * return length &ge; endOffset &amp;&amp; arrayCompare(aString, offset,
   * prefix, poffset, endOffset) == 0;
   * </pre>
   *
   * @param aString the byte array containing the reference UTF-8 encoded
   *     character sequence.
   * @param offset the starting offset of the reference character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the reference character sequence.
   * @param prefix the byte array containing the prefixing UTF-8 encoded
   *     character sequence.
   * @param poffset the starting offset in <code>prefix</code> of the prefixing
   *     character sequence.
   * @param plength the length (in bytes) of the prefixing character sequence.
   * @param codePointCount the number of code points to be used for testing.
   * @return <code>true</code> if the character sequence designated by
   *     <code>prefix</code>,
   * <code>poffset</code> and <code>plength</code> is a prefix of the character
   * sequence designated by <code>aString</code>, <code>offset</code> and
   * <code>length</code>; <code>false</code> otherwise.
   *
   * @throws java.lang.NullPointerException
   *                if <code>aString</code> or <code>prefix</code> is
   * <code>null</code>.
   *
   */
  public static boolean startsWith(byte[] aString, short offset, short length,
                                   byte[] prefix, short poffset, short plength,
                                   short codePointCount) {
    // TODO: Not yet implemented!!!
    return false;
  }

  /**
   * Tests if the UTF-8 encoded character sequence
   * designated by <code>aString</code>, <code>offset</code> and
   * <code>length</code> ends with the first <code>codePointCount</code>
   * characters of the character sequence designated by <code>suffix</code>,
   * <code>soffset</code> and <code>slength</code>. <p> If
   * <code>codePointCount</code> is negative, the whole suffix character
   * sequence is considered; in which case calling this method is equivalent to
   * calling
   * {@link Util#arrayCompare(byte[], short, byte[], short, short) arrayComapre
   * } as follows: <pre> return length &ge; slength &amp;&amp;
   * arrayCompare(aString, (short) (offset + length - slength), suffix, soffset,
   * slength) == 0;
   * </pre>
   * Otherwise if <code>codePointCount</code> is positive, calling this method
   * is equivalent to calling {@link Util#arrayCompare(byte[], short, byte[],
   * short, short) arrayCompare } as follows: <pre> short endOffset =
   * StringUtil.offsetByCodePoints(suffix, soffset, slength, 0, codePointCount);
   * return length &ge; endOffset &amp;&amp; arrayCompare(aString,
   *         (short) (offset + length - endOffset), suffix, soffset, endOffset)
   * == 0;
   * </pre>
   *
   * @param aString the byte array containing the reference UTF-8 encoded
   *     character sequence.
   * @param offset the starting offset of the reference character sequence in
   *     <code>aString</code>.
   * @param length the length (in bytes) of the reference character sequence.
   * @param suffix the byte array containing the suffixing UTF-8 encoded
   *     character sequence.
   * @param soffset the starting offset in <code>suffix</code> of the suffixing
   *     character sequence.
   * @param slength the length (in bytes) of the suffixing character sequence.
   * @param codePointCount the number of code points to be used for testing.
   * @return <code>true</code> if the character sequence designated by
   *     <code>suffix</code>,
   * <code>soffset</code> and <code>slength</code> is a suffix of the character
   * sequence designated by <code>aString</code>, <code>offset</code> and
   * <code>length</code>; <code>false</code> otherwise.
   *
   * @throws java.lang.NullPointerException
   *                if <code>aString</code> or <code>suffix</code> is
   * <code>null</code>.
   *
   */
  public static boolean endsWith(byte[] aString, short offset, short length,
                                 byte[] suffix, short soffset, short slength,
                                 short codePointCount) {
    // TODO: Not yet implemented!!!
    return false;
  }

  /**
   * Copies to the destination byte array the specified substring of the
   * designated source string. The substring begins at the specified
   * <code>codePointbeginIndex</code> and extends to the character at index
   * <code>codePointEndIndex - 1</code>. Thus the length of the substring in
   * codepoints (that is its codepoint count) is <code>codePointEndIndex -
   * codePointbeginIndex</code>. <p> Ill-formed or incomplete byte sequences
   * within the text range count as one code point each. <br> If
   * <code>codePointEndIndex</code> is negative, then the whole remaining
   * character sequence from the source string is considered.
   *
   * @param srcString the byte array containing the source UTF-8 encoded
   *     character sequence.
   * @param srcOffset the starting offset of the source character sequence in
   *     <code>srcString</code>.
   * @param srcLength the length (in bytes) of the source character sequence.
   * @param codePointBeginIndex the beginning index (relative to
   *     <code>srcOffset</code>), inclusive.
   * @param codePointEndIndex the ending index (relative to
   *     <code>srcOffset</code>), exclusive.
   * @param dstString the byte array for copying the resulting character
   *     sequence.
   * @param dstOffset the starting offset in <code>dstString</code> for copying
   *     the resulting character sequence.
   * @return the number of bytes copied.
   * @throws NullPointerException
   *                if <code>srcString</code> or <code>dstString</code> is
   * <code>null</code>.
   */
  public static short substring(byte[] srcString, short srcOffset,
                                short srcLength, short codePointBeginIndex,
                                short codePointEndIndex, byte[] dstString,
                                short dstOffset) {
    // TODO: Not yet implemented!!!
    return -1;
  }
}
