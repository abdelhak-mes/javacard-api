/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.crypto;

import static javacardx.crypto.AEADCipher.ALG_AES_CCM;
import static javacardx.crypto.AEADCipher.ALG_AES_GCM;

import javacard.framework.JCSystem;
import javacard.framework.SystemException;
import javacard.security.CryptoException;
import javacard.security.Key;

/**
 * The <code>Cipher</code> class is the abstract base class for Cipher
 * algorithms. Implementations of Cipher algorithms must extend this class and
 * implement all the abstract methods.
 * <p>
 * The term "pad" is used in the public key cipher algorithms below to refer to
 * all the operations specified in the referenced scheme to transform the
 * message block into the cipher block size.
 * <p>
 * The asymmetric key algorithms encrypt using either a public key (to cipher)
 * or a private key (to sign). In addition they decrypt using the either a
 * private key (to decipher) or a public key (to verify). However, usage of some
 * padding schemes, such as PKCS#1-OAEP, is intended for encryption or
 * decryption operations only, and therefore should be limited to their intended
 * purpose. <p> A tear or card reset event resets an initialized
 * <code>Cipher</code> object to the state it was in when previously initialized
 * via a call to <code>init()</code>. For algorithms which support keys with
 * transient key data sets, such as DES, triple DES and AES, and Korean SEED the
 * <code>Cipher</code> object key becomes uninitialized on clear events
 * associated with the <code>Key</code> object used to initialize the
 * <code>Cipher</code> object.
 * <p>
 * Even if a transaction is in progress, update of intermediate result state in
 * the implementation instance shall not participate in the transaction.<br>
 * <p>
 * Note:
 * <ul>
 * <li><em>On a tear or card reset event, the AES, DES, triple DES and Korean
 * SEED algorithms in CBC mode reset the initial vector(IV) to 0. The initial
 * vector(IV) can be re-initialized using the
 * </em><code>init(Key, byte, byte[], short, short)</code><em> method.</em>
 * </ul>
 */
public abstract class Cipher {

  /**
   * Cipher algorithm <code>ALG_DES_CBC_NOPAD</code> provides a cipher using
   * DES in CBC mode or triple DES in outer CBC mode, and does not pad input
   * data. If the input data is not (8-byte) block aligned it throws
   * <code>CryptoException</code> with the reason code
   * <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_CBC</code>,
   * <code>PAD_NOPAD</code> constants respectively.
   */
  public static final byte ALG_DES_CBC_NOPAD = 1;

  /**
   * Cipher algorithm <code>ALG_DES_CBC_ISO9797_M1</code> provides a cipher
   * using DES in CBC mode or triple DES in outer CBC mode, and pads input
   * data according to the ISO 9797 method 1 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_CBC</code>,
   * <code>PAD_ISO9797_M1</code> constants respectively.
   */
  public static final byte ALG_DES_CBC_ISO9797_M1 = 2;

  /**
   * Cipher algorithm <code>ALG_DES_CBC_ISO9797_M2</code> provides a cipher
   * using DES in CBC mode or triple DES in outer CBC mode, and pads input
   * data according to the ISO 9797 method 2 (ISO 7816-4, EMV'96) scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_CBC</code>,
   * <code>PAD_ISO9797_M2</code> constants respectively.
   */
  public static final byte ALG_DES_CBC_ISO9797_M2 = 3;

  /**
   * Cipher algorithm <code>ALG_DES_CBC_PKCS5</code> provides a cipher
   * using DES in CBC mode or triple DES in outer CBC mode, and pads input
   * data according to the PKCS#5 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_CBC</code>,
   * <code>PAD_PKCS5</code> constants respectively.
   */
  public static final byte ALG_DES_CBC_PKCS5 = 4;

  /**
   * Cipher algorithm <code>ALG_DES_ECB_NOPAD</code> provides a cipher using
   * DES in ECB mode, and does not pad input data. If the input data is not
   * (8-byte) block aligned it throws <code>CryptoException</code> with the
   * reason code <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_ECB</code>,
   * <code>PAD_NOPAD</code> constants respectively.
   */
  public static final byte ALG_DES_ECB_NOPAD = 5;

  /**
   * Cipher algorithm <code>ALG_DES_ECB_ISO9797_M1</code> provides a cipher
   * using DES in ECB mode, and pads input data according to the ISO 9797
   * method 1 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_ECB</code>,
   * <code>PAD_ISO9797_M1</code> constants respectively.
   */
  public static final byte ALG_DES_ECB_ISO9797_M1 = 6;

  /**
   * Cipher algorithm <code>ALG_DES_ECB_ISO9797_M2</code> provides a cipher
   * using DES in ECB mode, and pads input data according to the ISO 9797
   * method 2 (ISO 7816-4, EMV'96) scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_ECB</code>,
   * <code>PAD_ISO9797_M2</code> constants respectively.
   */
  public static final byte ALG_DES_ECB_ISO9797_M2 = 7;

  /**
   * Cipher algorithm <code>ALG_DES_ECB_PKCS5</code> provides a cipher using
   * DES in ECB mode, and pads input data according to the PKCS#5 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_DES_ECB</code>,
   * <code>PAD_PKCS5</code> constants respectively.
   */
  public static final byte ALG_DES_ECB_PKCS5 = 8;

