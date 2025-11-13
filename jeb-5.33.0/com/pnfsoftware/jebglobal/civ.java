package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class civ {
   private ciw pC;
   private int A;
   private int kS;
   private int wS;

   public civ(ciw var1, int var2, int var3, int var4) {
      if (var1 == null) {
         throw new NullPointerException("The resource data entry must have a resource entry parent");
      } else {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }
   }

   public ciw pC() {
      return this.pC;
   }

   public int A() {
      return this.A;
   }

   public int kS() {
      return this.kS;
   }

   @Override
   public String toString() {
      return Strings.ff("rva:%Xh,size=%d,codepage=%d", this.A, this.kS, this.wS);
   }
}
