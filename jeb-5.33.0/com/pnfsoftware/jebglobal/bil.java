package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public abstract class bil extends bio implements IJavaCompound {
   @Override
   public List getSubElements() {
      return this.getSubElements(false);
   }

   protected void pC(bil var1) {
      super.pC(var1);
   }
}
