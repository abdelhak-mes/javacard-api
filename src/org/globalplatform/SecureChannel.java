
package org.globalplatform;

import javacard.framework.*;

/**
 * This interface defines basic Secure Channel services used to manage entity
 * authentication and protect APDU commands and responses. It is typically
 * exposed by a Security Domain to its associated Applications.<p>
 *
 * Using an instance of this interface requires no knowledge of the underlying
 * protocols, algorithms and secrets used to perform entity authentication and
 * provide integrity and confidentiality of APDU commands and responses, which
 * only need to be known by the provider of the instance.<p>
 *
 * An Application that wishes to delegate such activities to its associated
 * Security Domain shall retrieve a {@link SecureChannel} instance provided by
 * its associated Security Domain using the {@link GPSystem#getSecureChannel}
 * method. On some implementations, this {@link SecureChannel} instance may also
 * be retrieved using the {@link GPSystem#getService} method.<p>
 * 
 * If the card supports logical channels, this interface is responsible for
 * correctly handling any indication of a logical channel number present in the
 * class byte of APDU commands. In particular, it shall be able to verify and
 * remove (i.e. unwrap) the protection (if any) of an APDU command without
 * altering such indication of a logical channel number.
 *
 * Upon successful initialization of a Secure Channel Session, the
 * implementation shall establish both a compulsory Session Security Level and a
 * Current Security Level:<ul>
 *
 * <li>The compulsory <em>Session Security Level</em> is the Security Level negotiated
 * during the initialization of the session, that shall apply as a minimum
 * security requirement during the entire Secure Channel Session. The compulsory
 * Session Security Level shall only be reset upon (full) termination of the
 * Secure Channel Session.
 *
 * <li>The <em>Current Security Level</em> is the security requirement that
 * shall indeed apply to APDU commands (and responses) during the session and is
 * initialized to the same value as the compulsory Session Security Level at the
 * beginning of the session. The Current Security Level may be subsequently
 * updated (e.g. R-MAC or not) upon successful processing of some commands
 * specific to the underlying security protocol or upon successful invocation of
 * the {@link SecureChannelx#setSecurityLevel} method (if supported), but shall
 * never get below the minimum requirement defined by the compulsory Session
 * Security Level. The Current Security Level, as well as any information
 * relating to the Secure Channel Session (except the compulsory Session
 * Security Level), shall be reset upon abortion or termination of the Secure
 * Channel Session.
 *
 * </ul>
 *
 * <a name="sc_abort">See Card Specification v2.2.1 section 10.2.3 for details
 * about abortion and termination of a Secure Channel Session.</a><p>
 * 
 * Until it is aborted, a Secure Channel Session shall be bound to the following
 * information:<ul>

 * <li>the Logical Channel on which it was initiated: The session only exists on
 * the Logical Channel on which it was initiated. If an Application invokes this
 * interface from another Logical Channel, then the interface shall behave as if
 * no Secure Channel Session was currently open but the existing session shall
 * neither be aborted nor be terminated. For resource management reasons,
 * attempts to open another Secure Channel Session from another Logical Channel
 * (i.e. concurrently to the existing one(s)) may be rejected by the
 * implementation.

 * <li>the Application that initiated the session: Any call performed to this
 * interface by another Application from the Logical Channel attached to the
 * session shall be rejected with an exception. For example, this may happen if
 * this other Application somehow obtained a reference to this interface using
 * the sharing mechanism.
 *
 * </ul>
 * 
 * @since <ul>
 * <li>export file version 1.0: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */

public interface SecureChannel extends Shareable
{
  /**
   * Entity Authentication has occurred as Application Provider (0x80).
   * <p>Note:<ul>
   * <li><em>Entity Authentication and the level of security that will be applied by the {@link #wrap}
   * and {@link #unwrap} methods are not necessarily related. A Security Domain, by default, could
   * verify the MAC on any command passed as a parameter in the {@link #unwrap} method without
   * entity authentication previously having occurred.</em> </ul>
   */
  public static final byte AUTHENTICATED = (byte) 0x80;

  /**
   * The {@link #unwrap} method will decrypt incoming command data (0x02).
   * <p>Note:<ul>
   * <li><em>Command data decryption could be indicated along with entity authentication and one or more
   * levels of security.</em> </ul>
   */
  public static final byte C_DECRYPTION = (byte) 0x02;

