package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class adg implements adf {
   private static final ILogger RF = GlobalLog.getLogger(adg.class);
   private static final int xK = 2;
   protected final INativeCodeAnalyzer q;
   private final int[] Dw;

   public adg(INativeCodeAnalyzer var1) {
      this(var1, 4);
   }

   public adg(INativeCodeAnalyzer var1, int... var2) {
      this.q = var1;
      this.Dw = var2;
   }

   @Override
   public List RF(long var1) {
      for (int var6 : this.Dw) {
         int var7 = 0;
         long var8 = (1 << 8 * (var6 >>> 1)) - 1;
         long var10 = var1;

         while (true) {
            Long var12 = VirtualMemoryUtil.readAsLongSafe(this.q.getMemory(), var10, var6);
            if (var12 == null || var12 == 0L) {
               break;
            }

            long var13 = var12 >>> 8 * (var6 >>> 1);
            if (var13 != 0L && var13 != var8 || this.q(var10, var6)) {
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

   protected boolean q(long var1, int var3) {
      return false;
   }
}
