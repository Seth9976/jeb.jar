package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.EMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.abl;
import com.pnfsoftware.jebglobal.amm;
import com.pnfsoftware.jebglobal.apl;
import com.pnfsoftware.jebglobal.apn;
import com.pnfsoftware.jebglobal.cjc;
import com.pnfsoftware.jebglobal.cjd;
import com.pnfsoftware.jebglobal.cje;
import com.pnfsoftware.jebglobal.cjg;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class t extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      var2.registerOptimizer(new cjc());
      var2.registerOptimizer(new cjd());
      var2.registerOptimizer(new cje((Yd)var1.getConverter()));
      var2.registerOptimizer(new cjg((Yd)var1.getConverter()));
      var2.addDefaultInput(1034, EUtil.imm(0L, 1));
      var2.addDisregardedOutputBits(Sets.createNonNulls(1024, 1026, 1028, 1030, 1031, 1032, 1033, 1034, 1035));
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult executePostPipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      if (var1 == NativeDecompilationStage.IR_CONVERSION) {
         t.Sv var3 = new t.Sv(var2.getIRContext());
         int var4 = var3.pC();
         if (var4 > 0) {
            var2.getIRContext().getCfg();
            var2.getIRContext();
            Object[] var10000 = new Object[]{var4};
         }
      }

      return super.executePostPipelineStage(var1, var2);
   }

   @Override
   public ChainedOperationResult isMemoryResolutionAllowed(INativeDecompilerContext var1, IERoutineContext var2, IEMem var3) {
      IEGeneric var4 = var3.getSegment();
      if (var4 instanceof IEVar) {
         int var5 = ((IEVar)var4).getId();
         Integer var6 = var2.getUnderlyingRegisterId(var5);
         if (var6 != null && (var6 == 1408 || var6 == 1440)) {
            return ChainedOperationResult.FALSE_STOP;
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult convertToInlinedCall(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3, long var4) {
      return new t.Av((Yd)var1, var2, var3, var4).pC() ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_CONTINUE;
   }

   static class Av {
      Yd pC;
      IERoutineContext A;
      ConverterInstructionEntry kS;
      long wS;
      INativeMethodItem UT;

      Av(Yd var1, IERoutineContext var2, ConverterInstructionEntry var3, long var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }

      boolean pC() {
         if (this.pC.getNativeContext() == null) {
            return false;
         } else {
            this.UT = this.pC.getNativeContext().getRoutine(this.wS);
            if (this.UT == null) {
               return false;
            } else {
               String var1 = this.UT.getName(true);
               if (this.pC.getNativeContext().getDetectedCompiler() instanceof abl) {
                  if (((vh)this.kS.insn).pC == 32) {
                     if (var1.contains("_SEH_prolog")) {
                        return this.A();
                     }

                     if (var1.contains("_SEH_epilog")) {
                        return this.kS();
                     }

                     if (Strings.contains(var1, "_EH_prolog", "_EH_epilog")) {
                        return this.wS();
                     }
                  }

                  if (var1.contains("_chkstk")) {
                     return true;
                  }
               }

               CFG var2 = this.UT.getData().getCFG();
               if (var2.size() != 1) {
                  return false;
               } else {
                  BasicBlock var3 = var2.get(0);
                  if (var2.getInstructionCount() >= 2) {
                     if (var2.getInstructionCount() == 2) {
                        if (((vh)var3.get(1)).pC() == 195) {
                           vh var4 = (vh)var3.get(0);
                           if (this.pC(var4)) {
                              byte[] var5 = var4.getCode();
                              int var6 = ((vh)this.kS.insn).pC;
                              long var7 = MG.pC(var5[1] >> 3 & 7, 0, var6);
                              IEGeneric var9 = this.pC.pC(var7);
                              long var10 = this.kS.address + ((vh)this.kS.insn).getSize();
                              this.kS.r.add(this.A.createAssign(var9, EUtil.imm(var10, var6)));
                              return true;
                           }
                        }
                     } else {
                        vh var12 = (vh)var3.get(0);
                        if (var12.pC() >= 88 && var12.pC() <= 91) {
                           int var13 = var12.pC() & 7;
                           vh var14 = (vh)var3.getLast();
                           if (this.pC(var14, var13)) {
                              ConverterInstructionEntry var15 = new ConverterInstructionEntry(this.kS);

                              for (int var8 = 1; var8 < var3.size() - 1; var8++) {
                                 var15.insn = var3.get(var8);
                                 var15.address = var3.getAddressOfInstruction(var8);
                                 this.pC.convertInstruction(var15);
                              }

                              return true;
                           }
                        }
                     }
                  }

                  return false;
               }
            }
         }
      }

      private boolean pC(vh var1) {
         byte[] var2 = var1.getCode();
         return var2.length == 3 && var2[0] == -117 && (var2[2] == 36 || var2[2] == -28) && (var2[1] & 199) == 4;
      }

      private boolean pC(vh var1, int var2) {
         byte[] var3 = var1.getCode();
         return var3.length == 2 && var3[0] == -1 && var3[1] == (byte)(224 + var2);
      }

      boolean A() {
         int var1 = ((vh)this.kS.insn).pC;
         IEGeneric var2 = this.pC.Ab.part(var1);
         IEGeneric var3 = this.pC.Sc.part(var1);
         IEGeneric var4 = this.pC.rl.part(var1);
         this.kS.r.add(this.A.createAssign(var2, EUtil.sub(var2, EUtil.imm(12L, var1))));
         this.kS.r.add(this.A.createAssign(var3, this.A.createMem(EUtil.add(var2, EUtil.imm(16L, var1)), var1)));
         this.kS.r.add(this.A.createAssign(this.A.createMem(EUtil.add(var2, EUtil.imm(16L, var1)), var1), var4));
         this.kS.r.add(this.A.createAssign(var4, EUtil.add(var2, EUtil.imm(16L, var1))));
         this.kS.r.add(this.A.createAssign(var2, EUtil.sub(var2, var3)));
         this.kS.r.add(this.A.createAssign(this.A.createMem(EUtil.sub(var2, EUtil.imm(4L, var1)), var1), this.pC.UO.part(var1)));
         this.kS.r.add(this.A.createAssign(this.A.createMem(EUtil.sub(var2, EUtil.imm(8L, var1)), var1), this.pC.z.part(var1)));
         this.kS.r.add(this.A.createAssign(this.A.createMem(EUtil.sub(var2, EUtil.imm(12L, var1)), var1), this.pC.Ek.part(var1)));
         this.kS.r.add(this.A.createAssign(var2, EUtil.sub(var2, EUtil.imm(12L, var1))));
         return true;
      }

      boolean kS() {
         int var1 = ((vh)this.kS.insn).pC;
         IEGeneric var2 = this.pC.Ab.part(var1);
         this.kS.r.add(this.A.createAssign(this.pC.Ek.part(var1), this.A.createMem(var2, var1)));
         this.kS.r.add(this.A.createAssign(this.pC.z.part(var1), this.A.createMem(EUtil.add(var2, EUtil.imm(4L, var1)), var1)));
         this.kS.r.add(this.A.createAssign(this.pC.UO.part(var1), this.A.createMem(EUtil.add(var2, EUtil.imm(8L, var1)), var1)));
         this.kS.r.add(this.A.createAssign(var2, EUtil.add(var2, EUtil.imm(12L, var1))));
         this.pC.ys.ys(this.kS);
         return true;
      }

      boolean wS() {
         CFG var1 = this.UT.getData().getCFG();
         if (var1.size() != 1) {
            return false;
         } else {
            BasicBlock var2 = var1.get(0);
            this.pC.pC(this.kS);
            ConverterInstructionEntry var3 = new ConverterInstructionEntry(this.kS);

            for (int var4 = 0; var4 < var2.size(); var4++) {
               var3.insn = var2.get(var4);
               var3.address = var2.getAddressOfInstruction(var4);
               this.pC.convertInstruction(var3);
            }

            return true;
         }
      }
   }

   static class Sv {
      IERoutineContext pC;
      CFG A;
      int kS;
      Map wS;

      public Sv(IERoutineContext var1) {
         this.pC = var1;
      }

      public int pC() {
         this.A = this.pC.getCfg();
         HashMap var1 = new HashMap();
         this.pC.setData("x87_TOP_values_post", var1);
         Yd var2 = (Yd)this.pC.getConverter();
         this.kS = var2.So.getId();
         EState var3 = var2.getGlobalContext().buildState();
         int var4 = 0;
         var3.setValue(this.kS, var4);
         this.wS = new IdentityHashMap(this.A.size());

         for (BasicBlock var6 : this.A.getBlocks()) {
            t.Sv.Av var7 = new t.Sv.Av(this.A, var6);
            this.wS.put(var6, var7);
         }

         BasicBlock var20 = this.A.getEntryBlock();
         ((t.Sv.Av)this.wS.get(var20)).kS = var3;
         ArrayDeque var22 = new ArrayDeque();
         var22.add(var20);

         while (!var22.isEmpty()) {
            var20 = (BasicBlock)var22.remove();
            t.Sv.Av var23 = (t.Sv.Av)this.wS.get(var20);
            if (var23.kS == null) {
               throw new RuntimeException("No input tracker for block: " + var20);
            }

            var3 = new EState(var23.kS, false);
            var4 = (int)var3.getValue(this.kS).getValueAsLong() & 7;
            var23.sY.clear();
            var23.ys.clear();
            boolean var8 = var23.E;
            boolean var9 = false;

            for (int var10 = 0; var10 < var20.size(); var10++) {
               IEStatement var11 = (IEStatement)var20.get(var10);
               if (var8 && !var9) {
                  var23.sY.add(null);
               } else {
                  var23.sY.add(t.Sv.Sv.pC(var4, var9));
                  var8 = false;
               }

               if (var11 instanceof IEAssign var12) {
                  if (EUtil.isPCAssign(var11) && var12.isRoutineCall()) {
                     var9 = true;
                     var4 = 7;
                     var3.setValue(this.kS, var4);
                  } else if (var12.getDstOperand() == var2.So) {
                     if (var12.getSrcOperand() instanceof IEImm) {
                        var9 = true;
                     }

                     try {
                        var11.evaluate(var3);
                        var4 = (int)var3.getValue(this.kS).getValueAsLong() & 7;
                     } catch (Exception var17) {
                        t.logger.catching(var17, "Cannot calculate x87 TOP update!");
                        var8 = true;
                     }
                  }
               }

               if (var8 && !var9) {
                  var23.ys.add(null);
               } else {
                  var23.ys.add(t.Sv.Sv.pC(var4, var9));
                  var8 = false;
               }
            }

            var23.UT = true;
            var23.wS = new EState(var3, false);

            for (BasicBlock var30 : var20.getOutputs()) {
               t.Sv.Av var32 = (t.Sv.Av)this.wS.get(var30);
               if (var32.kS == null) {
                  var32.kS = new EState(var23.wS, false);
                  var32.E = var8;
                  var22.add(var30);
               } else {
                  int var13 = (int)(var32.kS.getValue(this.kS).getValueAsLong() & 7L);
                  if (var13 != var4) {
                     Object[] var10000 = new Object[0];
                     this.pC(var30);
                  }
               }
            }
         }

         int var24 = 0;

         for (BasicBlock var27 : this.A) {
            t.Sv.Av var29 = (t.Sv.Av)this.wS.get(var27);
            int var31 = 0;

            for (AddressableInstruction var34 : var27.addressableInstructions()) {
               IEStatement var14 = (IEStatement)var34.getInstruction();
               t.Sv.Sv var15 = (t.Sv.Sv)var29.sY.get(var31);
               if (var15 != null) {
                  var24 += var14.replaceUsedVar(var2.So, EUtil.imm(var15.pC, 3));
               }

               var15 = (t.Sv.Sv)var29.ys.get(var31);
               if (var15 != null) {
                  Long var16 = var14.getPrimaryLowerLevelAddress();
                  if (var16 != null) {
                     var1.put(var16, var15.pC);
                  }
               }

               var31++;
            }
         }

         if (var24 == 0) {
            return 0;
         } else {
            this.A.invalidateDataFlowAnalysis();
            EMasterOptimizer var26 = new EMasterOptimizer(this.pC, -1, false);
            var26.registerOptimizer(new apl());
            var26.registerOptimizer(new apn());
            var26.perform();
            return var24;
         }
      }

      private void pC(BasicBlock var1) {
         ArrayDeque var2 = new ArrayDeque();
         var2.add(var1);
         HashSet var3 = new HashSet();

         while (!var2.isEmpty()) {
            BasicBlock var4 = (BasicBlock)var2.remove();
            if (var3.add(var4.getBase())) {
               t.Sv.Av var5 = (t.Sv.Av)this.wS.get(var4);
               if (!var5.UT) {
                  var5.E = true;
               } else {
                  int var6 = 0;

                  while (true) {
                     if (var6 < var5.A.size()) {
                        t.Sv.Sv var7 = (t.Sv.Sv)var5.sY.get(var6);
                        if (var7 == null || !var7.A) {
                           var5.sY.set(var6, null);
                           var7 = (t.Sv.Sv)var5.ys.get(var6);
                           if (var7 == null || !var7.A) {
                              var5.ys.set(var6, null);
                              var6++;
                              continue;
                           }
                        }
                     }

                     if (var6 == var5.A.size()) {
                        var2.addAll(var5.A.getAllOutputBlocks());
                     }
                     break;
                  }
               }
            }
         }
      }

      static class Av extends amm {
         boolean UT;
         boolean E;
         List sY;
         List ys;

         Av(CFG var1, BasicBlock var2) {
            super(var1, var2);
            this.sY = new ArrayList(var2.size());
            this.ys = new ArrayList(var2.size());
         }
      }

      static class Sv {
         int pC;
         boolean A;

         static t.Sv.Sv pC(int var0, boolean var1) {
            return new t.Sv.Sv(var0, var1);
         }

         Sv(int var1, boolean var2) {
            this.pC = var1;
            this.A = var2;
         }

         @Override
         public String toString() {
            String var1 = this.pC + "";
            if (!this.A) {
               var1 = var1 + "?";
            }

            return var1;
         }
      }
   }
}
