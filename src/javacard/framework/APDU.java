/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * Application Protocol Data Unit (APDU) is the communication format between the
 * card and the off-card applications. The format of the APDU is defined in ISO
 * specification 7816-4.
 * <p>
 *
 * This class only supports messages which conform to the structure of command
 * and response defined in ISO 7816-4. The behavior of messages which use
 * proprietary structure of messages is undefined. This class optionally
 * supports extended length fields but only when the currently selected applet
 * implements the <code>javacardx.apdu.ExtendedLength</code> interface.
 * <p>
 *
 * The <code>APDU</code> object is owned by the Java Card runtime environment.
 * The <code>APDU</code> class maintains a byte array buffer which is used to
 * transfer incoming APDU header and data bytes as well as outgoing data. The
 * buffer length must be at least 133 bytes ( 5 bytes of header and 128 bytes of
 * data ). The Java Card runtime environment must zero out the APDU buffer
 * before each new message received from the CAD.
 * <p>
 *
 * The Java Card runtime environment designates the <code>APDU</code> object
 * as a temporary Java Card runtime environment Entry Point Object (See
 * <em>Runtime Environment
 * Specification, Java Card Platform, Classic Edition</em>,
 * section 6.2.1 for details). A temporary Java Card runtime environment Entry
 * Point Object can be accessed from any applet context. References to these
 * temporary objects cannot be stored in class variables or instance variables
 * or array components.
 * <p>
 * The Java Card runtime environment similarly marks the APDU buffer as a global
 * array (See <em>Runtime Environment
 * Specification, Java Card Platform, Classic Edition</em>,
 * section 6.2.2 for details). A global array can be accessed from any applet
 * context. References to global arrays cannot be stored in class variables or
 * instance variables or array components.
 * <p>
 *
 * The applet receives the <code>APDU</code> instance to process from the Java
 * Card runtime environment in the <code>Applet.process(APDU)</code> method,
 * and the first five header bytes [ CLA, INS, P1, P2, P3 ] are available in the
 * APDU buffer. (The header format is the ISO7816-4 defined 7 byte extended APDU
 * format with a 3 byte Lc field when the Lc field in the incoming APDU header
 * is 3 bytes long).
 * <p>
 *
 * The <code>APDU</code> class API is designed to be transport protocol
 * independent. In other words, applets can use the same APDU methods regardless
 * of whether the underlying protocol in use is T=0 or T=1 (as defined in ISO
 * 7816-3).
 * <p>
 * The incoming APDU data size may be bigger than the APDU buffer size and may
 * therefore need to be read in portions by the applet. Similarly, the outgoing
 * response APDU data size may be bigger than the APDU buffer size and may need
 * to be written in portions by the applet. The <code>APDU</code> class has
 * methods to facilitate this.
 * <p>
 *
 * For sending large byte arrays as response data, the <code>APDU</code> class
 * provides a special method <code>sendBytesLong()</code> which manages the
 * APDU buffer.
 *
 * <pre>
 * // The purpose of this example is to show most of the methods
 * // in use and not to depict any particular APDU processing
 *
 * class MyApplet extends javacard.framework.Applet {
 *     // ...
 *     public void process(APDU apdu){
 *  // ...
 *  byte[] buffer = apdu.getBuffer();
 *  byte cla = buffer[ISO7816.OFFSET_CLA];
 *  byte ins = buffer[ISO7816.OFFSET_INS];
 *  ...
 *  // assume this command has incoming data
 *  // Lc tells us the incoming apdu command length
 *  short bytesLeft = (short) (buffer[ISO7816.OFFSET_LC] &amp; 0x00FF);
 *  if (bytesLeft &lt; (short)55) ISOException.throwIt( ISO7816.SW_WRONG_LENGTH
 * );
 *
 *  short readCount = apdu.setIncomingAndReceive();
 *  while ( bytesLeft &gt; 0){
 *      // enter code here to process the data previously read in buffer[5]
 *      // to buffer[readCount+4];
 *      bytesLeft -= readCount;
 *      readCount = apdu.receiveBytes ( ISO7816.OFFSET_CDATA ); // read more
 * APDU data
 *      }
 *  //
 *  //...
 *  //
 *  // Note that for a short response as in the case illustrated here
 *  // the three APDU method calls shown : setOutgoing(),setOutgoingLength()
 * &amp; sendBytes()
 *  // could be replaced by one APDU method call : setOutgoingAndSend().
 *
 *  // construct the reply APDU
 *  short le = apdu.setOutgoing();
 *  if (le &lt; (short)2) ISOException.throwIt( ISO7816.SW_WRONG_LENGTH );
 *  apdu.setOutgoingLength( (short)3 );
 *
 *  // build response data in apdu.buffer[ 0.. outCount-1 ];
 *  buffer[0] = (byte)1; buffer[1] = (byte)2; buffer[3] = (byte)3;
 *  apdu.sendBytes ( (short)0 , (short)3 );
 *  // return good complete status 90 00
 *  }
 *     // ...
 * }
 * </pre>
 *
 * The <code>APDU</code> class also defines a set of <code>STATE_..</code>
 * constants which represent the various processing states of the
 * <code>APDU</code> object based on the methods invoked and the state of the
 * data transfers. The <code>getCurrentState()</code> method returns the
 * current state.
 * <p>
 * Note that the state number assignments are ordered as follows: STATE_INITIAL
 * &lt; STATE_PARTIAL_INCOMING &lt; STATE_FULL_INCOMING &lt; STATE_OUTGOING &lt;
 * STATE_OUTGOING_LENGTH_KNOWN &lt; STATE_PARTIAL_OUTGOING &lt;
 * STATE_FULL_OUTGOING. <p> The following are processing error states and have
 * negative state number assignments : STATE_ERROR_NO_T0_GETRESPONSE,
 * STATE_ERROR_T1_IFD_ABORT, STATE_ERROR_IO and STATE_ERROR_NO_T0_REISSUE. <p>
 * Note:
 * <ul>
 * <li><em>The method descriptions use the ISO7816-4 notation
 * for the various APDU I/O cases of input and output directions. For example -
 * T=0 (Case 2S) protocol - refers to short length outbound only case
 * using the T=0 protocol.
 * The perspective of the notation used in the method descriptions
 * is that of the card(ICC) as seen at the transport layer(TPDU). External
 * transformations of the APDU I/O case may have occurred at the CAD
 * and therefore not visible to the card.</em>
 * <li><em>The method descriptions mention T=0 and T=1 protocols. Unless
 * specifically called out, the behavior described for T=1 protocol
 * is applicable to contactless protocols also.</em>
 * </ul>
 *
 * @see APDUException APDUException
 * @see ISOException ISOException
 */

