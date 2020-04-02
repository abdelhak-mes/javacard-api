/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry;

/**
 * The BioTemplate interface is the base interface for all biometric templates.
 * It provides the user interface for accessing biometric functionality.
 *
 * @since 2.2.2
 */
public interface BioTemplate {

  /**
   * The minimum successful matching score.
   */
  public static final short MINIMUM_SUCCESSFUL_MATCH_SCORE = (short)16384;
  /**
   * This negative score value indicates that more data are needed to continue
   * the matching session.
   */
  public static final short MATCH_NEEDS_MORE_DATA = (short)-1;

  /**
   * Returns true if the reference template is completely loaded and ready for
   * matching functions. This is independent of whether or not the match
   * process has been initialized (see <code>initMatch</code>).
   *
   * @return true if initialized, false otherwise.
   */
  public boolean isInitialized();

  /**
   * Returns true if the template has been successfully checked since the last
   * card reset or last call to <code>reset()</code>.
   * <p>
   * In addition to returning a {@code boolean} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @return true if validated, false otherwise.
   */
  public boolean isValidated();

  /**
   * Resets the validated flag associated with the reference template. This
   * could be appropriate as a last action after an access is completed.
   */
  public void reset();

  /**
   * Returns the number of times remaining that an incorrect candidate
   * template can be presented before the reference template is blocked.
   * <p>
   * In addition to returning a {@code byte} result, platform-implementations of
   * this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @return the number of tries remaining
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.NO_TEMPLATES_ENROLLED</code> if
   *                the reference template is uninitialized.
   *                </ul>
   */
  public byte getTriesRemaining();

  /**
   * Get the biometric type. Valid type are described in
   * <code>BioBuilder</code>.
   *
   * @return biometric general type.
   */
  public byte getBioType();

  /**
   * Get the matching algorithm version and ID.
   *
   * @param dest
   *            destination byte array.
   * @param offset
   *            starting offset within the destination byte array.
   * @return number of bytes written in the destination byte array.
   */
  public short getVersion(byte[] dest, short offset);

  /**
   * Get public part of the reference template. This method copies all or a
   * portion of the reference public data to the destination array.
   *
   * @param publicOffset
   *            starting offset within the public data.
   * @param dest
   *            destination byte array.
   * @param destOffset
   *            starting offset within the destination byte array.
   * @param length
   *            maximum length in bytes of the requested data.
   * @return number of bytes written to the destination byte array. 0 if
   *         public data are not available.
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.NO_TEMPLATES_ENROLLED</code> if
   *                the reference template is uninitialized.
   *                </ul>
   */
  public short getPublicTemplateData(short publicOffset, byte[] dest,
                                     short destOffset, short length)
      throws BioException;

  /**
   * Initialize or re-initialize a biometric matching session. The exact
   * return score value is implementation dependent and can be used, for
   * example, to code a confidence rate. If the reference is not blocked, a
   * matching session starts and, before any other processing, the validated
   * flag is reset and the try counter is decremented if the try counter has
   * reached zero, the reference is blocked.
   * <p>
   * This method results in one of the following:
   * <ul>
   * <li>The matching session ends with success state if the templates match.
   * The validated flag is set and the try counter is reset to its maximum.
   * <li>The matching session ends with failed state if the templates don't
   * match.
   * <li>The matching session continues if the matching needs more data. The
   * <code>match</code> method has to be called to continue the matching
   * session.
   * </ul>
   * <p>
   * If the reference is blocked, no matching session starts and this method
   * returns 0. Notes:
   * <ul>
   * <li>A correct matching sequence is :
   * <code>initMatch</code>,[<code>match</code>]. Calling <code>initMatch</code>
   * is mandatory, calling <code>match</code> is optional. <li>If a matching
   * session is in progress (case needs more data), a call to
   * <code>initMatch</code> makes the current session to fail and starts a new
   * matching session. <li>Even if a transaction is in progress, internal state
   * such as the try counter, the validated flag and the blocking state must not
   * be conditionally updated.
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @param candidate -
   *            the data or part of the data of the candidate template.
   * @param offset -
   *            starting offset into the candidate array where the candidate
   *            data is to be found.
   * @param length -
   *            number of bytes to be taken from the candidate array.
   * @return the matching score with the following meaning :
   *         <ul>
   *         <li> &ge; MINIMUM_SUCCESSFUL_MATCH_SCORE : the matching session is
   *         successful
   *         <li> &ge; 0 and &lt; MINIMUM_SUCCESSFUL_MATCH_SCORE : the matching
   *         session has failed
   *         <li> = MATCH_NEEDS_MORE_DATA : the matching session needs more
   *         data
   *         </ul>
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.INVALID_DATA</code> if the
   *                submitted candidate template data does not have the
   *                required format.
   *                <li><code>BioException.NO_TEMPLATES_ENROLLED</code> if
   *                the reference template is uninitialized.
   *                </ul>
   */
  public short initMatch(byte[] candidate, short offset, short length)
      throws BioException;

  /**
   * Continues a biometric matching session. The exact return score value is
   * implementation dependent and can be used, for example, to code a
   * confidence rate.
   * If a matching session is in progress, this method
   * results in one of the following:
   * <ul>
   * <li>The matching session ends with success state if the templates match.
   * The validated flag is set and the try counter is reset to its maximum.
   * <li>The matching session ends with failed state if the templates don't
   * match.
   * <li>The matching session continues if the matching needs more data. The
   * <code>match</code> method has to be called to continue the matching
   * session.
   * </ul>
   * Notes:
   * <ul>
   * <li>A correct matching sequence is :
   * <code>initMatch</code>,[<code>match</code>]. Calling <code>initMatch</code>
   * is mandatory, calling <code>match</code> is optional. <li>Even if a
   * transaction is in progress, internal state such as the try counter, the
   * validated flag and the blocking state must not be conditionally updated.
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the {@link javacardx.security.SensitiveResult
   * SensitiveResult} class, if supported by the platform.
   *
   * @param candidate -
   *            the data or part of the data of the candidate template.
   * @param offset -
   *            starting offset into the candidate array where the candidate
   *            data is to be found.
   * @param length -
   *            number of bytes to be taken from the candidate array.
   * @return the matching score with the following meaning :
   *         <ul>
   *         <li> &ge; MINIMUM_SUCCESSFUL_MATCH_SCORE : the matching session is
   *         successful
   *         <li> &ge; 0 and &lt; MINIMUM_SUCCESSFUL_MATCH_SCORE : the matching
   *         session has failed
   *         <li> = MATCH_NEEDS_MORE_DATA : the matching session needs more
   *         data
   *         </ul>
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.ILLEGAL_USE</code> if used
   *                outside a matching session.
   *                <li><code>BioException.INVALID_DATA</code> if the
   *                submitted candidate template data does not have the
   *                required format.
   *                <li><code>BioException.NO_TEMPLATES_ENROLLED</code> if
   *                the reference template is uninitialized.
   *                </ul>
   */
  public short match(byte[] candidate, short offset, short length)
      throws BioException;
}
