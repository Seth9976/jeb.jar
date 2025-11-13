package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class IntMap {
   private static final ILogger logger = GlobalLog.getLogger(IntMap.class);
   @SerId(1)
   private byte[][][] level0 = new byte[65536][][];
   @SerId(2)
   private int cnt;

   public int size() {
      return this.cnt;
   }

   public boolean isEmpty() {
      return this.cnt == 0;
   }

   public void clear() {
      this.level0 = new byte[65536][][];
      this.cnt = 0;
   }

   private int find(byte[] var1, int var2) {
      int var3 = 0;
      int var4 = var1.length / 5;

      while (var3 < var4) {
         int var5 = var3 + (var4 - var3) / 2;
         int var6 = var1[var5 * 5] & 255;
         if (var6 > var2) {
            var4 = var5;
         } else {
            if (var6 >= var2) {
               return var5;
            }

            var3 = var5 + 1;
         }
      }

      return -var3 - 1;
   }

   public int put(int var1, int var2) {
      int var3 = 0;
      int var4 = var1 >>> 16;
      int var5 = var1 >> 8 & 0xFF;
      int var6 = var1 & 0xFF;
      byte[][] var7 = this.level0[var4];
      if (var7 == null) {
         var7 = new byte[256][];
         this.level0[var4] = var7;
      }

      byte[] var8 = var7[var5];
      if (var8 == null) {
         var8 = new byte[5];
         var7[var5] = var8;
         var8[0] = (byte)var6;
         EndianUtil.intToLEBytes(var2, var8, 1);
         this.cnt++;
      } else {
         int var9 = this.find(var8, var6);
         if (var9 >= 0) {
            var9 *= 5;
            var3 = EndianUtil.littleEndianBytesToInt(var8, var9 + 1);
            EndianUtil.intToLEBytes(var2, var8, var9 + 1);
            if (var3 != var2) {
               this.cnt++;
            }
         } else {
            var9 = -(var9 + 1) * 5;
            byte[] var10 = new byte[var8.length + 5];
            System.arraycopy(var8, 0, var10, 0, var9);
            var10[var9] = (byte)var6;
            EndianUtil.intToLEBytes(var2, var10, var9 + 1);
            System.arraycopy(var8, var9, var10, var9 + 5, var8.length - var9);
            var7[var5] = var10;
            this.cnt++;
         }
      }

      return var3;
   }

   public int get(int var1) {
      int var2 = var1 >>> 16;
      int var3 = var1 >> 8 & 0xFF;
      int var4 = var1 & 0xFF;
      byte[][] var5 = this.level0[var2];
      if (var5 == null) {
         return 0;
      } else {
         byte[] var6 = var5[var3];
         if (var6 == null) {
            return 0;
         } else {
            int var7;
            if (var6.length == 1280) {
               var7 = var4;
            } else {
               var7 = this.find(var6, var4);
               if (var7 < 0) {
                  return 0;
               }
            }

            return EndianUtil.littleEndianBytesToInt(var6, var7 * 5 + 1);
         }
      }
   }

   public boolean containsKey(int var1) {
      int var2 = var1 >>> 16;
      int var3 = var1 >> 8 & 0xFF;
      int var4 = var1 & 0xFF;
      byte[][] var5 = this.level0[var2];
      if (var5 == null) {
         return false;
      } else {
         byte[] var6 = var5[var3];
         if (var6 == null) {
            return false;
         } else {
            if (var6.length != 1280) {
               int var7 = this.find(var6, var4);
               if (var7 < 0) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("{");
      int var2 = 0;
      int var3 = 0;

      for (byte[][] var7 : this.level0) {
         if (var7 != null) {
            int var8 = 0;

            for (byte[] var12 : var7) {
               if (var12 != null) {
                  for (byte var13 = 0; var13 < var12.length; var13 += 5) {
                     int var14 = var12[var13] & 255;
                     int var15 = EndianUtil.littleEndianBytesToInt(var12, var13 + 1);
                     if (var15 != 0) {
                        int var16 = var3 << 16 | var8 << 8 | var14;
                        if (var2 > 0) {
                           var1.append(", ");
                        }

                        var1.append(var16).append("=").append(var15);
                        var2++;
                     }
                  }
               }

               var8++;
            }
         }

         var3++;
      }

      var1.append("}");
      return var1.toString();
   }
}