public final class APDU {

  /**
   * This is the state of a new <code>APDU</code> object when only the
   * command header is valid.
   */
  public static final byte STATE_INITIAL = 0;

  /**
   * This is the state of a <code>APDU</code> object when incoming data has
   * partially been received.
   */
  public static final byte STATE_PARTIAL_INCOMING = 1;

  /**
   * This is the state of a <code>APDU</code> object when all the incoming
   * data been received.
   */
  public static final byte STATE_FULL_INCOMING = 2;

  /**
   * This is the state of a new <code>APDU</code> object when data transfer
   * mode is outbound but length is not yet known.
   */
  public static final byte STATE_OUTGOING = 3;

  /**
   * This is the state of a <code>APDU</code> object when data transfer mode
   * is outbound and outbound length is known.
   */
  public static final byte STATE_OUTGOING_LENGTH_KNOWN = 4;

  /**
   * This is the state of a <code>APDU</code> object when some outbound data
   * has been transferred but not all.
   */
  public static final byte STATE_PARTIAL_OUTGOING = 5;

  /**
   * This is the state of a <code>APDU</code> object when all outbound data
   * has been transferred.
   */
  public static final byte STATE_FULL_OUTGOING = 6;

  /**
   * This error state of a <code>APDU</code> object occurs when an
   * <code>APDUException</code> with reason code
   * <code>APDUException.NO_T0_GETRESPONSE</code> has been thrown.
   */
  public static final byte STATE_ERROR_NO_T0_GETRESPONSE = (byte)-1;

  /**
   * This error state of a <code>APDU</code> object occurs when an
   * <code>APDUException</code> with reason code
   * <code>APDUException.T1_IFD_ABORT</code> has been thrown.
   */
  public static final byte STATE_ERROR_T1_IFD_ABORT = (byte)-2;

  /**
   * This error state of a <code>APDU</code> object occurs when an
   * <code>APDUException</code> with reason code
   * <code>APDUException.IO_ERROR</code> has been thrown.
   */
  public static final byte STATE_ERROR_IO = (byte)-3;

  /**
   * This error state of a <code>APDU</code> object occurs when an
   * <code>APDUException</code> with reason code
   * <code>APDUException.NO_T0_REISSUE</code> has been thrown.
   */
  public static final byte STATE_ERROR_NO_T0_REISSUE = (byte)-4;

  /**
   * Media nibble mask in protocol byte
   */
  public static final byte PROTOCOL_MEDIA_MASK = (byte)0xF0;

  /**
   * Type nibble mask in protocol byte
   */
  public static final byte PROTOCOL_TYPE_MASK = (byte)0x0F;

  /**
   * ISO 7816 transport protocol type T=0.
   */
  public static final byte PROTOCOL_T0 = 0;

  /**
   * ISO 7816 transport protocol type T=1. This constant is also used to
   * denote the T=CL variant for contactless cards defined in ISO14443-4.
   */
  public static final byte PROTOCOL_T1 = 1;

  /**
   * Transport protocol Media - Contacted Asynchronous Half Duplex
   */
  public static final byte PROTOCOL_MEDIA_DEFAULT = (byte)0x00;

  /**
   * Transport protocol Media - Contactless Type A
   */
  public static final byte PROTOCOL_MEDIA_CONTACTLESS_TYPE_A = (byte)0x80;

  /**
   * Transport protocol Media - Contactless Type B
   */
  public static final byte PROTOCOL_MEDIA_CONTACTLESS_TYPE_B = (byte)0x90;

  /**
   * Transport protocol Media - USB
   */
  public static final byte PROTOCOL_MEDIA_USB = (byte)0xA0;

  /**
   * Transport protocol Media - APDU over HCI defined for the APDU gate in ETSI
   * TS 102 622
   */
  public static final byte PROTOCOL_MEDIA_HCI_APDU_GATE = (byte)0xB0;

