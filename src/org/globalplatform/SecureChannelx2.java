package org.globalplatform;

import javacard.framework.ISOException;

/**
 * This interface is an extension of the {@link SecureChannel} interface that
 * defines one supplementary method overriding the {@link
 * SecureChannel#processSecurity} method.<p>
 *
 * An Application that wishes to use the {@link SecureChannelx2} interface shall
 * obtain a reference to a {@link SecureChannel} instance and try to cast it to
 * the {@link SecureChannelx2} interface. Whether the objects returned by the
 * {@link GPSystem#getSecureChannel} method also implement the {@link
 * SecureChannelx2} interface is implementation dependent, however, this may be
 * expressed as a requirement in specific GlobalPlatform configuration
 * documents.
 *
 * @since <ul>
 * <li>export file version 1.4: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */
public interface SecureChannelx2 extends SecureChannel 
{
  /**
   * Processes security related APDU commands, that is, APDU commands relating
   * to the underlying security protocol.<p>
   * 
   * This method shall be used in the same way as the {@link
   * SecureChannel#processSecurity} method, except that the incoming APDU
   * command shall be read from, and any response data shall be written to the
   * <code>baBuffer</code> byte array.<p>
   *
   * Notes:<ul>
   *
   * <li><em>The applet is implicitly responsible for receiving the data field
   * of the incomping APDU command prior to invoking this method.</em>
   * 
   * </ul>
   * 
   * @param baBuffer byte array containing the incoming APDU command.
   * @param sInOffset offset of the incoming APDU command, i.e. offset of the
   * class byte.
   * @param sInLength length of the incoming APDU command, i.e length of the
   * entire APDU command (header + data field).
   * @param sOutOffset offset within <code>baBuffer</code> where response data
   * (if any) shall be written.
   *
   * @return the number of bytes to be output (i.e. length of response data).
   *
   * @exception ISOException with a reason code reflecting some error detected
   * by the underlying security protocol, or with one of the following reason
   * codes if the APDU command is not recognized and does not relate to the
   * underlying security protocol:<ul>
   * <li>'6E00' if the class byte of the command is not recognized
   * by this method.
   * <li>'6D00' if the instruction byte of the command is not
   * recognized by this method.  
   * </ul>
   *
   * @exception SecurityException if <code>baBuffer</code> is not accessible in
   * the caller's context e.g. <code>baBuffer</code> is not a <em>global</em> array nor
   * an array belonging to the caller context.
   * @exception NullPointerException if <code>baBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading the incoming APDU
   * command would cause access of data outside array bounds.
   */
  public short processSecurity(byte[] baBuffer, short sInOffset, short sInLength, short sOutOffset) throws ISOException;

}
