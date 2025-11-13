package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.util.format.Strings;

public class DexDecConversionException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;
   private IDalvikInstruction insn;
   private Integer opcode;
   private String details;

   public DexDecConversionException() {
   }

   public DexDecConversionException(int var1) {
      this.opcode = var1;
   }

   public DexDecConversionException(IDalvikInstruction var1) {
      this(var1, null);
   }

   public DexDecConversionException(IDalvikInstruction var1, String var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.insn = var1;
         this.opcode = var1.getOpcode();
         this.details = var2;
      }
   }

   public IDalvikInstruction getInstruction() {
      return this.insn;
   }

   public Integer getOpcode() {
      return this.opcode;
   }

   @Override
   public String getMessage() {
      String var1 = "Failed conversion";
      if (this.insn != null) {
         var1 = var1 + ": " + this.insn.format(null);
      } else if (this.opcode != null) {
         var1 = var1 + Strings.ff(": opcode= 0x%X", this.opcode);
      }

      if (this.details != null) {
         var1 = var1 + ": " + this.details;
      }

      return var1;
   }
}
