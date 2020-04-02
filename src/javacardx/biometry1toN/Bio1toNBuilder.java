/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry1toN;

/**
 * The {@code Bio1toNBuilder} class is a {@link BioMatcher} object factory. It
 * builds empty/blank biometric matchers.
 *
 * @since 3.0.5
 */
public class Bio1toNBuilder {

  /**
   * Facial feature recognition (visage).
   */
  public static final byte FACIAL_FEATURE = (byte)1;

  /**
   * Pattern is a voice sample (specific or unspecified speech).
   */
  public static final byte VOICE_PRINT = (byte)2;
  /**
   * Fingerprint identification (any finger).
   */
  public static final byte FINGERPRINT = (byte)3;
  /**
   * Pattern is a scan of the eye's iris.
   */
  public static final byte IRIS_SCAN = (byte)4;
  /**
   * Pattern is an infrared scan of the blood vessels of the retina of the eye.
   */
  public static final byte RETINA_SCAN = (byte)5;
  /**
   * Hand geometry ID is based on overall geometry/shape of the hand.
   */
  public static final byte HAND_GEOMETRY = (byte)6;
  /**
   * Written signature dynamics ID (behavioral).
   */
  public static final byte SIGNATURE = (byte)7;
  /**
   * Keystrokes dynamics (behavioral).
   */
  public static final byte KEYSTROKES = (byte)8;
  /**
   * Lip movement (behavioral).
   */
  public static final byte LIP_MOVEMENT = (byte)9;
  /**
   * Thermal Face Image.
   */
  public static final byte THERMAL_FACE = (byte)10;
  /**
   * Thermal Hand Image.
   */
  public static final byte THERMAL_HAND = (byte)11;
  /**
   * Gait (behavioral).
   */
  public static final byte GAIT_STYLE = (byte)12;
  /**
   * Body Odor.
   */
  public static final byte BODY_ODOR = (byte)13;
  /**
   * Pattern is a DNA sample for matching.
   */
  public static final byte DNA_SCAN = (byte)14;
  /**
   * Ear geometry ID is based on overall geometry/shape of the ear.
   */
  public static final byte EAR_GEOMETRY = (byte)15;
  /**
   * Finger geometry ID is based on overall geometry/shape of a finger.
   */
  public static final byte FINGER_GEOMETRY = (byte)16;
  /**
   * Palm geometry ID is based on overall geometry/shape of a palm.
   */
  public static final byte PALM_GEOMETRY = (byte)17;
  /**
   * Pattern is an infrared scan of the vein pattern in a face, wrist, or, hand.
   */
  public static final byte VEIN_PATTERN = (byte)18;

  /**
   * General password (a PIN is a special case of the password). Note that this
   * is not a biometric, but is nevertheless a pattern that must be matched for
   * security purposes, and since it is frequently combined with biometrics for
   * security, we provide a code here to assist with that combination.
   */
  public static final byte PASSWORD = (byte)31;

  /**
   * The default value of the provider specific initialization information,
   * {@code initParam} parameter in the {@code buildBioTemplate()} method.
   */
  public static final byte DEFAULT_INITPARAM = (byte)0;

  /**
   * Creates an empty/blank (un-initialized) {@code OwnerBioMatcher} instance of
   * the specified biometric type and from the default biometric provider. At
   * least one {@code OwnerBioTemplateData} should be enrolled with this
   * {@code OwnerBioMatcher}.
   *
   * @param maxNbOfBioTemplateData the maximum number of
   * {@code BioTemplateData} to be supported. {@code maxNbOfBioTemplateData}
   * must be at least 1.
   * @param bioType the biometric type of {@code BioTemplateData} to be
   * supported. Valid codes are listed in the biometric pattern type constants.
   * @param tryLimit the maximum unsuccessful matches before matcher is blocked.
   * {@code tryLimit} must be at least 1.
   * @return the {@code OwnerBioMatcher} object instance of the requested
   * biometric type and {@code tryLimit} access.
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#BIO_TEMPLATE_DATA_CAPACITY_EXCEEDED} if
   * {@code maxNbOfBioTemplateData} parameter exceeds the supported
   * capacity.</li>
   * <li>{@link Bio1toNException#ILLEGAL_VALUE} if {@code tryLimit} parameter or
   * {@code maxNbOfBioTemplateData} is less than 1.</li>
   * <li>{@link Bio1toNException#UNSUPPORTED_BIO_TYPE} if the requested
   * biometric type associated with the specified {@code bioType} is not
   * supported.</li>
   * </ul>
   */
  public static OwnerBioMatcher buildBioMatcher(short maxNbOfBioTemplateData,
                                                byte bioType, byte tryLimit)
      throws Bio1toNException {
    return buildBioMatcher(maxNbOfBioTemplateData, bioType, tryLimit, null,
                           DEFAULT_INITPARAM);
  }

