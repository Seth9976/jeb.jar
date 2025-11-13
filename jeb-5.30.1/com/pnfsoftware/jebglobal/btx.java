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

public class btx implements IDGlobalContext {
   private static final ILogger gO = GlobalLog.getLogger(btx.class);
   private com.pnfsoftware.jeb.corei.parsers.dexdec.ej nf;
   public com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   public cis RF;
   public cio xK;
   public bkg Dw;
   Map Uv = new ConcurrentHashMap();
   public ConcurrentHashMap oW;
   private btx.eo gP;
   private Thread za;
   private bwh lm;
   private Map zz = new ConcurrentHashMap();

   public btx(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1, cis var2, cio var3, bkg var4) {
      this.nf = var1;
      this.q = var1.TX();
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.oW = new ConcurrentHashMap();
      this.gP = new btx.eo();
      this.za = ThreadUtil.start("jeb-dexdec-SE-cleaner", new btx.CU());
      this.lm = new bwh(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.zz == null ? 0 : this.zz.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return var1 == this;
   }

   public com.pnfsoftware.jeb.corei.parsers.dexdec.ej q() {
      return this.nf;
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.bK RF() {
      return this.q;
   }

   public cis xK() {
      return this.RF;
   }

   public cio Dw() {
      return this.xK;
   }

   public IDMethodContext q(IDexMethod var1) {
      return new bud(this, var1);
   }

   @Override
   public IDImm createBoolean(boolean var1) {
      return new bty(var1 ? 1L : 0L, this.RF.xK());
   }

   @Override
   public IDImm createByte(byte var1) {
      return new bty(var1, this.RF.getByte());
   }

   @Override
   public IDImm createChar(char var1) {
      return new bty(var1, this.RF.Dw());
   }

   @Override
   public IDImm createShort(short var1) {
      return new bty(var1, this.RF.Uv());
   }

   @Override
   public IDImm createInt(int var1) {
      return new bty(var1, this.RF.oW());
   }

   @Override
   public IDImm createLong(long var1) {
      return new bty(var1, this.RF.gO());
   }

   @Override
   public IDImm createFloat(float var1) {
      return new bty(Float.floatToIntBits(var1) & 4294967295L, this.RF.nf());
   }

   @Override
   public IDImm createDouble(double var1) {
      return new bty(Double.doubleToLongBits(var1), this.RF.gP());
   }

   @Override
   public IDImm createImm(long var1, IJavaType var3) {
      return new bty(var1, var3);
   }

   @Override
   public IDImm createRef(int var1, IJavaType var2) {
      if (var2 == null) {
         var2 = this.RF.JY();
      } else if (!((IJavaType)var2).isObject()) {
         throw new IllegalArgumentException();
      }

      return new bty(var1, (IJavaType)var2);
   }

   @Override
   public IDImm createRef(int var1) {
      return this.createRef(var1, null);
   }

   @Override
   public IDImm createNull() {
      return new bty(0L, this.RF.JY());
   }

   @Override
   public IDImm createString(String var1) {
      bjs var2 = this.q.Uv(var1);
      return new bty(this.RF.getJavaLangString(), this.createIndex(var2.getIndex()));
   }

   @Override
   public IDImm createString(IDIndex var1) {
      return new bty(this.RF.getJavaLangString(), var1);
   }

   @Override
   public IDArrayElt createArrayElt(IDExpression var1, IDExpression var2, IJavaType var3) {
      return new btt(var1, var2, var3);
   }

   private IJavaType q(IJavaOperator var1, IDExpression var2, IDExpression var3) {
      Object var4 = null;
      if (var1.isArithmetic()) {
         if (var2 == null) {
            var4 = var3.getType();
         } else if (bug.q(var2, var1)) {
            var4 = this.RF.gO();
         } else {
            var4 = var3.getType();
         }
      } else if (var1.isLogical()) {
         var4 = this.RF.xK();
      } else if (var1.isCast()) {
         if (var1.is(JavaOperatorType.CAST_TO_BYTE)) {
            var4 = this.RF.getByte();
         } else if (var1.is(JavaOperatorType.CAST_TO_CHAR)) {
            var4 = this.RF.Dw();
         } else if (var1.is(JavaOperatorType.CAST_TO_SHORT)) {
            var4 = this.RF.Uv();
         } else if (var1.is(JavaOperatorType.CAST_TO_INT)) {
            var4 = this.RF.oW();
         } else if (var1.is(JavaOperatorType.CAST_TO_LONG)) {
            var4 = this.RF.gO();
         } else if (var1.is(JavaOperatorType.CAST_TO_FLOAT)) {
            var4 = this.RF.nf();
         } else if (var1.is(JavaOperatorType.CAST_TO_DOUBLE)) {
            var4 = this.RF.gP();
         } else if (var1.is(JavaOperatorType.CAST_TO_BOOLEAN)) {
            var4 = this.RF.xK();
         } else {
            var4 = var1.getCastType();
         }
      } else if (var1.is(JavaOperatorType.CONCAT)) {
         var4 = this.RF.getJavaLangString();
      } else if (var1.is(JavaOperatorType.COND_EXP)) {
         var4 = var2.getType();
      }

      return (IJavaType)var4;
   }

   @Override
   public IDOperation createOperation(IJavaType var1, JavaOperatorType var2, IDExpression var3, IDExpression var4) {
      IJavaOperator var5 = this.xK.getStandardOperator(var2);
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
         var1 = this.q(var3, var2, var4);
      }

      return new bug(var1, var2, var3, var4);
   }

   @Override
   public IDOperation createCastOperation(String var1, IDExpression var2) {
      return this.createCastOperation(this.RF.createType(var1), var2);
   }

   @Override
   public IDOperation createCastOperation(IJavaType var1, IDExpression var2) {
      return this.createOperation(var1, null, this.xK.createCastOperator(var1), var2);
   }

   @Override
   public IDOperation createConditional(IJavaType var1, IDExpression var2, IDExpression var3, IDExpression var4) {
      IJavaOperator var5 = this.xK.getStandardOperator(JavaOperatorType.COND_EXP);
      if (var1 == null) {
         var1 = this.q(var5, var3, var4);
      }

      return new bug(var1, var5, var2, var3, var4);
   }

   @Override
   public IDOperation createPredicate(JavaOperatorType var1, IDExpression var2, IDExpression var3) {
      return this.createPredicate(var2, this.xK.getStandardOperator(var1), var3);
   }

   @Override
   public IDOperation createPredicate(IDExpression var1, IJavaOperator var2, IDExpression var3) {
      return new bug(this.RF.xK(), var1, var2, var3);
   }

   @Override
   public IDReferenceType createReferenceType(IDIndex var1, IJavaType var2) {
      return new bui(this.RF.RF(), var1, var2);
   }

   @Override
   public IDReferenceType createReferenceType(String var1) {
      bjt var2 = this.q.RF(var1);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.createReferenceType(this.createIndex(var2.getIndex()), this.RF.createType(var2.getSignature(false)));
      }
   }

