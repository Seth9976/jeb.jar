package com.pnfsoftware.jebglobal;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ckx {
   private String A;
   private byte[] kS;
   private int wS;
   private int UT;
   static StringBuilder pC = new StringBuilder("Copyright (c) 1993, 2023, Oracle and/or its affiliates. All rights reserved.");

   public static String pC(String var0) {
      return var0;
   }

   public static String pC(byte[] var0, int var1, int var2) {
      return new ckx(var0, var1, var2).pC();
   }

   public ckx(byte[] var1, int var2, int var3) {
      this.kS = var1;
      this.wS = var2;
      this.UT = var3;
   }

   public String pC() {
      if (this.A != null) {
         return this.A;
      } else if (this.kS == null) {
         throw new RuntimeException();
      } else if (this.wS == 0 || this.kS.length == 0) {
         try {
            return new String(this.kS, "UTF-8");
         } catch (Exception var8) {
            return new String(this.kS, Charset.defaultCharset());
         }
      } else if (this.wS == 1) {
         int var9 = this.kS.length;
         byte[] var10 = new byte[var9];
         byte var11 = (byte)this.UT;

         for (int var12 = 0; var12 < var9; var12++) {
            var10[var12] = (byte)(var11 ^ this.kS[var12]);
            var11 = var10[var12];
         }

         try {
            return new String(var10, "UTF-8");
         } catch (Exception var6) {
            return new String(var10, Charset.defaultCharset());
         }
      } else if (this.wS != 2) {
         throw new RuntimeException();
      } else {
         int var1 = this.kS.length;
         byte[] var2 = new byte[var1];
         String var3 = pC.toString();
         int var4 = 0;

         for (int var5 = 0; var5 < var1; var5++) {
            var2[var5] = (byte)(this.kS[var5] ^ (byte)var3.charAt(var4));
            var4 = (var4 + 1) % var3.length();
         }

         try {
            return new String(var2, "UTF-8");
         } catch (Exception var7) {
            return new String(var2, Charset.defaultCharset());
         }
      }
   }

   public static final String A(String var0) {
      return var0;
   }

   public static int kS(String var0) {
      try {
         MessageDigest var1 = MessageDigest.getInstance("MD5");
         byte[] var2 = var1.digest(var0.getBytes("UTF-8"));
         return var2[0] & 0xFF | (var2[1] & 0xFF) << 8 | (var2[2] & 0xFF) << 16 | (var2[3] & 0xFF) << 24;
      } catch (NoSuchAlgorithmException var4) {
         throw new RuntimeException(var4);
      } catch (UnsupportedEncodingException var5) {
         throw new RuntimeException(var5);
      }
   }

   public static boolean pC(String var0, String var1) {
      return var0 == null ? var1 == null : var0.equals(var1);
   }

   public static boolean pC(int var0, int var1, int var2, String var3) {
      if (var3 == null) {
         return false;
      } else {
         int var4 = var3.length();
         if (var4 == var0 && var4 >= 4) {
            int var5 = var3.charAt(0) & 255 | (var3.charAt(var4 / 2) & 255) << 8 | (var3.charAt(var4 - 1) & 255) << 16;
            if (var5 != var1) {
               return false;
            } else {
               try {
                  MessageDigest var6 = MessageDigest.getInstance("MD5");
                  byte[] var7 = var6.digest(var3.getBytes("UTF-8"));
                  int var8 = var7[0] & 255 | (var7[1] & 255) << 8 | (var7[2] & 255) << 16 | (var7[3] & 255) << 24;
                  return var8 == var2;
               } catch (NoSuchAlgorithmException var9) {
                  throw new RuntimeException(var9);
               } catch (UnsupportedEncodingException var10) {
                  throw new RuntimeException(var10);
               }
            }
         } else {
            return false;
         }
      }
   }

   static {
      pC.setCharAt(23, (char)(pC.charAt(23) + 2));
   }
}
