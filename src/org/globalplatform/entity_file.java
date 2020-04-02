/**
 * entity_file.java
 *
 * Author: Vincent Giraud
 * Contact: vincent.giraud@stagiaires.ssi.gouv.fr
 *
 */

package org.globalplatform;

import javacard.framework.*;
import java.lang.*;

/**
 * Implementation of the interface defining the OPEN API provided to security domains and applications.
 */
public class entity_file implements GPRegistryEntry
{
    	// defined by the GP specification
	public static final byte APPLICATION_INSTALLED = (byte) 0x03;
	public static final byte APPLICATION_SELECTABLE = (byte) 0x07;
	public static final byte APPLICATION_LOCKED = (byte) 0x80;
	public static final byte SECURITY_DOMAIN_PERSONALIZED = (byte) 0x0F;
	public static final byte CARD_OP_READY = (byte) 0x01;
	public static final byte CARD_INITIALIZED = (byte) 0x07;
	public static final byte CARD_SECURED = (byte) 0x0F;
	public static final byte CARD_LOCKED = (byte) 0x7F;
	public static final byte CARD_TERMINATED = (byte) 0xFF;
	public static final byte CVM_GLOBAL_PIN = (byte) 0x11;
	public static final byte CVM_ETSI_PIN_APP_1 = (byte) 0x01;
	public static final byte CVM_ETSI_PIN_APP_2 = (byte) 0x02;
	public static final byte CVM_ETSI_PIN_APP_3 = (byte) 0x03;
	public static final byte CVM_ETSI_PIN_APP_4 = (byte) 0x04;
	public static final byte CVM_ETSI_PIN_APP_5 = (byte) 0x05;
	public static final byte CVM_ETSI_PIN_APP_6 = (byte) 0x06;
	public static final byte CVM_ETSI_PIN_APP_7 = (byte) 0x07;
	public static final byte CVM_ETSI_PIN_APP_8 = (byte) 0x08;
	public static final byte FAMILY_SECURE_CHANNEL = (byte) 0x81;
	public static final byte FAMILY_CVM = (byte) 0x82;
	public static final byte FAMILY_AUHTORITY = (byte) 0x83;
	public static final byte FAMILY_AUTHORITY = (byte) 0x83;
	public static final byte FAMILY_HTTP_ADMINISTRATION = (byte) 0x84;
	public static final byte FAMILY_HTTP_REPORT = (byte) 0x85;
	public static final byte FAMILY_USSM = (byte) 0xA0;
	public static final byte GLOBAL_SERVICE_IDENTIFIER = (byte) 0x80;
    
	// required by the specification
	byte[]				AID;
	byte 				LifeCycleState = 0x00;
	byte				PreviousLifeCycleState = 0x00;
	byte[]				parent_security_domain_AID;
	byte[] 				privileges = {(byte) 0x00, (byte) 0x00, (byte) 0x00}; // Coded as specified in the GP specification
	byte 				implicit_selection_parameter = 0x00;
	byte[]				global_services = new byte[PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE*2];
    	globalServiceProvider		provider_of_global_services;

	// required for implementation
	short 				current_nb_of_EM = -1;
	short 				current_nb_of_instances = -1;
	boolean				is_ELF = false;		// For clarity
	boolean				is_EM = false;		// For clarity
	boolean				is_instance = false;	// For clarity
	short[]				contained_EM = new short[PlateformeGlobale.MAX_EM_PER_ELF];
	short[]				associated_instances = new short[PlateformeGlobale.MAX_INSTANCES_PER_EM];
	static PlateformeGlobale	OPEN;

   /**
	* Constructor of the entity_file class. To specify that no global service can be present at initialization, the global_services array needs to be filled with 0xFF. See the GP specification for more info.
	*
	* @return The newly created entity_file instance.
	*/
	private entity_file()
	{
		Util.arrayFill(global_services, (short) 0, (short) (PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE*2), (byte) 0xFF); // No global service can be present at initialization.
	}

	public byte getState()
	{
		return this.LifeCycleState;
	}
	
	public byte getCardState()
	{
	    return OPEN.get_CardLifeCycleState();
	}

	
	public GPRegistryEntry getRegistryEntry(AID aid)
	{
		if(OPEN.get_by_index(OPEN.find_by_AID(aid)).is_EM == true) {return null;} // we cannot return an executable module

		if(aid.equals(this.AID, (short) 0, (byte) this.AID.length)) {return this;} // return if it is asking for itself
		
		if(this.isPrivileged(PRIVILEGE_GLOBAL_REGISTRY)) {return OPEN.get_by_index(OPEN.find_by_AID(aid));} // return if it has enough privileges
		
		if(OPEN.get_by_index(OPEN.find_by_AID(aid)).isAssociated(this.getAID())) {return OPEN.get_by_index(OPEN.find_by_AID(aid));} // return if it is a parent
		
		return null; // not returning anything otherwise
	}

