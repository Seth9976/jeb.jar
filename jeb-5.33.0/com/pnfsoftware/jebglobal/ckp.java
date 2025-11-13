package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IUnitFilter;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;

class ckp implements IUnitFilter {
   ckp(cko var1) {
      this.pC = var1;
   }

   @Override
   public int check2(IUnit var1) {
      return var1 instanceof IELFUnit
            && var1.getName()
               .contains(ckx.pC(new byte[]{(byte)51, (byte)14, (byte)25, (byte)11, (byte)27, (byte)25, (byte)4, (byte)7, (byte)6, (byte)69}, 2, 170))
         ? 3
         : 0;
   }
}
