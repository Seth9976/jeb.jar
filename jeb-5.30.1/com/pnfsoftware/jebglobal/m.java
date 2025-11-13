package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public enum m {
   q(0),
   RF(1),
   xK(2);

   private final int Dw;

   private m(int var3) {
      this.Dw = var3;
   }

   public byte q() {
      return (byte)this.Dw;
   }

   public static m q(int var0) {
      for (m var4 : values()) {
         if (var4.Dw == var0) {
            return var4;
         }
      }

      throw new si(Strings.ff("Invalid JDWP SuspendPolicy code: %d", var0));
   }
}
