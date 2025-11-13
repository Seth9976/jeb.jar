package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import java.util.ArrayList;
import java.util.List;

public class bxv {
   List q = new ArrayList();
   List RF = new ArrayList();

   bxu q(String var1) {
      this.q.add(var1);
      bxu var2 = new bxu(var1);
      this.RF.add(var2);
      return var2;
   }

   void q(String var1, String var2, IDImm var3) {
      int var4 = this.q.indexOf(var1);
      ((bxu)this.RF.get(var4)).q(var2, var3);
   }

   boolean RF(String var1) {
      return this.q.indexOf(var1) >= 0;
   }

   boolean RF(String var1, String var2, IDImm var3) {
      int var4 = this.q.indexOf(var1);
      return var4 >= 0 ? ((bxu)this.RF.get(var4)).RF(var2, var3) : false;
   }

   IDImm q(String var1, String var2) {
      int var3 = this.q.indexOf(var1);
      if (var3 >= 0) {
         for (bxu var5 : this.RF.subList(var3, this.RF.size())) {
            IDImm var6 = var5.q(var2);
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

      for (bxu var3 : this.RF) {
         var1.append(var3).append("\n");
      }

      return var1.toString();
   }
}
