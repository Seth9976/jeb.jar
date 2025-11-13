package com.pnfsoftware.jeb.core.units.code.android;

public enum EffectiveFinalityType {
   NON_FINAL(false),
   ALMOST_FINAL(true),
   FINAL(true),
   UNKNOWN(false);

   private final boolean finalLike;

   private EffectiveFinalityType(boolean var3) {
      this.finalLike = var3;
   }

   public boolean isFinalLike() {
      return this.finalLike;
   }
}