  /**
   * This Cipher algorithm <code>ALG_RSA_ISO14888</code> should not be used.
   * The ISO 14888 algorithms are intended for signatures.
   *
   * @deprecated
   */
  public static final byte ALG_RSA_ISO14888 = 9;

  /**
   * Cipher algorithm <code>ALG_RSA_PKCS1</code> provides a cipher using
   * RSA, and pads input data according to the PKCS#1 (v1.5) scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_RSA</code>,
   * <code>PAD_PKCS1</code> constants respectively.
   * <p>
   * Note:
   * <ul>
   * <li><em>This algorithm is only suitable for messages of limited length.
   * The total number of input bytes processed during encryption may not be more
   * than k-11, where k is the RSA key's modulus size in bytes.</em> <li><em>
   * The encryption block(EB) during encryption with a Public key is built as
   * follows:<br> &nbsp; EB = 00 || 02 || PS || 00 || M<br> &nbsp; &nbsp; &nbsp;
   * :: M (input bytes) is the plaintext message<br> &nbsp; &nbsp; &nbsp; :: PS
   * is an octet string of length k-3-||M|| of pseudo random nonzero octets. The
   * length of PS must be at least 8 octets.<br> &nbsp; &nbsp; &nbsp; :: k is
   * the RSA modulus size.</em><br> <li><em> The encryption block(EB) during
   * encryption with a Private key (used to compute signatures when the message
   * digest is computed off-card) is built as follows:<br> &nbsp; EB = 00 || 01
   * || PS || 00 || D<br> &nbsp; &nbsp; &nbsp; :: D (input bytes) is the DER
   * encoding of the hash computed elsewhere with an algorithm ID prepended if
   * appropriate<br> &nbsp; &nbsp; &nbsp; :: PS is an octet string of length
   * k-3-||D|| with value FF. The length of PS must be at least 8 octets.<br>
   * &nbsp; &nbsp; &nbsp; :: k is the RSA modulus size.</em><br>
   * </ul>
   */
  public static final byte ALG_RSA_PKCS1 = 10;

  /**
   * This Cipher algorithm <code>ALG_RSA_ISO9796</code> should not be used.
   * The ISO 9796-1 algorithm was withdrawn by ISO in July 2000.
   *
   * @deprecated
   */
  public static final byte ALG_RSA_ISO9796 = 11;

  /**
   * Cipher algorithm <code>ALG_RSA_NOPAD</code> provides a cipher using RSA
   * and does not pad input data. If the input data is bounded by incorrect
   * padding bytes while using RSAPrivateCrtKey, incorrect output may result.
   * If the input data is not block aligned or greater than or equal to the
   * modulus, it throws
   * <code>CryptoException</code> with the reason code
   * <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_RSA</code>,
   * <code>PAD_NOPAD</code> constants respectively.
   */
  public static final byte ALG_RSA_NOPAD = 12;

  /**
   * Cipher algorithm <code>ALG_AES_BLOCK_128_CBC_NOPAD</code> provides a
   * cipher using AES with block size 128 in CBC mode and does not pad input
   * data. If the input data is not block aligned it throws
   * <code>CryptoException</code> with the reason code
   * <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_CBC</code>,
   * <code>PAD_NOPAD</code> constants respectively.
   */
  public static final byte ALG_AES_BLOCK_128_CBC_NOPAD = 13;

  /**
   * Cipher algorithm <code>ALG_AES_BLOCK_128_ECB_NOPAD</code> provides a
   * cipher using AES with block size 128 in ECB mode and does not pad input
   * data. If the input data is not block aligned it throws
   * <code>CryptoException</code> with the reason code
   * <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_ECB</code>,
   * <code>PAD_NOPAD</code> constants respectively.
   */
  public static final byte ALG_AES_BLOCK_128_ECB_NOPAD = 14;

  /**
   * Cipher algorithm <code>ALG_RSA_PKCS1_OAEP</code> provides a cipher
   * using RSA, and pads input data according to the PKCS#1-OAEP scheme (IEEE
   * 1363-2000).
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_RSA</code>,
   * <code>PAD_PKCS1_OAEP</code> constants respectively.
   */
  public static final byte ALG_RSA_PKCS1_OAEP = 15;

  /**
   * Cipher algorithm <code>ALG_KOREAN_SEED_ECB_NOPAD</code> provides a
   * cipher using the Korean SEED algorithm specified in the Korean SEED
   * Algorithm specification provided by KISA, Korea Information Security
   * Agency in ECB mode and does not pad input data. If the input data is not
   * block aligned it throws <code>CryptoException</code> with the reason
   * code <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_KOREAN_SEED_ECB</code>,
   * <code>PAD_NOPAD</code> constants respectively.
   */
  public static final byte ALG_KOREAN_SEED_ECB_NOPAD = 16;

  /**
   * Cipher algorithm <code>ALG_KOREAN_SEED_CBC_NOPAD</code> provides a
   * cipher using the Korean SEED algorithm specified in the Korean SEED
   * Algorithm specification provided by KISA, Korea Information Security
   * Agency in CBC mode and does not pad input data. If the input data is not
   * block aligned it throws <code>CryptoException</code> with the reason
   * code <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_KOREAN_SEED_CBC</code>,
   * <code>PAD_NOPAD</code> constants respectively.
   */
  public static final byte ALG_KOREAN_SEED_CBC_NOPAD = 17;

