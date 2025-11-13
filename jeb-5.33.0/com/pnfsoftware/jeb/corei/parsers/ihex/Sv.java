package com.pnfsoftware.jeb.corei.parsers.ihex;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public class Sv {
   private int pC;
   private int A;
   private byte[] kS;
   private boolean wS;

   Sv(int var1, int var2, byte[] var3, boolean var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public int pC() {
      return this.A;
   }

   public byte[] A() {
      return this.kS;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%d/%Xh/%s", this.A, this.pC, Formatter.byteArrayToHex(this.kS));
      if (!this.wS) {
         var1 = var1 + "[CORRUPT]";
      }

      return var1;
   }
}
