/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The <code>Util</code> class contains common utility functions. Some of the
 * methods may be implemented as native functions for performance reasons. All
 * methods in <code>Util</code>, class are static methods.
 * <p>
 * Some methods of <code>Util</code>, namely <code>arrayCopy()</code>,
 * <code>arrayCopyNonAtomic()</code>, <code>arrayFill()</code>,
 * <code>arrayFillNonAtomic()</code> and <code>setShort()</code>, refer to the
 * persistence of array objects. The term <em>persistent</em> means that arrays
 * and their values persist from one CAD session to the next, indefinitely. The
 * <code>JCSystem</code> class is used to control the persistence and transience
 * of objects.
 *
 * @see javacard.framework.JCSystem JCSystem
 */
public class Util {

  /**
   * Copies an array from the specified source array, beginning at the
   * specified position, to the specified position of the destination array.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>srcOff</code><em> or </em><code>destOff</code><em> or
   * </em><code>length</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em></li> <li><em>If </em><code>srcOff+length</code><em> is greater
   * than </em><code>src.length</code><em>, the length of the
   * </em><code>src</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   *     and no copy is performed.</em></li>
   * <li><em>If </em><code>destOff+length</code><em> is greater than
   * </em><code>dest.length</code><em>, the length of the
   * </em><code>dest</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   *     and no copy is performed.</em></li>
   * <li><em>If </em><code>src</code><em> or </em><code>dest</code><em>
   * parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em></li>
   * <li><em>If the <code>src</code> and <code>dest</code> arguments refer to
   * the same array object, then the copying is performed as if the components
   * at positions </em><code>srcOff</code><em> through
   * </em><code>srcOff+length-1</code><em> were first copied to a temporary
   * array with </em><code>length</code><em> components and then the contents of
   * the temporary array were copied into positions
   * </em><code>destOff</code><em> through
   * </em><code>destOff+length-1</code><em> of the argument array.</em></li>
   * <li><em>If the destination array is persistent, the entire copy is
   * performed atomically.</em></li> <li><em>The copy operation is subject to
   * atomic commit capacity limitations. If the commit capacity is exceeded, no
   * copy is performed and a </em><code>TransactionException</code><em>
   *     exception is thrown.</em></li>
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param src
   *            source byte array
   * @param srcOff
   *            offset within source byte array to start copy from
   * @param dest
   *            destination byte array
   * @param destOff
   *            offset within destination byte array to start copy into
   * @param length
   *            byte length to be copied
   * @return <code>destOff+length</code>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if copying would cause access of data outside array bounds
   * @exception java.lang.NullPointerException
   *                if either <code>src</code> or <code>dest</code> is
   *                <code>null</code>
   * @exception javacard.framework.TransactionException
   *                if copying would cause the commit capacity to be exceeded
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   *      JCSystem.getUnusedCommitCapacity()
   */
  public static final short arrayCopy(byte[] src, short srcOff, byte[] dest,
                                      short destOff, short length)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             TransactionException {

    boolean inTransaction = false;

    if (JCSystem.getTransactionDepth() == 0) {
      inTransaction = true;
    }

    if (!inTransaction) {
      JCSystem.beginTransaction();
    }

    short ret = Util.arrayCopyNonAtomic(src, srcOff, dest, destOff, length);

    if (!inTransaction) {
      JCSystem.commitTransaction();
    }

    return ret;
  }

