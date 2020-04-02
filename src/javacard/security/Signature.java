/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

import javacard.framework.JCSystem;
import javacard.framework.SystemException;
import javacardx.crypto.Cipher;

/**
 * The <code>Signature</code> class is the base class for Signature
 * algorithms. Implementations of Signature algorithms must extend this class
 * and implement all the abstract methods.
 * <p>
 * The term "pad" is used in the public key signature algorithms below to refer
 * to all the operations specified in the referenced scheme to transform the
 * message digest into the encryption block size.
 * <p>
 * A tear or card reset event resets an initialized <code>Signature</code>
 * object to the state it was in when previously initialized via a call to
 * <code>init()</code>. For algorithms which support keys with transient key
 * data sets, such as DES, triple DES, AES, and Korean SEED the
 * <code>Signature</code> object key becomes uninitialized on clear events
 * associated with the <code>Key</code> object used to initialize the
 * <code>Signature</code> object.
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
public abstract class Signature {

  // algorithm options
  /**
   * Signature algorithm <code>ALG_DES_MAC4_NOPAD</code> generates a 4-byte
   * MAC (most significant 4 bytes of encrypted block) using DES in CBC mode
   * or triple DES in outer CBC mode. This algorithm does not pad input data.
   * If the input data is not (8 byte) block aligned it throws
   * <code>CryptoException</code> with the reason code
   * <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC4</code>, <code>Cipher.PAD_NOPAD</code> constants
   * respectively.
   */
  public static final byte ALG_DES_MAC4_NOPAD = 1;

  /**
   * Signature algorithm <code>ALG_DES_MAC8_NOPAD</code> generates an
   * 8-byte MAC using DES in CBC mode or triple DES in outer CBC mode. This
   * algorithm does not pad input data. If the input data is not (8 byte)
   * block aligned it throws <code>CryptoException</code> with the reason
   * code <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC8</code>, <code>Cipher.PAD_NOPAD</code> constants
   * respectively. <p> Note: <ul> <li><em>This algorithm must not be implemented
   * if export restrictions apply.</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC8_NOPAD = 2;

  /**
   * Signature algorithm <code>ALG_DES_MAC4_ISO9797_M1</code> generates a
   * 4-byte MAC (most significant 4 bytes of encrypted block) using DES in CBC
   * mode or triple DES in outer CBC mode. Input data is padded according to
   * the ISO 9797 method 1 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC4</code>, <code>Cipher.PAD_ISO9797_M1</code>
   * constants respectively.
   */
  public static final byte ALG_DES_MAC4_ISO9797_M1 = 3;

  /**
   * Signature algorithm <code>ALG_DES_MAC8_ISO9797_M1</code> generates an
   * 8-byte MAC using DES in CBC mode or triple DES in outer CBC mode. Input
   * data is padded according to the ISO 9797 method 1 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC8</code>, <code>Cipher.PAD_ISO9797_M1</code>
   * constants respectively. <p> Note: <ul> <li><em>This algorithm must not be
   * implemented if export restrictions apply.</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC8_ISO9797_M1 = 4;

  /**
   * Signature algorithm <code>ALG_DES_MAC4_ISO9797_M2</code> generates a
   * 4-byte MAC (most significant 4 bytes of encrypted block) using DES in CBC
   * mode or triple DES in outer CBC mode. Input data is padded according to
   * the ISO 9797 method 2 (ISO 7816-4, EMV'96) scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC4</code>, <code>Cipher.PAD_ISO9797_M2</code>
   * constants respectively.
   */
  public static final byte ALG_DES_MAC4_ISO9797_M2 = 5;

  /**
   * Signature algorithm <code>ALG_DES_MAC8_ISO9797_M2</code> generates an
   * 8-byte MAC using DES in CBC mode or triple DES in outer CBC mode. Input
   * data is padded according to the ISO 9797 method 2 (ISO 7816-4, EMV'96)
   * scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC8</code>, <code>Cipher.PAD_ISO9797_M2</code>
   * constants respectively. <p> Note: <ul> <li><em>This algorithm must not be
   * implemented if export restrictions apply.</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC8_ISO9797_M2 = 6;

  /**
   * Signature algorithm <code>ALG_DES_MAC4_PKCS5</code> generates a 4-byte
   * MAC (most significant 4 bytes of encrypted block) using DES in CBC mode
   * or triple DES in outer CBC mode. Input data is padded according to the
   * PKCS#5 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC4</code>, <code>Cipher.PAD_PKCS5</code> constants
   * respectively.
   */
  public static final byte ALG_DES_MAC4_PKCS5 = 7;

  /**
   * Signature algorithm <code>ALG_DES_MAC8_PKCS5</code> generates an
   * 8-byte MAC using DES in CBC mode or triple DES in outer CBC mode. Input
   * data is padded according to the PKCS#5 scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC8</code>, <code>Cipher.PAD_PKCS5</code> constants
   * respectively. <p> Note: <ul> <li><em>This algorithm must not be implemented
   * if export restrictions apply.</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC8_PKCS5 = 8;

  /**
   * Signature algorithm <code>ALG_RSA_SHA_ISO9796</code> generates a
   * 20-byte SHA digest, pads the digest according to the ISO 9796-2 scheme as
   * specified in EMV '96 and EMV 2000, and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_ISO9796</code> constants
   * respectively. <p> Note: <ul> <li><em>The <code>verify</code> method does
   * not support the message recovery semantics of this algorithm.</em>
   * </ul>
   */
  public static final byte ALG_RSA_SHA_ISO9796 = 9;

  /**
   * Signature algorithm <code>ALG_RSA_SHA_PKCS1</code> generates a 20-byte
   * SHA digest, pads the digest according to the PKCS#1 (v1.5) scheme, and
   * encrypts it using RSA.
   * <p>
   * Note:
   * <ul>
   * <li><em> The encryption block(EB) during signing is built as follows:<br>
   * &nbsp; EB = 00 || 01 || PS || 00 || T<br>
   * &nbsp; &nbsp; &nbsp; :: where T is the DER encoding of :<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestInfo ::= SEQUENCE {<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestAlgorithm
   * AlgorithmIdentifier of SHA-1,<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * digest OCTET STRING<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; }<br>
   * &nbsp; &nbsp; &nbsp; :: PS is an octet string of length k-3-||T|| with
   * value FF. The length of PS must be at least 8 octets.<br> &nbsp; &nbsp;
   * &nbsp; :: k is the RSA modulus size.<br> DER encoded SHA-1
   * AlgorithmIdentifier = 30 21 30 09 06 05 2B 0E 03 02 1A 05 00
   * 04 14.</em><br>
   * </ul>
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_PKCS1 = 10;

  /**
   * Signature algorithm <code>ALG_RSA_MD5_PKCS1</code> generates a 16-byte
   * MD5 digest, pads the digest according to the PKCS#1 (v1.5) scheme, and
   * encrypts it using RSA.
   * <p>
   * Note:
   * <ul>
   * <li><em> The encryption block(EB) during signing is built as follows:<br>
   * &lt; &nbsp; EB = 00 || 01 || PS || 00 || T<br>
   * &nbsp; &nbsp; &nbsp; :: where T is the DER encoding of :<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestInfo ::= SEQUENCE {<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestAlgorithm
   * AlgorithmIdentifier of MD5,<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * digest OCTET STRING<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; }<br>
   * &nbsp; &nbsp; &nbsp; :: PS is an octet string of length k-3-||T|| with
   * value FF. The length of PS must be at least 8 octets.<br> &nbsp; &nbsp;
   * &nbsp; :: k is the RSA modulus size.<br> DER encoded MD5
   * AlgorithmIdentifier = 30 20 30 0C 06 08 2A 86 48 86 F7 0D 02 05 05 00
   * 04 10.</em><br>
   * </ul>
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_MD5</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_MD5_PKCS1 = 11;

  /**
   * Signature algorithm <code>ALG_RSA_RIPEMD160_ISO9796</code> generates a
   * 20-byte RIPE MD-160 digest, pads the digest according to the ISO 9796
   * scheme, and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_RIPEMD160</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_ISO9796</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_RIPEMD160_ISO9796 = 12;

  /**
   * Signature algorithm <code>ALG_RSA_RIPEMD160_PKCS1</code> generates a
   * 20-byte RIPE MD-160 digest, pads the digest according to the PKCS#1
   * (v1.5) scheme, and encrypts it using RSA.
   * <p>
   * Note:
   * <ul>
   * <li><em> The encryption block(EB) during signing is built as follows:<br>
   * &lt;&nbsp; EB = 00 || 01 || PS || 00 || T<br>
   * &nbsp; &nbsp; &nbsp; :: where T is the DER encoding of :<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestInfo ::= SEQUENCE {<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestAlgorithm
   * AlgorithmIdentifier of RIPEMD160,<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * &nbsp; digest OCTET STRING<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * }<br> &nbsp; &nbsp; &nbsp; :: PS is an octet string of length k-3-||T||
   * with value FF. The length of PS must be at least 8 octets.<br> &nbsp;
   * &nbsp; &nbsp; :: k is the RSA modulus size. </em><br>
   * </ul>
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_RIPEMD160</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_RIPEMD160_PKCS1 = 13;

  /**
   * Signature algorithm <code>ALG_DSA_SHA</code> generates a 20-byte SHA
   * digest and signs/verifies the digests using DSA. The signature is encoded
   * as an ASN.1 sequence of two INTEGER values, r and s, in that order:
   * SEQUENCE ::= { r INTEGER, s INTEGER }
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_DSA</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_DSA_SHA = 14;

  /**
   * Signature algorithm <code>ALG_RSA_SHA_RFC2409</code> generates a
   * 20-byte SHA digest, pads the digest according to the RFC2409 scheme, and
   * encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_RFC2409</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_RFC2409 = 15;

  /**
   * Signature algorithm <code>ALG_RSA_MD5_RFC2409</code> generates a
   * 16-byte MD5 digest, pads the digest according to the RFC2409 scheme, and
   * encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_MD5</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_RFC2409</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_MD5_RFC2409 = 16;

  /**
   * Signature algorithm <code>ALG_ECDSA_SHA</code> generates a 20-byte SHA
   * digest and signs/verifies the digest using ECDSA. The signature is
   * encoded as an ASN.1 sequence of two INTEGER values, r and s, in that
   * order: SEQUENCE ::= { r INTEGER, s INTEGER }
   * <p>
   * Note:
   * <ul>
   * <li><em>This algorithm truncates the SHA-1 digest to the length of the EC
   * key for EC key lengths less than 160 bits in accordance with section 4.1
   *     "Elliptic Curve Digit Signature Algorithm" of the
   *     "SEC 1: Elliptic Curve Cryptography" specification</em>
   * </ul>
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_ECDSA</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_ECDSA_SHA = 17;

  /**
   * Signature algorithm <code>ALG_AES_MAC_128_NOPAD</code> generates a
   * 16-byte MAC using AES with blocksize 128 in CBC mode and does not pad
   * input data. If the input data is not (16-byte) block aligned it throws
   * <code>CryptoException</code> with the reason code
   * <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_AES_MAC128</code>, <code>Cipher.PAD_NOPAD</code> constants
   * respectively.
   *
   */
  public static final byte ALG_AES_MAC_128_NOPAD = 18;

  /**
   * Signature algorithm <code>ALG_AES_CMAC_128</code> generates a
   * 16-byte Cipher-based MAC (CMAC) using AES with
   * blocksize 128 in CBC mode with ISO9797_M2 padding scheme.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_AES_CMAC_128</code>, <code>Cipher.PAD_ISO9797_M2</code>
   * constants respectively.
   *
   */
  public static final byte ALG_AES_CMAC_128 = 49;

  /**
   * Signature algorithm <code>ALG_DES_MAC4_ISO9797_1_M2_ALG3</code>
   * generates a 4-byte MAC using triple DES with a 2-key DES3 key according
   * to ISO9797-1 MAC
   * algorithm 3 with method 2 (also EMV'96, EMV'2000), where input data is
   * padded using method 2 and the data is processed as described in MAC
   * Algorithm 3 of the ISO 9797-1 specification. The left key block of the
   * triple DES key is used as a single DES key(K) and the right key block of
   * the triple DES key is used as a single DES Key (K') during MAC
   * processing. The final result is truncated to 4 bytes as described in
   * ISO9797-1.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC4</code>, <code>Cipher.PAD_ISO9797_1_M2_ALG3</code>
   * constants respectively. <p> Note: <ul> <li><em>This algorithm is a triple
   * DES algorithm and requires a 16 byte key (2-key DES3 key)</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC4_ISO9797_1_M2_ALG3 = 19;

  /**
   * Signature algorithm <code>ALG_DES_MAC8_ISO9797_1_M2_ALG3</code>
   * generates an 8-byte MAC using triple DES with a 2-key DES3 key according
   * to ISO9797-1 MAC
   * algorithm 3 with method 2 (also EMV'96, EMV'2000), where input data is
   * padded using method 2 and the data is processed as described in MAC
   * Algorithm 3 of the ISO 9797-1 specification. The left key block of the
   * triple DES key is used as a single DES key(K) and the right key block of
   * the triple DES key is used as a single DES Key (K') during MAC
   * processing. The final result is truncated to 8 bytes as described in
   * ISO9797-1.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC8</code>, <code>Cipher.PAD_ISO9797_1_M2_ALG3</code>
   * constants respectively. <p> Note: <ul> <li><em>This algorithm is a triple
   * DES algorithm and requires a 16 byte key (2-key DES3 key)</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC8_ISO9797_1_M2_ALG3 = 20;

  /**
   * Signature algorithm <code>ALG_RSA_SHA_PKCS1_PSS</code> generates a
   * 20-byte SHA-1 digest, pads it according to the PKCS#1-PSS scheme (IEEE
   * 1363-2000), and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1_PSS</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_PKCS1_PSS = 21;

  /**
   * Signature algorithm <code>ALG_RSA_MD5_PKCS1_PSS</code> generates a
   * 16-byte MD5 digest, pads it according to the PKCS#1-PSS scheme (IEEE
   * 1363-2000), and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_MD5</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1_PSS</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_MD5_PKCS1_PSS = 22;

  /**
   * Signature algorithm <code>ALG_RSA_RIPEMD160_PKCS1_PSS</code> generates
   * a 20-byte RIPE MD-160 digest, pads it according to the PKCS#1-PSS scheme
   * (IEEE 1363-2000), and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_RIPEMD160</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1_PSS</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_RIPEMD160_PKCS1_PSS = 23;

  /**
   * HMAC message authentication algorithm <code>ALG_HMAC_SHA1</code> This
   * algorithm generates an HMAC following the steps found in RFC: 2104 using
   * SHA1 as the hashing algorithm.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_HMAC</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_HMAC_SHA1 = 24;

  /**
   * HMAC message authentication algorithm <code>ALG_HMAC_SHA_256</code>
   * This algorithm generates an HMAC following the steps found in RFC: 2104
   * using SHA-256 as the hashing algorithm.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_256</code>,
   * <code>SIG_CIPHER_HMAC</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_HMAC_SHA_256 = 25;

  /**
   * HMAC message authentication algorithm <code>ALG_HMAC_SHA_384</code>
   * This algorithm generates an HMAC following the steps found in RFC: 2104
   * using SHA-384 as the hashing algorithm.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_384</code>,
   * <code>SIG_CIPHER_HMAC</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_HMAC_SHA_384 = 26;

  /**
   * HMAC message authentication algorithm <code>ALG_HMAC_SHA_512</code>
   * This algorithm generates an HMAC following the steps found in RFC: 2104
   * using SHA-512 as the hashing algorithm.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_512</code>,
   * <code>SIG_CIPHER_HMAC</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_HMAC_SHA_512 = 27;

  /**
   * HMAC message authentication algorithm <code>ALG_HMAC_MD5</code> This
   * algorithm generates an HMAC following the steps found in RFC: 2104 using
   * MD5 as the hashing algorithm.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_MD5</code>,
   * <code>SIG_CIPHER_HMAC</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_HMAC_MD5 = 28;

  /**
   * HMAC message authentication algorithm <code>ALG_HMAC_RIPEMD160</code>
   * This algorithm generates an HMAC following the steps found in RFC: 2104
   * using RIPEMD160 as the hashing algorithm.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_RIPEMD160</code>,
   * <code>SIG_CIPHER_HMAC</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively.
   */
  public static final byte ALG_HMAC_RIPEMD160 = 29;

  /**
   * Signature algorithm<code>ALG_RSA_SHA_ISO9796_MR</code> generates
   * 20-byte SHA-1 digest, pads it according to the ISO9796-2 specification
   * and encrypts using RSA. This algorithm is conformant with EMV2000.
   * <p>
   * This algorithm uses the first part of the input message as padding bytes
   * during signing. During verification, these message bytes (recoverable
   * message) can be recovered to reconstruct the message.
   * <p>
   * To use this algorithm the <code>Signature</code> object instance
   * returned by the <code>getInstance</code> method must be cast to the
   * <code>SignatureMessageRecovery</code> interface to invoke the
   * applicable methods.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_ISO9796_MR</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_ISO9796_MR = 30;

  /**
   * Signature algorithm<code>ALG_RSA_RIPEMD160_ISO9796_MR</code> generates
   * 20-byte RIPE MD-160 digest, pads it according to the ISO9796-2
   * specification and encrypts using RSA.
   * <p>
   * This algorithm uses the first part of the input message as padding bytes
   * during signing. During verification, these message bytes (recoverable
   * message) can be recovered to reconstruct the message.
   * <p>
   * To use this algorithm the <code>Signature</code> object instance
   * returned by the <code>getInstance</code> method must be cast to the
   * <code>SignatureMessageRecovery</code> interface to invoke the
   * applicable methods.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_RIPEMD160</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_ISO9796_MR</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_RIPEMD160_ISO9796_MR = 31;

  /**
   * Signature algorithm <code>ALG_KOREAN_SEED_MAC_NOPAD</code> generates an
   * 16-byte MAC using Korean SEED in CBC mode. This algorithm does not pad
   * input data. If the input data is not (16 byte) block aligned it throws
   * <code>CryptoException</code> with the reason code
   * <code>ILLEGAL_USE</code>.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_KOREAN_SEED_MAC</code>, <code>Cipher.PAD_NOPAD</code>
   * constants respectively. <p> Note: <ul> <li><em>This algorithm must not be
   * implemented if export restrictions apply.</em>
   * </ul>
   */
  public static final byte ALG_KOREAN_SEED_MAC_NOPAD = 32;

  /**
   * Signature algorithm <code>ALG_ECDSA_SHA_256</code> generates a 32-byte
   * SHA-256 digest and signs/verifies the digest using ECDSA with the
   * curve defined in the <code>ECKey</code> parameters - such as the P-256
   * curve specified in the Digital Signature Standards specification[NIST FIPS
   * PUB 186-2]. The signature is encoded as an ASN.1 sequence of two INTEGER
   * values, r and s, in that order: SEQUENCE ::= { r INTEGER, s INTEGER }
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_256</code>,
   * <code>SIG_CIPHER_ECDSA</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively. <p> Note: <ul> <li><em>This algorithm truncates the SHA-256
   * digest to the length of the EC key for EC key lengths less than 256 bits in
   * accordance with section 4.1 "Elliptic Curve Digit Signature Algorithm" of
   * the "SEC 1: Elliptic Curve Cryptography" specification</em>
   * </ul>
   */
  public static final byte ALG_ECDSA_SHA_256 = 33;

  /**
   * Signature algorithm <code>ALG_ECDSA_SHA_384</code> generates a 48-byte
   * SHA-384 digest and signs/verifies the digest using ECDSA with the
   * curve defined in the <code>ECKey</code> parameters - such as the P-384
   * curve specified in the Digital Signature Standards specification[NIST FIPS
   * PUB 186-2]. The signature is encoded as an ASN.1 sequence of two INTEGER
   * values, r and s, in that order: SEQUENCE ::= { r INTEGER, s INTEGER }
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_384</code>,
   * <code>SIG_CIPHER_ECDSA</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively. <p> Note: <ul> <li><em>This algorithm truncates the SHA-384
   * digest to the length of the EC key for EC key lengths less than 384 bits in
   * accordance with section 4.1 "Elliptic Curve Digit Signature Algorithm" of
   * the "SEC 1: Elliptic Curve Cryptography" specification</em>
   * </ul>
   */
  public static final byte ALG_ECDSA_SHA_384 = 34;

  /**
   * This Signature algorithm <code>ALG_AES_MAC_192_NOPAD</code>
   * should not be used. AES algorithms as defined by NIST in the FIPS PUB 197
   * standard only support a block size of 128 bits.
   *
   * @deprecated
   *
   */
  public static final byte ALG_AES_MAC_192_NOPAD = 35;

  /**
   * This Signature algorithm <code>ALG_AES_MAC_256_NOPAD</code>
   * should not be used. AES algorithms as defined by NIST in the FIPS PUB 197
   * standard only support a block size of 128 bits.
   * @deprecated
   */
  public static final byte ALG_AES_MAC_256_NOPAD = 36;
  /**
   * Signature algorithm <code>ALG_ECDSA_SHA_224</code> generates a 28-byte
   * SHA-224 digest and signs/verifies the digest using ECDSA with the
   * curve defined in the <code>ECKey</code> parameters - such as the P-224
   * curve specified in the Digital Signature Standards specification[NIST FIPS
   * PUB 186-2]. The signature is encoded as an ASN.1 sequence of two INTEGER
   * values, r and s, in that order: SEQUENCE ::= { r INTEGER, s INTEGER }
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_224</code>,
   * <code>SIG_CIPHER_ECDSA</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively. <p> Note: <ul> <li><em>This algorithm truncates the SHA-224
   * digest to the length of the EC key for EC key lengths less than 224 bits in
   * accordance with section 4.1 "Elliptic Curve Digit Signature Algorithm" of
   * the "SEC 1: Elliptic Curve Cryptography" specification</em>
   * </ul>
   */
  public static final byte ALG_ECDSA_SHA_224 = 37;
  /**
   * Signature algorithm <code>ALG_ECDSA_SHA_512</code> generates a 64-byte
   * SHA-512 digest and signs/verifies the digest using ECDSA with the
   * curve defined in the <code>ECKey</code> parameters - such as the P-521
   * curve specified in the Digital Signature Standards specification[NIST FIPS
   * PUB 186-2]. The signature is encoded as an ASN.1 sequence of two INTEGER
   * values, r and s, in that order: SEQUENCE ::= { r INTEGER, s INTEGER }
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_512</code>,
   * <code>SIG_CIPHER_ECDSA</code>, <code>Cipher.PAD_NULL</code> constants
   * respectively. <p> Note: <ul> <li><em>This algorithm truncates the SHA-512
   * digest to the length of the EC key for EC key lengths less than 512 bits in
   * accordance with section 4.1 "Elliptic Curve Digit Signature Algorithm" of
   * the "SEC 1: Elliptic Curve Cryptography" specification</em>
   * </ul>
   */
  public static final byte ALG_ECDSA_SHA_512 = 38;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_224_PKCS1</code> generates a 28-byte
   * SHA digest, pads the digest according to the PKCS#1 (v1.5) scheme, and
   * encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_224</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1</code> constants
   * respectively. <p> Note: <ul> <li><em> The encryption block(EB) during
   * signing is built as follows:<br> &nbsp; EB = 00 || 01 || PS || 00 || T<br>
   * &nbsp; &nbsp; &nbsp; :: where T is the DER encoding of :<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestInfo ::= SEQUENCE {<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestAlgorithm
   * AlgorithmIdentifier of SHA-224,<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * &nbsp; digest OCTET STRING<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * }<br> &nbsp; &nbsp; &nbsp; :: PS is an octet string of length k-3-||T||
   * with value FF. The length of PS must be at least 8 octets.<br> &nbsp;
   * &nbsp; &nbsp; :: k is the RSA modulus size.<br> DER encoded SHA-224
   * AlgorithmIdentifier = 30 2d 30 0d 06 09 60 86 48 01 65 03 04 02 04 05 00 04
   * 1c.</em><br>
   * </ul>
   */
  public static final byte ALG_RSA_SHA_224_PKCS1 = 39;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_256_PKCS1</code> generates a 32-byte
   * SHA digest, pads the digest according to the PKCS#1 (v1.5) scheme, and
   * encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_256</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1</code> constants
   * respectively. <p> Note: <ul> <li><em> The encryption block(EB) during
   * signing is built as follows:<br> &nbsp; EB = 00 || 01 || PS || 00 || T<br>
   * &nbsp; &nbsp; &nbsp; :: where T is the DER encoding of :<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestInfo ::= SEQUENCE {<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestAlgorithm
   * AlgorithmIdentifier of SHA-256,<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * &nbsp; digest OCTET STRING<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * }<br> &nbsp; &nbsp; &nbsp; :: PS is an octet string of length k-3-||T||
   * with value FF. The length of PS must be at least 8 octets.<br> &nbsp;
   * &nbsp; &nbsp; :: k is the RSA modulus size.<br> DER encoded SHA-256
   * AlgorithmIdentifier = 30 31 30 0d 06 09 60 86 48 01 65 03 04 02 01 05 00
   * 04 20.</em><br>
   * </ul>
   */
  public static final byte ALG_RSA_SHA_256_PKCS1 = 40;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_384_PKCS1</code> generates a 48-byte
   * SHA digest, pads the digest according to the PKCS#1 (v1.5) scheme, and
   * encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_384</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1</code> constants
   * respectively. <p> Note: <ul> <li><em> The encryption block(EB) during
   * signing is built as follows:<br> &nbsp; EB = 00 || 01 || PS || 00 || T<br>
   * &nbsp; &nbsp; &nbsp; :: where T is the DER encoding of :<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestInfo ::= SEQUENCE {<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestAlgorithm
   * AlgorithmIdentifier of SHA-384,<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * &nbsp; digest OCTET STRING<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * }<br> &nbsp; &nbsp; &nbsp; :: PS is an octet string of length k-3-||T||
   * with value FF. The length of PS must be at least 8 octets.<br> &nbsp;
   * &nbsp; &nbsp; :: k is the RSA modulus size.<br> DER encoded SHA-384
   * AlgorithmIdentifier = 30 41 30 0d 06 09 60 86 48 01 65 03 04 02 02 05 00
   * 04 30.</em><br>
   * </ul>
   */
  public static final byte ALG_RSA_SHA_384_PKCS1 = 41;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_512_PKCS1</code> generates a 64-byte
   * SHA digest, pads the digest according to the PKCS#1 (v1.5) scheme, and
   * encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_512</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1</code> constants
   * respectively. <p> Note: <ul> <li><em> The encryption block(EB) during
   * signing is built as follows:<br> &nbsp; EB = 00 || 01 || PS || 00 || T<br>
   * &nbsp; &nbsp; &nbsp; :: where T is the DER encoding of :<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestInfo ::= SEQUENCE {<br>
   * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; digestAlgorithm
   * AlgorithmIdentifier of SHA-512,<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * &nbsp; digest OCTET STRING<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   * }<br> &nbsp; &nbsp; &nbsp; :: PS is an octet string of length k-3-||T||
   * with value FF. The length of PS must be at least 8 octets.<br> &nbsp;
   * &nbsp; &nbsp; :: k is the RSA modulus size.<br> DER encoded SHA-512
   * AlgorithmIdentifier = 30 51 30 0d 06 09 60 86 48 01 65 03 04 02 03 05 00
   * 04 40.</em><br>
   * </ul>
   */
  public static final byte ALG_RSA_SHA_512_PKCS1 = 42;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_224_PKCS1_PSS</code> generates a
   * 28-byte SHA-224 digest, pads it according to the PKCS#1-PSS scheme (IEEE
   * 1363-2000), and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_224</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1_PSS</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_224_PKCS1_PSS = 43;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_256_PKCS1_PSS</code> generates a
   * 32-byte SHA-256 digest, pads it according to the PKCS#1-PSS scheme (IEEE
   * 1363-2000), and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_256</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1_PSS</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_256_PKCS1_PSS = 44;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_384_PKCS1_PSS</code> generates a
   * 48-byte SHA-384 digest, pads it according to the PKCS#1-PSS scheme (IEEE
   * 1363-2000), and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_384</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1_PSS</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_384_PKCS1_PSS = 45;
  /**
   * Signature algorithm <code>ALG_RSA_SHA_512_PKCS1_PSS</code> generates a
   * 64-byte SHA-512 digest, pads it according to the PKCS#1-PSS scheme (IEEE
   * 1363-2000), and encrypts it using RSA.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_SHA_512</code>,
   * <code>SIG_CIPHER_RSA</code>, <code>Cipher.PAD_PKCS1_PSS</code> constants
   * respectively.
   */
  public static final byte ALG_RSA_SHA_512_PKCS1_PSS = 46;

  /**
   * Signature algorithm <code>ALG_DES_MAC4_ISO9797_1_M1_ALG3</code>
   * generates a 4-byte MAC using triple DES with a 2-key DES3 key according
   * to ISO9797-1 MAC
   * algorithm 3 with method 1, where input data is
   * padded using method 1 and the data is processed as described in MAC
   * Algorithm 3 of the ISO 9797-1 specification. The left key block of the
   * triple DES key is used as a single DES key(K) and the right key block of
   * the triple DES key is used as a single DES Key (K') during MAC
   * processing. The final result is truncated to 4 bytes as described in
   * ISO9797-1.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC4</code>, <code>Cipher.PAD_ISO9797_1_M1_ALG3</code>
   * constants respectively. <p> Note: <ul> <li><em>This algorithm is a triple
   * DES algorithm and requires a 16 byte key (2-key DES3 key)</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC4_ISO9797_1_M1_ALG3 = 47;

  /**
   * Signature algorithm <code>ALG_DES_MAC8_ISO9797_1_M1_ALG3</code>
   * generates an 8-byte MAC using triple DES with a 2-key DES3 key according
   * to ISO9797-1 MAC
   * algorithm 3 with method 1, where input data is
   * padded using method 1 and the data is processed as described in MAC
   * Algorithm 3 of the ISO 9797-1 specification. The left key block of the
   * triple DES key is used as a single DES key(K) and the right key block of
   * the triple DES key is used as a single DES Key (K') during MAC
   * processing. The final result is truncated to 8 bytes as described in
   * ISO9797-1.
   * <p>
   * To request this algorithm using the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method use the <code>MessageDigest.ALG_NULL</code>,
   * <code>SIG_CIPHER_DES_MAC8</code>, <code>Cipher.PAD_ISO9797_1_M1_ALG3</code>
   * constants respectively. <p> Note: <ul> <li><em>This algorithm is a triple
   * DES algorithm and requires a 16 byte key (2-key DES3 key)</em>
   * </ul>
   */
  public static final byte ALG_DES_MAC8_ISO9797_1_M1_ALG3 = 48;

  // Cipher algorithm choices for the Signature scheme in the
  // getInstance(byte, byte, byte, boolean) method

  /**
   * Cipher algorithm <code>SIG_CIPHER_DES_MAC4</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm generates a 4-byte MAC
   * (most significant 4 bytes of encrypted block) using DES in CBC mode or
   * triple DES in outer CBC mode.
   */
  public static final byte SIG_CIPHER_DES_MAC4 = 1;
  /**
   * Cipher algorithm <code>SIG_CIPHER_DES_MAC8</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm generates a 8-byte MAC
   * (most significant 8 bytes of encrypted block) using DES in CBC mode or
   * triple DES in outer CBC mode.
   */
  public static final byte SIG_CIPHER_DES_MAC8 = 2;
  /**
   * Cipher algorithm <code>SIG_CIPHER_RSA</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm uses the RSA cipher.
   */
  public static final byte SIG_CIPHER_RSA = 3;
  /**
   * Cipher algorithm <code>SIG_CIPHER_DSA</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm uses the DSA cipher. The
   * signature is encoded as an ASN.1 sequence of two INTEGER values, r and s,
   * in that order: SEQUENCE ::= { r INTEGER, s INTEGER }
   */
  public static final byte SIG_CIPHER_DSA = 4;
  /**
   * Cipher algorithm <code>SIG_CIPHER_ECDSA</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm uses the ECDSA cipher. <p>
   * The signature is
   * encoded as an ASN.1 sequence of two INTEGER values, r and s, in that
   * order: SEQUENCE ::= { r INTEGER, s INTEGER }
   * <p>
   * Note:
   * <ul>
   * <li><em>This algorithm truncates the message digest to the length of the EC
   * key for EC key lengths less than the length of the message digest in
   * accordance with section 4.1 "Elliptic Curve Digit Signature Algorithm" of
   * the "SEC 1: Elliptic Curve Cryptography" specification</em>
   * </ul>
   */
  public static final byte SIG_CIPHER_ECDSA = 5;

  /**
   * Cipher algorithm <code>SIG_CIPHER_ECDSA_PLAIN</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm uses the ECDSA cipher. <p>
   * The signature is
   * encoded as an octet string R || S as specified in section 5.2.1 of BSI
   * TR-03111. <p> Note: <ul> <li><em>This algorithm truncates the message
   * digest to the length of the EC key for EC key lengths less than the length
   * of the message digest in accordance with section 4.1 "Elliptic Curve Digit
   * Signature Algorithm" of the "SEC 1: Elliptic Curve Cryptography"
   * specification</em>
   * </ul>
   */
  public static final byte SIG_CIPHER_ECDSA_PLAIN = 9;

  /**
   * Cipher algorithm <code>SIG_CIPHER_AES_MAC128</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm generates a 16-byte MAC
   * using AES with block size 128 in CBC mode.
   */
  public static final byte SIG_CIPHER_AES_MAC128 = 6;
  /**
   * Cipher algorithm <code>SIG_CIPHER_AES_CMAC128</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm generates a 16-byte
   * Cipher-based MAC (CMAC) using AES with block size 128 in CBC mode.
   */
  public static final byte SIG_CIPHER_AES_CMAC128 = 10;
  /**
   * Cipher algorithm <code>SIG_CIPHER_HMAC</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm generates an HMAC following
   * the steps found in RFC: 2104 using the specified hashing algorithm.
   */
  public static final byte SIG_CIPHER_HMAC = 7;
  /**
   * Cipher algorithm <code>SIG_CIPHER_KOREAN_SEED_MAC</code> choice for the
   * <code>cipherAlgorithm</code> parameter of the
   * {@link #getInstance(byte, byte, byte, boolean) getInstance(byte, byte,
   * byte, boolean)} method. The signature algorithm generates a generates a
   * 16-byte MAC using Korean SEED in CBC mode.
   */
  public static final byte SIG_CIPHER_KOREAN_SEED_MAC = 8;

  // mode options
  /**
   * Used in <code>init()</code> methods to indicate signature sign mode.
   */
  public static final byte MODE_SIGN = 1;

  /**
   * Used in <code>init()</code> methods to indicate signature verify mode.
   */
  public static final byte MODE_VERIFY = 2;

  /**
   * Protected Constructor
   */
  protected Signature() {}

  /**
   * Creates a <code>Signature</code> object instance of the selected
   * algorithm.
   *
   * @param algorithm
   *            the desired Signature algorithm. Valid codes listed in
   *            <code>ALG_*</code> constants above e.g.
   *            {@link #ALG_DES_MAC4_NOPAD ALG_DES_MAC4_NOPAD}.
   * @param externalAccess
   *            <code>true</code> indicates that the instance will be shared
   *            among multiple applet instances and that the
   *            <code>Signature</code> instance will also be accessed (via a
   *            <code>Shareable</code> interface) when the owner of the
   *            <code>Signature</code> instance is not the currently
   *            selected applet. If <code>true</code> the implementation
   *            must not allocate CLEAR_ON_DESELECT transient space for
   *            internal data.
   * @return the <code>Signature</code> object instance of the requested
   *         algorithm
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm or shared access mode is not
   *                supported.
   *                </ul>
   * @see #getInstance(byte, byte, byte, boolean)
   * @see #getAlgorithm
   */
  public static final Signature getInstance(byte algorithm,
                                            boolean externalAccess)
      throws CryptoException {
    Signature instance = null;

    if (externalAccess) {
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    switch (algorithm) {
    default:
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    return null;
  }

  /**
   * Creates a <code>Signature</code> object instance with the selected
   * message digest algorithm, cipher algorithm and padding algorithm.
   * <p>
   * Note:
   * <ul>
   * <li><em>Note that the cipher algorithms listed in the Signature class
   * include some choices not available directly as a Cipher - e.g
   * <code>DSA</code>.</em> <li><em>When there is no discrete message digest
   * algorithm, use the <code>MessageDigest.ALG_NULL</code> choice for the
   * message digest algorithm.</em> <li><em>When the padding algorithm is built
   * into the cipher algorithm use the <code>PAD_NULL</code> choice for the
   * padding algorithm.</em>
   * </ul>
   *
   * @param messageDigestAlgorithm
   *            the desired message digest algorithm. Valid codes listed in
   *            <code>ALG_*</code> constants in the MessageDigest class e.g.
   *            {@link javacard.security.MessageDigest#ALG_NULL ALG_NULL}.
   * @param cipherAlgorithm
   *            the desired cipher algorithm. Valid codes listed in
   *            <code>SIG_CIPHER_*</code> constants in this class e.g.
   *            {@link #SIG_CIPHER_DES_MAC4 SIG_CIPHER_DES_MAC4}.
   * @param paddingAlgorithm
   *            the desired padding algorithm. Valid codes listed in
   *            <code>PAD_*</code> constants in the Cipher class e.g.
   *            {@link javacardx.crypto.Cipher#PAD_NULL PAD_NULL}.
   * @param externalAccess
   *            <code>true</code> indicates that the instance will be shared
   *            among multiple applet instances and that the
   *            <code>Signature</code> instance will also be accessed (via a
   *            <code>Shareable</code> interface) when the owner of the
   *            <code>Signature</code> instance is not the currently
   *            selected applet. If <code>true</code> the implementation
   *            must not allocate CLEAR_ON_DESELECT transient space for
   *            internal data.
   * @return the <code>Signature</code> object instance of the requested
   *         algorithm
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested message digest algorithm or cipher
   *                algorithm or padding algorithm or their combination
   *                or the requested shared access mode is not
   *                supported.
   *                </ul>
   * @since 3.0.4
   * @see #getInstance(byte, boolean)
   * @see #getMessageDigestAlgorithm
   * @see #getCipherAlgorithm
   * @see #getPaddingAlgorithm
   */
  public static final Signature getInstance(byte messageDigestAlgorithm,
                                            byte cipherAlgorithm,
                                            byte paddingAlgorithm,
                                            boolean externalAccess)
      throws CryptoException {
    Signature instance = null;

    if (externalAccess) {
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);

    return null;
  }

  /**
   * Initializes the <code>Signature</code> object with the appropriate
   * <code>Key</code>. This method should be used for algorithms which do
   * not need initialization parameters or use default parameter values.
   * <p>
   * <code>init()</code> must be used to update the <code>Signature</code>
   * object with a new key. If the <code>Key</code> object is modified after
   * invoking the <code>init()</code> method, the behavior of the
   * <code>update()</code>, <code>sign()</code>, and
   * <code>verify()</code> methods is unspecified.
   * <p>
   * The <code>Key</code> is checked for consistency with the
   * <code>Signature</code> algorithm. For example, the key type must be
   * matched. For elliptic curve algorithms, the key must represent a valid
   * point on the curve's domain parameters. Additional key component/domain
   * parameter strength checks are implementation specific. <p> Note: <ul>
   * <li><em>AES, DES, triple DES, and Korean SEED algorithms in CBC mode will
   * use 0 for initial vector(IV) if this method is used.</em> <li><em>RSA
   * algorithms using the padding scheme PKCS1_PSS will use a default salt
   * length equal to the length of the message digest.</em> <li><em>For optimal
   * performance, when the <code>theKey</code> parameter is a transient key, the
   * implementation should, whenever possible, use transient space for internal
   * storage.</em>
   * </ul>
   *
   * @param theKey
   *            the key object to use for signing or verifying
   * @param theMode
   *            one of <code>MODE_SIGN</code> or <code>MODE_VERIFY</code>
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if
   *                <code>theMode</code> option is an undefined value or if
   *                the <code>Key</code> is inconsistent with
   *                <code>theMode</code> or with the <code>Signature</code>
   *                implementation.
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                <code>theKey</code> instance is uninitialized.
   *                </ul>
   */
  public abstract void init(Key theKey, byte theMode) throws CryptoException;

  /**
   * Initializes the <code>Signature</code> object with the appropriate
   * <code>Key</code> and algorithm specific parameters.
   * <p>
   * <code>init()</code> must be used to update the <code>Signature</code>
   * object with a new key. If the <code>Key</code> object is modified after
   * invoking the <code>init()</code> method, the behavior of the
   * <code>update()</code>, <code>sign()</code>, and
   * <code>verify()</code> methods is unspecified.
   * <p>
   * The <code>Key</code> is checked for consistency with the
   * <code>Signature</code> algorithm. For example, the key type must be
   * matched. For elliptic curve algorithms, the key must represent a valid
   * point on the curve's domain parameters. Additional key component/domain
   * parameter strength checks are implementation specific. <p> Note: <ul>
   * <li><em>DES and triple DES algorithms in CBC mode expect an 8-byte
   * parameter value for the initial vector(IV) in
   * </em><code>bArray</code><em>.</em> <li><em>AES algorithms, except for
   * ALG_AES_CMAC_128, in CBC mode expect a 16-byte parameter value for the
   * initial vector(IV) in </em><code>bArray</code><em>.</em> <li><em>Korean
   * SEED algorithms in CBC mode expect a 16-byte parameter value for the
   * initial vector(IV) in </em><code>bArray</code><em>.</em> <li><em>ECDSA, DSA
   * and HMAC algorithms throw
   * </em><code>CryptoException.ILLEGAL_VALUE</code><em>. For RSA algorithms
   * using the padding scheme PKCS1_PSS expect a two-byte parameter value (b1
   * b2) for the salt length in </em><code>bArray</code><em>. This two-byte
   * parameter represents a short value where b1 is the first byte (high order
   * byte) and b2 is the second byte (low order byte). For all other RSA
   * algorithms CryptoException.ILLEGAL_VALUE is thrown.</em> <li><em>For
   * optimal performance, when the <code>theKey</code> parameter is a transient
   * key, the implementation should, whenever possible, use transient space for
   * internal storage.</em>
   * </ul>
   *
   * @param theKey
   *            the key object to use for signing
   * @param theMode
   *            one of <code>MODE_SIGN</code> or <code>MODE_VERIFY</code>
   * @param bArray
   *            byte array containing algorithm specific initialization
   *            information
   * @param bOff
   *            offset within <code>bArray</code> where the algorithm
   *            specific data begins
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
   *                <code>Key</code> is inconsistent with
   *                <code>theMode</code> or with the <code>Signature</code>
   *                implementation.
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                <code>theKey</code> instance is uninitialized.
   *                </ul>
   */
  public abstract void init(Key theKey, byte theMode, byte[] bArray, short bOff,
                            short bLen) throws CryptoException;

  /**
   * This method initializes the starting hash value in place of the default
   * value used by the <code>Signature</code> class. The starting
   * hash value represents the previously computed hash (using the same
   * algorithm) of the first part of the message. The remaining bytes of the
   * message must be presented to this <code>Signature</code>
   * object via the <code>update</code> and <code>sign</code> or
   * <code>verify</code> methods
   * to generate or verify the signature.
   * <p>
   * Note:
   * <ul>
   * <li><em>The maximum allowed value of the byte length of the first part of
   * the message is algorithm specific</em> <li><em>This method throws an
   * exception if the underlying signature algorithm does not compute a distinct
   * message digest value prior to applying cryptographic primitives. These
   * algorithms throw exception - DES, triple DES, AES, HMAC and KOREAN
   * SEED.</em>
   * </ul>
   *
   * @param initialDigestBuf
   *            input buffer containing the starting hash value representing
   *            the previously computed hash (using the same algorithm) of
   *            first part of the message
   * @param initialDigestOffset
   *            offset into <code>initialDigestBuf</code> array where the
   *            starting digest value data begins
   * @param initialDigestLength
   *            the length of data in <code>initialDigestBuf</code> array.
   * @param digestedMsgLenBuf
   *            the byte array containing the number of bytes in the first
   *            part of the message that has previously been hashed to obtain
   *            the specified starting digest value
   * @param digestedMsgLenOffset
   *            the offset within <code>digestedMsgLenBuf</code> where the
   *            digested length begins(the bytes starting at this offset for
   *            <code>digestedMsgLenLength</code> bytes are concatenated to
   *            form the actual digested message length value)
   * @param digestedMsgLenLength
   *            byte length of the digested length
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                parameter <code>initialDigestLength</code> is not equal
   *                to the intermediate hash value size of the algorithm
   *                or if the number of bytes
   *                in the first part of the message that has previously been
   *                hashed is 0 or not a multiple of the algorithm's block
   *                size or greater than the maximum length supported by the
   *                algorithm (see <code>ALG_*</code> algorithm descriptions
   *                {@link javacard.security.MessageDigest#ALG_SHA
   * MessageDigest.ALG_SHA}). <li><code>CryptoException.ILLEGAL_USE</code> if
   * the Signature algorithm does not compute a distinct message digest value
   * prior to applying cryptographic primitives or if this
   * <code>Signature</code> algorithm includes message recovery functionality.
   *                </ul>
   * @since 3.0.4
   */
  public abstract void
  setInitialDigest(byte[] initialDigestBuf, short initialDigestOffset,
                   short initialDigestLength, byte[] digestedMsgLenBuf,
                   short digestedMsgLenOffset, short digestedMsgLenLength)
      throws CryptoException;

  /**
   * Gets the Signature algorithm. Pre-defined codes listed in
   * <code>ALG_*</code> constants above, for example, {@link
   * #ALG_DES_MAC4_NOPAD}.
   *
   * @return the algorithm code defined above; if the algorithm is not one of
   *     the pre-defined
   *         algorithms, <code>0</code> is returned.
   */
  public abstract byte getAlgorithm();

  /**
   * Gets the message digest algorithm. Pre-defined codes listed in
   * {@code ALG_*} constants in the MessageDigest class e.g.
   * {@link javacard.security.MessageDigest#ALG_NULL ALG_NULL}.
   *
   * @return the message digest algorithm code defined in the
   * {@code MessageDigest} class; if the algorithm is not one of the pre-defined
   * algorithms, {@code 0} is returned.
   *
   * @since 3.0.5
   */
  public abstract byte getMessageDigestAlgorithm();

  /**
   * Gets the cipher algorithm. Pre-defined codes listed in {@code SIG_CIPHER_*}
   * constants in this class e.g.
   * {@link #SIG_CIPHER_DES_MAC4 SIG_CIPHER_DES_MAC4}.
   *
   * @return the cipher algorithm code defined above; if the algorithm is not
   * one of the pre-defined algorithms, <code>0</code> is returned.
   *
   * @since 3.0.5
   */
  public abstract byte getCipherAlgorithm();

  /**
   * Gets the padding algorithm. Pre-defined codes listed in {@code PAD_*}
   * constants in the {@code Cipher} class e.g.
   * {@link javacardx.crypto.Cipher#PAD_NULL PAD_NULL}.
   *
   * @return the padding algorithm code defined in the {@code Cipher} class; if
   * the algorithm is not one of the pre-defined algorithms, {@code 0} is
   * returned.
   *
   * @since 3.0.5
   */
  public abstract byte getPaddingAlgorithm();

  /**
   * Returns the byte length of the signature data.
   *
   * @return the byte length of the signature data
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Signature</code> object is not initialized.
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                </ul>
   */
  public abstract short getLength() throws CryptoException;

  /**
   * Accumulates a signature of the input data. This method requires temporary
   * storage of intermediate results. In addition, if the input data length is
   * not block aligned (multiple of block size) then additional internal
   * storage may be allocated at this time to store a partial input data
   * block. This may result in additional resource consumption and/or slow
   * performance. This method should only be used if all the input data
   * required for signing/verifying is not available in one byte array. If all
   * of the input data required for signing/verifying is located in a single
   * byte array, use of the <code>sign()</code> or <code>verify()</code>
   * method is recommended. The <code>sign()</code> or <code>verify()</code>
   * method must be called to complete processing of input data accumulated by
   * one or more calls to the <code>update()</code> method.
   * <p>
   * Note:
   * <ul>
   * <li><em>If <code>inLength</code> is 0 this method does nothing.</em>
   * </ul>
   *
   * @param inBuff
   *            the input buffer of data to be signed/verified
   * @param inOffset
   *            the offset into the input buffer where input data begins
   * @param inLength
   *            the byte length to sign/verify
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Signature</code> object is not initialized.
   *                <li><code>CryptoException.ILLEGAL_USE</code> if the
   *                message value is not supported by the
   *                <code>Signature</code> algorithm
   *                or if a message value consistency check failed.
   *                </ul>
   * @see #sign(byte[], short, short, byte[], short) sign(byte[], short,
   *      short, byte[], short)
   * @see #verify(byte[], short, short, byte[], short, short) verify(byte[],
   *      short, short, byte[], short, short)
   */
  public abstract void update(byte[] inBuff, short inOffset, short inLength)
      throws CryptoException;

  /**
   * Generates the signature of all/last input data.
   * <p>
   * A call to this method also resets this <code>Signature</code> object to
   * the state it was in when previously initialized via a call to
   * <code>init()</code>. That is, the object is reset and available to
   * sign another message. In addition, note that the initial vector(IV) used
   * in AES, DES and Korean SEED algorithms in CBC mode will be reset to 0.
   * <p>
   * Note:
   * <ul>
   * <li><em>AES, DES, triple DES, and Korean SEED algorithms in CBC mode reset
   * the initial vector(IV) to 0. The initial vector(IV) can be re-initialized
   * using the
   * </em><code>init(Key, byte, byte[], short, short)</code><em> method.</em>
   * </ul>
   * <p>
   * The input and output buffer data may overlap.
   * </p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param inBuff
   *            the input buffer of data to be signed
   * @param inOffset
   *            the offset into the input buffer at which to begin signature
   *            generation
   * @param inLength
   *            the byte length to sign
   * @param sigBuff
   *            the output buffer to store signature data
   * @param sigOffset
   *            the offset into sigBuff at which to begin signature data
   * @return number of bytes of signature output in sigBuff
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Signature</code> object is not initialized or
   *                initialized for signature verify mode.
   *                <li><code>CryptoException.ILLEGAL_USE</code> if one of
   *                the following conditions is met:
   *                <ul>
   *                <li>if this <code>Signature</code> algorithm does not
   *                pad the message and the message is not block aligned.
   *                <li>if this <code>Signature</code> algorithm does not
   *                pad the message and no input data has been provided in
   *                <code>inBuff</code> or via the <code>update()</code>
   *                method.
   *                <li>if the message value is not supported by the
   *                <code>Signature</code> algorithm
   *                or if a message value consistency check failed.
   *                <li>if this <code>Signature</code> algorithm includes
   *                message recovery functionality.
   *                </ul>
   *                </ul>
   */
  public abstract short sign(byte[] inBuff, short inOffset, short inLength,
                             byte[] sigBuff, short sigOffset)
      throws CryptoException;

  /**
   * Generates the signature of the precomputed hash data.
   * <p>
   * A call to this method also resets this <code>Signature</code> object to
   * the state it was in when previously initialized via a call to
   * <code>init()</code>. That is, the object is reset and available to
   * sign another precomputed hash.
   * <p>
   * Note:
   * <ul>
   * <li><em>This method throws an exception if the underlying signature
   * algorithm does not compute a distinct message digest value prior to
   * applying cryptographic primitives. These algorithms throw exception - DES,
   * triple DES, AES, HMAC and KOREAN SEED.</em> <li><em>Any data previously
   * accumulated from previous calls to the <code>update</code> method are
   * discarded.</em>
   * </ul>
   * <p>
   * The hash and output buffer data may overlap.
   * </p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param hashBuff
   *            the input buffer of precomputed hash to be signed
   * @param hashOffset
   *            the offset into the buffer where the hash begins
   * @param hashLength
   *            the byte length of the hash
   * @param sigBuff
   *            the output buffer to store signature data
   * @param sigOffset
   *            the offset into sigBuff at which to begin signature data
   * @return number of bytes of signature output in sigBuff
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Signature</code> object is not initialized or
   *                initialized for signature verify mode.
   *                <li><code>CryptoException.ILLEGAL_USE</code> if one of
   *                the following conditions is met:
   *                <ul>
   *                <li>if the <code>hashLength</code> value is not equal
   *                to the length of the algorithm's message digest length.
   *                <li>if this <code>Signature</code> algorithm includes
   *                message recovery functionality.
   *                <li>if the
   *                Signature algorithm does not compute a distinct message
   *                digest value prior to applying cryptographic primitives
   *                </ul>
   *                </ul>
   */
  public abstract short signPreComputedHash(byte[] hashBuff, short hashOffset,
                                            short hashLength, byte[] sigBuff,
                                            short sigOffset)
      throws CryptoException;

  /**
   * Verifies the signature of all/last input data against the passed in
   * signature.
   * A call to this method also resets this <code>Signature</code> object to
   * the state it was in when previously initialized via a call to
   * <code>init()</code>. That is, the object is reset and available to
   * verify another message. In addition, note that the initial vector(IV)
   * used in AES, DES and Korean SEED algorithms in CBC mode will be reset to 0.
   * <p>
   * Note:
   * <ul>
   * <li><em>AES, DES, triple DES, and Korean SEED algorithms in CBC mode reset
   * the initial vector(IV) to 0. The initial vector(IV) can be re-initialized
   * using the
   * </em><code>init(Key, byte, byte[], short, short)</code><em> method.</em>
   * </ul>
   * <p>
   * In addition to returning a {@code boolean} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param inBuff
   *            the input buffer of data to be verified
   * @param inOffset
   *            the offset into the input buffer at which to begin signature
   *            generation
   * @param inLength
   *            the byte length to sign
   * @param sigBuff
   *            the input buffer containing signature data
   * @param sigOffset
   *            the offset into <code>sigBuff</code> where signature data
   *            begins
   * @param sigLength
   *            the byte length of the signature data
   * @return <code>true</code> if the signature verifies, <code>false</code>
   *         otherwise. Note, if <code>sigLength</code> is inconsistent with
   *         this <code>Signature</code> algorithm, <code>false</code> is
   *         returned.
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Signature</code> object is not initialized or
   *                initialized for signature sign mode.
   *                <li><code>CryptoException.ILLEGAL_USE</code> if one of
   *                the following conditions is met:
   *                <ul>
   *                <li>if this <code>Signature</code> algorithm does not
   *                pad the message and the message is not block aligned.
   *                <li>if this <code>Signature</code> algorithm does not
   *                pad the message and no input data has been provided in
   *                <code>inBuff</code> or via the <code>update()</code>
   *                method.
   *                <li>if the message value is not supported by the
   *                <code>Signature</code> algorithm
   *                or if a message value consistency check failed.
   *                <li>if this <code>Signature</code> algorithm includes
   *                message recovery functionality.
   *                </ul>
   *                </ul>
   */
  public abstract boolean verify(byte[] inBuff, short inOffset, short inLength,
                                 byte[] sigBuff, short sigOffset,
                                 short sigLength) throws CryptoException;

  /**
   * Verifies the signature of precomputed hash data.
   * <p>
   * A call to this method also resets this <code>Signature</code> object to
   * the state it was in when previously initialized via a call to
   * <code>init()</code>. That is, the object is reset and available to
   * verify another precomputed hash. In addition, note that the initial
   * vector(IV) used in AES, DES and Korean SEED algorithms in CBC mode will be
   * reset to 0. <p> Note: <ul> <li><em>This method throws an exception if the
   * underlying signature algorithm does not compute a distinct message digest
   * value prior to applying cryptographic primitives. These algorithms throw
   * exception - DES, triple DES, AES, and KOREAN SEED.</em> <li><em>Any data
   * previously accumulated from previous calls to the <code>update</code>
   * method are discarded.</em>
   * </ul>
   * <p>
   * The hash and output buffer data may overlap.
   * </p>
   * In addition to returning a {@code boolean} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param hashBuff
   *            the input buffer of precomputed hash to be verified
   * @param hashOffset
   *            the offset into the buffer where the hash begins
   * @param hashLength
   *            the byte length of the hash
   * @param sigBuff
   *            the input buffer containing signature data
   * @param sigOffset
   *            the offset into <code>sigBuff</code> where signature data
   *            begins
   * @param sigLength
   *            the byte length of the signature data
   * @return <code>true</code> if the signature verifies, <code>false</code>
   *         otherwise. Note, if <code>sigLength</code> is inconsistent with
   *         this <code>Signature</code> algorithm, <code>false</code> is
   *         returned.
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                key not initialized.
   *                <li><code>CryptoException.INVALID_INIT</code> if this
   *                <code>Signature</code> object is not initialized or
   *                initialized for signature sign mode.
   *                <li><code>CryptoException.ILLEGAL_USE</code> if one of
   *                the following conditions is met:
   *                <ul>
   *                <li>if the <code>hashLength</code> value is not equal
   *                to the length of the algorithm's message digest length.
   *                <li>if this <code>Signature</code> algorithm includes
   *                message recovery functionality.
   *                <li>if the
   *                Signature algorithm does not compute a distinct message
   *                digest value prior to applying cryptographic primitives
   *                </ul>
   *                </ul>
   */
  public abstract boolean verifyPreComputedHash(
      byte[] hashBuff, short hashOffset, short hashLength, byte[] sigBuff,
      short sigOffset, short sigLength) throws CryptoException;

  /**
   * The {@code OneShot} class is a specialization of the
   * {@code Signature} class intended to support efficient one-shot signing and
   * verification operations that may avoid persistent memory writes entirely.
   * The {@code OneShot} class uses a delegation model where calls are
   * delegated to an instance of a {@code Signature}-implementing class
   * configured for one-shot use.
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
   * Signature.OneShot sig = null;
   * try {
   *     sig = Signature.OneShot.open(MessageDigest.ALG_SHA,
   * Signature.SIG_CIPHER_RSA, Cipher.PAD_PKCS1); sig.init(someRSAKey,
   * Signature.MODE_SIGN); sig.sign(someInData, (short) 0, (short)
   * someInData.length, sigData, (short) 0); } catch (CryptoException ce) {
   *     // Handle exception
   * } finally {
   *     if (sig != null) {
   *        sig.close();
   *        sig = null;
   *     }
   * }
   * ...
   * </pre>
   *
   * @since 3.0.5
   */
  public static final class OneShot extends Signature {

    /**
     * Opens/acquires a JCRE owned temporary Entry Point Object instance of
     * {@code OneShot} with the selected message digest algorithm, cipher
     * algorithm and padding algorithm.
     * <p>
     * Note:
     * <ul>
     * <li><em>Note that the cipher algorithms listed in the Signature class
     * include some choices not available directly as a Cipher - e.g
     * {@code DSA}.</em>
     * <li><em>When there is no discrete message digest algorithm, use the
     * {@code MessageDigest.ALG_NULL} choice for the message digest
     * algorithm.</em>
     * <li><em>When the padding algorithm is built into the cipher algorithm use
     * the {@code PAD_NULL} choice for the padding algorithm.</em>
     * </ul>
     *
     * @param messageDigestAlgorithm the desired message digest algorithm. Valid
     * codes listed in {@code ALG_*} constants in the MessageDigest class e.g.
     * {@link javacard.security.MessageDigest#ALG_NULL ALG_NULL}.
     * @param cipherAlgorithm the desired cipher algorithm. Valid codes listed
     * in {@code SIG_CIPHER_*} constants in the Signature class e.g.
     * {@link javacard.security.Signature#SIG_CIPHER_DES_MAC4}.
     * @param paddingAlgorithm the desired padding algorithm. Valid codes listed
     * in {@code PAD_*} constants in the Cipher class e.g.
     * {@link javacardx.crypto.Cipher#PAD_NULL PAD_NULL}.
     * @return the {@code OneShot} object instance of the requested
     * algorithm.
     * @throws CryptoException with the following reason codes:
     * <ul>
     * <li>{@code CryptoException.NO_SUCH_ALGORITHM} if the requested message
     * digest algorithm or cipher algorithm or padding algorithm or their
     * combination is not supported.</li>
     * </ul>
     * @throws javacard.framework.SystemException with the following reason
     * codes:
     * <ul>
     * <li>{@code SystemException.NO_RESOURCE} if sufficient resources are not
     * available.</li>
     * </ul>
     */
    public static final OneShot open(byte messageDigestAlgorithm,
                                     byte cipherAlgorithm,
                                     byte paddingAlgorithm)
        throws CryptoException {
      // TODO: Not yet implemented!!!
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
      // TODO: Not yet implemented!!!
    }

    /**
     * Always throws a {@code CryptoException}.This method is not supported by
     * {@code OneShot}.
     *
     * @param inBuff the input buffer of data to be signed/verified.
     * @param inOffset the offset into the input buffer where input data begins.
     * @param inLength the byte length to sign/verify.
     * @throws CryptoException with the following reason codes:
     * <ul>
     * <li>{@code CryptoException.ILLEGAL_USE} always.</li>
     * </ul>
     */
    @Override
    public final void update(byte[] inBuff, short inOffset, short inLength)
        throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.ILLEGAL_USE);
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public void init(Key theKey, byte theMode) throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
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
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public void
    setInitialDigest(byte[] initialDigestBuf, short initialDigestOffset,
                     short initialDigestLength, byte[] digestedMsgLenBuf,
                     short digestedMsgLenOffset, short digestedMsgLenLength)
        throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public byte getAlgorithm() {
      // TODO: Not yet implemented!!!
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public byte getMessageDigestAlgorithm() {
      // TODO: Not yet implemented!!!
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
      // TODO: Not yet implemented!!!
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
      // TODO: Not yet implemented!!!
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public short getLength() throws CryptoException {
      // TODO: Not yet implemented!!!
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public short sign(byte[] inBuff, short inOffset, short inLength,
                      byte[] sigBuff, short sigOffset) throws CryptoException {
      // TODO: Not yet implemented!!!
      return 0;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public short signPreComputedHash(byte[] hashBuff, short hashOff,
                                     short hashLength, byte[] sigBuff,
                                     short sigOffset) throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      return -1;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public boolean verify(byte[] inBuff, short inOffset, short inLength,
                          byte[] sigBuff, short sigOffset, short sigLength)
        throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      return false;
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public boolean verifyPreComputedHash(byte[] hashBuff, short hashOff,
                                         short hashLength, byte[] sigBuff,
                                         short sigOffset, short sigLength)
        throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      return false;
    }
  }
}
