package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class DCopyOptions {
   public Map replmap_id = new IdentityHashMap();
   public Map replmap_eq = new HashMap();
   public Map identmap = new HashMap();
   private Map replcntmap = new IdentityHashMap();

   public boolean hasOptions() {
      return !this.replmap_id.isEmpty() || !this.replmap_eq.isEmpty() || !this.identmap.isEmpty();
   }

   public IDExpression onDup(IDExpression var1) {
      IDExpression var2 = (IDExpression)this.replmap_id.get(var1);
      if (var2 == null) {
         var2 = (IDExpression)this.replmap_eq.get(var1);
      }

      if (var2 != null) {
         int var4 = (Integer)this.replcntmap.compute(var2, (var0, var1x) -> var1x == null ? 1 : var1x + 1);
         return var4 == 1 ? var2 : var2.duplicate();
      } else {
         if (var1 instanceof IDVar) {
            IDVar var3 = (IDVar)this.identmap.get(((IDVar)var1).getId());
            if (var3 != null) {
               return var3.duplicate();
            }
         }

         return null;
      }
   }
}
