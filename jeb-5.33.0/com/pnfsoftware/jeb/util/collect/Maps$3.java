package com.pnfsoftware.jeb.util.collect;

import java.util.Comparator;
import java.util.Map.Entry;

class Maps$3 implements Comparator {
   Maps$3(boolean var1) {
      this.val$descending = var1;
   }

   public int compare(Entry var1, Entry var2) {
      Comparable var3 = (Comparable)var1.getValue();
      Comparable var4 = (Comparable)var2.getValue();
      if (var3 == null) {
         return var4 == null ? 0 : 1;
      } else if (var4 == null) {
         return var3 == null ? 0 : -1;
      } else {
         int var5 = var3.compareTo(var4);
         return this.val$descending ? -var5 : var5;
      }
   }
}
