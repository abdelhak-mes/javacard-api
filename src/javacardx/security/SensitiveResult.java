/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.security;

import javacard.framework.ISOException;
import javacard.framework.JCSystem;

/**
 * The {@code SensitiveResult} class provides methods for asserting results of
 * sensitive functions. Sensitive methods of the Java Card API (refer to the
 * <span class="strong">See Also</span> section below) store their
 * results so that callers of these methods can assert their return values. If
 * such a method returns abnormally with an exception then the <em>stored
 * result</em> is tagged as <em>Unassigned</em> and any subsequent assertion of
 * the result will fail.
 * <br>
 * The <em>stored result</em> is unaffected by context switches; especially,
 * the <em>stored result</em> from an API method called by the method of a
 * Shareable Interface Object is not automatically reset upon switching back to
 * the context of the caller; it is the responsibility of the Shareable
 * Interface Object implementation to reset the <em>stored result</em> if
 * necessary using the {@link #reset() reset} method.
 * <br>
 * Upon entering any of the Applet entry point methods the <em>stored
 * result</em> is tagged as <em>Unassigned</em>. <p> The sample code below
 * illustrates the use of the {@code SensitiveResult} class: <blockquote> <pre>
 * try {
 *     boolean res = signature.verify(...);
 *     if (res) {
 *         SensitiveResult.assertTrue();
 *         // Grant service
 *     } else {
 *         SensitiveResult.assertFalse();
 *         // Deny service
 *     }
 * } finally {
 *     SensitiveResult.reset();
 * }
 * </pre>
 * </blockquote>
 * <p>
 * Note that results from Java Card API methods yielding a {@code byte} result
 * are stored as {@code short} after conversion (with sign-extension).
 *
 * @see javacard.framework.SensitiveArrays#isIntegritySensitive
 * @see javacard.framework.SensitiveArrays#isIntegritySensitiveArraysSupported
 * @see javacard.framework.SensitiveArrays#clearArray
 * @see javacard.framework.AID#equals(Object)
 * @see javacard.framework.AID#equals(byte[], short, byte)
 * @see javacard.framework.AID#partialEquals
 * @see javacard.framework.AID#RIDEquals
 * @see javacard.framework.JCSystem#getAID
 * @see javacard.framework.JCSystem#lookupAID
 * @see javacard.framework.JCSystem#getPreviousContextAID
 * @see javacard.framework.JCSystem#getAppletShareableInterfaceObject
 * @see javacard.framework.OwnerPINxWithPredecrement#decrementTriesRemaining()
 * @see javacard.framework.OwnerPINxWithPredecrement#check
 * @see javacard.framework.OwnerPIN#getTriesRemaining
 * @see javacard.framework.OwnerPIN#check
 * @see javacard.framework.OwnerPIN#isValidated
 * @see javacard.framework.OwnerPIN#getValidatedFlag
 * @see javacard.framework.PIN#getTriesRemaining
 * @see javacard.framework.PIN#check
 * @see javacard.framework.PIN#isValidated
 * @see javacard.framework.Util#arrayCopy
 * @see javacard.framework.Util#arrayCopyNonAtomic
 * @see javacard.framework.Util#arrayFill
 * @see javacard.framework.Util#arrayFillNonAtomic
 * @see javacard.framework.Util#arrayCompare
 * @see javacardx.biometry.BioTemplate#isValidated
 * @see javacardx.biometry.BioTemplate#getTriesRemaining
 * @see javacardx.biometry.BioTemplate#initMatch
 * @see javacardx.biometry.BioTemplate#match
 * @see javacardx.biometry1toN.BioMatcher#isValidated
 * @see javacardx.biometry1toN.BioMatcher#getTriesRemaining
 * @see javacardx.biometry1toN.BioMatcher#initMatch
 * @see javacardx.biometry1toN.BioMatcher#match
 * @see
 *     javacardx.framework.math.BigNumber#compareTo(javacardx.framework.math.BigNumber)
 * @see javacardx.framework.math.BigNumber#compareTo(byte[], short, short, byte)
 * @see javacard.security.Checksum#doFinal
 * @see javacard.security.MessageDigest#doFinal
 * @see javacard.security.MessageDigest.OneShot#doFinal
 * @see javacard.security.RandomData#getAlgorithm
 * @see javacard.security.RandomData#nextBytes
 * @see javacard.security.RandomData.OneShot#getAlgorithm
 * @see javacard.security.RandomData.OneShot#nextBytes
 * @see javacard.security.Signature#sign
 * @see javacard.security.Signature.OneShot#sign
 * @see javacard.security.Signature#signPreComputedHash
 * @see javacard.security.Signature.OneShot#signPreComputedHash
 * @see javacard.security.Signature#verify
 * @see javacard.security.Signature.OneShot#verify
 * @see javacard.security.Signature#verifyPreComputedHash
 * @see javacard.security.Signature.OneShot#verifyPreComputedHash
 * @see javacardx.crypto.Cipher#doFinal
 * @see javacardx.crypto.Cipher.OneShot#doFinal
 * @see javacardx.crypto.Cipher#update
 * @see javacardx.crypto.AEADCipher#doFinal
 * @see javacardx.crypto.AEADCipher#update
 * @see javacardx.crypto.AEADCipher#retrieveTag
 * @see javacardx.crypto.AEADCipher#verifyTag
 * @since 3.0.5
 */
