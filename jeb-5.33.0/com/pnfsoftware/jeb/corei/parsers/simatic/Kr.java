package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import java.util.Comparator;

class Kr implements Comparator {
   Kr(gb var1) {
      this.pC = var1;
   }

   public int pC(IS7Block var1, IS7Block var2) {
      S7.BlockType var3 = S7.BlockType.fromId(var1.getTypeId());
      S7.BlockType var4 = S7.BlockType.fromId(var2.getTypeId());
      int var5 = (Integer)gb.E.getOrDefault(var3, -1);
      int var6 = (Integer)gb.E.getOrDefault(var4, -1);
      int var7 = Integer.compareUnsigned(var5, var6);
      return var7 != 0 ? var7 : Integer.compareUnsigned(var1.getNumber(), var2.getNumber());
   }
}
