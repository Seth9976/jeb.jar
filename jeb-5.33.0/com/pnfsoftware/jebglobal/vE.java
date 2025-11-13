package com.pnfsoftware.jebglobal;

public class vE {
   private int pC;
   private int A;
   private int kS;
   private int wS;
   private int UT;

   public vE(int var1, int var2, int var3, int var4, int var5) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
   }

   public VD pC() {
      return new VD(this.pC, this.A, this.kS, this.wS, this.UT);
   }

   public AN pC(byte[] var1) {
      return new AN(var1, this.pC, this.A, this.kS, this.wS, this.UT);
   }
}
