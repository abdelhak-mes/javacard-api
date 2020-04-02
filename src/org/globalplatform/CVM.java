
package org.globalplatform;

import javacard.framework.*;

/**
 * This interface defines basic Cardholder Verification Method services
 * (e.g. comparison of CVM value, CVM state query). It is typically exposed to
 * on-card Applications by a Global Services Application implementing one or
 * more Cardholder Verification Methods. Some services are restricted to
 * Applications having the CVM Management Privilege.<p>
 * 
 * To retrieve an instance of this interface, an Application shall invoke the
 * {@link GPSystem#getCVM} method, or shall use a {@link GlobalService} instance
 * of the {@link GPSystem#FAMILY_CVM} family. For backward compatibility, the
 * {@link CVM} instances retrieved using the {@link GPSystem#getCVM} method are
 * mapped onto those retrieved as Global Services of the {@link
 * GPSystem#FAMILY_CVM} family.<p>
 *
 * The CVM instance maintains the following data (see Card Specification v2.2.1
 * section 8.2.2 for more details):<ul>
 *
 * <li>CVM state: ACTIVE, INVALID_SUBMISSION, VALIDATED or BLOCKED. In addition,
 * we distinguish the case where the CVM instance is not fully initialized
 * (i.e. either the CVM value or the CVM try limit has not been set) and has
 * never entered the state ACTIVE.
 *
 * <li>CVM try limit: the maximum value of the CVM try counter.
 *
 * <li>CVM try counter: the number of unsuccessful comparisons of the CVM value
 * that may be performed before this CVM instance gets blocked.
 *
 * <li>CVM value: the secret value held by this CVM instance, stored in ASCII,
 * BCD or HEX format.
 *
 * </ul>
 *
 * Operations performed by this interface shall be independent of, and not
 * interfere with, any transaction in progress (e.g. if the {@link #verify}
 * method is invoked from within a transaction and this transaction is aborted,
 * then the try counter is not revert to its original value).<p>
 *
 * @since <ul>
 * <li>export file version 1.0: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */

public interface CVM extends Shareable
{
  /**
   * The CVM value comparison was successful.
   */
  public static final short CVM_SUCCESS = 0;

  /**
   * The CVM value comparison failed.
   */
  public static final short CVM_FAILURE = -1;

  /**
   * The CVM value is formatted as ASCII bytes.<p>
   *
   * Note:<ul> <li><em>If the CVM value is stored in a format other than ASCII,
   * it is the responsibility of the interface to convert to the expected
   * format.</em> </ul>
   */
  public static final byte FORMAT_ASCII = (byte) 0x01;

  /**
   * The CVM value is formatted as numerical digits, coded on a nibble (4 bits)
   * and left justified.<p>
   *
   * Note:<ul> <li><em>If the CVM value is stored in a format other than BCD,
   * it is the responsibility of the interface to convert to the expected
   * format.</em>
   * <li><em>If the length of the CVM value is odd, the right most nibble of
   * the CVM value shall be high values ('F').</em> </ul>
   */
  public static final byte FORMAT_BCD = (byte) 0x02;

  /**
   * The CVM value is formatted as hexadecimal (binary) data.<p>
   *
   * Note:<ul> <li><em>If the CVM value is stored in a format other than HEX,
   * it is the responsibility of the interface to convert to the expected
   * format.</em> </ul>
   */
  public static final byte FORMAT_HEX = (byte) 0x03;

  /**
   * Indicates whether this CVM instance is active, that is, whether it has
   * been fully initialized (i.e. both value and try limit) and is ready
   * for use. If yes, then the CVM state is deemed to be in one of the
   * following states: ACTIVE, INVALID_SUBMISSION, VALIDATED or BLOCKED.<p>
   *
   * @return <code>true</code> if the CVM has been fully initialized and is
   * ready for use, <code>false</code> otherwise (i.e. the CVM state is NOT_READY).
   */
  public boolean isActive();

  /**
   * Indicates whether an attempt has been made to compare the CVM value, that
   * is, whether the CVM state is INVALID_SUBMISSION or VALIDATED.<p>
   *
   * @return <code>true</code> if the CVM state is INVALID_SUBMISSION or
   * VALIDATED, <code>false</code> otherwise.
   */
  public boolean isSubmitted();

  /**
   * Indicates whether a successful comparison of the CVM value has occurred,
   * that is, whether the CVM state is VALIDATED.<p>
   *
   * @return <code>true</code> if the CVM state is VALIDATED,
   * <code>false</code> otherwise.
   */
  public boolean isVerified();

