package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitFilter;

class UnitUtil$3 implements IUnitFilter {
   UnitUtil$3(String var1, Class var2, boolean var3) {
      this.val$name = var1;
      this.val$classtype = var2;
      this.val$exactClasstype = var3;
   }

   @Override
   public boolean check(IUnit var1) {
      return this.val$name != null && !this.val$name.equals(var1.getName())
         ? false
         : this.val$classtype == null || UnitUtil.isClassMatch(var1, this.val$classtype, this.val$exactClasstype);
   }
}
