package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class rm {
   static Map pC = new HashMap();
   int A;
   long kS;
   String wS;
   int[] UT;
   rm E;
   rm sY;

   static rm pC(int var0, long var1, String var3, int... var4) {
      boolean var5 = (var1 & 33818624L) == 0L;
      rm var6 = new rm(var0, var1, var3, var4);
      if (var5) {
         pC.put(var0, var6);
      }

      return var6;
   }

   static rm pC(int var0, String var1, int... var2) {
      return pC(var0, 0L, var1, var2);
   }

   static rm pC(int var0, long var1) {
      return pC(var0, var1, null);
   }

   static rm pC(int var0) {
      return pC(var0, 0L, null);
   }

   private rm(int var1, long var2, String var4, int... var5) {
      this.A = var1;
      this.kS = var2;
      this.wS = var4;
      this.UT = var5;
   }

   rm pC(rm var1) {
      this.E = var1;
      return this;
   }

   rm A(rm var1) {
      this.sY = var1;
      return this;
   }

   @Override
   public String toString() {
      return Strings.ff("%02X(%s)", this.A, this.wS);
   }
}
