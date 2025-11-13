package com.pnfsoftware.jeb.util.reflect;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class ClasspathCollector {
   private static final ILogger logger = GlobalLog.getLogger(ClasspathCollector.class);
   private List urls = new ArrayList();

   public ClasspathCollector() {
      logger.trace("Initializing ClassloaderCollector...");
   }

   public List getUrls() {
      return Collections.unmodifiableList(this.urls);
   }

   public void addSmart(File var1, String var2) {
      for (String var6 : var2.split(",", -1)) {
         File var7 = new File(var6.trim());
         String var8 = var7.getName();
         File var9;
         if (var7.isAbsolute()) {
            var9 = var7.getParentFile();
         } else if (var7.getParent() != null) {
            var9 = new File(var1, var7.getParent());
         } else {
            var9 = var1;
         }

         WildcardFileFilter var10 = WildcardFileFilter.builder().setWildcards(new String[]{var8}).get();
         File[] var11 = var9.listFiles(var10);

         for (File var15 : var11) {
            if (var15.getName().endsWith(".jar")) {
               this.addFromJar(var15);
            } else {
               this.add(var15.getAbsolutePath());
            }
         }
      }
   }

   public boolean add(String var1) {
      boolean var2 = true;

      for (String var6 : var1.trim().split(File.pathSeparator, -1)) {
         URL var7;
         try {
            var7 = new File(var6).toURI().toURL();
         } catch (MalformedURLException var9) {
            logger.catching(var9);
            var2 = false;
            continue;
         }

         this.addURL(var7);
      }

      return var2;
   }

   public boolean addFromJar(File var1) {
      boolean var2 = true;

      URL var3;
      Attributes var4;
      try {
         var3 = var1.toURI().toURL();
         URL var5 = new URL("jar", "", var3 + "!/");
         JarURLConnection var6 = (JarURLConnection)var5.openConnection();
         var4 = var6.getMainAttributes();
      } catch (IOException var11) {
         logger.catching(var11);
         return false;
      }

      this.addURL(var3);
      if (var4 != null) {
         String var14 = var4.getValue(Name.CLASS_PATH);
         if (var14 != null) {
            for (String var9 : var14.split("\\s+", -1)) {
               try {
                  var3 = new File(var1.getParentFile(), var9).toURI().toURL();
               } catch (MalformedURLException var12) {
                  logger.catching(var12);
                  var2 = false;
                  continue;
               }

               this.addURL(var3);
            }
         }
      }

      return var2;
   }

   private void addURL(URL var1) {
      logger.trace("Adding to ClasspathCollector: %s", var1);
      this.urls.add(var1);
   }

   public URLClassLoader createClassloader() {
      return this.getClassloader(null);
   }

   public URLClassLoader getClassloader(ClassLoader var1) {
      if (var1 == null) {
         var1 = this.getClass().getClassLoader();
      } else if (var1 instanceof URLClassLoader var2) {
         List var3 = Arrays.asList(var2.getURLs());
         if (var3.containsAll(this.urls)) {
            return var2;
         }
      }

      logger.trace("Creating Classloader with: %s", this.urls);
      return new URLClassLoader((URL[])this.urls.toArray(new URL[this.urls.size()]), var1);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (URL var3 : this.urls) {
         if (var1.length() > 0) {
            var1.append(File.pathSeparatorChar);
         }

         try {
            String var4 = Paths.get(var3.toURI()).toFile().getAbsolutePath();
            var1.append(var4);
         } catch (Exception var5) {
         }
      }

      return var1.toString();
   }
}
