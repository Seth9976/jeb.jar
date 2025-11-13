package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class bcg {
   List q = new ArrayList();
   Map RF = new HashMap();
   List xK = new ArrayList();
   int Dw;

   @Override
   public String toString() {
      String var1 = Strings.ff("items=%s typeStack=%s", this.q, this.xK);
      if (this.Dw > 0) {
         var1 = var1 + Strings.ff(" bitsize=%d", this.Dw);
      }

      if (!this.RF.isEmpty()) {
         var1 = var1 + Strings.ff(" spec=%s", this.RF.keySet());
      }

      return var1;
   }
}
