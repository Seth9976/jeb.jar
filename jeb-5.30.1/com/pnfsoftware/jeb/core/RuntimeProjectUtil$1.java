package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.units.IUnit;

class RuntimeProjectUtil$1 implements IUnitFilter {
   RuntimeProjectUtil$1(long var1) {
      this.val$uid = var1;
   }

   @Override
   public int check2(IUnit var1) {
      return var1.getUid() == this.val$uid ? 3 : 0;
   }
}
