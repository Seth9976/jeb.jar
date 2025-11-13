package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import java.util.Map;

class oM implements vn {
   oM(ej var1, Map var2) {
      this.RF = var1;
      this.q = var2;
   }

   @Override
   public boolean q(String var1) {
      return this.RF.Uv.getEntry(var1) != null;
   }

   @Override
   public void q(String var1, String var2) {
      this.q.put(var1, var2);
   }
}
