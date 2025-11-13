package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.IVariableInformationProvider;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public interface IDMethodContext extends IVariableInformationProvider {
   void verify() throws IllegalStateException;

   IDMethodContext copy();

   void load(IDMethodContext var1);

   Watchdog getWatchdog();

   int getDecompilationFlags();

   default boolean isBatchDecompilation() {
      return (this.getDecompilationFlags() & 32) != 0;
   }

   boolean isParseExceptions();

   boolean isParseDebugInfo();

   IDGlobalContext getGlobalContext();

   IDMethodContext getParentContext();

   List getChildrenContexts();

   List getCopiesContexts();

   IJavaOperatorFactory getOperatorFactory();

   IJavaTypeFactory getTypeFactory();

   IDTypeInfoProvider getTypeInfoProvider();

   IDexUnit getDex();

   IDexMethod getMethod();

   String getMethodSignature();

   SortedMap getParametersTypeMap();

   CFG getCfg();

   IDTryData getExceptionData();

   void replace(CFG var1, IDTryData var2);

   void replaceCFG(CFG var1, Map var2);

   void makeSSA();

   boolean isSSA();

   void propagateTypes();

   SortedMap getVariableMap();

   boolean isStaticMethod();

   List getParameterVariables();

   IDVar getVar(int var1);

   IDVar getVar(String var1);

   Collection getVars();

   boolean removeVar(int var1);

   void clearVirtualVars();

   IDVar createVar(int var1);

   IDVar createVar(int var1, IJavaType var2);

   IDVar createVar(int var1, IJavaType var2, boolean var3);

   IDVar createRegisterVar(int var1, IJavaType var2);

   IDVar createCopyVar(IDVar var1);

   IDVar createVirtualVar(IJavaType var1);

   IDVar retrieveTemporaryVariable(IJavaType var1);

   IDVar retrieveTemporaryVariable(IJavaType var1, int var2);

   int retrievePhysicalRegisterId(int var1);

   int retrievePrimaryVariableId(int var1);

   IDInstruction createNop();

   IDInstruction createAssign(IDExpression var1, IDExpression var2);

   IDInstruction createConstruct(IDNewInfo var1);

   IDInstruction createInvoke(IDCallInfo var1);

   IDInstruction createNewArray(IDNewArrayInfo var1);

   IDInstruction createJump(int var1);

   IDInstruction createJcond(int var1, IDExpression var2);

   IDInstruction createSwitch(IDExpression var1, IDSwitchData var2);

   IDInstruction createReturn(IDExpression var1);

   IDInstruction createThrow(IDExpression var1);

   IDInstruction createStoreException(IDVar var1);

   IDInstruction createMonitorEnter(IDExpression var1);

   IDInstruction createMonitorExit(IDExpression var1);

   default IDImm createImm(long var1, IJavaType var3) {
      return this.getGlobalContext().createImm(var1, var3);
   }

   default IDImm createBoolean(boolean var1) {
      return this.getGlobalContext().createBoolean(var1);
   }

   default IDImm createByte(byte var1) {
      return this.getGlobalContext().createByte(var1);
   }

   default IDImm createChar(char var1) {
      return this.getGlobalContext().createChar(var1);
   }

   default IDImm createShort(short var1) {
      return this.getGlobalContext().createShort(var1);
   }

   default IDImm createInt(int var1) {
      return this.getGlobalContext().createInt(var1);
   }

   default IDImm createLong(long var1) {
      return this.getGlobalContext().createLong(var1);
   }

   default IDImm createFloat(float var1) {
      return this.getGlobalContext().createFloat(var1);
   }

   default IDImm createDouble(double var1) {
      return this.getGlobalContext().createDouble(var1);
   }

   default IDImm createNull() {
      return this.getGlobalContext().createNull();
   }

   default IDImm createString(String var1) {
      return this.getGlobalContext().createString(var1);
   }

   default IDImm createString(IDIndex var1) {
      return this.getGlobalContext().createString(var1);
   }

   default IDArrayElt createArrayElt(IDExpression var1, IDExpression var2, IJavaType var3) {
      return this.getGlobalContext().createArrayElt(var1, var2, var3);
   }

   default IDOperation createOperation(IJavaType var1, JavaOperatorType var2, IDExpression var3, IDExpression var4) {
      return this.getGlobalContext().createOperation(var1, var2, var3, var4);
   }

   default IDOperation createOperation(IJavaType var1, IDExpression var2, IJavaOperator var3, IDExpression var4) {
      return this.getGlobalContext().createOperation(var1, var2, var3, var4);
   }

   default IDOperation createCast(IJavaType var1, IDExpression var2) {
      return this.getGlobalContext().createCastOperation(var1, var2);
   }

   default IDOperation createConditional(IJavaType var1, IDExpression var2, IDExpression var3, IDExpression var4) {
      return this.getGlobalContext().createConditional(var1, var2, var3, var4);
   }

   default IDOperation createPredicate(JavaOperatorType var1, IDExpression var2, IDExpression var3) {
      return this.getGlobalContext().createPredicate(var1, var2, var3);
   }

   default IDOperation createPredicate(IDExpression var1, IJavaOperator var2, IDExpression var3) {
      return this.getGlobalContext().createPredicate(var1, var2, var3);
   }

   default IDReferenceType createReferenceType(IDIndex var1, IJavaType var2) {
      return this.getGlobalContext().createReferenceType(var1, var2);
   }

   default IDReferenceType createReferenceType(String var1) {
      return this.getGlobalContext().createReferenceType(var1);
   }

   default IDIndex createIndex(int var1) {
      return this.getGlobalContext().createIndex(var1);
   }

   default IDStaticField createStaticField(IDIndex var1, IJavaType var2, String var3, String var4) {
      return this.getGlobalContext().createStaticField(var1, var2, var3, var4);
   }

   default IDInstanceField createInstanceField(IDExpression var1, IDIndex var2, IJavaType var3, String var4) {
      return this.getGlobalContext().createInstanceField(var1, var2, var3, var4);
   }

   default IDStaticField createClassObject(String var1) {
      return this.getGlobalContext().createClassObject(var1);
   }

   default IDInstanceField createArrayLength(IDExpression var1) {
      return this.getGlobalContext().createArrayLength(var1);
   }

   default IDCallInfo createCallInfo(IDIndex var1, IDExpression[] var2, IJavaType var3, String var4, DInvokeType var5) {
      return this.getGlobalContext().createCallInfo(var1, var2, var3, var4, var5);
   }

   default IDCallInfo createCallInfo(IDIndex var1, List var2, IJavaType var3, String var4, DInvokeType var5) {
      return this.getGlobalContext().createCallInfo(var1, var2, var3, var4, var5);
   }

   default IDCallInfo createCallInfo(DInvokeType var1, int var2, List var3) {
      return this.getGlobalContext().createCallInfo(var1, var2, var3);
   }

   default IDNewInfo createNewInfo(String var1, IDExpression... var2) {
      return this.getGlobalContext().createNewInfo(var1, var2);
   }

   default IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, IDExpression[] var4, String var5) {
      return this.getGlobalContext().createNewInfo(var1, var2, var3, var4, var5);
   }

   default IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, List var4, String var5) {
      return this.getGlobalContext().createNewInfo(var1, var2, var3, var4, var5);
   }

   default IDNewArrayInfo createNewArrayInfo(IJavaType var1, IDExpression var2, List var3) {
      return this.getGlobalContext().createNewArrayInfo(var1, var2, var3);
   }

   default IDNewArrayInfo createByteArray(byte[] var1) {
      return this.getGlobalContext().createByteArray(var1);
   }

   default IDTarget createTarget(int var1) {
      return this.getGlobalContext().createTarget(var1);
   }

   default IDSwitchData createSwitchData() {
      return this.getGlobalContext().createSwitchData();
   }

   Object setData(String var1, Object var2);

   Object getData(String var1);

   Set getDataKeys();

   IDImm evaluate(IDImm... var1) throws DexDecEvaluationException;

   IDImm evaluate(IDState var1, List var2) throws DexDecEvaluationException;

   void incrementDeobfuscationScore(int var1);

   int getDeobfuscationScore();

   void resetDeobfuscationScore();

   void pushWorkingOptimizer(IDOptimizer var1);

   IDOptimizer popWorkingOptimizer();
}
