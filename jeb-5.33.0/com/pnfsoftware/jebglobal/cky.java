package com.pnfsoftware.jebglobal;

public class cky {
   private byte[] pC = new byte[256];
   private int A;
   private int kS;

   public cky(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public cky(byte[] var1, int var2, int var3) {
      for (int var4 = 0; var4 < 256; var4++) {
         this.pC[var4] = (byte)var4;
      }

      int var8 = 0;
      int var5 = var2;

      for (int var6 = 0; var6 < 256; var6++) {
         if (var5 == var3) {
            var5 = var2;
         }

         var8 = (var8 + this.pC[var6] + var1[var5]) % 256 & 0xFF;
         byte var7 = this.pC[var6];
         this.pC[var6] = this.pC[var8];
         this.pC[var8] = var7;
         var5++;
      }
   }

   public void pC(byte[] var1, int var2, int var3) {
      while (var2 < var3) {
         this.A = (this.A + 1) % 256 & 0xFF;
         this.kS = (this.kS + this.pC[this.A]) % 256 & 0xFF;
         byte var4 = this.pC[this.A];
         this.pC[this.A] = this.pC[this.kS];
         this.pC[this.kS] = var4;
         byte var5 = this.pC[(this.pC[this.A] + this.pC[this.kS]) % 256 & 0xFF];
         var1[var2] ^= var5;
         var2++;
      }
   }
}
