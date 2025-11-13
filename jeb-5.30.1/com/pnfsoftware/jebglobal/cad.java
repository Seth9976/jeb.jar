package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalCodeThrownException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalSandboxExecutionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalStubException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.base.SystemUtil;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.modifier.ModifierContributor.ForField;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class cad {
   private static final ILogger zz = GlobalLog.getLogger(cad.class);
   private static final int JY = SystemUtil.getMajorJavaVersion();
   public static final long q = 10000L;
   private static final String HF;
   private static final String LK;
   private static final String io;
   private static final String qa;
   public static boolean RF;
   private static AtomicInteger Hk;
   private static AtomicInteger Me;
   private static Object PV;
   private static cak oQ;
   private static boolean xW;
   private cah KT;
   private bye Gf;
   private long Ef;
   private ExecutorService cC;
   private ExecutorService sH;
   public boolean xK = true;
   List Dw = new ArrayList();
   public int Uv;
   public int oW;
   public int gO;
   public long nf;
   public long gP;
   private cad.nI CE;
   public static final String za;
   private static final Map wF;
   private static final Set If;
   private static final Set Dz;
   private static final Set mI;
   private static final Set jq;
   private InputStream ui = new cag(this);
   private FileDescriptor TX = new FileDescriptor();
   private static final AtomicLong Rr;
   static String lm;
   private static String EB;
   private Map Xo = new HashMap();
   private static final Set Bu;

   static boolean q(Thread var0) {
      String var1 = var0.getName();
      return var1 != null && var1.startsWith(HF);
   }

   static boolean RF(Thread var0) {
      String var1 = var0.getName();
      return var1 != null && var1.startsWith(LK);
   }

   static boolean xK(Thread var0) {
      String var1 = var0.getName();
      return var1 != null && (var1.startsWith(LK) || var1.startsWith(HF));
   }

   public cad(bye var1) throws Exception {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.Gf = var1;
         this.KT = new cah(var1);
         synchronized (PV) {
            if (oQ == null) {
               oQ = new cak();
            }

            if (System.getSecurityManager() != oQ) {
               try {
                  System.setSecurityManager(oQ);
               } catch (UnsupportedOperationException var5) {
                  if (!xW) {
                     xW = true;
                     zz.error(S.L("The Java SecurityManager is unavailable! dexdec's sandboxing capabilities will be restricted to emulation only."));
                     zz.error(S.L("Your JDK version is %d. We recommend JDK 17, for which the SecurityManager is enabled by default."), JY);
                     zz.error(S.L("With Java 18 or above, you must explicitly enable the SecurityManager."));
                     zz.error(S.L("Example: you may start JEB like this: %s"), "`$ java -Djava.security.manager=allow -jar bin/app/jebc.jar`");
                     zz.catchingSilent(var5);
                  }

                  throw var5;
               } catch (SecurityException var6) {
                  if (!xW) {
                     xW = true;
                     zz.error(S.L("The Java SecurityManager cannot be used! dexdec's sandboxing capabilities will be restricted to emulation only."));
                     zz.catchingSilent(var6);
                  }

                  throw var6;
               }
            }
         }

         this.q(10000L);
         Object var8 = null;
         this.sH = Executors.newFixedThreadPool(1, new cae(this, (ThreadGroup)var8));
         this.cC = Executors.newFixedThreadPool(1, new caf(this, (ThreadGroup)var8));
      }
   }

   public void q() {
      if (!this.RF()) {
         if (this.sH != null) {
            this.sH.shutdown();
            this.sH = null;
         }

         if (this.cC != null) {
            this.cC.shutdown();
            this.cC = null;
         }

         if (this.CE == null || !this.CE.q) {
            for (AutoCloseable var2 : this.Dw) {
               try {
                  var2.close();
               } catch (Exception var3) {
               }
            }
         }

         this.CE = null;
         this.KT.q();
         this.Gf = null;
      }
   }

   public boolean RF() {
      return this.Gf == null;
   }

   public File q(String var1, boolean var2) throws IOException {
      return oQ.q(var1, var2);
   }

   public File xK() {
      return oQ.q();
   }

   public cah Dw() {
      return this.KT;
   }

   public void q(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException();
      } else {
         this.Ef = var1;
      }
   }

   public long Uv() {
      return this.Ef;
   }

   boolean q(cad.eo var1, String var2) {
      boolean var3 = Thread.currentThread().getContextClassLoader() == this.KT;
      Object[] var10000 = new Object[]{var3, var1, var2};
      return var3;
   }

   boolean q(boolean var1, cad.eo var2, String var3) {
      Object[] var10000 = new Object[]{var1, var2, var3};
      return var1;
   }

   private void q(cad.nI var1) throws Exception {
      int var2 = var1.xK;
      cad.eo var3 = var1.q();
      String var4 = var1.RF();
      Thread var5 = Thread.currentThread();
      boolean var6 = false;
      if (q(var5)) {
         var6 = true;
      } else if (var2 >= 1 && RF(var5)) {
         var6 = true;
      } else if (var2 >= 2) {
         var6 = true;
      }

      if (var6) {
         var1.run();
      } else {
         long var7 = Math.min(this.Gf.getTimeLeft(), this.Uv());
         if (var7 <= 0L) {
            throw new RuntimeException(
               cvm.q(
                  new byte[]{
                     13,
                     0,
                     4,
                     89,
                     23,
                     7,
                     8,
                     29,
                     19,
                     72,
                     8,
                     23,
                     64,
                     77,
                     84,
                     25,
                     85,
                     86,
                     74,
                     84,
                     18,
                     68,
                     93,
                     20,
                     69,
                     78,
                     57,
                     29,
                     10,
                     6,
                     76,
                     17,
                     72,
                     8,
                     29,
                     68,
                     76,
                     14,
                     30,
                     76
                  },
                  2,
                  247
               )
            );
         } else if (this.CE != null && !this.CE.q) {
            String var16 = Strings.ff(
               cvm.q(
                  new byte[]{
                     -66,
                     60,
                     13,
                     69,
                     80,
                     2,
                     23,
                     19,
                     31,
                     6,
                     26,
                     6,
                     83,
                     84,
                     21,
                     18,
                     24,
                     75,
                     72,
                     9,
                     18,
                     83,
                     78,
                     1,
                     27,
                     84,
                     67,
                     12,
                     2,
                     29,
                     28,
                     9,
                     17,
                     17,
                     1,
                     68,
                     79,
                     29,
                     82,
                     73,
                     26,
                     83,
                     66,
                     14,
                     3,
                     12,
                     8,
                     14,
                     1,
                     69,
                     1,
                     119,
                     62,
                     5,
                     0,
                     76,
                     78,
                     1,
                     27,
                     84,
                     68,
                     11,
                     85,
                     26,
                     5,
                     86,
                     73,
                     26,
                     5,
                     86
                  },
                  1,
                  234
               ),
               var3,
               var4
            );
            throw new RuntimeException(var16);
         } else {
            this.CE = var1;
            ExecutorService var9;
            if (var2 >= 1) {
               var9 = this.cC;
            } else {
               var9 = this.sH;
            }

            long var10 = System.nanoTime();
            Future var12 = var9.submit(var1);

            try {
               var12.get(var7, TimeUnit.MILLISECONDS);
            } finally {
               if (!var12.isDone()) {
                  var12.cancel(true);
               }

               if (var2 >= 1) {
                  this.nf = this.nf + (System.nanoTime() - var10);
               } else {
                  this.gP = this.gP + (System.nanoTime() - var10);
               }
            }
         }
      }
   }

   public Class q(String var1) throws DexDecEvaluationException {
      Class var2 = this.q(var1, null);
      if (var2 == null) {
         throw new DexDecEvaluationException(
            cvm.q(
                  new byte[]{
                     22,
                     1,
                     21,
                     1,
                     2,
                     12,
                     4,
                     28,
                     17,
                     68,
                     18,
                     67,
                     74,
                     76,
                     80,
                     74,
                     74,
                     19,
                     64,
                     79,
                     83,
                     84,
                     91,
                     90,
                     75,
                     0,
                     46,
                     6,
                     21,
                     6,
                     1,
                     21,
                     84,
                     65,
                     28,
                     1,
                     91,
                     26,
                     0,
                     78,
                     12,
                     16,
                     83,
                     78,
                     20,
                     10,
                     10,
                     83,
                     76
                  },
                  2,
                  241
               )
               + var1
         );
      } else {
         return var2;
      }
   }

   public Class q(bxy.eo var1) throws DexDecEvaluationException {
      Class var2;
      if (var1.q()) {
         var2 = this.q(var1.RF());
      } else {
         var2 = this.q(var1.Dw(), var1);
      }

      if (var2 == null) {
         throw new DexDecEvaluationException(
            cvm.q(
                  new byte[]{
                     -116,
                     59,
                     11,
                     29,
                     8,
                     21,
                     6,
                     23,
                     17,
                     1,
                     94,
                     26,
                     67,
                     15,
                     13,
                     18,
                     0,
                     83,
                     76,
                     3,
                     14,
                     5,
                     13,
                     7,
                     9,
                     71,
                     65,
                     21,
                     0,
                     17,
                     8,
                     29,
                     4,
                     84,
                     82,
                     23,
                     17,
                     1,
                     7,
                     28,
                     11,
                     1,
                     68,
                     78,
                     27,
                     25,
                     0,
                     86,
                     26
                  },
                  1,
                  217
               )
               + var1
         );
      } else {
         return var2;
      }
   }

   private Class q(String var1, bxy.eo var2) throws DexDecEvaluationException {
      if (var2 == null) {
         Class var3 = this.KT.q(var1);
         if (var3 != null) {
            return var3;
         }
      }

      long var16 = gP();
      boolean var5 = this.q(cad.eo.q, var1);
      byte var6 = 0;
      if (this.xK && var1.startsWith("Ljava/")) {
         var6 = 1;
      }

      cad.iZ var7 = new cad.iZ(this, var6, var16, var1, var2);

      Class var8;
      try {
         this.q((cad.nI)var7);
         if (var7.gO != null) {
            throw var7.gO;
         }

         var8 = var7.oW;
      } catch (DexDecEvaluationException var13) {
         throw var13;
      } catch (Exception var14) {
         if (var14 instanceof ClassNotFoundException && !JavaUtil.isValidInternalClassname(var1)) {
            throw new DexDecEvalCodeThrownException(this.Gf.registerObject(var14));
         }

         throw new DexDecEvalSandboxExecutionException(var14);
      } finally {
         this.q(var5, cad.eo.q, var1);
      }

      return var8;
   }

   private static void nf() {
      String var0 = null;
      byte var1 = 0;

      for (String var5 : Strings.splitLines(Strings.decodeUTF8(AssetManager.getAssetBytes(za)))) {
         if (var5.length() != 0 && !var5.startsWith("#")) {
            char var6 = var5.charAt(0);
            if (var6 == '=') {
               var5 = var5.substring(1);
               var1 = 1;
               var6 = var5.charAt(0);
               if (var6 == '@') {
                  var1 = 2;
                  var5 = var5.substring(1);
               }

               var0 = var5;
            } else if (var6 != '!' && var6 != '?') {
               byte var7 = var1;
               if (var6 == '@') {
                  var7 = 2;
                  var5 = var5.substring(1);
               }

               String var8 = var0 + "->" + var5;
               wF.put(var8, Integer.valueOf(var7));
            }
         }
      }
   }

   private boolean q(InputStream var1) {
      if (var1 == null) {
         return false;
      } else {
         Class var2 = var1.getClass();
         if (!var2.getName().startsWith("java.")) {
            return false;
         } else if (var2 == ByteArrayInputStream.class) {
            return true;
         } else if (var2 == FileInputStream.class) {
            try {
               FileDescriptor var7 = ((FileInputStream)var1).getFD();
               return var7 != FileDescriptor.in;
            } catch (IOException var5) {
               return false;
            }
         } else if (FilterInputStream.class.isInstance(var1)) {
            try {
               Field var3 = FilterInputStream.class.getDeclaredField("in");
               var3.setAccessible(true);
               InputStream var4 = (InputStream)var3.get(var1);
               return this.q(var4);
            } catch (Exception var6) {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private boolean q(String var1, Object var2) {
      switch (cvm.xK(var1)) {
         case -850814056:
         case -85702534:
         case 376262382:
            if (var2 instanceof ByteArrayOutputStream) {
               return true;
            }
         default:
            return false;
      }
   }

   private Object Uv(Object var1) {
      if (var1 == System.in) {
         return this.ui;
      } else {
         return var1 == FileDescriptor.in ? this.TX : var1;
      }
   }

   public IDImm q(DInvokeType var1, String var2, List var3, bxy.eo var4) throws DexDecEvaluationException {
      this.Uv++;
      JvmMethodSig var5 = JvmMethodSig.parse(var2);
      String var6 = var5.csig;
      String var7 = var5.mname;
      String var8 = var5.rettype;
      List var9 = var5.partypes;
      boolean var11 = var7.equals("<init>");
      List var12 = null;
      long var13 = gP();
      if (this.Gf.HF().q()) {
         if (var11) {
            var12 = q(this.Gf, var3, var9, var6, false);
            Wrapper var15 = this.Gf.HF().newInstance(var13, this.Gf.PV(), var2, var12);
            if (var15 != null) {
               Object var10 = var15.get();
               this.Gf.HF().examineCreatedInstance(var13, var10);
               if (!RF(var2)) {
                  Object[] var10000 = new Object[]{var13, var2, q(var12)};
                  var10000 = new Object[]{var13, q(var10)};
               }

               return this.Gf.q("L", var10);
            }
         } else {
            boolean var40 = var1 != DInvokeType.STATIC;
            var12 = q(this.Gf, var3, var9, var6, var40);
            Object var16 = null;
            Object var17 = var12;
            if (var40) {
               var16 = var12.get(0);
               var17 = new ArrayList(var12.subList(1, var12.size()));
            }

            Wrapper var18 = this.Gf.HF().invokeMethod(var13, this.Gf.PV(), var2, var16, (List)var17);
            if (var18 != null) {
               Object var39 = var18.get();
               var18 = this.Gf.HF().examineMethodResult(var13, var39);
               if (var18 != null) {
                  if (!RF(var2)) {
                     Object[] var51 = new Object[]{var2};
                  }

                  var39 = var18.get();
               }

               if (!RF(var2)) {
                  Object[] var52 = new Object[]{var13, var2, q(var16), q(var17)};
                  var52 = new Object[]{var13, q(var39)};
               }

               return this.Gf.q(var8, var39);
            }
         }
      }

      if (!this.Gf.RF(var2)) {
         throw new DexDecEvaluationException(
            cvm.q(
                  new byte[]{19, 0, 28, 16, 17, 16, 71, 14, 27, 82, 74, 10, 77, 83, 17, 77, 81, 86, 12, 69, 74, 85, 81, 65, 88, 73, 32, 28, 65, 12, 10, 69},
                  2,
                  212
               )
               + var2
         );
      } else {
         boolean var41 = this.q(cad.eo.RF, var2);
         int var42 = 0;
         if (this.xK) {
            try {
               if (If.contains(var2)) {
                  var42 = 1;
               } else if (Dz.contains(var2)) {
                  var42 = 2;
               } else if (var2.contains("-><init>(")) {
                  if (var4 == null || var4.q) {
                     var42 = (Integer)wF.getOrDefault(var2, 0);
                  }
               } else if (var1 == DInvokeType.STATIC) {
                  if (var2.startsWith("Ljava/") || var2.startsWith("Ljavax/")) {
                     var42 = (Integer)wF.getOrDefault(var2, 0);
                  }
               } else if (var1 != DInvokeType.NEW && var1 != DInvokeType.DIRECT) {
                  if ((var1 == DInvokeType.VIRTUAL || var1 == DInvokeType.INTERFACE || var1 == DInvokeType.SUPER || var1 == DInvokeType.DIRECT)
                     && var3.size() >= 1) {
                     Object var43 = this.Gf.getObject((IDImm)var3.get(0), false);
                     if (var43 != null) {
                        String var45 = var43.getClass().getName();
                        if (var45.startsWith("java.") || var45.startsWith("javax.")) {
                           var42 = (Integer)wF.getOrDefault(var2, 0);
                           if (var42 == 0 && this.q(var2, var43)) {
                              var42 = 1;
                           }
                        }
                     }
                  }
               } else if (var2.startsWith("Ljava/") || var2.startsWith("Ljavax/")) {
                  var42 = (Integer)wF.getOrDefault(var2, 0);
               }
            } catch (DexDecEvaluationException var36) {
            }
         }

         cad.Nt var44 = new cad.Nt(this, var42, var13, var1, var2, var3, var12, var4);

         Object var37;
         try {
            this.q((cad.nI)var44);
            if (var44.gO != null) {
               throw var44.gO;
            }

            var37 = var44.oW;
         } catch (InvocationTargetException var32) {
            Throwable var19 = var32.getCause();
            if (var19 instanceof RuntimeException && Strings.isContainedIn(var19.getMessage(), io, qa)) {
               Object[] var49 = new Object[]{var2, q(var12)};
               throw new DexDecEvalStubException(var2);
            }

            if (var19 instanceof NoSuchFieldException && cvm.q(80, 3891532, -1841955043, var2)) {
               try {
                  Class var20 = (Class)var12.get(0);
                  String var21 = var20.getName();
                  if ((
                        var21.startsWith(cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70}, 2, 65))
                           || var21.startsWith(cvm.q(new byte[]{39, 14, 28, 15, 27, 2, 73}, 2, 14))
                     )
                     && !q(Thread.currentThread())) {
                     String var22 = var21 + "." + (String)var12.get(1);
                     RuntimeException var23 = new RuntimeException(
                        cvm.q(new byte[]{54, 1, 27, 23, 29, 30, 9, 72, 21, 70, 95, 67, 79, 73, 84, 85, 93, 9, 12}, 2, 189) + var22
                     );
                     JebCoreService.notifySilentExceptionToClient(var23);
                  }
               } catch (Exception var31) {
               }
            }

            throw new DexDecEvalSandboxExecutionException(var32);
         } catch (DexDecEvaluationException var33) {
            throw var33;
         } catch (Exception var34) {
            throw new DexDecEvalSandboxExecutionException(var34);
         } finally {
            this.q(var41, cad.eo.RF, var2);
         }

         if (var11) {
            this.Gf.HF().examineCreatedInstance(var13, var37);
         } else {
            Wrapper var46 = this.Gf.HF().examineMethodResult(var13, var37);
            if (var46 != null) {
               if (!RF(var2)) {
                  Object[] var50 = new Object[]{var2};
               }

               var37 = var46.get();
            }
         }

         if (var11) {
            return var44.nf;
         } else {
            var37 = this.Uv(var37);
            return this.Gf.q(var8, var37);
         }
      }
   }

   public IDImm q(String var1, IDImm var2) throws DexDecEvaluationException {
      this.oW++;
      JvmFieldSig var3 = JvmFieldSig.parse(var1);
      String var4 = var3.csig;
      String var5 = var3.ftype;
      boolean var6 = var2 != null;
      List var7 = q(this.Gf, var2 == null ? Arrays.asList() : Arrays.asList(var2), Arrays.asList(), var4, var6);
      Object var9 = var6 ? var7.get(0) : null;
      long var10 = gP();
      if (this.Gf.HF().q()) {
         Wrapper var12 = this.Gf.HF().getField(var10, this.Gf.PV(), var1, var9);
         if (var12 != null) {
            Object var24 = var12.get();
            if (!RF(var1)) {
               Object[] var27 = new Object[]{var10, var1, q(var9)};
               var27 = new Object[]{var10, q(var24)};
            }

            var12 = this.Gf.HF().examineFieldValue(var10, var24);
            if (var12 != null) {
               if (!RF(var1)) {
                  Object[] var29 = new Object[]{var1};
               }

               var24 = var12.get();
            }

            return this.Gf.q(var5, var24);
         }
      }

      if (!this.Gf.xK(var1)) {
         throw new DexDecEvaluationException(
            cvm.q(new byte[]{19, 0, 28, 16, 17, 16, 71, 14, 27, 82, 74, 10, 77, 83, 17, 75, 92, 82, 72, 13, 83, 83, 81, 81, 95, 83, 111, 29, 7, 67}, 2, 177)
               + var1
         );
      } else {
         boolean var25 = this.q(cad.eo.xK, var1);
         byte var13 = 0;
         if (this.xK && (var4.startsWith("Ljava/") || var4.startsWith("Ljavax/"))) {
            var13 = 2;
         }

         cad.ej var14 = new cad.ej(this, var13, var10, var1, var9);

         Object var8;
         try {
            this.q((cad.nI)var14);
            if (var14.gO != null) {
               throw var14.gO;
            }

            var8 = var14.oW;
         } catch (DexDecEvaluationException var20) {
            throw var20;
         } catch (Exception var21) {
            throw new DexDecEvalSandboxExecutionException(var21);
         } finally {
            this.q(var25, cad.eo.xK, var1);
         }

         Wrapper var15 = this.Gf.HF().examineFieldValue(var10, var8);
         if (var15 != null) {
            if (!RF(var1)) {
               Object[] var10000 = new Object[]{var1};
            }

            var8 = var15.get();
         }

         var8 = this.Uv(var8);
         return this.Gf.q(var5, var8);
      }
   }

   public void q(String var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      this.gO++;
      JvmFieldSig var4 = JvmFieldSig.parse(var1);
      String var5 = var4.csig;
      String var6 = var4.ftype;
      boolean var7 = var2 != null;
      List var8 = q(this.Gf, var2 == null ? Arrays.asList(var3) : Arrays.asList(var2, var3), Arrays.asList(var6), var5, var7);
      Object var9;
      Object var10;
      if (var7) {
         var9 = var8.get(0);
         var10 = var8.get(1);
      } else {
         var9 = null;
         var10 = var8.get(0);
      }

      long var11 = gP();
      if (this.Gf.HF().q()) {
         Object[] var13 = new Object[]{var10};
         Boolean var14 = this.Gf.HF().setField(var11, this.Gf.PV(), var1, var9, var13);
         if (var14 != null) {
            if (var14) {
               if (!RF(var1)) {
                  Object[] var10000 = new Object[]{var11, var1, q(var9), q(var10)};
               }

               return;
            }

            var10 = var13[0];
         }
      }

      if (!this.Gf.Dw(var1)) {
         throw new DexDecEvaluationException(
            cvm.q(new byte[]{27, 63, 3, 5, 10, 26, 89, 70, 9, 29, 16, 11, 13, 23, 83, 87, 5, 27, 29, 17, 72, 76, 2, 0, 6, 22, 0, 83, 79, 9, 70}, 1, 75) + var1
         );
      } else {
         boolean var24 = this.q(cad.eo.Dw, var1);
         byte var25 = 0;
         cad.oM var15 = new cad.oM(this, var25, var11, var1, var9, var10);

         try {
            this.q((cad.nI)var15);
            if (var15.oW != null) {
               throw var15.oW;
            }
         } catch (DexDecEvaluationException var21) {
            throw var21;
         } catch (Exception var22) {
            throw new DexDecEvalSandboxExecutionException(var22);
         } finally {
            this.q(var24, cad.eo.Dw, var1);
         }
      }
   }

   private static final long gP() {
      return Rr.getAndIncrement();
   }

   static String q(Object var0) {
      return "null";
   }

   private static boolean RF(String var0) {
      return false;
   }

   Class q(bxy.eo var1, Class var2) throws Exception {
      String var3 = this.q(var2, var1.Dw());

      Class var4;
      try {
         var4 = this.KT.loadClass(var3);
      } catch (ClassNotFoundException var5) {
         var4 = new ByteBuddy()
            .makeEnumeration(new String[]{"V1"})
            .name(var3)
            .method(ElementMatchers.isMethod())
            .intercept(MethodDelegation.to(this.Gf.gP()))
            .defineField(EB, int.class, new ForField[]{Visibility.PUBLIC})
            .make()
            .load(this.KT, Default.INJECTION)
            .getLoaded();
         var1.q = true;
      }

      return var4;
   }

   private synchronized String q(Class var1, String var2) {
      String var3 = var1.getName() + "_" + var2;
      String var4 = Integer.toHexString(var3.hashCode());
      String var5 = var4;
      int var6 = 1;

      while (true) {
         String var7 = (String)this.Xo.get(var5);
         if (var7 == null || var7.equals(var3)) {
            this.Xo.put(var5, var3);
            return lm + var1.getSimpleName() + "_" + var5;
         }

         var5 = var4 + "_" + var6;
         var6++;
      }
   }

   private String xK(String var1) {
      int var2 = var1.lastIndexOf(95);
      String var3 = var1.substring(var2 + 1);
      String var4 = (String)this.Xo.get(var3);
      Assert.a(var4.endsWith("#"));
      return var4;
   }

   public static boolean RF(Object var0) {
      return var0.getClass().getName().startsWith(lm);
   }

   public static boolean xK(Object var0) {
      return var0 instanceof Class && ((Class)var0).getName().startsWith(lm);
   }

   public static void q(Object var0, DInvokeType var1) {
      try {
         var0.getClass().getDeclaredField(EB).setInt(var0, var1 == DInvokeType.SUPER ? 1 : 0);
      } catch (Exception var2) {
         throw new RuntimeException();
      }
   }

   public static int Dw(Object var0) {
      try {
         Field var1 = var0.getClass().getDeclaredField(EB);
         int var2 = var1.getInt(var0);
         var1.setInt(var0, 0);
         return var2;
      } catch (Exception var3) {
         throw new RuntimeException();
      }
   }

   private static List q(bye var0, List var1, List var2, String var3, boolean var4) throws DexDecEvaluationException {
      ArrayList var5 = new ArrayList(var1.size());
      int var6 = 0;
      if (var4) {
         IDImm var7 = (IDImm)var1.get(var6++);
         Object var8 = var0.q(var3, var7);
         var5.add(var8);
      }

      for (int var11 = 0; var11 < var2.size(); var6++) {
         String var12 = (String)var2.get(var11);
         IDImm var9 = (IDImm)var1.get(var6);
         Object var10 = var0.q(var12, var9);
         var5.add(var10);
         var11++;
      }

      return var5;
   }

   public IJLSTypeAdapter oW() {
      return new cad.tw();
   }

   public String gO() {
      StringBuilder var1 = new StringBuilder("dexdec sandbox stats:\n");
      Strings.ff(var1, "total invoke: %d\n", this.Uv);
      Strings.ff(var1, "total field.get: %d\n", this.oW);
      Strings.ff(var1, "total field.set: %d\n", this.gO);
      Strings.ff(var1, "time spent in... worker threads: %d ms\n", this.nf / 1000000L);
      Strings.ff(var1, "time spent in... sandbox threads: %d ms\n", this.gP / 1000000L);
      return var1.toString();
   }

   static {
      if (JY >= 17) {
         try {
            cai.q(cad.class);
         } catch (InaccessibleObjectException var2) {
            String var1 = cvm.q(new byte[]{41, 25, 29, 22, 2, 29, 73, 28, 12, 84}, 2, 120);
            zz.warn(S.L("The sandbox component of the dex emulator is limited!"));
            zz.warn(
               S.L("If your workload includes decompiling obfuscated dex code, consider appending this to the file %s (located in your JEB root folder):"),
               var1
            );
            zz.warn(
               cvm.q(
                  new byte[]{
                     46,
                     0,
                     76,
                     5,
                     0,
                     73,
                     66,
                     31,
                     21,
                     11,
                     29,
                     83,
                     74,
                     11,
                     23,
                     23,
                     79,
                     76,
                     3,
                     18,
                     22,
                     74,
                     69,
                     11,
                     23,
                     23,
                     79,
                     66,
                     13,
                     15,
                     9,
                     90,
                     124,
                     13,
                     0,
                     97,
                     120,
                     27,
                     0,
                     15,
                     12,
                     8,
                     1
                  },
                  1,
                  3
               )
            );
            zz.warn(S.L("Note that %s must contain a single line. Multiple options are separated by space."), var1);
         } catch (Exception var3) {
            zz.error(S.L("The sandbox component of the dex emulator is limited!"));
            zz.catchingSilent(var3);
         }
      }

      HF = cvm.q(new byte[]{41, 10, 18, 84, 22, 12, 31, 12, 17, 67, 5, 16, 75, 13, 66, 77, 20}, 2, 100);
      LK = cvm.q(new byte[]{4, 15, 7, 79, 73, 1, 29, 28, 1, 6, 78, 94, 17, 79, 90, 3, 89}, 1, 110);
      io = cvm.q(new byte[]{75, 39, 1, 23, 67}, 1, 24);
      qa = cvm.q(new byte[]{16, 27, 5, 27, 64, 72}, 2, 79);
      RF = true;
      Hk = new AtomicInteger();
      Me = new AtomicInteger();
      PV = new Object();
      oQ = null;
      xW = false;
      za = cvm.q(new byte[]{79, 0, 23, 17, 17, 30, 1, 66, 90, 12, 12}, 1, 43);
      wF = new HashMap();
      If = new HashSet();
      Dz = new HashSet();
      nf();
      If.add(
         cvm.q(
            new byte[]{
               -70,
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
               106,
               43,
               27,
               24,
               86,
               22,
               19,
               80,
               15,
               12,
               8,
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
               124,
               39,
               6,
               27,
               7,
               9,
               92
            },
            1,
            246
         )
      );
      If.add(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 38, 71, 85, 92, 2, 20, 13, 67, 82, 86, 89, 92, 85, 64, 8, 102, 59}, 2, 41));
      Dz.add(
         cvm.q(
            new byte[]{
               26,
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
               63,
               35,
               14,
               5,
               1,
               23,
               73,
               22,
               19,
               82,
               3,
               14,
               5,
               39,
               47,
               13,
               18,
               0,
               91,
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
               124,
               39,
               6,
               27,
               7,
               9,
               92,
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
               108,
               47,
               13,
               18,
               0,
               72
            },
            1,
            86
         )
      );
      Dz.add(
         cvm.q(
            new byte[]{
               120,
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
               63,
               35,
               14,
               5,
               1,
               23,
               73,
               22,
               19,
               82,
               3,
               14,
               5,
               39,
               47,
               13,
               18,
               0,
               91,
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
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               97,
               115,
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
               108,
               47,
               13,
               18,
               0,
               72
            },
            1,
            52
         )
      );
      Dz.add(
         cvm.q(
            new byte[]{
               112,
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
               63,
               35,
               14,
               5,
               1,
               23,
               73,
               22,
               19,
               88,
               15,
               7,
               10,
               39,
               47,
               13,
               18,
               0,
               91,
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
               124,
               39,
               6,
               27,
               7,
               9,
               92,
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
               108,
               47,
               13,
               18,
               0,
               72
            },
            1,
            60
         )
      );
      Dz.add(
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
               32,
               69,
               65,
               66,
               74,
               117,
               92,
               77,
               68,
               87,
               66,
               9,
               25,
               18,
               70,
               38,
               28,
               5,
               47,
               3,
               4,
               68,
               4,
               10,
               39,
               67,
               14,
               1,
               83,
               65,
               56,
               25,
               65,
               23,
               7,
               73,
               5,
               13,
               7,
               6,
               91,
               54,
               7,
               92,
               73,
               47,
               11,
               87,
               9,
               62,
               3,
               6,
               30,
               21,
               92,
               76,
               19,
               11,
               20,
               74,
               49,
               26,
               4,
               23,
               93,
               27
            },
            2,
            168
         )
      );
      Dz.add(
         cvm.q(
            new byte[]{
               75,
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
               88,
               9,
               29,
               60,
               47,
               12,
               8,
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
               124,
               39,
               6,
               27,
               7,
               9,
               92,
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
               108,
               47,
               13,
               18,
               0,
               72
            },
            1,
            7
         )
      );
      Dz.add(
         cvm.q(
            new byte[]{
               47,
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
               88,
               9,
               29,
               60,
               47,
               12,
               8,
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
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               97,
               22,
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
               63,
               35,
               14,
               5,
               1,
               23,
               73,
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
               108,
               47,
               13,
               18,
               0,
               72
            },
            1,
            99
         )
      );
      mI = new HashSet();
      mI.add("Ljava/nio/charset/StandardCharsets;->ISO_8851_9:Ljava/nio/charset/Charset;");
      mI.add("Ljava/nio/charset/StandardCharsets;->US_ASCII:Ljava/nio/charset/Charset;");
      mI.add("Ljava/nio/charset/StandardCharsets;->UTF_16:Ljava/nio/charset/Charset;");
      mI.add("Ljava/nio/charset/StandardCharsets;->UTF_16BE:Ljava/nio/charset/Charset;");
      mI.add("Ljava/nio/charset/StandardCharsets;->UTF_16LE:Ljava/nio/charset/Charset;");
      mI.add("Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;");
      jq = new IdentityHashSet();
      jq.add(StandardCharsets.ISO_8859_1);
      jq.add(StandardCharsets.US_ASCII);
      jq.add(StandardCharsets.UTF_16);
      jq.add(StandardCharsets.UTF_16BE);
      jq.add(StandardCharsets.UTF_16LE);
      jq.add(StandardCharsets.UTF_8);
      Rr = new AtomicLong(1L);
      lm = cvm.q(new byte[]{41, 10, 18, 29, 11, 7, 73, 12, 17, 88, 76, 6, 74, 14, 88, 75, 74, 81, 2}, 2, 91);
      EB = cvm.q(new byte[]{44, 26, 4, 10, 27, 13, 2, 63, 27, 82, 68, 7, 96, 78, 87, 86}, 2, 132);
      Bu = new HashSet();
      Bu.add("Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;");
   }

   public static class CU {
      private Throwable q;
      private Set RF = new HashSet();

      public CU(Throwable var1) {
         this.q = var1;
      }

      public String q() {
         return this.q(this.q);
      }

      private String q(Throwable var1) {
         StringBuilder var2 = new StringBuilder();

         while (var1 != null) {
            if (!this.RF.add(var1)) {
               var2.append("x");
               break;
            }

            var2.append(var1.toString());
            if (var1.getMessage() != null) {
               Strings.ff(var2, "(\"%s\")", var1.getMessage());
            }

            Throwable[] var3 = var1.getSuppressed();
            if (var3 != null && var3.length > 0) {
               var2.append("[suppressed: ");

               for (Throwable var7 : var3) {
                  var2.append(this.q(var7));
                  var2.append(";");
               }

               var2.append("]");
            }

            var1 = var1.getCause();
            if (var1 == null) {
               break;
            }

            var2.append(" <= ");
         }

         return var2.toString();
      }
   }

   static class Nt extends cad.nI {
      private DInvokeType gP;
      private String za;
      private List lm;
      private List zz;
      private bxy.eo JY;
      Object oW;
      Exception gO;
      IDImm nf;

      Nt(cad var1, int var2, long var3, DInvokeType var5, String var6, List var7, List var8, bxy.eo var9) {
         super(var1, var2, var3);
         this.gP = var5;
         this.za = var6;
         this.lm = var7;
         this.zz = var8;
         this.JY = var9;
      }

      @Override
      cad.eo q() {
         return cad.eo.RF;
      }

      @Override
      String RF() {
         return this.za;
      }

      @Override
      public void run() {
         try {
            this.xK();
            cah var1 = this.RF.Dw();
            JvmMethodSig var38 = JvmMethodSig.parse(this.za);
            String var3 = var38.csig;
            String var4 = var38.mname;
            Class var5 = var1.RF(var3);
            List var6 = var38.partypes;
            ArrayList var7 = new ArrayList(var6.size());
            Class[] var8 = new Class[var6.size()];
            int var9 = 0;

            for (String var11 : var6) {
               var8[var9] = var1.RF(var11);
               var7.add(var8[var9]);
               var9++;
            }

            boolean var39 = cad.RF(this.za);
            if (var4.equals("<init>")) {
               if (this.zz == null) {
                  this.zz = cad.q(this.Uv, this.lm, var6, var3, false);
               }

               boolean var40 = false;
               Runnable var12 = null;
               if (var3.equals("Ljava/lang/Thread;")) {
                  var40 = true;
                  int var13 = var38.partypes.indexOf("Ljava/lang/Runnable;");
                  if (var13 >= 0) {
                     var12 = (Runnable)this.zz.get(var13);
                  }
               }

               if (!this.Uv.RF(this.za)) {
                  throw new DexDecEvaluationException("Policy forbids the execution of " + this.za);
               }

               Object var43;
               if (this.JY != null && (var5.getModifiers() & 16) == 0 && !var5.isArray() && !var5.isPrimitive()) {
                  if (!var39) {
                     Object[] var96 = new Object[]{this.Dw, this.za, cad.q(this.zz)};
                  }

                  var43 = this.q(this.JY, var5, var8, this.zz.toArray(new Object[this.zz.size()]));
               } else {
                  Constructor var14;
                  try {
                     var14 = var5.getDeclaredConstructor(var8);
                  } catch (NoSuchMethodException var32) {
                     var14 = var5.getConstructor(var8);
                  }

                  if (!var14.canAccess(null)) {
                     var14.setAccessible(true);
                  }

                  if (!var39) {
                     Object[] var95 = new Object[]{this.Dw, this.za, cad.q(this.zz)};
                  }

                  var43 = var14.newInstance(this.zz.toArray(new Object[this.zz.size()]));
               }

               if (var1.Uv.isInstance(var43)) {
                  ArrayList var45 = new ArrayList();

                  for (IDEmuFrame var16 : this.Uv.getCurrentContext().getFrames()) {
                     var45.add(var16.getMethodSignature());
                  }

                  for (String var52 : this.Uv.getCurrentContext().getOrigins()) {
                     var45.add(var52);
                  }

                  int var49 = var45.size();
                  Object var53 = Array.newInstance(var1.oW, var49);
                  int var17 = 0;
                  Constructor var18 = var1.oW.getConstructor(var1.Dw, var1.Dw, var1.Dw, var1.loadClass("I"));

                  for (String var20 : var45) {
                     JvmMethodSig var21 = JvmMethodSig.parse(var20);
                     String var22 = cah.xK(var21.csig);
                     String var23 = var21.mname;
                     Object var24 = var18.newInstance(var22, var23, null, -1);
                     Array.set(var53, var17, var24);
                     var17++;
                  }

                  var1.Uv.getMethod(cvm.q(new byte[]{-74, 22, 17, 39, 39, 21, 2, 8, 63, 38, 19, 2, 6}, 1, 197), var1.gO).invoke(var43, var53);
               }

               if (var43 instanceof AutoCloseable) {
                  this.RF.Dw.add((AutoCloseable)var43);
               }

               this.oW = var43;
               this.nf = this.Uv.registerObject(var43);
               if (var40) {
                  byd var46 = this.Uv.q((Thread)var43);
                  var46.q(var12);
               }
            } else {
               Class[] var41 = (Class[])var7.toArray(new Class[var7.size()]);
               Method var42 = null;

               try {
                  var42 = var5.getDeclaredMethod(var4, var41);
               } catch (NoSuchMethodException var34) {
                  try {
                     var42 = var5.getMethod(var4, var41);
                  } catch (NoSuchMethodException var33) {
                     if (var5.isArray() && var4.equals("clone") && var41.length == 0) {
                        if (cad.JY > 15 || !cad.RF) {
                           if (!var39) {
                              Object[] var97 = new Object[]{this.za};
                           }

                           IDImm var50 = (IDImm)this.lm.get(0);
                           IDImm var54 = this.Uv.cloneArray(var50);
                           this.oW = this.Uv.q("[", var54);
                           return;
                        }

                        var42 = var5.getSuperclass().getDeclaredMethod(var4);
                        var42.setAccessible(true);
                     }
                  }
               }

               if (var42 == null) {
                  throw new NoSuchMethodException(
                     cvm.q(new byte[]{0, 14, 30, 23, 29, 29, 71, 14, 29, 78, 76, 67, 68, 69, 69, 81, 86, 87, 22, 0}, 2, 154) + var4
                  );
               }

               boolean var44 = !Modifier.isStatic(var42.getModifiers());
               if (this.zz == null) {
                  this.zz = cad.q(this.Uv, this.lm, var6, var3, var44);
               }

               Object var47 = null;
               boolean var51 = false;
               if (!var44) {
                  switch (cvm.xK(this.za)) {
                     case -2132577010:
                        String var58 = (String)this.zz.get(0);
                        boolean var63 = (Boolean)this.zz.get(1);
                        String var81 = JavaUtil.toJvmName(var58);
                        bxy var90 = this.Uv.q(var81, var63);
                        var47 = var90.Dw();
                        var51 = true;
                        break;
                     case -2109522095:
                        var47 = this.Uv.Me();
                        var51 = true;
                        break;
                     case -1936802804:
                     case -452023474:
                        String var57 = (String)this.zz.get(0);
                        if (var57 != null) {
                           boolean var62 = cvm.q(4, 6578540, -1289859604, var4);
                           Object[] var101 = new Object[]{var57, var62};
                           this.Uv.RF(var57, var62);
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case -1795100720:
                     case 1077373800:
                        var51 = true;
                        break;
                     case -1172353596:
                        var47 = false;
                        var51 = true;
                        break;
                     case -487507398:
                        String var56 = (String)this.zz.get(0);
                        String var61 = JavaUtil.toJvmName(var56);
                        bxy var80 = this.Uv.q(var61, true);
                        var47 = var80.Dw();
                        var51 = true;
                        break;
                     case -286084764:
                     case 1549383917:
                        var47 = false;
                        var51 = true;
                        break;
                     case 136388361:
                        var51 = true;
                        break;
                     case 587934895:
                        var47 = new TreeSet();
                        var51 = true;
                        break;
                     case 846870341:
                        this.Uv.q(this.zz.get(0));
                        var47 = null;
                        var51 = true;
                        break;
                     case 1610024648:
                        var47 = var1;
                        var51 = true;
                  }

                  if (var51) {
                     if (!var39) {
                        Object[] var102 = new Object[]{this.Dw, this.za, cad.q(this.zz)};
                     }
                  } else {
                     if (!var39) {
                        Object[] var103 = new Object[]{this.Dw, this.za, cad.q(this.zz)};
                     }

                     if (!var42.canAccess(null)) {
                        var42.setAccessible(true);
                     }

                     var47 = var42.invoke(null, this.zz.toArray(new Object[this.zz.size()]));
                  }
               } else {
                  Object var55 = this.zz.get(0);
                  ArrayList var60 = new ArrayList(this.zz.subList(1, this.zz.size()));
                  switch (cvm.xK(this.za)) {
                     case -2088301382:
                     case -1280323317:
                        String var79 = (String)this.zz.get(1);
                        if (var79 != null) {
                           boolean var89 = cvm.q(4, 6578540, -1289859604, var4);
                           Object[] var98 = new Object[]{var79, var89};
                           this.Uv.RF(var79, var89);
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case -1965212556:
                     case -1377251652:
                     case -688057012:
                     case 1314245546:
                        String var78 = (String)this.zz.get(1);
                        String var88 = JavaUtil.toJvmName(var78);
                        bxy var94 = this.Uv.q(var88, true);
                        var47 = var94.Dw();
                        var51 = true;
                        break;
                     case -1921022599:
                        Class var77 = (Class)var55;
                        Class var87 = var77.getSuperclass();
                        if (var87 != null) {
                           var47 = false;
                           var51 = true;
                        }
                        break;
                     case -1227635774:
                     case -1040008539:
                        var47 = var1;
                        var51 = true;
                        break;
                     case -1131396002:
                        Class var76 = (Class)var55;
                        Class var86 = var76.getSuperclass();
                        if (var86 != null && cvm.q(14, 7171690, 1115224853, var86.getName())) {
                           var47 = true;
                           var51 = true;
                        }
                        break;
                     case -1097556805:
                        if (var60.get(0) == null) {
                           var47 = false;
                           var51 = true;
                        } else {
                           Class var75 = (Class)var55;
                           if (cad.xK(var75)) {
                              bxy var85 = this.Uv.RF(var75);
                              if (var85 != null) {
                                 String var93 = JvmUtil.generateTypeSig(var60.get(0).getClass());
                                 var47 = bkm.q(this.Uv.getTypeAdapter(), var93, var85.getFullName());
                                 var51 = true;
                              }
                           }
                        }
                        break;
                     case -866845516:
                     case 1061930248:
                        byd var74 = this.Uv.RF((Thread)var55);
                        if (var74 != null) {
                           var74.Dw = 3;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case -179344045:
                        byd var73 = this.Uv.RF((Thread)var55);
                        if (var73 != null) {
                           var47 = var73.oW;
                           var51 = true;
                        }
                        break;
                     case 115068398:
                        byd var72 = this.Uv.RF((Thread)var55);
                        if (var72 != null) {
                           var72.Uv = true;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 596549722:
                        Class var71 = (Class)var55;
                        if (cad.xK(var71)) {
                           bxy var84 = this.Uv.RF(var71);
                           if (var84 != null) {
                              IJLSType var92 = this.Uv.getTypeAdapter().getType(var84.getFullName());
                              var47 = (var92.getAccessFlags() & 8192) != 0;
                              var51 = true;
                           }
                        }
                        break;
                     case 643903912:
                        byd var70 = this.Uv.RF((Thread)var55);
                        if (var70 != null) {
                           var47 = var70.Uv;
                           var51 = true;
                        }
                        break;
                     case 689031878:
                        var47 = false;
                        var51 = true;
                        break;
                     case 863762169:
                        Class var69 = (Class)var55;
                        if (cad.xK(var69)) {
                           bxy var83 = this.Uv.RF(var69);
                           if (var83 != null) {
                              var47 = false;
                              var51 = true;
                           }
                        }
                        break;
                     case 1113169250:
                        byd var68 = this.Uv.RF((Thread)var55);
                        if (var68 != null) {
                           var68.Dw = 2;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 1149688541:
                        byd var67 = this.Uv.RF((Thread)var55);
                        if (var67 != null && var67.Dw == 0) {
                           var67.Dw = 1;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 1630357828:
                        byd var66 = this.Uv.RF((Thread)var55);
                        if (var66 != null) {
                           var66.oW = var60.get(0);
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 1811282285:
                        Class var65 = (Class)var55;
                        if (cad.xK(var65)) {
                           bxy var82 = this.Uv.RF(var65);
                           if (var82 != null) {
                              IJLSType var91 = this.Uv.getTypeAdapter().getType(var82.getFullName());
                              var47 = (var91.getAccessFlags() & 512) != 0;
                              var51 = true;
                           }
                        }
                        break;
                     default:
                        if (cvm.q(17, 3894860, 1645344912, var3) && cad.xK(var55)) {
                           bxy var64 = this.Uv.RF((Class)var55);
                           var47 = var64.q(this.za, var60);
                           var51 = true;
                        } else if (var55 instanceof bxw) {
                           var47 = ((bxw)var55).q(this.Uv, this.za, var60);
                           var51 = true;
                        }
                  }

                  if (var51) {
                     if (!var39) {
                        Object[] var99 = new Object[]{this.Dw, this.za, cad.q(var55), cad.q(var60)};
                     }
                  } else {
                     if (cad.RF(var55)) {
                        cad.q(var55, this.gP);
                     }

                     if (!var39) {
                        Object[] var100 = new Object[]{this.Dw, this.za, cad.q(var55), cad.q(var60)};
                     }

                     if (!var42.canAccess(var55)) {
                        var42.setAccessible(true);
                     }

                     var47 = var42.invoke(var55, var60.toArray(new Object[var60.size()]));
                  }
               }

               if (!var51 && this.q(this.za, this.zz)) {
                  List var59 = this.Dw();
                  var59.add(
                     0,
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
                           55,
                           65,
                           82,
                           84,
                           88,
                           93,
                           8,
                           1,
                           30,
                           85,
                           85,
                           70,
                           103,
                           88,
                           65,
                           44,
                           25,
                           53,
                           17,
                           13,
                           6,
                           69,
                           73,
                           71,
                           63,
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
                           58,
                           24,
                           8,
                           2,
                           31,
                           49,
                           1,
                           79,
                           67,
                           36,
                           41,
                           0,
                           69,
                           31,
                           12,
                           9,
                           28,
                           79
                        },
                        2,
                        161
                     )
                  );
                  var47 = this.q(var1, var59);
               }

               this.oW = var47;
            }

            if (!var39) {
               Object[] var104 = new Object[]{this.Dw, cad.q(this.oW)};
            }
         } catch (Exception var35) {
            String var2 = new cad.CU(var35).q();
            Object[] var10000 = new Object[]{this.Dw, var2};
            this.gO = var35;
         } catch (Throwable var36) {
            throw var36;
         } finally {
            this.q = true;
         }
      }

      private boolean q(String var1, List var2) {
         if (cvm.q(65, 3892556, -1472856545, var1)) {
            return true;
         } else {
            if (cvm.q(91, 3878732, 857869226, var1)) {
               Method var3 = (Method)var2.get(0);
               String var4 = var3.getDeclaringClass().getName();
               if (cvm.q(16, 6580074, 298162211, var4) && cvm.q("getStackTrace", var3.getName())) {
                  return true;
               }
            }

            return false;
         }
      }

      private List Dw() {
         ArrayList var1 = new ArrayList();

         for (IDEmuFrame var3 : this.Uv.getCurrentContext().getFrames()) {
            var1.add(var3.getMethodSignature());
         }

         for (String var5 : this.Uv.getCurrentContext().getOrigins()) {
            var1.add(var5);
         }

         return var1;
      }

      private Object q(cah var1, List var2) throws Exception {
         int var3 = var2.size();
         Object var4 = Array.newInstance(var1.oW, var3);
         int var5 = 0;
         Constructor var6 = var1.oW.getConstructor(var1.Dw, var1.Dw, var1.Dw, var1.loadClass("I"));

         for (String var8 : var2) {
            JvmMethodSig var9 = JvmMethodSig.parse(var8);
            String var10 = cah.xK(var9.csig);
            String var11 = var9.mname;
            Object var12 = var6.newInstance(var10, var11, null, -1);
            Array.set(var4, var5, var12);
            var5++;
         }

         return var4;
      }

      Object q(bxy.eo var1, Class var2, Class[] var3, Object[] var4) throws Exception {
         cah var5 = this.RF.Dw();
         String var6 = this.RF.q(var2, var1.Dw());

         Class var7;
         try {
            var7 = var5.loadClass(var6);
         } catch (ClassNotFoundException var13) {
            Object[] var10000 = new Object[]{var6, var1.Dw(), var1.xK()};
            ArrayList var9 = new ArrayList();

            for (String var11 : var1.xK()) {
               var9.add(var5.RF(var11));
            }

            var7 = new ByteBuddy()
               .subclass(var2)
               .implement(var9)
               .name(var6)
               .method(ElementMatchers.isMethod())
               .intercept(MethodDelegation.to(this.Uv.gP()))
               .defineField(cad.EB, int.class, new ForField[]{Visibility.PUBLIC})
               .make()
               .load(var5, Default.INJECTION)
               .getLoaded();
            var1.q = true;
         }

         try {
            return var7.getConstructor(var3).newInstance(var4);
         } catch (Throwable var12) {
            throw var12;
         }
      }
   }

   static class ej extends cad.nI {
      private String nf;
      private Object gP;
      Object oW;
      Exception gO;

      ej(cad var1, int var2, long var3, String var5, Object var6) {
         super(var1, var2, var3);
         this.nf = var5;
         this.gP = var6;
      }

      @Override
      cad.eo q() {
         return cad.eo.xK;
      }

      @Override
      String RF() {
         return this.nf;
      }

      @Override
      public void run() {
         try {
            this.xK();
            cah var1 = this.RF.Dw();
            JvmFieldSig var17 = JvmFieldSig.parse(this.nf);
            String var3 = var17.csig;
            String var4 = var17.fname;
            boolean var5 = cad.Bu.contains(this.nf);
            Class var6 = var1.RF(var3);

            Field var7;
            try {
               var7 = var6.getDeclaredField(var4);
            } catch (NoSuchFieldException var13) {
               var7 = var6.getField(var4);
            }

            if (!var5) {
               Object[] var18 = new Object[]{this.Dw, this.nf, cad.q(this.gP)};
            }

            if (!var7.canAccess(this.gP)) {
               var7.setAccessible(true);
            }

            this.oW = var7.get(this.gP);
            if (!var5) {
               Object[] var19 = new Object[]{this.Dw, cad.q(this.oW)};
            }
         } catch (Exception var14) {
            String var2 = new cad.CU(var14).q();
            Object[] var10000 = new Object[]{this.Dw, var2};
            this.gO = var14;
         } catch (Throwable var15) {
            throw var15;
         } finally {
            this.q = true;
         }
      }
   }

   private static enum eo {
      q,
      RF,
      xK,
      Dw;
   }

   static class iZ extends cad.nI {
      private String nf;
      private bxy.eo gP;
      Class oW;
      Exception gO;

      iZ(cad var1, int var2, long var3, String var5, bxy.eo var6) {
         super(var1, var2, var3);
         this.nf = var5;
         this.gP = var6;
      }

      @Override
      cad.eo q() {
         return cad.eo.q;
      }

      @Override
      String RF() {
         return this.nf;
      }

      @Override
      public void run() {
         try {
            this.xK();
            cah var1 = this.RF.Dw();
            Class var17;
            if (this.gP == null) {
               var17 = var1.RF(this.nf);
            } else {
               Class var3 = var1.RF(this.gP.RF());
               String var4 = this.RF.q(var3, this.gP.Dw());

               try {
                  var17 = var1.loadClass(var4);
               } catch (ClassNotFoundException var13) {
                  Object[] var18 = new Object[]{var4, this.gP.Dw(), this.gP.xK()};
                  ArrayList var5 = new ArrayList();

                  for (String var7 : this.gP.xK()) {
                     var5.add(var1.RF(var7));
                  }

                  var17 = new ByteBuddy()
                     .subclass(var3)
                     .implement(var5)
                     .name(var4)
                     .method(ElementMatchers.isMethod())
                     .intercept(MethodDelegation.to(this.Uv.gP()))
                     .defineField(cad.EB, int.class, new ForField[]{Visibility.PUBLIC})
                     .make()
                     .load(var1, Default.INJECTION)
                     .getLoaded();
                  this.gP.q = true;
               }
            }

            this.oW = var17;
         } catch (Exception var14) {
            String var2 = new cad.CU(var14).q();
            Object[] var10000 = new Object[]{this.Dw, var2};
            this.gO = var14;
         } catch (Throwable var15) {
            throw var15;
         } finally {
            this.q = true;
         }
      }
   }

   abstract static class nI extends Thread {
      volatile boolean q;
      cad RF;
      int xK;
      long Dw;
      bye Uv;

      nI(cad var1, int var2, long var3) {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var3;
         this.Uv = var1.Gf;
      }

      abstract cad.eo q();

      abstract String RF();

      void xK() {
         ClassLoader var1 = Thread.currentThread().getContextClassLoader();
         cah var2 = this.RF.Dw();
         if (this.xK == 0) {
            cad.oQ.RF();
            Assert.a(var1 == var2);
         }
      }
   }

   static class oM extends cad.nI {
      private String gO;
      private Object nf;
      private Object gP;
      Exception oW;

      oM(cad var1, int var2, long var3, String var5, Object var6, Object var7) {
         super(var1, var2, var3);
         this.gO = var5;
         this.nf = var6;
         this.gP = var7;
      }

      @Override
      cad.eo q() {
         return cad.eo.Dw;
      }

      @Override
      String RF() {
         return this.gO;
      }

      @Override
      public void run() {
         try {
            this.xK();
            cah var1 = this.RF.Dw();
            JvmFieldSig var17 = JvmFieldSig.parse(this.gO);
            String var3 = var17.csig;
            String var4 = var17.fname;
            boolean var5 = false;
            Class var6 = var1.RF(var3);

            Field var7;
            try {
               var7 = var6.getDeclaredField(var4);
            } catch (NoSuchFieldException var13) {
               var7 = var6.getField(var4);
            }

            if (!var5) {
               Object[] var18 = new Object[]{this.Dw, this.gO, cad.q(this.nf), cad.q(this.gP)};
            }

            if (!var7.canAccess(this.nf)) {
               var7.setAccessible(true);
            }

            var7.set(this.nf, this.gP);
         } catch (Exception var14) {
            String var2 = new cad.CU(var14).q();
            Object[] var10000 = new Object[]{this.Dw, var2};
            this.oW = var14;
         } catch (Throwable var15) {
            throw var15;
         } finally {
            this.q = true;
         }
      }
   }

   private class tw implements IJLSTypeAdapter {
      @Override
      public IJLSType getType(String var1) {
         try {
            Class var2 = cad.this.q(var1);
            return new bkl(var1, var2.getModifiers());
         } catch (DexDecEvaluationException var3) {
            return null;
         }
      }

      @Override
      public String getSupertype(String var1) {
         try {
            Class var2 = cad.this.q(var1).getSuperclass();
            return var2 == null ? null : JvmUtil.generateTypeSig(var2);
         } catch (DexDecEvaluationException var3) {
            return null;
         }
      }

      @Override
      public List getInterfaces(String var1) {
         try {
            Class[] var2 = cad.this.q(var1).getInterfaces();
            ArrayList var3 = new ArrayList(var2.length);

            for (Class var7 : var2) {
               var3.add(JvmUtil.generateTypeSig(var7));
            }

            return var3;
         } catch (DexDecEvaluationException var8) {
            return null;
         }
      }

      @Override
      public List getParentTypes(String var1) {
         try {
            Class var2 = cad.this.q(var1);
            Class var3 = var2.getSuperclass();
            Class[] var4 = var2.getInterfaces();
            ArrayList var5 = new ArrayList((var3 == null ? 0 : 1) + var4.length);
            if (var3 != null) {
               var5.add(JvmUtil.generateTypeSig(var3));
            }

            for (Class var9 : var4) {
               var5.add(JvmUtil.generateTypeSig(var9));
            }

            return var5;
         } catch (DexDecEvaluationException var10) {
            return null;
         }
      }

      @Override
      public List getMethods(String var1) {
         try {
            Class var2 = cad.this.q(var1);
            Executable[][] var3 = new Executable[][]{var2.getDeclaredConstructors(), var2.getDeclaredMethods()};
            ArrayList var4 = new ArrayList(var3[0].length + var3[1].length);

            for (Executable[] var8 : var3) {
               for (Executable var12 : var8) {
                  String var13 = this.q(var12);
                  var4.add(new bkk(var12 instanceof Method ? var12.getName() : "<init>", var13, var12.getModifiers()));
               }
            }

            return var4;
         } catch (DexDecEvaluationException var14) {
            return null;
         }
      }

      @Override
      public IJLSMethod findMethod(String param1, String param2, String param3) {
         // $VF: Couldn't be decompiled
         // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
         // java.lang.RuntimeException: parsing failure!
         //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
         //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
         //
         // Bytecode:
         // 00: aload 0
         // 01: getfield com/pnfsoftware/jebglobal/cad$tw.q Lcom/pnfsoftware/jebglobal/cad;
         // 04: aload 1
         // 05: invokevirtual com/pnfsoftware/jebglobal/cad.q (Ljava/lang/String;)Ljava/lang/Class;
         // 08: astore 4
         // 0a: aload 2
         // 0b: ldc "<init>"
         // 0d: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
         // 10: ifeq 5b
         // 13: aload 4
         // 15: invokevirtual java/lang/Class.getDeclaredConstructors ()[Ljava/lang/reflect/Constructor;
         // 18: astore 5
         // 1a: aload 5
         // 1c: arraylength
         // 1d: istore 6
         // 1f: bipush 0
         // 20: istore 7
         // 22: iload 7
         // 24: iload 6
         // 26: if_icmpge 58
         // 29: aload 5
         // 2b: iload 7
         // 2d: aaload
         // 2e: astore 8
         // 30: aload 0
         // 31: aload 8
         // 33: invokevirtual com/pnfsoftware/jebglobal/cad$tw.q (Ljava/lang/reflect/Executable;)Ljava/lang/String;
         // 36: astore 9
         // 38: aload 9
         // 3a: aload 3
         // 3b: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
         // 3e: ifeq 52
         // 41: new com/pnfsoftware/jebglobal/bkk
         // 44: dup
         // 45: ldc "<init>"
         // 47: aload 9
         // 49: aload 8
         // 4b: invokevirtual java/lang/reflect/Constructor.getModifiers ()I
         // 4e: invokespecial com/pnfsoftware/jebglobal/bkk.<init> (Ljava/lang/String;Ljava/lang/String;I)V
         // 51: areturn
         // 52: iinc 7 1
         // 55: goto 22
         // 58: goto b0
         // 5b: aload 4
         // 5d: invokevirtual java/lang/Class.getDeclaredMethods ()[Ljava/lang/reflect/Method;
         // 60: astore 5
         // 62: aload 5
         // 64: arraylength
         // 65: istore 6
         // 67: bipush 0
         // 68: istore 7
         // 6a: iload 7
         // 6c: iload 6
         // 6e: if_icmpge b0
         // 71: aload 5
         // 73: iload 7
         // 75: aaload
         // 76: astore 8
         // 78: aload 8
         // 7a: invokevirtual java/lang/reflect/Method.getName ()Ljava/lang/String;
         // 7d: astore 9
         // 7f: aload 9
         // 81: aload 2
         // 82: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
         // 85: ifeq aa
         // 88: aload 0
         // 89: aload 8
         // 8b: invokevirtual com/pnfsoftware/jebglobal/cad$tw.q (Ljava/lang/reflect/Executable;)Ljava/lang/String;
         // 8e: astore 10
         // 90: aload 10
         // 92: aload 3
         // 93: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
         // 96: ifeq aa
         // 99: new com/pnfsoftware/jebglobal/bkk
         // 9c: dup
         // 9d: aload 9
         // 9f: aload 10
         // a1: aload 8
         // a3: invokevirtual java/lang/reflect/Method.getModifiers ()I
         // a6: invokespecial com/pnfsoftware/jebglobal/bkk.<init> (Ljava/lang/String;Ljava/lang/String;I)V
         // a9: areturn
         // aa: iinc 7 1
         // ad: goto 6a
         // b0: aconst_null
         // b1: areturn
         // b2: pop
         // b3: aconst_null
         // b4: areturn
      }

      @Override
      public List getFields(String var1) {
         try {
            Field[] var2 = cad.this.q(var1).getDeclaredFields();
            ArrayList var3 = new ArrayList(var2.length);

            for (Field var7 : var2) {
               String var8 = JvmUtil.generateTypeSig(var7.getType());
               var3.add(new bki(var7.getName(), var8, var7.getModifiers()));
            }

            return var3;
         } catch (DexDecEvaluationException var9) {
            return null;
         }
      }

      @Override
      public List getTypeAnnotations(String var1) {
         return null;
      }

      private String q(Executable var1) {
         StringBuilder var2 = new StringBuilder();
         var2.append("(");

         for (Class var6 : var1.getParameterTypes()) {
            var2.append(JvmUtil.generateTypeSig(var6));
         }

         var2.append(")");
         if (var1 instanceof Method) {
            var2.append(JvmUtil.generateTypeSig(((Method)var1).getReturnType()));
         } else {
            var2.append("V");
         }

         return var2.toString();
      }
   }
}
