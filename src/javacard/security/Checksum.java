/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;
/**
 * The <code>Checksum</code> class is the base class for CRC (cyclic
 * redundancy check) checksum algorithms. Implementations of Checksum algorithms
 * must extend this class and implement all the abstract methods.
 * <p>
 * A tear or card reset event resets a <code>Checksum</code> object to the
 * initial state (state upon construction).
 * <p>
 * Even if a transaction is in progress, update of intermediate result state in
 * the implementation instance shall not participate in the transaction.
 */
public abstract class Checksum {

  /**
   * ISO/IEC 3309 compliant 16 bit CRC algorithm. This algorithm uses the
   * generator polynomial : <code>x^16+x^12+x^5+1</code>. The default
   * initial checksum value used by this algorithm is 0. This algorithm is
   * also compliant with the frame checking sequence as specified in section
   * 4.2.5.2 of the ISO/IEC 13239 specification.
   * <p>
   * To obtain the commonly used CCITT behavior:
   * <ul>
   * <li>Initialize with <code>0xFFFF</code> via the <code>init()</code>
   * method
   * <li>One's complement the result.
   * </ul>
   * Algorithm specifics:
   * <ul>
   * <li>The input data is not reversed (reflected)
   * <li>The ISO 3309 algorithm is used with the polynomial value
   * <code>0x1021</code>
   * <li>The resulting 16 bit FCS is not reversed (reflected)
   * <li>The 16 bit FCS is xor'd with <code>OxFFFF</code>. This is the
   * CRC16 result.
   * </ul>
   */
  public static final byte ALG_ISO3309_CRC16 = 1;

  /**
   * ISO/IEC 3309 compliant 32 bit CRC algorithm. This algorithm uses the
   * generator polynomial :
   * <code>X^32 +X^26 +X^23 +X^22 +X^16 +X^12 +X^11 +X^10 +X^8 +X^7 +X^5 +X^4
   * +X^2 +X +1</code>. The default initial checksum value used by this
   * algorithm is 0. This algorithm is also compliant with the frame checking
   * sequence as specified in section 4.2.5.3 of the ISO/IEC 13239
   * specification. <p> To obtain the PKZIP (also JDK<sup>TM</sup>
   * java.util.zip.CRC32 class) behavior: <ul> <li>Initialize with
   * <code>0xFFFFFFFF</code> via the <code>init()</code> method
   * </ul>
   * Algorithm specifics:
   * <ul>
   * <li>The input data is reversed (reflected)
   * <li>The ISO 3309 algorithm is used with the polynomial value
   * <code>0x04C11DB7</code>
   * <li>The resulting 32 bit FCS is reversed (reflected)
   * <li>The reversed 32 bit FCS is xor'd with OxFFFFFFFF. This is the CRC32
   * result.
   * </ul>
   */
  public static final byte ALG_ISO3309_CRC32 = 2;

  /**
   * Creates a <code>Checksum</code> object instance of the selected
   * algorithm.
   *
   * @param algorithm
   *            the desired checksum algorithm. Valid codes listed in
   *            <code>ALG_*</code> constants above, for example,
   *            {@link #ALG_ISO3309_CRC16 ALG_ISO3309_CRC16}.
   * @param externalAccess
   *            <code>true</code> indicates that the instance will be shared
   *            among multiple applet instances and that the
   *            <code>Checksum</code> instance will also be accessed (via a
   *            <code>Shareable</code>. interface) when the owner of the
   *            <code>Checksum</code> instance is not the currently selected
   *            applet. If <code>true</code> the implementation must not
   *            allocate CLEAR_ON_DESELECT transient space for internal data.
   * @return the <code>Checksum</code> object instance of the requested
   *         algorithm.
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm or shared access mode is not
   *                supported.
   *                </ul>
   */
  public static final Checksum getInstance(byte algorithm,
                                           boolean externalAccess)
      throws CryptoException {

    if (externalAccess) { // TODO: not yet implemented
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }

    switch (algorithm) {
    case ALG_ISO3309_CRC16:
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      break;
    case ALG_ISO3309_CRC32:
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      break;
    default:
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
    }
    return null;
  }