  /**
   * JIS X 6319-4:2010 transport protocol Type F
   */
  public static final byte PROTOCOL_MEDIA_CONTACTLESS_TYPE_F = (byte)0xB0;

  /**
   * Returns the APDU buffer byte array.
   * <p>
   * Note:
   * <ul>
   * <li><em>References to the APDU buffer byte array
   * may be stored in local variables or method parameters.</em>
   * <li><em>References to the APDU buffer byte array
   * cannot be stored in class variables or instance variables or array
   * components. See Runtime Environment Specification, Java Card Platform,
   * Classic Edition, section 6.2.2 for details.</em>
   * </ul>
   *
   * @return byte array containing the APDU buffer
   */
  public byte[] getBuffer() {
    // TODO: To implement
    return null;
  }

  /**
   * Returns the configured incoming block size. In T=1 protocol, this
   * corresponds to IFSC (information field size for ICC), the maximum size of
   * incoming data blocks into the card. In T=0 protocol, this method returns
   * 1. IFSC is defined in ISO 7816-3. In contactless protocols, the maximum
   * size of the incoming frame without the protocol overhead is returned.
   * <p>
   * This information may be used to ensure that there is enough space
   * remaining in the APDU buffer when <code>receiveBytes()</code> is
   * invoked.
   * <p>
   * Note:
   * <ul>
   * <li><em>On </em><code>receiveBytes()</code><em> the
   * </em><code>bOff</code><em> param should account for this potential
   * blocksize.</em>
   * </ul>
   *
   * @return incoming block size setting
   * @see #receiveBytes(short) receiveBytes(short)
   */
  public static short getInBlockSize() {
    // TODO: To implement
    return 0;
  }

  /**
   * Returns the configured outgoing block size. In T=1 protocol, this
   * corresponds to IFSD (information field size for interface device), the
   * maximum size of outgoing data blocks to the CAD. In T=0 protocol, this
   * method returns 258 (accounts for 2 status bytes). IFSD is defined in ISO
   * 7816-3. In contactless protocols, the maximum frame size that can be
   * received by the PCD(proximity coupling device) without the protocol
   * overhead is returned.
   * <p>
   * This information may be used prior to invoking the
   * <code>setOutgoingLength()</code> method, to limit the length of
   * outgoing messages when chaining mode is not allowed.
   * See <em>Runtime Environment
   * Specification, Java Card Platform, Classic Edition</em>,
   * section 9.4 for details.
   * <p>
   * Note:
   * <ul>
   * <li><em>On </em><code>setOutgoingLength()</code><em> the
   * </em><code>len</code><em> param should account for this potential
   * blocksize.</em>
   * </ul>
   *
   * @return outgoing block size setting
   * @see #setOutgoingLength(short) setOutgoingLength(short)
   */
  public static short getOutBlockSize() {
    // TODO: To implement
    return 0;
  }

  /**
   * Returns the ISO 7816 transport protocol type, T=1 or T=0, in the low
   * nibble and the transport media in the upper nibble in use, based on
   * protocol and media used for the last APDU received.
   * <p>
   * Note:
   * <ul>
   * <li><em>This method returns T=1 in the low nibble for contactless
   * protocols.</em>
   * </ul>
   *
   * @return the protocol media and type in progress Valid nibble codes are
   *         listed in <code>PROTOCOL_*</code> constants above, for example
   *         {@link #PROTOCOL_T0 PROTOCOL_T0}.
   */
  public static byte getProtocol() {
    // TODO: To implement
    return 0;
  }

  /**
   * Returns the Node Address byte (NAD) in contacted T=1 protocol, and 0
   * in contacted T=0 and contactless protocols.
   * This may be used as additional information to maintain multiple
   * contexts.
   *
   * @return NAD transport byte as defined in ISO 7816-3
   */
  public byte getNAD() {
    // TODO: To implement
    return 0;
  }

  /**
   * This method is used to set the data transfer direction to outbound and to
   * obtain the expected length of response (Ne). This method should only be
   * called on a case 2 or case 4 command, otherwise erroneous behavior may
   * result.
   * <p>
   * Notes.
   * <ul>
   * <li><em>On a case 4 command, the
   * </em><code>setIncomingAndReceive()</code><em> must be invoked prior to
   * calling this method. Otherwise, erroneous behavior may result in T=0
   * protocol.</em> <li><em>Any remaining incoming data will be discarded.</em>
   * <li><em>In T=0 (Case 4S) protocol, this method will return 256 with normal
   * semantics.</em>
   * <li><em>In T=0 (Case 2E, 4S, 4E) protocol, this method will return 32767
   * when the currently selected applet implements the
   * </em><code>javacardx.apdu.ExtendedLength</code><em> interface.</em>
   * <li><em>In T=1 (Case 2E, 4E) protocol, this method will return 32767 when
   * the Le field in the APDU command is protocol is 0x0000 or greater than
   * 32767 and the currently selected applet implements the
   * </em><code>javacardx.apdu.ExtendedLength</code><em> interface.</em>
   * <li><em>This method sets the state of the </em><code>APDU</code><em> object
   * to
   * </em><code>STATE_OUTGOING</code><em>.</em>
   * </ul>
   *
   * @return Ne, the expected length of response
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if this
   *                method, or <code>setOutgoingNoChaining()</code> method
   *                already invoked.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is
   *                an error state.
   *                </ul>
   */
  public short setOutgoing() throws APDUException, ISOException {
    // TODO: To implement
    return 0;
  }

