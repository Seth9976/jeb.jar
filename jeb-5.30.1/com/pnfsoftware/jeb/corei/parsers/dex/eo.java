package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import java.io.File;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class eo extends com.pnfsoftware.jebglobal.Uz {
   private static final ILogger RF = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.dex.eo.class);
   private static final String[] xK = new String[]{
      "devsite-notification-promo",
      "devsite-dialog",
      "devsite-notification-eu-cookie",
      "devsite-notification-eu-cookie.devsite-notification-promo",
      "devsite-rating-container",
      "devsite-breadcrumb-nav",
      "devsite-nav"
   };

   public eo(IUnit var1, String var2, INet var3, String var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   protected String q(String var1) {
      String var2 = super.q(var1);
      if (var2 != null && this.xK() != null) {
         var2 = this.RF(this.xK(), var2);
      }

      return var2;
   }

   protected String xK() {
      return null;
   }

   String RF(String var1, String var2) {
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
               RF.catchingSilent(var7);
               return null;
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   @Override
   public String Dw() {
      return this.q();
   }

   @Override
   protected void q(StringBuilder var1) {
      var1.append("div h3.api-name { margin-top: 10px; } ");
      var1.append("h1 { margin:10px 0 20px; } ");
   }

   @Override
   protected String[] RF() {
      return xK;
   }

   @Override
   protected final Element q(String var1, Document var2) {
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

   public static class CU {
      private String q;
      private com.pnfsoftware.jeb.corei.parsers.dex.eo.eo RF;

      public CU(String var1, com.pnfsoftware.jeb.corei.parsers.dex.eo.eo var2) {
         this.q = var1;
         this.RF = var2;
      }

      public String q() {
         return this.q;
      }

      public Elements q(Document var1) {
         return var1.getElementsByTag(this.q());
      }

      public String q(Element var1) {
         return this.RF.getHtml(var1);
      }
   }

   @FunctionalInterface
   public interface eo {
      String getHtml(Element var1);
   }
}
