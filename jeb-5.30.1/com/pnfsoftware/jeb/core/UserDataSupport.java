package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.HashMap;
import java.util.Map;

@Ser
public class UserDataSupport implements IUserDataSupport {
   @SerId(1)
   Map persistedDatamap;
   @SerTransient
   Map datamap;

   private Map getDatamap(boolean var1, boolean var2) {
      synchronized (this) {
         if (var1) {
            if (this.persistedDatamap == null && var2) {
               this.persistedDatamap = new HashMap();
            }

            return this.persistedDatamap;
         } else {
            if (this.datamap == null && var2) {
               this.datamap = new HashMap();
            }

            return this.datamap;
         }
      }
   }

   @Override
   public void setData(Object var1, Object var2, boolean var3) {
      this.getDatamap(var3, true).put(var1, var2);
      Map var4 = this.getDatamap(!var3, false);
      if (var4 != null) {
         var4.remove(var1);
      }
   }

   @Override
   public Object getData(Object var1) {
      Map var2 = this.getDatamap(true, false);
      if (var2 != null && var2.containsKey(var1)) {
         return var2.get(var1);
      } else {
         var2 = this.getDatamap(false, false);
         return var2 != null && var2.containsKey(var1) ? var2.get(var1) : null;
      }
   }

   @Override
   public void clearAllData(Object var1) {
      this.persistedDatamap = null;
      this.datamap = null;
   }

   @Override
   public Map getAllData() {
      HashMap var1 = new HashMap();
      Map var2 = this.getDatamap(true, false);
      if (var2 != null) {
         var1.putAll(var2);
      }

      var2 = this.getDatamap(false, false);
      if (var2 != null) {
         var1.putAll(var2);
      }

      return var1;
   }
}
