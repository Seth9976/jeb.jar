package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class ME {
   static Map q = new HashMap();
   public int RF;
   int xK;
   Integer Dw;
   Integer Uv;
   public String oW;
   int[] gO;

   static ME q(int var0, int var1, Integer var2, Integer var3, String var4, int... var5) {
      ME var6 = new ME(var0, var1, var2, var3, var4, var5);
      q.put(var0, var6);
      return var6;
   }

   private ME(int var1, int var2, Integer var3, Integer var4, String var5, int... var6) {
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

   public Integer xK() {
      return this.Dw;
   }

   public Integer Dw() {
      return this.Uv;
   }

   public Integer Uv() {
      return this.Dw != null && this.Uv != null ? -this.Dw + this.Uv : null;
   }

   public String oW() {
      return this.oW;
   }

   public int[] gO() {
      return this.gO;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.RF, this.oW);
   }
}
