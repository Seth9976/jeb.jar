package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeg;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class qV {
   private static final StructuredLogger xK = aeg.q(qV.class);
   private static final int Dw = 500;
   private static final String Uv = "<!DOCTYPE html>\n<html>\n<head>\n<style>\ntable, th, td {\n    border: 1px solid black;\n}\n</style>\n</head>\n<body>";
   private static final String oW = "</body>\n</html>";
   List q;
   private boolean gO;
   List RF;

   public qV(List var1) {
      this.q = new ArrayList(var1);
   }

   public void q(File var1, boolean var2, boolean var3, String var4) {
      try {
         if (var2) {
            this.RF();
         }

         if (var3) {
            this.gO = true;
            this.q();
         }

         IO.writeFile(var1, this.q(var4));
      } catch (IOException var5) {
         xK.error("problem when writing HTML file");
      }
   }

   private void q() {
      this.RF = new ArrayList();
      boolean var1 = false;

      for (EE var3 : this.q) {
         for (EE.eo var5 : var3.xK) {
            if (var5.xK > 0) {
               var1 = true;
               break;
            }
         }

         if (!var1) {
            this.RF.add(var3);
         }
      }

      this.q.removeAll(this.RF);
   }

   private void RF() {
      this.q.sort(new vn(this));
   }

   private String q(String var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("<!DOCTYPE html>\n<html>\n<head>\n<style>\ntable, th, td {\n    border: 1px solid black;\n}\n</style>\n</head>\n<body>");
      if (this.gO) {
         Strings.ff(var2, "Number of non-optimizing pipelines: %d</br>\n", this.RF.size());
      }

      Iterator var3 = this.q.iterator();

      while (true) {
         EE var4;
         boolean var5;
         do {
            if (!var3.hasNext()) {
               var2.append("</body>\n</html>");
               return var2.toString();
            }

            var4 = (EE)var3.next();
            if (var1 == null) {
               break;
            }

            var5 = false;

            for (EE.eo var7 : var4.xK) {
               if (var7.RF.equals(var1) && var7.q()) {
                  var5 = true;
                  break;
               }
            }
         } while (!var5);

         var2.append("<table>\n");
         Strings.ff(var2, "%s:%s", var4.q, var4.RF);

         for (EE.eo var10 : var4.xK) {
            Strings.ff(var2, "<col width=\"%d\" bgcolor=\"%s\">\n", 500, var10.xK > 0 ? "LightGreen" : "Silver");
         }

         var2.append("<tr>\n");

         for (EE.eo var11 : var4.xK) {
            Strings.ff(var2, "<td>%s</td>\n", this.q(var11));
         }

         var2.append("</tr>\n");
         var2.append("</table>\n");
      }
   }

   private String q(EE.eo var1) {
      StringBuilder var2 = new StringBuilder();
      String var3 = var1.RF;
      if (var3.startsWith("COpt")) {
         var3 = var3.substring(4);
      }

      var2.append(var3);
      var2.append("<br/>");
      var2.append("grp=");
      var2.append(var1.q);
      var2.append(" cnt=");
      var2.append(var1.xK);
      var2.append(" time=");
      var2.append(var1.Dw);
      return var2.toString();
   }
}
