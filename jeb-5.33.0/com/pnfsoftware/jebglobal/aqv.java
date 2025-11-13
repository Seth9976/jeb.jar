package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.DUI;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

public class aqv extends AbstractEOptimizer {
   private static final StructuredLogger ah = aco.pC(aqv.class);
   private static long eP = -1L;
   public static long pC = 5000L;
   public static int A = 20;
   private boolean UO = true;
   private boolean Ab = true;
   int kS;
   int wS;
   int UT;
   int E;
   int sY;
   int ys;
   int ld;
   int gp;
   int oT;
   long fI;
   IdentityHashMap WR = new IdentityHashMap();
   int NS;
   int vP;
   IdentityHashMap xC = new IdentityHashMap();
   int ED;
   int Sc;
   private long rl;
   private long z = pC;
   private int Ek = A;
   private boolean hK;

   public aqv() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
      this.setPriority(20.0);
   }

   private boolean pC() {
      return acp.A ? false : this.z >= 0L && System.currentTimeMillis() - this.rl >= this.z;
   }

   @Override
   protected int perform() {
      this.rl = System.currentTimeMillis();

      int var1;
      try {
         var1 = this.kS();
      } finally {
         this.A();
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean pC(IEStatement var1) {
      Boolean var2 = (Boolean)this.WR.get(var1);
      if (var2 == null) {
         var2 = var1.findByType(IEMem.class) != null;
         this.WR.put(var1, var2);
         this.NS++;
      } else {
         this.vP++;
      }

      return var2;
   }

   private Set pC(IEGeneric var1) {
      Set var2 = (Set)this.xC.get(var1);
      if (var2 == null) {
         var2 = EUtil.getUsedVarIds(var1);
         this.xC.put(var1, var2);
         this.ED++;
      } else {
         this.Sc++;
      }

      return var2;
   }

   private void A() {
      this.WR.clear();
      this.xC.clear();
   }

   private int kS() {
      long var1 = System.currentTimeMillis();
      int var3 = 0;
      HashSet var4 = new HashSet();
      this.A();
      Set var5 = null;

      int var8;
      for (BasicBlock var7 : this.cfg.getBlocks()) {
         do {
            acj.pC();
            var8 = 0;
            long var9 = var7.getFirstAddress();
            int var11 = 0;
            int var12 = 0;

            label295:
            while (var12 < var7.size() - 1) {
               this.kS++;
               IEStatement var13 = (IEStatement)var7.get(var12);
               var9 += var11;
               var11 = var13.getSize();
               if (var13 instanceof IEAssign var14) {
                  if (EUtil.isSPAssignOrPCAssign(var14)) {
                     var12++;
                  } else if ((var14.getFlags() & 1) != 0) {
                     var12++;
                  } else {
                     if (eP >= 0L && var3 == 0 && System.currentTimeMillis() - var1 >= eP || this.pC()) {
                        return var3;
                     }

                     if (var14.getDstOperand() instanceof IEVar var15) {
                        IEGeneric var37 = var14.getSrcOperand();
                        if (var15 == var37) {
                           IENop var38 = this.ectx.createNop(var14);
                           var7.set(var12, var38);
                           var3++;
                           var12++;
                        } else {
                           int var17 = var15.getId();
                           this.wS++;
                           if (!this.Ab) {
                              if (var5 == null) {
                                 var5 = this.wS();
                              }

                              if (var5.contains(var17)) {
                                 if (!this.getMasterOptimizerSafe().canDiscardReachingOutDefinition(this.ectx, var9, var17)) {
                                    var12++;
                                    continue;
                                 }

                                 var5.remove(var17);
                              }
                           }

                           IDFA var18 = this.cfg.doDataFlowAnalysis();
                           if (var18.getInstructionAllDefs(var9).equals(List.of(var17))) {
                              var12++;
                           } else {
                              Collection var19;
                              if (this.Ek >= 0 && !(var37 instanceof IEVar) && !(var37 instanceof IEImm)) {
                                 var19 = var18.getBlockDefUses(var9, var17, this.Ek);
                                 if (var19.size() >= this.Ek) {
                                    var12++;
                                    continue;
                                 }
                              } else {
                                 var19 = var18.getBlockDefUses(var9, var17);
                              }

                              if (var19.contains(-2L)) {
                                 var12++;
                              } else {
                                 if (this.pC()) {
                                    return var3;
                                 }

                                 boolean var20 = true;
                                 var4.clear();
                                 int var21 = Integer.MIN_VALUE;

                                 for (Long var23 : var19) {
                                    if (var23 < 0L) {
                                       var12++;
                                       continue label295;
                                    }

                                    Couple var24 = this.cfg.getInstructionLocation(var23);
                                    if (var24.getFirst() != var7) {
                                       var12++;
                                       continue label295;
                                    }

                                    int var25 = (Integer)var24.getSecond();
                                    if ((((IEStatement)var7.get(var25)).getFlags() & 2) != 0) {
                                       var12++;
                                       continue label295;
                                    }

                                    var4.add(var25);
                                    if (var25 > var21) {
                                       var21 = var25;
                                    }
                                 }

                                 if (var4.isEmpty()) {
                                    var12++;
                                 } else {
                                    this.UT++;
                                    if (this.getMasterOptimizerSafe().getMode() == OptimizerMode.NORMAL) {
                                       IEVar[] var39 = new IEVar[2];

                                       for (long var43 : var19) {
                                          IEStatement var26 = (IEStatement)this.cfg.getInstruction(var43);
                                          if (var26 instanceof IEAssign var27
                                             && var27.getSrcOperand() instanceof IECompose var28
                                             && aov.pC(this.ectx, var28, var39)
                                             && (var39[0].getId() == var17 || var39[1].getId() == var17)) {
                                             var12++;
                                             continue label295;
                                          }
                                       }
                                    }

                                    boolean var40 = false;
                                    if (!this.getMasterOptimizerSafe().canDiscardReachingOutDefinition(this.ectx, var9, var17)) {
                                       if (var18.getOutputMap(var17).contains(var9)) {
                                          if (!this.Ab) {
                                             var12++;
                                             continue;
                                          }

                                          var40 = true;
                                       } else if (!this.getMasterOptimizerSafe().canDiscardUnusedDefinition(this.ectx, var9, var17)) {
                                          var12++;
                                          continue;
                                       }
                                    }

                                    this.E++;
                                    if (var40 && var18.getInstructionAllUses(var9).contains(var17)) {
                                       var12++;
                                    } else {
                                       Set var42 = this.pC(var37);
                                       if (!var42.isEmpty()) {
                                          for (Integer var46 : var42) {
                                             if (!Booleans.toBoolean(var18.isVarReachingFromTo(var46, var7, var12, var7, var21))) {
                                                var12++;
                                                continue label295;
                                             }

                                             if (this.pC()) {
                                                return var3;
                                             }
                                          }
                                       }

                                       this.sY++;
                                       int var45 = this.pC(var15, var37, var7, var4);
                                       if (var45 == 0) {
                                          Object[] var10000 = new Object[0];
                                          var20 = false;
                                       }

                                       if (!this.A(var7, var12, var21, var15, var37, var45, var4)) {
                                          var20 = false;
                                       }

                                       boolean var47 = this.pC((IEStatement)var14);
                                       if (var47 && var20 && !this.pC(var7, var12, var21, var15, var37, var45, var4)) {
                                          var20 = false;
                                       }

                                       if (!var20) {
                                          var12++;
                                       } else {
                                          this.ys++;
                                          HashSet var48 = new HashSet();

                                          for (int var53 : var4) {
                                             IEStatement var55 = (IEStatement)var7.get(var53);
                                             if (!EUtil.hasExplicitlyUsedVar(var55, var15)) {
                                                if (!this.UO) {
                                                   var12++;
                                                   continue label295;
                                                }
                                             } else {
                                                if (!var18.checkSingleDef(var7.getAddressOfInstruction(var53), var17, var9)) {
                                                   var12++;
                                                   continue label295;
                                                }

                                                var48.add(var53);
                                                if (this.pC()) {
                                                   return var3;
                                                }
                                             }
                                          }

                                          this.ld++;
                                          if (var48.isEmpty()) {
                                             var12++;
                                          } else {
                                             if (!this.getMasterOptimizerSafe().getMode().isUnfriendly()) {
                                                for (Integer var54 : var48) {
                                                   IEStatement var56 = (IEStatement)var7.get(var54);
                                                   if (var56 instanceof IEReturn) {
                                                      var12++;
                                                      continue label295;
                                                   }
                                                }
                                             }

                                             this.gp++;
                                             if (var48.size() != var4.size()) {
                                                Object[] var62 = new Object[0];
                                                IdRanges var51 = var37.getUsed();
                                                if (var51.containsVarPart(var15)) {
                                                   var62 = new Object[0];
                                                   var12++;
                                                   continue;
                                                }
                                             }

                                             this.oT++;
                                             long var52 = System.nanoTime();
                                             var18.invalidateForSubstitution(var9, var19, null);
                                             int var57 = 0;

                                             for (Integer var31 : var48) {
                                                IEStatement var32 = (IEStatement)var7.get(var31);
                                                var7.getAddressOfInstruction(var31);
                                                Object var35;
                                                if (var37 instanceof IEImm var36) {
                                                   if (EUtil.hasTypeInfo(var15)) {
                                                      var35 = var36.duplicateWithType(var15.getType());
                                                   } else {
                                                      var35 = var36.duplicateToMutable();
                                                   }
                                                } else {
                                                   var35 = var37.duplicate();
                                                }

                                                int var61 = var32.replaceUsedVar(var15, (IEGeneric)var35);
                                                if (var61 != 0) {
                                                   var32.copyLowerLevelAddresses(var14);
                                                   var57 += var61;
                                                }
                                             }

                                             boolean var58 = var48.size() == var4.size();
                                             if (!var40 && var58) {
                                                IENop var59 = this.ectx.createNop(var14);
                                                var7.set(var12, var59);
                                                var57++;
                                             }

                                             var12++;
                                             if (var57 != 0) {
                                                this.A();
                                                if (++var8 % 50 == 0) {
                                                   acj.pC();
                                                }

                                                long var60 = System.nanoTime();
                                                long var33 = (var60 - var52) / 1000000L;
                                                this.fI += var33;
                                                var3++;
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     } else {
                        var12++;
                     }
                  }
               } else {
                  var12++;
               }
            }
         } while (var8 > 0);
      }

      return var3;
   }

   protected int pC(IEVar var1, IEGeneric var2, BasicBlock var3, Set var4) {
      int var5 = 0;

      for (int var7 : var4) {
         IEStatement var8 = (IEStatement)var3.get(var7);
         int var9 = EUtil.countVariablePresence(var8, var1);
         var5 += this.pC(var1, var2, var8, var9, var5);
      }

      return var5;
   }

   protected int pC(IEVar var1, IEGeneric var2, IEStatement var3, int var4, int var5) {
      if (var3 instanceof IEAssign var6 && var6.getLeftOperand().equals(var1)) {
         var4--;
      }

      if (var3 instanceof IECall var14) {
         if (var14.getReturnExpressions().contains(var1)) {
            var4--;
         }

         if (var14.getSpoiledExpressions().contains(var1)) {
            var4--;
         }
      }

      if (var3 instanceof IEUntranslatedInstruction var15) {
         if (var15.getReturnExpression() == var1) {
            var4--;
         }

         if (var15.getSideEffectDefinedVariables().contains(var1)) {
            var4--;
         }
      }

      if (var4 > 1 && var2 instanceof IECompose var16) {
         int[] var7 = new int[var16.getPartsCount()];
         ArrayList var8 = new ArrayList();
         int var9 = 0;

         for (IEGeneric var11 : var16) {
            int var12 = var9 + var11.getBitsize();
            var8.add(var1.slice(var9, var12));
            var9 = var12;
         }

         EUtil.countExpressionsPresence(var3, var8, var7);
         var4 = 0;

         for (int var13 : var7) {
            var4 = var13 > var4 ? var13 : var4;
         }
      }

      return var4;
   }

   public boolean pC(BasicBlock var1, int var2, int var3, IEVar var4, IEGeneric var5, int var6, Set var7) {
      for (int var8 = var2 + 1; var8 < var3; var8++) {
         IEStatement var9 = (IEStatement)var1.get(var8);
         if (var9.writesMemory()) {
            return false;
         }
      }

      return true;
   }

   public boolean A(BasicBlock var1, int var2, int var3, IEVar var4, IEGeneric var5, int var6, Set var7) {
      for (Integer var9 : var7) {
         IEStatement var10 = (IEStatement)var1.get(var9);
         if (var10 instanceof IEAssign var11 && var11.getLeftOperand() instanceof IESlice var13) {
            IEGeneric var14 = var13.getWholeExpression();
            if (var14 == var4) {
               return false;
            }
         }
      }

      if (var6 <= 1) {
         return true;
      } else if (!(var5 instanceof IEVar) && !(var5 instanceof IEImm)) {
         if (var5 instanceof IESlice var15) {
            IEGeneric var16 = var15.getWholeExpression();
            if (var16 instanceof IEVar || var16 instanceof IEImm) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private Set wS() {
      IDFA var1 = this.cfg.doDataFlowAnalysis();

      for (BasicBlock var3 : this.cfg) {
         if (var3.outsize() == 0 && var1.isTerminator(var3)) {
            return Collections.emptySet();
         }
      }

      HashSet var10 = new HashSet();
      HashSet var11 = new HashSet();
      HashSet var4 = new HashSet();

      for (AddressableInstruction var6 : this.cfg.addressableInstructions()) {
         DUI var7 = var1.getDUI(var6);
         var4.clear();
         var4.addAll(var7.getDef());
         var4.addAll(var7.getDefPot());
         var4.addAll(var7.getSpoiled());

         for (Integer var9 : var4) {
            if (var10.contains(var9)) {
               var11.add(var9);
            }
         }

         var10.addAll(var4);
      }

      var10.removeAll(var11);
      return var10;
   }
}
