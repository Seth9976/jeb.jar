package com.pnfsoftware.jebglobal;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

class boa implements Comparator {
   boa(bnz var1) {
      this.q = var1;
   }

   public int q(bmo var1, bmo var2) {
      return Integer.compare((Integer)Collections.max((Collection)this.q.nf.get(var1)), (Integer)Collections.max((Collection)this.q.nf.get(var2)));
   }
}
