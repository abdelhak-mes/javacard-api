/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.crypto;

import javacard.security.CryptoException;
import javacard.security.Key;

/**
 * The AEADCipher class is the abstract base class for Authenticated Encryption
 * with Associated Data (AEAD) ciphers.
 * <p>
 * Examples of AEAD algorithms are the <a
 * href="http://csrc.nist.gov/publications/nistpubs/800-38D/SP-800-38D.pdf">GCM</a>
 * and <a href="http://tools.ietf.org/html/rfc3610">CCM</a> modes of operation
 * for AES.
 * </p>
 *
 * <p>
 * AEAD ciphers can be created by the {@link Cipher#getInstance(byte, boolean)
 * Cipher.getInstance} method using the
 * {@link AEADCipher#ALG_AES_GCM ALG_AES_GCM} and {@link AEADCipher#ALG_AES_CCM
 * ALG_AES_CCM} algorithm constants. The returned <code>Cipher</code> instance
 * should then be cast to <code>AEADCipher</code>.
 * </p>
 * @since 3.0.5
 */
public abstract class AEADCipher extends Cipher {

  /**
   * Cipher algorithm CIPHER_AES_GCM choice for the cipherAlgorithm parameter of
   * the {@link #getInstance(byte, byte, boolean)} method; the paddingAlgorithm
   * must be set to <code>PAD_NULL</code>. The cipher algorithm provides a
   * cipher using AES Galois/Counter Mode as specified in <a
   * href="http://csrc.nist.gov/publications/nistpubs/800-38D/SP-800-38D.pdf">NIST
   * SP 800-38D, November 2007</a>. To use the AEAD properties of the instance
   * returned  it is required to  cast the returned instance to {@link
   * AEADCipher}.
   * @since 3.0.5
   */
  public static final byte CIPHER_AES_GCM = (byte)0xF1;

  /**
   * Cipher algorithm CIPHER_AES_CCM choice for the cipherAlgorithm parameter of
   * the {@link #getInstance(byte, byte, boolean)} method; the paddingAlgorithm
   * must be set to <code>PAD_NULL</code>. The cipher algorithm provides a
   * cipher using AES in Counter with CBC-MAC mode as specified in <a
   * href="http://tools.ietf.org/html/rfc3610">RFC 3610</a>. To use the AEAD
   * properties of the instance returned  it is required to  cast the returned
   * instance to {@link AEADCipher}.
   * @since 3.0.5
   */
  public static final byte CIPHER_AES_CCM = (byte)0xF2;

  /**
   * Cipher algorithm <code>ALG_AES_GCM</code> provides a cipher using AES in
   * Galois/Counter Mode as specified in <a
   * href="http://csrc.nist.gov/publications/nistpubs/800-38D/SP-800-38D.pdf">NIST
   * SP 800-38D, November 2007</a>. To use the AEAD properties of the instance
   * returned by {@link #getInstance(byte, boolean)}, it is required to cast the
   * instance to {@link AEADCipher}
   * @since 3.0.5
   */
  public static final byte ALG_AES_GCM = (byte)0xF3;

  /**
   * Cipher algorithm <code>ALG_AES_CCM</code> provides a cipher using AES in
   * Counter with CBC-MAC mode as specified in <a
   * href="http://tools.ietf.org/html/rfc3610">RFC 3610</a>. To use the AEAD
   * properties of the instance returned by {@link #getInstance(byte, boolean)},
   * it is required to cast the instance to {@link AEADCipher}
   * @since 3.0.5
   */
  public static final byte ALG_AES_CCM = (byte)0xF4;

  /**
   * Avoid instantiation, compatible with protected constructor of Cipher.
   */
  protected AEADCipher() {}

  /**
   * {@inheritDoc}
   * <ul>
   * <li><em>AEADCipher in GCM mode will use 0 for initial vector(IV) if this
   * method is used.</em>
   * </ul>
   *
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if
   *                <code>theMode</code> option is an undefined value or if the
   *                <code>Key</code> is inconsistent with the
   *                <code>Cipher</code> implementation.</li>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                <code>theKey</code> instance is uninitialized.</li>
   *         <li><code>CryptoException.INVALID_INIT</code> if this method is
   * called for an offline mode of encryption</li>
   *         </ul>
   * @since 3.0.5
   */
  public abstract void init(Key theKey, byte theMode) throws CryptoException;

