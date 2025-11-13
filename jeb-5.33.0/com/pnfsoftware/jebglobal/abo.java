package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class abo implements abn {
   private static final ILogger A = GlobalLog.getLogger(abo.class);
   protected final INativeCodeAnalyzer pC;
   private final int[] kS;

   public abo(INativeCodeAnalyzer var1, int... var2) {
      this.pC = var1;
      this.kS = var2;
   }

   @Override
   public List A(long var1) {
      for (int var6 : this.kS) {
         int var7 = 0;
         long var8 = (1 << 8 * (var6 >>> 1)) - 1;
         long var10 = var1;

         while (true) {
            Long var12 = VirtualMemoryUtil.readAsLongSafe(this.pC.getMemory(), var10, var6);
            if (var12 == null || var12 == 0L) {
               break;
            }

            long var13 = var12 >>> 8 * (var6 >>> 1);
            if (var13 != 0L && var13 != var8 || this.pC(var10, var6)) {
               break;
            }

            var7++;
            var10 += var6;
         }

         if (var7 > 2) {
            Object[] var10000 = new Object[]{var1};
            ArrayList var15 = new ArrayList();

            for (int var16 = 0; var16 < var7; var16++) {
               var15.add(new Pointer(var1 + var16 * var6, var6, 2));
            }

            return var15;
         }
      }

      return null;
   }

   protected boolean pC(long var1, int var3) {
      return false;
   }
}
