package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Collection;
import java.util.Map;

public class aox {
   private static final StructuredLogger RF = aeg.q(aox.class);
   static boolean q = true;
   private IERoutineContext xK;

   public aox(IERoutineContext var1) {
      this.xK = var1;
   }

   public int q() {
      int var1 = 0;
      CFG var2 = this.xK.getCfg();
      BasicBlock var3 = var2.get(0);
      IEStatement var4 = null;
      long var5 = var3.getFirstAddress();
      int var7 = 0;

      label120:
      while (true) {
         if (var4 != null) {
            var5 += var4.getSize();
            var7++;
         }

         if (var7 >= var3.size()) {
            break;
         }

         var4 = (IEStatement)var3.get(var7);
         if (!(var4 instanceof IENop)) {
            if (!(var4 instanceof IEAssign var8) || !(var8.getDstOperand() instanceof IEVar)) {
               break;
            }

            if (!(var8.getSrcOperand() instanceof IEVar)) {
               if (!(var8.getSrcOperand() instanceof IEImm)) {
                  break;
               }
            } else {
               IEVar var9 = (IEVar)var8.getSrcOperand();
               IEVar var10 = (IEVar)var8.getDstOperand();
               Integer var11 = this.xK.getUnderlyingRegisterId(var9.getId());
               if (var11 == null || !var10.isStackVariable()) {
                  break;
               }

               IDFA var12 = var2.doDataFlowAnalysis(false, 2);
               int var13 = var9.getId();
               Collection var14 = var12.getUseDefs(var3.getAddressOfInstruction(var7), var13, 2);
               if (var14.size() == 1 && (Long)var14.iterator().next() == -1L) {
                  var14 = var12.getInputMap(var13);
                  if (var14.size() == 1 && (Long)var14.iterator().next() == var5) {
                     Collection var15 = var12.getDefUses(var5, var10.getId());

                     for (long var17 : var15) {
                        if (((BasicBlock)var2.getInstructionLocation(var17).getFirst()).outsize() > 0 || !var12.checkSingleDef(var17, var10.getId(), var5)) {
                           continue label120;
                        }
                     }

                     Object[] var10000 = new Object[]{var5, var4};
                     var10.addFlags(32);
                     var4.addFlags(4);
                     var1++;

                     for (long var33 : var15) {
                        IEStatement var19 = (IEStatement)var2.getInstruction(var33);
                        if (var19 instanceof IEAssign && ((IEAssign)var19).getDstOperand() instanceof IEVar) {
                           IEVar var20 = (IEVar)((IEAssign)var19).getDstOperand();
                           Integer var21 = this.xK.getUnderlyingRegisterId(var20.getId());
                           if (var21 != null && var21 == var11) {
                              var10000 = new Object[]{var33, var19};
                              var19.addFlags(8);
                              var1++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (q) {
         IDFA var23 = var2.doDataFlowAnalysis(false, 2);
         Map var24 = var23.getInputMap();

         for (int var26 : var24.keySet()) {
            for (long var30 : (Collection)var24.get(var26)) {
               if (var30 >= 0L) {
                  var4 = (IEStatement)var2.getInstruction(var30);
                  if (var4 instanceof IEAssign && ((IEAssign)var4).getSrcOperand().isVar(var26)) {
                     IEGeneric var32 = ((IEAssign)var4).getDstOperand();
                     if (var32 instanceof IEVar && ((IEVar)var32).isStackVariable()) {
                        var32.addFlags(64);
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         var2.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
