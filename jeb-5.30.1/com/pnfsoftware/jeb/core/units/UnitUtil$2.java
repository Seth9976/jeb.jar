package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitFilter;
import com.pnfsoftware.jeb.util.format.Strings;

class UnitUtil$2 implements IUnitFilter {
   UnitUtil$2(String var1) {
      this.val$name = var1;
   }

   @Override
   public boolean check(IUnit var1) {
      return Strings.equals(var1.getName(), this.val$name);
   }
}
