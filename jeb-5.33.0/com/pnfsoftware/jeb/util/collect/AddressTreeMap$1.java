package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.Comparator;

class AddressTreeMap$1 implements Comparator {
   public int compare(Long var1, Long var2) {
      return Longs.compareUnsigned(var1, var2);
   }
}
