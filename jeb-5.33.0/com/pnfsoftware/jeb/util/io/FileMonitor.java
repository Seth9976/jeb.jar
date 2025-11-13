package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileMonitor {
   public static final long TOUCH_MS = 1000L;
   public static final long CHECK_MS = 3000L;
   private static Map map = new HashMap();
   private File folder;
   private String filename;
   private boolean singlefile;
   private boolean cleanStaleFiles = true;
   private Thread toucherThread;

   public static FileMonitor get(File var0) throws IOException {
      return get(var0.getParentFile(), var0.getName(), true);
   }

   public static FileMonitor get(File var0, String var1, boolean var2) throws IOException {
      return retrieve(var0, var1, var2, false);
   }

   public static FileMonitor create(File var0) throws IOException {
      return create(var0.getParentFile(), var0.getName(), true);
   }

   public static FileMonitor create(File var0, String var1, boolean var2) throws IOException {
      return retrieve(var0, var1, var2, true);
   }

   private static FileMonitor retrieve(File var0, String var1, boolean var2, boolean var3) throws IOException {
      String var4 = var0.getCanonicalPath() + ";" + var1 + ";" + var2;
      FileMonitor var5 = (FileMonitor)map.get(var4);
      if (var5 == null && var3) {
         var5 = new FileMonitor(var0, var1, var2);
         map.put(var4, var5);
      }

      return var5;
   }

   private FileMonitor(File var1, String var2, boolean var3) {
      if (!var1.isDirectory()) {
         throw new IllegalArgumentException("Folder does not exist: " + var1);
      } else if (var2 != null && !var2.isEmpty()) {
         this.folder = var1;
         this.filename = var2;
         this.singlefile = var3;
      } else {
         throw new IllegalArgumentException("Filename or prefix name cannot be null or empty");
      }
   }

   public boolean isLocked() {
      if (this.singlefile) {
         File var7 = new File(this.folder, this.filename);
         if (!var7.exists()) {
            return false;
         } else if (System.currentTimeMillis() - var7.lastModified() <= 3000L) {
            return true;
         } else {
            if (this.cleanStaleFiles) {
               var7.delete();
            }

            return false;
         }
      } else {
         boolean var1 = false;

         for (String var5 : this.folder.list()) {
            if (var5.startsWith(this.filename)) {
               File var6 = new File(this.folder, var5);
               if (System.currentTimeMillis() - var6.lastModified() <= 3000L) {
                  var1 = true;
                  if (!this.cleanStaleFiles) {
                     break;
                  }
               } else if (this.cleanStaleFiles) {
                  var6.delete();
               }
            }
         }

         return var1;
      }
   }

   public void lock() {
      if (this.toucherThread == null) {
         this.toucherThread = ThreadUtil.start("jeb-file-lock-monitor", new FileMonitor$1(this));
      }
   }

   public void unlock(boolean var1) throws InterruptedException {
      if (this.toucherThread != null) {
         this.toucherThread.interrupt();
         this.toucherThread.join();
         this.toucherThread = null;
         if (var1) {
            Maps.removeForValue(map, this, true);
         }
      }
   }

   static {
      Assert.a(true);
   }
}
