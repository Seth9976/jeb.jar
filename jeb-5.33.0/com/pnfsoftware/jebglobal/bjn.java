package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import java.util.Comparator;

class bjn implements Comparator {
   bjn(bjl var1) {
      this.pC = var1;
   }

   public int pC(IJavaClass var1, IJavaClass var2) {
      return var1.getName().compareTo(var2.getName());
   }
}
