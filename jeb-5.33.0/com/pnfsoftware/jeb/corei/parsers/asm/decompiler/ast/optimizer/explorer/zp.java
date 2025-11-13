package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aco;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class zp {
   private static final StructuredLogger A = aco.pC(zp.class);
   List pC;

   public zp(List var1) {
      this.pC = new ArrayList(var1);
   }

   public void pC(File var1) {
      TreeMap var2 = new TreeMap(Collections.reverseOrder());

      for (cq var4 : this.pC) {
         for (cq.Av var6 : var4.kS) {
            long var7 = var6.wS;
            Object var9 = (List)var2.get(var7);
            if (var9 == null) {
               var9 = new ArrayList();
               var2.put(var7, var9);
            }

            var9.add(var6.A);
         }
      }

      StringBuilder var11 = new StringBuilder();

      for (Entry var13 : var2.entrySet()) {
         Strings.ff(var11, "[%d ms] %s%n", var13.getKey(), var13.getValue());
      }

      try {
         IO.writeFile(var1, var11.toString());
      } catch (IOException var10) {
         A.error("error when writing logs");
      }
   }
}
