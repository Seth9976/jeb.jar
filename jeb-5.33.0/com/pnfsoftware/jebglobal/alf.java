package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public abstract class alf {
   protected static final StructuredLogger pC = aco.pC(alf.class);
   protected IERoutineContext A;
   private long kS = -1L;

   public alf(IERoutineContext var1) {
      this.A = var1;
   }

   public final int pC() {
      long var1 = System.currentTimeMillis();

      int var3;
      try {
         var3 = this.A();
      } finally {
         this.kS = System.currentTimeMillis() - var1;
      }

      return var3;
   }

   protected abstract int A();
}
