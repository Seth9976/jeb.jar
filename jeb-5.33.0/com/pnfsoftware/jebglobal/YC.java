package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class YC {
   private String sY = "";
   public boolean pC;
   public boolean A;
   public boolean kS;
   public boolean wS;
   public boolean UT;
   public boolean E;

   YC(aI var1) throws IOException {
      String var2 = Strings.decodeASCII(var1.wS("vCont?"));
      if (var2.startsWith("vCont")) {
         for (String var6 : var2.substring(5).split(";")) {
            if (!var6.isEmpty()) {
               this.pC(var6);
               this.sY = this.sY + var6;
            }
         }
      }
   }

   public boolean pC(String var1) {
      switch (var1) {
         case "c":
            this.pC = true;
            return true;
         case "C":
            this.A = true;
            return true;
         case "s":
            this.kS = true;
            return true;
         case "S":
            this.wS = true;
            return true;
         case "t":
            this.UT = true;
            return true;
         case "r":
            this.E = true;
            return true;
         default:
            return false;
      }
   }

   @Override
   public String toString() {
      return this.sY;
   }
}
