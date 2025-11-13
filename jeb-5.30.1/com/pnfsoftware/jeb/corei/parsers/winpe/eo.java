package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class eo implements IPEDataDirectory {
   public static final String[] q = new String[]{
      ".edata", ".idata", ".rsrc", ".excpt", ".cert", ".reloc", ".debug", ".arch", ".glob", ".tls", ".load", ".bound", ".iat", ".delay", ".cli"
   };
   public static final String[] RF = new String[]{
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
   public long xK;
   @SerId(2)
   public long Dw;

   public static com.pnfsoftware.jeb.corei.parsers.winpe.eo.eo q(String var0) {
      for (com.pnfsoftware.jeb.corei.parsers.winpe.eo.eo var4 : com.pnfsoftware.jeb.corei.parsers.winpe.eo.eo.values()) {
         if (var4.Dw.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public long getPosition() {
      return this.xK;
   }

   @Override
   public long getSize() {
      return this.Dw;
   }

   static {
      Assert.a(q.length == RF.length);
   }

   public static enum eo {
      q(5, ".reloc"),
      RF(2, ".rsrc");

      final int xK;
      final String Dw;

      private eo(int var3, String var4) {
         this.xK = var3;
         this.Dw = var4;
      }
   }
}
