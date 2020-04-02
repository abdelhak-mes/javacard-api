/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines a character string constant.
 * <p>
 * This annotation is used within a {@link StringPool} annotation to define
 * a single character string constant.
 * <p>
 * A character string constant can be defined in one of the two following ways:
 * <ul>
 * <li>With a name and a value expressed as
 * a Java string literal (as defined by the Java Language Specification).
 * <br>
 * For example: <code> @StringDef(name = "Hello", value = "Hello
 * California!")</code>
 * </li>
 * <li>With a name and a reference to another
 * string constant (typically defined in another library package) using its
 * fully-qualified name.
 * <br>
 * For example: <code>@StringDef(name = "S2", reference =
 * "com.sun.jcclassic.samples.stringlib.LibStrings.Hello")</code>
 * </li>
 * </ul>
 * This annotation is processed at compile-time and is discarded by the
 * compiler. That is, it is not retained within the compiled class file.
 *
 *
 * @see StringPool
 * @since Java Card Classic 3.0.4
 */
@Retention(RetentionPolicy.SOURCE)
public @interface StringDef {

  /**
   * The name of the defined string constant.
   *
   * @return the name of the defined string constant
   */
  String name();

  /**
   * The literal value of the defined string constant.
   * <p>
   * This attribute is exclusive of the {@link #reference()} attribute. The
   * annotation processor will fail with an error message if both the {@link
   * #value()} and {@link #reference()} attributes are defined.
   *
   * @return "" by default
   */
  String value() default "";

  /**
   * The fully-qualified name of the referenced string constant.
   * <p>
   * This attribute is exclusive of the {@link #value()} attribute. The
   * annotation processor will fail with an error message if both the {@link
   * #value()} and {@link #reference()} attributes are defined.
   *
   *  @return "" by default
   */
  String reference() default "";
}
