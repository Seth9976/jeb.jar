package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class cfc extends AbstractEOptimizer {
   public cfc() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (IEStatement var3 : this.cfg.get(0)) {
         if (var3.isAssign()) {
            IEAssign var4 = var3.asAssign();
            if (var4.getDstOperand().isVar() && var4.getSrcOperand().isImm()) {
               IEVar var5 = var4.getDstOperand().asVar();
               IEImm var6 = var4.getSrcOperand().asImm();
               if (var5.isStackVariable() && var6.canReadAsAddress() && !var5.hasFlags(32)) {
                  long var7 = var6.getValueAsAddress();
                  if (var7 != 0L) {
                     long var9 = this.ectx.getNativeContext().getVirtualImageBase();
                     long var11 = var9 + this.ectx.getNativeContext().getImageSize();
                     if (var7 >= var9 && var7 < var11 + 65536L) {
                        var5.addFlags(32);
                        var1++;
                        break;
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
