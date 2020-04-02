/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.lang;

/**
 * A Java Card runtime environment-owned instance of
 * <code>NullPointerException</code> is thrown when an applet attempts to use
 * <code>null</code> in a case where an object is required. These include:
 * <p>
 * <ul>
 * <li> Calling the instance method of a <code>null</code> object.
 * <li> Accessing or modifying the field of a <code>null</code> object.
 * <li> Taking the length of <code>null</code> as if it were an array.
 * <li> Accessing or modifying the slots of <code>null</code> as if it were an
 * array.
 * <li> Throwing <code>null</code> as if it were a <code>Throwable</code>
 * value.
 * </ul>
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
public class NullPointerException extends RuntimeException {
  /**
   * Constructs a <code>NullPointerException</code>.
   */
  public NullPointerException() {}
}
