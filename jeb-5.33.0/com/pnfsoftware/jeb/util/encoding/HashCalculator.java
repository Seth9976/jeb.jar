package com.pnfsoftware.jeb.util.encoding;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import java.util.zip.CRC32;

public class HashCalculator {
   private static final int PAGE_SIZE = 16384;
   public static final int CRC32 = 1;
   public static final int ADLER32 = 2;
   public static final int MD5 = 4;
   public static final int SHA1 = 8;
   public static final int SHA256 = 16;
   private InputStream in;
   private int flags;
   private boolean computed;
   private long size = -1L;
   private long crc32 = -1L;
   private long adler32 = -1L;
   private byte[] md5;
   private byte[] sha1;
   private byte[] sha256;

   public static final Long crc32(InputStream var0) {
      return singleCrcCalculation(var0, 1);
   }

   public static final Long adler32(InputStream var0) {
      return singleCrcCalculation(var0, 2);
   }

   private static final Long singleCrcCalculation(InputStream var0, int var1) {
      try {
         HashCalculator var2 = new HashCalculator(var0, var1);
         if (!var2.compute()) {
            return null;
         } else {
            switch (var1) {
               case 1:
                  return var2.getCrc32();
               case 2:
                  return var2.getAdler32();
               default:
                  return null;
            }
         }
      } catch (IOException var3) {
         return null;
      }
   }

   public static final byte[] md5(InputStream var0) {
      return singleHashCalculation(var0, 4);
   }

   public static final byte[] sha1(InputStream var0) {
      return singleHashCalculation(var0, 8);
   }

   public static final byte[] sha256(InputStream var0) {
      return singleHashCalculation(var0, 16);
   }

   private static final byte[] singleHashCalculation(InputStream var0, int var1) {
      try {
         HashCalculator var2 = new HashCalculator(var0, var1);
         if (!var2.compute()) {
            return null;
         } else {
            switch (var1) {
               case 4:
                  return var2.getMd5();
               case 8:
                  return var2.getSha1();
               case 16:
                  return var2.getSha256();
               default:
                  return null;
            }
         }
      } catch (IOException var3) {
         return null;
      }
   }

   public HashCalculator(InputStream var1, int var2) {
      this.in = var1;
      this.flags = var2;
   }

   public long getSize() {
      if (this.size < 0L) {
         throw new IllegalStateException("Stream size is currently undetermined");
      } else {
         return this.size;
      }
   }

   public long getCrc32() {
      if (this.doAlgo(1) && this.crc32 >= 0L) {
         return this.crc32;
      } else {
         throw new IllegalStateException("CRC-32 value was not computed");
      }
   }

   public long getAdler32() {
      if (this.doAlgo(2) && this.adler32 >= 0L) {
         return this.adler32;
      } else {
         throw new IllegalStateException("Adler32 value was not computed");
      }
   }

   public byte[] getMd5() {
      if (this.doAlgo(4) && this.md5 != null) {
         return this.md5;
      } else {
         throw new IllegalStateException("MD5 value was not computed");
      }
   }

   public byte[] getSha1() {
      if (this.doAlgo(8) && this.sha1 != null) {
         return this.sha1;
      } else {
         throw new IllegalStateException("SHA-1 value was not computed");
      }
   }

   public byte[] getSha256() {
      if (this.doAlgo(16) && this.sha256 != null) {
         return this.sha256;
      } else {
         throw new IllegalStateException("SHA-256 value was not computed");
      }
   }

   private boolean doAlgo(int var1) {
      return (this.flags & var1) != 0;
   }

   public boolean compute() throws IOException {
      if (this.computed) {
         throw new IllegalStateException("Digests were already computed");
      } else {
         this.computed = true;

         CRC32 var1;
         Adler32 var2;
         MessageDigest var3;
         MessageDigest var4;
         MessageDigest var5;
         try {
            var1 = this.doAlgo(1) ? new CRC32() : null;
            var2 = this.doAlgo(2) ? new Adler32() : null;
            var3 = this.doAlgo(4) ? MessageDigest.getInstance("MD5") : null;
            var4 = this.doAlgo(8) ? MessageDigest.getInstance("SHA-1") : null;
            var5 = this.doAlgo(16) ? MessageDigest.getInstance("SHA-256") : null;
         } catch (NoSuchAlgorithmException var10) {
            return false;
         }

         long var6 = 0L;
         byte[] var9 = new byte[16384];

         int var8;
         while ((var8 = this.in.read(var9, 0, var9.length)) != -1) {
            if (var1 != null) {
               var1.update(var9, 0, var8);
            }

            if (var2 != null) {
               var2.update(var9, 0, var8);
            }

            if (var3 != null) {
               var3.update(var9, 0, var8);
            }

            if (var4 != null) {
               var4.update(var9, 0, var8);
            }

            if (var5 != null) {
               var5.update(var9, 0, var8);
            }

            var6 += var8;
         }

         this.size = var6;
         if (var1 != null) {
            this.crc32 = var1.getValue();
         }

         if (var2 != null) {
            this.adler32 = var2.getValue();
         }

         if (var3 != null) {
            this.md5 = var3.digest();
         }

         if (var4 != null) {
            this.sha1 = var4.digest();
         }

         if (var5 != null) {
            this.sha256 = var5.digest();
         }

         return true;
      }
   }
}
