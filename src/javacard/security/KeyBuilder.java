/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

import javacard.framework.JCSystem;

/**
 * The <code>KeyBuilder</code> class is a key object factory.
 */
public class KeyBuilder {

  /**
   * <code>Key</code> object which implements interface type
   * <code>DESKey</code> with CLEAR_ON_RESET transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_DES_TRANSIENT_RESET = 1;

  /**
   * <code>Key</code> object which implements interface type
   * <code>DESKey</code> with CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on, card reset and applet deselection.
   */
  public static final byte TYPE_DES_TRANSIENT_DESELECT = 2;

  /**
   * <code>Key</code> object which implements interface type
   * <code>DESKey</code> with persistent key data.
   */
  public static final byte TYPE_DES = 3;

  /**
   * <code>Key</code> object which implements interface type
   * <code>RSAPublicKey</code>.
   */
  public static final byte TYPE_RSA_PUBLIC = 4;

  /**
   * <code>Key</code> object which implements interface type
   * <code>RSAPrivateKey</code> which uses modulus/exponent form.
   */
  public static final byte TYPE_RSA_PRIVATE = 5;

  /**
   * <code>Key</code> object which implements interface type
   * <code>RSAPrivateCrtKey</code> which uses Chinese Remainder Theorem.
   */
  public static final byte TYPE_RSA_CRT_PRIVATE = 6;

  /**
   * <code>Key</code> object which implements the interface type
   * <code>DSAPublicKey</code> for the DSA algorithm.
   */
  public static final byte TYPE_DSA_PUBLIC = 7;

  /**
   * <code>Key</code> object which implements the interface type
   * <code>DSAPrivateKey</code> for the DSA algorithm.
   */
  public static final byte TYPE_DSA_PRIVATE = 8;

  /**
   * Key object which implements the interface type <code>ECPublicKey</code>
   * for EC operations over fields of characteristic 2 with polynomial basis.
   */
  public static final byte TYPE_EC_F2M_PUBLIC = 9;

  /**
   * Key object which implements the interface type <code>ECPrivateKey</code>
   * for EC operations over fields of characteristic 2 with polynomial basis.
   */
  public static final byte TYPE_EC_F2M_PRIVATE = 10;

  /**
   * Key object which implements the interface type <code>ECPublicKey</code>
   * for EC operations over large prime fields.
   */
  public static final byte TYPE_EC_FP_PUBLIC = 11;

  /**
   * Key object which implements the interface type <code>ECPrivateKey</code>
   * for EC operations over large prime fields.
   */
  public static final byte TYPE_EC_FP_PRIVATE = 12;

  /**
   * <code>Key</code> object which implements interface type
   * <code>AESKey</code> with CLEAR_ON_RESET transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_AES_TRANSIENT_RESET = 13;

  /**
   * <code>Key</code> object which implements interface type
   * <code>AESKey</code> with CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on, card reset and applet deselection.
   */
  public static final byte TYPE_AES_TRANSIENT_DESELECT = 14;

  /**
   * <code>Key</code> object which implements interface type
   * <code>AESKey</code> with persistent key data.
   */
  public static final byte TYPE_AES = 15;

  /**
   * <code>Key</code> object which implements interface type
   * <code>KoreanSEEDKey</code> with CLEAR_ON_RESET transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_KOREAN_SEED_TRANSIENT_RESET = 16;

  /**
   * <code>Key</code> object which implements interface type
   * <code>KoreanSEEDKey</code> with CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_KOREAN_SEED_TRANSIENT_DESELECT = 17;

  /**
   * <code>Key</code> object which implements interface type
   * <code>KoreanSEEDKey</code> with persistent key data.
   */
  public static final byte TYPE_KOREAN_SEED = 18;
  /**
   * <code>Key</code> object which implements interface type
   * <code>HMACKey</code> with CLEAR_ON_RESET transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset. Note, there is no
   * length constant associated with HMAC, since the specification states that
   * the key can have any length.
   */
  public static final byte TYPE_HMAC_TRANSIENT_RESET = 19;

