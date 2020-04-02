/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry;

/**
 * The <code>OwnerBioTemplate</code> interface should be implemented by the
 * applet which owns the biometric template. It extends the
 * <code>BioTemplate</code> interface and adds functionality to enroll a
 * reference template.
 *
 * @since 2.2.2
 */
public interface OwnerBioTemplate extends BioTemplate {
  /**
   * Initializes the enrollment of a reference template. This method is also
   * used to update a reference template. It resets the validated flag and, in
   * the update case, uninitializes the previous reference. Note: A correct
   * enrollment sequence is :
   * <code>init</code>,[<code>update</code>],<code>doFinal</code>. Calling init
   * and <code>doFinal</code> is mandatory, calling <code>update</code> is
   * optional.
   *
   * @param bArray -
   *            byte array containing the data of the template
   * @param offset -
   *            starting offset in the <code>bArray</code>
   * @param length -
   *            byte length of the template data in the <code>bArray</code>
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.INVALID_DATA</code> if the
   *                submitted template data does not have the required format.
   *                </ul>
   */
  public void init(byte[] bArray, short offset, short length)
      throws BioException;

  /**
   * Continues the enrollment of a reference template. This method should only
   * be used if all the input data required for enrollment is not available in
   * one byte array. It can be called several times. Note: A correct
   * enrollment sequence is :
   * <code>init</code>,[<code>update</code>],<code>doFinal</code>. Calling
   * <code>init</code> and <code>doFinal</code> is mandatory, calling
   * <code>update</code> is optional.
   *
   * @param bArray -
   *            byte array containing the data of the template
   * @param offset -
   *            starting offset in the <code>bArray</code>
   * @param length -
   *            byte length of the template data in the <code>bArray</code>
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.ILLEGAL_USE</code> if the
   *                reference is already initialized or the current enrollment
   *                state doesn't expect this method.
   *                <li><code>BioException.INVALID_DATA</code> if the
   *                submitted template data does not have the required format.
   *                </ul>
   */
  public void update(byte[] bArray, short offset, short length)
      throws BioException;

  /**
   * Finalizes the enrollment of a reference template. Final action of
   * enrollment is to designate a reference template as being complete and
   * ready for use (marks the reference as initialized, resets the try counter
   * and unblocks the reference). This routine may also include some error
   * checking prior to the validation of reference template as ready for use.
   * Note: A correct enrollment sequence is :
   * <code>init</code>,[<code>update</code>],<code>doFinal</code>. Calling
   * <code>init</code> and <code>doFinal</code> is mandatory, calling
   * <code>update</code> is optional.
   *
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.ILLEGAL_USE</code> if the
   *                reference is already initialized or the current enrollment
   *                state doesn't expect this method.
   *                <li><code>BioException.INVALID_DATA</code> if the
   *                submitted template data does not have the required format.
   *                </ul>
   */
  public void doFinal() throws BioException;

  /**
   * Resets the validated flag, unblocks the reference, updates the try limit
   * value and resets the try counter to the try limit value.
   *
   * @param newTryLimit -
   *            the number of tries allowed before the reference is blocked.
   *            <code>newTryLimit</code> must be at least 1.
   * @exception BioException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>BioException.ILLEGAL_VALUE</code> if the
   *                <code>newTryLimit</code> parameter is less than 1.
   *                </ul>
   */
  public void resetUnblockAndSetTryLimit(byte newTryLimit) throws BioException;
}
