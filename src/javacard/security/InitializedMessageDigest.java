/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.security;

import javacard.framework.JCSystem;
import javacard.framework.SystemException;

/**
 * The <code>InitializedMessageDigest</code> class is a subclass of the base
 * class <code>MessageDigest</code>. This class is used to generate a hash
 * representing a specified message but with the additional capability to
 * initialize the starting hash value corresponding to a previously hashed part
 * of the message. Implementations of <code>InitializedMessageDigest</code>
 * algorithms must extend this class and implement all the abstract methods.
 * <p>
 * A tear or card reset event resets a <code>InitializedMessageDigest</code>
 * object to the initial state (state upon construction).
 * <p>
 * Even if a transaction is in progress, update of intermediate result state in
 * the implementation instance shall not participate in the transaction.
 *
 * @since 2.2.2
 */
public abstract class InitializedMessageDigest extends MessageDigest {

  /**
   * This method initializes the starting hash value in place of the default
   * value used by the <code>MessageDigest</code> superclass. The starting
   * hash value represents the previously computed hash (using the same
   * algorithm) of the first part of the message. The remaining bytes of the
   * message must be presented to this <code>InitializedMessageDigest</code>
   * object via the <code>update</code> and <code>doFinal</code> methods
   * to generate the final message digest.
   * <p>
   * Note:
   * <ul>
   * <li><em>The maximum allowed value of the byte length of the first part of
   * the message is algorithm specific</em>
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
   *                {@link #ALG_SHA ALG_SHA}).
   *                </ul>
   */
  public abstract void
  setInitialDigest(byte[] initialDigestBuf, short initialDigestOffset,
                   short initialDigestLength, byte[] digestedMsgLenBuf,
                   short digestedMsgLenOffset, short digestedMsgLenLength)
      throws CryptoException;

  /**
   * The {@code OneShot} class is a specialization of the
   * {@code InitializedMessageDigest} class intended to support efficient
   * one-shot hash operations that may avoid persistent memory writes entirely.
   * The
   * {@code OneShot} class uses a delegation model where
   * calls are delegated to an instance of a
   * {@code InitializedMessageDigest}-implementing class configured for one-shot
   * use.
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
   * InitializedMessageDigest.OneShot dig = null;
   * try {
   *     dig = InitializedMessageDigest.OneShot.open(MessageDigest.ALG_SHA);
   *     dig.setInitialDigest(initialDigData, (short) 2, (short)
   * initialDigData.length, initialDigData, (short) 0, (short) 2);
   *     dig.doFinal(someInData, (short) 0, (short) someInData.length, digData,
   * (short) 0); } catch (CryptoException ce) {
   *     // Handle exception
   * } finally {
   *     if (dig != null) {
   *         dig.close();
   *         dig = null;
   *     }
   * }
   * ...
   * </pre>
   *
   * @since 3.0.5
   */
  public static final class OneShot extends InitializedMessageDigest {

    /**
     * Closes and acquires a JCRE owned temporary Entry Point Object instance of
     * {@code OneShot} with the selected algorithm.
     *
     * @param algorithm the desired message digest algorithm. Valid codes listed
     * in {@code ALG_*} constants above, for example, {@link #ALG_SHA ALG_SHA}.
     * @return the {@code InitializedMessageDigest} object instance of the
     * requested algorithm.
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
      return null;
    }

    /**
     * Closes and releases this JCRE owned temporary instance of the
     * {@code OneShot} object for reuse. If this method is
     * called again this method does nothing.
     *
     * @throws SecurityException if this JCRE owned temporary instance of the
     * {@code OneShot} object was opened in a context different from that of the
     * caller.
     */
    public void close() {
      // TODO: Not yet implemented!!!
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
      return 0;
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
      // TODO: Not yet implemented!!!
      CryptoException.throwIt(CryptoException.ILLEGAL_USE);
    }
  }
}
