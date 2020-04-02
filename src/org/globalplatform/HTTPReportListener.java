package org.globalplatform;

import javacard.framework.Shareable;

/**
 * This interface defines a method to receive a notification upon completion
 * (success or failure) of an HTTP Administration Session.<p>
 *
 * An Application that wishes to receive such a notification shall implement the
 * {@link javacard.framework.Applet#getShareableInterfaceObject} to return an
 * {@link HTTPReportListener} instance when the <code>clientAID</code> parameter
 * is set to <code>null</code>, and the <code>parameter</code> parameter is set
 * to {@link GPSystem#FAMILY_HTTP_REPORT}.<p>
 *
 * @since <ul>
 * <li>export file version 1.3: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */

public interface HTTPReportListener extends Shareable 
{
	/**
	 * Constant notifying that a HTTP Administration Session ended successfully
	 */
	public final static short HTTP_SESSION_NO_ERROR=0x0001;

	/**
	 * Constant notifying that a HTTP Administration Session failed. That is, the
	 * retry policy of the session is exhausted and the administration session
	 * request is aborted.
	 */
	public final static short HTTP_SESSION_ERROR=(short) 0x8001; 

	/**
	 * Notifies the Application that the requested HTTP Administration Session
	 * successfully completed or not.  <p>
   *
	 * The OPEN notifies the Application when the HTTP Administration Session ends
	 * or when the retry policy is exhausted.<p>
	 * 
	 * @param status Either {@link #HTTP_SESSION_NO_ERROR} (failure) or {@link
	 * #HTTP_SESSION_ERROR} (success).
	 */
	public void httpAdministationSessionReport(short status);

}