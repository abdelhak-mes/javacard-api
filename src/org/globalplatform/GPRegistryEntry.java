/**
 * GPRegistryEntry.java
 *
 * Provided by Global Platform. Additional features and calls were added while fully respecting the original API.
 * Author of enhancements and implementation : Vincent Giraud
 * Contact: vincent.giraud@stagiaires.ssi.gouv.fr
 */


package org.globalplatform;

import javacard.framework.*;

/**
 * This interface allows querying and potentially modifying the registry data of
 * an Application registered within the GlobalPlatform Registry.<p>
 *
 * Every {@link GPRegistryEntry} instance to an Application registered within
 * the GlobalPlatform Registry.<p>
 *
 * To retrieve an instance of this interface, an Application shall invoke the
 * {@link GPSystem#getRegistryEntry} method.<p>
 *
 * @since <ul>
 * <li>export file version 1.1: initial version.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */

public interface GPRegistryEntry extends Shareable
{
  /**
   * Privilege indicating Authorized Management (0x09).
   */
  public static final byte PRIVILEGE_AUTHORIZED_MANAGEMENT = (byte) 0x09;

  /**
   * Privilege indicating Card Lock (0x03).
   */
  public static final byte PRIVILEGE_CARD_LOCK = (byte) 0x03;

  /**
   * Privilege indicating Card Reset (0x05).
   */
  public static final byte PRIVILEGE_CARD_RESET = (byte) 0x05;

  /**
   * Privilege indicating Card Terminate (0x04).
   */
  public static final byte PRIVILEGE_CARD_TERMINATE = (byte) 0x04;

  /**
   * Privilege indicating CVM Management (0x06).
   */
  public static final byte PRIVILEGE_CVM_MANAGEMENT = (byte) 0x06;

  /**
   * Privilege indicating DAP verification (0x01).
   */
  public static final byte PRIVILEGE_DAP_VERIFICATION = (byte) 0x01;

  /**
   * Privilege indicating Delegated Management (0x02).
   */
  public static final byte PRIVILEGE_DELEGATED_MANAGEMENT = (byte) 0x02;

  /**
   * Privilege indicating Final Application (0x0E).
   */
  public static final byte PRIVILEGE_FINAL_APPLICATION = (byte) 0x0E;

  /**
   * Privilege indicating Global Delete (0x0B).
   */
  public static final byte PRIVILEGE_GLOBAL_DELETE = (byte) 0x0B;

  /**
   * Privilege indicating Global Lock (0x0C).
   */
  public static final byte PRIVILEGE_GLOBAL_LOCK = (byte) 0x0C;

  /**
   * Privilege indicating Global Registry (0x0D).
   */
  public static final byte PRIVILEGE_GLOBAL_REGISTRY = (byte) 0x0D;

  /**
   * Privilege indicating Global Service (0x0F).
   */
  public static final byte PRIVILEGE_GLOBAL_SERVICE = (byte) 0x0F;

  /**
   * Privilege indicating Mandated DAP verification privilege (0x07).
   */
  public static final byte PRIVILEGE_MANDATED_DAP = (byte) 0x07;

  /**
   * Privilege indicating Receipt Generation (0x10).
   */
  public static final byte PRIVILEGE_RECEIPT_GENERATION = (byte) 0x10;

  /**
   * Privilege indicating application is a Security Domain (0x00).
   */
  public static final byte PRIVILEGE_SECURITY_DOMAIN = (byte) 0x00;

  /**
   * Privilege indicating Token Verification (0x0A).
   */
  public static final byte PRIVILEGE_TOKEN_VERIFICATION = (byte) 0x0A;

  /**
   * Privilege indicating Trusted Path (0x08).
   */
  public static final byte PRIVILEGE_TRUSTED_PATH = (byte) 0x08;

  /**
   * Privilege indicating Ciphered Load File Data Block (0x11).
   */
  public static final byte PRIVILEGE_CIPHERED_LOAD_FILE_DATA_BLOCK = (byte) 0x11;