  /**
   * The {@link #unwrap} method will verify the MAC on an incoming command (0x01).
   * <p>Note:<ul>
   * <li><em>MAC verification could be indicated along with entity authentication and one or more
   * levels of security, e.g. a value of '03' indicates that while entity authentication has not
   * occurred, the {@link #unwrap} method will decrypt the command data of incoming commands
   * and verify the MAC on incoming commands.</em> </ul>
   */
  public static final byte C_MAC = (byte) 0x01;

  /**
   * The {@link #wrap} method will encrypt the outgoing response data (0x20).
   * <p>Note:<ul>
   * <li><em>Response data encryption could be indicated along with entity authentication and one
   * or more levels of security.</em> </ul>
   */
  public static final byte R_ENCRYPTION = (byte) 0x20;

  /**
   * The {@link #wrap} method will generate a MAC for the outgoing response data (0x10).
   * <p>Note:<ul>
   * <li><em>MAC generation could be indicated along with entity authentication and one or more
   * levels of security, e.g. a value of '91' indicates that entity authentication has occurred,
   * that the {@link #unwrap} method will verify the MAC on incoming commands and that the
   * {@link #wrap} method will generate a MAC on outgoing response data.</em> </ul>
   */
  public static final byte R_MAC = (byte) 0x10;

  /**
   * Entity Authentication has not occurred (0x00).
   * <p>Note:<ul>
   * <li><em>Entity Authentication and the level of security that will be applied by the
   * {@link #wrap} and {@link #unwrap} methods are not necessarily related. A
   * Security Domain, by default, could verify the MAC on any command passed as a parameter in
   * the {@link #unwrap} method without entity authentication previously having occurred.</em>
   * <li><em>The {@link #wrap} and {@link #unwrap} methods will not apply any cryptographic
   * processing to command or response data.</em> </ul>
   */
  public static final byte NO_SECURITY_LEVEL = (byte) 0x00;

  /**
   * Entity Any Authentication has occurred (0x40).
   * <p>Note:<ul>
   * <li><em>The authenticated entity is not the Application Provider of the Application.</em>
   * <li><em>Entity Authentication and the level of security that will be applied by the {@link #wrap}
   * and {@link #unwrap} methods are not necessarily related. A Security Domain, by default, could
   * verify the MAC on any command passed as a parameter in the {@link #unwrap} method without
   * entity authentication previously having occurred.</em> </ul>
   */
  public static final byte ANY_AUTHENTICATED = (byte) 0x40;

  /**
   * Processes security related APDU commands, that is, APDU commands relating
   * to the underlying security protocol.<p>
   *
   * As the intention is to allow an Application to use Secure Channel services
   * without having any knowledge of the underlying security protocols, the
   * Application may assume that APDU commands that it does not recognize are
   * part of the security protocol and will be recognized by this {@link
   * SecureChannel} instance. Therefore, the Application may either invoke this
   * method prior to determining if it recognizes the command or only invoke
   * this method for commands it does not recognize. In turn, this method will
   * throw an {@link ISOException} if it does not recognize the APDU command as
   * a security related APDU command.<p>
   *
   * This method is responsible for receiving the data field of APDU commands
   * that are recognized (i.e. that belong to the security protocol). When
   * processing a command, this method shall write response data in the
   * <code>APDU</code> buffer at offset {@link ISO7816#OFFSET_CDATA} or return
   * a status word under the form of an {@link ISOException}. The Application is
   * responsible for outputting such response data and/or status word.<p>
   *
   * @param apdu the incoming <code>APDU</code> object.
   *
   * @return the number of bytes to be output (i.e. length of response data).
   *
   * @exception ISOException with a reason code reflecting some error detected
   * by the underlying security protocol, or with one of the following reason
   * codes if the APDU command is not recognized and does not relate to the
   * underlying security protocol:<ul>
   * <li>'6E00' if the class byte of the command is not recognized by this method.
   * <li>'6D00' if the instruction byte of the command is not recognized by this method.
   * </ul>
   */
  public short processSecurity(APDU apdu) throws ISOException;

