package fr.gouv.ssi.nativeimpl;

/*-
 * #%L
 * Java Card API
 * %%
 * Copyright (C) 2020 National Cybersecurity Agency of France (ANSSI)
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import javacard.framework.AID;
import javacard.framework.Shareable;
import javacard.framework.SystemException;
import javacard.framework.TransactionException;
import javacardx.framework.util.UtilException;

/**
 * Class with native functions to implement
 *
 * @author Guillaume Bouffard
 */

public class NativeImplementation {

  /**
   * Native implementation of
   * <code>javacard.framework.service.BasicService.selectingApplet</code>
   * function
   *
   * @return <code>true</code> if applet SELECT FILE command is being
   *         processed
   *
   * @see javacard.framework.service.BasicService#selectingApplet()
   */
  public native boolean selectingApplet();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.isTransient()</code> function.
   *
   * @param theObj the object being queried
   * @return <code>NOT_A_TRANSIENT_OBJECT</code>,
   *         <code>CLEAR_ON_RESET</code>, or <code>CLEAR_ON_DESELECT</code>
   *
   * @see javacard.framework.JCSystem#isTransient(Object)
   */
  public static native byte isTransient(Object theObj);

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.makeTransientBooleanArray</code>
   * function.
   *
   * @param length
   *            the length of the boolean array
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
   * @return the new transient boolean array
   *
   * @see javacard.framework.JCSystem#makeTransientBooleanArray(short,byte)
   */
  public static native boolean[] makeTransientBooleanArray(short length,
                                                           byte event)
      throws NegativeArraySizeException, SystemException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.makeTransientByteArray</code>
   * function.
   *
   * @param length
   *            the length of the byte array
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
   * @return the new transient byte array
   *
   * @see javacard.framework.JCSystem#makeTransientByteArray(short,byte)
   */
  public static native byte[] makeTransientByteArray(short length, byte event)
      throws NegativeArraySizeException, SystemException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.makeTransientShortArray</code>
   * function.
   *
   * @param length
   *            the length of the short array
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
   * @return the new transient short array
   *
   * @see javacard.framework.JCSystem#makeTransientShortArray(short,byte)
   */
  public static native short[] makeTransientShortArray(short length, byte event)
      throws NegativeArraySizeException, SystemException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.makeTransientObjectArray</code>
   * function.
   *
   * @param length
   *            the length of the <code>Object</code> array
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
   * @return the new transient Object array
   *
   * @see javacard.framework.JCSystem#makeTransientObjectArray(short,byte)
   */
  public static native Object[] makeTransientObjectArray(short length,
                                                         byte event)
      throws NegativeArraySizeException, SystemException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.makeGlobalArray</code>
   * function.
   *
   * @param type
   *            the array type - must be one of :
   * <code>ARRAY_TYPE_BOOLEAN</code>, <code>ARRAY_TYPE_BYTE</code>,
   * <code>ARRAY_TYPE_SHORT</code>, <code>ARRAY_TYPE_INT</code>, or
   * <code>ARRAY_TYPE_OBJECT</code>
   * @param length
   *            the length of the global transient array
   * @throws NegativeArraySizeException
   *             if the <code>length</code> parameter is negative
   * @exception SystemException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>SystemException.ILLEGAL_VALUE</code> if type
   *                is not a valid type code. An implementation which does not
   *                support the "int" type may throw this exception if
   *                type is <code>ARRAY_TYPE_INT</code>
   *                <li><code>SystemException.NO_TRANSIENT_SPACE</code> if
   *                sufficient transient space is not available.
   *                </ul>
   * @return the new transient Object array
   *
   * @see javacard.framework.JCSystem#makeGlobalArray(byte,short)
   */
  public static native Object makeGlobalArray(byte type, short length);

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getAID</code>
   * function.
   *
   * @return the <code>AID</code> object
   *
   * @see javacard.framework.JCSystem#getAID()
   */
  public static native AID getAID();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.beginTransaction</code>
   * function.
   *
   * @exception TransactionException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TransactionException.IN_PROGRESS</code> if a
   *                transaction is already in progress.
   *                </ul>
   *
   * @see javacard.framework.JCSystem#beginTransaction()
   */
  public native static void beginTransaction() throws TransactionException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.abordTransaction</code>
   * function.
   *
   * @exception TransactionException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TransactionException.NOT_IN_PROGRESS</code>
   *                if a transaction is not in progress.
   *                </ul>
   *
   * @see javacard.framework.JCSystem#abortTransaction()
   */
  public static native void abortTransaction() throws TransactionException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.commitTransaction</code>
   * function.
   *
   * @exception TransactionException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>TransactionException.NOT_IN_PROGRESS</code>
   *                if a transaction is not in progress.
   *                </ul>
   *
   * @see javacard.framework.JCSystem#commitTransaction()
   */
  public static native void commitTransaction() throws TransactionException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getTransactionDepth</code>
   * function.
   *
   * @return 1 if transaction in progress, 0 if not
   *
   * @see javacard.framework.JCSystem#getTransactionDepth()
   */
  public static native byte getTransactionDepth();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getUnusedCommitCapacity</code>
   * function.
   *
   * @return the number of bytes left in the commit buffer
   *
   * @see javacard.framework.JCSystem#getUnusedCommitCapacity()
   */
  public static native short getUnusedCommitCapacity();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getMaxCommitCapacity</code>
   * function.
   *
   * @return the total number of bytes in the commit buffer
   *
   * @see javacard.framework.JCSystem#getMaxCommitCapacity()
   */
  public static native short getMaxCommitCapacity();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getPreviousContextAID</code>
   * function.
   *
   * @return the <code>AID</code> object of the previous context, or
   *         <code>null</code> if Java Card runtime environment
   *
   * @see javacard.framework.JCSystem#getPreviousContextAID()
   */
  public static native AID getPreviousContextAID();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getAvailableMemory</code>
   * function.
   *
   * @param memoryType
   *            the type of memory being queried. One of the
   *            <code>MEMORY_TYPE_*</code> constants value.
   * @return the upper bound on available bytes of memory for the specified
   *         type
   * @exception SystemException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>SystemException.ILLEGAL_VALUE</code> if
   *                <code>memoryType</code> is not a valid memory type.
   *                </ul>
   *
   * @see javacard.framework.JCSystem#getAvailableMemory(byte)
   */
  public static native short getAvailableMemory(byte memoryType);

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getAvailableMemory</code>
   * function.
   *
   * @param memoryType
   *            the type of memory being queried. One of the
   *            <code>MEMORY_TYPE_*</code> constants values.
   * @param buffer the output buffer for storing memory size information
   * @param offset the offset within the buffer where memory size
   *             information begins
   *
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if buffer[offset] or buffer[offset+1] outside array bounds
   * @exception java.lang.NullPointerException
   *                if buffer is <code>null</code>
   * @exception SystemException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>SystemException.ILLEGAL_VALUE</code> if
   *                <code>memoryType</code> is not a valid memory type.
   *                </ul>
   *
   * @see javacard.framework.JCSystem#getAvailableMemory(short[],short,byte)
   */
  public static native void getAvailableMemory(short[] buffer, short offset,
                                               byte memoryType)
      throws SystemException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getAppletShareableInterfaceObject</code>
   * function.
   *
   * @param serverAID
   *            the AID of the server applet
   * @param parameter
   *            optional parameter data
   * @return the shareable interface object or <code>null</code>
   * @exception SecurityException
   *                if the server applet is not multiselectable and is
   *                currently active on another logical channel
   *
   * @see
   *     javacard.framework.JCSystem#getAppletShareableInterfaceObject(AID,byte)
   */
  public static native Shareable
  getAppletShareableInterfaceObject(AID serverAID, byte parameter);

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.isObjectDeletionSupported</code>
   * function.
   *
   * @return <code>true</code> if the object deletion mechanism is
   *         supported, <code>false</code> otherwise
   *
   * @see javacard.framework.JCSystem#isObjectDeletionSupported()
   */
  public static native boolean isObjectDeletionSupported();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.requestObjectDeletion</code>
   * function.
   *
   * @exception SystemException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>SystemException.ILLEGAL_USE</code> if the
   *                object deletion mechanism is not implemented.
   *                </ul>
   *
   * @see javacard.framework.JCSystem#requestObjectDeletion()
   */
  public static native void requestObjectDeletion() throws SystemException;

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.getAssignedChannel</code>
   * function.
   *
   * @return the logical channel number in the range 0-19 assigned to the
   *         currently selected applet instance
   *
   * @see javacard.framework.JCSystem#getAssignedChannel()
   */
  public static native byte getAssignedChannel();

