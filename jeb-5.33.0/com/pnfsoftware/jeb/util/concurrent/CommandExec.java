package com.pnfsoftware.jeb.util.concurrent;

import java.util.Collection;

public class CommandExec {
   private long timeout;
   private byte[] output;

   public CommandExec(long var1) {
      this.timeout = var1;
   }

   public byte[] getOutput() {
      return this.output;
   }

   public boolean execute(Collection var1) {
      String[] var2 = (String[])var1.toArray(new String[var1.size()]);
      return this.execute(var2);
   }

   public boolean execute(String... var1) {
      this.output = null;
      CommandRoutine var2 = new CommandRoutine(var1);
      if (this.timeout == 0L) {
         var2.run();
      } else {
         if (this.timeout <= 0L) {
            Thread var5 = ThreadUtil.start(var2);

            try {
               var5.join(-this.timeout);
            } catch (InterruptedException var4) {
            }

            this.output = var2.getExecutionOutput();
            return true;
         }

         Thread var3 = ThreadUtil.start(var2);
         ThreadUtil.monitor(var3, new CommandExec$1(this, this.timeout, 100L, var2));
      }

      this.output = var2.getExecutionOutput();
      return var2.getExecutionResult();
   }
}
