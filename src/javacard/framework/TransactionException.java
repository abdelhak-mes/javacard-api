/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * <code>TransactionException</code> represents an exception in the
 * transaction subsystem. The methods referred to in this class are in the
 * <code>JCSystem</code> class.
 * <p>
 * The <code>JCSystem</code> class and the transaction facility throw Java
 * Card runtime environment-owned instances of
 * <code>TransactionException</code>. <p> Java Card runtime environment-owned
 * instances of exception classes are temporary Java Card runtime environment
 * Entry Point Objects and can be accessed from any applet context. References
 * to these temporary objects cannot be stored in class variables or instance
 * variables or array components. See <em>Runtime Environment Specification,
 * Java Card Platform, Classic Edition</em>, section 6.2.1 for details.
 *
 * @see JCSystem JCSystem
 */
public class TransactionException extends CardRuntimeException {

  /**
   * This reason code is used by the <code>beginTransaction</code> method to
   * indicate a transaction is already in progress.
   */
  public final static short IN_PROGRESS = 1;

  /**
   * This reason code is used by the <code>abortTransaction</code> and
   * <code>commitTransaction</code> methods when a transaction is not in
   * progress.
   */
  public final static short NOT_IN_PROGRESS = 2;

  /**
   * This reason code is used during a transaction to indicate that the commit
   * buffer is full.
   */
  public final static short BUFFER_FULL = 3;

  /**
   * This reason code is used during a transaction to indicate an internal
   * Java Card runtime environment problem (fatal error).
   */
  public final static short INTERNAL_FAILURE = 4;

  /**
   * This reason code is used by the transaction methods to indicate that
   * the method is not available in the caller's environment.
   */
  public final static short ILLEGAL_USE = 5;

  /**
   * Constructs a TransactionException with the specified reason. To conserve
   * on resources use <code>throwIt()</code> to use the Java Card runtime
   * environment-owned instance of this class.
   *
   * @param reason exception reason
   */
  public TransactionException(short reason) { super(reason); }

  /**
   * Throws the Java Card runtime environment-owned instance of
   * <code>TransactionException</code> with the specified reason.
   * <p>
   * Java Card runtime environment-owned instances of exception classes are
   * temporary Java Card runtime environment Entry Point Objects and can be
   * accessed from any applet context. References to these temporary objects
   * cannot be stored in class variables or instance variables or array
   * components. See
   * <em>Runtime Environment Specification, Java Card Platform, Classic
   * Edition</em>, section 6.2.1 for details.
   *
   * @param reason throwIt reason
   *
   * @exception TransactionException
   *                always
   */
  public static void throwIt(short reason) {
    TransactionException te = new TransactionException(reason);
    throw te;
  }
}