  /**
   * Computes and adds security protection to an outgoing APDU response
   * according to the <em>Current Security Level</em>.<p>
   *
   * If the Current Security Level is {@link #NO_SECURITY_LEVEL} and is
   * different from the compulsory Session Security Level (i.e. a previous
   * Secure Channel Session was <a href="#sc_abort">aborted</a> but not fully
   * terminated), then this method shall throw an exception (see below).<p>
   *
   * Otherwise, this method shall attempt computing and adding a security
   * protection to the outgoing message according to the Current Security Level
   * (e.g. {@link #R_MAC} and/or {@link #R_ENCRYPTION}). If the Current
   * Security Level does not require any protection for APDU responses (which
   * includes the case where there is no Secure Channel Session currently
   * open), the outgoing response message shall remain as is in the within the
   * <code>baBuffer</code> byte array and the returned length shall be set to a
   * value of <code>(sLength - 2)</code>, indicating the status bytes are no
   * longer present at the end of the returned data.<p>
   * 
   * Notes:<ul>
   *
   * <li><em>The Application is responsible for appending the expected status bytes
   * at the end of the response data prior to invoking this method in order for
   * them to be protected by secure messaging. The status bytes shall be
   * subsequently removed by this method.</em>
   *
   * </ul>
   *
   * @param baBuffer byte array containing response data (including the
   * expected status bytes).
   * @param sOffset offset of response data.
   * @param sLength length of response data (including the expected status
   * bytes).
   *
   * @return length of the reformatted (wrapped) response data, with security
   * information added and status bytes removed.
   *
   * @exception SecurityException if <code>baBuffer</code> is not accessible in
   * the caller's context e.g. <code>baBuffer</code> is not a <em>global</em>
   * array nor an array belonging to the caller context.
   * @exception NullPointerException if <code>baBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if writing security information
   * in <code>baBuffer</code> would cause access of data outside array bounds.
   */
  public short wrap(byte[] baBuffer, short sOffset, short sLength) throws ISOException;

  /**
   * Verifies and removes the security protection of an incoming APDU command
   * according to the <em>Current Security Level</em>.<p>
   *
   * If the Current Security Level is {@link #NO_SECURITY_LEVEL} and is
   * different from the compulsory Session Security Level (i.e. a previous
   * Secure Channel Session was <a href="#sc_abort">aborted</a> but not fully
   * terminated), then this method shall throw an exception (see below).<p>
   *
   * If the class byte does not indicate secure messaging (according to ISO/IEC
   * 7816-4), then this method shall not attempt to verify and remove any
   * security protection: the incoming command shall remain as is within the
   * <code>baBuffer</code> byte array and the returned length shall be set to
   * the value of the <code>sLength</code> parameter.<p>
   *
   * If the class byte indicates secure messaging (according to ISO/IEC
   * 7816-4), then this method shall attempt verifying and removing the
   * security protection according to the Current Security Level:<ul>
   *
   * <li>If the Current Security Level is {@link #NO_SECURITY_LEVEL}, {@link
   * #AUTHENTICATED} or {@link #ANY_AUTHENTICATED}, then this method shall not
   * attempt to verify and remove any security protection, the incoming command
   * will remain as is within the <code>baBuffer</code> byte array (with class
   * byte indicating secure messaging) and the returned length shall be set to
   * the value of the <code>sLength</code> parameter.
   *
   * <li>Incorrect verification and removal of the security protection shall
   * result in an exception being thrown (see below) and the current Secure
   * Channel Session being <a href="#sc_abort">aborted</a>: all information
   * relating to the Secure Channel Session, except the compulsory Session
   * Security Level, shall be reset.
   *
   * <li>Correct verification and removal of the security protection shall
   * result in the incoming command being reformatted within the
   * <code>baBuffer</code> byte array (a.k.a. unwrapped command). A reformatted
   * case 1 or case 2 command shall include an Lc byte set to '00'.
   *
   * </ul>
   *
   * Notes:<ul>
   *
   * <li><em>The Application is implicitly responsible for receiving the data field
   * of the incomping APDU command prior to invoking this method.</em>
   * 
   * <li><em>Depending on the underlying security protocol, if R_MAC is
   * indicated by the Current Security Level, R-MAC computation may be
   * initiated on the reformatted command once secure messaging processing of
   * the incoming command has successfully completed. If no secure messaging
   * processing was required for the incoming command, R-MAC computation would
   * be initiated on the unmodified incoming command, appended with a Lc byte
   * of '00' in the event of a case 1 or case 2 command.</em>
   *
   * </ul>
   *
   * @param baBuffer byte array containing the incoming APDU command.
   * @param sOffset offset of the incoming APDU command, i.e. offset of the
   * class byte.
   * @param sLength length of the incoming APDU command, i.e length of the
   * entire APDU command (header + data field).
   * 
   * @return length of the reformatted (unwrapped) APDU command, i.e length of
   * the entire APDU command (header + data field).
   * 
   * @exception ISOException with one of the following reason codes (other
   * reason codes specific to the underlying security protocol may be
   * returned):<ul>
   *
   * <li>'6985' if the Current Security Level is {@link #NO_SECURITY_LEVEL} but
   * the compulsory Session Security Level is different from {@link
   * #NO_SECURITY_LEVEL}, that is, a previous Secure Channel Session was <a
   * href="#sc_abort">aborted</a> but not fully terminated.
   *
   * <li>'6982' if this method failed
   * verifying or removing the security protection of the incoming APDU
   * command.
   *
   * </ul>
   *
   * @exception SecurityException if <code>baBuffer</code> is not accessible in
   * the caller's context e.g. <code>baBuffer</code> is not a <em>global</em> array nor
   * an array belonging to the caller context.
   * @exception NullPointerException if <code>baBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading the incoming APDU
   * command would cause access of data outside array bounds.
   */
  public short unwrap(byte [] baBuffer, short sOffset, short sLength) throws ISOException;