  /**
   * This method is used to set the data transfer direction to outbound
   * without using chaining mode and to obtain the
   * expected length of response (Ne). This method should be used in place of
   * the <code>setOutgoing()</code> method by applets which need to be
   * compatible with legacy CAD/terminals which do not support
   * chaining mode. See <em>Runtime Environment
   * Specification, Java Card Platform, Classic Edition</em>,
   * section 9.4 for details.
   * <p>
   * Notes.
   * <ul>
   * <li><em>On a case 4 command, the
   * </em><code>setIncomingAndReceive()</code><em> must be invoked prior to
   * calling this method. Otherwise, erroneous behavior may result in T=0
   * protocol.</em> <li><em>Any remaining incoming data will be discarded.</em>
   * <li><em>In T=0 (Case 4S) protocol, this method will return 256 with normal
   * semantics.</em>
   * <li><em>In T=0 (Case 2E, 4S, 4E) protocol, this method will return 256 when
   * the currently selected applet implements the
   * </em><code>javacardx.apdu.ExtendedLength</code><em> interface.</em>
   * <li><em>When this method is used, the </em><code>waitExtension()</code><em>
   * method cannot be used.</em> <li><em>In T=1 protocol, retransmission on
   * error may be restricted.</em> <li><em>In some cases of the T=0 protocol,
   * the outbound transfer must be performed without using
   * </em><code>(ISO7816.SW_BYTES_REMAINING_00+count)</code><em> response status
   * chaining. See the Runtime Environment Specification, Java Card Platform,
   * Classic Edition, section 9.4 for details.</em> <li><em>In T=1 protocol, the
   * outbound transfer must not set the More(M) Bit in the PCB of the I block.
   * See ISO 7816-3.</em> <li><em>This method sets the state of the
   * </em><code>APDU</code><em> object to
   * </em><code>STATE_OUTGOING</code><em>.</em>
   * </ul>
   *
   * @return Ne, the expected length of response data
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if this
   *                method, or <code>setOutgoing()</code> method already
   *                invoked.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is
   *                in an error state
   *                </ul>
   */
  public short setOutgoingNoChaining() throws APDUException, ISOException {
    // TODO: To implement
    return 0;
  }

  /**
   * Sets the actual length of response data. If a length of <code>0</code>
   * is specified, no data will be output.
   * <p>
   * Note:
   * <ul>
   * <li><em>In T=0 (Case 2&amp;4) protocol, the length is used by the Java Card
   * runtime environment to prompt the CAD for GET RESPONSE commands.</em>
   * <li><em>This method sets the state of the <code>APDU</code> object to
   * <code>STATE_OUTGOING_LENGTH_KNOWN</code>.</em>
   * </ul>
   *
   * @param len
   *            the length of response data
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setOutgoing()</code> or
   *                <code>setOutgoingNoChaining()</code> not called or if
   *                <code>setOutgoingAndSend()</code> already invoked, or
   *                this method already invoked.
   *                <li><code>APDUException.BAD_LENGTH</code> if any one of
   *                the following is true:
   *                <ul>
   *                <li><code>len</code> is negative.</li>
   *                <li><code>len</code> is greater than 256 and the
   *                currently selected applet does not implement the
   *                <code>javacardx.apdu.ExtendedLength</code> interface.</li>
   *                <li>T=0 protocol is in use, non BLOCK CHAINED data
   *                transfer is requested and <code>len</code> is greater
   *                than 256.</li>
   *                <li>T=1 protocol is in use, non BLOCK CHAINED data
   *                transfer is requested and <code>len</code> is greater
   *                than (IFSD-2), where IFSD is the Outgoing FlashBlock Size.
   * The -2 accounts for the status bytes in T=1.</li>
   *                </ul>
   *                <li><code>APDUException.NO_T0_GETRESPONSE</code> if T=0
   *                protocol is in use and the CAD does not respond to
   *                <code>(ISO7816.SW_BYTES_REMAINING_00+count)</code>
   *                response status with GET RESPONSE command on the same
   *                origin logical channel number as that of the current APDU
   *                command.
   *                <li><code>APDUException.NO_T0_REISSUE</code> if T=0
   *                protocol is in use and the CAD does not respond to
   *                <code>(ISO7816.SW_CORRECT_LENGTH_00+count)</code>
   *                response status by re-issuing same APDU command on the
   *                same origin logical channel number as that of the current
   *                APDU command with the corrected length.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is
   *                in an error state
   *                </ul>
   * @see #getOutBlockSize() getOutBlockSize()
   */
  public void setOutgoingLength(short len) throws APDUException {
    // TODO: To implement
  }