  /**
   * <code>Key</code> object which implements interface type
   * <code>HMACKey</code> with CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_HMAC_TRANSIENT_DESELECT = 20;

  /**
   * <code>Key</code> object which implements interface type
   * <code>HMACKey</code> with persistent key data.
   */
  public static final byte TYPE_HMAC = 21;

  /**
   * <code>Key</code> object which implements interface type
   * <code>RSAPrivateKey</code> which uses modulus/exponent form, with
   * CLEAR_ON_RESET transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_RSA_PRIVATE_TRANSIENT_RESET = 22;

  /**
   * <code>Key</code> object which implements interface type
   * <code>RSAPrivateKey</code> which uses modulus/exponent form, with
   * CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_RSA_PRIVATE_TRANSIENT_DESELECT = 23;

  /**
   * <code>Key</code> object which implements interface type
   * <code>RSAPrivateCrtKey</code> which uses Chinese Remainder Theorem,
   * with CLEAR_ON_RESET transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_RSA_CRT_PRIVATE_TRANSIENT_RESET = 24;

  /**
   * <code>Key</code> object which implements interface type
   * <code>RSAPrivateCrtKey</code> which uses Chinese Remainder Theorem,
   * with CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_RSA_CRT_PRIVATE_TRANSIENT_DESELECT = 25;

  /**
   * <code>Key</code> object which implements the interface type
   * <code>DSAPrivateKey</code> for the DSA algorithm, with CLEAR_ON_RESET
   * transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_DSA_PRIVATE_TRANSIENT_RESET = 26;

  /**
   * <code>Key</code> object which implements the interface type
   * <code>DSAPrivateKey</code> for the DSA algorithm, with
   * CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_DSA_PRIVATE_TRANSIENT_DESELECT = 27;

  /**
   * Key object which implements the interface type <code>ECPrivateKey</code>
   * for EC operations over fields of characteristic 2 with polynomial basis,
   * with CLEAR_ON_RESET transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_EC_F2M_PRIVATE_TRANSIENT_RESET = 28;

  /**
   * Key object which implements the interface type <code>ECPrivateKey</code>
   * for EC operations over fields of characteristic 2 with polynomial basis,
   * with CLEAR_ON_DESELECT transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_EC_F2M_PRIVATE_TRANSIENT_DESELECT = 29;

  /**
   * Key object which implements the interface type <code>ECPrivateKey</code>
   * for EC operations over large prime fields, with CLEAR_ON_RESET transient
   * key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_EC_FP_PRIVATE_TRANSIENT_RESET = 30;

  /**
   * Key object which implements the interface type <code>ECPrivateKey</code>
   * for EC operations over large prime fields, with CLEAR_ON_DESELECT
   * transient key data.
   * <p>
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on power on or card reset.
   */
  public static final byte TYPE_EC_FP_PRIVATE_TRANSIENT_DESELECT = 31;

  /**
   * Key object which implements the interface type <code>DHPublicKey</code>
   * for DH operations.
   */
  public static final byte TYPE_DH_PUBLIC = 32;

  /**
   * Key object which implements the interface type <code>DHPublicKey</code>
   * for DH operations, .

   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on applet deselection, card power on or card reset.
   */
  public static final byte TYPE_DH_PUBLIC_TRANSIENT_DESELECT = 33;

  /**
   * Key object which implements the interface type <code>DHPublicKey</code>
   * for DH operations.
   *
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on card power on or card reset.
   */
  public static final byte TYPE_DH_PUBLIC_TRANSIENT_RESET = 34;

  /**
   * Key object which implements the interface type <code>DHPrivateKey</code>
   * for DH operations.
   */
  public static final byte TYPE_DH_PRIVATE = 35;

  /**
   * Key object which implements the interface type <code>DHPrivateKey</code>
   * for DH operations, .

   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on applet deselection, card power on or card reset.
   */
  public static final byte TYPE_DH_PRIVATE_TRANSIENT_DESELECT = 36;

