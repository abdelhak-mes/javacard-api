/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.external;

/**
 * This interface provides methods to read and write the external memory space.
 * Note that it is up to the implementation to ensure that no instance of this
 * interface can ever be created or used to access memory that is directly
 * accessed and managed by the Java Card RE for code, heap and other data
 * structures.
 * <p>
 * An instance of this interface suitable for the available external memory
 * subsystem can be obtained via the <code>Memory</code> class.
 *
 * @see javacardx.external.Memory#getMemoryAccessInstance( byte, short[], short
 *     )
 * @since 2.2.2
 */
public interface MemoryAccess {

  /**
   * This method is used to write data into non-directly addressable memory
   * after providing the correct key(password) to authenticate.
   * <p>
   * If the authentication fails or writes are disallowed at the specified
   * memory subsystem location(s), this method returns <code>false</code>.
   *
   * @param src
   *            the source data byte array
   * @param src_off
   *            the byte offset into the <code>src</code> array where data
   *            begins
   * @param src_blen
   *            the byte length of the data to be written
   * @param auth_key
   *            the byte array containing the key(password)
   * @param auth_key_off
   *            the byte offset into the <code>auth_key</code> array where
   *            the key data begins
   * @param auth_key_blen
   *            the length in bytes of the key in the <code>auth_key</code>
   *            array
   * @param other_sector
   *            the external memory subsystem sector number
   * @param other_block
   *            the external memory subsystem block number
   * @return <code>true</code> if the write was successful,
   *         <code>false</code> otherwise
   * @exception ExternalException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>ExternalException.INVALID_PARAM</code> if any
   *                of the input parameters are invalid.
   *                <li><code>ExternalException.INTERNAL_ERROR</code> if an
   *                unrecoverable external memory access error occurred.
   *                </ul>
   */
  public boolean writeData(byte[] src, short src_off, short src_blen,
                           byte[] auth_key, short auth_key_off,
                           short auth_key_blen, short other_sector,
                           short other_block) throws ExternalException;

  /**
   * This method is used to read data from non-directly addressable memory
   * after providing the correct key(password) to authenticate.
   * <p>
   * If the authentication fails or reads are disallowed at the specified
   * memory subsystem location(s), this method returns <code>0</code>.
   *
   * @param dest
   *            the destination data byte array
   * @param dest_off
   *            the byte offset into the <code>dest array</code> where data
   *            should begin
   * @param auth_key
   *            the byte array containing the key(password)
   * @param auth_key_off
   *            the byte offset into the <code>auth_key</code> array where
   *            the key data begins
   * @param auth_key_blen
   *            the length in bytes of the key in the <code>auth_key</code>
   *            array
   * @param other_sector
   *            the other memory subsystem sector number
   * @param other_block
   *            the other memory subsystem block number
   * @param other_len
   *            the number of bytes of memory to be read
   * @return the length in bytes of the data returned in the <code>dest</code>
   *         array. 0 if none.
   * @exception ExternalException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>ExternalException.INVALID_PARAM</code> if any
   *                of the input parameters are invalid.
   *                <li><code>ExternalException.INTERNAL_ERROR</code> if an
   *                unrecoverable external memory access error occurred.
   *                </ul>
   */
  public short readData(byte[] dest, short dest_off, byte[] auth_key,
                        short auth_key_off, short auth_key_blen,
                        short other_sector, short other_block, short other_len)
      throws ExternalException;
}
