package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.List;

class aod {
   long pC;
   int A;
   List kS;
   List wS;
   List UT;

   aod(long var1, int var3, List var4) {
      if (var4.isEmpty()) {
         throw new RuntimeException("A vtable must contain at least one entry");
      } else if (var4.size() > var3) {
         throw new RuntimeException();
      } else {
         this.pC = var1;
         this.A = var3;
         this.kS = new ArrayList(var4);
      }
   }

   void pC() {
      Assert.a(this.wS == null && this.UT == null);
      this.wS = new ArrayList(this.kS.size());
      this.UT = new ArrayList(this.kS.size());
   }

   public int A() {
      return this.kS.size();
   }

   public String pC(ILabelManager var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("Vtable@");
      var2.append(aoc.pC(var1, this.pC));
      var2.append("=[");
      var2.append(aoc.pC(var1, this.kS));
      var2.append("]");
      return var2.toString();
   }

   @Override
   public String toString() {
      return this.pC(null);
   }
}
