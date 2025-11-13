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

public class Ws extends com.pnfsoftware.jeb.corei.parsers.dex.Av {
   private final String A;

   public static Ws pC(com.pnfsoftware.jeb.corei.parsers.xml.cq var0, String var1, INet var2) {
      String var3 = pC(var0);
      return var3 == null ? null : new Ws(var0, var1, var2, var3);
   }

   private Ws(com.pnfsoftware.jeb.corei.parsers.xml.cq var1, String var2, INet var3, String var4) {
      super(var1, var2, var3, "ApkResourcesDoc");
      this.A = var4;
   }

   @Override
   protected String A() {
      return "guide/topics/resources";
   }

   private static String pC(com.pnfsoftware.jeb.corei.parsers.xml.cq var0) {
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

   private String kS() {
      String var1 = this.A;
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

   private String[] wS() {
      String var1 = this.A;
      switch (var1) {
         case "bool":
         case "color":
         case "integer":
         case "string":
         case "plurals":
            return new String[]{this.ys(this.A)};
         case "integer-array":
         case "string-array":
            int var3 = this.A.indexOf("-");
            String var4 = this.A(this.A, var3 + 1);
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

   private String ys(String var1) {
      return Character.toUpperCase(var1.charAt(0)) + var1.substring(1, var1.length());
   }

   private String A(String var1, int var2) {
      return Character.toUpperCase(var1.charAt(0))
         + var1.substring(1, var2)
         + Character.toUpperCase(var1.charAt(var2))
         + var1.substring(var2 + 1, var1.length());
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      if (this.A != null && var1 == this.getPrimaryTarget()) {
         Object var6 = ((com.pnfsoftware.jeb.corei.parsers.xml.cq)this.getPrimaryTarget()).getItemObject(var2);
         if (var6 instanceof Element) {
            return this.ld(((Element)var6).getNodeName());
         } else {
            return var6 instanceof Attr ? this.pC((Attr)var6) : null;
         }
      } else {
         return null;
      }
   }

   @Override
   public TypedContent E(String var1) {
      return null;
   }

   private TypedContent ld(String var1) {
      Document var2 = this.gp(var1);
      if (var2 == null) {
         return null;
      } else {
         String var3 = this.pC(var2, var1);
         return var3 != null ? TypedContent.html(var3) : null;
      }
   }

   private String pC(Document var1, String var2) {
      org.jsoup.nodes.Element var3 = this.A(var1, var2);
      return var3 == null ? null : this.pC(var3.toString(), true, var1);
   }

   private org.jsoup.nodes.Element A(Document var1, String var2) {
      org.jsoup.nodes.Element var3 = null;
      org.jsoup.nodes.Element var4 = null;

      for (String var8 : this.wS()) {
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

   private TypedContent pC(Attr var1) {
      String var2 = var1.getOwnerElement().getNodeName();
      Document var3 = this.gp(var2);
      if (var3 == null) {
         return null;
      } else {
         org.jsoup.nodes.Element var4 = this.A(var3, var2);
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
                        String var10 = this.pC(var9.toString() + var4.toString(), true, var3);
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

   protected String pC(String var1, boolean var2, Document var3) {
      return this.pC(var1, "", var2, var3);
   }

   private Document gp(String var1) {
      return this.A(this.kS());
   }
}
