package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IEJumpWithOptionalCondition extends IEStatement {
   IEGeneric getCondition();

   void setCondition(IEGeneric var1);
}
