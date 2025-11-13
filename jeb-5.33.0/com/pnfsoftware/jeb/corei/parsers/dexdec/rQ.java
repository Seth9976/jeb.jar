package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.DemoLimitationException;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecConversionException;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.bqp;
import java.util.LinkedHashMap;
import java.util.Map;

public class rQ {
   private static final ILogger pC = GlobalLog.getLogger(rQ.class);
   private static Boolean A;

   public static boolean pC() {
      if (A == null) {
         synchronized (rQ.class) {
            if (A == null) {
               boolean var1 = true;
               A = var1;
            }
         }
      }

      return A;
   }

   public static void pC(Throwable var0) {
      pC(var0, null);
   }

   public static void pC(Throwable var0, String var1) {
      pC(var0, var1, -1);
   }

   public static void pC(Throwable var0, String var1, int var2) {
      pC(var0, var1, var2, null);
   }

   public static void pC(Throwable var0, String var1, int var2, Map var3) {
      pC(var0, var1, var2, var3, false);
   }

   public static void pC(Throwable var0, String var1, int var2, Map var3, boolean var4) {
      if (var0 instanceof DexDecConversionException) {
         IDalvikInstruction var5 = ((DexDecConversionException)var0).getInstruction();
         if (var5 != null && var5.isOptimized()) {
            return;
         }

         if (var5 == null || bqp.pC(var5.getOpcode())) {
            return;
         }
      } else if (var0 instanceof DemoLimitationException) {
         return;
      }

      LinkedHashMap var6 = new LinkedHashMap();
      if (var1 != null) {
         var6.put("eltsig", var1);
      }

      if (var2 >= 0) {
         var6.put("eltlen", var2);
      }

      if (var3 != null) {
         var6.putAll(var3);
      }

      if (Licensing.isReleaseBuild()) {
         JebCoreService.notifySilentExceptionToClient(var0, var6);
      }
   }

   public static boolean pC(IDexDecompilerUnit var0, IDexItem var1) {
      return pC(var0, var1, null);
   }

   public static boolean pC(IDexDecompilerUnit var0, IDexItem var1, String var2) {
      return var0 != null && var1 != null ? ((Ws)var0).pC(var1, true, var2) : false;
   }

   public static String pC(long var0) {
      return Av.pC ? "N/A" : TimeFormatter.formatExecutionTime(var0);
   }
}