  /**
   * Copies an array from the specified source array, beginning at the
   * specified position, to the specified position of the destination array
   * (non-atomically).
   * <p>
   * This method does not use the transaction facility during the copy
   * operation even if a transaction is in progress. Thus, this method is
   * suitable for use only when the contents of the destination array can be
   * left in a partially modified state in the event of a power loss in the
   * middle of the copy operation.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>srcOff</code><em> or </em><code>destOff</code><em> or
   * </em><code>length</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>srcOff+length</code><em> is greater than
   * </em><code>src.length</code><em>, the length of the
   * </em><code>src</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no copy is performed.</em>
   * <li><em>If </em><code>destOff+length</code><em> is greater than
   * </em><code>dest.length</code><em>, the length of the
   * </em><code>dest</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no copy is performed.</em>
   * <li><em>If </em><code>src</code><em> or </em><code>dest</code><em>
   * parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em>
   * <li><em>If the <code>src</code> and <code>dest</code> arguments refer to
   * the same array object, then the copying is performed as if the components
   * at positions </em><code>srcOff</code><em> through
   * </em><code>srcOff+length-1</code><em> were first copied to a temporary
   * array with </em><code>length</code><em> components and then the contents of
   * the temporary array were copied into positions
   * </em><code>destOff</code><em> through
   * </em><code>destOff+length-1</code><em> of the argument array.</em>
   * <li><em>If power is lost during the copy operation and the destination
   * array is persistent, a partially changed destination array could
   * result.</em> <li><em>The copy </em><code>length</code><em> parameter is not
   * constrained by the atomic commit capacity limitations.</em> <li><em>Non
   * atomic array operations on persistent integrity-sensitive arrays created
   * using the {@link javacard.framework.SensitiveArrays} API are not supported;
   * therefore if the </em><code>dest</code><em> array parameter is a persistent
   * integrity-sensitive array a {@code SystemException} exception is
   * thrown.</em>
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param src
   *            source byte array
   * @param srcOff
   *            offset within source byte array to start copy from
   * @param dest
   *            destination byte array
   * @param destOff
   *            offset within destination byte array to start copy into
   * @param length
   *            byte length to be copied
   * @return <code>destOff+length</code>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if copying would cause access of data outside array bounds
   * @exception java.lang.NullPointerException
   *                if either <code>src</code> or <code>dest</code> is
   *                <code>null</code>
   * @throws javacard.framework.SystemException with the following reason codes:
   * <ul>
   * <li>{@link javacard.framework.SystemException#ILLEGAL_VALUE ILLEGAL_VALUE}
   * if {@code dest} is a persistent integrity-sensitive array object.</li>
   * </ul>
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   *      JCSystem.getUnusedCommitCapacity()
   */
  public static final short arrayCopyNonAtomic(byte[] src, short srcOff,
                                               byte[] dest, short destOff,
                                               short length)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             SystemException {
    for (short idx = 0; idx < length; idx++) {
      dest[(short)(destOff + idx)] = src[(short)(srcOff + idx)];
    }

    return (short)(destOff + length);
  }

  /**
   * Fills the byte array beginning at the specified
   * position, for the specified length with the specified byte value.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff</code><em> or </em><code>bLen</code><em>
   * parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>bOff+bLen</code><em> is greater than
   * </em><code>bArray.length</code><em>, the length of the
   * </em><code>bArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>bArray</code><em> parameter is
   * </em><code>null</code><em> a </em><code>NullPointerException</code><em>
   * exception is thrown.</em> <li><em>The </em><code>bLen</code><em> parameter
   * is not constrained by the atomic commit capacity limitations.</em>
   * <li><em>The fill operation is subject to atomic commit capacity
   * limitations. If the commit capacity is exceeded, no filling is performed
   * and a </em><code>TransactionException</code><em> exception is
   * thrown.</em></li>
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param bArray
   *            the byte array
   * @param bOff
   *            offset within byte array to start filling bValue into
   * @param bLen
   *            byte length to be filled
   * @param bValue
   *            the value to fill the byte array with
   * @return <code>bOff+bLen</code>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if the fill operation would cause access of data outside
   *                array bounds
   * @exception java.lang.NullPointerException
   *                if bArray is <code>null</code>
   * @exception javacard.framework.TransactionException
   *                if filling would cause the commit capacity to be exceeded
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   *      JCSystem.getUnusedCommitCapacity()
   * @since 3.0.5
   */
  public static final short arrayFill(byte[] bArray, short bOff, short bLen,
                                      byte bValue)
      throws ArrayIndexOutOfBoundsException, NullPointerException {
    boolean inTransaction = false;

    if (JCSystem.getTransactionDepth() == 0) {
      inTransaction = true;
    }

    if (!inTransaction) {
      JCSystem.beginTransaction();
    }

    short ret = Util.arrayFillNonAtomic(bArray, bOff, bLen, bValue);

    if (!inTransaction) {
      JCSystem.commitTransaction();
    }

    return ret;
  }

