package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.JebNet;
import com.pnfsoftware.jeb.client.SystemInformation;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PY {
   private static final ILogger q = GlobalLog.getLogger(PY.class);
   private static String RF = cvm.q(
      new byte[]{
         20,
         0,
         4,
         5,
         7,
         14,
         14,
         7,
         7,
         14,
         8,
         8,
         8,
         4,
         2,
         1,
         3,
         0,
         2,
         14,
         13,
         4,
         0,
         4,
         6,
         5,
         6,
         2,
         0,
         4,
         0,
         15,
         13,
         5,
         1,
         5,
         4,
         8,
         13,
         1,
         5,
         7,
         3,
         7,
         6,
         12,
         1,
         10,
         6,
         4,
         1,
         5,
         6,
         5,
         14,
         1,
         15,
         7,
         8,
         1,
         0,
         0,
         8,
         7,
         3,
         13,
         8,
         1,
         4,
         2,
         1,
         1,
         7,
         6,
         7,
         4,
         4,
         1,
         0,
         4,
         4,
         4,
         3,
         15,
         11,
         10,
         12,
         5,
         0,
         8,
         11,
         5,
         5,
         4,
         14,
         8,
         5,
         0,
         0,
         6,
         1,
         10,
         12,
         0,
         6,
         2,
         7,
         0,
         2,
         0,
         7,
         4,
         1,
         1,
         0,
         7,
         6,
         14,
         12,
         12,
         11,
         10,
         8,
         3,
         10,
         9,
         2,
         5,
         15,
         10,
         11,
         13,
         3,
         5,
         3,
         6,
         0,
         14,
         11,
         5,
         2,
         6,
         2,
         5,
         6,
         7,
         12,
         11,
         10,
         13,
         1,
         2,
         6,
         8,
         15,
         7,
         7,
         2,
         12,
         9,
         9,
         11,
         4,
         2,
         4,
         1,
         9,
         9,
         5,
         13,
         1,
         15,
         0,
         1,
         0,
         1,
         6,
         0,
         2,
         2,
         5,
         5,
         1,
         9,
         8,
         4,
         6,
         10,
         10,
         0,
         10,
         11,
         3,
         2,
         11,
         13,
         0,
         1,
         3,
         1,
         2,
         12,
         11,
         3,
         3,
         2,
         9,
         13,
         7,
         0,
         5,
         5,
         10,
         12,
         4,
         5,
         13,
         12,
         6,
         10,
         12,
         2,
         15,
         13,
         4,
         3,
         0,
         5,
         15,
         8,
         9,
         1,
         11,
         11,
         12,
         4,
         1,
         0,
         7,
         14,
         8,
         4,
         0,
         0,
         6,
         2,
         9,
         15,
         0,
         7,
         6,
         6,
         5,
         6,
         2,
         5,
         2,
         15,
         15,
         15,
         0,
         12,
         12,
         8,
         6,
         7,
         0,
         4,
         1,
         2,
         15,
         10,
         2,
         2,
         11,
         14,
         1,
         2,
         1,
         6,
         6,
         0,
         7,
         6,
         5,
         2,
         4,
         5,
         11,
         12,
         1,
         7,
         7,
         0,
         2,
         15,
         13,
         12,
         0,
         1,
         12,
         4,
         2,
         11,
         14,
         2,
         3,
         15,
         11
      },
      1,
      37
   );
   private static String xK = cvm.q(new byte[]{117, 90, 69, 74, 69}, 2, 49);
   private INet Dw;
   private List Uv = new ArrayList();
   private String oW;

   public static List q() {
      return Arrays.asList("https://www.pnfsoftware.com/jps/checkupdate", "https://lise.pnfsoftware.com/jps/checkupdate");
   }

   public PY(INet var1) {
      this(var1, q());
   }

   public PY(INet var1, String var2) {
      this(var1, Arrays.asList(var2));
   }

   public PY(INet var1, List var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         this.Dw = var1;
         this.Uv.addAll(var2);
      } else {
         throw new RuntimeException();
      }
   }

   public List RF() {
      return this.Uv;
   }

   public String xK() {
      return this.oW;
   }

   public vn Dw() {
      return this.q(0);
   }

   public vn q(int var1) {
      try {
         Xa var2 = (Xa)JebCoreService.getExistingInstance();
         if (var2 == null) {
            return null;
         } else {
            long var3 = var2.gO();
            ByteArrayOutputStream var5 = new ByteArrayOutputStream();
            LEDataOutputStream var6 = new LEDataOutputStream(var5);
            this.q(var6, SystemInformation.username);
            this.q(var6, SystemInformation.javavendor);
            this.q(var6, SystemInformation.javaversion);
            this.q(var6, SystemInformation.osname);
            this.q(var6, SystemInformation.osarch);
            this.q(var6, SystemInformation.osversion);
            this.q(var6, SystemInformation.compname);
            var6.writeInt(var1);
            var6.close();
            int[] var7 = new int[1];
            byte[] var8 = qV.q(var3, var5.toByteArray(), var7);
            this.oW = qV.q(var8);
            long[] var9 = new long[1];
            String var10 = null;
            int var11 = 0;

            for (String var13 : this.Uv) {
               var10 = JebNet.post(this.Dw, var13, this.oW, var9);
               if (var10 != null && var10.length() != 0 && var10.indexOf(88) >= 0) {
                  var11 = var10.indexOf(88);

                  try {
                     BigInteger var14 = new BigInteger(RF);
                     BigInteger var15 = new BigInteger(xK);
                     BigInteger var16 = new BigInteger(var10.substring(0, var11));
                     var16 = var16.modPow(var15, var14);
                     var8 = var16.toByteArray();
                     if (var8.length <= 128) {
                        break;
                     }
                  } catch (NumberFormatException var31) {
                  }
               }

               Object[] var10000 = new Object[]{var13};
               var10 = null;
               var8 = null;
            }

            if (var10 == null) {
               Object[] var43 = new Object[0];
               return null;
            } else {
               if (var8.length < 128) {
                  byte[] var33 = new byte[128];
                  int var35 = 128 - var8.length;

                  for (int var37 = 0; var35 < 128; var37++) {
                     var33[var35] = var8[var37];
                     var35++;
                  }

                  var8 = var33;
               }

               byte[] var34 = Hash.calculateSHA256(Arrays.copyOf(var8, var8.length - 32));
               byte[] var36 = Arrays.copyOfRange(var8, var8.length - 32, var8.length);
               if (!Arrays.equals(var34, var36)) {
                  return null;
               } else {
                  ByteBuffer var38 = ByteBuffer.wrap(var8);
                  var38.order(ByteOrder.LITTLE_ENDIAN);
                  if (var38.getInt(4) != var7[0]) {
                     return null;
                  } else {
                     int var39 = var38.getInt(8);
                     int var41 = var38.getInt(12);
                     int var17 = var38.getInt(16);
                     if (20 + var17 > var8.length) {
                        return null;
                     } else {
                        byte[] var18 = Arrays.copyOfRange(var8, 20, 20 + var17);
                        byte[] var19 = qV.q(var10.substring(var11 + 1).trim());
                        if (var19.length != var41) {
                           return null;
                        } else {
                           cvk.q(var18, var19, 0, var19.length);
                           LEDataInputStream var20 = new LEDataInputStream(new ByteArrayInputStream(var19));
                           int var21 = var20.readInt();
                           if (var21 != 0) {
                              return null;
                           } else {
                              String var22 = this.q(var20);
                              String var23 = this.q(var20);
                              String var24 = this.q(var20);
                              String var25 = this.q(var20);
                              if (var22.isEmpty()) {
                                 return null;
                              } else {
                                 int var26 = var20.readInt();
                                 int var27 = var20.readInt();
                                 Version var28 = Version.parseFromString(var22);
                                 var28 = Version.create(var28.getMajor(), var28.getMinor(), var28.getBuildid(), var28.getTimestamp(), var26);
                                 vn.eo var29 = new vn.eo(var28, var23, Formatter.hexStringToByteArray(var24), var25, var27);
                                 return new vn(var9[0], var39, var29);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      } catch (Exception var32) {
         q.catchingSilent(var32);
         return null;
      }
   }

   private void q(LEDataOutputStream var1, String var2) throws IOException {
      byte[] var3 = Strings.encodeUTF8(var2);
      var1.writeInt(var3.length);
      var1.write(var3);
   }

   private String q(LEDataInputStream var1) throws IOException {
      int var2 = var1.readInt();
      if (var2 < 0) {
         throw new IOException();
      } else {
         byte[] var3 = new byte[var2];
         if (var2 > 0) {
            int var4 = var1.read(var3, 0, var2);
            if (var4 != var2) {
               throw new IOException();
            }
         }

         return Strings.decodeUTF8(var3);
      }
   }
}
