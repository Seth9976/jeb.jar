package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import com.pnfsoftware.jebglobal.at;
import java.io.File;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class Av extends at {
   private static final ILogger A = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.dex.Av.class);
   private static final String[] kS = new String[]{
      "devsite-notification-promo",
      "devsite-dialog",
      "devsite-notification-eu-cookie",
      "devsite-notification-eu-cookie.devsite-notification-promo",
      "devsite-rating-container",
      "devsite-breadcrumb-nav",
      "devsite-nav"
   };

   public Av(IUnit var1, String var2, INet var3, String var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   protected String pC(String var1) {
      String var2 = super.pC(var1);
      if (var2 != null && this.A() != null) {
         var2 = this.A(this.A(), var2);
      }

      return var2;
   }

   protected String A() {
      return null;
   }

   String A(String var1, String var2) {
      int var4 = var2.lastIndexOf("reference");
      int var5 = var2.length() - (var4 + "reference".length());
      if (var4 != -1 && var5 < 2) {
         String var3;
         if (var2.startsWith("http")) {
            var3 = Strings.replaceLast(var2, "reference", var1);
         } else {
            try {
               var3 = new File(Strings.replaceLast(var2, "reference", var1)).getCanonicalPath();
            } catch (IOException var7) {
               A.catchingSilent(var7);
               return null;
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   @Override
   protected void pC(StringBuilder var1) {
      var1.append("div h3.api-name { margin-top: 10px; } ");
      var1.append("h1 { margin:10px 0 20px; } ");
   }

   @Override
   protected String[] pC() {
      return kS;
   }

   @Override
   protected final Element pC(String var1, Document var2) {
      Element var3 = var2.getElementById("jd-content");
      if (var3 != null) {
         return var3;
      } else {
         Elements var4 = var2.getElementsByTag("devsite-content");
         if (var4.size() == 1) {
            return (Element)var4.get(0);
         } else {
            Element var5 = var2.getElementById("main");
            return var5 != null ? var5 : null;
         }
      }
   }

   @FunctionalInterface
   public interface Av {
      String getHtml(Element var1);
   }

   public static class Sv {
      private String pC;
      private com.pnfsoftware.jeb.corei.parsers.dex.Av.Av A;

      public Sv(String var1, com.pnfsoftware.jeb.corei.parsers.dex.Av.Av var2) {
         this.pC = var1;
         this.A = var2;
      }

      public String pC() {
         return this.pC;
      }

      public Elements pC(Document var1) {
         return var1.getElementsByTag(this.pC());
      }

      public String pC(Element var1) {
         return this.A.getHtml(var1);
      }
   }
}
