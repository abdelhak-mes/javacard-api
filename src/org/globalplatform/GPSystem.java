
package org.globalplatform;

import javacard.framework.*;

/**
 * This class exposes a subset of the behavior of the OPEN to the
 * outside world. The OPEN implements and enforces a Card Issuer's security
 * policy relating to these services. It provides functionality at
 * the same level as the JCRE, i.e. the "system" context with special
 * privileges. 
 * @since <ul>
 * <li>export file version 1.0: initial version.
 * <li>export file version 1.1: new services and/or constants added.
 * <li>export file version 1.3: new services and/or constants added.
 * <li>export file version 1.5: new services and/or constants added.
 * <li>export file version 1.6: reviewed overall description of this interface.
 * </ul>
 */

public class GPSystem
{
	/**
	 * The current applet context is in the Life Cycle State of INSTALLED (0x03).
	 * <p>Note:<ul>
	 * <li><em>The Life Cycle State INSTALLED could be indicated along with another application
	 * specific Life Cycle State, e.g. a value of (0x07) indicates that the applet has been made
	 * selectable.</em>
	 * </ul>
	 */
	public static final byte APPLICATION_INSTALLED = (byte) 0x03;

	/**
	 * The current applet context is in the Life Cycle State of SELECTABLE (0x07).
	 * <p>Note:<ul>
	 * <li><em>The Life Cycle State SELECTABLE could be indicated along with another application
	 * specific Life Cycle State.</em>
	 * </ul>
	 */
	public static final byte APPLICATION_SELECTABLE = (byte) 0x07;

	/**
	 * The current applet context is in the Life Cycle State of LOCKED (0x80).
	 *
	 * To know whether an application is locked or not, a logical AND operation
	 * shall be performed between this constant and the current application life
	 * cycle state.
	 */
	public static final byte APPLICATION_LOCKED = (byte) 0x80;
	  
	/**
	 * The Security Domain is in the Life Cycle State of PERSONALIZED (0x0F).
	 */
	public static final byte SECURITY_DOMAIN_PERSONALIZED = (byte) 0x0F;

	/**
	 * The card is in the Life Cycle State of OP_READY (0x01).
	 */
	public static final byte CARD_OP_READY = (byte) 0x01;

	/**
	 * The card is in the Life Cycle State of INITIALIZED (0x07).
	 */
	public static final byte CARD_INITIALIZED = (byte) 0x07;

	/**
	 * The card is in the Life Cycle State of SECURED (0x0F).
	 */
	public static final byte CARD_SECURED = (byte) 0x0F;

	/**
	 * The card is in the Life Cycle State of CARD_LOCKED (0x7F).
	 */
	public static final byte CARD_LOCKED = (byte) 0x7F;

	/**
	 * The card is in the Life Cycle State of TERMINATED (0xFF).
	 */
	public static final byte CARD_TERMINATED = (byte) 0xFF;

	/**
	 * Indicates that the required CVM interface is a Global PIN (0x11).
	 */
	public static final byte CVM_GLOBAL_PIN = (byte) 0x11;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 1 (0x01).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_1 = (byte) 0x01;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 2 (0x02).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_2 = (byte) 0x02;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 3 (0x03).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_3 = (byte) 0x03;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 4 (0x04).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_4 = (byte) 0x04;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 5 (0x05).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_5 = (byte) 0x05;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 6 (0x06).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_6 = (byte) 0x06;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 7 (0x07).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_7 = (byte) 0x07;

	/**
	 * Indicates that the required CVM interface is the ETSI PIN App 8 (0x08).
	 * @since export file version 1.5
	 */
	public static final byte CVM_ETSI_PIN_APP_8 = (byte) 0x08;

	/**
	 * Indicates the family of the Secure Channel Global Service Identifier (0x81).
	 * @since export file version 1.1
	 */
	public static final byte FAMILY_SECURE_CHANNEL = (byte) 0x81;
	  
	/**
	 * Indicates the family of the CVM Global Service Identifier (0x82).
	 * @since export file version 1.1
	 */
	public static final byte FAMILY_CVM = (byte) 0x82;
	  
