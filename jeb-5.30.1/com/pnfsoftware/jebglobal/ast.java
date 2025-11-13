package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class ast extends AbstractEOptimizer {
   public ast() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size() - 1; var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            IEStatement var6 = (IEStatement)var3.get(var4 + 1);
            if (this.q(var5) && this.q(var6)) {
               IEMem var7 = (IEMem)((IEAssign)var5).getLeftOperand();
               IESlice var8 = (IESlice)((IEAssign)var5).getRightOperand();
               IEMem var9 = (IEMem)((IEAssign)var6).getLeftOperand();
               IESlice var10 = (IESlice)((IEAssign)var6).getRightOperand();
               IEStatement var11 = this.q(var7, var9, var8, var10, true, var5, var6);
               if (var11 != null) {
                  var3.set(var4, var11);
                  var3.set(var4 + 1, this.ectx.createNop(var6));
                  Object[] var10000 = new Object[]{var7, var9};
                  var1++;
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private boolean q(IEStatement var1) {
      return var1 instanceof IEAssign && ((IEAssign)var1).getLeftOperand() instanceof IEMem && this.q(((IEAssign)var1).getRightOperand());
   }

   private boolean q(IEGeneric var1) {
      return var1 instanceof IESlice && !EUtil.containsMemoryAccess(((IESlice)var1).getWholeExpression());
   }

   private IEStatement q(IEMem var1, IEMem var2, IESlice var3, IESlice var4, boolean var5, IEStatement var6, IEStatement var7) {
      if (var3.getWholeExpression().equals(var4.getWholeExpression())) {
         if (var3.getBitStart() > var4.getBitStart()) {
            IESlice var8 = var4;
            var4 = var3;
            var3 = var8;
            IEMem var9 = var2;
            var2 = var1;
            var1 = var9;
         }

         if (var3.getBitEnd() == var4.getBitStart()) {
            boolean var12 = this.ectx.getGlobalContext().isBigEndian();
            IEMem var13 = amw.q(this.ectx, var12 ? var2 : var1, var12 ? var1 : var2);
            if (var13 != null) {
               IEGeneric var10 = var3.getBitStart() == 0 && var4.getBitEnd() == var3.getWholeExpression().getBitsize()
                  ? var3.getWholeExpression()
                  : var3.getWholeExpression().slice(var3.getBitStart(), var4.getBitEnd());
               IEAssign var11 = ((IEAssign)var6).duplicateWithNewOperands((IEGeneric)(var5 ? var13 : var10), (IEGeneric)(var5 ? var10 : var13));
               var11.copyProperties(var7);
               var11.setSize(var6.getSize());
               return var11;
            }
         }
      }

      return null;
   }
}
