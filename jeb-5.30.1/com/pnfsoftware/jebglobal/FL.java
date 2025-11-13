package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

public class FL {
   public static byte[] q = new byte[]{69, 103, -94, -103, 1, -125, -15, 16};
   private static final int RF = 1;
   private static Random xK = new Random(System.currentTimeMillis());
   private static final int Dw = 4096;
   private static byte[] Uv = "java".getBytes(Charset.forName("UTF-8"));
   private String oW;
   private String gO;
   private String nf;
   private int gP;
   private long za;
   private int lm;
   private int zz;
   private int JY;
   private String HF;
   private int LK;
   private int io;

   public FL(File var1) {
      try {
         for (String var3 : com.pnfsoftware.jeb.util.io.IO.readLines(var1)) {
            var3 = var3.trim();
            if (var3.startsWith(cvm.q(new byte[]{21, 14, 28, 16, 22, 8, 19, 1, 27, 78, 18}, 2, 194))) {
               this.q(var3.substring(11).trim());
               break;
            }
         }
      } catch (Exception var4) {
      }
   }

   private void q(String var1) throws IOException {
      byte[] var2 = Formatter.hexStringToByteArray(var1);
      cvk.q(Uv, var2, 0, var2.length);
      if (var2.length != 4096) {
         throw new RuntimeException();
      } else {
         byte var3 = 0;

         for (int var4 = 0; var4 < 4095; var4++) {
            var3 += var2[var4];
         }

         if ((byte)var3 != var2[4095]) {
            throw new RuntimeException();
         } else {
            LEDataInputStream var15 = new LEDataInputStream(new ByteArrayInputStream(var2));
            int var5 = var15.readInt();
            int var6 = 256 + (var5 & 0xFF);
            var15.skip(var6);
            int var7 = var15.readInt();
            int var8 = var15.readInt();
            byte[] var9 = new byte[var7];
            var15.read(var9);
            var15.close();
            byte[] var10 = new byte[16];
            ByteBuffer var11 = ByteBuffer.wrap(var10);
            var11.order(ByteOrder.LITTLE_ENDIAN);
            var11.putInt(var7);
            var11.putInt(var8);
            var11.put(q);
            cvk.q(var10, var9);
            var15 = new LEDataInputStream(new ByteArrayInputStream(var9));
            int var12 = var15.readInt();
            if (var12 == 1) {
               int var13 = var15.readInt() & -1;
               int var14 = Hash.calculateCRC32(Arrays.copyOfRange(var9, 8, var9.length));
               if (var13 != var14) {
                  throw new RuntimeException();
               } else {
                  var15.readLong();
                  this.oW = var15.readUTF();
                  this.gO = var15.readUTF();
                  this.nf = var15.readUTF();
                  this.gP = var15.readInt();
                  this.za = var15.readLong();
                  this.lm = var15.readInt();
                  this.zz = var15.readInt();
                  this.JY = var15.readInt();
                  this.HF = var15.readUTF();
                  this.LK = var15.readInt();
                  this.io = var15.readInt();
               }
            } else {
               throw new RuntimeException();
            }
         }
      }
   }

   public String q() {
      return this.oW;
   }

   public String RF() {
      return this.gO;
   }

   public String xK() {
      return this.nf;
   }

   public int Dw() {
      return this.gP;
   }

   public long Uv() {
      return this.za;
   }

   public int oW() {
      return this.lm;
   }

   public int gO() {
      return this.zz;
   }

   public int nf() {
      return this.JY;
   }

   public String gP() {
      return this.HF;
   }

   public int za() {
      return this.LK;
   }

   public int lm() {
      return this.io;
   }
}
