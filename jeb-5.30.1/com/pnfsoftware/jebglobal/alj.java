package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Ser
public class alj extends amp implements IEAssign {
   private static final StructuredLogger q = aeg.q(alj.class);
   @SerId(1)
   private IEGeneric RF;
   @SerId(2)
   private IEGeneric xK;
   @SerId(3)
   private int lm;
   @SerId(4)
   private IEBranchDetails zz;

   alj(IERoutineContext var1, IEGeneric var2, IEGeneric var3, int var4) {
      super(var1);
      if (var2 == null || var3 == null) {
         throw new IllegalArgumentException(Strings.ff("Illegal null operand: %s (op1), %s (op2)", var2, var3));
      } else if (var4 >= 0 && var4 <= 3) {
         if (var2.getBitsize() != var3.getBitsize()) {
            throw new IllegalArgumentException(
               Strings.ff("The operands op1 and op2 do not have the same bitsize: %d (op1), %d (op2)", var2.getBitsize(), var3.getBitsize())
            );
         } else {
            this.RF = var2;
            this.xK = var3;
            if (!this.q(var2, 0)) {
               throw new RuntimeException(Strings.ff("Illegal destination for EAssign (see javadoc): \"%s\"", var2));
            } else {
               boolean var5 = var2 instanceof amy && ((amy)var2).getId() == var1.getProgramCounterId();
               if (var5 && var4 == 0) {
                  var4 = 1;
               }

               if (!var5 && var4 != 0) {
                  var4 = 0;
               }

               this.lm = var4;
            }
         }
      } else {
         throw new RuntimeException("Branch type must be 0 (no branch), 1 (branch), 2 (call-to-sub), or 3 (tentative call-to-sub)");
      }
   }

