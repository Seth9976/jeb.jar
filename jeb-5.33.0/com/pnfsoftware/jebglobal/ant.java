package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

class ant {
   anu pC;
   List A;
   List kS;
   TreeMap wS;
   int UT = -1;
   List E = new ArrayList();
   IClassType sY;

   public ant(anu var1, Collection var2, Collection var3) {
      this.pC = var1;
      this.A = new ArrayList(var2);
      this.kS = new ArrayList(var3);
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   public String pC(ILabelManager var1) {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "Class%s{constructors:%d,vtables:%d}", this.UT < 0 ? "" : "_" + this.UT, this.A.size(), this.kS.size());
      if (this.wS != null && !this.wS.isEmpty()) {
         var2.append("^");
         var2.append("{");
         int var3 = 0;

         for (ant var5 : this.wS.values()) {
            if (var3 > 0) {
               var2.append(",");
            }

            var2.append(var5.UT);
            var3++;
         }

         var2.append("}");
      }

      if (this.E != null && !this.E.isEmpty()) {
         var2.append("v");
         var2.append("{");
         int var6 = 0;

         for (ant var8 : this.E) {
            if (var6 > 0) {
               var2.append(",");
            }

            var2.append(var8.UT);
            var6++;
         }

         var2.append("}");
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return this.pC(null);
   }
}
