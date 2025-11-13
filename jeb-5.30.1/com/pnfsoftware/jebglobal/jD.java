package com.pnfsoftware.jebglobal;

public abstract class jD implements Ef {
   private final jD.eo[] q;

   public jD() {
      this.q = null;
   }

   public jD(jD.eo... var1) {
      this.q = var1.length == 1 && var1[0] == null ? null : var1;
   }

   public boolean q(long var1) {
      if (this.q == null) {
         return false;
      } else {
         for (jD.eo var6 : this.q) {
            if (var6.q() && var1 == var6.RF()) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean q(jD.eo var1) {
      if (this.q == null) {
         return false;
      } else {
         for (jD.eo var5 : this.q) {
            if (var5 == var1) {
               return true;
            }
         }

         return false;
      }
   }

   public int q() {
      int var1 = 0;
      if (this.q == null) {
         return var1;
      } else {
         for (jD.eo var5 : this.q) {
            var1 |= var5.xK();
         }

         return var1;
      }
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za(0),
      lm(15),
      zz(30),
      JY(31);

      private final boolean HF;
      private final int LK;

      private eo() {
         this.HF = false;
         this.LK = 0;
      }

      private eo(int var3) {
         this.HF = true;
         this.LK = var3;
      }

      public boolean q() {
         return this.HF;
      }

      public int RF() {
         return this.LK;
      }

      public int xK() {
         int var1 = 0;
         if (this == Dw) {
            var1 += 65536;
         }

         if (this == xK) {
            var1++;
         }

         if (this == RF) {
            var1 += 131072;
         }

         if (this == Uv) {
            var1 += 64;
         }

         return var1;
      }
   }
}
