package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum ReferenceType {
   UNKNOWN("unk"),
   GEN_CODE("code"),
   BRANCH("br"),
   COND_BRANCH("cbr"),
   DYNAMIC_BRANCH("dynbr"),
   ROUTINE_CALL("call"),
   GEN_DATA("data"),
   PTR_DATA("ptr"),
   READ_DATA("rdata"),
   WRITE_DATA("wdata");

   private final String strCode;

   private ReferenceType(String var3) {
      this.strCode = var3;
   }

   public String getStrCode() {
      return this.strCode;
   }

   public boolean isCode() {
      return this == GEN_CODE || this == BRANCH || this == COND_BRANCH || this == DYNAMIC_BRANCH || this == ROUTINE_CALL;
   }

   public boolean isData() {
      return this == GEN_DATA || this == READ_DATA || this == WRITE_DATA || this == PTR_DATA;
   }
}
