package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.base.DynamicEnum;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collection;
import java.util.LinkedHashMap;

@Ser
public class ProcessorType extends DynamicEnum {
   protected static LinkedHashMap map = new LinkedHashMap();
   public static final ProcessorType UNKNOWN = register(0, "UNKNOWN");
   public static final ProcessorType X86 = register(3, "X86", ProcessorFamily.X86);
   public static final ProcessorType X86_64 = register(62, "X86_64", ProcessorFamily.X86);
   public static final ProcessorType ARM = register(40, "ARM", ProcessorFamily.ARM);
   public static final ProcessorType ARM64 = register(183, "ARM64", ProcessorFamily.ARM);
   public static final ProcessorType MIPS = register(8, "MIPS", ProcessorFamily.MIPS);
   public static final ProcessorType MIPS64 = register(61440, "MIPS64", ProcessorFamily.MIPS);
   public static final ProcessorType AVR = register(83, "AVR");
   public static final ProcessorType AVR32 = register(185, "AVR32");
   public static final ProcessorType PNFDMY1 = register(61541, "PNFDMY1");
   public static final ProcessorType PNFDMY2 = register(61542, "PNFDMY2");
   public static final int builtinCount = map.size();
   @SerTransient
   private ProcessorFamily family;

   protected ProcessorType(int var1, String var2, ProcessorFamily var3) {
      super(var1, var2);
      if (var3 == null) {
         var3 = ProcessorFamily.UNKNOWN;
      }

      this.family = var3;
   }

   protected ProcessorType(int var1, String var2) {
      this(var1, var2, null);
   }

   public ProcessorFamily getFamily() {
      return this.family;
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

   public static ProcessorType valueOf(String var0) {
      return (ProcessorType)valueOf(map, var0, UNKNOWN);
   }

   public static ProcessorType valueOf(int var0) {
      return (ProcessorType)valueOf(map, var0, UNKNOWN);
   }

   static ProcessorType register(int var0, String var1) {
      return register(var0, var1, null);
   }

   public static ProcessorType register(int var0, String var1, ProcessorFamily var2) {
      return (ProcessorType)register(map, new ProcessorType(var0, var1, var2));
   }

   public static boolean unregister(String var0) {
      return unregister(map, builtinCount, var0);
   }

   public boolean isIntel() {
      return this.family == ProcessorFamily.X86;
   }

   public boolean isARM() {
      return this.family == ProcessorFamily.ARM;
   }

   public boolean isMIPS() {
      return this.family == ProcessorFamily.MIPS;
   }

   public boolean isAVR() {
      return this.family == ProcessorFamily.AVR;
   }

   public boolean is64Bit() {
      return this == X86_64 || this == ARM64 || this == MIPS64;
   }
}
