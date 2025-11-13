package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.DH;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.p;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.yt;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.encoding.HashCalculator;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationException;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.axi;
import com.pnfsoftware.jebglobal.axm;
import com.pnfsoftware.jebglobal.axr;
import com.pnfsoftware.jebglobal.ayy;
import com.pnfsoftware.jebglobal.ckh;
import com.pnfsoftware.jebglobal.ko;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class NativeSignatureDBManager implements IEventListener {
   private static final ILogger logger = GlobalLog.getLogger(NativeSignatureDBManager.class);
   public static final String SIGLIB_EXTENSION = ".siglib";
   public static final String SIGLIB_USER_PACKAGE_FOLDER = "custom";
   private static NativeSignatureDBManager instance;
   private final IEnginesContext enginesContext;
   private final NativeSignatureGenerator signatureGenerator;
   private Set folders = new HashSet();
   private File userCreatedPackageFolder;
   private List entries = new ArrayList();
   private boolean isActive = true;
   private Map idEntryMap = new HashMap();
   private Map memoryStorage = new HashMap();
   private Map userSelectedPackageStorage = new HashMap();
   private Map autoModeManagerStorage = new HashMap();

   public static NativeSignatureDBManager getInstance(IEnginesContext var0) {
      if (instance == null) {
         instance = new NativeSignatureDBManager(var0);
      }

      return instance;
   }

   private NativeSignatureDBManager(IEnginesContext var1) {
      this.signatureGenerator = NativeSignatureGenerator.getInstance(new p());
      this.enginesContext = var1;
      this.enginesContext.addListener(this);
   }

   public void addFolder(File var1, boolean var2) {
      this.folders.add(var1);
      if (var1.getName().equals("custom")) {
         this.userCreatedPackageFolder = var1;
      }

      if (var2) {
         this.rescan();
      }
   }

   public void removeFolder(File var1, boolean var2) {
      this.folders.remove(var1);
      if (var2) {
         this.rescan();
      }
   }

   public void removeAllFolders() {
      this.folders.clear();
   }

   public synchronized void rescan() {
      this.rescan(true);
   }

   public synchronized void rescan(boolean var1) {
      ArrayList var2 = new ArrayList();

      for (NativeSignaturePackageEntry var4 : this.entries) {
         var4.setStatus(NativeSignaturePackageEntry.PackageStatus.INACTIVE);
      }

      for (File var22 : this.folders) {
         if (var22 == null || !var22.exists() || !var22.isDirectory()) {
            logger.warn(S.L("Native signature folder is invalid: %s"), var22);
         } else if (!var22.getName().equals("codeless")) {
            for (File var8 : var22.listFiles()) {
               if (var8.isFile() && var8.getName().endsWith(".siglib")) {
                  NativeSignaturePackageMetadata var9;
                  try (FileInputStream var10 = new FileInputStream(var8)) {
                     ckh var11 = ckh.pC();
                     SerializationManager var12 = new SerializationManager(var11);
                     Deserializer var13 = var12.getDeserializer(var10);
                     var9 = (NativeSignaturePackageMetadata)var13.deserialize();
                  } catch (Exception var18) {
                     logger.error(S.L("Error when opening native signature package %s"), var8);
                     collectInformationDeserFailure(var8, "metadata", var18);
                     continue;
                  }

                  NativeSignaturePackageEntry var25 = null;

                  for (NativeSignaturePackageEntry var29 : this.entries) {
                     if (var29.getFile().equals(var8)) {
                        var25 = var29;
                        break;
                     }
                  }

                  if (var25 != null) {
                     var25.setStatus(NativeSignaturePackageEntry.PackageStatus.ACTIVE);
                  } else {
                     boolean var28 = true;
                     int var30 = var9.getUuid();
                     if (var30 == 0) {
                        var2.add(var8);
                        var28 = false;
                     } else if (var30 > 0) {
                        NativeSignaturePackageEntry var31 = (NativeSignaturePackageEntry)this.idEntryMap.get(var30);
                        if (var31 != null) {
                           int var14 = var31.getMetadata().getVersion();
                           int var15 = var9.getVersion();
                           if (var14 > var15) {
                              var2.add(var8);
                              var28 = false;
                           } else if (var14 < var15) {
                              var2.add(var31.getFile());
                              this.entries.remove(var31);
                           } else {
                              logger.warn(
                                 S.L("Two siglib entries have the same UUID and version number: uuid=%d (files: %s and %s)"), var30, var8, var31.getFile()
                              );
                           }
                        }
                     }

                     if (var28) {
                        var25 = new NativeSignaturePackageEntry(var8, var9);
                        var25.setStatus(NativeSignaturePackageEntry.PackageStatus.ACTIVE);
                        this.entries.add(var25);
                        if (var30 > 0) {
                           this.idEntryMap.put(var30, var25);
                        }
                     }
                  }
               }
            }
         }
      }

      if (var1) {
         for (File var23 : var2) {
            var23.delete();
         }
      }

      int var21 = 0;

      while (var21 < this.entries.size()) {
         NativeSignaturePackageEntry var24 = (NativeSignaturePackageEntry)this.entries.get(var21);
         if (var24.getStatus() == NativeSignaturePackageEntry.PackageStatus.INACTIVE) {
            this.entries.remove(var21);
         } else {
            var21++;
         }
      }
   }

   private static void collectInformationDeserFailure(File var0, String var1, Exception var2) {
      String var3 = "";

      try (FileInputStream var4 = new FileInputStream(var0)) {
         HashCalculator var5 = new HashCalculator(var4, 16);
         if (var5.compute()) {
            var3 = Formatter.byteArrayToHexString(var5.getSha256());
         }
      } catch (IOException var9) {
      }

      String var10 = Strings.ff(
         "Signature package deser failed (msg:%s - fileName:%s - sha256:%s - lastModified:%d)", var1, var0.getName(), var3, var0.lastModified()
      );
      JebCoreService.notifySilentExceptionToClient(new SerializationException(var10, var2));
   }

   public List getAvailablePackages() {
      return this.entries;
   }

   public void removeAllPackages() {
      this.entries.clear();
      this.idEntryMap.clear();
   }

   public List getLoadedPackages() {
      ArrayList var1 = new ArrayList();

      for (NativeSignaturePackageEntry var3 : this.entries) {
         if (var3.isLoadedInMemory()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public List getUserCreatedPackages(ProcessorType var1) {
      ArrayList var2 = new ArrayList();

      for (NativeSignaturePackageEntry var4 : this.entries) {
         if (var4.isActive() && var4.isUserCreated() && var4.getMetadata().getTargetProcessorType().equals(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public List getUserCreatedPackages() {
      ArrayList var1 = new ArrayList();

      for (NativeSignaturePackageEntry var3 : this.entries) {
         if (var3.isActive() && var3.isUserCreated()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public void updateOnDiskPackages(boolean var1) {
      Iterator var2 = this.entries.iterator();

      while (true) {
         NativeSignaturePackageEntry var3;
         List var4;
         NativeSignaturePackageMetadata var5;
         qt var6;
         while (true) {
            if (!var2.hasNext()) {
               return;
            }

            var3 = (NativeSignaturePackageEntry)var2.next();
            if (var3.isActive() && var3.isUserCreated()) {
               var4 = var3.getSignatureToWrite();
               if (var4 != null && !var4.isEmpty()) {
                  try (FileInputStream var7 = new FileInputStream(var3.getFile())) {
                     ckh var8 = ckh.pC();
                     SerializationManager var9 = new SerializationManager(var8);
                     Deserializer var10 = var9.getDeserializer(var7);
                     var5 = (NativeSignaturePackageMetadata)var10.deserialize();
                     if (var5 != null) {
                        var6 = (qt)var10.deserialize();
                        if (var6 == null) {
                           continue;
                        }
                        break;
                     }
                  } catch (Exception var14) {
                     logger.error(S.L("Error when opening native signature package %s"), var3.getFile());
                     collectInformationDeserFailure(var3.getFile(), "update on disk", var14);
                  }
               }
            }
         }

         if (!var1) {
            for (INativeSignature var18 : var4) {
               var6.pC(var18);
            }
         } else {
            for (INativeSignature var17 : var4) {
               INativeSignature var19 = null;

               for (INativeSignature var11 : var6.getSignatures()) {
                  if (var11.matchExactly(var17)) {
                     var19 = var11;
                     break;
                  }
               }

               if (var19 != null) {
                  var6.getSignatures().remove(var19);
               }

               var6.pC(var17);
            }
         }

         this.writePackageOnDisk(var3.getFile(), var5, var6);
         var3.clearSignaturesToWrite();
      }
   }

   public NativeSignaturePackageEntry createUserPackage(ProcessorType var1, String var2, String var3, String var4) {
      if (this.userCreatedPackageFolder == null) {
         logger.error(S.L("user folder is not set"));
         return null;
      } else {
         return this.createPackageInternal(this.userCreatedPackageFolder, var1, var2, -1, 0, var3, var4, null, null);
      }
   }

   private NativeSignaturePackageEntry createPackageInternal(
      File var1, ProcessorType var2, String var3, int var4, int var5, String var6, String var7, ICompiler var8, LibraryID var9
   ) {
      File var10 = new File(var1, var3 + ".siglib");
      if (var10.exists()) {
         return null;
      } else {
         NativeSignaturePackageMetadata var11 = NativeSignaturePackageMetadata.create(var2, var3, var4, var5, var6, var7, var8, var9);
         qt var12 = new qt(var11);
         this.writePackageOnDisk(var10, var11, var12);
         NativeSignaturePackageEntry var13 = new NativeSignaturePackageEntry(var10, var11);
         var13.setStatus(NativeSignaturePackageEntry.PackageStatus.ACTIVE);
         this.entries.add(var13);
         return var13;
      }
   }

   private boolean writePackageOnDisk(File var1, NativeSignaturePackageMetadata var2, qt var3) {
      try {
         try (DirectByteArrayOutputStream var4 = new DirectByteArrayOutputStream()) {
            ckh var5 = ckh.pC();
            SerializationManager var6 = new SerializationManager(var5);
            Serializer var7 = var6.getSerializer(var4);
            var7.serialize(var2);
            var7.serialize(var3);
            var7.close();
            IO.writeFile(var1, var4.getRawBytes(), 0, var4.size());
         }

         return true;
      } catch (IOException var10) {
         logger.catching(var10);
         return false;
      }
   }

   public void loadDefaultPackages(INativeCodeAnalyzer var1) {
      ProcessorType var2 = var1.getProcessor().getType();
      ICompiler var3 = var1.getDetectedCompiler();

      for (NativeSignaturePackageEntry var5 : this.getAvailablePackages()) {
         if (var5.getMetadata().getTargetProcessorType().equals(var2) && !var5.isUserCreated()) {
            ICompiler var6 = var5.getMetadata().getTargetCompiler();
            if (var6 == null || var3 != null && var3.getClass().equals(var6.getClass())) {
               NativeSignatureDBManager.SignatureMemoryStorage var7 = (NativeSignatureDBManager.SignatureMemoryStorage)this.memoryStorage.get(var1);
               if (var7 == null || var7.loadedPackages == null || !var7.loadedPackages.contains(var5)) {
                  this.loadPackage(var1, var5);
               }
            }
         }
      }
   }

   public boolean loadPackage(NativeSignaturePackageEntry var1, boolean var2) {
      boolean var3 = false;

      for (INativeCodeAnalyzer var5 : this.memoryStorage.keySet()) {
         try (ACLock var6 = ((a)var5).ys().getLock().a()) {
            if (var5.getProcessor().getType().equals(var1.getMetadata().getTargetProcessorType()) && this.loadInternal(var5, var1)) {
               if (var2) {
                  ((a)var5).oT();
               }

               var3 = true;
            }
         }
      }

      return var3;
   }

   public boolean loadPackage(INativeCodeAnalyzer var1, NativeSignaturePackageEntry var2) {
      return this.loadInternal(var1, var2);
   }

   private synchronized boolean loadInternal(INativeCodeAnalyzer var1, NativeSignaturePackageEntry var2) {
      NativeSignatureDBManager.SignatureMemoryStorage var3 = (NativeSignatureDBManager.SignatureMemoryStorage)this.memoryStorage.get(var1);
      if (var3 == null) {
         logger.error(S.L("unknown analyzer"));
         return false;
      } else {
         return var3.loadPackage(var2);
      }
   }

   public void activateAutoSigningMode(INativeCodeAnalyzer var1) {
      Assert.a(this.getUserSelectedPackage(var1) != null);
      ko var2 = (ko)this.autoModeManagerStorage.get(var1);
      if (var2 == null) {
         byte var3 = 10;
         yt var4 = new yt(var1, this, var3);
         var1.getModel().addListener(var4);
         ((ayy)var1.getTypeManager()).addListener(var4);
         this.autoModeManagerStorage.put(var1, var4);
      }
   }

   public void deactivateAutoSigningMode(INativeCodeAnalyzer var1) {
      ko var2 = (ko)this.autoModeManagerStorage.remove(var1);
      if (var2 != null) {
         var1.getModel().removeListener(var2);
         ((ayy)var1.getTypeManager()).removeListener(var2);
      }
   }

   public boolean isAutoSigningModeActivated(INativeCodeAnalyzer var1) {
      return this.autoModeManagerStorage.containsKey(var1);
   }

   public void registerAnalyzer(INativeCodeAnalyzer var1) {
      NativeSignatureDBManager.SignatureMemoryStorage var2 = (NativeSignatureDBManager.SignatureMemoryStorage)this.memoryStorage.get(var1);
      if (var2 == null) {
         var2 = new NativeSignatureDBManager.SignatureMemoryStorage(var1);
         this.memoryStorage.put(var1, var2);
      }
   }

   public void unregisterAnalyzer(INativeCodeAnalyzer var1) {
      NativeSignatureDBManager.SignatureMemoryStorage var2 = (NativeSignatureDBManager.SignatureMemoryStorage)this.memoryStorage.remove(var1);
      if (var2 != null && var2.loadedPackages != null) {
         for (NativeSignaturePackageEntry var4 : var2.loadedPackages) {
            var4.decrementOpenHandle();
         }
      }

      this.userSelectedPackageStorage.remove(var1);
      this.deactivateAutoSigningMode(var1);
   }

   public int getNumberLoadedSigs() {
      int var1 = 0;

      for (NativeSignatureDBManager.SignatureMemoryStorage var3 : this.memoryStorage.values()) {
         for (Map var5 : var3.signaturesDB.values()) {
            var1 += var5.size();
         }
      }

      return var1;
   }

   private static int getRoutineSize(INativeSignature var0) {
      int var1 = 0;

      for (INativeFeature var3 : ((Sv)var0).pC()) {
         if (var3 instanceof axr) {
            var1 = ((axr)var3).pC();
            break;
         }
      }

      return var1;
   }

   private static axm getRoutineCodeHash(INativeSignature var0) {
      axm var1 = null;

      for (INativeFeature var3 : ((Sv)var0).pC()) {
         if (var3 instanceof axm) {
            var1 = (axm)var3;
            break;
         }
      }

      return var1;
   }

   public void setActive(boolean var1) {
      this.isActive = var1;
   }

   public boolean isActive() {
      return this.isActive;
   }

   private NativeSignatureMatchResult internalMatch(
      NativeSignatureDBManager.SignatureMemoryStorage var1, INativeMethodItem var2, boolean var3, boolean var4, boolean var5
   ) throws NativeSignatureScopeException {
      if (var1 == null) {
         List var16 = Collections.emptyList();
         return new NativeSignatureMatchResult(var2, var16, true, INativeSignature.ConfidenceLevel.UNKNOWN);
      } else {
         int var6 = var2.getData().getCFG().getInstructionCount();
         Set var7 = (Set)var1.routineSignersDB.get(var6);
         Map var8 = (Map)var1.signaturesDB.get(var6);
         if (var8 != null && var7 != null) {
            ArrayList var17 = new ArrayList();

            for (NativeFeatureSignerID var11 : var7) {
               axi var12 = (axi)var1.signersDB.get(var11);
               var12.pC(var2);
               var17.addAll(var12.pC());
               var12.A();
            }

            Object var18 = new ArrayList();
            boolean var19 = true;
            INativeSignature.ConfidenceLevel var20 = INativeSignature.ConfidenceLevel.LOW;

            for (INativeSignature var14 : var8.keySet()) {
               if (var14.getFlags().getTargetType() == SignatureTargetType.ROUTINE && ((KD)var14).kS(var17)) {
                  if (var4) {
                     INativeSignature.ConfidenceLevel var15 = var14.getConfidenceLevel();
                     if (var15 == null || var15.compareTo(var20) < 0) {
                        continue;
                     }

                     if (var15.compareTo(var20) == 0) {
                        var18.add(var14);
                     } else {
                        var20 = var15;
                        var18.clear();
                        var18.add(var14);
                     }
                  } else {
                     var18.add(var14);
                  }

                  if (var18.size() >= 2 && var3) {
                     var19 = false;
                     break;
                  }
               }
            }

            Collections.sort((List)var18, (var0, var1x) -> {
               if (var1x.getFlags().hasMeaningfulTargetName()) {
                  if (!var0.getFlags().hasMeaningfulTargetName()) {
                     return 1;
                  }
               } else if (var0.getFlags().hasMeaningfulTargetName()) {
                  return -1;
               }

               if (var1x.getTargetName() == null) {
                  return var0.getTargetName() == null ? 0 : -1;
               } else {
                  return var0.getTargetName() == null ? 1 : var0.getTargetName().compareTo(var1x.getTargetName());
               }
            });
            if (var5) {
               var1.registerPackageUsed((List)var18);
            } else {
               var18 = var1.findBestPackage((List)var18);
               if (var1.isSafeSignatureList((List)var18)) {
                  var1.registerPackageUsed((List)var18);
               }
            }

            return new NativeSignatureMatchResult(var2, (List)var18, var19, var4 ? var20 : INativeSignature.ConfidenceLevel.UNKNOWN);
         } else {
            List var9 = Collections.emptyList();
            return new NativeSignatureMatchResult(var2, var9, true, INativeSignature.ConfidenceLevel.UNKNOWN);
         }
      }
   }

   public List match(INativeCodeAnalyzer var1, Collection var2, boolean var3, boolean var4, boolean var5) {
      var1.getModel().getLock().verifyLocked();
      List var6 = Collections.emptyList();
      if (this.isActive && !var2.isEmpty()) {
         NativeSignatureDBManager.SignatureMemoryStorage var7 = (NativeSignatureDBManager.SignatureMemoryStorage)this.memoryStorage.get(var1);
         if (var7 == null) {
            return var6;
         } else {
            ArrayList var15 = new ArrayList();
            TreeMap var8 = new TreeMap();

            for (INativeMethodDataItem var10 : var2) {
               auu var11 = (auu)var10.getRoutine();
               if (this.canBeMatched(var11, var5)) {
                  var11.pC(Boolean.valueOf(false));

                  try {
                     NativeSignatureMatchResult var12 = this.internalMatch(var7, var11, var3, var4, true);
                     if (!var12.getSignatures().isEmpty() && !var7.isSafeSignatureList(var12.getSignatures())) {
                        var8.put(var15.size(), var11);
                        var15.add(var12);
                     } else {
                        var15.add(var12);
                     }
                  } catch (NativeSignatureScopeException var14) {
                     Object[] var10000 = new Object[0];
                  }
               }
            }

            for (NativeSignatureMatchResult var18 : var15) {
               var7.updatePackageNames(var18.getSignatures());
            }

            for (Entry var19 : var8.entrySet()) {
               try {
                  NativeSignatureMatchResult var20 = this.internalMatch(var7, (INativeMethodItem)var19.getValue(), var3, var4, false);
                  var15.set((Integer)var19.getKey(), var20);
               } catch (NativeSignatureScopeException var13) {
                  Object[] var21 = new Object[0];
               }
            }

            return var15;
         }
      } else {
         return var6;
      }
   }

   private boolean canBeMatched(auu var1, boolean var2) {
      if (var1 == null) {
         return false;
      } else if (var1.isDisposed()) {
         return false;
      } else {
         return var1.E() == null ? false : var2 || var1.z() == null;
      }
   }

   public NativeSignatureGenerator getSignatureGenerator() {
      return this.signatureGenerator;
   }

   public File getUserCreatedPackageFolder() {
      return this.userCreatedPackageFolder;
   }

   public NativeSignaturePackageEntry getUserSelectedPackage(INativeCodeAnalyzer var1) {
      return (NativeSignaturePackageEntry)this.userSelectedPackageStorage.get(var1);
   }

   public void setUserSelectedPackage(INativeCodeAnalyzer var1, NativeSignaturePackageEntry var2) {
      this.userSelectedPackageStorage.put(var1, var2);
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.ProjectSaved) {
         this.updateOnDiskPackages(true);
      }
   }

   private static class SignatureMemoryStorage {
      private List loadedPackages;
      private Map signaturesDB;
      private Map signersDB;
      private Map routineSignersDB;
      private Map occurences = new HashMap();
      private Map occurencesAlt = new HashMap();

      public SignatureMemoryStorage(INativeCodeAnalyzer var1) {
         this.loadedPackages = new ArrayList();
         this.signaturesDB = new HashMap();
         this.signersDB = DH.pC((a)var1);
         this.routineSignersDB = new HashMap();
      }

      public boolean loadPackage(NativeSignaturePackageEntry var1) {
         try {
            boolean var19;
            try (FileInputStream var2 = new FileInputStream(var1.getFile())) {
               ckh var3 = ckh.pC();
               SerializationManager var4 = new SerializationManager(var3);
               Deserializer var5 = var4.getDeserializer(var2);
               var5.deserialize();
               qt var6 = (qt)var5.deserialize();

               for (INativeSignature var8 : var6.getSignatures()) {
                  int var9 = NativeSignatureDBManager.getRoutineSize(var8);
                  if (var9 == 0) {
                     Object[] var10000 = new Object[]{var8};
                  } else {
                     Object var10 = (Map)this.signaturesDB.get(var9);
                     if (var10 == null) {
                        var10 = new HashMap();
                        this.signaturesDB.put(var9, var10);
                     }

                     INativeSignature var11 = (INativeSignature)var10.get(var8);
                     if (var11 != null) {
                        ((Sv)var11).pC(var1);
                     } else {
                        ((Sv)var8).pC(var1);
                        var10.put(var8, var8);
                     }

                     Object var12 = (Set)this.routineSignersDB.get(var9);
                     if (var12 == null) {
                        var12 = new HashSet();
                        this.routineSignersDB.put(var9, var12);
                     }

                     for (INativeFeature var14 : ((Sv)var8).pC()) {
                        NativeFeatureSignerID var15 = DH.pC(var14);
                        var12.add(var15);
                     }
                  }
               }

               var1.incrementOpenHandle();
               this.loadedPackages.add(var1);
               var19 = true;
            }

            return var19;
         } catch (Exception var18) {
            NativeSignatureDBManager.logger.error(S.L("Error when opening native signature package %s"), var1.getFile());
            NativeSignatureDBManager.collectInformationDeserFailure(var1.getFile(), "package loading", var18);
            return false;
         }
      }

      public boolean isSafeSignatureList(List var1) {
         if (var1.size() == 1) {
            List var2 = ((INativeSignature)var1.get(0)).getPossibleNames();
            if (var2 == null || var2.size() <= 1) {
               return true;
            }
         }

         return false;
      }

      public void registerPackageUsed(List var1) {
         Object var3 = new HashSet();
         if (!var1.isEmpty()) {
            Map var2;
            if (this.isSafeSignatureList(var1)) {
               var3 = ((Sv)var1.get(0)).kS();
               var2 = this.occurences;
            } else {
               for (INativeSignature var5 : var1) {
                  var3.addAll(((Sv)var5).kS());
               }

               var2 = this.occurencesAlt;
            }

            for (String var8 : var3) {
               Integer var6 = (Integer)var2.get(var8);
               if (var6 == null) {
                  var2.put(var8, 1);
               } else {
                  var2.put(var8, var6 + 1);
               }
            }
         }
      }

      private List fillOccurences(Set var1, boolean var2) {
         ArrayList var3 = new ArrayList();

         for (String var5 : var1) {
            Integer var6 = (Integer)(var2 ? this.occurencesAlt : this.occurences).get(var5);
            if (var6 != null) {
               var3.add(new Couple(var5, var6));
            }
         }

         return var3;
      }

      public List getBestPackages(List var1) {
         HashSet var2 = new HashSet();

         for (INativeSignature var4 : var1) {
            var2.addAll(((Sv)var4).kS());
         }

         if (var2.size() <= 1) {
            return null;
         } else {
            List var6 = this.fillOccurences(var2, false);
            if (var6.isEmpty()) {
               var6 = this.fillOccurences(var2, true);
            }

            Collections.sort(var6, (var0, var1x) -> {
               int var2x = ((Integer)var1x.getSecond()).compareTo((Integer)var0.getSecond());
               return var2x != 0 ? var2x : ((String)var0.getFirst()).compareTo((String)var1x.getFirst());
            });
            ArrayList var7 = new ArrayList();
            var7.add((Couple)var6.get(0));

            for (int var5 = 1;
               var5 < var6.size()
                  && ((Integer)((Couple)var6.get(var5)).getSecond()).doubleValue() / ((Integer)((Couple)var6.get(0)).getSecond()).doubleValue() >= 0.75;
               var5++
            ) {
               var7.add((Couple)var6.get(var5));
            }

            return var7;
         }
      }

      public List findBestPackage(List var1) {
         List var2 = this.getBestPackages(var1);
         if (var2 == null) {
            return var1;
         } else {
            ArrayList var3 = new ArrayList(var1);
            Object var4 = new ArrayList();

            for (Couple var6 : var2) {
               for (int var7 = 0; var7 < var3.size(); var7++) {
                  if (((Sv)var3.get(var7)).kS().contains(var6.getFirst())) {
                     var4.add((INativeSignature)var3.get(var7));
                     var3.remove(var7);
                     var7--;
                  }
               }
            }

            if (var4.isEmpty()) {
               var4 = var1;
            }

            this.updatePackageNames(var1, var2);
            return (List)var4;
         }
      }

      public void updatePackageNames(List var1) {
         List var2 = this.getBestPackages(var1);
         if (var2 != null) {
            this.updatePackageNames(var1, var2);
         }
      }

      private void updatePackageNames(List var1, List var2) {
         List var3 = (List)var2.stream().map(var0 -> (String)var0.getFirst()).collect(Collectors.toList());

         for (INativeSignature var5 : var1) {
            LinkedHashSet var6 = new LinkedHashSet();

            for (String var8 : var3) {
               if (((Sv)var5).kS().contains(var8)) {
                  var6.add(var8);
               }
            }

            if (!var6.isEmpty()) {
               ((KD)var5).pC(var6);
            }
         }
      }
   }
}