  /**
   * Decrypts sensitive user data.<p>
   * 
   * The decryption algorithm and cryptographic key used to decrypt data, as
   * well as any padding method, are known implicitly and depend on the
   * underlying security protocol.<p>
   *
   * If the Current Security Level is {@link #NO_SECURITY_LEVEL} or the
   * necessary cryptographic keys are not available, then this method shall
   * throw an exception (see below).<p>
   *
   * Otherwise, the data shall be decrypted and the clear text data shall
   * replace the encrypted data within the <code>baBuffer</code> byte
   * array. The removal of padding may cause the length of the clear text data
   * to be shorter than the length of the encrypted data.  Any failure in the
   * decryption or padding removal process shall result in an exception being
   * thrown (see below).<p>
   *
   * Notes:<ul>
   *
   * <li><em>The Application is responsible for removing application specific
   * padding, if any. For example, if the underlying security protocol is SCP
   * '02', then no padding method is defined and this method will not remove any
   * padding.</em>
   *
   * <li><em>The Application is responsible for checking the integrity of the
   * decrypted data. </em>
   *
   * </ul>
   *
   * @param baBuffer byte array containing the data that shall be decrypted.
   * @param sOffset  offset of the data that shall be decrypted.
   * @param sLength  length of the data that shall be decrypted.
   *
   * @return length of the decrypted data, with any padding removed if a padding
   * method is defined for the underlying security protocol.
   *
   * @exception ISOException with one of the following reason codes (other
   * reason codes specific to the underlying security protocol may be
   * returned):<ul>
   *
   * <li>'6985' if there is no Secure
   * Channel Session currently open.
   *
   * <li>'6700' if the length of the data that shall
   * be decrypted is not valid (e.g. underlying algorithm requires data to be
   * block-aligned and input data are not).</li>
   *
   * </ul>
   *
   * @exception SecurityException if <code>baBuffer</code> is not accessible in
   * the caller's context e.g. <code>baBuffer</code> is not a <em>global</em>
   * array nor an array belonging to the caller context.
   * @exception NullPointerException if <code>baBuffer</code> is
   * <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading user data or writing
   * decrypted data would cause access of data outside array bounds.
   */
  public short decryptData(byte[] baBuffer, short sOffset,  short sLength) throws ISOException;