  /**
   * Gets as many data bytes as will fit without APDU buffer overflow, at the
   * specified offset <code>bOff</code>. Gets all the remaining bytes if
   * they fit.
   * <p>
   * Notes:
   * <ul>
   * <li><em>The space in the buffer must allow for incoming block size.</em>
   * <li><em>In T=1 protocol, if all the remaining bytes do not fit in the
   * buffer, this method may return less bytes than the maximum incoming block
   * size (IFSC).</em> <li><em>In T=0 protocol, if all the remaining bytes do
   * not fit in the buffer, this method may return less than a full buffer of
   * bytes to optimize and reduce protocol overhead.</em> <li><em>In T=1
   * protocol, if this method throws an </em><code>APDUException</code><em> with
   * </em><code>T1_IFD_ABORT</code><em> reason code, the Java Card runtime
   * environment will restart APDU command processing using the newly received
   * command. No more input data can be received. No output data can be
   * transmitted. No error status response can be returned.</em> <li><em>This
   * method sets the state of the <code>APDU</code> object to
   * <code>STATE_PARTIAL_INCOMING</code> if all incoming bytes are not
   * received.</em> <li><em>This method sets the state of the <code>APDU</code>
   * object to <code>STATE_FULL_INCOMING</code> if all incoming bytes are
   * received.</em>
   * </ul>
   *
   * @param bOff
   *            the offset into APDU buffer
   * @return number of bytes read. Returns 0 if no bytes are available
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setIncomingAndReceive()</code> not called or if
   *                <code>setOutgoing()</code> or
   *                <code>setOutgoingNoChaining()</code> previously invoked.
   *                <li><code>APDUException.BUFFER_BOUNDS</code> if not
   *                enough buffer space for incoming block size or
   *                if <code>bOff</code> is negative.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is
   *                in an error state
   *                <li><code>APDUException.T1_IFD_ABORT</code> if T=1
   *                protocol is in use and the CAD sends in an ABORT
   * S-FlashBlock command to abort the data transfer.
   *                </ul>
   * @see #getInBlockSize() getInBlockSize()
   */
  public short receiveBytes(short bOff) throws APDUException {
    // TODO: To implement
    return 0;
  }

  /**
   * This is the primary receive method. Calling this method indicates that
   * this APDU has incoming data. This method gets as many bytes as will fit
   * without buffer overflow in the APDU buffer following the header. It gets
   * all the incoming bytes if they fit.
   * <p>
   * This method should only be called on a case 3 or case 4 command,
   * otherwise erroneous behavior may result.
   * <p>
   * Notes:
   * <ul>
   * <li><em>In T=0 ( Case 3&amp;4 ) protocol, the P3 param is assumed to be
   * Lc.</em> <li><em>Data is read into the buffer at offset 5 for normal APDU
   * semantics.</em> <li><em>Data is read into the buffer at offset 7 for an
   * extended length APDU (Case 3E/4E).</em> <li><em>In T=1 protocol, if all the
   * incoming bytes do not fit in the buffer, this method may return less bytes
   * than the maximum incoming block size (IFSC).</em> <li><em>In T=0 protocol,
   * if all the incoming bytes do not fit in the buffer, this method may return
   * less than a full buffer of bytes to optimize and reduce protocol
   * overhead.</em> <li><em>This method sets the transfer direction to be
   * inbound and calls </em><code>receiveBytes(5)</code><em> for normal
   * semantics or
   * </em><code>receiveBytes(7)</code><em> for extended semantics.</em>
   * <li><em>This method may only be called once in a
   * </em><code>Applet.process()</code><em> method.</em> <li><em>This method
   * sets the state of the <code>APDU</code> object to
   * <code>STATE_PARTIAL_INCOMING</code> if all incoming bytes are not
   * received.</em> <li><em>This method sets the state of the <code>APDU</code>
   * object to <code>STATE_FULL_INCOMING</code> if all incoming bytes are
   * received.</em>
   * </ul>
   *
   * @return number of data bytes read. The Le byte, if any, is not included
   *         in the count. Returns 0 if no bytes are available.
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setIncomingAndReceive()</code> already invoked or
   *                if <code>setOutgoing()</code> or
   *                <code>setOutgoingNoChaining()</code> previously invoked.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is in
   *                an error state
   *                <li><code>APDUException.T1_IFD_ABORT</code> if T=1
   *                protocol is in use and the CAD sends in an ABORT
   * S-FlashBlock command to abort the data transfer.
   *                </ul>
   * @see #getIncomingLength() getIncomingLength()
   * @see #getOffsetCdata() getOffsetCdata()
   */
  public short setIncomingAndReceive() throws APDUException {
    // TODO: To implement
    return 0;
  }

