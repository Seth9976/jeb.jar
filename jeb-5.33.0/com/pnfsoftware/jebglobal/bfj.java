package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class bfj extends CodeDocumentPart {
   boolean pC = false;
   private final int A;
   private Map kS = new HashMap();

   public bfj(int var1, String var2, boolean var3) {
      super(var1);
      this.pC = var3;
      this.setPaddingString(var2);
      this.A = this.registerKeyword("->");
   }

   @Override
   public void eol() {
      ICodeCoordinates var1 = this.getCurrentLineCoordinates();
      super.eol(var1);
   }

   void pC() {
      this.appendKeyword(this.A);
   }

   void pC(bff var1) {
      String var2 = var1.pC();
      this.appendAndRecord(var2, ItemClassIdentifiers.DIRECTIVE);
   }

   void pC(bfk var1) {
      String var2 = var1.pC();
      this.appendAndRecord(var2, ItemClassIdentifiers.KEYWORD);
   }

   void pC(bfe var1) {
      String var2 = var1.pC();
      this.appendAndRecord(var2, ItemClassIdentifiers.DIRECTIVE);
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      bfx var3 = (bfx)var1.sY().pC(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_STRING_%d", var2));
      } else {
         String var4 = Strings.safe(var3.getValue());
         boolean var6 = !this.pC && var4.length() <= 500;
         int var7 = var1.A(var3);
         String var5;
         if (var7 == 1 && var6) {
            var5 = "\"" + Formatter.escapeString(var4, -1, true, null) + "\"";
         } else if (var7 == 2) {
            var5 = "\"" + Formatter.escapeAllCharacters(var4) + "\"";
         } else {
            var5 = "\"" + Formatter.escapeString(var4, var6) + "\"";
         }

         this.appendAndRecord(var5, ItemClassIdentifiers.STRING, var1.pC(var3));
      }
   }

   void A(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      bfu var3 = var1.sY(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_METHOD_%d", var2));
      } else {
         this.UT(var1, var3.getClassTypeIndex());
         this.pC();
         this.appendAndRecord(var3.getName(true), ItemClassIdentifiers.METHOD_NAME, var1.pC(var3));
         this.pC(var1, var3.UT());
      }
   }

   void kS(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      bft var3 = var1.E(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_FIELD_%d", var2));
      } else {
         this.UT(var1, var3.getClassTypeIndex());
         this.pC();
         this.appendAndRecord(var3.getName(true), ItemClassIdentifiers.FIELD_NAME, var1.pC(var3));
         this.append(":");
         this.UT(var1, var3.getFieldTypeIndex());
      }
   }

   void wS(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      bfw var3 = var1.UT(var2);
      if (var3 == null) {
         this.append(Strings.ff("INVALID_PROTO_%d", var2));
      } else {
         this.pC(var1, var3);
      }
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, bfw var2) {
      this.pC(var1, var2, 20);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, bfw var2, int var3) {
      this.append("(");
      if (!this.pC) {
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
               this.UT(var1, var7);
            }
         }
      } else {
         for (int var11 : var2.getParameterTypeIndexes()) {
            this.UT(var1, var11);
         }
      }

      this.append(")");
      this.UT(var1, var2.getReturnTypeIndex());
   }

   void UT(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      this.pC(var1, var2, false);
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2, boolean var3) {
      bfy var4 = var1.wS(var2);
      if (var4 == null) {
         this.append(Strings.ff("INVALID_TYPE_%d", var2));
      } else {
         String var5 = var4.getNonArrayClass();
         if (var5 != null) {
            bfy var6 = (bfy)var1.ld().kS(var5);

            for (int var7 = 0; var7 < var4.getDimensions(); var7++) {
               this.append("[");
            }

            Object var12 = var6.A();
            if (var12 == null) {
               var12 = var6;
            }

            String var8;
            ItemClassIdentifiers var9;
            long var10;
            if (var12 instanceof bfs) {
               var8 = (String)this.kS.get(var12);
               if (var8 == null) {
                  var8 = ((bfs)var12).getSignature(true);
                  this.kS.put((bfs)var12, var8);
               }

               var9 = ItemClassIdentifiers.CLASS_NAME;
               var10 = var1.pC((bfs)var12);
            } else {
               var8 = var5;
               var9 = ItemClassIdentifiers.EXTERNAL_CLASS_NAME;
               var10 = var1.pC(var4);
            }

            if (!this.pC) {
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
