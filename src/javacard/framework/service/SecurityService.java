/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework.service;

/**
 * This interface describes the functions of a generic security service. It
 * extends the base <code>Service</code> interface and defines methods to
 * query the current security status. Note that this interface is generic and
 * does not include methods to initialize and change the security status of the
 * service; initialization is assumed to be performed through <code>APDU</code>
 * commands that the service is able to process.
 * <p>
 * A security service implementation class should extend
 * <code>BasicService</code> and implement this interface.
 *
 * @version 1.0
 */
public interface SecurityService extends Service {

  /**
   * This security property provides input confidentiality through encryption
   * of the incoming command. Note that this is a bit mask and security
   * properties can be combined by simply adding them together.
   */
  public final static byte PROPERTY_INPUT_CONFIDENTIALITY = 1;

  /**
   * This security property provides input integrity through MAC signature
   * checking of the incoming command. Note that this is a bit mask and
   * security properties can be combined by simply adding them together.
   */
  public final static byte PROPERTY_INPUT_INTEGRITY = 2;

  /**
   * This security property provides output confidentiality through encryption
   * of the outgoing response. Note that this is a bit mask and security
   * properties can be combined by simply adding them together.
   */
  public final static byte PROPERTY_OUTPUT_CONFIDENTIALITY = 4;

  /**
   * This security property provides output integrity through MAC signature
   * generation for the outgoing response. Note that this is a bit mask and
   * security properties can be combined by simply adding them together.
   */
  public final static byte PROPERTY_OUTPUT_INTEGRITY = 8;

  /**
   * The principal identifier for the cardholder.
   */
  public final static short PRINCIPAL_CARDHOLDER = 1;

  /**
   * The principal identifier for the card issuer.
   */
  public final static short PRINCIPAL_CARD_ISSUER = 2;

  /**
   * The principal identifier for the application provider.
   */
  public final static short PRINCIPAL_APP_PROVIDER = 3;

  /**
   * Checks whether or not the specified principal is currently authenticated.
   * The validity timeframe (selection or reset) and authentication method as
   * well as the exact interpretation of the specified principal parameter
   * needs to be detailed by the implementation class. The only generic
   * guarantee is that the authentication has been performed in the current
   * card session.
   *
   * @param principal
   *            an identifier of the principal that needs to be authenticated
   * @return true if the expected principal is authenticated
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             specified principal is unknown.
   *             </ul>
   */
  public boolean isAuthenticated(short principal) throws ServiceException;

  /**
   * Checks whether a secure channel is established between the card and the
   * host for the ongoing session that guarantees the indicated properties.
   *
   * @param properties
   *            the required properties
   * @return true if the required properties are true, false otherwise
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             specified property is unknown.
   *             </ul>
   */
  public boolean isChannelSecure(byte properties) throws ServiceException;

  /**
   * Checks whether a secure channel is in use between the card and the host
   * for the ongoing command that guarantees the indicated properties. The
   * result is only correct after pre-processing the command (for instance
   * during the processing of the command). For properties on incoming data,
   * the result is guaranteed to be correct; for outgoing data, the result
   * reflects the expectations of the client software, with no other
   * guarantee.
   *
   * @param properties
   *            the required properties
   * @return true if the required properties are <code>true</code>,
   *         <code>false</code> otherwise
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             specified property is unknown.
   *             </ul>
   */
  public boolean isCommandSecure(byte properties) throws ServiceException;
}
