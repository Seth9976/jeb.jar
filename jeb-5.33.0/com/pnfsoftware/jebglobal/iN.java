package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class iN extends jR {
   public iN(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      super(var1);
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      IFlowInformation var3 = super.getBreakingFlow(var1);
      if (var3.isBroken() && !this.UT().UT()) {
         FlowInformation var4 = new FlowInformation();

         for (int var5 = 0; var5 < var3.getTargets().size() - 1; var5++) {
            var4.addTarget((ICodePointer)var3.getTargets().get(var5));
         }

         return var4;
      } else {
         return var3;
      }
   }
}
