/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The {@code OwnerPINBuilder} class is factory for Owner {@link PIN} objects.
 *
 * @see OwnerPIN
 * @see OwnerPINx
 * @see OwnerPINxWithPredecrement
 *
 * @since 3.0.5
 */
public class OwnerPINBuilder {

  /**
   * The type identifier for the legacy {@link OwnerPIN}.
   */
  public static final byte OWNER_PIN = (byte)1;

  /**
   * The type identifier for {@link OwnerPINx}. {@code OwnerPINx} -
   * when compared to the legacy {@link OwnerPIN} - allows for changing the
   * tries limit.
   *
   * @see OwnerPINx#setTryLimit
   */
  public static final byte OWNER_PIN_X = (byte)2;

  /**
   * The type identifier for {@link OwnerPINxWithPredecrement}.
   * {@code OwnerPINxWithPredecrement} - when compared to
   * {@link OwnerPINx} - supports the decrementation of the tries counter
   * before any PIN validation attempts.
   *
   * @see OwnerPINxWithPredecrement#decrementTriesRemaining()
   */
  public static final byte OWNER_PIN_X_WITH_PREDECREMENT = (byte)3;

  /*
   * Prevents instantiation.
   */
  private OwnerPINBuilder() {}

  /**
   * Creates a new {@code PIN} instance of the requested type, maximum allowed
   * PIN size and tries limit, and with the {@code validated} flag initially set
   * to
   * {@code false}.
   *
   * @param tryLimit the maximum number of times an incorrect PIN can be
   * presented; {@code tryLimit} must be &ge;1.
   * @param maxPINSize the maximum allowed PIN size; {@code maxPINSize} must be
   *     &ge;1.
   * @param ownerPINType the requested Owner PIN type. Valid codes listed in
   * {@code OWNER_*} constants in this class e.g.
   * {@link #OWNER_PIN_X OWNER_PIN_X}.
   * @return a new {@code PIN} instance.
   * @throws PINException with the following reason codes:
   * <ul>
   * <li>{@link PINException#ILLEGAL_VALUE ILLEGAL_VALUE} if {@code tryLimit} or
   * {@code maxPINSize} is less than 1 or if {@code ownerPINType} is not one of
   * the defined valid codes.</li>
   * </ul>
   * @throws SystemException with the following reason codes:
   * <ul>
   * <li>{@link SystemException#ILLEGAL_USE ILLEGAL_USE} if the PIN type {@code
   * ownerPINType} is not implemented.</li>
   * </ul>
   */
  public static PIN buildOwnerPIN(byte tryLimit, byte maxPINSize,
                                  byte ownerPINType) throws PINException {
    switch (ownerPINType) {
    case OWNER_PIN:
      return new OwnerPIN(tryLimit, maxPINSize);
    case OWNER_PIN_X:
      // TODO: not yet implemented !!!
      return null;
    case OWNER_PIN_X_WITH_PREDECREMENT:
      // TODO: not yet implemented !!!
      return null;
    default:
      PINException.throwIt(PINException.ILLEGAL_VALUE);
    }
    return null;
  }
}
