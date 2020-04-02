/**
 * Java Card 3.0.5 API
 * url: https://docs.oracle.com/javacard/3.0.5/index.html
 *
 * Copyright (C) 2020, Oracle and/or its affiliates. All rights reserved.
 */

package javacard.framework;

/**
 * <code>ISO7816</code> encapsulates constants related to ISO 7816-3 and ISO
 * 7816-4. <code>ISO7816</code> interface contains only static fields.
 * <p>
 * The static fields with <code>SW_</code> prefixes define constants for the
 * ISO 7816-4 defined response status word. The fields which use the
 * <code>_00</code> suffix require the low order byte to be customized
 * appropriately e.g (ISO7816.SW_CORRECT_LENGTH_00 + (0x0025 &amp; 0xFF)).
 * <p>
 * The static fields with <code>OFFSET_</code> prefixes define constants to be
 * used to index into the APDU buffer byte array to access ISO 7816-4 defined
 * header information.
 */
public interface ISO7816 {

  // Mnemonics for the SW1,SW2 error codes

  /**
   * Response status : No Error = (short)0x9000
   */
  short SW_NO_ERROR = (short)0x9000;

  /**
   * Response status : Response bytes remaining = 0x6100
   */
  short SW_BYTES_REMAINING_00 = 0x6100;

  /**
   * Response status : Wrong length = 0x6700
   */
  short SW_WRONG_LENGTH = 0x6700;

  /**
   * Response status : Security condition not satisfied = 0x6982
   */
  short SW_SECURITY_STATUS_NOT_SATISFIED = 0x6982;

  /**
   * Response status : File invalid = 0x6983
   */
  short SW_FILE_INVALID = 0x6983;

  /**
   * Response status : Data invalid = 0x6984
   */
  short SW_DATA_INVALID = 0x6984;

  /**
   * Response status : Conditions of use not satisfied = 0x6985
   */
  short SW_CONDITIONS_NOT_SATISFIED = 0x6985;

  /**
   * Response status : Command not allowed (no current EF) = 0x6986
   */
  short SW_COMMAND_NOT_ALLOWED = 0x6986;

  /**
   * Response status : Applet selection failed = 0x6999;
   */
  short SW_APPLET_SELECT_FAILED = 0x6999;

  /**
   * Response status : Wrong data = 0x6A80
   */
  short SW_WRONG_DATA = 0x6A80;

  /**
   * Response status : Function not supported = 0x6A81
   */
  short SW_FUNC_NOT_SUPPORTED = 0x6A81;

  /**
   * Response status : File not found = 0x6A82
   */
  short SW_FILE_NOT_FOUND = 0x6A82;

  /**
   * Response status : Record not found = 0x6A83
   */
  short SW_RECORD_NOT_FOUND = 0x6A83;

  /**
   * Response status : Incorrect parameters (P1,P2) = 0x6A86
   */
  short SW_INCORRECT_P1P2 = 0x6A86;

  /**
   * Response status : Incorrect parameters (P1,P2) = 0x6B00
   */
  short SW_WRONG_P1P2 = 0x6B00;

  /**
   * Response status : Correct Expected Length (Le) = 0x6C00
   */
  short SW_CORRECT_LENGTH_00 = 0x6C00;

  /**
   * Response status : INS value not supported = 0x6D00
   */
  short SW_INS_NOT_SUPPORTED = 0x6D00;

  /**
   * Response status : CLA value not supported = 0x6E00
   */
  short SW_CLA_NOT_SUPPORTED = 0x6E00;

  /**
   * Response status : No precise diagnosis = 0x6F00
   */
  short SW_UNKNOWN = 0x6F00;

  /**
   * Response status : Not enough memory space in the file = 0x6A84
   */
  short SW_FILE_FULL = 0x6A84;

  // Logical channel errors

  /**
   * Response status : Card does not support the operation on the specified
   * logical channel = 0x6881
   */
  short SW_LOGICAL_CHANNEL_NOT_SUPPORTED = 0x6881;

