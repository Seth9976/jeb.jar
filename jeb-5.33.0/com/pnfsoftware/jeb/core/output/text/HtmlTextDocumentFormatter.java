package com.pnfsoftware.jeb.core.output.text;

import com.pnfsoftware.jeb.core.output.AbstractHtmlFormatter;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.XmlBuilder;
import java.util.HashSet;
import java.util.Set;

public class HtmlTextDocumentFormatter extends AbstractHtmlFormatter {
   private IItemStyleInfoProvider styleprv;
   private Set styleLessids = new HashSet();
   private static final int defaultForegroundColor = 0;
   private static final int defaultBackgroundColor = 16777215;

   public HtmlTextDocumentFormatter(IItemStyleInfoProvider var1, String var2, boolean var3) {
      super(var2, new StringBuilder("div {\n  display: inline-block;\n  margin:0;\n  padding:0;\n  border:0;\n}\n"), var3);
      this.styleprv = var1;
   }

   public String generate(ITextDocument var1) {
      ITextDocumentPart var2 = var1.getDocumentPart(var1.getFirstAnchor(), Integer.MAX_VALUE);
      return this.generate(var2);
   }

   public String generate(ITextDocumentPart var1) {
      StringBuilder var2 = new StringBuilder("<pre>\n");

      for (ILine var4 : var1.getLines()) {
         CharSequence var5 = var4.getText();
         int var6 = 0;
         String var7 = null;

         for (ITextItem var9 : var4.getItems()) {
            int var10 = var9.getOffset();
            int var11 = var9.getOffsetEnd();
            Object var12 = "";
            if (var10 > var6) {
               var12 = XmlBuilder.escapeAll(var5.subSequence(var6, var10));
               var2.append((CharSequence)var12);
               var6 = var10;
            }

            String var13 = this.generateCssDivStyle(var9);
            if (Strings.isBlank(var13)) {
               var2.append(XmlBuilder.escapeAll(var5.subSequence(var6, var11)));
            } else if (var13.equals(var7)) {
               var2.delete(var2.length() - var12.length() - 6, var2.length() - var12.length());
               var2.append(XmlBuilder.escapeAll(var5.subSequence(var6, var11))).append("</div>");
            } else {
               var2.append("<div class=\"").append(var13).append("\">");
               var2.append(XmlBuilder.escapeAll(var5.subSequence(var6, var11))).append("</div>");
            }

            var6 = var11;
            var7 = var13;
         }

         if (var6 < var5.length()) {
            var2.append(XmlBuilder.escapeAll(var5.subSequence(var6, var5.length())));
         }

         var2.append('\n');
      }

      var2.append("</pre>\n");
      return this.generate(var2);
   }

   private String generateCssDivStyle(ITextItem var1) {
      ItemClassIdentifiers var2 = ItemClassIdentifiers.DEFAULT;
      if (var1 instanceof IVisualTextItem) {
         var2 = ((IVisualTextItem)var1).getClassId();
      }

      return this.styleLessids.contains(var2) ? null : super.generateCssDivStyle(var2);
   }

   protected CharSequence generateCssBlock(ItemClassIdentifiers var1, String var2) {
      StyleInfo var3 = this.styleprv.getStyle(var1);
      if (var3 == null) {
         return "";
      } else {
         StringBuilder var4 = new StringBuilder().append(".").append(var2).append(" {\n");
         boolean var5 = true;
         if (var3.getForegroundColor() != null) {
            var4.append("  color: #").append(Formatter.toHexString(var3.getForegroundColor(), true, 6)).append(";\n");
            if (var3.getForegroundColor() != 0) {
               var5 = false;
            }
         }

         if (var3.getBackgroundColor() != null) {
            var4.append("  background-color: #").append(Formatter.toHexString(var3.getBackgroundColor(), true, 6)).append(";\n");
            if (var3.getBackgroundColor() != 16777215) {
               var5 = false;
            }
         }

         if (Boolean.TRUE.equals(var3.getBold())) {
            var4.append("  font-weight: bold;\n");
            var5 = false;
         }

         if (Boolean.TRUE.equals(var3.getItalic())) {
            var4.append("  font-style: italic;\n");
            var5 = false;
         }

         var4.append("}\n");
         if (var5) {
            this.styleLessids.add(var1);
            return null;
         } else {
            return var4;
         }
      }
   }
}
