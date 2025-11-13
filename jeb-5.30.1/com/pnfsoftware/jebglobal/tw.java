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

public class tw {
   private static tw q = null;
   private static String RF = cvm.q(new byte[]{126, 68, 12, 5, 2}, 1, 80);
   private File xK;
   private Random Dw;
   private boolean Uv;

   public static tw q() {
      if (q == null) {
         q = new tw();
      }

      return q;
   }

   private tw() {
      String var1 = System.getProperty(cvm.q(new byte[]{54, 28, 21, 11, 92, 1, 8, 5, 17}, 2, 178));
      String var2 = RF + Formatter.byteArrayToHexString(Hash.calculateSHA256(Strings.encodeASCII(Licensing.license_id + "_" + Licensing.user_id)), 0, 10);
      this.xK = new File(var1, var2);
      this.Dw = new Random(System.currentTimeMillis());
   }

   public boolean RF() {
      if (!this.xK.exists()) {
         return false;
      } else if (System.currentTimeMillis() - this.xK.lastModified() >= 5000L) {
         return false;
      } else {
         int var1;
         int var2;
         int var3;
         try (DataInputStream var4 = new DataInputStream(new FileInputStream(this.xK))) {
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

   public boolean q(int var1) {
      if (this.Uv) {
         return false;
      } else {
         this.Uv = true;
         if (var1 == 0) {
            return false;
         } else {
            int var2 = this.Dw.nextInt();
            int var3 = var2 ^ (this.Dw.nextInt() & -65536 | var1 & 65535);
            int var4 = var2 + var3;

            try (DataOutputStream var5 = new DataOutputStream(new FileOutputStream(this.xK))) {
               var5.writeInt(var2);
               var5.writeInt(var3);
               var5.writeInt(var4);
            } catch (Exception var10) {
               return false;
            }

            ThreadUtil.start(new EE(this));
            this.xK.deleteOnExit();
            return true;
         }
      }
   }
}
