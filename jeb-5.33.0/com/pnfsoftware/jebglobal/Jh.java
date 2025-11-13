package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public enum Jh {
   pC(0),
   A(1),
   kS(2);

   private final int wS;

   private Jh(int var3) {
      this.wS = var3;
   }

   public byte pC() {
      return (byte)this.wS;
   }

   public static Jh pC(int var0) {
      for (Jh var4 : values()) {
         if (var4.wS == var0) {
            return var4;
         }
      }

      throw new yb(Strings.ff("Invalid JDWP SuspendPolicy code: %d", var0));
   }
}
