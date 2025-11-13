package com.pnfsoftware.jebglobal;

public class bam {
   public static String pC(int var0) {
      return pC(0, var0);
   }

   private static String pC(int var0, int var1) {
      StringBuilder var2 = new StringBuilder();
      if (var0 == 0) {
         pC(var2, var1, 1, "public");
         pC(var2, var1, 16, "final");
         pC(var2, var1, 32, "super");
         pC(var2, var1, 512, "interface");
         pC(var2, var1, 1024, "abstract");
         pC(var2, var1, 4096, "synthetic");
         pC(var2, var1, 8192, "annotation");
         pC(var2, var1, 16384, "enum");
      } else if (var0 == 1) {
         pC(var2, var1, 1, "public");
         pC(var2, var1, 2, "private");
         pC(var2, var1, 4, "protected");
         pC(var2, var1, 8, "static");
         pC(var2, var1, 16, "final");
         pC(var2, var1, 64, "volatile");
         pC(var2, var1, 128, "transient");
         pC(var2, var1, 4096, "synthetic");
         pC(var2, var1, 16384, "enum");
      } else if (var0 == 2) {
         pC(var2, var1, 1, "public");
         pC(var2, var1, 2, "private");
         pC(var2, var1, 4, "protected");
         pC(var2, var1, 8, "static");
         pC(var2, var1, 16, "final");
         pC(var2, var1, 32, "synchronized");
         pC(var2, var1, 64, "bridge");
         pC(var2, var1, 128, "varargs");
         pC(var2, var1, 256, "native");
         pC(var2, var1, 1024, "abstract");
         pC(var2, var1, 2048, "strict");
         pC(var2, var1, 4096, "synthetic");
      }

      return var2.toString();
   }

   private static void pC(StringBuilder var0, int var1, int var2, String var3) {
      if (var3 != null && var3.length() > 0 && (var1 & var2) != 0) {
         if (var0.length() > 0) {
            var0.append("/");
         }

         var0.append(var3);
      }
   }
}
