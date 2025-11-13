package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.corei.parsers.apk.decoder.Uz;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.encoding.zip.GenericZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowserPNF;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.xn;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException.Feature;

public class CU extends ZipBrowserPNF {
   private static final ILogger nf = GlobalLog.getLogger(CU.class);
   private static final String gP = "android-resources.zip";
   private static final String za = "1.apk";
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 0;
   public static final int Dw = 1;
   public static final int Uv = 2;
   public static final int oW = 3;
   public static final int gO = 3;
   private static final Set lm = Collections.unmodifiableSet(Set.of("classes.dex", "AndroidManifest.xml", "resources.arsc"));
   private static final Set zz = Collections.unmodifiableSet((Set)lm.stream().map(String::toLowerCase).collect(Collectors.toSet()));
   private File JY;
   private ej HF;
   private File LK;
   private File io;
   private boolean qa;
   private String Hk;
   private int Me = 3;
   private int PV;
   private List oQ = new ArrayList();
   private Map xW = new HashMap();

   public CU(File var1, ej var2) throws IOException {
      super(var1, 0, true);
      this.JY = var1;
      this.HF = var2;
   }

   public void q(File var1) {
      this.LK = var1;
   }

   public File q() {
      return this.LK;
   }

   public void RF(File var1) {
      this.io = var1;
      IO.deleteDirectoryContents(var1);
   }

   public File RF() {
      return this.io;
   }

   public void q(boolean var1) {
      this.qa = var1;
   }

   public boolean xK() {
      return this.qa;
   }

   public String Dw() {
      return this.Hk;
   }

   public void q(int var1) {
      this.Me = var1;
   }

   public int Uv() {
      return this.Me;
   }

   public boolean q(String var1) {
      return this.getEntry(var1) != null;
   }

   public byte[] RF(String var1) {
      try {
         return this.readEntry(var1);
      } catch (IOException var3) {
         nf.catchingSilent(var3);
         return null;
      }
   }

   public File xK(String var1) {
      File var2 = new File(this.io, var1);

      for (Entry var4 : this.getEntries().entrySet()) {
         if (((String)var4.getKey()).startsWith(var1 + "/")) {
            String var5 = ((GenericZipEntry)var4.getValue()).getName();
            if (var5 != null && var5.length() != 0 && !((GenericZipEntry)var4.getValue()).isDirectory()) {
               File var6 = new File(this.io, (String)var4.getKey());

               try {
                  IO.writeFile(var6, IO.readInputStream(this.getEntryStream((String)var4.getKey())), true);
               } catch (IOException var7) {
               }
            }
         }
      }

      return var2;
   }

   public boolean RF(int var1) {
      File var2 = new File(this.LK, "1.apk");
      if (!var2.exists() || var2.length() != AssetManager.getAssetSize("android-resources.zip")) {
         try (
            InputStream var3 = AssetManager.getAsset("android-resources.zip");
            FileOutputStream var4 = new FileOutputStream(var2, false);
         ) {
            IO.copy(var3, var4);
         } catch (IOException var11) {
            throw new RuntimeException(var11);
         }
      }

      int var12 = this.xK(var1);
      return var12 != -2 ? var12 == 0 : false;
   }

