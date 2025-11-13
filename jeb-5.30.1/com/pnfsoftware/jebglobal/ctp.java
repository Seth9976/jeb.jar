package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.List;

public class ctp extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(ctp.class);

   public ctp() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      int var2 = 0;

      while (var2 < this.cfg.size()) {
         BasicBlock var3 = this.cfg.get(var2);
         AddressableInstruction var4 = var3.getLast2();
         IEStatement var5 = (IEStatement)var4.getInstruction();
         if (var5 instanceof IECall var6 && var6.getCallSite() instanceof IEImm) {
            IEImm var7 = (IEImm)var6.getCallSite();
            Long var8 = var7.getValueAsAddress();
            var8 = this.ectx.convertNativeAddress(var8);
            if (var8 != null && var8.intValue() == var4.getOffset() + var5.getSize() && !(var6.getReturnLocation() instanceof IEImm)) {
               IENop var9 = this.ectx.createNop(var6);
               var9.setSize(var6.getSize());
               var3.set(var3.size() - 1, var9);
               BasicBlock var10 = this.cfg.getBlockAt(var3.getEndAddress());
               if (var10 != null && var10.allinsize() == 1 && var3.getOutputBlock(0) == var10) {
                  var3.addAll(var10.getInstructions());
                  this.cfg.deleteEdges(var3, var10);
                  List var11 = var10.getAllOutputBlocks();
                  int var12 = 0;

                  for (BasicBlock var14 : var11) {
                     this.cfg.addEdge(var3, var14, var12);
                     var12++;
                  }

                  this.cfg.removeBlock(var10);
                  var1++;
                  continue;
               }
            }
         }

         var2++;
      }

      return this.postPerform(var1);
   }
}