  /**
   * {@inheritDoc}
   *
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if
   *                <code>theMode</code> option is an undefined value or if
   *                a byte array parameter option is not supported by the
   *                algorithm or if the <code>bLen</code> is an incorrect
   *                byte length for the algorithm specific data or if the
   *                <code>Key</code> is inconsistent with the
   *                <code>Cipher</code> implementation.</li>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                <code>theKey</code> instance is uninitialized.</li>
   *         <li><code>CryptoException.INVALID_INIT</code> if this method is
   * called for an offline mode of encryption</li>
   *         </ul>
   * @since 3.0.5
   */
  public abstract void init(Key theKey, byte theMode, byte[] bArray, short bOff,
                            short bLen) throws CryptoException;

  /**
   * Initializes this <code>Cipher</code> instance to encrypt or decrypt a with
   * the given key, nonce, AAD size and message size. <p> This method should
   * only be called for offline cipher mode encryption such as
   * <code>Cipher#ALG_AES_CCM</code>. In offline cipher mode encryption the
   * length of the authentication data, message size and authentication tag must
   * be known in advance. </p>
   *
   * @param theKey
   *        the key object to use for encrypting or decrypting
   * @param theMode
   *        one of <code>MODE_DECRYPT</code> or
   *        <code>MODE_ENCRYPT</code>
   * @param adataLen
   *        the length of the authenticated data
   *        as presented in the {@link #updateAAD(byte[], short, short)
   * updateAAD} method
   * @param nonceBuf
   *        a buffer holding the nonce
   * @param nonceOff
   *        the offset in the buffer of the nonce
   * @param nonceLen
   *        the length in the buffer of the nonce
   * @param messageLen
   *        the length of the message
   *        as presented in the {@link #update(byte[], short, short, byte[],
   * short) update} and {@link #doFinal(byte[], short, short, byte[], short)
   * doFinal} methods
   * @param tagSize
   *        the size in in bytes of the authentication tag
   * @throws CryptoException with the following reason codes:
   *         <ul>
   *         <li>{@link CryptoException#ILLEGAL_VALUE ILLEGAL_VALUE} if any of
   * the values are outside the accepted range</li> <li>{@link
   * CryptoException#UNINITIALIZED_KEY} if <code>theKey</code> instance is
   * uninitialized.</li> <li>{@link CryptoException#INVALID_INIT} if this method
   * is called for an online mode of encryption</li>
   *         </ul>
   * @since 3.0.5
   */
  public abstract void init(final Key theKey, final byte theMode,
                            final byte[] nonceBuf, final short nonceOff,
                            final short nonceLen, final short adataLen,
                            final short messageLen, final short tagSize)
      throws CryptoException;

  /**
   * Continues a multi-part update of the Additional Associated Data (AAD) that
   * will be verified by the authentication tag. The data is not included with
   * the ciphertext by this method.
   *
   * @param aadBuf
   *        the buffer containing the AAD data
   * @param aadOff
   *        the offset of the AAD data in the buffer
   * @param aadLen
   *        the length in bytes of the AAD data in the buffer
   * @throws CryptoException
   *         with the following reason codes:
   *         <ul>
   *         <li>{@link CryptoException#ILLEGAL_USE ILLEGAL_USE} if updating the
   * AAD value is conflicting with the state of this cipher</li> <li>{@link
   * CryptoException#ILLEGAL_VALUE ILLEGAL_VALUE} for CCM if the AAD size is
   * different from the AAD size given in the initial block used as IV</li>
   *         </ul>
   * @since 3.0.5
   */
  public abstract void updateAAD(byte[] aadBuf, short aadOff, short aadLen)
      throws CryptoException;

  /**
   * {@inheritDoc}
   *
   * @throws CryptoException
   *         with the following reason codes:
   *         <ul>
   *         <li>{@link CryptoException#INVALID_INIT INVALID_INIT} if this
   * <code>Cipher</code> object is not initialized.</li> <li>{@link
   * CryptoException#UNINITIALIZED_KEY} if key not initialized.</li> <li>{@link
   * CryptoException#ILLEGAL_USE ILLEGAL_USE} <ul> <li>for CCM if AAD is not
   * provided while it is indicated in the initial block used as IV</li> <li>for
   * CCM if the payload exceeds the payload size given in the initial block used
   * as IV</li>
   *         </ul>
   *         </li>
   *         </ul>
   * @since 3.0.5
   */
  public abstract short update(byte[] inBuff, short inOffset, short inLength,
                               byte[] outBuff, short outOffset)
      throws CryptoException;

