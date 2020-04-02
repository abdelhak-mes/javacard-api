/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.math;

import javacard.framework.Util;

/**
 * The <code>BigNumber</code> class encapsulates an unsigned number whose
 * value is represented in internal hexadecimal format using an implementation
 * specific maximum number of bytes. This class supports the BCD (binary coded
 * decimal) format for I/O.
 *
 * @since 2.2.2
 */
public final class BigNumber {

  /**
   * Constant to indicate a BCD (binary coded decimal) data format. When this
   * format is used a binary coded decimal digit is stored in 1 nibble (4
   * bits). A byte is packed with 2 BCD digits.
   */
  public static final byte FORMAT_BCD = (byte)1;

  /**
   * Constant to indicate a hexadecimal (simple binary) data format.
   */
  public static final byte FORMAT_HEX = (byte)2;

  // value
  byte[] value;

  // Default max value
  byte[] maxValue;

  protected static final byte MAX_BYTES_VALUE = (byte)8;

  /**
   * Creates a BigNumber instance with initial value 0. All implementations
   * must support at least 8 byte length internal representation capacity.
   *
   * @param maxBytes
   *            maximum number of bytes needed in the hexadecimal format for
   *            the largest unsigned big number. For example, maxBytes = 2
   *            allows a big number representation range 0-65535.
   * @exception java.lang.ArithmeticException
   *                if maxBytes is 0, negative or larger than the supported
   *                maximum
   */
  public BigNumber(short maxBytes) {
    this.value = new byte[maxBytes];
    this.maxValue = new byte[maxBytes];

    Util.arrayFillNonAtomic(maxValue, (byte)0, maxBytes, (byte)0xFF);
  }

  /**
   * Sets the maximum value that the BigNumber may contain. Attempts to
   * increase beyond the maximum results in an exception. If this method is
   * not called, the maximum value is the maximum hex value that fits within
   * the configured maximum number of bytes.
   * <p>
   * Note:
   * <ul>
   * <li><em>This method may allocate internal storage to store the specified
   * maximum value.</em>
   * </ul>
   *
   * @param maxValue
   *            input byte array
   * @param bOff
   *            offset within input byte array containing first byte (the high
   *            order byte)
   * @param bLen
   *            byte length of input data
   * @param arrayFormat
   *            indicates the format of the input data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @exception java.lang.NullPointerException
   *                if <code>maxValue</code> is <code>null</code>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds or if <code>bLen</code> is negative
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the specified maximum value is smaller than the
   *                encapsulated big number
   *                <li>if the specified maximum value is larger than will
   *                fit within the supported maximum number of bytes
   *                <li>if the input byte array format is not conformant with
   *                the specified <code>arrayFormat</code> parameter
   *                <li>if <code>bLen</code> is 0
   *                <li>if <code>arrayFormat</code> is not one of the
   *                FORMAT_ constants.
   *                </ul>
   */
  public void setMaximum(byte[] maxValue, short bOff, short bLen,
                         byte arrayFormat) {
    switch (arrayFormat) {
    case BigNumber.FORMAT_BCD:
      // TODO: Not yet implemented !!!
      break;
    case BigNumber.FORMAT_HEX:
      Util.arrayCopyNonAtomic(maxValue, bOff, this.maxValue, (short)0, bLen);
      break;
    }
  }

  /**
   * This method returns the byte length of the hex array that can store the
   * biggest BigNumber supported. This number is the maximum number in hex
   * byte representation. All implementations must support at least 8 bytes.
   *
   * @return the byte length of the biggest number supported
   */
  public static short getMaxBytesSupported() { return MAX_BYTES_VALUE; }

  /**
   * Initializes the big number using the input data
   *
   * @param bArray
   *            input byte array
   * @param bOff
   *            offset within byte array containing first byte (the high order
   *            byte)
   * @param bLen
   *            byte length of input data
   * @param arrayFormat
   *            indicates the format of the input data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access outside
   *                array bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the input byte array format is not conformant with
   *                the specified <code>arrayFormat</code> parameter
   *                <li>if the specified input data represents a number which
   *                is larger than the maximum value configured or larger than
   *                will fit within the supported maximum number of bytes
   *                <li>if <code>bLen</code> is 0
   *                <li>if <code>arrayFormat</code> is not one of the
   *                FORMAT_ constants.
   *                </ul>
   */
  public void init(byte[] bArray, short bOff, short bLen, byte arrayFormat)
      throws NullPointerException, ArrayIndexOutOfBoundsException,
             ArithmeticException {
    switch (arrayFormat) {
    case BigNumber.FORMAT_BCD:
      // TODO: Not yet implemented !!!
      break;
    case BigNumber.FORMAT_HEX:
      Util.arrayCopyNonAtomic(bArray, bOff, this.maxValue, (short)0, bLen);
      break;
    }
  }

  /**
   * Increments the internal big number by the specified operand value
   *
   * @param bArray
   *            input byte array
   * @param bOff
   *            offset within input byte array containing first byte (the high
   *            order byte)
   * @param bLen
   *            byte length of input data
   * @param arrayFormat
   *            indicates the format of the input data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the input byte array format is not conformant with
   *                the specified <code>arrayFormat</code> parameter
   *                <li>if the result of the addition results in a big number
   *                which cannot be represented within the maximum supported
   *                bytes or is greater than the configured max value. The
   *                internal big number is left unchanged.
   *                <li>if <code>bLen</code> is 0
   *                <li>if <code>arrayFormat</code> is not one of the
   *                FORMAT_ constants
   *                </ul>
   */
  public void add(byte[] bArray, short bOff, short bLen, byte arrayFormat)
      throws NullPointerException, ArrayIndexOutOfBoundsException,
             ArithmeticException {
    // TODO: Not yet implemented !!!
  }

