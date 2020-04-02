package org.globalplatform;

import javacard.framework.Shareable;
import javacard.security.CryptoException;

/**
 * This interface allows performing operations such as recovering a
 * cryptographic key or signing data. The required algorithms and credentials
 * are known implicitly.<p>
 *
 * It is intended that Security Domains would be able to access an {@link
 * Authority} instance through a Global Service by a Controlling Authority
 * Security Domain (CASD) with a service name of <code>({@link
 * GPSystem#FAMILY_AUTHORITY}<<8|0x00)</code>.<p>
 * 
 * @since <ul>
 * <li>export file version 1.2: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */

public interface Authority extends Shareable 
{
	/**
   * Used with {@link #init} method to indicate signing mode.
   */
	public final byte  MODE_SIGN=1;

	/**
   * Used with {@link #init} method to indicate key recovery mode.
   */
	public final byte  MODE_KEY_RECOVERY=2;

	/**
   * Initializes the Authority interface with the appropriate mode (<code>MODE_SIGN</code> or <code>MODE_KEY_RECOVERY</code>).
   * 
   * @param theMode one of {@link #MODE_SIGN} or {@link #MODE_KEY_RECOVERY}.
   *
   * @exception CryptoException with the following reason code:<ul>
   * <li><code>ILLEGUAL_VALUE</code> if theMode option is an undefined
   * value.</ul>
   */
  public void init(byte theMode) throws CryptoException;
    
  /**
   * Generates the signature of all/last input data. 
   * A call to this method resets this Authority interface to the state it was 
   * in when previously initialized via a call to init().
   * That is, the object is reset and available to sign another message.
   * The input and output buffer may overlap and shall be <em>global</em> arrays. 
   * 
   * @param inBuff the input buffer of data to be signed
   * @param inOffset the offset in input buffer at which the signature starts
   * @param inLength the byte length to sign 
   * @param sigBuff the output buffer to store signature data
   * @param sigOffset the offset into sigBuff at which to begin signature generation
   * 
   * @return the number of bytes of signature output in sigBuff
   * 
   * @throws CryptoException with the following reason codes:<ul>
   * <li><code>INVALID_INIT</code> if this Authority interface is not initialized or 
   * initialized in <code>MODE_KEY_RECOVERY</code> mode.
   * <li><code>ILLEGAL_USE</code> if this Authority algorithm does not pad the message and
   * the message is not block aligned.</ul>
   * @throws SecurityException if the inBuff or sigBuff are not <em>global</em> arrays.
   */
  public short sign(byte[] inBuff,
                    short inOffset,
                    short inLength,
                    byte[] sigBuff,
                    short sigOffset)
    throws CryptoException;
    
  /**
   * Accumulates input data. for the current operation (<code>MODE_SIGN</code> or <code>MODE_KEY_RECOVERY</code>).
   * <p>
   * When this method is used, temporary storage of intermediate results is required. 
   * This method should only be used if all the input data required for the current operation
   * is not available in one byte array.
   * The <code>sign</code> or <code>recoverKey</code> methods are recommended whenever possible. 
   * The inBuff shall be <em>global</em> array. 
   * 
   * @param inBuff buffer containing input data
   * @param inOffset offset of input data
   * @param inLength length of input data 
   * @throws CryptoException with the following reason codes:<ul>
   * <li><code>INVALID_INIT</code> if this Authority interface is not initialized.</ul>
   * @throws SecurityException if the inBuff is not <em>global</em> array.
   */
  public void update(byte[] inBuff,
                     short inOffset,
                     short inLength)
    throws CryptoException;

  /**
   * Recovers a cryptographic key from a set of data 
   * structures provided in the input buffer (inBuff).
   * As a mandatory step, the recovery mechanism includes the verification of
   * the origin and integrity of the recovered key. 
   * This method knows, from the set of data structures present in the input 
   * buffer, which recovery mechanism is to be used.
   * The recovered key is written in the ouput buffer (outBuff) at specified
   * offset (outOffset), in the form of a key data structure whose format 
   * depends on the type of the key. 
   * A call to this method resets this instance of the Authority interface to
   * the state it was in when previously initialized via a call to init(). 
   * That is, the object is reset and available to recover another key. 
   * The input and output buffers may overlap and shall be <em>global</em> arrays.
   * 
   * @param inBuff containing input data.
   * @param inOffset offset of input data.
   * @param inLength length of input data.
   * @param outBuff the buffer where recovered key data structure shall be written 
   * @param outOffset offset where recovered key data structure shall be written
   * @return <code>Length</code> of the recovered key data structure written 
   * in outBuff at outOffset,or 0 if the recovery mechanism failed 
   * (e.g. recovered key was considered invalid).
   *  
   * @throws CryptoException - with the following reason codes:<ul>
   * <li><code>INVALID_INIT</code> if this Authority interface is not initialized or
   * initialized in <code>MODE_SIGN</code> mode.</ul>
   * @throws SecurityException if the inBuff or outBuff are not <em>global</em> arrays.
   */
  public short recoverKey(byte[] inBuff,
                          short inOffset,
                          short inLength,
                          byte[] outBuff,
                          short outOffset)
    throws CryptoException;

}
