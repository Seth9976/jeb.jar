package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.encoding.zip.IGenericZipBrowser;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowserPNF;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CU implements PY {
   private static final ILogger gO = GlobalLog.getLogger(CU.class);
   static boolean q = false;
   List RF = new ArrayList();
   ej xK;
   Map Dw = new HashMap();
   boolean Uv = false;
   int oW = 3;

   public static void q(String var0, Object... var1) {
   }

   public static IGenericZipBrowser q(File var0) throws IOException {
      return new ZipBrowserPNF(var0, 0, true);
   }

   public CU(File var1) throws IOException {
      this.xK = this.xK(var1);
   }

   public void q(boolean var1) {
      this.Uv = var1;
   }

   public boolean q() {
      return this.Uv;
   }

   public void q(int var1) {
      this.oW = var1;
   }

   public int RF() {
      return this.oW;
   }

   public void RF(File var1) {
      String[] var2 = new String[]{".apk", ".jar", ".zip"};
      File[] var3 = var1.listFiles();

      for (String var7 : var2) {
         for (File var11 : var3) {
            String var12 = var11.getName();
            if (var12.endsWith(var7)) {
               var12 = var12.substring(0, var12.length() - var7.length());
               int var13 = var12.indexOf(45);
               if (var13 >= 0) {
                  var12 = var12.substring(0, var13);
               }

               int var14 = Conversion.stringToInt(var12);
               if (var14 != 0) {
                  this.q(var14, var11);
               }
            }
         }
      }
   }

   public boolean q(int var1, File var2) {
      return this.q(var1, var2, false);
   }

   public boolean q(int var1, File var2, boolean var3) {
      File var4 = (File)this.Dw.get(var1);
      if (!var3 && var4 != null) {
         gO.debug("Android package id %d: will not register apk %s, as it is already handled by %s", var1, var2.getName(), var4.getName());
         return false;
      } else {
         String var5 = var4 == null ? "" : Strings.ff(" (replacing %s)", var4.getName());
         this.Dw.put(var1, var2);
         gO.debug("Android package id %d: Registered file %s%s", var1, var2.getName(), var5);
         return true;
      }
   }

   public ej xK(File var1) throws IOException {
      ej var2 = new ej(var1, this);
      this.RF.add(var2);
      return var2;
   }

   public qV xK() {
      return this.xK.Uv();
   }

   public qV q(String var1) {
      return this.xK.q(var1, true);
   }

   public qV Dw() {
      return this.xK.Dw();
   }

   public Collection Uv() {
      return this.xK.q(true, this.oW);
   }

   public String RF(boolean var1) throws nI {
      return this.xK.q(var1);
   }

   List oW() {
      ArrayList var1 = new ArrayList();

      for (ej var3 : this.RF) {
         var1.add(var3.q());
      }

      return var1;
   }

   boolean Dw(File var1) {
      if (var1 != null) {
         for (ej var3 : this.RF) {
            if (var1.equals(var3.q())) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public String q(int var1, int var2) {
      if (var1 == 0) {
         var1 = this.xK.xK();
      }

      for (ej var4 : this.RF) {
         if (var4.q(var1)) {
            String var5 = var4.q(var1, var2);
            if (var5 != null) {
               return var5;
            }
         }
      }

      File var7 = (File)this.Dw.get(var1);
      if (var7 != null && !this.Dw(var7)) {
         ej var8;
         try {
            var8 = this.xK(var7);
         } catch (IOException var6) {
            return null;
         }

         if (var8.q(var1)) {
            return var8.q(var1, var2);
         }
      }

      return null;
   }

   @Override
   public boolean q(int var1, int var2, String var3) {
      if (var1 == 0) {
         var1 = this.xK.xK();
      }

      for (ej var5 : this.RF) {
         if (var5.q(var1) && var5.q(var1, var2, var3)) {
            return true;
         }
      }

      File var7 = (File)this.Dw.get(var1);
      if (var7 != null && !this.Dw(var7)) {
         ej var8;
         try {
            var8 = this.xK(var7);
         } catch (IOException var6) {
            return false;
         }

         if (var8.q(var1)) {
            return var8.q(var1, var2, var3);
         }
      }

      return false;
   }

   @Override
   public tl RF(int var1) {
      int var2 = var1 >>> 24;

      for (ej var4 : this.RF) {
         if (var4.q(var2)) {
            tl var5 = var4.RF(var1);
            if (var5 != null) {
               return var5;
            }
         }
      }

      File var7 = (File)this.Dw.get(var2);
      if (var7 != null && !this.Dw(var7)) {
         ej var8;
         try {
            var8 = this.xK(var7);
         } catch (IOException var6) {
            return null;
         }

         if (var8.q(var2)) {
            return var8.RF(var1);
         }
      }

      return null;
   }

   @Override
   public String xK(int var1) {
      tl var2 = this.RF(var1);
      return var2 == null ? null : var2.RF;
   }

   @Override
   public String q(int var1, String var2) {
      int var3 = var1 >>> 24;

      for (ej var5 : this.RF) {
         if (var5.q(var3)) {
            String var6 = var5.q(var1, var2);
            if (var6 != null) {
               return var6;
            }
         }
      }

      File var8 = (File)this.Dw.get(var3);
      if (var8 != null && !this.Dw(var8)) {
         ej var9;
         try {
            var9 = this.xK(var8);
         } catch (IOException var7) {
            return null;
         }

         if (var9.q(var3)) {
            return var9.q(var1, var2);
         }
      }

      return null;
   }

   public byte[] gO() throws IOException {
      return this.q(null, false);
   }

   public byte[] q(Integer var1, boolean var2) throws IOException {
      return this.xK.q(var1, var2);
   }
}
