package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class jL {
   private Map q = new LinkedHashMap();

   public static jL q(oH var0) throws IOException {
      try {
         String var1 = var0.xK("qHostInfo");
         jL var2 = new jL();
         var2.q = MO.Uv(var1);
         return var2;
      } catch (WI var3) {
         return null;
      }
   }

   public boolean q() {
      return this.q.isEmpty();
   }

   public String q(String var1) {
      return (String)this.q.get(var1);
   }

   public long RF() {
      return Conversion.stringToLong((String)this.q.get("cputype"), 0L, 10);
   }

   public long xK() {
      return Conversion.stringToLong((String)this.q.get("cpusubtype"), 0L, 10);
   }

   public String Dw() {
      return MO.gO((String)this.q.get("triple"));
   }

   public Endianness Uv() {
      String var1 = (String)this.q.get("endian");
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

   public int oW() {
      return Conversion.stringToInt((String)this.q.get("ptrsize"), 0, 10);
   }

   public String gO() {
      return MO.gO((String)this.q.get("hostname"));
   }

   public String nf() {
      return MO.gO((String)this.q.get("os_build"));
   }

   public String gP() {
      return MO.gO((String)this.q.get("os_kernel"));
   }

   public String za() {
      return MO.gO((String)this.q.get("os_version"));
   }

   public ProcessorType lm() {
      String var1 = this.Dw();
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
      return this.q.toString();
   }
}
