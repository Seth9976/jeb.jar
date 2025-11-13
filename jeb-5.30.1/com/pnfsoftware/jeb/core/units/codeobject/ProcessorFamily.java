package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.base.DynamicEnum;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.LinkedHashMap;

@Ser
public class ProcessorFamily extends DynamicEnum {
   protected static LinkedHashMap map = new LinkedHashMap();
   public static final ProcessorFamily UNKNOWN = register(0, "UNKNOWN");
   public static final ProcessorFamily X86 = register(1, "X86");
   public static final ProcessorFamily ARM = register(2, "ARM");
   public static final ProcessorFamily MIPS = register(3, "MIPS");
   public static final ProcessorFamily AVR = register(4, "AVR");
   public static final int builtinCount = map.size();

   protected ProcessorFamily(int var1, String var2) {
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

   public static ProcessorFamily valueOf(String var0) {
      return (ProcessorFamily)valueOf(map, var0, UNKNOWN);
   }

   public static ProcessorFamily valueOf(int var0) {
      return (ProcessorFamily)valueOf(map, var0, UNKNOWN);
   }

   public static ProcessorFamily register(int var0, String var1) {
      return (ProcessorFamily)register(map, new ProcessorFamily(var0, var1));
   }

   public static boolean unregister(String var0) {
      return unregister(map, builtinCount, var0);
   }
}
