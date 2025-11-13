package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaElementFactory {
   IJavaMethod createMethodReference(String var1);

   IJavaMethod createMethodReference(String var1, Boolean var2);

   IJavaField createFieldReference(String var1);

   IJavaField createFieldReference(String var1, Boolean var2);

   IJavaArrayElt createArrayElt(IJavaExpression var1, IJavaExpression var2);

   IJavaAssignment createAssignment(IJavaLeftExpression var1, IJavaExpression var2);

   IJavaBlock createBlock();

   IJavaBlock createBlock(IJavaStatement var1);

   IJavaBreak createBreak(IJavaLabel var1);

   IJavaTypeReference createTypeReference(IJavaType var1);

   IJavaConditionalExpression createConditionalExpression(IJavaExpression var1, IJavaExpression var2, IJavaExpression var3);

   IJavaContinue createContinue(IJavaLabel var1);

   IJavaDoWhile createDoWhile(IJavaBlock var1, IJavaPredicate var2);

   IJavaOperation createOperation(IJavaExpression var1, JavaOperatorType var2, IJavaExpression var3);

   IJavaOperation createOperation(IJavaExpression var1, IJavaOperator var2, IJavaExpression var3);

   IJavaOperation createCastOperation(IJavaType var1, IJavaExpression var2);

   IJavaFor createFor(IJavaStatement var1, IJavaPredicate var2, IJavaStatement var3, IJavaBlock var4);

   IJavaForEach createForEach(IJavaDefinition var1, IJavaExpression var2, IJavaBlock var3);

   IJavaGoto createGoto(IJavaLabel var1);

   IJavaIf createIf(IJavaPredicate var1, IJavaBlock var2);

   IJavaInstanceField createInstanceField(IJavaExpression var1, String var2);

   IJavaInstanceField createInstanceField(IJavaExpression var1, IJavaField var2);

   IJavaCall createCall(IJavaMethod var1, int var2);

   IJavaCall createCall(String var1, int var2);

   IJavaCall createCall(IJavaMethod var1, int var2, List var3);

   IJavaCall createCall(String var1, int var2, List var3);

   IJavaNew createNew(IJavaType var1, IJavaMethod var2);

   IJavaNew createNew(IJavaType var1, IJavaMethod var2, List var3);

   IJavaNew createNew(IJavaType var1, String var2, List var3);

   IJavaNewArray createNewArray(IJavaType var1, IJavaExpression var2);

   IJavaNewArray createNewArray(IJavaType var1, boolean var2, List var3);

   IJavaPredicate createPredicate(IJavaExpression var1);

   IJavaReturn createReturn();

   IJavaReturn createReturn(IJavaExpression var1);

   IJavaStaticField createStaticField(IJavaType var1, String var2);

   IJavaStaticField createStaticField(IJavaType var1, IJavaField var2);

   IJavaSwitch createSwitch(IJavaExpression var1);

   IJavaSwitch createSwitch(IJavaExpression var1, int var2);

   IJavaTry createTry(IJavaBlock var1);

   IJavaMonitor createMonitor(boolean var1, IJavaExpression var2);

   IJavaSynchronizedBlock createSynchronizedBlock(IJavaExpression var1, IJavaBlock var2);

   IJavaThrow createThrow(IJavaExpression var1);

   IJavaWhile createWhile(IJavaPredicate var1, IJavaBlock var2);

   IJavaAnnotation createAnnotation(IJavaType var1, List var2);

   IJavaAnnotationElement createAnnotationElement(IJavaConstant var1, IJavaExpression var2);
}
