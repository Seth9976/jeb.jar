package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.base.DynamicEnum;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.LinkedHashMap;

@Ser
public class CallingConventionName extends DynamicEnum {
   protected static LinkedHashMap map = new LinkedHashMap();
   public static final CallingConventionName UNKNOWN = register(0, "__unknown");
   public static final CallingConventionName CDECL = register(1, "__cdecl");
   public static final CallingConventionName STDCALL = register(2, "__stdcall");
   public static final CallingConventionName FASTCALL = register(3, "__fastcall");
   public static final CallingConventionName THISCALL = register(4, "__thiscall");
   public static final CallingConventionName VECTORCALL = register(5, "__vectorcall");
   public static final CallingConventionName ARM_A32 = register(6, "__a32");
   public static final CallingConventionName ARM_A64 = register(7, "aapcs64");
   public static final CallingConventionName MIPS_O32 = register(8, "__o32");
   public static final CallingConventionName MIPS_N32 = register(9, "__n32");
   public static final CallingConventionName MIPS_N64 = register(10, "__n64");
   public static final CallingConventionName SYSVAMD64 = register(11, "__sysv_abi");
   public static final CallingConventionName ARM_A32_HF = register(12, "__a32_hf");
   public static final int builtinCount = map.size();

   protected CallingConventionName(int var1, String var2) {
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

   public static CallingConventionName valueOf(String var0) {
      return (CallingConventionName)valueOf(map, var0, UNKNOWN);
   }

   public static CallingConventionName valueOf(int var0) {
      return (CallingConventionName)valueOf(map, var0, UNKNOWN);
   }

   public static CallingConventionName register(int var0, String var1) {
      return (CallingConventionName)register(map, new CallingConventionName(var0, var1));
   }

   public static boolean unregister(String var0) {
      return unregister(map, builtinCount, var0);
   }

   public static CallingConventionName find(String var0) {
      String var1 = Strings.ltrim(var0, '_').toLowerCase();

      for (String var3 : map.keySet()) {
         if (var1.equals(Strings.ltrim(var3, '_'))) {
            return (CallingConventionName)map.get(var3);
         }
      }

      return null;
   }
}
