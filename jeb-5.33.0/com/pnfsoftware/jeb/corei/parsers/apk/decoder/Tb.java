package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.XmlBuilder;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Bz;
import com.pnfsoftware.jebglobal.cS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tb {
   private static final ILogger pC = GlobalLog.getLogger(Tb.class);
   private GK A;
   private uX kS;
   private yt wS;
   private int UT = 3;

   public Tb(GK var1, uX var2, yt var3) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
   }

   public void pC(int var1) {
      this.UT = var1;
   }

   public Map pC() {
      if (this.A.kS.isEmpty()) {
         return null;
      } else {
         HashMap var1 = new HashMap();
         int var2 = (Integer)this.A.kS.get(0);
         int var3 = this.A.kS.size();
         if (var3 >= 2) {
            pC.warn(S.L("arsc: %d packages found; only the values of the first package will be generated (id=0x%X)"), var3, var2);
         }

         List var4 = (List)this.A.wS.get(var2);
         Set var5 = (Set)this.A.UT.get(var2);
         List var6 = this.pC(var2, 0, null);
         if (!var6.isEmpty()) {
            String var7 = this.pC(var6);
            if (var7 != null) {
               String var8 = "res/values/public.xml";
               var1.put(var8, new KD(Mh.UT, var8, TypedContent.bytes(Strings.encodeUTF8(var7))));
            }
         }

         var6 = this.pC(var2, var4.indexOf("id") + 1, null);
         if (!var6.isEmpty()) {
            String var18 = this.A(var6);
            if (var18 != null) {
               String var20 = "res/values/ids.xml";
               var1.put(var20, new KD(Mh.UT, var20, TypedContent.bytes(Strings.encodeUTF8(var18))));
            }
         }

         for (String var21 : var5) {
            String var9 = "res/values";
            if (!var21.isEmpty()) {
               var9 = var9 + "-" + var21;
            }

            int var10 = 0;

            for (String var12 : var4) {
               int var13 = var10 + 1;
               var6 = this.pC(var2, var13, var21);
               if (!var6.isEmpty()) {
                  String var14 = this.pC(var12, var6);
                  if (var14 != null) {
                     String var15 = var9 + "/" + var12 + (var12.endsWith("s") ? "" : "s") + ".xml";
                     var1.put(var15, new KD(Mh.UT, var15, TypedContent.bytes(Strings.encodeUTF8(var14))));
                  }
               }

               var10++;
            }
         }

         return var1;
      }
   }

   private String pC(String var1, List var2) {
      if (var1.equals("id")) {
         return null;
      } else {
         byte var4 = -1;
         switch (var1.hashCode()) {
            case -826507106:
               if (var1.equals("drawable")) {
                  var4 = 0;
               }
            default:
               switch (var4) {
                  case 0:
                     return this.pC(var1, var2, "item", "drawable");
                  default:
                     return this.pC(var1, var2, var1, null);
               }
         }
      }
   }

   private String pC(List var1) {
      XmlBuilder var2 = this.A();
      var2.openElement("resources");
      var2.appendLine();

      for (cS var4 : var1) {
         LinkedHashMap var5 = new LinkedHashMap();
         var5.put("type", this.A(var4.E));
         var5.put("name", var4.ys);
         var5.put("id", Strings.ff("0x%08x", var4.E));
         var2.openElement("public", var5, true);
         var2.appendLine();
      }

      var2.closeElement();
      return var2.toString();
   }

   private String A(List var1) {
      XmlBuilder var2 = this.A();
      var2.openElement("resources");
      var2.appendLine();

      for (cS var4 : var1) {
         LinkedHashMap var5 = new LinkedHashMap();
         var5.put("type", "id");
         var5.put("name", var4.ys);
         var2.openElement("item", var5, true);
         var2.appendLine();
      }

      var2.closeElement();
      return var2.toString();
   }

   private String pC(String var1, List var2, String var3, String var4) {
      XmlBuilder var5 = this.A();
      var5.openElement("resources");
      var5.appendLine();
      LinkedHashMap var6 = new LinkedHashMap();
      ArrayList var7 = new ArrayList();
      ArrayList var8 = new ArrayList();
      ArrayList var9 = new ArrayList();
      ArrayList var10 = new ArrayList();
      if (var3 == null) {
         var3 = "item";
      }

      int[] var11 = new int[2];
      int var12 = 0;

      for (cS var14 : var2) {
         if (this.kS.pC(var14, var11)) {
            String var15 = this.kS.pC(var11[0], var11[1]);
            if (this.wS.pC(var15)) {
               String var25 = this.pC(var1, var14.sY, var15, var14.ys);
               if (var25 != null) {
                  this.kS.pC(var11[0], var11[1], var25);
                  this.wS.pC(var15, var25);
               }
               continue;
            }
         }

         if (var14.pC()) {
            String var17 = this.kS.pC(var14);
            var6.clear();
            if (var4 != null) {
               var6.put("type", var4);
            }

            var6.put("name", var14.ys);
            var5.openElement(var3, var6);
            var5.append(XmlBuilder.escapeTextData(var17));
            var5.closeElement(true);
         } else {
            var6.clear();
            var6.put("name", var14.ys);
            var7.clear();
            var8.clear();
            var9.clear();
            var10.clear();
            this.pC(var1, var14, var6, var7, var8, var9, var10);
            var5.openElement(var3, var6);
            if (!var7.isEmpty()) {
               var5.appendLine();

               for (Tb.Sv var16 : var7) {
                  var6.clear();
                  var6.put("name", var16.pC);
                  var6.put("value", var16.A);
                  var5.openElement("enum", var6, true);
                  var5.appendLine();
               }
            }

            if (!var8.isEmpty()) {
               var5.appendLine();

               for (Tb.K var22 : var8) {
                  var6.clear();
                  var6.put("quantity", var22.pC);
                  var5.openElement("item", var6);
                  var5.append(XmlBuilder.escapeTextData(var22.A));
                  var5.closeElement(true);
               }
            }

            if (!var9.isEmpty()) {
               var5.appendLine();

               for (Tb.Av var23 : var9) {
                  var5.openElement("item");
                  var5.append(XmlBuilder.escapeTextData(var23.pC));
                  var5.closeElement(true);
               }
            }

            if (!var10.isEmpty()) {
               var5.appendLine();

               for (Tb.Ws var24 : var10) {
                  var5.openElement("item");
                  var5.append(XmlBuilder.escapeTextData(var24.pC));
                  var5.closeElement(true);
               }
            }

            var5.closeElement(true);
         }

         var12++;
      }

      if (var12 == 0) {
         return null;
      } else {
         var5.closeElement();
         return var5.toString();
      }
   }

   private void pC(String var1, cS var2, Map var3, List var4, List var5, List var6, List var7) {
      if (!var2.A()) {
         throw new RuntimeException();
      } else {
         for (Bz var9 : var2.wS) {
            switch (var9.pC) {
               case 16777216:
                  String var11 = Bz.pC(var9.A.kS);
                  var3.put("format", var11);
                  break;
               case 16777220:
               case 16777221:
               case 16777222:
               case 16777223:
               case 16777224:
               case 16777225:
                  if ("plurals".equals(var1)) {
                     var5.add(new Tb.K(Bz.A(var9.pC), this.kS.pC(var9.A)));
                     break;
                  }
               case 16777217:
               case 16777218:
               case 16777219:
               default:
                  if (Strings.contains((String)var3.get("format"), "enum", "flags") || var1.equals("style")) {
                     String var10 = this.kS(var9.pC);
                     var4.add(new Tb.Sv(var10, this.kS.pC(var9.A)));
                  } else if (var1.equals("array")) {
                     var6.add(new Tb.Av(this.kS.pC(var9.A)));
                  } else {
                     var7.add(new Tb.Ws(this.kS.pC(var9.A)));
                  }
            }
         }
      }
   }

   private String pC(String var1, String var2, String var3, String var4) {
      if (this.UT <= 0) {
         return null;
      } else {
         String[] var5 = var3.split("/");
         String var6 = var1 + (var2.length() == 0 ? "" : "-" + var2);
         String var7 = IO.getExtension(var3);
         if (this.UT <= 2 || Strings.isBlank(var4)) {
            return "res/" + var6 + "/" + var5[var5.length - 1];
         } else {
            return this.UT >= 3 ? "res/" + var6 + "/" + var4 + var7 : null;
         }
      }
   }

   private XmlBuilder A() {
      XmlBuilder var1 = new XmlBuilder(4);
      var1.appendStandardHeader();
      return var1;
   }

   private String A(int var1) {
      int var2 = var1 >>> 24;
      int var3 = var1 >>> 16 & 0xFF;
      return (String)((List)this.A.wS.get(var2)).get(var3 - 1);
   }

   private String kS(int var1) {
      return this.kS.kS(var1);
   }

   private List pC(int var1, int var2, String var3) {
      if (var1 == 0) {
         throw new RuntimeException();
      } else {
         int var4;
         int var5;
         if (var2 == 0) {
            var4 = var1 << 24;
            var5 = -16777216;
         } else {
            var4 = var1 << 24 | var2 << 16;
            var5 = -65536;
         }

         ArrayList var6 = new ArrayList();

         for (int var8 : this.A.E.keySet()) {
            if ((var8 & var5) == var4) {
               p var9 = (p)this.A.E.get(var8);
               if (var9 != null) {
                  cS var10;
                  if (var3 == null && !var9.E.isEmpty()) {
                     var10 = (cS)var9.E.values().iterator().next();
                  } else {
                     var10 = (cS)var9.E.get(var3);
                  }

                  if (var10 != null) {
                     var6.add(var10);
                  }
               }
            }
         }

         return var6;
      }
   }

   private static class Av {
      String pC;

      public Av(String var1) {
         this.pC = var1;
      }
   }

   private static class K {
      String pC;
      String A;

      public K(String var1, String var2) {
         this.pC = var1;
         this.A = var2;
      }
   }

   private static class Sv {
      String pC;
      String A;

      public Sv(String var1, String var2) {
         this.pC = var1;
         this.A = var2;
      }
   }

   private static class Ws {
      String pC;

      public Ws(String var1) {
         this.pC = var1;
      }
   }
}
