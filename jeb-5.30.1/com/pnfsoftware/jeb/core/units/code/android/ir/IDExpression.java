package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDExpression extends IDElement {
   IDExpression copy(DCopyOptions var1);

   IDExpression duplicate();

   void transferMetadataFrom(IDExpression var1);

   void setPhysicalMethodIndex(int var1);

   int getPhysicalMethodIndex();

   void collectAllPhysicalMethodIndices(Collection var1);

   void updateAllPhysicalMethodIndices(int var1);

   void setPhysicalOffset(int var1);

   int getPhysicalOffset();

   void collectAllPhysicalOffsets(Collection var1);

   void updateAllPhysicalOffsets(int var1);

   Object setData(String var1, Object var2);

   Object getData(String var1);

   void removeData(String var1);

   void setOrigin(String var1);

   String getOrigin();

   void updateTypes(DTypeInfo var1);

   IJavaType getType();

   boolean checkType(IJavaType var1);

   boolean setType(IJavaType var1);

   boolean setType(IJavaType var1, DTypeInfo var2);

   boolean setType(IJavaType var1, DTypeInfo var2, boolean var3);

   int replaceVariable(IDVar var1, IDExpression var2);

   void collectSubExpressions(Collection var1);

   default List getSubExpressions() {
      ArrayList var1 = new ArrayList();
      this.collectSubExpressions(var1);
      return var1;
   }

   boolean replaceSubExpression(IDExpression var1, IDExpression var2);

   void collectVarIds(Set var1);

   default Set getVarIds() {
      HashSet var1 = new HashSet();
      this.collectVarIds(var1);
      return var1;
   }

   int countVariable(IDVar var1);

   void setCustomCanThrow(Boolean var1);

   Boolean getCustomCanThrow();

   boolean canThrow(IDMethodContext var1);

   boolean hasSideEffects(IDMethodContext var1, boolean var2);

   IDImm evaluate(IDState var1) throws DexDecEvaluationException;

   IDImm evaluate(IDMethodContext var1) throws DexDecEvaluationException;

   IDImm evaluate(IDGlobalContext var1, Map var2) throws DexDecEvaluationException;

   IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2);

   IDImm spawn(long var1);

   boolean visitDepthPre(IDVisitor var1);

   boolean visitDepthPre(IDVisitor var1, IDExpression var2);

   boolean visitDepthPre(IDVisitor var1, IDExpression var2, DVisitResults var3);

   boolean visitDepthPost(IDVisitor var1);

   boolean visitDepthPost(IDVisitor var1, IDExpression var2);

   boolean visitDepthPost(IDVisitor var1, IDExpression var2, DVisitResults var3);

   IDExpression findParent(IDExpression var1);

   IDExpression findParent(IDExpression var1, int var2);

   Couple find(IDExpression var1, int var2, int var3, IDExpression var4);

   IDExpression findByType(Class var1);

   IDExpression findByType(Class var1, int var2);

   default boolean isInstruction() {
      return this instanceof IDInstruction;
   }

   default IDInstruction asInstruction() {
      return (IDInstruction)this;
   }

   default boolean isArrayElt() {
      return this instanceof IDArrayElt;
   }

   default IDArrayElt asArrayElt() {
      return (IDArrayElt)this;
   }

   default boolean isReferenceType() {
      return this instanceof IDReferenceType;
   }

   default IDReferenceType asReferenceType() {
      return (IDReferenceType)this;
   }

   default boolean isInstanceField() {
      return this instanceof IDInstanceField;
   }

   default IDInstanceField asInstanceField() {
      return (IDInstanceField)this;
   }

   default boolean isStaticField() {
      return this instanceof IDStaticField;
   }

   default IDStaticField asStaticField() {
      return (IDStaticField)this;
   }

   default boolean isOperation() {
      return this instanceof IDOperation;
   }

   default boolean isOperation(JavaOperatorType var1) {
      return this instanceof IDOperation var2 && var2.getOperator().is(var1);
   }

   default boolean isOperation(JavaOperatorType var1, JavaOperatorType var2) {
      return this instanceof IDOperation var3 && var3.getOperator().isAnyOf(var1, var2);
   }

   default boolean isOperation(JavaOperatorType var1, JavaOperatorType var2, JavaOperatorType var3) {
      return this instanceof IDOperation var4 && var4.getOperator().isAnyOf(var1, var2, var3);
   }

   default boolean isOperation(JavaOperatorType var1, JavaOperatorType var2, JavaOperatorType var3, JavaOperatorType var4) {
      return this instanceof IDOperation var5 && var5.getOperator().isAnyOf(var1, var2, var3, var4);
   }

   default boolean isOperation(JavaOperatorType var1, JavaOperatorType var2, JavaOperatorType var3, JavaOperatorType var4, JavaOperatorType var5) {
      return this instanceof IDOperation var6 && var6.getOperator().isAnyOf(var1, var2, var3, var4, var5);
   }

   default boolean isOperation(
      JavaOperatorType var1, JavaOperatorType var2, JavaOperatorType var3, JavaOperatorType var4, JavaOperatorType var5, JavaOperatorType var6
   ) {
      return this instanceof IDOperation var7 && var7.getOperator().isAnyOf(var1, var2, var3, var4, var5, var6);
   }

   default boolean isCastOperation() {
      return this instanceof IDOperation var1 && var1.getOperator().isCast();
   }

   default boolean isCastOperation(IJavaType var1) {
      return this instanceof IDOperation var2 && var2.getOperator().isCast() && var2.getOperator().getCastType() == var1;
   }

   default IDOperation asOperation() {
      return (IDOperation)this;
   }

   default boolean isCallInfo() {
      return this instanceof IDCallInfo;
   }

   default boolean isCallInfo(String var1) {
      return this instanceof IDCallInfo var2 && var2.getMethodSignature().equals(var1);
   }

   default IDCallInfo asCallInfo() {
      return (IDCallInfo)this;
   }

   default boolean isNewInfo() {
      return this instanceof IDNewInfo;
   }

   default IDNewInfo asNewInfo() {
      return (IDNewInfo)this;
   }

   default boolean isNewArrayInfo() {
      return this instanceof IDNewArrayInfo;
   }

   default IDNewArrayInfo asNewArrayInfo() {
      return (IDNewArrayInfo)this;
   }

   default boolean isImm() {
      return this instanceof IDImm;
   }

   default boolean isConstantImm() {
      return this instanceof IDImm var1 && !var1.isRef();
   }

   default boolean isConstantImm(long var1) {
      return this instanceof IDImm var3 && !var3.isRef() && var3.getRawValue() == var1;
   }

   default boolean isStringImm() {
      return this instanceof IDImm var1 && var1.isString();
   }

   default IDImm asImm() {
      return (IDImm)this;
   }

   default boolean isVar() {
      return this instanceof IDVar;
   }

   default boolean isVar(int var1) {
      return this instanceof IDVar var2 && var2.getId() == var1;
   }

   default IDVar asVar() {
      return (IDVar)this;
   }
}
