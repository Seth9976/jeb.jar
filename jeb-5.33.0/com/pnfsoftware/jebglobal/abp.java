package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class abp implements abn {
   private static final ILogger A = GlobalLog.getLogger(abp.class);
   protected final INativeCodeAnalyzer pC;
   private final int[] kS;

   public abp(INativeCodeAnalyzer var1, int... var2) {
      this.pC = var1;
      this.kS = var2;
   }

   @Override
   public List A(long var1) {
      for (int var6 : this.kS) {
         int var7 = 0;
         long var8 = var1;
         Long var10 = null;

         while (true) {
            Long var11 = VirtualMemoryUtil.readAsLongSafe(this.pC.getMemory(), var8, var6);
            if (var11 == null) {
               break;
            }

            if (var10 == null) {
               var10 = var11;
            } else if (!var10.equals(var11)) {
               break;
            }

            if (this.pC(var8, var6)) {
               break;
            }

            var7++;
            var8 += var6;
         }

         if (var7 > 2) {
            Object[] var10000 = new Object[]{var1};
            ArrayList var13 = new ArrayList();
            if (var10 != 0L) {
               for (int var12 = 0; var12 < var7; var12++) {
                  var13.add(new Pointer(var1 + var12 * var6, var6, 2));
               }
            }

            return var13;
         }
      }

      return null;
   }

   protected boolean pC(long var1, int var3) {
      return false;
   }
}
