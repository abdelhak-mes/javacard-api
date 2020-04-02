/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.crypto;

/**
 * <code>KeyEncryption</code> interface defines the methods used to enable
 * encrypted key data access to a key implementation.
 * <p>
 *
 * @see javacard.security.KeyBuilder javacard.security.KeyBuilder
 * @see Cipher Cipher
 */
public interface KeyEncryption {

  /**
   * Sets the <code>Cipher</code> object to be used to decrypt the input key
   * data and key parameters in the set methods.
   * <p>
   * Default <code>Cipher</code> object is <code>null</code> - no
   * decryption performed.
   *
   * @param keyCipher
   *            the decryption <code>Cipher</code> object to decrypt the
   *            input key data. The <code>null</code> parameter indicates
   *            that no decryption is required.
   */
  void setKeyCipher(Cipher keyCipher);

  /**
   * Returns the <code>Cipher</code> object to be used to decrypt the input
   * key data and key parameters in the set methods.
   * <p>
   * Default is <code>null</code> - no decryption performed.
   *
   * @return <code>keyCipher</code>, the decryption <code>Cipher</code>
   *         object to decrypt the input key data. The <code>null</code>
   *         return indicates that no decryption is performed.
   */
  Cipher getKeyCipher();
}
