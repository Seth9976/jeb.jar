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

public class iZ implements Runnable {
   private static final ILogger q = GlobalLog.getLogger(iZ.class);
   private CU RF;
   private Socket xK;
   private nI Dw;
   private String Uv;

   public iZ(Socket var1, CU var2, nI var3) {
      if (var1 != null && var2 != null && var3 != null) {
         this.xK = var1;
         this.RF = var2;
         this.Dw = var3;
         String var4 = var1.getInetAddress().getHostAddress();
         int var5 = var1.getPort();
         this.Uv = Strings.ff("%s:%d", var4, var5);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void run() {
      this.q();
   }

   private void q() {
      q.trace(cvm.q(new byte[]{102, 28, 74, 89, 33, 12, 21, 30, 29, 78, 79, 67, 74, 76, 88, 92, 87, 71, 2, 14, 28}, 2, 51), this.Uv);

      try {
         if (this.xK instanceof SSLSocket var3) {
            var3.setEnabledCipherSuites(var3.getSupportedCipherSuites());

            try {
               var3.startHandshake();
            } catch (IOException var54) {
               q.trace("%s: Failed SSL handshake", this.Uv);
               return;
            }
         }

         DataInputStream var1 = new DataInputStream(this.xK.getInputStream());
         DataOutputStream var2 = new DataOutputStream(this.xK.getOutputStream());

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
                        byte[] var71 = new byte[var61];
                        var1.readFully(var71);
                        var59 = this.q(var71, null);
                        if (var59 != null && var59.isEmpty()) {
                           return;
                        }
                     }
                  } else if (!var5 && var8) {
                     var59 = "";
                  } else if (!var5) {
                     CU.eo var60 = this.RF.q();
                     String var63 = cvm.q(
                        new byte[]{
                           21,
                           29,
                           101,
                           11,
                           12,
                           23,
                           13,
                           9,
                           21,
                           101,
                           72,
                           28,
                           25,
                           1,
                           82,
                           52,
                           54,
                           84,
                           28,
                           25,
                           1,
                           82,
                           52,
                           54,
                           84,
                           13,
                           4,
                           5,
                           90,
                           52,
                           54,
                           72,
                           29,
                           29,
                           24,
                           9,
                           91,
                           116,
                           15,
                           7,
                           98,
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
                           78,
                           19,
                           91,
                           29,
                           29,
                           24,
                           9,
                           91,
                           52,
                           54,
                           19,
                           71,
                           13,
                           4,
                           5,
                           90,
                           52,
                           54,
                           94,
                           13,
                           11,
                           29,
                           71,
                           52
                        },
                        1,
                        41
                     );
                     StringBuilder var16 = new StringBuilder(var63);
                     var63 = cvm.q(
                        new byte[]{
                           127,
                           7,
                           65,
                           71,
                           78,
                           8,
                           71,
                           0,
                           6,
                           69,
                           78,
                           94,
                           11,
                           5,
                           66,
                           27,
                           7,
                           121,
                           105,
                           98,
                           14,
                           31,
                           83,
                           10,
                           12,
                           99,
                           32,
                           28,
                           21,
                           17,
                           3,
                           9,
                           76,
                           4,
                           28,
                           68,
                           10,
                           28,
                           78,
                           15,
                           1,
                           69,
                           77,
                           42
                        },
                        2,
                        216
                     );
                     Strings.ff(var16, var63, "https://www.pnfsoftware.com", AbstractContext.app_ver);
                     long var17 = System.currentTimeMillis();
                     long var19 = this.Dw.q;
                     String var21 = TimeFormatter.formatTimestampDelta(var17 - var19);
                     var63 = cvm.q(
                        new byte[]{
                           127, 31, 78, 58, 29, 7, 19, 26, 27, 76, 68, 6, 91, 0, 66, 77, 88, 65, 88, 69, 86, 16, 23, 71, 12, 65, 40, 29, 93, 76, 28, 91, 42
                        },
                        2,
                        77
                     );
                     Strings.ff(var16, var63, var21);
                     String var22 = Formatter.htmlEscape(AbstractClientContext.formatProcessInfo(true, true, true, true), true);
                     Strings.ff(var16, "<p>%s</p>\n", var22);
                     String var23 = this.Dw.RF;
                     if (!Strings.isBlank(var23)) {
                        var23 = Formatter.htmlEscape(var23);
                        var63 = cvm.q(
                           new byte[]{
                              17,
                              76,
                              78,
                              127,
                              37,
                              9,
                              4,
                              7,
                              78,
                              109,
                              40,
                              22,
                              0,
                              18,
                              6,
                              2,
                              95,
                              26,
                              28,
                              79,
                              3,
                              17,
                              15,
                              78,
                              83,
                              7,
                              13,
                              21,
                              9,
                              88,
                              26,
                              69,
                              3,
                              2,
                              8,
                              12,
                              21,
                              29,
                              26,
                              27,
                              10,
                              73,
                              78,
                              12,
                              3,
                              3,
                              29,
                              72,
                              74,
                              31,
                              24,
                              19,
                              1,
                              23,
                              16,
                              14,
                              25,
                              16,
                              94,
                              28,
                              25,
                              2,
                              94,
                              92,
                              27,
                              86,
                              79,
                              19,
                              77,
                              92,
                              2,
                              19,
                              92,
                              3,
                              17,
                              15,
                              80,
                              2,
                              19,
                              95,
                              78,
                              52
                           },
                           1,
                           45
                        );
                        Strings.ff(var16, var63, var23);
                     }

                     var63 = cvm.q(new byte[]{127, 31, 2, 28, 76, 76, 20, 84, 91, 80, 90, 6, 23, 42}, 2, 28);
                     Strings.ff(var16, var63, AbstractClientContext.generateLicenseInformation());
                     var63 = cvm.q(
                        new byte[]{
                           127,
                           31,
                           78,
                           58,
                           7,
                           27,
                           21,
                           13,
                           26,
                           84,
                           8,
                           22,
                           90,
                           65,
                           86,
                           92,
                           3,
                           19,
                           16,
                           66,
                           12,
                           21,
                           86,
                           8,
                           3,
                           66,
                           113,
                           82,
                           2,
                           15,
                           5,
                           0,
                           78,
                           21,
                           75,
                           23,
                           15,
                           71,
                           31,
                           65,
                           17,
                           29,
                           30,
                           85,
                           12,
                           70,
                           7,
                           5,
                           0,
                           6,
                           22,
                           17,
                           1,
                           73,
                           14,
                           28,
                           35,
                           82,
                           73,
                           68,
                           78,
                           70,
                           5,
                           86,
                           93,
                           79,
                           15,
                           2,
                           91,
                           121
                        },
                        2,
                        5
                     );
                     Strings.ff(var16, var63, var60.RF.length, var60.RF.length <= 1 ? "" : "s", var60.q);
                     var16.append(cvm.q(new byte[]{45, 73, 25, 82, 52}, 1, 17));
                     int var24 = 1;

                     for (CU.eo.eo var28 : var60.RF) {
                        String var29 = TimeFormatter.formatTimestampLocal(var28.q);
                        String var30 = TimeFormatter.formatTimestampDelta(var17 - var28.q);
                        String var31 = TimeFormatter.formatTimestampLocal(var28.RF);
                        String var32 = TimeFormatter.formatTimestampDelta(var17 - var28.RF);
                        var63 = cvm.q(
                           new byte[]{
                              -84,
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
                              19,
                              67,
                              5,
                              87,
                              52
                           },
                           1,
                           144
                        );
                        Strings.ff(var16, var63, var24, var30, var29, var32, var31);
                        int var33 = 1;

                        for (CU.eo.CU var37 : var28.xK) {
                           var16.append(cvm.q(new byte[]{-15, 73, 25, 82, 52}, 1, 205));
                           var63 = cvm.q(
                              new byte[]{127, 3, 25, 71, 59, 7, 20, 28, 21, 78, 75, 6, 9, 3, 20, 93, 3, 19, 9, 83, 8, 21, 86, 8, 3, 76, 38, 76, 107}, 2, 102
                           );
                           Strings.ff(var16, var63, var33, var37.q, var37.RF);
                           var16.append(cvm.q(new byte[]{127, 64, 5, 21, 76, 99}, 2, 120));
                           var33++;
                        }

                        var24++;
                     }

                     var16.append(cvm.q(new byte[]{-107, 19, 90, 25, 82, 52}, 1, 169));
                     var16.append(cvm.q(new byte[]{-80, 19, 77, 13, 11, 29, 71, 52, 54, 19, 71, 28, 25, 1, 82, 52}, 1, 140));
                     var59 = var16.toString();
                  }

                  short var62 = 400;
                  String var72 = cvm.q(new byte[]{-19, 35, 5, 68, 114, 55, 20, 4, 16, 22, 7}, 1, 175);
                  String var73 = cvm.q(new byte[]{38, 15, 3, 28, 22}, 1, 69);
                  int var74 = 0;
                  if (var59 != null) {
                     if (var8) {
                        var62 = 404;
                        var72 = cvm.q(new byte[]{13, 0, 4, 89, 52, 6, 18, 6, 16}, 2, 228);
                     } else {
                        var62 = 200;
                        var72 = cvm.q(new byte[]{12, 36}, 2, 84);
                     }

                     var73 = cvm.q(new byte[]{-25, 14, 0, 21, 93, 76, 13, 5, 31, 19}, 1, 140);
                     var74 = Strings.encodeUTF8(var59).length;
                  }

                  StringBuilder var18 = new StringBuilder();
                  Strings.ff(
                     var18,
                     cvm.q(new byte[]{11, 59, 36, 41, 93, 88, 73, 77, 16, 0, 13, 7, 9, 5, 66, 52, 51}, 2, 49),
                     Integer.valueOf(var9),
                     Integer.valueOf(var62),
                     var72
                  );
                  Strings.ff(
                     var18,
                     cvm.q(new byte[]{7, 14, 4, 28, 72, 73, 66, 27, 121, 42}, 2, 187),
                     new SimpleDateFormat(
                           cvm.q(new byte[]{6, 42, 53, 85, 82, 13, 3, 72, 57, 109, 101, 67, 80, 89, 72, 64, 25, 123, 100, 26, 95, 93, 8, 71, 95, 0, 53}, 2, 208)
                        )
                        .format(new Date())
                  );
                  Strings.ff(
                     var18,
                     cvm.q(new byte[]{27, 54, 23, 4, 19, 23, 72, 26, 106, 15, 7, 98, 99, 44, 1, 26, 6, 29, 3, 0, 9, 23, 82, 5, 86, 126, 7}, 1, 72),
                     AbstractContext.app_ver
                  );
                  Strings.ff(var18, cvm.q(new byte[]{0, 0, 30, 23, 23, 10, 19, 1, 27, 78, 18, 67, 12, 83, 60, 51}, 2, 118), var73);
                  if (var74 > 0) {
                     Strings.ff(
                        var18, cvm.q(new byte[]{30, 44, 1, 26, 17, 11, 26, 89, 121, 45, 9, 21, 95, 26, 84, 17, 29, 12, 91, 71, 28, 25, 1, 97, 7}, 1, 93)
                     );
                     Strings.ff(var18, cvm.q(new byte[]{43, 44, 1, 26, 17, 11, 26, 89, 97, 41, 11, 9, 19, 28, 82, 26, 5, 65, 105, 7}, 1, 104), var74);
                  }

                  var18.append("\r\n");
                  if (var74 > 0) {
                     var18.append(var59);
                  }

                  Object[] var78 = new Object[]{this.Uv, Formatter.escapeString(var18)};
                  byte[] var75 = Strings.encodeUTF8(var18.toString());
                  var2.write(var75, 0, var75.length);
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
                  String var77 = var15[1];
               } else if (var15[0].equals("Content-Length:")) {
                  var12 = var15[1];
               }
            }
         }
      } catch (IOException var55) {
         q.catching(var55);
      } finally {
         try {
            if (this.xK != null && !this.xK.isClosed()) {
               this.xK.close();
            }
         } catch (IOException var50) {
            q.catching(var50);
         }

         q.trace(cvm.q(new byte[]{-16, 86, 73, 26, 115, 60, 12, 8, 14, 17, 84, 67, 15, 3, 28, 22, 1}, 1, 213), this.Uv);
      }
   }

   private String q(byte[] var1, qV[] var2) {
      try {
         String var3 = Strings.decodeUTF8(var1);
         if (var3.startsWith(cvm.q(new byte[]{39, 14, 4, 24, 79}, 2, 239))) {
            var3 = var3.substring(5);
         }

         qV var4 = qV.RF(qV.q(var3));
         if (var4 == null) {
            return null;
         } else {
            if (var2 != null && var2.length >= 1) {
               var2[0] = var4;
            }

            byte[] var5 = var4.lm();
            ByteArrayInputStream var6 = new ByteArrayInputStream(var5);
            LEDataInputStream var7 = new LEDataInputStream(var6);
            int var8 = var7.readInt();
            long var9 = var7.readLong();
            long var11 = var7.readLong();
            if (var8 != 0) {
               if (var8 == 2) {
                  this.RF.q(var9, var11);
                  return "";
               } else {
                  return null;
               }
            } else {
               long var13 = 0L;
               if (var4.Dw() != Licensing.license_id || var4.xK() != Licensing.user_id) {
                  var13 = 3L;
               } else if (var4.gO().compareTo(AbstractContext.app_ver) < 0) {
                  var13 = 2L;
               } else if (!this.RF.q(var9, var11, this.xK.getInetAddress().getHostAddress(), this.xK.getPort())) {
                  var13 = 1L;
               }

               ByteArrayOutputStream var15 = new ByteArrayOutputStream();
               LEDataOutputStream var16 = new LEDataOutputStream(var15);
               var16.writeInt(1);
               var16.writeLong(var13);
               var16.close();
               byte[] var17 = qV.q(this.Dw.xK, var15.toByteArray(), null);
               return qV.q(var17);
            }
         }
      } catch (Exception var19) {
         q.catching(var19);
         return null;
      }
   }
}
