package com.pnfsoftware.jebglobal;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

class bkd implements Comparator {
   bkd(bkc var1) {
      this.pC = var1;
   }

   public int pC(bir var1, bir var2) {
      return Integer.compare((Integer)Collections.max((Collection)this.pC.E.get(var1)), (Integer)Collections.max((Collection)this.pC.E.get(var2)));
   }
}
