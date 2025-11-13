package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitFilter;

class UnitUtil$4 implements IUnitFilter {
   UnitUtil$4(String var1, Class var2, boolean var3) {
      this.val$name = var1;
      this.val$c = var2;
      this.val$strict = var3;
   }

   @Override
   public boolean check(IUnit var1) {
      return this.val$name != null && !this.val$name.equals(var1.getName())
         ? false
         : this.val$c == null || UnitUtil.isClassMatch(var1, this.val$c, this.val$strict);
   }
}
