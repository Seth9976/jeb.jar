package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpWithOptionalCondition;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aoe {
   private static final StructuredLogger pC = aco.pC(aoe.class);

   public static void pC(IERoutineContext var0, BasicBlock var1, boolean var2) {
      IEStatement var3 = (IEStatement)var1.getLast();
      if (var3 instanceof IEJumpWithOptionalCondition && ((IEJumpWithOptionalCondition)var3).getCondition() != null) {
         CFG var4 = var0.getCfg();
         if (var2) {
            var4.deleteEdge(var1, var1.getOutputBlock(0));
            EUtil.makeUncond((IEJumpWithOptionalCondition)var3);
         } else {
            if (var1.outsize() >= 2) {
               var4.deleteEdge(var1, var1.getOutputBlock(1));
            }

            var1.set(var1.size() - 1, var0.createNop(var3));
         }

         akt.pC(var4);
         var4.invalidateDataFlowAnalysis();
      } else {
         throw new IllegalArgumentException("Last basic block instruction is not a conditional jump/jump-far");
      }
   }

   public static int pC(CFG var0) {
      Assert.a(var0.size() > 0 && var0.getFirstAddress() == 0L && var0.getEntryAddress() == 0L);
      int var1 = 0;
      BasicBlock var2 = var0.get(0);

      for (int var3 = 1; var3 < var0.size(); var3++) {
         BasicBlock var4 = var0.get(var3);
         long var5 = var4.getFirstAddress() - var2.getEndAddress();
         if (var5 != 0L) {
            if (var5 < 0L) {
               throw new RuntimeException();
            }

            ((IEStatement)var2.getLast()).adjustSize((int)var5);
            var1++;
         }

         var2 = var4;
      }

      return var1;
   }
}
