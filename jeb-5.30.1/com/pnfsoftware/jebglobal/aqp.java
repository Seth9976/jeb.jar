package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.List;

class aqp {
   long q;
   int RF;
   List xK;
   List Dw;
   List Uv;

   aqp(long var1, int var3, List var4) {
      if (var4.isEmpty()) {
         throw new RuntimeException("A vtable must contain at least one entry");
      } else if (var4.size() > var3) {
         throw new RuntimeException();
      } else {
         this.q = var1;
         this.RF = var3;
         this.xK = new ArrayList(var4);
      }
   }

   void q() {
      Assert.a(this.Dw == null && this.Uv == null);
      this.Dw = new ArrayList(this.xK.size());
      this.Uv = new ArrayList(this.xK.size());
   }

   public int RF() {
      return this.xK.size();
   }

   public String q(ILabelManager var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("Vtable@");
      var2.append(aqo.q(var1, this.q));
      var2.append("=[");
      var2.append(aqo.q(var1, this.xK));
      var2.append("]");
      return var2.toString();
   }

   @Override
   public String toString() {
      return this.q(null);
   }
}
