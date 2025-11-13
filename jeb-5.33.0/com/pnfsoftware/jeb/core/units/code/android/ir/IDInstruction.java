package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
import java.util.List;
import java.util.Map;

public interface IDInstruction extends ILocatedInstruction, IDExpression {
   String KEEP_INSTRUCTION = "KEEP_INSTRUCTION";

   IDInstruction duplicate();

   IDInstruction copy(DCopyOptions var1);

   IDInstruction duplicateForReplacement(IDInstruction var1);

   IDInstruction duplicateWithOffsetAndSize(long var1, int var3);

   void copyBaseFields(IDInstruction var1);

   IDMethodContext getContext();

   IDMethodContext setContext(IDMethodContext var1);

   @Override
   long getOffset();

   @Override
   long getOffsetEnd();

   void setOffset(long var1);

   IDInstruction withOffset(long var1);

   void setSize(int var1);

   IDInstruction withSize(int var1);

   void adjustSize(int var1);

   DOpcodeType getOpcode();

   void setOpcode(DOpcodeType var1);

   boolean isOpcode(DOpcodeType... var1);

   IDElement getOperand1();

   void setOperand1(IDElement var1);

   IDElement getOperand2();

   void setOperand2(IDElement var1);

   boolean hasUseSideEffects(boolean var1);

   boolean isNop();

   boolean isAssign();

   IDExpression getAssignDestination();

   IDExpression setAssignDestination(IDExpression var1);

   IDExpression getAssignSource();

   IDExpression setAssignSource(IDExpression var1);

   default boolean isAssignToVar() {
      return this.isAssign() && this.getAssignDestination() instanceof IDVar;
   }

   default boolean isAssignToVar(int var1) {
      return this.isAssign() && this.getAssignDestination().isVar(var1);
   }

   default boolean isAssignFromVar() {
      return this.isAssign() && this.getAssignSource() instanceof IDVar;
   }

   default boolean isAssignFromVar(int var1) {
      return this.isAssign() && this.getAssignSource().isVar(var1);
   }

   default boolean isAssignFromVarToVar() {
      return this.isAssign() && this.getAssignSource() instanceof IDVar && this.getAssignDestination() instanceof IDVar;
   }

   default boolean isAssignFromVarToVar(int var1, int var2) {
      return this.isAssign() && this.getAssignSource().isVar(var1) && this.getAssignDestination().isVar(var2);
   }

   boolean isInvoke();

   IDInvokeInfo getInvokeData();

   int setBranchTarget(int var1);

   int getBranchTarget();

   boolean isJump();

   boolean isJumpTo(int var1);

   boolean isJcond();

   boolean isJcondTo(int var1);

   boolean isJumpOrJcond();

   boolean isJumpOrJcondTo(int var1);

   boolean isJcondOrSwitch();

   IDExpression getJcondCondition();

   IDExpression setJcondCondition(IDExpression var1);

   void reverseJcondCondition();

   boolean isSwitch();

   boolean isSwitchOnInt();

   boolean isSwitchOnString();

   IDSwitchData getSwitchData();

   IDSwitchData setSwitchData(IDSwitchData var1);

   IDExpression getSwitchExpression();

   IDExpression setSwitchExpression(IDExpression var1);

   boolean isReturn();

   IDExpression getReturnExpression();

   IDExpression setReturnExpression(IDExpression var1);

   boolean isThrow();

   IDExpression getThrowExpression();

   IDExpression setThrowExpression(IDExpression var1);

   boolean isReturnOrThrow();

   boolean isStoreException();

   IDVar getStoredExceptionVariable();

   IDVar setStoredExceptionVariable(IDVar var1);

   boolean isMonitorEnter();

   boolean isMonitorExit();

   void transformToNop();

   void transformJcondToJump();

   void transformToJump(int var1);

   void transformToJump(IDTarget var1);

   void transformJcondToAssign(IDVar var1);

   boolean transformSwitchToJcond();

   int updateTargets(Map var1);

   int updateTargets(Map var1, boolean var2);

   void morph(DOpcodeType var1, IDElement var2, IDElement var3);

   void verify() throws IllegalStateException;

   int replaceUsedVariable(IDVar var1, IDExpression var2);

   int replaceDefinedVariable(IDVar var1, IDExpression var2);

   List getUsedVariables();

   IDVar getDefinedVariable();

   int countUsedVariable(IDVar var1);

   boolean visitInstruction(IDVisitor var1);

   boolean visitInstruction(IDVisitor var1, boolean var2);

   boolean visitInstructionPreOrder(IDVisitor var1, boolean var2);

   boolean visitInstructionPostOrder(IDVisitor var1, boolean var2);

   Integer evaluate(Map var1) throws DexDecEvaluationException;
}
