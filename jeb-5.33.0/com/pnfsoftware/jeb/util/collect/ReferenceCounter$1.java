package com.pnfsoftware.jeb.util.collect;

import java.util.Comparator;
import java.util.Map.Entry;

class ReferenceCounter$1 implements Comparator {
   ReferenceCounter$1(ReferenceCounter var1) {
      this.this$0 = var1;
   }

   public int compare(Entry var1, Entry var2) {
      return -Integer.compare((Integer)var1.getValue(), (Integer)var2.getValue());
   }
}