  /**
   * Key object which implements the interface type <code>DHPrivateKey</code>
   * for DH operations.
   *
   * This <code>Key</code> object implicitly performs a
   * <code>clearKey()</code> on card power on or card reset.
   */
  public static final byte TYPE_DH_PRIVATE_TRANSIENT_RESET = 37;

  // algorithmic key type choices for the Key instance in the
  // buildKey(byte, byte, short, boolean) method

  /**
   * Algorithmic key type <code>ALG_TYPE_DES</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>DESKey</code>.
   */
  public static final byte ALG_TYPE_DES = 1;
  /**
   * Algorithmic key type <code>ALG_TYPE_AES</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>AESKey</code>.
   */
  public static final byte ALG_TYPE_AES = 2;
  /**
   * Algorithmic key type <code>ALG_TYPE_DSA_PUBLIC</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>DSAPublicKey</code>.
   */
  public static final byte ALG_TYPE_DSA_PUBLIC = 3;
  /**
   * Algorithmic key type <code>ALG_TYPE_DSA_PRIVATE</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>DSAPrivateKey</code>.
   */
  public static final byte ALG_TYPE_DSA_PRIVATE = 4;
  /**
   * Algorithmic key type <code>ALG_TYPE_EC_F2M_PUBLIC</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>ECPublicKey</code>.
   */
  public static final byte ALG_TYPE_EC_F2M_PUBLIC = 5;
  /**
   * Algorithmic key type <code>ALG_TYPE_EC_F2M_PRIVATE</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>ECPrivateKey</code>.
   */
  public static final byte ALG_TYPE_EC_F2M_PRIVATE = 6;
  /**
   * Algorithmic key type <code>ALG_TYPE_EC_FP_PUBLIC</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>ECPublicKey</code>.
   */
  public static final byte ALG_TYPE_EC_FP_PUBLIC = 7;
  /**
   * Algorithmic key type <code>ALG_TYPE_EC_FP_PRIVATE</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>ECPrivateKey</code>.
   */
  public static final byte ALG_TYPE_EC_FP_PRIVATE = 8;
  /**
   * Algorithmic key type <code>ALG_TYPE_HMAC</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>HMACKey</code>.
   */
  public static final byte ALG_TYPE_HMAC = 9;
  /**
   * Algorithmic key type <code>ALG_TYPE_KOREAN_SEED</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>KoreanSEEDKey</code>.
   */
  public static final byte ALG_TYPE_KOREAN_SEED = 10;
  /**
   * Algorithmic key type <code>ALG_TYPE_RSA_PUBLIC</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>RSAPublicKey</code>.
   */
  public static final byte ALG_TYPE_RSA_PUBLIC = 11;
  /**
   * Algorithmic key type <code>ALG_TYPE_RSA_PRIVATE</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>RSAPrivateKey</code>.
   */
  public static final byte ALG_TYPE_RSA_PRIVATE = 12;
  /**
   * Algorithmic key type <code>ALG_TYPE_RSA_CRT_PRIVATE</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>RSAPrivateCrtKey</code>.
   */
  public static final byte ALG_TYPE_RSA_CRT_PRIVATE = 13;
  /**
   * Algorithmic key type <code>ALG_TYPE_DH_PUBLIC</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>DSAPublicKey</code>.
   */
  public static final byte ALG_TYPE_DH_PUBLIC = 14;
  /**
   * Algorithmic key type <code>ALG_TYPE_DH_PRIVATE</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface type
   * <code>DSAPrivateKey</code>.
   */
  public static final byte ALG_TYPE_DH_PRIVATE = 15;

  /**
   * Algorithmic key type <code>ALG_TYPE_EC_F2M_PARAMETERS</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface types
   * <code>ECKey</code> and <code>Key</code>.
   */
  public static final byte ALG_TYPE_EC_F2M_PARAMETERS = 16;
  /**
   * Algorithmic key type <code>ALG_TYPE_EC_FP_PARAMETERS</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface types
   * <code>ECKey</code> and <code>Key</code>.
   */
  public static final byte ALG_TYPE_EC_FP_PARAMETERS = 17;
  /**
   * Algorithmic key type <code>ALG_TYPE_DSA_PARAMETERS</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface types
   * <code>DSAKey</code> and <code>Key</code>.
   */
  public static final byte ALG_TYPE_DSA_PARAMETERS = 18;

