package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

class aqf {
   aqg q;
   List RF;
   List xK;
   TreeMap Dw;
   int Uv = -1;
   List oW = new ArrayList();
   IClassType gO;

   public aqf(aqg var1, Collection var2, Collection var3) {
      this.q = var1;
      this.RF = new ArrayList(var2);
      this.xK = new ArrayList(var3);
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   public String q(ILabelManager var1) {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "Class%s{constructors:%d,vtables:%d}", this.Uv < 0 ? "" : "_" + this.Uv, this.RF.size(), this.xK.size());
      if (this.Dw != null && !this.Dw.isEmpty()) {
         var2.append("^");
         var2.append("{");
         int var3 = 0;

         for (aqf var5 : this.Dw.values()) {
            if (var3 > 0) {
               var2.append(",");
            }

            var2.append(var5.Uv);
            var3++;
         }

         var2.append("}");
      }

      if (this.oW != null && !this.oW.isEmpty()) {
         var2.append("v");
         var2.append("{");
         int var6 = 0;

         for (aqf var8 : this.oW) {
            if (var6 > 0) {
               var2.append(",");
            }

            var2.append(var8.Uv);
            var6++;
         }

         var2.append("}");
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return this.q(null);
   }
}
