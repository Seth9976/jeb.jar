package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignaturePackage;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class bK {
   private static final ILogger Uv = GlobalLog.getLogger(bK.class);
   public static final String q = "siggen_stat.log";
   public static final String RF = "siggen_report.html";
   private Map oW = new HashMap();
   private Map gO = new HashMap();
   private Map nf = new HashMap();
   private Map gP = new HashMap();
   private Map za = new HashMap();
   private Map lm = new HashMap();
   private Map zz = new HashMap();
   private int JY = 0;
   private String HF;
   public static final String xK = "<!DOCTYPE html>\n<html>\n<head>\n<title>SigGen report</title>\n</head>\n<body>\n<style>\nhtml * {\nfont-size: 1.1em;\nfont-family: Courier New !important;\nwhite-space: nowrap;\n}\nh1 {\nfont-size: 2em;  \n}\n.details {\npadding-left: 20px;  \n}\n</style>\n<h1>SigGen Report</h1>";
   public static final String Dw = "</body>\n</html>";

   public void q(String var1, String var2, vb.eo var3) {
      String var4 = this.q(var1, var2);
      vb.eo var5 = (vb.eo)this.oW.get(var4);
      if (var5 != null) {
         if (var5 == var3) {
            this.q(var1, var2, "another routine with the same name/file name exist (same category)");
         } else {
            this.q(var1, var2, "another routine with the same name/file name exist exists (!! different category)");
         }
      } else {
         this.oW.put(var4, var3);
      }
   }

   public void q(String var1, String var2, INativeSignature var3) {
      String var4 = this.q(var1, var2);
      INativeSignature var5 = (INativeSignature)this.gO.get(var4);
      if (var5 == null) {
         this.gO.put(var4, var3);
      }
   }

   public void q(String var1, String var2, Set var3) {
      String var4 = this.q(var1, var2);
      Set var5 = (Set)this.nf.get(var4);
      if (var5 == null) {
         this.nf.put(var4, var3);
      }
   }

   public void RF(String var1, String var2, INativeSignature var3) {
      String var4 = this.q(var1, var2);
      this.gP.put(var4, var3);
   }

   public void q(String var1, String var2, String var3, String var4) {
      String var5 = this.q(var1, var2);
      String var6 = this.q(var3, var4);
      String var7 = (String)this.zz.get(var5);
      if (var7 == null) {
         this.zz.put(var5, var6);
      }
   }

   private String q(String var1, String var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append(var2);
      var3.append("->");
      var3.append(var1);
      return var3.toString();
   }

   public void q(String var1) {
      Integer var2 = (Integer)this.lm.get(var1);
      if (var2 == null) {
         var2 = 0;
      }

      this.lm.put(var1, var2 + 1);
   }

   public void q(String var1, String var2, String var3) {
      String var4 = this.q(var1, var2);
      Object var5 = (Set)this.za.get(var4);
      if (var5 == null) {
         var5 = new HashSet();
         this.za.put(var4, var5);
      }

      var5.add(var3);
   }

   public void q() {
      this.JY++;
   }

   private void RF() {
      Assert.a(this.HF == null);
      this.HF = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void q(INativeSignaturePackage var1, String var2) {
      try (
         BufferedWriter var3 = Files.newBufferedWriter(
            new File(var2, "siggen_stat.log").toPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND
         );
         PrintWriter var4 = new PrintWriter(var3);
      ) {
         Object[] var10000 = new Object[]{var2};
         int var5 = 0;
         int var6 = 0;
         int var7 = 0;
         int var8 = 0;
         int var9 = this.lm.size();
         int var10 = 0;

         for (Entry var12 : this.oW.entrySet()) {
            switch ((vb.eo)var12.getValue()) {
               case q:
                  var10++;
                  break;
               case RF:
                  var5++;
                  break;
               case xK:
                  var6++;
                  break;
               case Dw:
                  var7++;
                  break;
               case Uv:
                  var8++;
                  break;
               default:
                  throw new RuntimeException("unknown routine category");
            }
         }

         if (this.HF == null) {
            this.RF();
         }

         var4.write(Strings.ff("%n> Package created on %s%n", this.HF));
         var4.write(Strings.ff("> metadata: %s%n", var1.getMetadata()));
         var4.write(Strings.ff("> # sigs created: %d%n", var1.count()));
         var4.write(Strings.ff("> # very small routines: %d%n", var5));
         var4.write(Strings.ff("> # small routines: %d%n", var6));
         var4.write(Strings.ff("> # medium routines: %d%n", var7));
         var4.write(Strings.ff("> # large routines: %d%n", var8));
         var4.write(Strings.ff("> # unnamed routines: %d%n", var9));
         var4.write(Strings.ff("> # blacklisted routines: %d%n", var10));
         var4.write(Strings.ff("> # duplicated routines: %d%n", this.JY));
         var10000 = new Object[0];
      } catch (IOException var17) {
         Uv.error("> error during log file writing");
      }
   }

   public void RF(String var1) {
      File var2 = new File(var1, "siggen_report.html");
      if (var2.exists()) {
         try {
            IO.copyFile(var2, new File(var1, "backup_siggen_report.html"), true);
         } catch (IOException var14) {
            Uv.error("Problem while backuping report");
         }

         var2.delete();
      }

      try (
         BufferedWriter var3 = Files.newBufferedWriter(
            new File(var1, "siggen_report.html").toPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE
         );
         PrintWriter var4 = new PrintWriter(var3);
      ) {
         Object[] var10000 = new Object[]{var1};
         var4.write(
            "<!DOCTYPE html>\n<html>\n<head>\n<title>SigGen report</title>\n</head>\n<body>\n<style>\nhtml * {\nfont-size: 1.1em;\nfont-family: Courier New !important;\nwhite-space: nowrap;\n}\nh1 {\nfont-size: 2em;  \n}\n.details {\npadding-left: 20px;  \n}\n</style>\n<h1>SigGen Report</h1>"
         );
         if (this.HF == null) {
            this.RF();
         }

         var4.write(Strings.ff("Generated on %s<br><br>", this.HF));

         for (Entry var6 : this.oW.entrySet()) {
            String var7 = (String)var6.getKey();
            var4.write("<details>");
            var4.write(Strings.ff("<summary>%s</summary>", Formatter.htmlEscape(var7)));
            var4.write("<p class='details'>");
            var4.write(Strings.ff("category:%s<br>", this.q((vb.eo)var6.getValue())));
            if (this.gO.get(var7) == null) {
               var4.write("unsigned<br>");
               Set var8 = (Set)this.za.get(var7);
               if (var8 != null) {
                  var4.write("hints:");
                  var4.write(Formatter.htmlEscape(String.join(",", var8)));
               }

               var4.write("</p>");
               var4.write("</details>");
            } else {
               var4.write(Strings.ff("original sig: %s<br>", Formatter.htmlEscape(((INativeSignature)this.gO.get(var7)).toString())));
               Set var18 = (Set)this.nf.get(var7);
               if (var18 != null) {
                  var4.write(Strings.ff("tie breaker features: %s<br>", Formatter.htmlEscape(var18.toString())));
               }

               INativeSignature var9 = (INativeSignature)this.gP.get(var7);
               if (var9 != null) {
                  var4.write(Strings.ff("final sig: %s<br>", Formatter.htmlEscape(var9.toString())));
               }

               String var10 = (String)this.zz.get(var7);
               if (var10 != null) {
                  var4.write(Strings.ff("merged into %s", Formatter.htmlEscape(var10)));
               }

               Set var11 = (Set)this.za.get(var7);
               if (var11 != null) {
                  var4.write("hints:");
                  var4.write(Formatter.htmlEscape(String.join(",", var11)));
                  var4.write("<br>");
               }

               var4.write("</p>");
               var4.write("</details>");
            }
         }

         var4.write("</body>\n</html>");
         var10000 = new Object[0];
      } catch (IOException var17) {
         Uv.error("> error during HTML report file writing");
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private String q(vb.eo var1) {
      switch (var1) {
         case q:
            return "blacklisted";
         case RF:
            return "very small";
         case xK:
            return "small";
         case Dw:
            return "medium";
         case Uv:
            return "large";
         default:
            throw new RuntimeException("unknown routine category");
      }
   }
}
