package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Func;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class cq {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);

   public void pC(Ws var1, String var2, Long var3, Long var4) {
      Set var5 = var1.pC();
      int var6 = 0;
      HashMap var7 = new HashMap();

      for (Func var9 : var5) {
         if (var3 == null || var4 == null || var9.getLowestAddress() <= var4 && var9.getLowestAddress() >= var3) {
            var7.put(var9, var6);
            var6++;
         }
      }

      Object[] var10000 = new Object[]{var7.size()};
      this.pC(var1, var2, var7);
      StringBuilder var15 = new StringBuilder();
      var15.append("Id,Label,timeset,longitude,latitude");
      var15.append(Strings.LINESEP);
      double var16 = 90.0;
      ArrayList var11 = new ArrayList(var7.keySet());
      Collections.sort(var11, (var0, var1x) -> Long.compare(var0.getLowestAddress(), var1x.getLowestAddress()));

      for (Func var13 : var11) {
         var15.append(String.format("%d, %s,,0,%.2f", var7.get(var13), var13.getName(), var16));
         var15.append(Strings.LINESEP);
         var16 -= 0.03;
      }

      try {
         File var17 = new File(var2, "nodes.csv");
         IO.writeFile(var17, var15.toString());
      } catch (IOException var14) {
         pC.catching(var14);
      }
   }

   public void pC(Ws var1, String var2) {
      File var3 = new File(var2, "nodes.csv");
      StringBuilder var4 = new StringBuilder();
      var4.append("Id,Label,timeset,module");
      var4.append(Strings.LINESEP);
      Set var5 = var1.pC();
      HashMap var6 = new HashMap();
      int var7 = 0;

      for (Func var9 : var5) {
         var6.put(var9, var7);
         var4.append(String.format("%d, %s,,%s", var7, var9.getName(), var9.getModuleId().getFileName()));
         var4.append(Strings.LINESEP);
         var7++;
      }

      try {
         IO.writeFile(var3, var4.toString());
      } catch (IOException var10) {
         pC.catching(var10);
      }

      this.pC(var1, var2, var6);
   }

   public void A(Ws var1, String var2) {
      File var3 = new File(var2, "nodes.csv");
      StringBuilder var4 = new StringBuilder();
      var4.append("Id,Label,timeset,module");
      var4.append(Strings.LINESEP);
      Set var5 = var1.pC();
      HashMap var6 = new HashMap();
      int var7 = 0;

      for (Func var9 : var5) {
         var6.put(var9, var7);
         var4.append(String.format("%d, %s,,%s", var7, var9.getName(), var9.getModuleId().getFileName()));
         var4.append(Strings.LINESEP);
         var7++;
      }

      HashMap var12 = new HashMap();

      for (rQ var10 : var1.A.keySet()) {
         var12.put(var10, var7);
         var4.append(String.format("%d, %s,,%s", var7, var10.kS(), "FEATURE"));
         var4.append(Strings.LINESEP);
         var7++;
      }

      try {
         IO.writeFile(var3, var4.toString());
      } catch (IOException var11) {
         pC.catching(var11);
      }

      this.pC(var1, var2, var6, var12);
   }

   private void pC(Ws var1, String var2, Map var3, Map var4) {
      File var5 = new File(var2, "edges.csv");
      StringBuilder var6 = new StringBuilder();
      var6.append("Source,Target,Type,Id,Label,timeset,Weight");
      var6.append(Strings.LINESEP);
      int var7 = 0;

      for (Func var9 : var3.keySet()) {
         Integer var10 = (Integer)var3.get(var9);
         Set var11 = var1.kS.get(var9);
         if (var11 != null && !var11.isEmpty()) {
            for (rQ var13 : var11) {
               int var14 = (Integer)var4.get(var13);
               var6.append(String.format("%d,%d,Directed,%d,,,1", var10, var14, var7));
               var6.append(Strings.LINESEP);
               var7++;
            }
         }
      }

      try {
         IO.writeFile(var5, var6.toString());
      } catch (IOException var15) {
         pC.catching(var15);
      }
   }

   private void pC(Ws var1, String var2, Map var3) {
      File var4 = new File(var2, "edges.csv");
      StringBuilder var5 = new StringBuilder();
      var5.append("Source,Target,Type,Id,Label,timeset,Weight");
      var5.append(Strings.LINESEP);
      int var6 = (Integer)Collections.max(var3.values()) + 1;
      int var7 = 0;
      HashMap var8 = new HashMap();

      for (Func var10 : var3.keySet()) {
         Integer var11 = (Integer)var3.get(var10);
         Set var12 = var1.UT.get(var10);
         if (var12 != null) {
            for (Func var14 : var12) {
               Integer var15 = (Integer)var3.get(var14);
               var15 = var15 == null ? (Integer)var8.get(var14) : var15;
               if (var15 == null) {
                  var15 = var6;
                  var8.put(var14, var15);
                  var6++;
               }

               var5.append(String.format("%d,%d,Directed,%d,,,1", var11, var15, var7));
               var5.append(Strings.LINESEP);
               var7++;
            }
         }

         Set var18 = var1.E.get(var10);
         if (var18 != null) {
            for (Func var21 : var18) {
               Integer var16 = (Integer)var3.get(var21);
               var16 = var16 == null ? (Integer)var8.get(var21) : var16;
               if (var16 == null) {
                  var16 = var6;
                  var8.put(var21, var16);
                  var6++;
               }

               var5.append(String.format("%d,%d,Directed,%d,,,1", var16, var11, var7));
               var5.append(Strings.LINESEP);
               var7++;
            }
         }
      }

      Object[] var10000 = new Object[]{var8.size()};
      var3.putAll(var8);

      try {
         IO.writeFile(var4, var5.toString());
      } catch (IOException var17) {
         pC.catching(var17);
      }
   }
}
