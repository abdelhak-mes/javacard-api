/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

import javacard.framework.JCSystem;
import javacard.framework.SystemException;
import javacard.security.InitializedMessageDigest;

/**
 * The <code>MessageDigest</code> class is the base class for hashing
 * algorithms. Implementations of <code>MessageDigest</code> algorithms must
 * extend this class and implement all the abstract methods.
 * <p>
 * A tear or card reset event resets a <code>MessageDigest</code> object to
 * the initial state (state upon construction).
 * <p>
 * Even if a transaction is in progress, update of intermediate result state in
 * the implementation instance shall not participate in the transaction.
 */
public abstract class MessageDigest {

  /**
   * This constant indicates that there is no discrete message digest
   * algorithm. It is intended for use in the
   * {@link javacard.security.Signature#getInstance(byte, byte, byte, boolean)
   * Signature.getInstance(byte, byte, byte, boolean)} method. <p> If this
   * constant in used in the
   * {@link #getInstance(byte, boolean) getInstance(byte, boolean)} it results
   * in a CryptoException.
   */
  public static final byte ALG_NULL = 0;
  /**
   * Message Digest algorithm SHA-1. The block size used by this algorithm is 64
   * bytes. The intermediate hash value size used by this algorithm is 20 bytes.
   */
  public static final byte ALG_SHA = 1;

  /**
   * Message Digest algorithm MD5. The block size used by this algorithm is 64
   * bytes. The intermediate hash value size used by this algorithm is 16 bytes.
   */
  public static final byte ALG_MD5 = 2;

  /**
   * Message Digest algorithm RIPE MD-160. The block size used by this
   * algorithm is 64 bytes. The intermediate hash value size used by this
   * algorithm is 20 bytes.
   */
  public static final byte ALG_RIPEMD160 = 3;

  /**
   * Message Digest algorithm SHA-256. The block size used by this algorithm
   * is 64 bytes. The intermediate hash value size used by this algorithm
   * is 32 bytes.
   */
  public static final byte ALG_SHA_256 = 4;

  /**
   * Message Digest algorithm SHA-384. The block size used by this algorithm
   * is 128 bytes. The intermediate hash value size used by this algorithm
   * is 64 bytes.
   */
  public static final byte ALG_SHA_384 = 5;

  /**
   * Message Digest algorithm SHA-512. The block size used by this algorithm
   * is 128 bytes. The intermediate hash value size used by this algorithm
   * is 64 bytes.
   */
  public static final byte ALG_SHA_512 = 6;

  /**
   * Message Digest algorithm SHA-224. The block size used by this algorithm
   * is 64 bytes. The intermediate hash value size used by this algorithm
   * is 32 bytes.
   */
  public static final byte ALG_SHA_224 = 7;

  /**
   * Message Digest algorithm SHA3-224. The block size used by this algorithm
   * is 64 bytes. The intermediate hash value size used by this algorithm
   * is 32 bytes.
   */
  public static final byte ALG_SHA3_224 = 8;

  /**
   * Message Digest algorithm SHA3-256. The block size used by this algorithm
   * is 64 bytes. The intermediate hash value size used by this algorithm
   * is 32 bytes.
   */
  public static final byte ALG_SHA3_256 = 9;

  /**
   * Message Digest algorithm SHA3-384. The block size used by this algorithm
   * is 128 bytes. The intermediate hash value size used by this algorithm
   * is 64 bytes.
   */
  public static final byte ALG_SHA3_384 = 10;

  /**
   * Message Digest algorithm SHA3-512. The block size used by this algorithm
   * is 128 bytes. The intermediate hash value size used by this algorithm
   * is 64 bytes.
   */
  public static final byte ALG_SHA3_512 = 11;

  /**
   * Length of digest in bytes for MD5
   */
  public static final byte LENGTH_MD5 = (byte)16;

  /**
   * Length of digest in bytes for RIPE MD-160
   */
  public static final byte LENGTH_RIPEMD160 = (byte)20;

  /**
   * Length of digest in bytes for SHA-1
   */
  public static final byte LENGTH_SHA = (byte)20;

  /**
   * Length of digest in bytes for SHA-224
   */
  public static final byte LENGTH_SHA_224 = (byte)28;

  /**
   * Length of digest in bytes for SHA-256
   */
  public static final byte LENGTH_SHA_256 = (byte)32;

  /**
   * Length of digest in bytes for SHA-384
   */
  public static final byte LENGTH_SHA_384 = (byte)48;

