package com.pnfsoftware.jeb.corei.parsers.java;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.encoding.jflex.Token;
import com.pnfsoftware.jeb.util.encoding.jflex.TokenType;
import com.pnfsoftware.jeb.util.format.Formatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class eo extends CodeDocument {
   protected nI q;
   protected CodeDocumentPart RF;
   private static final Set xK = new HashSet(Arrays.asList('\\', '"', '\''));

   public eo(nI var1) {
      this.q = var1;
      this.q(var1);
   }

   @Override
   public IUnit getUnit() {
      return this.q;
   }

   @Override
   public final long getAnchorCount() {
      return 1L;
   }

   @Override
   public final ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      return this.RF;
   }

   private void q(nI var1) {
      this.RF = new com.pnfsoftware.jeb.corei.parsers.java.eo.eo(0L);
      ArrayList var2 = new ArrayList(var1.RF());
      String var3 = var1.q();
      this.RF.registerAnchor("wholeDocumentAnchor");
      int var4 = 0;
      Token var5 = null;

      while (var4 < var3.length()) {
         while (var4 < var3.length() && var3.charAt(var4) != '\r' && var3.charAt(var4) != '\n') {
            if (var5 != null && var4 >= var5.end()) {
               var5 = null;
            }

            if (var5 == null && !var2.isEmpty()) {
               var5 = (Token)var2.remove(0);
            }

            int var6 = var4;
            boolean var7 = var5 != null && var4 >= var5.start && var4 < var5.end();

            while (
               var4 < var3.length()
                  && var3.charAt(var4) != '\r'
                  && var3.charAt(var4) != '\n'
                  && (var7 && var4 < var5.end() || !var7 && (var5 == null || var4 < var5.start))
            ) {
               var4++;
            }

            this.q(var3, var6, var4, var7, var5);
         }

         this.RF.eol();
         if (var4 < var3.length() && var3.charAt(var4) == '\r' && var4 + 1 < var3.length() && var3.charAt(var4 + 1) == '\n') {
            var4++;
         }

         if (var4 < var3.length() && var3.charAt(var4) == '\n') {
            var4++;
         }
      }

      this.RF.eol();
   }

   protected void q(String var1, int var2, int var3, boolean var4, Token var5) {
      String var6 = var1.substring(var2, var3);
      ItemClassIdentifiers var7 = this.q(var5, var6);
      if (!var4 || var7 == null) {
         this.RF.append(var6);
      } else if (var5.type == TokenType.STRING) {
         this.q(var6.substring(1, var6.length() - 1), true);
      } else {
         this.RF.appendAndRecord(var6, var7);
      }
   }

   protected ItemClassIdentifiers q(Token var1, String var2) {
      if (var1 == null) {
         return null;
      } else {
         switch (var1.type) {
            case COMMENT:
            case COMMENT2:
               return ItemClassIdentifiers.COMMENT;
            case IDENTIFIER:
               return ItemClassIdentifiers.IDENTIFIER;
            case KEYWORD:
            case KEYWORD2:
               return ItemClassIdentifiers.KEYWORD;
            case NUMBER:
               return ItemClassIdentifiers.NUMBER;
            case REGEX:
            case REGEX2:
            case STRING:
            case STRING2:
               return ItemClassIdentifiers.STRING;
            case TYPE:
            case TYPE2:
               return ItemClassIdentifiers.CLASS_NAME;
            case DELIMITER:
            case TYPE3:
            case ERROR:
            case WARNING:
            case OPERATOR:
            case DEFAULT:
            default:
               return null;
         }
      }
   }

   protected void q(Object var1, boolean var2) {
      String var3 = Formatter.escapeString(String.valueOf(var1), 1, true, xK);
      if (var2) {
         StringBuilder var4 = new StringBuilder();
         var4.append("\"");
         var4.append(var3);
         var4.append("\"");
         this.RF.appendAndRecord(var4.toString(), ItemClassIdentifiers.STRING);
      } else {
         this.RF.appendAndRecord(var3, ItemClassIdentifiers.STRING);
      }
   }

   private static class eo extends CodeDocumentPart {
      public eo(long var1) {
         super(var1);
      }
   }
}
