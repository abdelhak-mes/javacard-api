/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The {@code OwnerPINxWithPredecrement} interface represents an Owner PIN,
 * extends Personal Identification Number functionality as defined in the
 * {@link PIN} interface, and provides the ability to update the PIN, update the
 * try limit and try counter and thus owner functionality.
 * <p>
 * The {@code OwnerPINxWithPredecrement} interface extends the
 * {@link OwnerPINx} to support the decrementing of the tries counter
 * before any PIN validation attempts.
 *
 * @see OwnerPINx
 * @see OwnerPINBuilder
 *
 * @since 3.0.5
 */
public interface OwnerPINxWithPredecrement extends OwnerPINx {

  /**
   * Decrements the try counter if the {@code PIN} is not blocked. It also
   * resets the validated flag. This method must be called prior to calling the
   * {@link #check check} method; the state for enforcing this constraint
   * must be implemented as a {@code CLEAR_ON_RESET} transient object; it must
   * also be cleared when the {@code check} method is called as well as when any
   * other public method that affects the try counter, the validated flag, or
   * the blocking state is called.
   * <p>
   * Even if a
   * transaction is in progress, update of internal state - the try counter, the
   * validated flag, and the blocking state, shall not participate in the
   * transaction.
   * <p>
   * In addition to returning a {@code byte} result, platform-implementations of
   * this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @return the number of times remaining after decrementing.
   */
  byte decrementTriesRemaining();

  /**
   * Compares <code>pin</code> against the PIN value without decrementing the
   * try counter. If the <code>PIN</code> is not blocked then: if the PIN value
   * matches, it sets the validated flag and resets the try counter to its
   * maximum; otherwise if the PIN value does not match, it resets the validated
   * flag, decrements the try counter - if not assumed to having been
   * pre-decremented - and, if the counter has reached zero, blocks the
   * <code>PIN</code>.
   * <p>
   * Even if a transaction is in progress, update of internal state - the try
   * counter, the validated flag, and the blocking state, shall not participate
   * in the transaction.
   * <p>
   * Note:
   * <ul>
   * <li><em>This method does not decrement the try counter. The
   * try counter must have been pre-decremented by a prior call
   * to the {@code decrementTriesRemaining} method.
   * If the {@code decrementTriesRemaining} method has not been
   * called prior to calling this method then a {@code PINException} exception
   * is thrown; the state for enforcing this constraint must be implemented as a
   * {@code CLEAR_ON_RESET} transient object; it must also be cleared when this
   * method is called as well as when any other public method that affects the
   * try counter, the validated flag, or the blocking state is called.</em></li>
   * <li><em>If </em>{@code NullPointerException}<em> or
   * </em>{@code ArrayIndexOutOfBoundsException}<em> is thrown, the validated
   * flag must be set to false and, the {@code PIN} blocked if the counter
   * reaches zero.</em></li>
   * <li><em>If </em>{@code offset}<em> or </em>{@code length}<em> parameter is
   * negative an </em>{@code ArrayIndexOutOfBoundsException}<em> exception is
   * thrown.</em></li>
   * <li><em>If </em>{@code offset+length}<em> is greater than
   * </em>{@code pin.length}<em>, the length of the </em>{@code pin}<em> array,
   * an </em>{@code ArrayIndexOutOfBoundsException}<em> exception is
   * thrown.</em></li>
   * <li><em>If </em>{@code pin}<em> parameter is </em>{@code null}<em>
   * a </em>{@code NullPointerException}<em> exception is thrown.</em></li>
   * </ul>
   * <p>
   * In addition to returning a {@code boolean} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @param pin the byte array containing the PIN value being checked
   * @param offset the starting offset in the {@code pin} array
   * @param length the length of {@code pin}
   * @return {@code true} if the PIN comparison is successful, {@code false}
   * otherwise.
   * @throws ArrayIndexOutOfBoundsException if the check operation would
   * cause access of data outside array bounds.
   * @throws NullPointerException if {@code pin} is {@code null}
   * @throws PINException with the following reason codes:
   * <ul>
   * <li>{@link PINException#ILLEGAL_STATE ILLEGAL_STATE} if the {@code
   * decrementTriesRemaining} method has not been called prior to calling this
   * method.</li>
   * </ul>
   */
  @Override
  boolean check(byte[] pin, short offset, byte length)
      throws PINException, ArrayIndexOutOfBoundsException, NullPointerException;
}
