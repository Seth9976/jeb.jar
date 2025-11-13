package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.IdentityHashMap;
import java.util.Map;

public class PreMap implements IPreObject {
   private Map map;
   private Object[] objPlaceholder;
   private int[] oidPlaceholder;
   private int index;

   public PreMap(Map var1, int var2) {
      this.map = var1;
      this.objPlaceholder = new Object[2 * var2];
      this.oidPlaceholder = new int[2 * var2];
   }

   public void recordObj(Object var1) {
      this.objPlaceholder[this.index++] = var1;
   }

   public void recordId(int var1) {
      this.oidPlaceholder[this.index++] = var1;
   }

   int getCountOfItems() {
      return this.objPlaceholder.length;
   }

   Object getItem(int var1, Map var2) {
      return this.oidPlaceholder[var1] == 0 ? this.objPlaceholder[var1] : var2.get(this.oidPlaceholder[var1]);
   }

   @Override
   public boolean canBuild(Map var1, IdentityHashSet var2) {
      if (this.map instanceof IdentityHashMap) {
         return true;
      } else {
         for (byte var3 = 0; var3 < this.objPlaceholder.length; var3 += 2) {
            Object var4 = this.getItem(var3, var1);
            if (var2.contains(var4)) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public Object build(Map var1) {
      for (byte var2 = 0; var2 < this.objPlaceholder.length; var2 += 2) {
         Object var3 = this.getItem(var2, var1);
         Object var4 = this.getItem(var2 + 1, var1);
         this.map.put(var3, var4);
      }

      this.objPlaceholder = null;
      this.oidPlaceholder = null;
      return this.map;
   }

   public Map getObject() {
      return this.map;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("Map: ");

      for (byte var2 = 0; var2 < this.oidPlaceholder.length; var2 += 2) {
         if (var2 > 0) {
            var1.append(", ");
         }

         Strings.ff(var1, "(%d:%d)", this.oidPlaceholder[var2], this.oidPlaceholder[var2 + 1]);
      }

      return var1.toString();
   }
}
