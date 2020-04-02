/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * The <code>MultiSelectable</code> interface identifies the implementing
 * Applet subclass as being capable of concurrent selections. A multiselectable
 * applet is a subclass of <code>javacard.framework.Applet</code> which
 * directly or indirectly implements this interface. All of the applets within
 * an applet package must be multiselectable. If they are not, then none of the
 * applets can be multiselectable.
 * <p>
 * An instance of a multiselectable applet can be selected on one logical
 * channel while the same applet instance or another applet instance from within
 * the same package is active on another logical channel.
 * </p>
 * <p>
 * The methods of this interface are invoked by the Java Card runtime
 * environment only when:
 * </p>
 * <ul>
 * <li> the same applet instance is still active on another logical channel, or
 * </li>
 * <li> another applet instance from the same package is still active on another
 * logical channel.</li>
 * </ul>
 * <p>
 * See <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em> for details.
 * </p>
 */
public interface MultiSelectable {

  /**
   * Called by the Java Card runtime environment to inform that this applet
   * instance has been selected while the same applet instance or another
   * applet instance from the same package is active on another logical
   * channel.
   * <p>
   * It is called either when the MANAGE CHANNEL APDU (open) command or the
   * SELECT APDU command is received and before the applet instance is
   * selected. SELECT APDU commands use instance AID bytes for applet
   * selection. See
   * <em>Runtime Environment Specification, Java Card Platform, Classic
   * Edition</em>, section 4.5 for details. <p> A subclass of
   * <code>Applet</code> should, within this method, perform any initialization
   * that may be required to process APDU commands that may follow. This method
   * returns a boolean to indicate that it is ready to accept incoming APDU
   * commands via its <code>process()</code> method. If this method returns
   * false, it indicates to the Java Card runtime environment that this applet
   * instance declines to be selected. <p> Note: <ul> <li><em>The
   * <code>javacard.framework.Applet.select(</code>) method is not called if
   * this method is invoked.</em></li>
   * </ul>
   *
   * @param appInstAlreadyActive
   *            boolean flag is <code>true</code> when the same applet
   *            instance is already active on another logical channel and
   *            <code>false</code> otherwise
   * @return <code>true</code> if the applet instance accepts selection,
   *         <code>false</code> otherwise
   */
  public boolean select(boolean appInstAlreadyActive);

  /**
   * Called by the Java Card runtime environment to inform that this currently
   * selected applet instance is being deselected on this logical channel
   * while the same applet instance or another applet instance from the same
   * package is still active on another logical channel. After deselection,
   * this logical channel will be closed or another applet instance (or the
   * same applet instance) will be selected on this logical channel. It is
   * called when a SELECT APDU command or a MANAGE CHANNEL (close) command is
   * received by the Java Card runtime environment. This method is called
   * prior to invoking either another applet instance's or this applet
   * instance's <code>select()</code> method.
   * <p>
   * A subclass of <code>Applet</code> should, within this method, perform
   * any cleanup or bookkeeping work before another applet instance is
   * selected or the logical channel is closed.
   * <p>
   * Notes:
   * <ul>
   * <li><em>The <code>javacard.framework.Applet.deselect(</code>) method is
   * not called if this method is invoked.</em></li>
   * <li><em>Unchecked exceptions thrown by this method are caught and ignored
   * by the Java Card runtime environment but the applet instance is
   * deselected.</em></li> <li><em>The Java Card runtime environment does NOT
   * clear any transient objects of
   * </em><code>JCSystem.CLEAR_ON_DESELECT</code><em> clear event type owned
   * by this applet instance since at least one applet instance from the same
   * package is still active.</em></li>
   * <li><em>This method is NOT called on reset or power loss.</em></li>
   * </ul>
   *
   * @param appInstStillActive
   *            boolean flag is <code>true</code> when the same applet
   *            instance is still active on another logical channel and
   *            <code>false</code> otherwise
   */
  public void deselect(boolean appInstStillActive);
}
