package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.Comparator;

class bjm implements Comparator {
   bjm(bjl var1) {
      this.pC = var1;
   }

   public int pC(IJavaType var1, IJavaType var2) {
      return var1.getName().compareTo(var2.getName());
   }
}
