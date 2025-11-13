package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import java.util.HashMap;
import java.util.Map;

public class btf {
   private String pC;
   private Map A = new HashMap();

   btf(String var1) {
      this.pC = var1;
   }

   private boolean pC(String var1, IDImm var2, boolean var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null field name");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Field field wrapped value");
      } else if (!var3 && !this.A.containsKey(var1)) {
         return false;
      } else {
         this.A.put(var1, var2);
         return true;
      }
   }

   void pC(String var1, IDImm var2) {
      this.pC(var1, var2, true);
   }

   boolean A(String var1, IDImm var2) {
      return this.pC(var1, var2, true);
   }

   IDImm pC(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null field name");
      } else {
         return (IDImm)this.A.get(var1);
      }
   }

   @Override
   public String toString() {
      return this.pC + ":" + this.A.toString();
   }
}
