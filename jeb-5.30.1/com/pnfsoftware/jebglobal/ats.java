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

public class ats extends AbstractEOptimizer {
   static final int q = 1;
   static final int RF = 2;

   public ats() {
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
                                    ats.eo var14 = new ats.eo(var11, var5, var13);
                                    if (var14.q()) {
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

   class eo implements IEStateHooks {
      int q;
      int RF;
      int xK;
      BasicBlock Dw;
      BasicBlock Uv;
      IDFA oW;
      EState gO;
      Map nf = new HashMap();
      Integer gP;
      int za;
      long lm;
      long zz;
      byte JY;
      int HF;
      long LK;
      long io;

      eo(int var2, int var3, int var4) {
         if (var2 == 0) {
            throw new IllegalArgumentException();
         } else if (var3 >= var4) {
            throw new IllegalArgumentException();
         } else {
            this.q = var2;
            this.RF = var3;
            this.xK = var4;
         }
      }

      boolean q() {
         if (!this.RF()) {
            return false;
         } else {
            this.Dw = ats.this.cfg.getBlockEndingAt((long)this.RF);
            this.Uv = ats.this.cfg.getBlockAt((long)this.RF);
            int var1 = 0;

            for (BasicBlock var2 = this.Uv; var2 != null && var2.getBase() != this.xK; var2 = ats.this.cfg.getBlockAt(var2.getEndAddress())) {
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
               this.oW = ats.this.cfg.doDataFlowAnalysis(false, 0);
               this.gO = ats.this.ectx.buildEmptyState();
               this.gO.setMemory(null);
               this.gO.setExecuteSubRoutines(false);
               this.gO.registerHooks(this, true);
               this.gO.setMaxEvaluationCount(1000);
               this.gO.setMemoryAutoAllocOnWrite(false);
               int var3 = this.RF;
               this.gO.setVirtualPC(var3);
               HashMap var4 = new HashMap();
               LinkedHashSet var5 = new LinkedHashSet();
               byte var6 = 100;
               int var7 = 0;
               int var8 = 0;

               do {
                  if (var7++ > var6) {
                     int var9 = this.za - var8;
                     if (var9 == 0) {
                        return false;
                     }

                     if (var6 >= '鱀') {
                        return false;
                     }

                     var8 = this.za;
                     var6 += 100;
                  }

                  IEStatement var20 = (IEStatement)var4.get(var3);
                  if (var20 == null) {
                     var20 = (IEStatement)ats.this.cfg.getInstruction(var3);
                     if (var20 == null) {
                        return false;
                     }

                     EDefUseInfo var10 = new EDefUseInfo(0, ats.this.ectx);
                     var20.getDefUse(var10);

                     for (int var12 : var10.getUsedVarIds()) {
                        IEImm var13 = this.gO.getValueSafe(var12);
                        if (var13 == null) {
                           IEVar var14 = ats.this.ectx.getVariableById(var12);
                           var13 = this.q(var14);
                           if (var13 == null) {
                              return false;
                           }

                           this.gO.setValue(var14, var13);
                        }
                     }

                     var4.put(var3, var20);
                     var5.addAll(var20.getLowerLevelAddresses());
                  }

                  try {
                     if (var20.evaluate(this.gO) == null) {
                        return false;
                     }
                  } catch (EvaluationException var17) {
                     return false;
                  }

                  var3 = this.gO.getVirtualPC();
                  if (var3 < this.RF || var3 > this.xK) {
                     return false;
                  }
               } while (var3 != this.xK);

               if (this.za <= 1) {
                  return false;
               } else {
                  IECall var21;
                  if (this.q == 1) {
                     int var22 = (int)(this.zz - this.lm);
                     Object[] var39 = new Object[]{this.JY, this.JY, var22};
                     Object var25 = (IEGeneric)this.nf.get(this.lm);
                     if (var25 == null) {
                        var25 = ats.this.ectx.createImm(this.lm, ats.this.ectx.getGlobalContext().getAddressBitsize());
                     }

                     IEImm var28 = ats.this.ectx.createImm(this.JY, 8);
                     IEImm var32 = ats.this.ectx.createImm(var22, ats.this.ectx.getGlobalContext().getRegisterBitsize());
                     var21 = ats.this.ectx.createBuiltinMethodCall("memset", null, (IEGeneric)var25, var28, var32);
                  } else {
                     if (this.q != 2) {
                        return false;
                     }

                     int var23 = (int)(this.zz - this.lm);
                     int var26 = (int)(this.io - this.LK);
                     if (var26 != var23) {
                        return false;
                     }

                     Object[] var40 = new Object[]{this.lm, this.LK, var23};
                     Object var29 = (IEGeneric)this.nf.get(this.lm);
                     if (var29 == null) {
                        var29 = ats.this.ectx.createImm(this.lm, ats.this.ectx.getGlobalContext().getAddressBitsize());
                     }

                     Object var33 = (IEGeneric)this.nf.get(this.LK);
                     if (var33 == null) {
                        var33 = ats.this.ectx.createImm(this.LK, ats.this.ectx.getGlobalContext().getAddressBitsize());
                     }

                     IEImm var35 = ats.this.ectx.createImm(var23, ats.this.ectx.getGlobalContext().getRegisterBitsize());
                     var21 = ats.this.ectx.createBuiltinMethodCall("memcpy", null, (IEGeneric)var29, (IEGeneric)var33, var35);
                  }

                  BasicBlock var24 = ats.this.cfg.getBlockAt((long)this.RF);
                  if (var24.size() > 1) {
                     ats.this.cfg.splitBlock(var24, 1);
                  }

                  IEStatement var27 = (IEStatement)var24.get(0);
                  int var30 = (int)ats.this.cfg.getEndAddress();
                  BasicBlock var34 = new BasicBlock(ats.this.cfg.getEndAddress());
                  var34.add(var21.withLowerLevelAddresses(var5));

                  for (int var15 : this.gO.getVariables().keySet()) {
                     if (var15 != ats.this.ectx.getProgramCounterId()) {
                        IEAssign var16 = ats.this.ectx.createAssign(ats.this.ectx.getVariableById(var15), this.gO.getValue(var15));
                        var34.add(var16.withLowerLevelAddresses(var5));
                     }
                  }

                  IEJump var37 = ats.this.ectx.createJump(this.xK);
                  var34.add(var37.withLowerLevelAddresses(var5));
                  IEJump var38 = ats.this.ectx.createJump(var30);
                  var38.copyProperties(var27);
                  var24.set(0, var38);
                  ats.this.cfg.addBlock(var34);
                  ats.this.cfg.addEdge(var34, ats.this.cfg.getBlockAt((long)this.xK));
                  ats.this.cfg.deleteOutEdges(var24);
                  ats.this.cfg.addEdge(var24, var34);
                  return true;
               }
            }
         }
      }

      private boolean RF() {
         long var1 = this.RF;

         while (var1 < this.xK) {
            BasicBlock var3 = ats.this.cfg.getBlockAt(var1);
            if (var3 == null) {
               return false;
            }

            for (BasicBlock var5 : var3.getInputs()) {
               if ((var1 != this.RF || var5.getEndAddress() != this.RF) && (var5.getBase() < this.RF || var5.getBase() >= this.xK)) {
                  return false;
               }
            }

            var1 = var3.getEndAddress();
         }

         return true;
      }

      private IEImm q(IEVar var1) {
         if (var1.isReference()) {
            return this.RF(var1);
         } else {
            Collection var2 = this.oW.getReachChains(this.Dw, this.Dw.size(), var1.getId());
            if (var2 != null && var2.size() == 1) {
               long var3 = (Long)var2.iterator().next();
               if (var3 < 0L) {
                  return null;
               } else {
                  IEStatement var5 = (IEStatement)ats.this.cfg.getInstruction(var3);
                  if (!var5.isAssignTo(var1)) {
                     return null;
                  } else {
                     IEGeneric var6 = var5.asAssign().getSrcOperand();
                     if (var6 instanceof IEImm) {
                        return var6.asImm();
                     } else {
                        return var6 instanceof IEVar ? this.RF(var6.asVar()) : null;
                     }
                  }
               }
            } else {
               return null;
            }
         }
      }

      private IEImm RF(IEVar var1) {
         if (var1.isStackReference()) {
            long var6 = var1.getAddress();
            long var4 = 1048576L + var6;
            this.nf.put(var4, var1);
            return ats.this.ectx.createImm(var4, var1.getBitsize());
         } else if (var1.isGlobalReference()) {
            long var2 = var1.getAddress();
            return ats.this.ectx.createImm(var2, var1.getBitsize());
         } else {
            return null;
         }
      }

      @Override
      public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
         int var5 = var1.getVirtualPC();
         if (this.gP == null) {
            this.gP = var5;
         } else if (var5 != this.gP) {
            return false;
         }

         if (this.za == 0) {
            this.lm = var2;
            this.zz = var2;
            this.JY = var4[0];
         }

         this.za++;
         if (var2 != this.zz) {
            return false;
         } else if (this.q == 1 && !ArrayUtil.isSled(var4, this.JY)) {
            return false;
         } else {
            this.zz = var2 + var4.length;
            return true;
         }
      }

      @Override
      public Integer onReadMemory(EState var1, long var2, byte[] var4) {
         if (this.q != 2) {
            return -1;
         } else {
            int var5 = var1.getVirtualPC();
            if (this.gP == null) {
               this.gP = var5;
            } else if (var5 != this.gP) {
               return -1;
            }

            if (this.HF == 0) {
               this.LK = var2;
               this.io = var2;
            }

            this.HF++;
            if (var2 != this.io) {
               return -1;
            } else {
               this.io = var2 + var4.length;
               return 0;
            }
         }
      }
   }
}
