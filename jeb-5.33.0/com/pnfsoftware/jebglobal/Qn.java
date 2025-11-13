package com.pnfsoftware.jebglobal;

import java.io.File;
import java.lang.reflect.Method;

public class Qn {
   private static File pC(Class var0) throws Exception {
      Object var1 = Class.forName(ckx.pC(new byte[]{61, 11, 23, 23, 79, 66, 13, 15, 9, 73, 109, 47, 13, 18, 0}, 1, 87))
         .getMethod(ckx.pC(new byte[]{97, 2, 17, 36, 34, 29, 27, 17, 6, 23, 29, 6, 1, 42, 43, 2, 12, 8, 7}, 1, 6))
         .invoke(var0);
      Object var2 = Class.forName(
            ckx.pC(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 97, 75, 86, 71, 73, 67, 70, 89, 93, 91, 104, 79, 34, 19, 8, 13}, 2, 7)
         )
         .getMethod(ckx.pC(new byte[]{8, 2, 17, 55, 44, 11, 1, 54, 60, 26, 7, 17, 6}, 1, 111))
         .invoke(var1);
      Object var3 = Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 114, 86, 93, 86, 127, 79, 71, 66, 81, 80}, 2, 159))
         .getMethod(ckx.pC(new byte[]{49, 2, 17, 56, 35, 12, 2, 21, 29, 6, 1}, 1, 86))
         .invoke(var2);
      Object var4 = Class.forName(
            ckx.pC(
               new byte[]{
                  32, 0, 29, 87, 2, 7, 1, 27, 27, 70, 92, 20, 72, 82, 84, 23, 83, 86, 78, 14, 71, 68, 91, 89, 2, 78, 42, 6, 79, 54, 62, 41, 117, 21, 7, 8
               },
               2,
               241
            )
         )
         .getMethod(ckx.pC(new byte[]{127, 7, 30, 56, 59, 41, 47, 5, 9}, 1, 10), Class.forName("java.net.URL"))
         .invoke(null, var3);
      return (File)var4;
   }

   public static boolean pC(Class[] var0) {
      if (var0.length == 0) {
         return true;
      } else {
         boolean var1 = false;

         try {
            File var2 = pC(var0[0]);
            var1 = pC(var2, var0);
         } catch (Exception var3) {
         }

         return var1;
      }
   }

   private static boolean pC(Object var0, Class[] var1) {
      boolean var2 = false;

      try {
         Object var3 = Class.forName(ckx.pC(new byte[]{-14, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 152))
            .getConstructor(Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 0, 8, 70, 50, 73, 68, 6}, 2, 108)), boolean.class)
            .newInstance(var0, true);
         byte[] var4 = new byte[4096];

         for (Class var8 : var1) {
            String var9 = var8.getName().replace('.', '/') + ".class";
            Object var10 = Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 130))
               .getMethod(ckx.pC(new byte[]{99, 2, 17, 49, 43, 26, 6, 11}, 1, 4), String.class)
               .invoke(var3, var9);
            Object var11 = Class.forName(ckx.pC(new byte[]{-12, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 158))
               .getMethod(
                  ckx.pC(new byte[]{36, 10, 4, 48, 28, 25, 18, 28, 39, 84, 90, 6, 72, 77}, 2, 90),
                  Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 82, 10, 89, 14, 107, 80, 73, 118, 66, 84, 64, 73}, 2, 23))
               )
               .invoke(var3, var10);
            Method var12 = Class.forName(ckx.pC(new byte[]{41, 11, 23, 23, 79, 71, 6, 65, 103, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 67))
               .getMethod(ckx.pC(new byte[]{49, 10, 17, 29}, 2, 152), byte[].class, int.class, int.class);

            while (var12.invoke(var11, var4, 0, var4.length) != -1) {
            }

            Object[] var13 = (Object[])Class.forName(
                  ckx.pC(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 118, 66, 84, 64, 73}, 2, 105)
               )
               .getMethod(ckx.pC(new byte[]{-49, 2, 17, 55, 38, 23, 6, 29, 15, 15, 10, 2, 21, 17, 22}, 1, 168))
               .invoke(var10);
            Object var14 = Class.forName(
                  ckx.pC(
                     new byte[]{-126, 11, 23, 23, 79, 93, 22, 6, 22, 7, 27, 29, 13, 87, 77, 6, 23, 6, 90, 109, 38, 23, 6, 29, 15, 15, 10, 2, 21, 17}, 1, 232
                  )
               )
               .getMethod(ckx.pC(new byte[]{32, 2, 17, 36, 37, 23, 14, 5, 10, 40, 46, 28}, 1, 71))
               .invoke(var13[0]);
            Object var15 = Class.forName(
                  ckx.pC(
                     new byte[]{
                        41,
                        14,
                        6,
                        24,
                        92,
                        26,
                        2,
                        11,
                        1,
                        82,
                        65,
                        23,
                        80,
                        14,
                        88,
                        87,
                        77,
                        86,
                        94,
                        70,
                        83,
                        83,
                        87,
                        70,
                        2,
                        114,
                        28,
                        51,
                        49,
                        22,
                        14,
                        9,
                        73,
                        2,
                        37,
                        1,
                        86
                     },
                     2,
                     172
                  )
               )
               .getMethod(ckx.pC(new byte[]{36, 10, 4, 52, 29, 13, 18, 4, 1, 83}, 2, 203))
               .invoke(var14);
            var2 = var15.toString()
               .substring(10)
               .substring(15, 30)
               .equals(ckx.pC(new byte[]{122, 94, 66, 76, 69, 91, 87, 91, 67, 23, 31, 81, 30, 22, 9}, 2, 209));
            if (!var2) {
               break;
            }
         }

         Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 52))
            .getMethod(ckx.pC(new byte[]{32, 3, 31, 10, 23}, 2, 57))
            .invoke(var3);
      } catch (Exception var16) {
      }

      return var2;
   }
}
