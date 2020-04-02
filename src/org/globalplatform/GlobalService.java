
package org.globalplatform;

import javacard.framework.*;

/**
 * This interface allows requesting a Global Services Application for a
 * Shareable Interface Object (SIO) providing the actual service.<p>
 *
 * To retrieve an instance of this interface, an Application shall invoke the
 * {@link GPSystem#getService} method.<p>
 *
 * @since export file version 1.1
 */

public interface GlobalService extends Shareable
{
  /**
   * Key Access indicating key may be used by the Security Domain and any
   * associated Application (0x00).
   */
  public static final byte KEY_ACCESS_ANY = (byte) 0x00;

  /**
   * Key Access indicating key may be used by the Security Domain but not by any
   * associated Application (0x01).
   */
  public static final byte KEY_ACCESS_SECURITY_DOMAIN = (byte) 0x01;

  /**
   * Key Access indicating key may be used by any associated Application but not
   * by the Security Domain (0x02).
   */
  public static final byte KEY_ACCESS_APPLICATION = (byte) 0x02;

  /**
   * Key type indicating AES (0x88).
   */
  public static final byte KEY_TYPE_AES = (byte) 0x88;

  /**
   * Key type indicating Triple DES reserved for specific implementations (0x81).
   */
  public static final byte KEY_TYPE_3DES = (byte) 0x81;

  /**
   * Key type indicating Triple DES in CBC mode (0x82).
   */
  public static final byte KEY_TYPE_3DES_CBC = (byte) 0x82;

  /**
   * Key type indicating DES with ECB/CBC implicitly known (0x80).
   */
  public static final byte KEY_TYPE_DES = (byte) 0x80;

  /**
   * Key type indicating DES in CBC mode (0x84).
   */
  public static final byte KEY_TYPE_DES_CBC = (byte) 0x84;

  /**
   * Key type indicating DES in ECB mode (0x83).
   */
  public static final byte KEY_TYPE_DES_ECB = (byte) 0x83;

  /**
   * Key type indicating extended key format (0xFF).
   */
  public static final byte KEY_TYPE_EXTENDED = (byte) 0xFF;

  /**
   * Key type indicating HMAC SHA1, length of HMAC implicitly known (0x90).
   */
  public static final byte KEY_TYPE_HMAC_SHA1 = (byte) 0x90;

  /**
   * Key type indicating HMAC SHA1, length of HMAC is 160 bits (0x91).
   */
  public static final byte KEY_TYPE_HMAC_SHA1_160 = (byte) 0x91;

  /**
   * Key type indicating RSA Private Key Chinese Remainder p component (0xA4).
   */
  public static final byte KEY_TYPE_RSA_PRIVATE_CRT_P = (byte) 0xA4;

  /**
   * Key type indicating RSA Private Key Chinese Remainder q component (0xA5).
   */
  public static final byte KEY_TYPE_RSA_PRIVATE_CRT_Q = (byte) 0xA5;

  /**
   * Key type indicating RSA Private Key Chinese Remainder pq component (0xA6).
   */
  public static final byte KEY_TYPE_RSA_PRIVATE_CRT_PQ = (byte) 0xA6;

  /**
   * Key type indicating RSA Private Key Chinese Remainder dp1 component (0xA7).
   */
  public static final byte KEY_TYPE_RSA_PRIVATE_CRT_DP1 = (byte) 0xA7;

  /**
   * Key type indicating RSA Private Key Chinese Remainder dq1 component (0xA8).
   */
  public static final byte KEY_TYPE_RSA_PRIVATE_CRT_DQ1 = (byte) 0xA8;

  /**
   * Key type indicating RSA Private exponent (0xA3).
   */
  public static final byte KEY_TYPE_RSA_PRIVATE_EXPONENT = (byte) 0xA3;

  /**
   * Key type indicating RSA Private Key modulus (0xA2).
   */
  public static final byte KEY_TYPE_RSA_PRIVATE_MODULUS = (byte) 0xA2;

  /**
   * Key type indicating RSA Public Key exponent (0xA0).
   */
  public static final byte KEY_TYPE_RSA_PUBLIC_EXPONENT = (byte) 0xA0;

  /**
   * Key type indicating RSA Public Key modulus (0xA1).
   */
  public static final byte KEY_TYPE_RSA_PUBLIC_MODULUS = (byte) 0xA1;

  /**
   * Key usage indicating computation and decipherment (0x40).
   */
  public static final byte KEY_USAGE_COMPUTATION_DECIPHERMENT = (byte) 0x40;

  /**
   * Key usage indicating sensitive data confidentiality (0x08).
   */
  public static final byte KEY_USAGE_CONFIDENTIALITY = (byte) 0x08;

