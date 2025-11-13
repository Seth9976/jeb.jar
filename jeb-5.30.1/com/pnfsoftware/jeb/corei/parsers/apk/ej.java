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
import com.pnfsoftware.jeb.corei.parsers.dex.bK;
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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Ser
@SerVersion(3)
public class ej extends AbstractBinaryUnit implements IArchiveUnit, IApkUnit {
   private static final ILogger xK = GlobalLog.getLogger(ej.class);
   @SerTransient
   File q;
   @SerTransient
   boolean RF;
   @SerTransient
   private byte[] Dw;
   @SerTransient
   private Object Uv;
   @SerTransient
   private Boolean oW;
   @SerId(1)
   private int gO;
   @SerId(2)
   private APKSigSchemeV2Block nf;
   @SerId(value = 3, version = 1)
   private int gP;
   @SerId(value = 4, version = 2)
   private String za;
   @SerId(value = 5, version = 3)
   private APKSigSchemeV3Block lm;
   @SerId(6)
   private IDexDynamic zz;
   @SerId(7)
   private String JY;
   @SerId(8)
   private APKSigSchemeV3Block HF;
   @SerTransient
   private ApkStringResHelper LK;
   @SerTransient
   private volatile String io;
   @SerTransient
   private volatile boolean qa;
   @SerTransient
   private boolean Hk;
   @SerTransient
   private boolean Me;
   @SerTransient
   private String PV;
   @SerTransient
   private String oQ;
   @SerTransient
   private String xW;
   @SerTransient
   private List KT;
   @SerTransient
   private List Gf;
   @SerTransient
   private List Ef;
   @SerTransient
   private List cC;
   @SerTransient
   private List sH;

   public ej(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/zip", var2, "apk", var1, var3, var4, var5);
   }

