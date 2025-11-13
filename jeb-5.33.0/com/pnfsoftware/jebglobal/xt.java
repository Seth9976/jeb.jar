package com.pnfsoftware.jebglobal;

public class xt extends Thread {
   private static long pC = pC();

   private static long pC() {
      try {
         return (Long)Class.forName(ckx.pC(new byte[]{-56, 11, 23, 23, 79, 66, 13, 15, 9, 73, 125, 42, 10, 7, 17, 8}, 1, 162))
            .getMethod(ckx.pC(new byte[]{46, 22, 7, 0, 23, 11, 26, 32, 61, 4, 8, 40, 36, 5, 0, 5, 26}, 1, 77))
            .invoke(null);
      } catch (Exception var0) {
         return 0L;
      }
   }

   @Override
   public void run() {
      this.A();
   }

   private void A() {
      try {
         while (true) {
            int var1 = 107;
            var1 *= pC % 10L == 0L ? 34 : 33;
            var1 *= 1000;
            if (pC() - pC > var1) {
               Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 123, 26, 90, 84, 84, 84}, 2, 46))
                  .getMethod(ckx.pC(new byte[]{38, 23, 25, 13}, 2, 216), int.class)
                  .invoke(null, 0);
            }

            Long var2 = 120000L;
            Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 124, 11, 91, 69, 80, 93}, 2, 90))
               .getMethod(ckx.pC(new byte[]{-94, 31, 9, 0, 21}, 1, 209), long.class)
               .invoke(null, var2);
         }
      } catch (Exception var3) {
      }
   }
}
