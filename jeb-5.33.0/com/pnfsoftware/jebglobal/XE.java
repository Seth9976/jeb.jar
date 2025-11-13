package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class XE {
   private static final ILogger pC = GlobalLog.getLogger(XE.class);
   private aI A;
   private int kS;
   private int wS;
   private boolean UT;
   private boolean E;

   XE(aI var1, int var2, int var3) throws IOException {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      if (var1.E() == jT.A) {
         this.UT = false;
         this.E = true;
      } else if (var1.E() == jT.kS) {
         this.UT = !Strings.decodeASCII(var1.wS("x0,0")).isEmpty();
         this.E = !Strings.decodeASCII(var1.wS("X0,0")).isEmpty();
      }
   }

   public int pC() {
      return this.kS;
   }

   public int A() {
      return this.wS;
   }

   private int kS(long var1, byte[] var3, int var4, int var5) throws IOException {
      if (var5 <= 0) {
         return 0;
      } else {
         int var6 = 0;
         if (this.UT) {
            while (var5 > 0) {
               int var13 = Math.min(2048, var5);
               String var14 = Strings.ff("x%x,%x", var1, var13);
               byte[] var15 = this.A.wS(var14);
               if (var15 == null) {
                  pC.debug("Reading error");
                  break;
               }

               if (var13 != 3 && zI.A(var15)) {
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
               byte[] var9 = this.A.wS(var8);
               if (var9 == null) {
                  break;
               }

               String var10 = Strings.decodeASCII(var9);
               if (zI.kS(var10)) {
                  pC.debug("Cannot read at address %Xh", var1);
                  break;
               }

               byte[] var11 = Formatter.hexStringToByteArray(var10);
               if (var11 == null) {
                  pC.debug("Invalid hexadecimal encoded data: %s", Strings.decodeUTF8(var9));
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

   public int pC(long var1, byte[] var3, int var4, int var5) throws IOException {
      return this.kS(var1, var3, 0, var3.length);
   }

   public int pC(long var1, byte[] var3) throws IOException {
      return this.pC(var1, var3, 0, var3.length);
   }

   private int wS(long var1, byte[] var3, int var4, int var5) throws IOException {
      if (var5 <= 0) {
         return 0;
      } else {
         int var6 = 0;
         if (this.E) {
            BytePipe var12 = new BytePipe(100 + Math.min(2048, var5));

            while (var5 > 0) {
               int var13 = Math.min(2048, var5);
               var12.reset();
               var12.append(Strings.encodeASCII(Strings.ff("X%x,%x:", var1, var13)));
               var12.append(var3, var4, var13);
               byte[] var14 = this.A.A(var12.getAll());
               if (var14 == null) {
                  pC.debug("Cannot write at address %Xh", var1);
                  break;
               }

               String var15 = Strings.decodeASCII(var14);
               if (!zI.A(var15) || zI.kS(var15)) {
                  pC.debug("Cannot write at address %Xh", var1);
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
               String var8 = zI.pC(var3, var4, var7);
               String var9 = Strings.ff("M%x,%x:%s", var1, var7, var8);
               byte[] var10 = this.A.wS(var9);
               if (var10 == null) {
                  pC.debug("Cannot write at address %Xh", var1);
                  break;
               }

               String var11 = Strings.decodeASCII(var10);
               if (!zI.A(var11) || zI.kS(var11)) {
                  pC.debug("Cannot write at address %Xh", var1);
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

   public int A(long var1, byte[] var3, int var4, int var5) throws IOException {
      return this.wS(var1, var3, var4, var5);
   }

   public int A(long var1, byte[] var3) throws IOException {
      return this.A(var1, var3, 0, var3.length);
   }

   private long A(long var1, long var3, byte[] var5) throws IOException {
      byte[] var6 = Strings.encodeASCII(Strings.ff("qSearch:memory:%x;%x;", var1, var3));
      byte[] var7 = new byte[var6.length + var5.length];
      ArrayUtil.copyBytes(var7, 0, var6, 0, var6.length);
      ArrayUtil.copyBytes(var7, var6.length, var5, 0, var5.length);
      byte[] var8 = this.A.A(var7);
      String var9 = Strings.decodeASCII(var8);
      if (var9.startsWith("0")) {
         return -1L;
      } else if (zI.kS(var9)) {
         return -2L;
      } else if (zI.pC(var9)) {
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

   private long kS(long var1, long var3, byte[] var5) throws IOException {
      while (var3 >= 65536L) {
         long var6 = this.A(var1, 65536L, var5);
         if (var6 >= 0L) {
            return var6;
         }

         if (var6 != -1L) {
            return var6;
         }

         var1 += 65536L;
         var3 -= 65536L;
      }

      return var3 > 0L ? this.A(var1, var3, var5) : -1L;
   }

   public long pC(long var1, long var3, byte[] var5) {
      try {
         return this.kS(var1, var3, var5);
      } catch (IOException var7) {
         pC.error(var7.toString());
         return -1L;
      }
   }
}
