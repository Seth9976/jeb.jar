package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aco;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DH {
   private static final StructuredLogger kS = aco.pC(DH.class);
   List pC;
   private boolean wS;
   List A;

   public DH(List var1) {
      this.pC = new ArrayList(var1);
   }

   public void pC(File var1, boolean var2, boolean var3, String var4) {
      try {
         if (var2) {
            this.A();
         }

         if (var3) {
            this.wS = true;
            this.pC();
         }

         IO.writeFile(var1, this.pC(var4));
      } catch (IOException var5) {
         kS.error("problem when writing HTML file");
      }
   }

   private void pC() {
      this.A = new ArrayList();
      boolean var1 = false;

      for (cq var3 : this.pC) {
         for (cq.Av var5 : var3.kS) {
            if (var5.kS > 0) {
               var1 = true;
               break;
            }
         }

         if (!var1) {
            this.A.add(var3);
         }
      }

      this.pC.removeAll(this.A);
   }

   private void A() {
      this.pC.sort(new rQ(this));
   }

   private String pC(String var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("<!DOCTYPE html>\n<html>\n<head>\n<style>\ntable, th, td {\n    border: 1px solid black;\n}\n</style>\n</head>\n<body>");
      if (this.wS) {
         Strings.ff(var2, "Number of non-optimizing pipelines: %d</br>\n", this.A.size());
      }

      Iterator var3 = this.pC.iterator();

      while (true) {
         cq var4;
         boolean var5;
         do {
            if (!var3.hasNext()) {
               var2.append("</body>\n</html>");
               return var2.toString();
            }

            var4 = (cq)var3.next();
            if (var1 == null) {
               break;
            }

            var5 = false;

            for (cq.Av var7 : var4.kS) {
               if (var7.A.equals(var1) && var7.pC()) {
                  var5 = true;
                  break;
               }
            }
         } while (!var5);

         var2.append("<table>\n");
         Strings.ff(var2, "%s:%s", var4.pC, var4.A);

         for (cq.Av var10 : var4.kS) {
            Strings.ff(var2, "<col width=\"%d\" bgcolor=\"%s\">\n", 500, var10.kS > 0 ? "LightGreen" : "Silver");
         }

         var2.append("<tr>\n");

         for (cq.Av var11 : var4.kS) {
            Strings.ff(var2, "<td>%s</td>\n", this.pC(var11));
         }

         var2.append("</tr>\n");
         var2.append("</table>\n");
      }
   }

   private String pC(cq.Av var1) {
      StringBuilder var2 = new StringBuilder();
      String var3 = var1.A;
      if (var3.startsWith("COpt")) {
         var3 = var3.substring(4);
      }

      var2.append(var3);
      var2.append("<br/>");
      var2.append("grp=");
      var2.append(var1.pC);
      var2.append(" cnt=");
      var2.append(var1.kS);
      var2.append(" time=");
      var2.append(var1.wS);
      return var2.toString();
   }
}
