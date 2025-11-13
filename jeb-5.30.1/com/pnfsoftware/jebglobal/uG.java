package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class uG {
   private String gO = "";
   public boolean q;
   public boolean RF;
   public boolean xK;
   public boolean Dw;
   public boolean Uv;
   public boolean oW;

   uG(oH var1) throws IOException {
      String var2 = Strings.decodeASCII(var1.Dw("vCont?"));
      if (var2.startsWith("vCont")) {
         for (String var6 : var2.substring(5).split(";")) {
            if (!var6.isEmpty()) {
               this.q(var6);
               this.gO = this.gO + var6;
            }
         }
      }
   }

   public boolean q(String var1) {
      switch (var1) {
         case "c":
            this.q = true;
            return true;
         case "C":
            this.RF = true;
            return true;
         case "s":
            this.xK = true;
            return true;
         case "S":
            this.Dw = true;
            return true;
         case "t":
            this.Uv = true;
            return true;
         case "r":
            this.oW = true;
            return true;
         default:
            return false;
      }
   }

   @Override
   public String toString() {
      return this.gO;
   }
}