  /**
   * Response status : Card does not support secure messaging = 0x6882
   */
  short SW_SECURE_MESSAGING_NOT_SUPPORTED = 0x6882;

  /**
   * Response status : Warning, card state unchanged = 0x6200
   */
  short SW_WARNING_STATE_UNCHANGED = 0x6200;

  /**
   * Response status : Last command in chain expected = 0x6883
   */
  short SW_LAST_COMMAND_EXPECTED = 0x6883;

  /**
   * Response status : Command chaining not supported = 0x6884
   */
  short SW_COMMAND_CHAINING_NOT_SUPPORTED = 0x6884;

  // Offsets into APDU header information

  /**
   * APDU header offset : CLA = 0
   */
  byte OFFSET_CLA = 0;

  /**
   * APDU header offset : INS = 1
   */
  byte OFFSET_INS = 1;

  /**
   * APDU header offset : P1 = 2
   */
  byte OFFSET_P1 = 2;

  /**
   * APDU header offset : P2 = 3
   */
  byte OFFSET_P2 = 3;

  /**
   * APDU header offset : LC = 4
   */
  byte OFFSET_LC = 4;

  /**
   * APDU command data offset : CDATA = 5
   */
  byte OFFSET_CDATA = 5;

  /**
   * APDU command data offset with extended length input data : EXT_CDATA = 7
   */
  byte OFFSET_EXT_CDATA = 7;
  // commands

  /**
   * APDU command CLA : ISO 7816 = 0x00
   */
  byte CLA_ISO7816 = (byte)0x00;

  /**
   * APDU command INS : SELECT = 0xA4
   */
  byte INS_SELECT = (byte)0xA4;

  /**
   * APDU command INS : EXTERNAL AUTHENTICATE = 0x82
   */
  byte INS_EXTERNAL_AUTHENTICATE = (byte)0x82;

