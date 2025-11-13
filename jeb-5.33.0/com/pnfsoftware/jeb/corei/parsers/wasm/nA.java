package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class nA {
   static Map pC = new HashMap();
   public int A;
   int kS;
   Integer wS;
   Integer UT;
   public String E;
   int[] sY;

   static nA pC(int var0, int var1, Integer var2, Integer var3, String var4, int... var5) {
      nA var6 = new nA(var0, var1, var2, var3, var4, var5);
      pC.put(var0, var6);
      return var6;
   }

   private nA(int var1, int var2, Integer var3, Integer var4, String var5, int... var6) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
      this.sY = var6;
   }

   public Integer pC() {
      return this.wS;
   }

   public Integer A() {
      return this.wS != null && this.UT != null ? -this.wS + this.UT : null;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.A, this.E);
   }
}
