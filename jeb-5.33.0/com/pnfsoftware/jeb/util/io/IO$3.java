package com.pnfsoftware.jeb.util.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class IO$3 implements Runnable {
   IO$3(InputStream var1, OutputStream var2) {
      this.val$input = var1;
      this.val$output = var2;
   }

   @Override
   public void run() {
      long var1 = 0L;

      try (
         BufferedInputStream var3 = new BufferedInputStream(this.val$input);
         BufferedOutputStream var4 = new BufferedOutputStream(this.val$output);
      ) {
         while (true) {
            byte[] var5 = new byte[4096];
            int var6 = var3.read(var5);
            if (var6 == -1) {
               var4.flush();
               IO.logger.trace("Background stream copy completed: %d bytes copied", var1);
               break;
            }

            var4.write(var5, 0, var6);
            var4.flush();
            var1 += var6;
         }
      } catch (IOException var11) {
         IO.logger.trace("Background stream copy stopped: %d bytes copied (error: %s)", var1, var11);
      }
   }
}
