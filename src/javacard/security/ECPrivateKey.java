/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

/**
 * The <code>ECPrivateKey</code> interface is used to generate signatures on
 * data using the ECDSA (Elliptic Curve Digital Signature Algorithm) and to
 * generate shared secrets using the ECDH (Elliptic Curve Diffie-Hellman)
 * algorithm. An implementation of <code>ECPrivateKey</code> interface must
 * also implement the <code>ECKey</code> interface methods.
 *
 * <p>
 * When all components of the key (S, A, B, G, R, Field) are set, the key is
 * initialized and ready for use. In addition, the <code>KeyAgreement</code>
 * algorithm type <code>ALG_EC_SVDP_DHC</code> requires that the cofactor, K,
 * be initialized.
 *
 * <p>
 * The notation used to describe parameters specific to the EC algorithm is
 * based on the naming conventions established in [IEEE P1363].
 *
 * @version 1.0
 * @see ECPublicKey ECPublicKey
 * @see KeyBuilder KeyBuilder
 * @see Signature Signature
 * @see javacardx.crypto.KeyEncryption javacardx.crypto.KeyEncryption
 * @see KeyAgreement KeyAgreement
 */
public interface ECPrivateKey extends PrivateKey, ECKey {

  /**
   * Sets the value of the secret key.
   *
   * The plain text data format is big-endian and right-aligned (the least
   * significant bit is the least significant bit of last byte). Input
   * parameter data is copied into the internal representation.
   *
   * <p>
   * Note:
   * <ul>
   * <li><em>If the key object implements the
   * </em><code>javacardx.crypto.KeyEncryption</code><em> interface and the
   * </em><code>Cipher</code><em> object specified via
   * </em><code>setKeyCipher()</code><em> is not </em><code>null</code><em>, the
   * key value is decrypted using the </em><code>Cipher</code><em> object.</em>
   * </ul>
   *
   * @param buffer
   *            the input buffer
   * @param offset
   *            the offset into the input buffer at which the secret value is
   *            to begin
   * @param length
   *            the byte length of the secret value
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                length parameter is 0 or invalid or the
   *                input key data is inconsistent with the key length or if
   *                input data decryption is required and fails.
   *                </ul>
   */
  public void setS(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Returns the value of the secret key in plaintext form.
   *
   * The data format is big-endian and right-aligned (the least significant
   * bit is the least significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the input buffer at which the secret value is
   *            to begin
   * @return the byte length of the secret value
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the value of the secret key has not been successfully
   *                initialized since the time the initialized state of the
   *                key was set to false.
   *                </ul>
   * @see Key Key
   */
  public short getS(byte[] buffer, short offset) throws CryptoException;
}
