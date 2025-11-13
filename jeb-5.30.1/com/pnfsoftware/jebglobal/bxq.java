package com.pnfsoftware.jebglobal;

public class bxq {
   public static bxm q(bxn var0, bxj... var1) {
      return new bxm(var0, var1);
   }

   public static bxm q(bxo var0, bxj... var1) {
      return new bxm(var0, var1);
   }

   public static bxl q(int var0, int var1, int var2, bxk var3) {
      return new bxl(var0, var1, var2, var3);
   }

   public static bxl q(int var0) {
      return new bxl(var0, 0, 7);
   }

   public static bxl q(int var0, int var1) {
      return new bxl(var0, var1, 7);
   }

   public static bxl RF(int var0) {
      return new bxl(var0, 0, 3);
   }

   public static bxl xK(int var0) {
      return new bxl(var0, 0, 1);
   }

   public static bxl Dw(int var0) {
      return new bxl(var0, 0, 2);
   }

   public static bxl Uv(int var0) {
      return new bxl(var0, 0, 3);
   }

   public static bxl q(long var0, int var2, int var3) {
      return new bxl(var0, var2, var3);
   }

   public static bxl q(long var0, int var2) {
      return new bxl(var0, var2, -1);
   }

   public static bxl q(long var0) {
      return new bxl(var0, 0, -1);
   }

   public static bxp q(bxj var0, bxj var1) {
      return new bxp(var0, var1);
   }

   public static void q(bxj var0) {
      if (var0 instanceof bxm && ((bxm)var0).Dw < 0) {
         q((bxm)var0);
      }
   }

   public static int q(bxm var0) {
      int var1 = 0;

      for (bxj var5 : var0.xK) {
         if (var5 instanceof bxm) {
            int var6 = q((bxm)var5);
            if (var6 > var1) {
               var1 = var6;
            }
         }
      }

      boolean var7 = var0.q == bxn.gP;
      int var8 = (var7 ? 0 : 1) + var1;
      var0.Dw = var8;
      return var8;
   }
}