  /**
   * This Cipher algorithm <code>ALG_AES_BLOCK_192_CBC_NOPAD</code>
   * should not be used. AES algorithms as defined by NIST in the FIPS PUB 197
   * standard only support a block size of 128 bits.
   *
   * @deprecated
   */
  public static final byte ALG_AES_BLOCK_192_CBC_NOPAD = 18;

  /**
   * This Cipher algorithm <code>ALG_AES_BLOCK_192_ECB_NOPAD</code>
   * should not be used. AES algorithms as defined by NIST in the FIPS PUB 197
   * standard only support a block size of 128 bits.
   *
   * @deprecated
   */
  public static final byte ALG_AES_BLOCK_192_ECB_NOPAD = 19;

  /**
   * This Cipher algorithm <code>ALG_AES_BLOCK_256_CBC_NOPAD</code>
   * should not be used. AES algorithms as defined by NIST in the FIPS PUB 197
   * standard only support a block size of 128 bits.
   *
   * @deprecated
   */
  public static final byte ALG_AES_BLOCK_256_CBC_NOPAD = 20;

  /**
   * This Cipher algorithm <code>ALG_AES_BLOCK_256_ECB_NOPAD</code>
   * should not be used. AES algorithms as defined by NIST in the FIPS PUB 197
   * standard only support a block size of 128 bits.
   *
   * @deprecated
   */
  public static final byte ALG_AES_BLOCK_256_ECB_NOPAD = 21;

  /**
   * Cipher algorithm <code>ALG_AES_CBC_ISO9797_M1</code> provides a cipher
   * using AES with block size 128 in CBC mode, and pads input data according to
   * the ISO 9797 method 1 scheme. <p> To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_CBC</code>,
   * <code>PAD_ISO9797_M1</code> constants respectively.
   */
  public static final byte ALG_AES_CBC_ISO9797_M1 = 22;

  /**
   * Cipher algorithm <code>ALG_AES_CBC_ISO9797_M2</code> provides a cipher
   * using AES with block size 128 in CBC mode, and pads input data according to
   * the ISO 9797 method 2 (ISO 7816-4, EMV'96) scheme. <p> To request this
   * algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_CBC</code>,
   * <code>PAD_ISO9797_M2</code> constants respectively.
   */
  public static final byte ALG_AES_CBC_ISO9797_M2 = 23;

  /**
   * Cipher algorithm <code>ALG_AES_CBC_PKCS5</code> provides a cipher
   * using AES with block size 128 in CBC mode, and pads input data according to
   * the PKCS#5 scheme. <p> To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_CBC</code>,
   * <code>PAD_PKCS5</code> constants respectively.
   */
  public static final byte ALG_AES_CBC_PKCS5 = 24;

  /**
   * Cipher algorithm <code>ALG_AES_ECB_ISO9797_M1</code> provides a cipher
   * using AES with block size 128 in ECB mode, and pads input data according to
   * the ISO 9797 method 1 scheme. <p> To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_ECB</code>,
   * <code>PAD_ISO9797_M1</code> constants respectively.
   */
  public static final byte ALG_AES_ECB_ISO9797_M1 = 25;

  /**
   * Cipher algorithm <code>ALG_AES_ECB_ISO9797_M2</code> provides a cipher
   * using AES with block size 128 in ECB mode, and pads input data according to
   * the ISO 9797 method 2 (ISO 7816-4, EMV'96) scheme. <p> To request this
   * algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_ECB</code>,
   * <code>PAD_ISO9797_M2</code> constants respectively.
   */
  public static final byte ALG_AES_ECB_ISO9797_M2 = 26;

  /**
   * Cipher algorithm <code>ALG_AES_ECB_PKCS5</code> provides a cipher using
   * AES with block size 128 in ECB mode, and pads input data according to the
   * PKCS#5 scheme. <p> To request this algorithm using the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method use the <code>CIPHER_AES_ECB</code>,
   * <code>PAD_PKCS5</code> constants respectively.
   */
  public static final byte ALG_AES_ECB_PKCS5 = 27;

  // Cipher algorithm choices for the Cipher instance in the
  // getInstance(byte, byte, boolean) method

  /**
   * Cipher algorithm <code>CIPHER_AES_CBC</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method. The cipher algorithm provides a cipher using AES with block size
   * 128 in CBC mode.
   */
  public static final byte CIPHER_AES_CBC = 1;
  /**
   * Cipher algorithm <code>CIPHER_AES_ECB</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method. The cipher algorithm provides a cipher using AES with block size
   * 128 in ECB mode.
   */
  public static final byte CIPHER_AES_ECB = 2;
  /**
   * Cipher algorithm <code>CIPHER_DES_CBC</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method. The cipher algorithm provides a cipher using DES in CBC mode.
   */
  public static final byte CIPHER_DES_CBC = 3;
  /**
   * Cipher algorithm <code>CIPHER_DES_ECB</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method. The cipher algorithm provides a cipher using DES in ECB mode.
   */
  public static final byte CIPHER_DES_ECB = 4;
  /**
   * Cipher algorithm <code>CIPHER_KOREAN_SEED_CBC</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method. The cipher algorithm provides a cipher using KOREAN_SEED
   * in CBC mode.
   */
  public static final byte CIPHER_KOREAN_SEED_CBC = 5;
  /**
   * Cipher algorithm <code>CIPHER_KOREAN_SEED_ECB</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method. The cipher algorithm provides a cipher using KOREAN_SEED
   * in ECB mode.
   */
  public static final byte CIPHER_KOREAN_SEED_ECB = 6;
  /**
   * Cipher algorithm <code>CIPHER_RSA</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method. The cipher algorithm provides a cipher using RSA.
   */
  public static final byte CIPHER_RSA = 7;

