package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public abstract class ank {
   protected static final StructuredLogger q = aeg.q(ank.class);
   protected IERoutineContext RF;
   private long xK = -1L;

   public ank(IERoutineContext var1) {
      this.RF = var1;
   }

   public IERoutineContext q() {
      return this.RF;
   }

   public long RF() {
      return this.xK;
   }

   public final int xK() {
      long var1 = System.currentTimeMillis();

      int var3;
      try {
         var3 = this.Dw();
      } finally {
         this.xK = System.currentTimeMillis() - var1;
      }

      return var3;
   }

   protected abstract int Dw();
}
