package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import java.util.TreeSet;

public class eR implements YX {
   private final aaf q;

   public eR(aaf var1) {
      this.q = var1;
   }

   public boolean q(long var1, fA var3, TreeSet var4, TreeSet var5) {
      axp var6 = this.q.oW(var1);
      CFG var7 = var6.getData().getCFG();
      INativeContinuousItem var8 = this.q.getPreviousItem(var7.getFirstAddress());
      return var8 == null || var7.getFirstAddress() - var8.getMemoryAddress() > 64L;
   }
}
