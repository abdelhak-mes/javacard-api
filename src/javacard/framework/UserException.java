/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * <code>UserException</code> represents a User exception. This class also
 * provides a resource-saving mechanism (the <code>throwIt()</code> method)
 * for user exceptions by using a Java Card runtime environment-owned instance.
 * <p>
 * Java Card runtime environment-owned instances of exception classes are
 * temporary Java Card runtime environment Entry Point Objects and can be
 * accessed from any applet context. References to these temporary objects
 * cannot be stored in class variables or instance variables or array
 * components. See
 * <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em>, section 6.2.1 for details.
 */
public class UserException extends CardException {

  /**
   * Constructs a <code>UserException</code> with reason = 0. To conserve on
   * resources use <code>throwIt()</code> to use the Java Card runtime
   * environment-owned instance of this class.
   */
  public UserException() { this((short)0); }

  /**
   * Constructs a <code>UserException</code> with the specified reason. To
   * conserve on resources use <code>throwIt()</code> to use the Java Card
   * runtime environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public UserException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>UserException</code> with the specified reason.
   * <p>
   * Java Card runtime environment-owned instances of exception classes are
   * temporary Java Card runtime environment Entry Point Objects and can be
   * accessed from any applet context. References to these temporary objects
   * cannot be stored in class variables or instance variables or array
   * components. See
   * <em>Runtime Environment Specification, Java Card Platform, Classic
   * Edition</em>, section 6.2.1 for details.
   *
   * @param reason
   *            the reason for the exception
   * @exception UserException
   *                always
   */
  public static void throwIt(short reason) throws UserException {
    UserException ue = new UserException(reason);
    throw ue;
  }
}
