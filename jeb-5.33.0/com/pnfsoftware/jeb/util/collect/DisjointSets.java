package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class DisjointSets {
   @SerId(1)
   private List sets = new ArrayList();

   public void clear() {
      this.sets.clear();
   }

   public boolean isEmpty() {
      return this.sets.isEmpty();
   }

   public int size() {
      return this.sets.size();
   }

   public int getCountOfValues() {
      int var1 = 0;

      for (Set var3 : this.sets) {
         var1 += var3.size();
      }

      return var1;
   }

   public Set getValues() {
      HashSet var1 = new HashSet(this.getCountOfValues());

      for (Set var3 : this.sets) {
         var1.addAll(var3);
      }

      return var1;
   }

   public Collection getSets() {
      ArrayList var1 = new ArrayList();
      this.sets.forEach(var1x -> var1.add(Collections.unmodifiableSet(var1x)));
      return Collections.unmodifiableList(var1);
   }

   public boolean add(Object var1) {
      for (Set var3 : this.sets) {
         if (var3.contains(var1)) {
            return false;
         }
      }

      HashSet var4 = new HashSet();
      var4.add(var1);
      this.sets.add(var4);
      return true;
   }

   public boolean add(Object var1, Object var2) {
      int var3 = -1;
      int var4 = -1;

      for (int var5 = 0; var5 < this.sets.size(); var5++) {
         Set var6 = (Set)this.sets.get(var5);
         if (var3 < 0 && var6.contains(var1)) {
            var3 = var5;
         }

         if (var4 < 0 && var6.contains(var2)) {
            var4 = var5;
         }
      }

      if (var3 < 0 && var4 < 0) {
         HashSet var10 = new HashSet();
         var10.add(var1);
         var10.add(var2);
         this.sets.add(var10);
         return true;
      } else if (var3 >= 0 && var4 < 0) {
         Set var9 = (Set)this.sets.get(var3);
         return var9.add(var2);
      } else if (var3 < 0 && var4 >= 0) {
         Set var8 = (Set)this.sets.get(var4);
         return var8.add(var1);
      } else if (var3 != var4) {
         Set var7 = (Set)this.sets.get(var3);
         var7.addAll((Collection)this.sets.remove(var4));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.sets.toString();
   }
}
