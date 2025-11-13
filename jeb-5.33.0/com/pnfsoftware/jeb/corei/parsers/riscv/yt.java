package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ACS;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.List;

@Ser
public class yt extends AbstractInstruction {
   @SerTransient
   qt.Av pC;

   @SerCustomInitPostGraph
   private void pC() {
      this.pC = (qt.Av)qt.pC.get(super.getMnemonic());
   }

   public yt(BytesBlock var1, qt.Av var2, RC[] var3, int var4) {
      super(var1, var2.pC, var3, var4);
      this.pC = var2;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if ((this.pC.A & 16777216) != 0) {
         FlowInformation var3 = new FlowInformation();
         String var4 = this.pC.pC;
         switch (var4) {
            case "beq":
            case "bne":
            case "blt":
            case "bge":
            case "bltu":
            case "bgeu":
               long var9 = ((RC[])this.getOperands())[2].getOperandValue(var1);
               var3.addTarget(new CodePointer(var1 + this.getSize()));
               var3.addTarget(new CodePointer(var9));
               break;
            case "beqz":
            case "bnez":
               long var8 = ((RC[])this.getOperands())[1].getOperandValue(var1);
               var3.addTarget(new CodePointer(var1 + this.getSize()));
               var3.addTarget(new CodePointer(var8));
               break;
            case "j":
               long var6 = ((RC[])this.getOperands())[0].getOperandValue(var1);
               var3.addTarget(new CodePointer(var6));
            case "jr":
         }

         return var3;
      } else {
         return FlowInformation.NONE;
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      if ((this.pC.A & 33554432) != 0) {
         FlowInformation var3 = new FlowInformation();
         String var4 = this.pC.pC;
         switch (var4) {
            case "jal":
               long var6 = ((RC[])this.getOperands())[1].getOperandValue(var1);
               var3.addTarget(new CodePointer(var6));
            case "jalr":
            default:
               return var3;
         }
      } else {
         return FlowInformation.NONE;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
   }

   @Override
   public boolean canThrow() {
      return true;
   }

   @Override
   public ACS getACS() {
      return this.pC.UT;
   }
}
