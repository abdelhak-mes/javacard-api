
package org.globalplatform;

import javacard.framework.*;

/**
 * This interface is an extension of the {@link SecureChannel} interface that
 * defines one supplementary method to update the <em>Current Security Level</em>
 * during a Secure Channel Session.<p>
 *
 * An Application that wishes to use the {@link SecureChannelx} interface shall
 * obtain a reference to a {@link SecureChannel} instance and try to cast it to
 * the {@link SecureChannelx} interface. Whether the objects returned by the
 * {@link GPSystem#getSecureChannel} method also implement the {@link
 * SecureChannelx} interface is implementation dependent, however, this may be
 * expressed as a requirement in specific GlobalPlatform configuration
 * documents.
 *
 * @since <ul>
 * <li>export file version 1.1: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */
public interface SecureChannelx extends SecureChannel
{
  /**
   * Updates the <em>Current Security Level</em>.
   *
   * If this method is not supported by the implementation or the underlying
   * protocol does not define any sensitive data encryption mechanism, it shall
   * do nothing and simply throw an exception (see below).<p>
   *
   * The Current Security Level cannot be set below the compulsory <em>Session
   * Security Level</em>, but only equal or above. It may be increased or
   * decreased during a Secure Channel Session as long as it is at least equal
   * to the compulsory Session Security Level.<p>
   *
   * If the Current Security Level is {@link #NO_SECURITY_LEVEL} or the
   * cryptographic keys required by the new Current Security Level are not
   * available, then this method shall throw an exception (see below).<p>
   *
   * The new Current Security Level shall apply for all subsequent invocations
   * of {@link SecureChannel#wrap} and {@link SecureChannel#unwrap} methods,
   * except when there is no current Secure Channel Session.<p>

   * @param bSecurityLevel The new Current Security Level, which shall a
   * combination of one or more the following constants:<ul>

   * <li>{@link #NO_SECURITY_LEVEL}
   * <li>{@link #AUTHENTICATED}
   * <li>{@link #ANY_AUTHENTICATED}
   * <li>{@link #C_MAC}
   * <li>{@link #C_DECRYPTION}
   * <li>{@link #R_MAC}
   * <li>{@link #R_ENCRYPTION}
   * </ul>
   *
   * @exception ISOException with one of the following reason codes (other
   * reason codes specific to the underlying security protocol may be
   * returned):<ul>
   *
   * <li>'6982' if this method is not supported by the implementation.
   *
   * <li>'6985' if there is no Secure Channel Session currently open or if the
   * new Current Security Level does not comply with (i.e. goes below) the
   * compulsory Session Security Level.
   *
   * </ul>
   */
  public void setSecurityLevel(byte bSecurityLevel);
}


