/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework.service;

import java.rmi.Remote;
import javacard.framework.APDU;
import javacard.framework.APDUException;
import javacard.framework.CardException;
import javacard.framework.CardRuntimeException;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.PINException;
import javacard.framework.SystemException;
import javacard.framework.TransactionException;
import javacard.framework.UserException;
import javacard.framework.Util;

/**
 * An implementation of a service that is used to process Java Card platform RMI
 * requests for remotely accessible objects.
 *
 */
public class RMIService extends BasicService implements RemoteService {

  /**
   * The default INS value (0x38) used for the remote method invocation
   * command (INVOKE) in the Java Card platform RMI protocol.
   */
  public static final byte DEFAULT_RMI_INVOKE_INSTRUCTION = 0x38;

  /**
   * Creates a new <code>RMIService</code> and sets the specified remote
   * object as the initial reference for the applet. The initial reference
   * will be published to the client in response to the SELECT APDU command
   * processed by this object.
   * <p>
   * The <code>RMIService</code> instance may create session data to manage
   * exported remote objects for the current applet session in
   * <code>CLEAR_ON_DESELECT</code> transient space.
   *
   * @param initialObject
   *            the remotely accessible initial object
   * @throws NullPointerException
   *             if the <code>initialObject</code> parameter is
   *             <code>null</code>
   */
  public RMIService(Remote initialObject) throws NullPointerException {
    // TODO: not yet implemented !!!
  }

  /**
   * Defines the instruction byte to be used in place of
   * <code>DEFAULT_RMI_INVOKE_INSTRUCTION</code> in the Java Card platform
   * RMI protocol for the INVOKE commands used to access the
   * <code>RMIService</code> for remote method invocations.
   * <p>
   * Note:
   * <ul>
   * <li><em>The new instruction byte goes into effect next time this
   * <code>RMIService</code> instance processes an applet SELECT command. The
   * Java Card platform RMI protocol until then is unchanged.</em>
   * </ul>
   *
   * @param ins
   *            the instruction byte
   */
  public void setInvokeInstructionByte(byte ins) {
    // TODO: not yet implemented !!!
  }

  /**
   * Processes the command within the <code>APDU</code> object. When
   * invoked, the APDU object should either be in <code>STATE_INITIAL</code>
   * with the APDU buffer in the Init format or in
   * <code>STATE_FULL_INCOMING</code> with the APDU buffer in the Input
   * Ready format defined in <code>BasicService</code>.
   * <p>
   * This method first checks if the command in the <code>APDU</code> object
   * is a Java Card platform RMI access command. The Java Card platform RMI
   * access commands currently defined are: Applet SELECT and INVOKE. If it is
   * not a Java Card platform RMI access command, this method does nothing and
   * returns false.
   * <p>
   * If the command is a Java Card platform RMI access command, this method
   * processes the command and generates the response to be returned to the
   * terminal. For a detailed description of the APDU protocol used in Java
   * Card platform RMI access commands please see the Remote Method Invocation
   * Service chapter of <em>Runtime Environment
   * Specification, Java Card Platform, Classic Edition</em>.
   * <p>
   * Java Card platform RMI access commands are processed as follows:
   * <ul>
   * <li>An applet SELECT command results in a Java Card platform RMI
   * information structure in FCI format containing the initial reference
   * object as the response to be returned to the terminal.
   * <li>An INVOKE command results in the following sequence -
   * </ul>
   * <ol>
   * <li><em>The remote object is located. A remote object is accessible only if
   * it was returned by this <code>RMIService</code> instance and since that
   * time some applet instance or the other from within the applet package has
   * been an active applet instance.</em> <li><em>The method of the object is
   * identified</em> <li><em>Primitive input parameters are unmarshalled onto
   * the stack. Array type input parameters are created as global arrays(See
   * Runtime Environment Specification, Java Card Platform, Classic
   * Edition)</em> and references to these are pushed onto the stack.
   * <li><em>An INVOKEVIRTUAL bytecode to the remote method is simulated</em>
   * <li>
   * <em>Upon return from the method, method return or exception information
   * is marshalled from the stack as the response to be returned to the
   * terminal</em>
   * </ol>
   * <p>
   * After normal completion, this method returns <code>true</code> and the
   * APDU object is in <code>STATE_OUTGOING</code> and the output response
   * is in the APDU buffer in the Output Ready format defined in
   * <code>BasicService</code>.
   *
   * @param apdu
   *            the APDU object containing the command being processed.
   * @return <code>true</code> if the command has been processed,
   *         <code>false</code> otherwise
   * @throws ServiceException
   *             with the following reason codes:
   *             <ul>
   *             <li><code>ServiceException.CANNOT_ACCESS_IN_COMMAND</code>
   *             if this is a Java Card platform RMI access command and the
   *             APDU object is not in STATE_INITIAL or in STATE_FULL_INCOMING
   *             <li><code>ServiceException.REMOTE_OBJECT_NOT_EXPORTED</code>
   *             if the remote method returned a remote object which has not
   *             been exported.
   *             </ul>
   * @throws TransactionException
   *             with the following reason code:
   *             <ul>
   *             <li><code>TransactionException.IN_PROGRESS</code> if this
   *             is a Java Card platform RMI INVOKE command and the remote
   *             method returned a remote object which has been exported
   *             within a transaction which is still in progress or if this is
   *             an applet SELECT command and the response information in the
   *             APDU buffer includes an initial reference object which has
   *             been exported within a transaction which is still in
   *             progress.
   *             </ul>
   * @throws SecurityException
   *             if one of the following conditions is met:
   *             <ul>
   *             <li> if this is a Java Card platform RMI INVOKE command and a
   *             firewall security violation occurred while trying to simulate
   *             an INVOKEVIRTUAL bytecode on the remote object.
   *             <li> if internal storage in <code>CLEAR_ON_DESELECT</code>
   *             transient space is accessed when the currently active context
   *             is not the context of the currently selected applet.
   *             <li> if this is a Java Card platform RMI INVOKE command and
   *             the invoked remote method returns an object or throws an
   *             exception object which is not accessible in the context of
   *             the currently selected applet.
   *             </ul>
   * @see CardRemoteObject CardRemoteObject
   */
  public boolean processCommand(APDU apdu) {
    // TODO: not yet implemented !!!
    return false;
  }
}
