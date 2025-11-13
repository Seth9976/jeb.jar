package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class PreArray implements IPreObject {
   private Object array;
   private Object[] objPlaceholder;
   private int[] oidPlaceholder;
   private int index;

   public PreArray(Object var1, int var2) {
      this.array = var1;
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
      return true;
   }

   @Override
   public Object build(Map var1) {
      for (int var2 = 0; var2 < this.objPlaceholder.length; var2++) {
         Object var3 = this.getItem(var2, var1);
         Array.set(this.array, var2, var3);
      }

      this.objPlaceholder = null;
      this.oidPlaceholder = null;
      return this.array;
   }

   @Override
   public Object getObject() {
      return this.array;
   }

   @Override
   public String toString() {
      return Strings.ff("Arr: %s", Arrays.toString(this.oidPlaceholder));
   }
}
