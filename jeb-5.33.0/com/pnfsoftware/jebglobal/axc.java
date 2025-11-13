package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModelListener;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class axc extends axa {
   public axc(awy var1) {
      super(var1);
   }

   @Override
   protected boolean pC(auu var1) {
      boolean var2 = super.pC(var1);
      IMemoryModelListener var3 = ((Tw)var1.E().ld()).pC();
      if (var3 instanceof C && var1.E() != null) {
         C var4 = (C)var3;
         var4.ys(var1.E().getMemoryAddress());
         return true;
      } else {
         return var2;
      }
   }
}
