package com.pnfsoftware.jebglobal;

import java.util.List;
import java.util.TreeSet;

public class ng implements zz {
   private final Tw pC;

   public ng(Tw var1) {
      this.pC = var1;
   }

   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3, TreeSet var4, TreeSet var5) {
      List var6 = this.pC.getContainedRoutineAddresses(var1);
      return var6.size() > 1;
   }
}
