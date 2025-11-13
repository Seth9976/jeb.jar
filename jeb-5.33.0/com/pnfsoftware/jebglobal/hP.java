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

public class hP extends tH {
   EX E;
   boolean sY;
   com.pnfsoftware.jeb.corei.parsers.apk.decoder.uX ys;
   Vb ld;
   private int[] ED;
   List gp = new ArrayList();
   int oT = 0;
   String fI;
   Deque WR = new ArrayDeque();
   Deque NS = new ArrayDeque();
   String vP;
   XmlBuilder xC = new XmlBuilder(4);
   private static int Sc = 20;
   private Map ah = new HashMap();

   public hP(EX var1, com.pnfsoftware.jeb.corei.parsers.apk.decoder.uX var2, String var3, boolean var4) throws IOException {
      super(var1, 0);
      this.E = var1;
      if (this.kS == 0) {
         this.sY = true;
      } else if (this.kS != 3) {
         if (this.kS == 16188) {
            throw new IOException("XML entry appears to be non-encoded");
         }

         if (!var4) {
            throw new IOException(Strings.ff("Illegal XML header type: 0x%x", this.kS));
         }

         this.pC(S.L("Illegal XML header type: 0x%X"), this.kS);
      }

      this.ys = var2 != null ? var2 : new com.pnfsoftware.jeb.corei.parsers.apk.decoder.uX();
      this.fI = var3;
      this.pC(var1);
      this.ld = new Vb(var1, "XML String Pool");
   }

   public void pC() throws IOException {
      this.xC.appendStandardHeader();

      while (this.E(this.E) > 0) {
         tH var1 = new tH(this.E, 0);
         if (var1.kS == 384) {
            int var2 = var1.E(this.E);
            int var3 = var2 / 4;
            this.ED = bM.pC(this.E, var3);
         } else if (!FG.E.contains(var1.kS)) {
            Object[] var10000 = new Object[0];
         } else {
            FG var13 = new FG(var1, this.E, false);
            if (var13.ys != -1) {
               pC.fine(S.L("Found a non -1 comment_index field in encoded XML: %d (chunk: %s)"), var13.ys, var13);
            }

            switch (var13.kS) {
               case 256:
               case 257:
                  int var16 = this.E.readInt();
                  int var18 = this.E.readInt();
                  this.pC(var13.kS == 256, var16, var18);
                  break;
               case 258:
               case 259:
                  if (this.gp.isEmpty() && this.oT == 0) {
                     this.gp.add(new hP.Av(0, "android", "http://schemas.android.com/apk/res/android"));
                  }

                  ArrayList var15 = null;
                  int var17 = this.E.readInt();
                  int var5 = this.E.readInt();
                  if (var13.kS == 258) {
                     var15 = new ArrayList();
                     int var6 = this.E.readUnsignedShort();
                     int var7 = this.E.readUnsignedShort();
                     int var8 = this.E.readUnsignedShort();
                     this.E.readUnsignedShort();
                     this.E.readUnsignedShort();
                     this.E.readUnsignedShort();
                     if (var8 > 0) {
                        var13.pC(this.E, var13.wS + var6);

                        for (int var9 = 0; var9 < var8; var9++) {
                           int var10 = this.E.pC();
                           lV var11 = new lV(this.E);
                           var15.add(var11);
                           if (var7 > 0) {
                              int var12 = this.E.pC() - var10;
                              if (var12 < var7) {
                                 this.E.skipBytes(var7 - var12);
                              }
                           }
                        }
                     }
                  }

                  this.pC(var15 != null, var17, var5, var15);
                  break;
               case 260:
                  int var14 = this.E.readInt();
                  fp var4 = new fp(this.E);
                  this.pC(var14, var4);
                  break;
               default:
                  throw new RuntimeException(Strings.ff("Not an XML chunk: %Xh", var13.kS));
            }
         }

         var1.kS(this.E);
      }
   }

   private void pC(String var1, Object... var2) {
      this.E.pC(var1, var2);
   }

   public String A() {
      return this.fI;
   }

   void pC(boolean var1, int var2, int var3) {
      this.vP = null;
      String var4 = this.pC(var2);
      var4 = XmlBuilder.escapeAll(var4).toString();
      if (var4.length() >= Sc) {
         String var5 = (String)this.ah.get(var4);
         if (var5 == null) {
            var5 = "nspfx" + this.ah.size();
            this.ah.put(var4, var5);
         }

         var4 = var5;
      }

      String var8 = this.pC(var3);
      hP.Av var6 = new hP.Av(this.oT, var4, var8);
      if (var1) {
         this.gp.add(var6);
      } else {
         this.gp.remove(var6);
      }
   }

   private String UT() {
      if (!this.gp.isEmpty()) {
         hP.Av var1 = (hP.Av)this.gp.get(this.gp.size() - 1);
         return var1.A + ":";
      } else {
         return "";
      }
   }

   String kS() {
      StringBuilder var1 = new StringBuilder();

      for (hP.Av var3 : this.gp) {
         if (var3.pC == this.oT) {
            if (var1.length() > 0) {
               var1.append(' ');
            }

            var1.append(var3.toString());
         }
      }

      return var1.toString();
   }

   String pC(String var1) {
      if (var1 != null) {
         for (int var2 = this.gp.size() - 1; var2 >= 0; var2--) {
            hP.Av var3 = (hP.Av)this.gp.get(var2);
            if (var1.equals(var3.A())) {
               return var3.pC();
            }
         }
      }

      return null;
   }

