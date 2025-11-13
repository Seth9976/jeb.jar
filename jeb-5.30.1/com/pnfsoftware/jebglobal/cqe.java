package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class cqe {
   private cqf q;
   private int RF;
   private int xK;
   private int Dw;

   public cqe(cqf var1, int var2, int var3, int var4) {
      if (var1 == null) {
         throw new NullPointerException("The resource data entry must have a resource entry parent");
      } else {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }
   }

   public cqf q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   public int Dw() {
      return this.Dw;
   }

   @Override
   public String toString() {
      return Strings.ff("rva:%Xh,size=%d,codepage=%d", this.RF, this.xK, this.Dw);
   }
}
