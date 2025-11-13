package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.code.ICodeType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Uz extends com.pnfsoftware.jeb.corei.parsers.dex.eo {
   private static final ILogger RF = GlobalLog.getLogger(Uz.class);
   private List xK = new ArrayList();
   private int Dw = -1;
   private Set Uv = new HashSet();
   private String oW = null;

   public Uz(bK var1, String var2, INet var3) {
      super(var1, var2, var3, "DexJavadoc");
      this.xK.add(new Uz.nI("Landroidx/", "https://androidx.de/"));
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      this.Dw = -1;
      this.Uv.clear();
      if (var1 != this.getPrimaryTarget()) {
         return null;
      } else {
         Object var6 = ((bK)this.getPrimaryTarget()).getObjectById(var2);
         if (var6 instanceof IDexType || var6 instanceof IDexClass) {
            String var11 = this.q(var6);

            while (var11.length() > 0 && var11.startsWith("[")) {
               var11 = var11.substring(1);
            }

            return this.nf(var11);
         } else if (var6 instanceof IDexMethod var10) {
            String var12 = this.q(var10.getClassType());
            String var13 = var10.getName(false);
            if (var13.equals("<init>")) {
               var13 = var10.getClassType().getName(false);
            }

            return this.q(var12, new Uz.CU(var10), var13, var10.getClassType().getImplementingClass());
         } else if (var6 instanceof IDexField var7) {
            String var8 = this.q(var7.getClassType());
            String var9 = var7.getName(false);
            return this.q(var8, new Uz.eo(var9), var9, var7.getClassType().getImplementingClass());
         } else {
            return null;
         }
      }
   }

   private String q(Object var1) {
      String var2 = this.RF(var1);
      return var2 == null ? null : var2.replace("$", ".");
   }

   private String RF(Object var1) {
      if (var1 instanceof ICodeType var3) {
         return var3.getSignature(true);
      } else {
         return var1 instanceof IDexClass var2 ? var2.getSignature(true) : null;
      }
   }

   @Override
   public TypedContent getLocationInformation(IAddressableUnit var1, String var2) {
      if (var2.startsWith("about:blank")) {
         return null;
      } else {
         if (this.Dw == -1) {
            TypedContent var3 = super.getLocationInformation(var1, var2);
            if (var3 != null) {
               return var3;
            }
         }

         for (int var4 : this.Uv) {
            if (var2.startsWith("file://")) {
               var2 = var2.substring(7);
            }

            String var5 = var2;
            Uz.nI var6 = (Uz.nI)this.xK.get(var4);
            String var7 = var6.RF.substring(0, var6.RF.indexOf("/", 8));
            if (var2.startsWith("/")) {
               var5 = var7 + var2;
            }

            if (var5.startsWith(var7)) {
               String var8 = var5.substring(var7.length() + 1);
               TypedContent var9 = this.oW(var8);
               if (var9 != null) {
                  return var9;
               }
            }

            TypedContent var11 = super.getLocationInformation(var1, var5);
            if (var11 != null) {
               return var11;
            }
         }

         return null;
      }
   }

   @Override
   public TypedContent oW(String var1) {
      String var2 = this.gP(var1);
      String var3 = "L" + (var2.charAt(0) == '/' ? var2.substring(1) : var2) + ";";
      if (var1.contains("#")) {
         int var4 = var1.indexOf(35);
         String var5 = var1.substring(var4 + 1);
         return this.xK(var3, var5);
      } else {
         return this.nf(var3);
      }
   }

   private String gP(String var1) {
      int var2 = var1.indexOf(".html");
      int var3 = var1.indexOf(35);
      String var4;
      if (var2 >= 0) {
         var4 = var1.substring(0, var2);
      } else {
         var4 = var3 >= 0 ? var1.substring(0, var3) : var1;
      }

      return var4.replace('\\', '/');
   }

   protected TypedContent nf(String var1) {
      this.Dw = -1;
      if (this.lm(var1)) {
         Document var2 = this.JY(var1);
         if (var2 == null) {
            return null;
         }

         String var3 = this.q(var2, var1, null);
         if (var3 != null) {
            return TypedContent.html(var3);
         }
      }

      for (Uz.nI var8 : this.xK) {
         if (var8.q(var1)) {
            Document var4 = this.q(var1, var8);
            if (var4 == null) {
               return null;
            }

            String var5 = this.q(var4, var1, var8);
            if (var5 != null) {
               this.Dw = 0;
               this.Uv.add(0);
               return TypedContent.html(var5);
            }
         }
      }

      if (var1.startsWith("Ldalvik/annotation/")) {
         String var7 = this.za(var1);
         if (var7 != null) {
            return TypedContent.html(var7);
         }
      }

      return null;
   }

   private String za(String var1) {
      Document var2 = this.Uv();
      String var3 = var1.substring(1, var1.length() - 1).replace("/", ".");
      Elements var4 = var2.getElementsContainingOwnText(var3);
      ArrayList var5 = new ArrayList();

      for (Element var7 : var4) {
         if (var7.html().trim().equalsIgnoreCase(var3) && var7.tagName().equals("h3")) {
            var5.add(var7);
         }
      }

      if (!var5.isEmpty()) {
         Iterator var9 = var5.iterator();
         if (var9.hasNext()) {
            Element var10 = (Element)var9.next();
            StringBuilder var8 = new StringBuilder();

            do {
               var8.append(var10.toString());
               var10 = var10.nextElementSibling();
            } while (var10 != null && !var10.tagName().equals("h3"));

            return this.RF(var8.toString(), "", true, var2);
         }
      }

      return null;
   }

   protected TypedContent q(String var1, Uz.ej var2, String var3, IDexClass var4) {
      if (this.lm(var1)) {
         Document var5 = this.JY(var1);
         if (var5 == null) {
            return null;
         }

         String var6 = this.q(var2, var3, var5, var1);
         if (var6 != null) {
            return TypedContent.html(var6);
         }
      }

      while (var4 != null) {
         ArrayList var12 = new ArrayList(var4.getImplementedInterfaces());

         for (int var13 = 0; var13 < var12.size(); var13++) {
            ICodeType var7 = (ICodeType)var12.get(var13);
            TypedContent var8 = this.q(this.q(var7), var2, var3, null);
            if (var8 != null) {
               return var8;
            }

            IDexClass var9 = ((IDexType)var7).getImplementingClass();
            if (var9 != null) {
               for (ICodeType var11 : var9.getImplementedInterfaces()) {
                  if (!var12.contains(var11)) {
                     var12.add(var11);
                  }
               }
            }
         }

         List var14 = var4.getSupertypes();

         for (ICodeType var16 : var14) {
            TypedContent var17 = this.q(this.q(var16), var2, var3, null);
            if (var17 != null) {
               return var17;
            }
         }

         if (!var14.isEmpty()) {
            var4 = ((IDexType)var14.get(0)).getImplementingClass();
         } else {
            var4 = null;
         }
      }

      return null;
   }

   protected TypedContent xK(String var1, String var2) {
      if (this.lm(var1)) {
         Document var3 = this.JY(var1);
         if (var3 == null) {
            return null;
         }

         String var4 = var2;
         int var5 = var2.indexOf("(");
         if (var5 > 0) {
            var4 = var2.substring(0, var5);
         }

         String var6 = this.q(new Uz.eo(var2), var4, var3, var1);
         if (var6 != null) {
            return TypedContent.html(var6);
         }
      }

      return null;
   }

   private boolean lm(String var1) {
      return Strings.startsWith(
            var1,
            "Ljava/",
            "Ljavax/",
            "Landroid/",
            "Landroidx/",
            "Lcom/google/",
            "Ldalvik/",
            "Ljunit/",
            "Lorg/apache/http/",
            "Lorg/json/",
            "Lorg/omg/",
            "Lorg/w3c/",
            "Lorg/xml/"
         )
         && (!var1.startsWith("Ldalvik/annotation/") || var1.startsWith("Ldalvik/annotation/TestTarget"));
   }

   private String q(Document var1, String var2, Uz.nI var3) {
      Element var4 = var1.getElementById("jd-content");
      if (var4 == null) {
         Elements var5 = var1.getElementsByClass("contentContainer");
         if (!var5.isEmpty()) {
            var4 = var5.first();
         }
      }

      if (var4 == null) {
         for (Element var13 : var1.getElementsByTag("meta")) {
            if (var13.hasAttr("http-equiv") && var13.attr("http-equiv").equals("refresh")) {
               return null;
            }
         }

         Elements var12 = var1.getElementsByTag("body");
         if (var12.size() != 0 && !((Element)var12.get(0)).children().isEmpty()) {
            Element var14 = this.q(null, var1);
            return var14 != null ? this.RF(var14.toString(), var2, true, var1) : this.RF(var1.toString(), var2, false, var1);
         } else {
            return null;
         }
      } else {
         Elements var9 = var4.children();
         StringBuilder var6 = new StringBuilder();

         for (int var7 = 0; var7 < var9.size() && var7 < 30; var7++) {
            Element var8 = (Element)var9.get(var7);
            if (var7 != 0 && this.zz(var8.tagName()) || var8.hasClass("details")) {
               break;
            }

            var6.append(var8.toString());
         }

         return this.RF(var6.toString(), var2, true, var1);
      }
   }

   private boolean zz(String var1) {
      return var1.length() > 1 && Character.toLowerCase(var1.charAt(0)) == 'h' && Character.isDigit(var1.charAt(1));
   }

   private String q(Uz.ej var1, String var2, Document var3, String var4) {
      ArrayList var5 = new ArrayList();
      TreeMap var6 = new TreeMap();
      boolean var7 = var2.contains(":") && var2.startsWith("attr_");

      for (Element var10 : var3.getElementsByTag("a")) {
         if (var10.hasAttr("name") && var10.attr("name").startsWith(var2)) {
            var5.add(var10);
         }
      }

      if (!var5.isEmpty()) {
         for (Element var21 : var5) {
            Element var11 = var21.nextElementSibling();
            if (var11 != null && (!var7 && (var11.tagName().equals("ul") || var11.tagName().equals("div")) || var7 && var11.tagName().equals("h3"))) {
               String var12 = var21.attr("name");
               int var13 = var1.q(var12);
               if (var13 == 0) {
                  return this.RF(var7 ? this.q(var11) : var11.toString(), var4, true, var3);
               }

               if (var13 > 0) {
                  Object var14 = (List)var6.get(var13);
                  if (var14 == null) {
                     var14 = new ArrayList();
                     var6.put(var13, var14);
                  }

                  var14.add(var11);
               }
            }
         }
      }

      if (var7) {
         var2 = var2.substring("attr_".length());
      }

      Elements var16 = var3.getElementsContainingOwnText(var2);
      var5 = new ArrayList();

      for (Element var22 : var16) {
         if (var22.html().trim().equalsIgnoreCase(var2) && var22.tagName().equals("h3") && (var22.hasAttr("name") || var22.hasAttr("id"))) {
            var5.add(var22);
         }
      }

      if (!var5.isEmpty()) {
         for (Element var23 : var5) {
            String var25 = var23.hasAttr("id") ? var23.attr("id") : var23.attr("name");
            int var27 = var1.q(var25);
            if (var27 == 0) {
               return this.RF(var7 ? this.q(var23) : var23.parent().toString(), var4, true, var3);
            }

            if (var27 > 0) {
               Object var29 = (List)var6.get(var27);
               if (var29 == null) {
                  var29 = new ArrayList();
                  var6.put(var27, var29);
               }

               var29.add(var23.parent());
            }
         }
      }

      if (var6.isEmpty()) {
         return null;
      } else {
         List var20 = (List)var6.values().iterator().next();
         if (var20.size() == 1) {
            return this.RF(((Element)var20.get(0)).toString(), var4, true, var3);
         } else {
            StringBuilder var24 = new StringBuilder(S.L("Several methods match:")).append(" ");

            for (Element var28 : var20) {
               var24.append(var28.toString()).append("<br/><br/><br/>");
            }

            return this.RF(var24.toString(), var4, true, var3);
         }
      }
   }

   private String q(Element var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("<div>");

      do {
         var2.append(var1.toString());
         var1 = var1.nextElementSibling();
      } while (var1 != null && !var1.tagName().startsWith("h"));

      var2.append("</div>");
      return var2.toString();
   }

   protected String RF(String var1, String var2, boolean var3, Document var4) {
      int var5 = var2.lastIndexOf(47);
      String var6 = null;
      if (var5 >= 0) {
         var6 = var2.substring(1, var5);
      }

      return this.q(var1, var6, var3, var4);
   }

   private Document JY(String var1) {
      String var2 = var1.substring(1, var1.length() - 1) + ".html";
      return this.RF(var2);
   }

   private Document q(String var1, Uz.nI var2) {
      try {
         String var3 = var1.substring(1, var1.length() - 1) + ".html";
         String var4 = this.q.query(var2.RF + var3);
         return var4 == null ? null : Jsoup.parse(var4, "");
      } catch (IOException var5) {
         RF.catchingSilent(var5);
         return null;
      }
   }

   private Document Uv() {
      try {
         if (this.oW == null) {
            this.oW = this.q.query("https://source.android.com/docs/core/runtime/dex-format");
            if (this.oW == null) {
               return null;
            }
         }

         return Jsoup.parse(this.oW, "");
      } catch (IOException var2) {
         RF.catchingSilent(var2);
         return null;
      }
   }

   private class CU implements Uz.ej {
      private IDexMethod RF;

      public CU(IDexMethod var2) {
         this.RF = var2;
      }

      @Override
      public int q(String var1) {
         var1 = Strings.urldecodeUTF8(var1);
         List var2 = this.RF.getParameterTypes();
         if (var1.contains("-")) {
            if (var1.endsWith("-")) {
               var1 = var1.substring(0, var1.length() - 1);
            }

            var1 = var1.replaceFirst("-", " ").replaceAll("-", ", ");
         }

         String[] var3 = var1.split(" |\\s|\\n|\\(");
         int var4 = 0;
         int var5 = 1;

         int var6;
         for (var6 = 0; var5 < var3.length; var5++) {
            String var7 = var3[var5];
            if (!var7.isEmpty() && !var7.equals(")")) {
               if (!var7.endsWith(",") && !var7.endsWith(")")) {
                  if (var5 + 1 < var3.length) {
                     var3[var5 + 1] = var3[var5] + " " + var3[var5 + 1];
                  }
               } else {
                  var7 = var7.substring(0, var7.length() - 1);
               }

               if (var7.endsWith("...")) {
                  var7 = var7.replace("...", "[]");
               } else if (var7.endsWith(":A")) {
                  var7 = var7.replace(":A", "[]");
               }

               if (var6 >= var2.size()) {
                  return -1;
               }

               ICodeType var8 = (ICodeType)var2.get(var6);
               int var9 = this.q(var7, this.q(var8));
               if (var9 == 0) {
                  var6++;
               } else {
                  if (var9 < 0) {
                     return -1;
                  }

                  var6++;
                  var4 += var9;
               }
            }
         }

         return var2.size() == var6 ? var4 : -1;
      }

      private int q(String var1, String var2) {
         if (Strings.equals(var1, var2)) {
            return 0;
         } else {
            while (var1.endsWith("[]") && var2.endsWith("[]")) {
               var1 = var1.substring(0, var1.length() - 2);
               var2 = var2.substring(0, var2.length() - 2);
            }

            if (var1.endsWith("[]")) {
               return -1;
            } else {
               byte var3 = 0;
               if (var2.endsWith("[]")) {
                  var3 += 2;
               }

               switch (var1) {
                  case "E":
                  case "T":
                  case "K":
                  case "V":
                  case "N":
                  case "C":
                  case "S":
                  case "U":
                     return var3 + 1;
                  default:
                     return var3 + 5;
               }
            }
         }
      }

      private String q(ICodeType var1) {
         String var2 = Uz.this.q(var1);
         int var3 = 0;

         for (int var4 = 0; var4 < var2.length() && var2.charAt(var4) == '['; var4++) {
            var3++;
         }

         if (var3 > 0) {
            var2 = var2.substring(var3);
         }

         String var6;
         if (var2.equals("V")) {
            var6 = "void";
         } else {
            String var5 = JavaUtil.letterToPrimitive(var2);
            if (var5 != null) {
               var6 = var5;
            } else {
               var6 = JavaUtil.extractFullName(var2, false);
            }
         }

         for (int var7 = 0; var7 < var3; var7++) {
            var6 = var6 + "[]";
         }

         return var6;
      }
   }

   private interface ej {
      int q(String var1);
   }

   private static class eo implements Uz.ej {
      private String q;

      public eo(String var1) {
         this.q = var1;
      }

      @Override
      public int q(String var1) {
         var1 = Strings.urldecodeUTF8(var1);
         return this.q.equals(var1) ? 0 : -1;
      }
   }

   private static class nI {
      String q;
      String RF;

      public nI(String var1, String var2) {
         this.q = var1;
         this.RF = var2;
      }

      public boolean q(String var1) {
         return Strings.startsWith(var1, this.q);
      }
   }
}
