package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class acj {
   private static final StructuredLogger pC = aco.pC(acj.class);

   public static void pC() {
      if (Thread.interrupted()) {
         throw new InterruptionException("The decompilation was interrupted");
      }
   }

   public static void pC(Throwable var0) {
      pC(var0, (Map)null);
   }

   public static void pC(Throwable var0, IERoutineContext var1) {
      if (var1 == null) {
         pC(var0, (Map)null);
      } else {
         pC(var0, var1.getNativeContext(), var1.getRoutine().getAddress(false));
      }
   }

   public static void pC(Throwable var0, ICMethod var1) {
      if (var1 == null) {
         pC(var0, (Map)null);
      } else {
         pC(var0, null, var1.getAddress());
      }
   }

   public static void pC(Throwable var0, INativeContext var1, String var2) {
      HashMap var3 = new HashMap();
      if (var1 instanceof IUnit var4) {
         String var5 = UnitUtil.buildFullyQualifiedUnitPath(var4);
         var3.put("unit-path", var5);
      }

      if (var2 != null) {
         var3.put("eltsig", var2);
      }

      pC(var0, var3);
   }

   public static void pC(Throwable var0, Map var1) {
      JebCoreService.notifySilentExceptionToClient(var0, var1);
   }

   public static boolean pC(IERoutineContext var0, String var1, Object... var2) {
      return pC(true, 0, var0.getCfg(), var0, var1, var2);
   }

   public static boolean pC(boolean var0, int var1, CFG var2, IERoutineContext var3, String var4, Object... var5) {
      return false;
   }

   public static int pC(IERoutineContext var0, IEMasterOptimizer var1, OptimizerMode var2, String var3, StructuredLogger var4, String var5) {
      System.currentTimeMillis();

      int var6;
      try {
         OptimizerMode var7 = null;
         if (var2 != null) {
            var7 = var1.setMode(var2);
         }

         if (var5 == null) {
            var5 = "Performing round of generic optimizations";
         }

         String var8 = Strings.ff("%s (%s)", var5, var1.getMode());
         var4.beginSection(var8);

         try {
            if (var3 != null) {
               List var9 = pC(var1, var3);
               var6 = var1.performMultiple(var9);
            } else {
               var6 = var1.perform();
            }
         } finally {
            if (var7 != null) {
               var1.setMode(var7);
            }
         }
      } finally {
         var4.closeSection();
      }

      return var6;
   }

   static List pC(IMasterOptimizer var0, String var1) {
      ArrayList var2 = new ArrayList();
      List var3 = var0.getRegisteredOptimizers();
      if (var3 != null) {
         for (OptimizerEntry var5 : var3) {
            IOptimizer var6 = var5.getOptimizer();
            if (var5.isEnabled() && var6.getTags().contains(var1)) {
               var2.add(var5);
            }
         }
      }

      return var2;
   }

   public static String pC(long var0) {
      return acp.A ? "N/A" : TimeFormatter.formatExecutionTime(var0);
   }
}
