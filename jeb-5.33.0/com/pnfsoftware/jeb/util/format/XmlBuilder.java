package com.pnfsoftware.jeb.util.format;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class XmlBuilder extends TextBuilder {
   Deque eltstack = new ArrayDeque();

   public XmlBuilder() {
   }

   public XmlBuilder(int var1) {
      super(var1);
   }

   public XmlBuilder(int var1, int var2) {
      super(var1, var2);
   }

   public void appendStandardHeader() {
      this.appendHeader(1, 0, "UTF-8", false);
   }

   public void appendHeader(int var1, int var2, String var3, boolean var4) {
      String var5 = !var4 ? "" : "standalone=\"true\"";
      this.appendLine("<?xml version=\"%d.%d\" encoding=\"%s\" %s?>", var1, var2, var3, var5);
   }

   public void openElement(String var1) {
      this.openElement(var1, null, false);
   }

   public void openElement(String var1, Map var2) {
      this.openElement(var1, var2, false);
   }

   public void openElement(String var1, Map var2, boolean var3) {
      this.append("<%s", var1);
      if (var2 != null) {
         for (String var5 : var2.keySet()) {
            this.append(" %s=\"%s\"", var5, var2.get(var5));
         }
      }

      if (var3) {
         this.append(" />");
      } else {
         this.eltstack.push(var1);
         this.append(">");
         this.indent();
      }
   }

   public void closeElement() {
      this.closeElement(false);
   }

   public void closeElement(boolean var1) {
      this.unindent();
      String var2 = (String)this.eltstack.pop();
      this.append("</%s>", var2);
      if (var1) {
         this.appendLine();
      }
   }

   public static CharSequence escapeTextData(CharSequence var0) {
      return escape(var0, XmlBuilder.MODE.TEXT);
   }

   public static CharSequence escapeAttributeData(CharSequence var0) {
      return escape(var0, XmlBuilder.MODE.ATTRIBUTE);
   }

   public static CharSequence escapeAll(CharSequence var0) {
      return escape(var0, XmlBuilder.MODE.ALL);
   }

   private static CharSequence escape(CharSequence var0, XmlBuilder.MODE var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < var0.length(); var3++) {
         char var4 = var0.charAt(var3);
         if (var4 == '&') {
            var2.append("&amp;");
         } else if (var4 == '<') {
            var2.append("&lt;");
         } else if (var4 == '>' && var1 != XmlBuilder.MODE.ATTRIBUTE) {
            var2.append("&gt;");
         } else if (var4 == '"' && var1 != XmlBuilder.MODE.TEXT) {
            var2.append("&quot;");
         } else if (var4 == '\'' && var1 != XmlBuilder.MODE.TEXT) {
            var2.append("&apos;");
         } else if (var4 == '\n' && var1 != XmlBuilder.MODE.TEXT) {
            var2.append("\\n");
         } else if (var4 == '\r' && var1 != XmlBuilder.MODE.TEXT) {
            var2.append("\\r");
         } else {
            var2.append(var4);
         }
      }

      return var2;
   }

   private static enum MODE {
      ALL,
      TEXT,
      ATTRIBUTE;
   }
}
