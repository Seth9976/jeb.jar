package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MO {
   static boolean q(byte[] var0) {
      return q(Strings.decodeASCII(var0));
   }

   static boolean q(String var0) {
      return var0.isEmpty();
   }

   static boolean RF(byte[] var0) {
      return RF(Strings.decodeASCII(var0));
   }

   static boolean RF(String var0) {
      return var0.equals("OK");
   }

   private static int Uv(byte[] var0) {
      if (var0.length < 3 || var0[0] != 69) {
         return 0;
      } else if (var0.length == 3) {
         return 1;
      } else {
         return var0[3] == 59 ? 2 : 0;
      }
   }

   static boolean xK(byte[] var0) {
      return Uv(var0) != 0;
   }

   private static int zz(String var0) {
      if (var0.length() < 3 || var0.charAt(0) != 'E') {
         return 0;
      } else if (var0.length() == 3) {
         return 1;
      } else {
         return var0.charAt(3) == 59 ? 2 : 0;
      }
   }

   static boolean xK(String var0) {
      return zz(var0) != 0;
   }

   static Couple Dw(String var0) {
      int var1 = zz(var0);
      if (var1 == 0) {
         return null;
      } else {
         int var2 = Conversion.stringToInt(var0.substring(1, 3), -1, 16) & 0xFF;
         if (var2 == -1) {
            return null;
         } else if (var1 == 1) {
            return new Couple(var2, null);
         } else {
            String var3 = var0.substring(4);
            return new Couple(var2, var3);
         }
      }
   }

   static void q(String var0, String var1) throws WI {
      if (var1.isEmpty()) {
         throw new JG(var0);
      } else {
         Couple var2 = Dw(var1);
         if (var2 != null) {
            throw new IW(var0, (Integer)var2.getFirst(), (String)var2.getSecond());
         }
      }
   }

   static Map Uv(String var0) {
      LinkedHashMap var1 = new LinkedHashMap();

      for (String var5 : var0.split(";")) {
         if (!var5.isEmpty()) {
            String[] var6 = var5.split(":");
            if (var6.length == 2) {
               String var7 = var6[0];
               String var8 = var6[1];
               var1.put(var7, var8);
            }
         }
      }

      return var1;
   }

   static List oW(String var0) {
      ArrayList var1 = new ArrayList();

      for (String var5 : var0.split(",")) {
         if (!var5.isEmpty()) {
            var1.add(var5);
         }
      }

      return var1;
   }

   static String gO(String var0) {
      return q(var0, true);
   }

   static String q(String var0, boolean var1) {
      byte[] var2 = Formatter.hexStringToByteArray(var0);
      if (var2 == null) {
         return var1 ? var0 : null;
      } else {
         return Strings.decodeASCII(var2);
      }
   }

   static int q(String var0, int var1) throws IOException {
      try {
         return Integer.parseInt(var0, var1);
      } catch (NumberFormatException var3) {
         throw new IOException("Cannot parse string to int", var3);
      }
   }

   static int nf(String var0) throws IOException {
      return q(var0, 16);
   }

   static long RF(String var0, int var1) throws IOException {
      try {
         return Long.parseLong(var0, var1);
      } catch (NumberFormatException var3) {
         throw new IOException("Cannot parse string to long", var3);
      }
   }

   static long gP(String var0) throws IOException {
      return RF(var0, 16);
   }

   static byte[] za(String var0) throws IOException {
      if (var0.length() % 2 != 0) {
         throw new IOException("Illegal hex-encoded data: odd character count: " + var0.length());
      } else {
         int var1 = var0.length() / 2;
         byte[] var2 = new byte[var1];

         for (int var3 = 0; var3 < var1; var3++) {
            try {
               int var4 = Integer.parseInt(var0.substring(var3 * 2, var3 * 2 + 2), 16);
               var2[var3] = (byte)var4;
            } catch (NumberFormatException var5) {
               throw new IOException("Illegal hex-encoded data", var5);
            }
         }

         return var2;
      }
   }

   static String Dw(byte[] var0) {
      return q(var0, 0, var0.length);
   }

   static String q(byte[] var0, int var1, int var2) {
      if (var0 != null && var1 >= 0 && var2 <= var0.length && var1 <= var2) {
         StringBuilder var3 = new StringBuilder();

         for (int var4 = var1; var4 < var2; var4++) {
            Strings.ff(var3, "%02x", var0[var4]);
         }

         return var3.toString();
      } else {
         throw new IllegalArgumentException();
      }
   }

   static int[] lm(String var0) throws IOException {
      try {
         int[] var1 = new int[]{0, 0};
         if (var0.startsWith("p")) {
            int var2 = var0.indexOf(46);
            if (var2 < 0) {
               return null;
            }

            var1[0] = Integer.parseInt(var0.substring(1, var2), 16);
            var0 = var0.substring(var2 + 1);
         }

         var1[1] = Integer.parseInt(var0, 16);
         return var1;
      } catch (NumberFormatException var3) {
         throw new IOException("Cannot parse thread-id", var3);
      }
   }

   static long q(String var0, Endianness var1) throws IOException {
      byte[] var2 = za(var0);
      return q(var2, var1);
   }

   static long q(byte[] var0, Endianness var1) throws IOException {
      ByteBuffer var2 = ByteBuffer.wrap(var0).order(var1.toByteOrder());
      switch (var0.length) {
         case 1:
            return var2.get() & 255L;
         case 2:
            return var2.getShort() & 65535L;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            throw new IOException("Unrecognized length for primitive: " + var0.length);
         case 4:
            return var2.getInt() & 4294967295L;
         case 8:
            return var2.getLong();
      }
   }
}
