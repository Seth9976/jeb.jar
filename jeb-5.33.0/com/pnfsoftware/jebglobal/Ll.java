package com.pnfsoftware.jebglobal;

public abstract class Ll implements Hu {
   private final Ll.Av[] pC;

   public Ll() {
      this.pC = null;
   }

   public Ll(Ll.Av... var1) {
      this.pC = var1.length == 1 && var1[0] == null ? null : var1;
   }

   public boolean pC(long var1) {
      if (this.pC == null) {
         return false;
      } else {
         for (Ll.Av var6 : this.pC) {
            if (var6.pC() && var1 == var6.A()) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean pC(Ll.Av var1) {
      if (this.pC == null) {
         return false;
      } else {
         for (Ll.Av var5 : this.pC) {
            if (var5 == var1) {
               return true;
            }
         }

         return false;
      }
   }

   public int pC() {
      int var1 = 0;
      if (this.pC == null) {
         return var1;
      } else {
         for (Ll.Av var5 : this.pC) {
            var1 |= var5.kS();
         }

         return var1;
      }
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp(0),
      oT(15),
      fI(30),
      WR(31);

      private final boolean NS;
      private final int vP;

      private Av() {
         this.NS = false;
         this.vP = 0;
      }

      private Av(int var3) {
         this.NS = true;
         this.vP = var3;
      }

      public boolean pC() {
         return this.NS;
      }

      public int A() {
         return this.vP;
      }

      public int kS() {
         int var1 = 0;
         if (this == wS) {
            var1 += 65536;
         }

         if (this == kS) {
            var1++;
         }

         if (this == A) {
            var1 += 131072;
         }

         if (this == UT) {
            var1 += 64;
         }

         return var1;
      }
   }
}
