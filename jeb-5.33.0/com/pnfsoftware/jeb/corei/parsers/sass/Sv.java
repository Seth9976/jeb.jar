package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.io.ByteArray;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class Sv {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class, Integer.MAX_VALUE);
   private byte[] A;
   private long kS;
   private List wS = new ArrayList();

   public Sv(byte[] var1) {
      Assert.a(var1 != null);
      this.A = var1;
   }

   public List pC() throws Exception {
      ByteArray var1 = new ByteArray(this.A);

      while (var1.available() > 80) {
         int var2 = var1.i32();
         if (var2 != -1168773808) {
            break;
         }

         Object[] var10000 = new Object[]{this.kS + var1.position()};
         int var3 = var1.u16();
         if (var3 != 1) {
            var10000 = new Object[0];
            break;
         }

         int var4 = var1.u16();
         int var5 = var1.i32();
         var1.i32();
         Assert.a(var4 >= 16);
         var1.skip(var4 - 16);
         ByteArray var6 = var1.sub(var5);
         this.pC(var6);
         var1.skip(var5);
         int var7 = var1.position() & 15;
         if (var7 != 0) {
            var7 = 16 - var7;
            if (var1.available() >= var7) {
               var1.skip(var7);
            }
         }
      }

      return this.wS;
   }

   private void pC(ByteArray var1) throws Exception {
      while (var1.available() > 64) {
         Object[] var10000 = new Object[]{this.kS + var1.position()};
         int var2 = var1.u16();
         var1.u16();
         int var3 = var1.i32();
         int var4 = var1.i32();
         var1.i32();
         int var5 = var1.i32();
         var1.i32();
         int var6 = var1.u16();
         int var7 = var1.u16();
         int var8 = var1.i32();
         var1.i32();
         var1.i32();
         int var9 = var1.i32();
         var1.i32();
         var1.i32();
         var1.i32();
         int var10 = var1.i32();
         var1.i32();
         Assert.a(var3 >= 64);
         var1.skip(var3 - 64);
         byte[] var11 = null;
         if (var5 == 0 && var10 == 0) {
            var11 = var1.get(var4);
         } else {
            byte[] var12 = var1.get(var5);
            var1.skip(var4 - var5);

            try {
               if ((var9 & 8192) != 0) {
                  LZ4FastDecompressor var13 = LZ4Factory.fastestJavaInstance().fastDecompressor();
                  var11 = var13.decompress(var12, var10);
               } else if ((var9 & 32768) != 0) {
                  var11 = new byte[var10];
                  long var17 = pC(var11, var12);
                  if (var17 != var10) {
                     var11 = null;
                  }
               }
            } catch (Exception var15) {
               var11 = null;
            }
         }

         if (var11 != null) {
            Av var16 = new Av(var2, var7, var6, var8, var9, var11);
            "  -> " + var16;
            var10000 = new Object[0];
            this.wS.add(var16);
         }
      }
   }

   private static long pC(byte[] var0, byte[] var1) throws Exception {
      return (Long)Class.forName("com.github.luben.zstd.Zstd").getMethod("decompress", byte[].class, byte[].class).invoke(null, var0, var1);
   }

   public static boolean pC(InputStream var0) throws IOException {
      if (var0.available() < 80) {
         return false;
      } else {
         LEDataInputStream var1 = new LEDataInputStream(var0);
         int var2 = var1.readInt();
         if (var2 != -1168773808) {
            return false;
         } else {
            short var3 = var1.readShort();
            if (var3 != 1) {
               return false;
            } else {
               short var4 = var1.readShort();
               if (var4 < 16) {
                  return false;
               } else {
                  int var5 = var1.readInt();
                  if (var5 <= 0) {
                     return false;
                  } else {
                     var1.readInt();
                     var1.skipNBytes(var4 - 16);
                     var1.readInt();
                     int var6 = var1.readInt();
                     return var6 >= 64 && var6 <= 256;
                  }
               }
            }
         }
      }
   }
}
