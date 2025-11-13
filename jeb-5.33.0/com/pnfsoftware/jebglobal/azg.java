package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class azg {
   List pC = new ArrayList();
   Map A = new HashMap();
   List kS = new ArrayList();
   int wS;

   @Override
   public String toString() {
      String var1 = Strings.ff("items=%s typeStack=%s", this.pC, this.kS);
      if (this.wS > 0) {
         var1 = var1 + Strings.ff(" bitsize=%d", this.wS);
      }

      if (!this.A.isEmpty()) {
         var1 = var1 + Strings.ff(" spec=%s", this.A.keySet());
      }

      return var1;
   }
}