  /**
   * Indicates whether this CVM instance is blocked, that is, whether the CVM
   * state is BLOCKED.<p>
   *
   * @return <code>true</code> if the CVM state is BLOCKED, <code>false</code>
   * otherwise.
   */
  public boolean isBlocked();

  /**
   * Gets the CVM try counter, that is, the number of unsuccessful comparisons
   * of the CVM value that may be performed before this CVM instance gets
   * blocked.<p>
   *
   * @return current value of the CVM try counter.
   */
  public byte getTriesRemaining();

  /**
   * Updates the CVM value.<p> 
   * 
   * If the Application invoking this method does not have the CVM Management
   * Privilege, or if the specified format (<code>bFormat</code>) is unknown
   * (or not supported by this CVM instance), or if the new CVM value is not
   * consistent with respect to the specified format, then the CVM value is not
   * updated.<p>
   * 
   * If the CVM value is successfully updated and the CVM try limit has already
   * been successfully set previously, then this method also resets the CVM try
   * counter to the CVM try limit, and (re)sets the CVM state to ACTIVE.<p>
   *
   * Notes:<ul>
   *
   * <li><em>If the Global Service Application providing this CVM instance has
   * the Global Registry Privilege, it is able to check that the Application
   * invoking this method has the CVM Management Privilege using the {@link
   * GPRegistryEntry} interface;</em>
   *
   * <li><em>The CVM instance shall record the format, length, and value of the
   * CVM value.</em>
   *
   * </ul>
   *
   * @param baBuffer byte array containing the new CVM value. Must be a
   * <em>global</em> byte array.
   * @param sOffset offset of the new CVM value within <code>baBuffer</code>.
   * @param bLength length of the new CVM value.
   * @param bFormat format of the new CVM value: {@link #FORMAT_ASCII}, {@link
   * #FORMAT_BCD} or {@link #FORMAT_HEX}.
   *
   * @return <code>true</code> if the CVM value was successfully updated,
   * <code>false</code> otherwise.
   *
   * @see GPRegistryEntry#PRIVILEGE_CVM_MANAGEMENT
   *
   * @exception SecurityException if <code>baBuffer</code> is not a
   * <em>global</em> byte array.
   * @exception NullPointerException if <code>baBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading the new CVM value
   * would cause access of data outside array bounds.
   */
  public boolean update(byte[] baBuffer, short sOffset, byte bLength, byte bFormat);

  /**
   * Resets the CVM state to ACTIVE.<p>
   *
   * The CVM state can only be reset to ACTIVE from the states ACTIVE,
   * INVALID_SUBMISSION or VALIDATED. In particular, it cannot be reset to
   * ACTIVE if it is in state BLOCKED or if the CVM instance never entered the
   * state ACTIVE, that is, if the CVM instance is not fully
   * initialized.<p>
   *
   * @return <code>true</code> if the CVM state was reset, <code>false</code> otherwise.
   */
  public boolean resetState();

  /**
   * Sets the CVM state to BLOCKED.<p>
   *
   * If the Application invoking this method does not have the CVM Management
   * Privilege, then the CVM state is not updated.<p>
   *
   * The CVM state can only be set to BLOCKED if the CVM instance already
   * entered the state ACTIVE once, that is, if the CVM instance is fully
   * initialized. Notice that this method shall return <code>true</code> if the
   * CVM state is already BLOCKED. <p>
   *
   * Notes:<ul>
   * <li><em>If the Global Service Application providing this CVM instance has
   * the Global Registry Privilege, it is able to check that the Application
   * invoking this method has the CVM Management Privilege using the {@link
   * GPRegistryEntry} interface;</em>
   * </ul>
   *
   * @return <code>true</code> if the CVM state was set to BLOCKED,
   * <code>false</code> otherwise.
   *
   * @see GPRegistryEntry#PRIVILEGE_CVM_MANAGEMENT
   */
  public boolean blockState();

  /**
   * Resets the CVM state to ACTIVE, even if it is currently BLOCKED.<p>
   *
   * If the Application invoking this method does not have the CVM Management
   * Privilege, then the CVM state is not updated.<p>
   *
   * The CVM state can only be set to ACTIVE if the CVM instance already
   * entered the state ACTIVE once, that is, if the CVM instance is fully
   * initialized.
   *
   * If the CVM state is successfully reset, then this method also resets the
   * CVM try counter to the CVM try limit.<p>
   *
   * Notes:<ul>
   * <li><em>If the Global Service Application providing this CVM instance has
   * the Global Registry Privilege, it is able to check that the Application
   * invoking this method has the CVM Management Privilege using the {@link
   * GPRegistryEntry} interface;</em>
   * </ul>
   *
   * @return <code>true</code> if the CVM state was reset to ACTIVE,
   * <code>false</code> otherwise.
   *
   * @see GPRegistryEntry#PRIVILEGE_CVM_MANAGEMENT
   */
  public boolean resetAndUnblockState();

