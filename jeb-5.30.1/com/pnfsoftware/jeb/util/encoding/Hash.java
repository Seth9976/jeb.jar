package com.pnfsoftware.jeb.util.encoding;

import com.pnfsoftware.jeb.util.base.Assert;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Hash {
   private static final long BLKSIZE = 65536L;
   private static final byte[] blk = new byte[65536];

   public static int calculateCRC32(byte[] var0) {
      CRC32 var1 = new CRC32();
      var1.update(var0);
      return (int)(var1.getValue() & -1L);
   }

   public static byte[] calculateMD5(byte[] var0) {
      try {
         MessageDigest var1 = MessageDigest.getInstance("MD5");
         return var1.digest(var0);
      } catch (NoSuchAlgorithmException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static byte[] calculateSHA1(byte[] var0) {
      try {
         MessageDigest var1 = MessageDigest.getInstance("SHA-1");
         return var1.digest(var0);
      } catch (NoSuchAlgorithmException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static byte[] calculateSHA256(byte[] var0) {
      try {
         MessageDigest var1 = MessageDigest.getInstance("SHA-256");
         return var1.digest(var0);
      } catch (NoSuchAlgorithmException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static void checksumBlockFileUpdate(Checksum var0, File var1) throws IOException {
      checksumBlockFileUpdate(var0, var1, 0L, var1.length());
   }

   public static void checksumBlockFileUpdate(Checksum var0, File var1, long var2, long var4) throws IOException {
      try (RandomAccessFile var6 = new RandomAccessFile(var1, "r")) {
         var6.seek(var2);
         checksumBlockFileUpdate(var0, var6, var4);
      }
   }

   public static void checksumBlockFileUpdate(Checksum var0, RandomAccessFile var1, long var2) throws IOException {
      long var4 = var1.getFilePointer();
      long var6 = var2;
      if (var2 <= 65536L) {
         int var8 = (int)var2;
         if (var1.read(blk, 0, var8) != var8) {
            throw new IOException();
         }

         var0.update(blk, 0, var8);
         var6 = var2 - var8;
      } else {
         long var11 = var4 & 65535L;
         if (var11 != 0L) {
            int var10 = (int)(65536L - var11);
            if (var1.read(blk, 0, var10) != var10) {
               throw new IOException();
            }

            var0.update(blk, 0, var10);
            var6 = var2 - var10;
         }

         while (var6 > 0L) {
            int var12 = (int)Math.min(65536L, var6);
            if (var1.read(blk, 0, var12) != var12) {
               throw new IOException();
            }

            var0.update(blk, 0, var12);
            var6 -= var12;
         }
      }

      Assert.a(var6 == 0L);
   }
}
