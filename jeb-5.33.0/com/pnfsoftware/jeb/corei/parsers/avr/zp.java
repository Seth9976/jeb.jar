package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class zp extends AbstractEExpressionOptimizer {
   public zp() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      cq var2 = (cq)this.ectx.getConverter();
      if (var1 instanceof IECompose var3 && var3.getPartsCount() == 2) {
         IEGeneric var4 = var3.getLowPart();
         IEGeneric var5 = var3.getHighPart();
         if (var4 instanceof IEVar && var5 instanceof IEVar) {
            int var6 = ((IEVar)var4).getId();
            int var7 = ((IEVar)var5).getId();
            if (var6 == 744 && var7 == 752) {
               IEVar var12 = var2.getStackPointer();
               IEOperation var9 = this.ectx.createOperation(OperationType.SUB, var12, this.ectx.createImm(1L, var12.getBitsize()));
               return AbstractEExpressionOptimizer.EOR.create(var9);
            }

            if (var6 == 208 && var7 == 216) {
               IEVar var11 = var2.pC();
               return AbstractEExpressionOptimizer.EOR.create(var11);
            }

            if (var6 == 224 && var7 == 232) {
               IEVar var10 = var2.A();
               return AbstractEExpressionOptimizer.EOR.create(var10);
            }

            if (var6 == 240 && var7 == 248) {
               IEVar var8 = var2.kS();
               return AbstractEExpressionOptimizer.EOR.create(var8);
            }
         }
      }

      return null;
   }
}
