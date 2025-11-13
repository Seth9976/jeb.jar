package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.Iterator;

public class aqs extends AbstractEOptimizer {
   public aqs() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      Iterator var2 = this.cfg.addressableInstructions().iterator();
      boolean var3 = false;
      AddressableInstruction var4 = null;

      while (true) {
         if (!var3) {
            if (!var2.hasNext()) {
               break;
            }

            var4 = (AddressableInstruction)var2.next();
         } else {
            var3 = false;
         }

         if (!var2.hasNext()) {
            break;
         }

         if (var4.getInstruction() instanceof IECall) {
            IECall var5 = (IECall)var4.getInstruction();
            if (var5.getCountOfReturns() == 1 && var5.getReturnExpression(0) instanceof IEVar) {
               IEVar var6 = (IEVar)var5.getReturnExpression(0);
               if (var6 != this.ectx.getStackPointer() && var6 != this.ectx.getProgramCounter()) {
                  AddressableInstruction var7 = (AddressableInstruction)var2.next();
                  if (var7.getInstruction() instanceof IECall) {
                     var4 = var7;
                     var3 = true;
                  } else if (var7.getInstruction() instanceof IEAssign) {
                     IEAssign var8 = (IEAssign)var7.getInstruction();
                     if (var8.getSrcOperand() == var6 && var8.getDstOperand() instanceof IEVar) {
                        IEVar var9 = (IEVar)var8.getDstOperand();
                        long var10 = var4.getOffset();
                        long var12 = var7.getOffset();
                        IDFA var14 = this.cfg.doDataFlowAnalysis();
                        if (var14.checkSingleDef(var12, var6.getId()) != null
                           && var14.checkSingleUse(var10, var6.getId()) != null
                           && this.getMasterOptimizerSafe().canDiscardUnusedDefinition(this.ectx, var10, var6.getId())
                           && var5.replaceDefinedVar(var6, var9) > 0) {
                           IENop var15 = this.ectx.createNop(var8);
                           this.cfg.replaceInstruction(var12, var15);
                           this.cfg.invalidateDataFlowAnalysis();
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      return var1;
   }
}
