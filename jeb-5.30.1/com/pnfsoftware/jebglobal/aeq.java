package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizersPerformanceCounters;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class aeq {
   long q;
   int RF;
   int xK;
   List Dw = new ArrayList();
   List Uv = new ArrayList();

   public long q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   public long Dw() {
      return this.q == 0L ? -1L : 1000000L * this.RF / this.q;
   }

   public void q(awb var1) {
      this.Dw.add(var1);
   }

   public List Uv() {
      return Collections.unmodifiableList(this.Dw);
   }

   public void q(OptimizersPerformanceCounters var1) {
      this.Uv.add(var1);
   }

   public List oW() {
      return this.Uv;
   }

   public String gO() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "-> Global: %s", this.toString());
      int var2 = 0;

      for (awb var4 : this.Dw) {
         Strings.ff(var1, "\n");
         Strings.ff(var1, "-> Pipeline #%d:\n", var2);
         Strings.ff(var1, "%s", var4.RF());
         var2++;
      }

      var2 = 0;

      for (OptimizersPerformanceCounters var7 : this.Uv) {
         Strings.ff(var1, "\n");
         Strings.ff(var1, "-> IR Optimizer #%d:\n", var2);
         Strings.ff(var1, "%s", var7.format());
         var2++;
      }

      return var1.toString();
   }

   @Override
   public String toString() {
      return Strings.ff("exectime=%d,nsize=%d,bsize=%d,speed=%d", this.q, this.RF, this.xK, this.Dw());
   }
}
