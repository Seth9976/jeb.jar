package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import java.util.Comparator;

class bnl implements Comparator {
   bnl(bni var1) {
      this.q = var1;
   }

   public int q(IJavaField var1, IJavaField var2) {
      return var1.getName().compareTo(var2.getName());
   }
}
