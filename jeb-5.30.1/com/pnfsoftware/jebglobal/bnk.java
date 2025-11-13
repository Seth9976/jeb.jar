package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import java.util.Comparator;

class bnk implements Comparator {
   bnk(bni var1) {
      this.q = var1;
   }

   public int q(IJavaClass var1, IJavaClass var2) {
      return var1.getName().compareTo(var2.getName());
   }
}
