package com.pnfsoftware.jeb.util.collect;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder {
   Map map;

   public MapBuilder() {
      this(null);
   }

   public MapBuilder(CollectionOrder var1) {
      if (var1 == null || var1 == CollectionOrder.NONE) {
         this.map = new HashMap();
      } else if (var1 == CollectionOrder.INSERTION) {
         this.map = new LinkedHashMap();
      } else {
         if (var1 != CollectionOrder.NATURAL) {
            throw new RuntimeException();
         }

         this.map = new LinkedHashMap();
      }
   }

   public MapBuilder put(Object var1, Object var2) {
      this.map.put(var1, var2);
      return this;
   }

   public MapBuilder put(Map var1) {
      this.map.putAll(var1);
      return this;
   }

   public Map build() {
      return this.map;
   }
}
