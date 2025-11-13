package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeg;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class PY {
   private static final StructuredLogger RF = aeg.q(PY.class);
   List q;

   public PY(List var1) {
      this.q = new ArrayList(var1);
   }

   public void q(File var1) {
      TreeMap var2 = new TreeMap(Collections.reverseOrder());

      for (EE var4 : this.q) {
         for (EE.eo var6 : var4.xK) {
            long var7 = var6.Dw;
            Object var9 = (List)var2.get(var7);
            if (var9 == null) {
               var9 = new ArrayList();
               var2.put(var7, var9);
            }

            var9.add(var6.RF);
         }
      }

      StringBuilder var11 = new StringBuilder();

      for (Entry var13 : var2.entrySet()) {
         Strings.ff(var11, "[%d ms] %s%n", var13.getKey(), var13.getValue());
      }

      try {
         IO.writeFile(var1, var11.toString());
      } catch (IOException var10) {
         RF.error("error when writing logs");
      }
   }
}
