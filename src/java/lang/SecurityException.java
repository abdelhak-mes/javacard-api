/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.lang;

/**
 * A Java Card runtime environment-owned instance of
 * <code>SecurityException</code> is thrown by the Java Card Virtual Machine
 * to indicate a security violation.
 * <p>
 * This exception is thrown when an attempt is made to illegally access an
 * object belonging to another applet. It may optionally be thrown by a Java
 * Card VM implementation to indicate fundamental language restrictions, such as
 * attempting to invoke a private method in another class.
 * <p>
 * For security reasons, the Java Card runtime environment implementation may
 * mute the card instead of throwing this exception.
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
public class SecurityException extends RuntimeException {

  /**
   * Constructs a <code>SecurityException</code>.
   */
  public SecurityException() {}
}
