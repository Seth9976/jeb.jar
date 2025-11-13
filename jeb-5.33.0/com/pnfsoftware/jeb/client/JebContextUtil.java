package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.net.URLUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class JebContextUtil {
   public static String getProgramDirectory() {
      try {
         URL var0 = JebContextUtil.class.getProtectionDomain().getCodeSource().getLocation();
         File var1 = URLUtil.urlToFile(var0);
         if (var1.isFile()) {
            String var2 = var1.getParent();
            return new File(var2).getParent();
         } else if (var1.isDirectory()) {
            if (var1.getName().equals("bin")) {
               var1 = var1.getParentFile();
            }

            return var1.getAbsolutePath();
         } else {
            return var1.getAbsolutePath();
         }
      } catch (Exception var3) {
         return null;
      }
   }

   public static boolean autoDetectPreferredLanguage() {
      String var0 = getPreferredLanguage();
      return var0 == null ? false : S.setCurrentLanguage(var0);
   }

   static String getPreferredLanguage() {
      try {
         String var0 = Strings.ff("%s/%s", getProgramDirectory(), "jeb-client.cfg");
         PropertiesConfiguration var1 = createPropertiesConfiguration(var0);
         return var1.getString(".PreferredLanguage", null);
      } catch (Exception var2) {
         return null;
      }
   }

   static PropertiesConfiguration createPropertiesConfiguration(String var0) {
      File var1 = new File(var0);
      if (!var1.isFile()) {
         try {
            var1.createNewFile();
         } catch (IOException var6) {
            throw new RuntimeException("Cannot create JEB properties file: " + var1, var6);
         }
      }

      Parameters var2 = new Parameters();
      FileBasedConfigurationBuilder var3 = new FileBasedConfigurationBuilder(PropertiesConfiguration.class)
         .configure(new BuilderParameters[]{(BuilderParameters)var2.properties().setFileName(var0)});
      var3.setAutoSave(true);

      try {
         return (PropertiesConfiguration)var3.getConfiguration();
      } catch (ConfigurationException var5) {
         throw new RuntimeException(var5);
      }
   }
}
