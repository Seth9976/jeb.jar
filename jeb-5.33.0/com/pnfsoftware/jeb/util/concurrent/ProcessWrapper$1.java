package com.pnfsoftware.jeb.util.concurrent;

import java.util.ArrayList;

class ProcessWrapper$1 extends Thread {
   @Override
   public void run() {
      if (ProcessWrapper.killSpawnedProcessesOnShutdown) {
         synchronized (ProcessWrapper.processWrappers) {
            for (ProcessWrapper var3 : new ArrayList(ProcessWrapper.processWrappers)) {
               try {
                  if (var3.isAlive()) {
                     var3.kill();
                  }
               } catch (Exception var6) {
                  ProcessWrapper.logger.catchingSilent(var6);
               }
            }
         }
      }
   }
}
