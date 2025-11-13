package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Q {
   private Map pC = new LinkedHashMap();

   public static Q pC(aI var0) throws IOException {
      try {
         String var1 = var0.kS("qHostInfo");
         Q var2 = new Q();
         var2.pC = zI.UT(var1);
         return var2;
      } catch (Ae var3) {
         return null;
      }
   }

   public boolean pC() {
      return this.pC.isEmpty();
   }

   public String A() {
      return zI.sY((String)this.pC.get("triple"));
   }

   public Endianness kS() {
      String var1 = (String)this.pC.get("endian");
      if (var1 != null) {
         switch (var1) {
            case "little":
               return Endianness.LITTLE_ENDIAN;
            case "big":
               return Endianness.BIG_ENDIAN;
            case "pdp":
               return Endianness.MIDDLE_ENDIAN;
         }
      }

      return null;
   }

   public ProcessorType wS() {
      String var1 = this.A();
      if (var1 == null) {
         return null;
      } else if (var1.startsWith("x86-") || var1.startsWith("i386-")) {
         return ProcessorType.X86;
      } else if (var1.startsWith("x86_64-")) {
         return ProcessorType.X86_64;
      } else if (var1.startsWith("arm-")) {
         return ProcessorType.ARM;
      } else if (var1.startsWith("aarch64-")) {
         return ProcessorType.ARM64;
      } else if (var1.startsWith("mips-")) {
         return ProcessorType.MIPS;
      } else {
         return var1.startsWith("mips64-") ? ProcessorType.MIPS64 : ProcessorType.UNKNOWN;
      }
   }

   @Override
   public String toString() {
      return this.pC.toString();
   }
}
