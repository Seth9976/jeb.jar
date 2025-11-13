package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class cuk {
   public static final int q = 2;
   public static final int RF = 0;
   public static final int xK = 1;
   public static final int Dw = 2;
   public static final int Uv = 4;
   int oW;
   private int lm;
   private List zz = new ArrayList();
   long gO;
   int nf;
   long gP;
   cuf za;

   public static cuk q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      cuk var7 = new cuk();
      int var10 = var6.readByte(var1) & 255;
      long var8 = var1 + 1L;
      var7.oW = var10 >> 3 & 31;
      var7.lm = var6.readByte(++var8) & 255;
      var8++;
      var8++;
      int var11 = 0;

      while (var11 < var7.lm) {
         cui var12 = cui.q(var0, var8, var5);
         if (var12 == null) {
            return var7;
         }

         var7.zz.add(var12);
         var8 += var12.q * 2;
         var11 += var12.q;
      }

      if (var7.lm % 2 == 1) {
         var8 += 2L;
      }

      if ((var7.oW & 4) != 0) {
         var7.za = cuf.q(var0, var8, var3, var5);
         if (var7.za == null) {
            return null;
         }
      } else if ((var7.oW & 3) != 0) {
         var7.gO = var3 + (var6.readInt(var8) & 4294967295L);
         var8 += 4L;
         var7.nf = var6.readInt(var8);
         var7.gP = var8;
      }

      if (var5) {
         q(var0, var1);
      }

      return var7;
   }

   private static void q(cum var0, long var1) {
      if (var0.za != null) {
         var0.q.defineData(var1, var0.za);
      }
   }

   boolean q() {
      return (this.oW & 3) != 0;
   }
}
