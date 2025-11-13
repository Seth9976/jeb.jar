package com.pnfsoftware.jebglobal;

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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class cry extends AbstractNativeDecompilerExtension {
   static final String q = "x87_TOP_values_post";

   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      var2.registerOptimizer(new ctp());
      var2.registerOptimizer(new ctq());
      var2.registerOptimizer(new ctr((crx)var1.getConverter()));
      var2.registerOptimizer(new ctt((crx)var1.getConverter()));
      var2.addDefaultInput(1034, EUtil.imm(0L, 1));
      var2.addDisregardedOutputBits(Sets.createNonNulls(1024, 1026, 1028, 1030, 1031, 1032, 1033, 1034, 1035));
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult executePostPipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      if (var1 == NativeDecompilationStage.IR_CONVERSION) {
         cry.CU var3 = new cry.CU(var2.getIRContext());
         int var4 = var3.q();
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
      return new cry.eo((crx)var1, var2, var3, var4).q() ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_CONTINUE;
   }

   static class CU {
      IERoutineContext q;
      CFG RF;
      int xK;
      Map Dw;

      public CU(IERoutineContext var1) {
         this.q = var1;
      }

      public int q() {
         this.RF = this.q.getCfg();
         HashMap var1 = new HashMap();
         this.q.setData("x87_TOP_values_post", var1);
         crx var2 = (crx)this.q.getConverter();
         this.xK = var2.Gg.getId();
         EState var3 = var2.getGlobalContext().buildState();
         int var4 = 0;
         var3.setValue(this.xK, var4);
         this.Dw = new IdentityHashMap(this.RF.size());

         for (BasicBlock var6 : this.RF.getBlocks()) {
            cry.CU.eo var7 = new cry.CU.eo(this.RF, var6);
            this.Dw.put(var6, var7);
         }

         BasicBlock var20 = this.RF.getEntryBlock();
         ((cry.CU.eo)this.Dw.get(var20)).xK = var3;
         ArrayDeque var22 = new ArrayDeque();
         var22.add(var20);

         while (!var22.isEmpty()) {
            var20 = (BasicBlock)var22.remove();
            cry.CU.eo var23 = (cry.CU.eo)this.Dw.get(var20);
            if (var23.xK == null) {
               throw new RuntimeException("No input tracker for block: " + var20);
            }

            var3 = new EState(var23.xK, false);
            var4 = (int)var3.getValue(this.xK).getValueAsLong() & 7;
            var23.gO.clear();
            var23.nf.clear();
            boolean var8 = var23.oW;
            boolean var9 = false;

            for (int var10 = 0; var10 < var20.size(); var10++) {
               IEStatement var11 = (IEStatement)var20.get(var10);
               if (var8 && !var9) {
                  var23.gO.add(null);
               } else {
                  var23.gO.add(cry.CU.CU.q(var4, var9));
                  var8 = false;
               }

               if (var11 instanceof IEAssign var12) {
                  if (EUtil.isPCAssign(var11) && var12.isRoutineCall()) {
                     var9 = true;
                     var4 = 7;
                     var3.setValue(this.xK, var4);
                  } else if (var12.getDstOperand() == var2.Gg) {
                     if (var12.getSrcOperand() instanceof IEImm) {
                        var9 = true;
                     }

                     try {
                        var11.evaluate(var3);
                        var4 = (int)var3.getValue(this.xK).getValueAsLong() & 7;
                     } catch (Exception var17) {
                        cry.logger.catching(var17, "Cannot calculate x87 TOP update!");
                        var8 = true;
                     }
                  }
               }

               if (var8 && !var9) {
                  var23.nf.add(null);
               } else {
                  var23.nf.add(cry.CU.CU.q(var4, var9));
                  var8 = false;
               }
            }

            var23.Uv = true;
            var23.Dw = new EState(var3, false);

            for (BasicBlock var30 : var20.getOutputBlocks()) {
               cry.CU.eo var32 = (cry.CU.eo)this.Dw.get(var30);
               if (var32.xK == null) {
                  var32.xK = new EState(var23.Dw, false);
                  var32.oW = var8;
                  var22.add(var30);
               } else {
                  int var13 = (int)(var32.xK.getValue(this.xK).getValueAsLong() & 7L);
                  if (var13 != var4) {
                     Object[] var10000 = new Object[0];
                     this.q(var30);
                  }
               }
            }
         }

         int var24 = 0;

         for (BasicBlock var27 : this.RF) {
            cry.CU.eo var29 = (cry.CU.eo)this.Dw.get(var27);
            int var31 = 0;

            for (AddressableInstruction var34 : var27.addressableInstructions()) {
               IEStatement var14 = (IEStatement)var34.getInstruction();
               cry.CU.CU var15 = (cry.CU.CU)var29.gO.get(var31);
               if (var15 != null) {
                  var24 += var14.replaceUsedVar(var2.Gg, EUtil.imm(var15.q, 3));
               }

               var15 = (cry.CU.CU)var29.nf.get(var31);
               if (var15 != null) {
                  Long var16 = var14.getPrimaryLowerLevelAddress();
                  if (var16 != null) {
                     var1.put(var16, var15.q);
                  }
               }

               var31++;
            }
         }

         if (var24 == 0) {
            return 0;
         } else {
            this.RF.invalidateDataFlowAnalysis();
            EMasterOptimizer var26 = new EMasterOptimizer(this.q, -1, false);
            var26.registerOptimizer(new ary());
            var26.registerOptimizer(new asb());
            var26.perform();
            return var24;
         }
      }

      private void q(BasicBlock var1) {
         ArrayDeque var2 = new ArrayDeque();
         var2.add(var1);
         HashSet var3 = new HashSet();

         while (!var2.isEmpty()) {
            BasicBlock var4 = (BasicBlock)var2.remove();
            if (var3.add(var4.getBase())) {
               cry.CU.eo var5 = (cry.CU.eo)this.Dw.get(var4);
               if (!var5.Uv) {
                  var5.oW = true;
               } else {
                  int var6 = 0;

                  while (true) {
                     if (var6 < var5.RF.size()) {
                        cry.CU.CU var7 = (cry.CU.CU)var5.gO.get(var6);
                        if (var7 == null || !var7.RF) {
                           var5.gO.set(var6, null);
                           var7 = (cry.CU.CU)var5.nf.get(var6);
                           if (var7 == null || !var7.RF) {
                              var5.nf.set(var6, null);
                              var6++;
                              continue;
                           }
                        }
                     }

                     if (var6 == var5.RF.size()) {
                        var2.addAll(var5.RF.getAllOutputBlocks());
                     }
                     break;
                  }
               }
            }
         }
      }

      static class CU {
         int q;
         boolean RF;

         static cry.CU.CU q(int var0, boolean var1) {
            return new cry.CU.CU(var0, var1);
         }

         CU(int var1, boolean var2) {
            this.q = var1;
            this.RF = var2;
         }

         @Override
         public String toString() {
            String var1 = this.q + "";
            if (!this.RF) {
               var1 = var1 + "?";
            }

            return var1;
         }
      }

      static class eo extends aot {
         boolean Uv;
         boolean oW;
         List gO;
         List nf;

         eo(CFG var1, BasicBlock var2) {
            super(var1, var2);
            this.gO = new ArrayList(var2.size());
            this.nf = new ArrayList(var2.size());
         }
      }
   }

   static class eo {
      crx q;
      IERoutineContext RF;
      ConverterInstructionEntry xK;
      long Dw;
      INativeMethodItem Uv;

      eo(crx var1, IERoutineContext var2, ConverterInstructionEntry var3, long var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }

      boolean q() {
         if (this.q.getNativeContext() == null) {
            return false;
         } else {
            this.Uv = this.q.getNativeContext().getRoutine(this.Dw);
            if (this.Uv == null) {
               return false;
            } else {
               String var1 = this.Uv.getName(true);
               if (this.q.getNativeContext().getDetectedCompiler() instanceof add) {
                  if (((ctc)this.xK.insn).gO == 32) {
                     if (var1.contains("_SEH_prolog")) {
                        return this.RF();
                     }

                     if (var1.contains("_SEH_epilog")) {
                        return this.xK();
                     }

                     if (Strings.contains(var1, "_EH_prolog", "_EH_epilog")) {
                        return this.Dw();
                     }
                  }

                  if (var1.contains("_chkstk")) {
                     return true;
                  }
               }

               CFG var2 = this.Uv.getData().getCFG();
               if (var2.size() != 1) {
                  return false;
               } else {
                  BasicBlock var3 = var2.get(0);
                  if (var2.getInstructionCount() >= 2) {
                     if (var2.getInstructionCount() == 2) {
                        if (((ctc)var3.get(1)).q() == 195) {
                           ctc var4 = (ctc)var3.get(0);
                           if (this.q(var4)) {
                              byte[] var5 = var4.getCode();
                              int var6 = ((ctc)this.xK.insn).gO;
                              long var7 = ctf.q(var5[1] >> 3 & 7, 0, var6);
                              IEGeneric var9 = this.q.q(var7);
                              long var10 = this.xK.address + ((ctc)this.xK.insn).getSize();
                              this.xK.r.add(this.RF.createAssign(var9, EUtil.imm(var10, var6)));
                              return true;
                           }
                        }
                     } else {
                        ctc var12 = (ctc)var3.get(0);
                        if (var12.q() >= 88 && var12.q() <= 91) {
                           int var13 = var12.q() & 7;
                           ctc var14 = (ctc)var3.getLast();
                           if (this.q(var14, var13)) {
                              ConverterInstructionEntry var15 = new ConverterInstructionEntry(this.xK);

                              for (int var8 = 1; var8 < var3.size() - 1; var8++) {
                                 var15.insn = var3.get(var8);
                                 var15.address = var3.getAddressOfInstruction(var8);
                                 this.q.convertInstruction(var15);
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

      private boolean q(ctc var1) {
         byte[] var2 = var1.getCode();
         return var2.length == 3 && var2[0] == -117 && (var2[2] == 36 || var2[2] == -28) && (var2[1] & 199) == 4;
      }

      private boolean q(ctc var1, int var2) {
         byte[] var3 = var1.getCode();
         return var3.length == 2 && var3[0] == -1 && var3[1] == (byte)(224 + var2);
      }

      boolean RF() {
         int var1 = ((ctc)this.xK.insn).gO;
         IEGeneric var2 = this.q.Sz.part(var1);
         IEGeneric var3 = this.q.os.part(var1);
         IEGeneric var4 = this.q.fq.part(var1);
         this.xK.r.add(this.RF.createAssign(var2, EUtil.sub(var2, EUtil.imm(12L, var1))));
         this.xK.r.add(this.RF.createAssign(var3, this.RF.createMem(EUtil.add(var2, EUtil.imm(16L, var1)), var1)));
         this.xK.r.add(this.RF.createAssign(this.RF.createMem(EUtil.add(var2, EUtil.imm(16L, var1)), var1), var4));
         this.xK.r.add(this.RF.createAssign(var4, EUtil.add(var2, EUtil.imm(16L, var1))));
         this.xK.r.add(this.RF.createAssign(var2, EUtil.sub(var2, var3)));
         this.xK.r.add(this.RF.createAssign(this.RF.createMem(EUtil.sub(var2, EUtil.imm(4L, var1)), var1), this.q.ZU.part(var1)));
         this.xK.r.add(this.RF.createAssign(this.RF.createMem(EUtil.sub(var2, EUtil.imm(8L, var1)), var1), this.q.mJ.part(var1)));
         this.xK.r.add(this.RF.createAssign(this.RF.createMem(EUtil.sub(var2, EUtil.imm(12L, var1)), var1), this.q.Bs.part(var1)));
         this.xK.r.add(this.RF.createAssign(var2, EUtil.sub(var2, EUtil.imm(12L, var1))));
         return true;
      }

      boolean xK() {
         int var1 = ((ctc)this.xK.insn).gO;
         IEGeneric var2 = this.q.Sz.part(var1);
         this.xK.r.add(this.RF.createAssign(this.q.Bs.part(var1), this.RF.createMem(var2, var1)));
         this.xK.r.add(this.RF.createAssign(this.q.mJ.part(var1), this.RF.createMem(EUtil.add(var2, EUtil.imm(4L, var1)), var1)));
         this.xK.r.add(this.RF.createAssign(this.q.ZU.part(var1), this.RF.createMem(EUtil.add(var2, EUtil.imm(8L, var1)), var1)));
         this.xK.r.add(this.RF.createAssign(var2, EUtil.add(var2, EUtil.imm(12L, var1))));
         this.q.cY.nf(this.xK);
         return true;
      }

      boolean Dw() {
         CFG var1 = this.Uv.getData().getCFG();
         if (var1.size() != 1) {
            return false;
         } else {
            BasicBlock var2 = var1.get(0);
            this.q.q(this.xK);
            ConverterInstructionEntry var3 = new ConverterInstructionEntry(this.xK);

            for (int var4 = 0; var4 < var2.size(); var4++) {
               var3.insn = var2.get(var4);
               var3.address = var2.getAddressOfInstruction(var4);
               this.q.convertInstruction(var3);
            }

            return true;
         }
      }
   }
}