	/**
	 * @since export file version 1.2
	 * @deprecated Use {@link #FAMILY_AUTHORITY} instead.
	 */
	public static final byte FAMILY_AUHTORITY= (byte)0x83;
 
	/**
	 * Indicates the family of the Authority Service Identifier (0x83).
	 * @since export file version 1.2
	 */
	public static final byte FAMILY_AUTHORITY= (byte)0x83;

	/**
	 * Indicates the family of the HTTP Administration Service Identifier (0x84).
	 * @since export file version 1.3
	 */
	public static final byte FAMILY_HTTP_ADMINISTRATION= (byte)0x84;
	
	/**
	 * Indicates the family of the HTTP Report Service Identifier (0x85).
	 * @since export file version 1.3
	 */
	public static final byte FAMILY_HTTP_REPORT= (byte)0x85;
	
	/**
	 * Indicates the family of the USSM Global Service Identifier (0xA0).
	 * @since export file version 1.1
	 */
	public static final byte FAMILY_USSM = (byte) 0xA0;
	
	/**
	 * Indicates the generic Global Service Identifier (0x80).
	 * @since export file version 1.1
	 */
	public static final byte GLOBAL_SERVICE_IDENTIFIER = (byte) 0x80;
	
	/**
	 * Indicates the GlobalPlatform implementation's AID.
	 */
	private static final byte[] bGP_AID = {(byte) 0xA0, (byte) 0x00, (byte) 0x00, (byte) 0xA5, (byte) 0x51, (byte) 0x01};
	
	/**
	 * Gets the Life Cycle State of the current applet context.<p>
	 *
	 * @return the Life Cycle State of the current applet context.
	 *
	 * @see #APPLICATION_INSTALLED
	 * @see #APPLICATION_SELECTABLE
	 * @see #APPLICATION_LOCKED
	 */
	public static byte getCardContentState()
	{
		GPRegistryEntry access_point;
		AID GP_AID = new AID(bGP_AID, (short) 0, (byte) bGP_AID.length);
		access_point = (GPRegistryEntry) JCSystem.getAppletShareableInterfaceObject(GP_AID, (byte) -1);
		return access_point.getState();
	}

	/**
	 * Gets the Life Cycle State of the card.<p>
	 *
	 * @return the Life Cycle State of the card.
	 *
	 * @see #CARD_OP_READY
	 * @see #CARD_INITIALIZED
	 * @see #CARD_SECURED
	 * @see #CARD_LOCKED
	 * @see #CARD_TERMINATED
	 */
	public static byte getCardState()
	{
		GPRegistryEntry access_point;
		AID GP_AID = new AID(bGP_AID, (short) 0, (byte) bGP_AID.length);
		access_point = (GPRegistryEntry) JCSystem.getAppletShareableInterfaceObject(GP_AID, (byte) -1);
		return access_point.getCardState();
	}

	/**
	 * Gets a reference to a {@link CVM} instance provided by the OPEN.<p>
	 *
	 * Since export file version 1.1, this method allows looking up for CVM
	 * instances registered as Global Services by so-called Global Services
	 * Applications (i.e. Applications having the Global Service Privilege) and
	 * the following mechanism is defined to retrieve such instances:<ul>
	 *
	 * <li>The OPEN looks up in the GlobalPlatform Registry for a Global Services
	 * Application (i.e. having the Global Service Privilege) that registered a
	 * Global Service with the specified <code>bCVMIdentifier</code> identifier
	 * for the {@link #FAMILY_CVM} family, or that uniquely registered a Global
	 * Service for the entire {@link #FAMILY_CVM} family.
	 *
	 * <li>The OPEN then retrieves the corresponding {@link GlobalService}
	 * instance by invoking the {@link Applet#getShareableInterfaceObject} method
	 * of that Global Services Application with the <code>clientAID</code>
	 * parameter set to the AID of the current applet context (i.e. the one
	 * invoking this method) and the <code>parameter</code> parameter set to
	 * {@link #GLOBAL_SERVICE_IDENTIFIER}.
	 * 
	 * <li>The OPEN then retrieves a {@link Shareable} instance by invoking the
	 * {@link GlobalService#getServiceInterface} method with the
	 * <code>clientRegistryEntry</code> parameter set to the {@link
	 * GPRegistryEntry} instance of the current applet context (i.e. the one
	 * invoking this method), with the <code>sServiceName</code> set to
	 * <code>({@link #FAMILY_CVM}<<8|bCVMIdentifier)</code>, the
	 * <code>baBuffer</code> parameter set to <code>null</code>, and the
	 * <code>sOffset</code> and <code>sLength</code> set to zero.
	 *
	 * <li>Finally, the OPEN casts the retrieved {@link Shareable} instance to the
	 * {@link CVM} interface before returning it.
	 *
	 * <li>If any of the above steps fails, the requested CVM instance is deemed
	 * to be unavailable.
	 *
	 * </ul>
	 *
	 * For backward compatibility, the {@link #CVM_GLOBAL_PIN} constant can still
	 * be used to access a Global Service registered with the <code>({@link
	 * #FAMILY_CVM}<<8|{@link #CVM_GLOBAL_PIN})</code> identifier, or uniquely
	 * registered for the entire {@link #FAMILY_CVM} family. Whether such a
	 * service is available or not still depends on the issuer's policy.
	 *
	 * @param bCVMIdentifier identifies the requested CVM instance.
	 *
	 * @return requested CVM instance, or <code>null</code> if the requested CVM
	 * instance is not available.
	 *
	 * @see #CVM_GLOBAL_PIN
	 */
	public static CVM getCVM(byte bCVMIdentifier)
	{
	  return (null);
	}

