package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class asx extends AbstractEOptimizer {
   private static final StructuredLogger RF = aeg.q(asx.class);
   boolean q = true;

   public asx() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         int var4 = 0;

         while (var4 < var3.size()) {
            if (var3.size() >= 2 && ((IEStatement)var3.get(var4)).isNop()) {
               int var5 = amw.q(this.ectx, var3, var4, true);
               if (var5 != 0) {
                  var1++;
                  if (var5 == 1) {
                     continue;
                  }
               }
            }

            var4++;
         }
      }

      if (this.q) {
         int var12 = 0;

         for (int var13 = 1; var13 < this.cfg.size() - 1; var13++) {
            BasicBlock var14 = this.cfg.get(var13);
            if (var14.size() == 1 && ((IEStatement)var14.get(0)).isNop()) {
               Object[] var10000 = new Object[]{var14};
               IENop var15 = ((IEStatement)var14.get(0)).asNop();
               int var6 = (int)var14.getBase();
               BasicBlock var7 = this.cfg.get(var13 - 1);
               BasicBlock var8 = this.cfg.get(var13 + 1);
               int var9 = (int)var8.getBase();
               if (var9 == var6 + var15.getSize() && (int)var7.getEndAddress() == var6) {
                  this.cfg.deleteOutEdges(var14);

                  for (BasicBlock var11 : var14.getInputBlocks()) {
                     EUtil.adjustBranchToIRInstruction((IEStatement)var11.getLast(), var6, var9);
                     this.cfg.reconnectEdge(var11, var14, var8);
                  }

                  this.cfg.removeBlock(var14);
                  ((IEStatement)var7.getLast()).adjustSize(var15.getSize());
                  var12++;
               } else {
                  aeb.q(new RuntimeException("Inconsistency detected"), this.ectx);
               }
            }
         }

         if (var12 > 0) {
            amw.RF(this.cfg, false, true);
         }

         var1 += var12;
      }

      return this.postPerform(var1);
   }
}
