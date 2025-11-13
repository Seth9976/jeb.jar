package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aqf extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(aqf.class);

   public aqf() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg.getBlocks()) {
         AddressableInstruction var4 = var3.getLast2();
         if (var4.getInstruction() instanceof IECall) {
            IECall var5 = (IECall)var4.getInstruction();
            if (var5.isTentativeCall()) {
               IEImm var6 = null;
               IEGeneric var7 = var5.getReturnLocation();
               if (var7 instanceof IEImm) {
                  var6 = (IEImm)var7;
               } else if (var7 instanceof IEVar) {
                  int var8 = ((IEVar)var7).getId();
                  IDFA var9 = this.cfg.doDataFlowAnalysis();
                  Long var10 = var9.checkSingleDef(var4.getOffset(), var8);
                  if (var10 != null) {
                     IEStatement var11 = (IEStatement)this.cfg.getInstruction(var10);
                     if (var11 instanceof IEAssign && var11.asAssign().getDstOperand() == var7 && var11.asAssign().getSrcOperand().isImm()) {
                        var6 = var11.asAssign().getSrcOperand().asImm();
                     }
                  }
               }

               if (var6 != null) {
                  long var13 = var6.getValueAsAddress();
                  Long var14 = this.ectx.convertNativeAddress(var13);
                  if (var14 != null) {
                     int var15 = (int)var4.getOffset() + var5.getSize();
                     if (var14.intValue() == var15) {
                        BasicBlock var12 = this.cfg.getBlockAt((long)var15);
                        if (var12 != null && this.cfg.addEdge(var3, var12)) {
                           var5.setTentativeCall(false);
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
