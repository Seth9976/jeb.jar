package com.pnfsoftware.jebglobal;

public class aza {
   public static aza pC = new aza(1, false);
   private int A;
   private boolean kS;

   public aza(int var1, boolean var2) {
      this.A = var1;
      this.kS = var2;
   }

   public aza(int var1) {
      this(var1, true);
   }

   public aza() {
      this(Integer.MAX_VALUE);
   }

   public int pC() {
      return this.A;
   }

   public boolean A() {
      return this.kS;
   }
}
