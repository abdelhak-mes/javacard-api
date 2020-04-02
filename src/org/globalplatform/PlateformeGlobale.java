package org.globalplatform;

/*-
 * #%L
 * Java Card API
 * %%
 * Copyright (C) 2020 National Cybersecurity Agency of France (ANSSI)
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import javacard.framework.*;

/**
 * <p>OPEN's bastion.</p>
 *
 * <p>Should be instanciated only once on the platform, during manufacturing.
 * All applets' installations should be done in accordance with this
 * GlobalPlatform implementation; implying no other applet can be loaded and
 * installed before this one, or after by short-circuiting GlobalPlatform.</p>
 *
 * @author Vincent Giraud
 */
public class PlateformeGlobale {

  /**
   * Maximum number of Executable Load File on the card
   */
  public static final byte MAX_ELF = 6;

  /**
   * Maximum number of Executable Module in each Executable Load File
   */
  public static final byte MAX_EM_PER_ELF = 4;

  /**
   * Maximum number of instances per Executable Module
   */
  public static final byte MAX_INSTANCES_PER_EM = 3;

  /**
   * Maximum number of global services provided by an application
   */
  public static final byte MAX_GLOBAL_SERVICES_PER_INSTANCE = 10;

  /**
   * Maximum number of childs a security domain can have
   */
  public static final byte MAX_CHILD_PER_INSTANCE = 8;

  /**
   * Maximum number of uniquely registered global services, for the whole card
   */
  public static final byte MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES = 32;

  /**
   * GP's implementation AID
   */
  public static final byte[] bGP_AID = {(byte)0xA0, (byte)0x00, (byte)0x00,
                                        (byte)0xA5, (byte)0x51, (byte)0x01}; //

  // TODO: Optional GP features still not implemented :
  //        - Memory resource management parameters
  //        - Counters associated with logs

  private entity_file[] entity_registry;
  private UniquelyRegistered[] uniquesServiceNames;
  private static PlateformeGlobale singleton = null;

  /**
   * Class constructor
   */
  private PlateformeGlobale() {
    entity_file.OPEN = this;
    entity_registry =
        new entity_file[MAX_ELF * MAX_EM_PER_ELF * MAX_INSTANCES_PER_EM];
    uniquesServiceNames =
        new UniquelyRegistered[MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES];

    // ISSUER SECURITY DOMAIN INITIALIZATION
    entity_registry[0].AID =
        new byte[] {(byte)0xA0, (byte)0x00, (byte)0x00, (byte)0xA5,
                    (byte)0x51, (byte)0x01}; // GP's implementation AID
    entity_registry[0].LifeCycleState = entity_file.CARD_OP_READY;
    entity_registry[0].PreviousLifeCycleState = entity_file.CARD_OP_READY;
    entity_registry[0].parent_security_domain_AID = new byte[] {
        (byte)0xA0, (byte)0x00, (byte)0x00, (byte)0xA5, (byte)0x51, (byte)0x01};
    entity_registry[0].privileges =
        new byte[] {(byte)0xFF, (byte)0xFF, (byte)0xFF};
    entity_registry[0].implicit_selection_parameter =
        0x00; // the issuer security domain is already, by default, the
              // implicitly selected application on all logical channels
    // entity_registry[0].global_services;      // leave this as
    // initialized
    entity_registry[0].current_nb_of_EM = -1;
    entity_registry[0].current_nb_of_instances = -1;
    entity_registry[0].is_ELF = false;
    entity_registry[0].is_EM = false;
    entity_registry[0].is_instance = true;
    // entity_registry[0].contained_EM;         // leave this as
    // initialized entity_registry[0].associated_instances;     // leave this as
    // initialized
  }

  /**
   * <p>PlateformeGlobale getinstance.</p>
   *
   * <p>As there is only one GlobalPlatform instance, we implement a singleton
   * design pattern to ensure this property</p>
   */
  public static PlateformeGlobale getInstance() {
    if (singleton == null) {
      singleton = new PlateformeGlobale();
    }
    return singleton;
  }

