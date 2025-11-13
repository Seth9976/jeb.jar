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
public class bub extends btk implements IDInstruction {
   private static final ILogger nf = GlobalLog.getLogger(bub.class, Integer.MAX_VALUE);
   public static final int RF = 0;
   public static final boolean xK = false;
   public static final boolean Dw = false;
   public static final String Uv = "KEEP_LAST";
   public static final String oW = "KEEP_FIRST";
   public static final String gO = "THIS_NOTNULL";
   private IDMethodContext gP;
   private DOpcodeType za;
   private IDElement lm;
   private IDElement zz;
   private int JY = -1;
   private int HF = 1;
   private bub.eo LK;

   bub(IDMethodContext var1, DOpcodeType var2, IDElement var3, IDElement var4) {
      super(null);
      this.gP = var1;
      this.q(var2, var3, var4);
   }

   bub(IDMethodContext var1, DOpcodeType var2, IDElement var3) {
      this(var1, var2, var3, null);
   }

   bub(IDMethodContext var1, DOpcodeType var2) {
      this(var1, var2, null, null);
   }

   @Override
   public IDMethodContext getContext() {
      return this.gP;
   }

   @Override
   public IDMethodContext setContext(IDMethodContext var1) {
      IDMethodContext var2 = this.gP;
      this.gP = var1;
      return var2;
   }

   @Override
   public void morph(DOpcodeType var1, IDElement var2, IDElement var3) {
      this.q(var1, var2, var3);
   }

