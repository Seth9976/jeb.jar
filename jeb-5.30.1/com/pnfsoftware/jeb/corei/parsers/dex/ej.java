package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.net.INet;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ej extends com.pnfsoftware.jeb.corei.parsers.dex.eo {
   private static final String RF = "guide/topics/resources";
   private final String xK;

   public static ej q(com.pnfsoftware.jeb.corei.parsers.xml.Nt var0, String var1, INet var2) {
      String var3 = q(var0);
      return var3 == null ? null : new ej(var0, var1, var2, var3);
   }

   private ej(com.pnfsoftware.jeb.corei.parsers.xml.Nt var1, String var2, INet var3, String var4) {
      super(var1, var2, var3, "ApkResourcesDoc");
      this.xK = var4;
   }

   @Override
   protected String xK() {
      return "guide/topics/resources";
   }

   private static String q(com.pnfsoftware.jeb.corei.parsers.xml.Nt var0) {
      Node var1 = var0.getDocument().getFirstChild();
      NodeList var2 = var1.getChildNodes();
      String var3 = null;

      for (int var4 = 0; var4 < var2.getLength(); var4++) {
         if (var2.item(var4).getNodeType() == 1) {
            var3 = var2.item(var4).getNodeName();
            break;
         }
      }

      if (var3 == null) {
         return null;
      } else {
         switch (var3) {
            case "bool":
            case "color":
            case "integer":
            case "dimen":
            case "item":
            case "integer-array":
            case "array":
               return var3;
            case "string":
            case "string-array":
            case "plurals":
               return var3;
            case "style":
               return var3;
            default:
               return null;
         }
      }
   }

   private String Uv() {
      String var1 = this.xK;
      switch (var1) {
         case "bool":
         case "color":
         case "integer":
         case "dimen":
         case "item":
         case "integer-array":
         case "array":
            return "more-resources.html";
         case "string":
         case "string-array":
         case "plurals":
            return "string-resource.html";
         case "style":
            return "style-resource.html";
         default:
            return null;
      }
   }

   private String[] oW() {
      String var1 = this.xK;
      switch (var1) {
         case "bool":
         case "color":
         case "integer":
         case "string":
         case "plurals":
            return new String[]{this.nf(this.xK)};
         case "integer-array":
         case "string-array":
            int var3 = this.xK.indexOf("-");
            String var4 = this.RF(this.xK, var3 + 1);
            return new String[]{var4.replace("-", "")};
         case "dimen":
            return new String[]{"Dimension"};
         case "item":
            return new String[]{"Id"};
         case "array":
            return new String[]{"TypedArray"};
         case "style":
            return new String[]{"style-resource"};
         default:
            return null;
      }
   }

   private String nf(String var1) {
      return Character.toUpperCase(var1.charAt(0)) + var1.substring(1, var1.length());
   }

   private String RF(String var1, int var2) {
      return Character.toUpperCase(var1.charAt(0))
         + var1.substring(1, var2)
         + Character.toUpperCase(var1.charAt(var2))
         + var1.substring(var2 + 1, var1.length());
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      if (this.xK != null && var1 == this.getPrimaryTarget()) {
         Object var6 = ((com.pnfsoftware.jeb.corei.parsers.xml.Nt)this.getPrimaryTarget()).getItemObject(var2);
         if (var6 instanceof Element) {
            return this.gP(((Element)var6).getNodeName());
         } else {
            return var6 instanceof Attr ? this.q((Attr)var6) : null;
         }
      } else {
         return null;
      }
   }

   @Override
   public TypedContent oW(String var1) {
      return null;
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
      org.jsoup.nodes.Element var3 = this.RF(var1, var2);
      return var3 == null ? null : this.q(var3.toString(), true, var1);
   }

   private org.jsoup.nodes.Element RF(Document var1, String var2) {
      org.jsoup.nodes.Element var3 = null;
      org.jsoup.nodes.Element var4 = null;

      for (String var8 : this.oW()) {
         var4 = var1.getElementById(var8);
      }

      if (var4 == null) {
         Elements var9 = var1.getElementsByClass("xml");
         if (var9.size() > 0) {
            var4 = var9.first().parent().child(0);
         }
      }

      if (var4 != null) {
         org.jsoup.nodes.Element var10 = var4;

         do {
            var10 = var10.nextElementSibling();
         } while (var10 != null && !var10.tagName().equals("dl"));

         if (var10 != null) {
            for (org.jsoup.nodes.Element var15 : var10.children()) {
               if ("elements:".equals(var15.text())) {
                  var3 = var15.nextElementSibling().child(0);
                  break;
               }
            }
         }
      }

      if (var3 == null) {
         return null;
      } else {
         String var11 = "<" + var2 + ">";

         while (var3.childNodeSize() > 0) {
            org.jsoup.nodes.Node var13 = var3.childNode(0);
            if (var13 instanceof org.jsoup.nodes.Element && var11.equals(((org.jsoup.nodes.Element)var13).text())) {
               while (var3.childNodeSize() > 4) {
                  var3.childNode(4).remove();
               }
               break;
            }

            var3.childNode(0).remove();
         }

         if ("resources".equals(var2)) {
            org.jsoup.nodes.Element var14 = var4.nextElementSibling();
            org.jsoup.nodes.Element var16 = var14.nextElementSibling();
            var3.insertChildren(0, new org.jsoup.nodes.Node[]{var4});
            var3.insertChildren(1, new org.jsoup.nodes.Node[]{var14});
            var3.insertChildren(2, new org.jsoup.nodes.Node[]{var16});
         }

         return var3;
      }
   }

   private TypedContent q(Attr var1) {
      String var2 = var1.getOwnerElement().getNodeName();
      Document var3 = this.za(var2);
      if (var3 == null) {
         return null;
      } else {
         org.jsoup.nodes.Element var4 = this.RF(var3, var2);
         if (var4 == null) {
            return null;
         } else {
            String var5 = var1.getNodeName();
            Elements var6 = var4.getElementsByTag("code");
            if (var6 != null && !var6.isEmpty()) {
               for (org.jsoup.nodes.Element var8 : var6) {
                  if (var5.equals(var8.text()) && "dt".equals(var8.parent().tagName())) {
                     org.jsoup.nodes.Element var9 = var8.parent();
                     var4 = var9.nextElementSibling();
                     if (var4 != null) {
                        String var10 = this.q(var9.toString() + var4.toString(), true, var3);
                        if (var10 != null) {
                           return TypedContent.html(var10);
                        }
                     }
                  }
               }
            }

            return null;
         }
      }
   }

   protected String q(String var1, boolean var2, Document var3) {
      return this.q(var1, "", var2, var3);
   }

   private Document za(String var1) {
      return this.RF(this.Uv());
   }
}
