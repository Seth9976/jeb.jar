package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class ard extends AbstractEOptimizer {
   public ard() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      label71:
      while (true) {
         for (BasicBlock var3 : this.cfg) {
            if (var3.size() <= 8) {
               IEStatement var4 = (IEStatement)var3.getLast();
               if (var4.isJump()) {
                  int var5 = var4.asJump().getBranchAddress();
                  if (var5 <= var3.getBase()) {
                     BasicBlock var6 = this.cfg.getBlockAt((long)var5);
                     if (var6 != null) {
                        for (int var7 = 0; var7 < var3.size(); var7++) {
                           IEStatement var8 = (IEStatement)var3.get(var7);
                           if (var8.isAssign()) {
                              IEGeneric var9 = var8.asAssign().getDstOperand();
                              IEGeneric var10 = var8.asAssign().getSrcOperand();
                              if (var9 instanceof IEMem) {
                                 byte var11;
                                 if (var10 instanceof IEImm) {
                                    var11 = 1;
                                 } else {
                                    if (!(var10 instanceof IEMem)) {
                                       continue;
                                    }

                                    var11 = 2;
                                 }

                                 if (var6 == var3 || CFGUtil.canReach(var6, var3)) {
                                    int var13 = (int)var3.getEndAddress();
                                    ard.Av var14 = new ard.Av(var11, var5, var13);
                                    if (var14.pC()) {
                                       this.cfg.invalidateDataFlowAnalysis();
                                       this.cleanCfg();
                                       var1++;
                                       continue label71;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return var1;
      }
   }

   class Av implements IEStateHooks {
      int pC;
      int A;
      int kS;
      BasicBlock wS;
      BasicBlock UT;
      IDFA E;
      EState sY;
      Map ys = new HashMap();
      Integer ld;
      int gp;
      long oT;
      long fI;
      byte WR;
      int NS;
      long vP;
      long xC;

      Av(int var2, int var3, int var4) {
         if (var2 == 0) {
            throw new IllegalArgumentException();
         } else if (var3 >= var4) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var2;
            this.A = var3;
            this.kS = var4;
         }
      }

      boolean pC() {
         if (!this.A()) {
            return false;
         } else {
            this.wS = ard.this.cfg.getBlockEndingAt((long)this.A);
            this.UT = ard.this.cfg.getBlockAt((long)this.A);
            int var1 = 0;

            for (BasicBlock var2 = this.UT; var2 != null && var2.getBase() != this.kS; var2 = ard.this.cfg.getBlockAt(var2.getEndAddress())) {
               for (IEStatement var19 : var2) {
                  if (var19 instanceof IECall || var19 instanceof IEJumpFar || var19 instanceof IEReturn || var19 instanceof IEUntranslatedInstruction) {
                     return false;
                  }

                  if (var19 instanceof IEAssign && ((IEAssign)var19).getDstOperand() instanceof IEMem) {
                     if (++var1 >= 2) {
                        return false;
                     }
                  }
               }
            }

            if (var1 != 1) {
               return false;
            } else {
               this.E = ard.this.cfg.doDataFlowAnalysis(false, 0);
               this.sY = ard.this.ectx.buildEmptyState();
               this.sY.setMemory(null);
               this.sY.setExecuteSubRoutines(false);
               this.sY.registerHooks(this, true);
               this.sY.setMaxEvaluationCount(1000);
               this.sY.setMemoryAutoAllocOnWrite(false);
               int var3 = this.A;
               this.sY.setVirtualPC(var3);
               HashMap var4 = new HashMap();
               LinkedHashSet var5 = new LinkedHashSet();
               byte var6 = 100;
               int var7 = 0;
               int var8 = 0;

               do {
                  if (var7++ > var6) {
                     int var9 = this.gp - var8;
                     if (var9 == 0) {
                        return false;
                     }

                     if (var6 >= '鱀') {
                        return false;
                     }

                     var8 = this.gp;
                     var6 += 100;
                  }

                  IEStatement var20 = (IEStatement)var4.get(var3);
                  if (var20 == null) {
                     var20 = (IEStatement)ard.this.cfg.getInstruction(var3);
                     if (var20 == null) {
                        return false;
                     }

                     EDefUseInfo var10 = new EDefUseInfo(0, ard.this.ectx);
                     var20.getDefUse(var10);

                     for (int var12 : var10.getUsedVarIds()) {
                        IEImm var13 = this.sY.getValueSafe(var12);
                        if (var13 == null) {
                           IEVar var14 = ard.this.ectx.getVariableById(var12);
                           var13 = this.pC(var14);
                           if (var13 == null) {
                              return false;
                           }

                           this.sY.setValue(var14, var13);
                        }
                     }

                     var4.put(var3, var20);
                     var5.addAll(var20.getLowerLevelAddresses());
                  }

                  try {
                     if (var20.evaluate(this.sY) == null) {
                        return false;
                     }
                  } catch (EvaluationException var17) {
                     return false;
                  }

                  var3 = this.sY.getVirtualPC();
                  if (var3 < this.A || var3 > this.kS) {
                     return false;
                  }
               } while (var3 != this.kS);

               if (this.gp <= 1) {
                  return false;
               } else {
                  IECall var21;
                  if (this.pC == 1) {
                     int var22 = (int)(this.fI - this.oT);
                     Object[] var39 = new Object[]{this.WR, this.WR, var22};
                     Object var25 = (IEGeneric)this.ys.get(this.oT);
                     if (var25 == null) {
                        var25 = ard.this.ectx.createImm(this.oT, ard.this.ectx.getGlobalContext().getAddressBitsize());
                     }

                     IEImm var28 = ard.this.ectx.createImm(this.WR, 8);
                     IEImm var32 = ard.this.ectx.createImm(var22, ard.this.ectx.getGlobalContext().getRegisterBitsize());
                     var21 = ard.this.ectx.createBuiltinMethodCall("memset", null, (IEGeneric)var25, var28, var32);
                  } else {
                     if (this.pC != 2) {
                        return false;
                     }

                     int var23 = (int)(this.fI - this.oT);
                     int var26 = (int)(this.xC - this.vP);
                     if (var26 != var23) {
                        return false;
                     }

                     Object[] var40 = new Object[]{this.oT, this.vP, var23};
                     Object var29 = (IEGeneric)this.ys.get(this.oT);
                     if (var29 == null) {
                        var29 = ard.this.ectx.createImm(this.oT, ard.this.ectx.getGlobalContext().getAddressBitsize());
                     }

                     Object var33 = (IEGeneric)this.ys.get(this.vP);
                     if (var33 == null) {
                        var33 = ard.this.ectx.createImm(this.vP, ard.this.ectx.getGlobalContext().getAddressBitsize());
                     }

                     IEImm var35 = ard.this.ectx.createImm(var23, ard.this.ectx.getGlobalContext().getRegisterBitsize());
                     var21 = ard.this.ectx.createBuiltinMethodCall("memcpy", null, (IEGeneric)var29, (IEGeneric)var33, var35);
                  }

                  BasicBlock var24 = ard.this.cfg.getBlockAt((long)this.A);
                  if (var24.size() > 1) {
                     ard.this.cfg.splitBlock(var24, 1);
                  }

                  IEStatement var27 = (IEStatement)var24.get(0);
                  int var30 = (int)ard.this.cfg.getEndAddress();
                  BasicBlock var34 = new BasicBlock(ard.this.cfg.getEndAddress());
                  var34.add(var21.withLowerLevelAddresses(var5));

                  for (int var15 : this.sY.getVariables().keySet()) {
                     if (var15 != ard.this.ectx.getProgramCounterId()) {
                        IEAssign var16 = ard.this.ectx.createAssign(ard.this.ectx.getVariableById(var15), this.sY.getValue(var15));
                        var34.add(var16.withLowerLevelAddresses(var5));
                     }
                  }

                  IEJump var37 = ard.this.ectx.createJump(this.kS);
                  var34.add(var37.withLowerLevelAddresses(var5));
                  IEJump var38 = ard.this.ectx.createJump(var30);
                  var38.copyProperties(var27);
                  var24.set(0, var38);
                  ard.this.cfg.addBlock(var34);
                  ard.this.cfg.addEdge(var34, ard.this.cfg.getBlockAt((long)this.kS));
                  ard.this.cfg.deleteOutEdges(var24);
                  ard.this.cfg.addEdge(var24, var34);
                  return true;
               }
            }
         }
      }

      private boolean A() {
         long var1 = this.A;

         while (var1 < this.kS) {
            BasicBlock var3 = ard.this.cfg.getBlockAt(var1);
            if (var3 == null) {
               return false;
            }

            for (BasicBlock var5 : var3.getInputs()) {
               if ((var1 != this.A || var5.getEndAddress() != this.A) && (var5.getBase() < this.A || var5.getBase() >= this.kS)) {
                  return false;
               }
            }

            var1 = var3.getEndAddress();
         }

         return true;
      }

      private IEImm pC(IEVar var1) {
         if (var1.isReference()) {
            return this.A(var1);
         } else {
            Collection var2 = this.E.getReachChains(this.wS, this.wS.size(), var1.getId());
            if (var2 != null && var2.size() == 1) {
               long var3 = (Long)var2.iterator().next();
               if (var3 < 0L) {
                  return null;
               } else {
                  IEStatement var5 = (IEStatement)ard.this.cfg.getInstruction(var3);
                  if (!var5.isAssignTo(var1)) {
                     return null;
                  } else {
                     IEGeneric var6 = var5.asAssign().getSrcOperand();
                     if (var6 instanceof IEImm) {
                        return var6.asImm();
                     } else {
                        return var6 instanceof IEVar ? this.A(var6.asVar()) : null;
                     }
                  }
               }
            } else {
               return null;
            }
         }
      }

      private IEImm A(IEVar var1) {
         if (var1.isStackReference()) {
            long var6 = var1.getAddress();
            long var4 = 1048576L + var6;
            this.ys.put(var4, var1);
            return ard.this.ectx.createImm(var4, var1.getBitsize());
         } else if (var1.isGlobalReference()) {
            long var2 = var1.getAddress();
            return ard.this.ectx.createImm(var2, var1.getBitsize());
         } else {
            return null;
         }
      }

      @Override
      public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
         int var5 = var1.getVirtualPC();
         if (this.ld == null) {
            this.ld = var5;
         } else if (var5 != this.ld) {
            return false;
         }

         if (this.gp == 0) {
            this.oT = var2;
            this.fI = var2;
            this.WR = var4[0];
         }

         this.gp++;
         if (var2 != this.fI) {
            return false;
         } else if (this.pC == 1 && !ArrayUtil.isSled(var4, this.WR)) {
            return false;
         } else {
            this.fI = var2 + var4.length;
            return true;
         }
      }

      @Override
      public Integer onReadMemory(EState var1, long var2, byte[] var4) {
         if (this.pC != 2) {
            return -1;
         } else {
            int var5 = var1.getVirtualPC();
            if (this.ld == null) {
               this.ld = var5;
            } else if (var5 != this.ld) {
               return -1;
            }

            if (this.NS == 0) {
               this.vP = var2;
               this.xC = var2;
            }

            this.NS++;
            if (var2 != this.xC) {
               return -1;
            } else {
               this.xC = var2 + var4.length;
               return 0;
            }
         }
      }
   }
}
