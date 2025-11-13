package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.Comparator;

class bnj implements Comparator {
   bnj(bni var1) {
      this.q = var1;
   }

   public int q(IJavaType var1, IJavaType var2) {
      return var1.getName().compareTo(var2.getName());
   }
}
