package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public abstract class azo implements INativeAttribute {
   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType());
      var1.append(" -> ");
      var1.append(this.getValue());
      return var1.toString();
   }
}
