package com.pnfsoftware.jebglobal;

public enum bfe {
   pC,
   A,
   kS,
   wS,
   UT,
   E,
   sY;

   private final String ys = "." + this.name().replace("__", "-").replace('_', ' ').toLowerCase();

   public String pC() {
      return this.ys;
   }
}
