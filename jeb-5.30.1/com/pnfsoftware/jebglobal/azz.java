package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModelListener;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class azz extends azx {
   public azz(azv var1) {
      super(var1);
   }

   @Override
   protected boolean q(axp var1) {
      boolean var2 = super.q(var1);
      IMemoryModelListener var3 = ((aaf)var1.oW().gP()).q();
      if (var3 instanceof abg && var1.oW() != null) {
         abg var4 = (abg)var3;
         var4.za(var1.oW().getMemoryAddress());
         return true;
      } else {
         return var2;
      }
   }
}