	/**
	 * Gets a {@link SecureChannel} instance.<p>
	 *
	 * This method allows the Application associated with the current applet
	 * context to retrieve a {@link SecureChannel} instance provided by its
	 * associated Security Domain.<p>
	 *
	 * Since export file version 1.1, although not required, this method may be
	 * implemented using the Global Service facility, in which case {@link
	 * SecureChannel} instances would be registered by Security Domains as Global
	 * Services. In this case, Security Domains shall check that they only provide
	 * such {@link SecureChannel} instances to their associated Applications.<p>
	 *
	 * @return the SecureChannel interface object reference.
	 *
	 * @see #getCVM the GPSystem.getCVM() method for an example of how to access a Global Service.
	 */
	public static SecureChannel getSecureChannel()
	{      
	  return (null);
	}

	/**
	 * Locks the card. 
	 *
	 * This method shall be used to transition the card to {@link #CARD_LOCKED}
	 * Life Cycle State.<p>
	 *
	 * The OPEN shall check that the Application invoking this method has the Card
	 * Lock Privilege. If not, the transition shall be rejected. <p>
	 *
	 * @return <code>true</code> if the card was locked, <code>false</code> otherwise.
	 */
	public static boolean lockCard()
	{
	    GPRegistryEntry access_point;
	    AID GP_AID = new AID(bGP_AID, (short) 0, (byte) bGP_AID.length);
	    access_point = (GPRegistryEntry) JCSystem.getAppletShareableInterfaceObject(GP_AID, (byte) -1);
	    return access_point.setCardLifeCycleState(CARD_LOCKED);
	}

	/**
	 * Terminates the card.
	 *
	 * This method shall be used to transition the card to {@link
	 * #CARD_TERMINATED} Life Cycle State.<p>
	 *
	 * The OPEN shall check that the Application invoking this method has the Card
	 * Terminate Privilege. If not, the transition shall be rejected.<p>
	 *
	 * @return <code>true</code> if the card was terminated, <code>false</code> otherwise.
	 */
	public static boolean terminateCard()
	{
	    GPRegistryEntry access_point;
	    AID GP_AID = new AID(bGP_AID, (short) 0, (byte) bGP_AID.length);
	    access_point = (GPRegistryEntry) JCSystem.getAppletShareableInterfaceObject(GP_AID, (byte) -1);
	    return access_point.setCardLifeCycleState(CARD_TERMINATED);
	}

