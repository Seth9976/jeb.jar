package com.pnfsoftware.jebglobal;

public class Qc extends Thread {
   private int pC;

   public Qc(int var1) {
      if (var1 < 0) {
         var1 = 0;
      }

      this.pC = var1;
   }

   @Override
   public void run() {
      try {
         Thread.sleep(this.pC * 1000);
      } catch (InterruptedException var1) {
      }
   }
}
