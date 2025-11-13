package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import com.pnfsoftware.jeb.util.net.Net;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class cpf {
   private static final ILogger RF = GlobalLog.getLogger(cpf.class);
   public static final String q = "https://msdl.microsoft.com/download/symbols";
   private INet xK;
   private List Dw = new ArrayList();

   public static String q() {
      return com.pnfsoftware.jeb.util.io.IO.expandPath("~/.jeb/pdbcache");
   }

   public static String RF() {
      return "https://msdl.microsoft.com/download/symbols";
   }

   public static cpf q(boolean var0) {
      cpf var1 = new cpf();
      String var2 = System.getenv("_NT_SYMBOL_PATH");
      if (var2 != null) {
         var1.q(var2);
      }

      var2 = System.getenv("_NT_ALT_SYMBOL_PATH");
      if (var2 != null) {
         var1.q(var2);
      }

      var2 = Strings.ff("srv*%s*%s", q(), RF());
      var1.q(var2);
      if (var0) {
         var1.q(new Net());
      }

      return var1;
   }

   public cpf() {
      this(null, null);
   }

   public cpf(Net var1) {
      this(var1, null);
   }

   public cpf(Net var1, String var2) {
      this.xK = var1;
      if (var2 != null) {
         this.q(var2);
      }
   }

   public int q(String var1) {
      int var2 = 0;

      for (String var6 : var1.split(";")) {
         String[] var7 = var6.split("\\*");
         if (var7[0].equalsIgnoreCase("srv")) {
            cpf.eo var8;
            if (var7.length == 2) {
               var8 = new cpf.eo(var1, null, var7[1], null);
            } else if (var7.length == 3) {
               var8 = new cpf.eo(var1, var7[1], var7[2], null);
            } else {
               if (var7.length != 4) {
                  continue;
               }

               var8 = new cpf.eo(var1, var7[1], var7[2], var7[3]);
            }

            if (!this.Dw.contains(var8)) {
               this.Dw.add(var8);
               var2++;
            }
         }
      }

      return var2;
   }

   public List xK() {
      return Collections.unmodifiableList(this.Dw);
   }

   public void q(INet var1) {
      this.xK = var1;
   }

   public INet Dw() {
      return this.xK;
   }

   public File q(cpd var1) throws IOException {
      if (var1.Dw()) {
         File var2 = new File(var1.xK());
         if (var2.exists()) {
            return var2;
         }
      }

      String var10 = var1.oW();

      for (cpf.eo var4 : this.Dw) {
         if (var4.RF != null) {
            File var5 = new File(var4.RF, var10);
            if (var5.isFile()) {
               return var5;
            }
         }
      }

      if (this.xK != null && Net.isConnectedToNetwork()) {
         for (cpf.eo var12 : this.Dw) {
            byte[] var13 = null;
            String var6 = var12.xK + "/" + var10;

            try {
               var13 = this.xK.queryBinary(var6);
            } catch (IOException var9) {
            }

            if (var13 == null && var12.Dw != null) {
               var6 = var12.xK + "/" + var10;

               try {
                  var13 = this.xK.queryBinary(var6);
               } catch (IOException var8) {
               }
            }

            if (var13 != null) {
               File var7;
               if (var12.RF == null) {
                  var7 = com.pnfsoftware.jeb.util.io.IO.createTempFile();
               } else {
                  var7 = new File(var12.RF, var10);
               }

               com.pnfsoftware.jeb.util.io.IO.writeFile(var7, var13, true);
               return var7;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static boolean q(cpd var0, File var1) {
      try {
         boolean var3;
         try (cpg var2 = new cpg(new FileInput(var1))) {
            var3 = var2.q(var0);
         }

         return var3;
      } catch (IOException var7) {
         return false;
      }
   }

   static class eo {
      private String q;
      private String RF;
      private String xK;
      private String Dw;

      public eo(String var1, String var2, String var3, String var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }

      @Override
      public int hashCode() {
         byte var1 = 1;
         return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
            cpf.eo var2 = (cpf.eo)var1;
            if (this.q == null) {
               if (var2.q != null) {
                  return false;
               }
            } else if (!this.q.equals(var2.q)) {
               return false;
            }

            return true;
         }
      }

      public String q() {
         return this.q;
      }

      public String RF() {
         return this.RF;
      }

      public String xK() {
         return this.xK;
      }

      public String Dw() {
         return this.Dw;
      }
   }
}