  /**
   * Algorithmic key type <code>ALG_TYPE_DH_PARAMETERS</code> choice for the
   * <code>algorithmicKeyType</code> parameter of the
   * {@link #buildKey(byte, byte, short, boolean) buildKey(byte, byte, short,
   * boolean)} method. The key instance created implements the interface types
   * <code>DHKey</code> and <code>Key</code>.
   */
  public static final byte ALG_TYPE_DH_PARAMETERS = 19;

  // keyLength parameter options
  /**
   * DES Key Length <code>LENGTH_DES</code> = 64.
   */
  public static final short LENGTH_DES = 64;

  /**
   * DES Key Length <code>LENGTH_DES3_2KEY</code> for triple DES algorithm =
   * 128.
   */
  public static final short LENGTH_DES3_2KEY = 128;

  /**
   * DES Key Length <code>LENGTH_DES3_3KEY</code> for triple DES algorithm =
   * 192.
   */
  public static final short LENGTH_DES3_3KEY = 192;

  /**
   * RSA Key Length <code>LENGTH_RSA_512</code> = 512.
   */
  public static final short LENGTH_RSA_512 = (short)512;

  /**
   * RSA Key Length <code>LENGTH_RSA_736</code> = 736.
   */
  public static final short LENGTH_RSA_736 = (short)736;

  /**
   * RSA Key Length <code>LENGTH_RSA_768</code> = 768.
   */
  public static final short LENGTH_RSA_768 = (short)768;

  /**
   * RSA Key Length <code>LENGTH_RSA_896</code> = 896.
   */
  public static final short LENGTH_RSA_896 = (short)896;

  /**
   * RSA Key Length <code>LENGTH_RSA_1024</code> = 1024.
   */
  public static final short LENGTH_RSA_1024 = (short)1024;

  /**
   * RSA Key Length <code>LENGTH_RSA_1280</code> = 1280.
   */
  public static final short LENGTH_RSA_1280 = (short)1280;

  /**
   * RSA Key Length <code>LENGTH_RSA_1536</code> = 1536.
   */
  public static final short LENGTH_RSA_1536 = (short)1536;

  /**
   * RSA Key Length <code>LENGTH_RSA_1984</code> = 1984.
   */
  public static final short LENGTH_RSA_1984 = (short)1984;

  /**
   * RSA Key Length <code>LENGTH_RSA_2048</code> = 2048.
   */
  public static final short LENGTH_RSA_2048 = (short)2048;

  /**
   * RSA Key Length <code>LENGTH_RSA_3072</code> = 3072.
   * @since 3.0.5
   */
  public static final short LENGTH_RSA_3072 = (short)3072;

  /**
   * RSA Key Length <code>LENGTH_RSA_4096</code> = 4096.
   */
  public static final short LENGTH_RSA_4096 = (short)4096;

  /**
   * DSA Key Length <code>LENGTH_DSA_512</code> = 512.
   */
  public static final short LENGTH_DSA_512 = (short)512;

  /**
   * DSA Key Length <code>LENGTH_DSA_768</code> = 768.
   */
  public static final short LENGTH_DSA_768 = (short)768;

  /**
   * DSA Key Length <code>LENGTH_DSA_1024</code> = 1024.
   */
  public static final short LENGTH_DSA_1024 = (short)1024;

  /**
   * EC Key Length <code>LENGTH_EC_FP_112</code> = 112.
   */
  public static final short LENGTH_EC_FP_112 = 112;

  /**
   * EC Key Length <code>LENGTH_EC_F2M_113</code> = 113.
   */
  public static final short LENGTH_EC_F2M_113 = 113;

  /**
   * EC Key Length <code>LENGTH_EC_FP_128</code> = 128.
   */
  public static final short LENGTH_EC_FP_128 = 128;

  /**
   * EC Key Length <code>LENGTH_EC_F2M_131</code> = 131.
   */
  public static final short LENGTH_EC_F2M_131 = 131;

