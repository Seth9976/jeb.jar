package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class rQ {
   private static rQ pC = null;
   private static String A = ckx.pC(new byte[]{20, 68, 12, 5, 2}, 1, 58);
   private File kS;
   private Random wS;
   private boolean UT;

   public static rQ pC() {
      if (pC == null) {
         pC = new rQ();
      }

      return pC;
   }

   private rQ() {
      String var1 = System.getProperty(ckx.pC(new byte[]{99, 6, 22, 23, 92, 70, 7, 2, 8}, 1, 22));
      String var2 = A + Formatter.byteArrayToHexString(Hash.calculateSHA256(Strings.encodeASCII(Licensing.license_id + "_" + Licensing.user_id)), 0, 10);
      this.kS = new File(var1, var2);
      this.wS = new Random(System.currentTimeMillis());
   }

   public boolean A() {
      if (!this.kS.exists()) {
         return false;
      } else if (System.currentTimeMillis() - this.kS.lastModified() >= 5000L) {
         return false;
      } else {
         int var1;
         int var2;
         int var3;
         try (DataInputStream var4 = new DataInputStream(new FileInputStream(this.kS))) {
            var1 = var4.readInt();
            var2 = var4.readInt();
            var3 = var4.readInt();
         } catch (Exception var12) {
            return false;
         }

         if (var1 + var2 != var3) {
            return true;
         } else {
            int var13 = (var1 ^ var2) & 65535;

            InetAddress var5;
            try {
               var5 = InetAddress.getByName(null);
            } catch (UnknownHostException var10) {
               return false;
            }

            try {
               Socket var6 = new Socket(var5, var13);
               boolean var7 = true;
               var6.close();
               return var7;
            } catch (IOException var9) {
               return false;
            }
         }
      }
   }

   public boolean pC(int var1) {
      if (this.UT) {
         return false;
      } else {
         this.UT = true;
         if (var1 == 0) {
            return false;
         } else {
            int var2 = this.wS.nextInt();
            int var3 = var2 ^ (this.wS.nextInt() & -65536 | var1 & 65535);
            int var4 = var2 + var3;

            try (DataOutputStream var5 = new DataOutputStream(new FileOutputStream(this.kS))) {
               var5.writeInt(var2);
               var5.writeInt(var3);
               var5.writeInt(var4);
            } catch (Exception var10) {
               return false;
            }

            ThreadUtil.start(new zp(this));
            this.kS.deleteOnExit();
            return true;
         }
      }
   }
}
