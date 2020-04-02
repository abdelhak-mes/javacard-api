/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.apdu;

/**
 * The <code>ExtendedLength</code> interface serves as a tagging interface to
 * indicate that the applet supports extended length APDU. If this interface is
 * implemented by the applet instance, the applet may receive and send up to
 * 32767 bytes of APDU data.
 * <p>
 * The APDU command header in the APDU buffer will use the variable length
 * header defined in ISO7816-4 with a 3 byte Lc value when the Lc field in the
 * incoming APDU header is 3 bytes long. The incoming data in that case will
 * begin at APDU buffer offset 7.
 * <p>
 * See <em>Runtime Environment Specification, Java Card Platform, Classic
 * Edition</em> for details.
 * </p>
 *
 * @since 2.2.2
 */
public interface ExtendedLength {}
