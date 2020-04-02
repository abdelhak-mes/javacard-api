/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

/**
 * This class is a container for a key pair (a public key and a private key). It
 * does not enforce any security, and, when initialized, should be treated like
 * a PrivateKey.
 * <p>
 * In addition, this class features a key generation method.
 *
 * @see PublicKey PublicKey
 * @see PrivateKey PrivateKey
 */
public final class KeyPair {

  /**
   * <code>KeyPair</code> object containing a RSA key pair.
   */
  public static final byte ALG_RSA = 1;

  /**
   * <code>KeyPair</code> object containing a RSA key pair with private key
   * in its Chinese Remainder Theorem form.
   */
  public static final byte ALG_RSA_CRT = 2;

  /**
   * <code>KeyPair</code> object containing a DSA key pair.
   */
  public static final byte ALG_DSA = 3;

  /**
   * <code>KeyPair</code> object containing an EC key pair for EC operations
   * over fields of characteristic 2 with polynomial basis.
   */
  public static final byte ALG_EC_F2M = 4;

  /**
   * <code>KeyPair</code> object containing an EC key pair for EC operations
   * over large prime fields
   */
  public static final byte ALG_EC_FP = 5;

  /**
   * <code>KeyPair</code> object containing an DH key pair for modular
   * exponentiation based Diffie Hellman <code>KeyAgreement</code> operations.
   */
  public static final byte ALG_DH = 6;

  /**
   * (Re)Initializes the key objects encapsulated in this <code>KeyPair</code>
   * instance with new key values. The initialized public and private key
   * objects encapsulated in this instance will then be suitable for use with
   * the <code>Signature</code>, <code>Cipher</code> and
   * <code>KeyAgreement</code> objects. An internal secure random number
   * generator is used during new key pair generation.
   * <p>
   * Notes:
   * <ul>
   * <li><em>For the RSA algorithm, if the exponent value in the public key
   * object is pre-initialized, it will be retained. Otherwise, a default value
   * of 65537 will be used.</em> <li><em>For the DSA algorithm, if the p, q and
   * g parameters of the public key object are pre-initialized, they will be
   * retained. Otherwise, default precomputed parameter sets will be used. The
   * required default precomputed values are listed in </em>Appendix B of
   * Java Cryptography Architecture API Specification &amp; Reference<em>
   * document.</em> <li><em>For the EC case, if the Field, A, B, G and R
   * parameters of the public key object are pre-initialized, then they will be
   * retained. Otherwise default pre-specified values MAY be used (e.g. WAP
   * predefined curves), since computation of random generic EC keys is
   * infeasible on the smart card platform.</em> <li><em>If the time taken to
   * generate the key values is excessive, the implementation may automatically
   * request additional APDU processing time from the CAD.</em>
   * </ul>
   *
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                pre-initialized exponent value parameter in the RSA public
   *                key or the pre-initialized p, q, g parameter set in the
   *                DSA public key or the pre-initialized Field, A, B, G and R
   *                parameter set in public EC key is invalid.
   *                </ul>
   * @see javacard.framework.APDU javacard.framework.APDU
   * @see Signature Signature
   * @see javacardx.crypto.Cipher javacardx.crypto.Cipher
   * @see RSAPublicKey RSAPublicKey
   * @see ECKey ECKey
   * @see DSAKey DSAKey
   */
  public final void genKeyPair() throws CryptoException {
    CryptoException.throwIt(CryptoException.ILLEGAL_VALUE);
  }

  /**
   * Constructs a <code>KeyPair</code> instance for the specified algorithm
   * and keylength; the encapsulated keys are uninitialized. To initialize the
   * <code>KeyPair</code> instance use the <code>genKeyPair()</code>
   * method.
   * <p>
   * The encapsulated key objects are of the specified <code>keyLength</code>
   * size and implement the appropriate <code>Key</code> interface
   * associated with the specified algorithm (example -
   * <code>RSAPublicKey</code> interface for the public key and
   * <code>RSAPrivateKey</code> interface for the private key within an
   * <code>ALG_RSA</code> key pair).
   * <p>
   * Notes:
   * <ul>
   * <li><em>The key objects encapsulated in the generated
   * </em><code>KeyPair</code><em> object need not support the
   * </em><code>KeyEncryption</code><em> interface.</em>
   * </ul>
   *
   * @param algorithm
   *            the type of algorithm whose key pair needs to be generated.
   *            Valid codes listed in <code>ALG_*</code> constants above, for
   * example
   *            {@link #ALG_RSA ALG_RSA}.
   * @param keyLength
   *            the key size in bits. The valid key bit lengths are key type
   *            dependent. See the <code>KeyBuilder</code> class.
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if:
   *                <ul>
   *                <li>the requested algorithm associated with the specified
   *                type, size of key is not supported.</li>
   *                <li>the requested algorithm is EC and default pre-specified
   *                values for field, A, B, G and R parameters are not
   * available.</li>
   *                </ul>
   *                </ul>
   * @see KeyBuilder KeyBuilder
   * @see Signature Signature
   * @see javacardx.crypto.Cipher javacardx.crypto.Cipher
   * @see javacardx.crypto.KeyEncryption javacardx.crypto.KeyEncryption
   */
  public KeyPair(byte algorithm, short keyLength) throws CryptoException {
    CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
  }

  /**
   * Constructs a new <code>KeyPair</code> object containing the specified
   * public key and private key.
   *
   * <p>
   * Note that this constructor only stores references to the public and
   * private key components in the generated <code>KeyPair</code> object. It
   * does not throw an exception if the key parameter objects are
   * uninitialized.
   *
   * @param publicKey
   *            the public key.
   * @param privateKey
   *            the private key.
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                input parameter key objects are mismatched - different
   *                algorithms or different key sizes. Parameter values are
   *                not checked.
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the algorithm associated with the specified type, size of
   *                key is not supported.
   *                </ul>
   */
  public KeyPair(PublicKey publicKey, PrivateKey privateKey)
      throws CryptoException {
    CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
  }

  /**
   * Returns a reference to the public key component of this
   * <code>KeyPair</code> object.
   *
   * @return a reference to the public key.
   */
  public PublicKey getPublic() {
    // TODO: Not yet implemented!!!
    return null;
  }

  /**
   * Returns a reference to the private key component of this
   * <code>KeyPair</code> object.
   *
   * @return a reference to the private key.
   */
  public PrivateKey getPrivate() {
    // TODO: Not yet implemented!!!
    return null;
  }
}
