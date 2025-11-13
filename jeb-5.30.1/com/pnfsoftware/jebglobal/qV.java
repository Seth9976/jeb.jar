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

public class qV {
   private static final ILogger RF = GlobalLog.getLogger(qV.class);
   private static final int xK = 5;
   private static final int Dw = 72;
   private static final long Uv = System.currentTimeMillis();
   private static final Random oW = new Random(Uv);
   public static byte[] q = new byte[]{69, 103, -94, -103, 33, -125, -15, 16};
   private int gO;
   private int nf;
   private long gP;
   private long za;
   private int lm;
   private Version zz;
   private int JY;
   private int HF;
   private int LK;
   private byte[] io;

   public static Random q() {
      return oW;
   }

   public static byte[] q(long var0, byte[] var2, int[] var3) {
      if (var2 == null) {
         return null;
      } else {
         try {
            ByteArrayOutputStream var4 = new ByteArrayOutputStream();
            LEDataOutputStream var5 = new LEDataOutputStream(var4);
            var5.writeInt(64 + var2.length);
            var5.writeInt(oW.nextInt());
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
            int var6 = (int)((System.currentTimeMillis() - Uv) / 1000L);
            var5.writeInt(var6);
            int var7 = zz();
            var5.writeInt(var7);
            int var8 = oW.nextInt();
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
               var11[var14] = q[var14 - 8];
            }

            cvk.q(var11, var9, 8, var9.length);
            if (var3 != null && var3.length >= 1) {
               var3[0] = var8;
            }

            return var9;
         } catch (Exception var13) {
            return null;
         }
      }
   }

   public static String q(byte[] var0) {
      try {
         return Formatter.byteArrayToHexString(var0);
      } catch (Exception var1) {
         return null;
      }
   }

   public static byte[] q(String var0) {
      try {
         return Formatter.hexStringToByteArray(var0);
      } catch (Exception var1) {
         return null;
      }
   }

   private static int zz() {
      Class[] var0 = new Class[]{
         AbstractContext.class, Xa.class, AbstractClientContext.class, vb.class, oL.class, Vj.class, Bu.class, bK.class, qV.class, PY.class
      };
      return (int)q(var0);
   }

   private static File q(Class var0) throws Exception {
      Object var1 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 107, 15, 72, 83, 66}, 2, 221))
         .getMethod(cvm.q(new byte[]{36, 10, 4, 41, 0, 6, 19, 13, 23, 84, 65, 12, 71, 100, 94, 84, 88, 90, 66}, 2, 241))
         .invoke(var0);
      Object var2 = Class.forName(
            cvm.q(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 97, 75, 86, 71, 73, 67, 70, 89, 93, 90, 104, 79, 34, 19, 8, 13}, 2, 168)
         )
         .getMethod(cvm.q(new byte[]{-115, 2, 17, 55, 44, 11, 1, 54, 60, 26, 7, 17, 6}, 1, 234))
         .invoke(var1);
      Object var3 = Class.forName(cvm.q(new byte[]{41, 14, 6, 24, 92, 26, 2, 11, 1, 82, 65, 23, 80, 14, 114, 86, 93, 86, 127, 79, 71, 66, 81, 81}, 2, 128))
         .getMethod(cvm.q(new byte[]{36, 10, 4, 53, 29, 10, 6, 28, 29, 79, 70}, 2, 97))
         .invoke(var2);
      Object var4 = Class.forName(
            cvm.q(
               new byte[]{
                  32, 0, 29, 87, 2, 7, 1, 27, 27, 70, 92, 20, 72, 82, 84, 23, 83, 86, 78, 14, 71, 68, 91, 88, 2, 78, 42, 6, 79, 54, 62, 41, 117, 21, 7, 8
               },
               2,
               207
            )
         )
         .getMethod(cvm.q(new byte[]{-117, 7, 30, 56, 59, 41, 47, 5, 9}, 1, 254), Class.forName("java.net.URL"))
         .invoke(null, var3);
      return (File)var4;
   }

   private static long q(Class[] var0) {
      if (var0.length == 0) {
         return 0L;
      } else {
         long var1 = 0L;

         try {
            File var3 = q(var0[0]);
            var1 = q(var3, var0);
         } catch (Exception var4) {
         }

         return var1;
      }
   }

   private static long q(Object var0, Class[] var1) {
      long var2 = 0L;

      try {
         Object var4 = Class.forName(cvm.q(new byte[]{13, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 103))
            .getConstructor(Class.forName(cvm.q(new byte[]{86, 11, 23, 23, 79, 71, 6, 65, 104, 47, 5, 9}, 1, 60)), boolean.class)
            .newInstance(var0, true);
         byte[] var5 = new byte[4096];
         int var6 = 0;

         for (Class var10 : var1) {
            String var11 = var10.getName().replace('.', '/') + ".class";
            Object var12 = Class.forName(cvm.q(new byte[]{-59, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 175))
               .getMethod(cvm.q(new byte[]{36, 10, 4, 60, 28, 29, 21, 17}, 2, 226), String.class)
               .invoke(var4, var11);
            Object var13 = Class.forName(cvm.q(new byte[]{-98, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 244))
               .getMethod(
                  cvm.q(new byte[]{18, 2, 17, 61, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 117),
                  Class.forName(cvm.q(new byte[]{-43, 11, 23, 23, 79, 91, 1, 29, 5, 66, 84, 19, 25, 94, 116, 51, 25, 53, 43, 26, 6, 11}, 1, 191))
               )
               .invoke(var4, var12);
            Method var14 = Class.forName(cvm.q(new byte[]{-86, 11, 23, 23, 79, 71, 6, 65, 103, 39, 30, 5, 1, 39, 39, 6, 23, 4, 12}, 1, 192))
               .getMethod(cvm.q(new byte[]{49, 10, 17, 29}, 2, 13), byte[].class, int.class, int.class);

            while (var14.invoke(var13, var5, 0, var5.length) != -1) {
            }

            Object[] var15 = (Object[])Class.forName(
                  cvm.q(new byte[]{-11, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 55, 43, 26, 6, 11}, 1, 159)
               )
               .getMethod(cvm.q(new byte[]{69, 2, 17, 55, 38, 23, 6, 29, 15, 15, 10, 2, 21, 17, 22}, 1, 34))
               .invoke(var12);
            Object var16 = Class.forName(
                  cvm.q(new byte[]{-69, 11, 23, 23, 79, 93, 22, 6, 22, 7, 27, 29, 13, 87, 77, 6, 23, 6, 90, 109, 38, 23, 6, 29, 15, 15, 10, 2, 21, 17}, 1, 209)
               )
               .getMethod(cvm.q(new byte[]{-34, 2, 17, 36, 37, 23, 14, 5, 10, 40, 46, 28}, 1, 185))
               .invoke(var15[0]);
            Object var17 = Class.forName(
                  cvm.q(
                     new byte[]{
                        14,
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
                     100
                  )
               )
               .getMethod(cvm.q(new byte[]{97, 2, 17, 57, 34, 11, 17, 25, 25, 6}, 1, 6))
               .invoke(var16);
            boolean var18 = var17.toString()
               .substring(10)
               .substring(15, 30)
               .equals(cvm.q(new byte[]{122, 94, 66, 76, 69, 91, 87, 91, 67, 23, 31, 81, 30, 22, 9}, 2, 94));
            if (var18) {
               var2 |= 1L << var6;
            }

            if (++var6 >= 64) {
               break;
            }
         }

         Class.forName(cvm.q(new byte[]{-58, 11, 23, 23, 79, 91, 1, 29, 5, 66, 68, 11, 19, 92, 100, 43, 19, 52, 47, 5, 9}, 1, 172))
            .getMethod(cvm.q(new byte[]{32, 3, 31, 10, 23}, 2, 58))
            .invoke(var4);
      } catch (Exception var19) {
      }

      return var2;
   }

   private qV() {
   }

   public static qV RF(byte[] var0) {
      try {
         if (var0.length < 72) {
            return null;
         } else {
            byte[] var1 = new byte[16];

            for (int var2 = 0; var2 < 8; var2++) {
               var1[var2] = var0[var2];
            }

            for (int var9 = 8; var9 < 16; var9++) {
               var1[var9] = q[var9 - 8];
            }

            cvk.q(var1, var0, 8, var0.length);
            ByteArrayInputStream var10 = new ByteArrayInputStream(var0);
            LEDataInputStream var3 = new LEDataInputStream(var10);
            int var4 = var3.readInt();
            int var5 = 8 + var4;
            if (var4 >= 64 && var5 <= var0.length) {
               var3.readInt();
               qV var6 = new qV();
               var6.gO = var3.readInt();
               if (var6.gO != 5) {
                  return null;
               } else {
                  int var7 = var3.readInt();
                  if (var7 != Hash.calculateCRC32(Arrays.copyOfRange(var0, 16, var5))) {
                     return null;
                  } else {
                     var6.nf = var3.readInt();
                     var6.gP = var3.readLong();
                     var6.za = var3.readLong();
                     var6.lm = var3.readInt();
                     var6.zz = new Version(var3.readInt(), var3.readInt(), var3.readInt(), var3.readLong());
                     var6.JY = var3.readInt();
                     var6.HF = var3.readInt();
                     var6.LK = var3.readInt();
                     var6.io = Arrays.copyOfRange(var0, 72, var5);
                     return var6;
                  }
               }
            } else {
               return null;
            }
         }
      } catch (Exception var8) {
         RF.catching(var8);
         return null;
      }
   }

   public int RF() {
      return this.gO;
   }

   public int xK() {
      return this.nf;
   }

   public long Dw() {
      return this.gP;
   }

   public long Uv() {
      return this.za;
   }

   public int oW() {
      return this.lm;
   }

   public Version gO() {
      return this.zz;
   }

   public int nf() {
      return this.JY;
   }

   public int gP() {
      return this.HF;
   }

   public int za() {
      return this.LK;
   }

   public byte[] lm() {
      return this.io;
   }

   @Override
   public String toString() {
      return super.toString();
   }
}
