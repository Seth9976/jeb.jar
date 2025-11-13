package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class adh implements adf {
   private static final ILogger RF = GlobalLog.getLogger(adh.class);
   private static final int xK = 2;
   protected final INativeCodeAnalyzer q;
   private final int[] Dw;

   public adh(INativeCodeAnalyzer var1) {
      this(var1, 4);
   }

   public adh(INativeCodeAnalyzer var1, int... var2) {
      this.q = var1;
      this.Dw = var2;
   }

   @Override
   public List RF(long var1) {
      for (int var6 : this.Dw) {
         int var7 = 0;
         long var8 = var1;
         Long var10 = null;

         while (true) {
            Long var11 = VirtualMemoryUtil.readAsLongSafe(this.q.getMemory(), var8, var6);
            if (var11 == null) {
               break;
            }

            if (var10 == null) {
               var10 = var11;
            } else if (!var10.equals(var11)) {
               break;
            }

            if (this.q(var8, var6)) {
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

   protected boolean q(long var1, int var3) {
      return false;
   }
}
