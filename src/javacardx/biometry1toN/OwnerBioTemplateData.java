/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry1toN;

/**
 * The {@code OwnerBioTemplateData} interface should be implemented by the
 * applet which owns the Biometric Template Data containers. It extends the
 * {@code BioTemplateData} interface and adds functionality to enroll a
 * Biometric Template Data.
 *
 * @since 3.0.5
 */
public interface OwnerBioTemplateData extends BioTemplateData {

  /**
   * Initializes the enrollment of biometric template data. This method is also
   * used to update biometric template data. It resets the validated flag and,
   * in the update case, uninitializes the previous reference. Note: A correct
   * enrollment sequence is: {@link #init init},[{@link #update update}],{@link
   * #doFinal doFinal}. Calling {@code #init} and {@code doFinal} is mandatory,
   * calling {@code update} is optional.
   *
   * @param bArray byte array containing the biometric template data
   * @param offset starting offset in the {@code bArray}
   * @param length byte length of the template data in the {@code bArray}
   *
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#INVALID_DATA} if the submitted biometric
   * template data does not have the required format. </li>
   * </ul>
   */
  void init(byte[] bArray, short offset, short length) throws Bio1toNException;

  /**
   * Continues the enrollment of biometric template data. This method should
   * only be used if all the input data required for enrollment is not available
   * in one byte array. It can be called several times. Note: A correct
   * enrollment sequence is : {@link #init init},[{@link #update update}],{@link
   * #doFinal doFinal}. Calling {@code init} and {@code doFinal} is mandatory,
   * calling
   * {@code update} is optional.
   *
   * @param bArray byte array containing the biometric template data
   * @param offset starting offset in the {@code bArray}
   * @param length byte length of the biometric template data in the
   * {@code bArray}
   *
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#ILLEGAL_USE} if the biometric template data is
   * already initialized or the current enrollment state doesn't expect this
   * method.
   * </li>
   * <li>{@link Bio1toNException#INVALID_DATA} if the submitted
   * biometric template data does not have the required format. </li>
   * </ul>
   */
  void update(byte[] bArray, short offset, short length)
      throws Bio1toNException;

  /**
   * Finalizes the enrollment of biometric template data. Final action of
   * enrollment is to designate a biometric template data as being complete and
   * ready for use (marks the reference as initialized, resets the try counter
   * and unblocks the reference). This routine may also include some error
   * checking prior to the validation of biometric template data as ready for
   * use. Note: A correct enrollment sequence is:
   * {@link #init init},[{@link #update update}],{@link #doFinal doFinal}.
   * Calling {@code init} and
   * {@code doFinal} is mandatory, calling {@code update} is optional.
   *
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@code Bio1toNException.ILLEGAL_USE} if the biometric template data is
   * already initialized or the current enrollment state doesn't expect this
   * method.
   * </li>
   * <li>{@code Bio1toNException.INVALID_DATA} if the submitted
   * biometric template data does not have the required format. </li>
   * </ul>
   */
  void doFinal() throws Bio1toNException;
}
