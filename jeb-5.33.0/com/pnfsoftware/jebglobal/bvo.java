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

public class bvo {
   private static final ILogger fI = GlobalLog.getLogger(bvo.class);
   private static final int WR = SystemUtil.getMajorJavaVersion();
   public static final long pC = 10000L;
   private static final String NS;
   private static final String vP;
   private static final String xC;
   private static final String ED;
   public static boolean A;
   private static AtomicInteger Sc;
   private static AtomicInteger ah;
   private static Object eP;
   private static bvv UO;
   private static boolean Ab;
   private bvs rl;
   private btp z;
   private long Ek;
   private ExecutorService hK;
   private ExecutorService Er;
   public boolean kS = true;
   List wS = new ArrayList();
   public int UT;
   public int E;
   public int sY;
   public long ys;
   public long ld;
   private bvo.K FE;
   public static final String gp;
   private static final Map Aj;
   private static final Set EX;
   private static final Set LM;
   private static final Set mv;
   private static final Set sO;
   private InputStream os = new bvr(this);
   private FileDescriptor Cu = new FileDescriptor();
   private static final AtomicLong hZ;
   static String oT;
   private static String UW;
   private Map PR = new HashMap();
   private static final Set cX;

   static boolean pC(Thread var0) {
      String var1 = var0.getName();
      return var1 != null && var1.startsWith(NS);
   }

   static boolean A(Thread var0) {
      String var1 = var0.getName();
      return var1 != null && var1.startsWith(vP);
   }

   static boolean kS(Thread var0) {
      String var1 = var0.getName();
      return var1 != null && (var1.startsWith(vP) || var1.startsWith(NS));
   }