public final class SensitiveResult {

  /**
   * Asserts the stored result to be an object reference equal to the provided
   * object reference. This method throws an exception if and only if the stored
   * result reference {@code res} and the provided object reference {@code obj}
   * do not refer to the same object or are not both {@code null}; in other
   * words {@code (res == obj)} evaluates to {@code false}.
   *
   * @param obj The object reference to compare with the stored result.
   *
   * @throws SecurityException if the provided object reference is not equal to
   * that of the stored result.
   */
  public static void assertEquals(Object obj) throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code boolean} value equal to
   * {@code true}.
   *
   * @throws SecurityException if the stored result is not set to {@code true}.
   */
  public static void assertTrue() throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code boolean} value equal to
   * {@code false}.
   *
   * @throws SecurityException if the stored result is not set to {@code false}.
   */
  public static void assertFalse() throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code short} value strictly negative. A
   * call to this method is semantically equivalent to a call to
   * {@link #assertLessThan(short)} with parameter {@code 0}.
   *
   * @throws SecurityException if the stored result is not negative.
   */
  public static void assertNegative() throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code short} value strictly positive. A
   * call to this method is semantically equivalent to a call to
   * {@link #assertGreaterThan(short)} with parameter {@code 0}.
   *
   * @throws SecurityException if the stored result is not positive.
   */
  public static void assertPositive() throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code short} value equal to zero. A call
   * to this method is semantically equivalent to a call to
   * {@link #assertEquals(short)} with parameter {@code 0}.
   *
   * @throws SecurityException if the stored result is not zero.
   */
  public static void assertZero() throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code short} value equal to the provided
   * {@code short} value.
   *
   * @param val The {@code short} value to compare with the stored result.
   *
   * @throws SecurityException if the provided value is not equal to that of the
   * stored result.
   */
  public static void assertEquals(short val) throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code short} value strictly greater than
   * the provided {@code short} value.
   *
   * @param val The {@code short} value to compare with the stored result.
   *
   * @throws SecurityException if the provided value is not greater than that of
   * the stored result.
   */
  public static void assertGreaterThan(short val) throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Asserts the stored result to be a {@code short} value strictly less than
   * the provided {@code short} value.
   *
   * @param val The {@code short} value to compare with the stored result.
   *
   * @throws SecurityException if the provided value is not less than that of
   * the stored result.
   */
  public static void assertLessThan(short val) throws SecurityException {
    // TODO: Not yet implemented!!!
  }

  /**
   * Resets the stored result. The stored result is tagged as
   * <em>Unassigned</em> and any subsequent assertion of the result will fail.
   */
  public static void reset() {
    // TODO: Not yet implemented!!!
  }
} // end class
