package com.pnfsoftware.jeb.util.collect;

import java.util.Comparator;

class Maps$1 implements Comparator {
   Maps$1(boolean var1) {
      this.val$ascending = var1;
   }

   public int compare(Comparable var1, Comparable var2) {
      int var3 = var1.compareTo(var2);
      return this.val$ascending ? var3 : -var3;
   }
}
