package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.List;

public class ConverterInstructionEntry {
   public IInstruction insn;
   public long address;
   public List r;
   public int irAddress;
   public IInstruction ph1Insn;

   public ConverterInstructionEntry() {
   }

   public ConverterInstructionEntry(ConverterInstructionEntry var1) {
      this.insn = var1.insn;
      this.address = var1.address;
      this.r = var1.r;
      this.irAddress = var1.irAddress;
   }

   public ConverterInstructionEntry(IInstruction var1, long var2, List var4, int var5) {
      this.insn = var1;
      this.address = var2;
      this.r = var4;
      this.irAddress = var5;
   }

   public int reljmp(int var1) {
      return this.irAddress + this.r.size() + var1;
   }

   @Override
   public String toString() {
      return Strings.ff("Native@0x%X \"%s\" -> IR@0x%X", this.address, this.insn, this.irAddress);
   }
}
