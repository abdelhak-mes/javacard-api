/**
 * globalServiceProvider.java
 *
 * Author: Vincent Giraud
 * Contact: vincent.giraud@stagiaires.ssi.gouv.fr
 *
 */

package org.globalplatform;

import javacard.framework.*;

/**
 * Implementation of the interface defining the global services server provided to security domains and applications.
 */
public class globalServiceProvider implements GlobalService
{
    private services[] registered_services;
    
    private class services
	{
	    Shareable shareable_of_service;
	    short service_name = (short) 0xFFFF;
	}

    public globalServiceProvider()
	{
	    registered_services = new services[PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE];
	}

    public Shareable getServiceInterface(GPRegistryEntry clientRegistryEntry,
                                                 short sServiceName,
                                                 byte[] baBuffer,
                                                 short sOffset,
                                                 short sLength)
                                                 throws javacard.framework.ISOException
	{
	    // TODO : verify that baBuffer is a global array, throw java.lang.SecurityException if it is not the case
	    // TODO : if we have enough privileges, we should check that the requesting application is not usurping another one, and throw 0x6982 if it is the case
	    // Additional security restrictions can be defined here, especially when involving authentification, via baBuffer. 0x6982 should be thrown in case of a security issue
	    short indexOfRequestedService = -1;
	    for(short i = 0; i < PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE && (registered_services[i].service_name != (short) 0xFFFF); i++)
	    {
		if(sServiceName == registered_services[i].service_name)
		{
		    indexOfRequestedService = i;
		    i = PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE; // To get out of the for loop
		}
	    }
	    if(indexOfRequestedService == (short) -1){ISOException.throwIt((short) 0x6A88);} // In case the service is not registered
	    return registered_services[indexOfRequestedService].shareable_of_service;
	}
}
