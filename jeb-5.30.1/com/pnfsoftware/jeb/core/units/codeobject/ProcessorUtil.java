package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class ProcessorUtil {
   private static final Map m = new HashMap();

   public static String toWellKnownUnitType(ProcessorType var0) {
      return (String)m.get(var0);
   }

   public static ProcessorType fromArchName(String var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Provide a non-null architecture string");
      } else {
         var0 = Strings.trim(var0).toLowerCase();
         String var1 = var0.toLowerCase();
         switch (var1) {
            case "arm":
               return ProcessorType.ARM;
            case "aarch64":
            case "arm64":
               return ProcessorType.ARM64;
            case "i386":
            case "x86":
               return ProcessorType.X86;
            case "amd64":
            case "x64":
            case "x86-64":
            case "x86_64":
            case "i386:x86-64":
               return ProcessorType.X86_64;
            case "mips":
               return ProcessorType.MIPS;
            case "mips64":
               return ProcessorType.MIPS64;
            case "avr":
               return ProcessorType.AVR;
            case "avr32":
               return ProcessorType.AVR32;
            default:
               return ProcessorType.valueOf(var0);
         }
      }
   }

   static {
      m.put(ProcessorType.ARM, "arm");
      m.put(ProcessorType.ARM64, "arm64");
      m.put(ProcessorType.X86, "x86");
      m.put(ProcessorType.X86_64, "x86_64");
      m.put(ProcessorType.MIPS, "mips");
      m.put(ProcessorType.MIPS64, "mips64");
      m.put(ProcessorType.AVR, "avr");
      m.put(ProcessorType.AVR32, "avr");
   }
}
