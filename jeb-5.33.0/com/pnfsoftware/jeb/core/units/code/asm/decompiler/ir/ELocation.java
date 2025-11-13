package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;

public class ELocation {
   IERoutineContext ctx;
   long stmOffset;

   public ELocation(IERoutineContext var1, long var2) {
      this.ctx = var1;
      this.stmOffset = var2;
   }

   public IERoutineContext getRoutineContext() {
      return this.ctx;
   }

   public long getStatementOffset() {
      return this.stmOffset;
   }

   public IEStatement getStatement() {
      return (IEStatement)this.ctx.getCfg().getInstruction(this.stmOffset);
   }
}
