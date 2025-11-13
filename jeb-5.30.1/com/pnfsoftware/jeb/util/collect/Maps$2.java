package com.pnfsoftware.jeb.util.collect;

import java.util.Comparator;
import java.util.Map.Entry;

class Maps$2 implements Comparator {
   Maps$2(boolean var1) {
      this.val$ascending = var1;
   }

   public int compare(Entry var1, Entry var2) {
      int var3 = ((Comparable)var1.getValue()).compareTo((Comparable)var2.getValue());
      return this.val$ascending ? var3 : -var3;
   }
}