   public bvo(btp var1) throws Exception {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.z = var1;
         this.rl = new bvs(var1);
         synchronized (eP) {
            if (UO == null) {
               UO = new bvv();
            }

            if (System.getSecurityManager() != UO) {
               try {
                  System.setSecurityManager(UO);
               } catch (UnsupportedOperationException var5) {
                  if (!Ab) {
                     Ab = true;
                     fI.error(S.L("The Java SecurityManager is unavailable! dexdec's sandboxing capabilities will be restricted to emulation only."));
                     fI.error(S.L("Your JDK version is %d. We recommend JDK 17, for which the SecurityManager is enabled by default."), WR);
                     fI.error(S.L("With Java 18 or above, you must explicitly enable the SecurityManager."));
                     fI.error(S.L("Example: you may start JEB like this: %s"), "`$ java -Djava.security.manager=allow -jar bin/app/jebc.jar`");
                     fI.catchingSilent(var5);
                  }

                  throw var5;
               } catch (SecurityException var6) {
                  if (!Ab) {
                     Ab = true;
                     fI.error(S.L("The Java SecurityManager cannot be used! dexdec's sandboxing capabilities will be restricted to emulation only."));
                     fI.catchingSilent(var6);
                  }

                  throw var6;
               }
            }
         }

         this.pC(10000L);
         Object var8 = null;
         this.Er = Executors.newFixedThreadPool(1, new bvp(this, (ThreadGroup)var8));
         this.hK = Executors.newFixedThreadPool(1, new bvq(this, (ThreadGroup)var8));
      }
   }

   public void pC() {
      if (!this.A()) {
         if (this.Er != null) {
            this.Er.shutdown();
            this.Er = null;
         }

         if (this.hK != null) {
            this.hK.shutdown();
            this.hK = null;
         }

         if (this.FE == null || !this.FE.pC) {
            for (AutoCloseable var2 : this.wS) {
               try {
                  var2.close();
               } catch (Exception var3) {
               }
            }
         }

         this.FE = null;
         this.rl.pC();
         this.z = null;
      }
   }

   public boolean A() {
      return this.z == null;
   }

   public File pC(String var1, boolean var2) throws IOException {
      return UO.pC(var1, var2);
   }

   public File kS() {
      return UO.pC();
   }

   public bvs wS() {
      return this.rl;
   }

   public void pC(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException();
      } else {
         this.Ek = var1;
      }
   }

   public long UT() {
      return this.Ek;
   }

   boolean pC(bvo.Av var1, String var2) {
      boolean var3 = Thread.currentThread().getContextClassLoader() == this.rl;
      btp.pC(4, ">>>>> [%b] SANBOX EMULATION: %s: %s", var3, var1, var2);
      return var3;
   }

   boolean pC(boolean var1, bvo.Av var2, String var3) {
      btp.pC(5, "<<<<< [%b] SANBOX EMULATION: %s: %s", var1, var2, var3);
      return var1;
   }

   private void pC(bvo.K var1) throws Exception {
      int var2 = var1.kS;
      bvo.Av var3 = var1.pC();
      String var4 = var1.A();
      Thread var5 = Thread.currentThread();
      boolean var6 = false;
      if (pC(var5)) {
         var6 = true;
      } else if (var2 >= 1 && A(var5)) {
         var6 = true;
      } else if (var2 >= 2) {
         var6 = true;
      }

      if (var6) {
         var1.run();
      } else {
         long var7 = Math.min(this.z.getTimeLeft(), this.UT());
         if (var7 <= 0L) {
            throw new RuntimeException(
               ckx.pC(
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
                     21,
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
                  222
               )
            );
         } else if (this.FE != null && !this.FE.pC) {
            String var16 = Strings.ff(
               ckx.pC(
                  new byte[]{
                     57,
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
                  109
               ),
               var3,
               var4
            );
            throw new RuntimeException(var16);
         } else {
            this.FE = var1;
            ExecutorService var9;
            if (var2 >= 1) {
               var9 = this.hK;
            } else {
               var9 = this.Er;
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
                  this.ys = this.ys + (System.nanoTime() - var10);
               } else {
                  this.ld = this.ld + (System.nanoTime() - var10);
               }
            }
         }
      }
   }

   public Class pC(String var1) throws DexDecEvaluationException {
      Class var2 = this.pC(var1, null);
      if (var2 == null) {
         throw new DexDecEvaluationException(
            ckx.pC(
                  new byte[]{
                     -3,
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
                  168
               )
               + var1
         );
      } else {
         return var2;
      }
   }

   public Class pC(btj.Av var1) throws DexDecEvaluationException {
      Class var2;
      if (var1.pC()) {
         var2 = this.pC(var1.A());
      } else {
         var2 = this.pC(var1.wS(), var1);
      }

      if (var2 == null) {
         throw new DexDecEvaluationException(
            ckx.pC(
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
                     91,
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
                  65
               )
               + var1
         );
      } else {
         return var2;
      }
   }

   private Class pC(String var1, btj.Av var2) throws DexDecEvaluationException {
      if (var2 == null) {
         Class var3 = this.rl.pC(var1);
         if (var3 != null) {
            return var3;
         }
      }

      long var16 = ld();
      boolean var5 = this.pC(bvo.Av.pC, var1);
      byte var6 = 0;
      if (this.kS && var1.startsWith("Ljava/")) {
         var6 = 1;
      }

      bvo.DH var7 = new bvo.DH(this, var6, var16, var1, var2);

      Class var8;
      try {
         this.pC((bvo.K)var7);
         if (var7.sY != null) {
            throw var7.sY;
         }

         var8 = var7.E;
      } catch (DexDecEvaluationException var13) {
         throw var13;
      } catch (Exception var14) {
         if (var14 instanceof ClassNotFoundException && !JavaUtil.isValidInternalClassname(var1)) {
            throw new DexDecEvalCodeThrownException(this.z.registerObject(var14));
         }

         throw new DexDecEvalSandboxExecutionException(var14);
      } finally {
         this.pC(var5, bvo.Av.pC, var1);
      }

      return var8;
   }

   private static void ys() {
      String var0 = null;
      byte var1 = 0;

      for (String var5 : Strings.splitLines(Strings.decodeUTF8(AssetManager.getAssetBytes(gp)))) {
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
               Aj.put(var8, Integer.valueOf(var7));
            }
         }
      }
   }

   private boolean pC(InputStream var1) {
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
               return this.pC(var4);
            } catch (Exception var6) {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private boolean pC(String var1, Object var2) {
      switch (ckx.kS(var1)) {
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

   private Object UT(Object var1) {
      if (var1 == System.in) {
         return this.os;
      } else {
         return var1 == FileDescriptor.in ? this.Cu : var1;
      }
   }

   public IDImm pC(DInvokeType var1, String var2, List var3, btj.Av var4) throws DexDecEvaluationException {
      this.UT++;
      JvmMethodSig var5 = JvmMethodSig.parse(var2);
      String var6 = var5.csig;
      String var7 = var5.mname;
      String var8 = var5.rettype;
      List var9 = var5.partypes;
      boolean var11 = var7.equals("<init>");
      List var12 = null;
      long var13 = ld();
      if (this.z.NS().pC()) {
         if (var11) {
            var12 = pC(this.z, var3, var9, var6, false);
            Wrapper var15 = this.z.NS().newInstance(var13, this.z.eP(), var2, var12);
            if (var15 != null) {
               Object var10 = var15.get();
               this.z.NS().examineCreatedInstance(var13, var10);
               if (!A(var2)) {
                  btp.pC(2, "(%d) HOOK: new-instance: %s: %s", var13, var2, pC(var12));
                  btp.pC(2, ">%d) HOOK: %s", var13, pC(var10));
               }

               return this.z.pC("L", var10);
            }
         } else {
            boolean var40 = var1 != DInvokeType.STATIC;
            var12 = pC(this.z, var3, var9, var6, var40);
            Object var16 = null;
            Object var17 = var12;
            if (var40) {
               var16 = var12.get(0);
               var17 = new ArrayList(var12.subList(1, var12.size()));
            }

            Wrapper var18 = this.z.NS().invokeMethod(var13, this.z.eP(), var2, var16, (List)var17);
            if (var18 != null) {
               Object var39 = var18.get();
               var18 = this.z.NS().examineMethodResult(var13, var39);
               if (var18 != null) {
                  if (!A(var2)) {
                     btp.pC(2, "POST-HOOK: invoke: %s", var2);
                  }

                  var39 = var18.get();
               }

               if (!A(var2)) {
                  btp.pC(2, "(%d) HOOK: invoke: %s: %s %s", var13, var2, pC(var16), pC(var17));
                  btp.pC(2, ">%d) HOOK: %s", var13, pC(var39));
               }

               return this.z.pC(var8, var39);
            }
         }
      }

      if (!this.z.A(var2)) {
         throw new DexDecEvaluationException(
            ckx.pC(
                  new byte[]{19, 0, 28, 16, 17, 16, 71, 14, 27, 82, 74, 10, 77, 83, 17, 77, 81, 86, 12, 69, 74, 85, 81, 64, 88, 73, 32, 28, 65, 12, 10, 69},
                  2,
                  11
               )
               + var2
         );
      } else {
         boolean var41 = this.pC(bvo.Av.A, var2);
         int var42 = 0;
         if (this.kS) {
            try {
               if (EX.contains(var2)) {
                  var42 = 1;
               } else if (LM.contains(var2)) {
                  var42 = 2;
               } else if (var2.contains("-><init>(")) {
                  if (var4 == null || var4.pC) {
                     var42 = (Integer)Aj.getOrDefault(var2, 0);
                  }
               } else if (var1 == DInvokeType.STATIC) {
                  if (var2.startsWith("Ljava/") || var2.startsWith("Ljavax/")) {
                     var42 = (Integer)Aj.getOrDefault(var2, 0);
                  }
               } else if (var1 != DInvokeType.NEW && var1 != DInvokeType.DIRECT) {
                  if ((var1 == DInvokeType.VIRTUAL || var1 == DInvokeType.INTERFACE || var1 == DInvokeType.SUPER || var1 == DInvokeType.DIRECT)
                     && var3.size() >= 1) {
                     Object var43 = this.z.getObject((IDImm)var3.get(0), false);
                     if (var43 != null) {
                        String var45 = var43.getClass().getName();
                        if (var45.startsWith("java.") || var45.startsWith("javax.")) {
                           var42 = (Integer)Aj.getOrDefault(var2, 0);
                           if (var42 == 0 && this.pC(var2, var43)) {
                              var42 = 1;
                           }
                        }
                     }
                  }
               } else if (var2.startsWith("Ljava/") || var2.startsWith("Ljavax/")) {
                  var42 = (Integer)Aj.getOrDefault(var2, 0);
               }
            } catch (DexDecEvaluationException var36) {
            }
         }

         bvo.cq var44 = new bvo.cq(this, var42, var13, var1, var2, var3, var12, var4);

         Object var37;
         try {
            this.pC((bvo.K)var44);
            if (var44.sY != null) {
               throw var44.sY;
            }

            var37 = var44.E;
         } catch (InvocationTargetException var32) {
            Throwable var19 = var32.getCause();
            if (var19 instanceof RuntimeException && Strings.isContainedIn(var19.getMessage(), xC, ED)) {
               btp.pC(1, "invoke failed on: %s: %s", var2, pC(var12));
               throw new DexDecEvalStubException(var2);
            }

            if (var19 instanceof NoSuchFieldException && ckx.pC(80, 3891532, -1841955043, var2)) {
               try {
                  Class var20 = (Class)var12.get(0);
                  String var21 = var20.getName();
                  if ((
                        var21.startsWith(ckx.pC(new byte[]{92, 15, 10, 22, 29, 6, 13, 74}, 1, 61))
                           || var21.startsWith(ckx.pC(new byte[]{-49, 5, 13, 26, 31, 2, 69}, 1, 171))
                     )
                     && !pC(Thread.currentThread())) {
                     String var22 = var21 + "." + (String)var12.get(1);
                     RuntimeException var23 = new RuntimeException(
                        ckx.pC(new byte[]{63, 27, 5, 5, 1, 24, 25, 78, 65, 7, 17, 87, 70, 15, 12, 9, 8, 94, 26}, 1, 74) + var22
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
            this.pC(var41, bvo.Av.A, var2);
         }

         if (var11) {
            this.z.NS().examineCreatedInstance(var13, var37);
         } else {
            Wrapper var46 = this.z.NS().examineMethodResult(var13, var37);
            if (var46 != null) {
               if (!A(var2)) {
                  btp.pC(2, "POST-HOOK: invoke: %s", var2);
               }

               var37 = var46.get();
            }
         }

         if (var11) {
            return var44.ys;
         } else {
            var37 = this.UT(var37);
            return this.z.pC(var8, var37);
         }
      }
   }

   public IDImm pC(String var1, IDImm var2) throws DexDecEvaluationException {
      this.E++;
      JvmFieldSig var3 = JvmFieldSig.parse(var1);
      String var4 = var3.csig;
      String var5 = var3.ftype;
      boolean var6 = var2 != null;
      List var7 = pC(this.z, var2 == null ? Arrays.asList() : Arrays.asList(var2), Arrays.asList(), var4, var6);
      Object var9 = var6 ? var7.get(0) : null;
      long var10 = ld();
      if (this.z.NS().pC()) {
         Wrapper var12 = this.z.NS().getField(var10, this.z.eP(), var1, var9);
         if (var12 != null) {
            Object var24 = var12.get();
            if (!A(var1)) {
               btp.pC(2, "(%d) HOOK: field.get: %s: obj=%s", var10, var1, pC(var9));
               btp.pC(2, ">%d) %s", var10, pC(var24));
            }

            var12 = this.z.NS().examineFieldValue(var10, var24);
            if (var12 != null) {
               if (!A(var1)) {
                  btp.pC(2, "POST-HOOK: field.get: %s", var1);
               }

               var24 = var12.get();
            }

            return this.z.pC(var5, var24);
         }
      }

      if (!this.z.kS(var1)) {
         throw new DexDecEvaluationException(
            ckx.pC(new byte[]{19, 0, 28, 16, 17, 16, 71, 14, 27, 82, 74, 10, 77, 83, 17, 75, 92, 82, 72, 13, 83, 83, 81, 80, 95, 83, 111, 29, 7, 67}, 2, 91)
               + var1
         );
      } else {
         boolean var25 = this.pC(bvo.Av.kS, var1);
         byte var13 = 0;
         if (this.kS && (var4.startsWith("Ljava/") || var4.startsWith("Ljavax/"))) {
            var13 = 2;
         }

         bvo.Ws var14 = new bvo.Ws(this, var13, var10, var1, var9);

         Object var8;
         try {
            this.pC((bvo.K)var14);
            if (var14.sY != null) {
               throw var14.sY;
            }

            var8 = var14.E;
         } catch (DexDecEvaluationException var20) {
            throw var20;
         } catch (Exception var21) {
            throw new DexDecEvalSandboxExecutionException(var21);
         } finally {
            this.pC(var25, bvo.Av.kS, var1);
         }

         Wrapper var15 = this.z.NS().examineFieldValue(var10, var8);
         if (var15 != null) {
            if (!A(var1)) {
               btp.pC(2, "POST-HOOK: field.get: %s", var1);
            }

            var8 = var15.get();
         }

         var8 = this.UT(var8);
         return this.z.pC(var5, var8);
      }
   }

   public void pC(String var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      this.sY++;
      JvmFieldSig var4 = JvmFieldSig.parse(var1);
      String var5 = var4.csig;
      String var6 = var4.ftype;
      boolean var7 = var2 != null;
      List var8 = pC(this.z, var2 == null ? Arrays.asList(var3) : Arrays.asList(var2, var3), Arrays.asList(var6), var5, var7);
      Object var9;
      Object var10;
      if (var7) {
         var9 = var8.get(0);
         var10 = var8.get(1);
      } else {
         var9 = null;
         var10 = var8.get(0);
      }

      long var11 = ld();
      if (this.z.NS().pC()) {
         Object[] var13 = new Object[]{var10};
         Boolean var14 = this.z.NS().setField(var11, this.z.eP(), var1, var9, var13);
         if (var14 != null) {
            if (var14) {
               if (!A(var1)) {
                  btp.pC(2, "(%d) HOOK: field.set: %s: obj=%s val=%s", var11, var1, pC(var9), pC(var10));
               }

               return;
            }

            var10 = var13[0];
         }
      }

      if (!this.z.wS(var1)) {
         throw new DexDecEvaluationException(
            ckx.pC(new byte[]{-125, 63, 3, 5, 10, 26, 89, 70, 9, 29, 16, 11, 13, 23, 83, 87, 5, 27, 29, 17, 72, 76, 2, 0, 6, 22, 0, 83, 79, 9, 70}, 1, 211)
               + var1
         );
      } else {
         boolean var24 = this.pC(bvo.Av.wS, var1);
         byte var25 = 0;
         bvo.bO var15 = new bvo.bO(this, var25, var11, var1, var9, var10);

         try {
            this.pC((bvo.K)var15);
            if (var15.E != null) {
               throw var15.E;
            }
         } catch (DexDecEvaluationException var21) {
            throw var21;
         } catch (Exception var22) {
            throw new DexDecEvalSandboxExecutionException(var22);
         } finally {
            this.pC(var24, bvo.Av.wS, var1);
         }
      }
   }

   private static final long ld() {
      return hZ.getAndIncrement();
   }

   static String pC(Object var0) {
      return "null";
   }

   private static boolean A(String var0) {
      return false;
   }

   Class pC(btj.Av var1, Class var2) throws Exception {
      String var3 = this.pC(var2, var1.wS());

      Class var4;
      try {
         var4 = this.rl.loadClass(var3);
      } catch (ClassNotFoundException var5) {
         var4 = new ByteBuddy()
            .makeEnumeration(new String[]{"V1"})
            .name(var3)
            .method(ElementMatchers.isMethod())
            .intercept(MethodDelegation.to(this.z.ld()))
            .defineField(UW, int.class, new ForField[]{Visibility.PUBLIC})
            .make()
            .load(this.rl, Default.INJECTION)
            .getLoaded();
         var1.pC = true;
      }

      return var4;
   }

   private synchronized String pC(Class var1, String var2) {
      String var3 = var1.getName() + "_" + var2;
      String var4 = Integer.toHexString(var3.hashCode());
      String var5 = var4;
      int var6 = 1;

      while (true) {
         String var7 = (String)this.PR.get(var5);
         if (var7 == null || var7.equals(var3)) {
            this.PR.put(var5, var3);
            return oT + var1.getSimpleName() + "_" + var5;
         }

         var5 = var4 + "_" + var6;
         var6++;
      }
   }

   private String kS(String var1) {
      int var2 = var1.lastIndexOf(95);
      String var3 = var1.substring(var2 + 1);
      String var4 = (String)this.PR.get(var3);
      Assert.a(var4.endsWith("#"));
      return var4;
   }

   public static boolean A(Object var0) {
      return var0.getClass().getName().startsWith(oT);
   }

   public static boolean kS(Object var0) {
      return var0 instanceof Class && ((Class)var0).getName().startsWith(oT);
   }

   public static void pC(Object var0, DInvokeType var1) {
      try {
         var0.getClass().getDeclaredField(UW).setInt(var0, var1 == DInvokeType.SUPER ? 1 : 0);
      } catch (Exception var2) {
         throw new RuntimeException();
      }
   }

   public static int wS(Object var0) {
      try {
         Field var1 = var0.getClass().getDeclaredField(UW);
         int var2 = var1.getInt(var0);
         var1.setInt(var0, 0);
         return var2;
      } catch (Exception var3) {
         throw new RuntimeException();
      }
   }

   private static List pC(btp var0, List var1, List var2, String var3, boolean var4) throws DexDecEvaluationException {
      ArrayList var5 = new ArrayList(var1.size());
      int var6 = 0;
      if (var4) {
         IDImm var7 = (IDImm)var1.get(var6++);
         Object var8 = var0.pC(var3, var7);
         var5.add(var8);
      }

      for (int var11 = 0; var11 < var2.size(); var6++) {
         String var12 = (String)var2.get(var11);
         IDImm var9 = (IDImm)var1.get(var6);
         Object var10 = var0.pC(var12, var9);
         var5.add(var10);
         var11++;
      }

      return var5;
   }

   public IJLSTypeAdapter E() {
      return new bvo.rQ();
   }

   public String sY() {
      StringBuilder var1 = new StringBuilder("dexdec sandbox stats:\n");
      Strings.ff(var1, "total invoke: %d\n", this.UT);
      Strings.ff(var1, "total field.get: %d\n", this.E);
      Strings.ff(var1, "total field.set: %d\n", this.sY);
      Strings.ff(var1, "time spent in... worker threads: %d ms\n", this.ys / 1000000L);
      Strings.ff(var1, "time spent in... sandbox threads: %d ms\n", this.ld / 1000000L);
      return var1.toString();
   }

   static {
      if (WR >= 17) {
         try {
            bvt.pC(bvo.class);
         } catch (InaccessibleObjectException var2) {
            String var1 = ckx.pC(new byte[]{-59, 28, 27, 2, 31, 4, 90, 90, 12, 12}, 1, 175);
            fI.warn(S.L("The sandbox component of the dex emulator is limited!"));
            fI.warn(
               S.L("If your workload includes decompiling obfuscated dex code, consider appending this to the file %s (located in your JEB root folder):"),
               var1
            );
            fI.warn(
               ckx.pC(
                  new byte[]{
                     96,
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
                  77
               )
            );
            fI.warn(S.L("Note that %s must contain a single line. Multiple options are separated by space."), var1);
         } catch (Exception var3) {
            fI.error(S.L("The sandbox component of the dex emulator is limited!"));
            fI.catchingSilent(var3);
         }
      }

      NS = ckx.pC(new byte[]{-100, 15, 7, 79, 73, 1, 29, 28, 1, 6, 78, 94, 17, 79, 94, 7, 89}, 1, 246);
      vP = ckx.pC(new byte[]{111, 15, 7, 79, 73, 1, 29, 28, 1, 6, 78, 94, 17, 79, 90, 3, 89}, 1, 5);
      xC = ckx.pC(new byte[]{16, 27, 5, 27, 83}, 2, 166);
      ED = ckx.pC(new byte[]{16, 27, 5, 27, 64, 72}, 2, 233);
      A = true;
      Sc = new AtomicInteger();
      ah = new AtomicInteger();
      eP = new Object();
      UO = null;
      Ab = false;
      gp = ckx.pC(new byte[]{68, 0, 23, 17, 17, 30, 1, 66, 90, 12, 12}, 1, 32);
      Aj = new HashMap();
      EX = new HashSet();
      LM = new HashSet();
      ys();
      EX.add(
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
               38,
               71,
               85,
               92,
               2,
               20,
               13,
               66,
               65,
               95,
               85,
               26,
               28,
               96,
               74,
               46,
               4,
               0,
               76,
               0,
               4,
               78,
               6,
               65,
               55,
               91,
               29,
               27,
               78,
               14,
               79
            },
            2,
            177
         )
      );
      EX.add(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 38, 71, 85, 92, 2, 20, 13, 67, 82, 86, 89, 92, 84, 64, 8, 102, 59}, 2, 227));
      LM.add(
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
               24,
               18,
               76,
               32,
               19,
               5,
               32,
               0,
               4,
               83,
               18,
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
               53,
               29,
               30,
               0,
               15,
               19,
               94,
               90,
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
               48,
               76,
               19,
               22,
               0,
               94
            },
            2,
            144
         )
      );
      LM.add(
         ckx.pC(
            new byte[]{
               -4,
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
            176
         )
      );
      LM.add(
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
               24,
               18,
               70,
               38,
               28,
               5,
               32,
               0,
               4,
               83,
               18,
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
               53,
               29,
               30,
               0,
               15,
               19,
               94,
               90,
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
               48,
               76,
               19,
               22,
               0,
               94
            },
            2,
            151
         )
      );
      LM.add(
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
               24,
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
               120
            },
            2,
            185
         )
      );
      LM.add(
         ckx.pC(
            new byte[]{
               -111,
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
            221
         )
      );
      LM.add(
         ckx.pC(
            new byte[]{
               -30,
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
            174
         )
      );
      mv = new HashSet();
      mv.add("Ljava/nio/charset/StandardCharsets;->ISO_8851_9:Ljava/nio/charset/Charset;");
      mv.add("Ljava/nio/charset/StandardCharsets;->US_ASCII:Ljava/nio/charset/Charset;");
      mv.add("Ljava/nio/charset/StandardCharsets;->UTF_16:Ljava/nio/charset/Charset;");
      mv.add("Ljava/nio/charset/StandardCharsets;->UTF_16BE:Ljava/nio/charset/Charset;");
      mv.add("Ljava/nio/charset/StandardCharsets;->UTF_16LE:Ljava/nio/charset/Charset;");
      mv.add("Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;");
      sO = new IdentityHashSet();
      sO.add(StandardCharsets.ISO_8859_1);
      sO.add(StandardCharsets.US_ASCII);
      sO.add(StandardCharsets.UTF_16);
      sO.add(StandardCharsets.UTF_16BE);
      sO.add(StandardCharsets.UTF_16LE);
      sO.add(StandardCharsets.UTF_8);
      hZ = new AtomicLong(1L);
      oT = ckx.pC(new byte[]{41, 10, 18, 29, 11, 7, 73, 12, 17, 88, 76, 6, 74, 14, 88, 75, 74, 81, 2}, 2, 68);
      UW = ckx.pC(new byte[]{44, 26, 4, 10, 27, 13, 2, 63, 27, 82, 68, 7, 96, 78, 87, 86}, 2, 107);
      cX = new HashSet();
      cX.add("Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;");
   }

   private static enum Av {
      pC,
      A,
      kS,
      wS;
   }

   static class DH extends bvo.K {
      private String ys;
      private btj.Av ld;
      Class E;
      Exception sY;

      DH(bvo var1, int var2, long var3, String var5, btj.Av var6) {
         super(var1, var2, var3);
         this.ys = var5;
         this.ld = var6;
      }

      @Override
      bvo.Av pC() {
         return bvo.Av.pC;
      }

      @Override
      String A() {
         return this.ys;
      }

      @Override
      public void run() {
         try {
            this.kS();
            bvs var1 = this.A.wS();
            Class var17;
            if (this.ld == null) {
               var17 = var1.A(this.ys);
            } else {
               Class var3 = var1.A(this.ld.A());
               String var4 = this.A.pC(var3, this.ld.wS());

               try {
                  var17 = var1.loadClass(var4);
               } catch (ClassNotFoundException var13) {
                  btp.pC(3, "Creating proxy class: %s / %s / %s", var4, this.ld.wS(), this.ld.kS());
                  ArrayList var5 = new ArrayList();

                  for (String var7 : this.ld.kS()) {
                     var5.add(var1.A(var7));
                  }

                  var17 = new ByteBuddy()
                     .subclass(var3)
                     .implement(var5)
                     .name(var4)
                     .method(ElementMatchers.isMethod())
                     .intercept(MethodDelegation.to(this.UT.ld()))
                     .defineField(bvo.UW, int.class, new ForField[]{Visibility.PUBLIC})
                     .make()
                     .load(var1, Default.INJECTION)
                     .getLoaded();
                  this.ld.pC = true;
               }
            }

            this.E = var17;
         } catch (Exception var14) {
            String var2 = new bvo.Sv(var14).pC();
            btp.pC(1, "!%d) %s", this.wS, var2);
            this.sY = var14;
         } catch (Throwable var15) {
            throw var15;
         } finally {
            this.pC = true;
         }
      }
   }

   abstract static class K extends Thread {
      volatile boolean pC;
      bvo A;
      int kS;
      long wS;
      btp UT;

      K(bvo var1, int var2, long var3) {
         this.A = var1;
         this.kS = var2;
         this.wS = var3;
         this.UT = var1.z;
      }

      abstract bvo.Av pC();

      abstract String A();

      void kS() {
         ClassLoader var1 = Thread.currentThread().getContextClassLoader();
         bvs var2 = this.A.wS();
         if (this.kS == 0) {
            bvo.UO.A();
            Assert.a(var1 == var2);
         }
      }
   }

   public static class Sv {
      private Throwable pC;
      private Set A = new HashSet();

      public Sv(Throwable var1) {
         this.pC = var1;
      }

      public String pC() {
         return this.pC(this.pC);
      }

      private String pC(Throwable var1) {
         StringBuilder var2 = new StringBuilder();

         while (var1 != null) {
            if (!this.A.add(var1)) {
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
                  var2.append(this.pC(var7));
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

   static class Ws extends bvo.K {
      private String ys;
      private Object ld;
      Object E;
      Exception sY;

      Ws(bvo var1, int var2, long var3, String var5, Object var6) {
         super(var1, var2, var3);
         this.ys = var5;
         this.ld = var6;
      }

      @Override
      bvo.Av pC() {
         return bvo.Av.kS;
      }

      @Override
      String A() {
         return this.ys;
      }

      @Override
      public void run() {
         try {
            this.kS();
            bvs var1 = this.A.wS();
            JvmFieldSig var17 = JvmFieldSig.parse(this.ys);
            String var3 = var17.csig;
            String var4 = var17.fname;
            boolean var5 = bvo.cX.contains(this.ys);
            Class var6 = var1.A(var3);

            Field var7;
            try {
               var7 = var6.getDeclaredField(var4);
            } catch (NoSuchFieldException var13) {
               var7 = var6.getField(var4);
            }

            if (!var5) {
               btp.pC(2, "(%d) field.get: %s: obj=%s", this.wS, this.ys, bvo.pC(this.ld));
            }

            if (!var7.canAccess(this.ld)) {
               var7.setAccessible(true);
            }

            this.E = var7.get(this.ld);
            if (!var5) {
               btp.pC(2, ">%d) %s", this.wS, bvo.pC(this.E));
            }
         } catch (Exception var14) {
            String var2 = new bvo.Sv(var14).pC();
            btp.pC(1, "!%d) %s", this.wS, var2);
            this.sY = var14;
         } catch (Throwable var15) {
            throw var15;
         } finally {
            this.pC = true;
         }
      }
   }

   static class bO extends bvo.K {
      private String sY;
      private Object ys;
      private Object ld;
      Exception E;

      bO(bvo var1, int var2, long var3, String var5, Object var6, Object var7) {
         super(var1, var2, var3);
         this.sY = var5;
         this.ys = var6;
         this.ld = var7;
      }

      @Override
      bvo.Av pC() {
         return bvo.Av.wS;
      }

      @Override
      String A() {
         return this.sY;
      }

      @Override
      public void run() {
         try {
            this.kS();
            bvs var1 = this.A.wS();
            JvmFieldSig var17 = JvmFieldSig.parse(this.sY);
            String var3 = var17.csig;
            String var4 = var17.fname;
            boolean var5 = false;
            Class var6 = var1.A(var3);

            Field var7;
            try {
               var7 = var6.getDeclaredField(var4);
            } catch (NoSuchFieldException var13) {
               var7 = var6.getField(var4);
            }

            if (!var5) {
               btp.pC(2, "(%d) field.set: %s: obj=%s val=%s", this.wS, this.sY, bvo.pC(this.ys), bvo.pC(this.ld));
            }

            if (!var7.canAccess(this.ys)) {
               var7.setAccessible(true);
            }

            var7.set(this.ys, this.ld);
         } catch (Exception var14) {
            String var2 = new bvo.Sv(var14).pC();
            btp.pC(1, "!%d) %s", this.wS, var2);
            this.E = var14;
         } catch (Throwable var15) {
            throw var15;
         } finally {
            this.pC = true;
         }
      }
   }

   static class cq extends bvo.K {
      private DInvokeType ld;
      private String gp;
      private List oT;
      private List fI;
      private btj.Av WR;
      Object E;
      Exception sY;
      IDImm ys;

      cq(bvo var1, int var2, long var3, DInvokeType var5, String var6, List var7, List var8, btj.Av var9) {
         super(var1, var2, var3);
         this.ld = var5;
         this.gp = var6;
         this.oT = var7;
         this.fI = var8;
         this.WR = var9;
      }

      @Override
      bvo.Av pC() {
         return bvo.Av.A;
      }

      @Override
      String A() {
         return this.gp;
      }

      @Override
      public void run() {
         try {
            this.kS();
            bvs var1 = this.A.wS();
            JvmMethodSig var38 = JvmMethodSig.parse(this.gp);
            String var3 = var38.csig;
            String var4 = var38.mname;
            Class var5 = var1.A(var3);
            List var6 = var38.partypes;
            ArrayList var7 = new ArrayList(var6.size());
            Class[] var8 = new Class[var6.size()];
            int var9 = 0;

            for (String var11 : var6) {
               var8[var9] = var1.A(var11);
               var7.add(var8[var9]);
               var9++;
            }

            boolean var39 = bvo.A(this.gp);
            if (var4.equals("<init>")) {
               if (this.fI == null) {
                  this.fI = bvo.pC(this.UT, this.oT, var6, var3, false);
               }

               boolean var40 = false;
               Runnable var12 = null;
               if (var3.equals("Ljava/lang/Thread;")) {
                  var40 = true;
                  int var13 = var38.partypes.indexOf("Ljava/lang/Runnable;");
                  if (var13 >= 0) {
                     var12 = (Runnable)this.fI.get(var13);
                  }
               }

               if (!this.UT.A(this.gp)) {
                  throw new DexDecEvaluationException("Policy forbids the execution of " + this.gp);
               }

               Object var43;
               if (this.WR != null && (var5.getModifiers() & 16) == 0 && !var5.isArray() && !var5.isPrimitive()) {
                  if (!var39) {
                     btp.pC(2, "(%d) new proxy: %s: %s", this.wS, this.gp, bvo.pC(this.fI));
                  }

                  var43 = this.pC(this.WR, var5, var8, this.fI.toArray(new Object[this.fI.size()]));
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
                     btp.pC(2, "(%d) new: %s: %s", this.wS, this.gp, bvo.pC(this.fI));
                  }

                  var43 = var14.newInstance(this.fI.toArray(new Object[this.fI.size()]));
               }

               if (var1.UT.isInstance(var43)) {
                  ArrayList var45 = new ArrayList();

                  for (IDEmuFrame var16 : this.UT.getCurrentContext().getFrames()) {
                     var45.add(var16.getMethodSignature());
                  }

                  for (String var52 : this.UT.getCurrentContext().getOrigins()) {
                     var45.add(var52);
                  }

                  int var49 = var45.size();
                  Object var53 = Array.newInstance(var1.E, var49);
                  int var17 = 0;
                  Constructor var18 = var1.E.getConstructor(var1.wS, var1.wS, var1.wS, var1.loadClass("I"));

                  for (String var20 : var45) {
                     JvmMethodSig var21 = JvmMethodSig.parse(var20);
                     String var22 = bvs.kS(var21.csig);
                     String var23 = var21.mname;
                     Object var24 = var18.newInstance(var22, var23, null, -1);
                     Array.set(var53, var17, var24);
                     var17++;
                  }

                  var1.UT.getMethod(ckx.pC(new byte[]{48, 10, 4, 42, 6, 8, 4, 3, 32, 82, 73, 0, 76}, 2, 42), var1.sY).invoke(var43, var53);
               }

               if (var43 instanceof AutoCloseable) {
                  this.A.wS.add((AutoCloseable)var43);
               }

               this.E = var43;
               this.ys = this.UT.registerObject(var43);
               if (var40) {
                  bto var46 = this.UT.pC((Thread)var43);
                  var46.pC(var12);
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
                        if (bvo.WR > 15 || !bvo.A) {
                           if (!var39) {
                              btp.pC(2, "array-clone(jdk16+): %s", this.gp);
                           }

                           IDImm var50 = (IDImm)this.oT.get(0);
                           IDImm var54 = this.UT.cloneArray(var50);
                           this.E = this.UT.pC("[", var54);
                           return;
                        }

                        var42 = var5.getSuperclass().getDeclaredMethod(var4);
                        var42.setAccessible(true);
                     }
                  }
               }

               if (var42 == null) {
                  throw new NoSuchMethodException(ckx.pC(new byte[]{-78, 34, 15, 0, 1, 27, 84, 70, 15, 7, 10, 68, 77, 8, 17, 28, 7, 11, 94, 26}, 1, 241) + var4);
               }

               boolean var44 = !Modifier.isStatic(var42.getModifiers());
               if (this.fI == null) {
                  this.fI = bvo.pC(this.UT, this.oT, var6, var3, var44);
               }

               Object var47 = null;
               boolean var51 = false;
               if (var44) {
                  Object var58 = this.fI.get(0);
                  ArrayList var63 = new ArrayList(this.fI.subList(1, this.fI.size()));
                  String var66 = this.gp;
                  if (var58 instanceof ClassLoader && !this.gp.startsWith("Ljava/lang/ClassLoader;->")) {
                     int var68 = this.gp.indexOf("->");
                     var66 = "Ljava/lang/ClassLoader;->" + this.gp.substring(var68 + 2);
                  }

                  switch (ckx.kS(var66)) {
                     case -2088301382:
                     case -1280323317:
                        String var84 = (String)this.fI.get(1);
                        if (var84 != null) {
                           boolean var92 = ckx.pC(4, 6578540, -1289859604, var4);
                           btp.pC(1, "NATIVE: Load Library: %s (path=%b)", var84, var92);
                           this.UT.A(var84, var92);
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case -1965212556:
                     case -1377251652:
                     case -688057012:
                     case 1314245546:
                        String var83 = (String)this.fI.get(1);
                        String var91 = JavaUtil.toJvmName(var83);
                        btj var96 = this.UT.pC(var91, true);
                        var47 = var96.wS();
                        var51 = true;
                        break;
                     case -1921022599:
                        Class var82 = (Class)var58;
                        Class var90 = var82.getSuperclass();
                        if (var90 != null) {
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
                        Class var81 = (Class)var58;
                        Class var89 = var81.getSuperclass();
                        if (var89 != null && ckx.pC(14, 7171690, 1115224853, var89.getName())) {
                           var47 = true;
                           var51 = true;
                        }
                        break;
                     case -1097556805:
                        if (var63.get(0) == null) {
                           var47 = false;
                           var51 = true;
                        } else {
                           Class var80 = (Class)var58;
                           if (bvo.kS(var80)) {
                              btj var88 = this.UT.A(var80);
                              if (var88 != null) {
                                 String var95 = JvmUtil.generateTypeSig(var63.get(0).getClass());
                                 var47 = bgq.pC(this.UT.getTypeAdapter(), var95, var88.getFullName());
                                 var51 = true;
                              }
                           }
                        }
                        break;
                     case -866845516:
                     case 1061930248:
                        bto var79 = this.UT.A((Thread)var58);
                        if (var79 != null) {
                           var79.wS = 3;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case -179344045:
                        bto var78 = this.UT.A((Thread)var58);
                        if (var78 != null) {
                           var47 = var78.E;
                           var51 = true;
                        }
                        break;
                     case 115068398:
                        bto var77 = this.UT.A((Thread)var58);
                        if (var77 != null) {
                           var77.UT = true;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 596549722:
                        Class var76 = (Class)var58;
                        if (bvo.kS(var76)) {
                           btj var87 = this.UT.A(var76);
                           if (var87 != null) {
                              IJLSType var94 = this.UT.getTypeAdapter().getType(var87.getFullName());
                              var47 = (var94.getAccessFlags() & 8192) != 0;
                              var51 = true;
                           }
                        }
                        break;
                     case 643903912:
                        bto var75 = this.UT.A((Thread)var58);
                        if (var75 != null) {
                           var47 = var75.UT;
                           var51 = true;
                        }
                        break;
                     case 689031878:
                        var47 = false;
                        var51 = true;
                        break;
                     case 863762169:
                        Class var74 = (Class)var58;
                        if (bvo.kS(var74)) {
                           btj var86 = this.UT.A(var74);
                           if (var86 != null) {
                              var47 = false;
                              var51 = true;
                           }
                        }
                        break;
                     case 1113169250:
                        bto var73 = this.UT.A((Thread)var58);
                        if (var73 != null) {
                           var73.wS = 2;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 1149688541:
                        bto var72 = this.UT.A((Thread)var58);
                        if (var72 != null && var72.wS == 0) {
                           var72.wS = 1;
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 1630357828:
                        bto var71 = this.UT.A((Thread)var58);
                        if (var71 != null) {
                           var71.E = var63.get(0);
                           var47 = null;
                           var51 = true;
                        }
                        break;
                     case 1811282285:
                        Class var70 = (Class)var58;
                        if (bvo.kS(var70)) {
                           btj var85 = this.UT.A(var70);
                           if (var85 != null) {
                              IJLSType var93 = this.UT.getTypeAdapter().getType(var85.getFullName());
                              var47 = (var93.getAccessFlags() & 512) != 0;
                              var51 = true;
                           }
                        }
                        break;
                     default:
                        if (ckx.pC(17, 3894860, 1645344912, var3) && bvo.kS(var58)) {
                           btj var69 = this.UT.A((Class)var58);
                           var47 = var69.pC(this.gp, var63);
                           var51 = true;
                        } else if (var58 instanceof bth) {
                           var47 = ((bth)var58).pC(this.UT, this.gp, var63);
                           var51 = true;
                        }
                  }

                  if (var51) {
                     if (!var39) {
                        btp.pC(2, "(%d) invoke pseudo: %s: %s %s", this.wS, this.gp, bvo.pC(var58), bvo.pC(var63));
                     }
                  } else {
                     if (bvo.A(var58)) {
                        bvo.pC(var58, this.ld);
                     }

                     if (!var39) {
                        btp.pC(2, "(%d) invoke: %s: %s %s", this.wS, this.gp, bvo.pC(var58), bvo.pC(var63));
                     }

                     if (!var42.canAccess(var58)) {
                        var42.setAccessible(true);
                     }

                     var47 = var42.invoke(var58, var63.toArray(new Object[var63.size()]));
                  }
               } else {
                  switch (ckx.kS(this.gp)) {
                     case -2132577010:
                        String var57 = (String)this.fI.get(0);
                        boolean var62 = (Boolean)this.fI.get(1);
                        String var65 = JavaUtil.toJvmName(var57);
                        btj var67 = this.UT.pC(var65, var62);
                        var47 = var67.wS();
                        var51 = true;
                        break;
                     case -2109522095:
                        var47 = this.UT.ah();
                        var51 = true;
                        break;
                     case -1936802804:
                     case -452023474:
                        String var56 = (String)this.fI.get(0);
                        if (var56 != null) {
                           boolean var61 = ckx.pC(4, 6578540, -1289859604, var4);
                           btp.pC(1, "NATIVE: Load Library: %s (path=%b)", var56, var61);
                           this.UT.A(var56, var61);
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
                        String var55 = (String)this.fI.get(0);
                        String var60 = JavaUtil.toJvmName(var55);
                        btj var64 = this.UT.pC(var60, true);
                        var47 = var64.wS();
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
                        this.UT.pC(this.fI.get(0));
                        var47 = null;
                        var51 = true;
                        break;
                     case 1610024648:
                        var47 = var1;
                        var51 = true;
                  }

                  if (var51) {
                     if (!var39) {
                        btp.pC(2, "(%d) invoke-static pseudo: %s: %s", this.wS, this.gp, bvo.pC(this.fI));
                     }
                  } else {
                     if (!var39) {
                        btp.pC(2, "(%d) invoke-static: %s: %s", this.wS, this.gp, bvo.pC(this.fI));
                     }

                     if (!var42.canAccess(null)) {
                        var42.setAccessible(true);
                     }

                     var47 = var42.invoke(null, this.fI.toArray(new Object[this.fI.size()]));
                  }
               }

               if (!var51 && this.pC(this.gp, this.fI)) {
                  List var59 = this.wS();
                  var59.add(
                     0,
                     ckx.pC(
                        new byte[]{
                           89,
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
                           123,
                           60,
                           26,
                           23,
                           4,
                           5,
                           95,
                           22,
                           19,
                           89,
                           2,
                           17,
                           39,
                           39,
                           21,
                           2,
                           8,
                           63,
                           38,
                           19,
                           2,
                           6,
                           77,
                           1,
                           114,
                           23,
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
                           21,
                           2,
                           8,
                           63,
                           38,
                           19,
                           2,
                           6,
                           32,
                           41,
                           9,
                           8,
                           8,
                           11,
                           26,
                           79
                        },
                        1,
                        21
                     )
                  );
                  var47 = this.pC(var1, var59);
               }

               this.E = var47;
            }

            if (!var39) {
               btp.pC(2, ">%d) %s", this.wS, bvo.pC(this.E));
            }
         } catch (Exception var35) {
            String var2 = new bvo.Sv(var35).pC();
            btp.pC(1, "!%d) %s", this.wS, var2);
            this.sY = var35;
         } catch (Throwable var36) {
            throw var36;
         } finally {
            this.pC = true;
         }
      }

      private boolean pC(String var1, List var2) {
         if (ckx.pC(65, 3892556, -1472856545, var1)) {
            return true;
         } else {
            if (ckx.pC(91, 3878732, 857869226, var1)) {
               Method var3 = (Method)var2.get(0);
               String var4 = var3.getDeclaringClass().getName();
               if (ckx.pC(16, 6580074, 298162211, var4) && ckx.pC("getStackTrace", var3.getName())) {
                  return true;
               }
            }

            return false;
         }
      }

      private List wS() {
         ArrayList var1 = new ArrayList();

         for (IDEmuFrame var3 : this.UT.getCurrentContext().getFrames()) {
            var1.add(var3.getMethodSignature());
         }

         for (String var5 : this.UT.getCurrentContext().getOrigins()) {
            var1.add(var5);
         }

         return var1;
      }

      private Object pC(bvs var1, List var2) throws Exception {
         int var3 = var2.size();
         Object var4 = Array.newInstance(var1.E, var3);
         int var5 = 0;
         Constructor var6 = var1.E.getConstructor(var1.wS, var1.wS, var1.wS, var1.loadClass("I"));

         for (String var8 : var2) {
            JvmMethodSig var9 = JvmMethodSig.parse(var8);
            String var10 = bvs.kS(var9.csig);
            String var11 = var9.mname;
            Object var12 = var6.newInstance(var10, var11, null, -1);
            Array.set(var4, var5, var12);
            var5++;
         }

         return var4;
      }

      Object pC(btj.Av var1, Class var2, Class[] var3, Object[] var4) throws Exception {
         bvs var5 = this.A.wS();
         String var6 = this.A.pC(var2, var1.wS());

         Class var7;
         try {
            var7 = var5.loadClass(var6);
         } catch (ClassNotFoundException var13) {
            btp.pC(5, "Creating proxy class for invoke: %s / %s / %s", var6, var1.wS(), var1.kS());
            ArrayList var9 = new ArrayList();

            for (String var11 : var1.kS()) {
               var9.add(var5.A(var11));
            }

            var7 = new ByteBuddy()
               .subclass(var2)
               .implement(var9)
               .name(var6)
               .method(ElementMatchers.isMethod())
               .intercept(MethodDelegation.to(this.UT.ld()))
               .defineField(bvo.UW, int.class, new ForField[]{Visibility.PUBLIC})
               .make()
               .load(var5, Default.INJECTION)
               .getLoaded();
            var1.pC = true;
         }

         try {
            return var7.getConstructor(var3).newInstance(var4);
         } catch (Throwable var12) {
            throw var12;
         }
      }
   }

   private class rQ implements IJLSTypeAdapter {
      @Override
      public IJLSType getType(String var1) {
         try {
            Class var2 = bvo.this.pC(var1);
            return new bgp(var1, var2.getModifiers());
         } catch (DexDecEvaluationException var3) {
            return null;
         }
      }

      @Override
      public String getSupertype(String var1) {
         try {
            Class var2 = bvo.this.pC(var1).getSuperclass();
            return var2 == null ? null : JvmUtil.generateTypeSig(var2);
         } catch (DexDecEvaluationException var3) {
            return null;
         }
      }

      @Override
      public List getInterfaces(String var1) {
         try {
            Class[] var2 = bvo.this.pC(var1).getInterfaces();
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
            Class var2 = bvo.this.pC(var1);
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
            Class var2 = bvo.this.pC(var1);
            Executable[][] var3 = new Executable[][]{var2.getDeclaredConstructors(), var2.getDeclaredMethods()};
            ArrayList var4 = new ArrayList(var3[0].length + var3[1].length);

            for (Executable[] var8 : var3) {
               for (Executable var12 : var8) {
                  String var13 = this.pC(var12);
                  var4.add(new bgo(var12 instanceof Method ? var12.getName() : "<init>", var13, var12.getModifiers()));
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
         // 01: getfield com/pnfsoftware/jebglobal/bvo$rQ.pC Lcom/pnfsoftware/jebglobal/bvo;
         // 04: aload 1
         // 05: invokevirtual com/pnfsoftware/jebglobal/bvo.pC (Ljava/lang/String;)Ljava/lang/Class;
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
         // 33: invokevirtual com/pnfsoftware/jebglobal/bvo$rQ.pC (Ljava/lang/reflect/Executable;)Ljava/lang/String;
         // 36: astore 9
         // 38: aload 9
         // 3a: aload 3
         // 3b: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
         // 3e: ifeq 52
         // 41: new com/pnfsoftware/jebglobal/bgo
         // 44: dup
         // 45: ldc "<init>"
         // 47: aload 9
         // 49: aload 8
         // 4b: invokevirtual java/lang/reflect/Constructor.getModifiers ()I
         // 4e: invokespecial com/pnfsoftware/jebglobal/bgo.<init> (Ljava/lang/String;Ljava/lang/String;I)V
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
         // 8b: invokevirtual com/pnfsoftware/jebglobal/bvo$rQ.pC (Ljava/lang/reflect/Executable;)Ljava/lang/String;
         // 8e: astore 10
         // 90: aload 10
         // 92: aload 3
         // 93: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
         // 96: ifeq aa
         // 99: new com/pnfsoftware/jebglobal/bgo
         // 9c: dup
         // 9d: aload 9
         // 9f: aload 10
         // a1: aload 8
         // a3: invokevirtual java/lang/reflect/Method.getModifiers ()I
         // a6: invokespecial com/pnfsoftware/jebglobal/bgo.<init> (Ljava/lang/String;Ljava/lang/String;I)V
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
            Field[] var2 = bvo.this.pC(var1).getDeclaredFields();
            ArrayList var3 = new ArrayList(var2.length);

            for (Field var7 : var2) {
               String var8 = JvmUtil.generateTypeSig(var7.getType());
               var3.add(new bgn(var7.getName(), var8, var7.getModifiers()));
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

      private String pC(Executable var1) {
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
