package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import java.util.ArrayList;
import java.util.List;

public class btg {
   List pC = new ArrayList();
   List A = new ArrayList();

   btf pC(String var1) {
      this.pC.add(var1);
      btf var2 = new btf(var1);
      this.A.add(var2);
      return var2;
   }

   void pC(String var1, String var2, IDImm var3) {
      int var4 = this.pC.indexOf(var1);
      ((btf)this.A.get(var4)).pC(var2, var3);
   }

   boolean A(String var1) {
      return this.pC.indexOf(var1) >= 0;
   }

   boolean A(String var1, String var2, IDImm var3) {
      int var4 = this.pC.indexOf(var1);
      return var4 >= 0 ? ((btf)this.A.get(var4)).A(var2, var3) : false;
   }

   IDImm pC(String var1, String var2) {
      int var3 = this.pC.indexOf(var1);
      if (var3 >= 0) {
         for (btf var5 : this.A.subList(var3, this.A.size())) {
            IDImm var6 = var5.pC(var2);
            if (var6 != null) {
               return var6;
            }
         }
      }

      return null;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (btf var3 : this.A) {
         var1.append(var3).append("\n");
      }

      return var1.toString();
   }
}
