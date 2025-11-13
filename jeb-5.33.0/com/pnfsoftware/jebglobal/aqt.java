package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class aqt extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(aqt.class);
   private static long A = -1L;

   public aqt() {
      this.getPluginInformation().setDescription(S.L("Constants propagation"));
   }

   @Override
   public int perform() {
      long var1 = System.currentTimeMillis();
      int var3 = 0;
      IDFA var4 = this.cfg.doDataFlowAnalysis();
      IEVar var5 = this.ectx.getConverter().getReturnAddressRegister();
      Set var6 = null;
      if (var5 != null) {
         var6 = this.ectx.getCopiesOfVariable(var5.getId());
         var6.add(var5.getId());
      }

      for (BasicBlock var8 : this.cfg.getBlocks()) {
         int var9 = 0;
         long var10 = var8.getAddress();
         int var12 = 0;

         while (var9 < var8.size()) {
            IEStatement var13 = (IEStatement)var8.get(var9);
            var10 += var12;
            var12 = var13.getSize();
            if (var13 instanceof IEAssign var14) {
               IEGeneric var15 = var14.getLeftOperand();
               if (var14.getRightOperand() instanceof IEImm var17) {
                  IEVar var18 = null;
                  if (var15 instanceof IEVar) {
                     var18 = (IEVar)var15;
                  } else {
                     if (var15 instanceof IEMem var19 && var19.getReference() instanceof IEVar var21 && var21.isStackReference()) {
                        long var22 = var21.getAddress();
                        IEVar var24 = this.ectx.getStackVariable((int)var22);
                        if (var24 != null && var24.getBitsize() <= var15.getBitsize()) {
                           var18 = var24;
                           var17 = var17.truncate(var24.getBitsize());
                        }
                     }

                     if (var18 == null) {
                        var9++;
                        continue;
                     }
                  }

                  if (this.ectx.getProgramCounterId() == var18.getId()) {
                     var9++;
                  } else if (var6 != null && var6.contains(var18.getId())) {
                     var9++;
                  } else {
                     int var37 = var18.getId();
                     Collection var38 = var4.getDefUses(var10, var37);
                     int var39 = 0;

                     for (long var23 : var38) {
                        if (A >= 0L && var3 == 0 && var39 == 0 && System.currentTimeMillis() - var1 >= A) {
                           return 0;
                        }

                        Couple var25 = this.cfg.getInstructionLocation(var23);
                        if (var25 == null) {
                           RuntimeException var41 = new RuntimeException(
                              Strings.ff("Cannot find block having address 0x%X (dfa.maxblk=%d)", var23, var4.getMaxBlocks())
                           );
                           acj.pC(var41, this.ectx);
                        } else {
                           BasicBlock var26 = (BasicBlock)var25.getFirst();
                           int var27 = (Integer)var25.getSecond();
                           Collection var28 = var4.getUseDefs(var23, var37);
                           boolean var29 = false;
                           Iterator var30 = var28.iterator();

                           while (true) {
                              if (var30.hasNext()) {
                                 long var31 = (Long)var30.next();
                                 if (var31 == var10) {
                                    var29 = true;
                                    continue;
                                 }

                                 IEStatement var33 = (IEStatement)this.cfg.getInstruction(var31);
                                 if (var33 instanceof IEAssign var34) {
                                    IEGeneric var35 = var34.getLeftOperand();
                                    IEGeneric var36 = var34.getRightOperand();
                                    if (var35 instanceof IEVar && var36 instanceof IEImm && akt.pC(var35, var18) && akt.pC(var36, var17)) {
                                       continue;
                                    }
                                 }

                                 var29 = false;
                              }

                              if (var29) {
                                 IEStatement var42 = (IEStatement)var26.get(var27);
                                 if (EUtil.hasExplicitlyUsedVar(var42, var18)) {
                                    IEImm var43;
                                    if (EUtil.hasTypeInfo(var18)) {
                                       var43 = var17.duplicateWithType(var18.getType());
                                    } else {
                                       var43 = var17.duplicateToMutable();
                                    }

                                    int var32 = var42.replaceUsedVar(var18, var43);
                                    if (var32 != 0) {
                                       var42.copyLowerLevelAddresses(var14);
                                       var4.invalidatePostSimpleSubstitutionWithMultiDefs(var28, var23, var37);
                                       var39 += var32;
                                    }
                                 }
                              }
                              break;
                           }
                        }
                     }

                     if (var39 > 0) {
                        var3++;
                     }

                     var9++;
                  }
               } else {
                  var9++;
               }
            } else {
               var9++;
            }
         }
      }

      if (var3 > 0) {
         this.ectx.invalidateDataFlowAnalysis();
      }

      return var3;
   }
}