	/**
	 * Sets the historical bytes of the Answer To Reset (ATR) string.<p>
	 *
	 * This method only updates the ATR string that is used for the contact-based
	 * IO interface (as specified by [ISO/IEC 7816] upon power-up or cold
	 * reset. The ATR string used for warm reset shall remain unchanged.  The new
	 * historical bytes shall be visible upon next power-up or cold reset.<p>
	 *
	 * The OPEN shall check that the Application invoking this method has the Card
	 * Reset Privilege and that the <code>bLength</code> is both positive and
	 * lower than 16. If not, the change shall be rejected.<p>
	 *
	 * Notes:<ul>
	 * <li><em>The OPEN is responsible for synchronizing the length of historical
	 * bytes in the format character (low order nibble of T0) of the ATR string.</em>
	 * </ul>
	 *
	 * @param baBuffer byte array containing the ATR historical bytes.
	 * @param sOffset  offset of the ATR historical bytes.
	 * @param bLength  length of the ATR historical bytes.
	 *
	 * @return <code>true</code> if ATR historical bytes were set, <code>false</code> otherwise.
	 */
	public static boolean setATRHistBytes (byte[] baBuffer, short sOffset, byte bLength)
	{      
	  return (false);
	}

	/**
	 * Sets the Life Cycle state of the Application invoking this method.  
	 *
	 * This method allows the Application associated with the current applet
	 * context to lock itself or to change its state from an application specific
	 * Life Cycle State to another application specific Life Cycle State. An
	 * Application cannot unlock itself.<p>
	 *
	 * The OPEN shall check that the Application is currently in an application
	 * specific Life Cycle State (i.e. in the range [0x07 .. 0x7F] and with its 3
	 * low order bits set to 1), in particular that it is not in the {@link
	 * #APPLICATION_INSTALLED} state and not currently locked. If not, the change
	 * shall be rejected.
	 *
	 * The OPEN shall check that <code>bState</code> either encodes an application
	 * specific Life Cycle State or has its high order bit (b8) set to 1: the
	 * latter case shall be interpreted as a request from the the Application to
	 * lock itself.
	 *
	 * @param bState either an application specific Life Cycle State (i.e. in the
	 * range [0x07 .. 0x7F] and with its 3 low order bits set to 1), or any value
	 * having its high order bit (b8) set to 1. A value of {@link
	 * #APPLICATION_LOCKED} may be used to request locking the Application.
	 *
	 * @return <code>true</code> if the Life Cycle State of the Application was
	 * changed, <code>false</code> otherwise.
	 *
	 * @see #APPLICATION_INSTALLED
	 * @see #APPLICATION_LOCKED
	 *
	 * @since <ul>
	 * <li>export file version 1.0: initial version.
	 * <li>export file version 1.5: this method now allows the application
	 * associated with the current applet context to lock itself.
	 * </ul>
	 */
	public static boolean setCardContentState(byte bState)
	{
	    GPRegistryEntry access_point;
	    AID GP_AID = new AID(bGP_AID, (short) 0, (byte) bGP_AID.length);
	    access_point = (GPRegistryEntry) JCSystem.getAppletShareableInterfaceObject(GP_AID, (byte) -1);
	    return access_point.setState(bState);
	}

	/**
	 * Gets a {@link GPRegistryEntry} instance.<p>
	 *
	 * This method allows the Application associated with the current applet
	 * context to get its own {@link GPRegistryEntry} instance or the one of
	 * another Application.
	 *
	 * If the <code>aid</code> parameter is not <code>null</code> and does not
	 * identify the Application invoking this method, the OPEN shall check that
	 * the Application invoking this method has the Global Registry Privilege. If
	 * not, this method shall return <code>null</code>.<p>
	 *
	 * @param aid the AID of the Application whose {@link GPRegistryEntry}
	 * instance is requested. Use <code>null</code> to retrieve the {@link
	 * GPRegistryEntry} instance of the current applet context.
	 *
	 * @return the requested {@link GPRegistryEntry} instance if it was found in
	 * the GlobalPlatform Registry and the Application invoking this method is
	 * allowed to access that entry, <code>null</code> otherwise.
	 *
	 * @since export file version 1.1
	 */
	public static GPRegistryEntry getRegistryEntry(AID aid)
	{
		GPRegistryEntry access_point;
		AID GP_AID = new AID(bGP_AID, (short) 0, (byte) bGP_AID.length);
		access_point = (GPRegistryEntry) JCSystem.getAppletShareableInterfaceObject(GP_AID, (byte) -1);
		return access_point.getRegistryEntry(aid);
	}

