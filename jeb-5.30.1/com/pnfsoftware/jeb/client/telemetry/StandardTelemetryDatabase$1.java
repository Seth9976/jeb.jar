package com.pnfsoftware.jeb.client.telemetry;

import java.util.Random;

class StandardTelemetryDatabase$1 implements Runnable {
   StandardTelemetryDatabase$1(StandardTelemetryDatabase var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      int var1 = 10 + new Random().nextInt(11);

      try {
         Thread.sleep(var1 * 1000L);
      } catch (InterruptedException var5) {
         return;
      }

      if (this.this$0.connection != null) {
         try {
            this.this$0.dump();
         } catch (Exception var4) {
            StandardTelemetryDatabase.logger.catchingSilent(var4);
         }

         while (true) {
            try {
               Thread.sleep(this.this$0.dumpPeriodSeconds * 1000L);
            } catch (InterruptedException var6) {
               break;
            }

            if (this.this$0.connection == null) {
               break;
            }

            try {
               this.this$0.dump();
            } catch (Exception var3) {
               StandardTelemetryDatabase.logger.catchingSilent(var3);
            }
         }
      }
   }
}
