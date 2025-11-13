package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class bje extends CodeDocumentPart {
   boolean q = false;
   private final int RF;
   private Map xK = new HashMap();

   public bje(int var1, String var2, boolean var3) {
      super(var1);
      this.q = var3;
      this.setPaddingString(var2);
      this.RF = this.registerKeyword("->");
   }

   @Override
   public void eol() {
      ICodeCoordinates var1 = this.getCurrentLineCoordinates();
      super.eol(var1);
   }

   void q() {
      this.appendKeyword(this.RF);
   }

   void q(bja var1) {
      String var2 = var1.q();
      this.appendAndRecord(var2, ItemClassIdentifiers.DIRECTIVE);
   }

   void q(bjf var1) {
      String var2 = var1.q();
      this.appendAndRecord(var2, ItemClassIdentifiers.KEYWORD);
   }

   void q(biz var1) {
      String var2 = var1.q();
      this.appendAndRecord(var2, ItemClassIdentifiers.DIRECTIVE);
   }

   void q(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      bjs var3 = (bjs)var1.JY().q(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_STRING_%d", var2));
      } else {
         String var4 = Strings.safe(var3.getValue());
         boolean var6 = !this.q && var4.length() <= 500;
         int var7 = var1.RF(var3);
         String var5;
         if (var7 == 1 && var6) {
            var5 = "\"" + Formatter.escapeString(var4, -1, true, null) + "\"";
         } else if (var7 == 2) {
            var5 = "\"" + Formatter.escapeAllCharacters(var4) + "\"";
         } else {
            var5 = "\"" + Formatter.escapeString(var4, var6) + "\"";
         }

         this.appendAndRecord(var5, ItemClassIdentifiers.STRING, var1.q(var3));
      }
   }

   void RF(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      bjp var3 = var1.gO(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_METHOD_%d", var2));
      } else {
         this.Uv(var1, var3.getClassTypeIndex());
         this.q();
         this.appendAndRecord(var3.getName(true), ItemClassIdentifiers.METHOD_NAME, var1.q(var3));
         this.q(var1, var3.Uv());
      }
   }

   void xK(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      bjo var3 = var1.oW(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_FIELD_%d", var2));
      } else {
         this.Uv(var1, var3.getClassTypeIndex());
         this.q();
         this.appendAndRecord(var3.getName(true), ItemClassIdentifiers.FIELD_NAME, var1.q(var3));
         this.append(":");
         this.Uv(var1, var3.getFieldTypeIndex());
      }
   }

   void Dw(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      bjr var3 = var1.Uv(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_PROTO_%d", var2));
      } else {
         this.q(var1, var3);
      }
   }

   void q(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, bjr var2) {
      this.q(var1, var2, 20);
   }

   private void q(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, bjr var2, int var3) {
      this.append("(");
      if (!this.q) {
         int[] var4 = var2.getParameterTypeIndexes();
         int var5 = -1;
         if (var3 >= 0) {
            if (var3 < 2) {
               var3 = 2;
            }

            if (var4.length > var3) {
               var5 = var3 - 2;
            }
         }

         for (int var6 = 0; var6 < var4.length; var6++) {
            int var7 = var4[var6];
            if (var6 >= 1) {
               this.appendParameterSeparator();
            }

            if (var6 == var5) {
               this.appendComment(Strings.ff("/* truncated %d more parameters */ ", var4.length - var3));
               var6 = var4.length - 2;
            } else {
               this.Uv(var1, var7);
            }
         }
      } else {
         for (int var11 : var2.getParameterTypeIndexes()) {
            this.Uv(var1, var11);
         }
      }

      this.append(")");
      this.Uv(var1, var2.getReturnTypeIndex());
   }

   void Uv(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      this.q(var1, var2, false);
   }

   void q(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, boolean var3) {
      bjt var4 = var1.Dw(var2);
      if (var4 == null) {
         this.append(Strings.ff("INVALID_TYPE_%d", var2));
      } else {
         String var5 = var4.getNonArrayClass();
         if (var5 != null) {
            bjt var6 = (bjt)var1.io().xK(var5);

            for (int var7 = 0; var7 < var4.getDimensions(); var7++) {
               this.append("[");
            }

            Object var12 = var6.xK();
            if (var12 == null) {
               var12 = var6;
            }

            String var8;
            ItemClassIdentifiers var9;
            long var10;
            if (var12 instanceof bjn) {
               var8 = (String)this.xK.get(var12);
               if (var8 == null) {
                  var8 = ((bjn)var12).getSignature(true);
                  this.xK.put((bjn)var12, var8);
               }

               var9 = ItemClassIdentifiers.CLASS_NAME;
               var10 = var1.q((bjn)var12);
            } else {
               var8 = var5;
               var9 = ItemClassIdentifiers.EXTERNAL_CLASS_NAME;
               var10 = var1.q(var4);
            }

            if (!this.q) {
               var8 = var8.substring(1, var8.length() - 1);
               var8 = var8.substring(var8.lastIndexOf(47) + 1);
            }

            this.appendAndRecord(var8, var9, var10, var3 ? 1 : 0);
         } else {
            this.append(var4.getSignature(true));
         }
      }
   }
}
