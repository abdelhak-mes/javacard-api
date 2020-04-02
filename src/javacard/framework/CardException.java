/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The <code>CardException</code> class defines a field <code>reason </code>and
 * two accessor methods <code>
 * getReason()</code> and <code>setReason()</code>.
 * The <code>reason</code> field encapsulates an exception cause identifier in
 * the Java Card platform. All Java Card platform checked Exception classes
 * should extend <code>CardException</code>. This class also provides a
 * resource-saving mechanism (<code>throwIt()</code> method) for using a Java
 * Card runtime environment-owned instance of this class.
 * <p>
 * Even if a transaction is in progress, the update of the internal
 * <code>reason</code> field shall not participate in the transaction. The
 * value of the internal <code>reason</code> field of Java Card runtime
 * environment-owned instance is reset to 0 on a tear or reset.
 */

public class CardException extends Exception {

  private short reason;

  /**
   * Construct a <code>CardException</code> instance with the specified reason.
   * To conserve on resources, use the <code>throwIt()</code> method to use the
   * Java Card runtime environment-owned instance of this class.
   *
   * @param reason
   *            the reason for the exception
   */
  public CardException(short reason) { this.reason = reason; }

  /**
   * Get reason code
   *
   * @return the reason for the exception
   */
  public short getReason() { return this.reason; }

  /**
   * Set reason code
   *
   * @param reason
   *            the reason for the exception
   */
  public void setReason(short reason) { this.reason = reason; }

  /**
   * Throw the Java Card runtime environment-owned instance of
   * <code>CardException</code> class with the specified reason.
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
   * @exception CardException
   *                always
   */
  public static void throwIt(short reason) throws CardException {
    CardException exception = new CardException(reason);
    throw exception;
  }
}