  /**
   * {@inheritDoc}
   *
   * @throws CryptoException
   *         with the following reason codes:
   *         <ul>
   *         <li>{@link CryptoException#INVALID_INIT INVALID_INIT} if this
   * <code>Cipher</code> object is not initialized.</li> <li>{@link
   * CryptoException#UNINITIALIZED_KEY UNINITIALIZED_KEY} if key not
   * initialized.</li> <li>{@link CryptoException#ILLEGAL_USE ILLEGAL_USE} <ul>
   *         <li>for CCM if all Additional Authenticated Data (AAD) was not
   * provided</li> <li>for CCM if the total message size provided is not
   * identical to the <code>messageLen</code> parameter given in the {@link
   * #init(Key, byte, byte[], short, short, short, short, short) init}
   * method</li>
   *         </ul>
   *         </li>
   *         </ul>
   * @since 3.0.5
   */
  public abstract short doFinal(byte[] inBuff, short inOffset, short inLength,
                                byte[] outBuff, short outOffset)
      throws CryptoException;

  /**
   * Retrieves <code>tagLen</code> bytes from the calculated authentication tag.
   * Depending on the algorithm, only certain tag lengths may be supported. <p>
   * Note:
   * <ul>
   * <li><i>This method may only be called for {@link Cipher#MODE_ENCRYPT
   * MODE_ENCRYPT} after
   * {@link #doFinal(byte[], short, short, byte[], short)
   * doFinal} has been called.</i></li>
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param tagBuf
   *        the buffer that will contain the authentication tag
   * @param tagOff
   *        the offset of the authentication tag in the buffer
   * @param tagLen
   *        the length in bytes of the authentication tag in the buffer
   * @return the tag length, as given by tagLen (for convenience)
   * @throws CryptoException
   *         with the following reason codes
   *         <ul>
   *         <li>{@link CryptoException#ILLEGAL_USE ILLEGAL_USE} if
   *         {@link #doFinal(byte[], short, short, byte[], short)
   *         doFinal} has not been called</li>
   *         <li>{@link CryptoException#ILLEGAL_VALUE ILLEGAL_VALUE} if the tag
   * length is not supported
   *         </ul>
   *
   * @since 3.0.5
   */
  public abstract short retrieveTag(byte[] tagBuf, short tagOff, short tagLen)
      throws CryptoException;

  /**
   * Verifies the authentication tag using the number of bits set in
   * <code>requiredTagLen</code> bits. Depending on the algorithm, only certain
   * tag lengths may be supported. For all algorithms the tag length must be a
   * multiple of 8 bits. <p> Note: <ul> <li><i>This method may only be called
   * for {@link Cipher#MODE_DECRYPT MODE_DECRYPT} after
   * {@link #doFinal(byte[], short, short, byte[], short) doFinal} has been
   * called.</i></li>
   * </ul>
   * <p>
   * In addition to returning a {@code boolean} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param receivedTagBuf
   *        the buffer that will contain the received authentication tag
   * @param receivedTagOff
   *        the offset of the received authentication tag in the buffer
   * @param receivedTagLen
   *        the length in bytes of the received authentication tag in the buffer
   * @param requiredTagLen
   *        the required length in bytes of the received authentication tag,
   *        usually a constant value
   * @return result of verify tag
   * @throws CryptoException
   *         with the following reason codes:
   *         <ul>
   *         <li>{@link CryptoException#ILLEGAL_USE ILLEGAL_USE} if
   *         {@link #doFinal(byte[], short, short, byte[], short) doFinal} has
   * not been called</li> <li>{@link CryptoException#ILLEGAL_VALUE
   * ILLEGAL_VALUE} if the tag length is not supported
   *         </ul>
   * @since 3.0.5
   */
  public abstract boolean verifyTag(byte[] receivedTagBuf, short receivedTagOff,
                                    short receivedTagLen, short requiredTagLen)
      throws CryptoException;
}