  /**
   * Fills the byte array (non-atomically) beginning at the specified
   * position, for the specified length with the specified byte value.
   * <p>
   * This method does not use the transaction facility during the fill
   * operation even if a transaction is in progress. Thus, this method is
   * suitable for use only when the contents of the byte array can be left in
   * a partially filled state in the event of a power loss in the middle of
   * the fill operation.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>bOff</code><em> or </em><code>bLen</code><em>
   * parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>bOff+bLen</code><em> is greater than
   * </em><code>bArray.length</code><em>, the length of the
   * </em><code>bArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>bArray</code><em> parameter is
   * </em><code>null</code><em> a </em><code>NullPointerException</code><em>
   * exception is thrown.</em> <li><em>If power is lost during the fill
   * operation and the byte array is persistent, a partially changed byte array
   * could result.</em> <li><em>The </em><code>bLen</code><em> parameter is not
   * constrained by the atomic commit capacity limitations.</em> <li><em>Non
   * atomic array operations on persistent integrity-sensitive arrays created
   * using the {@link javacard.framework.SensitiveArrays} API are not supported;
   * therefore if the </em><code>bArray</code><em> array parameter is a
   * persistent integrity-sensitive array a {@code SystemException} exception is
   * thrown.</em>
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param bArray
   *            the byte array
   * @param bOff
   *            offset within byte array to start filling bValue into
   * @param bLen
   *            byte length to be filled
   * @param bValue
   *            the value to fill the byte array with
   * @return <code>bOff+bLen</code>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if the fill operation would cause access of data outside
   *                array bounds
   * @exception java.lang.NullPointerException
   *                if bArray is <code>null</code>
   * @throws javacard.framework.SystemException with the following reason codes:
   * <ul>
   * <li>{@link javacard.framework.SystemException#ILLEGAL_VALUE ILLEGAL_VALUE}
   * if {@code bArray} is a persistent integrity-sensitive array object.</li>
   * </ul>
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   *      JCSystem.getUnusedCommitCapacity()
   */
  public static final short arrayFillNonAtomic(byte[] bArray, short bOff,
                                               short bLen, byte bValue)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             SystemException {

    for (short idx = 0; idx < bLen; idx++) {
      bArray[(short)(bOff + idx)] = bValue;
    }

    return (short)(bOff + bLen);
  }

  /**
   * Compares an array from the specified source array, beginning at the
   * specified position, with the specified position of the destination array
   * from left to right. Returns the ternary result of the comparison : less
   * than(-1), equal(0) or greater than(1).
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>srcOff</code><em> or </em><code>destOff</code><em> or
   * </em><code>length</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>srcOff+length</code><em> is greater than
   * </em><code>src.length</code><em>, the length of the
   * </em><code>src</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>destOff+length</code><em> is greater
   * than </em><code>dest.length</code><em>, the length of the
   * </em><code>dest</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>src</code><em> or
   * </em><code>dest</code><em> parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em>
   * </ul>
   * <p>
   * In addition to returning a {@code byte} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param src
   *            source byte array
   * @param srcOff
   *            offset within source byte array to start compare
   * @param dest
   *            destination byte array
   * @param destOff
   *            offset within destination byte array to start compare
   * @param length
   *            byte length to be compared
   * @return the result of the comparison as follows:
   *         <ul>
   *         <li> <code>0</code> if identical</li>
   *         <li> <code>-1</code> if the first miscomparing byte in source
   *         array is less than that in destination array</li>
   *         <li> <code>1</code> if the first miscomparing byte in source
   *         array is greater that that in destination array</li>
   *         </ul>
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if comparing all bytes would cause access of data outside
   *                array bounds
   * @exception java.lang.NullPointerException
   *                if either <code>src</code> or <code>dest</code> is
   *                <code>null</code>
   */
  public static final byte arrayCompare(byte[] src, short srcOff, byte[] dest,
                                        short destOff, short length)
      throws ArrayIndexOutOfBoundsException, NullPointerException {
    for (short idx = 0; idx < length; idx++) {
      if (src[(short)(srcOff + idx)] < dest[(short)(destOff + idx)]) {
        return -1;
      } else if (src[(short)(srcOff + idx)] == dest[(short)(destOff + idx)]) {
        continue;
      } else if (src[(short)(srcOff + idx)] < dest[(short)(destOff + idx)]) {
        return 1;
      }
    }

    return 0;
  }