	/**
	 * Gets a {@link GlobalService} instance matching the specified service name
	 * (<code>sServiceName</code>).<p>
	 *
	 * The <code>serverAID</code> parameter is optional (i.e. may be set to
	 * <code>null</code>) and identifies the Global Services Application providing
	 * the service.<p>
	 *
	 * The OPEN shall look for the Global Services Application providing the
	 * service:<ul>
	 *
	 * <li>If the <code>serverAID</code> parameter is <code>null</code>, then the
	 * OPEN shall look for the specified service name among the set of uniquely
	 * registered service names (see {@link GPRegistryEntry#registerService}). If
	 * the requested service name only identifies a family of services, then the
	 * OPEN shall look for a uniquely registered service name of the requested
	 * family (the search strategy remains implementation dependent). If a
	 * matching service name is found, the Global Services Application is the one
	 * that uniquely registered that service name.
	 *
	 * <li>Otherwise, if the <code>serverAID</code> parameter is not
	 * <code>null</code>, then the OPEN shall look in the GlobalPlatform Registry
	 * for the corresponding Application:<ul>
	 *
	 *   <li>If the Application does not have the Global Service Privilege, then
	 *   the search is deemed to be unsuccessful.
	 *
	 *   <li>If the requested service name (or family of service) was not
	 *   previously recorded for that Application (i.e. not specified as part of
	 *   System Install Parameters in the INSTALL command), then the search is
	 *   deemed to be unsuccessful.
	 *
	 *   </ul>
	 * 
	 * </ul>
	 *
	 * If a Global Services Application was found, then the OPEN shall retrieve
	 * the {@link GlobalService} instance by invoking the {@link
	 * Applet#getShareableInterfaceObject} method of that Global Services
	 * Application with the <code>clientAID</code> parameter set to the AID of the
	 * current applet context (i.e. the one invoking this method) and the
	 * <code>parameter</code> parameter set to {@link #GLOBAL_SERVICE_IDENTIFIER}.
	 *
	 * @param serverAID AID of the Global Services Application providing the
	 * requested service, or <code>null</code> if the caller of this method is
	 * requesting a uniquely registered service name.<p>
	 *
	 * @param sServiceName service name identifying a service or a family of
	 * services.<p>
	 *
	 * A service name is encoded on 2 bytes, the 1st byte identifying a family of
	 * services and the 2nd byte identifying a service within that family. If the
	 * 2nd byte is set to <code>0x00</code>, the caller of this method is
	 * requesting a service of the specified family, but does not care exactly
	 * which service within that family.<p>
	 *
	 * This class defines a set of constants <code>FAMILY_XXX</code> (of the
	 * <code>byte</code> type) that may be used to build a service name (of the
	 * <code>short</code> type) suitable to invoke this method as shown in the
	 * following examples:<ul>
	 *
	 * <li><code>(short)(({@link #FAMILY_CVM}<<8)|0x11)</code>
	 *
	 * <li><code>(short)(({@link #FAMILY_HTTP_ADMINISTRATION}<<8)|0x00)</code>
	 *
	 * </ul>
	 *
	 * @return the {@link GlobalService} instance giving access to the requested
	 * service, or <code>null</code> if the Global Services Application could not
	 * be found or did not provide a {@link GlobalService} instance.
	 *
	 * @see #GLOBAL_SERVICE_IDENTIFIER
	 * @see #FAMILY_CVM
	 * @see #FAMILY_SECURE_CHANNEL
	 * @see #FAMILY_USSM
	 * @see #FAMILY_AUTHORITY
	 * @see #FAMILY_HTTP_ADMINISTRATION
	 * @see #FAMILY_HTTP_REPORT
	 * @see GPRegistryEntry#registerService
	 *
	 * @since export file version 1.1
	 */
	public static GlobalService getService(AID serverAID, short sServiceName)
	{
	    GPRegistryEntry access_point;
	    AID GP_AID = new AID(bGP_AID, (short) 0, (byte) bGP_AID.length);
	    access_point = (GPRegistryEntry) JCSystem.getAppletShareableInterfaceObject(GP_AID, (byte) -1);
	    return access_point.getService(serverAID, sServiceName);
	}

}


