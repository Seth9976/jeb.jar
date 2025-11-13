package com.pnfsoftware.jeb.util.serialization;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTypeIdProvider implements ITypeIdProvider {
   private Map map = new HashMap();
   private Map rmap = new HashMap();
   private static Map movedmap = new HashMap();

   protected AbstractTypeIdProvider() {
      this.loadTypes(this.map, movedmap);

      for (Class var2 : this.map.keySet()) {
         Integer var3 = (Integer)this.map.get(var2);
         if (this.rmap.containsKey(var3)) {
            throw new RuntimeException("Duplicate type-ids were found");
         }

         this.rmap.put(var3, var2);
      }
   }

   @Override
   public int getId(Class var1) {
      Integer var2 = (Integer)this.map.get(var1);
      return var2 == null ? 0 : var2;
   }

   @Override
   public Class getType(int var1) {
      Class var2 = (Class)this.rmap.get(var1);
      if (var2 == null) {
         Integer var3 = (Integer)movedmap.get(var1);
         if (var3 != null) {
            var2 = (Class)this.rmap.get(var3);
         }
      }

      return var2;
   }

   @Override
   public Map getMap() {
      return this.map;
   }

   @Override
   public Map getReverseMap() {
      return this.rmap;
   }

   @Override
   public void addAll(ITypeIdProvider var1) {
      Map var2 = var1.getMap();

      for (Class var4 : var2.keySet()) {
         Integer var5 = (Integer)var2.get(var4);
         if (this.rmap.containsKey(var5)) {
            throw new RuntimeException("Duplicate type-ids were found");
         }

         this.map.put(var4, var5);
         this.rmap.put(var5, var4);
      }
   }

   protected abstract void loadTypes(Map var1, Map var2);
}
