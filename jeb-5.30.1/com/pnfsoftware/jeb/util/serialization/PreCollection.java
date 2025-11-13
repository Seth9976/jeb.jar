package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PreCollection implements IPreObject {
   Collection coll;
   Object[] objPlaceholder;
   int[] oidPlaceholder;
   int index;

   public PreCollection(Collection var1, int var2) {
      this.coll = var1;
      this.oidPlaceholder = new int[var2];
      this.objPlaceholder = new Object[var2];
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
      if (!(this.coll instanceof List) && !(this.coll instanceof IdentityHashSet)) {
         for (int var3 = 0; var3 < this.objPlaceholder.length; var3++) {
            Object var4 = this.getItem(var3, var1);
            if (var2.contains(var4)) {
               return false;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   @Override
   public Object build(Map var1) {
      for (int var2 = 0; var2 < this.objPlaceholder.length; var2++) {
         Object var3 = this.getItem(var2, var1);
         this.coll.add(var3);
      }

      this.objPlaceholder = null;
      this.oidPlaceholder = null;
      return this.coll;
   }

   public Collection getObject() {
      return this.coll;
   }

   @Override
   public String toString() {
      return Strings.ff("Col: %s", Arrays.toString(this.oidPlaceholder));
   }
}
