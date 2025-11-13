package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum DalvikParserErrorType {
   UNKNOWN_ERROR("Unknown error", true),
   OUT_OF_BOUNDARIES("Out of bytecode boundaries"),
   RESERVED_OPCODE("Reserved opcode"),
   OPTIMIZED_OPCODE("Optimized opcode"),
   INVALID_OPCODE_FORMAT("Invalid opcode format"),
   INVALID_PSEUDO_INSTRUCTION_ID("Invalid pseudo instruction ID"),
   INVALID_ARGUMENT_COUNT("Invalid argument count"),
   INVALID_INDEX("Invalid pool index"),
   INVALID_EXTENDED_OPCODE("Invalid extended opcode"),
   UNIMPLEMENTED_ENCODING_FORMAT("Unimplemented encoding format"),
   PADDING_BEFORE_PSEUDO_OPCODE("Padding detected before pseudo-opcode data"),
   OVERLAPPING_INSTRUCTIONS("Overlapping instructions"),
   BAD_TRY_BLOCK_START_ADDRESS("Bad try-block start-address"),
   BAD_TRY_BLOCK_END_ADDRESS("Bad try-block end-address"),
   GAPS_ERROR("Gaps collection error (bytecode overlaps)", true),
   REGISTER_OUTSIDE_FRAME("Uses registers outside the declared method frame"),
   EMPTY_CFG("Empty CFG", true);

   private final String str;
   private final boolean hardFail;

   private DalvikParserErrorType(String var3) {
      this(var3, false);
   }

   private DalvikParserErrorType(String var3, boolean var4) {
      this.str = var3;
      this.hardFail = var4;
   }

   public boolean isHardFail() {
      return this.hardFail;
   }

   @Override
   public String toString() {
      return this.str;
   }
}
