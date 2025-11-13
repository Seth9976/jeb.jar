package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractClientContext;
import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Random;

public class pB {
   private static final ILogger A = GlobalLog.getLogger(pB.class);
   private static final long kS = System.currentTimeMillis();
   private static final Random wS = new Random(kS);
   public static byte[] pC = new byte[]{69, 103, -94, -103, 33, -125, -15, 16};
   private int UT;
   private int E;
   private long sY;
   private long ys;
   private int ld;
   private Version gp;
   private int oT;
   private int fI;
   private int WR;
   private byte[] NS;

   public static byte[] pC(long var0, byte[] var2, int[] var3) {
      if (var2 == null) {
         return null;
      } else {
         try {
            ByteArrayOutputStream var4 = new ByteArrayOutputStream();
            LEDataOutputStream var5 = new LEDataOutputStream(var4);
            var5.writeInt(64 + var2.length);
            var5.writeInt(wS.nextInt());
            var5.writeInt(5);
            var5.writeInt(0);
            var5.writeInt(Licensing.user_id);
            var5.writeLong(Licensing.license_id);
            var5.writeLong(var0);
            var5.writeInt(Licensing.build_type);
            var5.writeInt(AbstractContext.app_ver.getMajor());
            var5.writeInt(AbstractContext.app_ver.getMinor());
            var5.writeInt(AbstractContext.app_ver.getBuildid());
            var5.writeLong(AbstractContext.app_ver.getTimestamp());
            int var6 = (int)((System.currentTimeMillis() - kS) / 1000L);
            var5.writeInt(var6);
            int var7 = E();
            var5.writeInt(var7);
            int var8 = wS.nextInt();
            var5.writeInt(var8);
            var5.write(var2);
            var5.close();
            byte[] var9 = var4.toByteArray();
            ByteBuffer var10 = ByteBuffer.wrap(var9);
            var10.order(ByteOrder.LITTLE_ENDIAN);
            var10.putInt(0, var9.length - 8);
            var10.putInt(12, Hash.calculateCRC32(Arrays.copyOfRange(var9, 16, var9.length)));
            byte[] var11 = new byte[16];

            for (int var12 = 0; var12 < 8; var12++) {
               var11[var12] = var9[var12];
            }

            for (int var14 = 8; var14 < 16; var14++) {
               var11[var14] = pC[var14 - 8];
            }

            ckv.pC(var11, var9, 8, var9.length);
            if (var3 != null && var3.length >= 1) {
               var3[0] = var8;
            }

            return var9;
         } catch (Exception var13) {
            return null;
         }
      }
   }

   public static String pC(byte[] var0) {
      try {
         return Formatter.byteArrayToHexString(var0);
      } catch (Exception var1) {
         return null;
      }
   }

   public static byte[] pC(String var0) {
      try {
         return Formatter.hexStringToByteArray(var0);
      } catch (Exception var1) {
         return null;
      }
   }

   private static int E() {
      Class[] var0 = new Class[]{
         AbstractContext.class, GA.class, AbstractClientContext.class, Rk.class, Qc.class, xt.class, Qn.class, NW.class, pB.class, hi.class
      };
      return (int)pC(var0);
   }

