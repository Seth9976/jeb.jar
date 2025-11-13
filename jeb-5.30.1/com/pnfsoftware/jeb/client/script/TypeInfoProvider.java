package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TypeInfoProvider implements Iterable {
   Map map = new LinkedHashMap();
   MultiMap map1 = new MultiMap();
   MultiMap map1L = new MultiMap();

   public TypeInfoProvider(File var1) throws IOException {
      for (String var3 : IO.readLines(var1)) {
         int var4 = var3.indexOf(35);
         if (var4 >= 0) {
            var3 = var3.substring(0, var4);
         }

         var3 = var3.trim();
         if (!var3.isEmpty()) {
            TypeInfo var5 = new TypeInfo(var3);
            this.map.put(var5.name, var5);
            this.map1.put(var5.simplename, var5);
            this.map1L.put(var5.simplename.toLowerCase(), var5);
         }
      }
   }

   public int size() {
      return this.map.size();
   }

   @Override
   public Iterator iterator() {
      return this.map.values().iterator();
   }

   public TypeInfo get(String var1) {
      return (TypeInfo)this.map.get(var1);
   }

   public String findTypeName(String var1) {
      return this.findTypeName(var1, false);
   }

   public String findTypeName(String var1, boolean var2) {
      TypeInfo var3 = this.findType(var1, var2);
      return var3 == null ? null : var3.name;
   }

   public TypeInfo findType(String var1) {
      return this.findType(var1, false);
   }

   public TypeInfo findType(String var1, boolean var2) {
      List var3;
      if (!var2) {
         var3 = this.map1.getSafe(var1);
      } else {
         var3 = this.map1L.getSafe(var1.toLowerCase());
      }

      return var3.size() != 1 ? null : (TypeInfo)var3.get(0);
   }
}
