package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IEJumpFar extends IEJumpWithOptionalCondition {
   IEGeneric getJumpsite();

   void setJumpsite(IEGeneric var1);

   List getPossibleTargets();

   void setPossibleTargets(List var1);
}
