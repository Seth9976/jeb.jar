package com.pnfsoftware.jeb.util.io;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;

public class StreamWrappers {
   public static FilterOutputStream getCompressedStream(OutputStream var0) {
      return new LZ4BlockOutputStream(var0);
   }

   public static FilterInputStream getUncompressedStream(InputStream var0) {
      return new LZ4BlockInputStream(var0);
   }

   public static FilterOutputStream getEncryptedStream(OutputStream var0, byte[] var1) {
      return getEncryptedStream(var0, "RC4", var1);
   }

   public static FilterInputStream getDecryptedStream(InputStream var0, byte[] var1) {
      return getDecryptedStream(var0, "RC4", var1);
   }

   public static FilterOutputStream getEncryptedStream(OutputStream var0, String var1, byte[] var2) {
      try {
         SecretKeySpec var3 = new SecretKeySpec(var2, 0, var2.length, var1);
         Cipher var4 = Cipher.getInstance(var1);
         var4.init(1, var3);
         return new CipherOutputStream(var0, var4);
      } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException var5) {
         return new NoopOutputStream(var0);
      }
   }

   public static FilterInputStream getDecryptedStream(InputStream var0, String var1, byte[] var2) {
      try {
         SecretKeySpec var3 = new SecretKeySpec(var2, 0, var2.length, var1);
         Cipher var4 = Cipher.getInstance(var1);
         var4.init(2, var3);
         return new CipherInputStream(var0, var4);
      } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException var5) {
         return new NoopInputStream(var0);
      }
   }
}
