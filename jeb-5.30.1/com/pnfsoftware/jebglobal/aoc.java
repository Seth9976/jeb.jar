package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;

public class aoc {
   IERoutineContext q;
   private int RF;

   public aoc(IERoutineContext var1) {
      this.q = var1;
   }

   public int q() {
      this.RF = 0;
      CFG var1 = this.q.getCfg();

      for (BasicBlock var3 : var1) {
         for (IEStatement var5 : var3) {
            var5.visitDepthPost(new aod(this));
         }
      }

      if (this.RF > 0) {
         var1.invalidateDataFlowAnalysis();
      }

      return this.RF;
   }
}
