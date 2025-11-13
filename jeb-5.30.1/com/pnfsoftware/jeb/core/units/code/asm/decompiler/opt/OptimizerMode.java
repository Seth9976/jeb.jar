package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

public enum OptimizerMode {
   NORMAL(0),
   AGGRESSIVE(10),
   UNFRIENDLY(1000);

   private final int value;

   private OptimizerMode(int var3) {
      this.value = var3;
   }

   public boolean isAggressive() {
      return this.value >= AGGRESSIVE.value;
   }

   public boolean isUnfriendly() {
      return this.value >= UNFRIENDLY.value;
   }

   public boolean meetsRequirement(OptimizerMode var1) {
      return var1 != null && this.value >= var1.value;
   }
}