  /**
   * Native implementation of
   * <code>javacard.framework.JCSystem.isAppletAcive</code>
   * function.
   *
   * @param theApplet
   *            the AID of the applet object being queried
   * @return <code>true</code> if and only if the applet specified by the
   *         AID parameter is currently active on this or another logical
   *         channel
   *
   * @see javacard.framework.JCSystem#isAppletActive(AID)
   */
  public static native boolean isAppletActive(AID theApplet);

  /**
   * Native implementation of
   * <code>javacard.framework.SensitiveArrays.assertIntegrity</code>
   * function.
   *
   *  @param obj the integrity-sensitive array object being queried
   *
   * @throws NullPointerException if {@code obj} is {@code null}.
   * @throws SecurityException if the integrity of {@code obj} has been
   * compromised.
   * @throws SystemException with the following reason codes:
   * <ul>
   * <li>{@link javacard.framework.SystemException#ILLEGAL_VALUE ILLEGAL_VALUE}
   * if the specified object is not an integrity-sensitive array object.</li>
   * </ul>
   *
   * @see javacard.framework.SensitiveArrays#assertIntegrity(Object)
   */
  public static native void assertIntegrity(Object obj);

  /**
   * Native implementation of
   * <code>javacard.framework.SensitiveArrays.isIntegritySensitive</code>
   * function.
   *
   * @param obj the object being queried.
   *
   * @return {@code true} if the provided object is an integrity-sensitive
   * array; {@code false} otherwise.
   *
   * @throws NullPointerException if {@code obj} is {@code null}.
   *
   * @see javacard.framework.SensitiveArrays#isIntegritySensitive(Object)
   */
  public static native boolean isIntegritySensitive(Object obj);

