package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class aca {
   @SerId(1)
   protected Object pC;
   @SerId(2)
   protected Object A;
   @SerId(3)
   protected Map kS;

   public aca(Object var1, Object var2) {
      this.pC = var1;
      this.A = var2;
      this.kS = new ConcurrentHashMap();
   }

   public Object pC() {
      return this.pC;
   }

   public Object A() {
      return this.A;
   }

   public void pC(String var1, Object var2) {
      this.kS.put(var1, var2);
   }

   public Object pC(String var1) {
      return this.kS.get(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         aca var2 = (aca)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "[" + this.pC + "-->" + this.A + ", attributes=" + this.kS + "]";
   }
}
