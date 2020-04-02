/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.lang;

/**
 * <code>RuntimeException</code> is the superclass of those exceptions that
 * can be thrown during the normal operation of the Java Card Virtual Machine.
 * <p>
 * A method is not required to declare in its throws clause any subclasses of
 * <code>RuntimeException</code> that might be thrown during the execution of
 * the method but not caught.
 * <p>
 * This Java Card platform class's functionality is a strict subset of the
 * definition in the
 * <em>Java<sup>TM</sup> Platform Standard Edition (Java SE<sup>TM</sup>) API
 * Specification</em>. <p>
 */
public class RuntimeException extends Exception {

  /**
   * Constructs a <code>RuntimeException</code> instance.
   */
  public RuntimeException() {}
}
