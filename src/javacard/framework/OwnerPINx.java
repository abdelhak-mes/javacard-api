/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The {@code OwnerPINx} interface represents an Owner PIN,
 * extends Personal Identification Number functionality as defined in the
 * {@link PIN} interface, and provides the ability to update the PIN, update the
 * try limit and try counter and thus owner functionality.
 * <p>
 * If an implementation of this interface creates transient arrays, it must
 * ensure that they are {@code CLEAR_ON_RESET} transient objects.
 * <p>
 * Some methods of this interface are only suitable for sharing when there
 * exists a trust relationship among the applets. A typical shared usage would
 * use a proxy PIN interface which extends both the {@code PIN} interface and
 * the {@code Shareable} interface and re-declares the methods of the
 * {@code PIN} interface.
 * <p>
 * Any of the methods of the {@code OwnerPINx} may be called with a
 * transaction in progress. None of the methods of an
 * {@code OwnerPINx}-implementing class must initiate or alter the state
 * of the transaction if one is in progress.
 * <br>
 * While update of the internal state shall not participate in an on-going
 * transaction during PIN presentation (using {@link #check check}), update of
 * the internal state during administrative operations (e.g. {@link
 * #setTriesRemaining setTriesRemaining}) shall participate in a transaction, if
 * one is in progress. Therefore, care must be taken to not mix PIN presentation
 * operations and administrative operations within the same transaction.
 *
 * @see PINException
 * @see PIN
 * @see Shareable
 * @see JCSystem
 * @see OwnerPINBuilder
 *
 * @since 3.0.5
 */
public interface OwnerPINx extends PIN {

  /**
   * Sets a new value for the PIN and resets the {@code PIN} try counter to the
   * value of the {@code PIN} try limit. It also resets the validated flag.
   * <p>
   * This method copies the input pin parameter into an internal representation.
   * If a transaction is in progress, the new pin and try counter update must be
   * conditional i.e the copy operation must use the transaction facility.
   *
   * @param pin the byte array containing the new PIN value.
   * @param offset the starting offset in the pin array.
   * @param length the length of the new PIN.
   * @throws PINException with the following reason codes:
   * <ul>
   * <li>{@link PINException#ILLEGAL_VALUE ILLEGAL_VALUE} if length is greater
   * than configured maximum PIN size.</li>
   * </ul>
   * @see JCSystem#beginTransaction JCSystem.beginTransaction
   */
  void update(byte[] pin, short offset, byte length) throws PINException;

  /**
   * Returns the the maximum number of times an incorrect PIN can be
   * presented before the PIN is blocked.
   * <p>
   * In addition to returning a {@code byte} result, platform-implementations of
   * this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @return the maximum number of times an incorrect PIN can be presented.
   */
  byte getTryLimit();

  /**
   * Sets the {@code PIN} try limit to the value of the {@code limit} parameter
   * and resets the validated flag. The try counter is set to the the new try
   * limit value.
   *
   * @param limit the new value for the maximum number of times an
   * incorrect PIN can be presented; {@code limit} must be &ge;1.
   * @throws PINException with the following reason codes:
   * <ul>
   * <li>{@link PINException#ILLEGAL_VALUE ILLEGAL_VALUE} if {@code limit}
   * parameter is less than 1.</li>
   * </ul>
   */
  void setTryLimit(byte limit);

  /**
   * Sets the {@code PIN} try counter to the value of the {@code remaining}
   * parameter and resets the validated flag. If the new try counter value is
   * zero, it blocks the {@code PIN}.
   *
   * @param remaining the new value for the remaining number of times an
   * incorrect PIN can be presented; {@code remaining} must be greater or equal
   * to {@code 0} and lesser or equal to the current try limit.
   * @throws PINException with the following reason codes:
   * <ul>
   * <li>{@link PINException#ILLEGAL_VALUE ILLEGAL_VALUE} if {@code remaining}
   * parameter is less than {@code 0} or greater than the current try
   * limit.</li>
   * </ul>
   */
  void setTriesRemaining(byte remaining);
}
