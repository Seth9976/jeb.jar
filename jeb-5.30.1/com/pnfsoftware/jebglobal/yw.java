package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class yw {
   private static final ILogger q = GlobalLog.getLogger(yw.class);
   private oH RF;
   private int xK;
   private int Dw;
   private boolean Uv;
   private boolean oW;

   yw(oH var1, int var2, int var3) throws IOException {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
      if (var1.gO() == u.RF) {
         this.Uv = false;
         this.oW = true;
      } else if (var1.gO() == u.xK) {
         this.Uv = !Strings.decodeASCII(var1.Dw("x0,0")).isEmpty();
         this.oW = !Strings.decodeASCII(var1.Dw("X0,0")).isEmpty();
      }
   }

   public int q() {
      return this.xK;
   }

   public int RF() {
      return this.Dw;
   }

   private int xK(long var1, byte[] var3, int var4, int var5) throws IOException {
      if (var5 <= 0) {
         return 0;
      } else {
         int var6 = 0;
         if (this.Uv) {
            while (var5 > 0) {
               int var13 = Math.min(2048, var5);
               String var14 = Strings.ff("x%x,%x", var1, var13);
               byte[] var15 = this.RF.Dw(var14);
               if (var15 == null) {
                  q.debug("Reading error");
                  break;
               }

               if (var13 != 3 && MO.xK(var15)) {
                  break;
               }

               int var16 = Math.min(var15.length, var13);
               var1 += var16;
               var5 -= var16;
               System.arraycopy(var15, 0, var3, var4, var16);
               var4 += var16;
               var6 += var16;
            }

            return var6;
         } else {
            while (var5 > 0) {
               int var7 = Math.min(2048, var5);
               String var8 = Strings.ff("m%x,%x", var1, var7);
               byte[] var9 = this.RF.Dw(var8);
               if (var9 == null) {
                  break;
               }

               String var10 = Strings.decodeASCII(var9);
               if (MO.xK(var10)) {
                  q.debug("Cannot read at address %Xh", var1);
                  break;
               }

               byte[] var11 = Formatter.hexStringToByteArray(var10);
               if (var11 == null) {
                  q.debug("Invalid hexadecimal encoded data: %s", Strings.decodeUTF8(var9));
                  break;
               }

               int var12 = Math.min(var11.length, var7);
               var1 += var12;
               var5 -= var12;
               System.arraycopy(var11, 0, var3, var4, var12);
               var4 += var12;
               var6 += var12;
            }

            return var6;
         }
      }
   }

   public int q(long var1, byte[] var3, int var4, int var5) throws IOException {
      return this.xK(var1, var3, 0, var3.length);
   }

   public int q(long var1, byte[] var3) throws IOException {
      return this.q(var1, var3, 0, var3.length);
   }

   private int Dw(long var1, byte[] var3, int var4, int var5) throws IOException {
      if (var5 <= 0) {
         return 0;
      } else {
         int var6 = 0;
         if (this.oW) {
            BytePipe var12 = new BytePipe(100 + Math.min(2048, var5));

            while (var5 > 0) {
               int var13 = Math.min(2048, var5);
               var12.reset();
               var12.append(Strings.encodeASCII(Strings.ff("X%x,%x:", var1, var13)));
               var12.append(var3, var4, var13);
               byte[] var14 = this.RF.RF(var12.getAll());
               if (var14 == null) {
                  q.debug("Cannot write at address %Xh", var1);
                  break;
               }

               String var15 = Strings.decodeASCII(var14);
               if (!MO.RF(var15) || MO.xK(var15)) {
                  q.debug("Cannot write at address %Xh", var1);
                  break;
               }

               var4 += var13;
               var5 -= var13;
               var1 += var13;
               var6 += var13;
            }
         } else {
            while (var5 > 0) {
               int var7 = Math.min(2048, var5);
               String var8 = MO.q(var3, var4, var7);
               String var9 = Strings.ff("M%x,%x:%s", var1, var7, var8);
               byte[] var10 = this.RF.Dw(var9);
               if (var10 == null) {
                  q.debug("Cannot write at address %Xh", var1);
                  break;
               }

               String var11 = Strings.decodeASCII(var10);
               if (!MO.RF(var11) || MO.xK(var11)) {
                  q.debug("Cannot write at address %Xh", var1);
                  break;
               }

               var4 += var7;
               var5 -= var7;
               var1 += var7;
               var6 += var7;
            }
         }

         return var6;
      }
   }

   public int RF(long var1, byte[] var3, int var4, int var5) throws IOException {
      return this.Dw(var1, var3, var4, var5);
   }

   public int RF(long var1, byte[] var3) throws IOException {
      return this.RF(var1, var3, 0, var3.length);
   }

   public boolean xK(long var1, byte[] var3) throws IOException {
      byte[] var4 = new byte[1];
      if (this.q(var1, var4) != 1) {
         return false;
      } else {
         var3[0] = var4[0];
         return true;
      }
   }

   public boolean q(long var1, byte var3) throws IOException {
      byte[] var4 = new byte[1];
      if (!this.xK(var1, var4)) {
         return false;
      } else {
         var4[0] = var3;
         return this.RF(var1, var4) == 1;
      }
   }

   public boolean q(long var1, short[] var3) throws IOException {
      byte[] var4 = new byte[2];
      if (this.q(var1, var4) != 2) {
         return false;
      } else {
         var3[0] = ByteBuffer.wrap(var4).order(ByteOrder.BIG_ENDIAN).getShort();
         return true;
      }
   }

   public boolean q(long var1, short var3) throws IOException {
      byte[] var4 = new byte[2];
      if (this.q(var1, var4) != 2) {
         return false;
      } else {
         ByteBuffer.wrap(var4).order(ByteOrder.BIG_ENDIAN).putShort(var3);
         return this.RF(var1, var4) == 2;
      }
   }

   public boolean q(long var1, int[] var3) throws IOException {
      byte[] var4 = new byte[4];
      if (this.q(var1, var4) != 4) {
         return false;
      } else {
         var3[0] = ByteBuffer.wrap(var4).order(ByteOrder.BIG_ENDIAN).getInt();
         return true;
      }
   }

   public boolean q(long var1, int var3) throws IOException {
      byte[] var4 = new byte[4];
      if (this.q(var1, var4) != 4) {
         return false;
      } else {
         ByteBuffer.wrap(var4).order(ByteOrder.BIG_ENDIAN).putInt(var3);
         return this.RF(var1, var4) == 4;
      }
   }

   private long RF(long var1, long var3, byte[] var5) throws IOException {
      byte[] var6 = Strings.encodeASCII(Strings.ff("qSearch:memory:%x;%x;", var1, var3));
      byte[] var7 = new byte[var6.length + var5.length];
      ArrayUtil.copyBytes(var7, 0, var6, 0, var6.length);
      ArrayUtil.copyBytes(var7, var6.length, var5, 0, var5.length);
      byte[] var8 = this.RF.RF(var7);
      String var9 = Strings.decodeASCII(var8);
      if (var9.startsWith("0")) {
         return -1L;
      } else if (MO.xK(var9)) {
         return -2L;
      } else if (MO.q(var9)) {
         return -3L;
      } else if (!var9.startsWith("1,")) {
         return -4L;
      } else {
         try {
            return Long.parseLong(var9.substring(2), 16) - var1;
         } catch (NumberFormatException var10) {
            return -5L;
         }
      }
   }

   private long xK(long var1, long var3, byte[] var5) throws IOException {
      while (var3 >= 65536L) {
         long var6 = this.RF(var1, 65536L, var5);
         if (var6 >= 0L) {
            return var6;
         }

         if (var6 != -1L) {
            return var6;
         }

         var1 += 65536L;
         var3 -= 65536L;
      }

      return var3 > 0L ? this.RF(var1, var3, var5) : -1L;
   }

   public long q(long var1, long var3, byte[] var5) {
      try {
         return this.xK(var1, var3, var5);
      } catch (IOException var7) {
         q.error(var7.toString());
         return -1L;
      }
   }
}
