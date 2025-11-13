package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.ICertificateUnit;
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
import com.pnfsoftware.jeb.util.io.IO;
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
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

public class bhg implements IEmulatedAndroid {
   private static final ILogger ED = GlobalLog.getLogger(bhg.class);
   static final AndroidPlatformABI pC = AndroidPlatformABI.ARM64;
   static final String A = ckx.pC(new byte[]{-16, 0, 19, 17, 11, 94, 2}, 1, 145);
   static final String kS = ckx.pC(new byte[]{17, 6, 22, 23}, 1, 100);
   static final String wS = ckx.pC(
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
         4,
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
      14
   );
   private IApkUnit Sc;
   private IDexUnit ah;
   private IDexDecompilerUnit eP;
   private IDGlobalContext UO;
   private IDState Ab;
   private btp rl;
   boolean UT;
   private bhg.Av z;
   private boolean Ek = false;
   private int hK;
   private int Er;
   private String FE;
   private File Aj;
   private String EX;
   private String LM;
   private String mv;
   private String sO;
   private String os;
   private String Cu;
   private String hZ;
   private File UW;
   private Set PR = new TreeSet();
   private Map cX = new TreeMap();
   private boolean DQ;
   private boolean ZN;
   private volatile cdy OB;
   private volatile Properties pF;
   private volatile Map Bc;
   Object E;
   Object sY;
   Object ys;
   Object ld;
   Object gp;
   Object oT;
   Object fI;
   Object WR;
   Object NS;
   Map vP = new HashMap();
   Map xC = new HashMap();
   private static final char[] OI = new char[63];
   private static final String[] Bf;
   private Map Pe = new HashMap();
   private Set ck = new HashSet();
   private static final String RW;

