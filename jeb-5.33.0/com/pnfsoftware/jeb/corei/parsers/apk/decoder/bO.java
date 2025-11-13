package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import java.util.Map;

class bO implements yt {
   bO(Ws var1, Map var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public boolean pC(String var1) {
      return this.A.kS.getEntry(var1) != null;
   }

   @Override
   public void pC(String var1, String var2) {
      this.pC.put(var1, var2);
   }
}
