package com.pnfsoftware.jebglobal;

public class Nl {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;

   public static void q(int var0) {
      if (var0 != 1 && var0 != 2 && var0 != 4 && var0 != 8) {
         throw new si("Illegal ClassStatus: " + var0);
      }
   }
}
