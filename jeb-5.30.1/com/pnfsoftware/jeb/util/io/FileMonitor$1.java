package com.pnfsoftware.jeb.util.io;

import java.io.File;
import java.io.IOException;

class FileMonitor$1 implements Runnable {
   FileMonitor$1(FileMonitor var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      File var1;
      if (this.this$0.singlefile) {
         var1 = new File(this.this$0.folder, this.this$0.filename);
      } else {
         String var2 = this.this$0.folder.getPath() + File.separatorChar + this.this$0.filename;
         int var3 = 0;
         String var4 = var2;

         while ((var1 = new File(var4)).exists() && var3 < 100000) {
            var4 = var2 + "_" + ++var3;
         }
      }

      try {
         var1.createNewFile();
         var1.deleteOnExit();
      } catch (IOException var6) {
         return;
      }

      while (true) {
         var1.setLastModified(System.currentTimeMillis());

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var5) {
            var1.delete();
            return;
         }
      }
   }
}
