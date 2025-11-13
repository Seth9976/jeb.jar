package com.pnfsoftware.jebglobal;

public class sv {
   public static final int q = 0;
   public static final int RF = 1;

   public static void q(int var0) {
      if (var0 < 0 || var0 > 1) {
         throw new si("Illegal StepSize: " + var0);
      }
   }
}
