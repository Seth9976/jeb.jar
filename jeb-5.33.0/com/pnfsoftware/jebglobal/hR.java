package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

public class hR {
   public static byte[] pC = new byte[]{69, 103, -94, -103, 1, -125, -15, 16};
   private static Random A = new Random(System.currentTimeMillis());
   private static byte[] kS = "java".getBytes(Charset.forName("UTF-8"));
   private String wS;
   private String UT;
   private String E;
   private int sY;
   private long ys;
   private int ld;
   private int gp;
   private int oT;
   private String fI;
   private int WR;
   private int NS;

   public hR(File var1) {
      try {
         for (String var3 : IO.readLines(var1)) {
            var3 = var3.trim();
            if (var3.startsWith(ckx.pC(new byte[]{96, 55, 13, 5, 13, 5, 21, 29, 6, 1, 84}, 1, 54))) {
               this.pC(var3.substring(11).trim());
               break;
            }
         }
      } catch (Exception var4) {
      }
   }

   private void pC(String var1) throws IOException {
      byte[] var2 = Formatter.hexStringToByteArray(var1);
      ckv.pC(kS, var2, 0, var2.length);
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
            var11.put(pC);
            ckv.pC(var10, var9);
            var15 = new LEDataInputStream(new ByteArrayInputStream(var9));
            int var12 = var15.readInt();
            if (var12 == 1) {
               int var13 = var15.readInt() & -1;
               int var14 = Hash.calculateCRC32(Arrays.copyOfRange(var9, 8, var9.length));
               if (var13 != var14) {
                  throw new RuntimeException();
               } else {
                  var15.readLong();
                  this.wS = var15.readUTF();
                  this.UT = var15.readUTF();
                  this.E = var15.readUTF();
                  this.sY = var15.readInt();
                  this.ys = var15.readLong();
                  this.ld = var15.readInt();
                  this.gp = var15.readInt();
                  this.oT = var15.readInt();
                  this.fI = var15.readUTF();
                  this.WR = var15.readInt();
                  this.NS = var15.readInt();
               }
            } else {
               throw new RuntimeException();
            }
         }
      }
   }

   public String pC() {
      return this.wS;
   }

   public String A() {
      return this.UT;
   }

   public String kS() {
      return this.E;
   }

   public int wS() {
      return this.sY;
   }

   public long UT() {
      return this.ys;
   }

   public int E() {
      return this.ld;
   }

   public int sY() {
      return this.gp;
   }

   public int ys() {
      return this.oT;
   }

   public String ld() {
      return this.fI;
   }
}
