package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizersPerformanceCounters;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class acy {
   long pC;
   int A;
   int kS;
   List wS = new ArrayList();
   List UT = new ArrayList();

   public long pC() {
      return this.pC == 0L ? -1L : 1000000L * this.A / this.pC;
   }

   public void pC(atj var1) {
      this.wS.add(var1);
   }

   public void pC(OptimizersPerformanceCounters var1) {
      this.UT.add(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("exectime=%d,nsize=%d,bsize=%d,speed=%d", this.pC, this.A, this.kS, this.pC());
   }
}