  private class UniquelyRegistered {
    short indexOfEntity = -1;
    short serviceName = (short)0xFFFF;
  }

  private class command_APDU {
    byte CLA;
    byte INS;
    byte P1;
    byte P2;
    int Lc;
    byte[] data;
    int Le;
    boolean Lc_presence = false;
    boolean Le_presence = false;
  }

  private class response_APDU {
    byte[] data;
    byte SW1;
    byte SW2;
  }

  /**
   * Return the entity corresponding to the given AID. It is searched throughout
   * the whole GP registry.
   *
   * @param AID_requested The AID of the entity that is searched.
   * @return The index of the first entity corresponding to the given AID. -1
   *     means the requested entity could not be found.
   */
  short find_by_AID(AID AID_requested) {
    for (short i = 0; i < (MAX_ELF * MAX_EM_PER_ELF * MAX_INSTANCES_PER_EM);
         i++) {
      if (AID_requested.equals(entity_registry[i].AID, (short)0,
                               (byte)entity_registry[i].AID.length)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Return the entity corresponding to the given index.
   *
   * @param index The index of the wanted entity.
   * @return The file corresponding to the index, or null if not possible.
   */
  entity_file get_by_index(short index) {
    if (index < 0 || index >= MAX_ELF * MAX_EM_PER_ELF * MAX_INSTANCES_PER_EM) {
      return null;
    } else {
      return entity_registry[index];
    }
  }

  /**
   * Find the appropriate entity_file instance and return it to the caller. This
   * will allow the caller to use the OPEN API.
   *
   * @param clientAID The AID of the calling entity.
   * @param parameter Optional parameter prescribed by the javacard API. Useless
   *     here, and can be set to anything.
   * @return A reference to the shareable object corresponding to the requesting
   *     entity.
   */
  public Shareable getShareableInterfaceObject(AID clientAID, byte parameter) {
    return entity_registry[find_by_AID(clientAID)];
  }

  /**
   * Return the card's life cycle state. No restriction or privileges
   * requirement is specified by the Global Platform specification.
   *
   * @return The card's life cycle state.
   */
  byte get_CardLifeCycleState() { return entity_registry[0].LifeCycleState; }

  /**
   * Allows to know if a global service name is already registered with one or
   * any of the instances installed on the card, or not. Useful when registering
   * a service name uniquely. If the <code>index</code> parameter is between 0
   * and MAX_ELF*MAX_EM_PER_ELF*MAX_INSTANCES_PER_EM , then the service name is
   * looked for in the corresponding entity. If it is negative, it is looked for
   * in all entities and the first occurence is returned.
   *
   * @param serviceName The service name looked for.
   * @param index An index pointing to the entity in which the service can be
   *     looked for. If negative, the service is searched for everywhere.
   * @return <code>true</code> if the service name is already registered
   *     anywhere or with the corresponding applications installed on the card.
   *     <code>false</code> otherwise; notably if index points to a non-existing
   *     entity.
   */
  boolean isRegistered(short serviceName, short index) {
    if (index < 0 ||
        index >= MAX_ELF * MAX_EM_PER_ELF *
                     MAX_INSTANCES_PER_EM) // index out of bound, search on all
                                           // entities
    {
      for (short i = 0; i < MAX_ELF * MAX_EM_PER_ELF * MAX_INSTANCES_PER_EM;
           i++) // for each entity
      {
        if (entity_registry[i].is_instance) // if it is an instance
        {
          for (short j = 0;
               j < PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE * 2;
               j = (short)(j + 2)) {
            if (Util.getShort(entity_registry[i].global_services, j) ==
                serviceName) {
              return true;
            }
          }
        }
      }
    } else // correct index, search only in the corresponding entity if it
           // exists
    {
      if (get_by_index(index).is_instance) {
        for (short i = 0;
             i < PlateformeGlobale.MAX_GLOBAL_SERVICES_PER_INSTANCE * 2;
             i = (short)(i + 2)) {
          if (Util.getShort(get_by_index(index).global_services, i) ==
              serviceName) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Flags a registered global service name as unique. No other application will
   * be able to register this service name as long as this is the case.
   *
   * @param indexOfApplication The index of the application containing the
   *     service that needs to be registered as unique.
   * @param serviceName The two bytes corresponding to the family and the name
   *     of the service that should be unique.
   *
   * @return <code>true</code> if the service name has been properly registered
   *     as unique by the OPEN. <code>false</code> otherwise.
   */
  boolean addUniquelyRegisteredServiceName(short indexOfApplication,
                                           short serviceName) {
    short i;
    for (i = 0; (i < MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES) &&
                (uniquesServiceNames[i].indexOfEntity != -1);
         i++) {
    }
    if (i ==
        MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES) // table of uniquely registered
                                                 // services is full
    {
      return false;
    }

    uniquesServiceNames[i].indexOfEntity = indexOfApplication;
    uniquesServiceNames[i].serviceName = serviceName;
    return true;
  }

  /**
   * Remove the uniqueness of a registered global service. Other application
   * will now be able to register the same service name.
   *
   * @param indexOfApplication The index of the application containing the
   *     service that will no longer be unique.
   * @param serviceName The two bytes corresponding to the family and the name
   *     of the service that will no longer be unique.
   *
   * @return <code>true</code> if the service name has been properly
   *     unregistered as unique by the OPEN. <code>false</code> otherwise.
   */
  boolean removeUniquelyRegisteredServiceName(short indexOfApplication,
                                              short serviceName) {
    short i;
    for (i = 0; (i < MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES) &&
                ((uniquesServiceNames[i].serviceName != serviceName) ||
                 (uniquesServiceNames[i].indexOfEntity != indexOfApplication));
         i++) {
    }
    if (i == MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES) // the service that should
                                                      // be removed is not
                                                      // actually in our table
    {
      return false;
    }

    uniquesServiceNames[i].indexOfEntity = -1;
    uniquesServiceNames[i].serviceName = (short)0xFFFF;
    return true;
  }

  /**
   * Allows to know if a global service name is already registered with any of
   * the instances installed on the card as unique, or not.
   *
   * @return The index of the owning entity if the service name is registered as
   *     unique. -1 otherwise.
   */
  short isRegisteredUniquely(short serviceName) {
    for (short i = 0; i < MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES;
         i++) // for each unique service name slot
    {
      if (uniquesServiceNames[i].serviceName == serviceName) {
        return uniquesServiceNames[i].indexOfEntity;
      }
    }
    return (short)-1;
  }

  /**
   * Allows to get a service name registered as unique in the OPEN, and
   * belonging to the family passed as parameter. The first corresponding result
   * is returned, and since the global service register is never organized and
   * ranked; it can be considered that any family member can be returned
   * randomly, if there are several family member in the register.
   *
   * @param familyName The function will look for the first occurence of member
   *     of this family.
   * @return The first corresponding service name that has been found. 0xFFFF if
   *     none has been found (it is an invalid value as specified in the
   *     GlobalPlatform specification).
   */
  short find_uniquely_registered_family_member(byte familyName) {
    for (short i = 0; i < MAX_UNIQUELY_REGISTERED_GLOBAL_SERVICES;
         i++) // for each unique service name slot
    {
      if ((byte)(uniquesServiceNames[i].serviceName >> 8) == familyName) {
        return uniquesServiceNames[i].serviceName;
      }
    }
    return (short)0xFFFF;
  }

  /**
   * The function started when a command APDU has been received. It will process
   * the incoming APDU and prepare the response one.
   * TODO : Extended fields support is unfinished : APDUs can contain unsigned
   * 16 bits numbers, where 0x0000 corresponds to 2^16. As these cannot be
   * stored and processed with java card's <code>short</code> type,
   * <code>int</code> must be used, however this prevents the use of
   * Util.arrayCopy(...).
   *
   * @param command_APDU The command APDU that has just been received. The
   *     array's length should correspond to the APDU's length; there should not
   *     be any padding.
   * @return The response APDU, or <code>null</code> if anything went wrong.
   */
  byte[] process_command_APDU(byte[] command_APDU) {
    if (command_APDU == null) {
      return null;
    }
    if (command_APDU.length < 4) {
      return null;
    } // Header cannot be complete
    if (command_APDU.length > 65544) {
      return null;
    } // Above maximum length

    command_APDU received_APDU = new command_APDU();
    received_APDU.CLA = command_APDU[0];
    received_APDU.INS = command_APDU[1];
    received_APDU.P1 = command_APDU[2];
    received_APDU.P2 = command_APDU[3];

    if (command_APDU.length > 4) // if there is more than just the header
    {
      if (command_APDU[4] != (byte)0x00) // if the first field is a short one
      {
        if (command_APDU.length > 5) // if the first field is Lc
        {
          received_APDU.Lc = read_APDU_byte_field(command_APDU[4]);
          received_APDU.Lc_presence = true;
          if (command_APDU.length ==
              (received_APDU.Lc + 5)) // if there is not a Le field at the end
          {
            Util.arrayCopy(command_APDU, (short)5, received_APDU.data, (short)0,
                           (short)received_APDU.Lc);
          } else if (command_APDU.length ==
                     (received_APDU.Lc +
                      6)) // if there is a Le field at the end
          {
            Util.arrayCopy(command_APDU, (short)5, received_APDU.data, (short)0,
                           (short)received_APDU.Lc);
            received_APDU.Le = read_APDU_byte_field(
                command_APDU[(short)(command_APDU.length - 1)]);
            received_APDU.Le_presence = true;
          }
        } else // if the first field is Le
        {
          received_APDU.Le = read_APDU_byte_field(command_APDU[4]);
          received_APDU.Le_presence = true;
        }
      } else // if the first field is an extended one
      {
        if (command_APDU.length > 7) // if the first field is Lc
        {
          received_APDU.Lc =
              read_APDU_short_field(command_APDU[5], command_APDU[6]);
          received_APDU.Lc_presence = true;
          if (command_APDU.length ==
              (received_APDU.Lc + 7)) // if there is not a Le field at the end
          {
            Util.arrayCopy(command_APDU, (short)7, received_APDU.data, (short)0,
                           (short)received_APDU.Lc);
          } else if (command_APDU.length ==
                     (received_APDU.Lc +
                      9)) // if there is a Le field at the end
          {
            Util.arrayCopy(command_APDU, (short)7, received_APDU.data, (short)0,
                           (short)received_APDU.Lc);
            received_APDU.Le = read_APDU_short_field(
                command_APDU[(short)(command_APDU.length - 2)],
                command_APDU[(short)(command_APDU.length - 1)]);
            received_APDU.Le_presence = true;
          }
        } else // if the first field is Le
        {
          received_APDU.Le =
              read_APDU_short_field(command_APDU[5], command_APDU[6]);
          received_APDU.Le_presence = true;
        }
      }
    }

    // TODO : actually apply the APDU request here
    /*
      switch(received_APDU.CLA)
      {
        case ...
      }
    */

    return null; // TODO : return an actual response APDU here ?
  }

  /**
   * This function converts a short field (short in the sense of ISO7816-3, not
   * as the javacard type) to a number contained in an int (as the javacard
   * type) variable. This is necessary because of some specificities, for
   * example in a ISO7816 short field, 0x00 = 256.
   *
   * @param byteField The short field as it is in the APDU.
   * @return The actual value described by the short field.
   */
  int read_APDU_byte_field(byte byteField) {
    if ((byteField + 128) == 0) {
      return (int)256;
    } else {
      return (int)byteField + 128;
    }
  }

  /**
   * This function converts an extended field (as in ISO7816-3) to a number
   * contained in an int variable. This is necessary because of some
   * specificities, for example the absence of negative values. An extended
   * field is composed of 16 bits, hence the two bytes parameters.
   *
   * @param MostSignificantByte The most significant byte of the 16 bits
   *     extended field.
   * @param LeastSignificantByte The least significant byte of the 16 bits
   *     extended field.
   * @return The actual value described by the extended field.
   */
  int read_APDU_short_field(byte MostSignificantByte,
                            byte LeastSignificantByte) {
    return 0x8000 | (MostSignificantByte << 8) | LeastSignificantByte;
  }
}
