package org.globalplatform;

import javacard.framework.*;

/**
 * This interface defines a method to trigger a new HTTP administration session.<p>
 *
 * To retrieve an instance of this interface, an Application shall use the
 * {@link GlobalService} instance, if available, registered with a service name
 * of <code>({@link GPSystem#FAMILY_HTTP_ADMINISTRATION}<<8|0x00)</code>.
 *
 * @see GlobalService
 * @see GPSystem#getService
 *
 * @since <ul>
 * <li>export file version 1.3: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */
public interface HTTPAdministration extends Shareable 
{
	/**
	 * Triggers a new administration session. <p>
   *
	 * The Security Domain of the Application invoking this method will handle the
	 * SCP81 (PSK TLS) security of the communication. <p>
   *
	 * The Application invoking this method will be notified of the result of the
	 * request if it implements the {@link HTTPReportListener} interface. <p>
   *
	 * @param triggeringParameters byte array containing administration session
	 * triggering parameters.
	 * @param offset offset of triggering parameters within <code>triggeringParameters</code>.
	 * @param length length of triggering parameters.
	 * 
	 * @exception SecurityException if <code>triggeringParameters</code> is not
	 * accessible in the caller's context.
	 * @exception NullPointerException if <code>triggeringParameters</code> is
	 * <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading triggering parameters
   * command would cause access of data outside array bounds.
   *
	 * @exception ISOException with one of the following reason codes: <ul>
	 * <li> <code>SW_WRONG_DATA</code> if parameters are not
	 * correctly formatted.
	 * <li> <code>SW_CONDITIONS_NOT_SATISFIED</code> if the request could not be
	 * processed (e.g. if no SCP81 session could be established).
	 * </ul>
	 */
	void requestHTTPAdministrationSession (byte[] triggeringParameters,
                                         short offset,
                                         short length);
}
