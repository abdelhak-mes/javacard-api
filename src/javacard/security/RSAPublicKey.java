/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

/**
 * The <code>RSAPublicKey</code> is used to verify signatures on signed data
 * using the RSA algorithm. It may also used by the
 * <code>javacardx.crypto.Cipher</code> class to encrypt/decrypt messages.
 * <p>
 * When both the modulus and exponent of the key are set, the key is initialized
 * and ready for use.
 *
 * @see RSAPrivateKey RSAPrivateKey
 * @see RSAPrivateCrtKey RSAPrivateCrtKey
 * @see KeyBuilder KeyBuilder
 * @see Signature Signature
 * @see javacardx.crypto.Cipher javacardx.crypto.Cipher
 * @see javacardx.crypto.KeyEncryption javacardx.crypto.KeyEncryption
 */
public interface RSAPublicKey extends PublicKey {

  /**
   * Sets the modulus value of the key. The plaintext data format is
   * big-endian and right-aligned (the least significant bit is the least
   * significant bit of last byte). Input modulus data is copied into the
   * internal representation.
   * <p>
   * Note:
   * <ul>
   * <li><em>If the key object implements the
   * </em><code>javacardx.crypto.KeyEncryption</code><em> interface and the
   * </em><code>Cipher</code><em> object specified via
   * </em><code>setKeyCipher()</code><em> is not </em><code>null</code><em>, the
   * modulus value is decrypted using the </em><code>Cipher</code><em>
   * object.</em>
   * </ul>
   *
   * @param buffer
   *            the input buffer
   * @param offset
   *            the offset into the input buffer at which the modulus value
   *            begins
   * @param length
   *            the byte length of the modulus
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                input modulus data length is inconsistent with the
   *                implementation or if input data decryption is required and
   *                fails.
   *                </ul>
   */
  void setModulus(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Sets the public exponent value of the key. The plaintext data format is
   * big-endian and right-aligned (the least significant bit is the least
   * significant bit of last byte). Input exponent data is copied into the
   * internal representation.
   * <p>
   * Notes:
   * <ul>
   * <li><em>All implementations must support exponent values up to 4 bytes in
   * length. Implementations may also support exponent values greater than 4
   * bytes in length.</em> <li><em>If the key object implements the
   * </em><code>javacardx.crypto.KeyEncryption</code><em> interface and the
   * </em><code>Cipher</code><em> object specified via
   * </em><code>setKeyCipher()</code><em> is not </em><code>null</code><em>, the
   * exponent value is decrypted using the </em><code>Cipher</code><em>
   * object.</em>
   * </ul>
   *
   * @param buffer
   *            the input buffer
   * @param offset
   *            the offset into the input buffer at which the exponent value
   *            begins
   * @param length
   *            the byte length of the exponent
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                input exponent data length is inconsistent with the
   *                implementation or if input data decryption is required and
   *                fails or if the implementation does not support the
   *                specified exponent length.
   *                </ul>
   */
  void setExponent(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Returns the modulus value of the key in plain text. The data format is
   * big-endian and right-aligned (the least significant bit is the least
   * significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the input buffer at which the modulus value
   *            starts
   * @return the byte length of the modulus value returned
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the modulus value of the key has not been successfully
   *                initialized since the time the initialized state of the
   *                key was set to false.
   *                </ul>
   * @see Key Key
   */
  short getModulus(byte[] buffer, short offset);

  /**
   * Returns the public exponent value of the key in plain text. The data
   * format is big-endian and right-aligned (the least significant bit is the
   * least significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the output buffer at which the exponent value
   *            begins
   * @return the byte length of the public exponent returned
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the public exponent value of the key has not been
   *                successfully initialized since the time the initialized
   *                state of the key was set to false.
   *                </ul>
   * @see Key Key
   */
  short getExponent(byte[] buffer, short offset);
}
