package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.XmlBuilder;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class pN extends jA implements zb {
   uL oW;
   boolean gO;
   boolean nf;
   byte[] gP;
   com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY za;
   AX lm;
   List zz = new ArrayList();
   Of JY;
   List HF = new ArrayList();
   int LK = 0;
   String io;
   Deque qa = new ArrayDeque();
   String Hk;
   XmlBuilder Me = new XmlBuilder(4);
   public static final String PV = "http://schemas.android.com/apk/res/android";

   public pN(uL var1, com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY var2, String var3, boolean var4) throws IOException {
      this(var1, var2, var3, var4, false);
   }

   public pN(uL var1, com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY var2, String var3, boolean var4, boolean var5) throws IOException {
      super(var1, 0);
      this.oW = var1;
      this.nf = var5;
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

      this.za = var2 != null ? var2 : new com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY();
      this.io = var3;
      if (var5) {
         this.gP = this.Dw(var1);
      } else {
         this.xK(var1);
      }

      this.lm = new AX(var1, "XML String Pool", var5);
      this.Me.appendStandardHeader();

      while (this.gP(var1) > 0) {
         jA var6 = new jA(var1, 0);
         Object[] var10000 = new Object[0];
         if (var6.xK == 384) {
            Of var7 = new Of(var6, var1, var5);
            if (this.JY != null) {
               throw new RuntimeException("Multiple resource maps were detected!");
            }

            this.JY = var7;
         } else {
            Object var8 = switch (var6.xK) {
               case 256 -> new Qp(var6, var1, var5);
               case 257 -> new WG(var6, var1, var5);
               case 258 -> new uD(var6, var1, var5);
               case 259 -> new BO(var6, var1, var5);
               case 260 -> new mP(var6, var1, var5);
               default -> throw new RuntimeException(Strings.ff("Not an XML chunk: %Xh", var6.xK));
            };
            if (((Hy)var8).nf != -1) {
               q.fine(S.L("Found a non -1 comment_index field in encoded XML: %d (chunk: %s)"), ((Hy)var8).nf, var8);
            }

            this.zz.add(var8);
         }

         var6.Uv(var1);
      }
   }

   public int q(String var1, String var2, boolean var3, int var4, Integer var5) {
      int var6 = var3 ? -1 : 0;

      for (Hy var8 : this.zz) {
         if (var8 instanceof uD var9) {
            String var10 = this.q(var9.zz);
            if (var10.equals(var1)) {
               Integer var11 = null;
               int var12 = -1;
               int var13 = 0;

               for (sy var15 : var9.PV) {
                  Object[] var10000 = new Object[]{this.q(var15.RF), var15};
                  if (var4 != 0 && this.JY != null) {
                     int var16 = this.JY.q(var15.RF);
                     if (var16 < var4) {
                        var11 = var13 + 1;
                     }
                  }

                  if (var12 == -1 && var15.q != -1 && "http://schemas.android.com/apk/res/android".equals(this.q(var15.q))) {
                     var12 = var15.q;
                  }

                  var10 = this.q(var15.RF, true);
                  int var20 = var10.lastIndexOf(58);
                  if (var20 >= 0) {
                     var10 = var10.substring(var20 + 1);
                  }

                  if (var10.equals(var2)) {
                     if (var15.Dw.RF != 18) {
                        return -1;
                     }

                     if (var15.Dw.xK == var6) {
                        return 1;
                     }

                     var15.Dw.xK = var6;
                     return 2;
                  }

                  var13++;
               }

               int var18;
               if (var5 != null) {
                  var18 = var5;
                  if (var18 < 0) {
                     var18 += var9.PV.size() + 1;
                  }
               } else if (var11 != null) {
                  var18 = var11;
               } else {
                  var18 = var9.PV.size();
               }

               int var19;
               if (var12 == -1) {
                  var19 = this.lm.q("http://schemas.android.com/apk/res/android:" + var2, true);
               } else {
                  var19 = this.lm.q(var2, true);
               }

               var9.PV.add(var18, new sy(var12, var19, -1, new HK(8, 18, var6)));
               if (var4 != 0) {
                  if (this.JY == null) {
                     this.JY = new Of();
                  }

                  this.JY.q(var19, var4);
               }

               return 3;
            }
         }
      }

      return 0;
   }

   @Override
   public void q(pK var1) {
      super.q(var1);
      var1.q.writeBytes(this.gP);
      this.lm.q(var1);
      if (this.JY != null) {
         this.JY.q(var1);
      }

      for (Hy var3 : this.zz) {
         var3.q(var1);
      }

      this.RF(var1);
   }

   private void RF(String var1, Object... var2) {
      this.oW.q(var1, var2);
   }

   public String q() {
      return this.io;
   }

   void q(boolean var1, int var2, int var3) {
      this.Hk = null;
      String var4 = this.q(var2);
      String var5 = this.q(var3);
      pN.eo var6 = new pN.eo(this.LK, var4, var5);
      if (var1) {
         this.HF.add(var6);
      } else {
         this.HF.remove(var6);
      }
   }

   String RF() {
      StringBuilder var1 = new StringBuilder();

      for (pN.eo var3 : this.HF) {
         if (var3.q == this.LK) {
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
         for (int var2 = this.HF.size() - 1; var2 >= 0; var2--) {
            pN.eo var3 = (pN.eo)this.HF.get(var2);
            if (var1.equals(var3.xK())) {
               return var3.RF();
            }
         }
      }

      return null;
   }

   void q(boolean var1, int var2, int var3, List var4) {
      if (!var1) {
         this.LK--;
         String var5 = this.q(var2, var3);
         String var6 = (String)this.qa.pop();
         if (!var6.equals(var5)) {
            throw new IllegalStateException(Strings.ff("Unexpected closing element: actual=%s, expected=%s", var5, var6));
         }

         String var7 = this.qa.size() + "-" + var5;
         this.Me.unindent();
         if (this.Hk != null && this.Hk.equals(var7)) {
            this.Me.removeLastLine();
            this.Me.removeLastChar();
            this.Me.appendLine("/>");
         } else {
            this.Me.appendLine("</%s>", this.q(var2, var3));
         }

         this.Hk = null;
      } else {
         String var13 = this.q(var2, var3);
         this.Hk = this.qa.size() + "-" + var13;
         this.qa.push(var13);
         String var14 = this.RF();
         LinkedHashMap var15 = new LinkedHashMap();

         for (sy var9 : var4) {
            this.q(var13, var9, var15);
         }

         StringBuilder var16 = new StringBuilder();
         int var17 = 0;

         for (String var11 : var15.keySet()) {
            CharSequence var12 = (CharSequence)var15.get(var11);
            if (var17 >= 1) {
               var16.append(" ");
            }

            var16.append(var11);
            var16.append("=\"");
            var16.append(var12);
            var16.append("\"");
            var17++;
         }

         this.Me.append("<%s", var13);
         if (var14.length() > 0) {
            this.Me.append(" %s", var14);
         }

         if (var16.length() > 0) {
            this.Me.append(" %s", var16);
         }

         this.Me.appendLine(">");
         this.Me.indent();
         this.LK++;
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

      var3.append(this.q(var2));
      return var3.toString();
   }

   boolean q(String var1, sy var2, Map var3) {
      String var4 = "";
      if (var2.q != -1) {
         var4 = this.q(var2.q);
         if (!Strings.isBlank(var4)) {
            var4 = this.q(var4);
         }
      }

      String var5;
      if (var2.xK != -1) {
         var5 = this.q(var2.xK);
      } else {
         var5 = this.za.q(var2.Dw);
         if (var2.Dw.RF == 1 && this.io != null && var5.startsWith("@" + this.io + ":")) {
            var5 = "@" + var5.substring(this.io.length() + 2);
         }
      }

      String var6 = this.q(var2.RF, true);
      int var7 = var6.indexOf(58);
      if (var7 >= 0) {
         var4 = var6.substring(0, var7);
         var6 = var6.substring(var7 + 1);
      }

      if (this.io == null && "manifest".equals(var1) && "package".equals(var6)) {
         this.io = var5;
      }

      String var8 = "";
      if (!Strings.isBlank(var4)) {
         var8 = var4 + ":";
      }

      var8 = var8 + var6;
      if (var3.containsKey(var8)) {
         this.RF(S.L("Duplicate attribute for tag '%s' in encoded AXML file: \"%s\""), var1, Formatter.escapeString(var8));
         return false;
      } else {
         var3.put(var8, XmlBuilder.escapeAttributeData(var5));
         return true;
      }
   }

   void q(int var1, HK var2) {
      this.Hk = null;
      String var3 = this.q(var1);
      this.Me.append(XmlBuilder.escapeTextData(var3));
   }

   String q(int var1) {
      return this.q(var1, false);
   }

   String q(int var1, boolean var2) {
      if (var1 < 0) {
         return "";
      } else {
         String var3 = this.lm.q(var1, null);
         if (var3 != null) {
            String var4 = Strings.trimWhitespaces(var3);
            if (var4.length() > 0 && !var4.equals(":")) {
               return var3;
            }
         }

         if (this.JY != null && var1 < this.JY.q()) {
            int var9 = this.JY.q(var1);
            String var5 = this.za.xK(var9);
            if (var5 == null) {
               return Strings.ff("ResId_0x%08x", var9);
            }

            if (var2) {
               String[] var6 = mw.q(var5);
               String var7 = var6[0];
               String var8 = var6[2];
               if (var7 == null) {
                  return var8;
               }

               return var7 + ":" + var8;
            }

            var3 = var5;
         }

         return var3 == null ? "" : var3;
      }
   }

   public String xK() {
      return this.Me.toString();
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
            pN.eo var2 = (pN.eo)var1;
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
