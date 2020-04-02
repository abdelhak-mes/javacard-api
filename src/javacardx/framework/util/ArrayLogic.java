/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.util;

import fr.gouv.ssi.nativeimpl.NativeImplementation;
import javacard.framework.SystemException;
import javacard.framework.TransactionException;

/**
 * The <code>ArrayLogic</code> class contains common utility functions for
 * manipulating arrays of primitive components - byte, short or int. Some of the
 * methods may be implemented as native functions for performance reasons. All
 * the methods in <code>ArrayLogic</code> class are static methods.
 * <p>
 * Some methods of <code>ArrayLogic</code>, namely
 * <code>arrayCopyRepack()</code>, <code>arrayCopyRepackNonAtomic()</code>
 * and <code>arrayFillGenericNonAtomic()</code>, refer to the persistence of
 * array objects. The term <em>persistent</em> means that arrays and their
 * values persist from one CAD session to the next, indefinitely. The
 * <code>JCSystem</code> class is used to control the persistence and
 * transience of objects.
 *
 * @see javacard.framework.JCSystem javacard.framework.JCSystem
 * @since 2.2.2
 */
public final class ArrayLogic {

  /**
   * Copies data from the specified source array, beginning at the specified
   * position, to the specified position of the destination array. Note that
   * this method may be used to copy from an array of any primitive component -
   * byte, short or int to another (or same) array of any primitive component -
   * byte, short or int. If the source array primitive component size is
   * smaller than that of the destination array, a packing conversion is
   * performed; if the source array primitive component size is larger than
   * that of the destination array, an unpacking operation is performed; if
   * the source and destination arrays are of the same component type, simple
   * copy without any repacking is performed.
   * <p>
   * Note:
   * <ul>
   * <li><em>If the source array is a byte array and the destination is a short
   * array, then pairs of source array bytes are concatenated (high order byte
   * component first) to form short components
   * before being written to the destination short array. If the
   * </em><code>srcLen</code><em> parameter is not a multiple of 2, an
   * </em><code>UtilException</code><em> exception is thrown.</em></li>
   * <li><em>If the source array is a byte array and the destination is an int
   * array, 4 bytes of the source array are concatenated at a time (high order
   * byte component first) to form int components before being written to the
   * destination int array. If the </em><code>srcLen</code><em> parameter is not
   * a multiple of 4, an </em><code>UtilException</code><em> exception is
   * thrown.</em></li> <li><em>If the source array is a short array and the
   * destination is an int array, then pairs of source array bytes are
   * concatenated (high order short component first) to form int components
   * before being written to the destination int array. If the
   * </em><code>srcLen</code><em> parameter is not a multiple of 2, an
   * </em><code>UtilException</code><em> exception is thrown.</em></li>
   * <li><em>If the source array is a short array and the destination is a byte
   * array, then each short component is split into 2 bytes (high order byte
   * component first)
   * before being written sequentially to the destination byte array.</em></li>
   * <li><em>If the source array is a int array and the destination is a short
   * array, then each int component is split into 2 shorts (high order short
   * component first)
   * before being written sequentially to the destination short array.</em></li>
   * <li><em>If the source array is a int array and the destination is a byte
   * array, then each int component is split into 4 bytes (high order byte
   * component first)
   * before being written sequentially to the destination byte array.</em></li>
   * <li><em>If </em><code>srcOff</code><em> or </em><code>destOff</code><em> or
   * </em><code>srcLen</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em></li> <li><em>If </em><code>srcOff+srcLen</code><em> is greater
   * than </em><code>src.length</code><em>, the length of the
   * </em><code>src</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no copy is performed.</em></li>
   * <li><em>If offset into the </em><code>dest</code><em> array would become
   * greater than </em><code>dest.length</code><em>, the length of the
   * </em><code>dest</code><em> array during the copy operation
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no copy is performed.</em></li>
   * <li><em>If </em><code>src</code><em> or </em><code>dest</code><em>
   * parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em></li>
   * <li><em>If the <code>src</code> and <code>dest</code> arguments refer to
   * the same array object, then the copying is performed as if the components
   * at positions </em><code>srcOff</code><em> through
   * </em><code>srcOff+srcLen-1</code><em> were first copied to a temporary
   * array with </em><code>srcLen</code><em> components and then the contents of
   * the temporary array were copied into positions
   * </em><code>destOff</code><em> through
   * </em><code>destOff+srcLen-1</code><em> of the destination array.</em></li>
   * <li><em>If the destination array is persistent, the entire copy is
   * performed atomically.</em></li> <li><em>The copy operation is subject to
   * atomic commit capacity limitations. If the commit capacity is exceeded, no
   * copy is performed and a </em><code>TransactionException</code><em>
   * exception is thrown.</em></li>
   * </ul>
   *
   * @param src
   *            source array object
   * @param srcOff
   *            offset within source array to start copy from
   * @param srcLen
   *            number of source component values to be copied from the source
   *            array
   * @param dest
   *            destination array object
   * @param destOff
   *            offset within destination array to start copy into
   * @return a value of one more than the offset within the <code>dest</code>
   *         array where the last copy was performed
   * @exception ArrayIndexOutOfBoundsException
   *                if copying would cause access of data outside array bounds
   * @exception NullPointerException
   *                if either <code>src</code> or <code>dest</code> is
   *                <code>null</code>
   * @exception TransactionException
   *                if copying would cause the commit capacity to be exceeded
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   *      javacard.framework.JCSystem.getUnusedCommitCapacity()
   * @exception UtilException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>UtilException.ILLEGAL_VALUE</code> if
   * <code>src</code> or <code>dest</code> is not an array of primitive
   *                components, or if the <code>srcLen</code> parameter is
   *                incorrect
   *                </ul>
   */
  public static final short arrayCopyRepack(Object src, short srcOff,
                                            short srcLen, Object dest,
                                            short destOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             TransactionException, UtilException {
    return NativeImplementation.arrayCopyRepack(src, srcOff, srcLen, dest,
                                                destOff);
  }

  /**
   * Non-atomically copies data from the specified source array, beginning at
   * the specified position, to the specified position of the destination
   * array. Note that this method may be used to copy from an array of any
   * primitive component - byte, short or int to another (or same) array of
   * any primitive component - byte, short or int. If the source array
   * primitive component size is smaller than that of the destination array, a
   * packing conversion is performed; if the source array primitive component
   * size is larger than that of the destination array, an unpacking operation
   * is performed; if the source and destination arrays are of the same
   * component type, simple copy without any repacking is performed.
   * <p>
   * This method does not use the transaction facility during the copy
   * operation even if a transaction is in progress. Thus, this method is
   * suitable for use only when the contents of the destination array can be
   * left in a partially modified state in the event of a power loss in the
   * middle of the copy operation.
   * <p>
   * Note:
   * <ul>
   * <li><em>If the source array is a byte array and the destination is a short
   * array, then pairs of source array bytes are concatenated (high order byte
   * component first) to form short components
   * before being written to the destination short array. If the
   * </em><code>srcLen</code><em> parameter is not a multiple of 2, an
   * </em><code>UtilException</code><em> exception is thrown.</em></li>
   * <li><em>If the source array is a byte array and the destination is an int
   * array, 4 bytes of the source array are concatenated at a time (high order
   * byte component first) to form int components before being written to the
   * destination int array. If the </em><code>srcLen</code><em> parameter is not
   * a multiple of 4, an </em><code>UtilException</code><em> exception is
   * thrown.</em></li> <li><em>If the source array is a short array and the
   * destination is an int array, then pairs of source array bytes are
   * concatenated (high order short component first) to form int components
   * before being written to the destination int array. If the
   * </em><code>srcLen</code><em> parameter is not a multiple of 2, an
   * </em><code>UtilException</code><em> exception is thrown.</em></li>
   * <li><em>If the source array is a short array and the destination is a byte
   * array, then each short component is split into 2 bytes (high order byte
   * component first)
   * before being written sequentially to the destination byte array.</em></li>
   * <li><em>If the source array is a int array and the destination is a short
   * array, then each int component is split into 2 shorts (high order short
   * component first)
   * before being written sequentially to the destination short array.</em></li>
   * <li><em>If the source array is a int array and the destination is a byte
   * array, then each int component is split into 4 bytes (high order byte
   * component first)
   * before being written sequentially to the destination byte array.</em></li>
   * <li><em>If </em><code>srcOff</code><em> or </em><code>destOff</code><em> or
   * </em><code>srcLen</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em></li> <li><em>If </em><code>srcOff+srcLen</code><em> is greater
   * than </em><code>src.length</code><em>, the length of the
   * </em><code>src</code><em> array a
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no copy is performed.</em></li>
   * <li><em>If offset into the </em><code>dest</code><em> array would become
   * greater than </em><code>dest.length</code><em>, the length of the
   * </em><code>dest</code><em> array during the copy operation
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is thrown
   * and no copy is performed.</em></li>
   * <li><em>If </em><code>src</code><em> or </em><code>dest</code><em>
   * parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em></li>
   * <li><em>If the <code>src</code> and <code>dest</code> arguments refer to
   * the same array object, then the copying is performed as if the components
   * at positions </em><code>srcOff</code><em> through
   * </em><code>srcOff+srcLen-1</code><em> were first copied to a temporary
   * array with </em><code>srcLen</code><em> components and then the contents of
   * the temporary array were copied into positions
   * </em><code>destOff</code><em> through
   * </em><code>destOff+srcLen-1</code><em> of the destination array.</em></li>
   * <li><em>Non atomic array operations on persistent integrity-sensitive
   * arrays created using the {@link javacard.framework.SensitiveArrays} API are
   * not supported; therefore if the </em><code>dest</code><em> array parameter
   * is a persistent integrity-sensitive array a {@code SystemException}
   * exception is thrown.</em>
   * </ul>
   *
   * @param src
   *            source array object
   * @param srcOff
   *            offset within source array to start copy from
   * @param srcLen
   *            number of source component values to be copied from the source
   *            array
   * @param dest
   *            destination array object
   * @param destOff
   *            offset within destination array to start copy into
   * @return a value of one more than the offset within the <code>dest</code>
   *         array where the last copy was performed
   * @exception ArrayIndexOutOfBoundsException
   *                if copying would cause access of data outside array bounds
   * @exception NullPointerException
   *                if either <code>src</code> or <code>dest</code> is
   *                <code>null</code>
   * @exception UtilException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>UtilException.ILLEGAL_VALUE</code> if
   * <code>src</code> or <code>dest</code> is not an array of primitive
   *                components, or if the <code>srcLen</code> parameter is
   *                incorrect
   *                </ul>
   * @throws javacard.framework.SystemException with the following reason codes:
   * <ul>
   * <li>{@link javacard.framework.SystemException#ILLEGAL_VALUE ILLEGAL_VALUE}
   * if {@code dest} is a persistent integrity-sensitive array object.</li>
   * </ul>
   */
  public static final short arrayCopyRepackNonAtomic(Object src, short srcOff,
                                                     short srcLen, Object dest,
                                                     short destOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException, SystemException {
    return NativeImplementation.arrayCopyRepackNonAtomic(src, srcOff, srcLen,
                                                         dest, destOff);
  }

  /**
   * Fills the array of primitive components beginning at the
   * specified position, for the specified length with the specified value.
   * Note that this method may be used to fill an array of any primitive
   * component type - byte, short or int. The value used for the fill
   * operation is itself specified using an array (<code>valArray</code>)
   * of the same primitive component type at offset <code>valOff</code>.
   * <p>
   * The following code snippet shows how this method is typically used:
   *
   * <pre>
   *   public short[] myArray = new short[10];
   *  ..
   *  // Fill the entire array myArray of 10 short components with the value
   * 0x1234 myArray[0] = (short)0x1234; ArrayLogic.arrayFillGeneric(myArray,
   * (short)0, (short)10, myArray, (short)0);
   *  ..
   * </pre>
   *
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>off</code><em> or </em><code>len</code><em> or
   * </em><code>valOff</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>off+len</code><em> is greater than
   * </em><code>theArray.length</code><em>, the length of the
   * </em><code>theArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>valOff</code><em> is equal to or greater
   * than </em><code>valArray.length</code><em>, the length of the
   * </em><code>valArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>theArray</code><em> or
   * </em><code>valArray</code><em> parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em>
   * <li><em>If the destination array is persistent, the entire copy is
   * performed atomically.</em></li> <li><em>The fill operation is subject to
   * atomic commit capacity limitations. If the commit capacity is exceeded, no
   * filling is performed and a </em><code>TransactionException</code><em>
   * exception is thrown.</em></li>
   * </ul>
   *
   * @param theArray
   *            the array object
   * @param off
   *            offset within array to start filling the specified value
   * @param len
   *            the number of component values to be filled
   * @param valArray
   *            the array object containing the fill value
   * @param valOff
   *            the offset within the <code>valArray</code> array containing
   *            the fill value
   * @return <code>off+len</code>
   * @exception ArrayIndexOutOfBoundsException
   *                if the fill operation would cause access of data outside
   *                array bounds
   * @exception NullPointerException
   *                if theArray or valArray is <code>null</code>
   * @exception UtilException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>UtilException.ILLEGAL_VALUE</code> if
   *                <code>theArray</code> or <code>valArray</code> is not
   *                an array of primitive components
   *                <li><code>UtilException.TYPE_MISMATCHED</code> if the
   *                <code>valArray</code> parameter is not an array of the
   *                same primitive component type as the <code>theArray</code>.
   *                </ul>
   * @exception TransactionException
   *                if filling would cause the commit capacity to be exceeded
   * @since 3.0.5
   */
  public static final short arrayFillGeneric(Object theArray, short off,
                                             short len, Object valArray,
                                             short valOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException, TransactionException {
    return NativeImplementation.arrayFillGeneric(theArray, off, len, valArray,
                                                 valOff);
  }

  /**
   * Fills the array of primitive components(non-atomically) beginning at the
   * specified position, for the specified length with the specified value.
   * Note that this method may be used to fill an array of any primitive
   * component type - byte, short or int. The value used for the fill
   * operation is itself specified using an array (<code>valArray</code>)
   * of the same primitive component type at offset <code>valOff</code>.
   * <p>
   * This method does not use the transaction facility during the fill
   * operation even if a transaction is in progress. Thus, this method is
   * suitable for use only when the contents of the array can be left in a
   * partially filled state in the event of a power loss in the middle of the
   * fill operation.
   * <p>
   * The following code snippet shows how this method is typically used:
   *
   * <pre>
   *   public short[] myArray = new short[10];
   *  ..
   *  // Fill the entire array myArray of 10 short components with the value
   * 0x1234 myArray[0] = (short)0x1234;
   *  ArrayLogic.arrayFillGenericNonAtomic(myArray, (short)0, (short)10,
   * myArray, (short)0);
   *  ..
   * </pre>
   *
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>off</code><em> or </em><code>len</code><em> or
   * </em><code>valOff</code><em> parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>off+len</code><em> is greater than
   * </em><code>theArray.length</code><em>, the length of the
   * </em><code>theArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>valOff</code><em> is equal to or greater
   * than </em><code>valArray.length</code><em>, the length of the
   * </em><code>valArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>theArray</code><em> or
   * </em><code>valArray</code><em> parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em>
   * <li><em>If power is lost during the copy operation and the array is
   * persistent, a partially changed array could result.</em> <li><em>The
   * </em><code>len</code><em> parameter is not constrained by the atomic commit
   * capacity limitations.</em> <li><em>Non atomic array operations on
   * persistent integrity-sensitive arrays created using the {@link
   * javacard.framework.SensitiveArrays} API are not supported; therefore if the
   * </em><code>theArray</code><em> array parameter is a persistent
   * integrity-sensitive array a {@code SystemException} exception is
   * thrown.</em>
   * </ul>
   *
   * @param theArray
   *            the array object
   * @param off
   *            offset within array to start filling the specified value
   * @param len
   *            the number of component values to be filled
   * @param valArray
   *            the array object containing the fill value
   * @param valOff
   *            the offset within the <code>valArray</code> array containing
   *            the fill value
   * @return <code>off+len</code>
   * @exception ArrayIndexOutOfBoundsException
   *                if the fill operation would cause access of data outside
   *                array bounds
   * @exception NullPointerException
   *                if theArray or valArray is <code>null</code>
   * @exception UtilException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>UtilException.ILLEGAL_VALUE</code> if
   *                <code>theArray</code> or <code>valArray</code> is not
   *                an array of primitive components
   *                <li><code>UtilException.TYPE_MISMATCHED</code> if the
   *                <code>valArray</code> parameter is not an array of the
   *                same primitive component type as the <code>theArray</code>.
   *                </ul>
   * @throws javacard.framework.SystemException with the following reason codes:
   * <ul>
   * <li>{@link javacard.framework.SystemException#ILLEGAL_VALUE ILLEGAL_VALUE}
   * if {@code theArray} is a persistent integrity-sensitive array object.</li>
   * </ul>
   */
  public static final short
  arrayFillGenericNonAtomic(Object theArray, short off, short len,
                            Object valArray, short valOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException, SystemException {
    return NativeImplementation.arrayFillGenericNonAtomic(theArray, off, len,
                                                          valArray, valOff);
  }

  /**
   * Compares an array from the specified source array, beginning at the
   * specified position, with the specified position of the destination array
   * from left to right. Note that this method may be used to compare any two
   * arrays of the same primitive component type - byte, short or int. Returns
   * the ternary result of the comparison : less than(-1), equal(0) or greater
   * than(1).
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
   *
   * @param src
   *            source array object
   * @param srcOff
   *            offset within source array to start compare
   * @param dest
   *            destination array object
   * @param destOff
   *            offset within destination array to start compare
   * @param length
   *            length to be compared
   * @return the result of the comparison as follows:
   *         <ul>
   *         <li> <code>0</code> if identical</li>
   *         <li> <code>-1</code> if the first miscomparing primitive
   *         component in source array is less than that in destination
   * array</li> <li> <code>1</code> if the first miscomparing primitive
   *         component in source array is greater than that in destination
   *         array</li>
   *         </ul>
   * @exception ArrayIndexOutOfBoundsException
   *                if comparing all the components would cause access of data
   *                outside array bounds
   * @exception NullPointerException
   *                if either <code>src</code> or <code>dest</code> is
   *                <code>null</code>
   * @exception UtilException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>UtilException.ILLEGAL_VALUE</code> if
   *                <code>src</code> or <code>dest</code> is not an array
   *                of primitive components, or if the <code>length</code>
   *                parameter is incorrect
   *                <li><code>UtilException.TYPE_MISMATCHED</code> if the
   *                <code>dest</code> parameter is not an array of the same
   *                primitive component type.
   *                </ul>
   */
  public static final byte arrayCompareGeneric(Object src, short srcOff,
                                               Object dest, short destOff,
                                               short length)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException {
    return NativeImplementation.arrayCompareGeneric(src, srcOff, dest, destOff,
                                                    length);
  }

  /**
   * Finds the first occurrence of the specified value within the specified
   * array. The search begins at the specified position and proceeds until the
   * end of the array. Note that this method may be used to search an array of
   * any primitive component type - byte, short or int. The value used in the
   * search operation is itself specified by the appropriate number of
   * consecutive bytes at offset <code>valOff</code> in the byte array
   * parameter <code>valArray</code>.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>off</code><em> or </em><code>valOff</code><em>
   * parameter is negative an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>off</code><em> is greater than
   * </em><code>theArray.length</code><em>, the length of the
   * </em><code>theArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If </em><code>theArray</code><em> or
   * </em><code>valArray</code><em> parameter is </em><code>null</code><em> a
   * </em><code>NullPointerException</code><em> exception is thrown.</em>
   * <li><em>If the specified array is an array of byte components, then the
   * byte at <code>valOff</code> in the <code>valArray</code> is used as the
   * search value. If </em><code>valOff+1</code><em> is greater than
   * </em><code>valArray.length</code><em>, the length of the
   * </em><code>valArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If the specified array is an array of short
   * components, then 2 consecutive bytes beginning at <code>valOff</code> in
   * the <code>valArray</code> are concatenated (high order byte component
   * first) to form the search value. If </em><code>valOff+2</code><em> is
   * greater than </em><code>valArray.length</code><em>, the length of the
   * </em><code>valArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em> <li><em>If the specified array is an array of int components,
   * then 4 consecutive bytes beginning at <code>valOff</code> in the
   * <code>valArray</code> are concatenated (high order byte component first) to
   * form the search value. If </em><code>valOff+4</code><em> is greater than
   * </em><code>valArray.length</code><em>, the length of the
   * </em><code>valArray</code><em> array an
   * </em><code>ArrayIndexOutOfBoundsException</code><em> exception is
   * thrown.</em>
   * </ul>
   *
   * @return the offset into the specified array where the first occurrence of
   *         specified value was found or -1 if the specified value does not
   *         occur in the specified portion of the array
   * @param theArray
   *            the array object to search
   * @param off
   *            offset within the array to start serching for the specified
   *            value
   * @param valArray
   *            the array object containing the search value
   * @param valOff
   *            the offset within the <code>valArray</code> array containing
   *            the search value
   * @exception ArrayIndexOutOfBoundsException
   *                if the search operation would cause access of data outside
   *                array bounds
   * @exception NullPointerException
   *                if <code>theArray</code> is <code>null</code>
   * @exception UtilException
   *                with the following reason code:
   *                <ul>
   *                <li><code>UtilException.ILLEGAL_VALUE</code> if
   *                <code>theArray</code> is not an array of primitive
   *                components.
   *                </ul>
   */
  public static final short arrayFindGeneric(Object theArray, short off,
                                             byte[] valArray, short valOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException {
    return NativeImplementation.arrayFindGeneric(theArray, off, valArray,
                                                 valOff);
  }
}
