package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IECond extends IEGeneric {
   IEGeneric getCondition();

   void setCondition(IEGeneric var1);

   IEGeneric getExpressionTrue();

   void setExpressionTrue(IEGeneric var1);

   IEGeneric getExpressionFalse();

   void setExpressionFalse(IEGeneric var1);
}
