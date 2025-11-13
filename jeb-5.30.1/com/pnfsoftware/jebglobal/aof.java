package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.AbstractCFGReorganizer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import java.util.List;
import java.util.Map;

public class aof extends AbstractCFGReorganizer {
   IERoutineContext q;

   public aof(IERoutineContext var1) {
      super(var1.getCfg());
      this.q = var1;
   }

   protected boolean q(IEStatement var1) {
      if (var1 instanceof IEJump) {
         return ((IEJump)var1).getCondition() != null;
      } else if (var1 instanceof IESwitch) {
         throw new RuntimeException("TBI");
      } else {
         return false;
      }
   }

   protected IEStatement q(long var1) {
      return this.q.createJump((int)var1);
   }

   protected IEStatement q(int var1) {
      IENop var2 = this.q.createNop();
      var2.setSize(var1);
      return var2;
   }

   protected void q(IEStatement var1, long var2) {
      if (var1 instanceof IEJump) {
         ((IEJump)var1).setBranchAddress((int)var2);
      }
   }

   protected boolean RF(IEStatement var1) {
      return var1 instanceof IEJump || var1 instanceof IESwitch;
   }

   protected void q(IEStatement var1, Map var2) {
      if (var1 instanceof IEJump) {
         int var3 = ((IEJump)var1).getBranchAddress();
         ((IEJump)var1).setBranchAddress(((Long)var2.get((long)var3)).intValue());
      } else if (var1 instanceof IESwitch) {
         throw new RuntimeException("TBI");
      }
   }

   @Override
   protected CFG buildCFG(List var1) {
      return this.q.buildCfg(var1, false, false);
   }
}
