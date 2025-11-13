package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.Checksum;

public class ArmEmu_CRC {
   public static void main(String[] var0) {
      int var1 = -1;
      var1 = ys(var1, 1234605616436508552L);
      Assert.a(var1 == 1353101033);
      var1 = ys(var1, 1161981756646125696L);
      Assert.a(var1 == 447661041);
      ArmEmu_CRC.Av var2 = ArmEmu_CRC.Sv.A(-1);
      byte[] var3 = new byte[]{-120, 119, 102, 85, 68, 51, 34, 17};
      var2.update(var3, 0, 8);
      var3 = new byte[]{-128, 112, 96, 80, 64, 48, 32, 16};
      var2.update(var3, 0, 8);
      int var4 = ~((int)var2.getValue());
      Assert.a(var4 == var1);
      int var7 = -1;
      var7 = wS(var7, 1234605616436508552L);
      Assert.a(var7 == -490008879);
      var7 = wS(var7, 1161981756646125696L);
      Assert.a(var7 == 189491996);
   }

   public static int pC(String var0, int var1, long var2) {
      String var4 = var0.toUpperCase();
      switch (var4) {
         case "CRC32B":
            return pC(var1, var2);
         case "CRC32H":
            return A(var1, var2);
         case "CRC32W":
            return kS(var1, var2);
         case "CRC32X":
            return wS(var1, var2);
         case "CRC32CB":
            return UT(var1, var2);
         case "CRC32CH":
            return E(var1, var2);
         case "CRC32CW":
            return sY(var1, var2);
         case "CRC32CX":
            return ys(var1, var2);
         default:
            throw new IllegalArgumentException("Unknown instruction: " + var0);
      }
   }

   public static int pC(int var0, long var1) {
      ArmEmu_CRC.Av var3 = ArmEmu_CRC.Sv.pC(var0);
      var3.update((byte)var1);
      return var3.A;
   }

   public static int A(int var0, long var1) {
      byte[] var3 = EndianUtil.shortToLEBytes((short)var1);
      ArmEmu_CRC.Av var4 = ArmEmu_CRC.Sv.pC(var0);
      var4.update(var3, 0, 2);
      return var4.A;
   }

   public static int kS(int var0, long var1) {
      byte[] var3 = EndianUtil.intToLEBytes((int)var1);
      ArmEmu_CRC.Av var4 = ArmEmu_CRC.Sv.pC(var0);
      var4.update(var3, 0, 4);
      return var4.A;
   }

   public static int wS(int var0, long var1) {
      byte[] var3 = EndianUtil.longToLEBytes(var1);
      ArmEmu_CRC.Av var4 = ArmEmu_CRC.Sv.pC(var0);
      var4.update(var3, 0, 8);
      return var4.A;
   }

   public static int UT(int var0, long var1) {
      ArmEmu_CRC.Av var3 = ArmEmu_CRC.Sv.A(var0);
      var3.update((byte)var1);
      return var3.A;
   }

   public static int E(int var0, long var1) {
      byte[] var3 = EndianUtil.shortToLEBytes((short)var1);
      ArmEmu_CRC.Av var4 = ArmEmu_CRC.Sv.A(var0);
      var4.update(var3, 0, 2);
      return var4.A;
   }

   public static int sY(int var0, long var1) {
      byte[] var3 = EndianUtil.intToLEBytes((int)var1);
      ArmEmu_CRC.Av var4 = ArmEmu_CRC.Sv.A(var0);
      var4.update(var3, 0, 4);
      return var4.A;
   }

   public static int ys(int var0, long var1) {
      byte[] var3 = EndianUtil.longToLEBytes(var1);
      ArmEmu_CRC.Av var4 = ArmEmu_CRC.Sv.A(var0);
      var4.update(var3, 0, 8);
      return var4.A;
   }

   static class Av implements Checksum {
      int[] pC;
      int A;

      Av(int[] var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public void reset() {
         this.A = -1;
      }

      @Override
      public long getValue() {
         return ~this.A & 4294967295L;
      }

      @Override
      public void update(int var1) {
         this.A = this.A >>> 8 ^ this.pC[(this.A ^ var1 & 0xFF) & 0xFF];
      }

      @Override
      public void update(byte[] var1, int var2, int var3) {
         if (var1 == null) {
            throw new NullPointerException();
         } else if (var2 >= 0 && var3 >= 0 && var2 <= var1.length - var3) {
            this.A = this.pC(this.A, var1, var2, var2 + var3);
         } else {
            throw new ArrayIndexOutOfBoundsException();
         }
      }

      private int pC(int var1, byte[] var2, int var3, int var4) {
         while (var3 < var4) {
            var1 = var1 >>> 8 ^ this.pC[(var1 ^ var2[var3]) & 0xFF];
            var3++;
         }

         return var1;
      }
   }

   static class K {
      final int pC;
      final int A;
      final int[] kS;

      K(int var1) {
         Assert.a(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
         this.pC = var1;
         this.A = Integer.reverse(var1);
         int[][] var2 = new int[8][256];

         for (int var3 = 0; var3 < var2[0].length; var3++) {
            int var4 = var3;

            for (int var5 = 0; var5 < 8; var5++) {
               if ((var4 & 1) != 0) {
                  var4 = var4 >>> 1 ^ this.A;
               } else {
                  var4 >>>= 1;
               }
            }

            var2[0][var3] = var4;
         }

         for (int var6 = 0; var6 < var2[0].length; var6++) {
            int var7 = var2[0][var6];

            for (int var8 = 1; var8 < var2.length; var8++) {
               var7 = var2[0][var7 & 0xFF] ^ var7 >>> 8;
               var2[var8][var6] = var7;
            }
         }

         this.kS = var2[0];
      }
   }

   static final class Sv {
      private static Map pC = new ConcurrentHashMap();

      private static ArmEmu_CRC.K kS(int var0) {
         return (ArmEmu_CRC.K)pC.computeIfAbsent(var0, var1 -> new ArmEmu_CRC.K(var0));
      }

      static ArmEmu_CRC.Av pC(int var0) {
         return new ArmEmu_CRC.Av(kS(79764919).kS, var0);
      }

      static ArmEmu_CRC.Av A(int var0) {
         return new ArmEmu_CRC.Av(kS(517762881).kS, var0);
      }
   }
}
