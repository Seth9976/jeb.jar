package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

public class K {
   public static final K pC = new K(null, 1, 0, null);
   private final String E;
   private final int sY;
   private final int ys;
   private final String ld;
   boolean A;
   Long kS;
   Integer wS;
   boolean UT;

   public K(String var1, int var2, int var3, String var4) {
      this.E = var1;
      this.sY = var2;
      this.ys = var3;
      this.ld = var4;
   }

   public K(String var1, int var2, int var3, String var4, boolean var5) {
      this(var1, var2, var3, var4);
      this.A = var5;
   }

   public void pC(long var1) {
      this.kS = var1;
   }

   public void pC(boolean var1) {
      this.UT = var1;
   }

   public void pC(int var1) {
      this.wS = var1;
   }

   public String pC() {
      return this.E;
   }

   public int A() {
      return this.sY;
   }

   public int kS() {
      return this.ys;
   }
}
