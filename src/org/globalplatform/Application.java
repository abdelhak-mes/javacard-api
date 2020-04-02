
package org.globalplatform;

import javacard.framework.*;

/**
 * This interface defines a method through which an Application may forward
 * input data to another Application.<p>
 *
 * This interface shall be implemented by an Application that wishes to receive
 * personalization data forwarded by its associated Security Domain. In such a
 * scenario, if the Application implements both the {@link Application} and the
 * {@link Personalization} interface, then the Security Domain shall use the
 * {@link Personalization} interface. <p>
 *
 * @see Personalization
 *
 * @since export file version 1.0
 */

public interface Application extends Shareable
{
  /**
   * Processes application specific data received from another on-card entity.<p>
   *
   * If the Application invoking this method is a Security Domain then it shall
   * be assumed that:<ul>
   *
   * <li>The <code>baBuffer</code> byte array is a <em>global</em> byte array;
   * 
   * <li>The data forwarded by the Security Domain is a STORE DATA command. The
   * <code>sOffset</code> parameter locates the class byte of the command and
   * the <code>sLength</code> parameter indicates the length of the entire
   * command (i.e. header + data field).
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
   * @param baBuffer byte array containing input data. Must be a
   * <em>global</em> byte array.
   * @param sOffset  offset of input data within <code>baBuffer</code>.
   * @param sLength  length of input data.
   *
   * @exception SecurityException if <code>baBuffer</code> is not a
   * <em>global</em> byte array.
   * @exception NullPointerException if <code>baBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading input data would
   * cause access of data outside array bounds.
   */
  public abstract void processData(byte[] baBuffer, short sOffset, short sLength);

}