  /**
   * EC Key Length <code>LENGTH_EC_FP_160</code> = 160.
   */
  public static final short LENGTH_EC_FP_160 = 160;

  /**
   * EC Key Length <code>LENGTH_EC_F2M_163</code> = 163.
   */
  public static final short LENGTH_EC_F2M_163 = 163;

  /**
   * EC Key Length <code>LENGTH_EC_FP_192</code> = 192.
   */
  public static final short LENGTH_EC_FP_192 = 192;

  /**
   * EC Key Length <code>LENGTH_EC_F2M_193</code> = 193.
   */
  public static final short LENGTH_EC_F2M_193 = 193;

  /**
   * EC Key Length <code>LENGTH_EC_FP_224</code> = 224.
   */
  public static final short LENGTH_EC_FP_224 = 224;

  /**
   * EC Key Length <code>LENGTH_EC_FP_256</code> = 256.
   */
  public static final short LENGTH_EC_FP_256 = 256;

  /**
   * EC Key Length <code>LENGTH_EC_FP_384</code> = 384.
   */
  public static final short LENGTH_EC_FP_384 = 384;

  /**
   * EC Key Length <code>LENGTH_EC_FP_521</code> = 521.
   */
  public static final short LENGTH_EC_FP_521 = 521;

  /**
   * AES Key Length <code>LENGTH_AES_128</code> = 128.
   */
  public static final short LENGTH_AES_128 = 128;

  /**
   * AES Key Length <code>LENGTH_AES_192</code> = 192.
   */
  public static final short LENGTH_AES_192 = 192;

  /**
   * AES Key Length <code>LENGTH_AES_256</code> = 256.
   */
  public static final short LENGTH_AES_256 = 256;

  /**
   * Korean Seed Key Length <code>LENGTH_KOREAN_SEED_128</code> = 128.
   */
  public static final short LENGTH_KOREAN_SEED_128 = 128;

  /**
   * HMAC Key Length <code>LENGTH_HMAC_SHA_1_BLOCK_64</code> = 64.
   */
  public static final short LENGTH_HMAC_SHA_1_BLOCK_64 = 64;

  /**
   * HMAC Key Length <code>LENGTH_HMAC_SHA_256_BLOCK_64</code> = 64.
   */
  public static final short LENGTH_HMAC_SHA_256_BLOCK_64 = 64;

  /**
   * HMAC Key Length <code>LENGTH_HMAC_SHA_384_BLOCK_128</code> = 128.
   */
  public static final short LENGTH_HMAC_SHA_384_BLOCK_128 = 128;

  /**
   * HMAC Key Length <code>LENGTH_HMAC_SHA_512_BLOCK_128</code> = 128.
   */
  public static final short LENGTH_HMAC_SHA_512_BLOCK_128 = 128;

  /**
   * DH Key Length <code>LENGTH_DH_1024</code> = 1024.
   */
  public static final short LENGTH_DH_1024 = (short)1024;

  /**
   * DH Key Length <code>LENGTH_DH_2048</code> = 2048.
   */
  public static final short LENGTH_DH_2048 = (short)2048;

  private KeyBuilder() {}

  /**
   * Creates uninitialized cryptographic keys for signature and cipher
   * algorithms. Only instances created by this method may be the key objects
   * used to initialize instances of <code>Signature</code>,
   * <code>Cipher</code> and <code>KeyPair</code>. Note that the object
   * returned must be cast to their appropriate key type interface.
   *
   * @param keyType
   *            the type of key to be generated. Valid codes listed in
   *            <code>TYPE_*</code> constants, for example
   *            {@link #TYPE_DES_TRANSIENT_RESET TYPE_DES_TRANSIENT_RESET}.
   * @param keyLength
   *            the key size in bits. The valid key bit lengths are key type
   *            dependent. Some common key lengths are listed above above in
   *            the <code>LENGTH_*</code> constants, for example
   *            {@link #LENGTH_DES LENGTH_DES}.
   * @param keyEncryption
   *            if <code>true</code> this boolean requests a key
   *            implementation which implements the
   *            <code>javacardx.crypto.KeyEncryption</code> interface. The
   *            key implementation returned may implement the
   *            <code>javacardx.crypto.KeyEncryption</code> interface even
   *            when this parameter is <code>false</code>.
   * @return the key object instance of the requested key type, length and
   *         encrypted access
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm associated with the specified
   *                type, size of key and key encryption interface is not
   *                supported.
   *                </ul>
   * @see #buildKey(byte, byte, short, boolean)
   */
  public static Key buildKey(byte keyType, short keyLength,
                             boolean keyEncryption) throws CryptoException {
    CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    return null;
  }

