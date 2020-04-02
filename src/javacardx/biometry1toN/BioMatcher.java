/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry1toN;

/**
 * The {@code BioMatcher} interface is the base interface for biometric
 * matching. It provides the user interface for accessing biometric
 * functionality.
 * <br>
 * Several Biometric Template Data containers ({@link BioTemplateData}) can be
 * enrolled in the same {@code BioMatcher}. {@code BioMatcher} is supporting
 * both 1 to 1 matching and 1 to N matching when N {@code BioTemplateData}
 * instances are enrolled.
 *
 * @since 3.0.5
 */
public interface BioMatcher {

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
   * Indicates whether this {@code BioMatcher} has been loaded with at least one
   * {@code BioTemplateData} and is ready for matching functions. This is
   * independent of whether or not the match process has been initialized (see
   * {@link #initMatch initMatch}).
   *
   * @return {@code true} if initialized, {@code false} otherwise.
   */
  boolean isInitialized();

  /**
   * Indicates whether a matching session was successfully since the last card
   * reset or last call to {@link #reset}.
   * <p>
   * In addition to returning a {@code boolean} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the
   * {@link javacardx.security.SensitiveResult SensitiveResult} class, if
   * supported by the platform.
   *
   * @return {@code true} if validated, {@code false} otherwise.
   */
  public boolean isValidated();

  /**
   * Resets the {@code validated} flag associated with this {@code BioMatcher}.
   * This could be appropriate as a last action after an access is completed.
   */
  void reset();

  /**
   * Gets the number of times remaining that an incorrect candidate can be
   * presented before the {@code BioMatcher} is blocked.
   * <p>
   * In addition to returning a {@code byte} result, platform-implementations of
   * this method set the result in an internal state which can be rechecked
   * using assertion methods of the
   * {@link javacardx.security.SensitiveResult SensitiveResult} class, if
   * supported by the platform.
   *
   * @return the number of tries remaining.
   *
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#NO_BIO_TEMPLATE_ENROLLED} if no
   * {@code BioTemplateData} has been enrolled.</li>
   * </ul>
   */
  byte getTriesRemaining();

  /**
   * Gets the biometric type. Valid types are described in
   * {@link Bio1toNBuilder}.
   *
   * @return the biometric general type.
   */
  byte getBioType();

  /**
   * Gets the matching algorithm version and ID.
   *
   * @param dest destination byte array.
   * @param offset starting offset within the destination byte array.
   *
   * @return the number of bytes written in the destination byte array.
   */
  short getVersion(byte[] dest, short offset);

  /**
   * Initialize or re-initialize a biometric matching session. If this
   * {@code BioMatcher} is not blocked, a matching session starts and, before
   * any other processing, the {@code validated} flag is reset, the internal
   * match state is set to {@link #MATCH_NEEDS_MORE_DATA}, and the try counter
   * is decremented. If the try counter has reached zero, the this
   * {@code BioMatcher} is blocked. This method results in one of the following:
   * <ul>
   * <li>The matching session ends with success state if at least one of the
   * {@link BioTemplateData} enrolled with this {@code BioMatcher} is matching.
   * The validated flag is set and the try counter is reset to its maximum.
   * <li>The matching session ends with failed state if none of the
   * {@link BioTemplateData} enrolled with this {@code BioMatcher} is matching.
   * The validated flag remains in the reset state.
   * <li>The matching session continues if the matching needs more data. The
   * {@link #match match} method has to be called to continue the matching
   * session.
   * </ul>
   * <p>
   * If this {@code BioMatcher} is blocked, no matching session starts.
   * <p>
   * Notes:
   * <ul>
   * <li>A correct matching sequence is :
   * {@link #initMatch initMatch},[{@link #match match}]. Calling
   * {@code initMatch} is mandatory, calling {@code match} is optional.
   * <li>If a matching session is in progress (case needs more data), a call to
   * {@code initMatch} makes the current session to be discarded and starts a
   * new matching session.
   * <li>Even if a transaction is in progress, internal state such as the try
   * counter, the validated flag and the blocking state must not be
   * conditionally updated.
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the
   * {@link javacardx.security.SensitiveResult SensitiveResult} class, if
   * supported by the platform.
   * <br>
   * The following code sample illustrates the use of {@code SensitiveResult}:
   * <pre>{@code
   * try {
   *     short matchResult = bioMatcher.initMatch( ...);
   *     SensitiveResult.assertEquals(matchResult);  // recheck for security
   *     while (matchResult == MATCH_NEEDS_MORE_DATA) {
   *          matchResult = bioMatcher.match(...);
   *     }
   *     SensitiveResult.assertEquals(matchResult);  // recheck for security
   *     if ((matchResult >= MINIMUM_SUCCESSFUL_MATCH_SCORE) {
   *        // grant service - user authenticated
   *     } else {
   *        // deny service - Bio Template mismatch
   *     }
   *  } finally {
   *     SensitiveResult.reset();
   *  }
   * }</pre>
   *
   * @param candidate the data or part of the data of the candidate.
   * @param offset the starting offset into the candidate array where the
   * candidate data is to be found.
   * @param length the number of bytes to be taken from the candidate array.
   * @return the matching score with the following meaning :
   * <ul>
   * <li> &gt;= {@link #MINIMUM_SUCCESSFUL_MATCH_SCORE} : the matching session
   * is successful.</li> <li> &gt;= 0 and &lt; {@link
   * #MINIMUM_SUCCESSFUL_MATCH_SCORE} : the matching session has failed.</li>
   * <li> = {@link #MATCH_NEEDS_MORE_DATA} : the matching session needs more
   * data.</li>
   * </ul>
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#INVALID_DATA} if the submitted candidate data
   * does not have the required format.</li>
   * <li>{@link Bio1toNException#NO_BIO_TEMPLATE_ENROLLED} if no
   * {@code BioTemplateData} has been enrolled.</li>
   * </ul>
   */
  short initMatch(byte[] candidate, short offset, short length)
      throws Bio1toNException;

  /**
   * Continues a biometric matching session. The exact return score value is
   * implementation dependent and can be used, for example, to code a confidence
   * rate. If a matching session is in progress, this method results in one of
   * the following:
   * <ul>
   * <li>The matching session ends with success state if at least one of the
   * {@code BioTemplateData} enrolled in this {@code BioMatcher} matches. The
   * validated flag is set and the try counter is reset to its maximum.
   * <li>The matching session ends with failed state if none of the
   * {@code BioTemplateData}s enrolled in this {@code BioMatcher} are matching.
   * <li>The matching session continues if the matching needs more data. The
   * {@code match} method has to be called to continue the matching session.
   * </ul>
   * Notes:
   * <ul>
   * <li>A correct matching sequence is:
   * {@link #initMatch initMatch},[{@link #match match}]. Calling
   * {@code initMatch} is mandatory, calling {@code match} is optional.
   * <li>Even if a transaction is in progress, internal state such as the try
   * counter, the validated flag and the blocking state must not be
   * conditionally updated.
   * </ul>
   * <p>
   * In addition to returning a {@code short} result, platform-implementations
   * of this method set the result in an internal state which can be rechecked
   * using assertion methods of the
   * {@link javacardx.security.SensitiveResult SensitiveResult} class, if
   * supported by the platform.
   * <br>
   * The following code sample illustrates the use of {@code SensitiveResult}:
   * <pre>{@code
   * try {
   *     short matchResult = bioMatcher.initMatch( ...);
   *     SensitiveResult.assertEquals(matchResult);  // recheck for security
   *     while (matchResult == MATCH_NEEDS_MORE_DATA) {
   *          matchResult = bioMatcher.match(...);
   *     }
   *     SensitiveResult.assertEquals(matchResult);  // recheck for security
   *     if ((matchResult >= MINIMUM_SUCCESSFUL_MATCH_SCORE) {
   *        // grant service - user authenticated
   *     } else {
   *        // deny service - Bio Template mismatch
   *     }
   *  } finally {
   *     SensitiveResult.reset();
   *  }
   * }</pre>
   *
   * @param candidate the data or part of the data of the candidate.
   * @param offset the starting offset into the candidate array where the
   * candidate data is to be found.
   * @param length number of bytes to be taken from the candidate array.
   * @return the matching score with the following meaning :
   * <ul>
   * <li> &gt;= {@link #MINIMUM_SUCCESSFUL_MATCH_SCORE} : the matching session
   * is successful.</li>
   * <li> &gt;= 0 and &lt; {@link #MINIMUM_SUCCESSFUL_MATCH_SCORE} : the
   * matching session has failed.</li>
   * <li> = {@link #MATCH_NEEDS_MORE_DATA} : the  matching session needs more
   * data.</li>
   * </ul>
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#ILLEGAL_USE} if used outside a matching
   * session.</li>
   * <li>{@link Bio1toNException#INVALID_DATA} if the submitted candidate data
   * does not have the required format. </li>
   * <li>{@link Bio1toNException#NO_BIO_TEMPLATE_ENROLLED} if no
   * {@code BioTemplateData} have been enrolled.</li>
   * </ul>
   */
  short match(byte[] candidate, short offset, short length)
      throws Bio1toNException;

  /**
   * Gets the maximum number of {@code BioTemplateData} that can be enrolled in
   * this {@code BioMatcher}.
   *
   * @return the maximum number of {@code BioTemplateData} that can be
   * enrolled.
   */
  short getMaxNbOfBioTemplateData();

  /**
   * Gets the index of the last matching {@code BioTemplateData}.
   *
   * @return the index of the last matching {@code BioTemplateData}.
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#ILLEGAL_USE} if no successful match occurred
   * since last reset.</li>
   * </ul>
   */
  short getIndexOfLastMatchingBioTemplateData();

  /**
   * Get the {@code BioTemplateData} enrolled at the specified index.
   *
   * This method is used to retrieve the {@code BioTemplateData} for readonly
   * use.
   *
   * @param index the index of the {@code BioTemplateData} to retrieve.
   *
   * @return {@code null} if no {@code BioTemplateData} is available at the
   * specified index.
   */
  BioTemplateData getBioTemplateData(short index);
}
