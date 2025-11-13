package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.IJLSField;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.INativeLibrary;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodDescriptor;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmTypeSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexExceptionItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.ir.DEmuExternalPolicy;
import com.pnfsoftware.jeb.core.units.code.android.ir.DExecutionParameters;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecConversionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalCodeThrownException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalFailedTranslationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalItercountExceededException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalSandboxExecutionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalTimeoutExceededException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecNativeEvalFailedException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodExecutionHelper;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

public class btp implements IDState {
   static final ILogger pC = GlobalLog.getLogger(btp.class);
   public static int A = 1;
   public static final int kS = 1;
   public static final int wS = 2;
   public static final int UT = 255;
   private static final int ld = 10000;
   private volatile boolean gp;
   private Map oT = new HashMap();
   private IDGlobalContext fI;
   private IDexUnit WR;
   private IJavaTypeFactory NS;
   private boolean vP;
   private int xC;
   private int ED = 100;
   private long Sc = 1000L;
   private int ah = 0;
   private long eP = 0L;
   private int UO = 50;
   private int Ab = 0;
   private int rl = 0;
   private static final int z = 209715200;
   private Watchdog Ek;
   private bvo hK;
   private boolean Er;
   private boolean FE;
   private boolean Aj;
   private bvm EX;
   private int LM;
   private boolean mv = true;
   private boolean sO = true;
   private List os = new ArrayList();
   private Deque Cu = new ArrayDeque();
   private Object hZ;
   private IDEmuContext UW;
   private IDEmuFrame PR;
   private bgt cX;
   private bgu DQ;
   private final Object ZN = new Object();
   private int OB = 0;
   private int pF = 0;
   private DEmuExternalPolicy Bc = null;
   private boolean OI;
   private btd Bf;
   private int Pe = 10000;
   private IdentityHashMap ck = new IdentityHashMap();
   private Map RW = new HashMap();
   Map E = new HashMap();
   Set sY = new HashSet();
   private List e = new ArrayList();
   private int xM;
   private int[] kU = new int[3];
   private LinkedHashSet Kq = new LinkedHashSet();
   private boolean go = true;
   private bvu JF = new bvu();
   private btl Nq = new btl();
   private IEmulatedAndroid pg;
   private bqi gj;
   private CacheMap ZD;
   private Map DL = new HashMap();
   public int ys;
   private static final AtomicLong UH = new AtomicLong(1L);
   private static final String VD = ckx.pC(
      new byte[]{
         -19,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         108,
         47,
         13,
         18,
         0,
         72,
         22,
         19,
         80,
         11,
         18,
         62,
         39,
         29,
         7,
         21,
         15,
         13,
         6,
         77,
         1,
         101,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         96,
         45,
         8,
         15,
         6,
         23,
         79
      },
      1,
      161
   );
   private static final String Xs = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         17,
         76,
         70,
         93,
         92,
         90,
         71,
         3,
         99,
         93,
         94,
         65,
         65,
         94,
         85,
         44,
         6,
         14,
         17,
         87,
         72,
         30,
         15,
         11,
         19,
         102,
         1,
         1,
         84,
         8,
         26,
         16,
         69,
         73,
         61,
         42,
         3,
         13,
         31,
         0,
         91,
         9,
         18,
         64,
         71,
         110,
         35,
         14,
         74,
         23,
         10,
         19,
         83,
         93,
         63,
         74,
         19,
         19,
         18,
         74,
         30,
         23,
         11,
         3,
         1,
         12,
         13,
         26,
         28,
         17,
         29,
         92
      },
      2,
      248
   );
   private static final String KV = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         17,
         76,
         70,
         93,
         92,
         90,
         71,
         3,
         109,
         87,
         68,
         90,
         90,
         72,
         27,
         98,
         76,
         8,
         13,
         26,
         10,
         75,
         4,
         70,
         40,
         69,
         14,
         4,
         65,
         70,
         24,
         18,
         78,
         6,
         73,
         41,
         11,
         6,
         12,
         2,
         0,
         94,
         40,
         98,
         74,
         32,
         26,
         13,
         15,
         30,
         8,
         9,
         15,
         91,
         60,
         66,
         24,
         0,
         16,
         17,
         73,
         95,
         41,
         14,
         79,
         53,
         14,
         95,
         21,
         19,
         7,
         0,
         71,
         59,
         66,
         66,
         6,
         74,
         84,
         10
      },
      2,
      12
   );
   private static final String FK = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         17,
         76,
         70,
         93,
         92,
         90,
         71,
         3,
         102,
         91,
         85,
         94,
         81,
         23,
         13,
         113,
         21,
         4,
         23,
         68,
         41,
         74,
         0,
         24,
         5,
         0,
         3,
         19,
         78,
         14,
         91,
         60,
         66,
         11,
         3,
         5,
         29,
         87,
         64,
         45,
         30,
         4,
         5,
         79,
         15,
         45,
         13,
         2,
         71,
         93,
         38,
         5,
         2,
         17,
         16,
         84,
         73
      },
      2,
      89
   );
   private static final String Bi = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         17,
         76,
         70,
         93,
         92,
         90,
         71,
         3,
         102,
         91,
         85,
         94,
         81,
         23,
         13,
         113,
         1,
         4,
         23,
         68,
         41,
         74,
         0,
         24,
         5,
         0,
         3,
         19,
         78,
         14,
         91,
         60,
         66,
         11,
         3,
         5,
         29,
         87,
         37,
         11,
         21,
         19,
         18,
         1,
         76,
         32,
         2,
         11,
         15,
         61,
         11,
         13,
         13,
         23,
         7,
         27,
         91,
         51
      },
      2,
      158
   );
   private static final Set wQ = new HashSet();
   private static final Set PZ = new HashSet();

   static final void pC(int var0, String var1, Object... var2) {
   }

   public btp(IDGlobalContext var1) {
      this.fI = var1;
      this.WR = var1.getDex();
      this.NS = var1.getTypeFactory();
      this.gj = new bqi(var1);
      this.ZD = new CacheMap(30);
      this.UT();
   }

   public btp pC() {
      btp var1 = new btp(this.fI);
      var1.gp = this.gp;
      var1.oT = this.oT;
      var1.fI = this.fI;
      var1.WR = this.WR;
      var1.NS = this.NS;
      var1.ED = this.ED;
      var1.Sc = this.Sc;
      var1.ah = this.ah;
      var1.eP = this.eP;
      var1.Ek = this.Ek;
      var1.hK = null;
      var1.Er = false;
      var1.FE = this.FE;
      var1.vP = this.vP;
      var1.xC = this.xC;
      var1.Aj = this.Aj;
      var1.mv = this.mv;
      var1.sO = this.sO;
      this.Cu.forEach(var1x -> var1.Cu.add(var1x.copy()));
      var1.UW = (IDEmuContext)var1.Cu.peek();
      var1.PR = var1.UW == null ? null : ((btm)var1.UW).A();
      var1.cX = this.cX;
      var1.DQ = this.DQ;
      var1.OB = this.OB;
      var1.pF = this.pF;
      var1.OI = this.OI;
      var1.Bc = this.Bc;
      var1.Bf = this.Bf;
      var1.Pe = this.Pe;
      var1.ck = this.ck;
      var1.RW = this.RW;
      var1.E = this.E;
      var1.sY = this.sY;
      var1.e = this.e;
      var1.xM = this.xM;
      var1.kU = Arrays.copyOf(this.kU, this.kU.length);
      var1.Kq = new LinkedHashSet(this.Kq);
      var1.go = this.go;
      var1.JF = this.JF;
      var1.Nq = this.Nq;
      return var1;
   }

   @Override
   public void dispose() {
      if (!this.A()) {
         if (this.hK != null) {
            this.hK.pC();
         }

         this.hK = null;
         if (this.EX != null) {
            this.EX.pC();
         }

         this.EX = null;
         this.fI = null;
      }
   }

   public boolean A() {
      return this.fI == null;
   }

   @Override
   public File setSandboxDroppedFilesCollection(String var1, boolean var2) throws IOException {
      if (!this.isSandboxEnabled()) {
         throw new IllegalStateException("The sandbox is disabled");
      } else {
         return this.sY().pC(var1, var2);
      }
   }

   @Override
   public File getSandboxDropFolder() {
      if (!this.isSandboxEnabled()) {
         throw new IllegalStateException("The sandbox is disabled");
      } else {
         return this.sY().kS();
      }
   }

   public boolean kS() {
      return this.gp;
   }

   public void wS() {
      this.gp = true;
   }

   public void UT() {
      this.ED = 100;
      this.Sc = 1000L;
      this.ah = 0;
      this.eP = 0L;
   }

   @Override
   public int getMaxIterationCount() {
      return this.ED;
   }

   @Override
   public int setMaxIterationCount(int var1) {
      if (var1 < 0) {
         var1 = Integer.MAX_VALUE;
      }

      int var2 = this.ED;
      this.ED = var1;
      return var2;
   }

   @Override
   public int getCurrentIterationCount() {
      return this.ah;
   }

   @Override
   public int getIterationCountLeft() {
      return Math.max(0, this.ED - this.ah);
   }

   @Override
   public long getMaxDuration() {
      return this.Sc;
   }

   @Override
   public long setMaxDuration(long var1) {
      if (var1 < 0L) {
         var1 = Long.MAX_VALUE;
      }

      long var3 = this.Sc;
      this.Sc = var1;
      return var3;
   }

   @Override
   public long getCurrentDuration() {
      return this.eP;
   }

   @Override
   public long getTimeLeft() {
      return Math.max(0L, this.Sc - this.eP);
   }

   long pC(long var1) {
      long var3 = var1 + this.getTimeLeft();
      if (var3 < 0L) {
         var3 = Long.MAX_VALUE;
      }

      return var3;
   }

   @Override
   public Watchdog getWatchdog() {
      return this.Ek;
   }

   @Override
   public Watchdog setWatchdog(Watchdog var1) {
      Watchdog var2 = this.Ek;
      this.Ek = var1;
      return var2;
   }

   @Override
   public boolean canRun() {
      return this.getIterationCountLeft() > 0 && this.getTimeLeft() > 0L;
   }

   @Override
   public int setMaxInvocationDepth(int var1) {
      int var2 = this.UO;
      this.UO = var1;
      return var2;
   }

   @Override
   public int getMaxInvocationDepth() {
      return this.UO;
   }

   @Override
   public IDGlobalContext getGlobalContext() {
      return this.fI;
   }

   @Override
   public IDexDecompilerUnit getDecompiler() {
      return this.fI.getDecompiler();
   }

   @Override
   public IDexUnit getDex() {
      return this.WR;
   }

   @Override
   public IApkUnit getApk() {
      return DexUtil.findParentApk(this.WR);
   }

   public IEmulatedAndroid pC(IEmulatedAndroid var1) {
      IEmulatedAndroid var2 = this.pg;
      this.pg = var1;
      return var2;
   }

   @Override
   public IEmulatedAndroid getEmulatedEnvironment() {
      return this.pg;
   }

   public IJavaTypeFactory E() {
      return this.NS;
   }

   @Override
   public IJLSTypeAdapter getTypeAdapter() {
      if (this.DQ == null) {
         synchronized (this.ZN) {
            if (this.cX == null) {
               this.cX = new bgt(this.WR, false, false);
            }

            if (this.DQ == null) {
               if (this.isSandboxEnabled()) {
                  this.DQ = new bgu(this.cX, this.hK.E());
               } else {
                  this.DQ = new bgu(this.cX);
               }
            }
         }
      }

      return this.DQ;
   }

   public bvo sY() {
      if (this.hK == null) {
         throw new IllegalStateException(
            ckx.pC(new byte[]{-118, 60, 13, 69, 83, 18, 15, 10, 6, 13, 23, 88, 73, 26, 83, 78, 1, 27, 84, 69, 11, 15, 3, 14, 9, 1}, 1, 222)
         );
      } else {
         return this.hK;
      }
   }

   public bvs ys() {
      return this.sY().wS();
   }

   @Override
   public void addClassfilesToSandbox(File var1) throws IOException {
      this.ys().pC(var1);
   }

   public btd ld() {
      if (this.Bf == null) {
         throw new IllegalStateException(
            ckx.pC(new byte[]{107, 60, 13, 69, 83, 18, 15, 10, 6, 13, 23, 88, 73, 26, 83, 78, 1, 27, 84, 69, 11, 15, 3, 14, 9, 1}, 1, 63)
         );
      } else {
         return this.Bf;
      }
   }

   @Override
   public void enableExceptionHandling(boolean var1) {
      this.vP = var1;
   }

   @Override
   public boolean setExceptionHandlingEnabled(boolean var1) {
      boolean var2 = this.vP;
      this.vP = var1;
      return var2;
   }

   @Override
   public boolean isExceptionHandlingEnabled() {
      return this.vP;
   }

   @Override
   public boolean enableSandbox(boolean var1) {
      if (var1 && this.hK != null) {
         this.Er = true;
         this.DQ = null;
         return true;
      } else if (!var1) {
         this.Er = false;
         this.DQ = null;
         return true;
      } else {
         if (var1 != this.Er) {
            if (this.hK == null) {
               try {
                  this.hK = new bvo(this);
               } catch (Exception var2) {
                  return false;
               }

               this.Bf = new btd(this);
            }

            this.Er = var1;
            this.DQ = null;
         }

         return true;
      }
   }

   @Override
   public boolean isSandboxEnabled() {
      return this.hK != null && this.Er;
   }

   public bvo gp() throws DexDecEvaluationException {
      if (!this.isSandboxEnabled()) {
         throw new DexDecEvaluationException(ckx.pC(new byte[]{0, 14, 30, 23, 29, 29, 71, 29, 7, 69, 8, 16, 72, 78, 85, 91, 86, 75}, 2, 121));
      } else {
         return this.hK;
      }
   }

   @Override
   public void enableEmulator(boolean var1) {
      this.FE = var1;
   }

   @Override
   public boolean isEmulatorEnabled() {
      return this.FE;
   }

   public bvm oT() {
      if (this.EX == null) {
         pC(1, "NATIVE: the manager is being created");
         this.EX = new bvm(this, null);
      }

      return this.EX;
   }

   public cdz fI() {
      bvm var1 = this.oT();

      try {
         var1.A();
      } catch (DexDecNativeEvalFailedException var3) {
         throw new RuntimeException(var3);
      }

      return var1.kS();
   }

   public void WR() throws DexDecEvaluationException {
      if (!this.FE) {
         throw new DexDecEvaluationException(ckx.pC(new byte[]{0, 14, 30, 23, 29, 29, 71, 29, 7, 69, 8, 6, 68, 85, 93, 88, 77, 92, 94}, 2, 146));
      }
   }

   public bvu NS() {
      return this.JF;
   }

   @Override
   public int registerSandboxHooks(IDSandboxHooks var1) {
      return this.JF.pC(var1, true);
   }

   @Override
   public boolean unregisterSandboxHooks(int var1) {
      return this.JF.A(var1);
   }

   @Override
   public void enableNativeCodeEmulator(boolean var1) {
      this.Aj = var1;
      if (!var1) {
         this.EX = null;
      }
   }

   @Override
   public boolean isNativeCodeEmulatorEnabled() {
      return this.Aj;
   }

   @Override
   public int registerNativeEmulatorHooks(IEEmulatorHooks var1) {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new IllegalStateException(
            ckx.pC(
               new byte[]{
                  (byte)23,
                  (byte)7,
                  (byte)21,
                  (byte)89,
                  (byte)28,
                  (byte)8,
                  (byte)19,
                  (byte)1,
                  (byte)2,
                  (byte)69,
                  (byte)8,
                  (byte)6,
                  (byte)68,
                  (byte)85,
                  (byte)93,
                  (byte)88,
                  (byte)77,
                  (byte)92,
                  (byte)94,
                  (byte)0,
                  (byte)95,
                  (byte)69,
                  (byte)65,
                  (byte)65,
                  (byte)12,
                  (byte)66,
                  (byte)42,
                  (byte)82,
                  (byte)4,
                  (byte)13,
                  (byte)13,
                  (byte)7,
                  (byte)76,
                  (byte)4,
                  (byte)10
               },
               2,
               202
            )
         );
      } else {
         return this.oT().pC(var1);
      }
   }

   @Override
   public boolean unregisterNativeEmulatorHooks(int var1) {
      return this.oT().pC(var1);
   }

   @Override
   public int registerNativeEmulatorMemoryHooks(IEStateHooks var1) {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new IllegalStateException(
            ckx.pC(
               new byte[]{
                  (byte)23,
                  (byte)7,
                  (byte)21,
                  (byte)89,
                  (byte)28,
                  (byte)8,
                  (byte)19,
                  (byte)1,
                  (byte)2,
                  (byte)69,
                  (byte)8,
                  (byte)6,
                  (byte)68,
                  (byte)85,
                  (byte)93,
                  (byte)88,
                  (byte)77,
                  (byte)92,
                  (byte)94,
                  (byte)0,
                  (byte)95,
                  (byte)69,
                  (byte)65,
                  (byte)65,
                  (byte)12,
                  (byte)66,
                  (byte)42,
                  (byte)82,
                  (byte)4,
                  (byte)13,
                  (byte)13,
                  (byte)7,
                  (byte)76,
                  (byte)4,
                  (byte)10
               },
               2,
               3
            )
         );
      } else {
         return this.oT().pC(var1);
      }
   }

   @Override
   public boolean unregisterNativeEmulatorMemoryHooks(int var1) {
      return this.oT().A(var1);
   }

   public int vP() {
      return this.LM;
   }

   @Override
   public boolean isLazyJNIOnLoadExec() {
      return this.mv;
   }

   @Override
   public boolean setLazyJNIOnLoadExec(boolean var1) {
      boolean var2 = this.mv;
      this.mv = var1;
      return var2;
   }

   @Override
   public boolean isRequireNonNullObjectForNonStaticInvoke() {
      return this.sO;
   }

   @Override
   public boolean setRequireNonNullObjectForNonStaticInvoke(boolean var1) {
      boolean var2 = this.sO;
      this.sO = var1;
      return var2;
   }

   static final long xC() {
      return UH.getAndIncrement();
   }

   public btl ED() {
      return this.Nq;
   }

   @Override
   public int registerEmulatorHooks(IDEmulatorHooks var1) {
      return this.Nq.pC(var1, true);
   }

   @Override
   public boolean unregisterEmulatorHooks(int var1) {
      return this.Nq.A(var1);
   }

   @Override
   public boolean setPerformDirectUnreflection(boolean var1) {
      boolean var2 = this.go;
      this.go = var1;
      return var2;
   }

   @Override
   public boolean isPerformDirectUnreflection() {
      return this.go;
   }

   @Override
   public void registerExecutionHelper(IDMethodExecutionHelper var1) {
      this.oT.put(var1.getMethodSignature(), var1);
   }

   public void pC(Collection var1) {
      var1.forEach(var1x -> this.registerExecutionHelper(var1x));
   }

   @Override
   public boolean unregisterExecutionHelper(String var1) {
      return this.oT.remove(var1) != null;
   }

   @Override
   public Collection getExecutionHelpers() {
      return Collections.unmodifiableCollection(this.oT.values());
   }

   public bto pC(Thread var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         bto var2 = new bto(this, var1);
         this.os.add(var2);
         return var2;
      }
   }

   public List Sc() {
      return this.os;
   }

   public bto A(Thread var1) {
      for (bto var3 : this.os) {
         if (var3.A == var1) {
            return var3;
         }
      }

      throw new IllegalArgumentException();
   }

   public Object ah() {
      return this.hZ;
   }

   public void pC(Object var1) {
      this.hZ = var1;
   }

   public String eP() {
      if (this.getCurrentFrame() == null) {
         return null;
      } else {
         String var1 = this.getCurrentFrame().getMethodSignature();
         int var2 = this.rl();
         return var2 < 0 ? var1 + "+???" : Strings.ff("%s+%Xh", var1, var2);
      }
   }

   @Override
   public IDEmuContext getCurrentContext() {
      return this.UW;
   }

   @Override
   public IDEmuContext pushContext(String var1) {
      this.UW = new btm(var1);
      this.Cu.push(this.UW);
      this.PR = null;
      return this.UW;
   }

   public IDEmuContext UO() {
      IDEmuContext var1 = (IDEmuContext)this.Cu.pop();
      this.UW = (IDEmuContext)this.Cu.peek();
      this.PR = null;
      if (this.UW != null) {
         btm var2 = (btm)this.UW;
         this.PR = var2.A();
      }

      return var1;
   }

   @Override
   public IDEmuFrame getCurrentFrame() {
      return this.PR;
   }

   @Override
   public IDEmuFrame pushFrame(String var1) {
      this.PR = new btn(var1);
      ((btm)this.UW).pC(this.PR);
      return this.PR;
   }

   public IDEmuFrame Ab() {
      btm var1 = (btm)this.UW;
      IDEmuFrame var2 = var1.pC();
      this.PR = var1.A();
      return var2;
   }

   @Override
   public IDEmuContext deleteTopContext() {
      return this.UO();
   }

   @Override
   public IDEmuFrame deleteTopFrame() {
      return this.Ab();
   }

   @Override
   public Collection getContexts() {
      return Collections.unmodifiableCollection(this.Cu);
   }

   private void pC(int var1, IDImm var2) throws DexDecEvaluationException {
      if (var2.getType().isSingleSlot()) {
         int var3 = DUtil.createRegisterVarId(var1, false);
         this.PR.setVariable(var3, var2);
      } else {
         if (!var2.getType().isDoubleSlot()) {
            throw new DexDecEvaluationException();
         }

         int var4 = DUtil.createRegisterVarId(var1, true);
         this.PR.setVariable(var4, var2);
      }
   }

   @Override
   public int getCountOfContexts() {
      return this.Cu.size();
   }

   @Override
   public void setVariable(int var1, IDImm var2) throws DexDecEvaluationException {
      this.PR.setVariable(var1, var2);
   }

   @Override
   public IDImm getVariable(int var1, boolean var2) throws DexDecEvaluationException {
      return this.PR.getVariable(var1, var2);
   }

   @Override
   public IDImm getVariable(int var1) throws DexDecEvaluationException {
      return this.PR.getVariable(var1);
   }

   @Override
   public boolean hasVariable(int var1) throws DexDecEvaluationException {
      return this.PR.hasVariable(var1);
   }

   @Override
   public boolean deleteVariable(int var1) throws DexDecEvaluationException {
      return this.PR.deleteVariable(var1);
   }

   @Override
   public void setPC(int var1) {
      this.PR.setPC(var1);
   }

   @Override
   public int getPC() {
      return this.PR.getPC();
   }

   public void pC(int var1) {
      ((btn)this.PR).A(var1);
   }

   public int rl() {
      return ((btn)this.PR).pC();
   }

   public Map z() {
      return this.PR.getVarMap();
   }

   boolean Ek() {
      return this.pC(false);
   }

   boolean pC(boolean var1) {
      boolean var2 = !this.UW.hasFrames();
      if (var2 && var1) {
         this.UO();
      }

      return var2;
   }

   boolean hK() {
      return this.Cu.isEmpty();
   }

   public void A(int var1) {
      btn var2 = (btn)this.PR;
      var2.pC(var1);
      var2.A();
      this.ah++;
      if (this.ah % 10000000 == 0) {
         pC(1, "Total iteration count: %d", this.ah);
      }
   }

   public int Er() {
      return this.PR.updatePC();
   }

   public IDImm FE() throws DexDecEvaluationException {
      IDImm var1 = this.PR.getRaisedException();
      if (var1 == null) {
         throw new DexDecEvaluationException(
            ckx.pC(new byte[]{-97, 61, 8, 21, 6, 23, 17, 1, 68, 65, 65, 83, 7, 27, 29, 23, 1, 68, 84, 28, 26, 29, 24, 22, 3, 14, 9, 68}, 1, 218)
         );
      } else {
         this.PR.setRaisedException(null);
         return var1;
      }
   }

   @Override
   public int setSubRoutineInvocationPolicy(int var1) {
      int var2 = this.OB;
      this.OB = var1;
      return var2;
   }

   @Override
   public int getSubRoutineInvocationPolicy() {
      return this.OB;
   }

   void Aj() throws DexDecEvaluationException {
      if ((this.OB & 1) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  0, 14, 30, 23, 29, 29, 71, 13, 12, 69, 75, 22, 93, 69, 17, 80, 87, 71, 73, 82, 92, 81, 94, 21, 95, 85, 45, 95, 19, 12, 25, 17, 73, 15, 11
               },
               2,
               9
            )
         );
      }
   }

   void EX() throws DexDecEvaluationException {
      if ((this.OB & 2) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{-104, 34, 15, 0, 1, 27, 84, 69, 29, 29, 6, 22, 1, 17, 69, 73, 7, 26, 17, 23, 28, 15, 13, 76, 83, 6, 23, 79, 95, 29, 26, 1, 29, 7, 11},
               1,
               219
            )
         );
      }
   }

   @Override
   public int setFieldAccessPolicy(int var1) {
      int var2 = this.pF;
      this.pF = var1;
      return var2;
   }

   @Override
   public int getFieldAccessPolicy() {
      return this.pF;
   }

   void LM() throws DexDecEvaluationException {
      if ((this.pF & 4) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  -119,
                  41,
                  29,
                  16,
                  11,
                  13,
                  0,
                  1,
                  11,
                  78,
                  84,
                  27,
                  79,
                  82,
                  23,
                  4,
                  5,
                  68,
                  73,
                  7,
                  26,
                  17,
                  23,
                  28,
                  15,
                  13,
                  76,
                  73,
                  7,
                  29,
                  7,
                  21,
                  15,
                  13,
                  6,
                  69,
                  70,
                  15,
                  12,
                  9,
                  8,
                  23
               },
               1,
               207
            )
         );
      }
   }

   void mv() throws DexDecEvaluationException {
      if ((this.pF & 8) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  40,
                  41,
                  29,
                  16,
                  11,
                  13,
                  0,
                  1,
                  11,
                  78,
                  84,
                  27,
                  79,
                  87,
                  5,
                  27,
                  29,
                  17,
                  69,
                  73,
                  7,
                  26,
                  17,
                  23,
                  28,
                  15,
                  13,
                  76,
                  73,
                  7,
                  29,
                  7,
                  21,
                  15,
                  13,
                  6,
                  69,
                  70,
                  15,
                  12,
                  9,
                  8,
                  23
               },
               1,
               110
            )
         );
      }
   }

   void sO() throws DexDecEvaluationException {
      if ((this.pF & 64) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  5,
                  0,
                  2,
                  27,
                  27,
                  13,
                  3,
                  13,
                  26,
                  0,
                  92,
                  12,
                  9,
                  82,
                  84,
                  88,
                  93,
                  19,
                  69,
                  78,
                  70,
                  85,
                  64,
                  91,
                  77,
                  76,
                  111,
                  1,
                  21,
                  2,
                  24,
                  12,
                  67,
                  65,
                  8,
                  13,
                  74,
                  3,
                  22,
                  83
               },
               2,
               210
            )
         );
      }
   }

   void os() throws DexDecEvaluationException {
      if ((this.pF & 128) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  81,
                  41,
                  29,
                  16,
                  11,
                  13,
                  0,
                  1,
                  11,
                  78,
                  84,
                  27,
                  79,
                  87,
                  5,
                  27,
                  29,
                  17,
                  69,
                  73,
                  7,
                  26,
                  17,
                  23,
                  28,
                  15,
                  13,
                  76,
                  83,
                  7,
                  21,
                  21,
                  29,
                  10,
                  67,
                  70,
                  15,
                  12,
                  9,
                  8,
                  23
               },
               1,
               23
            )
         );
      }
   }

   void Cu() throws DexDecEvaluationException {
      if ((this.pF & 1) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  56,
                  41,
                  29,
                  16,
                  11,
                  13,
                  0,
                  1,
                  11,
                  78,
                  84,
                  27,
                  79,
                  82,
                  23,
                  4,
                  5,
                  68,
                  69,
                  29,
                  12,
                  17,
                  23,
                  28,
                  15,
                  13,
                  76,
                  73,
                  7,
                  29,
                  7,
                  21,
                  15,
                  13,
                  6,
                  69,
                  70,
                  15,
                  12,
                  9,
                  8,
                  23
               },
               1,
               126
            )
         );
      }
   }

   void hZ() throws DexDecEvaluationException {
      if ((this.pF & 2) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  5,
                  0,
                  2,
                  27,
                  27,
                  13,
                  3,
                  13,
                  26,
                  0,
                  92,
                  12,
                  9,
                  87,
                  67,
                  80,
                  77,
                  86,
                  12,
                  69,
                  74,
                  68,
                  87,
                  71,
                  66,
                  65,
                  35,
                  82,
                  8,
                  13,
                  31,
                  17,
                  65,
                  15,
                  13,
                  1,
                  15,
                  9,
                  27,
                  69,
                  5,
                  16,
                  0
               },
               2,
               83
            )
         );
      }
   }

   void UW() throws DexDecEvaluationException {
      if ((this.pF & 16) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  5,
                  0,
                  2,
                  27,
                  27,
                  13,
                  3,
                  13,
                  26,
                  0,
                  92,
                  12,
                  9,
                  82,
                  84,
                  88,
                  93,
                  19,
                  73,
                  88,
                  70,
                  85,
                  64,
                  91,
                  77,
                  76,
                  111,
                  1,
                  21,
                  2,
                  24,
                  12,
                  67,
                  65,
                  8,
                  13,
                  74,
                  3,
                  22,
                  83
               },
               2,
               128
            )
         );
      }
   }

   void PR() throws DexDecEvaluationException {
      if ((this.pF & 32) == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  -124,
                  41,
                  29,
                  16,
                  11,
                  13,
                  0,
                  1,
                  11,
                  78,
                  84,
                  27,
                  79,
                  87,
                  5,
                  27,
                  29,
                  17,
                  69,
                  69,
                  29,
                  12,
                  17,
                  23,
                  28,
                  15,
                  13,
                  76,
                  83,
                  7,
                  21,
                  21,
                  29,
                  10,
                  67,
                  70,
                  15,
                  12,
                  9,
                  8,
                  23
               },
               1,
               194
            )
         );
      }
   }

   @Override
   public DEmuExternalPolicy setExternalPolicy(DEmuExternalPolicy var1) {
      DEmuExternalPolicy var2 = this.Bc;
      this.Bc = var1;
      return var2;
   }

   @Override
   public DEmuExternalPolicy getExternalPolicy() {
      return this.Bc;
   }

   @Override
   public boolean setStrictClassInit(boolean var1) {
      boolean var2 = this.OI;
      this.OI = var1;
      return var2;
   }

   @Override
   public boolean isStrictClassInit() {
      return this.OI;
   }

   public IDImm pC(String var1) throws DexDecEvaluationException {
      btj var2 = this.pC(var1, true);
      if (var2.pC()) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  0,
                  14,
                  30,
                  23,
                  29,
                  29,
                  71,
                  9,
                  24,
                  76,
                  71,
                  0,
                  72,
                  84,
                  84,
                  25,
                  88,
                  93,
                  12,
                  85,
                  92,
                  89,
                  92,
                  92,
                  88,
                  73,
                  46,
                  30,
                  8,
                  25,
                  9,
                  1,
                  0,
                  4,
                  22,
                  16,
                  74,
                  29,
                  28,
                  65,
                  5,
                  84,
                  28,
                  66,
                  11,
                  3,
                  5,
                  29
               },
               2,
               209
            )
         );
      } else {
         btk var3 = this.pC(var2);
         return this.registerObject(var3);
      }
   }

   public IDImm pC(String var1, Collection var2, DInvokeType var3) throws DexDecEvaluationException {
      String var4 = null;
      if (var3 == DInvokeType.NEW) {
         var4 = var1.substring(0, var1.indexOf("->"));
      }

      return this.pC(var1, var2, var3, var4);
   }

   public IDImm pC(String var1, Collection var2, DInvokeType var3, String var4) throws DexDecEvaluationException {
      if (this.Ab >= this.UO) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  6, 23, 19, 28, 23, 13, 2, 12, 84, 77, 73, 27, 9, 65, 93, 85, 86, 68, 73, 68, 18, 89, 92, 67, 67, 67, 46, 6, 8, 12, 2, 69, 68, 4, 30, 16, 71
               },
               2,
               182
            )
         );
      } else {
         this.Ab++;

         IDImm var5;
         try {
            var5 = this.A(var1, var2, var3, var4);
         } finally {
            this.Ab--;
         }

         return var5;
      }
   }

   private IDImm A(String var1, Collection var2, DInvokeType var3, String var4) throws DexDecEvaluationException {
      boolean var5;
      switch (var3) {
         case NEW:
            Assert.a(var4 != null);
         case DIRECT:
         case STATIC:
            var5 = false;
            break;
         case SUPER:
         case VIRTUAL:
         case INTERFACE:
            var5 = true;
            break;
         default:
            throw new DexDecEvaluationException(
               ckx.pC(
                     new byte[]{109, 34, 15, 0, 1, 27, 84, 69, 19, 23, 13, 25, 20, 21, 17, 69, 73, 7, 24, 25, 12, 2, 21, 29, 6, 1, 78, 84, 13, 9, 21, 95, 26},
                     1,
                     46
                  )
                  + var3
            );
      }

      JvmMethodSig var6 = JvmMethodSig.parse(var1);
      String var7 = var6.mname;
      int var8 = var6.partypes.size();
      boolean var9 = var7.equals("<init>");
      Assert.a(var4 == null || var9);
      if (this.OB == 0) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  -121, 39, 6, 27, 10, 23, 84, 78, 1, 79, 83, 6, 23, 79, 95, 29, 26, 1, 29, 7, 11, 69, 69, 29, 29, 6, 22, 1, 29, 6, 1, 78, 80, 31, 3, 5, 10, 26
               },
               1,
               212
            )
         );
      } else {
         if (var2 == null) {
            var2 = Collections.emptyList();
         }

         ArrayList var10 = new ArrayList(var2.size());

         for (IDExpression var12 : var2) {
            var10.add(var12.evaluate(this));
         }

         IDexMethod var28 = this.WR.getMethod(var1);
         boolean var29 = false;
         String var13 = null;
         IDexMethod var14 = null;
         boolean var15 = false;
         if (var3 == DInvokeType.NEW && !var6.csig.equals(var4)) {
            boolean var16 = this.WR.getClass(var4) == null;
            if (var16) {
               throw new DexDecEvaluationException("Cannot do alloc(extT1) + <init>(extT2)");
            }

            boolean var17 = var28 == null || !var28.isInternal();
            if (var17) {
               if (!var1.equals("Ljava/lang/Object;-><init>()V")) {
                  throw new DexDecEvaluationException("Cannot do alloc(intT1) + <init>(extT2)");
               }

               var15 = true;
            }
         }

         if (var6.csig.startsWith("[")) {
            Couple var30 = JvmTypeSig.parseArrayType(var6.csig);
            btj var34 = this.pC((String)var30.getFirst(), true);
            if (var34.isInternal()) {
               var6.csig = Strings.generate('[', (Integer)var30.getSecond()) + JvmUtil.generateTypeSig(var34.wS());
               var13 = var6.generate();
               var29 = true;
            }
         } else if (var3 == DInvokeType.STATIC) {
            this.pC(var6.csig, true);
         }

         if (!var29 && !var15) {
            if (!var5) {
               var14 = var28;
               var29 = var28 == null || !var28.isInternal();
               if (var29 && var3 == DInvokeType.STATIC) {
                  var13 = this.E(var1);
                  var14 = var13 == null ? null : this.WR.getMethod(var13);
                  var29 = var14 == null || !var14.isInternal();
               }
            } else {
               Object var31 = this.getObject((IDImm)var10.get(0));
               if (var31 == null) {
                  if (this.sO) {
                     throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
                  }

                  List var35 = this.getTypeAdapter().getParentTypes(JvmMethodSig.parse(var1).csig);
                  var29 = false;
                  if (var35 != null && var35.size() == 1 && ((String)var35.get(0)).equals("Ljava/lang/Object;")) {
                     var13 = var1;
                     var14 = var1 == null ? null : this.WR.getMethod(var1);
                     var29 = var14 == null || !var14.isInternal();
                  }

                  if (var29) {
                     throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
                  }
               } else if (var3 == DInvokeType.SUPER) {
                  if (var31 instanceof btk var36) {
                     var13 = var36.pC(var1, this.getCurrentFrame().getMethodSignature());
                     var14 = var13 == null ? null : this.WR.getMethod(var13);
                     var29 = var14 == null || !var14.isInternal();
                  } else {
                     var29 = true;
                  }
               } else if (var31 instanceof btk var37) {
                  var13 = var37.pC(var1, var3 == DInvokeType.INTERFACE);
                  var14 = var13 == null ? null : this.WR.getMethod(var13);
                  var29 = var14 == null || !var14.isInternal();
               } else {
                  var29 = true;
               }
            }
         }

         if (!var29) {
            this.Aj();
            ArrayList var33 = new ArrayList(var10);
            if (var4 != null) {
               btj var40 = this.pC(var4, true);
               btk var44 = this.pC(var40);
               IDImm var47 = this.registerObject(var44);
               if (!var15) {
                  var33.add(0, var47);
                  this.A(var28, var33);
               }

               return var47;
            } else if (var14 == null || var14.getData() == null) {
               throw new DexDecEvaluationException(
                  ckx.pC(
                        new byte[]{
                           14,
                           10,
                           4,
                           17,
                           29,
                           13,
                           71,
                           11,
                           27,
                           68,
                           77,
                           67,
                           71,
                           79,
                           69,
                           25,
                           95,
                           92,
                           89,
                           78,
                           86,
                           28,
                           18,
                           86,
                           77,
                           78,
                           33,
                           29,
                           21,
                           67,
                           9,
                           19,
                           65,
                           13,
                           27,
                           5,
                           91,
                           10,
                           72,
                           0
                        },
                        2,
                        3
                     )
                     + var1
               );
            } else if (!var14.getData().isNative()) {
               return this.A(var14, var33);
            } else if (!this.isNativeCodeEmulatorEnabled()) {
               throw new DexDecEvaluationException(
                  ckx.pC(
                     new byte[]{
                        6,
                        2,
                        5,
                        21,
                        19,
                        29,
                        14,
                        7,
                        26,
                        0,
                        71,
                        5,
                        9,
                        78,
                        80,
                        77,
                        80,
                        69,
                        73,
                        0,
                        95,
                        85,
                        70,
                        93,
                        67,
                        68,
                        60,
                        82,
                        8,
                        16,
                        76,
                        11,
                        79,
                        21,
                        78,
                        5,
                        67,
                        3,
                        29,
                        87,
                        12,
                        16
                     },
                     2,
                     4
                  )
               );
            } else {
               btp.Sv var39 = null;
               if (!this.Kq.isEmpty()) {
                  var39 = (btp.Sv)new ArrayList(this.Kq).get(this.Kq.size() - 1);
               }

               this.LM++;
               return this.oT().pC(var14, var33, var39);
            }
         } else {
            this.EX();
            ArrayList var32 = new ArrayList(var10);
            if (var13 == null) {
               var13 = var1;
            }

            btk var38 = null;
            if (var9 && var10.size() != var8) {
               Assert.a(var10.size() == var8 + 1);
               IDImm var41 = (IDImm)var32.remove(0);
               Object var45 = this.getObject(var41);
               Assert.a(var45 instanceof btk);
               var38 = (btk)var45;
            } else if (var3 != DInvokeType.STATIC && var3 != DInvokeType.NEW && var32.size() > 0) {
               IDImm var18 = (IDImm)var32.get(0);
               Object var19 = this.getObject(var18);
               if (var19 instanceof btk) {
                  var32.set(0, ((btk)var19).pC);
               }
            }

            if (var38 == null && this.go) {
               IDImm[] var42 = new IDImm[1];
               if (this.pC(var13, var32, var42)) {
                  return var42[0];
               }
            }

            IDImm var43 = null;
            boolean var46 = false;
            IDMethodExecutionHelper var20 = (IDMethodExecutionHelper)this.oT.get(var13);
            if (var20 != null) {
               try {
                  var43 = var20.simulateExecution(this, var32);
                  if (var43 != null) {
                     var46 = true;
                  }
               } catch (Exception var26) {
                  throw new DexDecEvalCodeThrownException(this.registerObject(var26));
               }
            }

            if (!var46) {
               var43 = this.gj.pC(var13, var32, null);
               if (var43 != null) {
                  var46 = true;
               }
            }

            if (!var46) {
               this.gp();
               btj.Av var21 = var38 == null ? null : var38.A().UT();

               try {
                  var43 = this.hK.pC(var3, var13, var32, var21);
               } catch (DexDecEvalSandboxExecutionException var27) {
                  Throwable var23 = var27.getCause();
                  Object var24;
                  if (var23 instanceof InvocationTargetException) {
                     var24 = var23.getCause();
                     if (this.rl >= 1) {
                        int var50 = this.rl;

                        while (var50-- > 0) {
                           var24 = new InvocationTargetException((Throwable)var24);
                        }
                     }
                  } else {
                     var24 = var23;
                     if (this.rl >= 1) {
                        int var25 = this.rl;

                        while (var25-- > 0) {
                           var24 = new InvocationTargetException((Throwable)var24);
                        }
                     }
                  }

                  throw new DexDecEvalCodeThrownException(this.registerObject(var24));
               }
            }

            if (!this.mv) {
               String var48 = null;
               boolean var22 = false;
               switch (ckx.kS(var13)) {
                  case -2088301382:
                     var48 = this.getStringObject((IDImm)var32.get(1));
                     var22 = true;
                     break;
                  case -1936802804:
                     var48 = this.getStringObject((IDImm)var32.get(0));
                     var22 = true;
                     break;
                  case -1280323317:
                     var48 = this.getStringObject((IDImm)var32.get(1));
                     break;
                  case -452023474:
                     var48 = this.getStringObject((IDImm)var32.get(0));
               }

               if (var48 != null) {
                  pC(1, "NATIVE: Load Library: %s (path=%b)", var48, var22);
                  btp.Sv var49 = new btp.Sv(var48, var22);
                  this.LM++;
                  this.oT().pC(var49);
               }
            }

            if (var38 != null) {
               this.pC(var38, var43);
               var43 = this.registerObject(var38);
            }

            return var43;
         }
      }
   }

   boolean pC(String var1, List var2, IDImm[] var3) throws DexDecEvaluationException {
      if (var2.isEmpty()) {
         return false;
      } else if (var1.equals(Xs) || var1.equals(VD)) {
         Couple var39 = null;
         if (var1.equals(VD)) {
            Object var46 = this.getObject((IDImm)var2.get(0), true);
            if (var46 instanceof Class) {
               try {
                  Constructor var42 = ((Class)var46).getConstructor();
                  var39 = this.pC(var42, null);
               } catch (Exception var34) {
               }
            }
         } else {
            var39 = this.pC(this.getObject((IDImm)var2.get(0), true), null);
         }

         if (var39 == null) {
            return false;
         } else {
            String var43 = (String)var39.getFirst();
            pC(2, "Resolved unreflected: new: %s", var43);
            JvmMethodSig var47 = JvmMethodSig.parse(var43);
            ArrayList var50 = new ArrayList();

            try {
               int var53 = var47.partypes.size();
               Object[] var57 = (Object[])this.getArrayObject((IDImm)var2.get(1));
               if (var57 == null) {
                  if (var53 != 0) {
                     return false;
                  }
               } else {
                  if (var53 != var57.length) {
                     return false;
                  }

                  int var60 = 0;

                  for (Object var75 : var57) {
                     String var77 = (String)var47.partypes.get(var60);
                     if (var77.length() == 1) {
                        var50.add(this.pC(var77, var75));
                     } else {
                        var50.add(this.pC(var75, true));
                     }

                     var60++;
                  }
               }
            } catch (Exception var36) {
               return false;
            }

            this.rl++;

            IDImm var54;
            try {
               var54 = this.pC(var43, var50, DInvokeType.NEW, var47.csig);
            } finally {
               this.rl--;
            }

            var3[0] = var54;
            return true;
         }
      } else if (var1.equals(KV)) {
         Object var38 = this.getObject((IDImm)var2.get(0), true);
         Object var41 = this.getObject((IDImm)var2.get(1), true);
         Couple var45 = this.pC(var38, var41);
         if (var45 == null) {
            return false;
         } else {
            String var49 = (String)var45.getFirst();
            Boolean var52 = (Boolean)var45.getSecond();
            pC(2, "Resolved unreflected: invoke: %s", var49);
            JvmMethodSig var56 = JvmMethodSig.parse(var49);
            ArrayList var63 = new ArrayList();

            DInvokeType var59;
            try {
               Object var67 = this.getObject((IDImm)var2.get(1), true);
               if (var52 != null) {
                  if (var52) {
                     var67 = null;
                  } else if (var67 == null) {
                     return false;
                  }
               }

               if (var67 == null) {
                  var59 = DInvokeType.STATIC;
               } else {
                  var59 = DInvokeType.VIRTUAL;
                  var63.add((IDExpression)var2.get(1));
               }

               int var72 = var56.partypes.size();
               Object[] var74 = (Object[])this.getArrayObject((IDImm)var2.get(2));
               if (var74 == null) {
                  if (var72 != 0) {
                     return false;
                  }
               } else {
                  if (var72 != var74.length) {
                     return false;
                  }

                  int var76 = 0;

                  for (Object var19 : var74) {
                     String var20 = (String)var56.partypes.get(var76);
                     if (var20.length() == 1) {
                        var63.add(this.pC(var20, var19));
                     } else {
                        var63.add(this.pC(var19, true));
                     }

                     var76++;
                  }
               }
            } catch (Exception var35) {
               return false;
            }

            this.rl++;

            IDImm var68;
            try {
               var68 = this.pC(var49, var63, var59, null);
            } finally {
               this.rl--;
            }

            if (var56.rettype.length() == 1) {
               if (var56.rettype.equals("V")) {
                  var68 = this.fI.createNull();
               } else {
                  var68 = this.A(var68, var56.rettype.charAt(0));
               }
            }

            var3[0] = var68;
            return true;
         }
      } else if (wQ.contains(var1)) {
         Object var37 = this.getObject((IDImm)var2.get(0), true);
         Object var40 = this.getObject((IDImm)var2.get(1), true);
         Couple var44 = this.pC(var37, var40);
         if (var44 == null) {
            return false;
         } else {
            String var48 = (String)var44.getFirst();
            Boolean var51 = (Boolean)var44.getSecond();
            pC(2, "Resolved unreflected: get: %s", var48);
            JvmFieldSig var55 = JvmFieldSig.parse(var48);
            Object var58 = this.getObject((IDImm)var2.get(1), true);
            if (var51 != null) {
               if (var51) {
                  var58 = null;
               } else if (var58 == null) {
                  return false;
               }
            }

            IDImm var62;
            if (var58 == null) {
               var62 = this.getStaticField(var48);
            } else {
               var62 = this.getInstanceField(var48, (IDImm)var2.get(1));
            }

            if (var1.equals(FK)) {
               if (var55.ftype.length() == 1) {
                  var62 = this.A(var62, var55.ftype.charAt(0));
               }
            } else {
               char var66 = var1.charAt(var1.length() - 1);
               char var71 = var55.ftype.charAt(0);
               if (var71 == '[' || var71 == 'L') {
                  return false;
               }

               if (var66 != var71) {
                  if (!bpl.pC(var71, var66)) {
                     return false;
                  }

                  var62 = bpl.pC(this.fI, var62, var71, var66);
               }
            }

            var3[0] = var62;
            return true;
         }
      } else if (!PZ.contains(var1)) {
         return false;
      } else {
         Object var4 = this.getObject((IDImm)var2.get(0), true);
         Object var5 = this.getObject((IDImm)var2.get(1), true);
         Couple var6 = this.pC(var4, var5);
         if (var6 == null) {
            return false;
         } else {
            String var7 = (String)var6.getFirst();
            Boolean var8 = (Boolean)var6.getSecond();
            pC(2, "Resolved unreflected: set: %s", var7);
            JvmFieldSig var9 = JvmFieldSig.parse(var7);
            Object var10 = this.getObject((IDImm)var2.get(1), true);
            if (var8 != null) {
               if (var8) {
                  var10 = null;
               } else if (var10 == null) {
                  return false;
               }
            }

            IDImm var11 = (IDImm)var2.get(2);
            if (var1.equals(Bi)) {
               char var65 = var9.ftype.charAt(0);
               String var70 = null;
               Object var14 = this.getObject((IDImm)var2.get(2));
               if (var14 instanceof btk) {
                  var70 = ((btk)var14).A().getFullName();
               } else if (var14 != null) {
                  var70 = JvmUtil.generateTypeSig(var14.getClass());
               }

               if (var65 != '[' && var65 != 'L') {
                  if (!bpl.pC(var70)) {
                     return false;
                  }

                  char var15 = bpl.A(var70);
                  var11 = this.pC(var11, '\u0000');
                  if (!bpl.pC(var15, var65)) {
                     return false;
                  }

                  var11 = bpl.pC(this.fI, var11, var15, var65);
               } else {
                  if (var70 == null || var11.maybeNullRef()) {
                     return true;
                  }

                  if (!bgq.pC(this.DQ, var70, var9.ftype)) {
                     return false;
                  }
               }
            } else {
               char var12 = var1.charAt(var1.length() - 3);
               char var13 = var9.ftype.charAt(0);
               if (var13 == '[' || var13 == 'L') {
                  return false;
               }

               if (var12 != var13) {
                  if (!bpl.pC(var12, var13)) {
                     return false;
                  }

                  var11 = bpl.pC(this.fI, var11, var12, var13);
               }
            }

            if (var10 == null) {
               this.setStaticField(var7, var11);
            } else {
               this.setInstanceField(var7, (IDImm)var2.get(1), var11);
            }

            var3[0] = null;
            return true;
         }
      }
   }

   private Couple pC(Object var1, Object var2) {
      if (var1 instanceof bth) {
         String var8 = ((bth)var1).wS();
         Integer var10 = ((bth)var1).sY();
         Boolean var5 = var10 == null ? null : Modifier.isStatic(var10);
         return new Couple(var8, var5);
      } else if (var1 instanceof Constructor) {
         String var7 = JvmUtil.generateConstructorSig((Constructor)var1);
         return new Couple(var7, false);
      } else if (var1 instanceof Method) {
         boolean var6 = Modifier.isStatic(((Method)var1).getModifiers());
         if (!var6 && var2 == null) {
            return null;
         } else {
            String var9 = JvmUtil.generateMethodSig((Method)var1);
            return new Couple(var9, var6);
         }
      } else if (var1 instanceof Field) {
         boolean var3 = Modifier.isStatic(((Field)var1).getModifiers());
         if (!var3 && var2 == null) {
            return null;
         } else {
            String var4 = JvmUtil.generateFieldSig((Field)var1);
            return new Couple(var4, var3);
         }
      } else {
         throw new RuntimeException(
            ckx.pC(new byte[]{0, 14, 30, 23, 29, 29, 71, 26, 17, 83, 71, 15, 95, 69, 17, 86, 91, 89, 73, 67, 70, 16, 93, 83, 12, 84, 54, 2, 4, 89, 76}, 2, 116)
               + (var1 == null ? null : var1.getClass().getName())
         );
      }
   }

   IDImm pC(IDImm var1, char var2) throws DexDecEvaluationException {
      if (var2 == 0) {
         String var3 = var1.getType().getSignature();
         var2 = JavaUtil.primitiveToLetter(JavaUtil.wrapperToPrimitive(var3)).charAt(0);
      }

      switch (var2) {
         case 'B':
            return this.fI.createConstant(((Byte)this.getObject(var1, true)).byteValue(), this.NS.getByte());
         case 'C':
            return this.fI.createConstant(((Character)this.getObject(var1, true)).charValue(), this.NS.getChar());
         case 'D':
            return this.fI.createConstant(Double.doubleToLongBits((Double)this.getObject(var1, true)), this.NS.getDouble());
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         default:
            throw new RuntimeException(ckx.pC(new byte[]{54, 1, 7, 11, 19, 25, 51, 7, 36, 82, 65, 14, 64, 84, 88, 79, 92, 19, 74, 65, 91, 92, 87, 81}, 2, 37));
         case 'F':
            return this.fI.createConstant(Float.floatToIntBits((Float)this.getObject(var1, true)), this.NS.getFloat());
         case 'I':
            return this.fI.createConstant(((Integer)this.getObject(var1, true)).intValue(), this.NS.getInt());
         case 'J':
            return this.fI.createConstant((Long)this.getObject(var1, true), this.NS.getLong());
         case 'S':
            return this.fI.createConstant(((Short)this.getObject(var1, true)).shortValue(), this.NS.getShort());
         case 'Z':
            return this.fI.createConstant(this.getObject(var1, true) ? 1L : 0L, this.NS.getBoolean());
      }
   }

   IDImm A(IDImm var1, char var2) throws DexDecEvaluationException {
      if (var2 == 0) {
         String var3 = var1.getType().getSignature();
         var2 = var3.charAt(0);
      }

      switch (var2) {
         case 'B':
            return this.registerObject((byte)var1.getRawValue());
         case 'C':
            return this.registerObject((char)var1.getRawValue());
         case 'D':
            return this.registerObject(var1.toDouble());
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         default:
            throw new RuntimeException(ckx.pC(new byte[]{120, 27, 25, 5, 19, 17, 36, 59, 63, 34, 27, 4, 4, 29, 29, 31, 19, 69, 70, 7, 8, 5, 9, 1}, 1, 13));
         case 'F':
            return this.registerObject(var1.toFloat());
         case 'I':
            return this.registerObject((int)var1.getRawValue());
         case 'J':
            return this.registerObject(var1.getRawValue());
         case 'S':
            return this.registerObject((short)var1.getRawValue());
         case 'Z':
            return this.registerObject(var1.getRawValue() != 0L);
      }
   }

   public boolean A(String var1) {
      boolean var2;
      if (this.Bc == null) {
         var2 = true;
      } else {
         var2 = this.Bc.canExecute(var1);
      }

      pC(4, "[TBX] EXTERNAL METHOD: %s -> ALLOWED:%b", var1, var2);
      return var2;
   }

   public boolean kS(String var1) {
      return this.Bc == null ? true : this.Bc.canAccess(var1);
   }

   public boolean wS(String var1) {
      return this.Bc == null ? true : this.Bc.canAccess(var1);
   }

   public Object pC(String var1, IDImm var2) throws DexDecEvaluationException {
      char var3 = var1.charAt(0);
      boolean var4 = var3 == '[' || var3 == 'L';
      Object var5 = null;
      if (var4) {
         if (var2.isString()) {
            return var2.getStringValue(this.fI);
         }

         if (var2.getObjectReferenceId() == 0) {
            return null;
         }

         var5 = this.getObject(var2, true);
      } else {
         switch (var3) {
            case 'B':
               return (byte)var2.toLong();
            case 'C':
               return (char)var2.toLong();
            case 'D':
               return var2.toDouble();
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
               break;
            case 'F':
               return var2.toFloat();
            case 'I':
               return (int)var2.toLong();
            case 'J':
               return var2.toLong();
            case 'S':
               return (short)var2.toLong();
            case 'V':
               return null;
            case 'Z':
               return var2.toLong() != 0L;
         }
      }

      if (var5 == null) {
         throw new DexDecEvaluationException(
            ckx.pC(new byte[]{0, 14, 30, 23, 29, 29, 71, 26, 17, 83, 71, 15, 95, 69, 17, 77, 86, 19, 77, 78, 18, 95, 80, 95, 73, 67, 59, 72, 65}, 2, 234)
               + var2
         );
      } else {
         return var5;
      }
   }

   public IDImm pC(String var1, Object var2) {
      if (var2 instanceof IDImm) {
         throw new RuntimeException(
            ckx.pC(new byte[]{0, 14, 30, 23, 29, 29, 71, 26, 17, 71, 65, 16, 93, 69, 67, 25, 112, 97, 12, 99, 93, 94, 65, 65, 77, 78, 59}, 2, 28)
         );
      } else {
         if (var1.length() == 1) {
            switch (var1) {
               case "Z":
                  return this.fI.createConstant((Boolean)var2 ? 1L : 0L, this.NS.getBoolean());
               case "B":
                  return this.fI.createConstant(((Byte)var2).byteValue(), this.NS.getByte());
               case "C":
                  return this.fI.createConstant(((Character)var2).charValue(), this.NS.getChar());
               case "S":
                  return this.fI.createConstant(((Short)var2).shortValue() & 65535L, this.NS.getShort());
               case "I":
                  return this.fI.createConstant(((Integer)var2).intValue(), this.NS.getInt());
               case "J":
                  return this.fI.createConstant((Long)var2, this.NS.getLong());
               case "F":
                  return this.fI.createConstant(Float.floatToIntBits((Float)var2) & 4294967295L, this.NS.getFloat());
               case "D":
                  return this.fI.createConstant(Double.doubleToLongBits((Double)var2), this.NS.getDouble());
               case "V":
                  return null;
               case "L":
               case "[":
                  break;
               default:
                  throw new RuntimeException(
                     ckx.pC(
                           new byte[]{
                              16,
                              14,
                              30,
                              29,
                              16,
                              6,
                              31,
                              72,
                              6,
                              69,
                              92,
                              22,
                              91,
                              78,
                              17,
                              77,
                              64,
                              67,
                              73,
                              0,
                              81,
                              95,
                              92,
                              67,
                              73,
                              82,
                              60,
                              27,
                              14,
                              13,
                              76,
                              3,
                              79,
                              19,
                              84,
                              68
                           },
                           2,
                           135
                        )
                        + var1
                  );
            }
         }

         return this.pC(var2, true);
      }
   }

   @Override
   public IDImm registerObject(Object var1) {
      return this.pC(var1, false);
   }

   public IDImm pC(Object var1, boolean var2) {
      if (var1 == null) {
         return this.fI.createNull();
      } else {
         synchronized (this.RW) {
            if (var2) {
               if (bvo.A(var1)) {
                  var1 = this.A(var1);
                  Assert.a(var1 != null);
               }

               if (bvo.kS(var1)) {
                  var1 = this.A((Class)var1);
                  Assert.a(var1 != null);
               }
            }

            Integer var4 = (Integer)this.ck.get(var1);
            if (var4 == null) {
               if (this.Pe < 0) {
                  throw new RuntimeException("object id overflow");
               }

               var4 = this.Pe;
               this.Pe++;
               this.RW.put(var4, var1);
               this.ck.put(var1, var4);
            }

            return this.fI.createConstant(var4.intValue(), this.pC(var1.getClass()));
         }
      }
   }

   @Override
   public Object releaseObject(IDImm var1) throws DexDecEvaluationException {
      synchronized (this.RW) {
         Object var3 = this.RW.remove(var1.getObjectReferenceId());
         if (var3 != null) {
            this.ck.remove(var3);
         }

         return var3;
      }
   }

   @Override
   public Object getObject(IDImm var1) throws DexDecEvaluationException {
      return this.getObject(var1, false);
   }

   @Override
   public Object getObject(IDImm var1, boolean var2) throws DexDecEvaluationException {
      if (var1.isString()) {
         return var1.getStringValue(this.fI);
      } else if (var1.getObjectReferenceId() == 0) {
         return null;
      } else {
         Object var3 = this.RW.get(var1.getObjectReferenceId());
         if (var3 == null) {
            throw new DexDecEvaluationException(
               ckx.pC(
                  new byte[]{
                     17,
                     10,
                     22,
                     28,
                     0,
                     12,
                     9,
                     11,
                     17,
                     0,
                     75,
                     2,
                     71,
                     78,
                     94,
                     77,
                     25,
                     81,
                     73,
                     0,
                     87,
                     70,
                     83,
                     89,
                     89,
                     65,
                     59,
                     23,
                     5,
                     67,
                     13,
                     22,
                     0,
                     0,
                     0,
                     68,
                     64,
                     13,
                     24,
                     69,
                     10,
                     0
                  },
                  2,
                  21
               )
            );
         } else {
            if (var2) {
               if (var3 instanceof btk) {
                  var3 = this.getObject(((btk)var3).pC, true);
               }

               if (var3 instanceof btj) {
                  var3 = ((btj)var3).wS();
               }
            }

            return var3;
         }
      }
   }

   @Override
   public Object getObject(int var1) throws DexDecEvaluationException {
      if (var1 == 0) {
         return null;
      } else {
         Object var2 = this.RW.get(var1);
         if (var2 == null) {
            throw new DexDecEvaluationException(ckx.pC(new byte[]{13, 0, 80, 22, 16, 3, 2, 11, 0, 0, 95, 10, 93, 72, 17, 75, 92, 85, 69, 68, 18}, 2, 90) + var1);
         } else {
            return var2;
         }
      }
   }

   @Override
   public int getObjectClassId(int var1) throws DexDecEvaluationException {
      Object var2 = this.getObject(var1);
      if (var2 == null) {
         return 0;
      } else {
         String var3;
         if (var2 instanceof btk) {
            btj var4 = ((btk)var2).A();
            var3 = var4.getFullName();
         } else {
            var3 = JvmUtil.generateTypeSig(var2.getClass());
         }

         IDImm var5 = this.getClassReference(var3);
         return var5.getObjectReferenceId();
      }
   }

   public btj kS(int var1) throws DexDecEvaluationException {
      int var2 = this.getObjectClassId(var1);
      return var2 == 0 ? null : (btj)this.RW.get(var2);
   }

   @Override
   public String getStringObject(IDImm var1) throws DexDecEvaluationException {
      Object var2 = this.getObject(var1);
      if (var2 != null && var2.getClass() != String.class) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  17,
                  10,
                  22,
                  28,
                  0,
                  12,
                  9,
                  11,
                  17,
                  0,
                  75,
                  2,
                  71,
                  78,
                  94,
                  77,
                  25,
                  81,
                  73,
                  0,
                  87,
                  70,
                  83,
                  89,
                  89,
                  65,
                  59,
                  23,
                  5,
                  67,
                  13,
                  22,
                  0,
                  0,
                  78,
                  23,
                  91,
                  29,
                  27,
                  78,
                  14
               },
               2,
               145
            )
         );
      } else {
         return (String)var2;
      }
   }

   public IJavaType pC(Class var1) {
      if (var1.isPrimitive()) {
         return this.NS.primitiveNameToType(var1.getName());
      } else if (var1.isArray()) {
         String var3 = var1.getName().replace('.', '/');
         return this.NS.parseType(var3);
      } else {
         String var2 = "L" + var1.getName().replace('.', '/') + ";";
         return this.NS.parseType(var2);
      }
   }

   @Override
   public Object getArrayObject(IDImm var1) throws DexDecEvaluationException {
      if (var1.getObjectReferenceId() == 0) {
         return null;
      } else {
         Object var2 = this.RW.get(var1.getObjectReferenceId());
         if (var2 != null && var2.getClass().isArray()) {
            return var2;
         } else {
            throw new DexDecEvaluationException(
               ckx.pC(
                  new byte[]{
                     17,
                     10,
                     22,
                     28,
                     0,
                     12,
                     9,
                     11,
                     17,
                     0,
                     75,
                     2,
                     71,
                     78,
                     94,
                     77,
                     25,
                     81,
                     73,
                     0,
                     87,
                     70,
                     83,
                     89,
                     89,
                     65,
                     59,
                     23,
                     5,
                     67,
                     13,
                     22,
                     0,
                     0,
                     0,
                     68,
                     78,
                     29,
                     0,
                     65,
                     16
                  },
                  2,
                  26
               )
            );
         }
      }
   }

   @Override
   public int getArrayObjectLength(IDImm var1) throws DexDecEvaluationException {
      Object var2 = this.getArrayObject(var1);
      if (var2 == null) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else if (!var2.getClass().isArray()) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  (byte)17,
                  (byte)10,
                  (byte)22,
                  (byte)28,
                  (byte)0,
                  (byte)12,
                  (byte)9,
                  (byte)11,
                  (byte)17,
                  (byte)0,
                  (byte)75,
                  (byte)2,
                  (byte)71,
                  (byte)78,
                  (byte)94,
                  (byte)77,
                  (byte)25,
                  (byte)81,
                  (byte)73,
                  (byte)0,
                  (byte)87,
                  (byte)70,
                  (byte)83,
                  (byte)89,
                  (byte)89,
                  (byte)65,
                  (byte)59,
                  (byte)23,
                  (byte)5,
                  (byte)67,
                  (byte)13,
                  (byte)22,
                  (byte)0,
                  (byte)0,
                  (byte)0,
                  (byte)68,
                  (byte)78,
                  (byte)29,
                  (byte)0,
                  (byte)65,
                  (byte)16
               },
               2,
               183
            )
         );
      } else {
         return Array.getLength(var2);
      }
   }

   @Override
   public IDImm getArrayElement(IDImm var1, int var2) throws DexDecEvaluationException {
      Object var3 = this.getArrayObject(var1);
      if (var3 == null) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else if (var2 >= 0 && var2 < Array.getLength(var3)) {
         Class var4 = var3.getClass().getComponentType();
         if (var4.isPrimitive()) {
            String var7 = var4.getName();

            return this.fI.createConstant(switch (var7) {
               case "boolean" -> Array.getBoolean(var3, var2) ? 1L : 0L;
               case "byte" -> Array.getByte(var3, var2);
               case "char" -> Array.getChar(var3, var2);
               case "short" -> Array.getShort(var3, var2) & 65535L;
               case "int" -> Array.getInt(var3, var2);
               case "long" -> Array.getLong(var3, var2);
               case "float" -> Float.floatToIntBits(Array.getFloat(var3, var2)) & 4294967295L;
               case "double" -> Double.doubleToLongBits(Array.getDouble(var3, var2));
               default -> throw new RuntimeException();
            }, this.pC(var4));
         } else {
            Object var5 = Array.get(var3, var2);
            if (var5 != null && bvo.A(var5)) {
               var5 = this.A(var5);
               Assert.a(var5 != null);
            }

            return this.registerObject(var5);
         }
      } else {
         throw new DexDecEvalCodeThrownException(this.registerObject(new ArrayIndexOutOfBoundsException()));
      }
   }

   @Override
   public IDImm cloneArray(IDImm var1) throws DexDecEvaluationException {
      Object var2 = this.getArrayObject(var1);
      if (var2 == null) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else {
         Class var4 = var2.getClass().getComponentType();
         Object var3;
         if (var4.isPrimitive()) {
            String var5 = var4.getName();
            switch (var5) {
               case "boolean":
                  var3 = ((boolean[])var2).clone();
                  break;
               case "byte":
                  var3 = ((byte[])var2).clone();
                  break;
               case "char":
                  var3 = ((char[])var2).clone();
                  break;
               case "short":
                  var3 = ((short[])var2).clone();
                  break;
               case "int":
                  var3 = ((int[])var2).clone();
                  break;
               case "long":
                  var3 = ((long[])var2).clone();
                  break;
               case "float":
                  var3 = ((float[])var2).clone();
                  break;
               case "double":
                  var3 = ((double[])var2).clone();
                  break;
               default:
                  throw new RuntimeException();
            }
         } else {
            var3 = ((Object[])var2).clone();
         }

         return this.registerObject(var3);
      }
   }

   @Override
   public void setArrayElement(IDImm var1, int var2, IDImm var3) throws DexDecEvaluationException {
      Object var4 = this.getArrayObject(var1);
      if (var4 == null) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else if (var2 >= 0 && var2 < Array.getLength(var4)) {
         Class var5 = var4.getClass().getComponentType();
         if (var5.isPrimitive()) {
            String var6 = var5.getName();
            switch (var6) {
               case "boolean":
                  Array.setBoolean(var4, var2, !var3.isZero());
                  break;
               case "byte":
                  Array.setByte(var4, var2, (byte)var3.getRawValue());
                  break;
               case "char":
                  Array.setChar(var4, var2, (char)var3.getRawValue());
                  break;
               case "short":
                  Array.setShort(var4, var2, (short)var3.getRawValue());
                  break;
               case "int":
                  Array.setInt(var4, var2, (int)var3.getRawValue());
                  break;
               case "long":
                  Array.setLong(var4, var2, var3.getRawValue());
                  break;
               case "float":
                  Array.setFloat(var4, var2, Float.intBitsToFloat((int)var3.getRawValue()));
                  break;
               case "double":
                  Array.setDouble(var4, var2, Double.longBitsToDouble(var3.getRawValue()));
                  break;
               default:
                  throw new RuntimeException();
            }
         } else {
            try {
               Array.set(var4, var2, this.getObject(var3, true));
            } catch (IllegalArgumentException var8) {
               throw new DexDecEvaluationException(
                  ckx.pC(
                     new byte[]{
                        0,
                        14,
                        30,
                        23,
                        29,
                        29,
                        71,
                        27,
                        17,
                        84,
                        8,
                        2,
                        91,
                        82,
                        80,
                        64,
                        25,
                        86,
                        64,
                        69,
                        95,
                        85,
                        92,
                        65,
                        0,
                        0,
                        59,
                        11,
                        17,
                        6,
                        31,
                        69,
                        77,
                        8,
                        29,
                        9,
                        78,
                        27,
                        17,
                        72,
                        12,
                        16
                     },
                     2,
                     42
                  )
               );
            }
         }
      } else {
         throw new DexDecEvalCodeThrownException(this.registerObject(new ArrayIndexOutOfBoundsException()));
      }
   }

   @Override
   public IDImm createArray(String var1, int var2, List var3) throws DexDecEvaluationException {
      return this.createArray(this.NS.createType(var1), var2, var3);
   }

   @Override
   public IDImm createArray(IJavaType var1, int var2, List var3) throws DexDecEvaluationException {
      if (var2 < 0) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NegativeArraySizeException()));
      } else if (var2 > 209715200) {
         throw new DexDecEvaluationException();
      } else {
         IJavaType var4 = var1.getArrayTypeDimensionBelow();
         Object var5 = null;
         if (var4.isPrimitive()) {
            String var6 = var4.getName();
            if (var6.equals("Z")) {
               boolean[] var7 = new boolean[var2];
               int var8 = 0;

               for (IDImm var10 : var3) {
                  var7[var8] = var10.toLong() != 0L;
                  var8++;
               }

               var5 = var7;
            } else if (var6.equals("B")) {
               byte[] var14 = new byte[var2];
               int var23 = 0;

               for (IDImm var42 : var3) {
                  var14[var23] = (byte)var42.toLong();
                  var23++;
               }

               var5 = var14;
            } else if (var6.equals("C")) {
               char[] var15 = new char[var2];
               int var24 = 0;

               for (IDImm var43 : var3) {
                  var15[var24] = (char)var43.toLong();
                  var24++;
               }

               var5 = var15;
            } else if (var6.equals("S")) {
               short[] var16 = new short[var2];
               int var25 = 0;

               for (IDImm var44 : var3) {
                  var16[var25] = (short)var44.toLong();
                  var25++;
               }

               var5 = var16;
            } else if (var6.equals("I")) {
               int[] var17 = new int[var2];
               int var26 = 0;

               for (IDImm var45 : var3) {
                  var17[var26] = (int)var45.toLong();
                  var26++;
               }

               var5 = var17;
            } else if (var6.equals("J")) {
               long[] var18 = new long[var2];
               int var27 = 0;

               for (IDImm var46 : var3) {
                  var18[var27] = var46.toLong();
                  var27++;
               }

               var5 = var18;
            } else if (var6.equals("F")) {
               float[] var19 = new float[var2];
               int var28 = 0;

               for (IDImm var47 : var3) {
                  var19[var28] = var47.toFloat();
                  var28++;
               }

               var5 = var19;
            } else if (var6.equals("D")) {
               double[] var20 = new double[var2];
               int var29 = 0;

               for (IDImm var48 : var3) {
                  var20[var29] = var48.toDouble();
                  var29++;
               }

               var5 = var20;
            }
         } else {
            String var13 = var4.getSignature();
            if ("Ljava/lang/String;".equals(var4.getSignature())) {
               String[] var21 = new String[var2];
               int var30 = 0;

               for (IDImm var49 : var3) {
                  var21[var30] = this.getStringObject(var49);
                  var30++;
               }

               var5 = var21;
            } else {
               btj var31 = this.pC(var13, false);
               Object var22;
               if (var31.pC()) {
                  Class var40 = var31.wS();
                  var22 = Array.newInstance(var40, var2);
               } else {
                  var22 = new Object[var2];
               }

               int var41 = 0;

               for (IDImm var11 : var3) {
                  Object var12 = this.getObject(var11, true);
                  Array.set(var22, var41, var12);
                  var41++;
               }

               var5 = var22;
            }
         }

         if (var5 == null) {
            throw new DexDecEvaluationException(
               ckx.pC(new byte[]{-62, 39, 8, 5, 9, 1, 68, 67, 17, 23, 4, 21, 29, 7, 9, 71, 65, 19, 0, 19, 24, 89, 79, 9, 70, 84, 13, 9, 21, 95, 26}, 1, 132)
                  + var1
            );
         } else {
            return this.registerObject(var5);
         }
      }
   }

   @Override
   public IDImm getClassReference(String var1) throws DexDecEvaluationException {
      btj var2 = this.pC(var1, false);
      return this.registerObject(var2);
   }

   @Override
   public boolean isInstanceOf(IDImm var1, IDImm var2) throws DexDecEvaluationException {
      Object var3 = this.getObject(var1);
      if (var3 == null) {
         return false;
      } else {
         String var4;
         if (var3 instanceof btk) {
            var4 = ((btk)var3).A().getFullName();
         } else if (var3 instanceof btj) {
            var4 = ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 32, 69, 65, 66, 74, 2}, 2, 196);
         } else if (var3 instanceof bth) {
            if (((bth)var3).pC()) {
               var4 = ckx.pC(new byte[]{-40, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 93, 23, 3, 10, 9, 6, 23, 91, 105, 47, 12, 9, 8, 95}, 1, 148);
            } else if (((bth)var3).A()) {
               var4 = ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 17, 76, 70, 93, 92, 90, 71, 3, 109, 87, 68, 90, 90, 72, 27}, 2, 154);
            } else {
               if (!((bth)var3).kS()) {
                  throw new RuntimeException();
               }

               var4 = ckx.pC(
                  new byte[]{-118, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 93, 23, 3, 10, 9, 6, 23, 91, 108, 44, 1, 29, 7, 6, 7, 22, 23, 27, 29, 73}, 1, 198
               );
            }
         } else {
            if (var3 instanceof bti) {
               throw new RuntimeException();
            }

            var4 = JvmUtil.generateTypeSig(var3.getClass());
         }

         btj var5 = (btj)this.getObject(var2);
         String var6 = var5.getFullName();
         return bgq.pC(this.getTypeAdapter(), var4, var6);
      }
   }

   @Override
   public IDImm getStaticField(String var1) throws DexDecEvaluationException {
      JvmFieldSig var2 = JvmFieldSig.parse(var1);
      return this.pC(var2.csig, var2.fname, var2.ftype);
   }

   public IDImm pC(String var1, String var2, String var3) throws DexDecEvaluationException {
      String var4 = var1 + "->" + var2 + ":" + var3;
      String var5 = this.sY(var4);
      if (var5 == null) {
         throw new DexDecEvaluationException(
            ckx.pC(
                  new byte[]{
                     -19,
                     34,
                     15,
                     0,
                     1,
                     27,
                     84,
                     82,
                     23,
                     22,
                     28,
                     3,
                     26,
                     19,
                     69,
                     83,
                     7,
                     21,
                     21,
                     29,
                     10,
                     67,
                     70,
                     15,
                     12,
                     9,
                     8,
                     68,
                     70,
                     9,
                     29,
                     82,
                     82,
                     23,
                     4,
                     5,
                     13,
                     7,
                     9,
                     93,
                     26
                  },
                  1,
                  174
               )
               + var4
         );
      } else {
         JvmFieldSig var6 = JvmFieldSig.parse(var5);
         if (Strings.isContainedIn(var3, "Z", "B", "C", "S", "I", "J", "F", "D", "Ljava/lang/String;")) {
            IDexClass var7 = this.WR.getClass(var6.csig);
            if (var7 != null) {
               IDexField var8 = var7.getField(false, var2, var3);
               if (var8 != null) {
                  byte var9 = 24;
                  if ((var8.getData().getAccessFlags() & var9) == var9) {
                     IDexValue var10 = var8.getStaticInitializer();
                     if (var10 != null && var10.getType() != 30) {
                        boolean var11 = true;

                        for (IDexAddress var14 : this.WR.getCrossReferences(DexPoolType.FIELD, var8.getIndex())) {
                           if (var14.getReferenceType() == DexReferenceType.SET) {
                              var11 = false;
                              break;
                           }
                        }

                        if (var11) {
                           return this.pC(var10, this.NS.createType(var3));
                        }
                     }
                  }
               }
            }
         }

         btj var15 = this.pC(var6.csig, true);
         IDImm var16 = var15.pC(var2, var3);
         if (var16 == null) {
            throw new DexDecEvaluationException();
         } else {
            return var16;
         }
      }
   }

   @Override
   public void setStaticField(String var1, IDImm var2) throws DexDecEvaluationException {
      JvmFieldSig var3 = JvmFieldSig.parse(var1);
      this.pC(var3.csig, var3.fname, var3.ftype, var2);
   }

   public void pC(String var1, String var2, String var3, IDImm var4) throws DexDecEvaluationException {
      String var5 = var1 + "->" + var2 + ":" + var3;
      String var6 = this.sY(var5);
      if (var6 == null) {
         throw new DexDecEvaluationException(
            ckx.pC(
                  new byte[]{
                     -118,
                     34,
                     15,
                     0,
                     1,
                     27,
                     84,
                     82,
                     23,
                     22,
                     28,
                     3,
                     26,
                     19,
                     69,
                     83,
                     7,
                     21,
                     21,
                     29,
                     10,
                     67,
                     70,
                     15,
                     12,
                     9,
                     8,
                     68,
                     70,
                     9,
                     29,
                     82,
                     87,
                     5,
                     27,
                     29,
                     29,
                     7,
                     9,
                     93,
                     26
                  },
                  1,
                  201
               )
               + var5
         );
      } else {
         JvmFieldSig var7 = JvmFieldSig.parse(var6);
         btj var8 = this.pC(var7.csig, true);
         if (!var8.pC(var7.fname, var7.ftype, var4)) {
            throw new DexDecEvaluationException();
         }
      }
   }

   @Override
   public IDImm getInstanceField(String var1, IDImm var2) throws DexDecEvaluationException {
      if (var2.getObjectReferenceId() == 0) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else {
         if (this.getObject(var2) instanceof btk var4) {
            IDImm var5 = var4.pC(var1, var2);
            if (var5 != null) {
               this.LM();
               return var5;
            }

            String var6 = var4.A(var1);
            if (var6 == null) {
               throw new DexDecEvaluationException(
                  ckx.pC(
                        new byte[]{
                           -1,
                           34,
                           15,
                           0,
                           1,
                           27,
                           84,
                           82,
                           23,
                           22,
                           28,
                           3,
                           26,
                           19,
                           69,
                           73,
                           7,
                           29,
                           7,
                           21,
                           15,
                           13,
                           6,
                           69,
                           70,
                           15,
                           12,
                           9,
                           8,
                           68,
                           70,
                           9,
                           29,
                           82,
                           82,
                           23,
                           4,
                           5,
                           13,
                           7,
                           9,
                           93,
                           26
                        },
                        1,
                        188
                     )
                     + var1
               );
            }

            var2 = var4.pC;
            var1 = var6;
         }

         this.Cu();
         this.gp();
         return this.hK.pC(var1, var2);
      }
   }

   @Override
   public void setInstanceField(String var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      if (var2.getObjectReferenceId() == 0) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else {
         if (this.getObject(var2) instanceof btk var5) {
            if (var5.pC(var1, var2, var3)) {
               this.mv();
               return;
            }

            String var6 = var5.A(var1);
            if (var6 == null) {
               throw new DexDecEvaluationException(
                  ckx.pC(
                        new byte[]{
                           0,
                           14,
                           30,
                           23,
                           29,
                           29,
                           71,
                           26,
                           17,
                           83,
                           71,
                           15,
                           95,
                           69,
                           17,
                           80,
                           87,
                           64,
                           88,
                           65,
                           92,
                           83,
                           87,
                           21,
                           74,
                           73,
                           42,
                           30,
                           5,
                           67,
                           10,
                           10,
                           82,
                           65,
                           25,
                           22,
                           70,
                           27,
                           27,
                           78,
                           14,
                           78,
                           83
                        },
                        2,
                        149
                     )
                     + var1
               );
            }

            var2 = var5.pC;
            var1 = var6;
         }

         this.hZ();
         this.gp();
         this.hK.pC(var1, var2, var3);
      }
   }

   @Override
   public Class loadClass(String var1) throws DexDecEvaluationException {
      return this.loadClass(var1, true);
   }

   @Override
   public Class loadClass(String var1, boolean var2) throws DexDecEvaluationException {
      btj var3 = this.pC(var1, var2);
      return var3.wS();
   }

   public btj pC(String var1, boolean var2) throws DexDecEvaluationException {
      this.WR();
      int var3 = JvmTypeSig.getDimensionCount(var1);
      var1 = var1.substring(var3);
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var1);

      while (!var4.isEmpty()) {
         String var5 = (String)var4.remove();
         btj var6 = (btj)this.E.get(var5);
         if (var6 != null) {
            if (var2 && !var6.isInitialized()) {
               var6.kS();
            }
         } else {
            IDexClass var7 = this.WR.getClass(var5);
            if (var7 == null) {
               this.gp();
               Class var8 = this.hK.pC(var5);
               var6 = new btj(this, var5, var8);
               this.E.put(var5, var6);
            } else {
               Assert.a(var5.equals(var7.getSignature(false)));
               if (this.sY.contains(var5)) {
                  throw new DexDecEvaluationException(
                     ckx.pC(
                           new byte[]{
                              120,
                              39,
                              7,
                              29,
                              29,
                              8,
                              13,
                              5,
                              19,
                              19,
                              7,
                              9,
                              71,
                              79,
                              9,
                              70,
                              67,
                              15,
                              13,
                              18,
                              0,
                              83,
                              80,
                              2,
                              23,
                              19,
                              31,
                              6,
                              26,
                              6,
                              31,
                              21,
                              89,
                              70,
                              7,
                              8,
                              5,
                              9,
                              1,
                              94,
                              26
                           },
                           1,
                           49
                        )
                        + var5
                  );
               }

               var6 = new btj(this, var5, var7);
               this.E.put(var5, var6);
               if (var2) {
                  var6.kS();
               }

               var4.add(var7.getSupertypeSignature(false));
               var4.addAll(Arrays.asList(var7.getInterfaceSignatures(false)));
            }
         }
      }

      btj var10 = (btj)this.E.get(var1);
      if (var3 > 0) {
         String var13 = Strings.generate('[', var3) + JvmUtil.generateTypeSig(var10.wS());
         Class var14 = this.hK.pC(var13);
         var10 = new btj(this, var13, var14);
      }

      return var10;
   }

   IDImm pC(IDexValue var1, IJavaType var2) throws DexDecEvaluationException {
      long var3 = 0L;
      IDImm var5 = null;
      switch (var1.getType()) {
         case 0:
            var3 = var1.getByte();
            this.NS.getByte();
            break;
         case 1:
         case 5:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 24:
         case 25:
         case 26:
         case 27:
         case 29:
         default:
            throw new DexDecEvaluationException(
               ckx.pC(
                     new byte[]{
                        22,
                        1,
                        3,
                        12,
                        2,
                        25,
                        8,
                        26,
                        0,
                        69,
                        76,
                        67,
                        90,
                        84,
                        80,
                        77,
                        80,
                        80,
                        12,
                        70,
                        91,
                        85,
                        94,
                        81,
                        12,
                        73,
                        33,
                        30,
                        8,
                        13,
                        9,
                        69,
                        73,
                        15,
                        7,
                        16,
                        15,
                        9,
                        29,
                        82,
                        73,
                        0,
                        10,
                        80,
                        4,
                        92,
                        70
                     },
                     2,
                     114
                  )
                  + var1.getType()
            );
         case 2:
            var3 = var1.getShort();
            this.NS.getShort();
            break;
         case 3:
            var3 = var1.getChar();
            this.NS.getChar();
            break;
         case 4:
            var3 = var1.getInt();
            this.NS.getInt();
            break;
         case 6:
            var3 = var1.getLong();
            this.NS.getLong();
            break;
         case 16:
            var3 = Float.floatToIntBits(var1.getFloat()) & 4294967295L;
            this.NS.getFloat();
            break;
         case 17:
            var3 = Double.doubleToLongBits(var1.getDouble());
            this.NS.getDouble();
            break;
         case 23:
            var5 = this.fI.createString(this.fI.createIndex(var1.getStringIndex()));
            var5 = var5.evaluate(this);
            break;
         case 28:
            IJavaType var6 = var2.getArrayTypeDimensionBelow();
            List var7 = var1.getArray();
            ArrayList var8 = new ArrayList(var7.size());

            for (IDexValue var10 : var7) {
               var8.add(this.pC(var10, var6));
            }

            var5 = this.createArray(var2, var8.size(), var8);
            break;
         case 30:
            var3 = 0L;
            this.NS.getGenericObjectWildcard();
            break;
         case 31:
            var3 = var1.getBoolean() ? 1L : 0L;
            this.NS.getBoolean();
      }

      if (var5 == null) {
         var5 = this.fI.createConstant(var3, var2);
      }

      return var5;
   }

   public btk pC(btj var1) throws DexDecEvaluationException {
      this.WR();
      btk var2 = new btk(this, var1);
      this.e.add(var2);
      return var2;
   }

   void pC(btk var1, IDImm var2) throws DexDecEvaluationException {
      Assert.a(var1.pC == null);
      Assert.a(bvo.A(this.getObject(var2)));
      var1.pC = var2;
   }

   public btk A(Object param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield com/pnfsoftware/jebglobal/btp.e Ljava/util/List;
      // 04: dup
      // 05: astore 2
      // 06: monitorenter
      // 07: aload 0
      // 08: getfield com/pnfsoftware/jebglobal/btp.e Ljava/util/List;
      // 0b: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 10: astore 3
      // 11: aload 3
      // 12: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 17: ifeq 46
      // 1a: aload 3
      // 1b: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 20: checkcast com/pnfsoftware/jebglobal/btk
      // 23: astore 4
      // 25: aload 4
      // 27: getfield com/pnfsoftware/jebglobal/btk.pC Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;
      // 2a: ifnull 3f
      // 2d: aload 0
      // 2e: aload 4
      // 30: getfield com/pnfsoftware/jebglobal/btk.pC Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;
      // 33: invokevirtual com/pnfsoftware/jebglobal/btp.getObject (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;)Ljava/lang/Object;
      // 36: aload 1
      // 37: if_acmpne 3f
      // 3a: aload 4
      // 3c: aload 2
      // 3d: monitorexit
      // 3e: areturn
      // 3f: goto 43
      // 42: pop
      // 43: goto 11
      // 46: aconst_null
      // 47: aload 2
      // 48: monitorexit
      // 49: areturn
      // 4a: astore 5
      // 4c: aload 2
      // 4d: monitorexit
      // 4e: aload 5
      // 50: athrow
   }

   public btj UT(String var1) {
      return (btj)this.E.get(var1);
   }

   public btj A(Class param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield com/pnfsoftware/jebglobal/btp.E Ljava/util/Map;
      // 04: dup
      // 05: astore 2
      // 06: monitorenter
      // 07: aload 0
      // 08: getfield com/pnfsoftware/jebglobal/btp.E Ljava/util/Map;
      // 0b: invokeinterface java/util/Map.values ()Ljava/util/Collection; 1
      // 10: invokeinterface java/util/Collection.iterator ()Ljava/util/Iterator; 1
      // 15: astore 3
      // 16: aload 3
      // 17: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 1c: ifeq 3f
      // 1f: aload 3
      // 20: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 25: checkcast com/pnfsoftware/jebglobal/btj
      // 28: astore 4
      // 2a: aload 4
      // 2c: invokevirtual com/pnfsoftware/jebglobal/btj.wS ()Ljava/lang/Class;
      // 2f: aload 1
      // 30: if_acmpne 38
      // 33: aload 4
      // 35: aload 2
      // 36: monitorexit
      // 37: areturn
      // 38: goto 3c
      // 3b: pop
      // 3c: goto 16
      // 3f: aconst_null
      // 40: aload 2
      // 41: monitorexit
      // 42: areturn
      // 43: astore 5
      // 45: aload 2
      // 46: monitorexit
      // 47: aload 5
      // 49: athrow
   }

   public void cX() {
      this.xM++;
   }

   public int DQ() {
      return this.xM;
   }

   public void wS(int var1) {
      this.kU[var1]++;
   }

   public int UT(int var1) {
      return this.kU[var1];
   }

   public btp.Sv A(String var1, boolean var2) {
      btp.Sv var3 = new btp.Sv(var1, var2);
      this.Kq.add(var3);
      return var3;
   }

   public String E(String var1) throws DexDecEvaluationException {
      IJLSTypeAdapter var2 = this.getTypeAdapter();
      JvmMethodSig var3 = JvmMethodSig.parse(var1);
      String var4 = var3.csig;
      String var5 = var3.mname;
      List var6 = var3.partypes;

      for (int var7 = 0; var4 != null; var7++) {
         String var8 = this.pC(var2, var7, var4, var5, var6);
         if (var8 != null) {
            return var8;
         }

         String var9 = var2.getSupertype(var4);
         if (var9 == null || "Ljava/lang/Object;".equals(var9)) {
            break;
         }

         var4 = var9;
      }

      return null;
   }

   private String pC(IJLSTypeAdapter var1, int var2, String var3, String var4, List var5) {
      List var6 = var1.getMethods(var3);
      if (var6 == null) {
         return null;
      } else {
         label58:
         for (IJLSMethod var8 : var6) {
            if ((var8.getAccessFlags() & 8) != 0 && (var2 <= 0 || (var8.getAccessFlags() & 2) == 0)) {
               String var9 = var8.getName();
               if (!var9.startsWith("<") && var9.equals(var4)) {
                  JvmMethodDescriptor var10 = JvmMethodDescriptor.parse(var8.getDescriptor());
                  if (var10.partypes.size() == var5.size()) {
                     for (int var11 = 0; var11 < var5.size(); var11++) {
                        if (!((String)var5.get(var11)).equals(var10.partypes.get(var11))) {
                           continue label58;
                        }
                     }

                     return var3 + "->" + var8;
                  }
               }
            }
         }

         return null;
      }
   }

   public String sY(String var1) throws DexDecEvaluationException {
      IJLSTypeAdapter var2 = this.getTypeAdapter();
      JvmFieldSig var3 = JvmFieldSig.parse(var1);
      String var4 = var3.csig;
      String var5 = var3.fname;
      String var6 = var3.ftype;

      for (int var7 = 0; var4 != null; var7++) {
         String var8 = this.pC(var2, var7, var4, var5, var6);
         if (var8 != null) {
            return var8;
         }

         String var9 = var2.getSupertype(var4);
         if (var9 == null || "Ljava/lang/Object;".equals(var9)) {
            break;
         }

         var4 = var9;
      }

      return null;
   }

   private String pC(IJLSTypeAdapter var1, int var2, String var3, String var4, String var5) {
      List var6 = var1.getFields(var3);
      if (var6 == null) {
         return null;
      } else {
         for (IJLSField var8 : var6) {
            if ((var8.getAccessFlags() & 8) != 0
               && (var2 <= 0 || (var8.getAccessFlags() & 2) == 0)
               && var8.getName().equals(var4)
               && var8.getDescriptor().equals(var5)) {
               return var3 + "->" + var8;
            }
         }

         return null;
      }
   }

   public String pC(String var1, int var2, int var3) throws DexDecEvaluationException {
      JvmMethodSig var4 = JvmMethodSig.parse(var1);
      String var5 = var4.csig;
      String var6 = var4.mname;
      List var7 = var4.partypes;
      return this.pC(var5, var6, var7, var2, var3);
   }

   public String pC(String var1, String var2, List var3, int var4, int var5) throws DexDecEvaluationException {
      IJLSTypeAdapter var6 = this.getTypeAdapter();
      if (var5 < 0) {
         var5 = Integer.MAX_VALUE;
      }

      for (int var7 = 0; var1 != null && var7 <= var5; var7++) {
         String var8 = this.pC(var6, var4, var7, var1, var2, var3);
         if (var8 != null) {
            return var8;
         }

         String var9 = var6.getSupertype(var1);
         if (var9 == null || "Ljava/lang/Object;".equals(var9)) {
            break;
         }

         var1 = var9;
      }

      return null;
   }

   private String pC(IJLSTypeAdapter var1, int var2, int var3, String var4, String var5, List var6) {
      List var7 = var1.getMethods(var4);
      if (var7 == null) {
         return null;
      } else {
         label53:
         for (IJLSMethod var9 : var7) {
            if ((var9.getAccessFlags() & var2) == var2 && (var3 <= 0 || (var9.getAccessFlags() & 2) == 0)) {
               String var10 = var9.getName();
               if (var10.equals(var5)) {
                  JvmMethodDescriptor var11 = JvmMethodDescriptor.parse(var9.getDescriptor());
                  if (var11.partypes.size() == var6.size()) {
                     for (int var12 = 0; var12 < var6.size(); var12++) {
                        if (!((String)var6.get(var12)).equals(var11.partypes.get(var12))) {
                           continue label53;
                        }
                     }

                     return var4 + "->" + var9;
                  }
               }
            }
         }

         return null;
      }
   }

   public String A(String var1, int var2, int var3) throws DexDecEvaluationException {
      JvmFieldSig var4 = JvmFieldSig.parse(var1);
      String var5 = var4.csig;
      String var6 = var4.fname;
      String var7 = var4.ftype;
      return this.pC(var5, var6, var7, var2, var3);
   }

   public String pC(String var1, String var2, String var3, int var4, int var5) throws DexDecEvaluationException {
      IJLSTypeAdapter var6 = this.getTypeAdapter();
      if (var5 < 0) {
         var5 = Integer.MAX_VALUE;
      }

      for (int var7 = 0; var1 != null && var7 <= var5; var7++) {
         String var8 = this.pC(var6, var4, var7, var1, var2, var3);
         if (var8 != null) {
            return var8;
         }

         String var9 = var6.getSupertype(var1);
         if (var9 == null || "Ljava/lang/Object;".equals(var9)) {
            break;
         }

         var1 = var9;
      }

      return null;
   }

   private String pC(IJLSTypeAdapter var1, int var2, int var3, String var4, String var5, String var6) {
      List var7 = var1.getFields(var4);
      if (var7 == null) {
         return null;
      } else {
         for (IJLSField var9 : var7) {
            if ((var9.getAccessFlags() & var2) == var2
               && (var3 <= 0 || (var9.getAccessFlags() & 2) == 0)
               && var9.getName().equals(var5)
               && (var6 == null || var6.equals(var9.getDescriptor()))) {
               return var4 + "->" + var9;
            }
         }

         return null;
      }
   }

   public IDImm ys(String var1) {
      return this.fI.createConstant(0L, switch (var1) {
         case "Z" -> this.NS.getBoolean();
         case "B" -> this.NS.getByte();
         case "C" -> this.NS.getChar();
         case "S" -> this.NS.getShort();
         case "I" -> this.NS.getInt();
         case "J" -> this.NS.getLong();
         case "F" -> this.NS.getFloat();
         case "D" -> this.NS.getDouble();
         default -> {
            if (var1.isEmpty() || var1.charAt(0) != '[' && var1.charAt(0) != 'L') {
               throw new RuntimeException(ckx.pC(new byte[]{10, 3, 28, 28, 21, 8, 11, 72, 0, 89, 88, 6, 19, 0}, 2, 4) + var1);
            }

            yield this.NS.getGenericObjectWildcard();
         }
      });
   }

   @Override
   public IDImm createNewInstance(String var1, Collection var2) throws DexDecEvaluationException {
      return this.pC(var1, var2, DInvokeType.NEW);
   }

   @Override
   public IDImm createNewInstance(String var1) throws DexDecEvaluationException {
      return this.createNewInstance(var1 + "-><init>()V", null);
   }

   @Override
   public IDImm invokeMethod(String var1, Collection var2, DInvokeType var3) throws DexDecEvaluationException {
      return this.pC(var1, var2, var3, null);
   }

   @Override
   public IDImm invokeMethod(DInvokeType var1, String var2, IDExpression... var3) throws DexDecEvaluationException {
      return this.pC(var2, Arrays.asList(var3), var1, null);
   }

   @Override
   public IDImm executeDexMethod(IDexMethod var1, List var2) throws DexDecEvaluationException {
      return this.pC(var1, var2);
   }

   @Override
   public List getNativeLibraries() throws DexDecEvaluationException {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  -32,
                  40,
                  24,
                  25,
                  13,
                  21,
                  29,
                  6,
                  1,
                  78,
                  79,
                  9,
                  70,
                  78,
                  15,
                  21,
                  29,
                  31,
                  19,
                  69,
                  67,
                  12,
                  11,
                  1,
                  69,
                  73,
                  26,
                  83,
                  78,
                  1,
                  27,
                  84,
                  65,
                  13,
                  0,
                  3,
                  24,
                  18,
                  1
               },
               1,
               165
            )
         );
      } else {
         return new ArrayList(this.oT().E());
      }
   }

   @Override
   public INativeLibrary loadNativeLibrary(IELFUnit var1) throws DexDecEvaluationException {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  6,
                  2,
                  5,
                  21,
                  19,
                  29,
                  14,
                  7,
                  26,
                  0,
                  71,
                  5,
                  9,
                  78,
                  80,
                  77,
                  80,
                  69,
                  73,
                  0,
                  81,
                  95,
                  86,
                  80,
                  12,
                  73,
                  60,
                  82,
                  15,
                  12,
                  24,
                  69,
                  65,
                  13,
                  2,
                  11,
                  88,
                  10,
                  22
               },
               2,
               116
            )
         );
      } else {
         return this.oT().pC(var1);
      }
   }

   @Override
   public INativeLibrary loadNativeLibrary(String var1) throws DexDecEvaluationException {
      return this.loadNativeLibrary(var1, false);
   }

   @Override
   public INativeLibrary loadNativeLibrary(String var1, boolean var2) throws DexDecEvaluationException {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  62,
                  40,
                  24,
                  25,
                  13,
                  21,
                  29,
                  6,
                  1,
                  78,
                  79,
                  9,
                  70,
                  78,
                  15,
                  21,
                  29,
                  31,
                  19,
                  69,
                  67,
                  12,
                  11,
                  1,
                  69,
                  73,
                  26,
                  83,
                  78,
                  1,
                  27,
                  84,
                  65,
                  13,
                  0,
                  3,
                  24,
                  18,
                  1
               },
               1,
               123
            )
         );
      } else {
         btp.Sv var3 = new btp.Sv(var1, var2);
         return this.oT().pC(var3);
      }
   }

   @Override
   public Map getRegisteredNatives() {
      return this.oT().UT();
   }

   @Override
   public EEmulator getNativeEmulator() {
      return this.oT().wS();
   }

   public IDImm pC(IDexMethod var1, List var2) throws DexDecEvaluationException {
      this.pushContext(var1.getSignature());

      IDImm var3;
      try {
         var3 = this.A(var1, var2);
      } finally {
         this.UO();
      }

      return var3;
   }

   public IDImm A(IDexMethod var1, List var2) throws DexDecEvaluationException {
      if (var1.getData() == null) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  2,
                  27,
                  4,
                  28,
                  31,
                  25,
                  19,
                  1,
                  26,
                  71,
                  8,
                  23,
                  70,
                  0,
                  84,
                  84,
                  76,
                  95,
                  77,
                  84,
                  87,
                  16,
                  83,
                  91,
                  12,
                  69,
                  55,
                  6,
                  4,
                  17,
                  2,
                  4,
                  76,
                  65,
                  3,
                  1,
                  91,
                  7,
                  29,
                  68
               },
               2,
               124
            )
         );
      } else if (var1.getData().getCodeItem() == null) {
         throw new DexDecEvaluationException(
            ckx.pC(
               new byte[]{
                  -72,
                  53,
                  0,
                  17,
                  8,
                  29,
                  4,
                  29,
                  7,
                  9,
                  71,
                  84,
                  27,
                  79,
                  69,
                  8,
                  24,
                  25,
                  13,
                  21,
                  17,
                  69,
                  65,
                  15,
                  78,
                  65,
                  3,
                  17,
                  7,
                  6,
                  19,
                  2,
                  23,
                  84,
                  79,
                  29,
                  82,
                  78,
                  15,
                  21,
                  29,
                  31,
                  19,
                  69,
                  77,
                  8,
                  17,
                  28,
                  7,
                  11
               },
               1,
               249
            )
         );
      } else {
         this.Aj();
         String var3 = var1.getSignature(false);
         btp.Av var4 = this.ZD == null ? null : (btp.Av)this.ZD.get(var3);
         if (var4 == null) {
            bpx var5 = (bpx)((bpr)this.fI).pC(var1);
            bqp var6 = var5.pC();

            try {
               var6.pC(this.vP, false, false);
            } catch (DexDecConversionException var18) {
               throw new DexDecEvalFailedTranslationException(var18);
            }

            var4 = new btp.Av(var6.pC(), var6.kS(), var6.UT());
            if (this.ZD != null) {
               this.ZD.put(var3, var4);
            }
         }

         List var19 = var4.pC;
         Map var20 = var4.A;
         Map var7 = var4.kS;
         this.pushFrame(var3);
         SortedMap var8 = JavaTypeUtil.parseMethodParameters(this.WR, var1, this.NS);
         Set var9 = var8.keySet();
         var9.remove(-1);
         ArrayList var10 = new ArrayList(var9.size());
         int var11 = 0;

         for (int var13 : var9) {
            IDImm var14 = ((IDExpression)var2.get(var11)).evaluate(this);
            this.pC(var13, var14);
            var10.add(var14);
            var11++;
         }

         TreeMap var21 = new TreeMap();
         var19.forEach(var1x -> var21.put((int)var1x.getOffset(), var1x));
         long var22 = 0L;
         if (this.ED().pC()) {
            var22 = xC();
            Wrapper var15 = this.ED().invokeMethod(var22, var3, var10);
            if (var15 != null) {
               this.PR.setExecutionComplete(true);
               this.Ab();
               return (IDImm)var15.get();
            }
         }

         this.ys++;
         DExecutionParameters var23 = new DExecutionParameters(var21, var20, var7);
         IDImm var16 = this.execute(var23);
         if (this.ED().pC()) {
            Wrapper var17 = this.ED().examineMethodResult(var22, var16);
            if (var17 != null) {
               return (IDImm)var17.get();
            }
         }

         return var16;
      }
   }

   @Override
   public IDImm execute(DExecutionParameters var1) throws DexDecEvaluationException {
      int var3 = 0;
      int var4 = Integer.MAX_VALUE;
      int var5 = -1;
      Map var6 = var1.getInstructionMap();
      Map var7 = var1.getDalvikToIRMap();
      var1.getIRToDalvikMap();
      CFG var8 = var1.getCFG();
      IDTryData var9 = var1.getExceptionData();
      boolean var2 = var1.pc == null || var1.pc.intValue() == 0L;
      if (var1.pc != null) {
         this.setPC(var1.pc);
      }

      for (Entry var11 : var1.getInitialValues().entrySet()) {
         this.setVariable((Integer)var11.getKey(), (IDImm)var11.getValue());
      }

      if (var1.pcThresholdMin != null) {
         var3 = var1.pcThresholdMin;
      }

      if (var1.pcThresholdMax != null) {
         var4 = var1.pcThresholdMax;
      }

      if (var1.pcExpectedTermination != null) {
         var5 = var1.pcExpectedTermination;
      }

      long var39 = System.currentTimeMillis();
      long var12 = var39;
      long var14 = this.pC(var39);

      try {
         IDImm var16 = null;

         while (this.getCurrentIterationCount() < this.ED && (var12 = System.currentTimeMillis()) < var14) {
            if (var1.iterationCountLeft != null) {
               if (var1.iterationCountLeft <= 0) {
                  return var16;
               }

               var1.iterationCountLeft = var1.iterationCountLeft - 1;
            }

            if ((this.ah & 15) == 0 && this.Ek != null) {
               this.Ek.verify();
            }

            int var17 = this.getPC();
            if (var17 == var5) {
               return var16;
            }

            if (var17 < var3 || var17 >= var4) {
               throw new DexDecEvaluationException(
                  ckx.pC(
                     new byte[]{
                        19,
                        44,
                        80,
                        22,
                        7,
                        29,
                        20,
                        1,
                        16,
                        69,
                        8,
                        15,
                        76,
                        71,
                        80,
                        85,
                        25,
                        65,
                        77,
                        78,
                        85,
                        85,
                        18,
                        83,
                        67,
                        82,
                        111,
                        17,
                        20,
                        17,
                        30,
                        0,
                        78,
                        21,
                        78,
                        9,
                        74,
                        27,
                        26,
                        79,
                        13
                     },
                     2,
                     223
                  )
               );
            }

            var16 = null;
            IDInstruction var18 = (IDInstruction)var6.get(var17);
            if (var18 == null) {
               throw new DexDecEvaluationException(Strings.ff("Cannot fetch instruction at 0x%X", var17));
            }

            this.pC(var18.getPhysicalOffset());

            try {
               var16 = var18.evaluate(this);
            } catch (DexDecEvalCodeThrownException var36) {
               this.xC++;
               if (!this.vP || var7 == null && (var8 == null || var9 == null)) {
                  throw var36;
               }

               if (this.xC > 100000 && this.ah / this.xC < 50) {
                  throw var36;
               }

               IDImm var20 = var36.getThrownObjectRef();
               this.PR.setRaisedException(var20);
               Integer var22 = null;
               Object var23 = this.getObject(var20);
               String var21;
               if (var23 instanceof btk var24) {
                  var21 = var24.A().getFullName();
                  pC(2, "The emulated code generated an exception: %s", var21);
               } else {
                  if (!(var23 instanceof Throwable var49)) {
                     throw var36;
                  }

                  var21 = JvmUtil.generateTypeSig(var49.getClass());
                  pC(2, "The emulated code generated an exception: %s", var49);
               }

               int var50 = var18.getPhysicalOffset();
               String var25 = this.PR.getMethodSignature();
               IDexMethod var26 = this.WR.getMethod(var25);
               if (var8 != null && var9 != null) {
                  for (IDExceptionHandler var58 : var9.getBlockHandlers((int)var8.getBlockFor(var18).getBase())) {
                     String var60 = this.WR.getType(var58.getTypeIndex()).getSignature(false);
                     if (bgq.pC(this.getTypeAdapter(), var21, var60)) {
                        var22 = var58.getAddress();
                        break;
                     }
                  }
               } else if (var7 != null) {
                  for (IDexExceptionHandler var29 : this.pC(var26, var50)) {
                     String var30 = var29.resolveType(this.WR).getSignature(false);
                     if (bgq.pC(this.getTypeAdapter(), var21, var30)) {
                        var22 = (Integer)var7.get(var29.getAddress());
                        break;
                     }
                  }
               }

               if (var22 == null) {
                  this.getCurrentFrame().setExecutionComplete(true);
                  this.Ab();
                  throw var36;
               }

               this.A(var22.intValue());
            } catch (DexDecEvaluationException var37) {
               throw var37;
            }

            boolean var19 = false;
            if (this.PR.isExecutionComplete()) {
               if (var19 && var2 && this.PR.getMethodSignature().endsWith("-><clinit>()V")) {
                  JvmMethodSig var42 = JvmMethodSig.parse(this.PR.getMethodSignature());
                  IDexClass var44 = this.WR.getClass(var42.csig);
                  if (var44 != null && var44.isEnumeration()) {
                     IDexMethod var45 = var44.getMethod(true, "values");
                     if (var45 != null) {
                        IDImm var46 = this.pC(var45, new ArrayList());
                        Object[] var51 = (Object[])this.getArrayObject(var46);
                        TreeMap var52 = new TreeMap();

                        for (Object var59 : var51) {
                           var46 = this.pC("Ljava/lang/Enum;->name()Ljava/lang/String;", Arrays.asList(this.registerObject(var59)), DInvokeType.VIRTUAL, null);
                           String var61 = this.getStringObject(var46);
                           var46 = this.pC("Ljava/lang/Enum;->ordinal()I", Arrays.asList(this.registerObject(var59)), DInvokeType.VIRTUAL, null);
                           int var31 = (int)var46.toLong();
                           var52.put(var31, var61);
                        }

                        pC(3, "Recovered Enumeration: %s", var52);
                     }
                  }
               }

               if (var5 == -1) {
                  this.Ab();
               }

               return var16;
            }

            this.Er();
         }

         if (var12 >= var14) {
            throw new DexDecEvalTimeoutExceededException(ckx.pC(new byte[]{23, 6, 29, 28, 29, 28, 19, 72, 3, 65, 91, 89, 9}, 2, 25) + this.Sc);
         } else {
            throw new DexDecEvalItercountExceededException(
               ckx.pC(new byte[]{57, 44, 25, 88, 73, 29, 17, 23, 19, 21, 29, 6, 1, 78, 67, 12, 26, 27, 26, 84, 87, 22, 18, 73, 26}, 1, 116) + this.ED
            );
         }
      } finally {
         this.eP += var12 - var39;
      }
   }

   private List pC(IDexMethod var1, int var2) {
      for (IDexExceptionItem var4 : var1.getData().getCodeItem().getExceptionItems()) {
         if (var2 >= var4.getTryAddress() && var2 < var4.getTryAddressEnd()) {
            return var4.getHandlers();
         }
      }

      return Collections.emptyList();
   }

   private String pC(IDImm var1) {
      if (var1 == null) {
         return "N/A";
      } else {
         if (var1.isNonNullRef()) {
            try {
               Object var2 = this.getObject(var1);
               if (var2 instanceof String) {
                  return "String:" + (String)var2;
               }

               if (var2 instanceof byte[]) {
                  return "byte[]:" + Arrays.toString((byte[])var2);
               }
            } catch (DexDecEvaluationException var3) {
            }
         }

         return var1.toString();
      }
   }

   @Override
   public void setData(String var1, Object var2) {
      this.DL.put(var1, var2);
   }

   @Override
   public Object getData(String var1) {
      return this.DL.get(var1);
   }

   public String ZN() {
      return Strings.ff("[*%d][CTX#%d=%s]", this.ah, this.Cu.size(), this.PR);
   }

   public String OB() {
      StringBuilder var1 = new StringBuilder("dexdec stats:\n");
      Strings.ff(var1, "total iteration count: %d\n", this.ah);
      Strings.ff(var1, "total invoke dex count: %d\n", this.ys);
      if (this.hK != null) {
         var1.append(this.hK.sY());
      }

      return var1.toString();
   }

   @Override
   public String toString() {
      return this.ZN();
   }

   static {
      wQ.add(FK);
      wQ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               46,
               10,
               79,
               13,
               11,
               5,
               65,
               71,
               62,
               74,
               8,
               2,
               18,
               15,
               13,
               7,
               8,
               14,
               67,
               38,
               3,
               30,
               0,
               16,
               90,
               27,
               104,
               54
            },
            2,
            42
         )
      );
      wQ.add(
         ckx.pC(
            new byte[]{
               91,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               54,
               59,
               13,
               17,
               77,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               107
            },
            1,
            23
         )
      );
      wQ.add(
         ckx.pC(
            new byte[]{
               -31,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               39,
               59,
               7,
               29,
               6,
               92,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               122
            },
            1,
            173
         )
      );
      wQ.add(
         ckx.pC(
            new byte[]{
               -36,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               89,
               2,
               17,
               55,
               43,
               9,
               19,
               90,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               18,
               106
            },
            1,
            144
         )
      );
      wQ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               37,
               11,
               84,
               73,
               34,
               14,
               78,
               25,
               19,
               15,
               5,
               21,
               29,
               71,
               78,
               41,
               4,
               3,
               9,
               10,
               21,
               79,
               76,
               58
            },
            2,
            196
         )
      );
      wQ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               32,
               10,
               78,
               6,
               70,
               40,
               69,
               14,
               4,
               65,
               70,
               24,
               18,
               78,
               6,
               73,
               41,
               11,
               6,
               12,
               2,
               0,
               94,
               90,
               100
            },
            2,
            67
         )
      );
      wQ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               42,
               9,
               79,
               0,
               26,
               76,
               99,
               5,
               19,
               86,
               8,
               91,
               31,
               65,
               15,
               1,
               73,
               38,
               14,
               3,
               4,
               23,
               17,
               72,
               7,
               102
            },
            2,
            7
         )
      );
      wQ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               21,
               4,
               23,
               40,
               10,
               85,
               3,
               2,
               1,
               7,
               35,
               24,
               65,
               31,
               21,
               92,
               76,
               0,
               8,
               1,
               70,
               35,
               11,
               11,
               17,
               6,
               7,
               21,
               9,
               5
            },
            2,
            143
         )
      );
      PZ.add(Bi);
      PZ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               46,
               10,
               79,
               13,
               11,
               5,
               65,
               71,
               62,
               74,
               8,
               2,
               18,
               15,
               13,
               7,
               8,
               14,
               67,
               38,
               3,
               30,
               0,
               16,
               90,
               27,
               27,
               69,
               58
            },
            2,
            44
         )
      );
      PZ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               46,
               28,
               84,
               4,
               70,
               40,
               69,
               14,
               4,
               65,
               70,
               24,
               18,
               78,
               6,
               73,
               41,
               11,
               6,
               12,
               2,
               0,
               94,
               49,
               7,
               118
            },
            2,
            34
         )
      );
      PZ.add(
         ckx.pC(
            new byte[]{
               14,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               77,
               22,
               17,
               39,
               59,
               7,
               29,
               6,
               92,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               104,
               122,
               127
            },
            1,
            66
         )
      );
      PZ.add(
         ckx.pC(
            new byte[]{
               84,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               77,
               22,
               17,
               55,
               43,
               9,
               19,
               90,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               120,
               106,
               127
            },
            1,
            24
         )
      );
      PZ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               37,
               11,
               84,
               73,
               34,
               14,
               78,
               25,
               19,
               15,
               5,
               21,
               29,
               71,
               78,
               41,
               4,
               3,
               9,
               10,
               21,
               79,
               44,
               90,
               120
            },
            2,
            84
         )
      );
      PZ.add(
         ckx.pC(
            new byte[]{
               -114,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               77,
               22,
               17,
               56,
               35,
               1,
               9,
               79,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               113,
               99,
               127
            },
            1,
            194
         )
      );
      PZ.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               17,
               76,
               70,
               93,
               92,
               90,
               71,
               3,
               102,
               91,
               85,
               94,
               81,
               23,
               13,
               113,
               1,
               4,
               23,
               42,
               9,
               79,
               0,
               26,
               76,
               99,
               5,
               19,
               86,
               8,
               91,
               31,
               65,
               15,
               1,
               73,
               38,
               14,
               3,
               4,
               23,
               17,
               72,
               104,
               9,
               23
            },
            2,
            34
         )
      );
      PZ.add(
         ckx.pC(
            new byte[]{
               -43,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               93,
               23,
               3,
               10,
               9,
               6,
               23,
               91,
               105,
               47,
               12,
               9,
               8,
               95,
               22,
               19,
               77,
               22,
               17,
               48,
               43,
               26,
               23,
               14,
               9,
               77,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               96,
               45,
               8,
               15,
               6,
               23,
               79,
               127,
               109,
               127
            },
            1,
            153
         )
      );
   }

   private static class Av {
      List pC;
      Map A;
      Map kS;

      Av(List var1, Map var2, Map var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }

   static class Sv {
      String pC;
      boolean A;

      public Sv(String var1, boolean var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return Strings.ff(ckx.pC(new byte[]{-16, 5, 11, 120, 31, 86, 91, 13, 65, 77}, 1, 188), this.pC, this.A ? 1 : 0);
      }
   }
}
