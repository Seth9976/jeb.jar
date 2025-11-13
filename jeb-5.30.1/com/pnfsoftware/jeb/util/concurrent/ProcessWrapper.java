package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProcessWrapper {
   private static final ILogger logger = GlobalLog.getLogger(ProcessWrapper.class);
   private static boolean killSpawnedProcessesOnShutdown = true;
   private static List processWrappers = Collections.synchronizedList(new ArrayList());
   private long timeout;
   private String[] cmdarray;
   private Process process;
   private OutputStream in;
   private InputStream out;
   private InputStream err;
   private Integer retcode;

   public static boolean isKillSpawnedProcessesOnShutdown() {
      return killSpawnedProcessesOnShutdown;
   }

   public static void setKillSpawnedProcessesOnShutdown(boolean var0) {
      killSpawnedProcessesOnShutdown = var0;
   }

   public ProcessWrapper(String... var1) {
      this(-1L, var1);
   }

   public ProcessWrapper(long var1, String... var3) {
      this.timeout = var1;
      this.cmdarray = var3;
   }

   public synchronized ProcessWrapper start() throws IOException {
      Object[] var10000 = new Object[]{Strings.join(" ", Arrays.asList(this.cmdarray))};
      ProcessBuilder var1 = new ProcessBuilder(this.cmdarray);
      var1.redirectErrorStream(true);
      this.process = var1.start();
      this.in = this.process.getOutputStream();
      this.out = this.process.getInputStream();
      this.err = this.process.getErrorStream();
      processWrappers.add(this);
      if (this.timeout > 0L) {
         ThreadUtil.start(new ProcessWrapper$2(this));
      }

      return this;
   }

   public synchronized void kill() {
      processWrappers.remove(this);
      if (this.isAlive()) {
         this.process.destroy();

         try {
            waitFor(this.process, 500L, TimeUnit.MILLISECONDS);
         } catch (InterruptedException var1) {
         }
      }

      if (!this.isAlive()) {
         this.retcode = this.process.exitValue();
      }
   }

   public void waitForCompletion() {
      try {
         this.process.waitFor();
      } catch (InterruptedException var1) {
      }

      this.kill();
   }

   public synchronized boolean isAlive() {
      return this.process != null && isAlive(this.process);
   }

   public synchronized OutputStream getProcessInput() {
      return this.in;
   }

   public synchronized InputStream getProcessOutput() {
      return this.out;
   }

   public synchronized InputStream getProcessError() {
      return this.err;
   }

   public synchronized Integer getReturnCode() {
      return this.retcode;
   }

   @Override
   public String toString() {
      return Strings.ff("Executing=%b,Command={%s}", this.process != null, Arrays.toString((Object[])this.cmdarray));
   }

   static boolean waitFor(Process var0, long var1, TimeUnit var3) throws InterruptedException {
      long var4 = System.nanoTime();
      long var6 = var3.toNanos(var1);

      while (true) {
         try {
            var0.exitValue();
            return true;
         } catch (IllegalThreadStateException var8) {
            if (var6 > 0L) {
               Thread.sleep(Math.min(TimeUnit.NANOSECONDS.toMillis(var6) + 1L, 100L));
            }

            var6 = var3.toNanos(var1) - (System.nanoTime() - var4);
            if (var6 <= 0L) {
               return false;
            }
         }
      }
   }

   static boolean isAlive(Process var0) {
      try {
         var0.exitValue();
         return false;
      } catch (IllegalThreadStateException var1) {
         return true;
      }
   }

   static {
      Runtime.getRuntime().addShutdownHook(new ProcessWrapper$1());
   }
}
