package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.DefUseInfo;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalCodeThrownException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDElement;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstantFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGlobalContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SerDisabled
public class bpv extends bph implements IDInstruction {
   private static final ILogger A = GlobalLog.getLogger(bpv.class, Integer.MAX_VALUE);
   private IDMethodContext kS;
   private DOpcodeType wS;
   private IDElement UT;
   private IDElement E;
   private int sY = -1;
   private int ys = 1;
   private bpv.Av ld;

   bpv(IDMethodContext var1, DOpcodeType var2, IDElement var3, IDElement var4) {
      super(null);
      this.kS = var1;
      this.pC(var2, var3, var4);
   }

   bpv(IDMethodContext var1, DOpcodeType var2, IDElement var3) {
      this(var1, var2, var3, null);
   }

   bpv(IDMethodContext var1, DOpcodeType var2) {
      this(var1, var2, null, null);
   }

   @Override
   public IDMethodContext getContext() {
      return this.kS;
   }

   @Override
   public IDMethodContext setContext(IDMethodContext var1) {
      IDMethodContext var2 = this.kS;
      this.kS = var1;
      return var2;
   }

   @Override
   public void morph(DOpcodeType var1, IDElement var2, IDElement var3) {
      this.pC(var1, var2, var3);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void pC(DOpcodeType var1, IDElement var2, IDElement var3) {
      if (var1 == null) {
         throw new RuntimeException("Illegal opcode, cannot be null");
      } else {
         this.wS = var1;
         this.UT = var2;
         this.E = var3;
         boolean var4 = false;

         var4 = switch (bpw.pC[var1.ordinal()]) {
            case 1 -> {
               if (var2 != null || var3 != null) {
                  yield true;
               }
            }
            case 2 -> {
               if (!(var2 instanceof bph) || !(var3 instanceof bph)) {
                  yield true;
               }
            }
            case 3 -> {
               if (!(var2 instanceof bqf) || var3 != null) {
                  yield true;
               }
            }
            case 4 -> {
               if (!(var2 instanceof bqf) || !(var3 instanceof IDExpression)) {
                  yield true;
               }
            }
            case 5 -> {
               if (!(var2 instanceof bqe) || !(var3 instanceof bph)) {
                  yield true;
               }
            }
            case 6 -> {
               if (var2 != null || var3 != null && !(var3 instanceof bph)) {
                  yield true;
               }
            }
            case 7 -> {
               if (var2 != null || !(var3 instanceof IDInvokeInfo)) {
                  yield true;
               }
            }
            case 8 -> {
               if (var2 != null || !(var3 instanceof bph)) {
                  yield true;
               }
            }
            case 9 -> {
               if (!(var2 instanceof bqg) || var3 != null) {
                  yield true;
               }
            }
            case 10, 11 -> {
               if (var2 != null || !(var3 instanceof bph)) {
                  yield true;
               }
            }
            default -> throw new RuntimeException();
         };

         if (var4) {
            throw new RuntimeException("Illegal operands for instruction " + var1);
         }
      }
   }

   @Override
   public int hashCode() {
      return System.identityHashCode(this);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      return this == var1;
   }

   public boolean pC(IDInstruction var1) {
      if (!(var1 instanceof bpv var2)) {
         return false;
      } else if (this.wS != var2.wS) {
         return false;
      } else {
         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equalsEx(var2.UT, false)) {
            return false;
         }

         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equalsEx(var2.E, false)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IDInstruction copy(DCopyOptions var1) {
      if (var1 != null) {
         bpv var2 = (bpv)var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bpv var3 = new bpv(this.kS, this.wS, this.UT == null ? null : this.UT.copy(var1), this.E == null ? null : this.E.copy(var1));
      var3.sY = this.sY;
      var3.ys = this.ys;
      var3.ld = this.ld;
      super.A(var3);
      return var3;
   }

   @Override
   public IDInstruction duplicate() {
      return this.copy(null);
   }

   @Override
   public void copyBaseFields(IDInstruction var1) {
      this.kS = ((bpv)var1).kS;
      this.sY = ((bpv)var1).sY;
      this.ys = ((bpv)var1).ys;
      this.ld = ((bpv)var1).ld;
      ((bpv)var1).A(this);
   }

   @Override
   public IDInstruction duplicateForReplacement(IDInstruction var1) {
      bpv var2 = (bpv)this.duplicate();
      var2.sY = ((bpv)var1).sY;
      var2.ys = ((bpv)var1).ys;
      var2.ld = ((bpv)var1).ld;
      return var2;
   }

   @Override
   public IDInstruction duplicateWithOffsetAndSize(long var1, int var3) {
      bpv var4 = (bpv)this.duplicate();
      var4.sY = (int)var1;
      var4.ys = var3;
      return var4;
   }

   @Override
   public DOpcodeType getOpcode() {
      return this.wS;
   }

   @Override
   public void setOpcode(DOpcodeType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public boolean isOpcode(DOpcodeType... var1) {
      return this.wS.isAnyOf(var1);
   }

   @Override
   public IDElement getOperand1() {
      return this.UT;
   }

   @Override
   public void setOperand1(IDElement var1) {
      this.UT = var1;
   }

   @Override
   public IDElement getOperand2() {
      return this.E;
   }

   @Override
   public void setOperand2(IDElement var1) {
      this.E = var1;
   }

   @Override
   public int getProcessorMode() {
      return 0;
   }

   @Override
   public long getOffset() {
      return this.sY;
   }

   @Override
   public long getOffsetEnd() {
      return (long)this.sY + this.ys;
   }

   @Override
   public void setOffset(long var1) {
      if (var1 > 2147483647L) {
         throw new IllegalArgumentException("IR offset exceeds 2^31");
      } else {
         this.sY = (int)var1;
      }
   }

   @Override
   public IDInstruction withOffset(long var1) {
      this.setOffset(var1);
      return this;
   }

   @Override
   public int getSize() {
      return this.ys;
   }

   @Override
   public void setSize(int var1) {
      if (var1 <= 0) {
         throw new RuntimeException();
      } else {
         this.ys = var1;
      }
   }

   @Override
   public IDInstruction withSize(int var1) {
      this.setSize(var1);
      return this;
   }

   @Override
   public void adjustSize(int var1) {
      if (this.ys + var1 <= 0) {
         throw new RuntimeException();
      } else {
         this.ys += var1;
      }
   }

   @Override
   public byte[] getCode() {
      return null;
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return new IInstructionOperand[]{this.UT, this.E};
   }

   @Override
   public void collectVarIds(Set var1) {
      if (this.UT instanceof IDExpression) {
         ((IDExpression)this.UT).collectVarIds(var1);
      }

      if (this.E instanceof IDExpression) {
         ((IDExpression)this.E).collectVarIds(var1);
      }
   }

   @Override
   public boolean canThrow() {
      return this.canThrow(this.kS);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public boolean canThrow(IDMethodContext var1) {
      switch (bpw.pC[this.wS.ordinal()]) {
         case 3:
            return false;
         case 4:
         case 5:
         case 6:
         case 7:
         case 9:
         default:
            if (this.UT instanceof bph && ((bph)this.UT).canThrow(var1)) {
               return true;
            } else {
               if (this.E instanceof bph && ((bph)this.E).canThrow(var1)) {
                  return true;
               }

               return false;
            }
         case 8:
         case 10:
         case 11:
            return true;
      }
   }

   @Override
   public boolean isNop() {
      return this.wS == DOpcodeType.IR_NOP;
   }

   @Override
   public boolean isAssign() {
      return this.wS == DOpcodeType.IR_ASSIGN;
   }

   @Override
   public IDExpression getAssignDestination() {
      Assert.a(this.wS == DOpcodeType.IR_ASSIGN);
      return (IDExpression)this.UT;
   }

   @Override
   public IDExpression setAssignDestination(IDExpression var1) {
      Assert.a(this.wS == DOpcodeType.IR_ASSIGN && var1 != null);
      IDExpression var2 = (IDExpression)this.UT;
      this.UT = var1;
      return var2;
   }

   @Override
   public IDExpression getAssignSource() {
      Assert.a(this.wS == DOpcodeType.IR_ASSIGN);
      return (bph)this.E;
   }

   @Override
   public IDExpression setAssignSource(IDExpression var1) {
      Assert.a(this.wS == DOpcodeType.IR_ASSIGN && var1 != null);
      IDExpression var2 = (IDExpression)this.E;
      this.E = var1;
      return var2;
   }

   @Override
   public boolean isReturn() {
      return this.wS == DOpcodeType.IR_RETURN;
   }

   @Override
   public IDExpression getReturnExpression() {
      Assert.a(this.wS == DOpcodeType.IR_RETURN);
      return (bph)this.E;
   }

   @Override
   public IDExpression setReturnExpression(IDExpression var1) {
      Assert.a(this.wS == DOpcodeType.IR_RETURN);
      IDExpression var2 = (IDExpression)this.E;
      this.E = var1;
      return var2;
   }

   @Override
   public boolean isThrow() {
      return this.wS == DOpcodeType.IR_THROW;
   }

   @Override
   public IDExpression getThrowExpression() {
      Assert.a(this.wS == DOpcodeType.IR_THROW);
      return (IDExpression)this.E;
   }

   @Override
   public IDExpression setThrowExpression(IDExpression var1) {
      Assert.a(this.wS == DOpcodeType.IR_THROW && var1 != null);
      IDExpression var2 = (IDExpression)this.E;
      this.E = var1;
      return var2;
   }

   @Override
   public boolean isReturnOrThrow() {
      return this.wS.isAnyOf(DOpcodeType.IR_RETURN, DOpcodeType.IR_THROW);
   }

   @Override
   public void transformToNop() {
      this.wS = DOpcodeType.IR_NOP;
      this.UT = null;
      this.E = null;
   }

   @Override
   public void transformToJump(int var1) {
      this.transformToJump(this.kS.getGlobalContext().createTarget(var1));
   }

   @Override
   public void transformToJump(IDTarget var1) {
      this.wS = DOpcodeType.IR_JUMP;
      this.UT = var1;
      this.E = null;
   }

   @Override
   public void transformJcondToJump() {
      Assert.a(this.wS == DOpcodeType.IR_JCOND);
      this.wS = DOpcodeType.IR_JUMP;
      this.E = null;
   }

   @Override
   public void transformJcondToAssign(IDVar var1) {
      Assert.a(this.wS == DOpcodeType.IR_JCOND);
      this.wS = DOpcodeType.IR_ASSIGN;
      this.UT = var1;
   }

   @Override
   public boolean transformSwitchToJcond() {
      Assert.a(this.wS == DOpcodeType.IR_SWITCH);
      bqe var1 = (bqe)this.UT;
      if (var1.getCaseCount() != 1) {
         return false;
      } else if (!var1.isRegularSwitch()) {
         return false;
      } else {
         int var2 = (Integer)var1.getCases().iterator().next();
         IDTarget var3 = (IDTarget)var1.getTargets(false).iterator().next();
         IDExpression var4 = (IDExpression)this.E;
         this.wS = DOpcodeType.IR_JCOND;
         this.UT = var3;
         this.E = this.kS
            .getGlobalContext()
            .createPredicate(
               var4, this.kS.getOperatorFactory().getStandardOperator(JavaOperatorType.EQ), this.kS.getGlobalContext().createConstant(var2, var4.getType())
            );
         return true;
      }
   }

   @Override
   public int updateTargets(Map var1) {
      return this.updateTargets(var1, false);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public int updateTargets(Map var1, boolean var2) {
      switch (bpw.pC[this.wS.ordinal()]) {
         case 3:
         case 4:
            int var3 = this.getBranchTarget();
            Integer var4 = (Integer)var1.get(var3);
            if (var4 == null) {
               if (var2) {
                  throw new IllegalArgumentException(Strings.ff("Missed entry for old offset 0x%X", var3));
               }

               return 0;
            }

            this.setBranchTarget(var4);
            return 1;
         case 5:
            return this.getSwitchData().updateCases(var1, var2);
         default:
            return 0;
      }
   }

   @Override
   public boolean isSwitch() {
      return this.wS == DOpcodeType.IR_SWITCH;
   }

   @Override
   public boolean isSwitchOnInt() {
      return this.isSwitch() && this.getSwitchData().isRegularSwitch();
   }

   @Override
   public boolean isSwitchOnString() {
      return this.isSwitch() && this.getSwitchData().isStringSwitch();
   }

   @Override
   public IDSwitchData getSwitchData() {
      Assert.a(this.wS == DOpcodeType.IR_SWITCH);
      return (IDSwitchData)this.UT;
   }

   @Override
   public IDSwitchData setSwitchData(IDSwitchData var1) {
      Assert.a(this.wS == DOpcodeType.IR_SWITCH && var1 != null);
      IDSwitchData var2 = (IDSwitchData)this.UT;
      this.UT = var1;
      return var2;
   }

   @Override
   public IDExpression getSwitchExpression() {
      Assert.a(this.wS == DOpcodeType.IR_SWITCH);
      return (IDExpression)this.E;
   }

   @Override
   public IDExpression setSwitchExpression(IDExpression var1) {
      Assert.a(this.wS == DOpcodeType.IR_SWITCH && var1 != null);
      IDExpression var2 = (IDExpression)this.E;
      this.E = var1;
      return var2;
   }

   @Override
   public boolean isJumpOrJcond() {
      return this.wS == DOpcodeType.IR_JUMP || this.wS == DOpcodeType.IR_JCOND;
   }

   @Override
   public boolean isJumpOrJcondTo(int var1) {
      return this.wS.isAnyOf(DOpcodeType.IR_JUMP, DOpcodeType.IR_JCOND) && ((bqf)this.UT).getOffset() == var1;
   }

   @Override
   public boolean isJcondOrSwitch() {
      return this.wS == DOpcodeType.IR_JCOND || this.wS == DOpcodeType.IR_SWITCH;
   }

   @Override
   public int setBranchTarget(int var1) {
      Assert.a(this.wS == DOpcodeType.IR_JUMP || this.wS == DOpcodeType.IR_JCOND);
      int var2 = ((IDTarget)this.UT).getOffset();
      ((IDTarget)this.UT).setOffset(var1);
      return var2;
   }

   @Override
   public int getBranchTarget() {
      Assert.a(this.wS == DOpcodeType.IR_JUMP || this.wS == DOpcodeType.IR_JCOND);
      return ((bqf)this.UT).getOffset();
   }

   @Override
   public boolean isJump() {
      return this.wS == DOpcodeType.IR_JUMP;
   }

   @Override
   public boolean isJumpTo(int var1) {
      return this.wS == DOpcodeType.IR_JUMP && ((bqf)this.UT).getOffset() == var1;
   }

   @Override
   public boolean isJcond() {
      return this.wS == DOpcodeType.IR_JCOND;
   }

   @Override
   public boolean isJcondTo(int var1) {
      return this.wS == DOpcodeType.IR_JCOND && ((bqf)this.UT).getOffset() == var1;
   }

   @Override
   public IDExpression getJcondCondition() {
      Assert.a(this.wS == DOpcodeType.IR_JCOND);
      return (IDExpression)this.E;
   }

   @Override
   public IDExpression setJcondCondition(IDExpression var1) {
      Assert.a(this.wS == DOpcodeType.IR_JCOND && var1 != null);
      IDExpression var2 = (IDExpression)this.E;
      this.E = var1;
      return var2;
   }

   @Override
   public void reverseJcondCondition() {
      Assert.a(this.wS == DOpcodeType.IR_JCOND);
      this.E = bpl.pC((IDExpression)this.E, this.kS.getGlobalContext());
   }

   @Override
   public boolean isInvoke() {
      return this.wS == DOpcodeType.IR_INVOKE;
   }

   @Override
   public IDInvokeInfo getInvokeData() {
      Assert.a(this.wS == DOpcodeType.IR_INVOKE);
      return (IDInvokeInfo)this.E;
   }

   @Override
   public boolean isStoreException() {
      return this.wS == DOpcodeType.IR_STORE_EXCEPTION;
   }

   @Override
   public IDVar getStoredExceptionVariable() {
      Assert.a(this.wS == DOpcodeType.IR_STORE_EXCEPTION);
      return (IDVar)this.UT;
   }

   @Override
   public IDVar setStoredExceptionVariable(IDVar var1) {
      Assert.a(this.wS == DOpcodeType.IR_STORE_EXCEPTION && var1 != null);
      IDVar var2 = (IDVar)this.UT;
      this.UT = var1;
      return var2;
   }

   @Override
   public boolean isMonitorEnter() {
      return this.wS == DOpcodeType.IR_MONITOR_ENTER;
   }

   @Override
   public boolean isMonitorExit() {
      return this.wS == DOpcodeType.IR_MONITOR_EXIT;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if (var1 != this.sY) {
         throw new RuntimeException();
      } else {
         return this.getBreakingFlow();
      }
   }

   @Override
   public IFlowInformation getBreakingFlow() {
      FlowInformation var1 = new FlowInformation();
      switch (this.wS) {
         case IR_JUMP:
            long var10 = ((bqf)this.UT).getOffset();
            var1.addTarget(new CodePointer(var10));
            break;
         case IR_JCOND:
            long var9 = this.getOffsetEnd();
            var1.addTarget(new CodePointer(var9));
            long var4 = ((bqf)this.UT).getOffset();
            if (var4 != var9) {
               var1.addTarget(new CodePointer(var4));
            }
            break;
         case IR_SWITCH:
            HashSet var2 = new HashSet();
            long var3 = this.getOffsetEnd();
            var2.add(var3);
            var1.addTarget(new CodePointer(var3));

            for (IDTarget var6 : ((IDSwitchData)this.UT).getTargets(false)) {
               long var7 = var6.getOffset();
               if (var2.add(var7)) {
                  var1.addTarget(new CodePointer(var7));
               }
            }
         case IR_RETURN:
         case IR_THROW:
            break;
         case IR_INVOKE:
         case IR_STORE_EXCEPTION:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
         default:
            var1 = FlowInformation.NONE;
      }

      return var1;
   }

   public FlowInformation pC(long var1) {
      throw new RuntimeException("Do not use");
   }

   @Override
   public IFlowInformation getRoutineCall() {
      throw new RuntimeException("Do not use");
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      throw new RuntimeException("Do not use");
   }

   @Override
   public IFlowInformation collectIndirectCallReferences() {
      throw new RuntimeException("Do not use");
   }

   @Override
   public long getPrimaryBranchAddress(long var1) {
      if (var1 != this.sY) {
         throw new RuntimeException();
      } else {
         return IDInstruction.super.getPrimaryBranchAddress(var1);
      }
   }

   @Override
   public long getPrimaryBranchAddress() {
      return this.getPrimaryBranchAddress(this.sY);
   }

   public bpv.Av pC(bpv.Av var1) {
      bpv.Av var2 = this.ld;
      this.ld = var1;
      return var2;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      var1.clear();
      var2.clear();
      Set var4 = null;
      if (this.UT instanceof IDExpression) {
         var4 = ((IDExpression)this.UT).getVarIds();
      }

      Set var5 = null;
      if (this.E instanceof IDExpression) {
         var5 = ((IDExpression)this.E).getVarIds();
      }

      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            if (this.UT instanceof bqg) {
               var1.addAll(var4);
            } else {
               var2.addAll(var4);
            }

            if (var5 != null) {
               for (Integer var7 : var5) {
                  if (!var2.contains(var7)) {
                     var2.add(var7);
                  }
               }
            }
            break;
         case IR_JCOND:
         case IR_SWITCH:
         case IR_INVOKE:
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            var2.addAll(var5);
            break;
         case IR_RETURN:
            if (this.E != null) {
               var2.addAll(var5);
            }
            break;
         case IR_STORE_EXCEPTION:
            var1.addAll(var4);
            break;
         default:
            throw new RuntimeException();
      }

      if (this.ld != null) {
         this.ld.customize(this, var1, var2);
      }
   }

   @Override
   public IDVar getDefinedVariable() {
      switch (this.wS) {
         case IR_ASSIGN:
         case IR_STORE_EXCEPTION:
            if (this.UT instanceof IDVar) {
               return (IDVar)this.UT;
            }
         case IR_NOP:
         case IR_JUMP:
         case IR_JCOND:
         case IR_SWITCH:
         case IR_RETURN:
         case IR_INVOKE:
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return null;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public DefUseInfo getDefUseInfo(long var1, int var3) {
      DefUseInfo var4 = new DefUseInfo(var3);
      var4.insnAddress = var1;
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();
      this.getDefUse(var5, var6, null);

      for (int var8 : var5) {
         var4.def.add(var8, 32);
      }

      for (int var10 : var6) {
         var4.use.add(var10, 32);
      }

      return var4;
   }

   @Override
   public List getUsedVariables() {
      Object var1;
      if (this.wS == DOpcodeType.IR_ASSIGN && this.UT instanceof IDVar) {
         var1 = (IDExpression)this.E;
      } else {
         if (this.wS == DOpcodeType.IR_STORE_EXCEPTION) {
            return new ArrayList();
         }

         var1 = this;
      }

      bpv.Sv var2 = new bpv.Sv();
      ((IDExpression)var1).visitDepthPost(var2);
      return var2.pC;
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            IDExpression var14 = (IDExpression)this.UT;
            IDExpression var15 = (IDExpression)this.E;
            var15.updateTypes(var1);
            var14.updateTypes(var1);
            IJavaType var16 = var15.getType();
            if (var16.isDefined() && !var14.getType().isDetermined()) {
               IJavaType var18 = var16.asWildcardSup();
               var14.setType(var18, var1);
            }

            var16 = var14.getType();
            if (var16.isDefined() && !var15.getType().isDetermined()) {
               IJavaType var19 = var16.asWildcardExt();
               var15.setType(var19, var1);
            }
            break;
         case IR_JCOND:
            IDExpression var13 = (IDExpression)this.E;
            var13.updateTypes(var1);
            Assert.a(var13.getType().isBoolean());
            break;
         case IR_SWITCH:
            IDExpression var12 = (IDExpression)this.E;
            var12.updateTypes(var1);
            if (!var12.getType().isDefined()) {
               var12.setType(this.kS.getTypeFactory().getSmallIntWildcard(), var1);
            }
            break;
         case IR_RETURN:
            IDExpression var11 = (IDExpression)this.E;
            if (var11 != null) {
               var11.updateTypes(var1);
            }
            break;
         case IR_INVOKE:
            IDInvokeInfo var10 = (IDInvokeInfo)this.E;
            var10.updateTypes(var1);
            break;
         case IR_THROW:
            IDExpression var9 = (IDExpression)this.E;
            var9.updateTypes(var1);
            if (!var9.getType().isDefined()) {
               var9.setType(this.kS.getTypeFactory().createWildcardType("Ljava/lang/Throwable;", true), var1);
            }
            break;
         case IR_STORE_EXCEPTION:
            IDVar var8 = (IDVar)this.UT;
            var8.updateTypes(var1);
            if (!var8.getType().isDefined()) {
               String var3 = null;
               if (!this.kS.getExceptionData().isEmpty()) {
                  IDTypeInfoProvider var4 = this.kS.getGlobalContext().getTypeInfoProvider();

                  for (int var6 : this.kS.getExceptionData().getHandledExceptionTypesAt((int)this.getOffset())) {
                     String var7;
                     if (var6 == -1) {
                        var7 = "Ljava/lang/Throwable;";
                     } else {
                        var7 = this.kS.getDex().getType(var6).getSignature(false);
                     }

                     if (var3 == null) {
                        var3 = var7;
                     } else {
                        if (!var4.isCompatible(var7, "Ljava/lang/Exception;")) {
                           var3 = "Ljava/lang/Throwable;";
                           break;
                        }

                        var3 = "Ljava/lang/Exception;";
                     }
                  }
               }

               if (var3 == null) {
                  var3 = "Ljava/lang/Throwable;";
               }

               var8.setType(this.kS.getTypeFactory().createWildcardType(var3, true));
            }
            break;
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            IDExpression var2 = (IDExpression)this.E;
            var2.updateTypes(var1);
            if (!var2.getType().isDefined()) {
               var2.setType(this.kS.getTypeFactory().getGenericObjectWildcard());
            }
            break;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      switch (this.wS) {
         case IR_NOP:
            return false;
         case IR_ASSIGN:
         case IR_JUMP:
         case IR_JCOND:
         case IR_SWITCH:
         case IR_RETURN:
         case IR_THROW:
         case IR_STORE_EXCEPTION:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return true;
         case IR_INVOKE:
            return ((bph)this.E).hasSideEffects(var1, var2);
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean hasUseSideEffects(boolean var1) {
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
         case IR_STORE_EXCEPTION:
            return false;
         case IR_ASSIGN:
            return ((bph)this.E).hasSideEffects(this.kS, var1) || ((bph)this.UT).hasSideEffects(this.kS, var1);
         case IR_JCOND:
            return ((IDExpression)this.E).hasSideEffects(this.kS, var1);
         case IR_SWITCH:
            return ((bph)this.E).hasSideEffects(this.kS, var1);
         case IR_RETURN:
            return this.E == null ? false : ((bph)this.E).hasSideEffects(this.kS, var1);
         case IR_INVOKE:
            return ((bph)this.E).hasSideEffects(this.kS, var1);
         case IR_THROW:
            return ((bph)this.E).hasSideEffects(this.kS, var1);
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return ((bph)this.E).hasSideEffects(this.kS, var1);
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean pC() {
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
            return false;
         case IR_ASSIGN:
            return ((bph)this.E).pC() || ((bph)this.UT).pC();
         case IR_JCOND:
            return ((bph)this.E).pC();
         case IR_SWITCH:
            return ((bph)this.E).pC();
         case IR_RETURN:
            return this.E == null ? false : ((bph)this.E).pC();
         case IR_INVOKE:
            return ((bph)this.E).pC();
         case IR_THROW:
            return ((bph)this.E).pC();
         case IR_STORE_EXCEPTION:
            return ((bqg)this.UT).pC();
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return ((bph)this.E).pC();
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public int countVariable(IDVar var1) {
      throw new RuntimeException("This method should not be called on IR instruction objects");
   }

   @Override
   public int countUsedVariable(IDVar var1) {
      byte var2 = 0;

      return switch (this.wS) {
         case IR_NOP, IR_JUMP, IR_STORE_EXCEPTION -> {
         }
         case IR_ASSIGN -> {
            if (!(this.UT instanceof IDVar)) {
               yield ((bph)this.UT).countVariable(var1);
            }

            yield ((bph)this.E).countVariable(var1);
         }
         case IR_JCOND -> ((bph)this.E).countVariable(var1);
         case IR_SWITCH -> ((bph)this.E).countVariable(var1);
         case IR_RETURN -> {
            if (this.E != null) {
               yield ((bph)this.E).countVariable(var1);
            }
         }
         case IR_INVOKE -> ((bph)this.E).countVariable(var1);
         case IR_THROW -> ((bph)this.E).countVariable(var1);
         case IR_MONITOR_ENTER, IR_MONITOR_EXIT -> ((bph)this.E).countVariable(var1);
         default -> throw new RuntimeException();
      };
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      return this.replaceUsedVariable(var1, var2) + this.replaceDefinedVariable(var1, var2);
   }

   @Override
   public int replaceUsedVariable(IDVar var1, IDExpression var2) {
      int var3 = 0;
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
         case IR_STORE_EXCEPTION:
            break;
         case IR_ASSIGN:
            if (!(this.UT instanceof IDVar)) {
               var3 += ((IDExpression)this.UT).replaceVariable(var1, var2);
            }

            if (this.E == var1) {
               this.E = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.E).replaceVariable(var1, var2);
            }
            break;
         case IR_JCOND:
            if (this.E == var1) {
               this.E = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.E).replaceVariable(var1, var2);
            }
            break;
         case IR_SWITCH:
            if (this.E == var1) {
               this.E = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.E).replaceVariable(var1, var2);
            }
            break;
         case IR_RETURN:
            if (this.E == var1) {
               this.E = var2 == null ? null : var2.duplicate();
               var3++;
            } else if (this.E != null) {
               var3 += ((IDExpression)this.E).replaceVariable(var1, var2);
            }
            break;
         case IR_INVOKE:
            var3 += ((IDInvokeInfo)this.E).replaceVariable(var1, var2);
            break;
         case IR_THROW:
            if (this.E == var1) {
               this.E = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.E).replaceVariable(var1, var2);
            }
            break;
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            if (this.E == var1) {
               this.E = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.E).replaceVariable(var1, var2);
            }
            break;
         default:
            throw new RuntimeException();
      }

      return var3;
   }

   @Override
   public int replaceDefinedVariable(IDVar var1, IDExpression var2) {
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
         case IR_JCOND:
         case IR_SWITCH:
         case IR_RETURN:
         case IR_INVOKE:
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return 0;
         case IR_ASSIGN:
         case IR_STORE_EXCEPTION:
            if (this.UT == var1) {
               this.UT = var2.duplicate();
               return 1;
            }

            return 0;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            ((bph)this.UT).pC(var1, var2);
            ((bph)this.E).pC(var1, var2);
            break;
         case IR_JCOND:
            ((bph)this.E).pC(var1, var2);
            break;
         case IR_SWITCH:
            ((bph)this.E).pC(var1, var2);
            break;
         case IR_RETURN:
            if (this.E != null) {
               ((bph)this.E).pC(var1, var2);
            }
            break;
         case IR_INVOKE:
            ((bph)this.E).pC(var1, var2);
            break;
         case IR_THROW:
            ((bph)this.E).pC(var1, var2);
            break;
         case IR_STORE_EXCEPTION:
            ((bqg)this.UT).pC(var1, var2);
            break;
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            ((bph)this.E).pC(var1, var2);
            break;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            if (this.E != null) {
               var1.add((IDExpression)this.E);
            }

            if (this.UT != null) {
               var1.add((IDExpression)this.UT);
            }
            break;
         case IR_JCOND:
         case IR_SWITCH:
         case IR_RETURN:
         case IR_INVOKE:
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            if (this.E != null) {
               var1.add((IDExpression)this.E);
            }
            break;
         case IR_STORE_EXCEPTION:
            if (this.UT != null) {
               var1.add((IDExpression)this.UT);
            }
            break;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public Integer evaluate(Map var1) throws DexDecEvaluationException {
      IDState var2 = this.kS.getGlobalContext().createState();

      Integer var4;
      try {
         var2.setSubRoutineInvocationPolicy(0);
         var2.setFieldAccessPolicy(0);
         var2.enableEmulator(true);
         var2.enableSandbox(false);
         var2.setMaxIterationCount(1);
         var2.pushContext("context");
         IDEmuFrame var3 = var2.pushFrame(this.kS.getMethodSignature());
         var3.getVarMap().putAll(var1);
         var3.setPC(this.sY);
         this.evaluate(var2);
         var4 = var3.getNextPC();
      } finally {
         var2.dispose();
      }

      return var4;
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      btp var2 = (btp)var1;
      Assert.a(var2.getPC() == this.getOffset(), "Unexpected PC value");
      var2.WR();
      IDImm var3 = null;
      switch (this.wS) {
         case IR_NOP:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            break;
         case IR_ASSIGN:
            var3 = ((bph)this.E).evaluate(var2);
            if (this.UT instanceof bqg var12) {
               var2.setVariable(var12.getId(), var3);
            } else if (this.UT instanceof bpo) {
               IDImm var13 = ((bpo)this.UT).getArray().evaluate(var2);
               IDImm var16 = ((bpo)this.UT).getIndex().evaluate(var2);
               var2.setArrayElement(var13, (int)var16.toLong(), var3);
            } else if (this.UT instanceof bqd) {
               String var14 = ((bqd)this.UT).getClassSignature();
               String var17 = ((bqd)this.UT).getFieldname();
               String var19 = ((bqd)this.UT).getType().getName();
               var2.pC(var14, var17, var19, var3);
            } else {
               if (!(this.UT instanceof bpu)) {
                  throw new DexDecEvaluationException("Not implememented");
               }

               String var15 = var2.getDex().getField(((bpu)this.UT).getIndex().getValue()).getSignature(false);
               IDImm var18 = ((bpu)this.UT).getInstance().evaluate(var2);
               var2.setInstanceField(var15, var18, var3);
            }
            break;
         case IR_JUMP:
            var2.A(this.getBranchTarget());
            return null;
         case IR_JCOND:
            IDImm var11 = ((bph)this.E).evaluate(var2);
            if (!var11.isZero()) {
               var2.A(this.getBranchTarget());
               return null;
            }
            break;
         case IR_SWITCH:
            IDImm var10 = this.getSwitchExpression().evaluate(var2);
            IDSwitchData var5 = this.getSwitchData();
            IDTarget var6 = null;
            if (var5.isRegularSwitch()) {
               Integer var7 = (int)var10.toLong();
               var6 = var5.getTargetForCase(var7);
            } else if (var5.isStringSwitch()) {
               String var20 = var2.getStringObject(var10);
               if (var20 == null) {
                  IDImm var8 = var2.registerObject(new NullPointerException());
                  throw new DexDecEvalCodeThrownException(var8);
               }

               var6 = var5.getTargetForCase(var20);
            }

            if (var6 != null) {
               var2.A(var6.getOffset());
               return null;
            }
            break;
         case IR_RETURN:
            if (this.E != null) {
               var3 = ((bph)this.E).evaluate(var2);
            }

            var2.getCurrentFrame().setExecutionComplete(true);
            return var3;
         case IR_INVOKE:
            var3 = ((bph)this.E).evaluate(var2);
            break;
         case IR_THROW:
            IDImm var9 = ((bph)this.E).evaluate(var2);
            if (var9.maybeNullRef()) {
               var9 = var2.registerObject(new NullPointerException());
            }

            throw new DexDecEvalCodeThrownException(var9);
         case IR_STORE_EXCEPTION:
            bqg var4 = (bqg)this.UT;
            var2.setVariable(var4.getId(), var2.FE());
            break;
         default:
            throw new RuntimeException();
      }

      var2.A((int)this.getOffsetEnd());
      return var3;
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      int var3 = 0;
      switch (this.wS) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            if (this.UT == var1 && var2 != null) {
               this.UT = pC(var2, var3);
               var3++;
            }

            if (this.E == var1 && var2 != null) {
               this.E = pC(var2, var3);
               var3++;
            }
            break;
         case IR_JCOND:
            if (this.E == var1 && var2 != null) {
               this.E = pC(var2, var3);
               var3++;
            }
            break;
         case IR_SWITCH:
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            if (this.E == var1 && var2 != null) {
               this.E = pC(var2, var3);
               var3++;
            }
            break;
         case IR_RETURN:
            if (this.E == var1) {
               this.E = pC(var2, var3);
               return true;
            }
            break;
         case IR_INVOKE:
            if (this.E == var1 && var2 instanceof IDInvokeInfo) {
               this.E = pC(var2, var3);
               var3++;
            }
            break;
         case IR_STORE_EXCEPTION:
            if (this.UT == var1 && var2 instanceof IDVar) {
               this.UT = pC(var2, var3);
               var3++;
            }
            break;
         default:
            throw new RuntimeException();
      }

      return var3 > 0;
   }

   @Override
   public String toString(IDMethodContext var1) {
      DFormattingContext var2 = new DFormattingContext(var1);
      var2.setMethodContext(var1);
      this.format(var2);
      return var2.toString();
   }

   @Override
   public String toString() {
      return this.toString(null);
   }

   @Override
   public void format(DFormattingContext var1) {
      if (var1.isDisplayCanThrow() && this.canThrow()) {
         var1.append('!');
      }

      switch (this.wS) {
         case IR_NOP:
            var1.append("nop");
            break;
         case IR_ASSIGN:
            ((IDExpression)this.UT).format(var1);
            var1.space();
            var1.append('=');
            var1.space();
            ((IDExpression)this.E).format(var1);
            break;
         case IR_JUMP:
            var1.append("jump");
            var1.space();
            ((IDTarget)this.UT).format(var1);
            break;
         case IR_JCOND:
            var1.append("jcond");
            var1.space();
            ((IDTarget)this.UT).format(var1);
            var1.append(", ");
            ((IDExpression)this.E).format(var1);
            break;
         case IR_SWITCH:
            var1.append("switch");
            var1.space();
            ((IDExpression)this.E).format(var1);
            var1.append(':');
            var1.space();
            ((IDSwitchData)this.UT).format(var1);
            break;
         case IR_RETURN:
            var1.append("return");
            if (this.E != null) {
               var1.space();
               ((IDExpression)this.E).format(var1);
            }
            break;
         case IR_INVOKE:
            ((IDInvokeInfo)this.E).format(var1);
            break;
         case IR_THROW:
            var1.append("throw");
            var1.space();
            ((IDExpression)this.E).format(var1);
            break;
         case IR_STORE_EXCEPTION:
            var1.append("catch");
            var1.space();
            ((IDVar)this.UT).format(var1);
            break;
         case IR_MONITOR_ENTER:
            var1.append("monitor-enter");
            var1.space();
            ((IDExpression)this.E).format(var1);
            break;
         case IR_MONITOR_EXIT:
            var1.append("monitor-exit");
            var1.space();
            ((IDExpression)this.E).format(var1);
            break;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public String format(Object var1) {
      return this.toString();
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      bjr var3 = (bjr)var2;
      bje var5 = null;
      ArrayList var6 = null;
      var3.UT = this;

      try {
         Object var4;
         switch (this.wS) {
            case IR_NOP:
               if (var3.sY) {
                  return null;
               }

               var4 = var3.E().create(this.sY);
               break;
            case IR_ASSIGN:
               IJavaExpression var33;
               if (this.UT instanceof bqg) {
                  var3.E = 1;
                  var33 = ((bqg)this.UT).pC(var1, var2);
                  var3.E = 0;
               } else {
                  var33 = (IJavaExpression)((bph)this.UT).generateAST(var1, var2);
               }

               IJavaExpression var37 = (IJavaExpression)((bph)this.E).generateAST(var1, var3);
               var4 = var3.getGlobalContext().createAssignment((IJavaLeftExpression)var33, var37);
               break;
            case IR_JUMP:
               int var32 = ((bqf)this.UT).getOffset();
               var4 = new bje(var3.E().create(var32));
               break;
            case IR_JCOND:
               int var31 = ((bqf)this.UT).getOffset();
               bje var36 = new bje(var3.E().create(var31));
               var5 = var36;
               IJavaGlobalContext var39 = var3.getGlobalContext();
               bjy var40 = (bjy)var39.createPredicate((IJavaExpression)((bph)this.E).generateAST(var1, var3));
               var4 = var39.createIf(var40, var39.createBlock(var36));
               break;
            case IR_SWITCH:
               IJavaConstantFactory var30 = var2.getGlobalContext().getConstantFactory();
               IDSwitchData var8 = (IDSwitchData)this.UT;
               HashMap var10 = null;
               byte var9;
               if (var8.isRegularSwitch()) {
                  var9 = 0;
                  var10 = new HashMap();
               } else {
                  if (!var8.isStringSwitch()) {
                     throw new RuntimeException("Cannot generate AST for switch");
                  }

                  var9 = 2;
               }

               IJavaExpression var11 = (IJavaExpression)((bph)this.E).generateAST(var1, var3);
               bkc var12 = (bkc)var3.getGlobalContext().createSwitch(var11, var9);
               Collection var13 = var8.getTargets(true);
               var6 = new ArrayList(var13.size());

               for (IDTarget var15 : var13) {
                  List var16 = var8.getKeysForTargets(var15);
                  int var17 = var15.getOffset();
                  bje var18 = new bje(var3.E().create(var17));
                  var6.add(var18);
                  var12.pC(var16, var3.getGlobalContext().createBlock(var18));
                  if (var9 == 0) {
                     for (Object var20 : var16) {
                        int var21 = (Integer)var20;
                        IJavaConstant var22 = var30.createInt(var21);
                        var10.put(var21, (bix)var22);
                     }
                  }
               }

               var12.pC(var10);
               var4 = var12;
               break;
            case IR_RETURN:
               IJavaExpression var29 = null;
               if (this.E != null) {
                  var29 = (IJavaExpression)((bph)this.E).generateAST(var1, var3);
               }

               var4 = var3.getGlobalContext().createReturn(var29);
               break;
            case IR_INVOKE:
               var4 = (bio)((bph)this.E).generateAST(var1, var3);
               break;
            case IR_THROW:
               IJavaExpression var28 = (IJavaExpression)((bph)this.E).generateAST(var1, var3);
               var4 = var3.getGlobalContext().createThrow(var28);
               break;
            case IR_STORE_EXCEPTION:
               if (var3.ys) {
                  if (var3.sY) {
                     return null;
                  }

                  var4 = var3.E().create(this.sY);
               } else {
                  var3.E = 2;
                  IJavaExpression var27 = ((bqg)this.UT).pC(var1, var3);
                  var3.E = 0;
                  var4 = new biu(var27);
               }
               break;
            case IR_MONITOR_ENTER:
            case IR_MONITOR_EXIT:
               IJavaExpression var7 = (IJavaExpression)((bph)this.E).generateAST(var1, var3);
               var4 = var3.getGlobalContext().createMonitor(this.wS == DOpcodeType.IR_MONITOR_ENTER, var7);
               break;
            default:
               throw new RuntimeException();
         }

         if (var4 != null) {
            ((IJavaStatement)var4).setIntermediateOffset(this.sY);
            ((IJavaStatement)var4).setPhysicalMethodIndex(this.getPhysicalMethodIndex());
            ((IJavaStatement)var4).setPhysicalOffset(this.getPhysicalOffset());
         }

         if (var5 != null) {
            var5.setIntermediateOffset(this.sY);
            var5.setPhysicalMethodIndex(this.getPhysicalMethodIndex());
            var5.setPhysicalOffset(this.getPhysicalOffset());
         }

         if (var6 == null) {
            return this.pC((IJavaElement)var4);
         } else {
            for (IJavaStatement var38 : var6) {
               var38.setIntermediateOffset(this.sY);
               var38.setPhysicalMethodIndex(this.getPhysicalMethodIndex());
               var38.setPhysicalOffset(this.getPhysicalOffset());
            }

            return this.pC((IJavaElement)var4);
         }
      } finally {
         var3.UT = null;
      }
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public String getMnemonic() {
      return Strings.toString(this.wS);
   }

   @Override
   public Collection getInstructionFlags() {
      switch (this.wS) {
         case IR_RETURN:
         case IR_THROW:
            return Arrays.asList(InstructionFlags.ROUTINE_TERMINATOR);
         default:
            return Collections.emptySet();
      }
   }

   @Override
   public boolean visitInstruction(IDVisitor var1) {
      return this.visitInstruction(var1, false);
   }

   @Override
   public boolean visitInstruction(IDVisitor var1, boolean var2) {
      return this.visitInstructionPreOrder(var1, var2);
   }

   @Override
   public boolean visitInstructionPreOrder(IDVisitor var1, boolean var2) {
      DVisitResults var3 = new DVisitResults(1 | (var2 ? 2 : 0));
      return this.visitDepthPre(var1, null, var3);
   }

   @Override
   public boolean visitInstructionPostOrder(IDVisitor var1, boolean var2) {
      DVisitResults var3 = new DVisitResults(1 | (var2 ? 2 : 0));
      return this.visitDepthPost(var1, null, var3);
   }

   @Override
   public void verify() throws IllegalStateException {
      if (!(switch (this.wS) {
         case IR_NOP -> this.UT == null && this.E == null;
         case IR_ASSIGN -> this.UT instanceof IDExpression && this.E instanceof IDExpression;
         case IR_JUMP -> this.UT instanceof IDTarget && this.E == null;
         case IR_JCOND -> this.UT instanceof IDTarget && this.E instanceof IDExpression;
         case IR_SWITCH -> this.UT instanceof IDSwitchData && this.E instanceof IDExpression;
         case IR_RETURN -> this.UT == null && (this.E == null || this.E instanceof IDExpression);
         case IR_INVOKE -> this.UT == null && this.E instanceof IDInvokeInfo;
         case IR_THROW -> this.UT == null && this.E instanceof IDExpression;
         case IR_STORE_EXCEPTION -> this.UT instanceof IDVar && this.E == null;
         case IR_MONITOR_ENTER, IR_MONITOR_EXIT -> this.UT == null && this.E instanceof IDExpression;
         default -> throw new RuntimeException();
      })) {
         throw new IllegalStateException("Unexpected operand");
      }
   }

   public interface Av {
      void customize(IDInstruction var1, List var2, List var3);
   }

   static class Sv implements IDVisitor {
      List pC = new ArrayList();

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDVar) {
            this.pC.add((IDVar)var1);
         }
      }
   }
}
