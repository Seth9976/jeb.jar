package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class cui {
   private cui.eo RF;
   private int xK;
   int q;

   public static cui q(cum var0, long var1, boolean var3) throws MemoryException {
      IVirtualMemory var4 = var0.q.getMemory();
      cui var5 = new cui();
      long var6 = var1 + 1L;
      int var8 = var4.readByte(var6) & 255;
      Optional var9 = cui.eo.q(var8 & 15);
      if (!var9.isPresent()) {
         return null;
      } else {
         var5.RF = (cui.eo)var9.get();
         var5.xK = var8 >> 4 & 15;
         var5.q = 1;
         switch (var5.RF) {
            case RF:
               if (var5.xK == 0) {
                  var5.q++;
               } else if (var5.xK == 1) {
                  var5.q += 2;
               }
               break;
            case Uv:
               var5.q++;
               break;
            case oW:
               var5.q += 2;
               break;
            case nf:
               var5.q++;
               break;
            case gP:
               var5.q += 2;
         }

         if (var3) {
            q(var0, var1);
         }

         return var5;
      }
   }

   private static void q(cum var0, long var1) {
      if (var0.lm != null) {
         var0.q.defineData(var1, var0.lm);
      }
   }

   static enum eo {
      q(0),
      RF(1),
      xK(2),
      Dw(3),
      Uv(4),
      oW(5),
      gO(6),
      nf(8),
      gP(9),
      za(10);

      private final int lm;
      private static final Map zz = (Map)Stream.of(values()).collect(Collectors.toMap(cui.eo::q, var0 -> var0));

      private eo(int var3) {
         this.lm = var3;
      }

      public int q() {
         return this.lm;
      }

      public static Optional q(int var0) {
         return Optional.ofNullable((cui.eo)zz.get(var0));
      }
   }
}