   public void q(DOpcodeType var1, IDElement var2, IDElement var3) {
      if (var1 == null) {
         throw new RuntimeException("Illegal opcode, cannot be null");
      } else {
         this.za = var1;
         this.lm = var2;
         this.zz = var3;
         boolean var4 = false;

         var4 = switch (var1) {
            case IR_NOP -> {
               if (var2 != null || var3 != null) {
                  yield true;
               }
            }
            case IR_ASSIGN -> {
               if (!(var2 instanceof btk) || !(var3 instanceof btk)) {
                  yield true;
               }
            }
            case IR_JUMP -> {
               if (!(var2 instanceof bul) || var3 != null) {
                  yield true;
               }
            }
            case IR_JCOND -> {
               if (!(var2 instanceof bul) || !(var3 instanceof IDExpression)) {
                  yield true;
               }
            }
            case IR_SWITCH -> {
               if (!(var2 instanceof buk) || !(var3 instanceof btk)) {
                  yield true;
               }
            }
            case IR_RETURN -> {
               if (var2 != null || var3 != null && !(var3 instanceof btk)) {
                  yield true;
               }
            }
            case IR_INVOKE -> {
               if (var2 != null || !(var3 instanceof IDInvokeInfo)) {
                  yield true;
               }
            }
            case IR_THROW -> {
               if (var2 != null || !(var3 instanceof btk)) {
                  yield true;
               }
            }
            case IR_STORE_EXCEPTION -> {
               if (!(var2 instanceof bum) || var3 != null) {
                  yield true;
               }
            }
            case IR_MONITOR_ENTER, IR_MONITOR_EXIT -> {
               if (var2 != null || !(var3 instanceof btk)) {
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

   public boolean q(IDInstruction var1) {
      if (!(var1 instanceof bub var2)) {
         return false;
      } else if (this.za != var2.za) {
         return false;
      } else {
         if (this.lm == null) {
            if (var2.lm != null) {
               return false;
            }
         } else if (!this.lm.equalsEx(var2.lm, false)) {
            return false;
         }

         if (this.zz == null) {
            if (var2.zz != null) {
               return false;
            }
         } else if (!this.zz.equalsEx(var2.zz, false)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IDInstruction copy(DCopyOptions var1) {
      if (var1 != null) {
         bub var2 = (bub)var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bub var3 = new bub(this.gP, this.za, this.lm == null ? null : this.lm.copy(var1), this.zz == null ? null : this.zz.copy(var1));
      var3.JY = this.JY;
      var3.HF = this.HF;
      var3.LK = this.LK;
      super.RF(var3);
      return var3;
   }

   @Override
   public IDInstruction duplicate() {
      return this.copy(null);
   }

   @Override
   public void copyBaseFields(IDInstruction var1) {
      this.gP = ((bub)var1).gP;
      this.JY = ((bub)var1).JY;
      this.HF = ((bub)var1).HF;
      this.LK = ((bub)var1).LK;
      ((bub)var1).RF(this);
   }

   @Override
   public IDInstruction duplicateForReplacement(IDInstruction var1) {
      bub var2 = (bub)this.duplicate();
      var2.JY = ((bub)var1).JY;
      var2.HF = ((bub)var1).HF;
      var2.LK = ((bub)var1).LK;
      return var2;
   }

   @Override
   public IDInstruction duplicateWithOffsetAndSize(long var1, int var3) {
      bub var4 = (bub)this.duplicate();
      var4.JY = (int)var1;
      var4.HF = var3;
      return var4;
   }

   @Override
   public DOpcodeType getOpcode() {
      return this.za;
   }

   @Override
   public void setOpcode(DOpcodeType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.za = var1;
      }
   }

   @Override
   public boolean isOpcode(DOpcodeType... var1) {
      return this.za.isAnyOf(var1);
   }

   public boolean oW() {
      return this.za == DOpcodeType.IR_ASSIGN || this.za == DOpcodeType.IR_STORE_EXCEPTION;
   }

   public boolean gO() {
      return this.oW() && this.getOperand1() instanceof bum;
   }

   @Override
   public IDElement getOperand1() {
      return this.lm;
   }

   @Override
   public void setOperand1(IDElement var1) {
      this.lm = var1;
   }

   @Override
   public IDElement getOperand2() {
      return this.zz;
   }

   @Override
   public void setOperand2(IDElement var1) {
      this.zz = var1;
   }

   @Override
   public int getProcessorMode() {
      return 0;
   }

   @Override
   public long getOffset() {
      return this.JY;
   }

   @Override
   public long getOffsetEnd() {
      return (long)this.JY + this.HF;
   }

   @Override
   public void setOffset(long var1) {
      if (var1 > 2147483647L) {
         throw new IllegalArgumentException("IR offset exceeds 2^31");
      } else {
         this.JY = (int)var1;
      }
   }

   @Override
   public IDInstruction withOffset(long var1) {
      this.setOffset(var1);
      return this;
   }

   @Override
   public int getSize() {
      return this.HF;
   }

   @Override
   public void setSize(int var1) {
      if (var1 <= 0) {
         throw new RuntimeException();
      } else {
         this.HF = var1;
      }
   }

   @Override
   public IDInstruction withSize(int var1) {
      this.setSize(var1);
      return this;
   }

   @Override
   public void adjustSize(int var1) {
      if (this.HF + var1 <= 0) {
         throw new RuntimeException();
      } else {
         this.HF += var1;
      }
   }

   @Override
   public byte[] getCode() {
      return null;
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return new IInstructionOperand[]{this.lm, this.zz};
   }

   @Override
   public void collectVarIds(Set var1) {
      if (this.lm instanceof IDExpression) {
         ((IDExpression)this.lm).collectVarIds(var1);
      }

      if (this.zz instanceof IDExpression) {
         ((IDExpression)this.zz).collectVarIds(var1);
      }
   }

   @Override
   public boolean canThrow() {
      return this.canThrow(this.gP);
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      switch (this.za) {
         case IR_JUMP:
            return false;
         case IR_JCOND:
         case IR_SWITCH:
         case IR_RETURN:
         case IR_INVOKE:
         case IR_STORE_EXCEPTION:
         default:
            if (this.lm instanceof btk && ((btk)this.lm).canThrow(var1)) {
               return true;
            } else {
               if (this.zz instanceof btk && ((btk)this.zz).canThrow(var1)) {
                  return true;
               }

               return false;
            }
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return true;
      }
   }

   @Override
   public boolean isNop() {
      return this.za == DOpcodeType.IR_NOP;
   }

   @Override
   public boolean isAssign() {
      return this.za == DOpcodeType.IR_ASSIGN;
   }

   @Override
   public IDExpression getAssignDestination() {
      Assert.a(this.za == DOpcodeType.IR_ASSIGN);
      return (IDExpression)this.lm;
   }

   @Override
   public IDExpression setAssignDestination(IDExpression var1) {
      Assert.a(this.za == DOpcodeType.IR_ASSIGN && var1 != null);
      IDExpression var2 = (IDExpression)this.lm;
      this.lm = var1;
      return var2;
   }

   @Override
   public IDExpression getAssignSource() {
      Assert.a(this.za == DOpcodeType.IR_ASSIGN);
      return (btk)this.zz;
   }

   @Override
   public IDExpression setAssignSource(IDExpression var1) {
      Assert.a(this.za == DOpcodeType.IR_ASSIGN && var1 != null);
      IDExpression var2 = (IDExpression)this.zz;
      this.zz = var1;
      return var2;
   }

   @Override
   public boolean isReturn() {
      return this.za == DOpcodeType.IR_RETURN;
   }

   @Override
   public IDExpression getReturnExpression() {
      Assert.a(this.za == DOpcodeType.IR_RETURN);
      return (btk)this.zz;
   }

   @Override
   public IDExpression setReturnExpression(IDExpression var1) {
      Assert.a(this.za == DOpcodeType.IR_RETURN);
      IDExpression var2 = (IDExpression)this.zz;
      this.zz = var1;
      return var2;
   }

   @Override
   public boolean isThrow() {
      return this.za == DOpcodeType.IR_THROW;
   }

   @Override
   public IDExpression getThrowExpression() {
      Assert.a(this.za == DOpcodeType.IR_THROW);
      return (IDExpression)this.zz;
   }

   @Override
   public IDExpression setThrowExpression(IDExpression var1) {
      Assert.a(this.za == DOpcodeType.IR_THROW && var1 != null);
      IDExpression var2 = (IDExpression)this.zz;
      this.zz = var1;
      return var2;
   }

   @Override
   public boolean isReturnOrThrow() {
      return this.za.isAnyOf(DOpcodeType.IR_RETURN, DOpcodeType.IR_THROW);
   }

   @Override
   public void transformToNop() {
      this.za = DOpcodeType.IR_NOP;
      this.lm = null;
      this.zz = null;
   }

   @Override
   public void transformToJump(int var1) {
      this.transformToJump(this.gP.getGlobalContext().createTarget(var1));
   }

   @Override
   public void transformToJump(IDTarget var1) {
      this.za = DOpcodeType.IR_JUMP;
      this.lm = var1;
      this.zz = null;
   }

   @Override
   public void transformJcondToJump() {
      Assert.a(this.za == DOpcodeType.IR_JCOND);
      this.za = DOpcodeType.IR_JUMP;
      this.zz = null;
   }

   @Override
   public void transformJcondToAssign(IDVar var1) {
      Assert.a(this.za == DOpcodeType.IR_JCOND);
      this.za = DOpcodeType.IR_ASSIGN;
      this.lm = var1;
   }

   @Override
   public boolean transformSwitchToJcond() {
      Assert.a(this.za == DOpcodeType.IR_SWITCH);
      buk var1 = (buk)this.lm;
      if (var1.getCaseCount() != 1) {
         return false;
      } else if (!var1.isRegularSwitch()) {
         return false;
      } else {
         int var2 = (Integer)var1.getCases().iterator().next();
         IDTarget var3 = (IDTarget)var1.getTargets(false).iterator().next();
         IDExpression var4 = (IDExpression)this.zz;
         this.za = DOpcodeType.IR_JCOND;
         this.lm = var3;
         this.zz = this.gP
            .getGlobalContext()
            .createPredicate(
               var4, this.gP.getOperatorFactory().getStandardOperator(JavaOperatorType.EQ), this.gP.getGlobalContext().createConstant(var2, var4.getType())
            );
         return true;
      }
   }

   @Override
   public int updateTargets(Map var1) {
      return this.updateTargets(var1, false);
   }

   @Override
   public int updateTargets(Map var1, boolean var2) {
      switch (this.za) {
         case IR_JUMP:
         case IR_JCOND:
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
         case IR_SWITCH:
            return this.getSwitchData().updateCases(var1, var2);
         default:
            return 0;
      }
   }

   @Override
   public boolean isSwitch() {
      return this.za == DOpcodeType.IR_SWITCH;
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
      Assert.a(this.za == DOpcodeType.IR_SWITCH);
      return (IDSwitchData)this.lm;
   }

   @Override
   public IDSwitchData setSwitchData(IDSwitchData var1) {
      Assert.a(this.za == DOpcodeType.IR_SWITCH && var1 != null);
      IDSwitchData var2 = (IDSwitchData)this.lm;
      this.lm = var1;
      return var2;
   }

   @Override
   public IDExpression getSwitchExpression() {
      Assert.a(this.za == DOpcodeType.IR_SWITCH);
      return (IDExpression)this.zz;
   }

   @Override
   public IDExpression setSwitchExpression(IDExpression var1) {
      Assert.a(this.za == DOpcodeType.IR_SWITCH && var1 != null);
      IDExpression var2 = (IDExpression)this.zz;
      this.zz = var1;
      return var2;
   }

   @Override
   public boolean isJumpOrJcond() {
      return this.za == DOpcodeType.IR_JUMP || this.za == DOpcodeType.IR_JCOND;
   }

   @Override
   public boolean isJumpOrJcondTo(int var1) {
      return this.za.isAnyOf(DOpcodeType.IR_JUMP, DOpcodeType.IR_JCOND) && ((bul)this.lm).getOffset() == var1;
   }

   @Override
   public boolean isJcondOrSwitch() {
      return this.za == DOpcodeType.IR_JCOND || this.za == DOpcodeType.IR_SWITCH;
   }

   @Override
   public int setBranchTarget(int var1) {
      Assert.a(this.za == DOpcodeType.IR_JUMP || this.za == DOpcodeType.IR_JCOND);
      int var2 = ((IDTarget)this.lm).getOffset();
      ((IDTarget)this.lm).setOffset(var1);
      return var2;
   }

   @Override
   public int getBranchTarget() {
      Assert.a(this.za == DOpcodeType.IR_JUMP || this.za == DOpcodeType.IR_JCOND);
      return ((bul)this.lm).getOffset();
   }

   @Override
   public boolean isJump() {
      return this.za == DOpcodeType.IR_JUMP;
   }

   @Override
   public boolean isJumpTo(int var1) {
      return this.za == DOpcodeType.IR_JUMP && ((bul)this.lm).getOffset() == var1;
   }

   @Override
   public boolean isJcond() {
      return this.za == DOpcodeType.IR_JCOND;
   }

   @Override
   public boolean isJcondTo(int var1) {
      return this.za == DOpcodeType.IR_JCOND && ((bul)this.lm).getOffset() == var1;
   }

   @Override
   public IDExpression getJcondCondition() {
      Assert.a(this.za == DOpcodeType.IR_JCOND);
      return (IDExpression)this.zz;
   }

   @Override
   public IDExpression setJcondCondition(IDExpression var1) {
      Assert.a(this.za == DOpcodeType.IR_JCOND && var1 != null);
      IDExpression var2 = (IDExpression)this.zz;
      this.zz = var1;
      return var2;
   }

   @Override
   public void reverseJcondCondition() {
      Assert.a(this.za == DOpcodeType.IR_JCOND);
      this.zz = bto.q((IDExpression)this.zz, this.gP.getGlobalContext());
   }

   @Override
   public boolean isInvoke() {
      return this.za == DOpcodeType.IR_INVOKE;
   }

   @Override
   public IDInvokeInfo getInvokeData() {
      Assert.a(this.za == DOpcodeType.IR_INVOKE);
      return (IDInvokeInfo)this.zz;
   }

   @Override
   public boolean isStoreException() {
      return this.za == DOpcodeType.IR_STORE_EXCEPTION;
   }

   @Override
   public IDVar getStoredExceptionVariable() {
      Assert.a(this.za == DOpcodeType.IR_STORE_EXCEPTION);
      return (IDVar)this.lm;
   }

   @Override
   public IDVar setStoredExceptionVariable(IDVar var1) {
      Assert.a(this.za == DOpcodeType.IR_STORE_EXCEPTION && var1 != null);
      IDVar var2 = (IDVar)this.lm;
      this.lm = var1;
      return var2;
   }

   @Override
   public boolean isMonitorEnter() {
      return this.za == DOpcodeType.IR_MONITOR_ENTER;
   }

   @Override
   public boolean isMonitorExit() {
      return this.za == DOpcodeType.IR_MONITOR_EXIT;
   }

   public bum nf() {
      return this.za != DOpcodeType.IR_STORE_EXCEPTION ? null : (bum)this.lm;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if (var1 != this.JY) {
         throw new RuntimeException();
      } else {
         return this.getBreakingFlow();
      }
   }

   @Override
   public IFlowInformation getBreakingFlow() {
      FlowInformation var1 = new FlowInformation();
      switch (this.za) {
         case IR_JUMP:
            long var10 = ((bul)this.lm).getOffset();
            var1.addTarget(new CodePointer(var10));
            break;
         case IR_JCOND:
            long var9 = this.getOffsetEnd();
            var1.addTarget(new CodePointer(var9));
            long var4 = ((bul)this.lm).getOffset();
            if (var4 != var9) {
               var1.addTarget(new CodePointer(var4));
            }
            break;
         case IR_SWITCH:
            HashSet var2 = new HashSet();
            long var3 = this.getOffsetEnd();
            var2.add(var3);
            var1.addTarget(new CodePointer(var3));

            for (IDTarget var6 : ((IDSwitchData)this.lm).getTargets(false)) {
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

   public FlowInformation q(long var1) {
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
      if (var1 != this.JY) {
         throw new RuntimeException();
      } else {
         return IDInstruction.super.getPrimaryBranchAddress(var1);
      }
   }

   @Override
   public long getPrimaryBranchAddress() {
      return this.getPrimaryBranchAddress(this.JY);
   }

   public bub.eo q(bub.eo var1) {
      bub.eo var2 = this.LK;
      this.LK = var1;
      return var2;
   }

   public bub.eo gP() {
      return this.LK;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      var1.clear();
      var2.clear();
      Set var4 = null;
      if (this.lm instanceof IDExpression) {
         var4 = ((IDExpression)this.lm).getVarIds();
      }

      Set var5 = null;
      if (this.zz instanceof IDExpression) {
         var5 = ((IDExpression)this.zz).getVarIds();
      }

      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            if (this.lm instanceof bum) {
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
            if (this.zz != null) {
               var2.addAll(var5);
            }
            break;
         case IR_STORE_EXCEPTION:
            var1.addAll(var4);
            break;
         default:
            throw new RuntimeException();
      }

      if (this.LK != null) {
         this.LK.customize(this, var1, var2);
      }
   }

   @Override
   public IDVar getDefinedVariable() {
      switch (this.za) {
         case IR_ASSIGN:
         case IR_STORE_EXCEPTION:
            if (this.lm instanceof IDVar) {
               return (IDVar)this.lm;
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
      if (this.za == DOpcodeType.IR_ASSIGN && this.lm instanceof IDVar) {
         var1 = (IDExpression)this.zz;
      } else {
         if (this.za == DOpcodeType.IR_STORE_EXCEPTION) {
            return new ArrayList();
         }

         var1 = this;
      }

      bub.CU var2 = new bub.CU();
      ((IDExpression)var1).visitDepthPost(var2);
      return var2.q;
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            IDExpression var14 = (IDExpression)this.lm;
            IDExpression var15 = (IDExpression)this.zz;
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
            IDExpression var13 = (IDExpression)this.zz;
            var13.updateTypes(var1);
            Assert.a(var13.getType().isBoolean());
            break;
         case IR_SWITCH:
            IDExpression var12 = (IDExpression)this.zz;
            var12.updateTypes(var1);
            if (!var12.getType().isDefined()) {
               var12.setType(this.gP.getTypeFactory().getSmallIntWildcard(), var1);
            }
            break;
         case IR_RETURN:
            IDExpression var11 = (IDExpression)this.zz;
            if (var11 != null) {
               var11.updateTypes(var1);
            }
            break;
         case IR_INVOKE:
            IDInvokeInfo var10 = (IDInvokeInfo)this.zz;
            var10.updateTypes(var1);
            break;
         case IR_THROW:
            IDExpression var9 = (IDExpression)this.zz;
            var9.updateTypes(var1);
            if (!var9.getType().isDefined()) {
               var9.setType(this.gP.getTypeFactory().createWildcardType("Ljava/lang/Throwable;", true), var1);
            }
            break;
         case IR_STORE_EXCEPTION:
            IDVar var8 = (IDVar)this.lm;
            var8.updateTypes(var1);
            if (!var8.getType().isDefined()) {
               String var3 = null;
               if (!this.gP.getExceptionData().isEmpty()) {
                  IDTypeInfoProvider var4 = this.gP.getGlobalContext().getTypeInfoProvider();

                  for (int var6 : this.gP.getExceptionData().getHandledExceptionTypesAt((int)this.getOffset())) {
                     String var7;
                     if (var6 == -1) {
                        var7 = "Ljava/lang/Throwable;";
                     } else {
                        var7 = this.gP.getDex().getType(var6).getSignature(false);
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

               var8.setType(this.gP.getTypeFactory().createWildcardType(var3, true));
            }
            break;
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            IDExpression var2 = (IDExpression)this.zz;
            var2.updateTypes(var1);
            if (!var2.getType().isDefined()) {
               var2.setType(this.gP.getTypeFactory().getGenericObjectWildcard());
            }
            break;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean q() {
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            return true;
         case IR_ASSIGN:
            return ((btk)this.zz).q() && ((btk)this.lm).q();
         case IR_JCOND:
            return ((btk)this.zz).q();
         case IR_SWITCH:
            return ((btk)this.zz).q();
         case IR_RETURN:
            return this.zz == null ? true : ((btk)this.zz).q();
         case IR_INVOKE:
            return ((btk)this.zz).q();
         case IR_THROW:
            return ((btk)this.zz).q();
         case IR_STORE_EXCEPTION:
            return ((bum)this.lm).q();
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return ((btk)this.zz).q();
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      switch (this.za) {
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
            return ((btk)this.zz).hasSideEffects(var1, var2);
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean hasUseSideEffects(boolean var1) {
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
         case IR_STORE_EXCEPTION:
            return false;
         case IR_ASSIGN:
            return ((btk)this.zz).hasSideEffects(this.gP, var1) || ((btk)this.lm).hasSideEffects(this.gP, var1);
         case IR_JCOND:
            return ((IDExpression)this.zz).hasSideEffects(this.gP, var1);
         case IR_SWITCH:
            return ((btk)this.zz).hasSideEffects(this.gP, var1);
         case IR_RETURN:
            return this.zz == null ? false : ((btk)this.zz).hasSideEffects(this.gP, var1);
         case IR_INVOKE:
            return ((btk)this.zz).hasSideEffects(this.gP, var1);
         case IR_THROW:
            return ((btk)this.zz).hasSideEffects(this.gP, var1);
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return ((btk)this.zz).hasSideEffects(this.gP, var1);
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean RF() {
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            return false;
         case IR_ASSIGN:
            return ((btk)this.zz).RF() || ((btk)this.lm).RF();
         case IR_JCOND:
            return ((btk)this.zz).RF();
         case IR_SWITCH:
            return ((btk)this.zz).RF();
         case IR_RETURN:
            return this.zz == null ? false : ((btk)this.zz).RF();
         case IR_INVOKE:
            return ((btk)this.zz).RF();
         case IR_THROW:
            return ((btk)this.zz).RF();
         case IR_STORE_EXCEPTION:
            return ((bum)this.lm).RF();
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return ((btk)this.zz).RF();
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean xK() {
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            return false;
         case IR_ASSIGN:
            return ((btk)this.zz).xK() || ((btk)this.lm).xK();
         case IR_JCOND:
            return ((btk)this.zz).xK();
         case IR_SWITCH:
            return ((btk)this.zz).xK();
         case IR_RETURN:
            return this.zz == null ? false : ((btk)this.zz).xK();
         case IR_INVOKE:
            return ((btk)this.zz).xK();
         case IR_THROW:
            return ((btk)this.zz).xK();
         case IR_STORE_EXCEPTION:
            return ((bum)this.lm).xK();
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            return ((btk)this.zz).xK();
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

      return switch (this.za) {
         case IR_NOP, IR_JUMP, IR_STORE_EXCEPTION -> {
         }
         case IR_ASSIGN -> {
            if (!(this.lm instanceof IDVar)) {
               yield ((btk)this.lm).countVariable(var1);
            }

            yield ((btk)this.zz).countVariable(var1);
         }
         case IR_JCOND -> ((btk)this.zz).countVariable(var1);
         case IR_SWITCH -> ((btk)this.zz).countVariable(var1);
         case IR_RETURN -> {
            if (this.zz != null) {
               yield ((btk)this.zz).countVariable(var1);
            }
         }
         case IR_INVOKE -> ((btk)this.zz).countVariable(var1);
         case IR_THROW -> ((btk)this.zz).countVariable(var1);
         case IR_MONITOR_ENTER, IR_MONITOR_EXIT -> ((btk)this.zz).countVariable(var1);
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
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
         case IR_STORE_EXCEPTION:
            break;
         case IR_ASSIGN:
            if (!(this.lm instanceof IDVar)) {
               var3 += ((IDExpression)this.lm).replaceVariable(var1, var2);
            }

            if (this.zz == var1) {
               this.zz = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.zz).replaceVariable(var1, var2);
            }
            break;
         case IR_JCOND:
            if (this.zz == var1) {
               this.zz = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.zz).replaceVariable(var1, var2);
            }
            break;
         case IR_SWITCH:
            if (this.zz == var1) {
               this.zz = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.zz).replaceVariable(var1, var2);
            }
            break;
         case IR_RETURN:
            if (this.zz == var1) {
               this.zz = var2 == null ? null : var2.duplicate();
               var3++;
            } else if (this.zz != null) {
               var3 += ((IDExpression)this.zz).replaceVariable(var1, var2);
            }
            break;
         case IR_INVOKE:
            var3 += ((IDInvokeInfo)this.zz).replaceVariable(var1, var2);
            break;
         case IR_THROW:
            if (this.zz == var1) {
               this.zz = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.zz).replaceVariable(var1, var2);
            }
            break;
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            if (this.zz == var1) {
               this.zz = var2.duplicate();
               var3++;
            } else {
               var3 += ((IDExpression)this.zz).replaceVariable(var1, var2);
            }
            break;
         default:
            throw new RuntimeException();
      }

      return var3;
   }

   @Override
   public int replaceDefinedVariable(IDVar var1, IDExpression var2) {
      switch (this.za) {
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
            if (this.lm == var1) {
               this.lm = var2.duplicate();
               return 1;
            }

            return 0;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public void q(IJavaType var1, IJavaType var2) {
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            ((btk)this.lm).q(var1, var2);
            ((btk)this.zz).q(var1, var2);
            break;
         case IR_JCOND:
            ((btk)this.zz).q(var1, var2);
            break;
         case IR_SWITCH:
            ((btk)this.zz).q(var1, var2);
            break;
         case IR_RETURN:
            if (this.zz != null) {
               ((btk)this.zz).q(var1, var2);
            }
            break;
         case IR_INVOKE:
            ((btk)this.zz).q(var1, var2);
            break;
         case IR_THROW:
            ((btk)this.zz).q(var1, var2);
            break;
         case IR_STORE_EXCEPTION:
            ((bum)this.lm).q(var1, var2);
            break;
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            ((btk)this.zz).q(var1, var2);
            break;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            if (this.zz != null) {
               var1.add((IDExpression)this.zz);
            }

            if (this.lm != null) {
               var1.add((IDExpression)this.lm);
            }
            break;
         case IR_JCOND:
         case IR_SWITCH:
         case IR_RETURN:
         case IR_INVOKE:
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            if (this.zz != null) {
               var1.add((IDExpression)this.zz);
            }
            break;
         case IR_STORE_EXCEPTION:
            if (this.lm != null) {
               var1.add((IDExpression)this.lm);
            }
            break;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public Integer evaluate(Map var1) throws DexDecEvaluationException {
      IDState var2 = this.gP.getGlobalContext().createState();

      Integer var4;
      try {
         var2.setSubRoutineInvocationPolicy(0);
         var2.setFieldAccessPolicy(0);
         var2.enableEmulator(true);
         var2.enableSandbox(false);
         var2.setMaxIterationCount(1);
         var2.pushContext("context");
         IDEmuFrame var3 = var2.pushFrame(this.gP.getMethodSignature());
         var3.getVarMap().putAll(var1);
         var3.setPC(this.JY);
         this.evaluate(var2);
         var4 = var3.getNextPC();
      } finally {
         var2.dispose();
      }

      return var4;
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      bye var2 = (bye)var1;
      Assert.a(var2.getPC() == this.getOffset(), "Unexpected PC value");
      var2.JY();
      IDImm var3 = null;
      switch (this.za) {
         case IR_NOP:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            break;
         case IR_ASSIGN:
            var3 = ((btk)this.zz).evaluate(var2);
            if (this.lm instanceof bum var12) {
               var2.setVariable(var12.getId(), var3);
            } else if (this.lm instanceof btt) {
               IDImm var13 = ((btt)this.lm).getArray().evaluate(var2);
               IDImm var16 = ((btt)this.lm).getIndex().evaluate(var2);
               var2.setArrayElement(var13, (int)var16.toLong(), var3);
            } else if (this.lm instanceof buj) {
               String var14 = ((buj)this.lm).getClassSignature();
               String var17 = ((buj)this.lm).getFieldname();
               String var19 = ((buj)this.lm).getType().getName();
               var2.q(var14, var17, var19, var3);
            } else {
               if (!(this.lm instanceof bua)) {
                  throw new DexDecEvaluationException("Not implememented");
               }

               String var15 = var2.getDex().getField(((bua)this.lm).getIndex().getValue()).getSignature(false);
               IDImm var18 = ((bua)this.lm).getInstance().evaluate(var2);
               var2.setInstanceField(var15, var18, var3);
            }
            break;
         case IR_JUMP:
            var2.RF(this.getBranchTarget());
            return null;
         case IR_JCOND:
            IDImm var11 = ((btk)this.zz).evaluate(var2);
            if (!var11.isZero()) {
               var2.RF(this.getBranchTarget());
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
               var2.RF(var6.getOffset());
               return null;
            }
            break;
         case IR_RETURN:
            if (this.zz != null) {
               var3 = ((btk)this.zz).evaluate(var2);
            }

            var2.getCurrentFrame().setExecutionComplete(true);
            return var3;
         case IR_INVOKE:
            var3 = ((btk)this.zz).evaluate(var2);
            break;
         case IR_THROW:
            IDImm var9 = ((btk)this.zz).evaluate(var2);
            if (var9.maybeNullRef()) {
               var9 = var2.registerObject(new NullPointerException());
            }

            throw new DexDecEvalCodeThrownException(var9);
         case IR_STORE_EXCEPTION:
            bum var4 = (bum)this.lm;
            var2.setVariable(var4.getId(), var2.CE());
            break;
         default:
            throw new RuntimeException();
      }

      var2.RF((int)this.getOffsetEnd());
      return var3;
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      int var3 = 0;
      switch (this.za) {
         case IR_NOP:
         case IR_JUMP:
            break;
         case IR_ASSIGN:
            if (this.lm == var1 && var2 != null) {
               this.lm = q(var2, var3);
               var3++;
            }

            if (this.zz == var1 && var2 != null) {
               this.zz = q(var2, var3);
               var3++;
            }
            break;
         case IR_JCOND:
            if (this.zz == var1 && var2 != null) {
               this.zz = q(var2, var3);
               var3++;
            }
            break;
         case IR_SWITCH:
         case IR_THROW:
         case IR_MONITOR_ENTER:
         case IR_MONITOR_EXIT:
            if (this.zz == var1 && var2 != null) {
               this.zz = q(var2, var3);
               var3++;
            }
            break;
         case IR_RETURN:
            if (this.zz == var1) {
               this.zz = q(var2, var3);
               return true;
            }
            break;
         case IR_INVOKE:
            if (this.zz == var1 && var2 instanceof IDInvokeInfo) {
               this.zz = q(var2, var3);
               var3++;
            }
            break;
         case IR_STORE_EXCEPTION:
            if (this.lm == var1 && var2 instanceof IDVar) {
               this.lm = q(var2, var3);
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

      switch (this.za) {
         case IR_NOP:
            var1.append("nop");
            break;
         case IR_ASSIGN:
            ((IDExpression)this.lm).format(var1);
            var1.space();
            var1.append('=');
            var1.space();
            ((IDExpression)this.zz).format(var1);
            break;
         case IR_JUMP:
            var1.append("jump");
            var1.space();
            ((IDTarget)this.lm).format(var1);
            break;
         case IR_JCOND:
            var1.append("jcond");
            var1.space();
            ((IDTarget)this.lm).format(var1);
            var1.append(", ");
            ((IDExpression)this.zz).format(var1);
            break;
         case IR_SWITCH:
            var1.append("switch");
            var1.space();
            ((IDExpression)this.zz).format(var1);
            var1.append(':');
            var1.space();
            ((IDSwitchData)this.lm).format(var1);
            break;
         case IR_RETURN:
            var1.append("return");
            if (this.zz != null) {
               var1.space();
               ((IDExpression)this.zz).format(var1);
            }
            break;
         case IR_INVOKE:
            ((IDInvokeInfo)this.zz).format(var1);
            break;
         case IR_THROW:
            var1.append("throw");
            var1.space();
            ((IDExpression)this.zz).format(var1);
            break;
         case IR_STORE_EXCEPTION:
            var1.append("catch");
            var1.space();
            ((IDVar)this.lm).format(var1);
            break;
         case IR_MONITOR_ENTER:
            var1.append("monitor-enter");
            var1.space();
            ((IDExpression)this.zz).format(var1);
            break;
         case IR_MONITOR_EXIT:
            var1.append("monitor-exit");
            var1.space();
            ((IDExpression)this.zz).format(var1);
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
      bno var3 = (bno)var2;
      bnb var5 = null;
      ArrayList var6 = null;
      var3.za = this;

      try {
         Object var4;
         switch (this.za) {
            case IR_NOP:
               if (var3.zz) {
                  return null;
               }

               var4 = var3.nf().create(this.JY);
               break;
            case IR_ASSIGN:
               IJavaExpression var33;
               if (this.lm instanceof bum) {
                  var3.lm = 1;
                  var33 = ((bum)this.lm).q(var1, var2);
                  var3.lm = 0;
               } else {
                  var33 = (IJavaExpression)((btk)this.lm).generateAST(var1, var2);
               }

               IJavaExpression var37 = (IJavaExpression)((btk)this.zz).generateAST(var1, var3);
               var4 = var3.getGlobalContext().createAssignment((IJavaLeftExpression)var33, var37);
               break;
            case IR_JUMP:
               int var32 = ((bul)this.lm).getOffset();
               var4 = new bnb(var3.nf().create(var32));
               break;
            case IR_JCOND:
               int var31 = ((bul)this.lm).getOffset();
               bnb var36 = new bnb(var3.nf().create(var31));
               var5 = var36;
               IJavaGlobalContext var39 = var3.getGlobalContext();
               bnv var40 = (bnv)var39.createPredicate((IJavaExpression)((btk)this.zz).generateAST(var1, var3));
               var4 = var39.createIf(var40, var39.createBlock(var36));
               break;
            case IR_SWITCH:
               IJavaConstantFactory var30 = var2.getGlobalContext().getConstantFactory();
               IDSwitchData var8 = (IDSwitchData)this.lm;
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

               IJavaExpression var11 = (IJavaExpression)((btk)this.zz).generateAST(var1, var3);
               bnz var12 = (bnz)var3.getGlobalContext().createSwitch(var11, var9);
               Collection var13 = var8.getTargets(true);
               var6 = new ArrayList(var13.size());

               for (IDTarget var15 : var13) {
                  List var16 = var8.getKeysForTargets(var15);
                  int var17 = var15.getOffset();
                  bnb var18 = new bnb(var3.nf().create(var17));
                  var6.add(var18);
                  var12.q(var16, var3.getGlobalContext().createBlock(var18));
                  if (var9 == 0) {
                     for (Object var20 : var16) {
                        int var21 = (Integer)var20;
                        IJavaConstant var22 = var30.createInt(var21);
                        var10.put(var21, (bmu)var22);
                     }
                  }
               }

               var12.q(var10);
               var4 = var12;
               break;
            case IR_RETURN:
               IJavaExpression var29 = null;
               if (this.zz != null) {
                  var29 = (IJavaExpression)((btk)this.zz).generateAST(var1, var3);
               }

               var4 = var3.getGlobalContext().createReturn(var29);
               break;
            case IR_INVOKE:
               var4 = (bml)((btk)this.zz).generateAST(var1, var3);
               break;
            case IR_THROW:
               IJavaExpression var28 = (IJavaExpression)((btk)this.zz).generateAST(var1, var3);
               var4 = var3.getGlobalContext().createThrow(var28);
               break;
            case IR_STORE_EXCEPTION:
               if (var3.JY) {
                  if (var3.zz) {
                     return null;
                  }

                  var4 = var3.nf().create(this.JY);
               } else {
                  var3.lm = 2;
                  IJavaExpression var27 = ((bum)this.lm).q(var1, var3);
                  var3.lm = 0;
                  var4 = new bmr(var27);
               }
               break;
            case IR_MONITOR_ENTER:
            case IR_MONITOR_EXIT:
               IJavaExpression var7 = (IJavaExpression)((btk)this.zz).generateAST(var1, var3);
               var4 = var3.getGlobalContext().createMonitor(this.za == DOpcodeType.IR_MONITOR_ENTER, var7);
               break;
            default:
               throw new RuntimeException();
         }

         if (var4 != null) {
            ((IJavaStatement)var4).setIntermediateOffset(this.JY);
            ((IJavaStatement)var4).setPhysicalMethodIndex(this.getPhysicalMethodIndex());
            ((IJavaStatement)var4).setPhysicalOffset(this.getPhysicalOffset());
         }

         if (var5 != null) {
            var5.setIntermediateOffset(this.JY);
            var5.setPhysicalMethodIndex(this.getPhysicalMethodIndex());
            var5.setPhysicalOffset(this.getPhysicalOffset());
         }

         if (var6 == null) {
            return this.q((IJavaElement)var4);
         } else {
            for (IJavaStatement var38 : var6) {
               var38.setIntermediateOffset(this.JY);
               var38.setPhysicalMethodIndex(this.getPhysicalMethodIndex());
               var38.setPhysicalOffset(this.getPhysicalOffset());
            }

            return this.q((IJavaElement)var4);
         }
      } finally {
         var3.za = null;
      }
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public String getMnemonic() {
      return Strings.toString(this.za);
   }

   @Override
   public Collection getInstructionFlags() {
      switch (this.za) {
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
      if (!(switch (this.za) {
         case IR_NOP -> this.lm == null && this.zz == null;
         case IR_ASSIGN -> this.lm instanceof IDExpression && this.zz instanceof IDExpression;
         case IR_JUMP -> this.lm instanceof IDTarget && this.zz == null;
         case IR_JCOND -> this.lm instanceof IDTarget && this.zz instanceof IDExpression;
         case IR_SWITCH -> this.lm instanceof IDSwitchData && this.zz instanceof IDExpression;
         case IR_RETURN -> this.lm == null && (this.zz == null || this.zz instanceof IDExpression);
         case IR_INVOKE -> this.lm == null && this.zz instanceof IDInvokeInfo;
         case IR_THROW -> this.lm == null && this.zz instanceof IDExpression;
         case IR_STORE_EXCEPTION -> this.lm instanceof IDVar && this.zz == null;
         case IR_MONITOR_ENTER, IR_MONITOR_EXIT -> this.lm == null && this.zz instanceof IDExpression;
         default -> throw new RuntimeException();
      })) {
         throw new IllegalStateException("Unexpected operand");
      }
   }

   static class CU implements IDVisitor {
      List q = new ArrayList();

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDVar) {
            this.q.add((IDVar)var1);
         }
      }
   }

   public interface eo {
      void customize(IDInstruction var1, List var2, List var3);
   }
}
