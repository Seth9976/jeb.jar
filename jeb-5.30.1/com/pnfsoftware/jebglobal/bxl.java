package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bxl implements bxj {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 3;
   public static final int Uv = 7;
   static final int oW = 16;
   static final int gO = 32;
   public int nf;
   public int gP;
   public int za;
   public bxk lm;
   public Long zz;

   public bxl(int var1, int var2, int var3, bxk var4) {
      if ((var3 & 16) != 0) {
         throw new IllegalArgumentException("Invalid flag, wildcard leaf cannot be a determined immediate value");
      } else if (var1 >= 0 && var1 < 1000) {
         this.nf = var1;
         this.gP = var2;
         this.za = var3;
         this.lm = var4;
         this.zz = null;
      } else {
         throw new IllegalArgumentException("Invalid id for leaf: " + var1);
      }
   }

   public bxl(int var1, int var2, int var3) {
      this(var1, var2, var3, null);
   }

   public bxl(long var1, int var3, int var4) {
      if (var4 >= -1 && var4 < 1000) {
         this.nf = var4;
         this.gP = var3;
         this.za = 16;
         this.lm = null;
         this.zz = var1;
      } else {
         throw new IllegalArgumentException("Invalid id for leaf: " + var4);
      }
   }

   public bxl q(bxk var1) {
      this.lm = var1;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.zz != null) {
         var1.append("I");
      } else {
         var1.append("L");
      }

      if (this.nf >= 0) {
         Strings.ff(var1, "(%d)", this.nf);
      }

      if (this.zz != null) {
         Strings.ff(var1, ":0x%X", this.zz);
      }

      return var1.toString();
   }
}
