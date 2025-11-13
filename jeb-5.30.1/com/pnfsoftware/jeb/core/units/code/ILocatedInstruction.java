package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ILocatedInstruction extends IInstruction {
   long getOffset();

   default long getOffsetEnd() {
      return this.getOffset() + this.getSize();
   }

   IFlowInformation getBreakingFlow();

   IFlowInformation getRoutineCall();

   IFlowInformation collectIndirectCallReferences();

   long getPrimaryBranchAddress();
}
