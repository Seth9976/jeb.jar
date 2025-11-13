package com.pnfsoftware.jeb.corei.parsers.apk;

import com.android.apksig.ApkVerifier;
import com.android.apksig.ApkVerifier.Builder;
import com.android.apksig.ApkVerifier.IssueWithParams;
import com.android.apksig.ApkVerifier.Result;
import com.android.apksig.ApkVerifier.Result.V1SchemeSignerInfo;
import com.android.apksig.ApkVerifier.Result.V2SchemeSignerInfo;
import com.android.apksig.ApkVerifier.Result.V3SchemeSignerInfo;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.ClientNotification;
import com.pnfsoftware.jeb.core.events.ClientNotificationLevel;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.QuestionNotificationYesNo;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.ICertificateUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.APKSigSchemeV2Block;
import com.pnfsoftware.jeb.core.units.code.android.APKSigSchemeV3Block;
import com.pnfsoftware.jeb.core.units.code.android.ApkManifestHelper;
import com.pnfsoftware.jeb.core.units.code.android.ApkStringResHelper;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDynamic;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.IGenericUnpacker;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.impl.FileContainerUnit;
import com.pnfsoftware.jeb.corei.parsers.dex.vi;
import com.pnfsoftware.jeb.util.encoding.zip.GenericZipEntry;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Ser
@SerVersion(3)
public class Ws extends AbstractBinaryUnit implements IArchiveUnit, IApkUnit {
   private static final ILogger kS = GlobalLog.getLogger(Ws.class);
   @SerTransient
   File pC;
   @SerTransient
   boolean A;
   @SerTransient
   private byte[] wS;
   @SerTransient
   private Object UT;
   @SerTransient
   private Boolean E;
   @SerId(1)
   private int sY;
   @SerId(2)
   private APKSigSchemeV2Block ys;
   @SerId(value = 3, version = 1)
   private int ld;
   @SerId(value = 4, version = 2)
   private String gp;
   @SerId(value = 5, version = 3)
   private APKSigSchemeV3Block oT;
   @SerId(6)
   private IDexDynamic fI;
   @SerId(7)
   private String WR;
   @SerId(8)
   private APKSigSchemeV3Block NS;
   @SerTransient
   private ApkStringResHelper vP;
   @SerTransient
   private volatile String xC;
   @SerTransient
   private volatile boolean ED;
   @SerTransient
   private boolean Sc;
   @SerTransient
   private boolean ah;
   @SerTransient
   private String eP;
   @SerTransient
   private String UO;
   @SerTransient
   private String Ab;
   @SerTransient
   private String rl;
   @SerTransient
   private List z;
   @SerTransient
   private List Ek;
   @SerTransient
   private List hK;
   @SerTransient
   private List Er;
   @SerTransient
   private List FE;

   public Ws(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/zip", var2, "apk", var1, var3, var4, var5);
   }

