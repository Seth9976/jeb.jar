package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import java.util.Comparator;

class bjo implements Comparator {
   bjo(bjl var1) {
      this.pC = var1;
   }

   public int pC(IJavaField var1, IJavaField var2) {
      return var1.getName().compareTo(var2.getName());
   }
}
