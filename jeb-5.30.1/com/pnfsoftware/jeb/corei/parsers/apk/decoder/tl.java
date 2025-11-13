package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import java.util.LinkedHashMap;
import java.util.Map;

public class tl {
   int q;
   String RF;
   String xK;
   boolean Dw;
   int Uv;
   Map oW = new LinkedHashMap();

   tl(int var1, String var2, String var3, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Uv = var4;
   }

   public int q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   public String xK() {
      return this.xK;
   }

   public boolean Dw() {
      return this.Dw;
   }

   public int Uv() {
      return this.Uv;
   }

   public Map oW() {
      return this.oW;
   }
}
