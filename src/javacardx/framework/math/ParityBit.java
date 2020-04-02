/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.math;

/**
 * The <code>ParityBit</code> class is a utility to assist with DES key parity
 * bit generation.
 *
 * @since 2.2.2
 */
public final class ParityBit {

  /**
   * Intended to be package visible. Retain for compatibility
   */
  public ParityBit() {}

  /**
   * Inserts the computed parity bit of the specified type as the last
   * bit(LSB) in each of the bytes of the specified byte array. The parity is
   * computed over the first(MS) 7 bits of each byte. The incoming last bit of
   * each byte is ignored.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff</code><em> or </em><code>bLen</code><em>
   * is negative an </em><code>ArrayIndexOutOfBoundsException</code><em>
   * exception is thrown.</em> <li><em>If </em><code>bLen</code><em> parameter
   * is equal to 0 no parity bits are inserted.</em> <li><em>If
   * </em><code>bOff+bLen</code><em> is greater than
   * </em><code>bArray.length</code><em>, the length of the
   * </em><code>bArray</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no parity bits are inserted.</em>
   * <li><em>If </em><code>bArray</code><em> parameter is
   * </em><code>null</code><em> a </em><code>NullPointerException</code><em>
   * exception is thrown.</em>
   * </ul>
   * @param bArray input/output byte array
   * @param bOff offset within byte array to start setting parity on
   * @param bLen byte length of input/output bytes
   * @param isEven <code>true</code> if even parity is required and
   *     <code>false</code> if odd parity is required
   * @exception java.lang.NullPointerException if <code>bArray</code> is
   * <code>null</code>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   * if accessing the input array would cause access of data outside array
   * bounds or if <code>bLen</code> is negative
   */
  public static void set(byte[] bArray, short bOff, short bLen,
                         boolean isEven) {
    // TODO: Not yet implemented
  }
}
