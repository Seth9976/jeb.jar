package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IECall extends IEStatement {
   void setFailsafePrototype(boolean var1);

   boolean isFailsafePrototype();

   IWildcardPrototype getPrototype();

   void setPrototype(IWildcardPrototype var1, boolean var2);

   IEGeneric getCallSite();

   void setCallsite(IEGeneric var1);

   boolean isStaticCallsite();

   INativeMethodItem getStaticCallsite();

   List getDynamicTargetCandidates();

   IEGeneric getReturnLocation();

   void setReturnLocation(IEGeneric var1);

   int getCountOfReturns();

   List getReturnExpressions();

   IEGeneric getReturnExpression(int var1);

   void setReturnExpression(int var1, IEGeneric var2);

   int getCountOfArguments();

   List getArguments();

   IEGeneric getArgument(int var1);

   List getSpoiledExpressions();

   void addSpoiledVariables(List var1);

   int getStackPointerDeltaAfterExecution();

   void setStackPointerDeltaAfterExecution(int var1);

   void setReturnExpressionUnused(boolean var1);

   boolean isReturnExpressionUnused();

   void setMemoryAccessInfo(MemoryAccessInfo var1);

   MemoryAccessInfo getMemoryAccessInfo();

   void setNonReturning(Boolean var1);

   Boolean getNonReturning();

   void setTentativeCall(boolean var1);

   boolean isTentativeCall();

   boolean setHintArgumentPointsToExternalMemory(int var1, boolean var2);

   boolean getHintArgumentPointsToExternalMemory(int var1);
}
