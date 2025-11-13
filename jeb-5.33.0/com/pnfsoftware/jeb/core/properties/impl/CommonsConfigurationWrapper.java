package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.configuration2.Configuration;

@SerDisabled
public class CommonsConfigurationWrapper implements IConfiguration {
   private Configuration cfg;

   public CommonsConfigurationWrapper(Configuration var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide a configuration ApacheCommons configuration object");
      } else {
         this.cfg = var1;
      }
   }

   @Override
   public void clearProperty(String var1) {
      this.cfg.clearProperty(var1);
   }

   @Override
   public void setProperty(String var1, Object var2) {
      this.cfg.setProperty(var1, var2);
   }

   @Override
   public Object getProperty(String var1) {
      return this.cfg.getString(var1);
   }

   @Override
   public Set getAllPropertyKeys() {
      LinkedHashSet var1 = new LinkedHashSet();
      Iterator var2 = this.cfg.getKeys();

      while (var2.hasNext()) {
         var1.add((String)var2.next());
      }

      return var1;
   }

   @Override
   public String toString() {
      return "cfg:" + this.cfg.toString();
   }
}