  /**
   * Privilege indicating Contactless Activation (0x12).
   */
  public static final byte PRIVILEGE_CONTACTLESS_ACTIVATION = (byte) 0x12;

  /**
   * Privilege indicating Contactless Self-Activation (0x13).
   */
  public static final byte PRIVILEGE_CONTACTLESS_SELF_ACTIVATION = (byte) 0x13;
    
  /**
   * Deregisters a service name.<p>
   *
   * The OPEN shall check that the Application invoking this method corresponds
   * to <code>this</code> entry, that it has the Global Service Privilege, and
   * that the specified service name was previously uniquely registered by that
   * same Application. If not, this method shall throw an exception (see
   * below).<p>
   *
   * @param sServiceName the service name that shall be deregistered.<p>
   *
   * A service name is encoded on 2 bytes, the 1st byte identifying a family of
   * services and the 2nd byte identifying a service within that family.<p>
   *
   * The {@link GPSystem} class defines a set of constants
   * <code>FAMILY_XXX</code> (of the <code>byte</code> type) that may be used to
   * build a service name (of the <code>short</code> type) suitable to invoke
   * this method as shown in the following examples:<ul>
   *
   * <li><code>(short)(({@link GPSystem#FAMILY_CVM}<<8)|0x11)</code>
   *
   * <li><code>(short)(({@link GPSystem#FAMILY_HTTP_ADMINISTRATION}<<8)|0x00)</code>
   *
   * </ul>
   *
   * @exception ISOException if this method is not supported or if the service
   * name was not found or if the conditions allowing to deregister the service
   * name are not satisfied.
   *
   * @see #registerService
   * @see GPSystem#FAMILY_CVM
   * @see GPSystem#FAMILY_SECURE_CHANNEL
   * @see GPSystem#FAMILY_USSM
   * @see GPSystem#FAMILY_AUTHORITY
   * @see GPSystem#FAMILY_HTTP_ADMINISTRATION
   * @see GPSystem#FAMILY_HTTP_REPORT
   */
  public void deregisterService(short sServiceName) throws ISOException;

  /**
   * Gets the AID of the Application corresponding to <code>this</code> entry.
   *
   * @return {@link AID} instance identifying the Application corresponding to
   * <code>this</code> entry.
   */
  public AID getAID();

  /**
   * Gets the Privilege Bytes of the Application corresponding to
   * <code>this</code> entry.
   *
   * @param baBuffer byte array where Privileges Bytes shall be written.
   * @param sOffset offset within <code>baBuffer</code> where Privileges Bytes
   * shall be written.
   *
   * @return <code>sOffset</code> + number of Privilege Bytes written to
   * <code>baBuffer</code>.
   *
   * @exception SecurityException if <code>baBuffer</code> is not accessible in
   * the caller's context e.g. <code>baBuffer</code> is not a <em>global</em>
   * array nor an array belonging to the caller context.
   * @exception NullPointerException if <code>baBuffer</code> is <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if writing Privileges Bytes would
   * cause access of data outside array bounds.
   */
  public short getPrivileges(byte[] baBuffer, short sOffset) throws ArrayIndexOutOfBoundsException;

  /**
   * Gets the Life Cycle State of the Application corresponding to
   * <code>this</code> entry.
   *
   * @return the Life Cycle State of the Application corresponding to
   * <code>this</code> entry.
   */
  public byte getState();

  /**
   * Gets the Life Cycle State of the card.
   * It may or may not be returned depending on the privileges of the calling entity. See the GP specification for more info about the requirements.
   *
   * @return the Life Cycle State of the card.
   */
  public byte getCardState();

