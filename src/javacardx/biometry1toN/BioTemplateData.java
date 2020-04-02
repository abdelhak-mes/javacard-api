/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry1toN;

/**
 * The {@code BioTemplateData} interface is the base interface for all
 * biometric template data container. It provides the user interface for
 * accessing biometric template data.
 *
 * @since 3.0.5
 */
public interface BioTemplateData {

  /**
   * Gets the biometric type. Valid type are described in
   * {@link Bio1toNBuilder}.
   *
   * @return the biometric type.
   */
  byte getBioType();

  /**
   * Indicates whether the {@code BioTemplateData} is completely
   * loaded/initialized.
   *
   * @return {@code true} if initialized, {@code false} otherwise.
   */
  boolean isInitialized();

  /**
   * Gets the public part of the biometric template data. This method copies all
   * or a portion of the reference public data to the destination array.
   *
   * @param publicOffset the starting offset within the public data.
   * @param dest the destination byte array.
   * @param destOffset the starting offset within the destination byte array.
   * @param length the maximum length in bytes of the requested data.
   *
   * @return the number of bytes written to the destination byte array;
   * {@code 0} if no public data are available.
   *
   * @throws Bio1toNException with the following reason codes:
   * <ul>
   * <li>{@link Bio1toNException#NO_BIO_TEMPLATE_ENROLLED} if this
   * {@code BioTemplateData} is uninitialized.</li>
   * </ul>
   */
  short getPublicData(short publicOffset, byte[] dest, short destOffset,
                      short length) throws Bio1toNException;
}