  /**
   * Concatenates the two parameter bytes to form a short value.
   *
   * @param b1
   *            the first byte (high order byte)
   * @param b2
   *            the second byte (low order byte)
   * @return the short value the concatenated result
   */
  public static final short makeShort(byte b1, byte b2) {
    return (short)((b1 << 8) | (b2 & 0x00FF));
  }

  /**
   * Concatenates two bytes in a byte array to form a short value.
   *
   * @param bArray
   *            byte array
   * @param bOff
   *            offset within byte array containing first byte (the high order
   *            byte)
   * @return the short value the concatenated result
   * @throws NullPointerException
   *             if the <code>bArray</code> parameter is <code>null</code>
   * @throws ArrayIndexOutOfBoundsException
   *             if the <code>bOff</code> parameter is negative or if
   *             <code>bOff+2</code> is greater than the length of
   *             <code>bArray</code>
   */
  public static final short getShort(byte[] bArray, short bOff)
      throws NullPointerException, ArrayIndexOutOfBoundsException {
    return (short)(((bArray[bOff]) << 8) |
                   ((bArray[(short)(bOff + 1)]) & 0x00FF));
  }

  /**
   * Deposits the short value as two successive bytes at the specified offset
   * in the byte array.
   *
   * @param bArray
   *            byte array
   * @param bOff
   *            offset within byte array to deposit the first byte (the high
   *            order byte)
   * @param sValue
   *            the short value to set into array.
   * @return <code>bOff+2</code>
   *         <p>
   *         Note:
   *         <ul>
   *         <li><em>If the byte array is persistent, this operation is
   * performed atomically. If the commit capacity is exceeded, no operation is
   * performed and a </em><code>TransactionException</code><em> exception is
   * thrown.</em></li>
   *         </ul>
   * @exception javacard.framework.TransactionException
   *                if the operation would cause the commit capacity to be
   *                exceeded
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   *      JCSystem.getUnusedCommitCapacity()
   * @throws ArrayIndexOutOfBoundsException
   *             if the <code>bOff</code> parameter is negative or if
   *             <code>bOff+2</code> is greater than the length of
   *             <code>bArray</code>
   * @throws NullPointerException
   *             if the <code>bArray</code> parameter is <code>null</code>
   */
  public static final short setShort(byte[] bArray, short bOff, short sValue)
      throws TransactionException, NullPointerException,
             ArrayIndexOutOfBoundsException {

    boolean inTransaction = false;

    if (JCSystem.getTransactionDepth() == 0) {
      inTransaction = true;
    }

    if (!inTransaction) {
      JCSystem.beginTransaction();
    }

    bArray[bOff] = (byte)(sValue >> 8);
    bArray[(short)(bOff + 1)] = (byte)(sValue & 0x00FF);

    if (!inTransaction) {
      JCSystem.commitTransaction();
    }

    return (short)(bOff + 2);
  }
}
