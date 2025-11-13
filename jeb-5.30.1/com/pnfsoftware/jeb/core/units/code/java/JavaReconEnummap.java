package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Ser(oldId = 65702)
public class JavaReconEnummap {
   @SerId(1)
   IJavaType enumtype;
   @SerId(value = 2, deprecated = true)
   private Map _map;
   @SerId(3)
   Map map;

   @SerCustomInitPostGraph
   private void init() {
      if (this._map != null) {
         this.map = new ConcurrentHashMap(this._map.size());
         this._map.forEach((var1, var2) -> this.map.put(var1, var2.getSignature()));
         this._map = null;
      }
   }

   public JavaReconEnummap(IJavaType var1) {
      this.enumtype = var1;
      this.map = new ConcurrentHashMap();
   }

   public IJavaType getEnumerationType() {
      return this.enumtype;
   }

   public Map getMap() {
      return this.map;
   }
}
