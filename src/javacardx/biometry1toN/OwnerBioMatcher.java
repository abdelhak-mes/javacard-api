/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry1toN;

/**
 * The {@code OwnerBioMatcher} interface should be implemented by the applet
 * which owns the {@code BioMatcher}. It extends the {@code BioMatcher}
 * interface and adds functionality to enroll one or more Biometric Template
 * Data containers.
 *
 * @since 3.0.5
 */
public interface OwnerBioMatcher extends BioMatcher {

  /**
   * Enrolls the provided {@code BioTemplateData} at the specified index. The
   * provided {@code BioTemplateData} must be loaded/initialized.
   * <p>
   * This method has to be called several times to add all the required
   * {@code BioTemplateData} for 1 to N matching
   * <p>
   * If the method is called with the same index several times, the former
   * {@code BioTemplateData} added to that index is replaced by the new one. If
   * the method is called with a {@code null} reference for the {@code
   * BioTemplateData}, the former {@code BioTemplateData} is removed.
   *
   * When {@code OwnerBioTemplateData} are already enrolled with a {@code
   * OwnerBioMatcher} modifying its data will impact the {@code OwnerBioMatcher}
   * (stored as reference).
   *
   * @param index the index where the {@code BioTemplateData} must be added.
   * @param templateData the {@code BioTemplateData} to enroll.
   *
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#ILLEGAL_VALUE} if the offset is invalid.</li>
   * <li>{@link Bio1toNException#NO_BIO_TEMPLATE_ENROLLED} if the provided
   * {@code BioTemplateData} is uninitialized.</li> <li>{@link
   * Bio1toNException#MISMATCHED_BIO_TYPE} if the type of the provided {@code
   * BioTemplateData} does not match that of this
   * {@code BioMatcher}.</li>
   * </ul>
   * @throws SecurityException if the reference template is not own by the
   * caller.
   */
  void putBioTemplateData(short index, BioTemplateData templateData)
      throws Bio1toNException, SecurityException;

  /**
   * Resets the validated flag, unblocks the enrolled {@code
   * OwnerBioTemplateData}s, updates the try limit value and resets the try
   * counter to the try limit value.
   *
   * Matching flags of an {@code OwnerBioMatcher} are independent of the
   * matching flags of {@code OwnerBioTemplateData}s enrolled with it.
   *
   * @param newTryLimit the number of tries allowed before the 1toN template is
   * blocked. {@code newTryLimit} must be at least 1.
   *
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#ILLEGAL_VALUE} if the {@code newTryLimit}
   * parameter is less than 1.</li>
   * </ul>
   */
  void resetUnblockAndSetTryLimit(byte newTryLimit) throws Bio1toNException;

  /**
   * Gets the index of the last matching {@code BioTemplateData}.
   *
   * @return the index of the last matching {@code BioTemplateData}.
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#ILLEGAL_USE} if no match was performed since
   * last reset.</li>
   * </ul>
   */
  @Override short getIndexOfLastMatchingBioTemplateData();

  /**
   * Gets the {@code OwnerBioTemplateData} enrolled at the specified index.
   *
   * This method is used to retrieve the 'orginal' {@code OwnerBioTemplateData}
   * in order to change its data using the {@link OwnerBioTemplateData#init
   * init},
   * {@link OwnerBioTemplateData#update update},
   * {@link OwnerBioTemplateData#doFinal doFinal} methods.
   *
   * @param index the index of the {@code OwnerBioTemplateData} to retrieve.
   *
   * @return {@code null} if no {@code OwnerBioTemplateData} is available at
   * the specified index.
   */
  @Override OwnerBioTemplateData getBioTemplateData(short index);
}
