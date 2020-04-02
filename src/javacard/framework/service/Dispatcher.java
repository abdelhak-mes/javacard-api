/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework.service;

import javacard.framework.APDU;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Util;

/**
 * A <code>Dispatcher</code> is used to build an application by aggregating
 * several services.
 * <p>
 * The dispatcher maintains a registry of <code>Service</code> objects. A
 * <code>Service</code> is categorized by the type of processing it performs:
 * <ul>
 * <li><em>A pre-processing service pre-processes input data for the command
 * being processed. It is associated with the <code>PROCESS_INPUT_DATA</code>
 * phase.</em></li> <li><em>A command processing service processes the input
 * data and generates output data. It is associated with the
 * <code>PROCESS_COMMAND</code> phase.</em></li> <li><em>A post-processing
 * service post-processes the generated output data. It is associated with the
 * <code>PROCESS_OUTPUT_DATA</code> phase.</em></li>
 * </ul>
 * The dispatcher simply dispatches incoming <code>APDU</code> object
 * containing the command being processed to the registered services.
 *
 * @version 1.0
 */
public class Dispatcher {

  /**
   * Identifies the null processing phase.
   */
  public static final byte PROCESS_NONE = (byte)0;

  /**
   * Identifies the input data processing phase.
   */
  public static final byte PROCESS_INPUT_DATA = (byte)1;

  /**
   * Identifies the main command processing phase.
   */
  public static final byte PROCESS_COMMAND = (byte)2;

  /**
   * Identifies the output data processing phase.
   */
  public static final byte PROCESS_OUTPUT_DATA = (byte)3;

  /**
   * Creates a <code>Dispatcher</code> with a designated maximum number of
   * services.
   *
   * @param maxServices
   *            the maximum number of services that can be registered to this
   *            dispatcher
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             maxServices parameter is negative.
   *             </ul>
   */
  public Dispatcher(short maxServices) throws ServiceException {
    // TODO: not yet implemented !!!
  }

  /**
   * Atomically adds the specified service to the dispatcher registry for the
   * specified processing phase. Services are invoked in the order in which
   * they are added to the registry during the processing of that phase. If
   * the requested service is already registered for the specified processing
   * phase, this method does nothing.
   *
   * @param service
   *            the Service to be added to the dispatcher
   * @param phase
   *            the processing phase associated with this service
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.DISPATCH_TABLE_FULL</code> if
   *             the maximum number of registered services is exceeded.
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             phase parameter is undefined or if the service parameter is
   *             null.
   *             </ul>
   */
  public void addService(Service service, byte phase) throws ServiceException {
    // TODO: not yet implemented !!!
  }

  /**
   * Atomically removes the specified service for the specified processing
   * phase from the dispatcher registry. Upon removal, the slot used by the
   * specified service in the dispatcher registry is available for re-use. If
   * the specified service is not registered for the specified processing
   * phase, this method does nothing.
   *
   * @param service
   *            the <code>Service</code> to be deleted from the dispatcher
   * @param phase
   *            the processing phase associated with this service
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             phase parameter is unknown or if the service parameter is
   *             null.
   *             </ul>
   */
  public void removeService(Service service, byte phase)
      throws ServiceException {
    // TODO: not yet implemented !!!
  }

  /**
   * Manages the processing of the command in the <code>APDU</code> object.
   * This method is called when only partial processing using the registered
   * services is required or when the APDU response following an error during
   * the processing needs to be controlled.
   * <p>
   * It sequences through the registered services by calling the appropriate
   * processing methods. Processing starts with the phase indicated in the
   * input parameter. Services registered for that processing phase are called
   * in the sequence in which they were registered until all the services for
   * the processing phase have been called or a service indicates that
   * processing for that phase is complete by returning <code>true</code>
   * from its processing method. The dispatcher then processes the next phases
   * in a similar manner until all the phases have been processed. The
   * <code>PROCESS_OUTPUT_DATA</code> processing phase is performed only if
   * the command processing has completed normally (<code>APDU</code>
   * object state is <code>APDU.STATE_OUTGOING</code>).
   * <p>
   * The processing sequence is <code>PROCESS_INPUT_DATA</code> phase,
   * followed by the <code>PROCESS_COMMAND</code> phase and lastly the
   * <code>PROCESS_OUTPUT_DATA</code>. The processing is performed as
   * follows:
   * <ul>
   * <li><code>PROCESS_INPUT_DATA</code> phase invokes the
   * <code>Service.processDataIn(APDU)</code> method
   * <li><code>PROCESS_COMMAND</code> phase invokes the
   * <code>Service.processCommand(APDU)</code> method
   * <li><code>PROCESS_OUTPUT_DATA</code> phase invokes the
   * <code>Service.processDataOut(APDU)</code> method
   * </ul>
   * If the command processing completes normally, the output data, assumed to
   * be in the APDU buffer in the Common Service Format (CSF) defined in
   * <code>BasicService</code>, is sent using <code>APDU.sendBytes</code>
   * and the response status is generated by throwing an
   * <code>ISOException</code> exception. If the command could not be
   * processed, <code>null</code> is returned. If any exception is thrown by
   * a Service during the processing, that exception is returned.
   * <p>
   *
   * @param command
   *            the APDU object containing the command to be processed
   * @param phase
   *            the processing phase to perform first
   * @return an exception that occurred during the processing of the command,
   *         or <code>null</code> if the command could not be processed
   * @throws ServiceException
   *             with the following reason code:
   *             <ul>
   *             <li><code>ServiceException.ILLEGAL_PARAM</code> if the
   *             phase parameter is PROCESS_NONE or an undefined value.
   *             </ul>
   * @see BasicService BasicService
   */
  public Exception dispatch(APDU command, byte phase) throws ServiceException {
    // TODO: not yet implemented !!!
    return null;
  }

  /**
   * Manages the entire processing of the command in the APDU object input
   * parameter. This method is called to delegate the complete processing of
   * the incoming APDU command to the configured services.
   * <p>
   * This method uses the {@link #dispatch(APDU,byte) dispatch(APDU,byte)}
   * method with <code>PROCESS_INPUT_DATA</code> as the input phase
   * parameter to sequence through the services registered for all three
   * phases : <code>PROCESS_INPUT_DATA</code> followed by
   * <code>PROCESS_COMMAND</code> and lastly
   * <code>PROCESS_OUTPUT_DATA</code>.
   * <p>
   * If the command processing completes normally, the output data is sent
   * using <code>APDU.sendBytes</code> and the response status is generated
   * by throwing an <code>ISOException</code> exception or by simply
   * returning (for status = 0x9000). If an exception is thrown by any Service
   * during the processing, <code>ISO7816.SW_UNKNOWN</code> response status
   * code is generated by throwing an <code>ISOException</code>. If the
   * command could not be processed <code>ISO7816.SW_INS_NOT_SUPPORTED</code>
   * response status is generated by throwing an <code>ISOException</code>.
   * <p>
   * Note:
   * <ul>
   * <li><em>If additional command processing is required following a call to
   * this method, the caller should catch and process exceptions thrown by this
   * method.</em>
   * </ul>
   *
   * @param command
   *            the APDU object containing command to be processed
   * @throws ISOException
   *             with the response bytes per ISO 7816-4
   */
  public void process(APDU command) throws ISOException {

    Exception e = dispatch(command, PROCESS_INPUT_DATA);

    if (e == null) {
      ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
    } else {
      ISOException.throwIt(ISO7816.SW_UNKNOWN);
    }
  }
}
