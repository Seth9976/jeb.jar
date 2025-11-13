package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class IB implements Mt {
   private static final String Uv = "documentation/ddi0602/2024-03/";
   private static final String oW = "documentation/ddi0597/2024-03/";
   private final String gO;
   private Map nf;
   private static String[] gP = new String[]{"EQ", "NE", "CS", "CC", "MI", "PL", "VS", "VC", "HI", "LS", "GE", "LT", "GT", "LE"};
   static final IB q = new PJ("documentation/ddi0597/2024-03/Base-Instructions");
   static final IB RF = new Sc("documentation/ddi0597/2024-03/SIMD-FP-Instructions");
   static final IB xK = new IB("documentation/ddi0602/2024-03/Base-Instructions");
   static final IB Dw = new IB("documentation/ddi0602/2024-03/SIMD-FP-Instructions");

   public IB(String var1) {
      this.gO = var1;
   }

   public void q(mC var1) {
      this.nf = new TreeMap();
      Document var2 = var1.nf(this.gO);

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
                  if (!this.nf.containsKey(var13)) {
                     this.nf.put(var13, var7);
                  }
               }
            }
         }
      }
   }

   @Override
   public String q(String var1) {
      return var1;
   }

   @Override
   public List RF(String var1) {
      ArrayList var2 = new ArrayList();
      String var3 = (String)this.nf.get(var1);
      if (var3 != null) {
         var2.add(var3);
      }

      return var2;
   }

   @Override
   public boolean xK(String var1) {
      return true;
   }
}
