package com.pnfsoftware.jebglobal;

public class cvp {
   private byte[] q = new byte[256];
   private int RF;
   private int xK;

   public cvp(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public cvp(byte[] var1, int var2, int var3) {
      for (int var4 = 0; var4 < 256; var4++) {
         this.q[var4] = (byte)var4;
      }

      int var8 = 0;
      int var5 = var2;

      for (int var6 = 0; var6 < 256; var6++) {
         if (var5 == var3) {
            var5 = var2;
         }

         var8 = (var8 + this.q[var6] + var1[var5]) % 256 & 0xFF;
         byte var7 = this.q[var6];
         this.q[var6] = this.q[var8];
         this.q[var8] = var7;
         var5++;
      }
   }

   public void q(byte[] var1) {
      this.q(var1, 0, var1.length);
   }

   public void q(byte[] var1, int var2, int var3) {
      while (var2 < var3) {
         this.RF = (this.RF + 1) % 256 & 0xFF;
         this.xK = (this.xK + this.q[this.RF]) % 256 & 0xFF;
         byte var4 = this.q[this.RF];
         this.q[this.RF] = this.q[this.xK];
         this.q[this.xK] = var4;
         byte var5 = this.q[(this.q[this.RF] + this.q[this.xK]) % 256 & 0xFF];
         var1[var2] ^= var5;
         var2++;
      }
   }
}
