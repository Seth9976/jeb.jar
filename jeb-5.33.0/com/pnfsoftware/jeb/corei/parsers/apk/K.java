package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.android.APKSigSchemeV2Block;
import com.pnfsoftware.jeb.core.units.code.android.APKSigSchemeV3Block;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class K {
   private static final ILogger UT = GlobalLog.getLogger(K.class);
   File pC;
   APKSigSchemeV2Block A;
   APKSigSchemeV3Block kS;
   APKSigSchemeV3Block wS;

   public K(File var1) {
      this.pC = var1;
   }

   public APKSigSchemeV2Block pC() {
      return this.A;
   }

   public APKSigSchemeV3Block A() {
      return this.kS;
   }

   public APKSigSchemeV3Block kS() {
      return this.wS;
   }

   public boolean wS() {
      try {
         boolean var16;
         try (RandomAccessFile var1 = new RandomAccessFile(this.pC, "r")) {
            long var2 = var1.length();
            long var4 = var2 - 22L;
            long var6 = Math.max(0L, var4 - 65536L);

            long var8;
            label72:
            for (var8 = -1L; var4 >= var6; var4--) {
               while (true) {
                  if (var4 >= var6) {
                     var1.seek(var4);
                     int var10 = var1.readInt();
                     if (var10 != 1347093766) {
                        var4--;
                        continue;
                     }
                  }

                  if (var4 < var6) {
                     return false;
                  }

                  var1.seek(var4 + 16L);
                  var8 = EndianUtil.swapInt(var1.readInt());
                  if (var8 >= 0L && var8 < var2) {
                     break label72;
                  }
                  break;
               }
            }

            if (var4 < var6) {
               return false;
            }

            byte[] var22 = Strings.encodeASCII("APK Sig Block 42");
            var1.seek(var8 - var22.length);
            byte[] var11 = new byte[var22.length];
            if (var1.read(var11) != var11.length || !Arrays.equals(var11, var22)) {
               return true;
            }

            var1.seek(var8 - 24L);
            long var12 = EndianUtil.swapLong(var1.readLong());
            var4 = var8 - var12 - 8L;
            var1.seek(var4);
            long var14 = EndianUtil.swapLong(var1.readLong());
            if (var14 != var12) {
               return false;
            }

            var16 = this.pC(var1, var4, var12);
         }

         return var16;
      } catch (IOException var19) {
         return false;
      }
   }

   public boolean pC(RandomAccessFile var1, long var2, long var4) {
      int var6;
      try {
         Object[] var10000 = new Object[]{var2 + 8L};
         var6 = 0;
         long var7 = var4 - 24L;

         while (var7 >= 12L) {
            long var9 = EndianUtil.swapLong(var1.readLong());
            if (var9 < 4L) {
               return false;
            }

            int var11 = EndianUtil.swapInt(var1.readInt());
            byte[] var12 = new byte[(int)var9 - 4];
            if (var1.read(var12) != var12.length) {
               return false;
            }

            if (!this.pC(var11, var12)) {
               var6++;
            }

            var7 -= var9 + 8L;
         }

         if (var7 != 0L) {
            return false;
         }
      } catch (IOException var13) {
         return false;
      }

      return var6 == 0;
   }

   private boolean pC(int var1, byte[] var2) {
      Object[] var10000 = new Object[]{var1, var2.length, Formatter.formatBinaryLine(var2, 0, Math.min(10, var2.length))};
      ByteBuffer var3 = ByteBuffer.wrap(var2);
      var3.order(ByteOrder.LITTLE_ENDIAN);
      if (var1 == 1896449818) {
         try {
            var10000 = new Object[0];
            this.A = new APKSigSchemeV2Block(var3);
         } catch (RuntimeException var7) {
            UT.catching(var7);
            JebCoreService.notifySilentExceptionToClient(var7);
            return false;
         }
      } else if (var1 == -262969152) {
         try {
            var10000 = new Object[0];
            this.kS = new APKSigSchemeV3Block(var3);
         } catch (RuntimeException var6) {
            UT.catching(var6);
            JebCoreService.notifySilentExceptionToClient(var6);
            return false;
         }
      } else if (var1 == 462663009) {
         try {
            var10000 = new Object[0];
            this.wS = new APKSigSchemeV3Block(var3, 1);
         } catch (RuntimeException var5) {
            JebCoreService.notifySilentExceptionToClient(var5);
            return false;
         }
      } else if (var1 == 1114793335) {
         UT.debug("Padding block, skipping.");
      } else {
         UT.debug("Disregarding block with unknown id 0x%X", var1);
      }

      return true;
   }
}
