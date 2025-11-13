package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class EnginesPropertiesUtil {
   private IPropertyManager pm;

   public EnginesPropertiesUtil(IPropertyManager var1) {
      this.pm = var1;
   }

   public List parseDevPluginClassnames(String var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         return var2;
      } else {
         for (String var6 : var1.split("[;,]")) {
            String[] var7 = var6.split(":");
            String var8;
            boolean var9;
            if (var7.length == 1) {
               var8 = var7[0].trim();
               var9 = true;
            } else {
               if (var7.length != 2) {
                  continue;
               }

               var8 = var7[0].trim();
               var9 = Conversion.stringToInt(var7[1]) == 1;
            }

            if (!var8.isEmpty()) {
               var2.add(new DevPluginClassname(var8, var9));
            }
         }

         return var2;
      }
   }

   public List getDevPluginClassnames() {
      String var1 = this.pm.getString(".DevPluginClassnames");
      return this.parseDevPluginClassnames(var1);
   }

   public String buildDevPluginClassnames(List var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (DevPluginClassname var5 : var1) {
         if (var3 > 0) {
            var2.append(",");
         }

         Strings.ff(var2, "%s:%s", var5.getClassname(), var5.isEnabled() ? "1" : "0");
         var3++;
      }

      return var2.toString();
   }

   public void setDevPluginClassnames(List var1) {
      String var2 = this.buildDevPluginClassnames(var1);
      this.pm.setString(".DevPluginClassnames", var2);
   }
}