   private int xK(int var1) {
      try {
         com.pnfsoftware.jeb.corei.parsers.apk.decoder.CU var2 = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.CU(this.JY);
         var2.RF(this.LK);
         var2.q(this.Me);
         if (var1 == 1) {
            com.pnfsoftware.jeb.corei.parsers.apk.decoder.qV var9 = var2.xK();
            this.q(var9);
         } else if (var1 == 2) {
            if (this.xK()) {
               try {
                  this.Hk = var2.RF(false);
               } catch (Exception var7) {
                  JebCoreService.notifySilentExceptionToClient(var7);
               }
            }

            for (com.pnfsoftware.jeb.corei.parsers.apk.decoder.qV var12 : var2.Uv()) {
               this.q(var12);
            }
         }

         if (this.PV > 0) {
            this.HF.logInfo(true, S.L("Resource files adjusted (%d)"), this.PV);
         }

         if (this.oQ.isEmpty()) {
            return 0;
         } else {
            LinkedHashMap var11 = new LinkedHashMap();
            int var13 = 0;

            for (Exception var6 : this.oQ) {
               if (var13 >= 20) {
                  var11.put("reported-exception-continued", "more exceptions were reported...");
                  break;
               }

               var11.put("reported-exception-" + var13, Strings.safe(var6.getMessage()));
               var13++;
            }

            JebCoreService.notifySilentExceptionToClient(new Exception("Exceptions reported when decoding APK resources"), var11);
            return -1;
         }
      } catch (Exception var8) {
         Throwable var3 = Throwables.getRootCause(var8);
         if (var3 instanceof UnsupportedZipFeatureException) {
            Feature var4 = ((UnsupportedZipFeatureException)var3).getFeature();
            nf.error(
               S.L("Some entries of this APK are unsupported: %s. Try to unzip the APK contents first, re-zip to a clean APK file"),
               Strings.safe(var4, S.L("unknown"))
            );
            nf.catching(var3);
            JebCoreService.notifyExceptionToClient(var3);
            return -3;
         } else {
            if (var8.getClass().getName().contains("CantFindFrameworkResException")) {
               nf.error(S.L("An additional Android framework is required to process the APK! Drop it in your frameworks directory: %s"), this.LK);
            } else {
               nf.catching(var8);
               JebCoreService.notifyExceptionToClient(var8);
            }

            return -1;
         }
      }
   }

   private boolean q(com.pnfsoftware.jeb.corei.parsers.apk.decoder.qV var1) throws Exception {
      for (xn var3 : var1.Uv()) {
         this.HF.logWarn(true, var3.toString());
      }

      if (var1.oW() != null) {
         this.HF.logError(true, var1.oW().getMessage());
         this.oQ.add(var1.oW());
      }

      String var10 = var1.xK();
      String var11 = Formatter.escapeString(var10);
      if (var1.RF()) {
         this.PV++;
      }

      if (!var1.gO()) {
         if (var1.q() == Uz.RF) {
            this.HF.logInfo(true, S.L("'%s': not found"), var11);
         } else if (var1.q() == Uz.xK) {
            this.HF.logError(true, S.L("'%s': failed processing"), var11);
         } else {
            this.HF.logWarn(true, S.L("'%s': unknown failure"), var11);
         }

         return false;
      } else if (var1.Dw() == null) {
         return false;
      } else {
         try {
            if (!var1.Dw().isBinary()) {
               throw new IOException("Illegal contents for resource");
            } else {
               String var4 = IO.sanitizePath(var10, false, true);
               if (!var4.equals(var10)) {
                  this.HF.logWarn(true, S.L("'%s': illegal path was sanitized to \"%s\""), var11, var4);
               }

               if (OSType.determine().isWindows()) {
                  String var5 = var4.toLowerCase();
                  Object var6 = (List)this.xW.get(var5);
                  if (var6 == null) {
                     var6 = new ArrayList();
                     this.xW.put(var5, var6);
                  }

                  var6.add(var4);
                  boolean var7 = var6.size() >= 2;
                  if (lm.contains(var4)) {
                     var7 = false;
                  } else if (zz.contains(var5)) {
                     var7 = true;
                  }

                  if (var7) {
                     String var8 = var4 + "." + var6.size();
                     this.HF
                        .logWarn(
                           true, S.L("'%s': Equivalent paths could resolve to writing the same file: %s, %s -> renaming to \"%s\""), var11, var6, var4, var8
                        );
                     var4 = var8;
                  }
               }

               File var12 = new File(this.io, var4);
               IO.createFoldersForFile(var12);
               IO.writeFile(var12, var1.Dw().getBytes());
               return true;
            }
         } catch (IOException var9) {
            this.HF.logWarn(true, S.L("'%s': not written to disk"), var11);
            return false;
         }
      }
   }
}