   public ApkStringResHelper pC() {
      if (this.vP == null) {
         ArrayList var1 = new ArrayList();
         if (this.WR != null) {
            for (String var5 : this.WR.split(",")) {
               var5 = var5.trim();
               if (!var5.isEmpty()) {
                  var1.add(var5);
               }
            }
         }

         this.vP = new ApkStringResHelper(this, var1);
      }

      return this.vP;
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         int var1 = this.getPropertyManager().getInteger("ArscRestructuringMode");
         boolean var2 = this.getPropertyManager().getBoolean("GenerateAapt2Output");
         boolean var3 = this.getPropertyManager().getBoolean("ProcessManifest");
         boolean var4 = this.getPropertyManager().getBoolean("ProcessResources");
         boolean var5 = this.getPropertyManager().getBoolean("ProcessCertificates");
         boolean var6 = this.getPropertyManager().getBoolean("ProcessBytecode");
         boolean var7 = this.getPropertyManager().getBoolean("ProcessLibraries");
         boolean var8 = this.getPropertyManager().getBoolean("ProcessAssets");
         this.WR = this.getPropertyManager().getString("PreferredLocales");
         File var9 = null;
         Sv var10 = null;
         this.UT = new Object();

         try {
            if (this.pC == null) {
               if (this.getInput() instanceof FileInput) {
                  this.pC = ((FileInput)this.getInput()).getFile();
                  this.A = false;
               } else {
                  try (InputStream var35 = this.getInput().getStream()) {
                     byte[] var11 = IO.readInputStream(var35);
                     this.pC = File.createTempFile("jebTempFile", null, null);
                     this.A = true;
                     IO.writeFile(this.pC, var11, false);
                  } catch (IOException var31) {
                     kS.catching(var31);
                     return false;
                  }
               }
            }

            String var36 = this.getPropertyManager().getString("FrameworksDirectory");
            File var34;
            if (Strings.isBlank(var36)) {
               var34 = new File(System.getProperty("user.home"), ".jeb-android-frameworks");
            } else {
               var34 = new File(var36);
            }

            var9 = IO.createTempFolder(this.pC.getName() + "-EXTRACTED");
            var10 = new Sv(this.pC, this);
            var10.pC(var2);
            var10.pC(var1);
            IO.createDirectory(var34.getPath());
            var10.pC(var34);
            var10.A(var9);
            if (var4) {
               var3 = true;
            }

            if (var3) {
               GenericZipEntry var37 = var10.getEntry("AndroidManifest.xml");
               if (var37 == null || var37.getSize() == 0L) {
                  var3 = false;
                  var4 = false;
                  this.addNotification(new UnitNotification(NotificationType.WARNING, S.L("Empty AndroidManifest.xml")));
               }
            }

            if (var3 || var4) {
               IUnit var38;
               if (!var4) {
                  if (!var10.A(1)) {
                     this.addNotification(new UnitNotification(NotificationType.ERROR, S.L("An error occurred while decoding the APK")));
                     this.ld = 1;
                  }

                  var38 = this.pC(var10, var9);
               } else {
                  if (!var10.A(2)) {
                     this.addNotification(new UnitNotification(NotificationType.ERROR, S.L("An error occurred while decoding the APK")));
                     this.ld = 1;
                  }

                  if (var10.pC()) {
                     String var14 = var10.A();
                     if (!Strings.isBlank(var14)) {
                        this.gp = var14;
                     }
                  }

                  var38 = this.pC(var10, var9);
               }

               if (var38 != null) {
                  this.addChild(var38);
               }
            }

            if (var5) {
               this.A(var10);
            }

            if (var6) {
               this.pC(var10);
            }

            if (var4) {
               File var39 = new File(var9, "res");
               if (var39.exists()) {
                  FileContainerUnit var43 = new FileContainerUnit(var39, "Resources", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
                  this.addChild(var43);
                  int var15 = IO.listFiles(var39).size();
                  int var16 = this.getPropertyManager().getInteger("DisableResourcesProcessingThreshold");
                  boolean var17 = this.pC(var15, var16, "resources");
                  var43.process(var17);
               }
            }

            if (var8) {
               File var40;
               if (!var4) {
                  var40 = var10.kS("assets");
               } else {
                  var40 = new File(var9, "assets");
               }

               if (var40.exists()) {
                  FileContainerUnit var44 = new FileContainerUnit(var40, "Assets", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
                  this.addChild(var44);
                  int var47 = IO.listFiles(var40).size();
                  int var49 = this.getPropertyManager().getInteger("DisableAssetsProcessingThreshold");
                  boolean var51 = this.pC(var47, var49, "assets");
                  var44.process(var51);
               }
            }

            if (var7) {
               File var41;
               if (!var4) {
                  var41 = var10.kS("lib");
                  if (!var41.exists()) {
                     var41 = var10.kS("libs");
                  }
               } else {
                  var41 = new File(var9, "lib");
                  if (!var41.exists()) {
                     var41 = new File(var9, "libs");
                  }
               }

               if (var41.exists()) {
                  FileContainerUnit var45 = new FileContainerUnit(var41, "Libraries", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
                  this.addChild(var45);
                  var45.process();
               }
            }

            String var42 = this.getPackageName();
            Object[] var10000 = new Object[]{var42};
            if (var42 != null) {
               int var46 = 0;
               int var48 = 0;

               for (int var50 = 0; var50 < var42.length(); var50++) {
                  char var52 = var42.charAt(var50);
                  if (var52 == '.') {
                     var46++;
                  } else if (Character.isWhitespace(var52)) {
                     var48++;
                  }
               }

               if (var46 + var48 == var42.length()) {
                  JebCoreService.silentExcept(new RuntimeException("Pseudo-empty package name"));
               }
            }

            if (!Strings.isBlank(var42)) {
               this.setRealName(this.getName());
               this.setName(var42);
            }

            this.logInfo(false, S.L("Analysis completed"));
         } catch (Exception var32) {
            kS.catching(var32);
            if (!this.A()) {
               JebCoreService.notifySilentExceptionToClient(var32);
            }

            return false;
         } finally {
            if (var10 != null) {
               try {
                  var10.close();
               } catch (IOException var28) {
               }
            }

            if (this.pC != null && this.A) {
               this.pC.deleteOnExit();
            }

            if (var9 != null) {
               IO.deleteDirectory(var9);
            }
         }

         this.setProcessed(true);
         return true;
      }
   }

   private boolean pC(int var1, int var2, String var3) {
      if (var2 > 0 && var1 > var2) {
         synchronized (this.UT) {
            if (this.E != null) {
               return this.E;
            } else {
               boolean var10000;
               try {
                  String var5 = Strings.ff(
                     S.L(
                        "A large number of %s (%d) was found for %s.\nProcessing them at launch may take a while and consume a lot of memory.\n\n(The current threshold for this warning is %d and may be configured or disabled in the settings.)\n\nProcess all the %s right now?"
                     ),
                     var3,
                     var1,
                     this.getName(),
                     var2,
                     var3
                  );
                  QuestionNotificationYesNo var6 = new QuestionNotificationYesNo(var5, false, true);
                  JebCoreService.getInstance().notifyListeners(new JebEvent(J.Notification, var6));
                  Boolean var7 = (Boolean)var6.getResponse();
                  if (var6.isDoNotShowAnymoreResponse()) {
                     this.E = var7;
                  }

                  var10000 = var7;
               } catch (JebException var9) {
                  return true;
               }

               return var10000;
            }
         }
      } else {
         return true;
      }
   }

   private void pC(Sv var1) {
      String var2 = null;
      IUnit var3 = null;
      LinkedHashMap var4 = null;
      String var5 = "Bytecode";
      boolean var6 = false;
      byte[] var7 = null;
      if (var1.pC("classes.dex")) {
         var7 = var1.A("classes.dex");
      }

      if (var7 != null) {
         var2 = this.pC(var7);
         if (var2 == null) {
            this.logError(true, S.L("Unrecognized format for classes.dex"));
            String var8 = S.L("A classes.dex file is present in this APK but its format is unknown!");
            this.notifyListeners(new JebEvent(J.Notification, new ClientNotification(var8, ClientNotificationLevel.ERROR)));
         } else if (var2 == "elf") {
            this.logWarn(true, S.L("Optimized code in DEX (OAT file) - consider extracting DEX files and re-process"));
            String var16 = S.L(
               "The code of this APK has been pre-compiled to OAT.\nConsider extracting dex file(s) using third-party tools (eg, lief, vdexExtractor, dextra) and re-process."
            );
            this.notifyListeners(new JebEvent(J.Notification, new ClientNotification(var16, ClientNotificationLevel.WARNING)));
         }

         var4 = new LinkedHashMap();
         int var17 = 2;

         while (true) {
            String var9 = Strings.ff("classes%d.dex", var17);
            if (!var1.pC(var9)) {
               if (!var4.isEmpty()) {
                  var9 = Strings.ff(S.L("Multi-DEX application (%d)"), 1 + var4.size());
                  this.addNotification(new UnitNotification(NotificationType.INFO, var9));
               }
               break;
            }

            byte[] var10 = var1.A(var9);
            if (var10 == null) {
               this.logError(true, S.L("Cannot read DEX file: %s"), var9);
               var6 = true;
            } else if (!Strings.equals(this.pC(var10), var2)) {
               String var11 = Strings.ff(S.L("Multi-DEX application (%d)"), 1 + var4.size());
               this.addNotification(new UnitNotification(NotificationType.INFO, var11));
               this.logWarn(true, S.L("Illegal dex entry in multi-dex APK: skipping %s"), var9);
            } else {
               var4.put(var9, var10);
            }

            var17++;
         }
      } else if (!this.A()) {
         this.addNotification(
            new UnitNotification(
               NotificationType.INFO, S.L("This APK file does not contain bytecode, or the bytecode cannot be read; a placeholder file will be used")
            )
         );
         if (this.wS == null) {
            this.wS = AssetManager.getAssetBytes("Placeholder.dex");
            if (this.wS == null) {
               throw new RuntimeException("The placeholder DEX file was not loaded");
            }
         }

         var7 = this.wS;
         var5 = var5 + " (Placeholder)";
         var2 = this.pC(var7);
      }

      boolean var18 = false;
      if (var4 != null && !var4.isEmpty() && !var6) {
         if (!this.getPropertyManager().getBoolean("MergeMultiDex")) {
            this.logInfo(
               true, S.L("JEB will not attempt to merge the multiple DEX files. Please analyze additional classesX.dex files in separate JEB instances")
            );
         } else {
            this.logInfo(true, S.L("Merging multiple DEX files (%d)"), 1 + var4.size());
            Object var20 = null;
            if (var20 != null) {
               this.logInfo(
                  true,
                  S.L(
                     "Merger succeeded. The resulting DEX file will replace the original classes.dex file in the JDB. The original classes.dex file will be stored in the JDB as a \"classes1.dex\" entry"
                  )
               );
               var4 = null;
               var7 = (byte[])var20;
            } else {
               var3 = this.getUnitProcessor().process(var5, new BytesInput(var7), this, var2, false, true);
               if (var3 instanceof vi) {
                  for (String var24 : var4.keySet()) {
                     byte[] var12 = (byte[])var4.get(var24);
                     ((vi)var3).pC((IInput)(new BytesInput(var12, 0, var12.length, var24)));
                  }
               }

               var18 = true;
            }
         }
      }

      if (var3 == null) {
         if (var7 == null) {
            throw new RuntimeException("no DEX Data found");
         }

         var3 = this.getUnitProcessor().process(var5, new BytesInput(var7), this, var2);
         var3.setRealName("classes.dex");
      }

      try {
         var3.process();
      } catch (RuntimeException var15) {
         if (var3 instanceof vi) {
            if (((vi)var3).ED()) {
               kS.catching(var15);
               this.notifyListeners(
                  new JebEvent(
                     J.Notification,
                     new ClientNotification(
                        S.L(
                           "An error occurred while merging/processing the multi-DEX APK. We recommend you to disable the DEX merging feature in this project, and reload the artifact."
                        ),
                        ClientNotificationLevel.ERROR
                     )
                  )
               );
               var3 = null;
            } else {
               JebCoreService.notifyExceptionToClient(var15);
            }
         }
      }

      if (var18 && var3 != null) {
         var4 = null;
         this.logTrace(S.L("DEX merger was successful and produced a virtual DEX unit"));
      }

      if (var6) {
         this.logWarn(true, S.L("DEX merger cannot be done"));
      }

      if (var3 == null) {
         var3 = this.getUnitProcessor().process(var5, new BytesInput(var7), this, var2);
      }

      this.addChild(var3);
      if (var4 != null && !var4.isEmpty()) {
         for (String var23 : var4.keySet()) {
            byte[] var25 = (byte[])var4.get(var23);
            IUnit var26 = null;

            try {
               var26 = this.getUnitProcessor().process(var23, new BytesInput(var25), this, var2);
               var26.process();
               this.addChild(var26);
            } catch (Exception var14) {
               kS.catching(var14);
               if (var26 == null) {
                  var26 = this.getUnitProcessor().process(var23, new BytesInput(var25), this, null, false, true);
               }

               this.addChild(var26);
            }
         }
      }
   }

   private String pC(byte[] var1) {
      if (var1 != null && var1.length >= 4) {
         if (var1[0] == 100 && var1[1] == 101 && var1[2] == 120 && var1[3] == 10) {
            return "dex";
         }

         if (var1[0] == 100 && var1[1] == 101 && var1[2] == 121 && var1[3] == 10) {
            return "odex";
         }

         if (var1[0] == 127 && var1[1] == 69 && var1[2] == 76 && var1[3] == 70) {
            return "elf";
         }
      }

      return null;
   }

   private void A(Sv var1) {
      int var2 = 1;
      byte[] var3 = this.kS(var1);
      if (var3 != null) {
         this.sY |= 1;
         if (com.pnfsoftware.jeb.corei.parsers.cert.Ws.pC(var3)) {
            String var4 = "Certificate";
            IUnit var5 = this.getUnitProcessor().process(var4, new BytesInput(var3), this, "cert", true);
            if (var5 != null) {
               this.addChild(var5);
               var5.process();
            }

            var2++;
         }
      }

      K var13 = new K(this.pC);
      if (!var13.wS()) {
         this.logWarn(true, S.L("Errors were reported while parsing the APK signatures"));
      }

      if (var13.pC() != null) {
         this.sY |= 2;
         this.ys = var13.pC();

         for (APKSigSchemeV2Block.Signer var6 : this.ys.getSigners()) {
            for (APKSigSchemeV2Block.Certificate var8 : var6.getCertificates()) {
               byte[] var9 = var8.getBytes();
               if (com.pnfsoftware.jeb.corei.parsers.cert.Ws.pC(var9)) {
                  String var10 = "Certificate (v2)";
                  if (var2 >= 2) {
                     var10 = Strings.ff("Certificate #%d (v2)", var2);
                  }

                  IUnit var11 = this.getUnitProcessor().process(var10, new BytesInput(var9), this, "cert", true);
                  if (var11 != null) {
                     var11.process();
                     this.addChild(var11);
                  }

                  var2++;
               }
            }
         }
      }

      if (var13.A() != null) {
         this.sY |= 4;
         this.oT = var13.A();

         for (APKSigSchemeV2Block.Signer var18 : this.oT.getSigners()) {
            for (APKSigSchemeV2Block.Certificate var24 : var18.getCertificates()) {
               byte[] var27 = var24.getBytes();
               if (com.pnfsoftware.jeb.corei.parsers.cert.Ws.pC(var27)) {
                  String var33 = "Certificate (v3)";
                  if (var2 >= 2) {
                     var33 = Strings.ff("Certificate #%d (v3)", var2);
                  }

                  IUnit var38 = this.getUnitProcessor().process(var33, new BytesInput(var27), this, "cert", true);
                  if (var38 != null) {
                     var38.process();
                     this.addChild(var38);
                  }

                  var2++;
               }
            }
         }
      }

      if (var13.kS() != null) {
         this.sY |= 8;
         this.NS = var13.kS();

         for (APKSigSchemeV2Block.Signer var19 : this.NS.getSigners()) {
            for (APKSigSchemeV2Block.Certificate var25 : var19.getCertificates()) {
               byte[] var28 = var25.getBytes();
               if (com.pnfsoftware.jeb.corei.parsers.cert.Ws.pC(var28)) {
                  String var34 = "Certificate (v3.1)";
                  if (var2 >= 2) {
                     var34 = Strings.ff("Certificate #%d (v3.1)", var2);
                  }

                  IUnit var39 = this.getUnitProcessor().process(var34, new BytesInput(var28), this, "cert", true);
                  if (var39 != null) {
                     var39.process();
                     this.addChild(var39);
                  }

                  var2++;
               }
            }
         }
      }

      if (var2 <= 1 && this.pC != null && var1.pC("AndroidManifest.xml")) {
         ApkVerifier var17 = new Builder(this.pC).build();

         try {
            Result var20 = var17.verify();
            TextBuilder var23 = new TextBuilder();
            if (var20.isVerified()) {
               var23.append((CharSequence)S.L("APK is verified."));
            } else {
               var23.append((CharSequence)S.L("APK is unverified!"));
            }

            var23.append((CharSequence)"\n\n");
            int var26 = 0;

            for (V1SchemeSignerInfo var35 : var20.getV1SchemeSigners()) {
               String var40 = Strings.ff("Certificate (v1)");
               var23.append((CharSequence)Strings.generate('-', 80)).append('\n');
               var23.append("=> %s (%s, signature file: %s)\n", var40, var35.getName(), var35.getSignatureFileName());
               var23.append((CharSequence)com.pnfsoftware.jeb.corei.parsers.cert.Ws.pC(var35.getCertificate()));
               var23.append((CharSequence)"\n");
               this.pC(true, var35.getErrors(), var23);
               this.pC(false, var35.getWarnings(), var23);
               var23.append((CharSequence)"\n");
               var26++;
            }

            for (V2SchemeSignerInfo var36 : var20.getV2SchemeSigners()) {
               String var41 = Strings.ff("Certificate (v2)");
               var23.append((CharSequence)Strings.generate('-', 80)).append('\n');
               var23.append("=> %s\n", var41);
               var23.append((CharSequence)com.pnfsoftware.jeb.corei.parsers.cert.Ws.pC(var36.getCertificate()));
               var23.append((CharSequence)"\n");
               this.pC(true, var36.getErrors(), var23);
               this.pC(false, var36.getWarnings(), var23);
               var23.append((CharSequence)"\n");
               var26++;
            }

            for (V3SchemeSignerInfo var37 : var20.getV3SchemeSigners()) {
               String var42 = Strings.ff("Certificate (v3)");
               var23.append((CharSequence)Strings.generate('-', 80)).append('\n');
               var23.append("=> %s\n", var42);
               var23.append((CharSequence)com.pnfsoftware.jeb.corei.parsers.cert.Ws.pC(var37.getCertificate()));
               var23.append((CharSequence)"\n");
               this.pC(true, var37.getErrors(), var23);
               this.pC(false, var37.getWarnings(), var23);
               var23.append((CharSequence)"\n");
               var26++;
            }

            if (var26 > 0) {
               IUnit var32 = this.getUnitProcessor().process("Certificates", new BytesInput(Strings.encodeUTF8(var23.toString())), this, "text");
               this.addChild(var32);
            }
         } catch (Exception var12) {
            kS.catchingSilent(var12);
         }
      }
   }

   private void pC(boolean var1, List var2, TextBuilder var3) {
      if (var2 != null && !var2.isEmpty()) {
         var3.append("%s:\n", var1 ? S.L("Error") : S.L("Warning"));

         for (IssueWithParams var5 : var2) {
            var3.append("- %s\n", var5);
         }
      }
   }

   private IUnit pC(Sv var1, File var2) {
      try {
         File var3 = new File(var2, "AndroidManifest.xml");
         if (var3.exists()) {
            byte[] var4 = IO.readFile(var3);
            return this.pC(var1, var4);
         }
      } catch (IOException var5) {
         this.logWarn(true, S.L("Cannot process the manifest"));
         kS.catchingSilent(var5);
         if (!this.A()) {
            JebCoreService.notifySilentExceptionToClient(var5);
         }
      }

      return null;
   }

   private IUnit pC(Sv var1, byte[] var2) {
      IUnit var3 = this.getUnitProcessor().process("Manifest", new BytesInput(var2), this, "xml");
      var3.setRealName("AndroidManifest.xml");
      if (!var3.process()) {
         var3 = this.getUnitProcessor().process("Manifest", new BytesInput(var2), this, "html");
         if (!var3.process()) {
            return null;
         }
      }

      return var3;
   }

   private byte[] kS(Sv var1) {
      String var2 = null;

      for (GenericZipEntry var4 : var1.getEntries().values()) {
         String var5 = var4.getName().toUpperCase();
         if (var5 != null && var5.startsWith("META-INF/") && (var5.endsWith(".RSA") || var5.endsWith(".DSA"))) {
            var2 = var4.getName();
            break;
         }
      }

      return var2 == null ? null : var1.A(var2);
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      Strings.ff(var1, S.L("Package name: %s\n"), this.getPackageName());
      Strings.ff(var1, S.L("Debuggable: %b\n"), this.isDebuggable());
      Strings.ff(var1, S.L("Signing Scheme: %s\n"), pC(this.sY));
      return var1.toString();
   }

   private static String pC(int var0) {
      StringBuilder var1 = new StringBuilder();
      if ((var0 & 1) != 0) {
         var1.append("v1");
      }

      if ((var0 & 2) != 0) {
         if (var1.length() != 0) {
            var1.append("+");
         }

         var1.append("v2");
      }

      if ((var0 & 4) != 0) {
         if (var1.length() != 0) {
            var1.append("+");
         }

         var1.append("v3");
      }

      if ((var0 & 8) != 0) {
         if (var1.length() != 0) {
            var1.append("+");
         }

         var1.append("v3.1");
      }

      return var1.toString();
   }

   public boolean A() {
      return this.ld != 0;
   }

   private void kS() {
      if (!this.ED) {
         synchronized (this) {
            if (!this.ED) {
               this.Sc = false;
               this.ah = false;
               this.eP = null;
               this.UO = null;
               this.Ab = null;
               this.rl = null;
               this.z = Collections.emptyList();
               this.Ek = Collections.emptyList();
               this.hK = Collections.emptyList();
               this.Er = Collections.emptyList();
               this.FE = Collections.emptyList();

               try {
                  if (ApkManifestHelper.canParse(this)) {
                     ApkManifestHelper var2 = new ApkManifestHelper(this);

                     try {
                        this.Sc = var2.isDebuggable();
                     } catch (Exception var14) {
                     }

                     try {
                        this.ah = var2.hasApplication();
                     } catch (Exception var13) {
                     }

                     try {
                        this.eP = var2.getApplicationComponentFactory();
                     } catch (Exception var12) {
                     }

                     try {
                        this.UO = var2.getApplicationName();
                     } catch (Exception var11) {
                     }

                     try {
                        this.Ab = var2.getPackageName();
                     } catch (Exception var10) {
                     }

                     try {
                        this.rl = var2.getMainActivity();
                     } catch (Exception var9) {
                     }

                     try {
                        this.z = var2.getActivities();
                     } catch (Exception var8) {
                     }

                     try {
                        this.Ek = var2.getProviders();
                     } catch (Exception var7) {
                     }

                     try {
                        this.hK = var2.getReceivers();
                     } catch (Exception var6) {
                     }

                     try {
                        this.Er = var2.getServices();
                     } catch (Exception var5) {
                     }

                     try {
                        this.FE = var2.getPermissions();
                     } catch (Exception var4) {
                     }
                  }
               } catch (Exception var15) {
                  if (!this.A()) {
                     JebCoreService.notifySilentExceptionToClient(new RuntimeException("ApkManifestHelper failed", var15));
                  }
               }

               this.ED = true;
            }
         }
      }
   }

   @Override
   public String getPackageName() {
      this.kS();
      return this.Ab;
   }

   @Override
   public List getPermissions() {
      this.kS();
      return this.FE;
   }

   @Override
   public boolean hasApplication() {
      this.kS();
      return this.ah;
   }

   @Override
   public String getApplicationComponentFactory() {
      this.kS();
      return this.eP;
   }

   @Override
   public String getApplicationName() {
      this.kS();
      return this.UO;
   }

   @Override
   public boolean isDebuggable() {
      this.kS();
      return this.Sc;
   }

   @Override
   public String getMainActivity() {
      this.kS();
      return this.rl;
   }

   @Override
   public List getActivities() {
      this.kS();
      return this.z;
   }

   @Override
   public List getServices() {
      this.kS();
      return this.Er;
   }

   @Override
   public List getReceivers() {
      this.kS();
      return this.hK;
   }

   @Override
   public List getProviders() {
      this.kS();
      return this.Ek;
   }

   @Override
   public boolean isMultiDex() {
      List var1 = this.getDex().getDexEntries();
      return var1 != null && var1.size() >= 2;
   }

   @Override
   public IDexUnit getDex() {
      return (IDexUnit)UnitUtil.findFirstChildByType(this, IDexUnit.class, false);
   }

   @Override
   public ICertificateUnit getCertificate() {
      return (ICertificateUnit)UnitUtil.findChildByType(this, ICertificateUnit.class, false, 0);
   }

   @Override
   public IXmlUnit getManifest() {
      return (IXmlUnit)UnitUtil.findChild(this, "Manifest", IXmlUnit.class, false, 0);
   }

   @Override
   public IUnit getLibraries() {
      return UnitUtil.findChild(this, "Libraries", null, false, 0);
   }

   @Override
   public List getLibrariesForArch(AndroidPlatformABI var1) {
      IUnit var2 = this.getLibraries();
      if (var2 == null) {
         return Collections.emptyList();
      } else {
         var2 = UnitUtil.findChild(var2, var1.toString(), null, false, 0);
         if (var2 == null) {
            return Collections.emptyList();
         } else {
            ArrayList var3 = new ArrayList();

            for (IUnit var5 : var2.getChildren()) {
               if (var5 instanceof IELFUnit) {
                  var3.add((IELFUnit)var5);
               }
            }

            return var3;
         }
      }
   }

   @Override
   public IUnit getAssets() {
      return UnitUtil.findChild(this, "Assets", null, false, 0);
   }

   @Override
   public IUnit getResources() {
      return UnitUtil.findChild(this, "Resources", null, false, 0);
   }

   @Override
   public int getSignatureSchemeVersionFlags() {
      if (this.sY == 0 && UnitUtil.findChildByType(this, com.pnfsoftware.jeb.corei.parsers.cert.Sv.class, false, 0) != null) {
         this.sY = 1;
      }

      return this.sY;
   }

   @Override
   public APKSigSchemeV2Block getSignatureSchemeV2Block() {
      return this.ys;
   }

   @Override
   public APKSigSchemeV3Block getSignatureSchemeV3Block() {
      return this.oT;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (this.gp != null && UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new bO(this, 1L, S.L("AAPT2-like Output"), true), false);
      }

      return var1;
   }

   @Override
   public synchronized IDexDynamic dynamic() {
      if (this.fI == null) {
         this.fI = new cq();
      }

      return this.fI;
   }

   @Override
   public IEmulatedAndroid createEmulatedAndroid() {
      IDexUnit var1 = this.getDex();
      if (var1 == null) {
         return null;
      } else {
         IDexDecompilerUnit var2 = var1.getDecompiler();
         return var2 == null ? null : var2.createEmulatedAndroid();
      }
   }

   @Override
   public IGenericUnpacker createGenericUnpacker() {
      IDexUnit var1 = this.getDex();
      if (var1 == null) {
         return null;
      } else {
         IDexDecompilerUnit var2 = var1.getDecompiler();
         return var2 == null ? null : var2.createGenericUnpacker();
      }
   }
}
