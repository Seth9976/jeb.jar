package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.base.PathProcessor;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class at extends Bv {
   private static final ILogger A = GlobalLog.getLogger(at.class);
   private List kS = new ArrayList();
   private String wS;
   private String UT;
   protected INet pC;
   private int E = 10;
   private LinkedHashMap sY = new LinkedHashMap();
   private boolean ys = false;
   private boolean ld = false;

   public at(IUnit var1, String var2, INet var3, String var4) {
      super(var1, var4);
      this.pC = var3;
      List var5 = PathProcessor.ENV.decodeFlexiMultiPath(var2);
      if (var5 != null && !var5.isEmpty()) {
         for (String var7 : var5) {
            String var8 = this.pC(var7);
            if (var8 != null) {
               this.kS.add(var8);
            }
         }
      } else {
         throw new NullPointerException("Cannot decode root paths");
      }
   }

   protected String pC(String var1) {
      if (Strings.isBlank(var1)) {
         return null;
      } else {
         if (var1.startsWith("http")) {
            try {
               return Strings.urldecodeUTF8(new URI(var1).toString());
            } catch (URISyntaxException var3) {
               A.catchingSilent(var3);
            }
         } else {
            if (this.ld) {
               return null;
            }

            try {
               return new File(var1).getCanonicalPath();
            } catch (IOException var4) {
               A.catchingSilent(var4);
            }
         }

         return var1;
      }
   }

   protected Document A(String var1) {
      try {
         String var2 = this.kS(var1);
         return var2 == null ? null : Jsoup.parse(var2, "");
      } catch (IOException var3) {
         A.catchingSilent(var3);
         return null;
      }
   }

   protected String kS(String var1) throws IOException {
      this.UT = var1;
      if (this.kS != null) {
         for (String var4 : this.kS) {
            String var2 = (String)this.sY.get(var4 + var1);
            if (var2 != null) {
               this.wS = var4;
               return var2;
            }
         }
      }

      String var5 = this.ys(var1);
      if (var5 != null) {
         this.sY.put(this.wS + var1, var5);
         if (this.sY.size() > this.E) {
            this.sY.remove(this.sY.keySet().iterator().next());
         }
      }

      return var5;
   }

   private String ys(String var1) throws IOException {
      for (String var3 : this.kS) {
         this.wS = var3;
         String var4;
         if (!var3.startsWith("http")) {
            File var5 = new File(this.wS, var1);
            var4 = var5.getCanonicalPath();
         } else {
            var4 = var3 + var1;
         }

         String var6 = this.ld(var4);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   private String ld(String var1) throws IOException {
      if (this.wS.startsWith("http") && !var1.startsWith(IO.getTempFolder().toString())) {
         if (this.pC != null) {
            if (!var1.startsWith("http")) {
               var1 = this.kS() + var1;
            }

            return this.pC.query(var1);
         }
      } else {
         File var2 = new File(var1);
         if (var2.exists()) {
            Object[] var10000 = new Object[]{var1};
            byte[] var3 = IO.readFile(var2);
            if (var3 == null) {
               return null;
            }

            return Strings.decodeUTF8(IO.readFile(var1));
         }
      }

      return null;
   }

   protected String pC(String var1, String var2, boolean var3, Document var4) {
      if (var3) {
         var1 = "<html>\n<head>" + this.pC(var4) + "\n</head>\n<body>" + var1 + "\n</body></html>";
      } else {
         var1 = this.wS(var1);
      }

      if (var2 != null) {
         var1 = this.pC(var1, var2);
      }

      return var1;
   }

   protected String pC(Document var1) {
      Elements var2 = var1.getElementsByTag("link");
      StringBuilder var3 = new StringBuilder();
      StringBuilder var4 = new StringBuilder();

      for (Element var6 : var2) {
         if (!"alternate".equals(var6.attr("rel"))) {
            if (this.ys) {
               String var7 = com.pnfsoftware.jeb.corei.parsers.dex.uX.pC(var6);
               if (var7 != null) {
                  File var8 = new File(new File(this.wS, this.UT).getParentFile(), var7);

                  try {
                     StringBuilder var9 = new StringBuilder();

                     for (String var12 : FileUtils.readLines(var8, Charset.defaultCharset())) {
                        var9.append(com.pnfsoftware.jeb.corei.parsers.dex.uX.pC(var12));
                     }

                     var4.append((CharSequence)var9);
                  } catch (IOException var13) {
                     var3.append("\n ").append(var6.toString());
                  }
               } else {
                  var3.append("\n ").append(var6.toString());
               }
            } else {
               var3.append("\n ").append(var6.toString());
            }
         }
      }

      for (Element var16 : var1.getElementsByTag("style")) {
         if (this.ys) {
            var4.append(com.pnfsoftware.jeb.corei.parsers.dex.uX.pC(var16.html()));
         } else {
            var16.html(var4);
         }
      }

      var3.append("\n ").append("<style>");
      var3.append((CharSequence)var4);
      this.A(var3);
      this.pC(var3);
      var3.append("</style>");
      return var3.toString();
   }

   protected String wS(String var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("<style>");
      this.A(var2);
      var2.append("</style>");
      var2.append("</head>");
      return var1.replace("</head>", var2);
   }

   protected abstract void pC(StringBuilder var1);

   protected void A(StringBuilder var1) {
      for (String var5 : this.pC()) {
         var1.append(".").append(var5).append(" { display:none; } ");
      }
   }

   protected abstract String[] pC();

   protected String pC(String var1, String var2) {
      var1 = var1.replace("href=\"#", "href=\"" + new File(this.UT).getName() + "#");
      String var3 = Matcher.quoteReplacement(new File(this.wS, var2 != null && !var2.equals("/") ? var2 : "").getPath());
      if (!this.wS.startsWith("http")) {
         return var1.replaceAll("href=\"(?![a-zA-Z]*://)", "href=\"" + var3 + "/").replaceAll("src=\"(?![a-zA-Z]*://)", "src=\"" + var3 + "/");
      } else {
         var1 = var1.replace("href=\"//", "href=\"https://");
         var1 = var1.replace("href=\"/", "href=\"" + this.kS());
         return var1.replaceAll("href=\\\"(?!https:)(?!http:)(?!/)", "href=\"" + var3 + "/");
      }
   }

   private int A() {
      int var1 = this.wS.indexOf("//");
      return var1 < 0 ? -1 : this.wS.indexOf("/", var1 + 2);
   }

   private String kS() {
      int var1 = this.A();
      return var1 < 0 ? "" : this.wS.substring(0, var1 + 1);
   }

   @Override
   public TypedContent getLocationInformation(IAddressableUnit var1, String var2) {
      if (this.wS != null && !var2.startsWith("about:blank")) {
         if (var2.startsWith("about:")) {
            var2 = var2.substring("about:".length());
         }

         Object[] var10000 = new Object[]{var2};
         if (var2.startsWith("file://")) {
            var2 = var2.substring(7);
         }

         if (this.wS.startsWith("http") && !var2.contains("://")) {
            try {
               var2 = new URI(this.wS).resolve(var2).toString();
            } catch (URISyntaxException var6) {
            }
         }

         var2 = Strings.urldecodeUTF8(var2);
         if (!var2.startsWith("http")) {
            try {
               var2 = new File(var2).getCanonicalPath();
            } catch (IOException var5) {
               A.catchingSilent(var5);
            }
         }

         if (var2.startsWith(this.wS)) {
            String var3 = var2.substring(this.wS.length());
            TypedContent var4 = this.E(var3);
            if (var4 != null) {
               return var4;
            }
         }

         return this.UT(var2);
      } else {
         return null;
      }
   }

   protected TypedContent UT(String var1) {
      try {
         int var2 = var1.lastIndexOf("#");
         String var3 = null;
         if (var2 > 0) {
            var3 = var1.substring(var2 + 1);
            var1 = var1.substring(0, var2);
         }

         String var4 = this.ld(var1);
         if (var4 == null) {
            return null;
         }

         Document var5 = Jsoup.parse(var4, "");
         if (var5 != null) {
            Element var6 = this.pC(var4, var5);
            if (var6 == null) {
               return null;
            }

            if (!this.wS.startsWith("http")) {
               File var7 = new File(var1);
               this.wS = var7.getParent();
               this.UT = var7.getName();
            }

            boolean var10 = !var6.tagName().equalsIgnoreCase("html");
            String var8 = this.pC(var6.toString(), "/", var10, var5);
            if (var8 != null) {
               if (Strings.isBlank(var3)) {
                  return TypedContent.html(var8);
               }

               return TypedContent.html(var8, var3);
            }
         }
      } catch (IOException var9) {
         A.catchingSilent(var9);
      }

      return null;
   }

   protected abstract Element pC(String var1, Document var2);

   public abstract TypedContent E(String var1);

   public TypedContent pC(Document var1, String var2, boolean var3, com.pnfsoftware.jeb.corei.parsers.dex.Av.Sv... var4) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.Av.Sv var8 : var4) {
         Elements var9 = var8.pC(var1);
         if (var9 != null && !var9.isEmpty()) {
            for (Element var12 : var9) {
               String var10;
               if ((var10 = var8.pC(var12)) != null) {
                  String var13 = this.pC(var10, var2, var3, var1);
                  if (var13 != null) {
                     return TypedContent.html(var13);
                  }
               }
            }
         }
      }

      return null;
   }
}
