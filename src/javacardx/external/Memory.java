/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacardx.external;

/**
 * This class provides access to memory subsystems that are not directly
 * addressable, typically that of other contactless state machine handlers such
 * as Mifare<sup>TM</sup>. This class could also be used to access specialized
 * memory spaces such as that of a mass storage device.
 *
 * @since 2.2.2
 */
public final class Memory {

  /**
   * MIFARE<sup>TM</sup> memory type constant. When a
   * <code>MemoryAccess</code> instance of this type is requested, the
   * <code>memorySize</code> and <code>memorySizeOffset</code> parameters
   * are ignored.
   * <p>
   * To use the <code>MemoryAccess</code> instance the following parameters
   * are applicable :
   * <ul>
   * <li><em>auth_key is an 8 byte password, other_len &le; 16</em>
   * <li><em>other_sector = 0, 0 &le; other_block &le; 63 for MIFARE 1K chip and
   * 0 &le; other_block &le; 255 for MIFARE 4K chip</em>
   * <li><em>security related errors return 0 on readData</em>
   * <li><em>security related errors return false on writeData</em>
   * </ul>
   * <p>
   * Note:
   * <ul>
   * <li><em>The actual external memory device servicing this memory type
   * maybe a MIFARE 1K chip or a MIFARE 4K chip.</em>
   * </ul>
   */
  public static final byte MEMORY_TYPE_MIFARE = (byte)1;

  /**
   * Extended Memory Store type constant. When a <code>MemoryAccess</code>
   * instance of this type is requested, the <code>memorySize</code>
   * parameter contains the 32 bit number representing the size in bytes of
   * the memory access required and must be a positive number less than or
   * equal to <code>2,147,483,647 (2^31 - 1)</code>.
   * <p>
   * To use the <code>MemoryAccess</code> instance the following parameters
   * are applicable.
   * <ul>
   * <li><em>auth_key parameter is not required; it is ignored</em>
   * <li><em>other_len &le; 32767</em>
   * <li><em>(other_sector, other_block) concatenated is a 32 bit address</em>
   * </ul>
   * <p>
   * Note.
   * <ul>
   * <li><em> To ensure optimal performance on all mass storage memory types
   * when accessing different areas of memory, use monotonically increasing
   * addresses.</em>
   * <li><em>Each time the </em><code>getMemoryAccessInstance</code><em> method
   * is called with this memory type parameter, a new memory access object to
   * access a distinct memory chunk is returned. A previously obtained memory
   * access object cannot be used to access the memory chunk obtained via this
   * new memory access object. The new memory access object cannot be used to
   * access the memory chuck accessible via any previously allocated memory
   * access object.</em>
   * </ul>
   */
  public static final byte MEMORY_TYPE_EXTENDED_STORE = (byte)2;

  /**
   * Creates a <code>MemoryAccess</code> object instance for the selected
   * memory subsystem.
   *
   * @param memoryType
   *            the desired external memory subsystem. Valid codes listed in
   *            <code>MEMORY_TYPE_*</code> constants above, for example
   *            {@link #MEMORY_TYPE_MIFARE MEMORY_TYPE_MIFARE}.
   * @param memorySize
   *            the array containing the desired size in bytes, if applicable,
   *            in the external memory subsystem. Check the descriptions of
   *            the MEMORY_TYPE_* constants above for more details. The 32 bit
   *            number representing the memory size in bytes is formed by
   *            concatenating the two short values at offset
   *            <code>memorySizeOffset</code> (most significant 16 bits) and
   *            <code>memorySizeOffset+1</code> (least significant 16 bits)
   *            in this array
   * @param memorySizeOffset
   *            the offset within the <code>memorySize</code> array where
   *            the 32 bit memory size number in bytes is specified
   * @return the <code>MemoryAccess</code> object instance of the requested
   *         memory subsystem
   * @exception ExternalException
   *                with the following reason codes:
   *                <ul>
   *                <li><code>ExternalException.NO_SUCH_SUBSYSTEM</code> if
   *                the requested memory subsystem is not available.
   *                <li><code>ExternalException.INVALID_PARAM</code> if the
   *                <code>memorySize</code> parameter is invalid.
   *                </ul>
   */
  public static final MemoryAccess getMemoryAccessInstance(
      byte memoryType, short[] memorySize, short memorySizeOffset)
      throws ExternalException {
    return null;
  }
}
