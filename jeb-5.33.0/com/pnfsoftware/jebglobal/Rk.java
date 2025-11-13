package com.pnfsoftware.jebglobal;

public class Rk {
   private static Thread pC;

   public static synchronized void pC() {
      if (pC == null) {
         pC = new Qc(120);
         pC.start();
      }
   }

   public static synchronized void A() {
      if (pC != null) {
         pC.interrupt();

         try {
            pC.join(5000L);
         } catch (InterruptedException var0) {
            return;
         }

         pC = null;
      }
   }
}
