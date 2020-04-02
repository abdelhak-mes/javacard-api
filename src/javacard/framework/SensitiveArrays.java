/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

import fr.gouv.ssi.nativeimpl.NativeImplementation;

/**
 * The {@code SensitiveArrays} class provides methods for creating and handling
 * integrity-sensitive array objects.
 * <p>
 * The {@link #makeIntegritySensitiveArray(byte, byte, short)
 * makeIntegritySensitiveArray} method can be used to create integrity-sensitive
 * array objects. Integrity-sensitive array objects include an integrity control
 * element that is automatically and transparently updated by the platform
 * whenever the array content is legally modified. The integrity control element
 * is automatically checked by the platform before any array update operation.
 * If an inconsistency is detected during an array integrity check a {@code
 * SecurityException} is thrown. The integrity control element is not
 * automatically checked by the platform before a read operation: it is up to
 * the applet to trigger such a check by calling the {@link
 * #assertIntegrity(Object) assertIntegrity} method.
 *
 * @since 3.0.5
 */
public class SensitiveArrays {

  /**
   * Checks the integrity of the specified integrity-sensitive array object.
   *
   * @param obj the integrity-sensitive array object being queried
   *
   * @throws NullPointerException if {@code obj} is {@code null}.
   * @throws SecurityException if the integrity of {@code obj} has been
   * compromised.
   * @throws SystemException with the following reason codes:
   * <ul>
   * <li>{@link SystemException#ILLEGAL_VALUE ILLEGAL_VALUE} if the specified
   * object is not an integrity-sensitive array object.</li>
   * </ul>
   *
   * @see #makeIntegritySensitiveArray(byte, byte, short)
   */
  public static void assertIntegrity(Object obj) {
    NativeImplementation.assertIntegrity(obj);
  }

  /**
   * Returns whether the provided object is an integrity-sensitive array.
   * <p>
   * In addition to returning a {@code boolean} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult} class.
   *
   * @param obj the object being queried.
   *
   * @return {@code true} if the provided object is an integrity-sensitive
   * array; {@code false} otherwise.
   *
   * @throws NullPointerException if {@code obj} is {@code null}.
   *
   * @see #makeIntegritySensitiveArray(byte, byte, short)
   */
  public static boolean isIntegritySensitive(Object obj) {
    return NativeImplementation.isIntegritySensitive(obj);
  }

  /**
   * Returns whether the implementation for the Java Card platform supports
   * integrity-sensitive arrays.
   * <p>
   * In addition to returning a {@code boolean} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult} class.
   *
   * @return {@code true} if integrity-sensitive arrays are supported; {@code
   *     false} otherwise.
   *
   * @see #makeIntegritySensitiveArray(byte, byte, short)
   */
  public static boolean isIntegritySensitiveArraysSupported() {
    return NativeImplementation.isIntegritySensitiveArraysSupported();
  }

  /**
   * Creates an integrity-sensitive array of the specified array and memory
   * type, with the specified array length.
   * <p>
   * Note: only the content of the array is sensitive, in particular in the case
   * of an array of object references, only the references stored in the array
   * are sensitive, not the objects themselves.
   *
   * @param type the array type - must be one of: {@link
   *     JCSystem#ARRAY_TYPE_BOOLEAN ARRAY_TYPE_BOOLEAN},
   * {@link JCSystem#ARRAY_TYPE_BYTE ARRAY_TYPE_BYTE}, {@link
   * JCSystem#ARRAY_TYPE_SHORT ARRAY_TYPE_SHORT}, {@link JCSystem#ARRAY_TYPE_INT
   * ARRAY_TYPE_INT} or {@link JCSystem#ARRAY_TYPE_OBJECT ARRAY_TYPE_OBJECT}.
   * @param memory the memory type - must be one of:
   * {@link JCSystem#MEMORY_TYPE_PERSISTENT MEMORY_TYPE_PERSISTENT}, {@link
   * JCSystem#MEMORY_TYPE_TRANSIENT_RESET MEMORY_TYPE_TRANSIENT_RESET} or
   * {@link JCSystem#MEMORY_TYPE_TRANSIENT_DESELECT
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
   */
  public static Object makeIntegritySensitiveArray(byte type, byte memory,
                                                   short length) {
    return NativeImplementation.makeIntegritySensitiveArray(type, memory,
                                                            length);
  }

  /**
   * Clears the specified array object. This method sets all the values in the
   * array to {@code null} for
   * {@link JCSystem#ARRAY_TYPE_OBJECT ARRAY_TYPE_OBJECT} arrays and to zero for
   * all other array types. The integrity of the array object is not checked by
   * this method before the clearing and the reinitialization of the
   * integrity-control information.
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult} class.
   * <p>
   * Note:
   * <ul>
   * <li><em>The clearing operation is atomic</em>.</li>
   * <li><em>When a transaction is in progress, the clearing of a
   * persistent integrity-sensitive array participates
   * to the transaction and is therefore subject
   * to atomic commit capacity limitations. If the commit capacity is exceeded,
   * no clearing is performed and a </em>{@code TransactionException}<em>
   * exception is thrown.</em></li>
   * </ul>
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
   * @see JCSystem#getUnusedCommitCapacity()
   */
  public static short clearArray(Object obj) throws TransactionException {
    return NativeImplementation.clearArray(obj);
  }
}
