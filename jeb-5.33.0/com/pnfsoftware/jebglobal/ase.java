package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;

public class ase extends AbstractEOptimizer {
   public static boolean pC;

   @Override
   protected int perform() {
      if (pC) {
         return 0;
      } else {
         int var1 = 0;

         for (int var2 = 0; var2 < this.cfg.size() - 1; var2++) {
            BasicBlock var3 = this.cfg.get(var2);
            IEStatement var4 = (IEStatement)var3.getLast();
            if (var4 instanceof IECall) {
               IECall var5 = var4.asCall();
               if (var5.getReturnLocation() instanceof IEImm) {
                  IEImm var6 = var5.getReturnLocation().asImm();
                  if (var6.canReadAsAddress()) {
                     long var7 = var6.getValueAsAddress();
                     CodePointer var9 = this.ectx.getNativeContext().getProcessor().createEntryPoint(var7);
                     if (var9 != null) {
                        var7 = var9.getAddress();
                     }

                     IEStatement var10 = (IEStatement)this.cfg.get(var2 + 1).get(0);
                     if (var10.getLowerLevelAddresses().contains(var7)) {
                        var5.setReturnLocation(null);
                        var1++;
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
}