  /**
   * Checks whether the Application corresponding to <code>this</code> entry is
   * associated with the specified Security Domain.<p>
   *
   * The OPEN shall check that the specified <code>sdAID</code> indeed
   * identifies a Security Domain present on the card, and check that the
   * Application corresponding to <code>this</code> entry is associated with
   * this Security Domain. If not, this method shall return
   * <code>false</code>.<p>
   *
   * @param sdAID AID of a Security Domain.
   *
   * @return <code>true</code> if the Application corresponding to
   * <code>this</code> entry is associated with the specified Security Domain,
   * <code>false</code> otherwise.
   */
  public boolean isAssociated(AID sdAID);

  /**
   * Checks whether the Application corresponding to <code>this</code> entry has
   * the specified privilege.<p>
   *
   * If the specified privilege is unknown, this method shall return
   * <code>false</code>.
   *
   * @param bPrivilege the privilege number to check, i.e. one of the
   * <code>PRIVILEGE_XXX</code> constants.
   *
   * @return <code>true</code> if the Application corresponding to
   * <code>this</code> entry has the specified privilege, <code>false</code>
   * otherwise.
   */
  public boolean isPrivileged(byte bPrivilege);

  /** 
   * Registers a service name identifying a service provided by the Application
   * corresponding to <code>this</code> entry.<p>
   * 
   * The specified service name (<code>sServiceName</code>) shall be unique
   * among all the service names previously registered in the GlobalPlatform
   * Registry using this method. Following successful invocation of this method,
   * this service name is known to be uniquely registered: no other Application
   * on the card will be able to register the same service name (until this
   * service name is deregistered (see {@link #deregisterService})). If the
   * service name identifies a family of service, no other Application on the
   * card will be able to register a service of that family.<p>
   *
   * The OPEN shall first check that the Application invoking this method
   * corresponds to <code>this</code> entry and that it has the Global Service
   * Privilege.<p>
   *
   * Then the OPEN shall check that the specified service name:<ul>
   *
   * <li>is not already uniquely registered in the GlobalPlatform Registry; if
   * the service name identifies an entire family of services, then the OPEN
   * shall check that no service name of that family is registered.
   *
   * <li>was previously recorded for the Application corresponding to
   * <code>this</code> entry (i.e. specified as part of System Install
   * Parameters in the INSTALL command).
   *
   * </ul>
   *
   * If any of the above conditions is not satisfied, this method shall throw an
   * exception (see below). Otherwise, the specified service name shall be
   * uniquely registered in the GlobalPlatform Registry.<p>
   *
   * @param sServiceName the service name that shall be uniquely registered.<p>
   *
   * A service name is encoded on 2 bytes, the 1st byte identifying a family of
   * services and the 2nd byte identifying a service within that family. If the
   * 2nd byte is set to <code>0x00</code>, the caller of this method is
   * registering an entire family of service.<p>
   *
   * The {@link GPSystem} class defines a set of constants
   * <code>FAMILY_XXX</code> (of the <code>byte</code> type) that may be used to
   * build a service name (of the <code>short</code> type) suitable to invoke
   * this method as shown in the following examples:<ul>
   *
   * <li><code>(short)(({@link GPSystem#FAMILY_CVM}<<8)|0x11)</code>
   *
   * <li><code>(short)(({@link GPSystem#FAMILY_HTTP_ADMINISTRATION}<<8)|0x00)</code>
   *
   * </ul>
   *
   * @exception ISOException if this method is not supported or if the
   * conditions allowing to register the service name are not satisfied.
   *
   * @see #deregisterService
   * @see GPSystem#getService
   * @see GPSystem#FAMILY_CVM
   * @see GPSystem#FAMILY_SECURE_CHANNEL
   * @see GPSystem#FAMILY_USSM
   * @see GPSystem#FAMILY_AUTHORITY
   * @see GPSystem#FAMILY_HTTP_ADMINISTRATION
   * @see GPSystem#FAMILY_HTTP_REPORT
   */
  public void registerService(short sServiceName) throws ISOException;

