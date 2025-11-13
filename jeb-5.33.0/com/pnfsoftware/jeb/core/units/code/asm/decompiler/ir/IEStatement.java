package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IResizableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface IEStatement extends IResizableInstruction, IEGeneric {
   int FLAG_OPT_BLOCK_PROPAGATION = 1;
   int FLAG_OPT_BLOCK_SUBSTITUTIONS = 2;
   int FLAG_LIKELY_PROLOGUE = 4;
   int FLAG_LIKELY_EPILOGUE = 8;

   IERoutineContext getContext();

   void copyProperties(IEStatement var1);

   void addLowerLevelAddress(long var1);

   void addLowerLevelAddresses(Collection var1);

   void removeLowerLevelAddress(long var1);

   void setLowerLevelAddress(long var1);

   void setLowerLevelAddresses(Collection var1);

   default void resetLowerLevelAddress() {
      this.resetLowerLevelAddresses();
   }

   void resetLowerLevelAddresses();

   void copyLowerLevelAddresses(IEStatement var1);

   Collection getLowerLevelAddresses();

   Long getPrimaryLowerLevelAddress();

   void setPrimaryLowerLevelAddress(long var1);

   IEStatement withLowerLevelAddress(long var1);

   IEStatement withLowerLevelAddresses(Collection var1);

   void setSPDelta(Integer var1);

   Integer getSPDelta();

   boolean writesMemory();

   void getDefUse(EDefUseInfo var1);

   EDefUseInfo getDefUseInfo(long var1, int var3);

   void collectSubExpressions(Collection var1, Boolean var2);

   @Override
   default void collectSubExpressions(Collection var1) {
      this.collectSubExpressions(var1, null);
   }

   void collectUsedExpressions(Collection var1);

   int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException;

   default int replaceUsedVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      return this.replaceVar(var1, var2, true);
   }

   int replaceDefinedVar(IEVar var1, IEGeneric var2);

   boolean equalsEx(Object var1, boolean var2, boolean var3);

   boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4);

   void preUpdateTypes(ETypeInfo var1);

   void postUpdateTypes(ETypeInfo var1);

   ICStatement generateC(IERoutineContext var1, ICMethod var2);

   default boolean isAssign() {
      return this instanceof IEAssign;
   }

   default boolean isAssignTo(IEVar var1) {
      return this instanceof IEAssign && ((IEAssign)this).getDstOperand() == var1;
   }

   default boolean isAssignTo(int var1) {
      return this instanceof IEAssign && ((IEAssign)this).getDstOperand().isVar(var1);
   }

   default boolean isAssignToVar() {
      return this instanceof IEAssign && ((IEAssign)this).getDstOperand().isVar();
   }

   default IEAssign asAssign() {
      return (IEAssign)this;
   }

   default boolean isCall() {
      return this instanceof IECall;
   }

   default IECall asCall() {
      return (IECall)this;
   }

   default boolean isJump() {
      return this instanceof IEJump;
   }

   default boolean isUnconditionalJump() {
      return this instanceof IEJump && ((IEJump)this).getCondition() == null;
   }

   default boolean isConditionalJump() {
      return this instanceof IEJump && ((IEJump)this).getCondition() != null;
   }

   default IEJump asJump() {
      return (IEJump)this;
   }

   default boolean isJumpFar() {
      return this instanceof IEJumpFar;
   }

   default boolean isUnconditionalJumpFar() {
      return this instanceof IEJumpFar && ((IEJumpFar)this).getCondition() == null;
   }

   default boolean isConditionalJumpFar() {
      return this instanceof IEJumpFar && ((IEJumpFar)this).getCondition() != null;
   }

   default IEJumpFar asJumpFar() {
      return (IEJumpFar)this;
   }

   default boolean isNop() {
      return this instanceof IENop;
   }

   default IENop asNop() {
      return (IENop)this;
   }

   default boolean isReturn() {
      return this instanceof IEReturn;
   }

   default IEReturn asReturn() {
      return (IEReturn)this;
   }

   default boolean isSwitch() {
      return this instanceof IESwitch;
   }

   default IESwitch asSwitch() {
      return (IESwitch)this;
   }

   default boolean isUntranslatedInstruction() {
      return this instanceof IEUntranslatedInstruction;
   }

   default IEUntranslatedInstruction asUntranslated() {
      return (IEUntranslatedInstruction)this;
   }

   default boolean visitInstruction(IEVisitor var1) {
      return this.visitInstruction(var1, false);
   }

   default boolean visitInstruction(IEVisitor var1, boolean var2) {
      EVisitResults var3 = new EVisitResults(1 | (var2 ? 2 : 0));
      return this.visitDepthPre(var1, null, var3);
   }

   default boolean visitInstructionPostOrder(IEVisitor var1, boolean var2) {
      EVisitResults var3 = new EVisitResults(1 | (var2 ? 2 : 0));
      return this.visitDepthPost(var1, null, var3);
   }
}
