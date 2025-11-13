package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public interface IEUntranslatedInstruction extends IEStatement {
   String TAG_CUSTOM_NAME = "CUSTOM_NAME";

   void setNativeAddress(long var1);

   long getNativeAddress();

   void setNativeMnemonic(String var1);

   String getNativeMnemonic();

   void setTag(Object var1);

   Object getTag();

   void setTag(String var1, Object var2);

   Map getTags();

   Object getTag(String var1);

   void setParameterExpressions(Collection var1);

   void setParameterExpressions(IEGeneric... var1);

   List getParameterExpressions();

   IEGeneric getParameterExpression(int var1);

   void setReturnExpression(IEGeneric var1);

   IEGeneric getReturnExpression();

   void setReturnExpressions(List var1);

   void setReturnExpressions(IEGeneric... var1);

   List getReturnExpressions();

   void setMemoryAccessInfo(MemoryAccessInfo var1);

   MemoryAccessInfo getMemoryAccessInfo();

   void addSideEffectDefinedVariable(IEVar... var1);

   Set getSideEffectDefinedVariables();

   void addSideEffectUsedVariable(IEVar... var1);

   Set getSideEffectUsedVariables();

   void addSideEffectSpoiledVariable(IEVar... var1);

   Set getSideEffectSpoiledVariables();

   void setBreakingFlow(IFlowInformation var1);

   void setRoutineCall(IFlowInformation var1);
}
