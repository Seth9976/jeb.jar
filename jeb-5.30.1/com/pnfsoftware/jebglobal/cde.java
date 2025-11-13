package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class cde extends AbstractDOptimizer {
   @Override
   public int perform() {
      ArrayList var1 = new ArrayList();

      for (BasicBlock var3 : this.cfg) {
         this.checkInterrupted();
         int var4 = 0;

         while (var4 < var3.size() - 1) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (!(var5.isAssignToVar() && var5.getAssignSource() instanceof IDNewArrayInfo var6)) {
               var4++;
            } else if (var6.getSize() instanceof IDImm var33 && var6.getInitialValues().isEmpty()) {
               IDVar var34 = var5.getAssignDestination().asVar();
               int var9 = (int)var33.getRawValue();
               if (var9 > 0 && var9 < 1000) {
                  int var10 = var34.getId();
                  this.analyzeChains(false);
                  Collection var11 = this.dfa.getInstructionDefs(var5.getOffset());
                  if (var11.size() == 1 && (Integer)var11.iterator().next() == var10) {
                     ArrayList var12 = new ArrayList(var9);
                     int var13 = 0;
                     boolean var14 = true;
                     boolean var15 = false;
                     boolean var16 = false;
                     int var17 = 0;
                     ArrayList var19 = new ArrayList();
                     ArrayList var20 = new ArrayList();

                     int var18;
                     for (var18 = var4 + 1; var18 < var3.size() && var17 < var9; var18++) {
                        IDInstruction var21 = (IDInstruction)var3.get(var18);
                        if (var21.isAssignToVar() && var21.getAssignSource() instanceof IDNewArrayInfo) {
                           break;
                        }

                        if (var21.isAssign() && var21.getAssignDestination() instanceof IDArrayElt var22) {
                           IDExpression var42 = var22.getArray();
                           if (!(var42 instanceof IDVar) || !var42.equals(var34) || !(var22.getIndex() instanceof IDImm var25)) {
                              break;
                           }

                           int var26 = (int)var25.getRawValue();
                           if (var26 != var13) {
                              var16 = true;
                              var13 = -1;
                              if (var15) {
                                 var14 = false;
                                 break;
                              }
                           }

                           if (var26 < 0 || var26 >= var9) {
                              var14 = false;
                              break;
                           }

                           IDExpression var27 = (IDExpression)var21.getOperand2();
                           if (var27.getVarIds().contains(var10)) {
                              var14 = false;
                              break;
                           }

                           if (!var15 && var27.hasSideEffects(this.ctx, true)) {
                              var15 = true;
                              if (var16) {
                                 var14 = false;
                                 break;
                              }
                           } else if (var15 && var16) {
                              var14 = false;
                              break;
                           }

                           if (!var16) {
                              var12.add(var27);
                              var13++;
                           } else {
                              while (var12.size() <= var26) {
                                 var12.add(null);
                              }

                              var12.set(var26, var27);
                           }

                           var20.add(var26);
                           var19.add(var18);
                           var17++;
                        } else {
                           var20.add(-1);
                        }
                     }

                     if (var17 == 0 || var17 < var9 / 2) {
                        var14 = false;
                     }

                     if (var14) {
                        for (int var35 = var20.size() - 1; var35 >= 0 && var20.get(var35) < 0; var35--) {
                           var20.remove(var35);
                        }

                        boolean var36 = false;

                        for (int var38 = 0; var38 < var20.size(); var38++) {
                           if ((Integer)var20.get(var38) < 0) {
                              var36 = true;
                              break;
                           }
                        }

                        if (var36) {
                           int var39 = var4 + 1;

                           for (int var44 : var20) {
                              IDInstruction var45 = (IDInstruction)var3.get(var39);
                              if (var44 < 0) {
                                 if (var45.getVarIds().contains(var10)) {
                                    var14 = false;
                                    break;
                                 }
                              } else {
                                 IDExpression var46 = var45.getAssignSource();
                                 if (!(var46 instanceof IDImm) && (!(var46 instanceof IDStaticField var47) || !var47.isTypeClass())) {
                                    var14 = false;
                                    break;
                                 }
                              }

                              var39++;
                           }
                        }
                     }

                     if (var14) {
                        while (var12.size() < var9) {
                           var12.add(null);
                        }

                        IJavaType var37 = var6.getType().getArrayTypeDimensionBelow();

                        for (int var40 = 0; var40 < var9; var40++) {
                           if (var12.get(var40) == null) {
                              var12.set(var40, this.g.createConstant(0L, var37));
                           }
                        }

                        cde.eo var41 = new cde.eo();
                        var41.q = var3;
                        var41.RF = var19;
                        var41.xK = var6;
                        var41.Dw = var9;
                        var41.Uv = var12;
                        var1.add(var41);
                     }

                     var4 = var18;
                  } else {
                     var4++;
                  }
               } else {
                  var4++;
               }
            } else {
               var4++;
            }
         }
      }

      Lists.reverse(var1);

      for (cde.eo var30 : var1) {
         var30.xK.setInitialValues(var30.Uv);

         for (int var32 : Lists.reverse(var30.RF)) {
            DUtil.removeInstruction(var30.q, var32);
         }
      }

      int var29 = var1.size();
      if (var29 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var29;
   }

   private static class eo {
      BasicBlock q;
      List RF;
      IDNewArrayInfo xK;
      int Dw;
      List Uv;
   }
}
