package org.globalplatform;

import javacard.framework.*;

/**
 * This interface defines a method through which an Application may forward
 * input data to another Application and retrieve output data from that
 * Application.<p>
 *
 * This interface shall be implemented by an Application that wishes to receive
 * personalization data forwarded by its associated Security Domain and request
 * outputting response data. In such a scenario, if the Application implements
 * both the {@link Application} and the {@link Personalization} interface, then
 * the Security Domain shall use the {@link Personalization} interface. <p>
 *
 * @see Application
 *
 * @since export file version 1.2
 */
public interface Personalization extends Shareable 
{
  /**
   * Processes application specific data received from another on-card entity.<p>
   *
   * If the Application invoking this method is a Security Domain then it shall
   * be assumed that:<ul>
   *
   * <li>Both the <code>inBuffer</code> and <code>outBuffer</code> byte arrays
   * are <em>global</em> byte arrays;
   * 
   * <li>The data forwarded by the Security Domain is a STORE DATA command. The
   * <code>sOffset</code> parameter locates the class byte of the command and
   * the <code>sLength</code> parameter indicates the length of the entire
   * command (i.e. header + data field).
   *
   * <li>The Security Domain will send back to the off-card entity the output
   * data written by the Application to <code>outBuffer</code>.
   *
   * <li>Any exception thrown by this method will be rethrown by the Security
   * Domain, hence will be converted to and returned as a response status word
   * to the off-card entity. If the exception is an {@link ISOException}, then
   * the status word will be the reason code of that exception. Otherwise, a
   * status word of '6F00' will be returned.
   *
   * </ul>
   *
   * <p>Notes:<ul>
   *
   * <li><em>Upon invocation of this method, the Java Card VM performs a
   * context switch.</em>
   *
   * <li><em>As the Application is not the currently selected Application, it
   * should not attempt to access transient memory of type
   * <code>CLEAR_ON_DESELECT</code> during the processing of this method.</em>
   *
   * <li><em>The Application is responsible for managing the atomic
   * modification of its own data, if needed.</em>
   *
   * <li><em>As this method may be invoked by a Security Domain immaterial of
   * the Application's internal state, the Application is responsible for
   * ensuring that its internal state is valid for this operation.</em>
   *
   * </ul>
   *  
   * @param inBuffer byte array containing input data. Must be a <em>global</em>
   * byte array.
   * @param inOffset  offset of input data within <code>inBuffer</code>.
   * @param inLength  length of input data.
   * @param outBuffer byte array where output data shall be written. Must be a
   * <em>global</em> byte array.
   * @param outOffset offset where output data shall be written within
   * <code>inBuffer</code>.
   *
   * @return the number of bytes written to <code>outBuffer</code>.
   *
   * @exception SecurityException if <code>inBuffer</code> or
   * <code>outBuffer</code> is not a <em>global</em> byte array.
   * @exception NullPointerException if <code>inBuffer</code> or
   * <code>outBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading intput data or writing
   * output data would cause access of data outside array bounds.
   */
  public short processData (byte[] inBuffer, short inOffset, short inLength, byte[] outBuffer, short outOffset);

}
