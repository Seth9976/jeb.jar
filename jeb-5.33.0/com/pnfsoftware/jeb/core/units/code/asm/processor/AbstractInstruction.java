package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.Set;

@Ser
public abstract class AbstractInstruction implements IInstruction {
   private static final boolean BREAK_FLOW = true;
   private static final boolean ROUTINE_CALL = true;
   @SerId(1)
   private BytesBlock code;
   @SerId(2)
   private String mnemonic;
   @SerId(3)
   protected IInstructionOperand[] operands;
   @SerId(4)
   protected int processorMode;

   public AbstractInstruction(BytesBlock var1, String var2, IInstructionOperand[] var3, int var4) {
      this.code = var1;
      this.mnemonic = var2;
      this.operands = var3;
      this.processorMode = var4;
   }

   @Override
   public int getSize() {
      return this.code.getCode().length;
   }

   public BytesBlock getCodeBlock() {
      return this.code;
   }

   @Override
   public byte[] getCode() {
      return this.code.getCode();
   }

   public byte[] getCode(ByteOrder var1) {
      return this.code.getCode(var1);
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public String getMnemonic() {
      return this.mnemonic;
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return this.operands;
   }

   @Override
   public IInstructionOperand getOperand(int var1) {
      return this.operands[var1];
   }

   @Override
   public int getCountOfOperands() {
      return this.operands.length;
   }

   @Override
   public int getProcessorMode() {
      return this.processorMode;
   }

   protected CodePointer buildNextEntryPoint(long var1) {
      return new CodePointer(var1 + this.getSize(), this.processorMode);
   }

   protected boolean isBreakingFlow(IInsnEmulator var1) {
      return !this.isJump(var1) ? false : var1.getBranchType() == IInsnEmulator.BranchType.JMP;
   }

   protected boolean isRoutineCall(IInsnEmulator var1) {
      return !this.isJump(var1) ? false : var1.getBranchType() == IInsnEmulator.BranchType.CALL;
   }

   protected boolean isJump(IInsnEmulator var1) {
      return var1 == null ? false : var1.isPCUpdated();
   }

   public Set getInstructionFlags() {
      return Collections.emptySet();
   }

   @Override
   public String format(Object var1) {
      Long var2 = (Long)var1;
      long var3 = var2 == null ? 0L : var2;
      StringBuilder var5 = new StringBuilder();

      try {
         var5.append(this.getMnemonic());
         var5.append(" ");
         int var6 = 0;

         for (IInstructionOperand var10 : this.getOperands()) {
            if (var6 >= 1) {
               var5.append(", ");
            }

            var5.append(var10.format(this, var3));
            var6++;
         }

         return var5.toString();
      } catch (RuntimeException var11) {
         throw new RuntimeException(Strings.ff("Unable to format %s: %s", Formatter.byteArrayToHex(this.getCode()), var5.toString()), var11);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.code == null ? 0 : this.code.hashCode());
      return 31 * var1 + this.processorMode;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         AbstractInstruction var2 = (AbstractInstruction)var1;
         if (this.code == null) {
            if (var2.code != null) {
               return false;
            }
         } else if (!this.code.equals(var2.code)) {
            return false;
         }

         return this.processorMode == var2.processorMode;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("(%s) %s", Formatter.byteArrayToHex(this.getCode()), this.format(0L));
   }
}
