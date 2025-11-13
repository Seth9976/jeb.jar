package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IESwitch extends IEStatement {
   IEGeneric getControlExpression();

   void setControlExpression(IEGeneric var1);

   boolean hasDefaultAddress();

   int getDefaultAddress();

   void setDefaultAddress(int var1);

   List getCases();

   void addCase(IEGeneric var1, int var2);

   Integer removeCase(IEGeneric var1);

   int getCountsToTarget(int var1, boolean var2);

   boolean isPossibleTarget(int var1);
}
