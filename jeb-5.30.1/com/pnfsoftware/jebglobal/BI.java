package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.io.InputStream;

class BI implements nO {
   BI(ProcessorType var1) {
      this.q = var1;
   }

   @Override
   public String q(String var1) throws IOException, WI {
      if (this.q == null) {
         throw new WI("The legacy register layout cannot be retrieved if the processor type is not specified");
      } else {
         String var2;
         if (this.q == ProcessorType.X86) {
            var2 = "x86";
         } else if (this.q == ProcessorType.X86_64) {
            var2 = "x8664";
         } else if (this.q == ProcessorType.ARM) {
            var2 = "arm";
         } else if (this.q == ProcessorType.ARM64) {
            var2 = "arm64";
         } else if (this.q == ProcessorType.MIPS) {
            var2 = "mips";
         } else {
            if (this.q != ProcessorType.MIPS64) {
               throw new IOException("No legacy register layout stored for processor " + this.q);
            }

            var2 = "mips64";
         }

         InputStream var3 = AssetManager.getAsset("gdb/legacy-register-layouts/" + var2 + "/" + var1);
         return Strings.decodeASCII(com.pnfsoftware.jeb.util.io.IO.readInputStream(var3));
      }
   }
}
