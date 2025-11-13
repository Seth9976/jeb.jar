package com.pnfsoftware.jebglobal;

import java.util.Comparator;

class blj implements Comparator {
   blj(bli var1) {
      this.q = var1;
   }

   public int q(bsy var1, bsy var2) {
      return -(var1.RF() - var2.RF());
   }
}