  /**
   * Native implementation of
   * <code>javacard.framework.SensitiveArrays.isIntegritySensitiveArraysSupported</code>
   * function.
   *
   * @return {@code true} if integrity-sensitive arrays are supported; {@code
   *     false} otherwise.
   *
   * @see
   *     javacard.framework.SensitiveArrays#isIntegritySensitiveArraysSupported()
   */
  public static native boolean isIntegritySensitiveArraysSupported();

  /**
   * Native implementation of
   * <code>javacard.framework.SensitiveArrays.makeIntegritySensitiveArray</code>
   * function.
   *
   * @param type the array type - must be one of: {@link
   *     javacard.framework.JCSystem#ARRAY_TYPE_BOOLEAN ARRAY_TYPE_BOOLEAN},
   * {@link javacard.framework.JCSystem#ARRAY_TYPE_BYTE ARRAY_TYPE_BYTE}, {@link
   * javacard.framework.JCSystem#ARRAY_TYPE_SHORT ARRAY_TYPE_SHORT}, {@link
   * javacard.framework.JCSystem#ARRAY_TYPE_INT ARRAY_TYPE_INT} or {@link
   * javacard.framework.JCSystem#ARRAY_TYPE_OBJECT ARRAY_TYPE_OBJECT}.
   * @param memory the memory type - must be one of:
   * {@link javacard.framework.JCSystem#MEMORY_TYPE_PERSISTENT
   * MEMORY_TYPE_PERSISTENT}, {@link
   * javacard.framework.JCSystem#MEMORY_TYPE_TRANSIENT_RESET
   * MEMORY_TYPE_TRANSIENT_RESET} or
   * {@link javacard.framework.JCSystem#MEMORY_TYPE_TRANSIENT_DESELECT
   * MEMORY_TYPE_TRANSIENT_DESELECT}.
   * @param length the length of the sensitive array.
   *
   * @return the new integrity-sensitive array.
   *
   * @throws NegativeArraySizeException if the {@code length} parameter is
   * negative
   * @throws SystemException with the following reason codes:
   * <ul>
   * <li>{@link SystemException#ILLEGAL_USE ILLEGAL_USE} if integrity-sensitive
   * arrays are not supported.</li>
   * <li>{@link SystemException#ILLEGAL_VALUE ILLEGAL_VALUE} if {@code type} or
   * {@code memory} is not a valid type code. An implementation which does not
   * support integrity-sensitive array objects of {@code int} array type may
   * throw this exception.</li>
   * <li>{@link SystemException#NO_TRANSIENT_SPACE NO_TRANSIENT_SPACE} if no
   * sufficient transient space is available.</li>
   * <li>{@link SystemException#NO_RESOURCE NO_RESOURCE} if no sufficient
   * persistent space is available.</li>
   * </ul>
   *
   * @see
   *     javacard.framework.SensitiveArrays#makeIntegritySensitiveArray(byte,byte,short)
   */
  public static native Object makeIntegritySensitiveArray(byte type,
                                                          byte memory,
                                                          short length);

