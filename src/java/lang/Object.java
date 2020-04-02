/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.lang;

/**
 * Class <code>Object</code> is the root of the Java Card platform class
 * hierarchy. Every class has <code>Object</code> as a superclass. All
 * objects, including arrays, implement the methods of this class.
 * <p>
 * This Java Card platform class's functionality is a strict subset of the
 * definition in the
 * <em>Java<sup>TM</sup> Platform Standard Edition (Java SE<sup>TM</sup>) API
 * Specification</em>. <p>
 */
public class Object {

  public Object() {}

  /**
   * Compares two Objects for equality.
   * <p>
   * The <code>equals</code> method implements an equivalence relation:
   * <ul>
   * <li>It is <i>reflexive</i>: for any reference value <code>x</code>,
   * <code>x.equals(x)</code> should return <code>true</code>.
   * <li>It is <i>symmetric</i>: for any reference values <code>x</code>
   * and <code>y</code>, <code>x.equals(y)</code> should return
   * <code>true</code> if and only if <code>y.equals(x)</code> returns
   * <code>true</code>.
   * <li>It is <i>transitive</i>: for any reference values <code>x</code>,
   * <code>y</code>, and <code>z</code>, if <code>x.equals(y)</code>
   * returns <code>true</code> and <code>y.equals(z)</code> returns
   * <code>true</code>, then <code>x.equals(z)</code> should return
   * <code>true</code>.
   * <li>It is <i>consistent</i>: for any reference values <code>x</code>
   * and <code>y</code>, multiple invocations of <code>x.equals(y)</code>
   * consistently return <code>true</code> or consistently return
   * <code>false</code>.
   * <li>For any reference value <code>x</code>,
   * <code>x.equals(null)</code> should return <code>false</code>.
   * </ul>
   * <p>
   * The <code>equals</code> method for class <code>Object</code>
   * implements the most discriminating possible equivalence relation on
   * objects; that is, for any reference values <code>x</code> and
   * <code>y</code>, this method returns <code>true</code> if and only if
   * <code>x</code> and <code>y</code> refer to the same object
   * (<code>x==y</code> has the value <code>true</code>).
   *
   * @param obj
   *            the reference object with which to compare.
   * @return <code>true</code> if this object is the same as the obj
   *         argument; <code>false</code> otherwise.
   */
  public boolean equals(Object obj) { return (this == obj); }
}
