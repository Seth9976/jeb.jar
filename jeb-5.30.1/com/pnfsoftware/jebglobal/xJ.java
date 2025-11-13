package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAdvancedAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Ser
public class xJ implements INativeCodeAdvancedAnalyzer {
   private static final ILogger Dw = GlobalLog.getLogger(xJ.class);
   private static final boolean Uv = false;
   @SerId(1)
   abg q;
   @SerTransient
   volatile INativeDecompilerUnit RF;
   @SerTransient
   volatile boolean xK;

   public xJ(abg var1) {
      this.q = var1;
   }

   @Override
   public void perform() {
      List var1 = this.q.getInternalMethods();
      this.analyzeRoutines(var1);
   }

   @Override
   public void analyzeRoutines(Collection var1) {
      ArrayList var2 = new ArrayList();

      for (INativeMethodItem var4 : var1) {
         if (var4.getData() != null) {
            var2.add((axo)var4.getData());
         }
      }

      this.analyzeInternalRoutines(var2);
   }

   @Override
   public void analyzeInternalRoutines(Collection var1) {
      this.RF = (INativeDecompilerUnit)DecompilerHelper.getDecompiler(this.q, true);
      if (this.RF != null) {
         try (ACLock var2 = this.q.q().a()) {
            long var3 = System.currentTimeMillis();
            int var5 = 0;
            Iterator var6 = this.q(var1);

            while (var6.hasNext()) {
               if (this.xK) {
                  Dw.status(S.L("Advanced analysis paused"));
                  return;
               }

               axo var7 = (axo)var6.next();
               if (var7.isDisposed()) {
                  var5++;
               } else {
                  if (!this.q(var7.xK(), var5, var1.size())) {
                     Dw.status(S.L("Advanced analysis interrupted on routine %Xh"), var7.getMemoryAddress());
                     return;
                  }

                  var5++;
               }
            }

            Dw.status(S.L("Advanced analysis completed (%s)"), TimeFormatter.formatTimestampDelta(System.currentTimeMillis() - var3));
         } finally {
            this.xK = false;
            this.RF.runGarbageCollection();
         }
      }
   }

   private boolean q(axp var1, int var2, int var3) {
      if (var1 != null && var1.oW() != null) {
         Integer var4 = (Integer)var1.oW().getAttribute("analysisStatus", Integer.class);
         if (var4 != null) {
            return true;
         } else {
            if (var2 >= 0 && var3 > 0) {
               int var5 = var2 * 100 / var3;
               Dw.status(S.L("Advanced analysis of routine %d/%d: \"%s\"... (%d%%)"), var2 + 1, var3, var1.getName(true), var5);
            }

            label84: {
               try {
                  var1.oW().setAttribute("analysisStatus", 0);
                  this.q(var1);
                  break label84;
               } catch (Exception var11) {
                  if (var1.oW() != null && !var1.oW().isDisposed()) {
                     var1.oW().setAttribute("analysisStatus", -1);
                  }

                  Dw.debug(S.L("Advanced analysis failed on method %s"), var1);
                  Throwable var6 = var11.getCause();
                  if (var6 instanceof UnsupportedConversionException) {
                     Dw.debug(S.L("Cannot convert instruction: %s"), var6.getMessage());
                  } else {
                     Dw.catchingSilent(var11);
                  }

                  if (var11 instanceof InterruptionException) {
                     return false;
                  }
               } finally {
                  ;
               }

               return true;
            }

            if (var1.oW() != null && !var1.oW().isDisposed()) {
               var1.oW().setAttribute("analysisStatus", 1);
               var1.q(Boolean.valueOf(true));
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private IDecompiledMethod q(axp var1) {
      NativeDecompilationStage var2 = (NativeDecompilationStage)this.q.getCodeAnalyzerExtensionsManager().getPreferredAdvancedAnalysisStage(var1).getResult();
      DecompilationContext var3 = new DecompilationContext(0, 5000L, null);
      if (var2 == null) {
         var2 = NativeDecompilationStage.SIMULATION;
      }

      return this.RF.decompileMethodEx(var1, var3, var2);
   }

   private Iterator q(Collection var1) {
      return new aaq((aaf)this.q.getCodeModel(), var1);
   }
}