  /**
   * This constant indicates that there is no discrete padding
   * algorithm. It is intended for use in the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method and
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method.
   */
  public static final byte PAD_NULL = 0;

  /**
   * Padding algorithm <code>PAD_NOPAD</code> choice for the paddingAlgorithm
   * parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requires that the data length is a multiple of the cipher
   * algorithm block size. Otherwise, a CryptoException is thrown.
   */
  public static final byte PAD_NOPAD = 1;

  /**
   * Padding algorithm <code>PAD_ISO9797_M1</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the ISO 9797 method 1 scheme.
   */
  public static final byte PAD_ISO9797_M1 = 2;

  /**
   * Padding algorithm <code>PAD_ISO9797_M2</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the ISO 9797 method 2 scheme.
   */
  public static final byte PAD_ISO9797_M2 = 3;

  /**
   * Padding algorithm <code>PAD_ISO9797_1_M1_ALG3</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the ISO9797-1 MAC algorithm 3
   * with method 1.
   */
  public static final byte PAD_ISO9797_1_M1_ALG3 = 4;
  /**
   * Padding algorithm <code>PAD_ISO9797_1_M2_ALG3</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the ISO9797-1 MAC algorithm 3
   * with method 2 (also EMV'96, EMV'2000).
   */
  public static final byte PAD_ISO9797_1_M2_ALG3 = 5;

  /**
   * Padding algorithm <code>PAD_PKCS5</code> choice for the paddingAlgorithm
   * parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS #5 scheme.
   */
  public static final byte PAD_PKCS5 = 6;

  /**
   * Padding algorithm <code>PAD_PKCS1</code> choice for the paddingAlgorithm
   * parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS v1.5 scheme.
   */
  public static final byte PAD_PKCS1 = 7;

