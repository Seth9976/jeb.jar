package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Iterator;

@Deprecated
public class asa extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(asa.class);

   public asa() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      boolean var2 = false;

      for (BasicBlock var4 : this.cfg.getBlocks()) {
         if ((var4.getLast() instanceof IEJump || var4.getLast() instanceof IENop)
            && var4.outsize() == 1
            && (var4.irrinsize() == 0 || var4.irroutsize() == 0)
            && var4 != this.cfg.getEntryBlock()) {
            boolean var5 = true;

            for (int var6 = 0; var6 < var4.size() - 1; var6++) {
               if (!(var4.get(var6) instanceof IENop)) {
                  var5 = false;
                  break;
               }
            }

            if (var5 && !var4.isInfiniteLoop()) {
               AddressableInstruction var14 = var4.getLast2();
               if (var14.getBreakingFlow().isBroken() || var14.getInstruction() instanceof IENop) {
                  Object[] var10000 = new Object[]{var4.getFirstAddress()};
                  boolean var7 = true;

                  for (BasicBlock var9 : var4.getInputBlocks()) {
                     IEStatement var10 = (IEStatement)var9.getLast();
                     if (EUtil.isConditionalJump(var10)) {
                        if (EUtil.containsUndeterminedInvocations(((IEJump)var10).getCondition())) {
                           var7 = false;
                           break;
                        }
                     } else if (!(var10 instanceof IEJump)) {
                        var7 = false;
                        break;
                     }
                  }

                  if (!var7) {
                     var10000 = new Object[0];
                  } else {
                     BasicBlock var15 = var4.getOutputBlock(0);
                     boolean var16 = true;
                     Iterator var17 = var4.getInputBlocks().iterator();

                     while (true) {
                        if (var17.hasNext()) {
                           BasicBlock var11 = (BasicBlock)var17.next();
                           IEStatement var12 = (IEStatement)var11.getLast();
                           if (!(var12 instanceof IEJump)) {
                              continue;
                           }

                           if (((IEJump)var12).getCondition() != null) {
                              if (var11.getOutputBlock(0) == var4) {
                                 if (var11.getOutputBlock(1) == var15) {
                                    if (this.cfg.deleteEdge(var11, var4) && this.cfg.deleteEdge(var4, var15)) {
                                       EUtil.makeUncond((IEJump)var12);
                                       var2 = true;
                                       var10000 = new Object[]{var12};
                                       var1++;
                                    }
                                    continue;
                                 }

                                 var10000 = new Object[0];
                                 var16 = false;
                              } else {
                                 int var13 = this.q(var11, var4, var15);
                                 if (var13 == 1) {
                                    ((IEJump)var12).setBranchAddress((int)var15.getFirstAddress());
                                    var10000 = new Object[]{var12};
                                    var1++;
                                    continue;
                                 }

                                 if (var13 == -1) {
                                    ((IEJump)var12).setBranchAddress((int)var15.getFirstAddress());
                                    EUtil.makeUncond((IEJump)var12);
                                    var2 = true;
                                    var10000 = new Object[]{var12};
                                    var1++;
                                    continue;
                                 }

                                 if (var13 != 0) {
                                    continue;
                                 }

                                 var16 = false;
                              }
                           } else {
                              int var18 = this.q(var11, var4, var15);
                              if (var18 == 1) {
                                 ((IEJump)var12).setBranchAddress((int)var15.getFirstAddress());
                                 var10000 = new Object[]{var12};
                                 var1++;
                                 continue;
                              }

                              if (var18 != 0) {
                                 continue;
                              }

                              var16 = false;
                           }
                        }

                        if (var16) {
                           this.cfg.removeBlock(var4);
                        }
                        break;
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1, var2);
   }

   private int q(BasicBlock var1, BasicBlock var2, BasicBlock var3) {
      Object[] var10000 = new Object[]{var1.getFirstAddress(), var3.getFirstAddress()};
      int var4 = this.cfg.reconnectEdge(var1, var2, var3);
      if (var4 == 0) {
         var10000 = new Object[0];
      }

      if (var4 == -1) {
         var10000 = new Object[0];
         this.cfg.deleteEdge(var1, var2);
         this.cfg.deleteEdge(var2, var3);
      }

      return var4;
   }
}
