package com.pnfsoftware.jebglobal;

public enum bff {
   pC,
   A,
   kS,
   wS,
   UT,
   E,
   sY,
   ys,
   ld,
   gp,
   oT,
   fI,
   WR,
   NS,
   vP,
   xC,
   ED,
   Sc,
   ah,
   eP,
   UO,
   Ab;

   private final String rl = "." + this.name().replace("__", "-").replace('_', ' ').toLowerCase();

   public String pC() {
      return this.rl;
   }
}
