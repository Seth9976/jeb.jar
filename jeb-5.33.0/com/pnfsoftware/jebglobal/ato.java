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
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.List;

public class ato extends atf {
   private static final StructuredLogger vP = aco.pC(ato.class);
   int WR;
   int NS;

   public ato() {
      super(atg.kS);
   }

   @Override
   public boolean kS() {
      SPDC var1 = this.UT.getDefaultBranchToRoutineSideEffects(this.ld).getStackPointerDelta();
      ((aki)this.ys).pC(var1, false);
      int var2 = this.UT();
      Object[] var10000 = new Object[]{var2};
      var2 = this.E();
      var10000 = new Object[]{var2};
      if (this.WR + this.NS > 0) {
         vP.iH("The IR-CFG is needs to be rebuilt.");
         this.ys.buildCfg(this.ys.getCfg().getInstructions());
      }

      return true;
   }

   private int UT() {
      int var1 = 0;
      CFG var2 = this.ys.getCfg();

      for (BasicBlock var4 : var2) {
         AddressableInstruction var5 = var4.getLast2();
         if (var5.getInstruction() instanceof IEAssign) {
            IEAssign var6 = (IEAssign)var5.getInstruction();
            if (var6.getLeftOperand() == this.ys.getProgramCounter()) {
               long var7 = var5.getOffset();
               IEGeneric var9 = var6.getRightOperand();
               Long var10 = var6.getPrimaryLowerLevelAddress();
               IFlowInformation var11 = var5.getRoutineCall();
               if (var11.isBroken()) {
                  INativeMethodItem var12 = null;
                  if (var9 instanceof IEImm var13) {
                     long var14 = var13.getValueAsAddress();
                     var12 = this.E.getRoutine(var14);
                     if (var12 == null) {
                        Long var16 = this.ys.convertNativeAddress(var14);
                        if (var16 != null && var2.getBlockAt(var16) != null && var6.downgradeCallToBreak()) {
                           Object[] var10000 = new Object[]{var7};
                           if (this.E.getNativeItemAt(var10) instanceof INativeInstructionItem var18) {
                              var18.getHints(true).setFakeCall(true);
                           }

                           this.WR++;
                           continue;
                        }
                     }

                     INativeMethodItem var29;
                     if (var12 != null && var12.getData() != null && (var29 = var12.getData().getTrampolineTarget()) != null) {
                        Long var17 = var29.getMemoryAddress();
                        if (var17 != null && var6.replaceSubExpression(var9, this.sY.createImm(var17, var9.getBitsize()))) {
                           var12 = var29;
                           var1++;
                        }
                     }
                  }

                  IEBranchDetails var22 = this.UT.getDefaultBranchToRoutineSideEffects(var12);
                  var6.setBranchDetails(var22);
                  var1++;
               }

               if (!(var9 instanceof IEImm) && var10 != null) {
                  IBranchResolution var19 = this.E.getDynamicBranchResolution(var10);
                  if (var19.isResolved()) {
                     IBranchTarget var23 = var19.getResolvedTarget();
                     if (var23.isInternal()) {
                        IEImm var27 = this.ys.createImm(var23.getInternalAddress().getAddress(), this.UT.getAddressBitsize());
                        if (var6.replaceSubExpression(var6.getRightOperand(), var27)) {
                           var1++;
                        }
                     } else {
                        var6.getBranchDetails(true).addCandidate(var23);
                        var1++;
                     }
                  } else {
                     List var24 = var19.getCandidates();
                     if (!var24.isEmpty()) {
                        var6.getBranchDetails(true).addCandidates(var24);
                        var1++;
                     }
                  }
               }

               if (var10 != null && this.E.getNativeItemAt(var10) instanceof INativeInstructionItem var25) {
                  InstructionHints var28 = var25.getHints(false);
                  if (var28 != null) {
                     IPrototypeItem var15 = var28.getCallsitePrototype();
                     if (var15 != null && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                        var6.getBranchDetails(true).setNativePrototypeHint(var15);
                        var1++;
                     }

                     Integer var30 = var28.getStackPointerDelta();
                     if (var30 != null && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                        var6.getBranchDetails(true).getStackPointerDeltaDeterminer().add(var30, 30, 40);
                        var1++;
                     }

                     if ((var28.isTailCall() || var28.isCondTailCall() || var28.isActualCall()) && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                        var1++;
                     }
                  }
               }

               if (!var11.isBroken() && var9 instanceof IEImm var21 && var21.canReadAsAddress()) {
                  long var26 = var21.getValueAsAddress();
                  if (this.E.getRoutine(var26) != null && (var6.isRoutineCall() || var6.upgradeBreakToCall(var7))) {
                     var1++;
                  }
               }
            }
         }
      }

      return var1;
   }

   private int E() {
      byte var1 = 0;

      for (BasicBlock var4 : this.ys.getCfg()) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IEStatement var6 = (IEStatement)var4.get(var5);
            if (var6 instanceof IEUntranslatedInstruction) {
               Long var7 = var6.getPrimaryLowerLevelAddress();
               if (var7 != null) {
                  IBranchResolution var8 = this.E.getDynamicBranchResolution(var7);
                  if (var8.isResolved()) {
                     IBranchTarget var9 = var8.getResolvedTarget();
                     if (!var9.isInternal() && this.UT.canCreateCalls()) {
                        INativeMethodItem var10 = var9.getRoutine();
                        IEVar var11 = this.ys.createSymbolForRoutine(var10);
                        IECall var12 = this.ys.createCall(var11, null, null, null, false);
                        var12.copyProperties(var6);
                        var4.set(var5, var12);
                        int var13 = (int)var4.getAddressOfInstruction(var5);
                        int var14 = var13 + var12.getSize();
                        Long var15 = this.ys.convertIntermediateOffset(var14);
                        if (var15 != null) {
                           IEImm var16 = this.ys.createImm(var15, this.UT.getAddressBitsize());
                           var12.setReturnLocation(var16);
                        }

                        IEVar var17 = this.UT.getReturnAddressRegister();
                        if (var17 != null) {
                           ((ajk)var12).pC(var17);
                        }

                        Object[] var10000 = new Object[]{var13};
                        this.NS++;
                     }
                  }
               }
            }
         }
      }

      return var1;
   }
}