   void pC(boolean var1, int var2, int var3, List var4) {
      if (!var1) {
         if (this.oT == 0) {
            return;
         }

         this.oT--;
         String var5 = this.pC(var2, var3);
         String var6 = var2 != -1 ? var5 : this.UT() + var5;
         String var7 = (String)this.WR.pop();
         String var8 = (String)this.NS.pop();
         if (!var7.equals(var5)) {
            if (var8.equals(var5)) {
               var5 = var7;
            } else if (!var5.startsWith(":") && var7.endsWith(":" + var5)) {
               var5 = var7;
            } else if (var5.isEmpty()) {
               String var9 = Strings.ff(S.L("Unexpected closing element: actual=%s, expected=%s"), var5, var7);
               this.pC(var9);
               var5 = var7;
            } else {
               String var18 = Strings.ff("Unexpected closing element: actual=%s, expected=%s", var5, var7);
               this.pC(var18);
            }
         }

         String var19 = this.WR.size() + "-" + var6;
         this.xC.unindent();
         if (this.vP != null && this.vP.equals(var19)) {
            this.xC.removeLastLine();
            this.xC.removeLastChar();
            this.xC.appendLine("/>");
         } else {
            this.xC.appendLine("</%s>", var5);
         }

         this.vP = null;
      } else {
         String var14 = this.pC(var2, var3);
         String var15 = var2 != -1 ? var14 : this.UT() + var14;
         this.vP = this.WR.size() + "-" + var15;
         this.WR.push(var14);
         this.NS.push(var15);
         String var16 = this.kS();
         LinkedHashMap var17 = new LinkedHashMap();

         for (lV var10 : var4) {
            this.pC(var14, var10, var17);
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

         this.xC.append("<%s", var14);
         if (var16.length() > 0) {
            this.xC.append(" %s", var16);
         }

         if (var21.length() > 0) {
            this.xC.append(" %s", var21);
         }

         this.xC.appendLine(">");
         this.xC.indent();
         this.oT++;
      }
   }

   String pC(int var1, int var2) {
      StringBuilder var3 = new StringBuilder();
      if (var1 != -1) {
         String var4 = this.pC(var1);
         if (!Strings.isBlank(var4)) {
            String var5 = this.pC(var4);
            var3.append(var5);
            var3.append(":");
         }
      }

      String var6 = this.pC(var2, false, 2);
      var6 = XmlBuilder.escapeAll(var6).toString();
      var3.append(var6);
      return var3.toString();
   }

   boolean pC(String var1, lV var2, Map var3) {
      String var4 = "";
      if (var2.pC != -1) {
         var4 = this.pC(var2.pC);
         if (!Strings.isBlank(var4)) {
            String var5 = this.pC(var4);
            if (var5 != null) {
               var4 = var5;
            }
         }
      }

      String var9 = this.pC(var2.A, true, 1);
      if (var2.pC != -1 || var9.isEmpty() || !Character.isWhitespace(var9.charAt(0)) || var9.indexOf(60) < 0 && var9.indexOf(62) < 0) {
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
         if (var2.kS != -1) {
            var11 = this.pC(var2.kS);
         } else {
            var11 = this.ys.pC(0, var2.wS, var9, this.WR);
            if (var2.wS.A == 1 && this.fI != null && var11.startsWith("@" + this.fI + ":")) {
               var11 = "@" + var11.substring(this.fI.length() + 2);
            }
         }

         if (this.fI == null && "manifest".equals(var1) && "package".equals(var9)) {
            this.fI = var11;
         }

         String var12 = "";
         if (!Strings.isBlank(var4)) {
            var12 = var4 + ":";
         }

         var12 = var12 + var9;
         if (var3.containsKey(var12)) {
            this.pC(S.L("Duplicate attribute for tag '%s' in encoded AXML file: \"%s\""), var1, Formatter.escapeString(var12));
            return false;
         } else {
            var3.put(var12, XmlBuilder.escapeAttributeData(var11));
            return true;
         }
      } else {
         this.pC(S.L("Removing bad attribute tag '%s' in encoded AXML file: \"%s\""), var1, Formatter.escapeString(var9));
         return false;
      }
   }

   void pC(int var1, fp var2) {
      this.vP = null;
      String var3 = this.pC(var1);
      this.xC.append(XmlBuilder.escapeTextData(var3));
   }

   String pC(int var1) {
      return this.pC(var1, false, 0);
   }

   String pC(int var1, boolean var2, int var3) {
      if (var1 < 0) {
         return "";
      } else {
         String var4 = this.ld.pC(var1, null);
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

         if (this.ED != null && var1 >= 0 && var1 < this.ED.length) {
            int var11 = this.ED[var1];
            com.pnfsoftware.jeb.corei.parsers.apk.decoder.p var6 = this.ys.A(var11);
            if (var6 == null || var6.A()) {
               return var4 != null ? var4 : Strings.ff("ResId_0x%08x", var11);
            }

            String var7 = var6.pC();
            if (var7 == null) {
               if (var4 != null) {
                  return var4;
               }

               return Strings.ff("ResId_0x%08x", var11);
            }

            if (var3 != 0) {
               String[] var8 = Eq.pC(var7);
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

   public String wS() {
      return this.xC.toString();
   }

   static class Av {
      int pC;
      String A;
      String kS;

      public Av(int var1, String var2, String var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      public String pC() {
         return this.A;
      }

      public String A() {
         return this.kS;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.pC;
         var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
         return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
            hP.Av var2 = (hP.Av)var1;
            if (this.pC != var2.pC) {
               return false;
            } else {
               if (this.A == null) {
                  if (var2.A != null) {
                     return false;
                  }
               } else if (!this.A.equals(var2.A)) {
                  return false;
               }

               if (this.kS == null) {
                  if (var2.kS != null) {
                     return false;
                  }
               } else if (!this.kS.equals(var2.kS)) {
                  return false;
               }

               return true;
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("xmlns:%s=\"%s\"", this.A, this.kS);
      }
   }
}
