package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import com.pnfsoftware.jeb.util.net.Net;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class chz {
   private static final ILogger pC = GlobalLog.getLogger(chz.class);
   private INet A;
   private List kS = new ArrayList();

   public static String pC() {
      return IO.expandPath("~/.jeb/pdbcache");
   }

   public static String A() {
      return "https://msdl.microsoft.com/download/symbols";
   }

   public static chz pC(boolean var0) {
      chz var1 = new chz();
      String var2 = System.getenv("_NT_SYMBOL_PATH");
      if (var2 != null) {
         var1.pC(var2);
      }

      var2 = System.getenv("_NT_ALT_SYMBOL_PATH");
      if (var2 != null) {
         var1.pC(var2);
      }

      var2 = Strings.ff("srv*%s*%s", pC(), A());
      var1.pC(var2);
      if (var0) {
         var1.pC(new Net());
      }

      return var1;
   }

   public chz() {
      this(null, null);
   }

   public chz(Net var1, String var2) {
      this.A = var1;
      if (var2 != null) {
         this.pC(var2);
      }
   }

   public int pC(String var1) {
      int var2 = 0;

      for (String var6 : var1.split(";")) {
         String[] var7 = var6.split("\\*");
         if (var7[0].equalsIgnoreCase("srv")) {
            chz.Av var8;
            if (var7.length == 2) {
               var8 = new chz.Av(var1, null, var7[1], null);
            } else if (var7.length == 3) {
               var8 = new chz.Av(var1, var7[1], var7[2], null);
            } else {
               if (var7.length != 4) {
                  continue;
               }

               var8 = new chz.Av(var1, var7[1], var7[2], var7[3]);
            }

            if (!this.kS.contains(var8)) {
               this.kS.add(var8);
               var2++;
            }
         }
      }

      return var2;
   }

   public void pC(INet var1) {
      this.A = var1;
   }

   public File pC(chx var1) throws IOException {
      if (var1.wS()) {
         File var2 = new File(var1.kS());
         if (var2.exists()) {
            return var2;
         }
      }

      String var10 = var1.E();

      for (chz.Av var4 : this.kS) {
         if (var4.A != null) {
            File var5 = new File(var4.A, var10);
            if (var5.isFile()) {
               return var5;
            }
         }
      }

      if (this.A != null && Net.isConnectedToNetwork()) {
         for (chz.Av var12 : this.kS) {
            byte[] var13 = null;
            String var6 = var12.kS + "/" + var10;

            try {
               var13 = this.A.queryBinary(var6);
            } catch (IOException var9) {
            }

            if (var13 == null && var12.wS != null) {
               var6 = var12.kS + "/" + var10;

               try {
                  var13 = this.A.queryBinary(var6);
               } catch (IOException var8) {
               }
            }

            if (var13 != null) {
               File var7;
               if (var12.A == null) {
                  var7 = IO.createTempFile();
               } else {
                  var7 = new File(var12.A, var10);
               }

               IO.writeFile(var7, var13, true);
               return var7;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static boolean pC(chx var0, File var1) {
      try {
         boolean var3;
         try (cia var2 = new cia(new FileInput(var1))) {
            var3 = var2.pC(var0);
         }

         return var3;
      } catch (IOException var7) {
         return false;
      }
   }

   static class Av {
      private String pC;
      private String A;
      private String kS;
      private String wS;

      public Av(String var1, String var2, String var3, String var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }

      @Override
      public int hashCode() {
         byte var1 = 1;
         return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            chz.Av var2 = (chz.Av)var1;
            if (this.pC == null) {
               if (var2.pC != null) {
                  return false;
               }
            } else if (!this.pC.equals(var2.pC)) {
               return false;
            }

            return true;
         }
      }
   }
}
