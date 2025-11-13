package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ati extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(ati.class);

   public ati() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      return this.q();
   }

   public int q() {
      int var1 = 0;
      IDFA var2 = this.cfg.doDataFlowAnalysis();
      HashSet var3 = new HashSet();
      IEVar var4 = this.ectx.getConverter().getReturnAddressRegister();
      Set var5 = null;
      if (var4 != null) {
         var5 = this.ectx.getCopiesOfVariable(var4.getId());
         var5.add(var4.getId());
      }

      for (BasicBlock var7 : this.cfg.getBlocks()) {
         int var8 = 0;
         long var9 = var7.getFirstAddress();
         int var11 = 0;

         while (var8 < var7.size()) {
            IEStatement var12 = (IEStatement)var7.get(var8);
            var9 += var11;
            var11 = var12.getSize();
            if (!(var12 instanceof IEAssign)) {
               var8++;
            } else {
               IEAssign var13 = (IEAssign)var7.get(var8);
               IEGeneric var14 = var13.getLeftOperand();
               if (!(var13.getRightOperand() instanceof IEImm var16)) {
                  var8++;
               } else {
                  IEVar var17 = null;
                  if (var14 instanceof IEVar) {
                     var17 = (IEVar)var14;
                  } else {
                     if (var14.isMem()) {
                        IEGeneric var18 = var14.asMem().getReference();
                        if (var18.isVar() && var18.asVar().isStackReference()) {
                           long var19 = var18.asVar().getAddress();
                           IEVar var21 = this.ectx.getStackVariable((int)var19);
                           if (var21 != null && var21.getBitsize() <= var14.getBitsize()) {
                              var17 = var21;
                              var16 = var16.truncate(var21.getBitsize());
                           }
                        }
                     }

                     if (var17 == null) {
                        var8++;
                        continue;
                     }
                  }

                  if (this.ectx.getProgramCounterId() == var17.getId()) {
                     var8++;
                  } else if (var5 != null && var5.contains(var17.getId())) {
                     var8++;
                  } else {
                     int var34 = var17.getId();
                     Collection var35 = var2.getDefUses(var9, var34);
                     if (var35 != null && !var35.isEmpty()) {
                        var3.clear();
                        var3.addAll(var35);

                        for (long var36 : var3) {
                           Couple var23 = this.cfg.getInstructionLocation(var36);
                           if (var23 == null) {
                              RuntimeException var37 = new RuntimeException(
                                 Strings.ff("Cannot find block having address 0x%X (dfa.maxblk=%d)", var36, var2.getMaxBlocks())
                              );
                              aeb.q(var37, this.ectx);
                           } else {
                              BasicBlock var24;
                              int var25;
                              boolean var27;
                              var24 = (BasicBlock)var23.getFirst();
                              var25 = (Integer)var23.getSecond();
                              Collection var26 = var2.getUseDefs(var36, var34);
                              var27 = false;
                              label110:
                              if (var26 != null) {
                                 Iterator var28 = var26.iterator();

                                 while (true) {
                                    if (!var28.hasNext()) {
                                       break label110;
                                    }

                                    long var29 = (Long)var28.next();
                                    if (var29 != var9) {
                                       IEStatement var31 = (IEStatement)this.cfg.getInstruction(var29);
                                       if (!(var31 instanceof IEAssign)) {
                                          break;
                                       }

                                       IEGeneric var32 = ((IEAssign)var31).getLeftOperand();
                                       IEGeneric var33 = ((IEAssign)var31).getRightOperand();
                                       if (!(var32 instanceof IEVar) || !(var33 instanceof IEImm) || !amw.q(var32, var17) || !amw.q(var33, var16)) {
                                          break;
                                       }
                                    } else {
                                       var27 = true;
                                    }
                                 }

                                 var27 = false;
                              }

                              if (var27) {
                                 IEStatement var38 = (IEStatement)var24.get(var25);
                                 if (EUtil.hasExplicitlyUsedVar(var38, var17)) {
                                    IEImm var39;
                                    if (EUtil.hasTypeInfo(var17)) {
                                       var39 = var16.duplicateWithType(var17.getType());
                                    } else {
                                       var39 = var16.duplicateToMutable();
                                    }

                                    if (var38.replaceUsedVar(var17, var39) > 0) {
                                       var38.copyLowerLevelAddresses(var13);
                                       this.cfg.invalidateDataFlowAnalysis();
                                       var1++;
                                    }
                                 }
                              }
                           }
                        }

                        var8++;
                     } else {
                        var8++;
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