  /**
   * Sends <code>len</code> more bytes from APDU buffer at specified offset
   * <code>bOff</code>.
   * <p>
   * If the last part of the response is being sent by the invocation of this
   * method, the APDU buffer must not be altered. If the data is altered,
   * incorrect output may be sent to the CAD. Requiring that the buffer not be
   * altered allows the implementation to reduce protocol overhead by
   * transmitting the last part of the response along with the status bytes.
   * <p>
   * Notes:
   * <ul>
   * <li><em>If </em><code>setOutgoingNoChaining()</code><em> was invoked,
   * output chaining mode must not be used.</em> See <em>Runtime Environment
   * Specification, Java Card Platform, Classic Edition section 9.4 for
   * details.</em> <li><em>In T=0 protocol, if
   * </em><code>setOutgoingNoChaining()</code><em> was invoked, Le bytes must be
   * transmitted before </em><code>(ISO7816.SW_BYTES_REMAINING_00+remaining
   * bytes)</code><em> response status is returned.</em> <li><em>In T=0
   * protocol, if this method throws an </em><code>APDUException</code><em> with
   * </em><code>NO_T0_GETRESPONSE</code><em> or
   * </em><code>NO_T0_REISSUE</code><em> reason code, the Java Card runtime
   * environment will restart APDU command processing using the newly received
   * command. No more output data can be transmitted. No error status response
   * can be returned.</em> <li><em>In T=1 protocol, if this method throws an
   * </em><code>APDUException</code><em> with </em><code>T1_IFD_ABORT</code><em>
   * reason code, the Java Card runtime environment will restart APDU command
   * processing using the newly received command. No more output data can be
   * transmitted. No error status response can be returned.</em> <li><em>This
   * method sets the state of the <code>APDU</code> object to
   * <code>STATE_PARTIAL_OUTGOING</code> if all outgoing bytes have not been
   * sent.</em> <li><em>This method sets the state of the <code>APDU</code>
   * object to <code>STATE_FULL_OUTGOING</code> if all outgoing bytes have been
   * sent.</em>
   * </ul>
   *
   * @param bOff
   *            the offset into APDU buffer
   * @param len
   *            the length of the data in bytes to send
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setOutgoingLength()</code> not called or
   *                <code>setOutgoingAndSend()</code> previously invoked or
   *                response byte count exceeded or if
   *                <code>APDUException.NO_T0_GETRESPONSE</code> or
   *                <code>APDUException.NO_T0_REISSUE</code> or
   *                <code>APDUException.T1_IFD_ABORT</code> previously
   *                thrown.
   *                <li><code>APDUException.BUFFER_BOUNDS</code> if
   *                <code>bOff</code> is negative or <code>len</code> is
   *                negative or <code>bOff+len</code> exceeds the buffer
   *                size.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is
   *                in an error state.
   *                <li><code>APDUException.NO_T0_GETRESPONSE</code> if T=0
   *                protocol is in use and the CAD does not respond to
   *                <code>(ISO7816.SW_BYTES_REMAINING_00+count)</code>
   *                response status with GET RESPONSE command on the same
   *                origin logical channel number as that of the current APDU
   *                command.
   *                <li><code>APDUException.NO_T0_REISSUE</code> if T=0
   *                protocol is in use and the CAD does not respond to
   *                <code>(ISO7816.SW_CORRECT_LENGTH_00+count)</code>
   *                response status by re-issuing same APDU command on the
   *                same origin logical channel number as that of the current
   *                APDU command with the corrected length.
   *                <li><code>APDUException.T1_IFD_ABORT</code> if T=1
   *                protocol is in use and the CAD sends in an ABORT
   * S-FlashBlock command to abort the data transfer.
   *                </ul>
   * @see #setOutgoing() setOutgoing()
   * @see #setOutgoingNoChaining() setOutgoingNoChaining()
   */
  public void sendBytes(short bOff, short len) throws APDUException {
    // TODO: To implement
  }

  /**
   * Sends <code>len</code> more bytes from <code>outData</code> byte
   * array starting at specified offset <code>bOff</code>.
   * <p>
   * If the last of the response is being sent by the invocation of this
   * method, the APDU buffer must not be altered. If the data is altered,
   * incorrect output may be sent to the CAD. Requiring that the buffer not be
   * altered allows the implementation to reduce protocol overhead by
   * transmitting the last part of the response along with the status bytes.
   * <p>
   * The Java Card runtime environment may use the APDU buffer to send data to
   * the CAD.
   * <p>
   * Notes:
   * <ul>
   * <li><em>If </em><code>setOutgoingNoChaining()</code><em> was invoked,
   * output chaining mode must not be used.</em> See <em>Runtime Environment
   * Specification, Java Card Platform, Classic Edition section 9.4 for
   * details.</em> <li><em>In T=0 protocol, if
   * </em><code>setOutgoingNoChaining()</code><em> was invoked, Le bytes must be
   * transmitted before </em><code>(ISO7816.SW_BYTES_REMAINING_00+remaining
   * bytes)</code><em> response status is returned.</em> <li><em>In T=0
   * protocol, if this method throws an </em><code>APDUException</code><em> with
   * </em><code>NO_T0_GETRESPONSE</code><em> or
   * </em><code>NO_T0_REISSUE</code><em> reason code, the Java Card runtime
   * environment will restart APDU command processing using the newly received
   * command. No more output data can be transmitted. No error status response
   * can be returned.</em> <li><em>In T=1 protocol, if this method throws an
   * </em><code>APDUException</code><em> with </em><code>T1_IFD_ABORT</code><em>
   * reason code, the Java Card runtime environment will restart APDU command
   * processing using the newly received command. No more output data can be
   * transmitted. No error status response can be returned.</em> <li><em>This
   * method sets the state of the <code>APDU</code> object to
   * <code>STATE_PARTIAL_OUTGOING</code> if all outgoing bytes have not been
   * sent.</em> <li><em>This method sets the state of the <code>APDU</code>
   * object to <code>STATE_FULL_OUTGOING</code> if all outgoing bytes have been
   * sent.</em>
   * </ul>
   * <p>
   *
   * @param outData
   *            the source data byte array
   * @param bOff
   *            the offset into OutData array
   * @param len
   *            the byte length of the data to send
   * @exception SecurityException
   *                if the <code>outData</code> array is not accessible in
   *                the caller's context
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setOutgoingLength()</code> not called or
   *                <code>setOutgoingAndSend()</code> previously invoked or
   *                response byte count exceeded or if
   *                <code>APDUException.NO_T0_GETRESPONSE</code> or
   *                <code>APDUException.NO_T0_REISSUE</code> or
   *                <code>APDUException.NO_T0_REISSUE</code> previously
   *                thrown.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is
   *                in an error state.
   *                <li><code>APDUException.NO_T0_GETRESPONSE</code> if T=0
   *                protocol is in use and CAD does not respond to
   *                <code>(ISO7816.SW_BYTES_REMAINING_00+count)</code>
   *                response status with GET RESPONSE command on the same
   *                origin logical channel number as that of the current APDU
   *                command.
   *                <li><code>APDUException.T1_IFD_ABORT</code> if T=1
   *                protocol is in use and the CAD sends in an ABORT
   * S-FlashBlock command to abort the data transfer.
   *                </ul>
   * @see #setOutgoing() setOutgoing()
   * @see #setOutgoingNoChaining() setOutgoingNoChaining()
   */
  public void sendBytesLong(byte[] outData, short bOff, short len)
      throws APDUException, SecurityException {
    // TODO: To implement
  }

