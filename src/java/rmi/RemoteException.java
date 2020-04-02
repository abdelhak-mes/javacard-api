/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.rmi;

import java.io.IOException;

/**
 * A Java Card runtime environment-owned instance of
 * <code>RemoteException</code> is thrown to indicate that a
 * communication-related exception has occurred during the execution of a remote
 * method call. Each method of a remote interface, an interface that extends
 * <code>java.rmi.Remote</code>, must list <code>RemoteException</code> or
 * a superclass in its <code>throws</code> clause.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See <em>Runtime Environment
 * Specification, Java Card Platform, Classic Edition</em>,
 * section 6.2.1 for details.
 * <p>
 * This Java Card platform class's functionality is a strict subset of the
 * definition in the
 * <em>Java<sup>TM</sup> Platform Standard Edition (Java SE<sup>TM</sup>)
 * API Specification</em>.
 * <p>
 */
public class RemoteException extends IOException {

  /**
   * Constructs a <code>RemoteException</code>.
   */
  public RemoteException() {}
}
