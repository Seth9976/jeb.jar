package com.pnfsoftware.jeb.util.serialization.objects;

import com.pnfsoftware.jeb.util.serialization.SerializerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

@Ser
public class SerEnumMap implements Map {
   @SerTransient
   private EnumMap map;
   @SerId(1)
   private Class c;
   @SerId(2)
   private Map mapForStorage;

   public static SerEnumMap wrap(Class var0, EnumMap var1) {
      if (var0 != null && var1 != null) {
         SerEnumMap var2 = new SerEnumMap();
         var2.c = var0;
         var2.map = var1;
         return var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @SerCustomWrite
   private void save(SerializerHelper var1) throws IOException {
      this.mapForStorage = new HashMap(this.map.size());

      for (Entry var3 : this.map.entrySet()) {
         this.mapForStorage.put((Enum)var3.getKey(), var3.getValue());
      }

      var1.saveStandard();
      this.mapForStorage = null;
   }

   @SerCustomInitPostGraph
   private void postInit() {
      this.map = new EnumMap(this.c);

      for (Entry var2 : this.mapForStorage.entrySet()) {
         this.put((Enum)var2.getKey(), var2.getValue());
      }

      this.mapForStorage = null;
   }

   public EnumMap wrapped() {
      return this.map;
   }

   @Override
   public int size() {
      return this.map.size();
   }

   @Override
   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   @Override
   public boolean containsKey(Object var1) {
      return this.map.containsKey(var1);
   }

   @Override
   public boolean containsValue(Object var1) {
      return this.map.containsValue(var1);
   }

   @Override
   public Object get(Object var1) {
      return this.map.get(var1);
   }

   public Object put(Enum var1, Object var2) {
      return this.map.put(var1, var2);
   }

   @Override
   public Object remove(Object var1) {
      return this.map.remove(var1);
   }

   @Override
   public void putAll(Map var1) {
      this.map.putAll(var1);
   }

   @Override
   public void clear() {
      this.map.clear();
   }

   @Override
   public Set keySet() {
      return this.map.keySet();
   }

   @Override
   public Collection values() {
      return this.map.values();
   }

   @Override
   public Set entrySet() {
      return this.map.entrySet();
   }

   @Override
   public int hashCode() {
      return this.map.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this.map.equals(var1);
   }

   @Override
   public String toString() {
      return this.map.toString();
   }
}