    public AID getAID()
	{
	    return new AID(this.AID, (short) 0, (byte) this.AID.length);
	}

    public boolean setState(byte bState)
	{
	// The conditions tree in this function has been defined to respect the Global Platform specification. More information is thus available in its Life Cycle Models chapter.
	    if(!this.isPrivileged(PRIVILEGE_SECURITY_DOMAIN)) // «this» is a simple application
	    {
			switch(bState)
			{
				case APPLICATION_INSTALLED :
					if(this.LifeCycleState == APPLICATION_LOCKED)
					{
						if(this.PreviousLifeCycleState == APPLICATION_INSTALLED)
						{
							if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
							{
								changeLifeCycleState(bState); return true;
							}
							else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
							{
								changeLifeCycleState(bState); return true;
							}
						}
					}
					break;

				case APPLICATION_SELECTABLE :
					switch(this.LifeCycleState)
					{
						case APPLICATION_INSTALLED :
							if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_SECURITY_DOMAIN)) // Security Domain
							{
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_AUTHORIZED_MANAGEMENT)) // Authorized management privilege
								{
									changeLifeCycleState(bState); return true;
								}
								else if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_DELEGATED_MANAGEMENT)) // Delegated management privilege
								{
									changeLifeCycleState(bState); return true;
								}
							}
							break;

						case APPLICATION_LOCKED :
							if(this.PreviousLifeCycleState == APPLICATION_SELECTABLE)
							{
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
								{
									changeLifeCycleState(bState); return true;
								}
								else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
								{
									changeLifeCycleState(bState); return true;
								}
							}
							break;

						default :
							if((this.LifeCycleState & 0x07) == 0x07 && (this.LifeCycleState | 0x7F) == 0x7F && (this.LifeCycleState & 0x78) != 0x00) // if it is in an application specific state
							{
								if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
								{
									changeLifeCycleState(bState); return true;
								}
							}
							break;
					}
					break;

				case APPLICATION_LOCKED :
					switch(this.LifeCycleState)
					{
						case APPLICATION_INSTALLED :
							if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
							{
								changeLifeCycleState(bState); return true;
							}
							else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
							{
								changeLifeCycleState(bState); return true;
							}
							else if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
							{
								changeLifeCycleState(bState); return true;
							}
							break;
							
						case APPLICATION_SELECTABLE :
							if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
							{
								changeLifeCycleState(bState); return true;
							}
							else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
							{
								changeLifeCycleState(bState); return true;
							}
							else if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
							{
								changeLifeCycleState(bState); return true;
							}
							break;
							
						default :
							if((this.LifeCycleState & 0x07) == 0x07 && (this.LifeCycleState | 0x7F) == 0x7F && (this.LifeCycleState & 0x78) != 0x00) // if it is in an application specific state
							{
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
								{
									changeLifeCycleState(bState); return true;
								}
								else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
								{
									changeLifeCycleState(bState); return true;
								}
								else if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
								{
									changeLifeCycleState(bState); return true;
								}
							}
							break;
					}
					break;

				default :
					if((bState & 0x07) == 0x07 && (bState | 0x7F) == 0x7F && (bState & 0x78) != 0x00) // going to an application specific state
					{
						switch(this.LifeCycleState)
						{
							case APPLICATION_SELECTABLE :
								if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
								{
									changeLifeCycleState(bState); return true;
								}
								break;
							case APPLICATION_LOCKED :
								if((this.PreviousLifeCycleState & 0x07) == 0x07 && (this.PreviousLifeCycleState | 0x7F) == 0x7F && (this.PreviousLifeCycleState & 0x78) != 0x00) // if it was in an application specific state
								{
									if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
									{
										changeLifeCycleState(bState); return true;
									}
									else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
									{
										changeLifeCycleState(bState); return true;
									}
								}
								break;
						}
					}
					break;
			}
			return false;
	    }
	    else // «this» is a security domain
	    {
			if(this.getAID().equals(PlateformeGlobale.bGP_AID, (short) 0, (byte) PlateformeGlobale.bGP_AID.length)) // «this» is the issuer security domain
			{
				switch(bState)
				{
					case CARD_OP_READY :
					return false;

					case CARD_INITIALIZED :
					return false;

					case CARD_SECURED :
					switch(this.LifeCycleState)
					{
						case CARD_LOCKED :
							if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(GPRegistryEntry.PRIVILEGE_CARD_LOCK)) // Card lock privilege
							{
								this.LifeCycleState = CARD_SECURED;
								return true;
							} else {return false;}
						default:
							return false;
					}

					case CARD_LOCKED :
						switch(this.LifeCycleState)
						{
						case CARD_SECURED :
							if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(GPRegistryEntry.PRIVILEGE_CARD_LOCK)) // Card lock privilege
							{
								this.LifeCycleState = CARD_LOCKED;
								return true;
							} else {return false;}
						default:
							return false;
						}

					case CARD_TERMINATED :
						if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(GPRegistryEntry.PRIVILEGE_CARD_TERMINATE)) // Card termination privilege
						{
							this.LifeCycleState = CARD_TERMINATED;
							return true;
						} else {return false;}

					default:
						return false;
				}
			}
			else // «this» is not the issuer security domain
			{
				switch(bState)
				{
					case APPLICATION_INSTALLED :
						if(this.LifeCycleState == APPLICATION_LOCKED)
						{
							if(this.PreviousLifeCycleState == APPLICATION_INSTALLED)
							{
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
								{
									changeLifeCycleState(bState); return true;
								}
								else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
								{
									changeLifeCycleState(bState); return true;
								}
							}
						}
						break;

					case APPLICATION_SELECTABLE :
						switch(this.LifeCycleState)
						{
							case APPLICATION_INSTALLED :
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_SECURITY_DOMAIN)) // Security Domain
								{
									if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_AUTHORIZED_MANAGEMENT)) // Authorized management privilege
									{
										changeLifeCycleState(bState); return true;
									}
									else if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_DELEGATED_MANAGEMENT)) // Delegated management privilege
									{
										changeLifeCycleState(bState); return true;
									}
								}
								break;

							case APPLICATION_LOCKED :
								if(this.PreviousLifeCycleState == APPLICATION_SELECTABLE)
								{
									if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
									{
										changeLifeCycleState(bState); return true;
									}
									else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
									{
										changeLifeCycleState(bState); return true;
									}
								}
								break;
						}
						break;

					case APPLICATION_LOCKED :
						switch(this.LifeCycleState)
						{
							case APPLICATION_INSTALLED :
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
								{
									changeLifeCycleState(bState); return true;
								}
								else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
								{
									changeLifeCycleState(bState); return true;
								}
								else if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
								{
									changeLifeCycleState(bState); return true;
								}
								break;

							case APPLICATION_SELECTABLE :
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
								{
									changeLifeCycleState(bState); return true;
								}
								else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
								{
									changeLifeCycleState(bState); return true;
								}
								else if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
								{
									changeLifeCycleState(bState); return true;
								}
								break;

							case SECURITY_DOMAIN_PERSONALIZED :
								if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
								{
									changeLifeCycleState(bState); return true;
								}
								else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
								{
									changeLifeCycleState(bState); return true;
								}
								else if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
								{
									changeLifeCycleState(bState); return true;
								}
								break;
						}
						break;

					case SECURITY_DOMAIN_PERSONALIZED :
						switch(this.LifeCycleState)
						{
							case APPLICATION_SELECTABLE :
								if(JCSystem.getPreviousContextAID().equals(this.getAID())) // Itself
								{
									changeLifeCycleState(bState); return true;
								}
								break;

							case APPLICATION_LOCKED :
								if(this.PreviousLifeCycleState == SECURITY_DOMAIN_PERSONALIZED)
								{
									if(OPEN.get_by_index(OPEN.find_by_AID(JCSystem.getPreviousContextAID())).isPrivileged(PRIVILEGE_GLOBAL_LOCK)) // Global lock privilege
									{
										changeLifeCycleState(bState); return true;
									}
									else if(this.isAssociated(JCSystem.getPreviousContextAID())) // Associated Security Domain
									{
										changeLifeCycleState(bState); return true;
									}
								}
								break;
						}
						break;

				}
				return false;
			}
	    }
	}

    public boolean isPrivileged(byte bPrivilege)
	{
	    if(bPrivilege < 0 || bPrivilege > PRIVILEGE_CONTACTLESS_SELF_ACTIVATION) // Check for an unknown entry
	    {
		return false;
	    }

	    if((privileges[(byte) (bPrivilege/8)] & (byte) (0x01<<((byte) (7-((byte) (bPrivilege%8)))))) != 0x00) // If the privilege bit is set
	    {
		return true;
	    }
	    return false;
	}

    public boolean isAssociated(AID sdAID)
	{
	    entity_file selected = this;
	    if(sdAID.equals(selected.getAID()))
	    {
		    return true;
	    }
	    
	    while(!selected.getAID().equals(selected.parent_security_domain_AID, (short) 0, (byte) selected.parent_security_domain_AID.length)) // while the root has not been reached
	    {
		selected = OPEN.get_by_index(OPEN.find_by_AID(new AID(this.parent_security_domain_AID, (short) 0, (byte) this.parent_security_domain_AID.length)));
		if(sdAID.equals(selected.getAID()))
		{
		    return true;
		}
	    }
	    
	    return false;
	}

    public short getPrivileges(byte[] baBuffer, short sOffset) throws ArrayIndexOutOfBoundsException
	{
	    // vérifier que baBuffer est appelable dans ce contexte, sinon -> SecurityException
	    if(baBuffer == null)
	    {
		throw new NullPointerException();
	    }
	    if((short) (sOffset + this.privileges.length) >= baBuffer.length)
	    {
		throw new ArrayIndexOutOfBoundsException();
	    }

	    for(byte i = 0; i < this.privileges.length; i++)
	    {
		baBuffer[(short) (sOffset+i)] = this.privileges[i];
	    }
	    
	    return (short) (sOffset+this.privileges.length);
	}

    public void registerService(short sServiceName) throws ISOException
	{
	    if(!this.isPrivileged(PRIVILEGE_GLOBAL_SERVICE)) // if the caller does not have the GLOBAL SERVICE privilege
	    {
		ISOException.throwIt((short) 0x6982); // «security status not satisfied», see ISO7816-4
	    }
	    if(!JCSystem.getPreviousContextAID().equals(this.getAID())) // if the caller is not the entity itself
	    {
		ISOException.throwIt((short) 0x6982); // «security status not satisfied», see ISO7816-4
	    }
	    short i;
	    for(i = 0; (i < PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE*2) && (Util.getShort(this.global_services, i) != sServiceName); i=(short)(i+2)){} // Is the service name present ?
	    if(i == PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE*2) // True if no such service name was encountered in this application's list of service name
	    {
		ISOException.throwIt((short) 0x6985); // «conditions of use not satisfied», see ISO7816-4
	    }

	    if(OPEN.isRegistered(sServiceName, (short) -1)) // True if an other application already has this global service name registered
	    {
		ISOException.throwIt((short) 0x6985); // «conditions of use not satisfied», see ISO7816-4
	    }

	    if(!OPEN.addUniquelyRegisteredServiceName(OPEN.find_by_AID(this.getAID()), sServiceName)) // verified if the unique services table is already full
	    {
		ISOException.throwIt((short) 0x6581); // «memory failure», see ISO7816-4
	    }
	}
    
    public void deregisterService(short sServiceName) throws ISOException
	{
	    if(!this.isPrivileged(PRIVILEGE_GLOBAL_SERVICE)) // if the caller does not have the GLOBAL SERVICE privilege
	    {
		ISOException.throwIt((short) 0x6982); // «security status not satisfied», see ISO7816-4
	    }
	    if(!JCSystem.getPreviousContextAID().equals(this.getAID())) // if the caller is not the entity itself
	    {
		ISOException.throwIt((short) 0x6982); // «security status not satisfied», see ISO7816-4
	    }

	    if(!OPEN.removeUniquelyRegisteredServiceName(OPEN.find_by_AID(this.getAID()), sServiceName)) // verified if the service is not registered as unique (for this application, or at all)
	    {
		ISOException.throwIt((short) 0x6985); // «conditions of use not satisfied», see ISO7816-4
	    }
	}

    private void changeLifeCycleState(byte futureLifeCycleState)
	{
	    this.PreviousLifeCycleState = this.LifeCycleState;
	    this.LifeCycleState = futureLifeCycleState;
	}

    public boolean setCardLifeCycleState(byte bState)
	{
	    return OPEN.get_by_index((short) 0).setState(bState);
	}

    public GlobalService getService(AID serverAID, short sServiceName)
	{
	    short indexOfOwner = -1;
	    if(serverAID == null)
	    {
		if((sServiceName | (short) 0xFF00) == (short) 0xFF00) // If the service name refers only to a service family
		{
		    sServiceName = OPEN.find_uniquely_registered_family_member((byte) (sServiceName >> 8));
		    if(sServiceName == (short) 0xFFFF) {return null;} // No uniquely registered service name belongs to the family
		}
		indexOfOwner = OPEN.isRegisteredUniquely(sServiceName);
		if(indexOfOwner == (short) -1) {return null;} // In case the service name is not registered uniquely
		if(!OPEN.get_by_index(indexOfOwner).isPrivileged(PRIVILEGE_GLOBAL_SERVICE)) {return null;}
	    }
	    else
	    {
		indexOfOwner = OPEN.find_by_AID(serverAID);
		if(indexOfOwner == (short) -1) {return null;} // In case the server does not exist
		if(!OPEN.get_by_index(indexOfOwner).isPrivileged(PRIVILEGE_GLOBAL_SERVICE)) {return null;}
		if(!OPEN.isRegistered(sServiceName, indexOfOwner)) {return null;}
	    }

	    return OPEN.get_by_index(indexOfOwner).provider_of_global_services;
	}
}
