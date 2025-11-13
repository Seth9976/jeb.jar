package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractClientContext;
import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.net.ssl.SSLSocket;

public class DH implements Runnable {
   private static final ILogger pC = GlobalLog.getLogger(DH.class);
   private Sv A;
   private Socket kS;
   private K wS;
   private String UT;

   public DH(Socket var1, Sv var2, K var3) {
      if (var1 != null && var2 != null && var3 != null) {
         this.kS = var1;
         this.A = var2;
         this.wS = var3;
         String var4 = var1.getInetAddress().getHostAddress();
         int var5 = var1.getPort();
         this.UT = Strings.ff("%s:%d", var4, var5);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void run() {
      this.pC();
   }

   private void pC() {
      pC.trace(ckx.pC(new byte[]{102, 28, 74, 89, 33, 12, 21, 30, 29, 78, 79, 67, 74, 76, 88, 92, 87, 71, 2, 14, 28}, 2, 73), this.UT);

      try {
         if (this.kS instanceof SSLSocket var3) {
            var3.setEnabledCipherSuites(var3.getSupportedCipherSuites());

            try {
               var3.startHandshake();
            } catch (IOException var54) {
               pC.trace("%s: Failed SSL handshake", this.UT);
               return;
            }
         }

         DataInputStream var1 = new DataInputStream(this.kS.getInputStream());
         DataOutputStream var2 = new DataOutputStream(this.kS.getOutputStream());

         while (true) {
            String var57;
            try {
               var57 = var1.readLine();
            } catch (SocketTimeoutException var51) {
               return;
            } catch (SocketException var52) {
               return;
            }

            if (var57 == null) {
               return;
            }

            String[] var4 = var57.split("\\s+", 0);
            if (var4.length != 3) {
               return;
            }

            String var6 = var4[0];
            boolean var5;
            if (var6.equals("GET")) {
               var5 = false;
            } else {
               if (!var6.equals("POST")) {
                  return;
               }

               var5 = true;
            }

            String var7 = var4[1];
            boolean var8 = false;
            if (var7.endsWith(".ico") || var7.endsWith(".png") || var7.endsWith(".gif") || var7.endsWith(".jpg") || var7.endsWith(".jpeg")) {
               var8 = true;
            }

            String var10 = var4[2];
            byte var9;
            if (var10.equals("HTTP/1.0")) {
               var9 = 0;
            } else {
               if (!var10.equals("HTTP/1.1")) {
                  return;
               }

               var9 = 1;
            }

            String var11 = null;
            String var12 = null;

            while (true) {
               var57 = var1.readLine();
               if (var57 == null) {
                  return;
               }

               if (var57.isEmpty()) {
                  if (var9 == 1 && var11 == null || var5 && var12 == null || !var5 && var12 != null) {
                     return;
                  }

                  String var59 = null;
                  if (var12 != null) {
                     int var61;
                     try {
                        var61 = Integer.parseInt(var12);
                     } catch (Exception var53) {
                        return;
                     }

                     if (var61 > 0) {
                        byte[] var72 = new byte[var61];
                        var1.readFully(var72);
                        var59 = this.pC(var72, null);
                        if (var59 != null && var59.isEmpty()) {
                           return;
                        }
                     }
                  } else if (!var5 && var8) {
                     var59 = "";
                  } else if (!var5) {
                     Sv.Av var60 = this.A.pC();
                     String var63 = ckx.pC(
                        new byte[]{
                           127,
                           78,
                           52,
                           54,
                           49,
                           61,
                           62,
                           56,
                           49,
                           0,
                           64,
                           23,
                           68,
                           76,
                           15,
                           51,
                           5,
                           91,
                           88,
                           77,
                           94,
                           14,
                           56,
                           9,
                           68,
                           69,
                           46,
                           22,
                           95,
                           105,
                           80,
                           17,
                           73,
                           21,
                           2,
                           1,
                           17,
                           37,
                           55,
                           98,
                           73,
                           55,
                           28,
                           78,
                           21,
                           20,
                           9,
                           5,
                           0,
                           12,
                           19,
                           72,
                           74,
                           7,
                           71,
                           84,
                           45,
                           9,
                           82,
                           42,
                           78,
                           70,
                           15,
                           13,
                           21,
                           23,
                           30,
                           120,
                           89,
                           17,
                           10,
                           22,
                           15,
                           91,
                           110
                        },
                        2,
                        170
                     );
                     StringBuilder var16 = new StringBuilder(var63);
                     var63 = ckx.pC(
                        new byte[]{
                           75,
                           84,
                           89,
                           15,
                           2,
                           93,
                           65,
                           72,
                           26,
                           23,
                           3,
                           91,
                           31,
                           7,
                           86,
                           81,
                           28,
                           116,
                           15,
                           7,
                           126,
                           19,
                           78,
                           95,
                           30,
                           99,
                           44,
                           1,
                           26,
                           6,
                           29,
                           3,
                           0,
                           9,
                           23,
                           82,
                           5,
                           86,
                           79,
                           19,
                           71,
                           89,
                           15,
                           52
                        },
                        1,
                        119
                     );
                     Strings.ff(var16, var63, "https://www.pnfsoftware.com", AbstractContext.app_ver);
                     long var17 = System.currentTimeMillis();
                     long var19 = this.wS.pC;
                     String var21 = TimeFormatter.formatTimestampDelta(var17 - var19);
                     var63 = ckx.pC(
                        new byte[]{
                           127, 31, 78, 58, 29, 7, 19, 26, 27, 76, 68, 6, 91, 0, 66, 77, 88, 65, 88, 69, 86, 16, 23, 70, 12, 65, 40, 29, 93, 76, 28, 91, 42
                        },
                        2,
                        114
                     );
                     Strings.ff(var16, var63, var21);
                     String var22 = Formatter.htmlEscape(AbstractClientContext.formatProcessInfo(true, true, true, true), true);
                     Strings.ff(var16, "<p>%s</p>\n", var22);
                     String var23 = this.wS.A;
                     if (!Strings.isBlank(var23)) {
                        var23 = Formatter.htmlEscape(var23);
                        var63 = ckx.pC(
                           new byte[]{
                              127,
                              31,
                              78,
                              56,
                              22,
                              4,
                              14,
                              6,
                              84,
                              109,
                              77,
                              16,
                              90,
                              65,
                              86,
                              92,
                              3,
                              19,
                              16,
                              83,
                              66,
                              81,
                              92,
                              21,
                              95,
                              84,
                              54,
                              30,
                              4,
                              94,
                              75,
                              7,
                              65,
                              2,
                              5,
                              3,
                              93,
                              0,
                              7,
                              78,
                              13,
                              89,
                              16,
                              79,
                              13,
                              9,
                              20,
                              83,
                              28,
                              6,
                              22,
                              16,
                              0,
                              1,
                              76,
                              76,
                              52,
                              9,
                              87,
                              7,
                              76,
                              85,
                              5,
                              86,
                              81,
                              0,
                              28,
                              93,
                              7,
                              77,
                              89,
                              93,
                              5,
                              21,
                              5,
                              64,
                              125,
                              83,
                              95,
                              9,
                              76,
                              99
                           },
                           2,
                           40
                        );
                        Strings.ff(var16, var63, var23);
                     }

                     var63 = ckx.pC(new byte[]{127, 31, 2, 28, 76, 76, 20, 84, 91, 80, 90, 6, 23, 42}, 2, 183);
                     Strings.ff(var16, var63, AbstractClientContext.generateLicenseInformation());
                     var63 = ckx.pC(
                        new byte[]{
                           99,
                           76,
                           78,
                           125,
                           54,
                           7,
                           0,
                           23,
                           11,
                           26,
                           84,
                           85,
                           6,
                           18,
                           6,
                           2,
                           95,
                           26,
                           28,
                           94,
                           92,
                           27,
                           65,
                           88,
                           19,
                           77,
                           92,
                           30,
                           67,
                           15,
                           5,
                           12,
                           11,
                           26,
                           81,
                           86,
                           83,
                           8,
                           69,
                           12,
                           25,
                           17,
                           4,
                           24,
                           24,
                           77,
                           65,
                           13,
                           0,
                           3,
                           24,
                           18,
                           1,
                           94,
                           26,
                           28,
                           94,
                           92,
                           27,
                           65,
                           88,
                           19,
                           77,
                           92,
                           23,
                           21,
                           19,
                           95,
                           78,
                           52
                        },
                        1,
                        95
                     );
                     Strings.ff(var16, var63, var60.A.length, var60.A.length <= 1 ? "" : "s", var60.pC);
                     var16.append(ckx.pC(new byte[]{127, 26, 28, 71, 120}, 2, 56));
                     int var24 = 1;

                     for (Sv.Av.Av var28 : var60.A) {
                        String var29 = TimeFormatter.formatTimestampLocal(var28.pC);
                        String var30 = TimeFormatter.formatTimestampDelta(var17 - var28.pC);
                        String var31 = TimeFormatter.formatTimestampLocal(var28.A);
                        String var32 = TimeFormatter.formatTimestampDelta(var17 - var28.A);
                        var63 = ckx.pC(
                           new byte[]{
                              -87,
                              80,
                              5,
                              87,
                              125,
                              47,
                              5,
                              12,
                              11,
                              26,
                              84,
                              3,
                              6,
                              65,
                              68,
                              83,
                              7,
                              21,
                              19,
                              6,
                              17,
                              1,
                              68,
                              5,
                              86,
                              83,
                              65,
                              6,
                              8,
                              79,
                              8,
                              13,
                              86,
                              90,
                              5,
                              12,
                              76,
                              13,
                              18,
                              7,
                              84,
                              80,
                              2,
                              29,
                              13,
                              7,
                              69,
                              82,
                              23,
                              6,
                              6,
                              12,
                              31,
                              19,
                              1,
                              68,
                              28,
                              94,
                              92,
                              27,
                              86,
                              79,
                              19,
                              77,
                              92,
                              30,
                              65,
                              6,
                              8,
                              79,
                              8,
                              13,
                              86,
                              90,
                              21,
                              94,
                              16,
                              76,
                              52
                           },
                           1,
                           149
                        );
                        Strings.ff(var16, var63, var24, var30, var29, var32, var31);
                        var63 = ckx.pC(
                           new byte[]{
                              -75,
                              38,
                              22,
                              23,
                              72,
                              26,
                              7,
                              2,
                              86,
                              84,
                              11,
                              12,
                              99,
                              44,
                              2,
                              29,
                              5,
                              1,
                              17,
                              23,
                              72,
                              26,
                              7,
                              2,
                              86,
                              84,
                              11,
                              12,
                              109,
                              40,
                              22,
                              0,
                              18,
                              6,
                              2,
                              95,
                              26,
                              7,
                              2,
                              86,
                              84,
                              27,
                              19,
                              67,
                              5,
                              87,
                              52
                           },
                           1,
                           224
                        );
                        Strings.ff(var16, var63, Formatter.htmlEscape(var28.wS), Formatter.htmlEscape(var28.UT), Formatter.htmlEscape(var28.E));
                        int var33 = 1;

                        for (Sv.Av.Sv var37 : var28.kS) {
                           var16.append(ckx.pC(new byte[]{-40, 73, 25, 82, 52}, 1, 228));
                           var63 = ckx.pC(
                              new byte[]{-124, 80, 5, 87, 119, 39, 29, 7, 21, 15, 13, 6, 69, 3, 6, 65, 94, 26, 5, 86, 73, 31, 65, 88, 19, 67, 5, 87, 52},
                              1,
                              184
                           );
                           Strings.ff(var16, var63, var33, var37.pC, var37.A);
                           var16.append(ckx.pC(new byte[]{127, 64, 5, 21, 76, 99}, 2, 59));
                           var33++;
                        }

                        var24++;
                     }

                     var16.append(ckx.pC(new byte[]{127, 64, 5, 21, 76, 99}, 2, 133));
                     var16.append(ckx.pC(new byte[]{47, 19, 77, 13, 11, 29, 71, 52, 54, 19, 71, 28, 25, 1, 82, 52}, 1, 19));
                     var59 = var16.toString();
                  }

                  short var62 = 400;
                  String var73 = ckx.pC(new byte[]{6, 35, 5, 68, 114, 55, 20, 4, 16, 22, 7}, 1, 68);
                  String var74 = ckx.pC(new byte[]{32, 3, 31, 10, 23}, 2, 103);
                  int var75 = 0;
                  if (var59 != null) {
                     if (var8) {
                        var62 = 404;
                        var73 = ckx.pC(new byte[]{13, 0, 4, 89, 52, 6, 18, 6, 16}, 2, 28);
                     } else {
                        var62 = 200;
                        var73 = ckx.pC(new byte[]{-24, 4}, 1, 167);
                     }

                     var74 = ckx.pC(new byte[]{-12, 14, 0, 21, 93, 76, 13, 5, 31, 19}, 1, 159);
                     var75 = Strings.encodeUTF8(var59).length;
                  }

                  StringBuilder var18 = new StringBuilder();
                  Strings.ff(
                     var18,
                     ckx.pC(new byte[]{11, 59, 36, 41, 93, 88, 73, 77, 16, 0, 13, 7, 9, 5, 66, 52, 51}, 2, 136),
                     Integer.valueOf(var9),
                     Integer.valueOf(var62),
                     var73
                  );
                  Strings.ff(
                     var18,
                     ckx.pC(new byte[]{7, 14, 4, 28, 72, 73, 66, 27, 121, 42}, 2, 235),
                     new SimpleDateFormat(
                           ckx.pC(new byte[]{6, 42, 53, 85, 82, 13, 3, 72, 57, 109, 101, 67, 80, 89, 72, 64, 25, 123, 100, 26, 95, 93, 8, 70, 95, 0, 53}, 2, 31)
                        )
                        .format(new Date())
                  );
                  Strings.ff(
                     var18,
                     ckx.pC(new byte[]{16, 10, 2, 15, 23, 27, 93, 72, 62, 101, 106, 67, 106, 79, 95, 77, 75, 92, 64, 76, 87, 66, 18, 16, 95, 45, 69}, 2, 183),
                     AbstractContext.app_ver
                  );
                  Strings.ff(var18, ckx.pC(new byte[]{-56, 44, 1, 0, 11, 6, 23, 29, 6, 1, 84, 26, 5, 86, 126, 7}, 1, 139), var74);
                  if (var75 > 0) {
                     Strings.ff(
                        var18, ckx.pC(new byte[]{-5, 44, 1, 26, 17, 11, 26, 89, 121, 45, 9, 21, 95, 26, 84, 17, 29, 12, 91, 71, 28, 25, 1, 97, 7}, 1, 184)
                     );
                     Strings.ff(var18, ckx.pC(new byte[]{0, 0, 30, 13, 23, 7, 19, 69, 56, 69, 70, 4, 93, 72, 11, 25, 28, 87, 33, 42}, 2, 60), var75);
                  }

                  var18.append("\r\n");
                  if (var75 > 0) {
                     var18.append(var59);
                  }

                  Object[] var79 = new Object[]{this.UT, Formatter.escapeString(var18)};
                  byte[] var76 = Strings.encodeUTF8(var18.toString());
                  var2.write(var76, 0, var76.length);
                  break;
               }

               char var13 = var57.charAt(0);
               boolean var14 = var13 == ' ' || var13 == '\t';
               if (var14) {
                  return;
               }

               String[] var15 = var57.split("\\s+", 2);
               if (var15.length != 2) {
                  return;
               }

               if (var15[0].equals("Host:")) {
                  var11 = var15[1];
               } else if (var15[0].equals("User-Agent:")) {
                  String var10000 = var15[1];
               } else if (var15[0].equals("Content-Type:")) {
                  String var78 = var15[1];
               } else if (var15[0].equals("Content-Length:")) {
                  var12 = var15[1];
               }
            }
         }
      } catch (IOException var55) {
         pC.catching(var55);
      } finally {
         try {
            if (this.kS != null && !this.kS.isClosed()) {
               this.kS.close();
            }
         } catch (IOException var50) {
            pC.catching(var50);
         }

         pC.trace(ckx.pC(new byte[]{102, 28, 74, 89, 33, 6, 4, 3, 17, 84, 8, 0, 69, 79, 66, 92, 93}, 2, 192), this.UT);
      }
   }

   private String pC(byte[] var1, pB[] var2) {
      try {
         String var3 = Strings.decodeUTF8(var1);
         if (var3.startsWith(ckx.pC(new byte[]{96, 5, 21, 21, 92}, 1, 4))) {
            var3 = var3.substring(5);
         }

         pB var4 = pB.A(pB.pC(var3));
         if (var4 == null) {
            return null;
         } else {
            if (var2 != null && var2.length >= 1) {
               var2[0] = var4;
            }

            byte[] var5 = var4.UT();
            int var6 = var5.length;
            LEDataInputStream var7 = new LEDataInputStream(new ByteArrayInputStream(var5));
            int var8 = var7.readInt();
            long var9 = var7.readLong();
            long var11 = var7.readLong();
            String var13 = null;
            String var14 = null;
            String var15 = null;
            if (var8 == 0 && var6 >= 24) {
               int var16 = var7.readInt();
               if (var16 >= 1) {
                  try {
                     int var17 = var7.readInt();
                     byte[] var18 = new byte[var17];
                     var7.readFully(var18);
                     var13 = Strings.decodeUTF8(var18);
                     var17 = var7.readInt();
                     var18 = new byte[var17];
                     var7.readFully(var18);
                     var14 = Strings.decodeUTF8(var18);
                     var17 = var7.readInt();
                     var18 = new byte[var17];
                     var7.readFully(var18);
                     var15 = Strings.decodeUTF8(var18);
                  } catch (Exception var22) {
                  }
               }
            }

            var7.close();
            if (var8 != 0) {
               if (var8 == 2) {
                  this.A.pC(var9, var11);
                  return "";
               } else {
                  return null;
               }
            } else {
               long var24 = 0L;
               if (var4.A() != Licensing.license_id || var4.pC() != Licensing.user_id) {
                  var24 = 3L;
               } else if (var4.wS().compareTo(AbstractContext.app_ver) < 0) {
                  var24 = 2L;
               } else if (!this.A.pC(var9, var11, this.kS.getInetAddress().getHostAddress(), this.kS.getPort(), var13, var14, var15)) {
                  var24 = 1L;
               }

               ByteArrayOutputStream var29 = new ByteArrayOutputStream();
               LEDataOutputStream var19 = new LEDataOutputStream(var29);
               var19.writeInt(1);
               var19.writeLong(var24);
               var19.close();
               byte[] var20 = pB.pC(this.wS.kS, var29.toByteArray(), null);
               return pB.pC(var20);
            }
         }
      } catch (Exception var23) {
         pC.catching(var23);
         return null;
      }
   }
}
