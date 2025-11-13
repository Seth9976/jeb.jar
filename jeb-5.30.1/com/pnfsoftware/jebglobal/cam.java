package com.pnfsoftware.jebglobal;

public class cam {
   static char[] q(int var0) {
      return new char[var0];
   }

   static void q(char[] var0) {
   }

   public static int q(CharSequence var0, char var1) {
      return q(var0, var1, var0.length() - 1);
   }

   public static void q(CharSequence var0, int var1, int var2, char[] var3, int var4) {
      Class var5 = var0.getClass();
      if (var5 == String.class) {
         ((String)var0).getChars(var1, var2, var3, var4);
      } else if (var5 == StringBuffer.class) {
         ((StringBuffer)var0).getChars(var1, var2, var3, var4);
      } else if (var5 == StringBuilder.class) {
         ((StringBuilder)var0).getChars(var1, var2, var3, var4);
      } else {
         for (int var6 = var1; var6 < var2; var6++) {
            var3[var4++] = var0.charAt(var6);
         }
      }
   }

   public static int q(CharSequence var0, char var1, int var2) {
      Class var3 = var0.getClass();
      return var3 == String.class ? ((String)var0).lastIndexOf(var1, var2) : q(var0, var1, 0, var2);
   }

   public static int q(CharSequence var0, char var1, int var2, int var3) {
      if (var3 < 0) {
         return -1;
      } else {
         if (var3 >= var0.length()) {
            var3 = var0.length() - 1;
         }

         int var4 = var3 + 1;
         Class var5 = var0.getClass();
         if (var5 != StringBuffer.class && var5 != StringBuilder.class && var5 != String.class) {
            for (int var6 = var4 - 1; var6 >= var2; var6--) {
               if (var0.charAt(var6) == var1) {
                  return var6;
               }
            }

            return -1;
         } else {
            char[] var7 = q(500);

            while (var2 < var4) {
               int var8 = var4 - 500;
               if (var8 < var2) {
                  var8 = var2;
               }

               q(var0, var8, var4, var7, 0);
               int var9 = var4 - var8;

               for (int var10 = var9 - 1; var10 >= 0; var10--) {
                  if (var7[var10] == var1) {
                     return var10 + var8;
                  }
               }

               var4 = var8;
            }

            return -1;
         }
      }
   }

   public static int RF(CharSequence var0, char var1) {
      return RF(var0, var1, 0);
   }

   public static int RF(CharSequence var0, char var1, int var2) {
      Class var3 = var0.getClass();
      return var3 == String.class ? ((String)var0).indexOf(var1, var2) : RF(var0, var1, var2, var0.length());
   }

   public static int RF(CharSequence var0, char var1, int var2, int var3) {
      Class var4 = var0.getClass();
      if (var4 != StringBuffer.class && var4 != StringBuilder.class && var4 != String.class) {
         for (int var5 = var2; var5 < var3; var5++) {
            if (var0.charAt(var5) == var1) {
               return var5;
            }
         }

         return -1;
      } else {
         char[] var6 = q(500);

         while (var2 < var3) {
            int var7 = var2 + 500;
            if (var7 > var3) {
               var7 = var3;
            }

            q(var0, var2, var7, var6, 0);
            int var8 = var7 - var2;

            for (int var9 = 0; var9 < var8; var9++) {
               if (var6[var9] == var1) {
                  return var9 + var2;
               }
            }

            var2 = var7;
         }

         return -1;
      }
   }

   public static int q(CharSequence var0, CharSequence var1) {
      return q(var0, var1, 0, var0.length());
   }

   public static int q(CharSequence var0, CharSequence var1, int var2) {
      return q(var0, var1, var2, var0.length());
   }

   public static int q(CharSequence var0, CharSequence var1, int var2, int var3) {
      int var4 = var1.length();
      if (var4 == 0) {
         byte var8;
         return var8;
      } else {
         char var5 = var1.charAt(0);

         while (true) {
            var2 = RF(var0, var5, var2);
            if (var2 > var3 - var4) {
               return -1;
            }

            if (var2 < 0) {
               return -1;
            }

            if (q(var0, var2, var1, 0, var4)) {
               return var2;
            }

            var2++;
         }
      }
   }

   public static boolean q(CharSequence var0, int var1, CharSequence var2, int var3, int var4) {
      int var5 = 2 * var4;
      if (var5 < var4) {
         throw new IndexOutOfBoundsException();
      } else {
         char[] var6 = q(var5);
         q(var0, var1, var1 + var4, var6, 0);
         q(var2, var3, var3 + var4, var6, var4);
         boolean var7 = true;

         for (int var8 = 0; var8 < var4; var8++) {
            if (var6[var8] != var6[var8 + var4]) {
               var7 = false;
               break;
            }
         }

         return var7;
      }
   }
}
