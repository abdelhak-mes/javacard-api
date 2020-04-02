/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

/**
 * The <code>ECKey</code> interface is the base interface for the EC
 * algorithm's private and public key implementations. An EC private key
 * implementation must also implement the <code>ECPrivateKey</code> interface
 * methods. An EC public key implementation must also implement the
 * <code>ECPublicKey</code> interface methods.
 *
 * <p>
 * The equation of the curves for keys of type <code>TYPE_EC_FP_PUBLIC</code>
 * or <code>TYPE_EC_FP_PRIVATE</code> is y^2 = x^3 + A * x + B. The equation
 * of the curves for keys of type <code>TYPE_EC_F2M_PUBLIC</code> or
 * <code>TYPE_EC_F2M_PRIVATE</code> is y^2 + x * y = x^3 + A * x^2 + B.
 *
 * <p>
 * The notation used to describe parameters specific to the EC algorithm is
 * based on the naming conventions established in [IEEE P1363].
 *
 * @version 1.0
 * @see ECPublicKey ECPublicKey
 * @see ECPrivateKey ECPrivateKey
 * @see KeyBuilder KeyBuilder
 * @see Signature Signature
 * @see javacardx.crypto.KeyEncryption javacardx.crypto.KeyEncryption
 * @see KeyAgreement KeyAgreement
 */
public interface ECKey {

