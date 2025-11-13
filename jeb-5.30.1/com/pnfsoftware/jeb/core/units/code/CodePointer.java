package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class CodePointer extends Pointer implements ICodePointer {
   @SerId(1)
   int processorMode;

   public CodePointer(long var1, int var3) {
      super(var1, 0, 1);
      this.processorMode = var3;
   }

   public CodePointer(long var1) {
      this(var1, 0);
   }

   public CodePointer(ICodePointer var1) {
      this(var1.getAddress(), var1.getMode());
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + this.processorMode;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         CodePointer var2 = (CodePointer)var1;
         return this.processorMode == var2.processorMode;
      }
   }

   public boolean almostEquals(Object var1) {
      return super.equals(var1);
   }

   @Override
   public int getMode() {
      return this.processorMode;
   }

   public void setMode(int var1) {
      this.processorMode = var1;
   }

   @Override
   public boolean isUnknownAddress() {
      return this.address == -1L;
   }

   @Override
   public String toString() {
      return this.processorMode == 0 ? Strings.ff("0x%X", this.address) : Strings.ff("0x%X(%d)", this.address, this.processorMode);
   }

   public static CodePointer createFrom(String var0) {
      String var1 = var0;
      if (var0 != null && var0.startsWith("0x")) {
         try {
            int var2 = var1.indexOf(40);
            if (var2 >= 0) {
               int var8 = var0.indexOf(41);
               long var4 = Long.parseLong(var1.substring(2, var2), 16);
               int var6 = Integer.parseInt(var1.substring(var8 + 1));
               return new CodePointer(var4, var6);
            } else {
               long var3 = Long.parseLong(var1.substring(2), 16);
               return new CodePointer(var3);
            }
         } catch (RuntimeException var7) {
            throw new IllegalArgumentException(Strings.f("Invalid address @%s", var0), var7);
         }
      } else {
         throw new IllegalArgumentException(Strings.f("Address @%s must start with 0x", var0));
      }
   }

   public static CodePointer createFrom(long var0, INativeCodeModel var2) {
      INativeContinuousItem var3 = var2.getItemAt(var0);
      if (!(var3 instanceof INativeInstructionItem)) {
         throw new IllegalArgumentException(Strings.f("invalid address @%xh -- no instruction here", var0));
      } else {
         return new CodePointer(var0, ((INativeInstructionItem)var3).getInstruction().getProcessorMode());
      }
   }

   public static CodePointer createFrom(ILocatedInstruction var0) {
      return new CodePointer(var0.getOffset(), var0.getProcessorMode());
   }

   public static CodePointer createFrom(INativeMethodDataItem var0) {
      CFG var1 = var0.getCFG();
      long var2 = var1.getEntryAddress();
      IInstruction var4 = var1.getInstruction(var2);
      if (var4 == null) {
         throw new RuntimeException(Strings.f("Cannot find instruction @%s", var0.getAddress()));
      } else {
         return new CodePointer(var2, var4.getProcessorMode());
      }
   }

   public static CodePointer createFrom(INativeMethodItem var0) {
      INativeMethodDataItem var1 = var0.getData();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return createFrom(var1);
      }
   }

   public static CodePointer createUnknown() {
      return createUnknown(0);
   }

   public static CodePointer createUnknown(int var0) {
      return new CodePointer(-1L, var0);
   }
}
