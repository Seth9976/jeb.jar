package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.corei.parsers.apk.decoder.Mh;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.encoding.zip.GenericZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowserPNF;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.oe;
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

public class Sv extends ZipBrowserPNF {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class);
   private static final Set A = Collections.unmodifiableSet(Set.of("classes.dex", "AndroidManifest.xml", "resources.arsc"));
   private static final Set kS = Collections.unmodifiableSet((Set)A.stream().map(String::toLowerCase).collect(Collectors.toSet()));
   private File wS;
   private Ws UT;
   private File E;
   private File sY;
   private boolean ys;
   private String ld;
   private int gp = 3;
   private int oT;
   private List fI = new ArrayList();
   private Map WR = new HashMap();

   public Sv(File var1, Ws var2) throws IOException {
      super(var1, 0, true);
      this.wS = var1;
      this.UT = var2;
   }

   public void pC(File var1) {
      this.E = var1;
   }

   public void A(File var1) {
      this.sY = var1;
      IO.deleteDirectoryContents(var1);
   }

   public void pC(boolean var1) {
      this.ys = var1;
   }

   public boolean pC() {
      return this.ys;
   }

   public String A() {
      return this.ld;
   }

   public void pC(int var1) {
      this.gp = var1;
   }

   public boolean pC(String var1) {
      return this.getEntry(var1) != null;
   }

   public byte[] A(String var1) {
      try {
         return this.readEntry(var1);
      } catch (IOException var3) {
         pC.catchingSilent(var3);
         return null;
      }
   }

   public File kS(String var1) {
      File var2 = new File(this.sY, var1);

      for (Entry var4 : this.getEntries().entrySet()) {
         if (((String)var4.getKey()).startsWith(var1 + "/")) {
            String var5 = ((GenericZipEntry)var4.getValue()).getName();
            if (var5 != null && var5.length() != 0 && !((GenericZipEntry)var4.getValue()).isDirectory()) {
               File var6 = new File(this.sY, (String)var4.getKey());

               try {
                  IO.writeFile(var6, IO.readInputStream(this.getEntryStream((String)var4.getKey())), true);
               } catch (IOException var7) {
               }
            }
         }
      }

      return var2;
   }

   public boolean A(int var1) {
      File var2 = new File(this.E, "1.apk");
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

      int var12 = this.kS(var1);
      return var12 != -2 ? var12 == 0 : false;
   }

   private int kS(int var1) {
      try {
         com.pnfsoftware.jeb.corei.parsers.apk.decoder.Sv var2 = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.Sv(this.wS);
         var2.A(this.E);
         var2.pC(this.gp);
         if (var1 == 1) {
            com.pnfsoftware.jeb.corei.parsers.apk.decoder.KD var9 = var2.A();
            this.pC(var9);
         } else if (var1 == 2) {
            if (this.pC()) {
               try {
                  this.ld = var2.pC(false);
               } catch (Exception var7) {
                  JebCoreService.notifySilentExceptionToClient(var7);
               }
            }

            for (com.pnfsoftware.jeb.corei.parsers.apk.decoder.KD var12 : var2.kS()) {
               this.pC(var12);
            }
         }

         if (this.oT > 0) {
            this.UT.logInfo(true, S.L("Resource files adjusted (%d)"), this.oT);
         }

         if (this.fI.isEmpty()) {
            return 0;
         } else {
            LinkedHashMap var11 = new LinkedHashMap();
            int var13 = 0;

            for (Exception var6 : this.fI) {
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
            pC.error(
               S.L("Some entries of this APK are unsupported: %s. Try to unzip the APK contents first, re-zip to a clean APK file"),
               Strings.safe(var4, S.L("unknown"))
            );
            pC.catching(var3);
            JebCoreService.notifyExceptionToClient(var3);
            return -3;
         } else {
            if (var8.getClass().getName().contains("CantFindFrameworkResException")) {
               pC.error(S.L("An additional Android framework is required to process the APK! Drop it in your frameworks directory: %s"), this.E);
            } else {
               pC.catching(var8);
               JebCoreService.notifyExceptionToClient(var8);
            }

            return -1;
         }
      }
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.apk.decoder.KD var1) throws Exception {
      for (oe var3 : var1.UT()) {
         this.UT.logWarn(true, var3.toString());
      }

      if (var1.E() != null) {
         this.UT.logError(true, var1.E().getMessage());
         this.fI.add(var1.E());
      }

      String var10 = var1.kS();
      String var11 = Formatter.escapeString(var10);
      if (var1.A()) {
         this.oT++;
      }

      if (!var1.sY()) {
         if (var1.pC() == Mh.A) {
            this.UT.logInfo(true, S.L("'%s': not found"), var11);
         } else if (var1.pC() == Mh.kS) {
            this.UT.logError(true, S.L("'%s': failed processing"), var11);
         } else {
            this.UT.logWarn(true, S.L("'%s': unknown failure"), var11);
         }

         return false;
      } else if (var1.wS() == null) {
         return false;
      } else {
         try {
            if (!var1.wS().isBinary()) {
               throw new IOException("Illegal contents for resource");
            } else {
               String var4 = IO.sanitizePath(var10, false, true);
               if (!var4.equals(var10)) {
                  this.UT.logWarn(true, S.L("'%s': illegal path was sanitized to \"%s\""), var11, var4);
               }

               if (OSType.determine().isWindows()) {
                  String var5 = var4.toLowerCase();
                  Object var6 = (List)this.WR.get(var5);
                  if (var6 == null) {
                     var6 = new ArrayList();
                     this.WR.put(var5, var6);
                  }

                  var6.add(var4);
                  boolean var7 = var6.size() >= 2;
                  if (A.contains(var4)) {
                     var7 = false;
                  } else if (kS.contains(var5)) {
                     var7 = true;
                  }

                  if (var7) {
                     String var8 = var4 + "." + var6.size();
                     this.UT
                        .logWarn(
                           true, S.L("'%s': Equivalent paths could resolve to writing the same file: %s, %s -> renaming to \"%s\""), var11, var6, var4, var8
                        );
                     var4 = var8;
                  }
               }

               File var12 = new File(this.sY, var4);
               IO.createFoldersForFile(var12);
               IO.writeFile(var12, var1.wS().getBytes());
               return true;
            }
         } catch (IOException var9) {
            this.UT.logWarn(true, S.L("'%s': not written to disk"), var11);
            return false;
         }
      }
   }
}