   @Override
   public IDIndex createIndex(int var1) {
      return new btz(var1);
   }

   @Override
   public IDStaticField createStaticField(IDIndex var1, IJavaType var2, String var3, String var4) {
      return new buj(var1, var2, var3, var4);
   }

   @Override
   public IDStaticField createStaticField(IDexField var1) {
      if (var1.getData() != null && !var1.getData().isStatic()) {
         throw new IllegalArgumentException("Internal field is non-static, expected static");
      } else {
         IDIndex var2 = this.createIndex(var1.getIndex());
         IJavaType var3 = this.RF.createType(var1.getFieldTypeSignature(false));
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
      bjo var3 = this.q.xK(var1);
      if (var3 == null) {
         if (!var2) {
            throw new IllegalArgumentException("Field does not exist");
         }

         var3 = this.q.gP(var1);
      }

      return this.createStaticField(var3);
   }

   @Override
   public IDInstanceField createInstanceField(IDExpression var1, IDIndex var2, IJavaType var3, String var4) {
      return new bua(var1, var2, var3, var4);
   }

   @Override
   public IDInstanceField createInstanceField(IDExpression var1, IDexField var2) {
      if (var2.getData() != null && var2.getData().isStatic()) {
         throw new IllegalArgumentException("Internal field is static, expected non-static");
      } else {
         IDIndex var3 = this.createIndex(var2.getIndex());
         IJavaType var4 = this.RF.createType(var2.getFieldTypeSignature(false));
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
      bjo var4 = this.q.xK(var2);
      if (var4 == null) {
         if (!var3) {
            throw new IllegalArgumentException("Field does not exist");
         }

         var4 = this.q.gP(var2);
      }

      return this.createInstanceField(var1, var4);
   }

   @Override
   public IDStaticField createClassObject(String var1) {
      return this.createStaticField(null, this.RF.createType("Ljava/lang/Class;"), var1, "class");
   }

   @Override
   public IDInstanceField createArrayLength(IDExpression var1) {
      return this.createInstanceField(var1, null, this.RF.oW(), "length");
   }

   @Override
   public IDCallInfo createCallInfo(IDIndex var1, IDExpression[] var2, IJavaType var3, String var4, DInvokeType var5) {
      return new btu(var1, var2, var3, var4, var5);
   }

   @Override
   public IDCallInfo createCallInfo(IDIndex var1, List var2, IJavaType var3, String var4, DInvokeType var5) {
      return new btu(var1, (IDExpression[])var2.toArray(new IDExpression[var2.size()]), var3, var4, var5);
   }

   @Override
   public IDCallInfo createCallInfo(DInvokeType var1, int var2, List var3) {
      bjp var4 = this.q.gO(var2);
      if (var4 == null) {
         throw new IllegalArgumentException("No method found for index " + var2);
      } else if (var4.getData() != null && var4.getData().isStatic() && var1 != DInvokeType.STATIC) {
         throw new IllegalArgumentException("Expecting a Static invocation type for method " + var4.getSignature());
      } else {
         return new btu(
            this.createIndex(var2),
            var3 == null ? null : (IDExpression[])var3.toArray(new IDExpression[var3.size()]),
            this.RF.createType(var4.getReturnType().getSignature(false)),
            var4.getSignature(false),
            var1
         );
      }
   }

   @Override
   public IDCallInfo createCallInfo(DInvokeType var1, String var2, List var3) {
      bjp var4 = this.q.Dw(var2);
      if (var4 == null) {
         var4 = this.q.nf(var2);
      }

      return this.createCallInfo(var1, var4.getIndex(), var3);
   }

   @Override
   public IDAllocObjectInfo createAllocObjectInfo(IJavaType var1) {
      return new bts(var1);
   }

   @Override
   public IDAllocObjectInfo createAllocObjectInfo(String var1) {
      return this.createAllocObjectInfo(this.RF.q(var1));
   }

   @Override
   public IDNewInfo createNewInfo(String var1, IDExpression... var2) {
      bjp var3 = this.q.nf(var1);
      IJavaType var4 = this.RF.createType(var3.getClassType().getSignature(false));
      IDIndex var5 = this.createIndex(var3.getIndex());
      return new buf(var4, var4, var5, var2, var3.getSignature(false));
   }

   @Override
   public IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, IDExpression[] var4, String var5) {
      return new buf(var1, var2, var3, var4, var5);
   }

   @Override
   public IDNewInfo createNewInfo(IJavaType var1, IJavaType var2, IDIndex var3, List var4, String var5) {
      return new buf(var1, var2, var3, (IDExpression[])var4.toArray(new IDExpression[var4.size()]), var5);
   }

   @Override
   public IDNewArrayInfo createNewArrayInfo(IJavaType var1, IDExpression var2, List var3) {
      return new bue(var1, var2, var3);
   }

   @Override
   public IDNewArrayInfo createBooleanArray(boolean[] var1) {
      ciq var2 = this.RF.xK();
      ArrayList var3 = new ArrayList(var1.length);

      for (boolean var7 : var1) {
         var3.add(this.createConstant(var7 ? 1L : 0L, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createByteArray(byte[] var1) {
      IJavaType var2 = this.RF.getByte();
      ArrayList var3 = new ArrayList(var1.length);

      for (byte var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createCharArray(char[] var1) {
      ciq var2 = this.RF.Dw();
      ArrayList var3 = new ArrayList(var1.length);

      for (char var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createShortArray(short[] var1) {
      ciq var2 = this.RF.Uv();
      ArrayList var3 = new ArrayList(var1.length);

      for (short var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createIntArray(int[] var1) {
      ciq var2 = this.RF.oW();
      ArrayList var3 = new ArrayList(var1.length);

      for (int var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var8 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createLongArray(long[] var1) {
      ciq var2 = this.RF.gO();
      ArrayList var3 = new ArrayList(var1.length);

      for (long var7 : var1) {
         var3.add(this.createConstant(var7, var2));
      }

      IDImm var9 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var9, var3);
   }

   @Override
   public IDNewArrayInfo createFloatArray(float[] var1) {
      ciq var2 = this.RF.nf();
      ArrayList var3 = new ArrayList(var1.length);

      for (float var7 : var1) {
         var3.add(this.createFloat(var7));
      }

      IDImm var8 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDNewArrayInfo createDoubleArray(double[] var1) {
      ciq var2 = this.RF.gP();
      ArrayList var3 = new ArrayList(var1.length);

      for (double var7 : var1) {
         var3.add(this.createDouble(var7));
      }

      IDImm var9 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var9, var3);
   }

   @Override
   public IDNewArrayInfo createStringArray(String[] var1) {
      IJavaType var2 = this.RF.getJavaLangString();
      ArrayList var3 = new ArrayList(var1.length);

      for (String var7 : var1) {
         var3.add(this.createString(var7));
      }

      IDImm var8 = this.createConstant(var1.length, this.RF.oW());
      return new bue(this.RF.createArrayType(var2, 1), var8, var3);
   }

   @Override
   public IDTarget createTarget(int var1) {
      return new bul(var1);
   }

   @Override
   public IDSwitchData createSwitchData() {
      return new buk();
   }

   public bkg Uv() {
      return this.Dw;
   }

   public bvt q(IDMethodContext var1, boolean var2, boolean var3, boolean var4) {
      return this.nf.q(var1, null, var2, var3, var4);
   }

   @Override
   public Object setData(String var1, Object var2) {
      return var2 == null ? this.zz.remove(var1) : this.zz.put(var1, var2);
   }

   @Override
   public Object getData(String var1) {
      return this.zz.get(var1);
   }

   @Override
   public Object getData(String var1, Function var2) {
      return this.zz.computeIfAbsent(var1, var2);
   }

   @Override
   public Set getDataKeys() {
      return Collections.unmodifiableSet(this.zz.keySet());
   }

   @Override
   public IDState createState() {
      return this.createState(false, false);
   }

   @Override
   public IDState createState(boolean var1, boolean var2) {
      return this.q(var1, var1, var2);
   }

   public IDState q(boolean var1, boolean var2, boolean var3) {
      bye var4 = new bye(this);
      byg.q(var4).q(var1, var2, var3);
      return var4;
   }

   public synchronized void oW() {
      if (this.za != null) {
         try {
            this.za.interrupt();
            this.za.join(3000L);
            this.za = null;
         } catch (InterruptedException var3) {
         }
      }

      this.gP.remove();
      if (this.oW != null) {
         try {
            for (bye var2 : this.oW.values()) {
               if (var2 != null && !var2.RF()) {
                  var2.dispose();
               }
            }

            this.oW.clear();
            this.oW = null;
         } catch (Exception var4) {
         }
      }
   }

   public synchronized bye gO() {
      if (this.oW == null) {
         throw new IllegalStateException("The IR context is disposed");
      } else {
         bye var1 = (bye)this.gP.get();
         if (var1 != null && var1.isSandboxEnabled() && var1.nf().xK() >= 250) {
            this.gP.remove();
            var1.dispose();
            var1 = (bye)this.gP.get();
         }

         return var1;
      }
   }

   @Override
   public IDState getEmulator() {
      bye var1 = this.gO();
      byg.q(var1).q(true, true, true);
      return var1;
   }

   @Override
   public void resetDeobfuscatorCounters() {
      chs.q(this);
   }

   @Override
   public void resetDeobfuscatorCounters(String var1) {
      chs.RF(this, var1);
   }

   public bwh nf() {
      return this.lm;
   }

   private class CU extends Thread {
      @Override
      public void run() {
         while (true) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var4) {
               return;
            }

            for (Entry var2 : btx.this.oW.entrySet()) {
               Thread var3 = (Thread)var2.getKey();
               if (!var3.isAlive()) {
                  ((bye)var2.getValue()).dispose();
                  btx.this.oW.remove(var3);
               }
            }
         }
      }
   }

   private class eo extends ThreadLocal {
      protected bye q() {
         bye var1 = new bye(btx.this);
         btx.this.oW.put(Thread.currentThread(), var1);
         return var1;
      }
   }
}