  /**
   * Encrypts sensitive user data.<p>
   *
   * If this method is not supported by the implementation or the underlying
   * protocol does not define any sensitive data encryption mechanism, it shall
   * do nothing and simply throw an exception (see below).<p>
   *
   * The encryption algorithm and cryptographic key used to encrypt data, as
   * well as any padding method, are known implicitly and depend on the
   * underlying security protocol.<p>
   *
   * If the Current Security Level is {@link #NO_SECURITY_LEVEL} or the
   * necessary cryptographic keys are not available, then this method shall
   * throw an exception (see below).<p>
   *
   * Otherwise, the data shall be padded (NOTE: depends on the underlying
   * protocol) and encrypted and the encrypted data shall replace the clear
   * text data within the <code>baBuffer</code> byte array. The addition of
   * padding may cause the length of the encrypted data to be longer than the
   * length of the clear text data.  Any failure in the padding or encryption
   * process shall result in an exception being thrown (see below).<p>
   *
   * Notes:<ul>
   *
   * <li><em>The Application is responsible for adding application specific
   * padding, if needed. For example, if the underlying security protocol is SCP
   * '02', then no padding method is defined and this method by itself will not
   * add any padding, which may lead to an exception being thrown (see below) if
   * input data are not block-aligned.
   *
   * </ul>
   *
   * @param baBuffer byte array containing the data that shall be encrypted.
   * @param sOffset  offset of the data that shall be encrypted.
   * @param sLength  length of the data that shall be encrypted.
   *
   * @return length of the encrypted data.
   *
   * @exception ISOException with one of the following reason codes (other
   * reason codes specific to the underlying security protocol may be
   * returned):<ul>
   *
   * <li>'6982' if this method is not
   * supported by the implementation.
   *
   * <li>'6985' if there is no Secure Channel Session currently open.
   *
   * <li>'6700' if the length of the data that shall
   * be encrypted is not valid (e.g. underlying algorithm requires data to be
   * block-aligned and input data are not).</li>
   *
   * </ul>
   *
   * @exception SecurityException if <code>baBuffer</code> is not accessible in
   * the caller's context e.g. <code>baBuffer</code> is not a <em>global</em>
   * array nor an array belonging to the caller context.
   * @exception NullPointerException if <code>baBuffer</code> is
   * <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading user data or writing
   * encrypted data would cause access of data outside array bounds.
   */
  public short encryptData(byte[] baBuffer, short sOffset, short sLength) throws ISOException;

  /**
   * Terminates the current Secure Channel Session.
   *
   * This method resets both the compulsory <em>Session Security Level</em> and the
   * <em>Current Security Level</em> to {@link #NO_SECURITY_LEVEL} and resets all
   * information relating to the current Secure Channel Session (e.g. internal
   * states, session keys).<p>
   *
   * This method shall not fail and shall simply return if no Secure
   * Channel Session has been initiated.<p>

   * Notes:<ul> <li><em>It is recommended that Applications using this interface
   * invoke this method in the implementation of their
   * <code>Applet.deselect()</code> method.</em> </ul>
   */
  public void resetSecurity();

  /**
   * Gets the <em>Current Security Level</em>. 
   *
   * An Application shall invoke this method to ensure that its own specific
   * security requirements are enforced by this interface. It shall also take
   * into account that the Current Security Level may change during the Secure
   * Channel Session (e.g. R_MAC may be enabled or disabled during a C_MAC
   * session).<p>
   *
   * Notes:<ul>
   * <li><em>The Current Security Level, as returned by this method, may be
   * different for an Application invoking the method and its associated
   * Security Domain depending on the underlying security protocol and the
   * authenticated off-card entity's Application Provider ID (e.g. it may be
   * ANY_AUTHENTICATED for the application and AUTHENTICATED for its associated
   * Security Domain, and vice versa).</em>
   * </ul>
   *
   * @return The Current Security Level, which is a combination of one or more
   * the following constants:<ul>
   * <li>{@link #NO_SECURITY_LEVEL}
   * <li>{@link #AUTHENTICATED}
   * <li>{@link #ANY_AUTHENTICATED}
   * <li>{@link #C_MAC}
   * <li>{@link #C_DECRYPTION}
   * <li>{@link #R_MAC}
   * <li>{@link #R_ENCRYPTION}
   * </ul>
   */
  public byte getSecurityLevel();
}


