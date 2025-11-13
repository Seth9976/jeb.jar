package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class cjo {
   private static final ILogger pC = GlobalLog.getLogger(cjo.class);

   public static boolean pC(auu var0, String var1) {
      String var2 = var0.getName(true);
      if (var2.equals(var1)) {
         return true;
      } else {
         if (var2.contains(".dll!")) {
            if (var2.endsWith(var1)) {
               return true;
            }
         } else if (var2.equals("→" + var1.substring(1))) {
            return true;
         }

         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE var3 = var0.z();
         return var3 != null && var3.pC(var1);
      }
   }

   public static boolean pC(INativeCodeAnalyzer var0, long var1) {
      return var0.getAnalysisRanges().contains(var1) && !(var0.getModel().getItemAt(var1) instanceof INativeInstructionItem)
         ? var0.enqueuePointerForAnalysis(var0.getProcessor().createEntryPoint(var1), 1, 1)
         : false;
   }

   public static boolean A(INativeCodeAnalyzer var0, long var1) {
      return var0.getAnalysisRanges().contains(var1) && !(var0.getModel().getItemAt(var1) instanceof INativeInstructionItem)
         ? var0.enqueuePointerForAnalysis(var0.getProcessor().createEntryPoint(var1), 1)
         : false;
   }

   public static boolean kS(INativeCodeAnalyzer var0, long var1) {
      return var0.getAnalysisRanges().contains(var1) && ((Tw)var0.getModel()).E(var1) == null
         ? var0.enqueuePointerForAnalysis(var0.getProcessor().createEntryPoint(var1), 1)
         : false;
   }

   public static boolean wS(INativeCodeAnalyzer var0, long var1) {
      MemoryRanges var3 = ((a)var0).pC() != null ? ((a)var0).pC() : var0.getAnalysisRanges();
      return var3 == null || var3.contains(var1);
   }

   public static void pC(String var0) {
      JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException(var0));
   }

   public static void pC(INativeCodeAnalyzer var0, auu var1) {
      abv var2 = var1.EX();
      if (var2 != null && !var2.kS()) {
         String var3 = var2.A().pC() ? "try{" : "__try{";
         String var4 = var2.A().pC() ? "catch" : "__except";
         String var5 = var2.A().pC() ? "unwinder" : "__finally";
         HashMap var6 = new HashMap();

         for (abx var8 : var2.pC()) {
            long var9 = var8.pC();
            long var11 = var8.A();
            StringBuilder var13 = new StringBuilder();

            for (abu var15 : var8.kS()) {
               if (var15.A()) {
                  var0.recordAnalysisComment(var15.pC(), Strings.ff("%s (for %Xh)", var5, var9));
                  Strings.ff(var13, "%s:%Xh", var5, var15.pC());
               } else {
                  var0.recordAnalysisComment(var15.pC(), Strings.ff("%s(%s) (for %Xh)", var4, ((abt)var15.kS().get(0)).pC(), var9));
                  Strings.ff(var13, "%s%s:%Xh", var13.length() != 0 ? "," : "", var4, var15.pC());
               }
            }

            Object var16 = (Set)var6.get(var9);
            String var17 = var13.toString();
            if (var16 == null || var16.isEmpty() || !var16.contains(var17)) {
               var0.recordAnalysisComment(var9, Strings.ff("%s (see %s)", var3, var17));
            }

            if (var16 == null) {
               var16 = new HashSet();
               var6.put(var9, var16);
            }

            var16.add(var17);
            var0.recordAnalysisComment(var11, Strings.ff("} (starts at %Xh)", var9));
         }
      }
   }
}