  /**
   * Sets the CVM try limit, that is, the maximum value of the CVM try
   * counter.<p>
   *
   * If the Application invoking this method does not have the CVM Management
   * Privilege, then the CVM try limit is not set.<p>
   *
   * If the CVM try limit is successfully set, then this method also resets the
   * CVM try counter to the new CVM try limit. If the CVM value has already
   * been successfully set previously, then this method also (re)sets the CVM
   * state to ACTIVE.<p>
   *
   * Notes:<ul>
   * <li><em>If the Global Service Application providing this CVM instance has
   * the Global Registry Privilege, it is able to check that the Application
   * invoking this method has the CVM Management Privilege using the {@link
   * GPRegistryEntry} interface;</em>
   * </ul>
   *
   * @param bTryLimit the maximum number of tries for the CVM.
   *
   * @return <code>true</code> if the try limit was set, <code>false</code>
   * otherwise.
   *
   * @see GPRegistryEntry#PRIVILEGE_CVM_MANAGEMENT
   */
  public boolean setTryLimit(byte bTryLimit);

  /**
   * Compares a value with the stored CVM value.<p>
   *
   * If the CVM state is BLOCKED, or if the submitted format
   * (<code>bFormat</code>) is unknown (or not supported by this CVM instance),
   * or if this method throws a {@link NullPointerException} or an {@link
   * ArrayIndexOutOfBoundsException}, then the comparison is deemed
   * unsuccessful.<p>
   * 
   * If the submitted CVM value is not in the same format as the stored CVM
   * value, then format conversion shall occur according to the following rules
   * prior to comparing values:<ul>
   *
   * <li>If HEX format is submitted and the stored CVM value is in ASCII or BCD
   * format, then the conversion cannot occur and the comparison is deemed
   * unsuccessful;
   *
   * <li>If BCD or ASCII format is submitted and the stored CVM value is in HEX
   * format, then the conversion cannot occur and the comparison is deemed
   * unsuccessful;
   *
   * <li>If ASCII format is submitted and the stored CVM value is in BCD
   * format, then conversion can occur if the submitted CVM value only contains
   * numerical ASCII characters: the numeric characters (coded on one byte) of
   * the submitted value are converted to numeric nibbles, padded together in
   * bytes, and a padding nibble 'F' is added on the right if necessary.
   * Otherwise, the conversion cannot occur and the comparison is deemed
   * unsuccessful;
   *
   * <li>If BCD format is submitted and the stored CVM value is in ASCII
   * format, then conversion can occur if the stored CVM value only contains
   * numerical ASCII characters: the numeric nibbles of the submitted value are
   * expanded to the corresponding characters coded on one byte and the padding
   * nibble 'F' is deleted (if present). Otherwise, the conversion cannot occur
   * and the comparison is deemed unsuccessful; </ul>
   *
   * If the comparison is unsuccessful and the CVM state is not BLOCKED, then
   * the CVM try counter must be decremented (by 1). In this case, if the CVM
   * try counter reaches a value of '0' then the CVM state shall be set to
   * BLOCKED, otherwise the CVM state shall be set to INVALID_SUBMISSION.<p>
   *
   * If the comparison is successful, then the CVM try counter shall be reset
   * to the CVM try limit and the CVM state shall be set to VALIDATED.<p>
   *
   * The CVM try counter and the CVM state shall not conform to a transaction
   * in progress, i.e. they shall not revert to a previous value if a
   * transaction in progress is aborted.<p>
   *
   * @param baBuffer byte array containing the submitted CVM value. Must be a
   * <em>global</em> byte array.
   * @param sOffset offset of the submitted CVM value within <code>baBuffer</code>.
   * @param bLength length of the submitted CVM value.
   * @param bFormat format of the submitted CVM value: {@link #FORMAT_ASCII},
   * {@link #FORMAT_BCD} or {@link #FORMAT_HEX}.
   *
   * @return {@link #CVM_SUCCESS} if the comparison was successful, {@link
   * #CVM_FAILURE} otherwise.
   *
   * @exception SecurityException if <code>baBuffer</code> is not a
   * <em>global</em> byte array.
   * @exception NullPointerException if <code>baBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if reading the submitted CVM
   * value would cause access of data outside array bounds.
   */
  public short verify(byte[] baBuffer, short sOffset, byte bLength, byte bFormat);
}


