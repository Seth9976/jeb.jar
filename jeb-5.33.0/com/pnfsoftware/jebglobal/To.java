package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.IOException;
import java.io.InputStream;

class To implements oi {
   To(ProcessorType var1) {
      this.pC = var1;
   }

   @Override
   public String pC(String var1) throws IOException, Ae {
      if (this.pC == null) {
         throw new Ae("The legacy register layout cannot be retrieved if the processor type is not specified");
      } else {
         String var2;
         if (this.pC == ProcessorType.X86) {
            var2 = "x86";
         } else if (this.pC == ProcessorType.X86_64) {
            var2 = "x8664";
         } else if (this.pC == ProcessorType.ARM) {
            var2 = "arm";
         } else if (this.pC == ProcessorType.ARM64) {
            var2 = "arm64";
         } else if (this.pC == ProcessorType.MIPS) {
            var2 = "mips";
         } else {
            if (this.pC != ProcessorType.MIPS64) {
               throw new IOException("No legacy register layout stored for processor " + this.pC);
            }

            var2 = "mips64";
         }

         InputStream var3 = AssetManager.getAsset("gdb/legacy-register-layouts/" + var2 + "/" + var1);
         return Strings.decodeASCII(IO.readInputStream(var3));
      }
   }
}
