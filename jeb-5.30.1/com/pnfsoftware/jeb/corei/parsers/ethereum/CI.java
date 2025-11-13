package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class CI {
   static Map q = new HashMap();
   public int RF;
   public int xK;
   public Integer Dw;
   public Integer Uv;
   public Integer oW;
   public String gO;

   static CI q(int var0, int var1, Integer var2, Integer var3, Integer var4, String var5) {
      CI var6 = new CI(var0, var1, var2, var3, var4, var5);
      q.put(var0, var6);
      return var6;
   }

   private CI(int var1, int var2, Integer var3, Integer var4, Integer var5, String var6) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
      this.oW = var5;
      this.gO = var6;
   }

   public int q() {
      return this.RF;
   }

   public int RF() {
      return this.xK;
   }

   public String xK() {
      return this.gO;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.RF, this.gO);
   }
}
