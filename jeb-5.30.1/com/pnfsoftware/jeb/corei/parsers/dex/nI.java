package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.net.INet;
import java.util.List;
import java.util.TreeMap;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class nI extends com.pnfsoftware.jeb.corei.parsers.dex.eo {
   private static final String RF = "guide/topics/manifest";

   public nI(com.pnfsoftware.jeb.corei.parsers.xml.Nt var1, String var2, INet var3) {
      super(var1, var2, var3, "ApkManifestDoc");
   }

   @Override
   protected String xK() {
      return "guide/topics/manifest";
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      if (var1 != this.getPrimaryTarget()) {
         return null;
      } else {
         Object var6 = ((com.pnfsoftware.jeb.corei.parsers.xml.Nt)this.getPrimaryTarget()).getItemObject(var2);
         if (var6 instanceof Element) {
            return this.gP(((Node)var6).getNodeName());
         } else if (var6 instanceof Attr) {
            return this.q((Attr)var6);
         } else {
            if (var6 instanceof Object[] && ((Object[])var6).length > 1) {
               Object[] var7 = (Object[])var6;
               if (var7[0] instanceof Attr) {
                  return this.RF((Attr)var7[0]);
               }
            }

            return null;
         }
      }
   }

   @Override
   public TypedContent oW(String var1) {
      if (var1.startsWith("/")) {
         var1 = var1.substring(1);
      }

      int var2 = var1.indexOf("-element.html");
      if (var2 < 0) {
         var2 = var1.indexOf("-element#");
         if (var2 < 0 && var1.endsWith("-element")) {
            var2 = var1.indexOf("-element");
         }
      }

      if (var2 > 0) {
         String var3 = var1.substring(0, var2);
         if (var1.contains("#")) {
            String var4 = var1.substring(var1.indexOf(35) + 1);
            return this.xK(var3, var4);
         } else {
            return this.gP(var3);
         }
      } else {
         return null;
      }
   }

   @Override
   protected TypedContent Uv(String var1) {
      int var2 = var1.indexOf("reference/android/Manifest.permission");
      if (var2 > 0 && var1.contains("#")) {
         String var3 = var1.substring(var1.indexOf(35) + 1);
         return this.nf(var3);
      } else {
         return super.Uv(var1);
      }
   }

   protected TypedContent xK(String var1, String var2) {
      Document var3 = this.za(var1);
      if (var3 == null) {
         return null;
      } else {
         com.pnfsoftware.jeb.corei.parsers.dex.eo.CU var4 = new com.pnfsoftware.jeb.corei.parsers.dex.eo.CU("a", var1x -> {
            if (var1x.hasAttr("name") && var1x.attr("name").equals(var2)) {
               org.jsoup.nodes.Element var2x = var1x.parent();
               org.jsoup.nodes.Element var3x = var2x.nextElementSibling();
               if (var3x != null) {
                  return var2x.toString() + var3x.toString();
               }
            }

            return null;
         });
         return this.q(var3, "/", true, var4);
      }
   }

   private TypedContent q(Attr var1) {
      String var2 = var1.getOwnerElement().getNodeName();
      Document var3 = this.za(var2);
      if (var3 == null) {
         return null;
      } else {
         String var4 = var1.getNodeName();
         com.pnfsoftware.jeb.corei.parsers.dex.eo.CU var5 = new com.pnfsoftware.jeb.corei.parsers.dex.eo.CU("code", var1x -> {
            if (var4.equals(var1x.text()) && "dt".equals(var1x.parent().tagName())) {
               org.jsoup.nodes.Element var2x = var1x.parent();
               org.jsoup.nodes.Element var3x = var2x.nextElementSibling();
               if (var3x != null) {
                  return var2x.toString() + var3x.toString();
               }
            }

            return null;
         });
         return this.q(var3, "/", true, var5);
      }
   }

   private TypedContent nf(String var1) {
      String var4 = "..";
      Document var2 = this.Uv();
      org.jsoup.nodes.Element var5 = var2.getElementById(var1);
      if (var5 != null) {
         String var6 = this.q(var5.parent().toString(), var4, true, var2);
         if (var6 != null) {
            return TypedContent.html(var6);
         }
      }

      com.pnfsoftware.jeb.corei.parsers.dex.eo.CU var3 = new com.pnfsoftware.jeb.corei.parsers.dex.eo.CU(
         "a", var1x -> var1x.hasAttr("name") && var1x.attr("name").equals(var1) ? var1x.nextElementSibling().toString() : null
      );
      return this.q(var2, var4, true, var3);
   }

   private TypedContent RF(Attr var1) {
      String var2 = var1.getOwnerElement().getNodeName();
      String var3 = var1.getNodeName();
      Document var4 = null;
      com.pnfsoftware.jeb.corei.parsers.dex.eo.CU var5 = null;
      String var6 = "..";
      if ((var3.equals("name") || var3.endsWith(":name")) && var2.equals("uses-permission")) {
         var4 = this.Uv();
         String var7 = "\"" + var1.getNodeValue() + "\"";
         var5 = new com.pnfsoftware.jeb.corei.parsers.dex.eo.CU("h3", var1x -> {
            org.jsoup.nodes.Element var2x = var1x.lastElementSibling();
            if (var2x != null) {
               String var3x = var2x.text();
               if (var3x.contains("Constant Value:") && var3x.contains(var7)) {
                  org.jsoup.nodes.Element var4x = var1x.parent();
                  if (var4x != null) {
                     return var4x.toString();
                  }
               }
            }

            return null;
         });
      }

      return var4 == null ? null : this.q(var4, var6, true, var5);
   }

   private TypedContent gP(String var1) {
      Document var2 = this.za(var1);
      if (var2 == null) {
         return null;
      } else {
         String var3 = this.q(var2, var1);
         return var3 != null ? TypedContent.html(var3) : null;
      }
   }

   private String q(Document var1, String var2) {
      org.jsoup.nodes.Element var3 = null;
      Elements var4 = var1.getElementsByClass("devsite-article-inner");
      if (var4 != null && !var4.isEmpty()) {
         var3 = (org.jsoup.nodes.Element)var4.get(0);
      }

      if (var3 == null) {
         var4 = var1.getElementsByClass("jd-descr");
         if (var4 != null && !var4.isEmpty()) {
            var3 = (org.jsoup.nodes.Element)var4.get(0);
         }
      }

      if (var3 == null) {
         var4 = var1.getElementsByTag("h1");
         TreeMap var5 = new TreeMap();

         for (org.jsoup.nodes.Element var7 : var4) {
            String var8 = var7.html();
            if (var8 != null && var8.contains(var2) && var5.get(var8.length()) == null) {
               var5.put(var8.length(), var7.parent());
            }
         }

         if (!var5.isEmpty()) {
            var3 = (org.jsoup.nodes.Element)var5.values().iterator().next();
         }
      }

      if (var3 != null) {
         if (var3.child(0).hasClass("devsite-banner")) {
            var3.child(0).remove();
         }

         if (var3.child(0).hasClass("devsite-rating-container")) {
            var3.child(0).remove();
            if (var3.child(0).tagName().equals("script")) {
               var3.child(0).remove();
            }
         }

         if (var3.child(0).hasClass("devsite-article-meta")) {
            var3.child(0).remove();
         }

         if (var3.child(0).hasClass("devsite-breadcrumb-nav")) {
            var3.child(0).remove();
         }

         return this.q(var3.toString(), "/", true, var1);
      } else {
         return this.q(var1.toString(), "/", false, var1);
      }
   }

   @Override
   protected void q(StringBuilder var1) {
      super.q(var1);
      var1.append("html { margin-top: 10px;  margin-bottom: 10px; } ");
   }

   private Document za(String var1) {
      return this.RF(var1 + "-element.html");
   }

   private Document Uv() {
      return this.RF("../../../reference/android/Manifest.permission.html");
   }
}
