/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.lang;

/**
 * <p>
 * A Java Card runtime environment-owned instance of
 * <code>ArrayIndexOutOfBoundsException</code> is thrown to indicate that an
 * array has been accessed with an illegal index. The index is either negative
 * or greater than or equal to the size of the array.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details. <p> This Java Card platform class's
 * functionality is a strict subset of the definition in the
 * <em>Java</em><sup>TM</sup> <em> Platform Standard Edition</em> (Java
 * SE<sup>TM</sup>) <em>API Specification</em>.
 * </p>
 */
public class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {
  /**
   * Constructs an <code>ArrayIndexOutOfBoundsException</code>.
   */
  public ArrayIndexOutOfBoundsException() {}
}
