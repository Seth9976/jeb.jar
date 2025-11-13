package com.pnfsoftware.jeb.core.output.text;

import com.pnfsoftware.jeb.util.format.Strings;

public class TextDocumentUtil {
   public static String getText(ITextDocument var0) {
      return buildText(var0).toString();
   }

   public static StringBuilder buildText(ITextDocument var0) {
      return buildText(var0, Integer.MAX_VALUE);
   }

   public static StringBuilder buildText(ITextDocument var0, int var1) {
      long var2 = var0.getFirstAnchor();
      ITextDocumentPart var4 = var0.getDocumentPart(var2, var1);
      if (var4 == null) {
         throw new RuntimeException("The part could not be retrieved");
      } else {
         return buildText(var4, var1);
      }
   }

   public static StringBuilder buildText(ITextDocumentPart var0) {
      return buildText(var0, Integer.MAX_VALUE);
   }

   public static StringBuilder buildText(ITextDocumentPart var0, int var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (ILine var5 : var0.getLines()) {
         if (var3 >= var1) {
            break;
         }

         var2.append(var5.getText());
         var2.append(Strings.LINESEP);
         var3++;
      }

      return var2;
   }
}
