package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class cjv {
   private cjv.Av A;
   private int kS;
   int pC;

   public static cjv pC(cjz var0, long var1, boolean var3) throws MemoryException {
      IVirtualMemory var4 = var0.pC.getMemory();
      cjv var5 = new cjv();
      long var6 = var1 + 1L;
      int var8 = var4.readByte(var6) & 255;
      Optional var9 = cjv.Av.pC(var8 & 15);
      if (!var9.isPresent()) {
         return null;
      } else {
         var5.A = (cjv.Av)var9.get();
         var5.kS = var8 >> 4 & 15;
         var5.pC = 1;
         switch (var5.A) {
            case A:
               if (var5.kS == 0) {
                  var5.pC++;
               } else if (var5.kS == 1) {
                  var5.pC += 2;
               }
               break;
            case UT:
               var5.pC++;
               break;
            case E:
               var5.pC += 2;
               break;
            case ys:
               var5.pC++;
               break;
            case ld:
               var5.pC += 2;
         }

         if (var3) {
            pC(var0, var1);
         }

         return var5;
      }
   }

   private static void pC(cjz var0, long var1) {
      if (var0.oT != null) {
         var0.pC.defineData(var1, var0.oT);
      }
   }

   static enum Av {
      pC(0),
      A(1),
      kS(2),
      wS(3),
      UT(4),
      E(5),
      sY(6),
      ys(8),
      ld(9),
      gp(10);

      private final int oT;
      private static final Map fI = (Map)Stream.of(values()).collect(Collectors.toMap(cjv.Av::pC, var0 -> var0));

      private Av(int var3) {
         this.oT = var3;
      }

      public int pC() {
         return this.oT;
      }

      public static Optional pC(int var0) {
         return Optional.ofNullable((cjv.Av)fI.get(var0));
      }
   }
}
