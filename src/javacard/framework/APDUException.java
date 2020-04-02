/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * <code>APDUException</code> represents an <code>APDU</code> related
 * exception.
 * <p>
 * The <code>APDU</code> class throws Java Card runtime environment-owned
 * instances of <code>APDUException</code>.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details.
 *
 * @see APDU APDU
 */

public class APDUException extends CardRuntimeException {

  /**
   * This <code>APDUException</code> reason code indicates that the method
   * should not be invoked based on the current state of the APDU.
   */
  public static final short ILLEGAL_USE = 1;

  /**
   * This reason code is used by the <code>APDU.sendBytes()</code> method to
   * indicate that the sum of buffer offset parameter and the byte length
   * parameter exceeds the APDU buffer size.
   */
  public static final short BUFFER_BOUNDS = 2;

  /**
   * This reason code is used by the <code>APDU.setOutgoingLength()</code>
   * method to indicate
   * <code>APDUException.BAD_LENGTH</code> if <code>len</code> is
   * negative, or greater than 256 and the currently selected applet does not
   * implement the <code>javacardx.apdu.ExtendedLength</code> interface, or
   * if non BLOCK CHAINED data transfer is requested and <code>len</code> is
   * greater than (IFSD-2), where IFSD is the Outgoing FlashBlock Size. The -2
   * accounts for the status bytes in T=1.
   */
  public static final short BAD_LENGTH = 3;

  /**
   * This reason code indicates that an unrecoverable error occurred in the
   * I/O transmission layer.
   */
  public static final short IO_ERROR = 4;

  /**
   * This reason code indicates that during T=0 protocol, the CAD did not
   * return a GET RESPONSE command in response to a <code>61xx</code> response
   * status to send additional data. The outgoing transfer has been aborted. No
   * more data or status can be sent to the CAD in this
   * <code>Applet.process()</code> method.
   */
  public static final short NO_T0_GETRESPONSE = 0xAA;

  /**
   * This reason code indicates that during T=1 protocol, the CAD returned an
   * ABORT S-FlashBlock command and aborted the data transfer. The incoming or
   * outgoing transfer has been aborted. No more data can be received from the
   * CAD. No more data or status can be sent to the CAD in this
   * <code>Applet.process()</code> method.
   */
  public static final short T1_IFD_ABORT = 0xAB;

  /**
   * This reason code indicates that during T=0 protocol, the CAD did not
   * reissue the same APDU command with the corrected length in response to a
   * <code>6Cxx</code> response status to request command reissue with the
   * specified length. The outgoing transfer has been aborted. No more data or
   * status can be sent to the CAD in this <code>Applet.process()</code> method.
   */
  public static final short NO_T0_REISSUE = 0xAC;

  /**
   * Constructs an APDUException. To conserve on resources use
   * <code>throwIt()</code> to use the Java Card runtime environment-owned
   * instance of this class.
   *
   * @param reason
   *            the reason for the exception.
   */
  public APDUException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>APDUException</code> with the specified reason.
   * <p>
   * Java Card runtime environment-owned instances of exception classes are
   * temporary Java Card runtime environment Entry Point Objects and can be
   * accessed from any applet context. References to these temporary objects
   * cannot be stored in class variables or instance variables or array
   * components. See
   * <em>Runtime Environment Specification, Java Card Platform, Classic
   * Edition</em>, section 6.2.1 for details.
   *
   * @param reason
   *            the reason for the exception
   * @exception APDUException
   *                always
   */
  public static void throwIt(short reason) {
    APDUException exception = new APDUException(reason);
    throw exception;
  }
}
