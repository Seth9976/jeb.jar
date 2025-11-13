package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tl {
   private static String RF = System.getProperty(cvm.q(new byte[]{97, 28, 93, 64, 15, 12, 8}, 1, 14), "");
   private static String xK = System.getProperty(cvm.q(new byte[]{-12, 6, 22, 23, 92, 64, 15, 12, 8}, 1, 129), "Lambda");
   private static long[] Dw = new long[oW()];
   public static String q = "";

   public static void q() {
      for (int var0 = 0; var0 < Dw.length; var0++) {
         Dw[var0] = -1L;
      }
   }

   private static int oW() {
      return RF.startsWith(cvm.q(new byte[]{(byte)20, (byte)6, (byte)30, (byte)29, (byte)29, (byte)30, (byte)20}, 2, 165)) ? 2 : 1;
   }

   public static synchronized long RF() {
      if (Dw[0] < 0L) {
         Dw[0] = q(0);
      }

      return Dw[0];
   }

   public static synchronized long[] xK() {
      for (int var0 = 0; var0 < Dw.length; var0++) {
         if (Dw[var0] < 0L) {
            Dw[var0] = q(var0);
         }
      }

      return Dw;
   }

   public static synchronized String Dw() {
      return q;
   }

   private static long q(int var0) {
      String var1 = null;
      if (RF.startsWith(cvm.q(new byte[]{66, 62, 7, 10, 11, 24, 4}, 1, 21))) {
         if (var0 == 0) {
            try {
               try {
                  var1 = nf();
                  var1 = var1 + "__";
                  var1 = var1 + gP();
               } catch (Exception var16) {
                  var1 = Uv();
               }
            } catch (Exception var17) {
               q = q + "W1:" + var17.getMessage() + ";";
            }
         } else if (var0 == 1) {
            try {
               try {
                  var1 = gP();
               } catch (Exception var14) {
                  var1 = Uv();
               }
            } catch (Exception var15) {
               q = q + "W2:" + var15.getMessage() + ";";
            }
         }
      } else if (RF.startsWith(cvm.q(new byte[]{4, 44, 2}, 1, 73))) {
         try {
            var1 = za();
         } catch (Exception var13) {
            q = q + "M0:" + var13.getMessage() + ";";
         }
      } else if (RF.startsWith(cvm.q(new byte[]{15, 6, 30, 12, 10}, 2, 165))) {
         try {
            var1 = lm();
         } catch (Exception var12) {
            q = q + "L0:" + var12.getMessage() + ";";
         }

         if (var1 == null) {
            try {
               var1 = zz();
            } catch (Exception var11) {
               q = q + "L1:" + var11.getMessage() + ";";
            }
         }
      }

      if (var1 == null) {
         var1 = cvm.q(new byte[]{-106, 45, 12, 15, 6, 5, 45, 45, 12, 15, 6, 5}, 1, 218);
         var1 = var1 + "__";
         var1 = var1 + xK;
         q = q + "X0:Generated failsafe for:'" + RF + "';";
      }

      try {
         MessageDigest var2 = MessageDigest.getInstance(cvm.q(new byte[]{14, 43, 69}, 2, 12));
         var2.update(var1.getBytes());
         byte[] var3 = var2.digest();
         ByteBuffer var4 = ByteBuffer.wrap(var3);
         var4.order(ByteOrder.LITTLE_ENDIAN);
         long var5 = var4.getLong() & Long.MAX_VALUE;
         String var7 = Formatter.byteArrayToHexString(Hash.calculateSHA256(Strings.encodeASCII(Licensing.license_id + ""))).toLowerCase();
         switch (var7) {
            case "5961a8fdd0d8de904a1ec0793485d94aca9298cad469c36041ede4c9a00b95f5":
               var5 = 3977136519742117954L;
               break;
            case "e527f97ca667eb881e887f76ba74ef9b60e96744bec391d1cca8f31478a58d42":
               var5 = 4774557939076289701L;
         }

         return var5;
      } catch (NoSuchAlgorithmException var10) {
         return 0L;
      }
   }

   private static String gO() {
      String var0 = cvm.q(new byte[]{-84, 42, 10, 7, 17, 8, 63, 61, 0, 27}, 1, 255);
      String var1 = System.getenv(var0);
      return var1 != null
         ? var1 + cvm.q(new byte[]{31, 60, 9, 10, 6, 12, 10, 91, 70, 124, 127, 1, 76, 77, 109, 110, 116, 122, 111, 14, 87, 72, 87}, 2, 24)
         : cvm.q(new byte[]{52, 26, 4, 10}, 1, 67);
   }

   private static String nf() throws Exception {
      String var0 = cvm.q(new byte[]{32, 28, 0, 11, 29, 13, 18, 11, 0}, 2, 28);
      String var1 = cvm.q(new byte[]{36, 10, 4}, 2, 4);
      String var2 = cvm.q(new byte[]{54, 26, 25, 29}, 2, 250);
      String[] var3 = new String[]{gO(), var0, var1, var2};
      String var4 = cvm.q(new byte[]{22, 58, 57, 61}, 2, 134);
      return q(var3, var4);
   }

   private static String gP() throws Exception {
      String var0 = cvm.q(new byte[]{123, 11, 6, 28}, 1, 25);
      String var1 = cvm.q(new byte[]{-83, 2, 17}, 1, 202);
      String var2 = cvm.q(new byte[]{109, 22, 23, 27, 8, 13, 2, 27, 24, 15, 7, 23}, 1, 30);
      String[] var3 = new String[]{gO(), var0, var1, var2};
      String var4 = cvm.q(new byte[]{77, 54, 23, 27, 8, 13, 34, 59, 24, 15, 7, 23}, 1, 30);
      return q(var3, var4);
   }

   private static String q(String[] var0, String var1) throws Exception {
      Process var2 = Runtime.getRuntime().exec(var0);
      InputStream var3 = var2.getInputStream();
      String var4 = "";
      Scanner var5 = new Scanner(var3);

      String var8;
      while (true) {
         try {
            if (var5.hasNext()) {
               String var6 = var5.next();
               var4 = var4 + var6 + "\n";
               if (!var1.equals(var6)) {
                  continue;
               }

               if (!var5.hasNext()) {
                  throw new RuntimeException("Missing value line");
               }

               String var7 = var5.next().trim();
               var8 = var7;
               break;
            }
         } catch (Throwable var10) {
            try {
               var5.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         var5.close();
         throw new RuntimeException("wmic failed output: " + var4);
      }

      var5.close();
      return var8;
   }

   public static String Uv() throws Exception {
      String var0 = cvm.q(new byte[]{49, 10, 23, 87, 23, 17, 2}, 2, 172);
      String var1 = cvm.q(new byte[]{-74, 42, 10, 7, 17, 8, 63, 61, 0, 27}, 1, 229);
      String var2 = System.getenv(var1);
      if (var2 != null) {
         var0 = var2 + cvm.q(new byte[]{-112, 15, 42, 10, 7, 17, 8, 94, 1, 110}, 1, 204) + var0;
      }

      String var3 = cvm.q(new byte[]{-63, 4, 16, 23, 11}, 1, 144);
      String var4 = cvm.q(
         new byte[]{
            11, 36, 60, 52, 46, 58, 40, 46, 32, 119, 105, 49, 108, 124, 124, 80, 90, 65, 67, 83, 93, 86, 70, 104, 111, 82, 54, 2, 21, 12, 11, 23, 65, 17, 6, 29
         },
         2,
         187
      );
      String var5 = cvm.q(new byte[]{14, 14, 19, 17, 27, 7, 2, 47, 1, 73, 76}, 2, 135);
      String[] var6 = new String[]{var0, var3, var4, "/v", var5};
      Process var7 = Runtime.getRuntime().exec(var6);
      InputStream var8 = var7.getInputStream();
      Pattern var9 = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", 2);
      String var10 = "";
      Scanner var11 = new Scanner(var8);

      String var15;
      while (true) {
         try {
            if (var11.hasNext()) {
               String var12 = var11.next();
               var10 = var10 + var12 + "\n";
               Matcher var13 = var9.matcher(var12);
               if (!var13.find()) {
                  continue;
               }

               String var14 = var13.group(0);
               var15 = var14;
               break;
            }
         } catch (Throwable var17) {
            try {
               var11.close();
            } catch (Throwable var16) {
               var17.addSuppressed(var16);
            }

            throw var17;
         }

         var11.close();
         throw new RuntimeException("reg failed output: " + var10);
      }

      var11.close();
      return var15;
   }

   private static String za() throws Exception {
      String[] var0 = cvm.q(
            new byte[]{
               -80,
               90,
               6,
               1,
               93,
               92,
               17,
               11,
               7,
               65,
               92,
               10,
               10,
               7,
               17,
               8,
               50,
               47,
               2,
               29,
               9,
               15,
               5,
               9,
               23,
               82,
               115,
               3,
               24,
               41,
               19,
               22,
               19,
               22,
               19,
               23,
               33,
               37,
               21,
               21,
               53,
               45,
               9,
               21
            },
            1,
            159
         )
         .split(" ");
      Process var1 = Runtime.getRuntime().exec(var0);
      InputStream var2 = var1.getInputStream();
      String var4 = cvm.q(new byte[]{-117, 54, 23, 27, 8, 13, 76, 110, 59, 24, 15, 7, 23}, 1, 216);
      String var5 = "";

      String var3;
      try (BufferedReader var6 = new BufferedReader(new InputStreamReader(var2))) {
         while ((var3 = var6.readLine()) != null) {
            var5 = var5 + var3 + "\n";
            if (var3.indexOf(var4) >= 0) {
               var3 = var3.split(var4)[1];
               int var7 = var3.indexOf(58);
               if (var7 < 0) {
                  throw new RuntimeException("Missing value token");
               }

               return var3.substring(var7 + 1).trim();
            }
         }
      }

      throw new RuntimeException("sp failed output: " + var5);
   }

   private static String lm() throws Exception {
      String var0 = cvm.q(new byte[]{80, 89, 23, 19, 93, 67, 5, 11, 77, 75, 6, 23, 6, 92, 66, 12, 2, 11, 1, 7, 11, 72, 68, 13}, 1, 127);
      String var1 = cvm.q(new byte[]{88, 89, 23, 19, 93, 75, 6, 77, 75, 6, 23, 6, 92, 66, 12, 2, 11, 1, 7, 11, 72, 68, 13}, 1, 119);
      String var2 = cvm.q(new byte[]{105, 74, 17, 23, 76, 66, 12, 2, 11, 1, 7, 11, 72, 68, 13}, 1, 70);

      for (String var6 : new String[]{var0, var1, var2}) {
         File var7 = new File(var6);
         if (var7.canRead()) {
            try (BufferedReader var8 = new BufferedReader(new InputStreamReader(new FileInputStream(var7)))) {
               String var9 = var8.readLine();
               if (var9 != null) {
                  String var10 = var9.trim();
                  if (!var10.isEmpty()) {
                     return var10;
                  }
               }
            }
         }
      }

      throw new RuntimeException("machine-id failed");
   }

   private static String zz() throws Exception {
      String var0 = cvm.q(new byte[]{93, 0, 28, 13, 121}, 1, 8);
      String var1 = cvm.q(new byte[]{17, 13, 3, 7, 9, 113}, 1, 93);
      String var2 = cvm.q(new byte[]{-67, 74, 17, 23, 76, 73, 21, 7, 21, 3}, 1, 146);
      File var3 = new File(var2);
      if (var3.canRead()) {
         String var5;
         try (BufferedReader var4 = new BufferedReader(new InputStreamReader(new FileInputStream(var3)))) {
            while ((var5 = var4.readLine()) != null) {
               var5 = var5.trim();
               if (var5.length() != 0 && !var5.startsWith("#")) {
                  String[] var6 = var5.split("[ \\t]+");
                  if (var6.length >= 2 && var6[1].equals("/")) {
                     String var8 = var6[0];
                     String var7;
                     if (var8.startsWith(var0)) {
                        var7 = var8.substring(var0.length());
                     } else if (var8.startsWith(var1)) {
                        var7 = var8.substring(var1.length());
                     } else {
                        var7 = var8;
                     }

                     return var7.toLowerCase();
                  }
               }
            }
         }
      }

      throw new RuntimeException("fstab failed");
   }

   static {
      q();
   }
}
