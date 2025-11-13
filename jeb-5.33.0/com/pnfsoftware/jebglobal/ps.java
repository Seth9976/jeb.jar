package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import java.util.TreeSet;

public class ps implements zz {
   private final Tw pC;

   public ps(Tw var1) {
      this.pC = var1;
   }

   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3, TreeSet var4, TreeSet var5) {
      auu var6 = this.pC.E(var1);
      CFG var7 = var6.getData().getCFG();
      if (var7.getFirstAddress() != var1) {
         Long var8 = (Long)var4.lower(var7.getFirstAddress());
         if (var8 != null) {
            auu var9 = this.pC.E(var8);
            CFG var10 = var9.getData().getCFG();
            if (var7.getFirstAddress() < var10.getEndAddress()) {
               return true;
            }
         }
      }

      return false;
   }
}
