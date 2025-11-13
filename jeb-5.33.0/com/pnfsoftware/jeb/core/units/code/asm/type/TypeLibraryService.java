package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SerDisabled
public class TypeLibraryService {
   private static final ILogger logger = GlobalLog.getLogger(TypeLibraryService.class);
   public static final int GROUPID_TYPELIB_UNKNOWN = 0;
   public static final int GROUPID_TYPELIB_POSIX = 1;
   public static final int GROUPID_TYPELIB_LINUX = 2;
   public static final int GROUPID_TYPELIB_LINUX_ANDROID = 3;
   public static final int GROUPID_TYPELIB_WINDOWS = 9;
   public static final int GROUPID_TYPELIB_WIN32 = 10;
   public static final int GROUPID_TYPELIB_WINDDK = 20;
   public static final int GROUPID_TYPELIB_EFI = 30;
   public static final int GROUPID_TYPELIB_TEST = 100;
   public static final int GROUPID_TYPELIB_TEST1 = 101;
   public static final int GROUPID_TYPELIB_TEST2 = 102;
   public static final int GROUPID_TYPELIB_TEST3 = 103;
   public static final int GROUPID_TYPELIB_TEST4 = 104;
   public static final String TYPELIB_FILE_EXTENSION = ".typelib";
   private List folders = new ArrayList();
   private List entries = new ArrayList();
   private Map idEntryMap = new HashMap();

   public TypeLibraryService() {
   }

   public TypeLibraryService(File var1) {
      this.addFolder(var1, true);
   }

   public synchronized void addFolder(File var1, boolean var2) {
      this.folders.add(var1);
      if (var2) {
         this.rescan();
      }
   }

   public synchronized void rescan() {
      this.rescan(true);
   }

   public synchronized void rescan(boolean var1) {
      ArrayList var2 = new ArrayList();

      for (TypeLibraryEntry var4 : this.entries) {
         var4.status = 0;
      }

      for (File var22 : this.folders) {
         if (var22 != null && var22.exists() && var22.isDirectory()) {
            for (File var8 : var22.listFiles()) {
               if (var8.isFile() && var8.getName().endsWith(".typelib")) {
                  TypeLibraryMetadata var9;
                  try (FileInputStream var10 = new FileInputStream(var8)) {
                     ckh var11 = ckh.pC();
                     SerializationManager var12 = new SerializationManager(var11);
                     Deserializer var13 = var12.getDeserializer(var10);
                     var9 = (TypeLibraryMetadata)var13.deserialize();
                  } catch (Exception var18) {
                     logger.error(S.L("File %s is not a valid type library"), var8);
                     JebCoreService.notifyExceptionToClient(var18, 5, Maps.toMap("details", var8.getName()), null);
                     continue;
                  }

                  TypeLibraryEntry var25 = null;

                  for (TypeLibraryEntry var29 : this.entries) {
                     if (var29.file.equals(var8)) {
                        var25 = var29;
                        break;
                     }
                  }

                  if (var25 != null && var25.typelib != null) {
                     var25.status = 1;
                  } else {
                     boolean var28 = true;
                     int var30 = var9.getUuid();
                     if (var30 == 0) {
                        String var31 = var9.getName();
                        if (var31 != null) {
                           var30 = Hash.calculateCRC32(Strings.encodeUTF8(var31));
                        }
                     }

                     if (var30 == 0) {
                        logger.warn(
                           S.L("Typelib entry has neither a uuid nor a name from which a uuid will be derived. It will not be loaded. Entry: %s"), var9
                        );
                        var2.add(var8);
                        var28 = false;
                     } else {
                        TypeLibraryEntry var32 = (TypeLibraryEntry)this.idEntryMap.get(var30);
                        if (var32 != null) {
                           int var14 = var32.getMetadataHeader().getVersion();
                           int var15 = var9.getVersion();
                           if (var14 > var15) {
                              var2.add(var8);
                              var28 = false;
                           } else if (var14 < var15) {
                              var2.add(var32.file);
                              this.entries.remove(var32);
                           } else {
                              logger.warn(S.L("Two typelib entries have the same UUID and version number: uuid=%d (files: %s and %s)"), var30, var8, var32.file);
                           }
                        }
                     }

                     if (var28) {
                        var25 = new TypeLibraryEntry(var8, var9);
                        var25.status = 1;
                        this.entries.add(var25);
                        this.idEntryMap.put(var30, var25);
                     }
                  }
               }
            }
         } else {
            logger.warn(S.L("Type library folder is invalid: %s"), var22);
         }
      }

      if (var1) {
         for (File var23 : var2) {
            var23.delete();
         }
      }

      int var21 = 0;

      while (var21 < this.entries.size()) {
         TypeLibraryEntry var24 = (TypeLibraryEntry)this.entries.get(var21);
         if (var24.status == 0) {
            if (var24.typelib == null) {
               this.entries.remove(var21);
               continue;
            }

            var24.status = -1;
         }

         var21++;
      }

      Collections.sort(this.entries);
   }

