/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework.service;

import fr.gouv.ssi.nativeimpl.NativeImplementation;
import javacard.framework.APDU;
import javacard.framework.ISO7816;
import javacard.framework.Util;

/**
 * This class should be used as the base class for implementing services. It
 * provides a default implementation for the methods defined in the
 * <code>Service</code> interface, and defines a set of helper methods that
 * manage the APDU buffer to enable co-operation among different Services.
 * <P>
 * The <code>BasicService</code> class uses the state of APDU processing to
 * enforce the validity of the various helper operations. It expects and
 * maintains the following Common Service Format (CSF) of data in the APDU
 * Buffer corresponding to the various APDU processing states (See
 * {@link APDU APDU} ):
 *
 * <pre>
 * Init State format of APDU Buffer. This format corresponds to the
 * APDU processing state -
 * <code>
 * STATE_INITIAL
 * </code>
 *  :
 *  0     1    2    3    4    5  &lt;- offset
 * +------------------------------------------------------------+
 * | CLA | INS | P1 | P2 | P3 | ... Implementation dependent ...|
 * +------------------------------------------------------------+
 *
 *
 * Input Ready format of APDU Buffer. This format corresponds
 * to the APDU processing state -
 * <code>
 * STATE_FULL_INCOMING
 * </code>
 * .
 *  0     1    2    3    4    5  &lt;- offset
 * +------------------------------------------------------------+
 * | CLA | INS | P1 | P2 | Lc | Incoming Data( Lc bytes )       |
 * +------------------------------------------------------------+
 *
 *
 * Output Ready format of APDU Buffer. This format corresponds
 * to the APDU processing status -
 * <code>
 * STATE_OUTGOING
 * </code>
 *  ..
 * <code>
 * STATE_FULL_OUTGOING
 * </code>
 *  0     1    2     3     4    5  &lt;- offset
 * +------------------------------------------------------------+
 * | CLA | INS | SW1 | SW2 | La | Outgoing Data( La bytes )     |
 * +------------------------------------------------------------+
 *
 *
 *
 * </pre>
 *
 * <p>
 * When the APDU buffer is in the Init and Input Ready formats, the helper
 * methods allow input access methods but flag errors if output access is
 * attempted. Conversely, when the APDU buffer is in the Output format, input
 * access methods result in exceptions.
 * <p>
 * The Common Service Format (CSF) of the APDU Buffer is only defined for APDUs
 * using the short length (normal semantics) of the ISO7816 protocol. When an
 * implementation supports extended length APDU format (see
 * {@link javacardx.apdu.ExtendedLength ExtendedLength}) and an APDU with more
 * than 255 input or output data bytes is being processed, the behavior of
 * <code>BasicService</code> class is undefined.
 * <p>
 * If the header areas maintained by the <code>BasicService</code> helper
 * methods are modified directly in the APDU buffer and the format of the APDU
 * buffer described above is not maintained, unexpected behavior might result.
 * <p>
 * In addition, both La=0 and La=256 are represented in the CSF format as La=0.
 * The distinction is implementation dependent. The <code>getOutputLength</code>
 * method must be used to avoid ambiguity.
 * <p>
 * Many of the helper methods also throw exceptions if the APDU object is in an
 * error state ( processing status code &lt; 0 ).
 *
 * @see APDU APDU
 * @see javacardx.apdu.ExtendedLength javacardx.apdu.ExtendedLength
 * @version 1.0
 */
public class BasicService implements Service {

  /**
   * Creates new <code>BasicService</code>.
   */
  public BasicService() {}

  /**
   * This <code>BasicService</code> method is a default implementation and
   * simply returns false without performing any processing.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return <code>false</code>
   */
  public boolean processDataIn(APDU apdu) { return false; }

  /**
   * This <code>BasicService</code> method is a default implementation and
   * simply returns false without performing any processing.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return <code>false</code>
   */
  public boolean processCommand(APDU apdu) { return false; }

  /**
   * This <code>BasicService</code> method is a default implementation and
   * simply returns false without performing any processing.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return <code>false</code>
   */
  public boolean processDataOut(APDU apdu) { return false; }

  /**
   * Receives the input data for the command in the <code>APDU</code> object
   * if the input has not already been received. The entire input data must
   * fit in the APDU buffer starting at offset 5. When invoked, the APDU
   * object must either be in <code>STATE_INITIAL</code> with the APDU
   * buffer in the Init format or in <code>STATE_FULL_INCOMING</code> with
   * the APDU buffer in the Input Ready format
   *
   * @return the length of input data received and present in the APDU Buffer
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_IN_COMMAND</code>
   *             if the APDU object is not in STATE_INITIAL or in
   *             STATE_FULL_INCOMING or,
   *             <li><code>ServiceException.COMMAND_DATA_TOO_LONG</code> if
   *             the input data does not fit in the APDU buffer starting at
   *             offset 5.
   *             </ul>
   * @param apdu
   *            the <code>APDU</code> object containing the apdu being
   *            processed
   */
  public short receiveInData(APDU apdu) throws ServiceException {
    short length;

    if (apdu.getCurrentState() == APDU.STATE_PARTIAL_INCOMING) {
      ServiceException.throwIt(ServiceException.CANNOT_ACCESS_IN_COMMAND);
    }

    byte[] buffer = apdu.getBuffer();

    if ((short)(buffer[ISO7816.OFFSET_LC] & 0x00FF) >
        (short)((buffer.length - 5) & 0x00FF)) {
      ServiceException.throwIt(ServiceException.COMMAND_DATA_TOO_LONG);
    }

    if (apdu.getCurrentState() == APDU.STATE_INITIAL) {
      length = apdu.setIncomingAndReceive();
    }

    return buffer[ISO7816.OFFSET_LC];
  }

