package com.pnfsoftware.jebglobal;

import java.io.File;
import java.lang.reflect.Method;

public class Bu {
   private static File RF(Class var0) throws Exception {
      Object var1 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 107, 15, 72, 83, 66}, 2, 26))
         .getMethod(cvm.q(new byte[]{36, 10, 4, 41, 0, 6, 19, 13, 23, 84, 65, 12, 71, 100, 94, 84, 88, 90, 66}, 2, 208))
         .invoke(var0);
      Object var2 = Class.forName(
            cvm.q(new byte[]{50, 11, 23, 23, 79, 93, 22, 6, 22, 7, 27, 29, 13, 87, 126, 34, 29, 27, 17, 6, 23, 29, 6, 1, 42, 43, 2, 12, 8, 7}, 1, 88)
         )
         .getMethod(cvm.q(new byte[]{-113, 2, 17, 55, 44, 11, 1, 54, 60, 26, 7, 17, 6}, 1, 232))
         .invoke(var1);
      Object var3 = Class.forName(cvm.q(new byte[]{-13, 11, 23, 23, 79, 93, 22, 6, 22, 7, 27, 29, 13, 87, 109, 44, 11, 1, 54, 60, 26, 7, 17, 6}, 1, 153))
         .getMethod(cvm.q(new byte[]{26, 2, 17, 56, 35, 12, 2, 21, 29, 6, 1}, 1, 125))
         .invoke(var2);
      Object var4 = Class.forName(
            cvm.q(
               new byte[]{
                  -120, 12, 2, 67, 94, 30, 8, 21, 28, 9, 18, 3, 22, 19, 23, 75, 68, 15, 7, 76, 91, 1, 29, 5, 66, 64, 11, 17, 90, 123, 7, 30, 25, 33, 29, 5
               },
               1,
               235
            )
         )
         .getMethod(cvm.q(new byte[]{23, 7, 30, 56, 59, 41, 47, 5, 9}, 1, 98), Class.forName("java.net.URL"))
         .invoke(null, var3);
      return (File)var4;
   }

   public static boolean q(Class var0) {
      boolean var1 = false;

      try {
         File var2 = RF(var0);
         var1 = q(var2, var0);
      } catch (Exception var3) {
      }

      return var1;
   }

   private static boolean q(Object var0, Class var1) {
      boolean var2 = false;

      try {
         Object var3 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 155))
            .getConstructor(Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 0, 8, 70, 50, 73, 68, 6}, 2, 8)), boolean.class)
            .newInstance(var0, true);
         byte[] var4 = new byte[4096];
         String var5 = var1.getName().replace('.', '/') + ".class";
         Object var6 = Class.forName(cvm.q(new byte[]{120, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 18))
            .getMethod(cvm.q(new byte[]{36, 10, 4, 60, 28, 29, 21, 17}, 2, 127), String.class)
            .invoke(var3, var5);
         Object var7 = Class.forName(cvm.q(new byte[]{30, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 116))
            .getMethod(
               cvm.q(new byte[]{-89, 2, 17, 61, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 192),
               Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 82, 10, 89, 14, 107, 80, 73, 118, 66, 84, 64, 73}, 2, 109))
            )
            .invoke(var3, var6);
         Method var8 = Class.forName(cvm.q(new byte[]{-9, 11, 23, 23, 79, 71, 6, 65, 103, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 157))
            .getMethod(cvm.q(new byte[]{119, 23, 4, 5}, 1, 5), byte[].class, int.class, int.class);

         while (var8.invoke(var7, var4, 0, var4.length) != -1) {
         }

         Object[] var9 = (Object[])Class.forName(
               cvm.q(new byte[]{-124, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 55, 43, 26, 6, 11}, 1, 238)
            )
            .getMethod(cvm.q(new byte[]{36, 10, 4, 58, 23, 27, 19, 1, 18, 73, 75, 2, 93, 69, 66}, 2, 43))
            .invoke(var6);
         Object var10 = Class.forName(
               cvm.q(new byte[]{-76, 11, 23, 23, 79, 93, 22, 6, 22, 7, 27, 29, 13, 87, 77, 6, 23, 6, 90, 109, 38, 23, 6, 29, 15, 15, 10, 2, 21, 17}, 1, 222)
            )
            .getMethod(cvm.q(new byte[]{1, 2, 17, 36, 37, 23, 14, 5, 10, 40, 46, 28}, 1, 102))
            .invoke(var9[0]);
         Object var11 = Class.forName(
               cvm.q(
                  new byte[]{
                     66,
                     11,
                     23,
                     23,
                     79,
                     93,
                     22,
                     6,
                     22,
                     7,
                     27,
                     29,
                     13,
                     87,
                     71,
                     7,
                     26,
                     17,
                     23,
                     20,
                     7,
                     2,
                     6,
                     22,
                     93,
                     124,
                     1,
                     18,
                     17,
                     37,
                     23,
                     14,
                     5,
                     10,
                     40,
                     46,
                     28
                  },
                  1,
                  40
               )
            )
            .getMethod(cvm.q(new byte[]{60, 2, 17, 57, 34, 11, 17, 25, 25, 6}, 1, 91))
            .invoke(var10);
         var2 = var11.toString().substring(10).substring(15, 30).equals(cvm.q(new byte[]{-35, 8, 3, 7, 2, 5, 2, 3, 4, 0, 0, 5, 5, 1, 14}, 1, 228));
         Class.forName(cvm.q(new byte[]{-104, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 242))
            .getMethod(cvm.q(new byte[]{-85, 15, 3, 28, 22}, 1, 200))
            .invoke(var3);
      } catch (Exception var12) {
      }

      return var2;
   }

   public static boolean q(Class[] var0) {
      if (var0.length == 0) {
         return true;
      } else {
         boolean var1 = false;

         try {
            File var2 = RF(var0[0]);
            var1 = q(var2, var0);
         } catch (Exception var3) {
         }

         return var1;
      }
   }

   private static boolean q(Object var0, Class[] var1) {
      boolean var2 = false;

      try {
         Object var3 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 51))
            .getConstructor(Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 0, 8, 70, 50, 73, 68, 6}, 2, 255)), boolean.class)
            .newInstance(var0, true);
         byte[] var4 = new byte[4096];

         for (Class var8 : var1) {
            String var9 = var8.getName().replace('.', '/') + ".class";
            Object var10 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 226))
               .getMethod(cvm.q(new byte[]{-7, 2, 17, 49, 43, 26, 6, 11}, 1, 158), String.class)
               .invoke(var3, var9);
            Object var11 = Class.forName(cvm.q(new byte[]{-23, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 131))
               .getMethod(
                  cvm.q(new byte[]{116, 2, 17, 61, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 19),
                  Class.forName(cvm.q(new byte[]{53, 11, 23, 23, 79, 91, 1, 29, 5, 66, 84, 19, 25, 94, 116, 51, 25, 53, 43, 26, 6, 11}, 1, 95))
               )
               .invoke(var3, var10);
            Method var12 = Class.forName(cvm.q(new byte[]{6, 11, 23, 23, 79, 71, 6, 65, 103, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 108))
               .getMethod(cvm.q(new byte[]{74, 23, 4, 5}, 1, 56), byte[].class, int.class, int.class);

            while (var12.invoke(var11, var4, 0, var4.length) != -1) {
            }

            Object[] var13 = (Object[])Class.forName(
                  cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 118, 66, 84, 64, 73}, 2, 65)
               )
               .getMethod(cvm.q(new byte[]{25, 2, 17, 55, 38, 23, 6, 29, 15, 15, 10, 2, 21, 17, 22}, 1, 126))
               .invoke(var10);
            Object var14 = Class.forName(
                  cvm.q(new byte[]{-36, 11, 23, 23, 79, 93, 22, 6, 22, 7, 27, 29, 13, 87, 77, 6, 23, 6, 90, 109, 38, 23, 6, 29, 15, 15, 10, 2, 21, 17}, 1, 182)
               )
               .getMethod(cvm.q(new byte[]{36, 10, 4, 41, 7, 11, 11, 1, 23, 107, 77, 26}, 2, 232))
               .invoke(var13[0]);
            Object var15 = Class.forName(
                  cvm.q(
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
                        71,
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
                     239
                  )
               )
               .getMethod(cvm.q(new byte[]{36, 10, 4, 52, 29, 13, 18, 4, 1, 83}, 2, 230))
               .invoke(var14);
            var2 = var15.toString().substring(10).substring(15, 30).equals(cvm.q(new byte[]{14, 8, 3, 7, 2, 5, 2, 3, 4, 0, 0, 5, 5, 1, 14}, 1, 55));
            if (!var2) {
               break;
            }
         }

         Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 186))
            .getMethod(cvm.q(new byte[]{32, 3, 31, 10, 23}, 2, 24))
            .invoke(var3);
      } catch (Exception var16) {
      }

      return var2;
   }

   public static long RF(Class[] var0) {
      if (var0.length == 0) {
         return 0L;
      } else {
         long var1 = 0L;

         try {
            File var3 = RF(var0[0]);
            var1 = RF(var3, var0);
         } catch (Exception var4) {
         }

         return var1;
      }
   }

   private static long RF(Object var0, Class[] var1) {
      long var2 = 0L;

      try {
         Object var4 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 40))
            .getConstructor(Class.forName(cvm.q(new byte[]{-123, 11, 23, 23, 79, 71, 6, 65, 104, 47, 5, 9}, 1, 239)), boolean.class)
            .newInstance(var0, true);
         byte[] var5 = new byte[4096];
         int var6 = 0;

         for (Class var10 : var1) {
            String var11 = var10.getName().replace('.', '/') + ".class";
            Object var12 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 17))
               .getMethod(cvm.q(new byte[]{36, 10, 4, 60, 28, 29, 21, 17}, 2, 99), String.class)
               .invoke(var4, var11);
            Object var13 = Class.forName(cvm.q(new byte[]{-98, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 244))
               .getMethod(
                  cvm.q(new byte[]{86, 2, 17, 61, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 49),
                  Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 82, 10, 89, 14, 107, 80, 73, 118, 66, 84, 64, 73}, 2, 229))
               )
               .invoke(var4, var12);
            Method var14 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 0, 8, 70, 61, 78, 88, 22, 93, 115, 69, 75, 92, 82, 65}, 2, 125))
               .getMethod(cvm.q(new byte[]{49, 10, 17, 29}, 2, 140), byte[].class, int.class, int.class);

            while (var14.invoke(var13, var5, 0, var5.length) != -1) {
            }

            Object[] var15 = (Object[])Class.forName(
                  cvm.q(new byte[]{19, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 55, 43, 26, 6, 11}, 1, 121)
               )
               .getMethod(cvm.q(new byte[]{36, 10, 4, 58, 23, 27, 19, 1, 18, 73, 75, 2, 93, 69, 66}, 2, 6))
               .invoke(var12);
            Object var16 = Class.forName(
                  cvm.q(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 82, 92, 75, 71, 2, 99, 87, 66, 70, 93, 74, 73, 44, 19, 21, 6}, 2, 169)
               )
               .getMethod(cvm.q(new byte[]{-30, 2, 17, 36, 37, 23, 14, 5, 10, 40, 46, 28}, 1, 133))
               .invoke(var15[0]);
            Object var17 = Class.forName(
                  cvm.q(
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
                        71,
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
                     17
                  )
               )
               .getMethod(cvm.q(new byte[]{36, 10, 4, 52, 29, 13, 18, 4, 1, 83}, 2, 254))
               .invoke(var16);
            boolean var18 = var17.toString().substring(10).substring(15, 30).equals(cvm.q(new byte[]{-115, 8, 3, 7, 2, 5, 2, 3, 4, 0, 0, 5, 5, 1, 14}, 1, 180));
            if (var18) {
               var2 |= 1L << var6;
            }

            if (++var6 >= 64) {
               break;
            }
         }

         Class.forName(cvm.q(new byte[]{-98, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 244))
            .getMethod(cvm.q(new byte[]{32, 3, 31, 10, 23}, 2, 50))
            .invoke(var4);
      } catch (Exception var19) {
      }

      return var2;
   }
}
