/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.lang;

/**
 * A Java Card runtime environment-owned instance of
 * <code>ArrayStoreException</code> is thrown to indicate that an attempt has
 * been made to store the wrong type of object into an array of objects. For
 * example, the following code generates an <code>ArrayStoreException</code>:
 * <p>
 *
 * <pre>
 * Object x[] = new AID[3];
 * x[0] = new OwnerPIN((byte) 3, (byte) 8);
 * </pre>
 *
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details. <p> This Java Card platform class's
 * functionality is a strict subset of the definition in the
 * <em>Java<sup>TM</sup> Platform Standard Edition (Java SE<sup>TM</sup>) API
 * Specification</em>. <p>
 */

public class ArrayStoreException extends RuntimeException {
  /**
   * Constructs an <code>ArrayStoreException</code>.
   */
  public ArrayStoreException() {}
}