  /**
   * Creates an empty/blank (un-initialized) {@code OwnerBioMatcher} instance of
   * the specified biometric type and from the specified biometric provider. At
   * least one
   * {@code OwnerBioTemplateData} should be enrolled with this
   * {@code OwnerBioMatcher}. This method takes in a provider identifier (RID)
   * and an initialization parameter which should be passed to the constructor
   * of the appropriate {@code OwnerBioMatcher} implementation.
   *
   * @param maxNbOfBioTemplateData the maximum number of
   * {@code BioTemplateData} to be supported. {@code maxNbOfBioTemplateData}
   * must be at least 1.
   * @param bioType the type of the biometric templates to be supported. Valid
   *     codes
   * are listed in the biometric pattern type constants.
   * @param tryLimit maximum unsuccessful matches before matcher is blocked.
   * {@code tryLimit} must be at least 1.
   * @param RID the RID of the provider of {@code OwnerBioMatcher}
   * implementation. null value means default provider
   * @param initParam the provider specific initialization information for the
   * {@code OwnerBioMatcher} instance. {@link #DEFAULT_INITPARAM} is default
   * value.
   *
   * @return the {@code OwnerBioMatcher} object instance of the requested
   * {@code bioType} and {@code tryLimit} access.
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#BIO_TEMPLATE_DATA_CAPACITY_EXCEEDED} if
   * {@code maxNbOfBioTemplateData} parameter exceeds the supported
   * capacity.</li>
   * <li>{@link Bio1toNException#ILLEGAL_VALUE} if {@code tryLimit} parameter or
   * {@code maxNbOfBioTemplateData} is less than 1.</li>
   * <li>{@link Bio1toNException#UNSUPPORTED_BIO_TYPE} if the requested
   * biometric type associated with the specified {@code bioType} and provider
   * {@code RID} is not supported.</li>
   * </ul>
   */
  public static OwnerBioMatcher buildBioMatcher(short maxNbOfBioTemplateData,
                                                byte bioType, byte tryLimit,
                                                byte[] RID, byte initParam)
      throws Bio1toNException {
    if (maxNbOfBioTemplateData < 1 || tryLimit < 1) {
      Bio1toNException.throwIt(Bio1toNException.ILLEGAL_VALUE);
    }

    switch (bioType) {
    default:
      Bio1toNException.throwIt(Bio1toNException.UNSUPPORTED_BIO_TYPE);
    }
    return null;
  }

  /**
   * Creates an empty/blank (un-initialized) {@code BioTemplateData} instance
   * of the specified biometric type and from the default biometric provider.
   *
   * @param bioType the biometric type of the {@code BioTemplateData} to be
   * created. Valid codes are listed in the biometric pattern type constants.
   * @return a {@code OwnerBioTemplateData} instance of the requested biometric
   * type and from the default biometric provider.
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#UNSUPPORTED_BIO_TYPE} if the requested
   * biometric type associated with the specified {@code bioType} is not
   * supported.</li>
   * </ul>
   */
  public static OwnerBioTemplateData buildBioTemplateData(byte bioType)
      throws Bio1toNException {
    return Bio1toNBuilder.buildBioTemplateData(bioType, null);
  }

  /**
   * Creates an empty/blank (un-initialized) {@code BioTemplateData} instance
   * of the specified biometric type and from the specified biometric provider.
   *
   * @param bioType the biometric type of the {@code BioTemplateData} to be
   * created. Valid codes are listed in the biometric pattern type constants.
   * @param RID the RID of the provider of {@code OwnerBioTemplateData}
   * implementation. {@code null} value stands for the default provider.
   * @return a {@code OwnerBioTemplateData} instance of the requested biometric
   * type and biometric provider.
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#UNSUPPORTED_BIO_TYPE} if the requested
   * biometric type associated with the specified {@code bioType} and provider
   * {@code RID} is not supported.</li>
   * </ul>
   */
  public static OwnerBioTemplateData buildBioTemplateData(byte bioType,
                                                          byte[] RID)
      throws Bio1toNException {
    switch (bioType) {
    default:
      Bio1toNException.throwIt(Bio1toNException.UNSUPPORTED_BIO_TYPE);
    }
    return null;
  }
}