  /**
   * Sets the processing state of the command in the <code>APDU</code>
   * object to <em>processed</em>. This is done by setting the
   * <code>APDU</code> object in outgoing mode by invoking the
   * <code>APDU.setOutgoing</code> method. If the APDU is already in
   * outgoing mode, this method does nothing (allowing the method to be called
   * several times).
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_OUT_COMMAND</code>
   *             if the APDU object is not accessible (APDU object in
   *             STATE_ERROR_.. )
   *             </ul>
   * @see javacard.framework.APDU#getCurrentState()
   *      javacard.framework.APDU.getCurrentState()
   */
  public void setProcessed(APDU apdu) throws ServiceException {
    try {
      if (apdu.getCurrentState() < APDU.STATE_OUTGOING) {
        apdu.setOutgoing();
      }
    } catch (Exception e) {
      ServiceException.throwIt(ServiceException.CANNOT_ACCESS_OUT_COMMAND);
    }
  }

  /**
   * Checks if the command in the <code>APDU</code> object has already been
   * <em>processed</em>. This is done by checking whether or not the
   * <code>APDU</code> object has been set in outgoing mode via a previous
   * invocation of the <code>APDU.setOutgoing</code> method.
   * <p>
   * Note:
   * <ul>
   * <li><em>This method returns true if the APDU
   * object is not accessible (APDU object in STATE_ERROR_.. ).</em>
   * </ul>
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return <code>true</code> if the command has been <em>processed</em>,
   *         <code>false</code> otherwise
   */
  public boolean isProcessed(APDU apdu) {
    return apdu.getCurrentState() >= APDU.STATE_OUTGOING;
  }

  /**
   * Sets the output length of the outgoing response for the command in the
   * <code>APDU</code> object. This method can be called regardless of the
   * current state of the APDU processing.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @param length
   *            the number of bytes in the response to the command
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             <code>length</code> parameter is greater than 256 or if the
   *             outgoing response will not fit within the APDU Buffer.
   *             </ul>
   */
  public void setOutputLength(APDU apdu, short length) throws ServiceException {

    if (length < 0 || length > (short)(apdu.getBuffer().length - 5)) {
      ServiceException.throwIt(ServiceException.ILLEGAL_PARAM);
    }

    apdu.getBuffer()[(short)4] = (byte)length;
  }

  /**
   * Returns the output length for the command in the <code>APDU</code>
   * object. This method can only be called if the APDU processing state
   * indicates that the command has been <em>processed</em>.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return a value in the range: 0 to 256(inclusive), that represents the
   *         number of bytes to be returned for this command
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_OUT_COMMAND</code>
   *             if the command is not <em>processed</em> or if the APDU
   *             object is not accessible (APDU object in STATE_ERROR_.. )
   *             </ul>
   * @see javacard.framework.APDU#getCurrentState()
   *      javacard.framework.APDU.getCurrentState()
   */
  public short getOutputLength(APDU apdu) throws ServiceException {
    try {
      return (short)(apdu.getBuffer()[4] & (short)0x00FF);
    } catch (Exception e) {
      ServiceException.throwIt(ServiceException.CANNOT_ACCESS_OUT_COMMAND);
    }
    return 0;
  }

  /**
   * Sets the response status word for the command in the <code>APDU</code>
   * object. This method can be called regardless of the APDU processing state
   * of the current command.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @param sw
   *            the status word response for this command
   */
  public void setStatusWord(APDU apdu, short sw) {
    Util.setShort(apdu.getBuffer(), (short)2, sw);
  }

  /**
   * Returns the response status word for the command in the <code>APDU</code>
   * object. This method can only be called if the APDU processing state
   * indicates that the command has been <em>processed</em>.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return the status word response for this command
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_OUT_COMMAND</code>
   *             if the command is not <em>processed</em> or if the APDU
   *             object is not accessible (APDU object in STATE_ERROR_.. )
   *             </ul>
   * @see javacard.framework.APDU#getCurrentState()
   *      javacard.framework.APDU.getCurrentState()
   */
  public short getStatusWord(APDU apdu) throws ServiceException {
    try {
      return Util.getShort(apdu.getBuffer(), (short)2);
    } catch (Exception e) {
      ServiceException.throwIt(ServiceException.CANNOT_ACCESS_OUT_COMMAND);
    }
    return 0;
  }

