package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class baw extends bay {
   public String pC;
   public bba A;
   public List kS = new ArrayList();
   public Long wS;

   baw(bbc var1) {
      super(var1);
      this.pC = var1.UT("name");
      bbc var2 = var1.wS("code");
      if (var2 != null && var2.A("entryPoint")) {
         this.wS = var1.pC.sY + ((Number)var2.pC("entryPoint", Number.class)).longValue();
      }
   }

   @Override
   void pC(bbb var1) {
   }

   @Override
   public String toString() {
      String var1 = "";
      if (this.A == null) {
         var1 = var1 + this.pC;
      } else {
         var1 = var1 + Strings.ff("%s %s(%s)", this.A, this.pC, Strings.join(", ", this.kS));
      }

      if (this.wS != null) {
         var1 = var1 + Strings.ff(" @ 0x%X", this.wS);
      }

      return var1;
   }
}
