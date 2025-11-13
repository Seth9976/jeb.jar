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

public class bye implements IDState {
   static final ILogger q = GlobalLog.getLogger(bye.class);
   public static int RF = 1;
   public static final int xK = 1;
   public static final int Dw = 2;
   public static final int Uv = 255;
   private static final int gP = 10000;
   private volatile boolean za;
   private Map lm = new HashMap();
   private IDGlobalContext zz;
   private IDexUnit JY;
   private IJavaTypeFactory HF;
   private boolean LK;
   private int io;
   private int qa = 100;
   private long Hk = 1000L;
   private int Me = 0;
   private long PV = 0L;
   private int oQ = 50;
   private int xW = 0;
   private int KT = 0;
   private static final int Gf = 209715200;
   private Watchdog Ef;
   private cad cC;
   private boolean sH;
   private boolean CE;
   private boolean wF;
   private cab If;
   private int Dz;
   private boolean mI = true;
   private boolean jq = true;
   private List ui = new ArrayList();
   private Deque TX = new ArrayDeque();
   private Object Rr;
   private IDEmuContext EB;
   private IDEmuFrame Xo;
   private bkp Bu;
   private bkq IN;
   private final Object rL = new Object();
   private int eJ = 0;
   private int YN = 0;
   private DEmuExternalPolicy Rv = null;
   private boolean zx;
   private bxs ZT;
   private int Ri = 10000;
   private IdentityHashMap GY = new IdentityHashMap();
   private Map Wx = new HashMap();
   Map oW = new HashMap();
   Set gO = new HashSet();
   private List AB = new ArrayList();
   private int CY;
   private int[] WI = new int[3];
   private LinkedHashSet Tq = new LinkedHashSet();
   private boolean Yp = true;
   private caj Gu = new caj();
   private bya nY = new bya();
   private IEmulatedAndroid lF;
   private buo nq;
   private CacheMap NX;
   private Map br = new HashMap();
   public int nf;
   private static final AtomicLong tW = new AtomicLong(1L);
   private static final String ZA = cvm.q(
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
         32,
         69,
         65,
         66,
         74,
         2,
         30,
         18,
         78,
         87,
         71,
         123,
         90,
         95,
         84,
         46,
         28,
         2,
         6,
         68,
         76,
         108,
         11,
         15,
         18,
         78,
         64,
         30,
         65,
         7,
         19,
         92,
         111,
         3,
         12,
         3,
         10,
         24,
         82
      },
      2,
      174
   );
   private static final String Ov = cvm.q(
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
         64,
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
         111,
         33,
         5,
         21,
         26,
         6,
         82
      },
      2,
      252
   );
   private static final String Lj = cvm.q(
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
         91,
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
         86,
         34,
         64,
         28,
         24,
         28,
         14,
         72,
         39,
         22,
         74,
         77,
         0,
         93,
         27
      },
      2,
      77
   );
   private static final String nv = cvm.q(
      new byte[]{
         21,
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
      89
   );
   private static final String LL = cvm.q(
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
         80,
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
      35
   );
   private static final Set PQ = new HashSet();
   private static final Set fQ = new HashSet();

   static final void q(int var0, String var1, Object... var2) {
   }

   public bye(IDGlobalContext var1) {
      this.zz = var1;
      this.JY = var1.getDex();
      this.HF = var1.getTypeFactory();
      this.nq = new buo(var1);
      this.NX = new CacheMap(30);
      this.Uv();
   }

   public bye q() {
      bye var1 = new bye(this.zz);
      var1.za = this.za;
      var1.lm = this.lm;
      var1.zz = this.zz;
      var1.JY = this.JY;
      var1.HF = this.HF;
      var1.qa = this.qa;
      var1.Hk = this.Hk;
      var1.Me = this.Me;
      var1.PV = this.PV;
      var1.Ef = this.Ef;
      var1.cC = null;
      var1.sH = false;
      var1.CE = this.CE;
      var1.LK = this.LK;
      var1.io = this.io;
      var1.wF = this.wF;
      var1.mI = this.mI;
      var1.jq = this.jq;
      this.TX.forEach(var1x -> var1.TX.add(var1x.copy()));
      var1.EB = (IDEmuContext)var1.TX.peek();
      var1.Xo = var1.EB == null ? null : ((byb)var1.EB).RF();
      var1.Bu = this.Bu;
      var1.IN = this.IN;
      var1.eJ = this.eJ;
      var1.YN = this.YN;
      var1.zx = this.zx;
      var1.Rv = this.Rv;
      var1.ZT = this.ZT;
      var1.Ri = this.Ri;
      var1.GY = this.GY;
      var1.Wx = this.Wx;
      var1.oW = this.oW;
      var1.gO = this.gO;
      var1.AB = this.AB;
      var1.CY = this.CY;
      var1.WI = Arrays.copyOf(this.WI, this.WI.length);
      var1.Tq = new LinkedHashSet(this.Tq);
      var1.Yp = this.Yp;
      var1.Gu = this.Gu;
      var1.nY = this.nY;
      return var1;
   }

   @Override
   public void dispose() {
      if (!this.RF()) {
         if (this.cC != null) {
            this.cC.q();
         }

         this.cC = null;
         if (this.If != null) {
            ;
         }

         this.If = null;
         this.zz = null;
      }
   }

   public boolean RF() {
      return this.zz == null;
   }

   @Override
   public File setSandboxDroppedFilesCollection(String var1, boolean var2) throws IOException {
      if (!this.isSandboxEnabled()) {
         throw new IllegalStateException("The sandbox is disabled");
      } else {
         return this.gO().q(var1, var2);
      }
   }

   @Override
   public File getSandboxDropFolder() {
      if (!this.isSandboxEnabled()) {
         throw new IllegalStateException("The sandbox is disabled");
      } else {
         return this.gO().xK();
      }
   }

   public boolean xK() {
      return this.za;
   }

   public void Dw() {
      this.za = true;
   }

   public void Uv() {
      this.qa = 100;
      this.Hk = 1000L;
      this.Me = 0;
      this.PV = 0L;
   }

   @Override
   public int getMaxIterationCount() {
      return this.qa;
   }

   @Override
   public int setMaxIterationCount(int var1) {
      if (var1 < 0) {
         var1 = Integer.MAX_VALUE;
      }

      int var2 = this.qa;
      this.qa = var1;
      return var2;
   }

   @Override
   public int getCurrentIterationCount() {
      return this.Me;
   }

   @Override
   public int getIterationCountLeft() {
      return Math.max(0, this.qa - this.Me);
   }

   @Override
   public long getMaxDuration() {
      return this.Hk;
   }

   @Override
   public long setMaxDuration(long var1) {
      if (var1 < 0L) {
         var1 = Long.MAX_VALUE;
      }

      long var3 = this.Hk;
      this.Hk = var1;
      return var3;
   }

   @Override
   public long getCurrentDuration() {
      return this.PV;
   }

   @Override
   public long getTimeLeft() {
      return Math.max(0L, this.Hk - this.PV);
   }

   long q(long var1) {
      long var3 = var1 + this.getTimeLeft();
      if (var3 < 0L) {
         var3 = Long.MAX_VALUE;
      }

      return var3;
   }

   @Override
   public Watchdog getWatchdog() {
      return this.Ef;
   }

   @Override
   public Watchdog setWatchdog(Watchdog var1) {
      Watchdog var2 = this.Ef;
      this.Ef = var1;
      return var2;
   }

   @Override
   public boolean canRun() {
      return this.getIterationCountLeft() > 0 && this.getTimeLeft() > 0L;
   }

   @Override
   public int setMaxInvocationDepth(int var1) {
      int var2 = this.oQ;
      this.oQ = var1;
      return var2;
   }

   @Override
   public int getMaxInvocationDepth() {
      return this.oQ;
   }

   @Override
   public IDGlobalContext getGlobalContext() {
      return this.zz;
   }

   @Override
   public IDexDecompilerUnit getDecompiler() {
      return this.zz.getDecompiler();
   }

   @Override
   public IDexUnit getDex() {
      return this.JY;
   }

   @Override
   public IApkUnit getApk() {
      return DexUtil.findParentApk(this.JY);
   }

   public IEmulatedAndroid q(IEmulatedAndroid var1) {
      IEmulatedAndroid var2 = this.lF;
      this.lF = var1;
      return var2;
   }

   @Override
   public IEmulatedAndroid getEmulatedEnvironment() {
      return this.lF;
   }

   public IJavaTypeFactory oW() {
      return this.HF;
   }

   @Override
   public IJLSTypeAdapter getTypeAdapter() {
      if (this.IN == null) {
         synchronized (this.rL) {
            if (this.Bu == null) {
               this.Bu = new bkp(this.JY, false, false);
            }

            if (this.IN == null) {
               if (this.isSandboxEnabled()) {
                  this.IN = new bkq(this.Bu, this.cC.oW());
               } else {
                  this.IN = new bkq(this.Bu);
               }
            }
         }
      }

      return this.IN;
   }

   public cad gO() {
      if (this.cC == null) {
         throw new IllegalStateException(
            cvm.q(new byte[]{23, 7, 21, 89, 1, 8, 9, 12, 22, 79, 80, 67, 64, 83, 17, 87, 86, 71, 12, 69, 92, 81, 80, 88, 73, 68}, 2, 183)
         );
      } else {
         return this.cC;
      }
   }

   public cah nf() {
      return this.gO().Dw();
   }

   @Override
   public void addClassfilesToSandbox(File var1) throws IOException {
      this.nf().q(var1);
   }

   public bxs gP() {
      if (this.ZT == null) {
         throw new IllegalStateException(
            cvm.q(new byte[]{95, 60, 13, 69, 83, 18, 15, 10, 6, 13, 23, 88, 73, 26, 83, 78, 1, 27, 84, 69, 11, 15, 3, 14, 9, 1}, 1, 11)
         );
      } else {
         return this.ZT;
      }
   }

   @Override
   public void enableExceptionHandling(boolean var1) {
      this.LK = var1;
   }

   @Override
   public boolean setExceptionHandlingEnabled(boolean var1) {
      boolean var2 = this.LK;
      this.LK = var1;
      return var2;
   }

   @Override
   public boolean isExceptionHandlingEnabled() {
      return this.LK;
   }

   @Override
   public boolean enableSandbox(boolean var1) {
      if (var1 && this.cC != null) {
         this.sH = true;
         this.IN = null;
         return true;
      } else if (!var1) {
         this.sH = false;
         this.IN = null;
         return true;
      } else {
         if (var1 != this.sH) {
            if (this.cC == null) {
               try {
                  this.cC = new cad(this);
               } catch (Exception var2) {
                  return false;
               }

               this.ZT = new bxs(this);
            }

            this.sH = var1;
            this.IN = null;
         }

         return true;
      }
   }

   @Override
   public boolean isSandboxEnabled() {
      return this.cC != null && this.sH;
   }

   public cad za() throws DexDecEvaluationException {
      if (!this.isSandboxEnabled()) {
         throw new DexDecEvaluationException(cvm.q(new byte[]{0, 14, 30, 23, 29, 29, 71, 29, 7, 69, 8, 16, 72, 78, 85, 91, 86, 75}, 2, 173));
      } else {
         return this.cC;
      }
   }

   @Override
   public void enableEmulator(boolean var1) {
      this.CE = var1;
   }

   @Override
   public boolean isEmulatorEnabled() {
      return this.CE;
   }

   public cab lm() {
      if (this.If == null) {
         Object[] var10000 = new Object[0];
         this.If = new cab(this, null);
      }

      return this.If;
   }

   public cjo zz() {
      cab var1 = this.lm();

      try {
         var1.RF();
      } catch (DexDecNativeEvalFailedException var3) {
         throw new RuntimeException(var3);
      }

      return var1.xK();
   }

   public void JY() throws DexDecEvaluationException {
      if (!this.CE) {
         throw new DexDecEvaluationException(cvm.q(new byte[]{0, 14, 30, 23, 29, 29, 71, 29, 7, 69, 8, 6, 68, 85, 93, 88, 77, 92, 94}, 2, 96));
      }
   }

   public caj HF() {
      return this.Gu;
   }

   @Override
   public int registerSandboxHooks(IDSandboxHooks var1) {
      return this.Gu.q(var1, true);
   }

   @Override
   public boolean unregisterSandboxHooks(int var1) {
      return this.Gu.RF(var1);
   }

   @Override
   public void enableNativeCodeEmulator(boolean var1) {
      this.wF = var1;
      if (!var1) {
         this.If = null;
      }
   }

   @Override
   public boolean isNativeCodeEmulatorEnabled() {
      return this.wF;
   }

   @Override
   public int registerNativeEmulatorHooks(IEEmulatorHooks var1) {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new IllegalStateException(
            cvm.q(
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
                  (byte)64,
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
               84
            )
         );
      } else {
         return this.lm().q(var1);
      }
   }

   @Override
   public boolean unregisterNativeEmulatorHooks(int var1) {
      return this.lm().q(var1);
   }

   @Override
   public int registerNativeEmulatorMemoryHooks(IEStateHooks var1) {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new IllegalStateException(
            cvm.q(
               new byte[]{
                  (byte)89,
                  (byte)60,
                  (byte)13,
                  (byte)69,
                  (byte)78,
                  (byte)15,
                  (byte)21,
                  (byte)29,
                  (byte)31,
                  (byte)19,
                  (byte)69,
                  (byte)69,
                  (byte)8,
                  (byte)24,
                  (byte)25,
                  (byte)13,
                  (byte)21,
                  (byte)27,
                  (byte)29,
                  (byte)82,
                  (byte)77,
                  (byte)24,
                  (byte)6,
                  (byte)7,
                  (byte)84,
                  (byte)66,
                  (byte)7,
                  (byte)69,
                  (byte)69,
                  (byte)11,
                  (byte)15,
                  (byte)3,
                  (byte)14,
                  (byte)9,
                  (byte)1
               },
               1,
               13
            )
         );
      } else {
         return this.lm().q(var1);
      }
   }

   @Override
   public boolean unregisterNativeEmulatorMemoryHooks(int var1) {
      return this.lm().RF(var1);
   }

   public int LK() {
      return this.Dz;
   }

   @Override
   public boolean isLazyJNIOnLoadExec() {
      return this.mI;
   }

   @Override
   public boolean setLazyJNIOnLoadExec(boolean var1) {
      boolean var2 = this.mI;
      this.mI = var1;
      return var2;
   }

   @Override
   public boolean isRequireNonNullObjectForNonStaticInvoke() {
      return this.jq;
   }

   @Override
   public boolean setRequireNonNullObjectForNonStaticInvoke(boolean var1) {
      boolean var2 = this.jq;
      this.jq = var1;
      return var2;
   }

   static final long io() {
      return tW.getAndIncrement();
   }

   public bya qa() {
      return this.nY;
   }

   @Override
   public int registerEmulatorHooks(IDEmulatorHooks var1) {
      return this.nY.q(var1, true);
   }

   @Override
   public boolean unregisterEmulatorHooks(int var1) {
      return this.nY.RF(var1);
   }

   @Override
   public boolean setPerformDirectUnreflection(boolean var1) {
      boolean var2 = this.Yp;
      this.Yp = var1;
      return var2;
   }

   @Override
   public boolean isPerformDirectUnreflection() {
      return this.Yp;
   }

   @Override
   public void registerExecutionHelper(IDMethodExecutionHelper var1) {
      this.lm.put(var1.getMethodSignature(), var1);
   }

   public void q(Collection var1) {
      var1.forEach(var1x -> this.registerExecutionHelper(var1x));
   }

   @Override
   public boolean unregisterExecutionHelper(String var1) {
      return this.lm.remove(var1) != null;
   }

   @Override
   public Collection getExecutionHelpers() {
      return Collections.unmodifiableCollection(this.lm.values());
   }

   public byd q(Thread var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         byd var2 = new byd(this, var1);
         this.ui.add(var2);
         return var2;
      }
   }

   public List Hk() {
      return this.ui;
   }

   public byd RF(Thread var1) {
      for (byd var3 : this.ui) {
         if (var3.RF == var1) {
            return var3;
         }
      }

      throw new IllegalArgumentException();
   }

   public Object Me() {
      return this.Rr;
   }

   public void q(Object var1) {
      this.Rr = var1;
   }

   public String PV() {
      if (this.getCurrentFrame() == null) {
         return null;
      } else {
         String var1 = this.getCurrentFrame().getMethodSignature();
         int var2 = this.KT();
         return var2 < 0 ? var1 + "+???" : Strings.ff("%s+%Xh", var1, var2);
      }
   }

   @Override
   public IDEmuContext getCurrentContext() {
      return this.EB;
   }

   @Override
   public IDEmuContext pushContext(String var1) {
      this.EB = new byb(var1);
      this.TX.push(this.EB);
      this.Xo = null;
      return this.EB;
   }

   public IDEmuContext oQ() {
      IDEmuContext var1 = (IDEmuContext)this.TX.pop();
      this.EB = (IDEmuContext)this.TX.peek();
      this.Xo = null;
      if (this.EB != null) {
         byb var2 = (byb)this.EB;
         this.Xo = var2.RF();
      }

      return var1;
   }

   @Override
   public IDEmuFrame getCurrentFrame() {
      return this.Xo;
   }

   @Override
   public IDEmuFrame pushFrame(String var1) {
      this.Xo = new byc(var1);
      ((byb)this.EB).q(this.Xo);
      return this.Xo;
   }

   public IDEmuFrame xW() {
      byb var1 = (byb)this.EB;
      IDEmuFrame var2 = var1.q();
      this.Xo = var1.RF();
      return var2;
   }

   @Override
   public IDEmuContext deleteTopContext() {
      return this.oQ();
   }

   @Override
   public IDEmuFrame deleteTopFrame() {
      return this.xW();
   }

   @Override
   public Collection getContexts() {
      return Collections.unmodifiableCollection(this.TX);
   }

   private void q(int var1, IDImm var2) throws DexDecEvaluationException {
      if (var2.getType().isSingleSlot()) {
         int var3 = DUtil.createRegisterVarId(var1, false);
         this.Xo.setVariable(var3, var2);
      } else {
         if (!var2.getType().isDoubleSlot()) {
            throw new DexDecEvaluationException();
         }

         int var4 = DUtil.createRegisterVarId(var1, true);
         this.Xo.setVariable(var4, var2);
      }
   }

   @Override
   public int getCountOfContexts() {
      return this.TX.size();
   }

   @Override
   public void setVariable(int var1, IDImm var2) throws DexDecEvaluationException {
      this.Xo.setVariable(var1, var2);
   }

   @Override
   public IDImm getVariable(int var1, boolean var2) throws DexDecEvaluationException {
      return this.Xo.getVariable(var1, var2);
   }

   @Override
   public IDImm getVariable(int var1) throws DexDecEvaluationException {
      return this.Xo.getVariable(var1);
   }

   @Override
   public boolean hasVariable(int var1) throws DexDecEvaluationException {
      return this.Xo.hasVariable(var1);
   }

   @Override
   public boolean deleteVariable(int var1) throws DexDecEvaluationException {
      return this.Xo.deleteVariable(var1);
   }

   @Override
   public void setPC(int var1) {
      this.Xo.setPC(var1);
   }

   @Override
   public int getPC() {
      return this.Xo.getPC();
   }

   public void q(int var1) {
      ((byc)this.Xo).RF(var1);
   }

   public int KT() {
      return ((byc)this.Xo).q();
   }

   public Map Gf() {
      return this.Xo.getVarMap();
   }

   boolean Ef() {
      return this.q(false);
   }

   boolean q(boolean var1) {
      boolean var2 = !this.EB.hasFrames();
      if (var2 && var1) {
         this.oQ();
      }

      return var2;
   }

   boolean cC() {
      return this.TX.isEmpty();
   }

   public void RF(int var1) {
      byc var2 = (byc)this.Xo;
      var2.q(var1);
      var2.RF();
      this.Me++;
      if (this.Me % 10000000 == 0) {
         Object[] var10000 = new Object[]{this.Me};
      }
   }

   public int sH() {
      return this.Xo.updatePC();
   }

   public IDImm CE() throws DexDecEvaluationException {
      IDImm var1 = this.Xo.getRaisedException();
      if (var1 == null) {
         throw new DexDecEvaluationException(
            cvm.q(new byte[]{6, 23, 0, 28, 17, 29, 2, 12, 84, 65, 8, 16, 93, 79, 67, 92, 93, 19, 88, 72, 64, 95, 69, 85, 78, 76, 42, 83}, 2, 47)
         );
      } else {
         this.Xo.setRaisedException(null);
         return var1;
      }
   }

   @Override
   public int setSubRoutineInvocationPolicy(int var1) {
      int var2 = this.eJ;
      this.eJ = var1;
      return var2;
   }

   @Override
   public int getSubRoutineInvocationPolicy() {
      return this.eJ;
   }

   void wF() throws DexDecEvaluationException {
      if ((this.eJ & 1) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{-116, 34, 15, 0, 1, 27, 84, 69, 29, 29, 6, 22, 1, 17, 69, 73, 7, 26, 17, 23, 28, 15, 13, 76, 83, 6, 23, 79, 95, 29, 26, 1, 29, 7, 11},
               1,
               207
            )
         );
      }
   }

   void If() throws DexDecEvaluationException {
      if ((this.eJ & 2) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  0, 14, 30, 23, 29, 29, 71, 13, 12, 69, 75, 22, 93, 69, 17, 80, 87, 71, 73, 82, 92, 81, 94, 20, 95, 85, 45, 95, 19, 12, 25, 17, 73, 15, 11
               },
               2,
               204
            )
         );
      }
   }

   @Override
   public int setFieldAccessPolicy(int var1) {
      int var2 = this.YN;
      this.YN = var1;
      return var2;
   }

   @Override
   public int getFieldAccessPolicy() {
      return this.YN;
   }

   void Dz() throws DexDecEvaluationException {
      if ((this.YN & 4) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  -66,
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
               248
            )
         );
      }
   }

   void mI() throws DexDecEvaluationException {
      if ((this.YN & 8) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
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
                  73,
                  92,
                  68,
                  87,
                  70,
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
               73
            )
         );
      }
   }

   void jq() throws DexDecEvaluationException {
      if ((this.YN & 64) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  -6,
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
               188
            )
         );
      }
   }

   void ui() throws DexDecEvaluationException {
      if ((this.YN & 128) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
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
                  73,
                  92,
                  68,
                  87,
                  70,
                  66,
                  65,
                  35,
                  82,
                  18,
                  23,
                  13,
                  17,
                  73,
                  2,
                  78,
                  2,
                  70,
                  10,
                  30,
                  68,
                  26
               },
               2,
               115
            )
         );
      }
   }

   void TX() throws DexDecEvaluationException {
      if ((this.YN & 1) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
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
                  90,
                  77,
                  76,
                  111,
                  27,
                  15,
                  16,
                  24,
                  4,
                  78,
                  2,
                  11,
                  68,
                  73,
                  6,
                  23,
                  76,
                  13,
                  7
               },
               2,
               61
            )
         );
      }
   }

   void Rr() throws DexDecEvaluationException {
      if ((this.YN & 2) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
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
                  70,
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
               45
            )
         );
      }
   }

   void EB() throws DexDecEvaluationException {
      if ((this.YN & 16) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  -59,
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
               131
            )
         );
      }
   }

   void Xo() throws DexDecEvaluationException {
      if ((this.YN & 32) == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  21,
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
               83
            )
         );
      }
   }

   @Override
   public DEmuExternalPolicy setExternalPolicy(DEmuExternalPolicy var1) {
      DEmuExternalPolicy var2 = this.Rv;
      this.Rv = var1;
      return var2;
   }

   @Override
   public DEmuExternalPolicy getExternalPolicy() {
      return this.Rv;
   }

   @Override
   public boolean setStrictClassInit(boolean var1) {
      boolean var2 = this.zx;
      this.zx = var1;
      return var2;
   }

   @Override
   public boolean isStrictClassInit() {
      return this.zx;
   }

   public IDImm q(String var1) throws DexDecEvaluationException {
      bxy var2 = this.q(var1, true);
      if (var2.q()) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  50,
                  34,
                  15,
                  0,
                  1,
                  27,
                  84,
                  65,
                  13,
                  0,
                  3,
                  12,
                  2,
                  21,
                  17,
                  69,
                  65,
                  15,
                  78,
                  85,
                  27,
                  7,
                  7,
                  7,
                  29,
                  29,
                  8,
                  13,
                  5,
                  19,
                  31,
                  1,
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
                  79,
                  13,
                  8,
                  15,
                  6,
                  23
               },
               1,
               113
            )
         );
      } else {
         bxz var3 = this.q(var2);
         return this.registerObject(var3);
      }
   }

   public IDImm q(String var1, Collection var2, DInvokeType var3) throws DexDecEvaluationException {
      String var4 = null;
      if (var3 == DInvokeType.NEW) {
         var4 = var1.substring(0, var1.indexOf("->"));
      }

      return this.q(var1, var2, var3, var4);
   }

   public IDImm q(String var1, Collection var2, DInvokeType var3, String var4) throws DexDecEvaluationException {
      if (this.xW >= this.oQ) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{42, 61, 27, 6, 0, 1, 1, 1, 68, 77, 12, 25, 88, 65, 13, 0, 3, 24, 18, 1, 68, 73, 7, 24, 25, 12, 2, 21, 29, 6, 1, 78, 68, 1, 21, 4, 28},
               1,
               111
            )
         );
      } else {
         this.xW++;

         IDImm var5;
         try {
            var5 = this.RF(var1, var2, var3, var4);
         } finally {
            this.xW--;
         }

         return var5;
      }
   }

   private IDImm RF(String var1, Collection var2, DInvokeType var3, String var4) throws DexDecEvaluationException {
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
               cvm.q(
                     new byte[]{0, 14, 30, 23, 29, 29, 71, 13, 2, 65, 68, 22, 72, 84, 84, 25, 80, 93, 90, 79, 81, 81, 70, 93, 67, 78, 111, 6, 24, 19, 9, 95, 0},
                     2,
                     239
                  )
                  + var3
            );
      }

      JvmMethodSig var6 = JvmMethodSig.parse(var1);
      String var7 = var6.mname;
      int var8 = var6.partypes.size();
      boolean var9 = var7.equals("<init>");
      Assert.a(var4 == null || var9);
      if (this.eJ == 0) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  34, 39, 6, 27, 10, 23, 84, 78, 1, 79, 83, 6, 23, 79, 95, 29, 26, 1, 29, 7, 11, 69, 69, 29, 29, 6, 22, 1, 29, 6, 1, 78, 80, 31, 3, 5, 10, 26
               },
               1,
               113
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

         IDexMethod var27 = this.JY.getMethod(var1);
         boolean var28 = false;
         String var13 = null;
         IDexMethod var14 = null;
         if (var6.csig.startsWith("[")) {
            Couple var15 = JvmTypeSig.parseArrayType(var6.csig);
            bxy var16 = this.q((String)var15.getFirst(), true);
            if (var16.isInternal()) {
               var6.csig = Strings.generate('[', (Integer)var15.getSecond()) + JvmUtil.generateTypeSig(var16.Dw());
               var13 = var6.generate();
               var28 = true;
            }
         } else if (var3 == DInvokeType.STATIC) {
            this.q(var6.csig, true);
         }

         if (!var28) {
            if (!var5) {
               var14 = var27;
               var28 = var27 == null || !var27.isInternal();
               if (var28 && var3 == DInvokeType.STATIC) {
                  var13 = this.oW(var1);
                  var14 = var13 == null ? null : this.JY.getMethod(var13);
                  var28 = var14 == null || !var14.isInternal();
               }
            } else {
               Object var29 = this.getObject((IDImm)var10.get(0));
               if (var29 == null) {
                  if (this.jq) {
                     throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
                  }

                  List var32 = this.getTypeAdapter().getParentTypes(JvmMethodSig.parse(var1).csig);
                  var28 = false;
                  if (var32 != null && var32.size() == 1 && ((String)var32.get(0)).equals("Ljava/lang/Object;")) {
                     var13 = var1;
                     var14 = var1 == null ? null : this.JY.getMethod(var1);
                     var28 = var14 == null || !var14.isInternal();
                  }

                  if (var28) {
                     throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
                  }
               } else if (var3 == DInvokeType.SUPER) {
                  if (var29 instanceof bxz var33) {
                     var13 = var33.q(var1, this.getCurrentFrame().getMethodSignature());
                     var14 = var13 == null ? null : this.JY.getMethod(var13);
                     var28 = var14 == null || !var14.isInternal();
                  } else {
                     var28 = true;
                  }
               } else if (var29 instanceof bxz var34) {
                  var13 = var34.q(var1, var3 == DInvokeType.INTERFACE);
                  var14 = var13 == null ? null : this.JY.getMethod(var13);
                  var28 = var14 == null || !var14.isInternal();
               } else {
                  var28 = true;
               }
            }
         }

         if (!var28) {
            this.wF();
            ArrayList var31 = new ArrayList(var10);
            if (var14 == null || var14.getData() == null) {
               throw new DexDecEvaluationException(
                  cvm.q(
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
                           87,
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
                        87
                     )
                     + var1
               );
            } else if (var4 != null) {
               bxy var37 = this.q(var4, true);
               bxz var41 = this.q(var37);
               IDImm var44 = this.registerObject(var41);
               var31.add(0, var44);
               this.RF(var14, var31);
               return var44;
            } else if (!var14.getData().isNative()) {
               return this.RF(var14, var31);
            } else if (!this.isNativeCodeEmulatorEnabled()) {
               throw new DexDecEvaluationException(
                  cvm.q(
                     new byte[]{
                        -127,
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
                        77,
                        8,
                        17,
                        28,
                        7,
                        11,
                        23,
                        83,
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
                     196
                  )
               );
            } else {
               bye.CU var36 = null;
               if (!this.Tq.isEmpty()) {
                  var36 = (bye.CU)new ArrayList(this.Tq).get(this.Tq.size() - 1);
               }

               this.Dz++;
               return this.lm().q(var14, var31, var36);
            }
         } else {
            this.If();
            ArrayList var30 = new ArrayList(var10);
            if (var13 == null) {
               var13 = var1;
            }

            bxz var35 = null;
            if (var9 && var10.size() != var8) {
               Assert.a(var10.size() == var8 + 1);
               IDImm var38 = (IDImm)var30.remove(0);
               Object var42 = this.getObject(var38);
               Assert.a(var42 instanceof bxz);
               var35 = (bxz)var42;
            } else if (var3 != DInvokeType.STATIC && var3 != DInvokeType.NEW && var30.size() > 0) {
               IDImm var17 = (IDImm)var30.get(0);
               Object var18 = this.getObject(var17);
               if (var18 instanceof bxz) {
                  var30.set(0, ((bxz)var18).q);
               }
            }

            if (var35 == null && this.Yp) {
               IDImm[] var39 = new IDImm[1];
               if (this.q(var13, var30, var39)) {
                  return var39[0];
               }
            }

            IDImm var40 = null;
            boolean var43 = false;
            IDMethodExecutionHelper var19 = (IDMethodExecutionHelper)this.lm.get(var13);
            if (var19 != null) {
               try {
                  var40 = var19.simulateExecution(this, var30);
                  if (var40 != null) {
                     var43 = true;
                  }
               } catch (Exception var25) {
                  throw new DexDecEvalCodeThrownException(this.registerObject(var25));
               }
            }

            if (!var43) {
               var40 = this.nq.q(var13, var30, null);
               if (var40 != null) {
                  var43 = true;
               }
            }

            if (!var43) {
               this.za();
               bxy.eo var20 = var35 == null ? null : var35.RF().Uv();

               try {
                  var40 = this.cC.q(var3, var13, var30, var20);
               } catch (DexDecEvalSandboxExecutionException var26) {
                  Throwable var22 = var26.getCause();
                  Object var23;
                  if (var22 instanceof InvocationTargetException) {
                     var23 = var22.getCause();
                     if (this.KT >= 1) {
                        int var47 = this.KT;

                        while (var47-- > 0) {
                           var23 = new InvocationTargetException((Throwable)var23);
                        }
                     }
                  } else {
                     var23 = var22;
                     if (this.KT >= 1) {
                        int var24 = this.KT;

                        while (var24-- > 0) {
                           var23 = new InvocationTargetException((Throwable)var23);
                        }
                     }
                  }

                  throw new DexDecEvalCodeThrownException(this.registerObject(var23));
               }
            }

            if (!this.mI) {
               String var45 = null;
               boolean var21 = false;
               switch (cvm.xK(var13)) {
                  case -2088301382:
                     var45 = this.getStringObject((IDImm)var30.get(1));
                     var21 = true;
                     break;
                  case -1936802804:
                     var45 = this.getStringObject((IDImm)var30.get(0));
                     var21 = true;
                     break;
                  case -1280323317:
                     var45 = this.getStringObject((IDImm)var30.get(1));
                     break;
                  case -452023474:
                     var45 = this.getStringObject((IDImm)var30.get(0));
               }

               if (var45 != null) {
                  Object[] var49 = new Object[]{var45, var21};
                  bye.CU var46 = new bye.CU(var45, var21);
                  this.Dz++;
                  this.lm().q(var46);
               }
            }

            if (var35 != null) {
               this.q(var35, var40);
               var40 = this.registerObject(var35);
            }

            return var40;
         }
      }
   }

   boolean q(String var1, List var2, IDImm[] var3) throws DexDecEvaluationException {
      if (var2.isEmpty()) {
         return false;
      } else if (var1.equals(Ov) || var1.equals(ZA)) {
         Couple var39 = null;
         if (var1.equals(ZA)) {
            Object var46 = this.getObject((IDImm)var2.get(0), true);
            if (var46 instanceof Class) {
               try {
                  Constructor var42 = ((Class)var46).getConstructor();
                  var39 = this.q(var42, null);
               } catch (Exception var34) {
               }
            }
         } else {
            var39 = this.q(this.getObject((IDImm)var2.get(0), true), null);
         }

         if (var39 == null) {
            return false;
         } else {
            String var43 = (String)var39.getFirst();
            Object[] var80 = new Object[]{var43};
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
                        var50.add(this.q(var77, var75));
                     } else {
                        var50.add(this.q(var75, true));
                     }

                     var60++;
                  }
               }
            } catch (Exception var36) {
               return false;
            }

            this.KT++;

            IDImm var54;
            try {
               var54 = this.q(var43, var50, DInvokeType.NEW, var47.csig);
            } finally {
               this.KT--;
            }

            var3[0] = var54;
            return true;
         }
      } else if (var1.equals(Lj)) {
         Object var38 = this.getObject((IDImm)var2.get(0), true);
         Object var41 = this.getObject((IDImm)var2.get(1), true);
         Couple var45 = this.q(var38, var41);
         if (var45 == null) {
            return false;
         } else {
            String var49 = (String)var45.getFirst();
            Boolean var52 = (Boolean)var45.getSecond();
            Object[] var79 = new Object[]{var49};
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
                        var63.add(this.q(var20, var19));
                     } else {
                        var63.add(this.q(var19, true));
                     }

                     var76++;
                  }
               }
            } catch (Exception var35) {
               return false;
            }

            this.KT++;

            IDImm var68;
            try {
               var68 = this.q(var49, var63, var59, null);
            } finally {
               this.KT--;
            }

            if (var56.rettype.length() == 1) {
               if (var56.rettype.equals("V")) {
                  var68 = this.zz.createNull();
               } else {
                  var68 = this.RF(var68, var56.rettype.charAt(0));
               }
            }

            var3[0] = var68;
            return true;
         }
      } else if (PQ.contains(var1)) {
         Object var37 = this.getObject((IDImm)var2.get(0), true);
         Object var40 = this.getObject((IDImm)var2.get(1), true);
         Couple var44 = this.q(var37, var40);
         if (var44 == null) {
            return false;
         } else {
            String var48 = (String)var44.getFirst();
            Boolean var51 = (Boolean)var44.getSecond();
            Object[] var78 = new Object[]{var48};
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

            if (var1.equals(nv)) {
               if (var55.ftype.length() == 1) {
                  var62 = this.RF(var62, var55.ftype.charAt(0));
               }
            } else {
               char var66 = var1.charAt(var1.length() - 1);
               char var71 = var55.ftype.charAt(0);
               if (var71 == '[' || var71 == 'L') {
                  return false;
               }

               if (var66 != var71) {
                  if (!bto.q(var71, var66)) {
                     return false;
                  }

                  var62 = bto.q(this.zz, var62, var71, var66);
               }
            }

            var3[0] = var62;
            return true;
         }
      } else if (!fQ.contains(var1)) {
         return false;
      } else {
         Object var4 = this.getObject((IDImm)var2.get(0), true);
         Object var5 = this.getObject((IDImm)var2.get(1), true);
         Couple var6 = this.q(var4, var5);
         if (var6 == null) {
            return false;
         } else {
            String var7 = (String)var6.getFirst();
            Boolean var8 = (Boolean)var6.getSecond();
            Object[] var10000 = new Object[]{var7};
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
            if (var1.equals(LL)) {
               char var65 = var9.ftype.charAt(0);
               String var70 = null;
               Object var14 = this.getObject((IDImm)var2.get(2));
               if (var14 instanceof bxz) {
                  var70 = ((bxz)var14).RF().getFullName();
               } else if (var14 != null) {
                  var70 = JvmUtil.generateTypeSig(var14.getClass());
               }

               if (var65 != '[' && var65 != 'L') {
                  if (!bto.q(var70)) {
                     return false;
                  }

                  char var15 = bto.RF(var70);
                  var11 = this.q(var11, '\u0000');
                  if (!bto.q(var15, var65)) {
                     return false;
                  }

                  var11 = bto.q(this.zz, var11, var15, var65);
               } else {
                  if (var70 == null || var11.maybeNullRef()) {
                     return true;
                  }

                  if (!bkm.q(this.IN, var70, var9.ftype)) {
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
                  if (!bto.q(var12, var13)) {
                     return false;
                  }

                  var11 = bto.q(this.zz, var11, var12, var13);
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

   private Couple q(Object var1, Object var2) {
      if (var1 instanceof bxw) {
         String var8 = ((bxw)var1).Dw();
         Integer var10 = ((bxw)var1).gO();
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
            cvm.q(new byte[]{0, 14, 30, 23, 29, 29, 71, 26, 17, 83, 71, 15, 95, 69, 17, 86, 91, 89, 73, 67, 70, 16, 93, 82, 12, 84, 54, 2, 4, 89, 76}, 2, 19)
               + (var1 == null ? null : var1.getClass().getName())
         );
      }
   }

   IDImm q(IDImm var1, char var2) throws DexDecEvaluationException {
      if (var2 == 0) {
         String var3 = var1.getType().getSignature();
         var2 = JavaUtil.primitiveToLetter(JavaUtil.wrapperToPrimitive(var3)).charAt(0);
      }

      switch (var2) {
         case 'B':
            return this.zz.createConstant(((Byte)this.getObject(var1, true)).byteValue(), this.HF.getByte());
         case 'C':
            return this.zz.createConstant(((Character)this.getObject(var1, true)).charValue(), this.HF.getChar());
         case 'D':
            return this.zz.createConstant(Double.doubleToLongBits((Double)this.getObject(var1, true)), this.HF.getDouble());
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
            throw new RuntimeException(cvm.q(new byte[]{121, 27, 25, 5, 19, 17, 36, 59, 63, 34, 27, 4, 4, 29, 29, 31, 19, 69, 70, 7, 8, 5, 9, 1}, 1, 12));
         case 'F':
            return this.zz.createConstant(Float.floatToIntBits((Float)this.getObject(var1, true)), this.HF.getFloat());
         case 'I':
            return this.zz.createConstant(((Integer)this.getObject(var1, true)).intValue(), this.HF.getInt());
         case 'J':
            return this.zz.createConstant((Long)this.getObject(var1, true), this.HF.getLong());
         case 'S':
            return this.zz.createConstant(((Short)this.getObject(var1, true)).shortValue(), this.HF.getShort());
         case 'Z':
            return this.zz.createConstant(this.getObject(var1, true) ? 1L : 0L, this.HF.getBoolean());
      }
   }

   IDImm RF(IDImm var1, char var2) throws DexDecEvaluationException {
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
            throw new RuntimeException(cvm.q(new byte[]{62, 27, 25, 5, 19, 17, 36, 59, 63, 34, 27, 4, 4, 29, 29, 31, 19, 69, 70, 7, 8, 5, 9, 1}, 1, 75));
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

   public boolean RF(String var1) {
      boolean var2;
      if (this.Rv == null) {
         var2 = true;
      } else {
         var2 = this.Rv.canExecute(var1);
      }

      Object[] var10000 = new Object[]{var1, var2};
      return var2;
   }

   public boolean xK(String var1) {
      return this.Rv == null ? true : this.Rv.canAccess(var1);
   }

   public boolean Dw(String var1) {
      return this.Rv == null ? true : this.Rv.canAccess(var1);
   }

   public Object q(String var1, IDImm var2) throws DexDecEvaluationException {
      char var3 = var1.charAt(0);
      boolean var4 = var3 == '[' || var3 == 'L';
      Object var5 = null;
      if (var4) {
         if (var2.isString()) {
            return var2.getStringValue(this.zz);
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
            cvm.q(new byte[]{0, 14, 30, 23, 29, 29, 71, 26, 17, 83, 71, 15, 95, 69, 17, 77, 86, 19, 77, 78, 18, 95, 80, 94, 73, 67, 59, 72, 65}, 2, 88) + var2
         );
      } else {
         return var5;
      }
   }

   public IDImm q(String var1, Object var2) {
      if (var2 instanceof IDImm) {
         throw new RuntimeException(
            cvm.q(new byte[]{0, 14, 30, 23, 29, 29, 71, 26, 17, 71, 65, 16, 93, 69, 67, 25, 112, 97, 12, 99, 93, 94, 65, 64, 77, 78, 59}, 2, 246)
         );
      } else {
         if (var1.length() == 1) {
            switch (var1) {
               case "Z":
                  return this.zz.createConstant((Boolean)var2 ? 1L : 0L, this.HF.getBoolean());
               case "B":
                  return this.zz.createConstant(((Byte)var2).byteValue(), this.HF.getByte());
               case "C":
                  return this.zz.createConstant(((Character)var2).charValue(), this.HF.getChar());
               case "S":
                  return this.zz.createConstant(((Short)var2).shortValue() & 65535L, this.HF.getShort());
               case "I":
                  return this.zz.createConstant(((Integer)var2).intValue(), this.HF.getInt());
               case "J":
                  return this.zz.createConstant((Long)var2, this.HF.getLong());
               case "F":
                  return this.zz.createConstant(Float.floatToIntBits((Float)var2) & 4294967295L, this.HF.getFloat());
               case "D":
                  return this.zz.createConstant(Double.doubleToLongBits((Double)var2), this.HF.getDouble());
               case "V":
                  return null;
               case "L":
               case "[":
                  break;
               default:
                  throw new RuntimeException(
                     cvm.q(
                           new byte[]{
                              -63,
                              50,
                              15,
                              10,
                              6,
                              13,
                              23,
                              88,
                              82,
                              23,
                              17,
                              1,
                              7,
                              28,
                              78,
                              84,
                              13,
                              9,
                              21,
                              69,
                              67,
                              12,
                              1,
                              24,
                              19,
                              23,
                              1,
                              26,
                              6,
                              1,
                              78,
                              70,
                              9,
                              29,
                              72,
                              26
                           },
                           1,
                           146
                        )
                        + var1
                  );
            }
         }

         return this.q(var2, true);
      }
   }

   @Override
   public IDImm registerObject(Object var1) {
      return this.q(var1, false);
   }

   public IDImm q(Object var1, boolean var2) {
      if (var1 == null) {
         return this.zz.createNull();
      } else {
         synchronized (this.Wx) {
            if (var2) {
               if (cad.RF(var1)) {
                  var1 = this.RF(var1);
                  Assert.a(var1 != null);
               }

               if (cad.xK(var1)) {
                  var1 = this.RF((Class)var1);
                  Assert.a(var1 != null);
               }
            }

            Integer var4 = (Integer)this.GY.get(var1);
            if (var4 == null) {
               if (this.Ri < 0) {
                  throw new RuntimeException("object id overflow");
               }

               var4 = this.Ri;
               this.Ri++;
               this.Wx.put(var4, var1);
               this.GY.put(var1, var4);
            }

            return this.zz.createConstant(var4.intValue(), this.q(var1.getClass()));
         }
      }
   }

   @Override
   public Object releaseObject(IDImm var1) throws DexDecEvaluationException {
      synchronized (this.Wx) {
         Object var3 = this.Wx.remove(var1.getObjectReferenceId());
         if (var3 != null) {
            this.GY.remove(var3);
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
         return var1.getStringValue(this.zz);
      } else if (var1.getObjectReferenceId() == 0) {
         return null;
      } else {
         Object var3 = this.Wx.get(var1.getObjectReferenceId());
         if (var3 == null) {
            throw new DexDecEvaluationException(
               cvm.q(
                  new byte[]{
                     -115,
                     55,
                     3,
                     3,
                     23,
                     23,
                     11,
                     13,
                     6,
                     69,
                     67,
                     2,
                     15,
                     0,
                     1,
                     27,
                     84,
                     66,
                     7,
                     69,
                     69,
                     19,
                     23,
                     13,
                     25,
                     20,
                     21,
                     17,
                     1,
                     68,
                     65,
                     18,
                     83,
                     65,
                     15,
                     78,
                     79,
                     13,
                     8,
                     15,
                     6,
                     23
                  },
                  1,
                  223
               )
            );
         } else {
            if (var2) {
               if (var3 instanceof bxz) {
                  var3 = this.getObject(((bxz)var3).q, true);
               }

               if (var3 instanceof bxy) {
                  var3 = ((bxy)var3).Dw();
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
         Object var2 = this.Wx.get(var1);
         if (var2 == null) {
            throw new DexDecEvaluationException(cvm.q(new byte[]{13, 0, 80, 22, 16, 3, 2, 11, 0, 0, 95, 10, 93, 72, 17, 75, 92, 85, 69, 68, 18}, 2, 213) + var1);
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
         if (var2 instanceof bxz) {
            bxy var4 = ((bxz)var2).RF();
            var3 = var4.getFullName();
         } else {
            var3 = JvmUtil.generateTypeSig(var2.getClass());
         }

         IDImm var5 = this.getClassReference(var3);
         return var5.getObjectReferenceId();
      }
   }

   public bxy xK(int var1) throws DexDecEvaluationException {
      int var2 = this.getObjectClassId(var1);
      return var2 == 0 ? null : (bxy)this.Wx.get(var2);
   }

   @Override
   public String getStringObject(IDImm var1) throws DexDecEvaluationException {
      Object var2 = this.getObject(var1);
      if (var2 != null && var2.getClass() != String.class) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  -118,
                  55,
                  3,
                  3,
                  23,
                  23,
                  11,
                  13,
                  6,
                  69,
                  67,
                  2,
                  15,
                  0,
                  1,
                  27,
                  84,
                  66,
                  7,
                  69,
                  69,
                  19,
                  23,
                  13,
                  25,
                  20,
                  21,
                  17,
                  1,
                  68,
                  65,
                  18,
                  83,
                  65,
                  65,
                  83,
                  7,
                  6,
                  27,
                  7,
                  9
               },
               1,
               216
            )
         );
      } else {
         return (String)var2;
      }
   }

   public IJavaType q(Class var1) {
      if (var1.isPrimitive()) {
         return this.HF.primitiveNameToType(var1.getName());
      } else if (var1.isArray()) {
         String var3 = var1.getName().replace('.', '/');
         return this.HF.parseType(var3);
      } else {
         String var2 = "L" + var1.getName().replace('.', '/') + ";";
         return this.HF.parseType(var2);
      }
   }

   @Override
   public Object getArrayObject(IDImm var1) throws DexDecEvaluationException {
      if (var1.getObjectReferenceId() == 0) {
         return null;
      } else {
         Object var2 = this.Wx.get(var1.getObjectReferenceId());
         if (var2 != null && var2.getClass().isArray()) {
            return var2;
         } else {
            throw new DexDecEvaluationException(
               cvm.q(
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
                     88,
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
                  176
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
            cvm.q(
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
                  (byte)88,
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
               190
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

            return this.zz.createConstant(switch (var7) {
               case "boolean" -> Array.getBoolean(var3, var2) ? 1L : 0L;
               case "byte" -> Array.getByte(var3, var2);
               case "char" -> Array.getChar(var3, var2);
               case "short" -> Array.getShort(var3, var2) & 65535L;
               case "int" -> Array.getInt(var3, var2);
               case "long" -> Array.getLong(var3, var2);
               case "float" -> Float.floatToIntBits(Array.getFloat(var3, var2)) & 4294967295L;
               case "double" -> Double.doubleToLongBits(Array.getDouble(var3, var2));
               default -> throw new RuntimeException();
            }, this.q(var4));
         } else {
            Object var5 = Array.get(var3, var2);
            if (var5 != null && cad.RF(var5)) {
               var5 = this.RF(var5);
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
                  cvm.q(
                     new byte[]{
                        65,
                        34,
                        15,
                        0,
                        1,
                        27,
                        84,
                        83,
                        22,
                        17,
                        84,
                        65,
                        19,
                        0,
                        19,
                        24,
                        89,
                        69,
                        9,
                        9,
                        8,
                        8,
                        11,
                        26,
                        88,
                        12,
                        84,
                        13,
                        9,
                        21,
                        22,
                        83,
                        77,
                        4,
                        26,
                        30,
                        12,
                        21,
                        23,
                        11,
                        13,
                        1
                     },
                     1,
                     2
                  )
               );
            }
         }
      } else {
         throw new DexDecEvalCodeThrownException(this.registerObject(new ArrayIndexOutOfBoundsException()));
      }
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
               bxy var31 = this.q(var13, false);
               Object var22;
               if (var31.q()) {
                  Class var40 = var31.Dw();
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
               cvm.q(new byte[]{113, 39, 8, 5, 9, 1, 68, 67, 17, 23, 4, 21, 29, 7, 9, 71, 65, 19, 0, 19, 24, 89, 79, 9, 70, 84, 13, 9, 21, 95, 26}, 1, 55)
                  + var1
            );
         } else {
            return this.registerObject(var5);
         }
      }
   }

   @Override
   public IDImm getClassReference(String var1) throws DexDecEvaluationException {
      bxy var2 = this.q(var1, false);
      return this.registerObject(var2);
   }

   @Override
   public boolean isInstanceOf(IDImm var1, IDImm var2) throws DexDecEvaluationException {
      Object var3 = this.getObject(var1);
      if (var3 == null) {
         return false;
      } else {
         String var4;
         if (var3 instanceof bxz) {
            var4 = ((bxz)var3).RF().getFullName();
         } else if (var3 instanceof bxy) {
            var4 = cvm.q(new byte[]{-45, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 108, 47, 13, 18, 0, 72}, 1, 159);
         } else if (var3 instanceof bxw) {
            if (((bxw)var3).q()) {
               var4 = cvm.q(new byte[]{-99, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 93, 23, 3, 10, 9, 6, 23, 91, 105, 47, 12, 9, 8, 95}, 1, 209);
            } else if (((bxw)var3).RF()) {
               var4 = cvm.q(new byte[]{83, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 93, 23, 3, 10, 9, 6, 23, 91, 98, 40, 17, 28, 7, 11, 95}, 1, 31);
            } else {
               if (!((bxw)var3).xK()) {
                  throw new RuntimeException();
               }

               var4 = cvm.q(
                  new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 17, 76, 70, 93, 92, 90, 71, 3, 99, 93, 94, 65, 64, 94, 85, 44, 6, 14, 17, 87}, 2, 213
               );
            }
         } else {
            if (var3 instanceof bxx) {
               throw new RuntimeException();
            }

            var4 = JvmUtil.generateTypeSig(var3.getClass());
         }

         bxy var5 = (bxy)this.getObject(var2);
         String var6 = var5.getFullName();
         return bkm.q(this.getTypeAdapter(), var4, var6);
      }
   }

   @Override
   public IDImm getStaticField(String var1) throws DexDecEvaluationException {
      JvmFieldSig var2 = JvmFieldSig.parse(var1);
      return this.q(var2.csig, var2.fname, var2.ftype);
   }

   public IDImm q(String var1, String var2, String var3) throws DexDecEvaluationException {
      String var4 = var1 + "->" + var2 + ":" + var3;
      String var5 = this.gO(var4);
      if (var5 == null) {
         throw new DexDecEvaluationException(
            cvm.q(
                  new byte[]{
                     -15,
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
                  178
               )
               + var4
         );
      } else {
         JvmFieldSig var6 = JvmFieldSig.parse(var5);
         if (Strings.isContainedIn(var3, "Z", "B", "C", "S", "I", "J", "F", "D", "Ljava/lang/String;")) {
            IDexClass var7 = this.JY.getClass(var6.csig);
            if (var7 != null) {
               IDexField var8 = var7.getField(false, var2, var3);
               if (var8 != null) {
                  byte var9 = 24;
                  if ((var8.getData().getAccessFlags() & var9) == var9) {
                     IDexValue var10 = var8.getStaticInitializer();
                     if (var10 != null && var10.getType() != 30) {
                        boolean var11 = true;

                        for (IDexAddress var14 : this.JY.getCrossReferences(DexPoolType.FIELD, var8.getIndex())) {
                           if (var14.getReferenceType() == DexReferenceType.SET) {
                              var11 = false;
                              break;
                           }
                        }

                        if (var11) {
                           return this.q(var10, this.HF.createType(var3));
                        }
                     }
                  }
               }
            }
         }

         bxy var15 = this.q(var6.csig, true);
         IDImm var16 = var15.q(var2, var3);
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
      this.q(var3.csig, var3.fname, var3.ftype, var2);
   }

   public void q(String var1, String var2, String var3, IDImm var4) throws DexDecEvaluationException {
      String var5 = var1 + "->" + var2 + ":" + var3;
      String var6 = this.gO(var5);
      if (var6 == null) {
         throw new DexDecEvaluationException(
            cvm.q(
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
                     74,
                     77,
                     82,
                     88,
                     73,
                     81,
                     16,
                     84,
                     93,
                     73,
                     76,
                     43,
                     82,
                     7,
                     12,
                     30,
                     69,
                     87,
                     19,
                     7,
                     16,
                     70,
                     1,
                     21,
                     26,
                     73
                  },
                  2,
                  115
               )
               + var5
         );
      } else {
         JvmFieldSig var7 = JvmFieldSig.parse(var6);
         bxy var8 = this.q(var7.csig, true);
         if (!var8.q(var7.fname, var7.ftype, var4)) {
            throw new DexDecEvaluationException();
         }
      }
   }

   @Override
   public IDImm getInstanceField(String var1, IDImm var2) throws DexDecEvaluationException {
      if (var2.getObjectReferenceId() == 0) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else {
         if (this.getObject(var2) instanceof bxz var4) {
            IDImm var5 = var4.q(var1, var2);
            if (var5 != null) {
               this.Dz();
               return var5;
            }

            String var6 = var4.RF(var1);
            if (var6 == null) {
               throw new DexDecEvaluationException(
                  cvm.q(
                        new byte[]{
                           99,
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
                        32
                     )
                     + var1
               );
            }

            var2 = var4.q;
            var1 = var6;
         }

         this.TX();
         this.za();
         return this.cC.q(var1, var2);
      }
   }

   @Override
   public void setInstanceField(String var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      if (var2.getObjectReferenceId() == 0) {
         throw new DexDecEvalCodeThrownException(this.registerObject(new NullPointerException()));
      } else {
         if (this.getObject(var2) instanceof bxz var5) {
            if (var5.q(var1, var2, var3)) {
               this.mI();
               return;
            }

            String var6 = var5.RF(var1);
            if (var6 == null) {
               throw new DexDecEvaluationException(
                  cvm.q(
                        new byte[]{
                           -67,
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
                        254
                     )
                     + var1
               );
            }

            var2 = var5.q;
            var1 = var6;
         }

         this.Rr();
         this.za();
         this.cC.q(var1, var2, var3);
      }
   }

   @Override
   public Class loadClass(String var1) throws DexDecEvaluationException {
      return this.loadClass(var1, true);
   }

   @Override
   public Class loadClass(String var1, boolean var2) throws DexDecEvaluationException {
      bxy var3 = this.q(var1, var2);
      return var3.Dw();
   }

   public bxy q(String var1, boolean var2) throws DexDecEvaluationException {
      this.JY();
      int var3 = JvmTypeSig.getDimensionCount(var1);
      var1 = var1.substring(var3);
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var1);

      while (!var4.isEmpty()) {
         String var5 = (String)var4.remove();
         bxy var6 = (bxy)this.oW.get(var5);
         if (var6 != null) {
            if (var2 && !var6.isInitialized()) {
               var6.xK();
            }
         } else {
            IDexClass var7 = this.JY.getClass(var5);
            if (var7 == null) {
               this.za();
               Class var8 = this.cC.q(var5);
               var6 = new bxy(this, var5, var8);
               this.oW.put(var5, var6);
            } else {
               Assert.a(var5.equals(var7.getSignature(false)));
               if (this.gO.contains(var5)) {
                  throw new DexDecEvaluationException(
                     cvm.q(
                           new byte[]{
                              10,
                              1,
                              25,
                              13,
                              27,
                              8,
                              11,
                              1,
                              14,
                              73,
                              70,
                              4,
                              9,
                              79,
                              87,
                              25,
                              90,
                              95,
                              77,
                              83,
                              65,
                              16,
                              66,
                              70,
                              73,
                              86,
                              38,
                              29,
                              20,
                              16,
                              0,
                              28,
                              0,
                              7,
                              15,
                              13,
                              67,
                              10,
                              22,
                              26,
                              73
                           },
                           2,
                           184
                        )
                        + var5
                  );
               }

               var6 = new bxy(this, var5, var7);
               this.oW.put(var5, var6);
               if (var2) {
                  var6.xK();
               }

               var4.add(var7.getSupertypeSignature(false));
               var4.addAll(Arrays.asList(var7.getInterfaceSignatures(false)));
            }
         }
      }

      bxy var10 = (bxy)this.oW.get(var1);
      if (var3 > 0) {
         String var13 = Strings.generate('[', var3) + JvmUtil.generateTypeSig(var10.Dw());
         Class var14 = this.cC.q(var13);
         var10 = new bxy(this, var13, var14);
      }

      return var10;
   }

   IDImm q(IDexValue var1, IJavaType var2) throws DexDecEvaluationException {
      long var3 = 0L;
      IDImm var5 = null;
      switch (var1.getType()) {
         case 0:
            var3 = var1.getByte();
            this.HF.getByte();
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
               cvm.q(
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
                        80,
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
                     63
                  )
                  + var1.getType()
            );
         case 2:
            var3 = var1.getShort();
            this.HF.getShort();
            break;
         case 3:
            var3 = var1.getChar();
            this.HF.getChar();
            break;
         case 4:
            var3 = var1.getInt();
            this.HF.getInt();
            break;
         case 6:
            var3 = var1.getLong();
            this.HF.getLong();
            break;
         case 16:
            var3 = Float.floatToIntBits(var1.getFloat()) & 4294967295L;
            this.HF.getFloat();
            break;
         case 17:
            var3 = Double.doubleToLongBits(var1.getDouble());
            this.HF.getDouble();
            break;
         case 23:
            var5 = this.zz.createString(this.zz.createIndex(var1.getStringIndex()));
            var5 = var5.evaluate(this);
            break;
         case 28:
            IJavaType var6 = var2.getArrayTypeDimensionBelow();
            List var7 = var1.getArray();
            ArrayList var8 = new ArrayList(var7.size());

            for (IDexValue var10 : var7) {
               var8.add(this.q(var10, var6));
            }

            var5 = this.createArray(var2, var8.size(), var8);
            break;
         case 30:
            var3 = 0L;
            this.HF.getGenericObjectWildcard();
            break;
         case 31:
            var3 = var1.getBoolean() ? 1L : 0L;
            this.HF.getBoolean();
      }

      if (var5 == null) {
         var5 = this.zz.createConstant(var3, var2);
      }

      return var5;
   }

   public bxz q(bxy var1) throws DexDecEvaluationException {
      this.JY();
      bxz var2 = new bxz(this, var1);
      this.AB.add(var2);
      return var2;
   }

   void q(bxz var1, IDImm var2) throws DexDecEvaluationException {
      Assert.a(var1.q == null);
      Assert.a(cad.RF(this.getObject(var2)));
      var1.q = var2;
   }

   public bxz RF(Object param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield com/pnfsoftware/jebglobal/bye.AB Ljava/util/List;
      // 04: dup
      // 05: astore 2
      // 06: monitorenter
      // 07: aload 0
      // 08: getfield com/pnfsoftware/jebglobal/bye.AB Ljava/util/List;
      // 0b: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 10: astore 3
      // 11: aload 3
      // 12: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 17: ifeq 46
      // 1a: aload 3
      // 1b: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 20: checkcast com/pnfsoftware/jebglobal/bxz
      // 23: astore 4
      // 25: aload 4
      // 27: getfield com/pnfsoftware/jebglobal/bxz.q Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;
      // 2a: ifnull 3f
      // 2d: aload 0
      // 2e: aload 4
      // 30: getfield com/pnfsoftware/jebglobal/bxz.q Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;
      // 33: invokevirtual com/pnfsoftware/jebglobal/bye.getObject (Lcom/pnfsoftware/jeb/core/units/code/android/ir/IDImm;)Ljava/lang/Object;
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

   public bxy Uv(String var1) {
      return (bxy)this.oW.get(var1);
   }

   public bxy RF(Class param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield com/pnfsoftware/jebglobal/bye.oW Ljava/util/Map;
      // 04: dup
      // 05: astore 2
      // 06: monitorenter
      // 07: aload 0
      // 08: getfield com/pnfsoftware/jebglobal/bye.oW Ljava/util/Map;
      // 0b: invokeinterface java/util/Map.values ()Ljava/util/Collection; 1
      // 10: invokeinterface java/util/Collection.iterator ()Ljava/util/Iterator; 1
      // 15: astore 3
      // 16: aload 3
      // 17: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 1c: ifeq 3f
      // 1f: aload 3
      // 20: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 25: checkcast com/pnfsoftware/jebglobal/bxy
      // 28: astore 4
      // 2a: aload 4
      // 2c: invokevirtual com/pnfsoftware/jebglobal/bxy.Dw ()Ljava/lang/Class;
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

   public void Bu() {
      this.CY++;
   }

   public int IN() {
      return this.CY;
   }

   public void Dw(int var1) {
      this.WI[var1]++;
   }

   public int Uv(int var1) {
      return this.WI[var1];
   }

   public bye.CU RF(String var1, boolean var2) {
      bye.CU var3 = new bye.CU(var1, var2);
      this.Tq.add(var3);
      return var3;
   }

   public String oW(String var1) throws DexDecEvaluationException {
      IJLSTypeAdapter var2 = this.getTypeAdapter();
      JvmMethodSig var3 = JvmMethodSig.parse(var1);
      String var4 = var3.csig;
      String var5 = var3.mname;
      List var6 = var3.partypes;

      for (int var7 = 0; var4 != null; var7++) {
         String var8 = this.q(var2, var7, var4, var5, var6);
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

   private String q(IJLSTypeAdapter var1, int var2, String var3, String var4, List var5) {
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

   public String gO(String var1) throws DexDecEvaluationException {
      IJLSTypeAdapter var2 = this.getTypeAdapter();
      JvmFieldSig var3 = JvmFieldSig.parse(var1);
      String var4 = var3.csig;
      String var5 = var3.fname;
      String var6 = var3.ftype;

      for (int var7 = 0; var4 != null; var7++) {
         String var8 = this.q(var2, var7, var4, var5, var6);
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

   private String q(IJLSTypeAdapter var1, int var2, String var3, String var4, String var5) {
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

   public String q(String var1, int var2, int var3) throws DexDecEvaluationException {
      JvmMethodSig var4 = JvmMethodSig.parse(var1);
      String var5 = var4.csig;
      String var6 = var4.mname;
      List var7 = var4.partypes;
      return this.q(var5, var6, var7, var2, var3);
   }

   public String q(String var1, String var2, List var3, int var4, int var5) throws DexDecEvaluationException {
      IJLSTypeAdapter var6 = this.getTypeAdapter();
      if (var5 < 0) {
         var5 = Integer.MAX_VALUE;
      }

      for (int var7 = 0; var1 != null && var7 <= var5; var7++) {
         String var8 = this.q(var6, var4, var7, var1, var2, var3);
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

   private String q(IJLSTypeAdapter var1, int var2, int var3, String var4, String var5, List var6) {
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

   public String RF(String var1, int var2, int var3) throws DexDecEvaluationException {
      JvmFieldSig var4 = JvmFieldSig.parse(var1);
      String var5 = var4.csig;
      String var6 = var4.fname;
      String var7 = var4.ftype;
      return this.q(var5, var6, var7, var2, var3);
   }

   public String q(String var1, String var2, String var3, int var4, int var5) throws DexDecEvaluationException {
      IJLSTypeAdapter var6 = this.getTypeAdapter();
      if (var5 < 0) {
         var5 = Integer.MAX_VALUE;
      }

      for (int var7 = 0; var1 != null && var7 <= var5; var7++) {
         String var8 = this.q(var6, var4, var7, var1, var2, var3);
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

   private String q(IJLSTypeAdapter var1, int var2, int var3, String var4, String var5, String var6) {
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

   public IDImm nf(String var1) {
      return this.zz.createConstant(0L, switch (var1) {
         case "Z" -> this.HF.getBoolean();
         case "B" -> this.HF.getByte();
         case "C" -> this.HF.getChar();
         case "S" -> this.HF.getShort();
         case "I" -> this.HF.getInt();
         case "J" -> this.HF.getLong();
         case "F" -> this.HF.getFloat();
         case "D" -> this.HF.getDouble();
         default -> {
            if (var1.isEmpty() || var1.charAt(0) != '[' && var1.charAt(0) != 'L') {
               throw new RuntimeException(cvm.q(new byte[]{18, 37, 0, 9, 2, 6, 13, 76, 84, 13, 9, 21, 95, 26}, 1, 91) + var1);
            }

            yield this.HF.getGenericObjectWildcard();
         }
      });
   }

   @Override
   public IDImm createNewInstance(String var1, Collection var2) throws DexDecEvaluationException {
      return this.q(var1, var2, DInvokeType.NEW);
   }

   @Override
   public IDImm createNewInstance(String var1) throws DexDecEvaluationException {
      return this.createNewInstance(var1 + "-><init>()V", null);
   }

   @Override
   public IDImm invokeMethod(String var1, Collection var2, DInvokeType var3) throws DexDecEvaluationException {
      return this.q(var1, var2, var3, null);
   }

   @Override
   public IDImm invokeMethod(DInvokeType var1, String var2, IDExpression... var3) throws DexDecEvaluationException {
      return this.q(var2, Arrays.asList(var3), var1, null);
   }

   @Override
   public IDImm executeDexMethod(IDexMethod var1, List var2) throws DexDecEvaluationException {
      return this.q(var1, var2);
   }

   @Override
   public List getNativeLibraries() throws DexDecEvaluationException {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new DexDecEvaluationException(
            cvm.q(
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
                  81,
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
               28
            )
         );
      } else {
         return new ArrayList(this.lm().oW());
      }
   }

   @Override
   public INativeLibrary loadNativeLibrary(IELFUnit var1) throws DexDecEvaluationException {
      if (!this.isNativeCodeEmulatorEnabled()) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  -95,
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
               228
            )
         );
      } else {
         return this.lm().q(var1);
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
            cvm.q(
               new byte[]{
                  -33,
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
               154
            )
         );
      } else {
         bye.CU var3 = new bye.CU(var1, var2);
         return this.lm().q(var3);
      }
   }

   @Override
   public Map getRegisteredNatives() {
      return this.lm().Uv();
   }

   @Override
   public EEmulator getNativeEmulator() {
      return this.lm().Dw();
   }

   public IDImm q(IDexMethod var1, List var2) throws DexDecEvaluationException {
      this.pushContext(var1.getSignature());

      IDImm var3;
      try {
         var3 = this.RF(var1, var2);
      } finally {
         this.oQ();
      }

      return var3;
   }

   public IDImm RF(IDexMethod var1, List var2) throws DexDecEvaluationException {
      if (var1.getData() == null) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  122,
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
                  69,
                  29,
                  12,
                  17,
                  23,
                  28,
                  15,
                  13,
                  76,
                  77,
                  8,
                  17,
                  28,
                  7,
                  11
               },
               1,
               59
            )
         );
      } else if (var1.getData().getCodeItem() == null) {
         throw new DexDecEvaluationException(
            cvm.q(
               new byte[]{
                  -2,
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
               191
            )
         );
      } else {
         this.wF();
         String var3 = var1.getSignature(false);
         bye.eo var4 = this.NX == null ? null : (bye.eo)this.NX.get(var3);
         if (var4 == null) {
            bud var5 = (bud)((btx)this.zz).q(var1);
            buv var6 = var5.xK();

            try {
               var6.q(this.LK, false, false);
            } catch (DexDecConversionException var18) {
               throw new DexDecEvalFailedTranslationException(var18);
            }

            var4 = new bye.eo(var6.q(), var6.xK(), var6.Uv());
            if (this.NX != null) {
               this.NX.put(var3, var4);
            }
         }

         List var19 = var4.q;
         Map var20 = var4.RF;
         Map var7 = var4.xK;
         this.pushFrame(var3);
         SortedMap var8 = JavaTypeUtil.parseMethodParameters(this.JY, var1, this.HF);
         Set var9 = var8.keySet();
         var9.remove(-1);
         ArrayList var10 = new ArrayList(var9.size());
         int var11 = 0;

         for (int var13 : var9) {
            IDImm var14 = ((IDExpression)var2.get(var11)).evaluate(this);
            this.q(var13, var14);
            var10.add(var14);
            var11++;
         }

         TreeMap var21 = new TreeMap();
         var19.forEach(var1x -> var21.put((int)var1x.getOffset(), var1x));
         long var22 = 0L;
         if (this.qa().q()) {
            var22 = io();
            Wrapper var15 = this.qa().invokeMethod(var22, var3, var10);
            if (var15 != null) {
               this.Xo.setExecutionComplete(true);
               this.xW();
               return (IDImm)var15.get();
            }
         }

         this.nf++;
         DExecutionParameters var23 = new DExecutionParameters(var21, var20, var7);
         IDImm var16 = this.execute(var23);
         if (this.qa().q()) {
            Wrapper var17 = this.qa().examineMethodResult(var22, var16);
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
      long var14 = this.q(var39);

      try {
         IDImm var16 = null;

         while (this.getCurrentIterationCount() < this.qa && (var12 = System.currentTimeMillis()) < var14) {
            if (var1.iterationCountLeft != null) {
               if (var1.iterationCountLeft <= 0) {
                  return var16;
               }

               var1.iterationCountLeft = var1.iterationCountLeft - 1;
            }

            if ((this.Me & 15) == 0 && this.Ef != null) {
               this.Ef.verify();
            }

            int var17 = this.getPC();
            if (var17 == var5) {
               return var16;
            }

            if (var17 < var3 || var17 >= var4) {
               throw new DexDecEvaluationException(
                  cvm.q(
                     new byte[]{
                        -33,
                        19,
                        99,
                        79,
                        26,
                        1,
                        7,
                        26,
                        13,
                        1,
                        69,
                        76,
                        9,
                        2,
                        6,
                        13,
                        76,
                        82,
                        19,
                        15,
                        9,
                        2,
                        69,
                        70,
                        9,
                        29,
                        82,
                        67,
                        22,
                        7,
                        0,
                        23,
                        11,
                        26,
                        84,
                        77,
                        8,
                        17,
                        28,
                        7,
                        11
                     },
                     1,
                     143
                  )
               );
            }

            var16 = null;
            IDInstruction var18 = (IDInstruction)var6.get(var17);
            if (var18 == null) {
               throw new DexDecEvaluationException(Strings.ff("Cannot fetch instruction at 0x%X", var17));
            }

            this.q(var18.getPhysicalOffset());

            try {
               var16 = var18.evaluate(this);
            } catch (DexDecEvalCodeThrownException var36) {
               this.io++;
               if (!this.LK || var7 == null && (var8 == null || var9 == null)) {
                  throw var36;
               }

               if (this.io > 100000 && this.Me / this.io < 50) {
                  throw var36;
               }

               IDImm var20 = var36.getThrownObjectRef();
               this.Xo.setRaisedException(var20);
               Integer var22 = null;
               Object var23 = this.getObject(var20);
               String var21;
               if (var23 instanceof bxz var24) {
                  var21 = var24.RF().getFullName();
                  Object[] var10000 = new Object[]{var21};
               } else {
                  if (!(var23 instanceof Throwable var49)) {
                     throw var36;
                  }

                  var21 = JvmUtil.generateTypeSig(var49.getClass());
                  Object[] var62 = new Object[]{var49};
               }

               int var50 = var18.getPhysicalOffset();
               String var25 = this.Xo.getMethodSignature();
               IDexMethod var26 = this.JY.getMethod(var25);
               if (var8 != null && var9 != null) {
                  for (IDExceptionHandler var58 : var9.getBlockHandlers((int)var8.getBlockFor(var18).getBase())) {
                     String var60 = this.JY.getType(var58.getTypeIndex()).getSignature(false);
                     if (bkm.q(this.getTypeAdapter(), var21, var60)) {
                        var22 = var58.getAddress();
                        break;
                     }
                  }
               } else if (var7 != null) {
                  for (IDexExceptionHandler var29 : this.q(var26, var50)) {
                     String var30 = var29.resolveType(this.JY).getSignature(false);
                     if (bkm.q(this.getTypeAdapter(), var21, var30)) {
                        var22 = (Integer)var7.get(var29.getAddress());
                        break;
                     }
                  }
               }

               if (var22 == null) {
                  this.getCurrentFrame().setExecutionComplete(true);
                  this.xW();
                  throw var36;
               }

               this.RF(var22.intValue());
            } catch (DexDecEvaluationException var37) {
               throw var37;
            }

            boolean var19 = false;
            if (this.Xo.isExecutionComplete()) {
               if (var19 && var2 && this.Xo.getMethodSignature().endsWith("-><clinit>()V")) {
                  JvmMethodSig var42 = JvmMethodSig.parse(this.Xo.getMethodSignature());
                  IDexClass var44 = this.JY.getClass(var42.csig);
                  if (var44 != null && var44.isEnumeration()) {
                     IDexMethod var45 = var44.getMethod(true, "values");
                     if (var45 != null) {
                        IDImm var46 = this.q(var45, new ArrayList());
                        Object[] var51 = (Object[])this.getArrayObject(var46);
                        TreeMap var52 = new TreeMap();

                        for (Object var59 : var51) {
                           var46 = this.q("Ljava/lang/Enum;->name()Ljava/lang/String;", Arrays.asList(this.registerObject(var59)), DInvokeType.VIRTUAL, null);
                           String var61 = this.getStringObject(var46);
                           var46 = this.q("Ljava/lang/Enum;->ordinal()I", Arrays.asList(this.registerObject(var59)), DInvokeType.VIRTUAL, null);
                           int var31 = (int)var46.toLong();
                           var52.put(var31, var61);
                        }

                        Object[] var63 = new Object[]{var52};
                     }
                  }
               }

               if (var5 == -1) {
                  this.xW();
               }

               return var16;
            }

            this.sH();
         }

         if (var12 >= var14) {
            throw new DexDecEvalTimeoutExceededException(cvm.q(new byte[]{9, 61, 4, 8, 10, 26, 1, 84, 87, 22, 18, 73, 26}, 1, 93) + this.Hk);
         } else {
            throw new DexDecEvalItercountExceededException(
               cvm.q(new byte[]{-9, 44, 25, 88, 73, 29, 17, 23, 19, 21, 29, 6, 1, 78, 67, 12, 26, 27, 26, 84, 87, 22, 18, 73, 26}, 1, 186) + this.qa
            );
         }
      } finally {
         this.PV += var12 - var39;
      }
   }

   private List q(IDexMethod var1, int var2) {
      for (IDexExceptionItem var4 : var1.getData().getCodeItem().getExceptionItems()) {
         if (var2 >= var4.getTryAddress() && var2 < var4.getTryAddressEnd()) {
            return var4.getHandlers();
         }
      }

      return Collections.emptyList();
   }

   private String q(IDImm var1) {
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
      this.br.put(var1, var2);
   }

   @Override
   public Object getData(String var1) {
      return this.br.get(var1);
   }

   public String rL() {
      return Strings.ff("[*%d][CTX#%d=%s]", this.Me, this.TX.size(), this.Xo);
   }

   public String eJ() {
      StringBuilder var1 = new StringBuilder("dexdec stats:\n");
      Strings.ff(var1, "total iteration count: %d\n", this.Me);
      Strings.ff(var1, "total invoke dex count: %d\n", this.nf);
      if (this.cC != null) {
         var1.append(this.cC.gO());
      }

      return var1.toString();
   }

   @Override
   public String toString() {
      return this.rL();
   }

   static {
      PQ.add(nv);
      PQ.add(
         cvm.q(
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
               80,
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
            126
         )
      );
      PQ.add(
         cvm.q(
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
               80,
               23,
               13,
               113,
               21,
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
               90,
               108
            },
            2,
            54
         )
      );
      PQ.add(
         cvm.q(
            new byte[]{
               52,
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
            120
         )
      );
      PQ.add(
         cvm.q(
            new byte[]{
               -87,
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
            229
         )
      );
      PQ.add(
         cvm.q(
            new byte[]{
               61,
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
               61,
               39,
               26,
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
               96
            },
            1,
            113
         )
      );
      PQ.add(
         cvm.q(
            new byte[]{
               33,
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
               18,
               99
            },
            1,
            109
         )
      );
      PQ.add(
         cvm.q(
            new byte[]{
               57,
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
               50,
               42,
               3,
               14,
               21,
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
               111
            },
            1,
            117
         )
      );
      PQ.add(
         cvm.q(
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
               80,
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
            83
         )
      );
      fQ.add(LL);
      fQ.add(
         cvm.q(
            new byte[]{
               66,
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
               54,
               45,
               0,
               3,
               9,
               4,
               15,
               70,
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
               97,
               115,
               127
            },
            1,
            14
         )
      );
      fQ.add(
         cvm.q(
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
               80,
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
            74
         )
      );
      fQ.add(
         cvm.q(
            new byte[]{
               -93,
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
            239
         )
      );
      fQ.add(
         cvm.q(
            new byte[]{
               10,
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
            70
         )
      );
      fQ.add(
         cvm.q(
            new byte[]{
               -46,
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
               61,
               39,
               26,
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
               114,
               96,
               127
            },
            1,
            158
         )
      );
      fQ.add(
         cvm.q(
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
               80,
               23,
               13,
               113,
               1,
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
               57,
               7,
               118
            },
            2,
            140
         )
      );
      fQ.add(
         cvm.q(
            new byte[]{
               -56,
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
               50,
               42,
               3,
               14,
               21,
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
               125,
               111,
               127
            },
            1,
            132
         )
      );
      fQ.add(
         cvm.q(
            new byte[]{
               94,
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
            18
         )
      );
   }

   static class CU {
      String q;
      boolean RF;

      public CU(String var1, boolean var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public String toString() {
         return Strings.ff(cvm.q(new byte[]{-85, 5, 11, 120, 31, 86, 91, 13, 65, 77}, 1, 231), this.q, this.RF ? 1 : 0);
      }
   }

   private static class eo {
      List q;
      Map RF;
      Map xK;

      eo(List var1, Map var2, Map var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }
   }
}
