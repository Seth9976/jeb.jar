package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Av implements IPEDataDirectory {
   public static final String[] pC = new String[]{
      ".edata", ".idata", ".rsrc", ".excpt", ".cert", ".reloc", ".debug", ".arch", ".glob", ".tls", ".load", ".bound", ".iat", ".delay", ".cli"
   };
   public static final String[] A = new String[]{
      "Export Table",
      "Import Table",
      "Resources",
      "Exceptions",
      "Certificates",
      "Base Relocations",
      "Debug Information",
      "Architecture Spec.",
      "Global Pointer",
      "Thread Local Storage",
      "Load Configuration",
      "Bound Imports",
      "Import Address Table",
      "Delayed Imports",
      "CLI Descriptor"
   };
   @SerId(1)
   public long kS;
   @SerId(2)
   public long wS;

   public static com.pnfsoftware.jeb.corei.parsers.winpe.Av.Av pC(String var0) {
      for (com.pnfsoftware.jeb.corei.parsers.winpe.Av.Av var4 : com.pnfsoftware.jeb.corei.parsers.winpe.Av.Av.values()) {
         if (var4.wS.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public long getPosition() {
      return this.kS;
   }

   @Override
   public long getSize() {
      return this.wS;
   }

   static {
      Assert.a(pC.length == A.length);
   }

   public static enum Av {
      pC(5, ".reloc"),
      A(2, ".rsrc");

      final int kS;
      final String wS;

      private Av(int var3, String var4) {
         this.kS = var3;
         this.wS = var4;
      }
   }
}
