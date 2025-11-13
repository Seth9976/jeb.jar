package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

class aqe {
   boolean q;
   int RF;
   int xK;
   Long Dw;

   public aqe(int var1, long var2) {
      this.q = true;
      this.RF = var1;
      this.Dw = var2;
   }

   public aqe(int var1, int var2, Long var3) {
      this.q = false;
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
   }

   @Override
   public String toString() {
      String var1;
      if (this.q) {
         var1 = Strings.ff("call_%Xh(this+%Xh,...)", this.Dw, this.RF);
      } else {
         var1 = Strings.ff("%d[this+%Xh]", this.xK, this.RF);
         if (this.Dw != null) {
            var1 = var1 + Strings.ff("=0x%X", this.Dw);
         }
      }

      return var1;
   }
}