   private boolean q(IEGeneric var1, int var2) {
      if (var1 instanceof IEVar) {
         return var2 < 1 || var1 != this.Dw.getProgramCounter();
      } else if (var1 instanceof IEMem) {
         return true;
      } else if (var1 instanceof alt) {
         return true;
      } else if (var1 instanceof IESlice) {
         IEGeneric var7 = ((IESlice)var1).getWholeExpression();
         return this.q(var7, var2 + 1);
      } else if (var1 instanceof alo) {
         IEGeneric[] var3 = ((alo)var1).q();
         int var4 = var3.length;
         byte var5 = 0;
         if (var5 < var4) {
            IEGeneric var6 = var3[var5];
            return this.q(var6, var2 + 1);
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   alj(IERoutineContext var1, IEGeneric var2, IEGeneric var3) {
      this(var1, var2, var3, 0);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.lm;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2, var3, var4)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         alj var5 = (alj)var1;
         if (this.zz != null && var5.zz != null && !this.zz.equals(var5.zz)) {
            return false;
         } else if (this.lm != var5.lm) {
            return false;
         } else {
            if (this.RF == null) {
               if (var5.RF != null) {
                  return false;
               }
            } else if (!this.RF.equalsEx(var5.RF, var2)) {
               return false;
            }

            if (this.xK == null) {
               if (var5.xK != null) {
                  return false;
               }
            } else if (!this.xK.equalsEx(var5.xK, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public int getBitsize() {
      return this.RF.getBitsize();
   }

   @Override
   public IEGeneric getDstOperand() {
      return this.RF;
   }

   @Override
   public IEGeneric getSrcOperand() {
      return this.xK;
   }

   @Override
   public IEGeneric getLeftOperand() {
      return this.RF;
   }

   @Override
   public IEGeneric getRightOperand() {
      return this.xK;
   }

   public IEGeneric[] q() {
      return new IEGeneric[]{this.RF, this.xK};
   }

   @Override
   public String getMnemonic() {
      return "assign";
   }

   public boolean xK() {
      return this.RF instanceof IEVar && this.RF == this.Dw.getProgramCounter();
   }

   public int Dw() {
      return this.lm;
   }

   public void q(int var1) {
      this.lm = var1;
   }

   @Override
   public boolean isBranching() {
      return this.lm != 0;
   }

   @Override
   public boolean isBreakingFlow() {
      return this.lm == 1;
   }

   @Override
   public boolean isRoutineCall() {
      return this.lm >= 2;
   }

   @Override
   public boolean isTentativeCall() {
      return this.lm == 3;
   }

   @Override
   public boolean upgradeBreakToCall(long var1) {
      if (this.lm != 1) {
         return false;
      } else if (this.xK instanceof IECond) {
         return false;
      } else {
         IFlowInformation var3 = this.getBreakingFlow(var1);
         if (var3.isBrokenUnknown()) {
            this.lm = 3;
            this.Dw.invalidateDataFlowAnalysis();
            return true;
         } else {
            if (var3.getTargets().size() == 1) {
               ICodePointer var4 = (ICodePointer)var3.getTargets().get(0);
               long var5 = var4.getAddress();
               if (var5 == var1 + this.getSize()) {
                  this.lm = 2;
                  this.Dw.invalidateDataFlowAnalysis();
                  return true;
               }
            }

            return false;
         }
      }
   }

   @Override
   public boolean downgradeCallToBreak() {
      if (this.lm < 2) {
         return false;
      } else if (this.xK instanceof IECond) {
         return false;
      } else {
         this.lm = 1;
         this.Dw.invalidateDataFlowAnalysis();
         return true;
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      if (this.lm < 2) {
         return FlowInformation.NONE;
      } else if (!(this.RF instanceof IEVar) || this.RF != this.Dw.getProgramCounter()) {
         return FlowInformation.NONE;
      } else if (this.lm == 2) {
         return FlowInformation.EMPTY;
      } else {
         Assert.a(this.lm == 3);
         return FlowInformation.EMPTY_NO_FT;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return this.getBreakingFlow(var1, false);
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1, boolean var3) {
      if (this.lm != 1) {
         return FlowInformation.NONE;
      } else if (this.RF instanceof amy && this.RF == this.Dw.getProgramCounter()) {
         FlowInformation var4 = new FlowInformation();
         if (EUtil.isLikeLongImmediate(this.xK)) {
            Long var11 = EUtil.evaluateAddress_preVerified(this.xK);
            if (var3) {
               var4.addTarget(new CodePointer(var11));
            } else {
               var11 = this.Dw.convertNativeAddress(var11);
               if (var11 == null) {
                  return this.q(var4, var3);
               }

               var4.addTarget(new CodePointer(var11));
            }

            return var4;
         } else if (!(this.xK instanceof IECond)) {
            return this.q(var4, var3);
         } else {
            IEGeneric var5 = ((IECond)this.xK).getExpressionTrue();
            IEGeneric var6 = ((IECond)this.xK).getExpressionFalse();
            if (EUtil.isLikeLongImmediate(var5) && EUtil.isLikeLongImmediate(var6)) {
               Long var14 = EUtil.evaluateAddress_preVerified(var5);
               Long var8 = EUtil.evaluateAddress_preVerified(var6);
               if (var3) {
                  var4.addTarget(new CodePointer(var14));
                  if (!var14.equals(var8)) {
                     var4.addTarget(new CodePointer(var8));
                  }
               } else {
                  var14 = this.Dw.convertNativeAddress(var14);
                  var8 = this.Dw.convertNativeAddress(var8);
                  long var9 = var1 + this.getSize();
                  if (var14 == null || var8 == null) {
                     return this.q(var4, var3);
                  }

                  if (var14 == var9) {
                     var4.addTarget(new CodePointer(var14));
                     if (!var14.equals(var8)) {
                        var4.addTarget(new CodePointer(var8));
                     }
                  } else {
                     var4.addTarget(new CodePointer(var8));
                     if (!var14.equals(var8)) {
                        var4.addTarget(new CodePointer(var14));
                     }
                  }
               }
            } else if (EUtil.isLikeLongImmediate(var5)) {
               Long var7 = EUtil.evaluateAddress_preVerified(var5);
               if (!var3) {
                  var7 = this.Dw.convertNativeAddress(var7);
                  if (var7 == null) {
                     return this.q(var4, var3);
                  }
               }

               var4.addTarget(new CodePointer(var7));
               var4.addTarget(CodePointer.createUnknown());
            } else {
               if (!EUtil.isLikeLongImmediate(var6)) {
                  return this.q(var4, var3);
               }

               Long var13 = EUtil.evaluateAddress_preVerified(var6);
               if (!var3) {
                  var13 = this.Dw.convertNativeAddress(var13);
                  if (var13 == null) {
                     return this.q(var4, var3);
                  }
               }

               var4.addTarget(new CodePointer(var13));
               var4.addTarget(CodePointer.createUnknown());
            }

            return var4;
         }
      } else {
         return FlowInformation.NONE;
      }
   }

   private FlowInformation q(FlowInformation var1, boolean var2) {
      boolean var3 = false;
      if (this.zz != null) {
         HashSet var4 = new HashSet();

         for (IBranchTarget var6 : this.zz.getDynamicTargetCandidates()) {
            if (var6.isInternal()) {
               ICodePointer var7 = var6.getInternalAddress();
               if (var4.add(var7.getAddress())) {
                  if (var2) {
                     var1.addTarget(var7);
                  } else {
                     Long var8 = this.Dw.convertNativeAddress(var7.getAddress());
                     if (var8 != null) {
                        var1.addTarget(new CodePointer(var8));
                     } else {
                        var3 = true;
                     }
                  }
               }
            } else {
               var3 = true;
            }
         }

         if (this.zz.isIncludeUnknownTarget()) {
            var1.addTarget(CodePointer.createUnknown());
         }
      }

      if (var1.getTargets().isEmpty()) {
         var3 = true;
      }

      if (var3) {
         var1.addTarget(CodePointer.createUnknown());
      }

      return var1;
   }

   @Override
   public IEBranchDetails getBranchDetails() {
      return this.getBranchDetails(false);
   }

   @Override
   public IEBranchDetails getBranchDetails(boolean var1) {
      if (this.zz == null && var1) {
         this.setBranchDetails(this.Dw.getConverter().getDefaultBranchToRoutineSideEffects(null));
      }

      return this.zz;
   }

   @Override
   public boolean setBranchDetails(IEBranchDetails var1) {
      if (this.lm == 0) {
         throw new IllegalStateException("This assignment instruction is not a PC-assignment");
      } else if (this.zz == var1) {
         return false;
      } else {
         this.zz = var1;
         this.Dw.invalidateDataFlowAnalysis();
         return true;
      }
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      if (this.lm >= 2) {
         IEBranchDetails var2 = this.zz;
         if (var2 == null) {
            if (var1.shouldCollectPotentials()) {
               HashSet var3 = new HashSet();
               var3.add(this.Dw.getProgramCounterId());
               Collection var4 = this.Dw.getGlobalContext().getAllRegisters(var3);
               var1.addPotentialUsed(var4);
               var1.addPotentialDefined(var4);
            } else {
               var2 = this.Dw.getConverter().getDefaultBranchToRoutineSideEffects(null);
            }
         }

         if (var2 != null) {
            var1.addDefined(var2.getDef());
            var1.addUsed(var2.getUse());
            if (var1.shouldCollectSpoiled()) {
               var1.addSpoiled(var2.getSpoiled());
            }
         }

         if (var1.shouldCollectPotentials()) {
            Collection var5 = this.Dw.getMemoryVariables();
            var1.addPotentialDefined(var5);
            var1.addPotentialUsed(var5);
         }
      }

      this.RF.getDefinedOrUsedAsDestination(var1);
      this.xK.getUsed(var1);
   }

   @Override
   public void getExplicitlyUsed(EDefUseInfo var1) {
      if (this.lm < 2) {
         super.getExplicitlyUsed(var1);
      } else {
         Set var2 = EUtil.collectVars(this.xK);
         var1.addUsed(var2);
      }
   }

   @Override
   public boolean accessesMemory() {
      return this.xK.accessesMemory() || this.RF.accessesMemory();
   }

   @Override
   public boolean writesMemory() {
      return this.RF.accessesMemory();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;
      if (!var3 || this.RF instanceof IEMem || this.RF instanceof IEGroupElt) {
         if (this.RF == var1) {
            RF(this.RF, var2);
            this.RF = var2.duplicate();
            var4++;
         } else {
            var4 += this.RF.replaceVar(var1, var2);
         }
      }

      if (this.xK == var1) {
         RF(this.xK, var2);
         this.xK = var2.duplicate();
         var4++;
      } else {
         var4 += this.xK.replaceVar(var1, var2);
      }

      return var4;
   }

   @Override
   public int replaceDefinedVar(IEVar var1, IEGeneric var2) {
      if (this.RF == var1) {
         RF(this.RF, var2);
         this.RF = var2;
         return 1;
      } else {
         return 0;
      }
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null) {
         var1.add(this.xK);
         var1.add(this.RF);
      } else if (var2) {
         var1.add(this.xK);
      } else {
         var1.add(this.RF);
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      var1.add(new Couple(this, this.xK));
      RF(this, this.RF, var1);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);
      if (this.RF == var1) {
         RF(this.RF, var2);
         this.RF = var2;
         return true;
      } else if (this.xK == var1) {
         if (this.xK() && this.xK instanceof IECond && !(var2 instanceof IECond)) {
            return false;
         } else {
            RF(this.xK, var2);
            this.xK = var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      this.xK.updateTypes(var1);
      this.RF.updateTypes(var1);
      IWildcardType var2 = EUtil.getBestType(this.RF.getType(), this.xK.getType());
      if (var2 != null) {
         this.xK.setType(var2, var1);
         this.RF.setType(var2, var1);
      }
   }

   public alj Uv() {
      return this.q(this.RF.duplicate(), this.xK.duplicate());
   }

   public alj q(IEGeneric var1, IEGeneric var2) {
      alj var3 = new alj(this.Dw, var1, var2, this.lm);
      if (this.zz != null) {
         var3.zz = new EBranchDetails(this.zz);
      }

      return (alj)this.q(var3);
   }

   @Override
   public IEImm evaluate(EState var1) {
      return this.evaluate(var1, false);
   }

   @Override
   public IEImm evaluate(EState var1, boolean var2) {
      IEImm var3 = this.xK.evaluate(var1);
      if (var3 == null && this.lm != 0) {
         throw new EvaluationException(Strings.ff("Cannot evaluate assignment \"%s\" at virtual address %Xh", this, var1.getVirtualPC()));
      } else {
         if (this.lm >= 2) {
            if (var1.isExecuteSubRoutines()) {
               long var10 = var3.getValueAsAddress();
               var1.setValue(this.Dw.getProgramCounterId(), var10);
               if (var1.getRoutineContext() == null) {
                  var1.setVirtualPC(-1);
               } else {
                  var1.setVirtualPC(0);
                  var1.setRoutineContext(null);
               }

               return var3;
            }

            if (this.zz != null) {
               int var4 = this.Dw.getStackPointerId();
               long var5 = var1.getValueAsLong(var4) + this.zz.getStackPointerDeltaValue();
               var1.setValue(var4, var5);
            }
         } else {
            if (this.lm == 1) {
               long var13 = var3.getValueAsAddress();
               var1.setValue(this.Dw.getProgramCounterId(), var13);
               Long var16 = this.Dw.convertNativeAddress(var13);
               if (var16 == null) {
                  var1.setRoutineContext(null);
                  var16 = -1L;
               }

               var1.setVirtualPC(var16.intValue());
               return var3;
            }

            if (this.RF instanceof alo) {
               if (var3 == null) {
                  throw new RuntimeException("Fail-safe eval mode unsupported for a bad return assigned to a composition!");
               }

               IEImm var11 = var3;

               for (IEGeneric var8 : ((alo)this.RF).q()) {
                  int var9 = var8.getBitsize();
                  this.q(var8, var11.truncate(var9), var1);
                  var11 = var11._shr(var9);
               }
            } else {
               this.q(this.RF, var3, var1);
            }
         }

         if (this.lm != 0 || !var2) {
            int var12 = var1.adjustVirtualPC(this.getSize());
            Long var15 = this.Dw.convertIntermediateOffset(var12);
            if (var15 == null) {
               var1.removeValue(this.Dw.getProgramCounterId());
            } else {
               var1.setValue(this.Dw.getProgramCounterId(), var15);
            }
         }

         return var3;
      }
   }

   private void q(IEGeneric var1, IEImm var2, EState var3) {
      IEGeneric var4 = var1;
      IERange var5 = null;
      if (var1 instanceof IESlice) {
         var4 = ((IESlice)var1).getWholeExpression();
         var5 = ((IESlice)var1).getRange();
      }

      if (var4 instanceof IEVar var6) {
         if (var2 == null) {
            var3.setValue(var6, var2);
            return;
         }

         if (var5 == null) {
            var3.setValue(var6, var2);
         } else {
            IEImm var7 = var3.getValue(var6);
            IEImm var8 = EUtil.mask(var6.getBitsize(), var5.getBegin(), var5.getEnd());
            var7 = var7._and(var8._not());
            var2 = var2.zeroExtend(var7.getBitsize())._shl(var5.getBegin());
            var7 = var7._or(var2);
            var3.setValue(var6, var7);
         }
      } else {
         if (!(var4 instanceof IEMem) || var5 != null) {
            throw new RuntimeException("Unsupported statement evaluation: " + var1.getClass().getName());
         }

         IEMem var19 = (IEMem)var4;
         int var22 = var19.getBitsize();
         long[] var23 = null;
         if (var2 != null) {
            if (var2.getBitsize() <= 64) {
               var23 = new long[]{var2.getValueAsLong()};
            } else {
               if (var2.getBitsize() % 64 != 0) {
                  throw new RuntimeException("Unsupported non multiple of 64-bit Big Immediate: " + var2);
               }

               var23 = new long[var2.getBitsize() / 64];

               for (int var9 = 0; var9 < var23.length; var9++) {
                  var23[var9] = var2.slice(var9 * 64, (var9 + 1) * 64).evaluate(var3).getValueAsLong();
               }

               if (var3.isBigEndian()) {
                  ArrayUtil.swap(var23);
               }

               var22 = 64;
            }
         }

         IEImm var24 = var19.getReference().evaluate(var3);
         if (var24 == null) {
            return;
         }

         long var10 = var24.getValueAsAddress();
         if (var2 == null) {
            var3.writeMemoryBad(var10, var22, 3);
            return;
         }

         for (int var12 = 0; var12 < var23.length; var12++) {
            long var13 = var23[var12];
            long var15 = var10 + var12 * 8;

            byte[] var17 = switch (var22) {
               case 8 -> new byte[]{(byte)var13};
               case 16 -> EndianUtil.shortToLEBytes((short)var13);
               case 32 -> EndianUtil.intToLEBytes((int)var13);
               case 64 -> EndianUtil.longToLEBytes(var13);
               default -> throw new RuntimeException("Unsupported assign-to-memory write bitsize: " + var4.getBitsize());
            };
            if (var3.isBigEndian()) {
               EndianUtil.swap(var17);
            }

            if (!var3.writeMemory(var15, var17)) {
               throw new EvaluationException(Strings.ff("Cannot write IR-state memory at 0x%X", var15));
            }
         }
      }
   }

   @Override
   public void q(and var1) {
      var1.q(this.RF);
      var1.append(" = ");
      var1.q(this.xK);
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.Dw, "Illegal IR context");
      Object var3 = null;
      ICElementFactory var4 = var2.getElementFactory();
      if (this.RF instanceof IEVar
         && this.RF == var1.getProgramCounter()
         && this.xK instanceof alp
         && EUtil.isLongImmediate(((IECond)this.xK).getExpressionTrue())
         && EUtil.isLongImmediate(((IECond)this.xK).getExpressionFalse())) {
         IEGeneric var5 = ((IECond)this.xK).getCondition();
         ICExpression var6 = (ICExpression)var5.generateC(var1, var2);
         ICPredicate var7 = var4.createPredicate(var6);
         alu var8 = (alu)((alp)this.xK).getExpressionTrue();
         long var9 = var8.getValueAsAddress();
         var9 = var1.convertNativeAddress(var9);
         ICGoto var11 = var4.createGoto(var2.getLabelFactory().create(var9));
         alu var12 = (alu)((alp)this.xK).getExpressionFalse();
         long var13 = var12.getValueAsAddress();
         var13 = var1.convertNativeAddress(var13);
         ICGoto var15 = var4.createGoto(var2.getLabelFactory().create(var13));
         ICIfStm var16 = var4.createIfStm(var7, var11);
         var16.setDefaultBlock(var4.createBlock(var15));
         var3 = var16;
      }

      if (var3 == null) {
         ICLeftExpression var17;
         if (this.RF instanceof amy) {
            var17 = (ICLeftExpression)((amy)this.RF).generateC(var1, var2, 1);
         } else {
            var17 = (ICLeftExpression)this.RF.generateC(var1, var2);
         }

         Object var18 = (ICExpression)this.xK.generateC(var1, var2);
         IWildcardType var19 = this.RF.getType();
         IWildcardType var20 = this.xK.getType();
         if (!EUtil.isSameType(var19, var20) && EUtil.requiresExplicitCast(var19, var20)) {
            ICType var22 = var2.getTypeFactory().create(var19);
            var18 = var4.createCast(var22, (ICExpression)var18);
         }

         var3 = var4.createAssignment(var17, (ICExpression)var18);
      }

      return (ICStatement)this.q((ICElement)var3);
   }

   @Override
   public void verify() throws IllegalIntermediateExpressionException {
      super.verify();
      boolean var1 = this.RF instanceof amy && ((amy)this.RF).getId() == this.Dw.getProgramCounterId();
      if (this.lm == 0 && var1) {
         throw new IllegalIntermediateExpressionException("Illegal PC-assign: branchtype should be non-zero");
      } else if (this.lm != 0 && !var1) {
         throw new IllegalIntermediateExpressionException("Illegal non-PC-assign: branchtype should be zero");
      }
   }
}
