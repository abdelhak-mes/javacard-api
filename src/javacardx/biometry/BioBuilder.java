/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry;

/**
 * Builds an empty/blank biometric reference template.
 *
 * @since 2.2.2
 */
public final class BioBuilder {

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
   * Pattern is an infrared scan of the blood vessels of the retina of the
   * eye.
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
   * Pattern is an infrared scan of the vein pattern in a face, wrist, or,
   * hand.
   */
  public static final byte VEIN_PATTERN = (byte)18;

  /**
   * General password (a PIN is a special case of the password). Note that
   * this is not a biometric, but is nevertheless a pattern that must be
   * matched for security purposes, and since it is frequently combined with
   * biometrics for security, we provide a code here to assist with that
   * combination.
   */
  public static final byte PASSWORD = (byte)31;

  /**
   * The default value of the provider specific initialization information,
   * <code>initParam</code> parameter in the <code>buildBioTemplate()</code>
   * method.
   */
  public static final byte DEFAULT_INITPARAM = (byte)0;

  /**
   * Creates an empty/blank biometric reference template instance of the
   * default biometric provider with default initialization parameter.
   *
   * @param bioType
   *            the type of the template to be generated. Valid codes are
   *            listed in the biometric pattern type constants.
   * @param tryLimit
   *            maximum unsuccessful matches before template is blocked.
   *            <code>tryLimit</code> must be at least 1.
   * @return the <code>OwnerBioTemplate</code> object instance of the
   *         requested <code>bioType</code> and <code>tryLimit</code>
   *         access.
   * @throws BioException
   *             with the following reason codes:
   *             <ul>
   *             <li><code>BioException.ILLEGAL_VALUE</code> if
   *             <code>tryLimit</code> parameter is less than 1.
   *             <li><code>BioException.NO_SUCH_BIO_TEMPLATE</code> if the
   *             requested template associated with the specified
   *             <code>bioType</code> is not supported.
   *             </ul>
   */
  public static OwnerBioTemplate buildBioTemplate(byte bioType, byte tryLimit)
      throws BioException {
    return buildBioTemplate(bioType, tryLimit, null, DEFAULT_INITPARAM);
  }

  /**
   * Creates an empty/blank biometric reference template. This method takes in
   * a provider identifier (RID) and an initialization parameter which should
   * be passed to the constructor of the appropriate
   * <code>OwnerBioTemplate</code> implementation.
   *
   * @param bioType
   *            the type of the template to be generated. Valid codes are
   *            listed in the biometric pattern type constants.
   * @param tryLimit
   *            maximum unsuccessful matches before template is blocked.
   *            <code>tryLimit</code> must be at least 1.
   * @param RID
   *            the RID of the provider of <code>OwnerBioTemplate</code>
   *            implementation. null value means default provider
   * @param initParam
   *            the provider specific initialization information for the
   *            <code>OwnerBioTemplate</code> instance.
   *            <code>DEFAULT_INITPARAM</code> is default value.
   * @return the <code>OwnerBioTemplate</code> object instance of the
   *         requested <code>bioType</code> and <code>tryLimit</code>
   *         access.
   * @throws BioException
   *             with the following reason codes:
   *             <ul>
   *             <li><code>BioException.ILLEGAL_VALUE</code> if
   *             <code>tryLimit</code> parameter is less than 1.
   *             <li><code>BioException.NO_SUCH_BIO_TEMPLATE</code> if the
   *             requested template associated with the specified
   *             <code>bioType</code> is not supported.
   *             </ul>
   */
  public static OwnerBioTemplate buildBioTemplate(byte bioType, byte tryLimit,
                                                  byte[] RID, byte initParam)
      throws BioException {
    if (tryLimit < 1) {
      BioException.throwIt(BioException.ILLEGAL_VALUE);
    }

    switch (bioType) {
    default:
      BioException.throwIt(BioException.NO_SUCH_BIO_TEMPLATE);
    }

    return null;
  }
}
