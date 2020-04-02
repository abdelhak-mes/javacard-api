/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The Shareable interface serves to identify all shared objects. Any object
 * that needs to be shared through the applet firewall must directly or
 * indirectly implement this interface. Only those methods specified in a
 * shareable interface are available through the firewall.
 *
 * Implementation classes can implement any number of shareable interfaces and
 * can extend other shareable implementation classes.
 */

public interface Shareable {}
