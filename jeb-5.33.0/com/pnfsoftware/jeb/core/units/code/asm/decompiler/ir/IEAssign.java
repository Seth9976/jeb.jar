package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IEAssign extends IEStatement {
   IEGeneric getDstOperand();

   IEGeneric getSrcOperand();

   IEGeneric getLeftOperand();

   IEGeneric getRightOperand();

   boolean isBranching();

   boolean isBreakingFlow();

   boolean isRoutineCall();

   boolean isTentativeCall();

   boolean upgradeBreakToCall(long var1);

   boolean downgradeCallToBreak();

   IFlowInformation getBreakingFlow(long var1, boolean var3);

   IEBranchDetails getBranchDetails();

   IEBranchDetails getBranchDetails(boolean var1);

   boolean setBranchDetails(IEBranchDetails var1);

   IEAssign duplicateWithNewOperands(IEGeneric var1, IEGeneric var2);

   IEImm evaluate(EState var1, boolean var2);
}
