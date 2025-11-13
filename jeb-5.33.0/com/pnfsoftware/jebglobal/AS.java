package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AS {
   private Map pC = new LinkedHashMap();

   public static AS pC(aI var0) throws IOException {
      try {
         String var1 = var0.kS("qProcessInfo");
         AS var2 = new AS();
         var2.pC = zI.UT(var1);
         return var2;
      } catch (Ae var3) {
         return null;
      }
   }

   @Override
   public String toString() {
      return this.pC.toString();
   }
}
