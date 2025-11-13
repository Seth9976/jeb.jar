package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

class CommandRoutine implements Runnable {
   public static final ILogger logger = GlobalLog.getLogger(CommandRoutine.class);
   private String[] cmdarray;
   private boolean success;
   private Object lock = new Object();
   private ByteArrayOutputStream resultBytes;
   private Process process;

   public CommandRoutine(String... var1) {
      this.cmdarray = var1;
   }

   @Override
   public void run() {
      this.execute(this.cmdarray);
   }

   public boolean getExecutionResult() {
      return this.success;
   }

   public byte[] getExecutionOutput() {
      synchronized (this.lock) {
         return this.resultBytes == null ? null : this.resultBytes.toByteArray();
      }
   }

   public void killProcess() {
      if (this.process != null) {
         this.process.destroy();
      }
   }

   private boolean execute(String... var1) {
      this.success = this.executeSync(var1);
      return this.success;
   }

   private boolean executeSync(String... var1) {
      try {
         ProcessBuilder var2 = new ProcessBuilder(var1);
         var2.redirectErrorStream(true);
         this.process = var2.start();
      } catch (IOException var5) {
         logger.catching(var5);
         return false;
      }

      synchronized (this.lock) {
         this.resultBytes = new ByteArrayOutputStream();
      }

      return this.manageStreams(this.process, this.resultBytes);
   }

   private boolean manageStreams(Process param1, ByteArrayOutputStream param2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 00: aload 1
      // 01: invokevirtual java/lang/Process.getOutputStream ()Ljava/io/OutputStream;
      // 04: astore 3
      // 05: aload 3
      // 06: ifnull 0d
      // 09: aload 3
      // 0a: invokevirtual java/io/OutputStream.close ()V
      // 0d: goto 1c
      // 10: astore 3
      // 11: getstatic com/pnfsoftware/jeb/util/concurrent/CommandRoutine.logger Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 14: aload 3
      // 15: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catching (Ljava/lang/Throwable;)V 2
      // 1a: bipush 0
      // 1b: ireturn
      // 1c: aload 1
      // 1d: invokevirtual java/lang/Process.getInputStream ()Ljava/io/InputStream;
      // 20: astore 3
      // 21: sipush 16384
      // 24: newarray 8
      // 26: astore 5
      // 28: aload 3
      // 29: invokevirtual java/io/InputStream.available ()I
      // 2c: istore 4
      // 2e: iload 4
      // 30: ifne 61
      // 33: invokestatic java/lang/Thread.interrupted ()Z
      // 36: istore 6
      // 38: iload 6
      // 3a: ifne 4a
      // 3d: ldc2_w 100
      // 40: invokestatic java/lang/Thread.sleep (J)V
      // 43: goto 4a
      // 46: pop
      // 47: bipush 1
      // 48: istore 6
      // 4a: iload 6
      // 4c: ifeq 61
      // 4f: aload 1
      // 50: invokevirtual java/lang/Process.destroy ()V
      // 53: bipush 0
      // 54: istore 7
      // 56: aload 3
      // 57: ifnull 5e
      // 5a: aload 3
      // 5b: invokevirtual java/io/InputStream.close ()V
      // 5e: iload 7
      // 60: ireturn
      // 61: aload 3
      // 62: aload 5
      // 64: bipush 0
      // 65: aload 5
      // 67: arraylength
      // 68: invokevirtual java/io/InputStream.read ([BII)I
      // 6b: istore 4
      // 6d: invokestatic java/lang/Thread.interrupted ()Z
      // 70: istore 6
      // 72: iload 4
      // 74: bipush -1
      // 75: if_icmpne 8f
      // 78: iload 6
      // 7a: ifne 81
      // 7d: bipush 1
      // 7e: goto 82
      // 81: bipush 0
      // 82: istore 7
      // 84: aload 3
      // 85: ifnull 8c
      // 88: aload 3
      // 89: invokevirtual java/io/InputStream.close ()V
      // 8c: iload 7
      // 8e: ireturn
      // 8f: aload 0
      // 90: getfield com/pnfsoftware/jeb/util/concurrent/CommandRoutine.lock Ljava/lang/Object;
      // 93: dup
      // 94: astore 7
      // 96: monitorenter
      // 97: aload 2
      // 98: aload 5
      // 9a: bipush 0
      // 9b: iload 4
      // 9d: invokevirtual java/io/ByteArrayOutputStream.write ([BII)V
      // a0: aload 2
      // a1: invokevirtual java/io/ByteArrayOutputStream.flush ()V
      // a4: aload 7
      // a6: monitorexit
      // a7: goto b2
      // aa: astore 8
      // ac: aload 7
      // ae: monitorexit
      // af: aload 8
      // b1: athrow
      // b2: iload 6
      // b4: ifeq c5
      // b7: bipush 0
      // b8: istore 7
      // ba: aload 3
      // bb: ifnull c2
      // be: aload 3
      // bf: invokevirtual java/io/InputStream.close ()V
      // c2: iload 7
      // c4: ireturn
      // c5: goto 28
      // c8: astore 4
      // ca: aload 3
      // cb: ifnull de
      // ce: aload 3
      // cf: invokevirtual java/io/InputStream.close ()V
      // d2: goto de
      // d5: astore 5
      // d7: aload 4
      // d9: aload 5
      // db: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // de: aload 4
      // e0: athrow
      // e1: astore 3
      // e2: getstatic com/pnfsoftware/jeb/util/concurrent/CommandRoutine.logger Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // e5: aload 3
      // e6: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catching (Ljava/lang/Throwable;)V 2
      // eb: bipush 0
      // ec: ireturn
   }
}
