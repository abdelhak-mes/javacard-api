/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework.service;

import javacard.framework.APDU;

/**
 * This is the base interface for the service framework on the Java Card
 * platform. A <code>Service</code> is an object that is able to perform
 * partial or complete processing on a set of incoming commands encapsulated in
 * an APDU.
 * <p>
 * Services collaborate in pre-processing, command processing and
 * post-processing of incoming <code>APDU</code> commands. They share the same
 * <code>APDU</code> object by using the communication framework and the
 * Common Service Format (CSF) defined in <code>BasicService</code>. An
 * application is built by combining pre-built and newly defined Services within
 * a <code>Dispatcher</code> object.
 *
 * @see BasicService BasicService
 *
 * @version 1.0
 */
public interface Service {

  /**
   * Pre-processes the input data for the command in the <code>APDU</code>
   * object. When invoked, the APDU object should either be in
   * <code>STATE_INITIAL</code> with the APDU buffer in the Init format or
   * in <code>STATE_FULL_INCOMING</code> with the APDU buffer in the Input
   * Ready format defined in <code>BasicService</code>.
   * <p>
   * The method must return <code>true</code> if no more pre-processing
   * should be performed, and <code>false</code> otherwise. In particular,
   * it must return <code>false</code> if it has not performed any
   * processing on the command.
   * <P>
   * After normal completion, the <code>APDU</code> object is usually in
   * <code>STATE_FULL_INCOMING</code> with the APDU buffer in the Input
   * Ready format defined in <code>BasicService</code>. However, in some
   * cases if the Service processes the command entirely, the
   * <code>APDU</code> object may be in <code>STATE_OUTGOING</code> with
   * the APDU buffer in the Output Ready format defined in
   * <code>BasicService</code>.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return <code>true</code> if input processing is finished,
   *         <code>false</code> otherwise
   */
  public boolean processDataIn(APDU apdu);

  /**
   * Processes the command in the <code>APDU</code> object. When invoked,
   * the <code>APDU</code> object should normally be in
   * <code>STATE_INITIAL</code> with the APDU buffer in the Init format or
   * in <code>STATE_FULL_INCOMING</code> with the <code>APDU</code> buffer
   * in the Input Ready format defined in <code>BasicService</code>.
   * However, in some cases, if a pre-processing service has processed the
   * command entirely, the <code>APDU</code> object may be in
   * <code>STATE_OUTGOING</code> with the APDU buffer in the Output Ready
   * format defined in <code>BasicService</code>.
   * <p>
   * The method must return <code>true</code> if no more command processing
   * is required, and <code>false</code> otherwise. In particular, it should
   * return <code>false</code> if it has not performed any processing on the
   * command.
   * <P>
   * After normal completion, the <code>APDU</code> object must be in
   * <code>STATE_OUTGOING</code> and the output response must be in the APDU
   * buffer in the Output Ready format defined in <code>BasicService</code>.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return <code>true</code> if the command has been processed,
   *         <code>false</code> otherwise
   */
  public boolean processCommand(APDU apdu);

  /**
   * Post-processes the output data for the command in the <code>APDU</code>
   * object. When invoked, the <code>APDU</code> object should be in
   * <code>STATE_OUTGOING</code> with the APDU buffer in the Output Ready
   * format defined in <code>BasicService</code>.
   * <p>
   * The method should return <code>true</code> if no more post-processing
   * is required, and <code>false</code> otherwise. In particular, it should
   * return <code>false</code> if it has not performed any processing on the
   * command.
   * <P>
   * After normal completion, the <code>APDU</code> object should must be in
   * <code>STATE_OUTGOING</code> and the output response must be in the APDU
   * buffer in the Output Ready format defined in <code>BasicService</code>.
   *
   * @param apdu
   *            the <code>APDU</code> object containing the command being
   *            processed
   * @return <code>true</code> if output processing is finished,
   *         <code>false</code> otherwise
   */
  public boolean processDataOut(APDU apdu);
}
