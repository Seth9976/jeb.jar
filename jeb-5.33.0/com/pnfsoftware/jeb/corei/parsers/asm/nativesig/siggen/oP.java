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

public class oP {
   private static final ILogger pC = GlobalLog.getLogger(oP.class);
   private Map A = new HashMap();
   private Map kS = new HashMap();
   private Map wS = new HashMap();
   private Map UT = new HashMap();
   private Map E = new HashMap();
   private Map sY = new HashMap();
   private Map ys = new HashMap();
   private int ld = 0;
   private String gp;

   public void pC(String var1, String var2, sy.Av var3) {
      String var4 = this.pC(var1, var2);
      sy.Av var5 = (sy.Av)this.A.get(var4);
      if (var5 != null) {
         if (var5 == var3) {
            this.pC(var1, var2, "another routine with the same name/file name exist (same category)");
         } else {
            this.pC(var1, var2, "another routine with the same name/file name exist exists (!! different category)");
         }
      } else {
         this.A.put(var4, var3);
      }
   }

   public void pC(String var1, String var2, INativeSignature var3) {
      String var4 = this.pC(var1, var2);
      INativeSignature var5 = (INativeSignature)this.kS.get(var4);
      if (var5 == null) {
         this.kS.put(var4, var3);
      }
   }

   public void pC(String var1, String var2, Set var3) {
      String var4 = this.pC(var1, var2);
      Set var5 = (Set)this.wS.get(var4);
      if (var5 == null) {
         this.wS.put(var4, var3);
      }
   }

   public void A(String var1, String var2, INativeSignature var3) {
      String var4 = this.pC(var1, var2);
      this.UT.put(var4, var3);
   }

   public void pC(String var1, String var2, String var3, String var4) {
      String var5 = this.pC(var1, var2);
      String var6 = this.pC(var3, var4);
      String var7 = (String)this.ys.get(var5);
      if (var7 == null) {
         this.ys.put(var5, var6);
      }
   }

   private String pC(String var1, String var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append(var2);
      var3.append("->");
      var3.append(var1);
      return var3.toString();
   }

   public void pC(String var1) {
      Integer var2 = (Integer)this.sY.get(var1);
      if (var2 == null) {
         var2 = 0;
      }

      this.sY.put(var1, var2 + 1);
   }

   public void pC(String var1, String var2, String var3) {
      String var4 = this.pC(var1, var2);
      Object var5 = (Set)this.E.get(var4);
      if (var5 == null) {
         var5 = new HashSet();
         this.E.put(var4, var5);
      }

      var5.add(var3);
   }

   public void pC() {
      this.ld++;
   }

   private void A() {
      Assert.a(this.gp == null);
      this.gp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
   }

   public void pC(INativeSignaturePackage var1, String var2) {
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
         int var9 = this.sY.size();
         int var10 = 0;

         for (Entry var12 : this.A.entrySet()) {
            switch ((sy.Av)var12.getValue()) {
               case pC:
                  var10++;
                  break;
               case A:
                  var5++;
                  break;
               case kS:
                  var6++;
                  break;
               case wS:
                  var7++;
                  break;
               case UT:
                  var8++;
                  break;
               default:
                  throw new RuntimeException("unknown routine category");
            }
         }

         if (this.gp == null) {
            this.A();
         }

         var4.write(Strings.ff("%n> Package created on %s%n", this.gp));
         var4.write(Strings.ff("> metadata: %s%n", var1.getMetadata()));
         var4.write(Strings.ff("> # sigs created: %d%n", var1.count()));
         var4.write(Strings.ff("> # very small routines: %d%n", var5));
         var4.write(Strings.ff("> # small routines: %d%n", var6));
         var4.write(Strings.ff("> # medium routines: %d%n", var7));
         var4.write(Strings.ff("> # large routines: %d%n", var8));
         var4.write(Strings.ff("> # unnamed routines: %d%n", var9));
         var4.write(Strings.ff("> # blacklisted routines: %d%n", var10));
         var4.write(Strings.ff("> # duplicated routines: %d%n", this.ld));
         var10000 = new Object[0];
      } catch (IOException var17) {
         pC.error("> error during log file writing");
      }
   }

   public void A(String var1) {
      File var2 = new File(var1, "siggen_report.html");
      if (var2.exists()) {
         try {
            IO.copyFile(var2, new File(var1, "backup_siggen_report.html"), true);
         } catch (IOException var14) {
            pC.error("Problem while backuping report");
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
         if (this.gp == null) {
            this.A();
         }

         var4.write(Strings.ff("Generated on %s<br><br>", this.gp));

         for (Entry var6 : this.A.entrySet()) {
            String var7 = (String)var6.getKey();
            var4.write("<details>");
            var4.write(Strings.ff("<summary>%s</summary>", Formatter.htmlEscape(var7)));
            var4.write("<p class='details'>");
            var4.write(Strings.ff("category:%s<br>", this.pC((sy.Av)var6.getValue())));
            if (this.kS.get(var7) == null) {
               var4.write("unsigned<br>");
               Set var8 = (Set)this.E.get(var7);
               if (var8 != null) {
                  var4.write("hints:");
                  var4.write(Formatter.htmlEscape(String.join(",", var8)));
               }

               var4.write("</p>");
               var4.write("</details>");
            } else {
               var4.write(Strings.ff("original sig: %s<br>", Formatter.htmlEscape(((INativeSignature)this.kS.get(var7)).toString())));
               Set var18 = (Set)this.wS.get(var7);
               if (var18 != null) {
                  var4.write(Strings.ff("tie breaker features: %s<br>", Formatter.htmlEscape(var18.toString())));
               }

               INativeSignature var9 = (INativeSignature)this.UT.get(var7);
               if (var9 != null) {
                  var4.write(Strings.ff("final sig: %s<br>", Formatter.htmlEscape(var9.toString())));
               }

               String var10 = (String)this.ys.get(var7);
               if (var10 != null) {
                  var4.write(Strings.ff("merged into %s", Formatter.htmlEscape(var10)));
               }

               Set var11 = (Set)this.E.get(var7);
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
         pC.error("> error during HTML report file writing");
      }
   }

   private String pC(sy.Av var1) {
      switch (var1) {
         case pC:
            return "blacklisted";
         case A:
            return "very small";
         case kS:
            return "small";
         case wS:
            return "medium";
         case UT:
            return "large";
         default:
            throw new RuntimeException("unknown routine category");
      }
   }
}
