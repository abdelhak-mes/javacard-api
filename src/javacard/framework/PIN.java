/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * This interface represents a PIN. An implementation must maintain these
 * internal values:
 * <ul>
 * <li>PIN value.
 * <li>Try limit - the maximum number of times an incorrect PIN can be
 * presented before the PIN is blocked. When the PIN is blocked, it cannot be
 * validated even on valid PIN presentation.
 * <li>Max PIN size - the maximum length of PIN allowed.
 * <li>Try counter - the remaining number of times an incorrect PIN
 * presentation is permitted before the {@code PIN} becomes blocked.
 * <li>Validated flag - true if a valid PIN has been presented. This flag is
 * reset on every card reset.
 * </ul>
 * This interface does not make any assumptions about where the data for the PIN
 * value comparison is stored.
 * <p>
 * An owner implementation of this interface must provide a way to
 * initialize/update the PIN value. The owner implementation of the interface
 * must protect against attacks based on program flow prediction. In addition,
 * even if a transaction is in progress, update of internal state such as the
 * try counter, the validated flag, and the blocking state, shall not
 * participate in the transaction during PIN presentation.
 * <br>
 * This interface does not make any assumptions about how the blocking state is
 * internally represented: the blocking state is concomitant to
 * the try counter value being equal to zero.
 * <p>
 *
 * A typical card global PIN usage will combine an instance of
 * the {@code OwnerPIN} class or of an {@code OwnerPINx}-implementing class
 * and a Proxy PIN interface which extends both
 * the {@code PIN} and the {@code Shareable} interfaces and
 * re-declares the methods of the PIN interface. The {@code OwnerPIN} or {@code
 * OwnerPINx} instance would be manipulated only by the owner who has update
 * privilege. All others would access the global PIN functionality via the proxy
 * PIN interface.
 *
 * @see OwnerPIN OwnerPIN
 * @see OwnerPINx OwnerPINx
 * @see OwnerPINxWithPredecrement OwnerPINxWithPredecrement
 * @see OwnerPINBuilder OwnerPINBuilder
 * @see Shareable Shareable
 */
public interface PIN {

  /**
   * Returns the number of times remaining that an incorrect PIN can be
   * presented before the {@code PIN} is blocked.
   * <p>
   * In addition to returning a {@code byte} result, platform-implementations of
   * this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @return the number of times remaining
   */
  byte getTriesRemaining();

  /**
   * Compares {@code pin} against the PIN value. If the
   * {@code PIN} is not already blocked then:
   * <ul>
   * <li>if the PIN value matches, it sets the validated flag and resets
   * the try counter to its maximum;</li>
   * <li>otherwise, it resets the validated flag, decrements the
   * try counter - if not assumed to having been pre-decremented - and, if the
   * counter has reached zero, blocks the
   * {@code PIN}.</li>
   * </ul>
   * <p>
   * Even if a transaction is in progress, update of
   * internal state - the try counter, the validated flag, and the blocking
   * state, shall not participate in the transaction.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em>{@code NullPointerException}<em> or </em>{@code
   * ArrayIndexOutOfBoundsException}<em> is thrown, the validated flag must be
   * set to false, the try counter must be decremented and, the {@code PIN}
   * blocked if the counter reaches zero.</em> <li><em>If </em>{@code
   * offset}<em> or </em>{@code length}<em> parameter is negative an </em>{@code
   * ArrayIndexOutOfBoundsException}<em> exception is thrown.</em> <li><em>If
   * </em>{@code offset+length}<em> is greater than </em>{@code pin.length}<em>,
   * the length of the </em>{@code pin}<em> array, an </em>{@code
   * ArrayIndexOutOfBoundsException}<em> exception is thrown.</em> <li><em>If
   * </em>{@code pin}<em> parameter is </em>{@code null}<em> a </em>{@code
   * NullPointerException}<em> exception is thrown.</em>
   * </ul>
   * <p>
   * In addition to returning a {@code boolean} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @param pin
   *            the byte array containing the PIN value being checked
   * @param offset
   *            the starting offset in the {@code pin} array
   * @param length
   *            the length of {@code pin}
   * @return {@code true} if the PIN value matches; {@code false}
   *         otherwise
   * @exception java.lang.ArrayIndexOutOfBoundsException
   *                if the check operation would cause access of data outside
   *                array bounds.
   * @exception java.lang.NullPointerException
   *                if {@code pin} is {@code null}
   */
  public boolean check(byte[] pin, short offset, byte length)
      throws ArrayIndexOutOfBoundsException, NullPointerException;

  /**
   * Returns the validated flag; {@code true} if a valid PIN value has been
   * presented since the last card reset and the validated flag was not reset
   * since then by a call to {@link #reset reset} or by any owner PIN
   * administrative method operations (see {@code OwnerPIN} and {@code
   * OwnerPINx}).
   * <p>
   * In addition to returning a {@code boolean} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @return {@code true} if validated; {@code false} otherwise
   */
  boolean isValidated();

  /**
   * If the validated flag is set, this method resets the validated flag.
   * If the validated flag is not set, this method does nothing.
   */
  void reset();
}
