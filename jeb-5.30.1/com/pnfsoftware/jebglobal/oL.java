package com.pnfsoftware.jebglobal;

public class oL extends Thread {
   private int q;

   public oL(int var1) {
      if (var1 < 0) {
         var1 = 0;
      }

      this.q = var1;
   }

   @Override
   public void run() {
      try {
         Thread.sleep(this.q * 1000);
      } catch (InterruptedException var1) {
      }
   }
}