  /**
   * This is the "convenience" send method. It provides for the most efficient
   * way to send a short response which fits in the buffer and needs the least
   * protocol overhead. This method is a combination of
   * <code>setOutgoing(), setOutgoingLength( len )</code> followed by
   * <code>sendBytes ( bOff, len )</code>. In addition, once this method is
   * invoked, <code>sendBytes()</code> and <code>sendBytesLong()</code>
   * methods cannot be invoked and the APDU buffer must not be altered.
   * <p>
   * Sends <code>len</code> byte response from the APDU buffer starting at
   * the specified offset <code>bOff</code>.
   * <p>
   * Notes:
   * <ul>
   * <li><em>No other </em><code>APDU</code><em> send methods can be
   * invoked.</em> <li><em>The APDU buffer must not be altered. If the data is
   * altered, incorrect output may be sent to the CAD.</em> <li><em>The actual
   * data transmission may only take place on return from
   * </em><code>Applet.process()</code> <li><em>This method sets the state of
   * the <code>APDU</code> object to <code>STATE_FULL_OUTGOING</code>.</em>
   * </ul>
   * <p>
   *
   * @param bOff
   *            the offset into APDU buffer
   * @param len
   *            the bytelength of the data to send
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setOutgoing()</code> or
   *                <code>setOutgoingAndSend()</code> previously invoked.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is
   *                in an error state.
   *                <li><code>APDUException.BAD_LENGTH</code> if
   *                <code>len</code> is negative or greater than 256 and the
   *                currently selected applet does not implement the
   *                <code>javacardx.apdu.ExtendedLength</code> interface.
   *                </ul>
   */
  public void setOutgoingAndSend(short bOff, short len) throws APDUException {
    // TODO: To implement
  }

  /**
   * This method returns the current processing state of the <code>APDU</code>
   * object. It is used by the <code>BasicService</code> class to help
   * services collaborate in the processing of an incoming APDU command. Valid
   * codes are listed in <code>STATE_*</code> constants above, for example
   * {@link #STATE_INITIAL STATE_INITIAL}.
   *
   * @return the current processing state of the APDU
   * @see javacard.framework.service.BasicService
   *      javacard.framework.service.BasicService
   */
  public byte getCurrentState() {
    // TODO: To implement
    return 0;
  }

  /**
   * This method is called during the <code>Applet.process(APDU)</code>
   * method to obtain a reference to the current <code>APDU</code> object.
   * This method can only be called in the context of the currently selected
   * applet.
   * <p>
   * Note:
   * <ul>
   * <li><em>Do not call this method directly or indirectly from within a method
   * invoked remotely via Java Card RMI method invocation from the client. The
   * APDU object and APDU buffer are reserved for use by RMIService. Remote
   * method parameter data may become corrupted.</em>
   * </ul>
   *
   * @return the current <code>APDU</code> object being processed
   * @exception SecurityException
   *                if
   *                <ul>
   *                <li>the current context is not the context of the
   *                currently selected applet instance or
   *                <li>this method was not called, directly or indirectly,
   *                from the applet's process method (called directly by the
   *                Java Card runtime environment), or
   *                <li>the method is called during applet installation or
   *                deletion.
   *                </ul>
   */
  public static APDU getCurrentAPDU() throws SecurityException {
    // TODO: To implement
    return null;
  }

  /**
   * This method is called during the <code>Applet.process(APDU)</code>
   * method to obtain a reference to the current APDU buffer. This method can
   * only be called in the context of the currently selected applet.
   * <p>
   * Note:
   * <ul>
   * <li><em>Do not call this method directly or indirectly from within a method
   * invoked remotely via Java Card RMI method invocation from the client. The
   * <code>APDU</code> object and APDU buffer are reserved for use by
   * <code>RMIService</code>. Remote method parameter data may become
   * corrupted.</em>
   * </ul>
   *
   * @exception SecurityException
   *                if
   *                <ul>
   *                <li>the current context is not the context of the
   *                currently selected applet or
   *                <li>this method was not called, directly or indirectly,
   *                from the applet's process method (called directly by the
   *                Java Card runtime environment), or
   *                <li>the method is called during applet installation or
   *                deletion.
   *                </ul>
   * @return the APDU buffer of the <code>APDU</code> object being processed
   */
  public static byte[] getCurrentAPDUBuffer() throws SecurityException {
    // TODO: To implement
    return null;
  }

