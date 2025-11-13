package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bsx implements bsv {
   public int pC;
   public int A;
   public int kS;
   public bsw wS;
   public Long UT;

   public bsx(int var1, int var2, int var3, bsw var4) {
      if ((var3 & 16) != 0) {
         throw new IllegalArgumentException("Invalid flag, wildcard leaf cannot be a determined immediate value");
      } else if (var1 >= 0 && var1 < 1000) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = null;
      } else {
         throw new IllegalArgumentException("Invalid id for leaf: " + var1);
      }
   }

   public bsx(int var1, int var2, int var3) {
      this(var1, var2, var3, null);
   }

   public bsx(long var1, int var3, int var4) {
      if (var4 >= -1 && var4 < 1000) {
         this.pC = var4;
         this.A = var3;
         this.kS = 16;
         this.wS = null;
         this.UT = var1;
      } else {
         throw new IllegalArgumentException("Invalid id for leaf: " + var4);
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.UT != null) {
         var1.append("I");
      } else {
         var1.append("L");
      }

      if (this.pC >= 0) {
         Strings.ff(var1, "(%d)", this.pC);
      }

      if (this.UT != null) {
         Strings.ff(var1, ":0x%X", this.UT);
      }

      return var1.toString();
   }
}
