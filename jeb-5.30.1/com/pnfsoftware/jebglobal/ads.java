package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class ads {
   @SerId(1)
   protected Object RF;
   @SerId(2)
   protected Object xK;
   @SerId(3)
   protected Map Dw;

   public ads(Object var1, Object var2) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = new ConcurrentHashMap();
   }

   public Object RF() {
      return this.RF;
   }

   public Object xK() {
      return this.xK;
   }

   public void q(String var1, Object var2) {
      this.Dw.put(var1, var2);
   }

   public void q(String var1) {
      this.Dw.remove(var1);
   }

   public Object RF(String var1) {
      return this.Dw.get(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
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
         ads var2 = (ads)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "[" + this.RF + "-->" + this.xK + ", attributes=" + this.Dw + "]";
   }
}
