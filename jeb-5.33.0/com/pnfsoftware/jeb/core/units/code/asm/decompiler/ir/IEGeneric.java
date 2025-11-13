package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.function.Predicate;

@Ser
public interface IEGeneric extends IInstructionOperand {
   int getFlags();

   void setFlags(int var1);

   boolean hasFlags(int var1);

   boolean addFlags(int var1);

   void removeFlags(int var1);

   int getBitsize();

   int getPriority();

   void getUsed(EDefUseInfo var1);

   default IdRanges getUsed() {
      EDefUseInfo var1 = new EDefUseInfo();
      this.getUsed(var1);
      return var1.getUse();
   }

   void getDefinedOrUsedAsDestination(EDefUseInfo var1);

   void getExplicitlyUsed(EDefUseInfo var1);

   default IdRanges getExplicitlyUsed() {
      EDefUseInfo var1 = new EDefUseInfo();
      this.getExplicitlyUsed(var1);
      return var1.getUse();
   }

   boolean accessesMemory();

   IEImm evaluate(EState var1) throws EvaluationException;

   long evaluateUnsignedLong(EState var1) throws EvaluationException;

   long evaluateAddress(EState var1) throws EvaluationException;

   int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException;

   void collectSubExpressions(Collection var1);

   boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException;

   void updateTypes(ETypeInfo var1);

   boolean setType(IWildcardType var1);

   boolean setType(IWildcardType var1, ETypeInfo var2);

   boolean setType(IWildcardType var1, ETypeInfo var2, boolean var3);

   IWildcardType getType();

   IWildcardType safelyType(IWildcardTypeManager var1);

   IWildcardType getSafeType(IWildcardTypeManager var1);

   boolean visitDepthPre(IEVisitor var1);

   boolean visitDepthPre(IEVisitor var1, IEGeneric var2);

   boolean visitDepthPre(IEVisitor var1, IEGeneric var2, EVisitResults var3);

   boolean visitDepthPost(IEVisitor var1);

   boolean visitDepthPost(IEVisitor var1, IEGeneric var2);

   boolean visitDepthPost(IEVisitor var1, IEGeneric var2, EVisitResults var3);

   default boolean examine(Predicate var1) {
      return !this.visitDepthPre(new IEGeneric$1(this, var1));
   }

   void verify() throws IllegalIntermediateExpressionException;

   IEGeneric duplicate();

   void copyProperties(IEGeneric var1);

   ICElement generateC(IERoutineContext var1, ICMethod var2);

   IEGeneric part(int var1);

   IEGeneric bit(int var1);

   IEGeneric slice(int var1);

   IEGeneric slice(int var1, int var2);

   IEGeneric slice(IERange var1);

   IEGeneric msb();

   IEGeneric lsb();

   IEGeneric half();

   IEGeneric topHalf();

   default IEGeneric extend(int var1, boolean var2) {
      return var2 ? this.signExtend(var1) : this.zeroExtend(var1);
   }

   IEGeneric zeroExtend(int var1);

   IEGeneric signExtend(int var1);

   IEGeneric leftShift(int var1, int var2);

   IEGeneric leftShift(int var1);

   IEGeneric rightShift(int var1, int var2);

   IEGeneric rightShift(int var1);

   IECond countSuccessiveBits(boolean var1, boolean var2, int var3);

   boolean equalsEx(Object var1, boolean var2);

   default boolean isCompose() {
      return this instanceof IECompose;
   }

   default IECompose asCompose() {
      return (IECompose)this;
   }

   default boolean isCond() {
      return this instanceof IECond;
   }

   default IECond asCond() {
      return (IECond)this;
   }

   default boolean isGroup() {
      return this instanceof IEGroup;
   }

   default IEGroup asGroup() {
      return (IEGroup)this;
   }

   default boolean isGroupElt() {
      return this instanceof IEGroupElt;
   }

   default IEGroupElt asGroupElt() {
      return (IEGroupElt)this;
   }

   default boolean isImm() {
      return this instanceof IEImm;
   }

   default IEImm asImm() {
      return (IEImm)this;
   }

   default boolean isMem() {
      return this instanceof IEMem;
   }

   default IEMem asMem() {
      return (IEMem)this;
   }

   default boolean isOperation() {
      return this instanceof IEOperation;
   }

   default boolean isOperation(OperationType var1) {
      return this instanceof IEOperation && ((IEOperation)this).getOperationType() == var1;
   }

   default boolean isOperation(OperationType... var1) {
      if (!(this instanceof IEOperation)) {
         return false;
      } else {
         OperationType var2 = ((IEOperation)this).getOperationType();

         for (OperationType var6 : var1) {
            if (var6 == var2) {
               return true;
            }
         }

         return false;
      }
   }

   default boolean isOperation(String var1) {
      return this instanceof IEOperation && var1.equals(((IEOperation)this).getOperationName());
   }

   default IEOperation asOperation() {
      return (IEOperation)this;
   }

   default boolean isRange() {
      return this instanceof IERange;
   }

   default IERange asRange() {
      return (IERange)this;
   }

   default boolean isSlice() {
      return this instanceof IESlice;
   }

   default boolean isSlice(int var1, int var2) {
      return this instanceof IESlice && ((IESlice)this).getBitStart() == var1 && ((IESlice)this).getBitEnd() == var2;
   }

   default IESlice asSlice() {
      return (IESlice)this;
   }

   default boolean isVar() {
      return this instanceof IEVar;
   }

   default boolean isVar(int var1) {
      return this instanceof IEVar && ((IEVar)this).getId() == var1;
   }

   default IEVar asVar() {
      return (IEVar)this;
   }

   default boolean isStatement() {
      return this instanceof IEStatement;
   }

   default IEStatement asStatement() {
      return (IEStatement)this;
   }

   IEGeneric findParent(IEGeneric var1);

   IEGeneric findParent(IEGeneric var1, int var2);

   Couple find(IEGeneric var1, int var2, int var3, IEGeneric var4);

   IEGeneric findByType(Class var1);

   IEGeneric findByType(Class var1, int var2);
}
