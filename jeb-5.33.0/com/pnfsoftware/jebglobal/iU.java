package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import java.util.TreeSet;

public class iU implements zz {
   private final Tw pC;

   public iU(Tw var1) {
      this.pC = var1;
   }

   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3, TreeSet var4, TreeSet var5) {
      auu var6 = this.pC.E(var1);
      CFG var7 = var6.getData().getCFG();
      INativeContinuousItem var8 = this.pC.getPreviousItem(var7.getFirstAddress());
      return var8 == null || var7.getFirstAddress() - var8.getMemoryAddress() > 64L;
   }
}
