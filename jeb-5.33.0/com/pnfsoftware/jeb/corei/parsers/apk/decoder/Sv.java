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

public class Sv implements RC {
   private static final ILogger sY = GlobalLog.getLogger(Sv.class);
   static boolean pC = false;
   List A = new ArrayList();
   Ws kS;
   Map wS = new HashMap();
   boolean UT = false;
   int E = 3;

   public static IGenericZipBrowser pC(File var0) throws IOException {
      return new ZipBrowserPNF(var0, 0, true);
   }

   public Sv(File var1) throws IOException {
      this.kS = this.kS(var1);
   }

   public boolean pC() {
      return this.UT;
   }

   public void pC(int var1) {
      this.E = var1;
   }

   public void A(File var1) {
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
                  this.pC(var14, var11);
               }
            }
         }
      }
   }

   public boolean pC(int var1, File var2) {
      return this.pC(var1, var2, false);
   }

   public boolean pC(int var1, File var2, boolean var3) {
      File var4 = (File)this.wS.get(var1);
      if (!var3 && var4 != null) {
         sY.debug("Android package id %d: will not register apk %s, as it is already handled by %s", var1, var2.getName(), var4.getName());
         return false;
      } else {
         String var5 = var4 == null ? "" : Strings.ff(" (replacing %s)", var4.getName());
         this.wS.put(var1, var2);
         sY.debug("Android package id %d: Registered file %s%s", var1, var2.getName(), var5);
         return true;
      }
   }

   public Ws kS(File var1) throws IOException {
      Ws var2 = new Ws(var1, this);
      this.A.add(var2);
      return var2;
   }

   public KD A() {
      return this.kS.UT();
   }

   public Collection kS() {
      return this.kS.pC(true, this.E);
   }

   public String pC(boolean var1) throws K {
      return this.kS.pC(var1);
   }

   boolean wS(File var1) {
      if (var1 != null) {
         for (Ws var3 : this.A) {
            if (var1.equals(var3.pC())) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public String pC(int var1, int var2) {
      if (var1 == 0) {
         var1 = this.kS.kS();
      }

      for (Ws var4 : this.A) {
         if (var4.pC(var1)) {
            String var5 = var4.pC(var1, var2);
            if (var5 != null) {
               return var5;
            }
         }
      }

      File var7 = (File)this.wS.get(var1);
      if (var7 != null && !this.wS(var7)) {
         Ws var8;
         try {
            var8 = this.kS(var7);
         } catch (IOException var6) {
            return null;
         }

         if (var8.pC(var1)) {
            return var8.pC(var1, var2);
         }
      }

      return null;
   }

   @Override
   public boolean pC(int var1, int var2, String var3) {
      if (var1 == 0) {
         var1 = this.kS.kS();
      }

      for (Ws var5 : this.A) {
         if (var5.pC(var1) && var5.pC(var1, var2, var3)) {
            return true;
         }
      }

      File var7 = (File)this.wS.get(var1);
      if (var7 != null && !this.wS(var7)) {
         Ws var8;
         try {
            var8 = this.kS(var7);
         } catch (IOException var6) {
            return false;
         }

         if (var8.pC(var1)) {
            return var8.pC(var1, var2, var3);
         }
      }

      return false;
   }

   @Override
   public p A(int var1) {
      int var2 = var1 >>> 24;

      for (Ws var4 : this.A) {
         if (var4.pC(var2)) {
            p var5 = var4.A(var1);
            if (var5 != null) {
               return var5;
            }
         }
      }

      File var7 = (File)this.wS.get(var2);
      if (var7 != null && !this.wS(var7)) {
         Ws var8;
         try {
            var8 = this.kS(var7);
         } catch (IOException var6) {
            return null;
         }

         if (var8.pC(var2)) {
            return var8.A(var1);
         }
      }

      return null;
   }

   @Override
   public String kS(int var1) {
      p var2 = this.A(var1);
      return var2 == null ? null : var2.A;
   }
}
