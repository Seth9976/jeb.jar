package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.nio.ByteBuffer;

public class cuf {
   public static final int q = 12;
   long RF;
   private long Dw;
   private long Uv;
   cuk xK;

   public static cuf q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      cuf var9 = new cuf();
      var9.RF = var3 + (var6.readInt(var1) & 4294967295L);
      long var7 = var1 + 4L;
      var9.Dw = var3 + (var6.readInt(var7) & 4294967295L);
      var7 += 4L;
      var9.Uv = var3 + (var6.readInt(var7) & 4294967295L);
      var9.xK = cuk.q(var0, var9.Uv, var3, var5);
      if (var9.xK == null) {
         return null;
      } else {
         if (var5) {
            q(var0, var1);
         }

         return var9;
      }
   }

   public static cuf q(cum var0, ByteBuffer var1, long var2, long var4, boolean var6) throws MemoryException {
      cuf var7 = new cuf();
      var7.RF = var4 + (var1.getInt() & 4294967295L);
      var7.Dw = var4 + (var1.getInt() & 4294967295L);
      var7.Uv = var4 + (var1.getInt() & 4294967295L);
      var7.xK = cuk.q(var0, var7.Uv, var4, var6);
      if (var7.xK == null) {
         return null;
      } else {
         if (var6) {
            q(var0, var2);
         }

         return var7;
      }
   }

   private static void q(cum var0, long var1) {
      if (var0.JY != null) {
         var0.q.defineData(var1, var0.JY);
      }
   }
}
