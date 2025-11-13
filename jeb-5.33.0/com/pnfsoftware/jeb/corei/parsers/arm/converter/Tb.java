package com.pnfsoftware.jeb.corei.parsers.arm.converter;

public class Tb {
   public static void pC(String var0, int[] var1, int[] var2, int[] var3) {
      String var4 = var0.toUpperCase();
      switch (var4) {
         case "SHA256H":
            pC(var1, var2, var3);
            break;
         case "SHA256H2":
            A(var1, var2, var3);
            break;
         case "SHA256SU0":
            pC(var1, var2);
            break;
         case "SHA256SU1":
            kS(var1, var2, var3);
            break;
         default:
            throw new IllegalArgumentException("Unknown instruction: " + var0);
      }
   }

   public static void pC(int[] var0, int[] var1, int[] var2) {
      int[] var3 = pC(var0, var1, var2, true);
      var0[0] = var3[0];
      var0[1] = var3[1];
      var0[2] = var3[2];
      var0[3] = var3[3];
   }

   public static void A(int[] var0, int[] var1, int[] var2) {
      int[] var3 = pC(var1, var0, var2, false);
      var0[0] = var3[0];
      var0[1] = var3[1];
      var0[2] = var3[2];
      var0[3] = var3[3];
   }

   public static void pC(int[] var0, int[] var1) {
      int[] var2 = new int[]{var0[1], var0[2], var0[3], var1[0]};

      for (int var3 = 0; var3 < 4; var3++) {
         int var4 = var2[var3];
         var4 = pC(var4, 7) ^ pC(var4, 18) ^ A(var4, 3);
         var0[var3] += var4;
      }
   }

   public static void kS(int[] var0, int[] var1, int[] var2) {
      int[] var3 = new int[]{var1[1], var1[2], var1[3], var2[0]};
      int[] var4 = new int[]{var2[2], var2[3]};

      for (int var5 = 0; var5 < 2; var5++) {
         int var6 = var4[var5];
         var6 = pC(var6, 17) ^ pC(var6, 19) ^ A(var6, 10);
         var0[var5] = var6 + var0[var5] + var3[var5];
      }

      for (int var7 = 2; var7 < 4; var7++) {
         int var9 = var0[var7 - 2];
         var9 = pC(var9, 17) ^ pC(var9, 19) ^ A(var9, 10);
         var0[var7] = var9 + var0[var7] + var3[var7];
      }
   }

   private static int[] pC(int[] var0, int[] var1, int[] var2, boolean var3) {
      int[] var4 = new int[]{var0[0], var0[1], var0[2], var0[3]};
      int[] var5 = new int[]{var1[0], var1[1], var1[2], var1[3]};

      for (int var6 = 0; var6 < 4; var6++) {
         int var7 = pC(var5[0], var5[1], var5[2]);
         int var8 = A(var4[0], var4[1], var4[2]);
         int var9 = var5[3] + A(var5[0]) + var7 + var2[var6];
         var4[3] += var9;
         var5[3] = var9 + pC(var4[0]) + var8;
         int var10 = var5[3];
         var5[3] = var5[2];
         var5[2] = var5[1];
         var5[1] = var5[0];
         var5[0] = var4[3];
         var4[3] = var4[2];
         var4[2] = var4[1];
         var4[1] = var4[0];
         var4[0] = var10;
      }

      return var3 ? var4 : var5;
   }

   private static int pC(int var0, int var1, int var2) {
      return (var1 ^ var2) & var0 ^ var2;
   }

   private static int A(int var0, int var1, int var2) {
      return var0 & var1 | (var0 | var1) & var2;
   }

   private static int pC(int var0) {
      return pC(var0, 2) ^ pC(var0, 13) ^ pC(var0, 22);
   }

   private static int A(int var0) {
      return pC(var0, 6) ^ pC(var0, 11) ^ pC(var0, 25);
   }

   private static int pC(int var0, int var1) {
      return var1 == 0 ? var0 : Integer.rotateRight(var0, var1);
   }

   private static int A(int var0, int var1) {
      return var1 == 0 ? var0 : var0 >>> var1;
   }
}
