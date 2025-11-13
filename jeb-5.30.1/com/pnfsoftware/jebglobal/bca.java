package com.pnfsoftware.jebglobal;

public class bca {
   public static bca q = new bca(1, false);
   public static final int RF = Integer.MAX_VALUE;
   private int xK;
   private boolean Dw;

   public bca(int var1, boolean var2) {
      this.xK = var1;
      this.Dw = var2;
   }

   public bca(int var1) {
      this(var1, true);
   }

   public bca() {
      this(Integer.MAX_VALUE);
   }

   public int q() {
      return this.xK;
   }

   public boolean RF() {
      return this.Dw;
   }
}
