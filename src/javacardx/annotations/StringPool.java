/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a pool of character string constants.
 * <p>
 * Within a defined pool each string constant is defined using the
 * {@link StringDef} annotation.
 * <p>
 * This annotation is processed at compile-time and is then discarded by the
 * compiler. The annotation processor that handles this annotation generates the
 * source for a class that defines a field of type <code>byte[]</code> for each
 * of the string constants. The generated class is named as per the given
 * {@link #name() name} attribute.
 * <p>
 * If the {@link #export() export} attribute is set to <code>true</code> then
 * the string constant definitions are typically intended to be part of a
 * library. The generated class is a <code>public</code> class and for each
 * string constant definition a field with the following characteristics is
 * generated: <ul> <li>the field is an instance field</li> <li>the access
 * modifier is <code>public</code></li> <li>the type is <code>byte[]</code></li>
 * <li>the name is set to that of the string constant definition's {@link
 * StringDef#name() name} attribute</li> <li>the field is initialized with the
 * UTF-8 encoded byte sequence of the string constant definition's {@link
 * StringDef#value() value} attribute or the value of the string constant
 * definition's field referenced by the {@link StringDef#reference() reference}
 * attribute</li>
 * </ul>
 * The generated class has a <code>public</code> default constructor.
 * <br>
 * If string constants are defined by referencing other string constants
 * (typically string constants defined in one or more other libraries), the
 * default constructor of the generated class instantiates all the string pool
 * classes from these libraries defining the string constants that are
 * referenced. <p> If the {@link #export() export} attribute is set to
 * <code>false</code> then the string constant definitions are typically
 * intended to be used within an applet's package. The generated class is a
 * <code>package-private</code> class and for each string constant definition a
 * field with the following characteristics is generated: <ul> <li>the field is
 * a class (<code>static</code>) field</li> <li>the access modifier is
 * <code>package-private</code></li> <li>the type is <code>byte[]</code></li>
 * <li>the name is set to that of the string constant definition's {@link
 * StringDef#name() name} attribute</li> <li>the field is initialized with the
 * UTF-8 encoded byte sequence of the string constant definition's {@link
 * StringDef#value() value} attribute or the value of the string constant
 * definition's field referenced by the {@link StringDef#reference() reference}
 * attribute</li>
 * </ul>
 * If one of the string constants is defined by referencing another string
 * constant (typically a string constant defined in a library), the generated
 * class includes a <code>package-private</code> <code>static</code> method
 * <code>importLibConstants()</code> with the <code>void</code> return type that
 * must be invoked prior to accessing any string constant field defined by
 * reference. This method instantiates all the string pool classes defining the
 * string constants that are referenced. <p> Defining string constants that
 * reference other string constants from libaries is a convenience mechanism
 * that allows for an applet developer to use the same coding pattern regardless
 * of whether a string constant is defined in the applet's package or in a
 * library. In both cases, the applet developer can use static field access. If
 * footprint is an issue, an applet developer should directly instantiate string
 * constant pool classes from libraries.
 * </p>
 * The following example illustrates how to use the annotation to declare string
 * constants local to the declaring application or imported from a library:
 * <pre>
 *      package com.sun.jcclassic.samples.stringapp;
 *
 *       import javacardx.annotations.*;
 *
 *        <i>{@code @}StringPool(value = &#123;
 *               {@code @}StringDef(name = "S1", value = "Hello World!"),
 *               {@code @}StringDef(name = "S2", reference =
 * "com.sun.jcclassic.samples.stringlib.LibStrings.Hello")
 *            &#125;,
 *           name = "AppStrings")</i>
 *        public class StringHandlingApp extends Applet &#123;
 *          protected StringHandlingApp() &#123;
 *              <i>AppStrings.importLibConstants()</i>; // initializes lib
 * constants byte[] x = <i>AppStrings.S1</i>; byte[] y = <i>AppStrings.S2</i>;
 *              short l = (short) <i>AppStrings.S1.length</i>;
 *              ...
 *          &#125;
 *          ...
 *        &#125;
 * </pre>
 *
 * For information on how to invoke a string annotation processor, see
 * <a href="{@docRoot}/StringProc.html">Oracle's string annotation
 * processor</a>.
 *
 * @see StringDef
 * @since Java Card Classic 3.0.4
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface StringPool {

  /**
   * The individual string constant definitions.
   *
   * @return individual string constant definitions
   */
  StringDef[] value();

  /**
   * Whether the string constant definitions are to be exported, such as for a
   * library.
   *
   * @return false by default
   */
  boolean export() default false;

  /**
   * The name of the generated class. If this attribute is set to the empty
   * string value (the default), the annotation processor generates a class
   * named by appending the string <code>"$$Strings"</code> to the fully
   * qualified name of the annotated class or interface.
   *
   * @return "" by default
   */
  String name() default "";
}
