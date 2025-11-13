package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDAllocObjectInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class bpr implements IDGlobalContext {
   private static final ILogger sY = GlobalLog.getLogger(bpr.class);
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws ys;
   public com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   public cdk A;
   public cdg kS;
   public bgl wS;
   Map UT = new ConcurrentHashMap();
   public ConcurrentHashMap E;
   private bpr.Av ld;
   private Thread gp;
   private brw oT;
   private Map fI = new ConcurrentHashMap();

   public bpr(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1, cdk var2, cdg var3, bgl var4) {
      this.ys = var1;
      this.pC = var1.ld();
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.E = new ConcurrentHashMap();
      this.ld = new bpr.Av();
      this.gp = ThreadUtil.start("jeb-dexdec-SE-cleaner", new bpr.Sv());
      this.oT = new brw(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + (this.ys == null ? 0 : this.ys.hashCode());
      var1 = 31 * var1 + (this.fI == null ? 0 : this.fI.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return var1 == this;
   }

   public com.pnfsoftware.jeb.corei.parsers.dexdec.Ws pC() {
      return this.ys;
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.vi A() {
      return this.pC;
   }

   public cdk kS() {
      return this.A;
   }

   public cdg wS() {
      return this.kS;
   }

   public IDMethodContext pC(IDexMethod var1) {
      return new bpx(this, var1);
   }

   @Override
   public IDImm createBoolean(boolean var1) {
      return new bps(var1 ? 1L : 0L, this.A.kS());
   }

   @Override
   public IDImm createByte(byte var1) {
      return new bps(var1, this.A.getByte());
   }

   @Override
   public IDImm createChar(char var1) {
      return new bps(var1, this.A.wS());
   }

   @Override
   public IDImm createShort(short var1) {
      return new bps(var1, this.A.UT());
   }

   @Override
   public IDImm createInt(int var1) {
      return new bps(var1, this.A.E());
   }

   @Override
   public IDImm createLong(long var1) {
      return new bps(var1, this.A.sY());
   }

   @Override
   public IDImm createFloat(float var1) {
      return new bps(Float.floatToIntBits(var1) & 4294967295L, this.A.ys());
   }

   @Override
   public IDImm createDouble(double var1) {
      return new bps(Double.doubleToLongBits(var1), this.A.ld());
   }

   @Override
   public IDImm createImm(long var1, IJavaType var3) {
      return new bps(var1, var3);
   }

   @Override
   public IDImm createRef(int var1, IJavaType var2) {
      if (var2 == null) {
         var2 = this.A.WR();
      } else if (!((IJavaType)var2).isObject()) {
         throw new IllegalArgumentException();
      }

      return new bps(var1, (IJavaType)var2);
   }

   @Override
   public IDImm createRef(int var1) {
      return this.createRef(var1, null);
   }

   @Override
   public IDImm createNull() {
      return new bps(0L, this.A.WR());
   }

   @Override
   public IDImm createString(String var1) {
      bfx var2 = this.pC.UT(var1);
      return new bps(this.A.getJavaLangString(), this.createIndex(var2.getIndex()));
   }

   @Override
   public IDImm createString(IDIndex var1) {
      return new bps(this.A.getJavaLangString(), var1);
   }

   @Override
   public IDArrayElt createArrayElt(IDExpression var1, IDExpression var2, IJavaType var3) {
      return new bpo(var1, var2, var3);
   }

   private IJavaType pC(IJavaOperator var1, IDExpression var2, IDExpression var3) {
      Object var4 = null;
      if (var1.isArithmetic()) {
         if (var2 == null) {
            var4 = var3.getType();
         } else if (bqa.pC(var2, var1)) {
            var4 = this.A.sY();
         } else {
            var4 = var3.getType();
         }
      } else if (var1.isLogical()) {
         var4 = this.A.kS();
      } else if (var1.isCast()) {
         if (var1.is(JavaOperatorType.CAST_TO_BYTE)) {
            var4 = this.A.getByte();
         } else if (var1.is(JavaOperatorType.CAST_TO_CHAR)) {
            var4 = this.A.wS();
         } else if (var1.is(JavaOperatorType.CAST_TO_SHORT)) {
            var4 = this.A.UT();
         } else if (var1.is(JavaOperatorType.CAST_TO_INT)) {
            var4 = this.A.E();
         } else if (var1.is(JavaOperatorType.CAST_TO_LONG)) {
            var4 = this.A.sY();
         } else if (var1.is(JavaOperatorType.CAST_TO_FLOAT)) {
            var4 = this.A.ys();
         } else if (var1.is(JavaOperatorType.CAST_TO_DOUBLE)) {
            var4 = this.A.ld();
         } else if (var1.is(JavaOperatorType.CAST_TO_BOOLEAN)) {
            var4 = this.A.kS();
         } else {
            var4 = var1.getCastType();
         }
      } else if (var1.is(JavaOperatorType.CONCAT)) {
         var4 = this.A.getJavaLangString();
      } else if (var1.is(JavaOperatorType.COND_EXP)) {
         var4 = var2.getType();
      }

      return (IJavaType)var4;
   }

   @Override
   public IDOperation createOperation(IJavaType var1, JavaOperatorType var2, IDExpression var3, IDExpression var4) {
      IJavaOperator var5 = this.kS.getStandardOperator(var2);
      if (var4 == null) {
         Assert.a(var2.isUnary());
         var4 = var3;
         var3 = null;
      }

      return this.createOperation(var1, var3, var5, var4);
   }

   @Override
   public IDOperation createOperation(IJavaType var1, IDExpression var2, IJavaOperator var3, IDExpression var4) {
      if (var1 == null) {
         var1 = this.pC(var3, var2, var4);
      }

      return new bqa(var1, var2, var3, var4);
   }

   @Override
   public IDOperation createCastOperation(String var1, IDExpression var2) {
      return this.createCastOperation(this.A.createType(var1), var2);
   }

   @Override
   public IDOperation createCastOperation(IJavaType var1, IDExpression var2) {
      return this.createOperation(var1, null, this.kS.createCastOperator(var1), var2);
   }

   @Override
   public IDOperation createConditional(IJavaType var1, IDExpression var2, IDExpression var3, IDExpression var4) {
      IJavaOperator var5 = this.kS.getStandardOperator(JavaOperatorType.COND_EXP);
      if (var1 == null) {
         var1 = this.pC(var5, var3, var4);
      }

      return new bqa(var1, var5, var2, var3, var4);
   }

   @Override
   public IDOperation createPredicate(JavaOperatorType var1, IDExpression var2, IDExpression var3) {
      return this.createPredicate(var2, this.kS.getStandardOperator(var1), var3);
   }

   @Override
   public IDOperation createPredicate(IDExpression var1, IJavaOperator var2, IDExpression var3) {
      return new bqa(this.A.kS(), var1, var2, var3);
   }

   @Override
   public IDReferenceType createReferenceType(IDIndex var1, IJavaType var2) {
      return new bqc(this.A.A(), var1, var2);
   }

   @Override
   public IDReferenceType createReferenceType(String var1) {
      bfy var2 = this.pC.A(var1);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.createReferenceType(this.createIndex(var2.getIndex()), this.A.createType(var2.getSignature(false)));
      }
   }

   @Override
   public IDIndex createIndex(int var1) {
      return new bpt(var1);
   }

   @Override
   public IDStaticField createStaticField(IDIndex var1, IJavaType var2, String var3, String var4) {
      return new bqd(var1, var2, var3, var4);
   }

   @Override
   public IDStaticField createStaticField(IDexField var1) {
      if (var1.getData() != null && !var1.getData().isStatic()) {
         throw new IllegalArgumentException("Internal field is non-static, expected static");
      } else {
         IDIndex var2 = this.createIndex(var1.getIndex());
         IJavaType var3 = this.A.createType(var1.getFieldTypeSignature(false));
         String var4 = var1.getClassTypeSignature(false);
         String var5 = var1.getName(false);
         return this.createStaticField(var2, var3, var4, var5);
      }
   }

   @Override
   public IDStaticField createStaticField(String var1) {
      return this.createStaticField(var1, false);
   }

   @Override
   public IDStaticField createStaticField(String var1, boolean var2) {
      bft var3 = this.pC.kS(var1);
      if (var3 == null) {
         if (!var2) {
            throw new IllegalArgumentException("Field does not exist");
         }

         var3 = this.pC.ld(var1);
      }

      return this.createStaticField(var3);
   }

   @Override
   public IDInstanceField createInstanceField(IDExpression var1, IDIndex var2, IJavaType var3, String var4) {
      return new bpu(var1, var2, var3, var4);
   }

   @Override
   public IDInstanceField createInstanceField(IDExpression var1, IDexField var2) {
      if (var2.getData() != null && var2.getData().isStatic()) {
         throw new IllegalArgumentException("Internal field is static, expected non-static");
      } else {
         IDIndex var3 = this.createIndex(var2.getIndex());
         IJavaType var4 = this.A.createType(var2.getFieldTypeSignature(false));
         String var5 = var2.getName(false);
         return this.createInstanceField(var1, var3, var4, var5);
      }
   }

   @Override
   public IDInstanceField createInstanceField(IDExpression var1, String var2) {
      return this.createInstanceField(var1, var2, false);
   }

   @Override
   public IDInstanceField createInstanceField(IDExpression var1, String var2, boolean var3) {
      bft var4 = this.pC.kS(var2);
      if (var4 == null) {
         if (!var3) {
            throw new IllegalArgumentException("Field does not exist");
         }

         var4 = this.pC.ld(var2);
      }

      return this.createInstanceField(var1, var4);
   }

   @Override
   public IDStaticField createClassObject(String var1) {
      return this.createStaticField(null, this.A.createType("Ljava/lang/Class;"), var1, "class");
   }

   @Override
   public IDInstanceField createArrayLength(IDExpression var1) {
      return this.createInstanceField(var1, null, this.A.E(), "length");
   }

   @Override
   public IDCallInfo createCallInfo(IDIndex var1, IDExpression[] var2, IJavaType var3, String var4, DInvokeType var5) {
      return new bpp(var1, var2, var3, var4, var5);
   }

   @Override
   public IDCallInfo createCallInfo(IDIndex var1, List var2, IJavaType var3, String var4, DInvokeType var5) {
      return new bpp(var1, (IDExpression[])var2.toArray(new IDExpression[var2.size()]), var3, var4, var5);
   }

   @Override
   public IDCallInfo createCallInfo(DInvokeType var1, int var2, List var3) {
      bfu var4 = this.pC.sY(var2);
      if (var4 == null) {
         throw new IllegalArgumentException("No method found for index " + var2);
      } else if (var4.getData() != null && var4.getData().isStatic() && var1 != DInvokeType.STATIC) {
         throw new IllegalArgumentException("Expecting a Static invocation type for method " + var4.getSignature());
      } else {
         return new bpp(
            this.createIndex(var2),
            var3 == null ? null : (IDExpression[])var3.toArray(new IDExpression[var3.size()]),
            this.A.createType(var4.getReturnType().getSignature(false)),
            var4.getSignature(false),
            var1
         );
      }
   }

   @Override
   public IDCallInfo createCallInfo(DInvokeType var1, String var2, List var3) {
      bfu var4 = this.pC.wS(var2);
      if (var4 == null) {
         var4 = this.pC.ys(var2);
      }

      return this.createCallInfo(var1, var4.getIndex(), var3);
   }

   @Override
   public IDAllocObjectInfo createAllocObjectInfo(IJavaType var1) {
      return new bpn(var1);
   }

   @Override
   public IDAllocObjectInfo createAllocObjectInfo(String var1) {
      return this.createAllocObjectInfo(this.A.pC(var1));
   }

   @Override
   public IDNewInfo createNewInfo(String var1, IDExpression... var2) {
      bfu var3 = this.pC.ys(var1);
      IJavaType var4 = this.A.createType(var3.getClassType().getSignature(false));
      IDIndex var5 = this.createIndex(var3.getIndex());
      return new bpz(var4, var4, var5, var2, var3.getSignature(false));
   }

   @Override
   public IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, IDExpression[] var4, String var5) {
      return new bpz(var1, var2, var3, var4, var5);
   }

   @Override
   public IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, List var4, String var5) {
      return new bpz(var1, var2, var3, (IDExpression[])var4.toArray(new IDExpression[var4.size()]), var5);
   }

   @Override
   public IDNewArrayInfo createNewArrayInfo(IJavaType var1, IDExpression var2, List var3) {
      return new bpy(var1, var2, var3);
   }

   @Override
   public IDNewArrayInfo createBooleanArray(boolean[] var1) {
      cdi var2 = this.A.kS();
      ArrayList var3 = new ArrayList(var1.length);

      for (boolean var7 : var1) {
         var3.add(this.createConstant(var7 ? 1L : 0L, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createByteArray(byte[] var1) {
      IJavaType var2 = this.A.getByte();
      ArrayList var3 = new ArrayList(var1.length);

      for (byte var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createCharArray(char[] var1) {
      cdi var2 = this.A.wS();
      ArrayList var3 = new ArrayList(var1.length);

      for (char var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createShortArray(short[] var1) {
      cdi var2 = this.A.UT();
      ArrayList var3 = new ArrayList(var1.length);

      for (short var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createIntArray(int[] var1) {
      cdi var2 = this.A.E();
      ArrayList var3 = new ArrayList(var1.length);

      for (int var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createLongArray(long[] var1) {
      cdi var2 = this.A.sY();
      ArrayList var3 = new ArrayList(var1.length);

      for (long var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var9 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var9, var3);
   }

   @Override
   public IDNewArrayInfo createFloatArray(float[] var1) {
      cdi var2 = this.A.ys();
      ArrayList var3 = new ArrayList(var1.length);

      for (float var7 : var1) {
         var3.add(this.createFloat(var7));
      }

      IDImm var8 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createDoubleArray(double[] var1) {
      cdi var2 = this.A.ld();
      ArrayList var3 = new ArrayList(var1.length);

      for (double var7 : var1) {
         var3.add(this.createDouble(var7));
      }

      IDImm var9 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var9, var3);
   }

   @Override
   public IDNewArrayInfo createStringArray(String[] var1) {
      IJavaType var2 = this.A.getJavaLangString();
      ArrayList var3 = new ArrayList(var1.length);

      for (String var7 : var1) {
         var3.add(this.createString(var7));
      }

      IDImm var8 = this.createConstant(var1.length, this.A.E());
      return new bpy(this.A.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDTarget createTarget(int var1) {
      return new bqf(var1);
   }

   @Override
   public IDSwitchData createSwitchData() {
      return new bqe();
   }

   public bgl UT() {
      return this.wS;
   }

   public brk pC(IDMethodContext var1, boolean var2, boolean var3, boolean var4) {
      return this.ys.pC(var1, null, var2, var3, var4);
   }

   @Override
   public Object setData(String var1, Object var2) {
      return var2 == null ? this.fI.remove(var1) : this.fI.put(var1, var2);
   }

   @Override
   public Object getData(String var1) {
      return this.fI.get(var1);
   }

   @Override
   public Object getData(String var1, Function var2) {
      return this.fI.computeIfAbsent(var1, var2);
   }

   @Override
   public Set getDataKeys() {
      return Collections.unmodifiableSet(this.fI.keySet());
   }

   @Override
   public IDState createState() {
      return this.createState(false, false);
   }

   @Override
   public IDState createState(boolean var1, boolean var2) {
      return this.pC(var1, var1, var2);
   }

   public IDState pC(boolean var1, boolean var2, boolean var3) {
      btp var4 = new btp(this);
      btr.pC(var4).pC(var1, var2, var3);
      return var4;
   }

   public synchronized void E() {
      if (this.gp != null) {
         try {
            this.gp.interrupt();
            this.gp.join(3000L);
            this.gp = null;
         } catch (InterruptedException var3) {
         }
      }

      this.ld.remove();
      if (this.E != null) {
         try {
            for (btp var2 : this.E.values()) {
               if (var2 != null && !var2.A()) {
                  var2.dispose();
               }
            }

            this.E.clear();
            this.E = null;
         } catch (Exception var4) {
         }
      }
   }

   public synchronized btp sY() {
      if (this.E == null) {
         throw new IllegalStateException("The IR context is disposed");
      } else {
         btp var1 = (btp)this.ld.get();
         if (var1 != null && var1.isSandboxEnabled() && var1.ys().kS() >= 250) {
            this.ld.remove();
            var1.dispose();
            var1 = (btp)this.ld.get();
         }

         return var1;
      }
   }

   @Override
   public IDState getEmulator() {
      btp var1 = this.sY();
      btr.pC(var1).pC(true, true, true);
      return var1;
   }

   @Override
   public void resetDeobfuscatorCounters() {
      ccw.pC(this);
   }

   @Override
   public void resetDeobfuscatorCounters(String var1) {
      ccw.A(this, var1);
   }

   public brw ys() {
      return this.oT;
   }

   private class Av extends ThreadLocal {
      protected btp pC() {
         btp var1 = new btp(bpr.this);
         bpr.this.E.put(Thread.currentThread(), var1);
         return var1;
      }
   }

   private class Sv extends Thread {
      @Override
      public void run() {
         while (true) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var4) {
               return;
            }

            for (Entry var2 : bpr.this.E.entrySet()) {
               Thread var3 = (Thread)var2.getKey();
               if (!var3.isAlive()) {
                  ((btp)var2.getValue()).dispose();
                  bpr.this.E.remove(var3);
               }
            }
         }
      }
   }
}