  /**
   * Creates uninitialized cryptographic keys for signature and cipher
   * algorithms. Only instances created by this method may be the key objects
   * used to initialize instances of <code>Signature</code>,
   * <code>Cipher</code> and <code>KeyPair</code>. Note that the object
   * returned must be cast to their appropriate key type interface.
   *
   * @param algorithmicKeyType
   *            the type of key and algorithm to be generated. Valid codes
   * listed in <code>ALG_TYPE_*</code> constants, for example
   *            {@link #ALG_TYPE_DES ALG_TYPE_DES}.
   * @param keyMemoryType
   *            the type of memory for key data storage. Valid codes listed in
   *            <code>JCSystem.MEMORY_TYPE_*</code> constants, for example
   *            {@link javacard.framework.JCSystem#MEMORY_TYPE_PERSISTENT
   * javacard.framework.JCSystem#MEMORY_TYPE_PERSISTENT}.
   * @param keyLength
   *            the key size in bits. The valid key bit lengths are key type
   *            dependent. Some common key lengths are listed above above in
   *            the <code>LENGTH_*</code> constants, for example
   *            {@link #LENGTH_DES LENGTH_DES}.
   * @param keyEncryption
   *            if <code>true</code> this boolean requests a key
   *            implementation which implements the
   *            <code>javacardx.crypto.KeyEncryption</code> interface. The
   *            key implementation returned may implement the
   *            <code>javacardx.crypto.KeyEncryption</code> interface even
   *            when this parameter is <code>false</code>.
   * @return the key object instance of the requested algorithmic key type,
   *             key memory storage type, key length and
   *             encrypted access required
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm associated with the specified
   *                algorithmic type, or memory storage type for key, or
   *                size of key or key encryption interface requested is not
   *                supported.
   *                </ul>
   * @since 3.0.4
   * @see #buildKey(byte, short, boolean)
   */
  public static Key buildKey(byte algorithmicKeyType, byte keyMemoryType,
                             short keyLength, boolean keyEncryption)
      throws CryptoException {

    CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    return null;
  }

