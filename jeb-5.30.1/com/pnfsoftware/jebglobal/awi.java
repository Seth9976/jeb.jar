package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchResolution;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.List;

public class awi extends avx {
   private static final StructuredLogger LK = aeg.q(awi.class);
   int JY;
   int HF;

   public awi() {
      super(avy.xK);
   }

   @Override
   public boolean Dw() {
      SPDC var1 = this.Uv.getDefaultBranchToRoutineSideEffects(this.gP).getStackPointerDelta();
      ((aml)this.nf).q(var1, false);
      int var2 = this.gO();
      Object[] var10000 = new Object[]{var2};
      var2 = this.nf();
      var10000 = new Object[]{var2};
      if (this.JY + this.HF > 0) {
         LK.iH("The IR-CFG is needs to be rebuilt.");
         this.nf.buildCfg(this.nf.getCfg().getInstructions());
      }

      return true;
   }

   private int gO() {
      int var1 = 0;
      CFG var2 = this.nf.getCfg();

      for (BasicBlock var4 : var2) {
         AddressableInstruction var5 = var4.getLast2();
         if (var5.getInstruction() instanceof IEAssign) {
            IEAssign var6 = (IEAssign)var5.getInstruction();
            if (var6.getLeftOperand() == this.nf.getProgramCounter()) {
               long var7 = var5.getOffset();
               IEGeneric var9 = var6.getRightOperand();
               Long var10 = var6.getPrimaryLowerLevelAddress();
               IFlowInformation var11 = var5.getRoutineCall();
               if (var11.isBroken()) {
                  INativeMethodItem var12 = null;
                  if (var9 instanceof IEImm) {
                     long var13 = ((IEImm)var9).getValueAsAddress();
                     var12 = this.oW.getRoutine(var13);
                     if (var12 == null) {
                        Long var15 = this.nf.convertNativeAddress(var13);
                        if (var15 != null && var2.getBlockAt(var15) != null && var6.downgradeCallToBreak()) {
                           Object[] var10000 = new Object[]{var7};
                           INativeContinuousItem var27 = this.oW.getNativeItemAt(var10);
                           if (var27 instanceof INativeInstructionItem) {
                              ((INativeInstructionItem)var27).getHints(true).setFakeCall(true);
                           }

                           this.JY++;
                           continue;
                        }
                     }

                     INativeMethodItem var25;
                     if (var12 != null && var12.getData() != null && (var25 = var12.getData().getTrampolineTarget()) != null) {
                        Long var16 = var25.getMemoryAddress();
                        if (var16 != null && var6.replaceSubExpression(var9, this.gO.createImm(var16, var9.getBitsize()))) {
                           var12 = var25;
                           var1++;
                        }
                     }
                  }

                  IEBranchDetails var20 = this.Uv.getDefaultBranchToRoutineSideEffects(var12);
                  var6.setBranchDetails(var20);
                  var1++;
               }

               if (!(var9 instanceof IEImm) && var10 != null) {
                  IBranchResolution var17 = this.oW.getDynamicBranchResolution(var10);
                  if (var17.isResolved()) {
                     IBranchTarget var21 = var17.getResolvedTarget();
                     if (var21.isInternal()) {
                        IEImm var14 = this.nf.createImm(var21.getInternalAddress().getAddress(), this.Uv.getAddressBitsize());
                        if (var6.replaceSubExpression(var6.getRightOperand(), var14)) {
                           var1++;
                        }
                     } else {
                        var6.getBranchDetails(true).addCandidate(var21);
                        var1++;
                     }
                  } else {
                     List var22 = var17.getCandidates();
                     if (!var22.isEmpty()) {
                        var6.getBranchDetails(true).addCandidates(var22);
                        var1++;
                     }
                  }
               }

               if (var10 != null) {
                  INativeContinuousItem var18 = this.oW.getNativeItemAt(var10);
                  if (var18 instanceof INativeInstructionItem) {
                     InstructionHints var23 = ((INativeInstructionItem)var18).getHints(false);
                     if (var23 != null) {
                        IPrototypeItem var24 = var23.getCallsitePrototype();
                        if (var24 != null && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                           var6.getBranchDetails(true).setNativePrototypeHint(var24);
                           var1++;
                        }

                        Integer var26 = var23.getStackPointerDelta();
                        if (var26 != null && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                           var6.getBranchDetails(true).getStackPointerDeltaDeterminer().add(var26, 30, 40);
                           var1++;
                        }

                        if ((var23.isTailCall() || var23.isCondTailCall() || var23.isActualCall()) && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                           var1++;
                        }
                     }
                  }
               }

               if (!var11.isBroken() && var9 instanceof IEImm && ((IEImm)var9).canReadAsAddress()) {
                  long var19 = ((IEImm)var9).getValueAsAddress();
                  if (this.oW.getRoutine(var19) != null && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                     var1++;
                  }
               }
            }
         }
      }

      return var1;
   }

   private int nf() {
      byte var1 = 0;

      for (BasicBlock var4 : this.nf.getCfg()) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IEStatement var6 = (IEStatement)var4.get(var5);
            if (var6 instanceof IEUntranslatedInstruction) {
               Long var7 = var6.getPrimaryLowerLevelAddress();
               if (var7 != null) {
                  IBranchResolution var8 = this.oW.getDynamicBranchResolution(var7);
                  if (var8.isResolved()) {
                     IBranchTarget var9 = var8.getResolvedTarget();
                     if (!var9.isInternal() && this.Uv.canCreateCalls()) {
                        INativeMethodItem var10 = var9.getRoutine();
                        IEVar var11 = this.nf.createSymbolForRoutine(var10);
                        IECall var12 = this.nf.createCall(var11, null, null, null, false);
                        var12.copyProperties(var6);
                        var4.set(var5, var12);
                        int var13 = (int)var4.getAddressOfInstruction(var5);
                        int var14 = var13 + var12.getSize();
                        Long var15 = this.nf.convertIntermediateOffset(var14);
                        if (var15 != null) {
                           IEImm var16 = this.nf.createImm(var15, this.Uv.getAddressBitsize());
                           ((aln)var12).setReturnLocation(var16);
                        }

                        IEVar var17 = this.Uv.getReturnAddressRegister();
                        if (var17 != null) {
                           ((aln)var12).q(var17);
                        }

                        Object[] var10000 = new Object[]{var13};
                        this.HF++;
                     }
                  }
               }
            }
         }
      }

      return var1;
   }
}
