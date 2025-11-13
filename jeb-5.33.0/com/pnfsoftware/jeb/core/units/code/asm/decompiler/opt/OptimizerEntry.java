package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

import com.pnfsoftware.jeb.util.format.Strings;

public class OptimizerEntry {
   private IOptimizer opt;
   private int group;
   private boolean enabled;
   OptimizerEntry.Stat stat;

   public OptimizerEntry(IOptimizer var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.opt = var1;
         this.group = var2;
         this.enabled = true;
         this.stat = new OptimizerEntry.Stat();
      }
   }

   public IOptimizer getOptimizer() {
      return this.opt;
   }

   public int getGroup() {
      return this.group;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean setEnabled(boolean var1) {
      boolean var2 = this.enabled;
      this.enabled = var1;
      return var2;
   }

   public OptimizerEntry.Stat getStatistics() {
      return this.stat;
   }

   @Override
   public String toString() {
      return Strings.ff("Optimizer:\"%s\",stats=%s", this.opt.getPluginInformation().getName(), this.getStatistics());
   }

   public static class Stat {
      long runtimeMs;
      int runcnt;
      int optcnt;

      public long getExecutionTimeMillis() {
         return this.runtimeMs;
      }

      public int getRunCount() {
         return this.runcnt;
      }

      public int getOptimizationCount() {
         return this.optcnt;
      }

      @Override
      public String toString() {
         return Strings.ff("ms=%d,run=%d,opt=%d", this.getExecutionTimeMillis(), this.getRunCount(), this.getOptimizationCount());
      }
   }
}
