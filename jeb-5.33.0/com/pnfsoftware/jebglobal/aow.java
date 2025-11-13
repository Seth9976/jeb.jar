package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.ArrayDeque;

public class aow extends AbstractEOptimizer {
   public aow() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      ArrayDeque var2 = new ArrayDeque();

      for (BasicBlock var4 : this.cfg) {
         if (var4.size() == 1 && var4.get(0) instanceof IEReturn) {
            IEReturn var5 = (IEReturn)var4.get(0);
            if (var5.getValues().size() < 2) {
               IEGeneric var6 = var5.getValue();
               if (var4.alloutsize() == 0 || !var5.canThrow()) {
                  for (BasicBlock var8 : var4.getInputBlocks()) {
                     int var9 = var8.size();
                     if (var9 <= 2) {
                        IEStatement var10 = (IEStatement)var8.get(0);
                        if (var9 == 2) {
                           if (!(var6 instanceof IEVar) || !(var10 instanceof IEAssign) || !((IEAssign)var10).getDstOperand().equalsEx(var6, false)) {
                              continue;
                           }

                           var10 = (IEStatement)var8.getLast();
                        }

                        if (EUtil.isUnconditionalJump(var10) && var8.getInputs().size() == 1) {
                           BasicBlock var11 = var8.getInputBlock(0);
                           if (((IEStatement)var11.getLast()).isConditionalJump()) {
                              Object[] var10000 = new Object[]{var8.getLastAddress(), var10, var5};
                              this.cfg.deleteEdge(var8, var4);
                              IEReturn var12 = (IEReturn)var5.duplicate();
                              var12.copyProperties(var10);
                              var8.set(var8.size() - 1, var12);
                              var1++;
                              if (var4.allinsize() == 0) {
                                 var2.add(var4);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      CFGUtil.removeUnreachableBlocks(this.cfg, var2);
      return this.postPerform(var1);
   }
}
