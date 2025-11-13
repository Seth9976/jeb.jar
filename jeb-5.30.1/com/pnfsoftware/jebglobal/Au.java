package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import java.util.TreeSet;

public class Au implements YX {
   private final aaf q;

   public Au(aaf var1) {
      this.q = var1;
   }

   public boolean q(long var1, fA var3, TreeSet var4, TreeSet var5) {
      axp var6 = this.q.oW(var1);
      CFG var7 = var6.getData().getCFG();
      if (var7.getFirstAddress() != var1) {
         Long var8 = (Long)var4.lower(var7.getFirstAddress());
         if (var8 != null) {
            axp var9 = this.q.oW(var8);
            CFG var10 = var9.getData().getCFG();
            if (var7.getFirstAddress() < var10.getEndAddress()) {
               return true;
            }
         }
      }

      return false;
   }
}