  /**
   * Native implementation of
   * <code>javacard.framework.SensitiveArrays.clearArray</code>
   * function.
   *
   * @param obj the array being cleared.
   * @return the length of the specified array object.
   *
   * @throws SystemException with the following reason codes:
   * <ul>
   * <li>{@link SystemException#ILLEGAL_VALUE ILLEGAL_VALUE} if the specified
   * object is not an integrity-sensitive array object.</li>
   * </ul>
   * @throws NullPointerException if {@code obj} is {@code null}.
   * @throws TransactionException if clearing would cause the commit capacity to
   * be exceeded.
   *
   * @see javacard.framework.SensitiveArrays#clearArray(Object)
   */
  public static native short clearArray(Object obj) throws TransactionException;

  /**
   * Native implementation of
   * <code>javacardx.framework.util.intx.JCint.makeTransientIntArray</code>
   * function.
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
   *
   * @see javacardx.framework.util.intx.JCint#makeTransientIntArray(short,byte)
   */
  public static native int[] makeTransientIntArray(short length, byte event)
      throws NegativeArraySizeException, SystemException;

  /**
   * Native implementation of
   * <code>javacardx.framework.util.ArrayLogic.arrayCopyRepack</code>
   * function.
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
   *
   *  @see
   *     javacardx.framework.util.ArrayLogic#arrayCopyRepack(Object,short,short,Object,short)
   */
  public static final native short arrayCopyRepack(Object src, short srcOff,
                                                   short srcLen, Object dest,
                                                   short destOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             TransactionException, UtilException;

  /**
   * Native implementation of
   * <code>javacardx.framework.util.ArrayLogic.arrayCopyRepackNonAtomic</code>
   * function.
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
   *
   *  @see
   *     javacardx.framework.util.ArrayLogic#arrayCopyRepackNonAtomic(Object,short,short,Object,short)
   */
  public static final native short
  arrayCopyRepackNonAtomic(Object src, short srcOff, short srcLen, Object dest,
                           short destOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException, SystemException;

  /**
   * Native implementation of
   * <code>javacardx.framework.util.ArrayLogic.arrayFillGeneric</code>
   * function.
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
   *
   *  @see
   *     javacardx.framework.util.ArrayLogic#arrayFillGeneric(Object,short,short,Object,short)
   */
  public static final native short arrayFillGeneric(Object theArray, short off,
                                                    short len, Object valArray,
                                                    short valOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException, TransactionException;

  /**
   * Native implementation of
   * <code>javacardx.framework.util.ArrayLogic.arrayFillGenericNonAtomic</code>
   * function.
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
   *
   *  @see
   *     javacardx.framework.util.ArrayLogic#arrayFillGenericNonAtomic(Object,short,short,Object,short)
   */
  public static final native short
  arrayFillGenericNonAtomic(Object theArray, short off, short len,
                            Object valArray, short valOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException, SystemException;

  /**
   * Native implementation of
   * <code>javacardx.framework.util.ArrayLogic.arrayCompareGeneric</code>
   * function.
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
   *
   *  @see
   *     javacardx.framework.util.ArrayLogic#arrayCompareGeneric(Object,short,Object,short,short)
   */
  public static final native byte arrayCompareGeneric(Object src, short srcOff,
                                                      Object dest,
                                                      short destOff,
                                                      short length)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException;

  /**
   * Native implementation of
   * <code>javacardx.framework.util.ArrayLogic.arrayFindGeneric</code>
   * function.
   *
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
   * @return the offset into the specified array where the first occurrence of
   *         specified value was found or -1 if the specified value does not
   *         occur in the specified portion of the array
   *
   * @see
   *     javacardx.framework.util.ArrayLogic#arrayFindGeneric(Object,short,byte[],short)
   */
  public static final native short
  arrayFindGeneric(Object theArray, short off, byte[] valArray, short valOff)
      throws ArrayIndexOutOfBoundsException, NullPointerException,
             UtilException;
}
