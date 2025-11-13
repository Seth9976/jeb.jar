package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitFilter;

class UnitUtil$1 implements IUnitFilter {
   UnitUtil$1(Class var1, boolean var2) {
      this.val$classtype = var1;
      this.val$exactClasstype = var2;
   }

   @Override
   public boolean check(IUnit var1) {
      return this.val$classtype == null || this.val$classtype == var1.getClass() || !this.val$exactClasstype && this.val$classtype.isInstance(var1);
   }
}
