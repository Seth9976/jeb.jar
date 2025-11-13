package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.format.HtmlFormatter;
import com.pnfsoftware.jeb.util.format.XmlBuilder;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHtmlFormatter {
   private CharSequence titledata;
   private boolean insertCopyright;
   private StringBuilder cssdata;
   private Map styleidToDivclassname = new HashMap();
   private Map cssToStyleId = new HashMap();

   public AbstractHtmlFormatter(String var1, StringBuilder var2, boolean var3) {
      this.titledata = XmlBuilder.escapeAll(var1);
      this.cssdata = var2;
      this.insertCopyright = var3;
   }

   protected String generate(CharSequence var1) {
      return HtmlFormatter.generate(this.titledata, this.cssdata, var1, this.insertCopyright).toString();
   }

   protected String generateCssDivStyle(Object var1) {
      String var2 = (String)this.styleidToDivclassname.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         String var3 = this.getClassname(var1);
         CharSequence var4 = this.generateCssBlock(var1, var3);
         if (var4 != null && var4.length() != 0) {
            CharSequence var5 = var4.subSequence(var3.length() + 2, var4.length());
            if (this.cssToStyleId.containsKey(var5)) {
               Object var6 = this.cssToStyleId.get(var5);
               var3 = (String)this.styleidToDivclassname.get(var6);
            } else {
               this.cssdata.append(var4);
               this.cssToStyleId.put(var4.subSequence(var3.length() + 2, var4.length()), var1);
            }

            this.styleidToDivclassname.put(var1, var3);
            return var3;
         } else {
            return null;
         }
      }
   }

   protected abstract CharSequence generateCssBlock(Object var1, String var2);

   protected String getClassname(Object var1) {
      String var2 = var1.toString().toLowerCase().substring(0, 1);
      if (var2.isEmpty() || var2.charAt(0) < 'a' || var2.charAt(0) > 'z') {
         var2 = "z";
      }

      if (this.styleidToDivclassname.containsValue(var2)) {
         for (char var3 = 'z'; var3 >= 'a'; var3--) {
            var2 = Character.toString(var3);
            if (!this.styleidToDivclassname.containsValue(var2)) {
               return var2;
            }
         }
      }

      String var5 = var1.toString().toLowerCase().substring(0, 1);

      for (int var4 = 0; this.styleidToDivclassname.containsValue(var2); var4++) {
         var2 = var5.substring(0, 1) + var4;
      }

      return var2;
   }
}
