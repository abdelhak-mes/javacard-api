/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry;

/**
 * The <code>SharedBioTemplate</code> interface provides the means for
 * accessing unrestricted biometric functionality, e.g., the biometric matching
 * functions. A biometric manager/server can implement this interface with a
 * proxy to the public matching functions; thus giving a biometric client access
 * to matching functions but not to the enrollment functions. Without this
 * interface, the client could potentially cast a biometric reference to gain
 * access to enrollment functionality and thereby circumvent security measures.
 *
 * @since 2.2.2
 */
public interface SharedBioTemplate
    extends BioTemplate, javacard.framework.Shareable {}
