package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

class LogRedirectionOutputStream extends Writer {
   private static final ILogger logger = GlobalLog.getLogger(LogRedirectionOutputStream.class);
   private int logLevel;
   private StringBuilder current;

   public LogRedirectionOutputStream() {
      this(60, false);
   }

   public LogRedirectionOutputStream(int var1, boolean var2) {
      this.logLevel = var1;
      if (var2) {
         this.current = new StringBuilder();
      }
   }

   @Override
   public void write(char[] var1, int var2, int var3) throws IOException {
      if (var3 > 0) {
         String var4 = new String(Arrays.copyOfRange(var1, var2, var2 + var3));
         if (var4.isEmpty()) {
            return;
         }

         if (this.logLevel != Integer.MAX_VALUE) {
            logger.log(this.logLevel, true, var4);
         }

         if (this.current != null) {
            synchronized (this.current) {
               this.current.append(var4);
            }
         }
      }
   }

   @Override
   public void flush() throws IOException {
   }

   @Override
   public void close() throws IOException {
   }

   public String pullCurrent() {
      if (this.current == null) {
         throw new IllegalStateException();
      } else {
         synchronized (this.current) {
            String var2 = this.current.toString();
            this.current.setLength(0);
            return var2;
         }
      }
   }
}