  /**
   * Decrements the internal big number by the specified operand value
   *
   * @param bArray
   *            input byte array
   * @param bOff
   *            offset within input byte array containing first byte (the high
   *            order byte)
   * @param bLen
   *            byte length of input data
   * @param arrayFormat
   *            indicates the format of the input data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the input byte array format is not conformant with
   *                the specified <code>arrayFormat</code> parameter
   *                <li>if the result of the subtraction results in a
   *                negative number. The internal big number is left
   *                unchanged.
   *                <li>if <code>bLen</code> is 0
   *                <li>if <code>arrayFormat</code> is not one of the
   *                FORMAT_ constants.
   *                </ul>
   */
  public void subtract(byte[] bArray, short bOff, short bLen, byte arrayFormat)
      throws ArithmeticException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Multiplies the internal big number by the specified operand value
   *
   * @param bArray
   *            input byte array
   * @param bOff
   *            offset within input byte array containing first byte (the high
   *            order byte)
   * @param bLen
   *            byte length of input data
   * @param arrayFormat
   *            indicates the format of the input data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the input byte array format is not conformant with
   *                the specified <code>arrayFormat</code> parameter
   *                <li>if the result of the multiplication results in a big
   *                number which cannot be represented within the maximum
   *                supported bytes or is greater than the configured max
   *                value. The internal big number is left unchanged.
   *                <li>if <code>bLen</code> is 0
   *                <li>if <code>arrayFormat</code> is not one of the
   *                FORMAT_ constants.
   *                </ul>
   */
  public void multiply(byte[] bArray, short bOff, short bLen, byte arrayFormat)
      throws ArithmeticException {
    // TODO: Not yet implemented !!!
  }

  /**
   * Compares the internal big number against the specified operand.
   * <p>
   * In addition to returning a {@code byte} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param operand
   *            contains the BigNumber operand
   * @return the result of the comparison as follows:
   *         <ul>
   *         <li> <code>0</code> if equal</li>
   *         <li> <code>-1</code> if the internal big number is less than
   *         the specified operand</li>
   *         <li> <code>1</code> if the internal big number is greater than
   *         the specified operand</li>
   *         </ul>
   * @exception java.lang.NullPointerException
   *                if <code>operand</code> is <code>null</code>
   */
  public byte compareTo(BigNumber operand) {
    // TODO: Not yet implemented!!!
    return -1;
  }

  /**
   * Compares the internal big number against the specified operand. The
   * operand is specified in an input byte array.
   * <p>
   * In addition to returning a {@code byte} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param bArray
   *            input byte array
   * @param bOff
   *            offset within input byte array containing first byte (the high
   *            order byte)
   * @param bLen
   *            byte length of input data
   * @param arrayFormat
   *            indicates the format of the input data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the input array would cause access of data
   *                outside array bounds or if <code>bLen</code> is negative
   * @exception java.lang.NullPointerException
   *                if <code>bArray</code> is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if the input byte array format is not conformant with
   *                the specified <code>arrayFormat</code> parameter
   *                <li>if <code>bLen</code> is 0
   *                <li>if <code>arrayFormat</code> is not one of the
   *                FORMAT_ constants.
   *                </ul>
   * @return the result of the comparison as follows:
   *         <ul>
   *         <li> <code>0</code> if equal</li>
   *         <li> <code>-1</code> if the internal big number is less than
   *         the specified operand</li>
   *         <li> <code>1</code> if the internal big number is greater than
   *         the specified operand</li>
   *         </ul>
   */
  public byte compareTo(byte[] bArray, short bOff, short bLen,
                        byte arrayFormat) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Writes the internal big number out in the desired format. Note that the
   * value output into the specified byte array is right justified for the
   * number of requested bytes. BCD 0 nibbles are prepended to the output BCD
   * data written out.
   *
   * @param outBuf
   *            output byte array
   * @param bOff
   *            offset within byte array containing first byte (the high order
   *            byte)
   * @param numBytes
   *            number of output bytes required
   * @param arrayFormat
   *            indicates the format of the input data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if accessing the output array would cause access of data
   *                outside array bounds or if <code>numBytes</code> is
   *                negative
   * @exception java.lang.NullPointerException
   *                if <code>outBuf</code> is <code>null</code>
   * @exception java.lang.ArithmeticException
   *                for the following conditions:
   *                <ul>
   *                <li>if <code>numBytes</code> is not sufficient to
   *                represent the big number in the desired format
   *                <li>if <code>numBytes</code> is 0
   *                <li>if <code>arrayFormat</code> is not one of the
   *                FORMAT_ constants.
   *                </ul>
   */
  public void toBytes(byte[] outBuf, short bOff, short numBytes,
                      byte arrayFormat)
      throws ArrayIndexOutOfBoundsException, NullPointerException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Returns the number of bytes required to represent the big number using
   * the desired format
   *
   * @param arrayFormat
   *            indicates the format of the output data. Valid codes listed in
   *            <code>FORMAT_*</code> constants, for example
   *            {@link #FORMAT_BCD FORMAT_BCD}.
   * @return the byte length of big number
   * @exception ArithmeticException
   *                if <code>arrayFormat</code> is not one of the FORMAT_
   *                constants.
   */
  public short getByteLength(byte arrayFormat) {
    // TODO: Not yet implemented !!!
    return -1;
  }

  /**
   * Resets the big number to 0
   */
  public void reset() {
    // TODO: Not yet implemented !!!
  }
}