  /**
   * Padding algorithm <code>PAD_PKCS1</code> choice for the paddingAlgorithm
   * parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-PSS scheme (IEEE
   * 1363-2000) scheme.
   */
  public static final byte PAD_PKCS1_PSS = 8;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000).
   */
  public static final byte PAD_PKCS1_OAEP = 9;
  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA224</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA224 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA224 = 13;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA256</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA256 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA256 = 14;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA384</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA384 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA384 = 15;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA512</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA512 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA512 = 16;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA3_224</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA3-224 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA3_224 = 17;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA3_256</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA3-256 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA3_256 = 18;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA3_384</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA3-384 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA3_384 = 19;

  /**
   * Padding algorithm <code>PAD_PKCS1_OAEP_SHA3_512</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link #getInstance(byte, byte, boolean) getInstance(byte, byte, boolean)}
   * method and the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the PKCS#1-OAEP scheme (IEEE
   * 1363-2000) with SHA3-512 as hash function.
   */
  public static final byte PAD_PKCS1_OAEP_SHA3_512 = 20;

  /**
   * Padding algorithm <code>PAD_ISO9796</code> choice for the paddingAlgorithm
   * parameter of the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the ISO 9796-2 scheme as
   * specified in EMV '96 and EMV 2000
   */
  public static final byte PAD_ISO9796 = 10;

  /**
   * Padding algorithm <code>PAD_ISO9796_MR</code> choice for the
   * paddingAlgorithm parameter of the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the ISO9796-2 specification with
   * message recovery(also EMV'96, EMV'2000).
   */
  public static final byte PAD_ISO9796_MR = 11;

  /**
   * Padding algorithm <code>PAD_RFC2409</code> choice for the paddingAlgorithm
   * parameter of the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. This padding
   * algorithm choice requests padding based on the RFC 2409 scheme.
   */
  public static final byte PAD_RFC2409 = 12;

  /**
   * Used in <code>init()</code> methods to indicate decryption mode.
   */
  public static final byte MODE_DECRYPT = 1;

  /**
   * Used in <code>init()</code> methods to indicate encryption mode.
   */
  public static final byte MODE_ENCRYPT = 2;

  /**
   * Cipher algorithm <code>ALG_AES_CTR</code> provides a cipher using
   * AES in counter (CTR) mode.
   * @since 3.0.5
   */
  public static final byte ALG_AES_CTR = (byte)0xF0;

  /**
   * Protected constructor.
   */
  protected Cipher() {}

  /**
   * Creates a <code>Cipher</code> object instance of the selected
   * algorithm.
   *
   * @param algorithm
   *            the desired Cipher algorithm. Valid codes listed in
   *            <code>ALG_*</code> constants above, for example,
   *            {@link #ALG_DES_CBC_NOPAD ALG_DES_CBC_NOPAD}.
   * @param externalAccess
   *            <code>true</code> indicates that the instance will be shared
   *            among multiple applet instances and that the
   *            <code>Cipher</code> instance will also be accessed (via a
   *            <code>Shareable</code> interface) when the owner of the
   *            <code>Cipher</code> instance is not the currently selected
   *            applet. If <code>true</code> the implementation must not
   *            allocate CLEAR_ON_DESELECT transient space for internal data.
   * @return the <code>Cipher</code> object instance of the requested
   *         algorithm
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm is not supported or shared access
   *                mode is not supported.
   *                </ul>
   * @see #getInstance(byte, byte, boolean)
   * @see #getAlgorithm()
   */
  public static final Cipher getInstance(byte algorithm, boolean externalAccess)
      throws CryptoException {
    Cipher instance = null;

    if (externalAccess) {
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    switch (algorithm) {
    default:
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      break;
    }

    return null;
  }

  /**
   * Creates a <code>Cipher</code> object instance with the selected
   * of the selected raw cipher algorithm and padding algorithm.
   * <p>
   * Note:
   * <ul>
   * <li><em>When the padding algorithm is built into the cipher algorithm use
   * the <code>PAD_NULL</code> choice for the padding algorithm.</em>
   * </ul>
   *
   * @param cipherAlgorithm
   *            the desired cipher algorithm. Valid codes listed in
   *            <code>CIPHER_*</code> constants in this class.g.
   *            {@link #CIPHER_AES_CBC CIPHER_AES_CBC}.
   * @param paddingAlgorithm
   *            the desired padding algorithm. Valid codes listed in
   *            <code>PAD_*</code> constants in this class e.g.
   *            {@link #PAD_NULL PAD_NULL}.
   * @param externalAccess
   *            <code>true</code> indicates that the instance will be shared
   *            among multiple applet instances and that the
   *            <code>Cipher</code> instance will also be accessed (via a
   *            <code>Shareable</code> interface) when the owner of the
   *            <code>Cipher</code> instance is not the currently selected
   *            applet. If <code>true</code> the implementation must not
   *            allocate CLEAR_ON_DESELECT transient space for internal data.
   * @return the <code>Cipher</code> object instance of the requested
   *         algorithm
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                cipher algorithm or padding algorithm or their combination
   *                or the requested shared access mode is not
   *                supported.
   *                </ul>
   * @since 3.0.4
   * @see #getInstance(byte, boolean)
   * @see #getCipherAlgorithm()
   * @see #getPaddingAlgorithm()
   */
  public static final Cipher getInstance(byte cipherAlgorithm,
                                         byte paddingAlgorithm,
                                         boolean externalAccess)
      throws CryptoException {
    Cipher instance = null;

    if (externalAccess) {
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    switch (cipherAlgorithm) {
    default:
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      break;
    }

    return null;
  }

  /**
   * Initializes the <code>Cipher</code> object with the appropriate
   * <code>Key</code>. This method should be used for algorithms which do
   * not need initialization parameters or use default parameter values.
   * <p>
   * <code>init()</code> must be used to update the <code>Cipher</code>
   * object with a new key. If the <code>Key</code> object is modified after
   * invoking the <code>init()</code> method, the behavior of the
   * <code>update()</code> and <code>doFinal()</code> methods is
   * unspecified.
   * <p>
   * The <code>Key</code> is checked for consistency with the
   * <code>Cipher</code> algorithm. For example, the key type must be matched.
   * For elliptic curve algorithms, the key must represent a valid point on the
   * curve's domain parameters. Additional key component/domain parameter
   * strength checks are implementation specific.
   * <p>
   * Note:
   * <ul>
   * <li><em>AES, DES, triple DES and Korean SEED algorithms in CBC mode will
   * use 0 for initial vector(IV) if this method is used.</em> <li><em>For
   * optimal performance, when the <code>theKey</code> parameter is a transient
   * key, the implementation should, whenever possible, use transient space for
   * internal storage.</em>
   * </ul>
   *
   * @param theKey
   *            the key object to use for encrypting or decrypting
   * @param theMode
   *            one of <code>MODE_DECRYPT</code> or
   *            <code>MODE_ENCRYPT</code>
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if
   *                <code>theMode</code> option is an undefined value or if
   *                the <code>Key</code> is inconsistent with the
   *                <code>Cipher</code> implementation.
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                <code>theKey</code> instance is uninitialized.
   *                </ul>
   */
  public abstract void init(Key theKey, byte theMode) throws CryptoException;

  /**
   * Initializes the <code>Cipher</code> object with the appropriate Key and
   * algorithm specific parameters.
   * <p>
   * <code>init()</code> must be used to update the <code>Cipher</code>
   * object with a new key. If the <code>Key</code> object is modified after
   * invoking the <code>init()</code> method, the behavior of the
   * <code>update()</code> and <code>doFinal()</code> methods is
   * unspecified.
   * <p>
   * The <code>Key</code> is checked for consistency with the
   * <code>Cipher</code> algorithm. For example, the key type must be matched.
   * For elliptic curve algorithms, the key must represent a valid point on the
   * curve's domain parameters. Additional key component/domain parameter
   * strength checks are implementation specific.
   * <p>
   * Note:
   * <ul>
   * <li><em>DES and triple DES algorithms in CBC mode expect an 8-byte
   * parameter value for the initial vector(IV) in
   * </em><code>bArray</code><em>.</em> <li><em>AES algorithms in CBC mode
   * expect a 16-byte parameter value for the initial vector(IV) in
   * </em><code>bArray</code><em>.</em> <li><em>Korean SEED algorithms in CBC
   * mode expect a 16-byte parameter value for the initial vector(IV) in
   * </em><code>bArray</code><em>.</em> <li><em>AES algorithms in ECB mode, DES
   * algorithms in ECB mode, Korean SEED algorithm in ECB mode, RSA and DSA
   * algorithms throw </em><code>CryptoException.ILLEGAL_VALUE</code><em>.</em>
   * <li><em>For optimal performance, when the <code>theKey</code> parameter is
   * a transient key, the implementation should, whenever possible, use
   * transient space for internal storage.</em>
   * </ul>
   *
   * @param theKey
   *            the key object to use for encrypting or decrypting.
   * @param theMode
   *            one of <code>MODE_DECRYPT</code> or
   *            <code>MODE_ENCRYPT</code>
   * @param bArray
   *            byte array containing algorithm specific initialization info
   * @param bOff
   *            offset within bArray where the algorithm specific data begins
   * @param bLen
   *            byte length of algorithm specific parameter data
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if
   *                <code>theMode</code> option is an undefined value or if
   *                a byte array parameter option is not supported by the
   *                algorithm or if the <code>bLen</code> is an incorrect
   *                byte length for the algorithm specific data or if the
   *                <code>Key</code> is inconsistent with the
   *                <code>Cipher</code> implementation.
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                <code>theKey</code> instance is uninitialized.
   *                </ul>
   */
  public abstract void init(Key theKey, byte theMode, byte[] bArray, short bOff,
                            short bLen) throws CryptoException;

  /**
   * Gets the Cipher algorithm.
   *
   * @return the algorithm code defined above; if the algorithm is not one of
   *     the pre-defined
   *         algorithms, <code>0</code> is returned.
   * @see #getInstance(byte, boolean)
   */
  public abstract byte getAlgorithm();

  /**
   * Gets the raw cipher algorithm.
   * Pre-defined codes listed in {@code CIPHER_*} constants in this class e.g.
   * {@link #CIPHER_AES_CBC CIPHER_AES_CBC}.
   *
   * @return the raw cipher algorithm code defined above; if the algorithm is
   *     not
   * one of the pre-defined algorithms, <code>0</code> is returned.
   *
   * @since 3.0.5
   */
  public abstract byte getCipherAlgorithm();

  /**
   * Gets the padding algorithm. Pre-defined codes listed in {@code PAD_*}
   * constants in this class e.g.
   * {@link #PAD_NULL PAD_NULL}.
   *
   * @return the padding algorithm code defined in the {@code Cipher} class; if
   * the algorithm is not one of the pre-defined algorithms, {@code 0} is
   * returned.
   *
   * @since 3.0.5
   */
  public abstract byte getPaddingAlgorithm();

  /**
   * Generates encrypted/decrypted output from all/last input data. This
   * method must be invoked to complete a cipher operation. This method
   * processes any remaining input data buffered by one or more calls to the
   * <code>update()</code> method as well as input data supplied in the
   * <code>inBuff</code> parameter.
   * <p>
   * A call to this method also resets this <code>Cipher</code> object to
   * the state it was in when previously initialized via a call to
   * <code>init()</code>. That is, the object is reset and available to
   * encrypt or decrypt (depending on the operation mode that was specified in
   * the call to <code>init()</code>) more data. In addition, note that the
   * initial vector(IV) used in AES, DES and Korean SEED algorithms will be
   * reset to 0.
   * <p>
   * Notes:
   * <ul>
   * <li><em>When using block-aligned data (multiple of block size),
   * if the input buffer, </em><code>inBuff</code><em> and the output buffer,
   * </em><code>outBuff</code><em>
   * are the same array, then the output data area must not partially overlap
   * the input data area such that the input data is modified before it is used;
   * if </em><code>inBuff==outBuff</code><em> and<br> </em><code>inOffset &lt;
   * outOffset &lt; inOffset+inLength</code><em>, incorrect output may
   * result.</em> <li><em>When non-block aligned data is presented as input
   * data, no amount of input and output buffer data overlap is allowed; if
   * </em><code>inBuff==outBuff</code><em> and<br> </em><code>outOffset &lt;
   * inOffset+inLength</code><em>, incorrect output may result.</em>
   * <li><em>AES, DES, triple DES and Korean SEED algorithms in CBC mode reset
   * the initial vector(IV) to 0. The initial vector(IV) can be re-initialized
   * using the
   * </em><code>init(Key, byte, byte[], short, short)</code><em> method.</em>
   * <li><em>On decryption operations (except when ISO 9797 method 1 padding is
   * used), the padding bytes are not written to
   * </em><code>outBuff</code><em>.</em> <li><em>On encryption and decryption
   * operations, the number of bytes output into </em><code>outBuff</code><em>
   * may be larger or smaller than </em><code>inLength</code><em> or even
   * 0.</em> <li><em>On decryption operations resulting in an
   * </em><code>ArrayIndexOutOfBoundsException</code><em>,
   * </em><code>outBuff</code><em> may be partially modified.</em>
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param inBuff
   *            the input buffer of data to be encrypted/decrypted
   * @param inOffset
   *            the offset into the input buffer at which to begin
   *            encryption/decryption
   * @param inLength
   *            the byte length to be encrypted/decrypted
   * @param outBuff
   *            the output buffer, may be the same as the input buffer
   * @param outOffset
   *            the offset into the output buffer where the resulting output
   *            data begins
   * @return number of bytes output in <code>outBuff</code>
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Cipher</code> object is not initialized.
   *                <li><code>CryptoException.ILLEGAL_USE</code> if one of
   *                the following conditions is met:
   *                <ul>
   *                <li>This <code>Cipher</code> algorithm does not pad the
   *                message and the message is not block aligned.
   *                <li>This <code>Cipher</code> algorithm does not pad the
   *                message and no input data has been provided in
   *                <code>inBuff</code> or via the <code>update()</code>
   *                method.
   *                <li>The input message length is not supported or the message
   * value is greater than or equal to the modulus. <li>The decrypted data is
   * not bounded by appropriate padding bytes.
   *                </ul>
   *                </ul>
   */
  public abstract short doFinal(byte[] inBuff, short inOffset, short inLength,
                                byte[] outBuff, short outOffset)
      throws CryptoException;

  /**
   * Generates encrypted/decrypted output from input data. This method is
   * intended for multiple-part encryption/decryption operations.
   * <p>
   * This method requires temporary storage of intermediate results. In
   * addition, if the input data length is not block aligned (multiple of
   * block size) then additional internal storage may be allocated at this
   * time to store a partial input data block. This may result in additional
   * resource consumption and/or slow performance.
   * <p>
   * This method should only be used if all the input data required for the
   * cipher is not available in one byte array. If all the input data required
   * for the cipher is located in a single byte array, use of the
   * <code>doFinal()</code> method to process all of the input data is
   * recommended. The <code>doFinal()</code> method must be invoked to
   * complete processing of any remaining input data buffered by one or more
   * calls to the <code>update()</code> method.
   * <p>
   * Notes:
   * <ul>
   * <li><em>When using block-aligned data (multiple of block size),
   * if the input buffer, </em><code>inBuff</code><em> and the output buffer,
   * </em><code>outBuff</code><em>
   * are the same array, then the output data area must not partially overlap
   * the input data area such that the input data is modified before it is used;
   * if </em><code>inBuff==outBuff</code><em> and<br> </em><code>inOffset &lt;
   * outOffset &lt; inOffset+inLength</code><em>, incorrect output may
   * result.</em> <li><em>When non-block aligned data is presented as input
   * data, no amount of input and output buffer data overlap is allowed; if
   * </em><code>inBuff==outBuff</code><em> and<br> </em><code>outOffset &lt;
   * inOffset+inLength</code><em>, incorrect output may result.</em> <li><em>On
   * decryption operations(except when ISO 9797 method 1 padding is used), the
   * padding bytes are not written to </em><code>outBuff</code><em>.</em>
   * <li><em>On encryption and decryption operations,
   * block alignment considerations may require that
   * the number of bytes output into </em><code>outBuff</code><em> be larger or
   * smaller than
   * </em><code>inLength</code><em> or even 0.</em>
   * <li><em>If </em><code>inLength</code><em> is 0 this method does
   * nothing.</em>
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param inBuff
   *            the input buffer of data to be encrypted/decrypted
   * @param inOffset
   *            the offset into the input buffer at which to begin
   *            encryption/decryption
   * @param inLength
   *            the byte length to be encrypted/decrypted
   * @param outBuff
   *            the output buffer, may be the same as the input buffer
   * @param outOffset
   *            the offset into the output buffer where the resulting
   *            ciphertext/plaintext begins
   * @return number of bytes output in <code>outBuff</code>
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Cipher</code> object is not initialized.
   *                <li><code>CryptoException.ILLEGAL_USE</code> if the
   *                input message length is not supported or the message value
   *                is greater than or equal to the modulus.
   *                </ul>
   */
  public abstract short update(byte[] inBuff, short inOffset, short inLength,
                               byte[] outBuff, short outOffset)
      throws CryptoException;

  /**
   * The {@code OneShot} class is a specialization of the {@code Cipher}
   * class intended to support efficient one-shot ciphering and deciphering
   * operations that may avoid persistent memory writes entirely. The
   * {@code OneShot} class uses a delegation model where calls are delegated
   * to an instance of a {@code Cipher}-implementing class configured for
   * one-shot use.
   * <p>
   * Note:
   * <ul>
   * <li><em>Instances of </em>{@code OneShot}<em> are JCRE owned temporary
   * Entry Point Object instances and references to these temporary objects
   * cannot be stored in class variables or instance variables or array
   * components. See Runtime Environment Specification, Java Card Platform,
   * Classic Edition, section 6.2.1 for details.</em></li>
   * <li><em>The platform must support at least one instance of
   * </em>{@code OneShot}<em>. Support for several </em>{@code OneShot}<em>
   * instances is platform dependent. To guarantee application code portability,
   * acquiring/opening and then releasing/closing
   * </em>{@code OneShot}<em> instances should be performed within tight
   * {@code try-catch-finally} blocks (as illustrated in the code sample below)
   * in order to avoid unnecessarily keeping hold of instances and to prevent
   * interleaving invocations - hence enforcing the <b>One-Shot</b> usage
   * pattern. Additionally, any local variable holding a reference to a
   * </em>{@code OneShot}<em> instance should be set to {@code null} once the
   * instance is closed in order to prevent further use attempts.</em></li>
   * <li><em>Upon return from any {@code Applet} entry point method, back to the
   * JCRE, and on tear or card reset events any {@code OneShot} instances in use
   * are released back to the JCRE.</em> <li><em>The internal state associated
   * with an instance of
   * </em>{@code OneShot}<em> must be bound to the initial calling context
   * (owner context) as to preclude use/calls on that instance from other
   * contexts.</em></li>
   * <li><em>Unless otherwise specified, after an instance of
   * </em>{@code OneShot}<em> is released back to the JCRE, calls to any of the
   * instance methods of the </em>{@code OneShot}<em> class results in an
   * </em>{@code CryptoException}<em> being thrown with reason code
   * </em>{@code CryptoException.ILLEGAL_USE}<em>.</em></li>
   * </ul>
   * <p>
   * The following code shows a typical usage pattern for the
   * {@code OneShot} class.
   * <pre>
   * ...
   * Cipher.OneShot enc = null;
   * try {
   *     enc = Cipher.OneShot.open(Cipher.CIPHER_RSA, Cipher.PAD_PKCS1);
   *     enc.init(someRSAKey, Cipher.MODE_ENCRYPT);
   *     enc.doFinal(someInData, (short) 0, (short) someInData.length, encData,
   * (short) 0); } catch (CryptoException ce) {
   *     // Handle exception
   * } finally {
   *     if (enc != null) {
   *         enc.close();
   *         enc = null;
   *     }
   * }
   * ...
   * </pre>
   *
   * @since 3.0.5
   */
  public static final class OneShot extends Cipher {

    /**
     * Opens/acquires a JCRE owned temporary Entry Point Object instance of
     * {@code OneShot} with the selected cipher algorithm and padding
     * algorithm.
     * <p>
     * Note:
     * <ul>
     * <li><em>When the padding algorithm is built into the cipher algorithm use
     * the {@code PAD_NULL} choice for the padding algorithm.</em>
     * </ul>
     *
     * @param cipherAlgorithm the desired cipher algorithm. Valid codes listed
     * in {@code CIPHER_*} constants in this class.g.
     * {@link #CIPHER_AES_CBC CIPHER_AES_CBC}.
     * @param paddingAlgorithm the desired padding algorithm. Valid codes listed
     * in {@code PAD_*} constants in the Cipher class e.g.
     * {@link javacardx.crypto.Cipher#PAD_NULL PAD_NULL}.
     * @return the {@code OneShot} object instance of the requested
     * algorithm.
     * @throws CryptoException with the following reason codes:
     * <ul>
     * <li>{@code CryptoException.NO_SUCH_ALGORITHM} if the requested cipher
     * algorithm or padding algorithm or their combination is not
     * supported.</li>
     * </ul>
     * @throws javacard.framework.SystemException with the following reason
     * codes:
     * <ul>
     * <li>{@code SystemException.NO_RESOURCE} if sufficient resources are not
     * available.</li>
     * </ul>
     */
    public static final OneShot open(byte cipherAlgorithm,
                                     byte paddingAlgorithm)
        throws CryptoException {
      // TODO: Not yet implemented
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);

      return null;
    }

    /**
     * Closes and releases this JCRE owned temporary instance of the {@code
     * OneShot} object for reuse. If this method is called again this method
     * does nothing.
     *
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    public void close() {
      // TODO: Not yet implemented
    }

    /**
     * Always throws a {@code CryptoException}. This method is not supported by
     * {@code OneShot}.
     *
     * @param inBuff the input buffer of data to be encrypted/decrypted
     * @param inOffset the offset into the input buffer at which to begin
     * encryption/decryption
     * @param inLength the byte length to be encrypted/decrypted
     * @param outBuff the output buffer, may be the same as the input buffer
     * @param outOffset the offset into the output buffer where the resulting
     * ciphertext/plaintext begins
     * @return number of bytes output in <code>outBuff</code>
     * @throws CryptoException with the following reason codes:
     * <ul>
     * <li>{@code CryptoException.ILLEGAL_USE} always.</li>
     * </ul>
     */
    @Override
    public short update(byte[] inBuff, short inOffset, short inLength,
                        byte[] outBuff, short outOffset)
        throws CryptoException {
      // TODO: Not yet implemented
      CryptoException.throwIt(CryptoException.ILLEGAL_USE);
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public void init(Key theKey, byte theMode) throws CryptoException {
      // TODO: Not yet implemented
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public void init(Key theKey, byte theMode, byte[] bArray, short bOff,
                     short bLen) throws CryptoException {
      // TODO: Not yet implemented
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public byte getAlgorithm() {
      // TODO: Not yet implemented
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public byte getCipherAlgorithm() {
      // TODO: Not yet implemented
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public byte getPaddingAlgorithm() {
      // TODO: Not yet implemented
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public short doFinal(byte[] inBuff, short inOffset, short inLength,
                         byte[] outBuff, short outOffset)
        throws CryptoException {
      // TODO: Not yet implemented
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);

      return 0;
    }
  }
}