  private static byte getKeyType(byte algorithmicKeyType, byte keyMemoryType) {
    switch (algorithmicKeyType) {
    case ALG_TYPE_DES: {
      switch (keyMemoryType) {
      case JCSystem.MEMORY_TYPE_PERSISTENT:
        return TYPE_DES;
      case JCSystem.MEMORY_TYPE_TRANSIENT_DESELECT:
        return TYPE_DES_TRANSIENT_DESELECT;
      case JCSystem.MEMORY_TYPE_TRANSIENT_RESET:
        return TYPE_DES_TRANSIENT_RESET;
      default:
        return 0;
      }
    }
    case ALG_TYPE_AES: {
      switch (keyMemoryType) {
      case JCSystem.MEMORY_TYPE_PERSISTENT:
        return TYPE_AES;
      case JCSystem.MEMORY_TYPE_TRANSIENT_DESELECT:
        return TYPE_AES_TRANSIENT_DESELECT;
      case JCSystem.MEMORY_TYPE_TRANSIENT_RESET:
        return TYPE_AES_TRANSIENT_RESET;
      default:
        return 0;
      }
    }
    case ALG_TYPE_DH_PRIVATE: {
      switch (keyMemoryType) {
      case JCSystem.MEMORY_TYPE_PERSISTENT:
        return TYPE_DH_PRIVATE;
      case JCSystem.MEMORY_TYPE_TRANSIENT_RESET:
        return TYPE_DH_PRIVATE_TRANSIENT_RESET;
      default:
        return 0;
      }
    }
    case ALG_TYPE_DH_PUBLIC: {
      switch (keyMemoryType) {
      case JCSystem.MEMORY_TYPE_PERSISTENT:
        return TYPE_DH_PUBLIC;
      case JCSystem.MEMORY_TYPE_TRANSIENT_RESET:
        return TYPE_DH_PUBLIC_TRANSIENT_RESET;
      default:
        return 0;
      }
    }
    case ALG_TYPE_EC_FP_PUBLIC: {
      if (keyMemoryType != JCSystem.MEMORY_TYPE_PERSISTENT)
        return 0;
      return TYPE_EC_FP_PUBLIC;
    }
    case ALG_TYPE_EC_FP_PRIVATE: {
      if (keyMemoryType != JCSystem.MEMORY_TYPE_PERSISTENT)
        return 0;
      return TYPE_EC_FP_PRIVATE;
    }
    case ALG_TYPE_RSA_PUBLIC: {
      if (keyMemoryType != JCSystem.MEMORY_TYPE_PERSISTENT)
        return 0;
      return TYPE_RSA_PUBLIC;
    }
    case ALG_TYPE_RSA_PRIVATE: {
      if (keyMemoryType != JCSystem.MEMORY_TYPE_PERSISTENT)
        return 0;
      return TYPE_RSA_PRIVATE;
    }
    default:
      return 0;
    }
  }

  /**
   * Creates cryptographic keys for signature and key agreement
   * algorithms. Only instances created by this method may be the key objects
   * used to initialize instances of <code>Signature</code>,
   * <code>KeyAgreement</code> and <code>KeyPair</code>. Note that the object
   * returned must be cast to their appropriate key type interface.
   *
   * <p>
   * The domain parameters object in the <code>domainParameters</code> argument
   * is shared with other keys created using the same parameter argument. This
   * means that any changes to the shared parameters - by calling one of the
   * setter methods - will be reflected within the other keys sharing these
   * parameters.
   *
   * <p>
   * The domainParameters argument must be a special parameters object.
   * Parameter objects can be created created using key algorithm types which
   * are suffixed with _PARAMETERS using the
   * {@link #buildKey(byte, byte, short, boolean)} method.
   * The key value, such as the secret S or the public point W for an EC Key, or
   * X or Y for DH and DSA Key will never be shared. The key size is determined
   * by information contained in the <code>domainParameters</code> argument. <P>
   *
   * @param algorithmicKeyType
   *            the type of key and algorithm to be generated. Valid codes
   * listed in <code>ALG_TYPE_*</code> constants, for example
   *            {@link #ALG_TYPE_EC_F2M_PUBLIC ALG_TYPE_EC_F2M_PUBLIC}.
   * @param keyMemoryType
   *            the type of memory for key data storage. Valid codes listed in
   *            <code>JCSystem.MEMORY_TYPE_*</code> constants, for example
   *            {@link javacard.framework.JCSystem#MEMORY_TYPE_PERSISTENT
   * MEMORY_TYPE_PERSISTENT}.
   * @param domainParameters
   *            a special parameters object containing domain parameters.
   *            Parameter objects can be created created using key types
   *            which are suffixed with _PARAMETERS
   * @param keyEncryption
   *            if <code>true</code> this boolean requests a key
   *            implementation which implements the
   *            <code>javacardx.crypto.KeyEncryption</code> interface. The
   *            key implementation returned may implement the
   *            <code>javacardx.crypto.KeyEncryption</code> interface even
   *            when this parameter is <code>false</code>.
   * @return the key object instance of the requested key type, length and
   *         encrypted access
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm associated with the specified
   *                type, size of key and key encryption interface is not
   *                supported.</li>
   *                </ul>
   */
  public static Key
  buildKeyWithSharedDomain(byte algorithmicKeyType, byte keyMemoryType,
                           Key domainParameters, boolean keyEncryption)
      throws CryptoException {
    CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    return null;
  }
}
