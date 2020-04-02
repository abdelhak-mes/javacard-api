/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

/**
 * <code>KoreanSEEDKey</code> contains an 16-byte key for Korean Seed
 * Algorithm operations.
 * <p>
 * When the key data is set, the key is initialized and ready for use.
 * <p>
 *
 * @see KeyBuilder KeyBuilder
 * @see Signature Signature
 * @see javacardx.crypto.Cipher javacardx.crypto.Cipher
 * @see javacardx.crypto.KeyEncryption javacardx.crypto.KeyEncryption
 * @since 2.2.2
 */
public interface KoreanSEEDKey extends SecretKey {

  /**
   * Sets the <code>Key</code> data. The plain text length of input key data
   * is The data format is big-endian and right-aligned (the least significant
   * bit is the least significant bit of last byte). Input key data is copied
   * into the internal representation.
   * <p>
   * Note:
   * <ul>
   * <li><em>If the key object implements the
   * </em><code>javacardx.crypto.KeyEncryption</code><em> interface and the
   * </em><code>Cipher</code><em> object specified via
   * </em><code>setKeyCipher()</code><em> is not </em><code>null</code><em>,
   * </em><code>keyData</code><em> is decrypted using the
   * </em><code>Cipher</code><em> object.</em>
   * </ul>
   *
   * @param keyData
   *            byte array containing key initialization data
   * @param kOff
   *            offset within keyData to start
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if input
   *                data decryption is required and fails.
   *                </ul>
   * @exception ArrayIndexOutOfBoundsException
   *                if <code>kOff</code> is negative or the
   *                <code>keyData</code> array is too short
   * @exception NullPointerException
   *                if the <code>keyData</code> parameter is
   *                <code>null</code>
   */
  void setKey(byte[] keyData, short kOff) throws CryptoException,
                                                 NullPointerException,
                                                 ArrayIndexOutOfBoundsException;

  /**
   * Returns the <code>Key</code> data in plain text. The length of output
   * key data is 16 bytes for Korean Seed Algorithm. The data format is
   * big-endian and right-aligned (the least significant bit is the least
   * significant bit of last byte).
   *
   * @param keyData
   *            byte array to return key data
   * @param kOff
   *            offset within <code>keyData</code> to start
   * @return the byte length of the key data returned
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the key data has not been successfully initialized since
   *                the time the initialized state of the key was set to
   *                false.
   *                </ul>
   * @see Key Key
   */
  byte getKey(byte[] keyData, short kOff);
}
