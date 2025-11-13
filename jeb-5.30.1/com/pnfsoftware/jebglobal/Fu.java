package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Fu {
   private static final ILogger RF = GlobalLog.getLogger(Fu.class);
   List q = new ArrayList();

   public static Fu q(ByteBuffer var0, boolean var1, long var2, long var4) {
      Fu var6 = new Fu();

      try {
         for (long var7 = 0L; var7 < var4; var7 += 8L) {
            int var9 = var0.getInt();
            if (var9 == 0 || (var9 & -2147483648) != 0) {
               break;
            }

            long var10 = q(var9, var2 + var7);
            Fu.eo var12 = new Fu.eo();
            var12.q = var10;
            var12.RF = var0.getInt();
            var6.q.add(var12);
         }

         return var6;
      } catch (BufferUnderflowException var13) {
         return null;
      }
   }

   public List q() {
      return this.q;
   }

   private static long q(int var0, long var1) {
      long var3 = MathUtil.signExtend32(var0 & 4294967295L, 31);
      return var3 + var1;
   }

   public static class eo {
      public long q;
      public int RF;
   }
}