   public static IEmulatedAndroid pC(IDState var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("This method requires an existing dex emulator state");
      } else if (var0.getEmulatedEnvironment() != null) {
         return var0.getEmulatedEnvironment();
      } else {
         IApkUnit var1 = var0.getApk();
         if (var1 == null) {
            return null;
         } else {
            return var1.getPackageName() == null ? null : new bhg(null, var0);
         }
      }
   }

   public bhg(IDexDecompilerUnit var1) {
      this(DexUtil.findParentApk(var1.getCodeUnit()), null);
   }

   private bhg(IApkUnit var1, IDState var2) {
      if (var1 == null) {
         var1 = var2.getApk();
         if (var1 == null) {
            throw new IllegalArgumentException(
               ckx.pC(
                  new byte[]{
                     23,
                     7,
                     21,
                     89,
                     51,
                     57,
                     44,
                     72,
                     7,
                     65,
                     70,
                     7,
                     75,
                     79,
                     73,
                     25,
                     75,
                     86,
                     93,
                     85,
                     91,
                     66,
                     87,
                     70,
                     12,
                     65,
                     33,
                     82,
                     32,
                     51,
                     39,
                     69,
                     85,
                     15,
                     7,
                     16,
                     14
                  },
                  2,
                  67
               )
            );
         }
      }

      if (var1.getPackageName() == null) {
         throw new IllegalArgumentException(
            ckx.pC(
               new byte[]{
                  23,
                  7,
                  21,
                  89,
                  51,
                  57,
                  44,
                  72,
                  25,
                  65,
                  81,
                  67,
                  71,
                  79,
                  69,
                  25,
                  91,
                  86,
                  73,
                  78,
                  18,
                  86,
                  71,
                  89,
                  64,
                  89,
                  111,
                  2,
                  19,
                  12,
                  15,
                  0,
                  83,
                  18,
                  11,
                  0,
                  15,
                  0,
                  0,
                  0,
                  13,
                  27,
                  22,
                  83,
                  65,
                  8,
                  9,
                  29,
                  76,
                  10,
                  14,
                  26,
                  17,
                  18,
                  71,
                  78,
                  97,
                  13,
                  76,
                  86,
                  19,
                  5,
                  14,
                  12,
                  84,
                  50,
                  78,
                  22,
                  23,
                  28,
                  12,
                  22,
                  86,
                  8,
                  5,
                  64,
                  42,
                  9,
                  21,
                  10,
                  6,
                  73,
                  16,
                  1,
                  0,
                  72,
                  8,
                  2,
                  9,
                  80,
                  80,
                  90,
                  82,
                  82,
                  75,
                  69,
                  18,
                  94,
                  83,
                  88,
                  73,
                  1
               },
               2,
               94
            )
         );
      } else {
         this.Sc = var1;
         this.ah = var1.getDex();
         this.eP = this.ah.getDecompiler();
         this.UO = this.eP.getIntermediateContext();
         if (var2 == null) {
            var2 = this.oT();
            this.UT = true;
         } else if (var2.getEmulatedEnvironment() != null) {
            throw new IllegalStateException(
               ckx.pC(
                  new byte[]{
                     93,
                     40,
                     24,
                     25,
                     13,
                     21,
                     27,
                     29,
                     82,
                     65,
                     13,
                     30,
                     23,
                     4,
                     5,
                     29,
                     89,
                     65,
                     21,
                     0,
                     21,
                     2,
                     11,
                     13,
                     1,
                     68,
                     84,
                     27,
                     79,
                     65,
                     15,
                     78,
                     105,
                     13,
                     23,
                     39,
                     21,
                     21,
                     17
                  },
                  1,
                  24
               )
            );
         }

         this.Ab = var2;
         this.rl = (btp)var2;
         this.z = new bhg.Av();
         var2.registerSandboxHooks(this.z);
         this.rl.pC((IEmulatedAndroid)this);
         this.hK = 1000 + (int)(Math.random() * 5000.0);
         this.Er = 10000 + (int)(Math.random() * 10000.0);
         this.FE = var1.getPackageName();
         ApkManifestHelper var3 = new ApkManifestHelper(var1);
         int[] var4 = var3.getSdkVersion();
         this.EX = pC(this.FE);
         this.LM = this.EX + ckx.pC(new byte[]{84, 77, 3, 18, 22, 75, 79, 17, 27}, 1, 123);
         if (var1.getInput() instanceof FileInput) {
            this.Aj = ((FileInput)var1.getInput()).getFile();
         }

         this.mv = this.EX + ckx.pC(new byte[]{100, 67, 5, 11}, 1, 75);
         this.sO = this.EX + ckx.pC(new byte[]{-53, 67, 5, 11, 77, 78, 19, 31, 91, 2}, 1, 228);
         this.os = ckx.pC(new byte[]{108, 11, 17, 13, 19, 70, 18, 27, 17, 82, 7, 83, 6}, 2, 230) + this.FE;
         this.Cu = ckx.pC(
               new byte[]{108, 28, 4, 22, 0, 8, 0, 13, 91, 69, 69, 22, 69, 65, 69, 92, 93, 28, 28, 15, 115, 94, 86, 71, 67, 73, 43, 93, 5, 2, 24, 4, 15},
               2,
               208
            )
            + this.FE;
         this.hZ = ckx.pC(
               new byte[]{88, 92, 7, 27, 29, 19, 6, 2, 74, 74, 8, 24, 25, 13, 21, 17, 1, 75, 31, 31, 110, 47, 10, 22, 29, 6, 13, 75, 64, 13, 0, 77}, 1, 119
            )
            + this.FE;
         if (var2.isSandboxEnabled()) {
            try {
               String var5 = this.FE;
               this.UW = var2.setSandboxDroppedFilesCollection(var5, false);
               IO.deleteDirectoryContents(this.UW);
               new File(this.UW, this.os).mkdirs();
               new File(this.UW, this.EX).mkdirs();
            } catch (Exception var12) {
               throw new RuntimeException(var12);
            }

            try {
               URLClassLoader var13 = var2.getSandboxClassLoader();
               Class var6 = var13.loadClass(
                  ckx.pC(
                     new byte[]{
                        34,
                        1,
                        20,
                        11,
                        29,
                        0,
                        3,
                        70,
                        23,
                        79,
                        70,
                        23,
                        76,
                        78,
                        69,
                        23,
                        73,
                        94,
                        2,
                        112,
                        83,
                        83,
                        89,
                        84,
                        75,
                        69,
                        2,
                        19,
                        15,
                        2,
                        11,
                        0,
                        82,
                        40,
                        3,
                        20,
                        67
                     },
                     2,
                     171
                  )
               );
               this.E = var6.getConstructor().newInstance();
               var6 = var13.loadClass(ckx.pC(new byte[]{112, 15, 10, 22, 29, 6, 13, 74, 65, 28, 93, 108, 55, 27, 10, 8, 9}, 1, 17));
               this.ld = var6.getConstructor().newInstance();
               var6 = var13.loadClass(
                  ckx.pC(
                     new byte[]{
                        -99, 15, 10, 22, 29, 6, 13, 74, 77, 12, 1, 26, 17, 11, 26, 90, 94, 29, 67, 111, 49, 0, 28, 5, 10, 2, 21, 29, 6, 1, 39, 39, 8, 9
                     },
                     1,
                     252
                  )
               );
               this.gp = var6.getConstructor().newInstance();
               var6.getField(ckx.pC(new byte[]{-120, 12, 2, 29, 25, 5, 9, 54, 55, 15, 61, 51, 23, 1, 26, 6, 1}, 1, 235)).setInt(this.gp, 33);
               var6.getField(ckx.pC(new byte[]{-76, 12, 2, 29, 25, 5, 9, 54, 55, 15, 61, 51, 23, 1, 26, 6, 1, 45, 44, 11, 1, 11, 15, 12, 8}, 1, 215))
                  .set(this.gp, "13");
               var6.getField(ckx.pC(new byte[]{39, 14, 4, 24, 54, 0, 21}, 2, 61)).set(this.gp, this.os);
               var6.getField(ckx.pC(new byte[]{27, 2, 29, 12, 6, 22, 0, 61, 47, 12, 8}, 1, 107)).set(this.gp, this.FE);
               var6.getField(ckx.pC(new byte[]{48, 0, 5, 11, 17, 12, 35, 1, 6}, 2, 3)).set(this.gp, this.LM);
               var6.getField(ckx.pC(new byte[]{51, 26, 18, 21, 27, 10, 52, 7, 1, 82, 75, 6, 109, 73, 67}, 2, 65)).set(this.gp, this.LM);
               var6.getField(ckx.pC(new byte[]{51, 14, 19, 18, 19, 14, 2, 38, 21, 77, 77}, 2, 46)).set(this.gp, this.FE);
               var6.getField(ckx.pC(new byte[]{55, 14, 2, 30, 23, 29, 52, 12, 31, 118, 77, 17, 90, 73, 94, 87}, 2, 104)).set(this.gp, var4[1]);
               var6.getField(ckx.pC(new byte[]{103, 8, 17, 21, 37, 37, 21, 21}, 1, 10)).set(this.gp, this.ld);
               var6 = var13.loadClass(
                  ckx.pC(new byte[]{4, 15, 10, 22, 29, 6, 13, 74, 77, 12, 1, 26, 17, 11, 26, 90, 94, 29, 67, 125, 58, 14, 9, 15, 21, 1, 7, 23}, 1, 101)
               );
               Object var7 = Array.newInstance(var6, 1);
               ICertificateUnit var8 = var1.getCertificate();
               if (var8 != null && var8.process()) {
                  Certificate var9 = var8.getCertificate();
                  Object var10 = var6.getConstructor(Certificate.class).newInstance(var9);
                  Array.set(var7, 0, var10);
               }

               var6 = var13.loadClass(
                  ckx.pC(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 23, 79, 70, 23, 76, 78, 69, 23, 73, 94, 2, 112, 83, 83, 89, 84, 75, 69, 6, 28, 7, 12}, 2, 141)
               );
               this.oT = var6.getConstructor().newInstance();
               var6.getField(ckx.pC(new byte[]{34, 31, 0, 21, 27, 10, 6, 28, 29, 79, 70, 42, 71, 70, 94}, 2, 216)).set(this.oT, this.gp);
               var6.getField(ckx.pC(new byte[]{51, 14, 19, 18, 19, 14, 2, 38, 21, 77, 77}, 2, 75)).set(this.oT, this.FE);
               var6.getField(ckx.pC(new byte[]{-15, 26, 14, 9, 15, 21, 1, 7, 23, 22}, 1, 130)).set(this.oT, var7);
               var6 = var13.loadClass(ckx.pC(new byte[]{94, 15, 10, 22, 29, 6, 13, 74, 79, 17, 0, 94, 98, 35, 14, 5, 1, 1, 37, 49, 27}, 1, 63));
               this.fI = var6.getConstructor(String.class).newInstance(this.FE);
               this.WR = this.UT("android.app.ContextImpl");
               this.pC(this.WR, ckx.pC(new byte[]{-117, 61, 49, 2, 8, 10, 6, 2, 44, 39, 8, 9}, 1, 230), this.fI);
               var6 = var13.loadClass(ckx.pC(new byte[]{-4, 15, 10, 22, 29, 6, 13, 74, 79, 17, 0, 94, 111, 49, 0, 28, 5, 10, 2, 21, 29, 6, 1}, 1, 157));
               this.ys = var6.getConstructor().newInstance();
               this.pC(this.ys, ckx.pC(new byte[]{46, 35, 31, 24, 22, 12, 3, 41, 4, 75}, 2, 102), this.fI);
               var6 = var13.loadClass(
                  ckx.pC(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 21, 80, 88, 77, 104, 67, 69, 80, 79, 90, 88, 89, 102, 88, 64, 80, 77, 68}, 2, 148)
               );
               this.sY = var6.getMethod(ckx.pC(new byte[]{42, 1, 25, 13, 27, 8, 11, 1, 14, 69}, 2, 6), this.ys.getClass(), this.gp.getClass())
                  .invoke(null, this.ys, this.gp);
               this.pC(this.fI, ckx.pC(new byte[]{99, 44, 34, 23, 29, 31, 31, 29, 13, 45, 60, 26, 23, 4, 5}, 1, 14), this.sY);
               this.pC(this.fI, ckx.pC(new byte[]{4, 44, 49, 0, 28, 5, 10, 2, 21, 29, 6, 1, 39, 39, 8, 9}, 1, 105), this.gp);
               this.pC(this.fI, ckx.pC(new byte[]{-25, 44, 49, 0, 28, 5, 10, 2, 21, 29, 6, 1}, 1, 138), this.ys);
               this.pC(this.fI, ckx.pC(new byte[]{46, 44, 28, 24, 1, 26, 43, 7, 21, 68, 77, 17}, 2, 80), var13);
               this.pC(this.fI, ckx.pC(new byte[]{46, 46, 0, 9, 54, 0, 21}, 2, 60), this.LM);
               this.pC(this.fI, ckx.pC(new byte[]{45, 63, 55, 22, 55, 45, 27}, 1, 64), this.LM);
               this.pC(this.fI, ckx.pC(new byte[]{-41, 41, 37, 21, 21, 37, 45, 27}, 1, 186), this.LM);
               ((Map)this.E.getClass().getField("map").get(this.E)).put(this.FE, this.oT);
               var6 = var13.loadClass(
                  ckx.pC(
                     new byte[]{39, 14, 28, 15, 27, 2, 73, 27, 13, 83, 92, 6, 68, 14, 115, 88, 74, 86, 104, 69, 74, 115, 94, 84, 95, 83, 3, 29, 0, 7, 9, 23},
                     2,
                     147
                  )
               );
               this.NS = var6.getConstructor(String.class).newInstance(this.EX);
            } catch (Exception var11) {
               throw new RuntimeException(var11);
            }
         }
      }
   }

   public void pC(Object var1) {
      this.ys = var1;
      this.pC(this.fI, ckx.pC(new byte[]{46, 46, 0, 9, 30, 0, 4, 9, 0, 73, 71, 13}, 2, 28), this.ys);
      this.pC(this.sY, ckx.pC(new byte[]{-102, 36, 39, 7, 29, 29, 8, 13, 45, 49, 0, 28, 5, 10, 2, 21, 29, 6, 1}, 1, 247), this.ys);
   }

   public void pC() {
      if (this.UT) {
         this.Ab.dispose();
      }
   }

   public void pC(boolean var1) {
      this.DQ = var1;
   }

   public void A(boolean var1) {
      this.ZN = var1;
   }

   public boolean A() {
      return this.ZN;
   }

   private static String pC(String var0) {
      Random var1 = new Random();
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < 22; var3++) {
         var2.append(OI[var1.nextInt(62)]);
      }

      StringBuilder var5 = new StringBuilder();

      for (int var4 = 0; var4 < 22; var4++) {
         var5.append(OI[var1.nextInt(63)]);
      }

      String var6 = ckx.pC(ckx.pC(new byte[]{108, 11, 17, 13, 19, 70, 6, 24, 4, 15, 86, 29, 12, 83, 12, 4, 22, 22, 95, 13, 23, 67, 15, 8}, 2, 95));
      return Strings.ff(var6, var2, var0, var5);
   }

   public cdy kS() {
      if (this.OB == null) {
         synchronized (this) {
            if (this.OB == null) {
               this.OB = this.ys();
            }
         }
      }

      return this.OB;
   }

   private cdy ys() {
      return cdy.pC(this.Sc.getParentProject());
   }

   @Override
   public Properties getJavaProperties() {
      if (this.pF == null) {
         synchronized (this) {
            if (this.pF == null) {
               this.pF = this.ld();
            }
         }
      }

      return this.pF;
   }

   private Properties ld() {
      Map var1 = this.A(5);
      Properties var2 = new Properties();

      for (Entry var4 : var1.entrySet()) {
         var2.setProperty((String)var4.getKey(), (String)var4.getValue());
      }

      return var2;
   }

   @Override
   public Map getSystemProperties() {
      if (this.Bc == null) {
         synchronized (this) {
            if (this.Bc == null) {
               this.Bc = this.gp();
            }
         }
      }

      return this.Bc;
   }

   @Override
   public int getProcessId() {
      return this.hK;
   }

   public int wS() {
      return this.Er;
   }

   private Map gp() {
      return this.A(6);
   }

   private Map A(int var1) {
      HashMap var2 = new HashMap();

      byte[] var3;
      try {
         var3 = this.kS().kS(var1);
      } catch (IOException var17) {
         ED.catchingSilent(var17);
         return var2;
      }

      String var4 = Strings.decodeUTF8(var3);

      for (String var8 : Strings.splitLines(var4)) {
         var8 = var8.trim();
         if (!var8.isEmpty() && !var8.startsWith("#")) {
            String[] var9 = var8.split("=", -1);
            String var10 = Strings.urldecodeUTF8(var9[0]);
            String var11 = Strings.urldecodeUTF8(var9[1]);

            for (String var15 : Bf) {
               var11 = var11.replace("{{" + var15 + "}}", switch (ckx.kS(var15)) {
                  case 42690319 -> this.FE;
                  case 1424785874 -> this.hK + "";
                  case 1749817814 -> A;
                  default -> throw new RuntimeException();
               });
            }

            var2.put(var10, var11);
         }
      }

      return var2;
   }

   private IDState oT() {
      IDState var1 = ((bpr)this.UO).pC(true, false, true);
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
      return this.Sc;
   }

   @Override
   public IDexUnit getDex() {
      return this.ah;
   }

   @Override
   public IDexDecompilerUnit getDecompiler() {
      return this.eP;
   }

   @Override
   public IDState getState() {
      return this.Ab;
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
      return pC;
   }

   @Override
   public String getPackageName() {
      return this.FE;
   }

   @Override
   public String getAppFolder() {
      return this.EX;
   }

   @Override
   public String getApkPath() {
      return this.LM;
   }

   @Override
   public String getAppDataFolder() {
      return this.os;
   }

   @Override
   public File getLocalApkFile() {
      return this.Aj;
   }

   @Override
   public File getDropboxFolder() {
      return this.UW;
   }

   public IUnit kS(boolean var1) {
      String var2 = ckx.pC(new byte[]{-71, 27, 30, 17, 2, 8, 14, 1}, 1, 204);
      IUnitProcessor var3 = this.Sc.getUnitProcessor();
      Object var4 = UnitUtil.findChildByName(this.Sc, var2, 0);
      if (var4 == null && var1) {
         var4 = new ContainerUnit(var2, var3, this.Sc, this.Sc.getPropertyDefinitionManager());
         ((IUnit)var4).process();
         this.Sc.addChild((IUnit)var4);
      }

      return (IUnit)var4;
   }

   @Override
   public String getMainActivity() {
      String var1 = this.Sc.getMainActivity();
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

   private void pC(String var1, File var2) {
      byte[] var3;
      try {
         var3 = IO.readFile(var2);
      } catch (IOException var13) {
         return;
      }

      if (var3.length >= 4 && var3[0] == 127 && var3[1] == 69 && var3[2] == 76 && var3[3] == 70) {
         String var4 = Formatter.byteArrayToHex(Hash.calculateSHA256(var3)).toString();
         if (!this.cX.containsKey(var1)) {
            this.cX.put(var1, var4);
            String var5 = Strings.ff(S.L("A native library was recovered: sha256 %s"), var4);
            DexDecompilerEvent.Message var6 = new DexDecompilerEvent.Message(var5, null);
            this.eP.recordDecompilationEvent(var6);
            ED.info(var5);
            File var7 = new File(this.UW, ckx.pC(new byte[]{-63, 106, 17, 24, 29, 21, 1}, 1, 239));
            if (!var7.exists()) {
               var7.mkdir();
            }

            File var8 = new File(var7, var4);

            try {
               IO.writeFile(var8, var3);
               ED.info(S.L("A copy of the recovered so file was dumped to: %s"), var8);
            } catch (IOException var12) {
               ED.error(S.L("Cannot write recovered so file: %s: %s"), var8, var12);
            }

            IUnit var9 = this.kS(true);
            IUnit var10 = UnitUtil.findChildByName(var9, var4, 0);
            if (var10 == null) {
               IUnitProcessor var11 = this.Sc.getUnitProcessor();
               var10 = var11.process(var4, new BytesInput(var3), var9, null, false, true);
               var9.addChild(var10);
            }

            ED.info(S.L("An unprocessed native unit was created as %s/%s under your APK unit"), var9.getName(), var4);
         }
      }
   }

   public Collection UT() {
      return Collections.unmodifiableCollection(this.cX.values());
   }

   public Map E() {
      return Collections.unmodifiableMap(this.cX);
   }

   private void pC(File var1) {
      int var2 = IO.getFirstIntLE(var1.getPath());
      if (var2 == 175662436) {
         this.A(var1);
      } else if (var2 == 67324752) {
         try (
            ZipBrowser var3 = new ZipBrowser(var1);
            InputStream var4 = var3.getEntryStream(ckx.pC(new byte[]{-4, 15, 13, 18, 0, 22, 22, 93, 74, 1, 29}, 1, 159));
         ) {
            byte[] var5 = IO.readInputStream(var4);
            this.pC(var5);
         } catch (IOException var11) {
         }
      }
   }

   private void A(File var1) {
      byte[] var2;
      try {
         var2 = IO.readFile(var1);
      } catch (IOException var3) {
         return;
      }

      this.pC(var2);
   }

   private void pC(ByteBuffer var1) {
      int var2 = var1.position();
      int var3 = var1.limit();
      int var4 = var3 - var2;
      byte[] var5 = new byte[var4];
      var1.get(var5);
      var1.position(var2);
      this.pC(var5);
   }

   private void pC(byte[] var1) {
      if (var1.length >= 3 && var1[0] == 100 && var1[1] == 101 && var1[2] == 120) {
         String var2 = Formatter.byteArrayToHex(Hash.calculateSHA256(var1)).toString();
         if (this.PR.add(var2)) {
            String var3 = Strings.ff(S.L("A dex file was recovered: sha256 %s"), var2);
            DexDecompilerEvent.Message var4 = new DexDecompilerEvent.Message(var3, null);
            this.eP.recordDecompilationEvent(var4);
            ED.info(var3);
            File var5 = new File(this.UW, ckx.pC(new byte[]{109, 43, 37, 52, 34, 44, 35}, 2, 159));
            if (!var5.exists()) {
               var5.mkdir();
            }

            File var6 = new File(var5, var2);

            try {
               IO.writeFile(var6, var1);
               ED.info(S.L("A copy of the recovered dex file was dumped to: %s"), var6);
               if (this.DQ) {
                  FileInput var7 = null;

                  try {
                     var7 = new FileInput(var6.getPath());
                     this.ah.addDex(var7);
                  } finally {
                     if (var7 != null) {
                        var7.close();
                     }
                  }
               }
            } catch (IOException var13) {
               ED.error(S.L("Cannot write recovered dex file: %s: %s"), var6, var13);
            }

            IUnit var14 = this.kS(true);
            IUnit var8 = UnitUtil.findChildByName(var14, var2, 0);
            if (var8 == null) {
               IUnitProcessor var9 = this.Sc.getUnitProcessor();
               var8 = var9.process(var2, new BytesInput(var1), var14, null, false, true);
               var14.addChild(var8);
            }

            ED.info(S.L("An unprocessed dex unit was created as %s/%s under your APK unit"), var14.getName(), var2);
            ED.info(S.L("You may choose to merge it with your primary dex unit via the Android menu, handler: Add/Merge Additional Dex Files..."));
         }
      }
   }

   public Set sY() {
      return Collections.unmodifiableSet(this.PR);
   }

   private boolean A(String var1) {
      File var2 = new File(this.UW, var1);
      if (var2.exists()) {
         return true;
      } else {
         if (this.LM.equals(var1)) {
            try {
               IO.createFoldersForFile(var2);
               IO.copyFile(this.Aj, var2, false);
            } catch (IOException var22) {
            }
         }

         if (var1.startsWith(this.sO + "/")) {
            String var3 = var1.substring(this.sO.length() + 1);
            if (var3.startsWith("lib") && var3.endsWith(".so") && !var3.contains("/")) {
               IInput var4 = this.Sc.getInput();
               if (var4 instanceof FileInput) {
                  File var5 = ((FileInput)var4).getFile();

                  try {
                     boolean var34;
                     try (ZipBrowser var32 = new ZipBrowser(var5)) {
                        byte[] var33 = var32.readEntry("lib/" + pC + "/" + var3);
                        IO.createFoldersForFile(var2);
                        IO.writeFile(var2, var33);
                        var34 = true;
                     }

                     return var34;
                  } catch (IOException var26) {
                     ED.catching(var26);
                  }
               }
            }
         }

         if (var1.startsWith(ckx.A("/proc/self/"))) {
            Object[] var10000 = new Object[]{var1};
            String var27 = var1.substring(11);
            byte[] var29 = null;
            switch (ckx.kS(var27)) {
               case -2042563435:
                  var29 = Strings.encodeUTF8(this.FE);
                  break;
               case 2033445016:
                  try {
                     var29 = this.kS().kS(7);
                  } catch (IOException var19) {
                  }
            }

            if (var29 != null) {
               try {
                  IO.createFoldersForFile(var2);
                  IO.writeFile(var2, var29);
                  return true;
               } catch (IOException var25) {
               }
            }
         }

         if (this.Ek) {
            cel var28 = this.rl.fI().WR();
            int var30 = var28.pC(var1, 0, 0, true);
            if (var30 != -1) {
               boolean var9;
               try {
                  cel.Av var31 = var28.pC(var30);
                  if (!var31.wS()) {
                     return false;
                  }

                  int var6 = var31.ld();
                  if (var6 < 0 || var6 >= 10000000) {
                     return false;
                  }

                  byte[] var7 = new byte[var6];
                  int var8 = var28.A(var30, var6, var7, 0);
                  if (var8 != var6) {
                     return false;
                  }

                  try {
                     IO.createFoldersForFile(var2);
                     IO.writeFile(var2, var7);
                     var9 = true;
                  } catch (IOException var23) {
                     return false;
                  }
               } finally {
                  var28.A(var30);
               }

               return var9;
            }
         }

         return false;
      }
   }

   private String kS(String var1) {
      if (var1.isEmpty()) {
         return null;
      } else {
         char var2 = var1.charAt(0);
         if (var2 != '/' && var2 != '\\') {
            return null;
         } else {
            File var3 = new File(var1);
            if (IO.inFolder(var3, this.UW)) {
               return null;
            } else {
               this.A(var1);
               File var4 = new File(this.UW, var1);
               return var4.getPath();
            }
         }
      }
   }

   private boolean pC(List var1, int var2) {
      String var3 = this.kS((String)var1.get(0));
      if (var3 == null) {
         return false;
      } else {
         var1.set(var2, var3);
         return true;
      }
   }

   private File kS(File var1) {
      if (IO.inFolder(var1, this.UW)) {
         return var1;
      } else {
         String var2 = var1.getPath();
         this.A(var2);
         return new File(this.UW, var2);
      }
   }

   private String wS(File var1) {
      String var3;
      if (IO.inFolder(var1, this.UW)) {
         var3 = IO.getRelativePath(var1, this.UW);
         var3 = "/" + var3.replace('\\', '/');
      } else {
         var3 = var1.getPath();
         var3 = var3.replace('\\', '/');
      }

      return var3;
   }

   public void pC(int var1) {
      this.ck.add(var1);
   }

   private Wrapper wS(String var1) {
      IInput var2 = this.Sc.getInput();
      if (var2 instanceof FileInput) {
         File var3 = ((FileInput)var2).getFile();

         try {
            Wrapper var7;
            try (ZipBrowser var4 = new ZipBrowser(var3)) {
               InputStream var5 = var4.getEntryStream(var1);
               byte[] var6 = IO.readInputStream(var5);
               var7 = Wrapper.wrap(new ByteArrayInputStream(var6));
            }

            return var7;
         } catch (IOException var10) {
            ED.catching(var10);
         }
      }

      return null;
   }

   private Wrapper pC(List var1) {
      String var2 = "";
      if (var1.size() == 2 && (((String)var1.get(0)).equals(RW) || ((String)var1.get(0)).endsWith("/" + RW))) {
         String var3 = (String)var1.get(1);
         var2 = (String)this.getSystemProperties().getOrDefault(var3, "");
      }

      Class var6 = new ByteBuddy()
         .subclass(Process.class)
         .method(ElementMatchers.named(ckx.pC(new byte[]{36, 10, 4, 48, 28, 25, 18, 28, 39, 84, 90, 6, 72, 77}, 2, 36)))
         .intercept(FixedValue.value(new ByteArrayInputStream(Strings.encodeUTF8(var2))))
         .method(ElementMatchers.named(ckx.pC(new byte[]{-42, 2, 17, 59, 58, 1, 4, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 177)))
         .intercept(FixedValue.value(new ByteArrayOutputStream()))
         .method(ElementMatchers.named(ckx.pC(new byte[]{36, 10, 4, 60, 0, 27, 8, 26, 39, 84, 90, 6, 72, 77}, 2, 143)))
         .intercept(FixedValue.value(new ByteArrayInputStream(new byte[0])))
         .method(ElementMatchers.named(ckx.pC(new byte[]{58, 1, 22, 7, 6, 29, 22}, 1, 94)))
         .intercept(FixedValue.value(void.class))
         .method(ElementMatchers.named(ckx.pC(new byte[]{42, 28, 49, 21, 27, 31, 2}, 2, 8)))
         .intercept(FixedValue.value(false))
         .method(ElementMatchers.named(ckx.pC(new byte[]{38, 23, 25, 13, 36, 8, 11, 29, 17}, 2, 124)))
         .intercept(FixedValue.value(0))
         .make()
         .load(this.getClass().getClassLoader())
         .getLoaded();

      try {
         Process var4 = (Process)var6.getConstructor().newInstance();
         return Wrapper.wrap(var4);
      } catch (Exception var5) {
         ED.catching(var5);
         return null;
      }
   }

   private Object UT(String var1) {
      try {
         URLClassLoader var2 = this.Ab.getSandboxClassLoader();
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

   private boolean pC(Object var1, String var2, Object var3) {
      try {
         Field var4 = var1.getClass().getDeclaredField(var2);
         var4.setAccessible(true);
         var4.set(var1, var3);
         return true;
      } catch (Exception var5) {
         return false;
      }
   }

   private Object pC(Object var1, String var2) {
      try {
         Field var3 = var1.getClass().getDeclaredField(var2);
         var3.setAccessible(true);
         return var3.get(var1);
      } catch (Exception var4) {
         return null;
      }
   }

   @Override
   public Object getValue(int var1, Object... var2) {
      if (var1 == 0) {
         return this.kS();
      } else {
         if (var1 == 1) {
            int var3 = (Integer)var2[0];
            this.pC(var3);
         } else if (var1 == 10) {
            return this.wS();
         }

         return null;
      }
   }

   static {
      int var0 = 0;

      for (int var1 = 0; var1 < 26; var1++) {
         OI[var0++] = (char)(97 + var1);
      }

      for (int var2 = 0; var2 < 26; var2++) {
         OI[var0++] = (char)(65 + var2);
      }

      for (int var3 = 0; var3 < 10; var3++) {
         OI[var0++] = (char)(48 + var3);
      }

      OI[var0] = '_';
      Bf = new String[]{ckx.pC(new byte[]{34, 29, 19, 17}, 2, 156), ckx.pC(new byte[]{53, 17, 0, 32, 49, 2, 8, 10, 6, 2, 43, 47, 12, 8}, 1, 84)};
      RW = ckx.pC(new byte[]{105, 2, 17, 4, 2, 29, 31}, 1, 14);
   }

   private class Av implements IDSandboxHooks {
      @Override
      public Class loadClass(String var1) throws DexDecEvalSandboxExecutionException {
         if (!bhg.this.ck.isEmpty()) {
            for (int var3 : bhg.this.ck) {
               byte[] var4 = bhg.this.rl.fI().kS(var3);
               if (var4 != null) {
                  bhg.this.pC(var4);
               }
            }

            bhg.this.ck.clear();
         }

         return null;
      }

      @Override
      public Wrapper newInstance(long var1, String var3, String var4, List var5) throws DexDecEvalSandboxExecutionException {
         switch (ckx.kS(var4)) {
            case -1812307848:
               bhg.this.pC(var5, 0);
               break;
            case -1416508139:
               bhg.this.pC(var5, 0);
               break;
            case -999752482:
            case -877117263:
               bhg.this.pC(var5, 0);
               break;
            case -833629172:
            case 828593573:
               String var17 = (String)var5.get(0);
               bhg.this.A(new File(var17));
               break;
            case -105607493:
               bhg.this.kS(new File((String)var5.get(0), (String)var5.get(1)));
               bhg.this.pC(var5, 0);
               break;
            case -29468192:
               bhg.this.pC(var5, 0);
               break;
            case 175910812:
               String var16 = (String)var5.get(0);

               for (String var29 : var16.split(":")) {
                  File var11 = bhg.this.kS(new File(var29));
                  bhg.this.pC(var11);
               }
               break;
            case 559727105:
               ByteBuffer var15 = (ByteBuffer)var5.get(0);
               bhg.this.pC(var15);
               break;
            case 770005835:
               bhg.this.pC(var5, 0);
               break;
            case 906692029:
            case 1576832637:
               File var14 = (File)var5.get(0);
               bhg.this.A(var14);
               break;
            case 936210013:
            case 1275043432:
               bhg.this.pC(var5, 0);
               break;
            case 971761922:
            case 1442903201:
               ByteBuffer[] var13 = (ByteBuffer[])var5.get(0);

               for (ByteBuffer var28 : var13) {
                  bhg.this.pC(var28);
               }
               break;
            case 1725683616:
            case 1877271014:
               ByteBuffer[] var12 = (ByteBuffer[])var5.get(0);

               for (ByteBuffer var27 : var12) {
                  if (var27 != null) {
                     bhg.this.pC(var27);
                  }
               }
               break;
            case 1734388911:
               URI var6 = (URI)var5.get(0);
               String var7 = var6.toString();
               if (var7.startsWith("file://")) {
                  String var8 = var7.substring(7);
                  String var9 = bhg.this.kS(var8);
                  if (var9 != null) {
                     URI var10 = URI.create("file://" + var9);
                     var5.set(0, var10);
                  }
               }
               break;
            case 1971570765:
               bhg.this.pC(var5, 0);
         }

         return null;
      }

      @Override
      public Wrapper getField(long var1, String var3, String var4, Object var5) {
         switch (ckx.kS(var4)) {
            case -2102237115:
               return Wrapper.wrap("/");
            case -1849657712:
               return Wrapper.wrap('/');
            case -1409371876:
            case -1162579909:
               return Wrapper.wrap(new String[]{bhg.pC.toString()});
            case -1299541426:
               return Wrapper.wrap(new String[0]);
            case -955909561:
            case 1513990710:
               return Wrapper.wrap(bhg.pC.toString());
            case -630597275:
            case 2114079683:
               int var13 = System.identityHashCode(var5);
               int var14 = (Integer)bhg.this.Pe.computeIfAbsent(var13, var1x -> bhg.this.Pe.size());
               long var8 = bhg.this.rl.fI().A(var14);
               long[] var10 = new long[]{0L, var8};
               return Wrapper.wrap(var10);
            case -581505709:
               return Wrapper.wrap(bhg.kS);
            case -371725219:
               return Wrapper.wrap(33);
            case -179451200:
               return Wrapper.wrap(false);
            case 82640088:
               try {
                  if (var5 == bhg.this.Ab.getSandboxClassLoader()) {
                     var5 = bhg.this.NS;
                  }

                  Object var12 = var5.getClass().getField(ckx.pC(new byte[]{-16, 17, 21, 28, 36, 37, 26, 7}, 1, 128)).get(var5);
                  return Wrapper.wrap(var12);
               } catch (Exception var11) {
               }
            default:
               return null;
            case 456185105:
               byte var6 = 0;
               long var7 = bhg.this.rl.fI().pC(var6);
               return Wrapper.wrap(var7);
            case 879178136:
               return Wrapper.wrap(null);
            case 1014093460:
               return Wrapper.wrap(bhg.wS);
         }
      }

      @Override
      public Boolean setField(long var1, String var3, String var4, Object var5, Object[] var6) {
         if (var4.endsWith(
            ckx.pC(
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
                  80,
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
               24
            )
         )) {
            Object[] var11 = new Object[0];
            return null;
         } else {
            switch (ckx.kS(var4)) {
               case -2015283300:
                  Object[] var10 = new Object[]{var6[0]};
                  return null;
               case -1619593404:
                  Object[] var9 = new Object[]{var6[0]};
                  return null;
               case 879178136:
                  Object[] var8 = new Object[]{var6[0]};
                  return true;
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
         switch (ckx.kS(var4)) {
            case -2146531055:
               long var115 = 0L;
               long var173 = 0L;
               boolean var189 = false;

               try {
                  FileLock var197 = ((FileChannel)var5).lock(var115, var173, var189);
                  return Wrapper.wrap(var197);
               } catch (IOException var29) {
                  throw new DexDecEvalSandboxExecutionException(var29);
               }
            case -2128637108:
               int var114 = (Integer)var6.get(0);
               int var154 = (Integer)var6.get(1);
               int var172;
               if (var114 == var154) {
                  var172 = var114;
               } else if (var114 == 0 || var154 == 0) {
                  var172 = 0;
               } else if (var114 == -3 || var154 == -3) {
                  var172 = -3;
               } else if (var114 != -2 && var154 != -2) {
                  var172 = -1;
               } else {
                  var172 = -2;
               }

               return Wrapper.wrap(var172);
            case -2093031467:
            case -1047024949:
            case -425070165:
            case 1939751571:
               Object var113 = bhg.this.UT(
                  ckx.pC(
                     new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 23, 79, 70, 23, 76, 78, 69, 23, 75, 86, 95, 14, 115, 67, 65, 80, 88, 109, 46, 28, 0, 4, 9, 23},
                     2,
                     45
                  )
               );
               return Wrapper.wrap(var113);
            case -2088301382:
            case -1936802804:
               String var112 = (String)var6.get(0);
               Object[] var207 = new Object[]{var112};
               String var153 = bhg.this.kS(var112);
               if (var153 != null) {
                  bhg.this.pC(var112, new File(var153));
               }
               break;
            case -2061638181:
               return Wrapper.wrap(null);
            case -2036523909:
            case -1965212556:
            case -1377251652:
            case -688057012:
            case 1314245546:
               Object[] var206 = new Object[]{var6.get(0)};
               break;
            case -2031926764:
               return Wrapper.wrap(bhg.this.wS());
            case -1986661425:
            case -1841955043:
               if ((Class)var5 == bhg.this.Ab.getSandboxClassLoader().getClass()
                  && ckx.pC(ckx.pC(new byte[]{-117, 17, 21, 28, 36, 37, 26, 7}, 1, 251), (String)var6.get(0))) {
                  Object[] var205 = new Object[0];
               }
               break;
            case -1820388399:
            case 847643324:
               long var111 = (3600000000000L + System.nanoTime()) / 1000000L;
               return Wrapper.wrap(var111);
            case -1779652544:
               return Wrapper.wrap(null);
            case -1756995395:
            case -488269789:
            case 898564948:
               File var110 = new File(bhg.this.UW, bhg.this.hZ);
               if (!var110.exists()) {
                  var110.mkdirs();
               }

               return Wrapper.wrap(new File[]{var110});
            case -1737602261:
               long var109 = System.nanoTime() / 1000000L / 2L;
               return Wrapper.wrap(var109);
            case -1724804910:
            case -734012392:
               return Wrapper.wrap(bhg.this.FE);
            case -1712406020:
            case -1007002235:
               String var108 = bhg.this.LM;
               return Wrapper.wrap(var108);
            case -1695331608:
            case -62178274:
            case 1225355020:
               return null;
            case -1605690625:
               long var107 = System.nanoTime() / 1000000L;
               return Wrapper.wrap(var107);
            case -1594025097:
            case -606749359:
            case 877099975:
               return Wrapper.wrap(bhg.this.gp);
            case -1593528056:
            case -1092273275:
            case 824903017:
               File var106 = new File(bhg.this.UW, bhg.this.hZ);
               if (!var106.exists()) {
                  var106.mkdirs();
               }

               return Wrapper.wrap(var106);
            case -1575261098:
            case 1272988761:
            case 1621299812:
               String var105 = (String)var6.get(0);
               Object var152 = bhg.this.vP.get(var105);
               if (var152 == null) {
                  try {
                     URLClassLoader var171 = bhg.this.Ab.getSandboxClassLoader();
                     Class var182 = var171.loadClass(
                        ckx.pC(
                           new byte[]{
                              -85, 15, 10, 22, 29, 6, 13, 74, 79, 17, 0, 94, 125, 59, 9, 19, 23, 1, 52, 34, 23, 3, 3, 23, 23, 11, 13, 6, 22, 58, 36, 29, 28
                           },
                           1,
                           202
                        )
                     );
                     var152 = var182.getConstructor().newInstance();
                     bhg.this.vP.put(var105, var152);
                  } catch (Exception var28) {
                     throw new DexDecEvalSandboxExecutionException(var28);
                  }
               }

               return Wrapper.wrap(var152);
            case -1549123739:
            case -1534067014:
            case 644629666:
               String var104 = (String)var6.get(0);
               File var151 = new File(bhg.this.UW, bhg.this.os + ckx.pC(new byte[]{44, 73, 15, 5, 9, 22}, 1, 3));
               if (!var151.exists()) {
                  var151.mkdirs();
               }

               File var170 = new File(var151, var104);
               return Wrapper.wrap(var170);
            case -1453311859:
               ArrayList var103 = new ArrayList();
               return Wrapper.wrap(var103);
            case -1376434574:
               ProcessBuilder var102 = (ProcessBuilder)var5;
               List var150 = var102.command();
               return bhg.this.pC(var150);
            case -1350886070:
               String var101 = (String)var6.get(0);
               List var149 = Arrays.asList(var101.split("\\s+"));
               return bhg.this.pC(var149);
            case -1280323317:
            case -452023474:
               String var100 = (String)var6.get(0);
               Object[] var10000 = new Object[]{var100};
               break;
            case -1259485068:
               List var99 = (List)var6.get(0);

               for (File var168 : var99) {
                  int var180 = IO.getFirstIntLE(var168.getPath());
                  if (var180 == 175662436) {
                     bhg.this.A(var168);
                  } else if (var180 == 67324752) {
                     try (
                        ZipBrowser var187 = new ZipBrowser(var168);
                        InputStream var195 = var187.getEntryStream(ckx.pC(new byte[]{32, 3, 17, 10, 1, 12, 20, 70, 16, 69, 80}, 2, 91));
                     ) {
                        byte[] var202 = IO.readInputStream(var195);
                        bhg.this.pC(var202);
                     } catch (IOException var41) {
                     }
                  }
               }

               try {
                  URLClassLoader var148 = bhg.this.Ab.getSandboxClassLoader();
                  Class var169 = var148.loadClass(
                     ckx.pC(new byte[]{39, 14, 28, 15, 27, 2, 73, 27, 13, 83, 92, 6, 68, 14, 117, 92, 65, 117, 69, 76, 87}, 2, 136)
                  );
                  Constructor var181 = var169.getConstructor(String.class);
                  Class var188 = var148.loadClass(
                     ckx.pC(
                        new byte[]{
                           -113, 5, 13, 26, 31, 2, 69, 93, 10, 10, 7, 17, 8, 67, 106, 33, 29, 40, 49, 21, 28, 36, 37, 26, 7, 80, 97, 41, 9, 8, 8, 11, 26
                        },
                        1,
                        235
                     )
                  );
                  Constructor var196 = var188.getConstructor(File.class, Boolean.class, var169, boolean.class);
                  Object var203 = Array.newInstance(var188, var99.size());
                  int var204 = 0;

                  for (File var16 : var99) {
                     Object var17 = var181.newInstance(bhg.this.wS(var16));
                     Object var18 = var196.newInstance(var16, null, var17, false);
                     Array.set(var203, var204, var18);
                     var204++;
                  }

                  return Wrapper.wrap(var203);
               } catch (Exception var38) {
                  return null;
               }
            case -1228609088:
            case -513911807:
            case -176640088:
               File var98 = new File(bhg.this.UW, bhg.this.Cu + ckx.pC(new byte[]{108, 12, 17, 26, 26, 12}, 2, 29));
               if (!var98.exists()) {
                  var98.mkdirs();
               }

               return Wrapper.wrap(var98);
            case -1223121674:
               return Wrapper.wrap(null);
            case -1221764277:
            case -639099770:
            case 1466331619:
               return Wrapper.wrap(bhg.this.LM);
            case -1139591185:
            case -456127112:
            case 2027658666:
               Object var97 = bhg.this.UT(
                  ckx.pC(
                     new byte[]{64, 15, 10, 22, 29, 6, 13, 74, 77, 12, 1, 26, 17, 11, 26, 90, 109, 44, 1, 26, 17, 11, 26, 38, 55, 22, 28, 3, 26, 19, 23}, 1, 33
                  )
               );
               return Wrapper.wrap(var97);
            case -1139064595:
            case 575404928:
            case 873107103:
               File var96 = new File(bhg.this.UW, bhg.this.os + ckx.pC(new byte[]{108, 12, 31, 29, 23, 54, 4, 9, 23, 72, 77}, 2, 179));
               if (!var96.exists()) {
                  var96.mkdirs();
               }

               return Wrapper.wrap(var96);
            case -1126172715:
            case -282766482:
            case 2087380722:
               File var95 = new File(bhg.this.UW, bhg.this.os + ckx.pC(new byte[]{108, 12, 17, 26, 26, 12}, 2, 241));
               if (!var95.exists()) {
                  var95.mkdirs();
               }

               return Wrapper.wrap(var95);
            case -1036679460:
               String var94 = (String)var6.get(0);
               if (ckx.pC(5, 6647664, -416307977, var94)) {
                  String var146 = ckx.pC(
                     new byte[]{
                        34, 1, 20, 11, 29, 0, 3, 70, 0, 69, 68, 6, 89, 72, 94, 87, 64, 29, 120, 69, 94, 85, 66, 93, 67, 78, 54, 63, 0, 13, 13, 2, 69, 19
                     },
                     2,
                     167
                  );
                  Object var167 = bhg.this.xC.get(var146);
                  if (var167 == null) {
                     var167 = bhg.this.UT(var146);
                     bhg.this.xC.put(var146, var167);
                  }

                  if (var167 != null) {
                     return Wrapper.wrap(var167);
                  }
               }
               break;
            case -1026979224:
            case 1971158807:
               return Wrapper.wrap(bhg.this.hK);
            case -1010614805:
               int var93 = (Integer)var6.get(0);
               int var145 = var93 >> 8 & 0xFF;
               return Wrapper.wrap(var145);
            case -1006881245:
            case -736443888:
            case 622086970:
               String var92 = (String)var6.get(0);
               File var144 = new File(bhg.this.UW, bhg.this.os + ckx.pC(new byte[]{0, 78, 17, 0, 47}, 1, 47) + var92);
               if (!var144.exists()) {
                  var144.mkdirs();
               }

               return Wrapper.wrap(var144);
            case -963875898:
               if (var5 == bhg.this.ld) {
                  ApkManifestHelper.ApplicationDescription var91 = new ApkManifestHelper(bhg.this.Sc).getApplicationDescription();
                  if (var91 != null) {
                     String var143 = (String)var6.get(0);
                     String var166 = (String)var91.getMetadata().get(var143);
                     return Wrapper.wrap(var166);
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
               File var90 = (File)var5;
               if (IO.inFolder(var90, bhg.this.UW)) {
                  String var141 = IO.getRelativePath(var90, bhg.this.UW);
                  var141 = "/" + var141.replace('\\', '/');
                  return Wrapper.wrap(var141);
               }
               break;
            case -853057070:
               if (var5 == bhg.this.ld) {
                  ApkManifestHelper.ApplicationDescription var89 = new ApkManifestHelper(bhg.this.Sc).getApplicationDescription();
                  if (var89 != null) {
                     String var140 = (String)var6.get(0);
                     boolean var165 = var89.getMetadata().containsKey(var140);
                     return Wrapper.wrap(var165);
                  }
               }
               break;
            case -841515190:
               if (Strings.isBlank((String)var6.get(0))) {
                  return Wrapper.wrap(-1);
               }

               return null;
            case -836565104:
               return Wrapper.wrap(bhg.this.getJavaProperties());
            case -832732344:
            case 312676424:
               long var88 = 3600000000000L + System.nanoTime();
               return Wrapper.wrap(var88);
            case -814996805:
               int var87 = (Integer)var6.get(0);
               int var139 = (Integer)var6.get(1);
               int var164 = (Integer)var6.get(2);
               int var179 = 0xFF000000 | var87 << 16 | var139 << 8 | var164;
               return Wrapper.wrap(var179);
            case -802113500:
            case 1568344285:
            case 2071058608:
               return Wrapper.wrap(bhg.this.NS);
            case -779258908:
               String var86 = (String)var6.get(0);
               if (var86.equals(bhg.this.FE)) {
                  return Wrapper.wrap(bhg.this.gp);
               }
               break;
            case -760637487:
               if (bhg.this.Sc.getInput() instanceof FileInput) {
                  String var85 = (String)bhg.this.pC(var5, ckx.pC(new byte[]{34, 31, 27, 56, 1, 26, 2, 28, 36, 65, 92, 11}, 2, 226));
                  if (var85 != null) {
                     File var138 = ((FileInput)bhg.this.Sc.getInput()).getFile();

                     try {
                        Wrapper var201;
                        try (ZipBrowser var163 = new ZipBrowser(var138)) {
                           InputStream var178 = var163.getEntryStream(var85);
                           byte[] var186 = IO.readInputStream(var178);
                           File var194 = bhg.this.kS(new File(bhg.this.EX + "/" + var85));
                           if (!var194.isFile()) {
                              IO.createFile(var194, true);
                              IO.writeFile(var194, var186);
                           }

                           var201 = Wrapper.wrap(new FileInputStream(var194));
                        }

                        return var201;
                     } catch (IOException var45) {
                     }
                  }
               }
               break;
            case -694125512:
            case 172001029:
            case 265782452:
               String var84 = (String)var6.get(0);
               if ("activity".equals(var84)) {
                  Object var137 = bhg.this.UT(
                     ckx.pC(new byte[]{-12, 15, 10, 22, 29, 6, 13, 74, 79, 17, 0, 94, 111, 34, 23, 29, 31, 31, 29, 13, 52, 44, 15, 15, 6, 2, 23}, 1, 149)
                  );
                  if (var137 != null) {
                     return Wrapper.wrap(var137);
                  }
               }

               return Wrapper.wrap(null);
            case -686046401:
            case -252633682:
            case 486193603:
               File var83 = new File(bhg.this.UW, bhg.this.os + ckx.pC(new byte[]{42, 73, 15, 5, 9, 22}, 1, 5));
               if (!var83.exists()) {
                  var83.mkdirs();
               }

               return Wrapper.wrap(var83);
            case -647574041:
               File[] var82 = new File[]{bhg.this.UW};
               return Wrapper.wrap(var82);
            case -569667798:
               long var81 = System.nanoTime();
               return Wrapper.wrap(var81);
            case -554491784:
               File var80 = (File)var5;
               File var136 = (File)var6.get(0);
               if (IO.inFolder(var80, bhg.this.UW) && IO.inFolder(var136, bhg.this.UW)) {
                  boolean var162 = var80.renameTo(var136);
                  return Wrapper.wrap(var162);
               }
               break;
            case -530190971:
               String var79 = (String)var6.get(0);
               if (!var79.startsWith("/")) {
                  break;
               }

               String var135 = var79.substring(1);

               try (ZipFailSafeReader var161 = new ZipFailSafeReader(bhg.this.Sc.getInput().getChannel())) {
                  if (var161.getEntry(var135) != null) {
                     String var177 = ckx.pC(ckx.pC(new byte[]{58, 11, 19, 72, 92, 15, 5, 9, 95}, 1, 80)) + bhg.this.LM + "!/" + var135;
                     return Wrapper.wrap(new URL(var177));
                  }
               } catch (IOException var50) {
                  bhg.ED.catching(var50);
               }
               break;
            case -469121180:
               int var78 = (Integer)var6.get(0);
               String var134 = ((com.pnfsoftware.jeb.corei.parsers.apk.Ws)bhg.this.Sc).pC().getDefaultString(var78);
               return Wrapper.wrap(var134);
            case -422691230:
               String var77 = "L" + (String)var6.get(0) + ";";

               try {
                  Class var133 = bhg.this.getState().loadClass(var77);
                  return Wrapper.wrap(var133);
               } catch (Exception var44) {
                  bhg.ED.catching(var44);
                  break;
               }
            case -340267726:
               File var76 = (File)var5;
               if (IO.inFolder(var76, bhg.this.UW)) {
                  if (var76.isDirectory()) {
                     return Wrapper.wrap(var76.delete());
                  }

                  File var132 = new File(bhg.this.UW, ckx.pC(new byte[]{101, 106, 1, 9, 9, 17, 17, 1}, 1, 75));
                  if (!var132.exists()) {
                     var132.mkdir();
                  }

                  File var160 = new File(var132, var76.getName());
                  return Wrapper.wrap(var76.renameTo(var160));
               }
               break;
            case -324555365:
               String var75 = (String)var6.get(0);
               if (var75.equals(bhg.this.LM)) {
                  Object var131 = bhg.this.UT(
                     ckx.pC(
                        new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 23, 79, 70, 23, 76, 78, 69, 23, 73, 94, 2, 112, 83, 83, 89, 84, 75, 69, 6, 28, 7, 12}, 2, 78
                     )
                  );
                  bhg.this.pC(var131, ckx.pC(new byte[]{86, 17, 0, 28, 5, 10, 2, 21, 29, 6, 1, 39, 39, 8, 9}, 1, 55), bhg.this.gp);
                  return Wrapper.wrap(var131);
               }
               break;
            case -286084764:
            case 1549383917:
               return Wrapper.wrap(false);
            case -139657970:
               long var74 = System.currentTimeMillis();
               return Wrapper.wrap(var74);
            case -109422525:
            case 1000147492:
            case 1870773074:
               return Wrapper.wrap(bhg.this.ys);
            case -17774301:
               String var72 = (String)var6.get(0);
               if (var72.startsWith("/")) {
                  var72 = var72.substring(1);
                  return bhg.this.wS(var72);
               }
               break;
            case 2941652:
            case 762628043:
            case 1596848825:
               return null;
            case 74405431:
               return Wrapper.wrap(bhg.this.Er);
            case 78138881:
               if (bhg.this.Sc.getInput() instanceof FileInput var71) {
                  String var129 = (String)var6.get(0);
                  File var159 = var71.getFile();
                  var129 = ckx.pC(new byte[]{117, 18, 0, 22, 17, 7}, 1, 20) + "/" + var129 + "/";

                  try {
                     Wrapper var200;
                     try (ZipBrowser var176 = new ZipBrowser(var159)) {
                        ArrayList var184 = new ArrayList();

                        for (String var199 : var176.getEntries().keySet()) {
                           if (var199.startsWith(var129)) {
                              String var14 = var199.substring(var129.length());
                              if (var14.indexOf(47) < 0) {
                                 var184.add(var14);
                              }
                           }
                        }

                        Object[] var193 = var184.toArray(new String[var184.size()]);
                        var200 = Wrapper.wrap(var193);
                     }

                     return var200;
                  } catch (IOException var37) {
                     throw new DexDecEvalSandboxExecutionException(new InvocationTargetException(var37));
                  }
               }
               break;
            case 88896433:
            case 1181807553:
               if (var6.size() == 3) {
                  File var69 = (File)var6.get(2);
                  if (var69 != null) {
                     break;
                  }
               }

               File var70 = new File(bhg.this.UW, bhg.this.os + ckx.pC(new byte[]{108, 12, 17, 26, 26, 12}, 2, 146));
               if (!var70.exists()) {
                  var70.mkdirs();
               }

               String var127 = (String)var6.get(0);
               String var158 = (String)var6.get(1);

               try {
                  File var175 = File.createTempFile(var127, var158, var70);
                  return Wrapper.wrap(var175);
               } catch (IOException var46) {
                  break;
               }
            case 111705615:
               int var68 = (Integer)var6.get(0);
               int var126 = var68 & 0xFF;
               return Wrapper.wrap(var126);
            case 134764335:
            case 709051306:
               if (bhg.this.Sc.getInput() instanceof FileInput var67) {
                  File var125 = var67.getFile();
                  String var157 = (String)var6.get(0);
                  String var174 = ckx.pC(new byte[]{34, 28, 3, 28, 6, 26}, 2, 30) + "/" + var157;

                  byte[] var183;
                  try (
                     ZipBrowser var190 = new ZipBrowser(var125);
                     InputStream var198 = var190.getEntryStream(var174);
                  ) {
                     var183 = IO.readInputStream(var198);
                  } catch (IOException var35) {
                     try (ZipFailSafeReader var13 = new ZipFailSafeReader(var125, 0, true, false, false)) {
                        var183 = var13.readData(var174).getUncompressedData();
                     } catch (IOException var23) {
                        throw new DexDecEvalSandboxExecutionException(new InvocationTargetException(var23));
                     }
                  }

                  ByteArrayInputStream var191 = new ByteArrayInputStream(var183);
                  return Wrapper.wrap(var191);
               }
               break;
            case 140561163:
               int var66 = (Integer)var6.get(0);
               int var123 = var66 >> 16 & 0xFF;
               return Wrapper.wrap(var123);
            case 199965973:
               Object var65 = bhg.this.UT(ckx.pC(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 2, 73, 77, 20, 7, 119, 88, 87, 93, 92, 91}, 2, 22));
               return Wrapper.wrap(var65);
            case 358646882:
               if (Strings.isBlank((String)var6.get(0))) {
                  return Wrapper.wrap(0);
               }

               return null;
            case 410077869:
               return Wrapper.wrap(null);
            case 421591354:
               return Wrapper.wrap(bhg.this.ys);
            case 504643480:
            case 597015138:
            case 783671992:
               Object var64 = bhg.this.UT("android.content.res.Resources");
               return Wrapper.wrap(var64);
            case 558029666:
               long var63 = (Long)var6.get(0);
               long var156 = 0L;
               boolean var11 = (Boolean)var6.get(2);

               try {
                  FileLock var12 = ((FileChannel)var5).lock(var63, var156, var11);
                  return Wrapper.wrap(var12);
               } catch (IOException var21) {
                  throw new DexDecEvalSandboxExecutionException(var21);
               }
            case 560641042:
               String var62 = "L" + ((String)var6.get(0)).replace('.', '/') + ";";

               try {
                  Class var122 = bhg.this.getState().loadClass(var62);
                  return Wrapper.wrap(var122);
               } catch (Exception var43) {
                  bhg.ED.catching(var43);
               }
            case 566204235:
            case 733911727:
            default:
               break;
            case 743738796:
               String var61 = (String)var6.get(0);
               bhg.this.pC(bhg.this.kS(new File(var61)));
               break;
            case 777284998:
               String var60 = (String)var6.get(0);
               if (!var60.startsWith("/")) {
                  return bhg.this.wS(var60);
               }
               break;
            case 795794457:
            case 880103391:
            case 1425473603:
               return Wrapper.wrap(bhg.this.FE);
            case 855197106:
               String var59 = (String)var6.get(0);

               try (ZipFailSafeReader var121 = new ZipFailSafeReader(bhg.this.Sc.getInput().getChannel())) {
                  if (var121.getEntry(var59) != null) {
                     String var155 = ckx.pC(ckx.pC(new byte[]{86, 11, 19, 72, 92, 15, 5, 9, 95}, 1, 60)) + bhg.this.LM + "!/" + var59;
                     return Wrapper.wrap(new URL(var155));
                  }
               } catch (IOException var48) {
                  bhg.ED.catching(var48);
               }
               break;
            case 967372291:
               float var58 = (Float)var6.get(0);
               float var120 = (Float)var6.get(1);
               float var9 = (float)Math.hypot(var58, var120);
               return Wrapper.wrap(var9);
            case 1070486385:
               String var57 = (String)var6.get(0);
               String var119 = bhg.this.getJavaProperties().getProperty(var57);
               if (var119 == null) {
                  var119 = (String)var6.get(1);
               }

               return Wrapper.wrap(var119);
            case 1264513105:
               String var56 = (String)var6.get(0);
               if (var56 != null) {
                  String var118 = bhg.this.sO + "/lib" + var56 + ".so";
                  return Wrapper.wrap(var118);
               }
               break;
            case 1305482006:
               String var55 = (String)var6.get(0);
               if (var55.equals("PATH")) {
                  String var117 = ckx.pC(
                     new byte[]{
                        -60,
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
                     235
                  );
                  return Wrapper.wrap(var117);
               }
               break;
            case 1473156074:
            case 2044573158:
            case 2057857915:
               return Wrapper.wrap(bhg.this.E);
            case 1491814536:
               return Wrapper.wrap(false);
            case 1501799209:
               return Wrapper.wrap(0);
            case 1842063898:
               String var54 = (String)var6.get(0);
               String var116 = bhg.this.getJavaProperties().getProperty(var54);
               return Wrapper.wrap(var116);
            case 1877396972:
               return Wrapper.wrap(2);
            case 1911166531:
               String var53 = (String)var6.get(1);
               if (ckx.pC(10, 6580577, 189448852, var53)) {
                  String var8 = ckx.A("27637ac3f5a4198e");
                  return Wrapper.wrap(var8);
               }
               break;
            case 1929818550:
               Object var52 = bhg.this.UT(
                  ckx.pC(
                     new byte[]{83, 15, 10, 22, 29, 6, 13, 74, 77, 12, 1, 26, 17, 11, 26, 90, 92, 23, 22, 93, 111, 50, 0, 22, 17, 57, 44, 15, 15, 6, 2, 23},
                     1,
                     50
                  )
               );
               return Wrapper.wrap(var52);
            case 2028113802:
               Object var51 = bhg.this.UT(ckx.pC(new byte[]{34, 1, 20, 11, 29, 0, 3, 70, 2, 73, 77, 20, 7, 118, 88, 92, 78}, 2, 106));
               return Wrapper.wrap(var51);
            case 2101906358:
               Object var7 = bhg.this.UT(
                  ckx.pC(
                     new byte[]{
                        6, 15, 10, 22, 29, 6, 13, 74, 88, 31, 12, 18, 89, 121, 62, 7, 10, 11, 24, 62, 39, 29, 22, 17, 7, 48, 44, 1, 26, 6, 29, 3, 0, 9, 23
                     },
                     1,
                     103
                  )
               );
               return Wrapper.wrap(var7);
         }

         return null;
      }
   }
}
