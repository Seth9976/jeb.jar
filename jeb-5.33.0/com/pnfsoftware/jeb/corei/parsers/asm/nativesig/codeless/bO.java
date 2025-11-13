package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ModuleId;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;

public class bO {
   private static final ILogger pC = GlobalLog.getLogger(bO.class);

   void pC(Ws var1, String var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append(Strings.ff("#funcs: %d", var1.pC().size()));
      var3.append(Strings.LINESEP);
      var3.append("-----------------");
      var3.append(Strings.LINESEP);

      for (ModuleId var5 : var1.wS.keySet()) {
         var3.append(Strings.ff("%d -> %s", var1.wS.get(var5).size(), var5.getFileName()));
         var3.append(Strings.LINESEP);
      }

      try {
         File var7 = new File(var2, "stats.txt");
         IO.writeFile(var7, var3.toString());
      } catch (IOException var6) {
         pC.catching(var6);
      }
   }
}
