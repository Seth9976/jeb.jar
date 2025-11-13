package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ctr extends AbstractEOptimizer {
   private Set q = new HashSet();

   public ctr(IEConverter var1) {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      Assert.a(var1 instanceof crx);
      String[] var2 = new String[]{"cs", "ds", "ss", "es"};

      for (String var6 : var2) {
         IEVar var7 = var1.getGlobalContext().getVariableByName(var6);
         if (var7 != null) {
            this.q.add(var7.getId());
         }
      }
   }

   @Override
   protected int perform() {
      for (IEStatement var2 : this.cfg.instructions()) {
         if (var2.isAssignToVar()) {
            int var3 = var2.asAssign().getDstOperand().asVar().getId();
            if (this.q.contains(var3)) {
               this.q.remove(var3);
            }
         }
      }

      AtomicInteger var4 = new AtomicInteger();

      for (IEStatement var6 : this.cfg.instructions()) {
         var6.visitInstruction(new cts(this, var4));
      }

      return this.postPerform(var4.get());
   }
}