   public ApkStringResHelper q() {
      if (this.LK == null) {
         ArrayList var1 = new ArrayList();
         if (this.JY != null) {
            for (String var5 : this.JY.split(",")) {
               var5 = var5.trim();
               if (!var5.isEmpty()) {
                  var1.add(var5);
               }
            }
         }

         this.LK = new ApkStringResHelper(this, var1);
      }

      return this.LK;
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
         this.JY = this.getPropertyManager().getString("PreferredLocales");
         File var9 = null;
         CU var10 = null;
         this.Uv = new Object();

         try {
            if (this.q == null) {
               if (this.getInput() instanceof FileInput) {
                  this.q = ((FileInput)this.getInput()).getFile();
                  this.RF = false;
               } else {
                  try (InputStream var35 = this.getInput().getStream()) {
                     byte[] var11 = IO.readInputStream(var35);
                     this.q = File.createTempFile("jebTempFile", null, null);
                     this.RF = true;
                     IO.writeFile(this.q, var11, false);
                  } catch (IOException var31) {
                     xK.catching(var31);
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

            var9 = IO.createTempFolder(this.q.getName() + "-EXTRACTED");
            var10 = new CU(this.q, this);
            var10.q(var2);
            var10.q(var1);
            IO.createDirectory(var34.getPath());
            var10.q(var34);
            var10.RF(var9);
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
                  if (!var10.RF(1)) {
                     this.addNotification(new UnitNotification(NotificationType.ERROR, S.L("An error occurred while decoding the APK")));
                     this.gP = 1;
                  }

                  var38 = this.q(var10, var9);
               } else {
                  if (!var10.RF(2)) {
                     this.addNotification(new UnitNotification(NotificationType.ERROR, S.L("An error occurred while decoding the APK")));
                     this.gP = 1;
                  }

                  if (var10.xK()) {
                     String var14 = var10.Dw();
                     if (!Strings.isBlank(var14)) {
                        this.za = var14;
                     }
                  }

                  var38 = this.q(var10, var9);
               }

               if (var38 != null) {
                  this.addChild(var38);
               }
            }

            if (var5) {
               this.RF(var10);
            }

            if (var6) {
               this.q(var10);
            }

            if (var4) {
               File var39 = new File(var9, "res");
               if (var39.exists()) {
                  FileContainerUnit var43 = new FileContainerUnit(var39, "Resources", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
                  this.addChild(var43);
                  int var15 = IO.listFiles(var39).size();
                  int var16 = this.getPropertyManager().getInteger("DisableResourcesProcessingThreshold");
                  boolean var17 = this.q(var15, var16, "resources");
                  var43.process(var17);
               }
            }

            if (var8) {
               File var40;
               if (!var4) {
                  var40 = var10.xK("assets");
               } else {
                  var40 = new File(var9, "assets");
               }

               if (var40.exists()) {
                  FileContainerUnit var44 = new FileContainerUnit(var40, "Assets", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
                  this.addChild(var44);
                  int var47 = IO.listFiles(var40).size();
                  int var49 = this.getPropertyManager().getInteger("DisableAssetsProcessingThreshold");
                  boolean var51 = this.q(var47, var49, "assets");
                  var44.process(var51);
               }
            }

            if (var7) {
               File var41;
               if (!var4) {
                  var41 = var10.xK("lib");
                  if (!var41.exists()) {
                     var41 = var10.xK("libs");
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
            xK.catching(var32);
            if (!this.RF()) {
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

            if (this.q != null && this.RF) {
               this.q.deleteOnExit();
            }

            if (var9 != null) {
               IO.deleteDirectory(var9);
            }
         }

         this.setProcessed(true);
         return true;
      }
   }

   private boolean q(int var1, int var2, String var3) {
      if (var2 > 0 && var1 > var2) {
         synchronized (this.Uv) {
            if (this.oW != null) {
               return this.oW;
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
                     this.oW = var7;
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

   private void q(CU var1) {
      String var2 = null;
      IUnit var3 = null;
      LinkedHashMap var4 = null;
      String var5 = "Bytecode";
      boolean var6 = false;
      byte[] var7 = null;
      if (var1.q("classes.dex")) {
         var7 = var1.RF("classes.dex");
      }

      if (var7 != null) {
         var2 = this.q(var7);
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
            if (!var1.q(var9)) {
               if (!var4.isEmpty()) {
                  var9 = Strings.ff(S.L("Multi-DEX application (%d)"), 1 + var4.size());
                  this.addNotification(new UnitNotification(NotificationType.INFO, var9));
               }
               break;
            }

            byte[] var10 = var1.RF(var9);
            if (var10 == null) {
               this.logError(true, S.L("Cannot read DEX file: %s"), var9);
               var6 = true;
            } else if (!Strings.equals(this.q(var10), var2)) {
               String var11 = Strings.ff(S.L("Multi-DEX application (%d)"), 1 + var4.size());
               this.addNotification(new UnitNotification(NotificationType.INFO, var11));
               this.logWarn(true, S.L("Illegal dex entry in multi-dex APK: skipping %s"), var9);
            } else {
               var4.put(var9, var10);
            }

            var17++;
         }
      } else if (!this.RF()) {
         this.addNotification(
            new UnitNotification(
               NotificationType.INFO, S.L("This APK file does not contain bytecode, or the bytecode cannot be read; a placeholder file will be used")
            )
         );
         if (this.Dw == null) {
            this.Dw = AssetManager.getAssetBytes("Placeholder.dex");
            if (this.Dw == null) {
               throw new RuntimeException("The placeholder DEX file was not loaded");
            }
         }

         var7 = this.Dw;
         var5 = var5 + " (Placeholder)";
         var2 = this.q(var7);
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
               if (var3 instanceof bK) {
                  for (String var24 : var4.keySet()) {
                     byte[] var12 = (byte[])var4.get(var24);
                     ((bK)var3).q((IInput)(new BytesInput(var12, 0, var12.length, var24)));
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
         if (var3 instanceof bK) {
            if (((bK)var3).Ef()) {
               xK.catching(var15);
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
               xK.catching(var14);
               if (var26 == null) {
                  var26 = this.getUnitProcessor().process(var23, new BytesInput(var25), this, null, false, true);
               }

               this.addChild(var26);
            }
         }
      }
   }

   private String q(byte[] var1) {
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

   private void RF(CU var1) {
      int var2 = 1;
      byte[] var3 = this.xK(var1);
      if (var3 != null) {
         this.gO |= 1;
         if (com.pnfsoftware.jeb.corei.parsers.cert.ej.q(var3)) {
            String var4 = "Certificate";
            IUnit var5 = this.getUnitProcessor().process(var4, new BytesInput(var3), this, "cert", true);
            if (var5 != null) {
               this.addChild(var5);
               var5.process();
            }

            var2++;
         }
      }

      nI var13 = new nI(this.q);
      if (!var13.Dw()) {
         this.logWarn(true, S.L("Errors were reported while parsing the APK signatures"));
      }

      if (var13.q() != null) {
         this.gO |= 2;
         this.nf = var13.q();

         for (APKSigSchemeV2Block.Signer var6 : this.nf.getSigners()) {
            for (APKSigSchemeV2Block.Certificate var8 : var6.getCertificates()) {
               byte[] var9 = var8.getBytes();
               if (com.pnfsoftware.jeb.corei.parsers.cert.ej.q(var9)) {
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

      if (var13.RF() != null) {
         this.gO |= 4;
         this.lm = var13.RF();

         for (APKSigSchemeV2Block.Signer var18 : this.lm.getSigners()) {
            for (APKSigSchemeV2Block.Certificate var24 : var18.getCertificates()) {
               byte[] var27 = var24.getBytes();
               if (com.pnfsoftware.jeb.corei.parsers.cert.ej.q(var27)) {
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

      if (var13.xK() != null) {
         this.gO |= 8;
         this.HF = var13.xK();

         for (APKSigSchemeV2Block.Signer var19 : this.HF.getSigners()) {
            for (APKSigSchemeV2Block.Certificate var25 : var19.getCertificates()) {
               byte[] var28 = var25.getBytes();
               if (com.pnfsoftware.jeb.corei.parsers.cert.ej.q(var28)) {
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

      if (var2 <= 1 && this.q != null && var1.q("AndroidManifest.xml")) {
         ApkVerifier var17 = new Builder(this.q).build();

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
               var23.append((CharSequence)com.pnfsoftware.jeb.corei.parsers.cert.ej.q(var35.getCertificate()));
               var23.append((CharSequence)"\n");
               this.q(true, var35.getErrors(), var23);
               this.q(false, var35.getWarnings(), var23);
               var23.append((CharSequence)"\n");
               var26++;
            }

            for (V2SchemeSignerInfo var36 : var20.getV2SchemeSigners()) {
               String var41 = Strings.ff("Certificate (v2)");
               var23.append((CharSequence)Strings.generate('-', 80)).append('\n');
               var23.append("=> %s\n", var41);
               var23.append((CharSequence)com.pnfsoftware.jeb.corei.parsers.cert.ej.q(var36.getCertificate()));
               var23.append((CharSequence)"\n");
               this.q(true, var36.getErrors(), var23);
               this.q(false, var36.getWarnings(), var23);
               var23.append((CharSequence)"\n");
               var26++;
            }

            for (V3SchemeSignerInfo var37 : var20.getV3SchemeSigners()) {
               String var42 = Strings.ff("Certificate (v3)");
               var23.append((CharSequence)Strings.generate('-', 80)).append('\n');
               var23.append("=> %s\n", var42);
               var23.append((CharSequence)com.pnfsoftware.jeb.corei.parsers.cert.ej.q(var37.getCertificate()));
               var23.append((CharSequence)"\n");
               this.q(true, var37.getErrors(), var23);
               this.q(false, var37.getWarnings(), var23);
               var23.append((CharSequence)"\n");
               var26++;
            }

            if (var26 > 0) {
               IUnit var32 = this.getUnitProcessor().process("Certificates", new BytesInput(Strings.encodeUTF8(var23.toString())), this, "text");
               this.addChild(var32);
            }
         } catch (Exception var12) {
            xK.catchingSilent(var12);
         }
      }
   }

   private void q(boolean var1, List var2, TextBuilder var3) {
      if (var2 != null && !var2.isEmpty()) {
         var3.append("%s:\n", var1 ? S.L("Error") : S.L("Warning"));

         for (IssueWithParams var5 : var2) {
            var3.append("- %s\n", var5);
         }
      }
   }

   private IUnit q(CU var1, File var2) {
      try {
         File var3 = new File(var2, "AndroidManifest.xml");
         if (var3.exists()) {
            byte[] var4 = IO.readFile(var3);
            return this.q(var1, var4);
         }
      } catch (IOException var5) {
         this.logWarn(true, S.L("Cannot process the manifest"));
         xK.catchingSilent(var5);
         if (!this.RF()) {
            JebCoreService.notifySilentExceptionToClient(var5);
         }
      }

      return null;
   }

   private IUnit q(CU var1, byte[] var2) {
      IUnit var3 = this.getUnitProcessor().process("Manifest", new BytesInput(var2), this, "xml");
      var3.setRealName("AndroidManifest.xml");
      if (!var3.process()) {
         var3 = this.getUnitProcessor().process("Manifest (bad)", new BytesInput(var2), this, "html");
         if (!var3.process()) {
            return null;
         }
      }

      return var3;
   }

   private byte[] xK(CU var1) {
      String var2 = null;

      for (GenericZipEntry var4 : var1.getEntries().values()) {
         String var5 = var4.getName().toUpperCase();
         if (var5 != null && var5.startsWith("META-INF/") && (var5.endsWith(".RSA") || var5.endsWith(".DSA"))) {
            var2 = var4.getName();
            break;
         }
      }

      return var2 == null ? null : var1.RF(var2);
   }

   private Certificate[] Dw(CU var1) {
      String var2 = null;

      for (GenericZipEntry var4 : var1.getEntries().values()) {
         String var5 = var4.getName().toUpperCase();
         if (var5 != null && var5.startsWith("META-INF/") && (var5.endsWith(".RSA") || var5.endsWith(".DSA"))) {
            var2 = var4.getName();
            break;
         }
      }

      if (var2 == null) {
         return null;
      } else {
         byte[] var11 = var1.RF(var2);
         if (var11 == null) {
            this.logWarn(true, "%s: %s", S.L("Cannot read file"), var2);
            return null;
         } else {
            ByteArrayInputStream var12 = new ByteArrayInputStream(var11);

            Collection var13;
            try {
               CertificateFactory var6 = CertificateFactory.getInstance("X.509");
               var13 = var6.generateCertificates(var12);
            } catch (CertificateException var10) {
               this.logWarn(true, "%s: %s", S.L("Could not parse certificate data"), var10.toString());
               if (var11.length > 16 && !this.RF()) {
                  JebCoreService.notifySilentExceptionToClient(var10);
               }

               return null;
            }

            Certificate[] var14 = new Certificate[var13.size()];
            int var7 = 0;

            for (Certificate var9 : var13) {
               var14[var7++] = var9;
            }

            return var14;
         }
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      Strings.ff(var1, S.L("Package name: %s\n"), this.getPackageName());
      Strings.ff(var1, S.L("Debuggable: %b\n"), this.isDebuggable());
      Strings.ff(var1, S.L("Signing Scheme: %s\n"), q(this.gO));
      return var1.toString();
   }

   private static String q(int var0) {
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

   public boolean RF() {
      return this.gP != 0;
   }

   private void xK() {
      if (!this.qa) {
         synchronized (this) {
            if (!this.qa) {
               this.Hk = false;
               this.Me = false;
               this.PV = null;
               this.oQ = null;
               this.xW = null;
               this.KT = Collections.emptyList();
               this.Gf = Collections.emptyList();
               this.Ef = Collections.emptyList();
               this.cC = Collections.emptyList();
               this.sH = Collections.emptyList();

               try {
                  if (ApkManifestHelper.canParse(this)) {
                     ApkManifestHelper var2 = new ApkManifestHelper(this);

                     try {
                        this.Hk = var2.isDebuggable();
                     } catch (Exception var13) {
                     }

                     try {
                        this.Me = var2.hasApplication();
                     } catch (Exception var12) {
                     }

                     try {
                        this.PV = var2.getApplicationName();
                     } catch (Exception var11) {
                     }

                     try {
                        this.oQ = var2.getPackageName();
                     } catch (Exception var10) {
                     }

                     try {
                        this.xW = var2.getMainActivity();
                     } catch (Exception var9) {
                     }

                     try {
                        this.KT = var2.getActivities();
                     } catch (Exception var8) {
                     }

                     try {
                        this.Gf = var2.getProviders();
                     } catch (Exception var7) {
                     }

                     try {
                        this.Ef = var2.getReceivers();
                     } catch (Exception var6) {
                     }

                     try {
                        this.cC = var2.getServices();
                     } catch (Exception var5) {
                     }

                     try {
                        this.sH = var2.getPermissions();
                     } catch (Exception var4) {
                     }
                  }
               } catch (Exception var14) {
                  if (!this.RF()) {
                     JebCoreService.notifySilentExceptionToClient(new RuntimeException("ApkManifestHelper failed", var14));
                  }
               }

               this.qa = true;
            }
         }
      }
   }

   @Override
   public String getPackageName() {
      this.xK();
      return this.oQ;
   }

   @Override
   public List getPermissions() {
      this.xK();
      return this.sH;
   }

   @Override
   public boolean hasApplication() {
      this.xK();
      return this.Me;
   }

   @Override
   public String getApplicationName() {
      this.xK();
      return this.PV;
   }

   @Override
   public boolean isDebuggable() {
      this.xK();
      return this.Hk;
   }

   @Override
   public String getMainActivity() {
      this.xK();
      return this.xW;
   }

   @Override
   public List getActivities() {
      this.xK();
      return this.KT;
   }

   @Override
   public List getServices() {
      this.xK();
      return this.cC;
   }

   @Override
   public List getReceivers() {
      this.xK();
      return this.Ef;
   }

   @Override
   public List getProviders() {
      this.xK();
      return this.Gf;
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
      if (this.gO == 0 && UnitUtil.findChildByType(this, com.pnfsoftware.jeb.corei.parsers.cert.CU.class, false, 0) != null) {
         this.gO = 1;
      }

      return this.gO;
   }

   @Override
   public APKSigSchemeV2Block getSignatureSchemeV2Block() {
      return this.nf;
   }

   @Override
   public APKSigSchemeV3Block getSignatureSchemeV3Block() {
      return this.lm;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (this.za != null && UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new oM(this, 1L, S.L("AAPT2-like Output"), true), false);
      }

      return var1;
   }

   @Override
   public synchronized IDexDynamic dynamic() {
      if (this.zz == null) {
         this.zz = new Nt();
      }

      return this.zz;
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
