/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package java.rmi;

/**
 * The Remote interface serves to identify interfaces whose methods may be
 * invoked from a CAD client application. An object that is a remote object must
 * directly or indirectly implement this interface. Only those methods specified
 * in a "remote interface", an interface that extends
 * <code>java.rmi.Remote</code> are available remotely.
 *
 * Implementation classes can implement any number of remote interfaces and can
 * extend other remote implementation classes. RMI for the Java Card platform
 * provides a convenience class called
 * <code>javacard.framework.service.CardRemoteObject</code> that remote object
 * implementations can extend which facilitates remote object creation. For
 * complete details on RMI for the Java Card platform, see the <I>Runtime
 * Environment Specification, Java Card Platform, Classic Edition</I> and the
 * <code>javacard.framework.service</code> API package.
 */
public interface Remote {}