  /**
   * Sets the processing state for the command in the <code>APDU</code>
   * object to <em>processed</em>, and indicates that the processing has
   * failed. Sets the output length to <code>0</code> and the status word of
   * the response to the specified value.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @param sw
   *            the status word response for this command
   * @return <code>true</code>
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_OUT_COMMAND</code>
   *             if the APDU object is not accessible (APDU object in
   *             STATE_ERROR_.. )
   *             </ul>
   * @see javacard.framework.APDU#getCurrentState()
   *      javacard.framework.APDU.getCurrentState()
   */
  public boolean fail(APDU apdu, short sw) throws ServiceException {
    setProcessed(apdu);
    apdu.getBuffer()[(short)4] = (byte)0;
    Util.setShort(apdu.getBuffer(), (short)2, sw);
    return true;
  }

  /**
   * Sets the processing state for the command in the <code>APDU</code>
   * object to <em>processed</em>, and indicates that the processing has
   * succeeded. Sets the status word of the response to <code>0x9000</code>.
   * The output length of the response must be set separately.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed.
   * @return <code>true</code>
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_OUT_COMMAND</code>
   *             if the APDU object is not accessible (APDU object in
   *             STATE_ERROR_.. )
   *             </ul>
   * @see javacard.framework.APDU#getCurrentState()
   *      javacard.framework.APDU.getCurrentState()
   */
  public boolean succeed(APDU apdu) throws ServiceException {
    setProcessed(apdu);
    Util.setShort(apdu.getBuffer(), (short)2, (short)0x9000);
    return true;
  }

  /**
   * Sets the processing state for the command in the <code>APDU</code>
   * object to <em>processed</em>, and indicates that the processing has
   * partially succeeded. Sets the status word of the response to the
   * specified value. The output length of the response must be set
   * separately.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @param sw
   *            the status word to be returned for this command
   * @return <code>true</code>
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_OUT_COMMAND</code>
   *             if the APDU object is not accessible (APDU object in
   *             STATE_ERROR_.. )
   *             </ul>
   * @see javacard.framework.APDU#getCurrentState()
   *      javacard.framework.APDU.getCurrentState()
   */
  public boolean succeedWithStatusWord(APDU apdu, short sw)
      throws ServiceException {
    setProcessed(apdu);
    Util.setShort(apdu.getBuffer(), (short)2, sw);
    return true;
  }

  /**
   * Returns the class byte for the command in the <code>APDU</code> object.
   * This method can be called regardless of the APDU processing state of the
   * current command.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return the value of the CLA byte
   */
  public byte getCLA(APDU apdu) { return apdu.getBuffer()[ISO7816.OFFSET_CLA]; }

  /**
   * Returns the instruction byte for the command in the <code>APDU</code>
   * object. This method can be called regardless of the APDU processing state
   * of the current command.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return the value of the INS byte
   */
  public byte getINS(APDU apdu) { return apdu.getBuffer()[ISO7816.OFFSET_INS]; }

  /**
   * Returns the first parameter byte for the command in the <code>APDU</code>
   * object. When invoked, the <code>APDU</code> object must be in
   * <code>STATE_INITIAL</code> or <code>STATE_FULL_INCOMING</code>.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return the value of the P1 byte
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_IN_COMMAND</code>
   *             if the APDU object is not in STATE_INITIAL or in
   *             STATE_FULL_INCOMING. </li>
   *             </ul>
   */
  public byte getP1(APDU apdu) throws ServiceException {
    if (apdu.getCurrentState() == APDU.STATE_PARTIAL_INCOMING) {
      ServiceException.throwIt(ServiceException.CANNOT_ACCESS_IN_COMMAND);
    }
    return apdu.getBuffer()[ISO7816.OFFSET_P1];
  }

  /**
   * Returns the second parameter byte for the command in the
   * <code>APDU</code> object. When invoked, the <code>APDU</code> object
   * must be in <code>STATE_INITIAL</code> or
   * <code>STATE_FULL_INCOMING</code>.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return the value of the P2 byte
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_IN_COMMAND</code>
   *             if the APDU object is not in STATE_INITIAL or in
   *             STATE_FULL_INCOMING. </li>
   *             </ul>
   */
  public byte getP2(APDU apdu) throws ServiceException {
    if (apdu.getCurrentState() == APDU.STATE_PARTIAL_INCOMING) {
      ServiceException.throwIt(ServiceException.CANNOT_ACCESS_IN_COMMAND);
    }
    return apdu.getBuffer()[ISO7816.OFFSET_P2];
  }

  /**
   * This method is used to determine if the command in the <code>APDU</code>
   * object is the applet SELECT FILE command which selected the currently
   * selected applet.
   *
   * @return <code>true</code> if applet SELECT FILE command is being
   *         processed
   */
  public boolean selectingApplet() {
    NativeImplementation n = new NativeImplementation();
    return n.selectingApplet();
  }
}
