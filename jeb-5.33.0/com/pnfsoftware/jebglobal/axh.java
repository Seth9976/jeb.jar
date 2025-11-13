package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public abstract class axh implements INativeFeature {
   @Override
   public boolean match(INativeFeature var1) {
      throw new RuntimeException("match not implemented");
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType());
      var1.append(" -> ");
      var1.append(this.getValue());
      return var1.toString();
   }
}