  /**
   * Sets the field specification parameter value for keys of type
   * <code>TYPE_EC_FP_PRIVATE</code> or <code>TYPE_EC_FP_PUBLIC</code>.
   * The specified value is the prime p corresponding to the field GF(p).
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
   *            the offset into the input buffer at which the parameter value
   *            begins
   * @param length
   *            the byte length of the parameter value
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                length parameter is 0 or invalid or if the
   *                input parameter data is inconsistent with the key length
   *                or if input data decryption is required and fails.
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the key is neither of type <code>TYPE_EC_FP_PUBLIC</code>
   *                nor <code>TYPE_EC_FP_PRIVATE</code>.
   *                </ul>
   */
  public void setFieldFP(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Sets the field specification parameter value for keys of type
   * <code>TYPE_EC_F2M_PUBLIC</code> or <code>TYPE_EC_F2M_PRIVATE</code>
   * in the case where the polynomial is a trinomial, of the form x^n + x^e +
   * 1 (where n is the bit length of the key). It is required that n &gt; e &gt;
   * 0.
   *
   * @param e
   *            the value of the intermediate exponent of the trinomial
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                input parameter e is not such that 0 &lt; e &lt; n.
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the key is neither of type <code>TYPE_EC_F2M_PUBLIC</code>
   *                nor <code>TYPE_EC_F2M_PRIVATE</code>.
   *                </ul>
   */
  public void setFieldF2M(short e) throws CryptoException;

  /**
   * Sets the field specification parameter value for keys of type
   * <code>TYPE_EC_F2M_PUBLIC</code> or <code>TYPE_EC_F2M_PRIVATE</code>
   * in the case where the polynomial is a pentanomial, of the form x^n + x^e1 +
   * x^e2 + x^e3 + 1 (where n is the bit length of the key). It is required
   * for all ei where ei = {e1, e2, e3} that n &gt; ei &gt; 0.
   *
   * @param e1
   *            the value of the first of the intermediate exponents of the
   *            pentanomial
   * @param e2
   *            the value of the second of the intermediate exponent of the
   *            pentanomial
   * @param e3
   *            the value of the third of the intermediate exponents
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                input parameters ei where ei = {<code>e1</code>,
   *                <code>e2</code>, <code>e3</code>} are not such that
   *                for all ei, n &gt; ei &gt; 0.
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the key is neither of type <code>TYPE_EC_F2M_PUBLIC</code>
   *                nor <code>TYPE_EC_F2M_PRIVATE</code>.
   *                </ul>
   */
  public void setFieldF2M(short e1, short e2, short e3) throws CryptoException;

  /**
   * Sets the first coefficient of the curve of the key.
   *
   * For keys of type <code>TYPE_EC_FP_PRIVATE</code> or
   * <code>TYPE_EC_FP_PUBLIC</code>, this is the value of A as an integer
   * modulo the field specification parameter p, that is, an integer in the
   * range <code>0</code> to p-1. For keys of type
   * <code>TYPE_EC_F2M_PRIVATE</code> or <code>TYPE_EC_F2M_PUBLIC</code>,
   * the bit representation of this value specifies a polynomial with binary
   * coefficients which represents the value of A in the field.
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
   *            the offset into the input buffer at which the coefficient
   *            value begins
   * @param length
   *            the byte length of the coefficient value
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                length parameter is 0 or invalid or if the
   *                input parameter data is inconsistent with the key length
   *                or if input data decryption is required and fails.
   *                </ul>
   */
  public void setA(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Sets the second coefficient of the curve of the key.
   *
   * For keys of type <code>TYPE_EC_FP_PRIVATE</code> or
   * <code>TYPE_EC_FP_PUBLIC</code>, this is the value of B as an integer
   * modulo the field specification parameter p, that is, an integer in the
   * range <code>0</code> to p-1. For keys of type
   * <code>TYPE_EC_F2M_PRIVATE</code> or <code>TYPE_EC_F2M_PUBLIC</code>,
   * the bit representation of this value specifies a polynomial with binary
   * coefficients which represents the value of B in the field.
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
   *            the offset into the input buffer at which the coefficient
   *            value begins
   * @param length
   *            the byte length of the coefficient value
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                length parameter is 0 or invalid or if the
   *                input parameter data is inconsistent with the key length
   *                or if input data decryption is required and fails.
   *                </ul>
   */
  public void setB(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Sets the fixed point of the curve. The point should be specified as an
   * octet string as per ANSI X9.62. A specific implementation need not
   * support the compressed form, but must support the uncompressed form of
   * the point.
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
   *            the offset into the input buffer at which the point
   *            specification begins
   * @param length
   *            the byte length of the point specification
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                length parameter is 0 or invalid or if the
   *                input parameter data format is incorrect, or if the input
   *                parameter data is inconsistent with the key length, or if
   *                input data decryption is required and fails.
   *                </ul>
   */
  public void setG(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Sets the order of the fixed point G of the curve.
   *
   * The plain text data format is big-endian and right-aligned (the least
   * significant bit is the least significant bit of last byte). Input
   * parameter data is copied into the internal representation.
   *
   * @param buffer
   *            the input buffer
   * @param offset
   *            the offset into the input buffer at which the order begins
   * @param length
   *            the byte length of the order
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
   *                length parameter is 0 or invalid or if the
   *                input parameter data is inconsistent with the key length,
   *                or if input data decryption is required and fails.
   *                </ul>
   *                <p>
   *                Note:
   *                <ul>
   *                <li><em>If the key object implements the
   * </em><code>javacardx.crypto.KeyEncryption</code><em> interface and the
   * </em><code>Cipher</code><em> object specified via
   * </em><code>setKeyCipher()</code><em> is not </em><code>null</code><em>, the
   * key value is decrypted using the </em><code>Cipher</code><em> object.</em>
   *                </ul>
   */
  public void setR(byte[] buffer, short offset, short length)
      throws CryptoException;

  /**
   * Sets the cofactor of the order of the fixed point G of the curve.
   *
   * The cofactor need not be specified for the key to be initialized.
   * However, the <code>KeyAgreement</code> algorithm type
   * <code>ALG_EC_SVDP_DHC</code> requires that the cofactor, K, be
   * initialized.
   *
   * @param K
   *            the value of the cofactor
   */
  public void setK(short K);

  /**
   * Returns the field specification parameter value of the key.
   *
   * For keys of type <code>TYPE_EC_FP_PRIVATE</code> or
   * <code>TYPE_EC_FP_PUBLIC</code>, this is the value of the prime p
   * corresponding to the field GF(p). For keys of type
   * <code>TYPE_EC_F2M_PRIVATE</code> or <code>TYPE_EC_F2M_PUBLIC</code>,
   * it is the value whose bit representation specifies the polynomial with
   * binary coefficients used to define the arithmetic operations in the field
   * GF(2^n)
   *
   * The plain text data format is big-endian and right-aligned (the least
   * significant bit is the least significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the output buffer at which the parameter value
   *            is to begin
   * @return the byte length of the parameter
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the field specification parameter value of the key has not
   *                been successfully initialized since the time the
   *                initialized state of the key was set to false.
   *                </ul>
   * @see Key Key
   */
  public short getField(byte[] buffer, short offset) throws CryptoException;

  /**
   * Returns the first coefficient of the curve of the key.
   *
   * For keys of type <code>TYPE_EC_FP_PRIVATE</code> or
   * <code>TYPE_EC_FP_PUBLIC</code>, this is the value of A as an integer
   * modulo the field specification parameter p, that is, an integer in the
   * range <code>0</code> to p-1. For keys of type
   * <code>TYPE_EC_F2M_PRIVATE</code> or <code>TYPE_EC_F2M_PUBLIC</code>,
   * the bit representation of this value specifies a polynomial with binary
   * coefficients which represents the value of A in the field.
   *
   * The plain text data format is big-endian and right-aligned (the least
   * significant bit is the least significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the output buffer at which the coefficient
   *            value is to begin
   * @return the byte length of the coefficient
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the coefficient of the curve of the key has not been
   *                successfully initialized since the time the initialized
   *                state of the key was set to false.
   *                </ul>
   * @see Key
   */
  public short getA(byte[] buffer, short offset) throws CryptoException;

  /**
   * Returns the second coefficient of the curve of the key.
   *
   * For keys of type <code>TYPE_EC_FP_PRIVATE</code> or
   * <code>TYPE_EC_FP_PUBLIC</code>, this is the value of B as an integer
   * modulo the field specification parameter p, that is, an integer in the
   * range 0 to p-1. For keys of type <code>TYPE_EC_F2M_PRIVATE</code> or
   * <code>TYPE_EC_F2M_PUBLIC</code>, the bit representation of this value
   * specifies a polynomial with binary coefficients which represents the
   * value of B in the field.
   *
   * The plain text data format is big-endian and right-aligned (the least
   * significant bit is the least significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the output buffer at which the coefficient
   *            value is to begin
   * @return the byte length of the coefficient
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the second coefficient of the curve of the key has not
   *                been successfully initialized since the time the
   *                initialized state of the key was set to false.
   *                </ul>
   * @see Key
   */
  public short getB(byte[] buffer, short offset) throws CryptoException;

  /**
   * Returns the fixed point of the curve. The point is represented as an
   * octet string in compressed or uncompressed forms as per ANSI X9.62.
   *
   * The plain text data format is big-endian and right-aligned (the least
   * significant bit is the least significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the output buffer at which the point
   *            specification data is to begin
   * @return the byte length of the point specification
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the fixed point of the curve of the key has not been
   *                successfully initialized since the time the initialized
   *                state of the key was set to false.
   *                </ul>
   * @see Key Key
   */
  public short getG(byte[] buffer, short offset) throws CryptoException;

  /**
   * Returns the order of the fixed point G of the curve.
   *
   * The plain text data format is big-endian and right-aligned (the least
   * significant bit is the least significant bit of last byte).
   *
   * @param buffer
   *            the output buffer
   * @param offset
   *            the offset into the input buffer at which the order begins
   * @return the byte length of the order
   * @exception CryptoException
   *                with the following reason code:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the order of the fixed point G of the curve of the key has
   *                not been successfully initialized since the time the
   *                initialized state of the key was set to false.
   *                </ul>
   * @see Key Key
   */
  public short getR(byte[] buffer, short offset) throws CryptoException;

  /**
   * Returns the cofactor of the order of the fixed point G of the curve.
   *
   * @return the value of the cofactor
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
   *                the cofactor of the order of the fixed point G of the
   *                curve of the key has not been successfully initialized
   *                since the time the initialized state of the key was set to
   *                false.
   *                </ul>
   * @see Key Key
   */
  public short getK() throws CryptoException;

  /**
   * Copies the domain parameters from the specified <code>ECKey</code> instance
   * into the domain parameters of <code>this</code> object instance. <p> This
   * method should only be used when a separate set of parameters is required.
   * It is better to share the domain parameters using the
   * {@link KeyBuilder#buildKeyWithSharedDomain(byte, byte, Key, boolean)}
   * method when the value of the domain parameters remains constant in time.
   *
   * @param ecKey
   *        a key or domain parameter object from which the domain parameters
   * will be copied
   * @throws CryptoException
   *         with the following reason codes:
   *         <ul>
   *         <li><code>CryptoException.ILLEGAL_VALUE</code> if the parameters
   * could not be assigned because the given key is null, not of the correct
   * size, or if some or all of the domain parameters are not valid</li>
   *         <li><code>CryptoException.UNINITIALIZED_KEY</code> if the domain
   * parameters are not all present</li>
   *         </ul>
   */
  void copyDomainParametersFrom(ECKey ecKey) throws CryptoException;
}
