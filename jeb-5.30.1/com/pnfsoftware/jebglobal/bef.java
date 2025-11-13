package com.pnfsoftware.jebglobal;

public class bef {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 16;
   public static final int oW = 32;
   public static final int gO = 32;
   public static final int nf = 64;
   public static final int gP = 64;
   public static final int za = 128;
   public static final int lm = 128;
   public static final int zz = 256;
   public static final int JY = 512;
   public static final int HF = 1024;
   public static final int LK = 2048;
   public static final int io = 4096;
   public static final int qa = 8192;
   public static final int Hk = 16384;

   public static String q(int var0) {
      return q(0, var0);
   }

   public static String RF(int var0) {
      return q(1, var0);
   }

   public static String xK(int var0) {
      return q(2, var0);
   }

   private static String q(int var0, int var1) {
      StringBuilder var2 = new StringBuilder();
      if (var0 == 0) {
         q(var2, var1, 1, "public");
         q(var2, var1, 16, "final");
         q(var2, var1, 32, "super");
         q(var2, var1, 512, "interface");
         q(var2, var1, 1024, "abstract");
         q(var2, var1, 4096, "synthetic");
         q(var2, var1, 8192, "annotation");
         q(var2, var1, 16384, "enum");
      } else if (var0 == 1) {
         q(var2, var1, 1, "public");
         q(var2, var1, 2, "private");
         q(var2, var1, 4, "protected");
         q(var2, var1, 8, "static");
         q(var2, var1, 16, "final");
         q(var2, var1, 64, "volatile");
         q(var2, var1, 128, "transient");
         q(var2, var1, 4096, "synthetic");
         q(var2, var1, 16384, "enum");
      } else if (var0 == 2) {
         q(var2, var1, 1, "public");
         q(var2, var1, 2, "private");
         q(var2, var1, 4, "protected");
         q(var2, var1, 8, "static");
         q(var2, var1, 16, "final");
         q(var2, var1, 32, "synchronized");
         q(var2, var1, 64, "bridge");
         q(var2, var1, 128, "varargs");
         q(var2, var1, 256, "native");
         q(var2, var1, 1024, "abstract");
         q(var2, var1, 2048, "strict");
         q(var2, var1, 4096, "synthetic");
      }

      return var2.toString();
   }

   private static void q(StringBuilder var0, int var1, int var2, String var3) {
      if (var3 != null && var3.length() > 0 && (var1 & var2) != 0) {
         if (var0.length() > 0) {
            var0.append("/");
         }

         var0.append(var3);
      }
   }
}
