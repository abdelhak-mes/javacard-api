/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.io;

/**
 * A Java Card runtime environment-owned instance of <code>IOException</code>
 * is thrown to signal that an I/O exception of some sort has occurred. This
 * class is the general class of exceptions produced by failed or interrupted
 * I/O operations.
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
 * definition in the <em>Java 2 Platform Standard Edition API
 * Specification</em>. <p>
 */

public class IOException extends Exception {
  /**
   * Constructs an <code>IOException</code>.
   */
  public IOException() {}
}
