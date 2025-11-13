package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class Mu implements tR {
   private final String UT;
   private Map E;
   private static String[] sY = new String[]{"EQ", "NE", "CS", "CC", "MI", "PL", "VS", "VC", "HI", "LS", "GE", "LT", "GT", "LE"};
   static final Mu pC = new eg("documentation/ddi0597/2024-03/Base-Instructions");
   static final Mu A = new QR("documentation/ddi0597/2024-03/SIMD-FP-Instructions");
   static final Mu kS = new Mu("documentation/ddi0602/2024-03/Base-Instructions");
   static final Mu wS = new Mu("documentation/ddi0602/2024-03/SIMD-FP-Instructions");

   public Mu(String var1) {
      this.UT = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.Ws var1) {
      this.E = new TreeMap();
      Document var2 = var1.ys(this.UT);

      for (Element var5 : var2.getElementsByTag("a")) {
         String var6 = var5.text();
         if (var6 != null && !var6.isBlank()) {
            String var7 = var5.attr("href");
            if (var7 != null) {
               int var8 = var6.indexOf("(");
               if (var8 > 0) {
                  var6 = var6.substring(0, var8).trim();
               }

               String[] var9 = var6.split(",");

               for (String var13 : var9) {
                  var13 = var13.trim();
                  if (!this.E.containsKey(var13)) {
                     this.E.put(var13, var7);
                  }
               }
            }
         }
      }
   }

   @Override
   public String pC(String var1) {
      return var1;
   }

   @Override
   public List A(String var1) {
      ArrayList var2 = new ArrayList();
      String var3 = (String)this.E.get(var1);
      if (var3 != null) {
         var2.add(var3);
      }

      return var2;
   }

   @Override
   public boolean kS(String var1) {
      return true;
   }
}
