package com.pnfsoftware.jebglobal;

public class vb {
   private static Thread q;

   public static synchronized void q() {
      if (q == null) {
         q = new oL(120);
         q.start();
      }
   }

   public static synchronized void RF() {
      if (q != null) {
         q.interrupt();

         try {
            q.join(5000L);
         } catch (InterruptedException var0) {
            return;
         }

         q = null;
      }
   }
}
