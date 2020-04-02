/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.lang;

/**
 * A Java Card runtime environment-owned instance of
 * <code>ArithmeticException</code> is thrown when an exceptional arithmetic
 * condition has occurred. For example, a "divide by zero" is an exceptional
 * arithmetic condition.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details. <p> This Java Card platform class's
 * functionality is a strict subset of the definition in the
 * <em>Java</em><sup>TM</sup><em> Platform Standard Edition (Java
 * SE</em><sup>TM</sup><em>) API Specification</em>. <p>
 */

public class ArithmeticException extends RuntimeException {
  /**
   * Constructs an <code>ArithmeticException</code>.
   */
  public ArithmeticException() {}
}