  /**
   * STATUS WORD EXTRACTED FROM THE ISO7816-4 STANDARD. IN ADDITION TO THE
   * ALREADY PRESENT STATUS WORDS.
   */
  short SW_WARNING_MEMORY_UNCHANGED_NO_INFORMATION_GIVEN =
      0x6200; // No information given
  short SW_WARNING_MEMORY_UNCHANGED_PART_OF_RETURNED_DATA_MAY_BE_CORRUPTED =
      0x6281; // Part of returned data may be corrupted
  short
      SW_WARNING_MEMORY_UNCHANGED_END_OF_FILE_OR_RECORD_REACHED_BEFORE_READING_NE_BYTES_OR_UNSUCCESSFUL_SEARCH =
          0x6282; // End of file or record reached before reading Ne bytes or
                  // unsuccessful search.
  short SW_WARNING_MEMORY_UNCHANGED_SELECTED_FILE_DEACTIVATED =
      0x6283; // Selected file deactivated
  short
      SW_WARNING_MEMORY_UNCHANGED_FILE_OR_DATA_CONTROL_INFORMATION_NOT_FORMATTED =
          0x6284; // File or data control information not formatted according
                  // to 7.4
  short SW_WARNING_MEMORY_UNCHANGED_SELECTED_FILE_IN_TERMINATION_STATE =
      0x6285; // Selected file in termination state
  short
      SW_WARNING_MEMORY_UNCHANGED_NO_INPUT_DATA_AVAILABLE_FROM_A_SENSOR_ON_THE_CARD =
          0x6286; // No input data available from a sensor on the card
  short
      SW_WARNING_MEMORY_UNCHANGED_AT_LEAST_ONE_OF_THE_REFERENCED_RECORDS_IS_DEACTIVATED =
          0x6287; // At least one of the referenced records is deactivated
  short SW_WARNING_MEMORY_CHANGED_NO_INFORMATION_GIVEN =
      0x6300; // No information given
  short SW_WARNING_MEMORY_CHANGED_UNSUCCESSFUL_COMPARISON =
      0x6340; // Unsuccessful comparison (exact meaning depends on the command)
  short SW_WARNING_MEMORY_CHANGED_FILE_FILLED_UP_BY_THE_LAST_WRITE =
      0x6381; // File filled up by the last write
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_0 =
      0x63C0; // Counter from 0 to 15 encoded by '0' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_1 =
      0x63C1; // Counter from 0 to 15 encoded by '1' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_2 =
      0x63C2; // Counter from 0 to 15 encoded by '2' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_3 =
      0x63C3; // Counter from 0 to 15 encoded by '3' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_4 =
      0x63C4; // Counter from 0 to 15 encoded by '4' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_5 =
      0x63C5; // Counter from 0 to 15 encoded by '5' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_6 =
      0x63C6; // Counter from 0 to 15 encoded by '6' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_7 =
      0x63C7; // Counter from 0 to 15 encoded by '7' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_8 =
      0x63C8; // Counter from 0 to 15 encoded by '8' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_9 =
      0x63C9; // Counter from 0 to 15 encoded by '9' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_A =
      0x63CA; // Counter from 0 to 15 encoded by 'A' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_B =
      0x63CB; // Counter from 0 to 15 encoded by 'B' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_C =
      0x63CC; // Counter from 0 to 15 encoded by 'C' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_D =
      0x63CD; // Counter from 0 to 15 encoded by 'D' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_E =
      0x63CE; // Counter from 0 to 15 encoded by 'E' (exact meaning depends on
              // the command)
  short SW_WARNING_MEMORY_CHANGED_COUNTER_FROM_0_TO_15_ENcodeD_BY_F =
      0x63CF; // Counter from 0 to 15 encoded by 'F' (exact meaning depends on
              // the command)
  short SW_ERROR_MEMORY_UNCHANGED_NO_INFORMATION_GIVEN =
      0x6400; // No information given
  short SW_ERROR_MEMORY_UNCHANGED_IMMEDIATE_RESPONSE_REQUIRED_BY_THE_CARD =
      0x6401; // Immediate response required by the card
  short SW_ERROR_MEMORY_UNCHANGED_LOGICAL_CHANNEL_SHARED_ACCESS_DENIED =
      0x6481; // Logical channel shared access denied
  short SW_ERROR_MEMORY_UNCHANGED_LOGICAL_CHANNEL_OPENING_DENIED =
      0x6482; // Logical channel opening denied
  short SW_ERROR_MEMORY_CHANGED_NO_INFORMATION_GIVEN =
      0x6500;                                            // No information given
  short SW_ERROR_MEMORY_CHANGED_MEMORY_FAILURE = 0x6581; // Memory failure
  short SW_ERROR_SECURITY_RELATED_NO_INFORMATION_GIVEN_OTHER_VALUES_ARE_RFU =
      0x6600; // No information given other values are RFU
  short SW_ERROR_WRONG_LENGTH_NO_INFORMATION_GIVEN =
      0x6700; // No information given
  short
      SW_ERROR_WRONG_LENGTH_COMMAND_APDU_FORMAT_NOT_COMPLIANT_WITH_THIS_STANDARD =
          0x6701; // Command APDU format not compliant with this standard
                  // (see 5.1)
  short SW_ERROR_WRONG_LENGTH_THE_VALUE_OF_LC_IS_NOT_THE_ONE_EXPECTED =
      0x6702; // The value of Lc is not the one expected.
  short SW_ERROR_FUNCTION_CLA_NO_INFORMATION_GIVEN =
      0x6800; // No information given
  short SW_ERROR_FUNCTION_CLA_LOGICAL_CHANNEL_NOT_SUPPORTED =
      0x6881; // Logical channel not supported
  short SW_ERROR_FUNCTION_CLA_SECURE_MESSAGING_NOT_SUPPORTED =
      0x6882; // Secure messaging not supported
  short SW_ERROR_FUNCTION_CLA_LAST_COMMAND_OF_THE_CHAIN_EXPECTED =
      0x6883; // Last command of the chain expected
  short SW_ERROR_FUNCTION_CLA_COMMAND_CHAINING_NOT_SUPPORTED =
      0x6884; // Command chaining not supported
  short SW_ERROR_COMMAND_NOT_ALLOWED_NO_INFORMATION_GIVEN =
      0x6900; // No information given
  short SW_ERROR_COMMAND_NOT_ALLOWED_COMMAND_INCOMPATIBLE_WITH_FILE_STRUCTURE =
      0x6981; // Command incompatible with file structure
  short SW_ERROR_COMMAND_NOT_ALLOWED_SECURITY_STATUS_NOT_SATISFIED =
      0x6982; // Security status not satisfied
  short SW_ERROR_COMMAND_NOT_ALLOWED_AUTHENTICATION_METHOD_BLOCKED =
      0x6983; // Authentication method blocked
  short SW_ERROR_COMMAND_NOT_ALLOWED_REFERENCE_DATA_NOT_USABLE =
      0x6984; // Reference data not usable
  short SW_ERROR_COMMAND_NOT_ALLOWED_CONDITIONS_OF_USE_NOT_SATISFIED =
      0x6985; // Conditions of use not satisfied
  short SW_ERROR_COMMAND_NOT_ALLOWED_COMMAND_NOT_ALLOWED =
      0x6986; // Command not allowed (no current EF)
  short SW_ERROR_COMMAND_NOT_ALLOWED_EXPECTED_SECURE_MESSAGING_DOS_MISSING =
      0x6987; // Expected secure messaging DOs missing
  short SW_ERROR_COMMAND_NOT_ALLOWED_INCORRECT_SECURE_MESSAGING_DOS =
      0x6988; // Incorrect secure messaging DOs
  short SW_ERROR_WRONG_PARAMETERS_NO_INFORMATION_GIVEN =
      0x6A00; // No information given
  short
      SW_ERROR_WRONG_PARAMETERS_INCORRECT_PARAMETERS_IN_THE_COMMAND_DATA_FIELD =
          0x6A80; // Incorrect parameters in the command data field
  short SW_ERROR_WRONG_PARAMETERS_FUNCTION_NOT_SUPPORTED =
      0x6A81; // Function not supported
  short SW_ERROR_WRONG_PARAMETERS_FILE_OR_APPLICATION_NOT_FOUND =
      0x6A82; // File or application not found
  short SW_ERROR_WRONG_PARAMETERS_RECORD_NOT_FOUND = 0x6A83; // Record not found
  short SW_ERROR_WRONG_PARAMETERS_NOT_ENOUGH_MEMORY_SPACE_IN_THE_FILE =
      0x6A84; // Not enough memory space in the file
  short SW_ERROR_WRONG_PARAMETERS_NC_INCONSISTENT_WITH_TLV_STRUCTURE =
      0x6A85; // Nc inconsistent with TLV structure
  short SW_ERROR_WRONG_PARAMETERS_INCORRECT_PARAMETERS_P1_P2 =
      0x6A86; // Incorrect parameters P1-P2
  short SW_ERROR_WRONG_PARAMETERS_NC_INCONSISTENT_WITH_PARAMETERS_P1_P2 =
      0x6A87; // Nc inconsistent with parameters P1-P2
  short SW_ERROR_WRONG_PARAMETERS_REFERENCED_DATA_OR_REFERENCE_DATA_NOT_FOUND =
      0x6A88; // Referenced data or reference data not found (exact meaning
              // depending on the command)
  short SW_ERROR_WRONG_PARAMETERS_FILE_ALREADY_EXISTS =
      0x6A89; // File already exists
  short SW_ERROR_WRONG_PARAMETERS_DF_NAME_ALREADY_EXISTS =
      0x6A8A; // DF name already exists
}
