/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.biometry1toN;

import javacard.framework.*;

/**
 * The {@code SharedBioTemplateData} interface provides the means for accessing
 * unrestricted biometric template data. A biometric manager/server can
 * implement this interface with a proxy to the public accessor functions; thus
 * giving a biometric client read access to these public information, but not
 * write access to these information. Without this interface, the client could
 * potentially cast a biometric reference to gain access to enrollment
 * functionality and thereby circumvent security measures.
 *
 * @since 3.0.5
 */
public interface SharedBioTemplateData extends BioTemplateData, Shareable {}