   public synchronized List getAvailables() {
      return new ArrayList(this.entries);
   }

   public synchronized List getLoadedTypeLibraries() {
      ArrayList var1 = new ArrayList();

      for (TypeLibraryEntry var3 : this.entries) {
         if (var3.typelib != null) {
            var1.add(var3.typelib);
         }
      }

      return var1;
   }

   public synchronized List getLoadedTypeLibraryEntries() {
      ArrayList var1 = new ArrayList();

      for (TypeLibraryEntry var3 : this.entries) {
         if (var3.typelib != null) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public synchronized List getNotLoadedTypeLibraryEntries() {
      ArrayList var1 = new ArrayList();

      for (TypeLibraryEntry var3 : this.entries) {
         if (var3.typelib == null) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public synchronized boolean load(TypeLibraryEntry var1) {
      return var1.typelib != null ? true : this.loadInternal(var1);
   }

   public synchronized boolean loadSingle(ProcessorType var1, int var2) {
      return this.load(var1, var2, 1);
   }

   public synchronized boolean load(ProcessorType var1, int var2) {
      return this.load(var1, var2, Integer.MAX_VALUE);
   }

   public synchronized boolean load(ProcessorType var1, int var2, int var3) {
      if (var3 < 0) {
         var3 = Integer.MAX_VALUE;
      }

      ArrayList var4 = new ArrayList();

      for (ITypeLibrary var6 : this.getLoadedTypeLibraries()) {
         if (var6.getProcessorTypes().contains(var1) && var6.getGroupId() == var2) {
            var4.add(var6);
         }
      }

      int var11 = var4.size();
      if (var11 >= var3) {
         return var11 > 0;
      } else {
         var3 -= var11;
         ArrayList var12 = new ArrayList();

         for (TypeLibraryEntry var8 : this.getAvailables()) {
            TypeLibraryMetadata var9 = var8.getMetadataHeader();
            if (var8.typelib == null && var8.status == 1 && var9.getProcessorTypes().contains(var1) && var9.getGroupId() == var2) {
               var12.add(var8);
            }
         }

         Collections.sort(var12, new TypeLibraryService$1(this));

         for (TypeLibraryEntry var14 : var12) {
            var14.hdr.getName();
            Object[] var10000 = new Object[0];
            if (var3 <= 0) {
               break;
            }

            if (this.loadInternal(var14)) {
               var3--;
               var11++;
            }
         }

         return var11 > 0;
      }
   }

   private synchronized boolean loadInternal(TypeLibraryEntry var1) {
      File var2 = var1.getFile();
      logger.debug("Loading typelib: %s", var2);

      try {
         boolean var8;
         try (FileInputStream var3 = new FileInputStream(var2)) {
            ckh var4 = ckh.pC();
            SerializationManager var5 = new SerializationManager(var4);
            Deserializer var6 = var5.getDeserializer(var3);
            var6.deserialize();
            ITypeLibrary var7 = (ITypeLibrary)var6.deserialize();
            var1.typelib = var7;
            var8 = true;
         }

         return var8;
      } catch (Exception var11) {
         logger.error(S.L("File %s is not a valid type library"), var2);
         return false;
      }
   }

   public synchronized boolean loadExternal(ITypeLibrary var1) {
      for (TypeLibraryEntry var3 : this.entries) {
         if (var3.typelib == var1) {
            var3.status = 1;
            return true;
         }
      }

      TypeLibraryEntry var4 = new TypeLibraryEntry(null, null);
      var4.typelib = var1;
      var4.status = 1;
      this.entries.add(var4);
      return true;
   }

   public synchronized boolean unload(ITypeLibrary var1) {
      for (TypeLibraryEntry var3 : this.entries) {
         if (var3.typelib == var1) {
            return this.unload(var3);
         }
      }

      return false;
   }

   public synchronized boolean unload(TypeLibraryEntry var1) {
      if (var1.typelib == null) {
         return false;
      } else {
         var1.typelib = null;
         return true;
      }
   }

   public synchronized int unloadAll() {
      int var1 = 0;

      for (TypeLibraryEntry var3 : this.entries) {
         if (this.unload(var3)) {
            var1++;
         }
      }

      return var1;
   }

   public synchronized INativeMethodItem findRoutineByName(String var1, ProcessorType var2) {
      return this.findRoutineByName(var1, var2, 0);
   }

   public synchronized INativeMethodItem findRoutineByName(String var1, ProcessorType var2, int var3) {
      for (ITypeLibrary var5 : this.getLoadedTypeLibraries()) {
         if ((var2 == null || var5.getProcessorTypes().contains(var2)) && (var3 == 0 || var3 == var5.getGroupId())) {
            for (INativeMethodItem var7 : var5.getRoutines()) {
               if (Strings.isContainedIn(var1, var7.getName(true), var7.getName(false))) {
                  return var7;
               }
            }
         }
      }

      return null;
   }

   public synchronized INativeType findTypeBySignature(String var1, ProcessorType var2) {
      return this.findTypeBySignature(var1, var2, 0);
   }

   public synchronized INativeType findTypeBySignature(String var1, ProcessorType var2, int var3) {
      for (ITypeLibrary var5 : this.getLoadedTypeLibraries()) {
         if ((var2 == null || var5.getProcessorTypes().contains(var2)) && (var3 == 0 || var3 == var5.getGroupId())) {
            for (INativeType var7 : var5.getTypes()) {
               if (Strings.isContainedIn(var1, var7.getSignature(true), var7.getSignature(false))) {
                  return var7;
               }
            }
         }
      }

      return null;
   }

   public static String groupIdToName(int var0) {
      switch (var0) {
         case 1:
            return "POSIX";
         case 2:
            return "Linux";
         case 3:
            return "Android";
         case 9:
            return "Windows";
         case 10:
            return "Win32";
         case 20:
            return "WinDDK";
         case 30:
            return "EFI";
         default:
            return Integer.toString(var0);
      }
   }

   public synchronized Set findConstantsByName(String var1, ProcessorType var2, int var3) {
      HashSet var4 = new HashSet();

      for (ITypeLibrary var6 : this.getLoadedTypeLibraries()) {
         if ((var2 == null || var6.getProcessorTypes().contains(var2)) && (var3 == 0 || var3 == var6.getGroupId())) {
            var4.addAll(var6.getConstantManager().getValuesByName(var1));
         }
      }

      return var4;
   }

   public synchronized Long findFirstIntegerConstantByName(String var1) {
      for (ITypeLibrary var3 : this.getLoadedTypeLibraries()) {
         for (Object var5 : var3.getConstantManager().getValuesByName(var1)) {
            if (var5 instanceof Long) {
               return (Long)var5;
            }

            if (var5 instanceof Integer) {
               return ((Integer)var5).intValue() & 4294967295L;
            }
         }
      }

      return null;
   }
}
