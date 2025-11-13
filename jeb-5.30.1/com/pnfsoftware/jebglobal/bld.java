package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.ApkManifestHelper;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEvent;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.code.android.ir.DEmuExternalPolicy;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalSandboxExecutionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.impl.ContainerUnit;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowser;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipFailSafeReader;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class bld implements IEmulatedAndroid {
   private static final ILogger Me = GlobalLog.getLogger(bld.class);
   static final int q = 13;
   static final int RF = 33;
   static final AndroidPlatformABI xK = AndroidPlatformABI.ARM64;
   static final String Dw = cvm.q(new byte[]{34, 14, 2, 26, 26, 95, 83}, 2, 255);
   static final String Uv = cvm.q(new byte[]{115, 6, 22, 23}, 1, 6);
   static final String oW = cvm.q(
      new byte[]{
         36,
         0,
         31,
         30,
         30,
         12,
         72,
         27,
         1,
         78,
         78,
         10,
         90,
         72,
         30,
         74,
         76,
         93,
         74,
         73,
         65,
         88,
         8,
         5,
         31,
         15,
         27,
         35,
         83,
         34,
         66,
         87,
         19,
         81,
         90,
         84,
         26,
         65,
         66,
         16,
         90,
         91,
         74,
         23,
         80,
         95,
         95,
         91,
         91,
         83,
         20,
         7,
         0,
         1,
         1,
         82,
         36,
         0,
         9,
         65,
         1,
         12,
         74,
         3,
         17,
         10,
         83
      },
      2,
      171
   );
   private IApkUnit PV;
   private IDexUnit oQ;
   private IDexDecompilerUnit xW;
   private IDGlobalContext KT;
   private IDState Gf;
   boolean gO;
   private bld.eo Ef;
   private boolean cC = false;
   private int sH;
   private String CE;
   private File wF;
   private String If;
   private String Dz;
   private String mI;
   private String jq;
   private String ui;
   private String TX;
   private String Rr;
   private File EB;
   private Set Xo = new TreeSet();
   private Map Bu = new TreeMap();
   private boolean IN;
   private boolean rL;
   private volatile cjn eJ;
   private volatile Properties YN;
   private volatile Map Rv;
   Object nf;
   Object gP;
   Object za;
   Object lm;
   Object zz;
   Object JY;
   Object HF;
   Object LK;
   Object io;
   Map qa = new HashMap();
   Map Hk = new HashMap();
   private static final char[] zx = new char[63];
   private static final String[] ZT;
   private static final String Ri;

   public static IEmulatedAndroid q(IDState var0) {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else if (var0.getEmulatedEnvironment() != null) {
         return var0.getEmulatedEnvironment();
      } else {
         IApkUnit var1 = var0.getApk();
         if (var1 == null) {
            return null;
         } else {
            return var1.getPackageName() == null ? null : new bld(null, var0);
         }
      }
   }

   public bld(IDexDecompilerUnit var1) {
      this(DexUtil.findParentApk(var1.getCodeUnit()), null);
   }

   public bld(IApkUnit var1) {
      this(var1, null);
   }

   private bld(IApkUnit var1, IDState var2) {
      if (var1 == null) {
         var1 = var2.getApk();
         if (var1 == null) {
            throw new IllegalArgumentException(
               cvm.q(
                  new byte[]{
                     90,
                     60,
                     13,
                     69,
                     97,
                     17,
                     27,
                     107,
                     83,
                     18,
                     15,
                     10,
                     6,
                     13,
                     23,
                     88,
                     82,
                     23,
                     20,
                     4,
                     28,
                     27,
                     23,
                     22,
                     83,
                     65,
                     15,
                     78,
                     97,
                     17,
                     27,
                     107,
                     85,
                     27,
                     7,
                     29,
                     85
                  },
                  1,
                  14
               )
            );
         }
      }

      if (var1.getPackageName() == null) {
         throw new IllegalArgumentException(
            cvm.q(
               new byte[]{
                  92,
                  60,
                  13,
                  69,
                  97,
                  17,
                  27,
                  107,
                  77,
                  12,
                  24,
                  89,
                  78,
                  1,
                  27,
                  84,
                  66,
                  7,
                  0,
                  11,
                  78,
                  70,
                  19,
                  25,
                  0,
                  21,
                  89,
                  80,
                  2,
                  29,
                  12,
                  6,
                  22,
                  0,
                  22,
                  1,
                  68,
                  79,
                  29,
                  82,
                  68,
                  11,
                  10,
                  22,
                  83,
                  78,
                  1,
                  27,
                  84,
                  67,
                  12,
                  1,
                  26,
                  21,
                  8,
                  7,
                  78,
                  65,
                  65,
                  86,
                  23,
                  13,
                  5,
                  13,
                  68,
                  97,
                  47,
                  10,
                  22,
                  29,
                  6,
                  13,
                  68,
                  77,
                  12,
                  15,
                  7,
                  15,
                  3,
                  22,
                  7,
                  84,
                  87,
                  30,
                  29,
                  28,
                  72,
                  65,
                  65,
                  80,
                  17,
                  2,
                  8,
                  10,
                  6,
                  2,
                  69,
                  78,
                  15,
                  12,
                  8,
                  68
               },
               1,
               8
            )
         );
      } else {
         this.PV = var1;
         this.oQ = var1.getDex();
         this.xW = this.oQ.getDecompiler();
         this.KT = this.xW.getIntermediateContext();
         if (var2 == null) {
            var2 = this.zz();
            this.gO = true;
         } else if (var2.getEmulatedEnvironment() != null) {
            throw new IllegalStateException(
               cvm.q(
                  new byte[]{
                     6,
                     2,
                     5,
                     21,
                     19,
                     29,
                     8,
                     26,
                     84,
                     65,
                     68,
                     17,
                     76,
                     65,
                     85,
                     64,
                     25,
                     82,
                     88,
                     84,
                     83,
                     83,
                     90,
                     81,
                     72,
                     0,
                     59,
                     29,
                     65,
                     2,
                     2,
                     69,
                     105,
                     37,
                     61,
                     16,
                     78,
                     27,
                     23
                  },
                  2,
                  5
               )
            );
         }

         this.Gf = var2;
         this.Ef = new bld.eo();
         var2.registerSandboxHooks(this.Ef);
         ((bye)var2).q((IEmulatedAndroid)this);
         this.sH = 1000 + (int)(Math.random() * 5000.0);
         this.CE = var1.getPackageName();
         ApkManifestHelper var3 = new ApkManifestHelper(var1);
         int[] var4 = var3.getSdkVersion();
         this.If = q(this.CE);
         this.Dz = this.If + cvm.q(new byte[]{108, 13, 17, 10, 23, 71, 6, 24, 31}, 2, 50);
         if (var1.getInput() instanceof FileInput) {
            this.wF = ((FileInput)var1.getInput()).getFile();
         }

         this.mI = this.If + cvm.q(new byte[]{108, 3, 25, 27}, 2, 167);
         this.jq = this.If + cvm.q(new byte[]{-110, 67, 5, 11, 77, 78, 19, 31, 91, 2}, 1, 189);
         this.ui = cvm.q(new byte[]{-33, 75, 5, 21, 21, 78, 90, 6, 22, 23, 93, 31, 31}, 1, 240) + this.CE;
         this.TX = cvm.q(
               new byte[]{72, 92, 7, 27, 29, 19, 6, 2, 74, 74, 8, 24, 25, 13, 21, 17, 1, 75, 31, 31, 110, 47, 10, 22, 29, 6, 13, 75, 75, 5, 21, 21, 78}, 1, 103
            )
            + this.CE;
         this.Rr = cvm.q(
               new byte[]{-32, 92, 7, 27, 29, 19, 6, 2, 74, 74, 8, 24, 25, 13, 21, 17, 1, 75, 31, 31, 110, 47, 10, 22, 29, 6, 13, 75, 64, 13, 0, 77}, 1, 207
            )
            + this.CE;
         if (var2.isSandboxEnabled()) {
            try {
               String var5 = this.CE;
               this.EB = var2.setSandboxDroppedFilesCollection(var5, false);
               com.pnfsoftware.jeb.util.io.IO.deleteDirectoryContents(this.EB);
               new File(this.EB, this.ui).mkdirs();
               new File(this.EB, this.If).mkdirs();
            } catch (Exception var8) {
               throw new RuntimeException(var8);
            }

            try {
               URLClassLoader var9 = var2.getSandboxClassLoader();
               Class var6 = var9.loadClass(
                  cvm.q(
                     new byte[]{
                        -56,
                        15,
                        10,
                        22,
                        29,
                        6,
                        13,
                        74,
                        77,
                        12,
                        1,
                        26,
                        17,
                        11,
                        26,
                        90,
                        94,
                        29,
                        67,
                        126,
                        49,
                        2,
                        8,
                        10,
                        6,
                        2,
                        40,
                        44,
                        15,
                        15,
                        6,
                        2,
                        23,
                        59,
                        36,
                        29,
                        28
                     },
                     1,
                     169
                  )
               );
               this.nf = var6.getConstructor().newInstance();
               var6 = var9.loadClass(cvm.q(new byte[]{-72, 15, 10, 22, 29, 6, 13, 74, 65, 28, 93, 108, 55, 27, 10, 8, 9}, 1, 217));
               this.lm = var6.getConstructor().newInstance();
               var6 = var9.loadClass(
                  cvm.q(
                     new byte[]{
                        -78, 15, 10, 22, 29, 6, 13, 74, 77, 12, 1, 26, 17, 11, 26, 90, 94, 29, 67, 111, 49, 0, 28, 5, 10, 2, 21, 29, 6, 1, 39, 39, 8, 9
                     },
                     1,
                     211
                  )
               );
               this.zz = var6.getConstructor().newInstance();
               var6.getField(cvm.q(new byte[]{32, 0, 29, 9, 27, 5, 2, 59, 16, 75, 126, 6, 91, 83, 88, 86, 87}, 2, 244)).setInt(this.zz, 33);
               var6.getField(cvm.q(new byte[]{32, 0, 29, 9, 27, 5, 2, 59, 16, 75, 126, 6, 91, 83, 88, 86, 87, 112, 67, 68, 87, 94, 83, 89, 73}, 2, 26))
                  .set(this.zz, "13");
               var6.getField(cvm.q(new byte[]{39, 14, 4, 24, 54, 0, 21}, 2, 64)).set(this.zz, this.ui);
               var6.getField(cvm.q(new byte[]{54, 2, 29, 12, 6, 22, 0, 61, 47, 12, 8}, 1, 70)).set(this.zz, this.CE);
               var6.getField(cvm.q(new byte[]{48, 0, 5, 11, 17, 12, 35, 1, 6}, 2, 7)).set(this.zz, this.Dz);
               var6.getField(cvm.q(new byte[]{51, 26, 18, 21, 27, 10, 52, 7, 1, 82, 75, 6, 109, 73, 67}, 2, 84)).set(this.zz, this.Dz);
               var6.getField(cvm.q(new byte[]{81, 17, 2, 8, 10, 6, 2, 43, 47, 12, 8}, 1, 33)).set(this.zz, this.CE);
               var6.getField(cvm.q(new byte[]{55, 14, 2, 30, 23, 29, 52, 12, 31, 118, 77, 17, 90, 73, 94, 87}, 2, 137)).set(this.zz, var4[1]);
               var6.getField(cvm.q(new byte[]{46, 10, 4, 24, 54, 8, 19, 9}, 2, 33)).set(this.zz, this.lm);
               var6 = var9.loadClass(
                  cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 23, 79, 70, 23, 76, 78, 69, 23, 73, 94, 2, 112, 83, 83, 89, 85, 75, 69, 6, 28, 7, 12}, 2, 93)
               );
               this.JY = var6.getConstructor().newInstance();
               var6.getField(cvm.q(new byte[]{34, 31, 0, 21, 27, 10, 6, 28, 29, 79, 70, 42, 71, 70, 94}, 2, 225)).set(this.JY, this.zz);
               var6.getField(cvm.q(new byte[]{51, 14, 19, 18, 19, 14, 2, 38, 21, 77, 77}, 2, 166)).set(this.JY, this.CE);
               var6 = var9.loadClass(cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 21, 80, 88, 77, 101, 79, 80, 93, 92, 87, 109, 80, 89}, 2, 31));
               this.HF = var6.getConstructor(String.class).newInstance(this.CE);
               this.LK = this.Uv("android.app.ContextImpl");
               this.q(this.LK, cvm.q(new byte[]{-52, 61, 49, 2, 8, 10, 6, 2, 44, 39, 8, 9}, 1, 161), this.HF);
               var6 = var9.loadClass(cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 21, 80, 88, 77, 104, 80, 65, 85, 80, 80, 77, 84, 91, 95, 92}, 2, 168));
               this.za = var6.getConstructor().newInstance();
               this.q(this.za, cvm.q(new byte[]{-100, 33, 35, 14, 5, 1, 1, 37, 49, 27}, 1, 241), this.HF);
               var6 = var9.loadClass(
                  cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 21, 80, 88, 77, 104, 67, 69, 80, 79, 90, 88, 89, 102, 88, 64, 81, 77, 68}, 2, 59)
               );
               this.gP = var6.getMethod(cvm.q(new byte[]{42, 1, 25, 13, 27, 8, 11, 1, 14, 69}, 2, 26), this.za.getClass(), this.zz.getClass())
                  .invoke(null, this.za, this.zz);
               this.q(this.HF, cvm.q(new byte[]{-71, 44, 34, 23, 29, 31, 31, 29, 13, 45, 60, 26, 23, 4, 5}, 1, 212), this.gP);
               this.q(this.HF, cvm.q(new byte[]{46, 46, 0, 9, 30, 0, 4, 9, 0, 73, 71, 13, 96, 78, 87, 86}, 2, 48), this.zz);
               this.q(this.HF, cvm.q(new byte[]{46, 46, 0, 9, 30, 0, 4, 9, 0, 73, 71, 13}, 2, 254), this.za);
               this.q(this.HF, cvm.q(new byte[]{46, 44, 28, 24, 1, 26, 43, 7, 21, 68, 77, 17}, 2, 16), var9);
               ((Map)this.nf.getClass().getField("map").get(this.nf)).put(this.CE, this.JY);
               var6 = var9.loadClass(
                  cvm.q(
                     new byte[]{45, 5, 13, 26, 31, 2, 69, 93, 10, 10, 7, 17, 8, 67, 108, 35, 18, 22, 33, 33, 29, 59, 47, 13, 18, 0, 63, 35, 14, 5, 1, 23},
                     1,
                     73
                  )
               );
               this.io = var6.getConstructor(String.class).newInstance(this.If);
            } catch (Exception var7) {
               throw new RuntimeException(var7);
            }
         }
      }
   }

   public void q(Object var1) {
      this.za = var1;
      this.q(this.HF, cvm.q(new byte[]{-92, 44, 49, 0, 28, 5, 10, 2, 21, 29, 6, 1}, 1, 201), this.za);
      this.q(this.gP, cvm.q(new byte[]{46, 38, 30, 16, 6, 0, 6, 4, 53, 80, 88, 15, 64, 67, 80, 77, 80, 92, 66}, 2, 177), this.za);
   }

   public void q() {
      if (this.gO) {
         this.Gf.dispose();
      }
   }

   public void q(boolean var1) {
      this.IN = var1;
   }

   public boolean RF() {
      return this.IN;
   }

   public void RF(boolean var1) {
      this.rL = var1;
   }

   public boolean xK() {
      return this.rL;
   }

   private static String q(String var0) {
      Random var1 = new Random();
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < 22; var3++) {
         var2.append(zx[var1.nextInt(62)]);
      }

      StringBuilder var5 = new StringBuilder();

      for (int var4 = 0; var4 < 22; var4++) {
         var5.append(zx[var1.nextInt(63)]);
      }

      String var6 = cvm.q(cvm.q(new byte[]{88, 75, 5, 21, 21, 78, 78, 17, 0, 95, 81, 0, 91, 86, 78, 0, 18, 10, 86, 94, 8, 86, 78, 0}, 1, 119));
      return Strings.ff(var6, var2, var0, var5);
   }

   public cjn Dw() {
      if (this.eJ == null) {
         synchronized (this) {
            if (this.eJ == null) {
               this.eJ = this.gP();
            }
         }
      }

      return this.eJ;
   }

   private cjn gP() {
      return cjn.q(this.PV.getParentProject());
   }

   @Override
   public Properties getJavaProperties() {
      if (this.YN == null) {
         synchronized (this) {
            if (this.YN == null) {
               this.YN = this.za();
            }
         }
      }

      return this.YN;
   }

   private Properties za() {
      Map var1 = this.q(5);
      Properties var2 = new Properties();

      for (Entry var4 : var1.entrySet()) {
         var2.setProperty((String)var4.getKey(), (String)var4.getValue());
      }

      return var2;
   }

   @Override
   public Map getSystemProperties() {
      if (this.Rv == null) {
         synchronized (this) {
            if (this.Rv == null) {
               this.Rv = this.lm();
            }
         }
      }

      return this.Rv;
   }

   private Map lm() {
      return this.q(6);
   }

   private Map q(int var1) {
      HashMap var2 = new HashMap();

      byte[] var3;
      try {
         var3 = this.Dw().xK(var1);
      } catch (IOException var17) {
         Me.catchingSilent(var17);
         return var2;
      }

      String var4 = Strings.decodeUTF8(var3);

      for (String var8 : Strings.splitLines(var4)) {
         var8 = var8.trim();
         if (!var8.isEmpty() && !var8.startsWith("#")) {
            String[] var9 = var8.split("=", -1);
            String var10 = Strings.urldecodeUTF8(var9[0]);
            String var11 = Strings.urldecodeUTF8(var9[1]);

            for (String var15 : ZT) {
               var11 = var11.replace("{{" + var15 + "}}", switch (cvm.xK(var15)) {
                  case 42690319 -> this.CE;
                  case 1749817814 -> Dw;
                  default -> throw new RuntimeException();
               });
            }

            var2.put(var10, var11);
         }
      }

      return var2;
   }

   private IDState zz() {
      IDState var1 = ((btx)this.KT).q(true, false, true);
      var1.setMaxIterationCount(1000000000);
      var1.setMaxDuration(1800000L);
      var1.enableEmulator(true);
      var1.enableNativeCodeEmulator(true);
      var1.enableSandbox(true);
      var1.setSubRoutineInvocationPolicy(255);
      var1.setFieldAccessPolicy(255);
      var1.setPerformDirectUnreflection(true);
      var1.setExternalPolicy(new DEmuExternalPolicy(false, false, false));
      return var1;
   }

   @Override
   public IApkUnit getApk() {
      return this.PV;
   }

   @Override
   public IDexUnit getDex() {
      return this.oQ;
   }

   @Override
   public IDexDecompilerUnit getDecompiler() {
      return this.xW;
   }

   public cjn Uv() {
      return this.Dw();
   }

   @Override
   public IDState getState() {
      return this.Gf;
   }

   @Override
   public int getAndroidVersion() {
      return 13;
   }

   @Override
   public int getAndroidApiLevel() {
      return 33;
   }

   @Override
   public AndroidPlatformABI getAndroidPlatformABI() {
      return xK;
   }

   @Override
   public String getPackageName() {
      return this.CE;
   }

   @Override
   public String getAppFolder() {
      return this.If;
   }

   @Override
   public String getApkPath() {
      return this.Dz;
   }

   @Override
   public String getAppDataFolder() {
      return this.ui;
   }

   @Override
   public File getLocalApkFile() {
      return this.wF;
   }

   @Override
   public File getDropboxFolder() {
      return this.EB;
   }

   public IUnit xK(boolean var1) {
      String var2 = cvm.q(new byte[]{54, 1, 0, 24, 17, 2, 2, 12}, 2, 243);
      IUnitProcessor var3 = this.PV.getUnitProcessor();
      Object var4 = UnitUtil.findChildByName(this.PV, var2, 0);
      if (var4 == null && var1) {
         var4 = new ContainerUnit(var2, var3, this.PV, this.PV.getPropertyDefinitionManager());
         ((IUnit)var4).process();
         this.PV.addChild((IUnit)var4);
      }

      return (IUnit)var4;
   }

   @Override
   public String getMainActivity() {
      String var1 = this.PV.getMainActivity();
      return var1 == null ? null : JavaUtil.toJvmName(var1);
   }

   @Override
   public IDImm invokeMethod(DInvokeType var1, String var2, List var3) throws DexDecEvaluationException {
      IDState var4 = this.getState();
      var4.pushContext("INIT");

      IDImm var5;
      try {
         var5 = var4.invokeMethod(var2, var3, var1);
      } finally {
         var4.deleteTopContext();
      }

      return var5;
   }

   private void q(String var1, File var2) {
      byte[] var3;
      try {
         var3 = com.pnfsoftware.jeb.util.io.IO.readFile(var2);
      } catch (IOException var13) {
         return;
      }

      if (var3.length >= 4 && var3[0] == 127 && var3[1] == 69 && var3[2] == 76 && var3[3] == 70) {
         String var4 = Formatter.byteArrayToHex(Hash.calculateSHA256(var3)).toString();
         if (!this.Bu.containsKey(var1)) {
            this.Bu.put(var1, var4);
            String var5 = Strings.ff(S.L("A native library was recovered: sha256 %s"), var4);
            DexDecompilerEvent.Message var6 = new DexDecompilerEvent.Message(var5, null);
            this.xW.recordDecompilationEvent(var6);
            Me.info(var5);
            File var7 = new File(this.EB, cvm.q(new byte[]{109, 43, 37, 52, 34, 44, 35}, 2, 193));
            if (!var7.exists()) {
               var7.mkdir();
            }

            File var8 = new File(var7, var4);

            try {
               com.pnfsoftware.jeb.util.io.IO.writeFile(var8, var3);
               Me.info(S.L("A copy of the recovered so file was dumped to: %s"), var8);
            } catch (IOException var12) {
               Me.error(S.L("Cannot write recovered so file: %s: %s"), var8, var12);
            }

            IUnit var9 = this.xK(true);
            IUnit var10 = UnitUtil.findChildByName(var9, var4, 0);
            if (var10 == null) {
               IUnitProcessor var11 = this.PV.getUnitProcessor();
               var10 = var11.process(var4, new BytesInput(var3), var9, null, false, true);
               var9.addChild(var10);
            }

            Me.info(S.L("An unprocessed native unit was created as %s/%s under your APK unit"), var9.getName(), var4);
         }
      }
   }

   public Collection oW() {
      return Collections.unmodifiableCollection(this.Bu.values());
   }

   public Map gO() {
      return Collections.unmodifiableMap(this.Bu);
   }

   private void q(File var1) {
      int var2 = com.pnfsoftware.jeb.util.io.IO.getFirstIntLE(var1.getPath());
      if (var2 == 175662436) {
         this.RF(var1);
      } else if (var2 == 67324752) {
         try (
            ZipBrowser var3 = new ZipBrowser(var1);
            InputStream var4 = var3.getEntryStream(cvm.q(new byte[]{32, 3, 17, 10, 1, 12, 20, 70, 16, 69, 80}, 2, 169));
         ) {
            byte[] var5 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var4);
            this.q(var5);
         } catch (IOException var11) {
         }
      }
   }

   private void RF(File var1) {
      byte[] var2;
      try {
         var2 = com.pnfsoftware.jeb.util.io.IO.readFile(var1);
      } catch (IOException var3) {
         return;
      }

      this.q(var2);
   }

   private void q(ByteBuffer var1) {
      int var2 = var1.position();
      int var3 = var1.limit();
      int var4 = var3 - var2;
      byte[] var5 = new byte[var4];
      var1.get(var5);
      var1.position(var2);
      this.q(var5);
   }

   private void q(byte[] var1) {
      if (var1.length >= 3 && var1[0] == 100 && var1[1] == 101 && var1[2] == 120) {
         String var2 = Formatter.byteArrayToHex(Hash.calculateSHA256(var1)).toString();
         if (this.Xo.add(var2)) {
            String var3 = Strings.ff(S.L("A dex file was recovered: sha256 %s"), var2);
            DexDecompilerEvent.Message var4 = new DexDecompilerEvent.Message(var3, null);
            this.xW.recordDecompilationEvent(var4);
            Me.info(var3);
            File var5 = new File(this.EB, cvm.q(new byte[]{-34, 106, 17, 24, 29, 21, 1}, 1, 240));
            if (!var5.exists()) {
               var5.mkdir();
            }

            File var6 = new File(var5, var2);

            try {
               com.pnfsoftware.jeb.util.io.IO.writeFile(var6, var1);
               Me.info(S.L("A copy of the recovered dex file was dumped to: %s"), var6);
               if (this.IN) {
                  FileInput var7 = null;

                  try {
                     var7 = new FileInput(var6.getPath());
                     this.oQ.addDex(var7);
                  } finally {
                     if (var7 != null) {
                        var7.close();
                     }
                  }
               }
            } catch (IOException var13) {
               Me.error(S.L("Cannot write recovered dex file: %s: %s"), var6, var13);
            }

            IUnit var14 = this.xK(true);
            IUnit var8 = UnitUtil.findChildByName(var14, var2, 0);
            if (var8 == null) {
               IUnitProcessor var9 = this.PV.getUnitProcessor();
               var8 = var9.process(var2, new BytesInput(var1), var14, null, false, true);
               var14.addChild(var8);
            }

            Me.info(S.L("An unprocessed dex unit was created as %s/%s under your APK unit"), var14.getName(), var2);
            Me.info(S.L("You may choose to merge it with your primary dex unit via the Android menu, handler: Add/Merge Additional Dex Files..."));
         }
      }
   }

   public Set nf() {
      return Collections.unmodifiableSet(this.Xo);
   }

   private boolean RF(String var1) {
      File var2 = new File(this.EB, var1);
      if (var2.exists()) {
         return true;
      } else {
         if (this.Dz.equals(var1)) {
            try {
               com.pnfsoftware.jeb.util.io.IO.createFoldersForFile(var2);
               com.pnfsoftware.jeb.util.io.IO.copyFile(this.wF, var2, false);
            } catch (IOException var22) {
            }
         }

         if (var1.startsWith(this.jq + "/")) {
            String var3 = var1.substring(this.jq.length() + 1);
            if (var3.startsWith("lib") && var3.endsWith(".so") && !var3.contains("/")) {
               IInput var4 = this.PV.getInput();
               if (var4 instanceof FileInput) {
                  File var5 = ((FileInput)var4).getFile();

                  try {
                     boolean var34;
                     try (ZipBrowser var32 = new ZipBrowser(var5)) {
                        byte[] var33 = var32.readEntry("lib/" + xK + "/" + var3);
                        com.pnfsoftware.jeb.util.io.IO.createFoldersForFile(var2);
                        com.pnfsoftware.jeb.util.io.IO.writeFile(var2, var33);
                        var34 = true;
                     }

                     return var34;
                  } catch (IOException var26) {
                     Me.catching(var26);
                  }
               }
            }
         }

         if (var1.startsWith(cvm.RF("/proc/self/"))) {
            Object[] var10000 = new Object[]{var1};
            String var27 = var1.substring(11);
            byte[] var29 = null;
            switch (cvm.xK(var27)) {
               case -2042563435:
                  var29 = Strings.encodeUTF8(this.CE);
                  break;
               case 2033445016:
                  try {
                     var29 = this.Dw().xK(7);
                  } catch (IOException var19) {
                  }
            }

            if (var29 != null) {
               try {
                  com.pnfsoftware.jeb.util.io.IO.createFoldersForFile(var2);
                  com.pnfsoftware.jeb.util.io.IO.writeFile(var2, var29);
                  return true;
               } catch (IOException var25) {
               }
            }
         }

         if (this.cC) {
            cjw var28 = ((bye)this.Gf).zz().lm();
            int var30 = var28.q(var1, 0, 0, true);
            if (var30 != -1) {
               boolean var9;
               try {
                  cjw.eo var31 = var28.q(var30);
                  if (!var31.Dw()) {
                     return false;
                  }

                  int var6 = var31.gP();
                  if (var6 < 0 || var6 >= 10000000) {
                     return false;
                  }

                  byte[] var7 = new byte[var6];
                  int var8 = var28.RF(var30, var6, var7, 0);
                  if (var8 != var6) {
                     return false;
                  }

                  try {
                     com.pnfsoftware.jeb.util.io.IO.createFoldersForFile(var2);
                     com.pnfsoftware.jeb.util.io.IO.writeFile(var2, var7);
                     var9 = true;
                  } catch (IOException var23) {
                     return false;
                  }
               } finally {
                  var28.RF(var30);
               }

               return var9;
            }
         }

         return false;
      }
   }

   private String xK(String var1) {
      if (var1.isEmpty()) {
         return null;
      } else {
         char var2 = var1.charAt(0);
         if (var2 != '/' && var2 != '\\') {
            return null;
         } else {
            File var3 = new File(var1);
            if (com.pnfsoftware.jeb.util.io.IO.inFolder(var3, this.EB)) {
               return null;
            } else {
               this.RF(var1);
               File var4 = new File(this.EB, var1);
               return var4.getPath();
            }
         }
      }
   }

   private boolean q(List var1, int var2) {
      String var3 = this.xK((String)var1.get(0));
      if (var3 == null) {
         return false;
      } else {
         var1.set(var2, var3);
         return true;
      }
   }

   private File xK(File var1) {
      if (com.pnfsoftware.jeb.util.io.IO.inFolder(var1, this.EB)) {
         return var1;
      } else {
         String var2 = var1.getPath();
         this.RF(var2);
         return new File(this.EB, var2);
      }
   }

   private String Dw(File var1) {
      String var3;
      if (com.pnfsoftware.jeb.util.io.IO.inFolder(var1, this.EB)) {
         var3 = com.pnfsoftware.jeb.util.io.IO.getRelativePath(var1, this.EB);
         var3 = "/" + var3.replace('\\', '/');
      } else {
         var3 = var1.getPath();
         var3 = var3.replace('\\', '/');
      }

      return var3;
   }

   private Wrapper Dw(String var1) {
      IInput var2 = this.PV.getInput();
      if (var2 instanceof FileInput) {
         File var3 = ((FileInput)var2).getFile();

         try {
            Wrapper var7;
            try (ZipBrowser var4 = new ZipBrowser(var3)) {
               InputStream var5 = var4.getEntryStream(var1);
               byte[] var6 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var5);
               var7 = Wrapper.wrap(new ByteArrayInputStream(var6));
            }

            return var7;
         } catch (IOException var10) {
            Me.catching(var10);
         }
      }

      return null;
   }

   private Wrapper q(List var1) {
      String var2 = "";
      if (var1.size() == 2 && (((String)var1.get(0)).equals(Ri) || ((String)var1.get(0)).endsWith("/" + Ri))) {
         String var3 = (String)var1.get(1);
         var2 = (String)this.getSystemProperties().getOrDefault(var3, "");
      }

      Class var6 = new ByteBuddy()
         .subclass(Process.class)
         .method(ElementMatchers.named(cvm.q(new byte[]{36, 10, 4, 48, 28, 25, 18, 28, 39, 84, 90, 6, 72, 77}, 2, 222)))
         .intercept(FixedValue.value(new ByteArrayInputStream(Strings.encodeUTF8(var2))))
         .method(ElementMatchers.named(cvm.q(new byte[]{36, 10, 4, 54, 7, 29, 23, 29, 0, 115, 92, 17, 76, 65, 92}, 2, 164)))
         .intercept(FixedValue.value(new ByteArrayOutputStream()))
         .method(ElementMatchers.named(cvm.q(new byte[]{-116, 2, 17, 49, 55, 0, 29, 29, 33, 39, 6, 23, 4, 12}, 1, 235)))
         .intercept(FixedValue.value(new ByteArrayInputStream(new byte[0])))
         .method(ElementMatchers.named(cvm.q(new byte[]{39, 10, 3, 13, 0, 6, 30}, 2, 66)))
         .intercept(FixedValue.value(void.class))
         .method(ElementMatchers.named(cvm.q(new byte[]{67, 26, 50, 45, 5, 31, 19}, 1, 42)))
         .intercept(FixedValue.value(false))
         .method(ElementMatchers.named(cvm.q(new byte[]{38, 23, 25, 13, 36, 8, 11, 29, 17}, 2, 195)))
         .intercept(FixedValue.value(0))
         .make()
         .load(this.getClass().getClassLoader())
         .getLoaded();

      try {
         Process var4 = (Process)var6.getConstructor().newInstance();
         return Wrapper.wrap(var4);
      } catch (Exception var5) {
         Me.catching(var5);
         return null;
      }
   }

   private Object Uv(String var1) {
      try {
         URLClassLoader var2 = this.Gf.getSandboxClassLoader();
         Class var3 = var2.loadClass(var1);
         if (Modifier.isAbstract(var3.getModifiers())) {
            if (Modifier.isFinal(var3.getModifiers())) {
               return null;
            }

            Class var4 = new ByteBuddy().subclass(var3).make().load(var3.getClassLoader()).getLoaded();
            var3 = var4;
         }

         Constructor[] var13 = var3.getDeclaredConstructors();
         if (var13.length == 0) {
            return null;
         } else {
            Constructor var5 = null;
            Object[] var6 = null;

            for (Constructor var10 : var13) {
               if (var10.getParameterCount() == 0) {
                  var5 = var10;
                  var6 = new Object[0];
                  break;
               }
            }

            if (var5 == null) {
               var5 = var13[0];
               int var14 = 0;
               var6 = new Object[var5.getParameterCount()];

               for (Class var11 : var5.getParameterTypes()) {
                  if (var11.isPrimitive()) {
                     if (var11 == boolean.class) {
                        var6[var14] = false;
                     } else if (var11 == byte.class) {
                        var6[var14] = (byte)0;
                     } else if (var11 == short.class) {
                        var6[var14] = (short)0;
                     } else if (var11 == char.class) {
                        var6[var14] = '\u0000';
                     } else if (var11 == int.class) {
                        var6[var14] = 0;
                     } else if (var11 == long.class) {
                        var6[var14] = 0L;
                     } else if (var11 == float.class) {
                        var6[var14] = 0.0F;
                     } else {
                        if (var11 != double.class) {
                           throw new RuntimeException();
                        }

                        var6[var14] = 0.0;
                     }
                  } else {
                     var6[var14] = null;
                  }

                  var14++;
               }
            }

            var5.setAccessible(true);
            return var5.newInstance(var6);
         }
      } catch (Exception var12) {
         return null;
      }
   }

   private boolean q(Object var1, String var2, Object var3) {
      try {
         Field var4 = var1.getClass().getDeclaredField(var2);
         var4.setAccessible(true);
         var4.set(var1, var3);
         return true;
      } catch (Exception var5) {
         return false;
      }
   }

   private Object q(Object var1, String var2) {
      try {
         Field var3 = var1.getClass().getDeclaredField(var2);
         var3.setAccessible(true);
         return var3.get(var1);
      } catch (Exception var4) {
         return null;
      }
   }

   static {
      int var0 = 0;

      for (int var1 = 0; var1 < 26; var1++) {
         zx[var0++] = (char)(97 + var1);
      }

      for (int var2 = 0; var2 < 26; var2++) {
         zx[var0++] = (char)(65 + var2);
      }

      for (int var3 = 0; var3 < 10; var3++) {
         zx[var0++] = (char)(48 + var3);
      }

      zx[var0] = '_';
      ZT = new String[]{cvm.q(new byte[]{91, 19, 17, 11}, 1, 58), cvm.q(new byte[]{34, 31, 0, 41, 19, 10, 12, 9, 19, 69, 102, 2, 68, 69}, 2, 27)};
      Ri = cvm.q(new byte[]{126, 2, 17, 4, 2, 29, 31}, 1, 25);
   }

   private class eo implements IDSandboxHooks {
      @Override
      public Wrapper newInstance(long var1, String var3, String var4, List var5) throws DexDecEvalSandboxExecutionException {
         switch (cvm.xK(var4)) {
            case -1812307848:
               bld.this.q(var5, 0);
               break;
            case -1416508139:
               bld.this.q(var5, 0);
               break;
            case -999752482:
            case -877117263:
               bld.this.q(var5, 0);
               break;
            case -105607493:
               bld.this.xK(new File((String)var5.get(0), (String)var5.get(1)));
               bld.this.q(var5, 0);
               break;
            case -29468192:
               bld.this.q(var5, 0);
               break;
            case 175910812:
               String var14 = (String)var5.get(0);

               for (String var22 : var14.split(":")) {
                  File var11 = bld.this.xK(new File(var22));
                  bld.this.q(var11);
               }
               break;
            case 559727105:
               ByteBuffer var13 = (ByteBuffer)var5.get(0);
               bld.this.q(var13);
               break;
            case 770005835:
               bld.this.q(var5, 0);
               break;
            case 936210013:
            case 1275043432:
               bld.this.q(var5, 0);
               break;
            case 971761922:
            case 1442903201:
               ByteBuffer[] var12 = (ByteBuffer[])var5.get(0);

               for (ByteBuffer var21 : var12) {
                  bld.this.q(var21);
               }
               break;
            case 1734388911:
               URI var6 = (URI)var5.get(0);
               String var7 = var6.toString();
               if (var7.startsWith("file://")) {
                  String var8 = var7.substring(7);
                  String var9 = bld.this.xK(var8);
                  if (var9 != null) {
                     URI var10 = URI.create("file://" + var9);
                     var5.set(0, var10);
                  }
               }
               break;
            case 1971570765:
               bld.this.q(var5, 0);
         }

         return null;
      }

      @Override
      public Wrapper getField(long var1, String var3, String var4, Object var5) {
         switch (cvm.xK(var4)) {
            case -2102237115:
               return Wrapper.wrap("/");
            case -1849657712:
               return Wrapper.wrap('/');
            case -1409371876:
            case -1162579909:
               return Wrapper.wrap(new String[]{bld.xK.toString()});
            case -1299541426:
               return Wrapper.wrap(new String[0]);
            case -955909561:
            case 1513990710:
               return Wrapper.wrap(bld.xK.toString());
            case -581505709:
               return Wrapper.wrap(bld.Uv);
            case -371725219:
               return Wrapper.wrap(33);
            case 82640088:
               try {
                  if (var5 == bld.this.Gf.getSandboxClassLoader()) {
                     var5 = bld.this.io;
                  }

                  Object var6 = var5.getClass().getField(cvm.q(new byte[]{51, 14, 4, 17, 62, 0, 20, 28}, 2, 119)).get(var5);
                  return Wrapper.wrap(var6);
               } catch (Exception var7) {
               }
            default:
               return null;
            case 1014093460:
               return Wrapper.wrap(bld.oW);
         }
      }

      @Override
      public Boolean setField(long var1, String var3, String var4, Object var5, Object[] var6) {
         if (var4.endsWith(
            cvm.q(
               new byte[]{
                  110,
                  81,
                  0,
                  24,
                  6,
                  1,
                  43,
                  1,
                  7,
                  84,
                  18,
                  47,
                  77,
                  65,
                  93,
                  79,
                  80,
                  88,
                  3,
                  83,
                  75,
                  67,
                  70,
                  81,
                  65,
                  15,
                  11,
                  23,
                  25,
                  51,
                  13,
                  17,
                  72,
                  45,
                  7,
                  23,
                  91,
                  84
               },
               2,
               97
            )
         )) {
            Object[] var10 = new Object[0];
            return null;
         } else {
            switch (cvm.xK(var4)) {
               case -2015283300:
                  Object[] var9 = new Object[]{var6[0]};
                  return null;
               case -1619593404:
                  Object[] var8 = new Object[]{var6[0]};
                  return null;
               case 1356014482:
                  Object[] var7 = new Object[]{Arrays.toString((Object[])var6[0])};
                  return null;
               case 1485640103:
                  Object[] var10000 = new Object[]{Arrays.toString((Object[])var6[0])};
                  return null;
               default:
                  return null;
            }
         }
      }

      @Override
      public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) throws DexDecEvalSandboxExecutionException {
         switch (cvm.xK(var4)) {
            case -2146531055:
               long var110 = 0L;
               long var164 = 0L;
               boolean var179 = false;

               try {
                  FileLock var188 = ((FileChannel)var5).lock(var110, var164, var179);
                  return Wrapper.wrap(var188);
               } catch (IOException var29) {
                  throw new DexDecEvalSandboxExecutionException(var29);
               }
            case -2128637108:
               int var109 = (Integer)var6.get(0);
               int var147 = (Integer)var6.get(1);
               int var163;
               if (var109 == var147) {
                  var163 = var109;
               } else if (var109 == 0 || var147 == 0) {
                  var163 = 0;
               } else if (var109 == -3 || var147 == -3) {
                  var163 = -3;
               } else if (var109 != -2 && var147 != -2) {
                  var163 = -1;
               } else {
                  var163 = -2;
               }

               return Wrapper.wrap(var163);
            case -2093031467:
            case -1047024949:
            case -425070165:
               Object var108 = bld.this.Uv(
                  cvm.q(
                     new byte[]{-103, 15, 10, 22, 29, 6, 13, 74, 77, 12, 1, 26, 17, 11, 26, 90, 92, 23, 22, 93, 111, 50, 0, 22, 17, 57, 44, 15, 15, 6, 2, 23},
                     1,
                     248
                  )
               );
               return Wrapper.wrap(var108);
            case -2088301382:
            case -1936802804:
               String var107 = (String)var6.get(0);
               Object[] var199 = new Object[]{var107};
               String var146 = bld.this.xK(var107);
               if (var146 != null) {
                  bld.this.q(var107, new File(var146));
               }
               break;
            case -2061638181:
               return Wrapper.wrap(null);
            case -2036523909:
            case -1965212556:
            case -1377251652:
            case -688057012:
            case 1314245546:
               Object[] var198 = new Object[]{var6.get(0)};
               break;
            case -1986661425:
            case -1841955043:
               if ((Class)var5 == bld.this.Gf.getSandboxClassLoader().getClass()
                  && cvm.q(cvm.q(new byte[]{51, 14, 4, 17, 62, 0, 20, 28}, 2, 14), (String)var6.get(0))) {
                  Object[] var197 = new Object[0];
               }
               break;
            case -1820388399:
            case 847643324:
               long var106 = (3600000000000L + System.nanoTime()) / 1000000L;
               return Wrapper.wrap(var106);
            case -1779652544:
               return Wrapper.wrap(null);
            case -1756995395:
            case -488269789:
            case 898564948:
               File var105 = new File(bld.this.EB, bld.this.Rr);
               if (!var105.exists()) {
                  var105.mkdirs();
               }

               return Wrapper.wrap(new File[]{var105});
            case -1737602261:
               long var104 = System.nanoTime() / 1000000L / 2L;
               return Wrapper.wrap(var104);
            case -1712406020:
            case -1007002235:
               String var103 = bld.this.Dz;
               return Wrapper.wrap(var103);
            case -1695331608:
            case -62178274:
            case 1225355020:
               return null;
            case -1605690625:
               long var102 = System.nanoTime() / 1000000L;
               return Wrapper.wrap(var102);
            case -1596857532:
               return Wrapper.wrap(300);
            case -1594025097:
            case -606749359:
            case 877099975:
               return Wrapper.wrap(bld.this.zz);
            case -1593528056:
            case -1092273275:
            case 824903017:
               File var101 = new File(bld.this.EB, bld.this.Rr);
               if (!var101.exists()) {
                  var101.mkdirs();
               }

               return Wrapper.wrap(var101);
            case -1575261098:
            case 1272988761:
            case 1621299812:
               String var100 = (String)var6.get(0);
               Object var145 = bld.this.qa.get(var100);
               if (var145 == null) {
                  try {
                     URLClassLoader var162 = bld.this.Gf.getSandboxClassLoader();
                     Class var172 = var162.loadClass(
                        cvm.q(
                           new byte[]{
                              -94, 15, 10, 22, 29, 6, 13, 74, 79, 17, 0, 94, 125, 59, 9, 19, 23, 1, 52, 34, 23, 3, 3, 23, 23, 11, 13, 6, 22, 58, 36, 29, 28
                           },
                           1,
                           195
                        )
                     );
                     var145 = var172.getConstructor().newInstance();
                     bld.this.qa.put(var100, var145);
                  } catch (Exception var28) {
                     throw new DexDecEvalSandboxExecutionException(var28);
                  }
               }

               return Wrapper.wrap(var145);
            case -1549123739:
            case -1534067014:
            case 644629666:
               String var99 = (String)var6.get(0);
               File var144 = new File(bld.this.EB, bld.this.ui + cvm.q(new byte[]{108, 9, 25, 21, 23, 26}, 2, 122));
               if (!var144.exists()) {
                  var144.mkdirs();
               }

               File var161 = new File(var144, var99);
               return Wrapper.wrap(var161);
            case -1531575882:
               return Wrapper.wrap(400);
            case -1453311859:
               ArrayList var98 = new ArrayList();
               return Wrapper.wrap(var98);
            case -1376434574:
               ProcessBuilder var97 = (ProcessBuilder)var5;
               List var143 = var97.command();
               return bld.this.q(var143);
            case -1350886070:
               String var96 = (String)var6.get(0);
               List var142 = Arrays.asList(var96.split("\\s+"));
               return bld.this.q(var142);
            case -1280323317:
            case -452023474:
               String var95 = (String)var6.get(0);
               Object[] var10000 = new Object[]{var95};
               break;
            case -1259485068:
               List var94 = (List)var6.get(0);

               for (File var159 : var94) {
                  int var170 = com.pnfsoftware.jeb.util.io.IO.getFirstIntLE(var159.getPath());
                  if (var170 == 175662436) {
                     bld.this.RF(var159);
                  } else if (var170 == 67324752) {
                     try (
                        ZipBrowser var177 = new ZipBrowser(var159);
                        InputStream var186 = var177.getEntryStream(cvm.q(new byte[]{32, 3, 17, 10, 1, 12, 20, 70, 16, 69, 80}, 2, 216));
                     ) {
                        byte[] var194 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var186);
                        bld.this.q(var194);
                     } catch (IOException var40) {
                     }
                  }
               }

               try {
                  URLClassLoader var141 = bld.this.Gf.getSandboxClassLoader();
                  Class var160 = var141.loadClass(cvm.q(new byte[]{39, 14, 28, 15, 27, 2, 73, 27, 13, 83, 92, 6, 68, 14, 117, 92, 65, 117, 69, 76, 87}, 2, 100));
                  Constructor var171 = var160.getConstructor(String.class);
                  Class var178 = var141.loadClass(
                     cvm.q(
                        new byte[]{
                           39, 14, 28, 15, 27, 2, 73, 27, 13, 83, 92, 6, 68, 14, 117, 92, 65, 99, 77, 84, 90, 124, 91, 71, 88, 4, 10, 30, 4, 14, 9, 11, 84
                        },
                        2,
                        120
                     )
                  );
                  Constructor var187 = var178.getConstructor(File.class, Boolean.class, var160, boolean.class);
                  Object var195 = Array.newInstance(var178, var94.size());
                  int var196 = 0;

                  for (File var16 : var94) {
                     Object var17 = var171.newInstance(bld.this.Dw(var16));
                     Object var18 = var187.newInstance(var16, null, var17, false);
                     Array.set(var195, var196, var18);
                     var196++;
                  }

                  return Wrapper.wrap(var195);
               } catch (Exception var37) {
                  return null;
               }
            case -1228609088:
            case -513911807:
            case -176640088:
               File var93 = new File(bld.this.EB, bld.this.TX + cvm.q(new byte[]{108, 12, 17, 26, 26, 12}, 2, 252));
               if (!var93.exists()) {
                  var93.mkdirs();
               }

               return Wrapper.wrap(var93);
            case -1223121674:
               return Wrapper.wrap(null);
            case -1221764277:
            case -639099770:
            case 1466331619:
               return Wrapper.wrap(bld.this.Dz);
            case -1139591185:
            case -456127112:
            case 2027658666:
               Object var92 = bld.this.Uv(
                  cvm.q(
                     new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 23, 79, 70, 23, 76, 78, 69, 23, 122, 92, 66, 84, 87, 94, 70, 102, 73, 83, 32, 30, 23, 6, 30},
                     2,
                     141
                  )
               );
               return Wrapper.wrap(var92);
            case -1139064595:
            case 575404928:
            case 873107103:
               File var91 = new File(bld.this.EB, bld.this.ui + cvm.q(new byte[]{108, 12, 31, 29, 23, 54, 4, 9, 23, 72, 77}, 2, 221));
               if (!var91.exists()) {
                  var91.mkdirs();
               }

               return Wrapper.wrap(var91);
            case -1126172715:
            case -282766482:
            case 2087380722:
               File var90 = new File(bld.this.EB, bld.this.ui + cvm.q(new byte[]{32, 76, 2, 2, 11, 13}, 1, 15));
               if (!var90.exists()) {
                  var90.mkdirs();
               }

               return Wrapper.wrap(var90);
            case -1036679460:
               String var89 = (String)var6.get(0);
               if (cvm.q(5, 6647664, -416307977, var89)) {
                  String var139 = cvm.q(
                     new byte[]{105, 15, 10, 22, 29, 6, 13, 74, 90, 17, 9, 9, 21, 24, 7, 1, 23, 87, 122, 49, 9, 9, 21, 24, 7, 1, 23, 52, 44, 15, 15, 6, 2, 23},
                     1,
                     8
                  );
                  Object var158 = bld.this.Hk.get(var139);
                  if (var158 == null) {
                     var158 = bld.this.Uv(var139);
                     bld.this.Hk.put(var139, var158);
                  }

                  if (var158 != null) {
                     return Wrapper.wrap(var158);
                  }
               }
               break;
            case -1026979224:
               return Wrapper.wrap(bld.this.sH);
            case -1010614805:
               int var88 = (Integer)var6.get(0);
               int var138 = var88 >> 8 & 0xFF;
               return Wrapper.wrap(var138);
            case -1006881245:
            case -736443888:
            case 622086970:
               String var87 = (String)var6.get(0);
               File var137 = new File(bld.this.EB, bld.this.ui + cvm.q(new byte[]{108, 14, 0, 9, 45}, 2, 230) + var87);
               if (!var137.exists()) {
                  var137.mkdirs();
               }

               return Wrapper.wrap(var137);
            case -963875898:
               if (var5 == bld.this.lm) {
                  ApkManifestHelper.ApplicationDescription var86 = new ApkManifestHelper(bld.this.PV).getApplicationDescription();
                  if (var86 != null) {
                     String var136 = (String)var6.get(0);
                     String var157 = (String)var86.getMetadata().get(var136);
                     return Wrapper.wrap(var157);
                  }
               }
               break;
            case -919097730:
            case 634143722:
            case 1573542084:
               return Wrapper.wrap(null);
            case -909999335:
            case -888488609:
            case 1490512063:
               return Wrapper.wrap(0);
            case -894335529:
            case 532206526:
            case 605906730:
            case 1192292059:
               File var85 = (File)var5;
               if (com.pnfsoftware.jeb.util.io.IO.inFolder(var85, bld.this.EB)) {
                  String var134 = com.pnfsoftware.jeb.util.io.IO.getRelativePath(var85, bld.this.EB);
                  var134 = "/" + var134.replace('\\', '/');
                  return Wrapper.wrap(var134);
               }
               break;
            case -853057070:
               if (var5 == bld.this.lm) {
                  ApkManifestHelper.ApplicationDescription var84 = new ApkManifestHelper(bld.this.PV).getApplicationDescription();
                  if (var84 != null) {
                     String var133 = (String)var6.get(0);
                     boolean var156 = var84.getMetadata().containsKey(var133);
                     return Wrapper.wrap(var156);
                  }
               }
               break;
            case -836565104:
               return Wrapper.wrap(bld.this.getJavaProperties());
            case -832732344:
            case 312676424:
               long var83 = 3600000000000L + System.nanoTime();
               return Wrapper.wrap(var83);
            case -814996805:
               int var82 = (Integer)var6.get(0);
               int var132 = (Integer)var6.get(1);
               int var155 = (Integer)var6.get(2);
               int var169 = 0xFF000000 | var82 << 16 | var132 << 8 | var155;
               return Wrapper.wrap(var169);
            case -802113500:
            case 1568344285:
            case 2071058608:
               return Wrapper.wrap(bld.this.io);
            case -779258908:
               String var81 = (String)var6.get(0);
               if (var81.equals(bld.this.CE)) {
                  return Wrapper.wrap(bld.this.zz);
               }
               break;
            case -760637487:
               if (bld.this.PV.getInput() instanceof FileInput) {
                  String var80 = (String)bld.this.q(var5, cvm.q(new byte[]{-103, 17, 27, 42, 50, 0, 22, 17, 36, 49, 21, 28}, 1, 248));
                  if (var80 != null) {
                     File var131 = ((FileInput)bld.this.PV.getInput()).getFile();

                     try {
                        Wrapper var193;
                        try (ZipBrowser var154 = new ZipBrowser(var131)) {
                           InputStream var168 = var154.getEntryStream(var80);
                           byte[] var176 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var168);
                           File var185 = bld.this.xK(new File(bld.this.If + "/" + var80));
                           if (!var185.isFile()) {
                              com.pnfsoftware.jeb.util.io.IO.createFile(var185, true);
                              com.pnfsoftware.jeb.util.io.IO.writeFile(var185, var176);
                           }

                           var193 = Wrapper.wrap(new FileInputStream(var185));
                        }

                        return var193;
                     } catch (IOException var44) {
                     }
                  }
               }
               break;
            case -694125512:
            case 172001029:
            case 265782452:
               String var79 = (String)var6.get(0);
               if ("activity".equals(var79)) {
                  Object var130 = bld.this.Uv(
                     cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 21, 80, 88, 77, 104, 67, 69, 80, 79, 90, 88, 89, 127, 81, 92, 85, 75, 69, 61}, 2, 250)
                  );
                  if (var130 != null) {
                     return Wrapper.wrap(var130);
                  }
               }

               return Wrapper.wrap(null);
            case -686046401:
            case -252633682:
            case 486193603:
               File var78 = new File(bld.this.EB, bld.this.ui + cvm.q(new byte[]{108, 9, 25, 21, 23, 26}, 2, 110));
               if (!var78.exists()) {
                  var78.mkdirs();
               }

               return Wrapper.wrap(var78);
            case -647574041:
               File[] var77 = new File[]{bld.this.EB};
               return Wrapper.wrap(var77);
            case -569667798:
               long var76 = System.nanoTime();
               return Wrapper.wrap(var76);
            case -554491784:
               File var75 = (File)var5;
               File var129 = (File)var6.get(0);
               if (com.pnfsoftware.jeb.util.io.IO.inFolder(var75, bld.this.EB) && com.pnfsoftware.jeb.util.io.IO.inFolder(var129, bld.this.EB)) {
                  boolean var153 = var75.renameTo(var129);
                  return Wrapper.wrap(var153);
               }
               break;
            case -530190971:
               String var74 = (String)var6.get(0);
               if (!var74.startsWith("/")) {
                  break;
               }

               String var128 = var74.substring(1);
               IInput var152 = bld.this.PV.getInput();
               if (!(var152 instanceof FileInput)) {
                  break;
               }

               File var167 = ((FileInput)var152).getFile();

               try (ZipBrowser var175 = new ZipBrowser(var167)) {
                  if (var175.getEntry(var128) != null) {
                     String var184 = cvm.q(cvm.q(new byte[]{-122, 11, 19, 72, 92, 15, 5, 9, 95}, 1, 236)) + bld.this.Dz + "!/" + var128;
                     return Wrapper.wrap(new URL(var184));
                  }
               } catch (IOException var47) {
                  bld.Me.catching(var47);
               }
               break;
            case -469121180:
               int var73 = (Integer)var6.get(0);
               String var127 = ((com.pnfsoftware.jeb.corei.parsers.apk.ej)bld.this.PV).q().getDefaultString(var73);
               return Wrapper.wrap(var127);
            case -422691230:
               String var72 = "L" + (String)var6.get(0) + ";";

               try {
                  Class var126 = bld.this.getState().loadClass(var72);
                  return Wrapper.wrap(var126);
               } catch (Exception var43) {
                  bld.Me.catching(var43);
                  break;
               }
            case -340267726:
               File var71 = (File)var5;
               if (com.pnfsoftware.jeb.util.io.IO.inFolder(var71, bld.this.EB)) {
                  if (var71.isDirectory()) {
                     return Wrapper.wrap(var71.delete());
                  }

                  File var125 = new File(bld.this.EB, cvm.q(new byte[]{109, 43, 53, 53, 55, 61, 34, 44}, 2, 174));
                  if (!var125.exists()) {
                     var125.mkdir();
                  }

                  File var151 = new File(var125, var71.getName());
                  return Wrapper.wrap(var71.renameTo(var151));
               }
               break;
            case -324555365:
               String var70 = (String)var6.get(0);
               if (var70.equals(bld.this.Dz)) {
                  Object var124 = bld.this.Uv(
                     cvm.q(new byte[]{-69, 15, 10, 22, 29, 6, 13, 74, 77, 12, 1, 26, 17, 11, 26, 90, 94, 29, 67, 126, 49, 2, 8, 10, 6, 2, 44, 39, 8, 9}, 1, 218)
                  );
                  bld.this.q(var124, cvm.q(new byte[]{95, 17, 0, 28, 5, 10, 2, 21, 29, 6, 1, 39, 39, 8, 9}, 1, 62), bld.this.zz);
                  return Wrapper.wrap(var124);
               }
               break;
            case -286084764:
            case 1549383917:
               return Wrapper.wrap(false);
            case -165524179:
               return Wrapper.wrap(400);
            case -139657970:
               long var69 = System.currentTimeMillis();
               return Wrapper.wrap(var69);
            case -109422525:
            case 1000147492:
            case 1870773074:
               return Wrapper.wrap(bld.this.za);
            case -17774301:
               String var67 = (String)var6.get(0);
               if (var67.startsWith("/")) {
                  var67 = var67.substring(1);
                  return bld.this.Dw(var67);
               }
               break;
            case 2941652:
            case 762628043:
            case 1596848825:
               return null;
            case 78138881:
               if (bld.this.PV.getInput() instanceof FileInput var66) {
                  String var122 = (String)var6.get(0);
                  File var150 = var66.getFile();
                  var122 = cvm.q(new byte[]{-117, 18, 0, 22, 17, 7}, 1, 234) + "/" + var122 + "/";

                  try {
                     Wrapper var191;
                     try (ZipBrowser var166 = new ZipBrowser(var150)) {
                        ArrayList var174 = new ArrayList();

                        for (String var190 : var166.getEntries().keySet()) {
                           if (var190.startsWith(var122)) {
                              String var14 = var190.substring(var122.length());
                              if (var14.indexOf(47) < 0) {
                                 var174.add(var14);
                              }
                           }
                        }

                        Object[] var183 = var174.toArray(new String[var174.size()]);
                        var191 = Wrapper.wrap(var183);
                     }

                     return var191;
                  } catch (IOException var36) {
                     throw new DexDecEvalSandboxExecutionException(new InvocationTargetException(var36));
                  }
               }
               break;
            case 88896433:
            case 1181807553:
               if (var6.size() == 3) {
                  File var64 = (File)var6.get(2);
                  if (var64 != null) {
                     break;
                  }
               }

               File var65 = new File(bld.this.EB, bld.this.ui + cvm.q(new byte[]{108, 12, 17, 26, 26, 12}, 2, 218));
               if (!var65.exists()) {
                  var65.mkdirs();
               }

               String var120 = (String)var6.get(0);
               String var149 = (String)var6.get(1);

               try {
                  File var165 = File.createTempFile(var120, var149, var65);
                  return Wrapper.wrap(var165);
               } catch (IOException var45) {
                  break;
               }
            case 111705615:
               int var63 = (Integer)var6.get(0);
               int var119 = var63 & 0xFF;
               return Wrapper.wrap(var119);
            case 134764335:
            case 709051306:
               if (bld.this.PV.getInput() instanceof FileInput var62) {
                  File var118 = var62.getFile();
                  String var148 = (String)var6.get(0);
                  String var10 = cvm.q(new byte[]{-52, 18, 0, 22, 17, 7}, 1, 173) + "/" + var148;

                  byte[] var173;
                  try (
                     ZipBrowser var180 = new ZipBrowser(var118);
                     InputStream var189 = var180.getEntryStream(var10);
                  ) {
                     var173 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var189);
                  } catch (IOException var34) {
                     try (ZipFailSafeReader var13 = new ZipFailSafeReader(var118, 0, true, false, false)) {
                        var173 = var13.readData(var10).getUncompressedData();
                     } catch (IOException var23) {
                        throw new DexDecEvalSandboxExecutionException(new InvocationTargetException(var23));
                     }
                  }

                  ByteArrayInputStream var181 = new ByteArrayInputStream(var173);
                  return Wrapper.wrap(var181);
               }
               break;
            case 140561163:
               int var61 = (Integer)var6.get(0);
               int var116 = var61 >> 16 & 0xFF;
               return Wrapper.wrap(var116);
            case 199965973:
               Object var60 = bld.this.Uv(cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 2, 73, 77, 20, 7, 119, 88, 87, 93, 92, 91}, 2, 62));
               return Wrapper.wrap(var60);
            case 410077869:
               return Wrapper.wrap(null);
            case 421591354:
               return Wrapper.wrap(bld.this.za);
            case 504643480:
            case 597015138:
            case 783671992:
               Object var59 = bld.this.Uv("android.content.res.Resources");
               return Wrapper.wrap(var59);
            case 558029666:
               long var58 = (Long)var6.get(0);
               long var9 = 0L;
               boolean var11 = (Boolean)var6.get(2);

               try {
                  FileLock var12 = ((FileChannel)var5).lock(var58, var9, var11);
                  return Wrapper.wrap(var12);
               } catch (IOException var21) {
                  throw new DexDecEvalSandboxExecutionException(var21);
               }
            case 560641042:
               String var57 = "L" + ((String)var6.get(0)).replace('.', '/') + ";";

               try {
                  Class var115 = bld.this.getState().loadClass(var57);
                  return Wrapper.wrap(var115);
               } catch (Exception var42) {
                  bld.Me.catching(var42);
               }
            case 566204235:
            case 733911727:
            case 855197106:
            default:
               break;
            case 743738796:
               String var56 = (String)var6.get(0);
               bld.this.q(bld.this.xK(new File(var56)));
               break;
            case 777284998:
               String var55 = (String)var6.get(0);
               if (!var55.startsWith("/")) {
                  return bld.this.Dw(var55);
               }
               break;
            case 795794457:
            case 880103391:
            case 1425473603:
               return Wrapper.wrap(bld.this.CE);
            case 1070486385:
               String var54 = (String)var6.get(0);
               String var114 = bld.this.getJavaProperties().getProperty(var54);
               if (var114 == null) {
                  var114 = (String)var6.get(1);
               }

               return Wrapper.wrap(var114);
            case 1264513105:
               String var53 = (String)var6.get(0);
               if (var53 != null) {
                  String var113 = bld.this.jq + "/lib" + var53 + ".so";
                  return Wrapper.wrap(var113);
               }
               break;
            case 1305482006:
               String var52 = (String)var6.get(0);
               if (var52.equals("PATH")) {
                  String var112 = cvm.q(
                     new byte[]{
                        64,
                        95,
                        2,
                        29,
                        11,
                        17,
                        22,
                        23,
                        91,
                        77,
                        11,
                        7,
                        84,
                        21,
                        78,
                        17,
                        21,
                        29,
                        87,
                        76,
                        12,
                        2,
                        67,
                        79,
                        15,
                        10,
                        22,
                        29,
                        6,
                        13,
                        74,
                        92,
                        7,
                        27,
                        26,
                        29,
                        4,
                        8,
                        74,
                        77,
                        11,
                        7,
                        84,
                        21,
                        78,
                        17,
                        21,
                        29,
                        87,
                        76,
                        12,
                        2,
                        67,
                        79,
                        15,
                        10,
                        22,
                        29,
                        6,
                        13,
                        74,
                        79,
                        19,
                        6,
                        91,
                        77,
                        11,
                        7,
                        84,
                        21,
                        92,
                        10,
                        10,
                        7,
                        17,
                        8,
                        50,
                        58,
                        29,
                        12,
                        91,
                        77,
                        11,
                        7,
                        84,
                        21,
                        92,
                        10,
                        10,
                        7,
                        17,
                        8,
                        66,
                        77,
                        11,
                        7,
                        84,
                        21,
                        92,
                        10,
                        10,
                        7,
                        17,
                        8,
                        66,
                        87,
                        26,
                        11,
                        7,
                        84,
                        21,
                        64,
                        11,
                        9,
                        66,
                        77,
                        11,
                        7,
                        84,
                        21,
                        89,
                        19,
                        11,
                        10,
                        11,
                        29,
                        93,
                        77,
                        11,
                        7,
                        84,
                        21,
                        89,
                        19,
                        11,
                        10,
                        11,
                        29,
                        93,
                        87,
                        26,
                        11,
                        7
                     },
                     1,
                     111
                  );
                  return Wrapper.wrap(var112);
               }
               break;
            case 1329997687:
               return Wrapper.wrap(50);
            case 1473156074:
            case 2044573158:
            case 2057857915:
               return Wrapper.wrap(bld.this.nf);
            case 1842063898:
               String var51 = (String)var6.get(0);
               String var111 = bld.this.getJavaProperties().getProperty(var51);
               return Wrapper.wrap(var111);
            case 1877396972:
               return Wrapper.wrap(2);
            case 1911166531:
               String var50 = (String)var6.get(1);
               if (cvm.q(10, 6580577, 189448852, var50)) {
                  String var8 = cvm.RF("27637ac3f5a4198e");
                  return Wrapper.wrap(var8);
               }
               break;
            case 1929818550:
               Object var49 = bld.this.Uv(
                  cvm.q(
                     new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 23, 79, 70, 23, 76, 78, 69, 23, 75, 86, 95, 14, 115, 67, 65, 81, 88, 109, 46, 28, 0, 4, 9, 23},
                     2,
                     181
                  )
               );
               return Wrapper.wrap(var49);
            case 2028113802:
               Object var48 = bld.this.Uv(cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 2, 73, 77, 20, 7, 118, 88, 92, 78}, 2, 152));
               return Wrapper.wrap(var48);
            case 2101906358:
               Object var7 = bld.this.Uv(
                  cvm.q(
                     new byte[]{
                        34, 1, 20, 11, 29, 0, 3, 70, 2, 73, 77, 20, 7, 119, 88, 87, 93, 92, 91, 105, 92, 67, 87, 64, 95, 99, 32, 28, 21, 17, 3, 9, 76, 4, 28
                     },
                     2,
                     13
                  )
               );
               return Wrapper.wrap(var7);
         }

         return null;
      }
   }
}
