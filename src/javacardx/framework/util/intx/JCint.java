/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.framework.util.intx;

import fr.gouv.ssi.nativeimpl.NativeImplementation;
import javacard.framework.JCSystem;
import javacard.framework.SystemException;
import javacard.framework.TransactionException;

/**
 * The <code>JCint</code> class contains common utility functions using ints.
 * Some of the methods may be implemented as native functions for performance
 * reasons. All the methods in <code>JCint</code> class are static methods.
 * <p>
 * The methods <code>makeTransientIntArray()</code> and and
 * <code>setInt()</code>, refer to the persistence of array objects. The term
 * <em>persistent</em> means that arrays and their values persist from one CAD
 * session to the next, indefinitely. The <code>makeTransientIntArray()</code>
 * method is used to create transient int arrays. Constants related to
 * transience control are available in the <code>JCSystem</code> class.
 *
 * @see javacard.framework.JCSystem javacard.framework.JCSystem
 * @since 2.2.2
 */
public final class JCint {

  /**
   * Concatenates the four parameter bytes to form an int value.
   *
   * @param b1
   *            the first byte ( high order byte )
   * @param b2
   *            the second byte
   * @param b3
   *            the third byte
   * @param b4
   *            the fourth byte ( low order byte )
   * @return the int value the concatenated result
   */
  public static final int makeInt(byte b1, byte b2, byte b3, byte b4) {
    return (int)((b1 << 24) | ((b2 << 16) & 0xFF0000) | ((b3 << 8) & 0xFF00) |
                 (b4 & 0xFF));
  }

  /**
   * Concatenates the two parameter short values to form an int value.
   *
   * @param s1
   *            the first short value ( high order short value )
   * @param s2
   *            the second short value ( low order short value )
   * @return the int value the concatenated result
   */
  public static final int makeInt(short s1, short s2) {
    return (int)((s1 << 16) | (s2 & 0x0FFFF));
  }

  /**
   * Concatenates four bytes in a byte array to form a int value.
   *
   * @param bArray
   *            byte array
   * @param bOff
   *            offset within byte array containing first byte (the high order
   *            byte)
   * @return the int value the concatenated result
   * @throws NullPointerException
   *             if the <code>bArray</code> parameter is <code>null</code>
   * @throws ArrayIndexOutOfBoundsException
   *             if the <code>bOff</code> parameter is negative or if
   *             <code>bOff+4</code> is greater than the length of
   *             <code>bArray</code>
   */
  public static final int getInt(byte[] bArray, short bOff)
      throws NullPointerException, ArrayIndexOutOfBoundsException {
    return JCint.makeInt(bArray[bOff], bArray[(short)(bOff + 1)],
                         bArray[(short)(bOff + 2)], bArray[(short)(bOff + 3)]);
  }

  /**
   * Deposits the int value as four successive bytes at the specified offset
   * in the byte array.
   *
   * @param bArray
   *            byte array
   * @param bOff
   *            offset within byte array to deposit the first byte (the high
   *            order byte)
   * @param iValue
   *            the short value to set into array.
   * @return <code>bOff+4</code>
   *         <p>
   *         Note:
   *         <ul>
   *         <li><em>If the byte array is persistent, this operation is
   * performed atomically. If the commit capacity is exceeded, no operation is
   * performed and a </em><code>TransactionException</code><em> exception is
   * thrown.</em></li>
   *         </ul>
   * @exception TransactionException
   *                if the operation would cause the commit capacity to be
   *                exceeded
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   *      javacard.framework.JCSystem.getUnusedCommitCapacity()
   * @throws NullPointerException
   *             if the <code>bArray</code> parameter is <code>null</code>
   * @throws ArrayIndexOutOfBoundsException
   *             if the <code>bOff</code> parameter is negative or if
   *             <code>bOff+4</code> is greater than the length of
   *             <code>bArray</code>
   */
  public static final short setInt(byte[] bArray, short bOff, int iValue)
      throws TransactionException, NullPointerException,
             ArrayIndexOutOfBoundsException {
    boolean inTransaction = false;

    if (JCSystem.getTransactionDepth() == 0) {
      inTransaction = true;
    }

    if (!inTransaction) {
      JCSystem.beginTransaction();
    }

    bArray[bOff] = (byte)((iValue >> 24) & 0x00FF);
    bArray[bOff + 1] = (byte)((iValue >> 16) & 0x00FF);
    bArray[bOff + 2] = (byte)((iValue >> 8) & 0x00FF);
    bArray[bOff + 3] = (byte)(iValue & 0x00FF);

    if (!inTransaction) {
      JCSystem.commitTransaction();
    }

    return (short)(bOff + 4);
  }

  /**
   * Creates a transient int array with the specified array length.
   *
   * @param length
   *            the length of the int array
   * @param event
   *            the <code>CLEAR_ON...</code> event which causes the array
   *            elements to be cleared
   * @throws NegativeArraySizeException
   *             if the <code>length</code> parameter is negative
   * @exception SystemException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>SystemException.ILLEGAL_VALUE</code> if event
   *                is not a valid event code.
   *                <li><code>SystemException.NO_TRANSIENT_SPACE</code> if
   *                sufficient transient space is not available.
   *                <li><code>SystemException.ILLEGAL_TRANSIENT</code> if
   *                the current applet context is not the currently selected
   *                applet context and <code>CLEAR_ON_DESELECT</code> is
   *                specified.
   *                </ul>
   * @return the new transient int array
   * @see javacard.framework.JCSystem javacard.framework.JCSystem
   */
  public static int[] makeTransientIntArray(short length, byte event)
      throws NegativeArraySizeException, SystemException {
    return NativeImplementation.makeTransientIntArray(length, event);
  }
}
