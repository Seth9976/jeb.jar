package com.pnfsoftware.jebglobal;

public class btb {
   public static void pC(bsv var0) {
      if (var0 instanceof bsy var1 && var1.wS < 0) {
         synchronized (var1) {
            if (var1.wS < 0) {
               pC(var1);
               A(var1);
            }
         }
      }
   }

   private static int pC(bsy var0) {
      if (var0.wS >= 0) {
         return var0.wS;
      } else {
         int var1 = 0;

         for (bsv var5 : var0.kS) {
            if (var5 instanceof bsy var6) {
               int var7 = pC(var6);
               if (var7 > var1) {
                  var1 = var7;
               }
            }
         }

         boolean var8 = var0.pC == bsz.ld;
         int var9 = (var8 ? 0 : 1) + var1;
         var0.wS = var9;
         return var9;
      }
   }

   private static int A(bsy var0) {
      if (var0.UT >= 0) {
         return var0.UT;
      } else {
         int var1 = 1;

         for (bsv var5 : var0.kS) {
            if (var5 instanceof bsy var6) {
               var1 += A(var6);
            } else {
               var1++;
            }
         }

         var0.UT = var1;
         return var1;
      }
   }
}
