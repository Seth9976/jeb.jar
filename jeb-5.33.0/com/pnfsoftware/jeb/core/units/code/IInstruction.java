package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.exceptions.NotImplementedException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ACS;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IInstruction {
   int getProcessorMode();

   int getSize();

   byte[] getCode();

   String getPrefix();

   String getMnemonic();

   IInstructionOperand[] getOperands();

   default IInstructionOperand getOperand(int var1) {
      return this.getOperands()[var1];
   }

   default int getCountOfOperands() {
      return this.getOperands().length;
   }

   IFlowInformation getBreakingFlow(long var1);

   IFlowInformation getRoutineCall(long var1);

   IFlowInformation collectIndirectCallReferences(long var1);

   default long getPrimaryBranchAddress(long var1) {
      ICodePointer var3 = null;
      IFlowInformation var4 = this.getBreakingFlow(var1);
      if (var4.isBroken()) {
         if (var4.getTargets().size() == 1) {
            var3 = (ICodePointer)var4.getTargets().get(0);
         } else if (var4.getTargets().size() >= 2) {
            var3 = (ICodePointer)var4.getTargets().get(1);
         }
      } else {
         var4 = this.getRoutineCall(var1);
         if (var4.isBroken() && var4.getTargets().size() >= 1) {
            var3 = (ICodePointer)var4.getTargets().get(0);
         }
      }

      return var3 == null ? -1L : var3.getAddress();
   }

   void getDefUse(List var1, List var2, Object var3);

   default void getDefUse(List var1, List var2) {
      this.getDefUse(var1, var2, null);
   }

   default DefUseInfo getDefUseInfo(long var1, int var3) throws NotImplementedException {
      throw new NotImplementedException();
   }

   Collection getInstructionFlags();

   boolean canThrow();

   String format(Object var1);

   default ACS getACS() {
      return null;
   }
}
