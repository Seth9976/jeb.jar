package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jebglobal.aco;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class bO {
   private static final StructuredLogger A = aco.pC(bO.class);
   List pC;

   public bO(List var1) {
      this.pC = var1;
   }

   public void pC(File var1) {
      TreeMap var2 = new TreeMap(Collections.reverseOrder());
      ArrayList var3 = new ArrayList();

      for (cq var5 : this.pC) {
         Integer var6 = var5.kS.size();
         var3.add(var6);
         Long var7 = (Long)var2.get(var6);
         if (var7 == null) {
            var7 = 0L;
         }

         var2.put(var6, var7 + 1L);
      }

      if (!var3.isEmpty()) {
         double var10 = MathUtil.avg(var3);
         StringBuilder var11 = new StringBuilder();
         var11.append("#pipeline logs:");
         var11.append(this.pC.size());
         var11.append(Strings.LINESEP);
         Strings.ff(var11, "size average = %f", var10);
         var11.append(Strings.LINESEP);

         for (Entry var8 : var2.entrySet()) {
            Strings.ff(var11, "size=%d -> instances=%d%n", var8.getKey(), var8.getValue());
         }

         try {
            IO.writeFile(var1, var11.toString());
         } catch (IOException var9) {
            A.error("error when writing logs");
         }
      }
   }
}
