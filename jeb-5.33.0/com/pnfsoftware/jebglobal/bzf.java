package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collection;
import java.util.Iterator;

public class bzf extends AbstractDOptimizer {
   @Override
   public int perform() {
      if (this.ctx.getExceptionData().isEmpty()) {
         return 0;
      } else {
         int var1 = 0;
         Iterator var2 = this.cfg.iterator();

         label173:
         while (true) {
            IDInstruction var5;
            IDVar var6;
            BasicBlock var7;
            IDInstruction var22;
            IDVar var23;
            while (true) {
               BasicBlock var3;
               int var4;
               while (true) {
                  if (!var2.hasNext()) {
                     return var1;
                  }

                  var3 = (BasicBlock)var2.next();
                  if (var3.irroutsize() != 0) {
                     var4 = var3.size() - 1;
                     if (((IDInstruction)var3.getLast()).isJump()) {
                        var4--;
                     }

                     if (var4 >= 0) {
                        var5 = (IDInstruction)var3.get(var4);
                        if (var5.isAssignToVar() && var5.canThrow() && !DUtil.canThrow(var3, 0, var4)) {
                           var6 = var5.getAssignDestination().asVar();
                           var7 = var3.getOutputBlock(0);
                           if (var7.insize() == 1) {
                              if (--var4 >= 0) {
                                 break;
                              }

                              BasicBlock var8;
                              if (var3.insize() == 1 && (var8 = var3.getInputBlock(0)).outsize() == 1) {
                                 var3 = var8;
                                 var4 = var8.size() - 1;
                                 break;
                              }
                           }
                        }
                     }
                  }
               }

               var22 = (IDInstruction)var3.get(var4);
               if (var22.isAssignToVar() && var22.getAssignSource() instanceof IDImm var9 && var9.isZero()) {
                  var23 = var22.getAssignDestination().asVar();
                  if (var23 != var6) {
                     break;
                  }

                  if (--var4 >= 0) {
                     IDInstruction var11 = (IDInstruction)var3.get(var4);
                     if (var11.isAssignToVar() && var11.getAssignSource() instanceof IDImm var12 && var12.isZero()) {
                        IDVar var27 = var11.getAssignDestination().asVar();
                        if (var27 != var23) {
                           var22 = var11;
                           var23 = var27;
                           break;
                        }
                     }
                  }
               }
            }

            this.analyzeChains();
            if (!this.dfa.isAlive(var7, 0, var23.getId())) {
               Collection var24 = this.dfa.getDefUses(var22.getOffset(), var23.getId());

               for (long var28 : var24) {
                  for (long var16 : this.dfa.getUseDefs(var28, var23.getId())) {
                     IDInstruction var18 = (IDInstruction)this.cfg.getInstruction(var16);
                     if (var18 != var22) {
                        if (var18 == null || !var18.isAssignToVar() || var18.getAssignDestination() != var23 || var18.getAssignSource() != var6) {
                           continue label173;
                        }

                        Long var19 = this.dfa.checkSingleDef(var16, var6.getId());
                        if (var19 == null || var19 != var5.getOffset() || !this.dfa.isVarReachingFromTo(var6.getId(), var18.getOffset(), var28)) {
                           continue label173;
                        }
                     }
                  }
               }

               if (var22.replaceDefinedVariable(var23, var6) != 0) {
                  for (long var29 : var24) {
                     IDInstruction var30 = (IDInstruction)this.cfg.getInstruction(var29);
                     if (var30.replaceVariable(var23, var6) == 0) {
                        RuntimeException var31 = new RuntimeException("Failed used-var replacement");
                        com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var31, this.ctx.getMethodSignature());
                     }
                  }

                  this.cfg.invalidateDataFlowAnalysis();
                  var1++;
               }
            }
         }
      }
   }
}
