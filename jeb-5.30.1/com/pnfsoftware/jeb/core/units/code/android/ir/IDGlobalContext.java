package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface IDGlobalContext {
   IDexUnit getDex();

   IDexDecompilerUnit getDecompiler();

   IJavaOperatorFactory getOperatorFactory();

   IJavaTypeFactory getTypeFactory();

   IDTypeInfoProvider getTypeInfoProvider();

   IDMasterOptimizer createMasterOptimizer(IDMethodContext var1, boolean var2, boolean var3, boolean var4);

   default IDMasterOptimizer createMasterOptimizer(IDMethodContext var1) {
      return this.createMasterOptimizer(var1, true, true, true);
   }

   IDState getThreadSharedState();

   IDState getEmulator();

   IDState createState();

   IDState createState(boolean var1, boolean var2);

   Object setData(String var1, Object var2);

   Object getData(String var1);

   Object getData(String var1, Function var2);

   Set getDataKeys();

   void resetDeobfuscatorCounters();

   void resetDeobfuscatorCounters(String var1);

   default IDImm createConstant(long var1, IJavaType var3) {
      return this.createImm(var1, var3);
   }

   IDImm createImm(long var1, IJavaType var3);

   IDImm createRef(int var1, IJavaType var2);

   IDImm createRef(int var1);

   IDImm createBoolean(boolean var1);

   IDImm createByte(byte var1);

   IDImm createChar(char var1);

   IDImm createShort(short var1);

   IDImm createInt(int var1);

   IDImm createLong(long var1);

   IDImm createFloat(float var1);

   IDImm createDouble(double var1);

   IDImm createNull();

   IDImm createString(String var1);

   IDImm createString(IDIndex var1);

   IDArrayElt createArrayElt(IDExpression var1, IDExpression var2, IJavaType var3);

   IDOperation createOperation(IJavaType var1, JavaOperatorType var2, IDExpression var3, IDExpression var4);

   IDOperation createOperation(IJavaType var1, IDExpression var2, IJavaOperator var3, IDExpression var4);

   IDOperation createCastOperation(IJavaType var1, IDExpression var2);

   IDOperation createCastOperation(String var1, IDExpression var2);

   IDOperation createConditional(IJavaType var1, IDExpression var2, IDExpression var3, IDExpression var4);

   IDOperation createPredicate(JavaOperatorType var1, IDExpression var2, IDExpression var3);

   IDOperation createPredicate(IDExpression var1, IJavaOperator var2, IDExpression var3);

   IDReferenceType createReferenceType(IDIndex var1, IJavaType var2);

   IDReferenceType createReferenceType(String var1);

   IDIndex createIndex(int var1);

   IDStaticField createStaticField(String var1);

   IDStaticField createStaticField(String var1, boolean var2);

   IDStaticField createStaticField(IDexField var1);

   IDStaticField createStaticField(IDIndex var1, IJavaType var2, String var3, String var4);

   IDInstanceField createInstanceField(IDExpression var1, String var2);

   IDInstanceField createInstanceField(IDExpression var1, String var2, boolean var3);

   IDInstanceField createInstanceField(IDExpression var1, IDexField var2);

   IDInstanceField createInstanceField(IDExpression var1, IDIndex var2, IJavaType var3, String var4);

   IDStaticField createClassObject(String var1);

   IDInstanceField createArrayLength(IDExpression var1);

   IDCallInfo createCallInfo(IDIndex var1, IDExpression[] var2, IJavaType var3, String var4, DInvokeType var5);

   IDCallInfo createCallInfo(IDIndex var1, List var2, IJavaType var3, String var4, DInvokeType var5);

   IDCallInfo createCallInfo(DInvokeType var1, int var2, List var3);

   IDCallInfo createCallInfo(DInvokeType var1, String var2, List var3);

   IDAllocObjectInfo createAllocObjectInfo(IJavaType var1);

   IDAllocObjectInfo createAllocObjectInfo(String var1);

   IDNewInfo createNewInfo(String var1, IDExpression... var2);

   IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, IDExpression[] var4, String var5);

   IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, List var4, String var5);

   IDNewArrayInfo createNewArrayInfo(IJavaType var1, IDExpression var2, List var3);

   IDNewArrayInfo createBooleanArray(boolean[] var1);

   IDNewArrayInfo createByteArray(byte[] var1);

   IDNewArrayInfo createCharArray(char[] var1);

   IDNewArrayInfo createShortArray(short[] var1);

   IDNewArrayInfo createIntArray(int[] var1);

   IDNewArrayInfo createLongArray(long[] var1);

   IDNewArrayInfo createFloatArray(float[] var1);

   IDNewArrayInfo createDoubleArray(double[] var1);

   IDNewArrayInfo createStringArray(String[] var1);

   IDTarget createTarget(int var1);

   IDSwitchData createSwitchData();
}
