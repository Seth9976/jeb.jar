package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import java.util.HashMap;
import java.util.Map;

public class bxu {
   private String q;
   private Map RF = new HashMap();

   bxu(String var1) {
      this.q = var1;
   }

   private boolean q(String var1, IDImm var2, boolean var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null field name");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Field field wrapped value");
      } else if (!var3 && !this.RF.containsKey(var1)) {
         return false;
      } else {
         this.RF.put(var1, var2);
         return true;
      }
   }

   void q(String var1, IDImm var2) {
      this.q(var1, var2, true);
   }

   boolean RF(String var1, IDImm var2) {
      return this.q(var1, var2, true);
   }

   IDImm q(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null field name");
      } else {
         return (IDImm)this.RF.get(var1);
      }
   }

   @Override
   public String toString() {
      return this.q + ":" + this.RF.toString();
   }
}
