package com.pnfsoftware.jebglobal;

public class ael {
   public static int pC(int var0) {
      int var1 = 0;
      var1 |= pC(var0, 1, 1);
      var1 |= pC(var0, 2, 2);
      var1 |= pC(var0, 4, 4);
      var1 |= pC(var0, 8, 8);
      var1 |= pC(var0, 16, 16);
      var1 |= pC(var0, 64, 64);
      var1 |= pC(var0, 128, 128);
      var1 |= pC(var0, 512, 512);
      var1 |= pC(var0, 1024, 1024);
      var1 |= pC(var0, 4096, 4096);
      var1 |= pC(var0, 16384, 16384);
      var1 |= pC(var0, 65536, 65536);
      var1 |= pC(var0, 536870912, 536870912);
      return var1 | pC(var0, 268435456, 268435456);
   }

   private static int pC(int var0, int var1, int var2) {
      return (var0 & var1) != 0 ? var2 : 0;
   }
}
