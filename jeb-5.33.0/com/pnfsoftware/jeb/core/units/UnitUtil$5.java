package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitFilter;
import com.pnfsoftware.jeb.util.format.Strings;

class UnitUtil$5 implements IUnitFilter {
   UnitUtil$5(String var1) {
      this.val$formatType = var1;
   }

   @Override
   public boolean check(IUnit var1) {
      return Strings.equals(var1.getFormatType(), this.val$formatType);
   }
}
