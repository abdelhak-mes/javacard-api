/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.apdu.util;

/**
 * The <code>APDUUtil</code> class contains utility methods to parse CLA byte
 * from a command APDU. All methods in <code>APDUUtil</code>, class are static
 * methods. <p>
 * @see javacard.framework.APDU APDU
 * @since 3.0.5
 */
public class APDUUtil {
  /**
   * Returns the logical channel number encoded in the <code>CLAbyte</code>
   * parameter which represents a CLA byte from a command APDU. A number in the
   * range 0-19 based on the CLA byte encoding is returned if the CLA contains
   * logical channel encoding. If the CLA byte does not contain logical channel
   * information, 0 is returned.
   * <p>
   * Note:
   * <ul>
   * <li><em>This method returns 0 if the CLA bits (b8,b7,b6) is
   * %b001 which is a CLA encoding reserved for future use(RFU),
   * or if CLA is 0xFF which is an invalid value as defined in
   * the ISO 7816-4:2013 specification.</em>
   * </ul>
   * <p>
   * See <em>Runtime Environment
   * Specification, Java Card Platform, Classic Edition</em>,
   * section 4.3 for encoding details.
   * </p>
   *
   * @param CLAbyte is CLA byte from a command APDU
   * @return logical channel number, if present, within the CLA byte, 0
   *         otherwise
   * @since 3.0.5
   * @see javacard.framework.APDU#getCLAChannel()
   */
  public static byte getCLAChannel(byte CLAbyte) {
    // TODO: Not yet implemented
    return 0;
  }

  /**
   * Returns <code>true</code> if encoding of the <code>CLAbyte</code>
   * parameter, which represents a CLA byte from a command APDU, indicates
   * secure messaging. The secure messaging information is in bits (b4,b3) for
   * commands with origin channel numbers 0-3, and in bit b6 for origin
   * channel numbers 4-19.
   * <p>
   * Note:
   * <ul>
   * <li><em>This method returns <code>false</code> if the CLA bits (b8,b7,b6)
   * is %b001 which is a CLA encoding reserved for future use(RFU), or if CLA is
   * 0xFF which is an invalid value as defined in the ISO 7816-4:2013
   * specification.</em>
   * </ul>
   * <p>
   * See <em>Runtime Environment Specification, Java Card
   * Platform, Classic Edition</em>, section 4.3 for encoding details.
   * </p>
   *
   * @param CLAbyte is CLA byte from a command APDU
   * @return <code>true</code> if the secure messaging bit(s) is(are)
   *         nonzero, <code>false</code> otherwise
   * @since 3.0.5
   * @see javacard.framework.APDU#isSecureMessagingCLA()
   */
  public static boolean isSecureMessagingCLA(byte CLAbyte) {
    // TODO: Not yet implemented
    return false;
  }

  /**
   * Based on encoding of the <code>CLAByte</code> parameter which represents a
   * CLA byte from a command APDU, returns whether an <code>APDU</code> command
   * is the first or part of a command chain. Bit b5 of the CLA byte if set,
   * indicates that the <code>APDU</code> is the first or part of a chain of
   * commands. <p> Note: <ul> <li><em>This method returns <code>false</code> if
   * the CLA bits (b8,b7,b6) is %b001 which is a CLA encoding reserved for
   * future use(RFU), or if CLA is 0xFF which is an invalid value as defined in
   * the ISO 7816-4:2013 specification.</em>
   * </ul>
   * <p>
   * See <em>Runtime Environment Specification, Java Card
   * Platform, Classic Edition</em>, section 4.3 for encoding details.
   * </p>
   *
   * @param CLAbyte is CLA byte from a command APDU
   * @return <code>true</code> if the CLA byte encoding indicates if an APDU
   * is not the last APDU of a command chain,
   * <code>false</code> otherwise.
   * @since 3.0.5
   * @see javacard.framework.APDU#isCommandChainingCLA()
   */
  public static boolean isCommandChainingCLA(byte CLAbyte) {
    // TODO: Not yet implemented
    return false;
  }

  /**
   * Returns whether the <code>CLAByte</code> parameter which represents a
   * CLA bye from a command APDU, corresponds to an interindustry command as
   * defined in ISO 7816-4:2013 specification. Bit b8 of the CLA byte if
   * <code>0</code>, indicates that the <code>APDU</code> is an interindustry
   * command.
   *
   * @param CLAbyte is CLA byte from a command APDU
   * @return <code>true</code> if this APDU CLA byte corresponds to an ISO
   *         interindustry command, <code>false</code> otherwise.
   * @since 3.0.5
   * @see javacard.framework.APDU#isISOInterindustryCLA()
   */
  public static boolean isISOInterindustryCLA(byte CLAbyte) {
    // TODO: Not yet implemented
    return false;
  }

  /**
   * Returns whether the current <code>CLAByte</code> parameter represents a
   * valid CLA byte. The CLA byte is invalid if the CLA bits (b8,b7,b6) is
   * %b001, which is a CLA encoding reserved for future use(RFU), or if CLA is
   * 0xFF which is an invalid value as defined in the ISO 7816-4:2013
   * specification. <p> See <em>Runtime Environment Specification, Java Card
   * Platform, Classic Edition</em>, section 4.3 for encoding details.
   * </p>
   *
   * @param CLAbyte is CLA byte from a command APDU
   * @return <code>true</code> if CLA byte is valid,
   *         <code>false</code> otherwise.
   * @since 3.0.5
   * @see javacard.framework.APDU#isValidCLA()
   */
  public static boolean isValidCLA(byte CLAbyte) {
    // TODO: Not yet implemented
    return false;
  }
}
