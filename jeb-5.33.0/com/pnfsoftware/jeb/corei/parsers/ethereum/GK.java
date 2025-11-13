package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class GK {
   static Map pC = new HashMap();
   public int A;
   public int kS;
   public Integer wS;
   public Integer UT;
   public Integer E;
   public String sY;

   static GK pC(int var0, int var1, Integer var2, Integer var3, Integer var4, String var5) {
      GK var6 = new GK(var0, var1, var2, var3, var4, var5);
      pC.put(var0, var6);
      return var6;
   }

   private GK(int var1, int var2, Integer var3, Integer var4, Integer var5, String var6) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
      this.sY = var6;
   }

   public int pC() {
      return this.A;
   }

   public String A() {
      return this.sY;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.A, this.sY);
   }
}
