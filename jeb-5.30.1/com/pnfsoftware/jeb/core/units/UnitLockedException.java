package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.concurrent.ConcurrentException;

public class UnitLockedException extends ConcurrentException {
   private static final long serialVersionUID = 1L;

   public UnitLockedException() {
   }

   public UnitLockedException(String var1) {
      super(var1);
   }

   public UnitLockedException(Throwable var1) {
      super(var1);
   }

   public UnitLockedException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
