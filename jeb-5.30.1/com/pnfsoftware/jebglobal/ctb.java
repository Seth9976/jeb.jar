package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class ctb {
   static Map q = new HashMap();
   int RF;
   long xK;
   String Dw;
   int[] Uv;
   ctb oW;
   ctb gO;

   static ctb q(int var0, long var1, String var3, int... var4) {
      boolean var5 = (var1 & 33818624L) == 0L;
      ctb var6 = new ctb(var0, var1, var3, var4);
      if (var5) {
         q.put(var0, var6);
      }

      return var6;
   }

   static ctb q(int var0, String var1, int... var2) {
      return q(var0, 0L, var1, var2);
   }

   static ctb q(int var0, long var1) {
      return q(var0, var1, null);
   }

   static ctb q(int var0) {
      return q(var0, 0L, null);
   }

   private ctb(int var1, long var2, String var4, int... var5) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var4;
      this.Uv = var5;
   }

   ctb q(ctb var1) {
      this.oW = var1;
      return this;
   }

   ctb RF(ctb var1) {
      this.gO = var1;
      return this;
   }

   ctb RF(int var1) {
      this.xK |= var1;
      return this;
   }

   @Override
   public String toString() {
      return Strings.ff("%02X(%s)", this.RF, this.Dw);
   }
}