  /**
   * Protected Constructor
   */
  protected Checksum() {}

  /**
   * Resets and initializes the <code>Checksum</code> object with the
   * algorithm specific parameters.
   * <p>
   * Note:
   * <ul>
   * <li><em>The ALG_ISO3309_CRC16 algorithm expects 2 bytes of parameter
   * information in
   * </em><code>bArray</code><em> representing the initial checksum value.</em>
   * <li><em>The ALG_ISO3309_CRC32 algorithm expects 4 bytes of parameter
   * information in
   * </em><code>bArray</code><em> representing the initial checksum value.</em>
   * </ul>
   *
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
   *                <li><code>CryptoException.ILLEGAL_VALUE</code> if a
   *                byte array parameter option is not supported by the
   *                algorithm or if the <code>bLen</code> is an incorrect
   *                byte length for the algorithm specific data.
   *                </ul>
   */
  public abstract void init(byte[] bArray, short bOff, short bLen)
      throws CryptoException;

  /**
   * Gets the Checksum algorithm. Valid codes listed in <code>ALG_*</code>
   * constants above, for example,
   * {@link #ALG_ISO3309_CRC16 ALG_ISO3309_CRC16}.
   *
   * @return the algorithm code defined above
   */
  public abstract byte getAlgorithm();

  /**
   * Generates a CRC checksum of all/last input data. The CRC engine processes
   * input data starting with the byte at offset <code>inOffset</code> and
   * continuing on until the byte at <code>(inOffset+inLength-1)</code> of
   * the <code>inBuff</code> array. Within each byte the processing proceeds
   * from the least significant bit to the most.
   * <p>
   * Completes and returns the checksum computation. The <code>Checksum</code>
   * object is reset to the initial state(state upon construction) when this
   * method completes.
   * <p>
   * Note:
   * <ul>
   * <li><em>The <code>ALG_ISO3309_CRC16</code> and
   * <code>ALG_ISO3309_CRC32</code> algorithms reset the initial checksum value
   * to 0. The initial checksum value can be re-initialized using the
   * </em>{@link #init(byte[], short, short) init(byte[], short, short)}
   * <em> method.</em>
   * </ul>
   * <p>
   * The input and output buffer data may overlap.
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param inBuff
   *            the input buffer of data to be checksummed
   * @param inOffset
   *            the offset into the input buffer at which to begin checksum
   *            generation
   * @param inLength
   *            the byte length to checksum
   * @param outBuff
   *            the output buffer, may be the same as the input buffer
   * @param outOffset
   *            the offset into the output buffer where the resulting checksum
   *            value begins
   * @return number of bytes of checksum output in <code>outBuff</code>
   */
  public abstract short doFinal(byte[] inBuff, short inOffset, short inLength,
                                byte[] outBuff, short outOffset);

  /**
   * Accumulates a partial checksum of the input data. The CRC engine
   * processes input data starting with the byte at offset
   * <code>inOffset</code> and continuing on until the byte at
   * <code>(inOffset+inLength-1)</code> of the <code>inBuff</code> array.
   * Within each byte the processing proceeds from the least significant bit
   * to the most.
   * <p>
   * This method requires temporary storage of intermediate results. This may
   * result in additional resource consumption and/or slow performance. This
   * method should only be used if all the input data required for the
   * checksum is not available in one byte array. The
   * {@link #doFinal(byte[], short, short, byte[], short) doFinal(byte[], short,
   * short, byte[], short)} method is recommended whenever possible. <p> Note:
   * <ul>
   * <li><em>If <code>inLength</code> is 0 this method does nothing.</em>
   * </ul>
   *
   * @param inBuff
   *            the input buffer of data to be checksummed
   * @param inOffset
   *            the offset into the input buffer at which to begin checksum
   *            generation
   * @param inLength
   *            the byte length to checksum
   * @see #doFinal doFinal
   */
  public abstract void update(byte[] inBuff, short inOffset, short inLength);
}