  /**
   * Length of digest in bytes for SHA-512
   */
  public static final byte LENGTH_SHA_512 = (byte)64;

  /**
   * Length of digest in bytes for SHA3-224
   */
  public static final byte LENGTH_SHA3_224 = (byte)28;

  /**
   * Length of digest in bytes for SHA3-256
   */
  public static final byte LENGTH_SHA3_256 = (byte)32;

  /**
   * Length of digest in bytes for SHA3-384
   */
  public static final byte LENGTH_SHA3_384 = (byte)48;

  /**
   * Length of digest in bytes for SHA3-512
   */
  public static final byte LENGTH_SHA3_512 = (byte)64;

  /**
   * Protected Constructor
   */
  protected MessageDigest() {}

  /**
   * Creates a <code>MessageDigest</code> object instance of the selected
   * algorithm.
   *
   * @param algorithm
   *            the desired message digest algorithm. Valid codes listed in
   *            <code>ALG_*</code> constants above, for example,
   *            {@link #ALG_SHA ALG_SHA}.
   * @param externalAccess
   *            <code>true</code> indicates that the instance will be shared
   *            among multiple applet instances and that the
   *            <code>MessageDigest</code> instance will also be accessed
   *            (via a <code>Shareable</code>. interface) when the owner of
   *            the <code>MessageDigest</code> instance is not the currently
   *            selected applet. If <code>true</code> the implementation
   *            must not allocate CLEAR_ON_DESELECT transient space for
   *            internal data.
   * @return the <code>MessageDigest</code> object instance of the requested
   *         algorithm
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm or shared access mode is not
   *                supported.
   *                </ul>
   */
  public static final MessageDigest getInstance(byte algorithm,
                                                boolean externalAccess)
      throws CryptoException {

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
   * Creates a <code>InitializedMessageDigest</code> object instance of the
   * selected algorithm.
   *
   * @param algorithm
   *            the desired message digest algorithm. Valid codes listed in
   *            <code>ALG_*</code> constants above, for example,
   *            {@link #ALG_SHA ALG_SHA}.
   * @param externalAccess
   *            <code>true</code> indicates that the instance will be shared
   *            among multiple applet instances and that the
   *            <code>InitializedMessageDigest</code> instance will also be
   *            accessed (via a <code>Shareable</code>. interface) when the
   *            owner of the <code>InitializedMessageDigest</code> instance
   *            is not the currently selected applet. If <code>true</code>
   *            the implementation must not allocate CLEAR_ON_DESELECT
   *            transient space for internal data.
   * @return the <code>InitializedMessageDigest</code> object instance of
   *         the requested algorithm
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
   *                the requested algorithm or shared access mode is not
   *                supported.
   *                </ul>
   * @since 2.2.2
   */
  public static final InitializedMessageDigest
  getInitializedMessageDigestInstance(byte algorithm, boolean externalAccess)
      throws CryptoException {

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
   * Gets the Message digest algorithm.
   *
   * @return the algorithm code defined above
   */
  public abstract byte getAlgorithm();

  /**
   * Returns the byte length of the hash.
   *
   * @return hash length
   */
  public abstract byte getLength();

  /**
   * Generates a hash of all/last input data. Completes and returns the hash
   * computation after performing final operations such as padding. The
   * <code>MessageDigest</code> object is reset to the initial state after
   * this call is made. The data format is big-endian.
   * <p>
   * The input and output buffer data may overlap.
   * <p>
   * In addition to returning a {@code short} result, this method sets the
   * result in an internal state which can be rechecked using assertion methods
   * of the {@link javacardx.security.SensitiveResult SensitiveResult} class,
   * if supported by the platform.
   *
   * @param inBuff
   *            the input buffer of data to be hashed
   * @param inOffset
   *            the offset into the input buffer at which to begin hash
   *            generation
   * @param inLength
   *            the byte length to hash
   * @param outBuff
   *            the output buffer, may be the same as the input buffer
   * @param outOffset
   *            the offset into the output buffer where the resulting hash
   *            value begins
   * @return number of bytes of hash output in <code>outBuff</code>
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_USE</code> if the
   *                accumulated message length is greater than the maximum
   *                length supported by the algorithm.
   *                </ul>
   */
  public abstract short doFinal(byte[] inBuff, short inOffset, short inLength,
                                byte[] outBuff, short outOffset)
      throws CryptoException;

  /**
   * Accumulates a hash of the input data. This method requires temporary
   * storage of intermediate results. In addition, if the input data length is
   * not block aligned (multiple of block size) then additional internal
   * storage may be allocated at this time to store a partial input data
   * block. This may result in additional resource consumption and/or slow
   * performance. This method should only be used if all the input data
   * required for the hash is not available in one byte array. If all of the
   * input data required for the hash is located in a single byte array, use
   * of the <code>doFinal()</code> method is recommended. The
   * <code>doFinal()</code> method must be called to complete processing of
   * input data accumulated by one or more calls to the <code>update()</code>
   * method. The data format is big-endian.
   * <p>
   * Note:
   * <ul>
   * <li><em>If </em><code>inLength</code><em> is 0 this method does
   * nothing.</em>
   * </ul>
   *
   * @param inBuff
   *            the input buffer of data to be hashed
   * @param inOffset
   *            the offset into the input buffer at which to begin hash
   *            generation
   * @param inLength
   *            the byte length to hash
   * @exception CryptoException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>CryptoException.ILLEGAL_USE</code> if the
   *                accumulated message length is greater than the maximum
   *                length supported by the algorithm.
   *                </ul>
   * @see #doFinal doFinal
   */
  public abstract void update(byte[] inBuff, short inOffset, short inLength)
      throws CryptoException;

  /**
   * Resets the <code>MessageDigest</code> object to the initial state for
   * further use.
   */
  public abstract void reset();

  /**
   * The {@code OneShot} class is a specialization of the
   * {@code MessageDigest} class intended to support efficient one-shot hash
   * operations that may avoid persistent memory writes entirely. The
   * {@code OneShot} class uses a delegation model where calls are
   * delegated to an instance of a {@code MessageDigest}-implementing class
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
   * MessageDigest.OneShot dig = null;
   * try {
   *     dig = MessageDigest.OneShot.open(MessageDigest.ALG_SHA);
   *     dig.doFinal(someInData, (short) 0, (short) someInData.length, digData,
   * (short) 0); } catch (CryptoException ce) {
   *     // Handle exception
   * } finally {
   *     if (dig != null) {
   *        dig.close();
   *        dig = null;
   *     }
   * }
   * ...
   * </pre>
   *
   * @since 3.0.5
   */
  public static final class OneShot extends MessageDigest {

    /**
     * Opens/acquires a JCRE owned temporary Entry Point Object instance of
     * {@code OneShot} with the selected algorithm.
     *
     * @param algorithm the desired message digest algorithm. Valid codes listed
     * in {@code ALG_*} constants above, for example, {@link #ALG_SHA ALG_SHA}.
     * @return the {@code MessageDigest} object instance of the requested
     * algorithm.
     * @throws CryptoException with the following reason codes:
     * <ul>
     * <li>{@code CryptoException.NO_SUCH_ALGORITHM} if the requested algorithm
     * or shared access mode is not supported.</li>
     * </ul>
     * @throws javacard.framework.SystemException with the following reason
     * codes:
     * <ul>
     * <li>{@code SystemException.NO_RESOURCE} if sufficient resources are not
     * available.</li>
     * </ul>
     */
    public static final OneShot open(byte algorithm) throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
      return null;
    }

    /**
     * Closes and releases this JCRE owned temporary instance of the
     * {@code OneShot} object for reuse. If this method is called
     * again this method does nothing.
     *
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    public void close() { return; }

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
    public byte getLength() {
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
    public short doFinal(byte[] inBuff, short inOffset, short inLength,
                         byte[] outBuff, short outOffset)
        throws CryptoException {
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.ILLEGAL_USE);
      return -1;
    }

    /**
     * Always throws a {@code CryptoException}.This method is not supported by
     * {@code OneShot}.
     *
     * @param inBuff
     *            the input buffer of data to be hashed
     * @param inOffset
     *            the offset into the input buffer at which to begin hash
     *            generation
     * @param inLength
     *            the byte length to hash
     * @throws CryptoException with the following reason codes:
     * <ul>
     * <li>{@code CryptoException.ILLEGAL_USE} always.</li>
     * </ul>
     */
    @Override
    public void update(byte[] inBuff, short inOffset, short inLength)
        throws CryptoException {
      CryptoException.throwIt(CryptoException.ILLEGAL_USE);
    }

    /**
     * {@inheritDoc}
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    @Override
    public void reset() {
      // TODO: Not yet implemented!!!
    }
  }
}
