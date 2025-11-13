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

public class cub {
   private static final ILogger q = GlobalLog.getLogger(cub.class);

   public static boolean q(axp var0, String var1) {
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

         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL var3 = var0.wF();
         return var3 != null && var3.q(var1);
      }
   }

   public static boolean q(INativeCodeAnalyzer var0, long var1) {
      return var0.getAnalysisRanges().contains(var1) && !(var0.getModel().getItemAt(var1) instanceof INativeInstructionItem)
         ? var0.enqueuePointerForAnalysis(var0.getProcessor().createEntryPoint(var1), 1, 1)
         : false;
   }

   public static boolean RF(INativeCodeAnalyzer var0, long var1) {
      return var0.getAnalysisRanges().contains(var1) && !(var0.getModel().getItemAt(var1) instanceof INativeInstructionItem)
         ? var0.enqueuePointerForAnalysis(var0.getProcessor().createEntryPoint(var1), 1)
         : false;
   }

   public static boolean xK(INativeCodeAnalyzer var0, long var1) {
      return var0.getAnalysisRanges().contains(var1) && ((aaf)var0.getModel()).oW(var1) == null
         ? var0.enqueuePointerForAnalysis(var0.getProcessor().createEntryPoint(var1), 1)
         : false;
   }

   public static boolean Dw(INativeCodeAnalyzer var0, long var1) {
      MemoryRanges var3 = ((aae)var0).q() != null ? ((aae)var0).q() : var0.getAnalysisRanges();
      return var3 == null || var3.contains(var1);
   }

   public static void q(String var0) {
      JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException(var0));
   }

   public static void q(INativeCodeAnalyzer var0, axp var1) {
      adn var2 = var1.TX();
      if (var2 != null && !var2.xK()) {
         String var3 = var2.RF().RF() ? "try{" : "__try{";
         String var4 = var2.RF().RF() ? "catch" : "__except";
         String var5 = var2.RF().RF() ? "unwinder" : "__finally";
         HashMap var6 = new HashMap();

         for (adp var8 : var2.q()) {
            long var9 = var8.q();
            long var11 = var8.RF();
            StringBuilder var13 = new StringBuilder();

            for (adm var15 : var8.xK()) {
               if (var15.RF()) {
                  var0.recordAnalysisComment(var15.q(), Strings.ff("%s (for %Xh)", var5, var9));
                  Strings.ff(var13, "%s:%Xh", var5, var15.q());
               } else {
                  var0.recordAnalysisComment(var15.q(), Strings.ff("%s(%s) (for %Xh)", var4, ((adl)var15.xK().get(0)).q(), var9));
                  Strings.ff(var13, "%s%s:%Xh", var13.length() != 0 ? "," : "", var4, var15.q());
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
