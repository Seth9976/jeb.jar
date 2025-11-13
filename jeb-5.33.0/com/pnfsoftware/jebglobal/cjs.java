package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.nio.ByteBuffer;

public class cjs {
   long pC;
   private long kS;
   private long wS;
   cjx A;

   public static cjs pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      cjs var9 = new cjs();
      var9.pC = var3 + (var6.readInt(var1) & 4294967295L);
      long var7 = var1 + 4L;
      var9.kS = var3 + (var6.readInt(var7) & 4294967295L);
      var7 += 4L;
      var9.wS = var3 + (var6.readInt(var7) & 4294967295L);
      var9.A = cjx.pC(var0, var9.wS, var3, var5);
      if (var9.A == null) {
         return null;
      } else {
         if (var5) {
            pC(var0, var1);
         }

         return var9;
      }
   }

   public static cjs pC(cjz var0, ByteBuffer var1, long var2, long var4, boolean var6) throws MemoryException {
      cjs var7 = new cjs();
      var7.pC = var4 + (var1.getInt() & 4294967295L);
      var7.kS = var4 + (var1.getInt() & 4294967295L);
      var7.wS = var4 + (var1.getInt() & 4294967295L);
      var7.A = cjx.pC(var0, var7.wS, var4, var6);
      if (var7.A == null) {
         return null;
      } else {
         if (var6) {
            pC(var0, var2);
         }

         return var7;
      }
   }

   private static void pC(cjz var0, long var1) {
      if (var0.WR != null) {
         var0.pC.defineData(var1, var0.WR);
      }
   }
}
