/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The <code>AppletEvent</code> interface provides a callback interface for
 * the Java Card runtime environment to inform the applet about life cycle
 * events. An applet instance - subclass of <code>Applet</code> - should
 * implement this interface if it needs to be informed about supported life
 * cycle events.
 * <p>
 * See <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em> for details.
 */
public interface AppletEvent {

  /**
   * Called by the Java Card runtime environment to inform this applet
   * instance that the Applet Deletion Manager has been requested to delete
   * it. This method is invoked by the Applet Deletion Manager before any
   * dependency checks are performed. The Applet Deletion Manager will perform
   * dependency checks upon return from this method. If the dependency check
   * rules disallow it, the applet instance will not be deleted.
   * <p>
   * See <em>Runtime Environment Specification, Java Card Platform, Classic
   * Edition</em>, section 11.3.4 for details. <p> This method executes in the
   * context of the applet instance and as the currently selected applet. This
   * method should make changes to state in a consistent manner using the
   * transaction API to ensure atomicity and proper behavior in the event of a
   * tear or reset. <p> A subclass of <code>Applet</code> should, within this
   * method, perform any cleanup required for deletion such as release
   * resources, backup data, or notify other dependent applets. <p> Note: <ul>
   * <li><em>Exceptions thrown by this method are caught by the Java Card
   * runtime environment and ignored.</em> <li><em> The Java Card runtime
   * environment will not rollback state automatically if applet deletion
   * fails.</em>
   * <li><em>This method may be called by the Java Card runtime environment
   * multiple times, once for each attempt to delete this applet instance.</em>
   * </ul>
   */
  public void uninstall();
}
