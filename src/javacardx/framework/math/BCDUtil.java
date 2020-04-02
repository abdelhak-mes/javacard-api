/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.math;

/**
 * The <code>BCDUtil</code> class contains common BCD(binary coded decimal)
 * related utility functions. This class supports Packed BCD format. All methods
 * in this class are static.
 * <p>
 * The <code>BCDUtil</code> class only supports unsigned numbers whose value
 * can are represented in hexadecimal format using an implementation specific
 * maximum number of bytes.
 *
 * @since 2.2.2
 */
public final class BCDUtil {

  /**
   * Intended to be package visible. Retain for compatibility
   */
  public BCDUtil() {}

  /**
   * This method returns the largest value that can be used with the BCD
   * utility functions. This number represents the byte length of the
   * largest value in hex byte representation. All implementations must
   * support at least 8 byte length usage capacity.
   *
   * @return the byte length of the largest hex value supported
   */
  public static short getMaxBytesSupported() {
    return BigNumber.MAX_BYTES_VALUE;
  }

  /**
   * Converts the input BCD data into hexadecimal format.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff</code><em> or </em><code>bLen</code><em> or
   * </em><code>outOff</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>bOff+bLen</code><em> is greater than
   * </em><code>bcdArray.length</code><em>, the length of the
   * </em><code>bcdArray</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no conversion is performed.</em>
   * <li><em>If the output bytes need to be written at an offset greater than
   * </em><code>hexArray.length</code><em>, the length of the
   * </em><code>hexArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no conversion is performed.</em>
   * <li><em>If </em><code>bcdArray</code><em> or </em><code>hexArray</code><em>
   * parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em>
   * <li><em>If the <code>bcdArray</code> and <code>hexArray</code> arguments
   * refer to the same array object, then the conversion is performed as if the
   * components at positions </em><code>bOff</code><em> through
   * </em><code>bOff+bLen-1</code><em> were first copied to a temporary array
   * with </em><code>bLen</code><em> components and then the contents of the
   * temporary array were converted into positions </em><code>outOff</code><em>
   * onwards for the converted bytes of the output array.</em>
   * </ul>
   *
   * @param bcdArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte (the high order
   *            byte)
   * @param bLen
   *            byte length of input BCD data
   * @param hexArray
   *            output byte array
   * @param outOff
   *            offset within hexArray where output data begins
   * @return the byte length of the output hexadecimal data
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if converting would cause access of data outside array
   *                bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if either <code>bcdArray</code> or <code>hexArray</code>
   *                is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the input byte array format is not a correctly
   *                formed BCD value
   *                <li>the size of the BCD value requires greater than
   *                supported maximum number of bytes to represent in hex
   *                format
   *                <li>if <code>bLen</code> is 0
   *                </ul>
   */
  public static short convertToHex(byte[] bcdArray, short bOff, short bLen,
                                   byte[] hexArray, short outOff) {
    for (short idx = 0, idx_hex = 0; idx < bLen; idx += 2, idx_hex++) {
      byte value = (byte)((bcdArray[(short)(idx + bOff)] << 8) |
                          (bcdArray[(short)(idx + bOff + 1)] & 0x0F));
      hexArray[(short)(idx_hex + outOff)] = value;
    }

    return (short)hexArray.length;
  }

  /**
   * Converts the input hexadecimal data into BCD format. The output data is
   * right justified. If the number of output BCD nibbles is odd, the first
   * BCD nibble written is 0.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff</code><em> or </em><code>bLen</code><em> or
   * </em><code>outOff</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>bOff+bLen</code><em> is greater than
   * </em><code>hexArray.length</code><em>, the length of the
   * </em><code>hexArray</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown and no conversion is performed.</em> <li><em>If the output bytes
   * need to be written at an offset greater than
   * </em><code>bcdArray.length</code><em>, the length of the
   * </em><code>bcdArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown and no conversion is performed.</em> <li><em>If
   * </em><code>bcdArray</code><em> or </em><code>hexArray</code><em>
   * parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em>
   * <li><em>If the <code>bcdArray</code> and <code>hexArray</code>
   * arguments refer to the same array object, then the conversion is
   * performed as if the components at positions </em><code>bOff</code><em>
   * through
   * </em><code>bOff+bLen-1</code><em> were first copied to a temporary
   * array with </em><code>bLen</code><em> components and then the contents
   * of the temporary array were converted into positions
   * </em><code>outOff</code><em> onwards for the converted bytes of the
   * output array.</em>
   * </ul>
   *
   * @param hexArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte (the high
   * order byte)
   * @param bLen
   *            byte length of input hex data
   * @param bcdArray
   *            output byte array
   * @param outOff
   *            offset within bcdArray where output data begins
   * @return the byte length of the output bcd formatted data
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if converting would cause access of data outside array
   *                bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if either <code>bcdArray</code> or <code>hexArray</code>
   *                is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the length of the input hex value is larger than
   *                the supported maximum number of bytes
   *                <li>if <code>bLen</code> is 0
   *                </ul>
   */
  public static short convertToBCD(byte[] hexArray, short bOff, short bLen,
                                   byte[] bcdArray, short outOff) {
    for (short idx = 0, idx_bcd = 0; idx < bLen; idx++, idx_bcd += 2) {
      bcdArray[(short)(idx_bcd + outOff)] =
          (byte)(hexArray[(short)(idx + bOff)] >> 8);
      bcdArray[(short)(idx_bcd + outOff + 1)] =
          (byte)(hexArray[(short)(idx + bOff + 1)] & 0x0F);
    }

    return (short)bcdArray.length;
  }

  /**
   * Checks if the input data is in BCD format. Note that this method does not
   * enforce an upper bound on the length of the input BCD value.
   *
   * @param bcdArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte (the high order
   *            byte)
   * @param bLen
   *            byte length of input BCD data
   * @return true if input data is in BCD format, false otherwise
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if <code>bcdArray</code> is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                if <code>bLen</code> is 0
   *
   */
  public static boolean isBCDFormat(byte[] bcdArray, short bOff, short bLen) {
    // TODO: Not yet implemented !
    return false;
  }
}
