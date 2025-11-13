package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICElementFactory {
   ICDecl createDecl(ICIdentifier var1);

   ICArrayElement createArrayElement(ICExpression var1, ICExpression var2);

   ICAssignment createAssignment(ICLeftExpression var1, ICExpression var2);

   ICBlock createBlock();

   ICBlock createBlock(ICStatement var1);

   ICBlock createBlock(ICStatement... var1);

   ICBreak createBreak();

   ICBreak createBreak(ICLabel var1);

   ICContinue createContinue();

   ICContinue createContinue(ICLabel var1);

   ICCall createCall(ICMethod var1, List var2);

   ICCall createCall(ICExpression var1, List var2, List var3);

   ICDoWhileStm createDoWhileStm(ICBlock var1, ICPredicate var2);

   ICForStm createForLoop(ICStatement var1, ICPredicate var2, ICStatement var3, ICBlock var4);

   ICGoto createGoto(ICLabel var1);

   ICIfStm createIfStm(ICPredicate var1, ICBlock var2);

   ICIfStm createIfStm(ICPredicate var1, ICStatement var2);

   ICIfStm createIfStm(ICPredicate var1, ICStatement var2, ICStatement var3);

   ICInstanceField createInstanceField(ICField var1, ICExpression var2, boolean var3);

   ICJumpFar createJumpFar(ICExpression var1);

   ICCustomStatement createNativeStatement(long var1);

   ICOperation createOperation(ICOperator var1, ICExpression var2);

   ICOperation createOperation(ICOperator var1, ICExpression var2, ICExpression var3);

   ICOperation createOperation(ICOperator var1, ICExpression var2, ICExpression var3, ICExpression var4);

   ICOperation createOperation(ICOperator var1, List var2);

   ICOperation createOperation(COperatorType var1, ICExpression var2);

   ICOperation createOperation(COperatorType var1, ICExpression var2, ICExpression var3);

   ICOperation createOperation(COperatorType var1, ICExpression var2, ICExpression var3, ICExpression var4);

   ICOperation createOperation(COperatorType var1, List var2);

   ICOperation createCast(ICType var1, ICExpression var2);

   ICPredicate createPredicate(ICExpression var1);

   ICReturn createReturn();

   ICReturn createReturn(ICExpression var1);

   ICSwitchStm createSwitchStm(ICExpression var1);

   ICTuple createTuple(List var1);

   ICWhileStm createWhileStm(ICPredicate var1, ICBlock var2);

   ICThrow createThrow(ICExpression var1);
}
