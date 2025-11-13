package com.pnfsoftware.jebglobal;

public class Vj extends Thread {
   private static long q = q();

   private static long q() {
      try {
         return (Long)Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 123, 26, 90, 84, 84, 84}, 2, 238))
            .getMethod(cvm.q(new byte[]{32, 26, 2, 11, 23, 7, 19, 60, 29, 77, 77, 46, 64, 76, 93, 80, 74}, 2, 122))
            .invoke(null);
      } catch (Exception var0) {
         return 0L;
      }
   }

   @Override
   public void run() {
      this.RF();
   }

   private void RF() {
      try {
         while (true) {
            int var1 = 107;
            var1 *= q % 10L == 0L ? 34 : 33;
            var1 *= 1000;
            if (q() - q > var1) {
               Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 123, 26, 90, 84, 84, 84}, 2, 187))
                  .getMethod(cvm.q(new byte[]{97, 29, 17, 29}, 1, 4), int.class)
                  .invoke(null, 0);
            }

            Long var2 = 120000L;
            Class.forName(cvm.q(new byte[]{-68, 11, 23, 23, 79, 66, 13, 15, 9, 73, 122, 60, 26, 23, 4, 5}, 1, 214))
               .getMethod(cvm.q(new byte[]{-19, 31, 9, 0, 21}, 1, 158), long.class)
               .invoke(null, var2);
         }
      } catch (Exception var3) {
      }
   }
}
