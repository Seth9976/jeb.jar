package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public abstract class bmi extends bml implements IJavaCompound {
   @Override
   public List getSubElements() {
      return this.getSubElements(false);
   }

   protected void q(bmi var1) {
      super.q(var1);
   }
}
