package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class tw extends AbstractEOptimizer {
   public tw() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            IEVar var6 = this.q(var5);
            if (var6 != null) {
               boolean var7 = false;
               int var8 = -1;

               for (int var9 = var4 + 1; var9 < var3.size(); var9++) {
                  IEStatement var10 = (IEStatement)var3.get(var9);
                  if (!(var10 instanceof IEAssign)) {
                     break;
                  }

                  if (((IEAssign)var10).getDstOperand() instanceof IEMem) {
                     IEMem var11 = (IEMem)((IEAssign)var10).getDstOperand();
                     if (var11.getReference() != var6) {
                        break;
                     }

                     var7 = true;
                  } else if (((IEAssign)var10).getDstOperand() == var6) {
                     IEVar var12 = this.q(var10);
                     if (var12 == var6) {
                        var8 = var9;
                     }
                     break;
                  }
               }

               if (var7 && var8 >= 0) {
                  var3.set(var8, this.ectx.createNop((IEStatement)var3.get(var8)));
                  var1++;
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private IEVar q(IEStatement var1) {
      if (!(var1 instanceof IEAssign)) {
         return null;
      } else {
         IEGeneric var2 = ((IEAssign)var1).getDstOperand();
         if (!(var2 instanceof IEVar)) {
            return null;
         } else {
            IEGeneric var3 = ((IEAssign)var1).getSrcOperand();
            return var3 instanceof IEMem && var3.getBitsize() == 256 && EUtil.isImmValue(((IEMem)var3).getReference(), 64L) ? (IEVar)var2 : null;
         }
      }
   }
}
