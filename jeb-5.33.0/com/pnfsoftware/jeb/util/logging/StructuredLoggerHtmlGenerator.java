package com.pnfsoftware.jeb.util.logging;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

class StructuredLoggerHtmlGenerator {
   private static final String stylesheet;
   StructuredLogger log;
   boolean genTimestampInfo;
   boolean genCallerInfo;

   StructuredLoggerHtmlGenerator(StructuredLogger var1, boolean var2, boolean var3) {
      this.log = var1;
      this.genTimestampInfo = var2;
      this.genCallerInfo = var3;
   }

   public String generate(FileOutputStream var1, Charset var2) {
      StringBuilder var3 = new StringBuilder(
         Strings.ff("<!DOCTYPE html>\n<html>\n<head>\n<title>JEB Debug Log</title>\n<style>\n%s\n</style>\n</head>\n<body>\n", stylesheet)
      );
      Strings.ff(var3, "<h1>%s</h1>\n", this.log.rootEntry.text);
      this.gen(this.log.rootEntry, var3, 0, var1, var2);
      var3.append("</body>\n</html>");
      if (var1 != null) {
         this.write(var3, var1, var2);
      }

      return var3.toString();
   }

   private void write(StringBuilder var1, FileOutputStream var2, Charset var3) {
      byte[] var4 = var1.toString().getBytes(var3);

      try {
         var2.write(var4, 0, var4.length);
      } catch (IOException var6) {
         throw new RuntimeException("Can not write buffer", var6);
      }

      var1.delete(0, var1.length());
   }

   public String generate() {
      return this.generate(null, null);
   }

   private void gen(StructuredLogger.Entry var1, StringBuilder var2, int var3, FileOutputStream var4, Charset var5) {
      if (var1.sub == null) {
         StringBuilder var12 = new StringBuilder();
         StringBuilder var13 = new StringBuilder();
         if (var1.importance != 10) {
            if (this.genTimestampInfo) {
               var12.append("<span class='timestamp'>(").append(TimeFormatter.formatTimestampDelta(var1.timestamp - this.log.ts0)).append(")</span> ");
            }

            if (this.genCallerInfo) {
               if (var1.caller == null) {
                  var13.append("?");
               } else {
                  var13.append("<span class='caller'>[").append(this.escape(var1.caller)).append("]</span> ");
               }
            }
         }

         String var14 = "";
         String var9 = this.importanceToClass(var1.importance);
         if (!var9.isEmpty()) {
            var14 = var14 + var9;
         }

         String var10 = this.levelToClass(var1.level);
         if (!var10.isEmpty()) {
            var14 = var14 + " " + var10;
         }

         StringBuilder var11 = new StringBuilder();
         var11.append("<span class='").append(var14).append("'>");
         var11.append((CharSequence)var12).append((CharSequence)var13).append(this.escape(var1.text)).append("</span><br>");
         this.appendLine(var2, var3, var11);
         if (var4 != null) {
            this.write(var2, var4, var5);
         }
      } else {
         for (StructuredLogger.Entry var7 : var1.sub) {
            if (var2.length() > 268435456) {
               this.appendLine(var2, var3 + 1, "[...]");
               return;
            }

            if (var7.sub == null) {
               this.gen(var7, var2, var3 + 1, var4, var5);
            } else {
               String var8 = var7.open ? " open" : "";
               this.appendLine(var2, var3 + 1, "<details%s><summary>%s</summary>", var8, this.escape(var7.text));
               this.appendLine(var2, var3 + 1, "<div class='logsection'>");
               this.gen(var7, var2, var3 + 1, var4, var5);
               this.appendLine(var2, var3 + 1, "</div>");
               this.appendLine(var2, var3 + 1, "</details>");
            }
         }
      }
   }

   private String importanceToClass(int var1) {
      switch (var1) {
         case -1:
            return "logimp-small";
         case 0:
            return "logimp-normal";
         case 1:
            return "logimp-highlight";
         case 2:
            return "logimp-section";
         case 3:
            return "logimp-subtitle";
         case 4:
            return "logimp-title";
         case 5:
            return "logimp-chapter";
         default:
            return "";
      }
   }

   private String levelToClass(int var1) {
      switch (var1) {
         case 10:
            return "loglevel-trace";
         case 20:
            return "loglevel-debug";
         case 30:
            return "loglevel-info";
         case 40:
            return "loglevel-warn";
         case 50:
            return "loglevel-error";
         case 60:
            return "loglevel-critical";
         default:
            return "";
      }
   }

   private void appendLine(StringBuilder var1, int var2, String var3, Object... var4) {
      this.appendLine(var1, var2, var4 != null && var4.length != 0 ? Strings.ff(var3, var4) : var3);
   }

   private void appendLine(StringBuilder var1, int var2, CharSequence var3) {
      var1.append(Strings.generate(' ', var2 * 2));
      var1.append(var3);
      var1.append('\n');
   }

   private String escape(String var1) {
      var1 = Formatter.htmlEscape(var1);
      var1 = var1.replace("\t", "    ");
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < var1.length(); var3++) {
         char var4 = var1.charAt(var3);
         if (var4 == ' ' && var3 + 1 < var1.length() && var1.charAt(var3 + 1) == ' ') {
            var2.append("&nbsp;");
         } else {
            var2.append(var4);
         }
      }

      var1 = var2.toString();
      return var1.replaceAll("(\r\n|\r|\n)", "<br>");
   }

   static {
      try {
         stylesheet = Strings.decodeUTF8(IO.readInputStream(StructuredLogger.class.getResourceAsStream("slstyles.css")));
      } catch (IOException var1) {
         throw new RuntimeException("Cannot retrieve html style sheet for the structured logger", var1);
      }
   }
}
