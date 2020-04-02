/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework.service;

import java.rmi.Remote;
import javacard.framework.SystemException;

/**
 * A convenient base class for remote objects for the Java Card platform. An
 * instance of a subclass of this <code>CardRemoteObject</code> class will be
 * exported automatically upon construction.
 *
 * @version 1.0
 *
 */
public class CardRemoteObject extends Object implements java.rmi.Remote {

  /**
   * Creates a new <code>CardRemoteObject</code> and automatically exports
   * it. When exported, the object is enabled for remote access from outside
   * the card until unexported. Only when the object is enabled for remote
   * access can it be returned as the initial reference during selection or
   * returned by a remote method. In addition, remote methods can be invoked
   * only on objects enabled for remote access.
   */
  public CardRemoteObject() { export(this); }

  /**
   * Exports the specified remote object. The object is now enabled for remote
   * access from outside the card until unexported. In order to remotely
   * access the remote object from the terminal client, it must either be set
   * as the initial reference or be returned by a remote method.
   *
   * @param obj
   *            the remotely accessible object
   * @throws SecurityException
   *             if the specified obj parameter is not owned by the caller
   *             context
   * @throws SystemException
   *             with the following reason codes:
   *             <ul>
   *             <li><code>SystemException.NO_RESOURCE</code> if too many
   *             exported remote objects. All implementations must support a
   *             minimum of 16 exported remote objects.
   *             </ul>
   */
  public static void export(Remote obj) throws SecurityException {
    // TODO: not yet implemented !!!
  }

  /**
   * Unexports the specified remote object. After applying this method, the
   * object cannot be remotely accessed from outside the card until it is
   * exported again.
   * <p>
   * Note:
   * <ul>
   * <li><em>If this method is called during the session in which the specified
   * remote object parameter is the initial reference object or has been
   * returned by a remote method,
   * the specified remote object will continue to be remotely accessible until
   * the end of the associated selection session(s).</em>
   * </ul>
   *
   * @param obj
   *            the remotely accessible object
   * @throws SecurityException
   *             if the specified obj parameter is not owned by the caller
   *             context
   */
  public static void unexport(Remote obj) throws SecurityException {
    // TODO: not yet implemented !!!
  }
}
