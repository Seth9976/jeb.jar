package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.base.DynamicEnum;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.LinkedHashMap;

@Ser
public class CompilerType extends DynamicEnum {
   protected static LinkedHashMap map = new LinkedHashMap();
   public static final CompilerType UNKNOWN = register(0, "UNKNOWN");
   public static final CompilerType GCC = register(100, "GCC");
   public static final CompilerType MSVC = register(200, "MSVC");
   public static final int builtinCount = map.size();

   protected CompilerType(int var1, String var2) {
      super(var1, var2);
   }

   @Override
   public int ordinal() {
      return ordinal(map, this);
   }

   public static int count() {
      return map.size();
   }

   public static Collection values() {
      return values(map);
   }

   public static CompilerType valueOf(String var0) {
      return (CompilerType)valueOf(map, var0, UNKNOWN);
   }

   public static CompilerType valueOf(int var0) {
      return (CompilerType)valueOf(map, var0, UNKNOWN);
   }

   public static CompilerType register(int var0, String var1) {
      return (CompilerType)register(map, new CompilerType(var0, var1));
   }

   public static boolean unregister(String var0) {
      return unregister(map, builtinCount, var0);
   }
}