  /**
   * Sets the Life Cycle state of the Application corresponding to
   * <code>this</code> entry.<p>
   * 
   * This method enforces the Life Cycle State transition rules described in
   * Card Specification v2.2.1 section 5.<p>
   * 
   * If <code>this</code> entry corresponds to the Issuer Security Domain (ISD),
   * then the OPEN shall check that the requested transition complies with Card
   * Life Cycle State transition rules. If needed, the OPEN shall check that the
   * Application invoking this method has the Card Lock Privilege or the Card
   * Terminate Privilege.<p>
   * 
   * Otherwise, the following rules shall apply:<ul>
   *
   * <li>If <code>this</code> entry corresponds to a Security Domain, then the
   * OPEN shall check that the requested transition complies with Security
   * Domains' Life Cycle State transition rules.
   *
   * <li>If <code>this</code> entry does not correspond to a Security Domain,
   * then the OPEN shall check the requested transition complies with
   * Applications' Life Cycle State transition rules.
   *
   * <li>If this method is invoked to transition an Application (or Security
   * Domain) from the INSTALLED state to the SELECTABLE state, then the request
   * shall be rejected.
   * 
   * <li>If the high order bit (b8) of <code>bState</code> is set to 1, then the
   * call to this method shall be interpreted as an attempt to lock an
   * Application (or Security Domain), and other bits of <code>bState</code>
   * shall be ignored (b7-b1).
   *
   * <li>If <code>this</code> entry corresponds to an Application (or Security
   * Domain) that is currenly locked, then only the high order bit (b8) of
   * <code>bState</code> shall be taken into account and, if it is set to 0 then
   * the call to this method shall be interpreted as an attempt to unlock the
   * Application (or Security Domain). Other bits of <code>bState</code> shall
   * be ignored (b7-b1).
   *
   * <li>If this method is invoked to lock or unlock an Application (or Security
   * Domain), then the OPEN shall check that the Application invoking this
   * method either corresponds to <code>this</code> entry or has the Global Lock
   * Privilege.
   *
   * </ul>
   *
   * @param bState the new Life Cycle State. See Card Specification v2.2.1
   * section 11.1.1 for details on Life Cycle State Coding. A value of {@link
   * GPSystem#APPLICATION_LOCKED} (resp. 0x00) may be used to request locking
   * (resp. unlocking) an Application or a Security Domain (other than the ISD).
   *
   * @return <code>true</code> if the transition was successful,
   * <code>false</code> otherwise.
   */
  public boolean setState(byte bState);

  /**
   * Allows to ask for a GPRegistryEntry instance possibly refering to another entity than <code>this</code>' one.<p>
   * 
   * This method enforces the checks and restrictions imposed by the Global Platform specifications.<p>
   * 
   * </ul>
   *
   * @param aid The AID of the requested entity. The requested entity cannot be an executable module.
   *
   * @return A reference to the requested entry if the caller has the necessary rights or ownerships, if it exists, and if it is not an executable module. <code>null</code> otherwise.
   */
  public GPRegistryEntry getRegistryEntry(AID aid);

  /**
   * Allows to ask to change the card's life cycle state.<p>
   * 
   * This method enforces the checks and restrictions imposed by the Global Platform specifications.<p>
   * 
   * </ul>
   *
   * @param bState The card life cycle state asked for.
   *
   * @return <code>true</code> if the state transition has been achieved, <code>false</code> otherwise.
   */
  public boolean setCardLifeCycleState(byte bState);

  /**
   * Ask for a GlobalService instance corresponding to an actual global service provided by an executable module's instance.<p>
   * 
   * This method enforces the checks and restrictions imposed by the Global Platform specifications.<p>
   * 
   * </ul>
   *
   * @param serverAID The AID of the server instance supposed to provide the service. If null, then the OPEN shall look for the server proposing the service as unique (it may not exists).
   * @param sServiceName The name of the global service that is asked for. If it refers only to a family and serverAID is null, any uniquely registered service name from this family can be chosen.
   * @return The GlobalService instance asked for. <code>null</code> in case of error, or if any security requirement is not met.
   */
  public GlobalService getService(AID serverAID, short sServiceName);
}