   private static File pC(Class var0) throws Exception {
      Object var1 = Class.forName(ckx.pC(new byte[]{-71, 11, 23, 23, 79, 66, 13, 15, 9, 73, 109, 47, 13, 18, 0}, 1, 211))
         .getMethod(ckx.pC(new byte[]{97, 2, 17, 36, 34, 29, 27, 17, 6, 23, 29, 6, 1, 42, 43, 2, 12, 8, 7}, 1, 6))
         .invoke(var0);
      Object var2 = Class.forName(
            ckx.pC(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 97, 75, 86, 71, 73, 67, 70, 89, 93, 91, 104, 79, 34, 19, 8, 13}, 2, 162)
         )
         .getMethod(ckx.pC(new byte[]{-95, 2, 17, 55, 44, 11, 1, 54, 60, 26, 7, 17, 6}, 1, 198))
         .invoke(var1);
      Object var3 = Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 114, 86, 93, 86, 127, 79, 71, 66, 81, 80}, 2, 80))
         .getMethod(ckx.pC(new byte[]{36, 10, 4, 53, 29, 10, 6, 28, 29, 79, 70}, 2, 44))
         .invoke(var2);
      Object var4 = Class.forName(
            ckx.pC(
               new byte[]{
                  118, 12, 2, 67, 94, 30, 8, 21, 28, 9, 18, 3, 22, 19, 23, 75, 68, 15, 7, 76, 91, 1, 29, 5, 66, 64, 11, 17, 90, 123, 7, 30, 25, 33, 29, 5
               },
               1,
               21
            )
         )
         .getMethod(ckx.pC(new byte[]{-80, 7, 30, 56, 59, 41, 47, 5, 9}, 1, 197), Class.forName("java.net.URL"))
         .invoke(null, var3);
      return (File)var4;
   }

   private static long pC(Class[] var0) {
      if (var0.length == 0) {
         return 0L;
      } else {
         long var1 = 0L;

         try {
            File var3 = pC(var0[0]);
            var1 = pC(var3, var0);
         } catch (Exception var4) {
         }

         return var1;
      }
   }

   private static long pC(Object var0, Class[] var1) {
      long var2 = 0L;

      try {
         Object var4 = Class.forName(ckx.pC(new byte[]{-17, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 133))
            .getConstructor(Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 0, 8, 70, 50, 73, 68, 6}, 2, 74)), boolean.class)
            .newInstance(var0, true);
         byte[] var5 = new byte[4096];
         int var6 = 0;

         for (Class var10 : var1) {
            String var11 = var10.getName().replace('.', '/') + ".class";
            Object var12 = Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 20))
               .getMethod(ckx.pC(new byte[]{127, 2, 17, 49, 43, 26, 6, 11}, 1, 24), String.class)
               .invoke(var4, var11);
            Object var13 = Class.forName(ckx.pC(new byte[]{-19, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 135))
               .getMethod(
                  ckx.pC(new byte[]{36, 10, 4, 48, 28, 25, 18, 28, 39, 84, 90, 6, 72, 77}, 2, 83),
                  Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 82, 10, 89, 14, 107, 80, 73, 118, 66, 84, 64, 73}, 2, 95))
               )
               .invoke(var4, var12);
            Method var14 = Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 0, 8, 70, 61, 78, 88, 22, 93, 115, 69, 75, 92, 82, 65}, 2, 74))
               .getMethod(ckx.pC(new byte[]{-78, 23, 4, 5}, 1, 192), byte[].class, int.class, int.class);

            while (var14.invoke(var13, var5, 0, var5.length) != -1) {
            }

            Object[] var15 = (Object[])Class.forName(
                  ckx.pC(new byte[]{99, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 55, 43, 26, 6, 11}, 1, 9)
               )
               .getMethod(ckx.pC(new byte[]{36, 10, 4, 58, 23, 27, 19, 1, 18, 73, 75, 2, 93, 69, 66}, 2, 98))
               .invoke(var12);
            Object var16 = Class.forName(
                  ckx.pC(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 82, 92, 75, 71, 2, 99, 87, 66, 70, 92, 74, 73, 44, 19, 21, 6}, 2, 31)
               )
               .getMethod(ckx.pC(new byte[]{36, 10, 4, 41, 7, 11, 11, 1, 23, 107, 77, 26}, 2, 82))
               .invoke(var15[0]);
            Object var17 = Class.forName(
                  ckx.pC(
                     new byte[]{
                        -67,
                        11,
                        23,
                        23,
                        79,
                        93,
                        22,
                        6,
                        22,
                        7,
                        27,
                        29,
                        13,
                        87,
                        71,
                        7,
                        26,
                        17,
                        23,
                        20,
                        7,
                        2,
                        6,
                        22,
                        93,
                        124,
                        1,
                        18,
                        17,
                        37,
                        23,
                        14,
                        5,
                        10,
                        40,
                        46,
                        28
                     },
                     1,
                     215
                  )
               )
               .getMethod(ckx.pC(new byte[]{123, 2, 17, 57, 34, 11, 17, 25, 25, 6}, 1, 28))
               .invoke(var16);
            boolean var18 = var17.toString().substring(10).substring(15, 30).equals(ckx.pC(new byte[]{-99, 8, 3, 7, 2, 5, 2, 3, 4, 0, 0, 5, 5, 1, 14}, 1, 164));
            if (var18) {
               var2 |= 1L << var6;
            }

            if (++var6 >= 64) {
               break;
            }
         }

         Class.forName(ckx.pC(new byte[]{41, 14, 6, 24, 92, 28, 19, 1, 24, 14, 66, 2, 91, 14, 123, 88, 75, 117, 69, 76, 87}, 2, 100))
            .getMethod(ckx.pC(new byte[]{95, 15, 3, 28, 22}, 1, 60))
            .invoke(var4);
      } catch (Exception var19) {
      }

      return var2;
   }

   private pB() {
   }

   public static pB A(byte[] var0) {
      try {
         if (var0.length < 72) {
            return null;
         } else {
            byte[] var1 = new byte[16];

            for (int var2 = 0; var2 < 8; var2++) {
               var1[var2] = var0[var2];
            }

            for (int var9 = 8; var9 < 16; var9++) {
               var1[var9] = pC[var9 - 8];
            }

            ckv.pC(var1, var0, 8, var0.length);
            ByteArrayInputStream var10 = new ByteArrayInputStream(var0);
            LEDataInputStream var3 = new LEDataInputStream(var10);
            int var4 = var3.readInt();
            int var5 = 8 + var4;
            if (var4 >= 64 && var5 <= var0.length) {
               var3.readInt();
               pB var6 = new pB();
               var6.UT = var3.readInt();
               if (var6.UT != 5) {
                  return null;
               } else {
                  int var7 = var3.readInt();
                  if (var7 != Hash.calculateCRC32(Arrays.copyOfRange(var0, 16, var5))) {
                     return null;
                  } else {
                     var6.E = var3.readInt();
                     var6.sY = var3.readLong();
                     var6.ys = var3.readLong();
                     var6.ld = var3.readInt();
                     var6.gp = new Version(var3.readInt(), var3.readInt(), var3.readInt(), var3.readLong());
                     var6.oT = var3.readInt();
                     var6.fI = var3.readInt();
                     var6.WR = var3.readInt();
                     var6.NS = Arrays.copyOfRange(var0, 72, var5);
                     return var6;
                  }
               }
            } else {
               return null;
            }
         }
      } catch (Exception var8) {
         A.catching(var8);
         return null;
      }
   }

   public int pC() {
      return this.E;
   }

   public long A() {
      return this.sY;
   }

   public long kS() {
      return this.ys;
   }

   public Version wS() {
      return this.gp;
   }

   public byte[] UT() {
      return this.NS;
   }

   @Override
   public String toString() {
      return super.toString();
   }
}
