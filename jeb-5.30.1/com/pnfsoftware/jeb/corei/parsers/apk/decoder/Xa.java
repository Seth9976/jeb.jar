package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.XmlBuilder;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.oI;
import com.pnfsoftware.jebglobal.ta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Xa {
   private static final ILogger RF = GlobalLog.getLogger(Xa.class);
   public static final String q = "res";
   private Nz xK;
   private kY Dw;
   private vn Uv;
   private int oW = 3;

   public Xa(Nz var1, kY var2, vn var3) {
      this.xK = var1;
      this.Dw = var2;
      this.Uv = var3;
   }

   public void q(int var1) {
      this.oW = var1;
   }

   public int q() {
      return this.oW;
   }

   public Map RF() {
      if (this.xK.xK.isEmpty()) {
         return null;
      } else {
         HashMap var1 = new HashMap();
         int var2 = (Integer)this.xK.xK.get(0);
         int var3 = this.xK.xK.size();
         if (var3 >= 2) {
            RF.warn(S.L("arsc: %d packages found; only the values of the first package will be generated (id=0x%X)"), var3, var2);
         }

         List var4 = (List)this.xK.Dw.get(var2);
         Set var5 = (Set)this.xK.Uv.get(var2);
         List var6 = this.q(var2, 0, null);
         if (!var6.isEmpty()) {
            String var7 = this.q(var6);
            if (var7 != null) {
               String var8 = "res/values/public.xml";
               var1.put(var8, new qV(Uz.Uv, var8, TypedContent.bytes(Strings.encodeUTF8(var7))));
            }
         }

         var6 = this.q(var2, var4.indexOf("id") + 1, null);
         if (!var6.isEmpty()) {
            String var18 = this.RF(var6);
            if (var18 != null) {
               String var20 = "res/values/ids.xml";
               var1.put(var20, new qV(Uz.Uv, var20, TypedContent.bytes(Strings.encodeUTF8(var18))));
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
               var6 = this.q(var2, var13, var21);
               if (!var6.isEmpty()) {
                  String var14 = this.q(var12, var6);
                  if (var14 != null) {
                     String var15 = var9 + "/" + var12 + (var12.endsWith("s") ? "" : "s") + ".xml";
                     var1.put(var15, new qV(Uz.Uv, var15, TypedContent.bytes(Strings.encodeUTF8(var14))));
                  }
               }

               var10++;
            }
         }

         return var1;
      }
   }

   private String q(String var1, List var2) {
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
                     return this.q(var1, var2, "item", "drawable");
                  default:
                     return this.q(var1, var2, var1, null);
               }
         }
      }
   }

   private String q(List var1) {
      XmlBuilder var2 = this.xK();
      var2.openElement("resources");
      var2.appendLine();

      for (ta var4 : var1) {
         LinkedHashMap var5 = new LinkedHashMap();
         var5.put("type", this.RF(var4.lm));
         var5.put("name", var4.JY);
         var5.put("id", Strings.ff("0x%08x", var4.lm));
         var2.openElement("public", var5, true);
         var2.appendLine();
      }

      var2.closeElement();
      return var2.toString();
   }

   private String RF(List var1) {
      XmlBuilder var2 = this.xK();
      var2.openElement("resources");
      var2.appendLine();

      for (ta var4 : var1) {
         LinkedHashMap var5 = new LinkedHashMap();
         var5.put("type", "id");
         var5.put("name", var4.JY);
         var2.openElement("item", var5, true);
         var2.appendLine();
      }

      var2.closeElement();
      return var2.toString();
   }

   private String q(String var1, List var2, String var3, String var4) {
      XmlBuilder var5 = this.xK();
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

      for (ta var14 : var2) {
         if (this.Dw.q(var14, var11)) {
            String var15 = this.Dw.q(var11[0], var11[1]);
            if (this.Uv.q(var15)) {
               String var25 = this.q(var1, var14.zz, var15, var14.JY);
               if (var25 != null) {
                  this.Dw.q(var11[0], var11[1], var25);
                  this.Uv.q(var15, var25);
               }
               continue;
            }
         }

         if (var14.q()) {
            String var17 = this.Dw.q(var14);
            var6.clear();
            if (var4 != null) {
               var6.put("type", var4);
            }

            var6.put("name", var14.JY);
            var5.openElement(var3, var6);
            var5.append(XmlBuilder.escapeTextData(var17));
            var5.closeElement(true);
         } else {
            var6.clear();
            var6.put("name", var14.JY);
            var7.clear();
            var8.clear();
            var9.clear();
            var10.clear();
            this.q(var1, var14, var6, var7, var8, var9, var10);
            var5.openElement(var3, var6);
            if (!var7.isEmpty()) {
               var5.appendLine();

               for (Xa.CU var16 : var7) {
                  var6.clear();
                  var6.put("name", var16.q);
                  var6.put("value", var16.RF);
                  var5.openElement("enum", var6, true);
                  var5.appendLine();
               }
            }

            if (!var8.isEmpty()) {
               var5.appendLine();

               for (Xa.nI var22 : var8) {
                  var6.clear();
                  var6.put("quantity", var22.q);
                  var5.openElement("item", var6);
                  var5.append(XmlBuilder.escapeTextData(var22.RF));
                  var5.closeElement(true);
               }
            }

            if (!var9.isEmpty()) {
               var5.appendLine();

               for (Xa.eo var23 : var9) {
                  var5.openElement("item");
                  var5.append(XmlBuilder.escapeTextData(var23.q));
                  var5.closeElement(true);
               }
            }

            if (!var10.isEmpty()) {
               var5.appendLine();

               for (Xa.ej var24 : var10) {
                  var5.openElement("item");
                  var5.append(XmlBuilder.escapeTextData(var24.q));
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

   private void q(String var1, ta var2, Map var3, List var4, List var5, List var6, List var7) {
      if (!var2.RF()) {
         throw new RuntimeException();
      } else {
         for (oI var9 : var2.gP) {
            switch (var9.q) {
               case 16777216:
                  String var11 = oI.q(var9.RF.xK);
                  var3.put("format", var11);
                  break;
               case 16777220:
               case 16777221:
               case 16777222:
               case 16777223:
               case 16777224:
               case 16777225:
                  if ("plurals".equals(var1)) {
                     var5.add(new Xa.nI(oI.RF(var9.q), this.Dw.q(var9.RF)));
                     break;
                  }
               case 16777217:
               case 16777218:
               case 16777219:
               default:
                  if (Strings.contains((String)var3.get("format"), "enum", "flags") || var1.equals("style")) {
                     String var10 = this.xK(var9.q);
                     var4.add(new Xa.CU(var10, this.Dw.q(var9.RF)));
                  } else if (var1.equals("array")) {
                     var6.add(new Xa.eo(this.Dw.q(var9.RF)));
                  } else {
                     var7.add(new Xa.ej(this.Dw.q(var9.RF)));
                  }
            }
         }
      }
   }

   private String q(String var1, String var2, String var3, String var4) {
      if (this.oW <= 0) {
         return null;
      } else {
         String[] var5 = var3.split("/");
         String var6 = var1 + (var2.length() == 0 ? "" : "-" + var2);
         String var7 = IO.getExtension(var3);
         if (this.oW <= 2 || Strings.isBlank(var4)) {
            return "res/" + var6 + "/" + var5[var5.length - 1];
         } else {
            return this.oW >= 3 ? "res/" + var6 + "/" + var4 + var7 : null;
         }
      }
   }

   private XmlBuilder xK() {
      XmlBuilder var1 = new XmlBuilder(4);
      var1.appendStandardHeader();
      return var1;
   }

   private String RF(int var1) {
      int var2 = var1 >>> 24;
      int var3 = var1 >>> 16 & 0xFF;
      return (String)((List)this.xK.Dw.get(var2)).get(var3 - 1);
   }

   private String xK(int var1) {
      return this.Dw.xK(var1);
   }

   private List q(int var1, int var2, String var3) {
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

         for (int var8 : this.xK.oW.keySet()) {
            if ((var8 & var5) == var4) {
               tl var9 = (tl)this.xK.oW.get(var8);
               if (var9 != null) {
                  ta var10;
                  if (var3 == null && !var9.oW.isEmpty()) {
                     var10 = (ta)var9.oW.values().iterator().next();
                  } else {
                     var10 = (ta)var9.oW.get(var3);
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

   private static class CU {
      String q;
      String RF;

      public CU(String var1, String var2) {
         this.q = var1;
         this.RF = var2;
      }
   }

   private static class ej {
      String q;

      public ej(String var1) {
         this.q = var1;
      }
   }

   private static class eo {
      String q;

      public eo(String var1) {
         this.q = var1;
      }
   }

   private static class nI {
      String q;
      String RF;

      public nI(String var1, String var2) {
         this.q = var1;
         this.RF = var2;
      }
   }
}
