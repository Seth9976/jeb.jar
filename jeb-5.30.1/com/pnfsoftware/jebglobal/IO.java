package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.XmlBuilder;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IO extends jA implements zb {
   uL oW;
   boolean gO;
   com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY nf;
   AX gP;
   private int[] qa;
   List za = new ArrayList();
   int lm = 0;
   String zz;
   Deque JY = new ArrayDeque();
   Deque HF = new ArrayDeque();
   String LK;
   XmlBuilder io = new XmlBuilder(4);
   private static int Hk = 20;
   private Map Me = new HashMap();

   public IO(uL var1, com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY var2, String var3, boolean var4) throws IOException {
      super(var1, 0);
      this.oW = var1;
      if (this.xK == 0) {
         this.gO = true;
      } else if (this.xK != 3) {
         if (this.xK == 16188) {
            throw new IOException("XML entry appears to be non-encoded");
         }

         if (!var4) {
            throw new IOException(Strings.ff("Illegal XML header type: 0x%x", this.xK));
         }

         this.RF(S.L("Illegal XML header type: 0x%X"), this.xK);
      }

      this.nf = var2 != null ? var2 : new com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY();
      this.zz = var3;
      this.xK(var1);
      this.gP = new AX(var1, "XML String Pool");
   }

   public void q() throws IOException {
      this.io.appendStandardHeader();

      while (this.gP(this.oW) > 0) {
         jA var1 = new jA(this.oW, 0);
         if (var1.xK == 384) {
            int var2 = var1.gP(this.oW);
            int var3 = var2 / 4;
            this.qa = zR.q(this.oW, var3);
         } else if (!Hy.oW.contains(var1.xK)) {
            Object[] var10000 = new Object[0];
         } else {
            Hy var13 = new Hy(var1, this.oW, false);
            if (var13.nf != -1) {
               q.fine(S.L("Found a non -1 comment_index field in encoded XML: %d (chunk: %s)"), var13.nf, var13);
            }

            switch (var13.xK) {
               case 256:
               case 257:
                  int var16 = this.oW.readInt();
                  int var18 = this.oW.readInt();
                  this.q(var13.xK == 256, var16, var18);
                  break;
               case 258:
               case 259:
                  if (this.za.isEmpty() && this.lm == 0) {
                     this.za.add(new IO.eo(0, "android", "http://schemas.android.com/apk/res/android"));
                  }

                  ArrayList var15 = null;
                  int var17 = this.oW.readInt();
                  int var5 = this.oW.readInt();
                  if (var13.xK == 258) {
                     var15 = new ArrayList();
                     int var6 = this.oW.readUnsignedShort();
                     int var7 = this.oW.readUnsignedShort();
                     int var8 = this.oW.readUnsignedShort();
                     this.oW.readUnsignedShort();
                     this.oW.readUnsignedShort();
                     this.oW.readUnsignedShort();
                     if (var8 > 0) {
                        var13.q(this.oW, var13.Dw + var6);

                        for (int var9 = 0; var9 < var8; var9++) {
                           int var10 = this.oW.q();
                           sy var11 = new sy(this.oW);
                           var15.add(var11);
                           if (var7 > 0) {
                              int var12 = this.oW.q() - var10;
                              if (var12 < var7) {
                                 this.oW.skipBytes(var7 - var12);
                              }
                           }
                        }
                     }
                  }

                  this.q(var15 != null, var17, var5, var15);
                  break;
               case 260:
                  int var14 = this.oW.readInt();
                  HK var4 = new HK(this.oW);
                  this.q(var14, var4);
                  break;
               default:
                  throw new RuntimeException(Strings.ff("Not an XML chunk: %Xh", var13.xK));
            }
         }

         var1.Uv(this.oW);
      }
   }

   private void RF(String var1, Object... var2) {
      this.oW.q(var1, var2);
   }

   public String RF() {
      return this.zz;
   }

   void q(boolean var1, int var2, int var3) {
      this.LK = null;
      String var4 = this.q(var2);
      var4 = XmlBuilder.escapeAll(var4).toString();
      if (var4.length() >= Hk) {
         String var5 = (String)this.Me.get(var4);
         if (var5 == null) {
            var5 = "nspfx" + this.Me.size();
            this.Me.put(var4, var5);
         }

         var4 = var5;
      }

      String var8 = this.q(var3);
      IO.eo var6 = new IO.eo(this.lm, var4, var8);
      if (var1) {
         this.za.add(var6);
      } else {
         this.za.remove(var6);
      }
   }

   private String Uv() {
      if (!this.za.isEmpty()) {
         IO.eo var1 = (IO.eo)this.za.get(this.za.size() - 1);
         return var1.RF + ":";
      } else {
         return "";
      }
   }

   String xK() {
      StringBuilder var1 = new StringBuilder();

      for (IO.eo var3 : this.za) {
         if (var3.q == this.lm) {
            if (var1.length() > 0) {
               var1.append(' ');
            }

            var1.append(var3.toString());
         }
      }

      return var1.toString();
   }

   String q(String var1) {
      if (var1 != null) {
         for (int var2 = this.za.size() - 1; var2 >= 0; var2--) {
            IO.eo var3 = (IO.eo)this.za.get(var2);
            if (var1.equals(var3.xK())) {
               return var3.RF();
            }
         }
      }

      return null;
   }

   void q(boolean var1, int var2, int var3, List var4) {
      if (!var1) {
         if (this.lm == 0) {
            return;
         }

         this.lm--;
         String var5 = this.q(var2, var3);
         String var6 = var2 != -1 ? var5 : this.Uv() + var5;
         String var7 = (String)this.JY.pop();
         String var8 = (String)this.HF.pop();
         if (!var7.equals(var5)) {
            if (var8.equals(var5)) {
               var5 = var7;
            } else if (!var5.startsWith(":") && var7.endsWith(":" + var5)) {
               var5 = var7;
            } else if (var5.isEmpty()) {
               String var9 = Strings.ff(S.L("Unexpected closing element: actual=%s, expected=%s"), var5, var7);
               this.RF(var9);
               var5 = var7;
            } else {
               String var18 = Strings.ff("Unexpected closing element: actual=%s, expected=%s", var5, var7);
               this.RF(var18);
            }
         }

         String var19 = this.JY.size() + "-" + var6;
         this.io.unindent();
         if (this.LK != null && this.LK.equals(var19)) {
            this.io.removeLastLine();
            this.io.removeLastChar();
            this.io.appendLine("/>");
         } else {
            this.io.appendLine("</%s>", var5);
         }

         this.LK = null;
      } else {
         String var14 = this.q(var2, var3);
         String var15 = var2 != -1 ? var14 : this.Uv() + var14;
         this.LK = this.JY.size() + "-" + var15;
         this.JY.push(var14);
         this.HF.push(var15);
         String var16 = this.xK();
         LinkedHashMap var17 = new LinkedHashMap();

         for (sy var10 : var4) {
            this.q(var14, var10, var17);
         }

         StringBuilder var21 = new StringBuilder();
         int var22 = 0;

         for (String var12 : var17.keySet()) {
            CharSequence var13 = (CharSequence)var17.get(var12);
            if (var22 >= 1) {
               var21.append(" ");
            }

            var21.append(var12);
            var21.append("=\"");
            var21.append(var13);
            var21.append("\"");
            var22++;
         }

         this.io.append("<%s", var14);
         if (var16.length() > 0) {
            this.io.append(" %s", var16);
         }

         if (var21.length() > 0) {
            this.io.append(" %s", var21);
         }

         this.io.appendLine(">");
         this.io.indent();
         this.lm++;
      }
   }

   String q(int var1, int var2) {
      StringBuilder var3 = new StringBuilder();
      if (var1 != -1) {
         String var4 = this.q(var1);
         if (!Strings.isBlank(var4)) {
            String var5 = this.q(var4);
            var3.append(var5);
            var3.append(":");
         }
      }

      String var6 = this.q(var2, false, 2);
      var6 = XmlBuilder.escapeAll(var6).toString();
      var3.append(var6);
      return var3.toString();
   }

   boolean q(String var1, sy var2, Map var3) {
      String var4 = "";
      if (var2.q != -1) {
         var4 = this.q(var2.q);
         if (!Strings.isBlank(var4)) {
            String var5 = this.q(var4);
            if (var5 != null) {
               var4 = var5;
            }
         }
      }

      String var9 = this.q(var2.RF, true, 1);
      if (var2.q != -1 || var9.isEmpty() || !Character.isWhitespace(var9.charAt(0)) || var9.indexOf(60) < 0 && var9.indexOf(62) < 0) {
         if (Strings.hasBlank(var9)) {
            StringBuilder var6 = new StringBuilder();

            for (int var7 = 0; var7 < var9.length(); var7++) {
               char var8 = var9.charAt(var7);
               if (Character.isWhitespace(var8)) {
                  Strings.ff(var6, "_x%04X", Integer.valueOf(var8));
               } else {
                  var6.append(var8);
               }
            }

            var9 = var6.toString();
         }

         int var10 = var9.indexOf(58);
         if (var10 >= 0) {
            if (var4.length() == 0) {
               var4 = var9.substring(0, var10);
            }

            var9 = var9.substring(var10 + 1);
         }

         String var11;
         if (var2.xK != -1) {
            var11 = this.q(var2.xK);
         } else {
            var11 = this.nf.q(0, var2.Dw, var9, this.JY);
            if (var2.Dw.RF == 1 && this.zz != null && var11.startsWith("@" + this.zz + ":")) {
               var11 = "@" + var11.substring(this.zz.length() + 2);
            }
         }

         if (this.zz == null && "manifest".equals(var1) && "package".equals(var9)) {
            this.zz = var11;
         }

         String var12 = "";
         if (!Strings.isBlank(var4)) {
            var12 = var4 + ":";
         }

         var12 = var12 + var9;
         if (var3.containsKey(var12)) {
            this.RF(S.L("Duplicate attribute for tag '%s' in encoded AXML file: \"%s\""), var1, Formatter.escapeString(var12));
            return false;
         } else {
            var3.put(var12, XmlBuilder.escapeAttributeData(var11));
            return true;
         }
      } else {
         this.RF(S.L("Removing bad attribute tag '%s' in encoded AXML file: \"%s\""), var1, Formatter.escapeString(var9));
         return false;
      }
   }

   void q(int var1, HK var2) {
      this.LK = null;
      String var3 = this.q(var1);
      this.io.append(XmlBuilder.escapeTextData(var3));
   }

   String q(int var1) {
      return this.q(var1, false, 0);
   }

   String q(int var1, boolean var2, int var3) {
      if (var1 < 0) {
         return "";
      } else {
         String var4 = this.gP.q(var1, null);
         if (var4 != null) {
            String var5 = Strings.trimWhitespaces(var4);
            if (var5.length() != 0 && !var5.equals(":")) {
               if (!var2) {
                  return var4;
               }
            } else {
               var4 = null;
            }
         }

         if (this.qa != null && var1 >= 0 && var1 < this.qa.length) {
            int var11 = this.qa[var1];
            com.pnfsoftware.jeb.corei.parsers.apk.decoder.tl var6 = this.nf.RF(var11);
            if (var6 == null || var6.Dw()) {
               return var4 != null ? var4 : Strings.ff("ResId_0x%08x", var11);
            }

            String var7 = var6.RF();
            if (var7 == null) {
               if (var4 != null) {
                  return var4;
               }

               return Strings.ff("ResId_0x%08x", var11);
            }

            if (var3 != 0) {
               String[] var8 = mw.q(var7);
               String var9 = var8[0];
               String var10 = var8[2];
               if (var9 == null) {
                  return var10;
               }

               if (var3 == 1) {
                  return var9 + ":" + var10;
               }

               if (var3 == 2) {
                  return var10;
               }

               throw new RuntimeException("Unsupported clean-up style: " + var3);
            }

            var4 = var7;
         }

         return var4 == null ? "" : var4;
      }
   }

   public String Dw() {
      return this.io.toString();
   }

   static class eo {
      int q;
      String RF;
      String xK;

      public eo(int var1, String var2, String var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public int q() {
         return this.q;
      }

      public String RF() {
         return this.RF;
      }

      public String xK() {
         return this.xK;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.q;
         var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
         return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            IO.eo var2 = (IO.eo)var1;
            if (this.q != var2.q) {
               return false;
            } else {
               if (this.RF == null) {
                  if (var2.RF != null) {
                     return false;
                  }
               } else if (!this.RF.equals(var2.RF)) {
                  return false;
               }

               if (this.xK == null) {
                  if (var2.xK != null) {
                     return false;
                  }
               } else if (!this.xK.equals(var2.xK)) {
                  return false;
               }

               return true;
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("xmlns:%s=\"%s\"", this.RF, this.xK);
      }
   }
}
