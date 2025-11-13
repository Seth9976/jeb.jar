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
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class atk extends AbstractEOptimizer {
   private static final StructuredLogger qa = aeg.q(atk.class);
   private boolean Hk = true;
   private boolean Me = true;
   int q;
   int RF;
   int xK;
   int Dw;
   int Uv;
   int oW;
   int gO;
   int nf;
   int gP;
   long za;
   IdentityHashMap lm = new IdentityHashMap();
   int zz;
   int JY;
   IdentityHashMap HF = new IdentityHashMap();
   int LK;
   int io;

   public atk() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
      this.setPriority(20.0);
   }

   public boolean q() {
      return this.Hk;
   }

   public void q(boolean var1) {
      this.Hk = var1;
   }

   public void RF(boolean var1) {
      this.Me = var1;
   }

   public boolean RF() {
      return this.Me;
   }

   @Override
   protected int perform() {
      int var1;
      try {
         var1 = this.Uv();
      } finally {
         this.Dw();
      }

      return var1;
   }

   private boolean q(IEStatement var1) {
      Boolean var2 = (Boolean)this.lm.get(var1);
      if (var2 == null) {
         var2 = var1.findByType(IEMem.class) != null;
         this.lm.put(var1, var2);
         this.zz++;
      } else {
         this.JY++;
      }

      return var2;
   }

   private Set q(IEGeneric var1) {
      Set var2 = (Set)this.HF.get(var1);
      if (var2 == null) {
         var2 = EUtil.getUsedVarIds(var1);
         this.HF.put(var1, var2);
         this.LK++;
      } else {
         this.io++;
      }

      return var2;
   }

   private void Dw() {
      this.lm.clear();
      this.HF.clear();
   }

   private int Uv() {
      int var1 = 0;
      HashSet var2 = new HashSet();
      this.Dw();
      boolean var3 = false;
      Set var4 = null;

      int var7;
      for (BasicBlock var6 : this.cfg.getBlocks()) {
         do {
            aeb.q();
            var7 = 0;
            long var8 = var6.getFirstAddress();
            int var10 = 0;
            int var11 = 0;

            label267:
            while (var11 < var6.size() - 1) {
               this.q++;
               IEStatement var12 = (IEStatement)var6.get(var11);
               var8 += var10;
               var10 = var12.getSize();
               if (!(var12 instanceof IEAssign var13)) {
                  var11++;
               } else if (EUtil.isSPAssignOrPCAssign(var12)) {
                  var11++;
               } else if ((var13.getFlags() & 1) != 0) {
                  var11++;
               } else {
                  IEGeneric var14 = var13.getLeftOperand();
                  IEGeneric var15 = var13.getRightOperand();
                  if (!(var14 instanceof IEVar var16)) {
                     var11++;
                  } else {
                     IEGeneric var17 = var15;
                     if (var16 == var15) {
                        IENop var38 = this.ectx.createNop(var13);
                        var6.set(var11, var38);
                        var1++;
                        var11++;
                     } else {
                        this.RF++;
                        if (!this.Me) {
                           if (var4 == null) {
                              var4 = this.oW();
                           }

                           if (var4.contains(var16.getId())) {
                              if (!this.getMasterOptimizerSafe().canDiscardReachingOutDefinition(this.ectx, var8, var16.getId())) {
                                 var11++;
                                 continue;
                              }

                              var4.remove(var16.getId());
                           }
                        }

                        IDFA var18 = this.cfg.getDataFlowAnalysis();
                        if (var18 == null) {
                           var18 = this.cfg.doDataFlowAnalysis();
                           var3 = true;
                        }

                        var18.setMaxBlocks(50);
                        Map var19 = var18.getDefUseChains(var8);
                        boolean var20 = true;
                        var2.clear();
                        int var21 = Integer.MIN_VALUE;

                        for (Integer var23 : var19.keySet()) {
                           for (Long var25 : (Collection)var19.get(var23)) {
                              if (var25 < 0L) {
                                 var11++;
                                 continue label267;
                              }

                              Couple var26 = this.cfg.getInstructionLocation(var25);
                              if (var26.getFirst() != var6) {
                                 var11++;
                                 continue label267;
                              }

                              int var27 = (Integer)var26.getSecond();
                              if ((((IEStatement)var6.get(var27)).getFlags() & 2) != 0) {
                                 var11++;
                                 continue label267;
                              }

                              var2.add(var27);
                              if (var27 > var21) {
                                 var21 = var27;
                              }
                           }
                        }

                        if (var2.isEmpty()) {
                           var11++;
                        } else {
                           this.xK++;
                           boolean var39 = false;

                           for (int var42 : var19.keySet()) {
                              if (!this.getMasterOptimizerSafe().canDiscardReachingOutDefinition(this.ectx, var8, var42)) {
                                 if (var18.getOutputMap(var42).contains(var8)) {
                                    if (!this.Me) {
                                       var11++;
                                       continue label267;
                                    }

                                    var39 = true;
                                    break;
                                 }

                                 if (!this.getMasterOptimizerSafe().canDiscardUnusedDefinition(this.ectx, var8, var42)) {
                                    var11++;
                                    continue label267;
                                 }
                              }
                           }

                           this.Dw++;
                           if (var39 && var18.getInstructionAllUses(var8).contains(var16.getId())) {
                              var11++;
                           } else {
                              Set var41 = this.q(var15);
                              if (!var41.isEmpty()) {
                                 for (Integer var45 : var41) {
                                    if (!Booleans.toBoolean(var18.isVarReachingFromTo(var45, var6, var11, var6, var21))) {
                                       var11++;
                                       continue label267;
                                    }
                                 }
                              }

                              this.Uv++;
                              int var44 = this.q(var16, var15, var6, var2);
                              if (var44 == 0) {
                                 Object[] var10000 = new Object[0];
                                 var20 = false;
                              }

                              if (!this.RF(var6, var11, var21, var16, var15, var44, var2)) {
                                 var20 = false;
                              }

                              boolean var46 = this.q((IEStatement)var13);
                              if (var46 && var20 && !this.q(var6, var11, var21, var16, var15, var44, var2)) {
                                 var20 = false;
                              }

                              if (!var20) {
                                 var11++;
                              } else {
                                 this.oW++;
                                 HashSet var47 = new HashSet();

                                 for (int var28 : var2) {
                                    IEStatement var29 = (IEStatement)var6.get(var28);
                                    if (!EUtil.hasExplicitlyUsedVar(var29, var16)) {
                                       if (!this.Hk) {
                                          var11++;
                                          continue label267;
                                       }
                                    } else {
                                       if (!var18.checkSingleDef(var6.getAddressOfInstruction(var28), var16.getId(), var8)) {
                                          var11++;
                                          continue label267;
                                       }

                                       var47.add(var28);
                                    }
                                 }

                                 this.gO++;
                                 if (var47.isEmpty()) {
                                    Object[] var68 = new Object[0];
                                    var11++;
                                 } else {
                                    if (!this.getMasterOptimizerSafe().getMode().isUnfriendly()) {
                                       for (Integer var52 : var47) {
                                          IEStatement var53 = (IEStatement)var6.get(var52);
                                          if (var53 instanceof IEReturn) {
                                             Object[] var59 = new Object[0];
                                             var11++;
                                             continue label267;
                                          }
                                       }
                                    }

                                    this.nf++;
                                    if (var47.size() != var2.size()) {
                                       Object[] var60 = new Object[0];
                                       IdRanges var50 = var15.getUsed();
                                       if (var50.containsVarPart(var16)) {
                                          var60 = new Object[0];
                                          var11++;
                                          continue;
                                       }
                                    }

                                    this.gP++;
                                    long var51 = System.nanoTime();
                                    int var54 = 0;

                                    for (Integer var31 : var47) {
                                       IEStatement var32 = (IEStatement)var6.get(var31);
                                       long var33 = var6.getAddressOfInstruction(var31);
                                       Object var35;
                                       if (var17 instanceof IEImm) {
                                          if (EUtil.hasTypeInfo(var16)) {
                                             var35 = ((IEImm)var17).duplicateWithType(var16.getType());
                                          } else {
                                             var35 = ((IEImm)var17).duplicateToMutable();
                                          }
                                       } else {
                                          var35 = var17.duplicate();
                                       }

                                       int var36 = var32.replaceUsedVar(var16, (IEGeneric)var35);
                                       if (var36 != 0) {
                                          var32.copyLowerLevelAddresses(var13);
                                          var18.notifyInstructionUpdate(var33);
                                          var54 += var36;
                                       }
                                    }

                                    boolean var55 = var47.size() == var2.size();
                                    if (!var39 && var55) {
                                       int var56 = amw.q(this.ectx, var6, var11);
                                       if (var56 != 0) {
                                          var54++;
                                       }

                                       if (var56 <= 0) {
                                          Object[] var63 = new Object[0];
                                          var11++;
                                       } else {
                                          Object[] var64 = new Object[0];
                                       }
                                    } else {
                                       if (var55) {
                                          Object[] var61 = new Object[0];
                                       } else {
                                          Object[] var62 = new Object[0];
                                       }

                                       var11++;
                                    }

                                    if (var54 != 0) {
                                       var18.notifyInstructionUpdate(var8);
                                       var3 = false;
                                       Object[] var65 = new Object[0];
                                       var65 = new Object[0];
                                       this.Dw();
                                       var1++;
                                       if (++var7 % 50 == 0) {
                                          aeb.q();
                                       }

                                       long var57 = System.nanoTime();
                                       long var58 = (var57 - var51) / 1000000L;
                                       this.za += var58;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         } while (var7 > 0);
      }

      if (var1 > 0 && !var3) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      IDFA var37 = this.cfg.getDataFlowAnalysis();
      if (var37 != null) {
         var37.setMaxBlocks(-1);
      }

      return var1;
   }

   protected int q(IEVar var1, IEGeneric var2, BasicBlock var3, Set var4) {
      int var5 = 0;

      for (int var7 : var4) {
         IEStatement var8 = (IEStatement)var3.get(var7);
         int var9 = EUtil.countVariablePresence(var8, var1);
         var5 += this.q(var1, var2, var8, var9, var5);
      }

      return var5;
   }

   protected int q(IEVar var1, IEGeneric var2, IEStatement var3, int var4, int var5) {
      if (var3 instanceof IEAssign && ((IEAssign)var3).getLeftOperand().equals(var1)) {
         var4--;
      }

      if (var3 instanceof IECall) {
         if (((IECall)var3).getReturnExpressions().contains(var1)) {
            var4--;
         }

         if (((IECall)var3).getSpoiledExpressions().contains(var1)) {
            var4--;
         }
      }

      if (var3 instanceof IEUntranslatedInstruction) {
         if (((IEUntranslatedInstruction)var3).getReturnExpression() == var1) {
            var4--;
         }

         if (((IEUntranslatedInstruction)var3).getSideEffectDefinedVariables().contains(var1)) {
            var4--;
         }
      }

      if (var4 > 1 && var2 instanceof IECompose) {
         int[] var6 = new int[var2.asCompose().getPartsCount()];
         ArrayList var7 = new ArrayList();
         int var8 = 0;

         for (IEGeneric var10 : var2.asCompose()) {
            int var11 = var8 + var10.getBitsize();
            var7.add(var1.slice(var8, var11));
            var8 = var11;
         }

         EUtil.countExpressionsPresence(var3, var7, var6);
         var4 = 0;

         for (int var12 : var6) {
            var4 = var12 > var4 ? var12 : var4;
         }
      }

      return var4;
   }

   public boolean q(BasicBlock var1, int var2, int var3, IEVar var4, IEGeneric var5, int var6, Set var7) {
      for (int var8 = var2 + 1; var8 < var3; var8++) {
         IEStatement var9 = (IEStatement)var1.get(var8);
         if (var9.writesMemory()) {
            return false;
         }
      }

      return true;
   }

   public boolean RF(BasicBlock var1, int var2, int var3, IEVar var4, IEGeneric var5, int var6, Set var7) {
      for (Integer var9 : var7) {
         IEStatement var10 = (IEStatement)var1.get(var9);
         if (var10 instanceof IEAssign) {
            IEGeneric var11 = ((IEAssign)var10).getLeftOperand();
            if (var11 instanceof IESlice) {
               IEGeneric var12 = ((IESlice)var11).getWholeExpression();
               if (var12 == var4) {
                  return false;
               }
            }
         }
      }

      if (var6 <= 1) {
         return true;
      } else if (!(var5 instanceof IEVar) && !(var5 instanceof IEImm)) {
         if (var5 instanceof IESlice) {
            IEGeneric var13 = ((IESlice)var5).getWholeExpression();
            if (var13 instanceof IEVar || var13 instanceof IEImm) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private int q(IEGeneric var1, int var2) {
      if (var1 instanceof IEImm) {
         return 1;
      } else if (var1 instanceof IEVar) {
         return 2;
      } else {
         if (var2 == 0) {
            if (var1 instanceof IESlice) {
               var1 = ((IESlice)var1).getWholeExpression();
               return this.q(var1, var2 + 1);
            }

            if (var1 instanceof IEOperation) {
               IEOperation var3 = var1.asOperation();
               if (var3.getCountOfOperands() == 1) {
                  return this.q(var3.getOperand1(), var2 + 1);
               }

               if (var3.getCountOfOperands() == 2) {
                  int var4 = this.q(var3.getOperand1(), var2 + 1);
                  if (var4 <= 2) {
                     int var5 = this.q(var3.getOperand2(), var2 + 1);
                     if (var5 <= 2) {
                        return var4 + var5;
                     }
                  }
               }
            }
         }

         return 100;
      }
   }

   public String xK() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "CP0=%d\n", this.q);
      Strings.ff(var1, "CP1=%d\n", this.RF);
      Strings.ff(var1, "CP2=%d\n", this.xK);
      Strings.ff(var1, "CP3=%d\n", this.Dw);
      Strings.ff(var1, "CP4=%d\n", this.Uv);
      Strings.ff(var1, "CP5=%d\n", this.oW);
      Strings.ff(var1, "CP6=%d\n", this.gO);
      Strings.ff(var1, "CP7=%d\n", this.nf);
      Strings.ff(var1, "CP8=%d\n", this.gP);
      Strings.ff(var1, "TotalPropagationTimeMs= %d\n", this.za);
      Strings.ff(var1, "Cache: AccessesMemory: fetch/miss = %d/%d\n", this.JY, this.zz);
      Strings.ff(var1, "Cache: UsedVarIds: fetch/miss = %d/%d\n", this.io, this.LK);
      return var1.toString();
   }

   private Set oW() {
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