  /**
   * Returns the logical channel number associated with the current
   * <code>APDU</code> command based on the CLA byte. A number in the range
   * 0-19 based on the CLA byte encoding is returned if the command contains
   * logical channel encoding. If the command does not contain logical channel
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
   * @return logical channel number, if present, within the CLA byte, 0
   *         otherwise
   */
  public static byte getCLAChannel() { // TODO: To implement
    return 0;
  }

  /**
   * Requests additional processing time from CAD. The implementation should
   * ensure that this method needs to be invoked only under unusual conditions
   * requiring excessive processing times.
   * <p>
   * Notes:
   * <ul>
   * <li><em>In T=0 protocol, a NULL procedure byte is sent to reset the work
   * waiting time (see ISO 7816-3).</em> <li><em>In T=1 protocol, the
   * implementation needs to request the same T=0 protocol work waiting time
   * quantum by sending a T=1 protocol request for wait time extension(see ISO
   * 7816-3).</em> <li><em>If the implementation uses an automatic timer
   * mechanism instead, this method may do nothing.</em>
   * </ul>
   * <p>
   *
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setOutgoingNoChaining()</code> previously invoked.
   *                <li><code>APDUException.IO_ERROR</code> if the APDU is in
   *                an error state.
   *                </ul>
   */
  public static void waitExtension() throws APDUException {
    // TODO: To implement
  }

  /**
   * Returns whether the current <code>APDU</code> command is the first or
   * part of a command chain. Bit b5 of the CLA byte if set, indicates that
   * the <code>APDU</code> is the first or part of a chain of commands.
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
   * @return <code>true</code> if this APDU is not the last APDU of a
   *         command chain, <code>false</code> otherwise.
   * @since 2.2.2
   */
  public boolean isCommandChainingCLA() {
    // TODO: To implement
    return false;
  }

  /**
   * Returns <code>true</code> if the encoding of the current
   * <code>APDU</code> command based on the CLA byte indicates secure
   * messaging. The secure messaging information is in bits (b4,b3) for
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
   * @return <code>true</code> if the secure messaging bit(s) is(are)
   *         nonzero, <code>false</code> otherwise
   * @since 2.2.2
   */
  public boolean isSecureMessagingCLA() {
    // TODO: To implement
    return false;
  }

  /**
   * Returns whether the current <code>APDU</code> command CLA byte
   * corresponds to an interindustry command as defined in ISO 7816-4:2013
   * specification. Bit b8 of the CLA byte if <code>0</code>, indicates
   * that the <code>APDU</code> is an interindustry command.
   *
   * @return <code>true</code> if this APDU CLA byte corresponds to an ISO
   *         interindustry command, <code>false</code> otherwise.
   * @since 2.2.2
   */
  public boolean isISOInterindustryCLA() {
    // TODO: To implement
    return false;
  }

  /**
   * Returns whether the current <code>APDU</code> command CLA byte is
   * valid. The CLA byte is invalid if the CLA bits (b8,b7,b6) is %b001, which
   * is a CLA encoding reserved for future use(RFU), or if CLA is 0xFF which
   * is an invalid value as defined in the ISO 7816-4:2013 specification.
   * <p>
   * See <em>Runtime Environment Specification, Java Card
   * Platform, Classic Edition</em>, section 4.3 for encoding details.
   * </p>
   *
   * @return <code>true</code> if this APDU CLA byte is valid,
   *         <code>false</code> otherwise.
   * @since 3.0
   */
  public boolean isValidCLA() {
    // TODO: To implement
    return false;
  }

  /**
   * Returns the incoming data length(Lc). This method can be invoked whenever
   * inbound data processing methods can be invoked during case 1, 3 or 4
   * processing. It is most useful for an extended length enabled applet to
   * avoid parsing the variable length Lc format in the APDU header.
   *
   * @return the incoming byte length indicated by the Lc field in the APDU
   *         header. Return <code>0</code> if no incoming data (Case 1)
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setIncomingAndReceive()</code> not called or if
   *                <code>setOutgoing()</code> or
   *                <code>setOutgoingNoChaining()</code> previously invoked.
   *                </ul>
   * @since 2.2.2
   * @see #getOffsetCdata() getOffsetCdata()
   */
  public short getIncomingLength() {
    // TODO: To implement
    return 0;
  }

  /**
   * Returns the offset within the APDU buffer for incoming command data. This
   * method can be invoked whenever inbound data processing methods can be
   * invoked during case 1, 3 or 4 processing. It is most useful for an
   * extended length enabled applet to avoid parsing the variable length Lc
   * format in the APDU header.
   *
   * @return the offset within the APDU buffer for incoming command data from
   *         the previous call to <code>setIncomingAndReceive()</code>
   *         method. The value returned is either 5 (Lc is 1 byte), or 7 (when
   *         Lc is 3 bytes)
   * @exception APDUException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>APDUException.ILLEGAL_USE</code> if
   *                <code>setIncomingAndReceive()</code> not called or if
   *                <code>setOutgoing()</code> or
   *                <code>setOutgoingNoChaining()</code> previously invoked.
   *                </ul>
   * @since 2.2.2
   * @see #getIncomingLength() getIncomingLength()
   */
  public short getOffsetCdata() {
    // TODO: To implement
    return 0;
  }
}
