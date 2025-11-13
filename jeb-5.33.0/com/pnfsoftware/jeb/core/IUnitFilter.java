package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.units.IUnit;

public interface IUnitFilter {
   default boolean check(IUnit var1) {
      return false;
   }

   default int check2(IUnit var1) {
      return this.check(var1) ? 1 : 0;
   }
}