  /**
   * Key usage indicating cryptographic authorization (0x01).
   */
  public static final byte KEY_USAGE_CRYPTOGRAPHIC_AUTHORIZATION = (byte) 0x01;

  /**
   * Key usage indicating cryptographic checksum e.g. MAC (0x04).
   */
  public static final byte KEY_USAGE_CRYPTOGRAPHIC_CHECKSUM = (byte) 0x04;

  /**
   * Key usage indicating Digital Signature (0x02).
   */
  public static final byte KEY_USAGE_DIGITAL_SIGNATURE = (byte) 0x02;

  /**
   * Key usage indicating Secure Messaging in command data field (0x10).
   */
  public static final byte KEY_USAGE_SM_COMMAND = (byte) 0x10;

  /**
   * Key usage indicating Secure Messaging in response data field (0x20).
   */
  public static final byte KEY_USAGE_SM_RESPONSE = (byte) 0x20;

  /**
   * Key usage indicating verification and encipherment (0x80).
   */
  public static final byte KEY_USAGE_VERIFICATION_ENCIPHERMENT = (byte) 0x80;

  /**
   * Gets a Shareable Interface Object (SIO) actually providing the requested service.<p>
   *
   * The Application invoking this method shall set the
   * <code>clientRegistryEntry</code> to its own {@link GPRegistryEntry}
   * instance.<p>
   *
   * The Global Services Application shall verify the validity of the request
   * according to its own security policies for the specified
   * <code>sServiceName</code>, based on the identity and characteristics of the
   * Application invoking this method as registered by the specified
   * <code>clientRegistryEntry</code>, and possibly based on the data contained
   * in the <code>baBuffer</code> byte array.<p>
   *
   * If the request is valid, the Global Service Application returns a SIO
   * implementing the actual service: this SIO may either be this {@link
   * GlobalService} instance or another object. If the request is deemed to be
   * invalid, the Global Services Application shall reject the request by either
   * throwing an exception or returning <code>null</code>.<p>
   *
   * It is assumed that the Application invoking this method is aware of the
   * interface (extension of the {@link Shareable} interface) to which the
   * retrieved SIO shall be casted in order to acces the service.<p>
   *
   * Notes:<ul>
   *
   * <li><em>It shall be noticed that an Application having the Global Registry
   * Privilege could potentially invoke this method with the
   * <code>clientRegistryEntry</code> parameter set to the {@link
   * GPRegistryEntry} instance of another Application. If the Global Services
   * Application itself has the Global Registry Privilege, it may explicitly
   * retrieve and check the {@link GPRegistryEntry} instance of the Application
   * invoking this method, by performing the following call:
   * <code>GPSystem.getRegistryEntry(JCSystem.getPreviousContextAID())</code>.</em>
   * 
   * </ul>
   * 
   * @param clientRegistryEntry the {@link GPRegistryEntry} instance of the
   * requesting Application.<p>
   * @param sServiceName a service name identifying the requested service.<p>
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
   * @param baBuffer byte array containing additional parameters of the request,
   * potentially authentication data. Must be <em>global</em> byte array.
   * @param sOffset offset of the additional parameters.
   * @param sLength length of the additional parameters.
   *
   * @return the SIO providing the actual service, or <code>null</code> if the
   * service is not available or the request was rejected. Alternatively, this
   * method may reject the request by throwing an <code>ISOException</code>.
   *
   * @exception ISOException if the request was rejected. Although not
   * mandatory, it is recommended to use one of the following reason codes:<ul>
   * <li>'6A88' if the specified service was not found or is not available.
   * <li>'6982' if some security conditions are not satisfied.
   * <li>'6985' if some other conditions are not satisfied.
   * </ul>
   * Alternatively, this method may reject the request by returning <code>null</code>.
   *
   * @exception SecurityException if the Global Services Application requires
   * reading data from <code>baBuffer</code> and <code>baBuffer</code> is not a
   * <em>global</em> byte array.
   * @exception NullPointerException if the Global Services Application requires
   * reading data from <code>baBuffer</code> and <code>baBuffer</code> is
   * <code>null</code>.
   * @exception ArrayIndexOutOfBoundsException if the Global Services
   * Application requires reading data from <code>baBuffer</code> and reading
   * data would cause access of data outside array bounds.
   *
   * @see GPSystem#getService
   * @see GPSystem#FAMILY_CVM
   * @see GPSystem#FAMILY_SECURE_CHANNEL
   * @see GPSystem#FAMILY_USSM
   * @see GPSystem#FAMILY_AUTHORITY
   * @see GPSystem#FAMILY_HTTP_ADMINISTRATION
   * @see GPSystem#FAMILY_HTTP_REPORT
   */
  public Shareable getServiceInterface(GPRegistryEntry clientRegistryEntry, short sServiceName, byte[] baBuffer, short sOffset, short sLength) throws ISOException;

}
