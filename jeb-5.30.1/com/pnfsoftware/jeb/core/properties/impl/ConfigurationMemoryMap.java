package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Ser
public class ConfigurationMemoryMap implements IConfiguration {
   @SerId(1)
   private Map map = new HashMap();

   public ConfigurationMemoryMap() {
   }

   public ConfigurationMemoryMap(IConfiguration var1) {
      for (String var3 : var1.getAllPropertyKeys()) {
         if (!Strings.isBlank(var3)) {
            this.map.put(var3, var1.getProperty(var3));
         }
      }
   }

   @Override
   public void setProperty(String var1, Object var2) {
      this.map.put(var1, var2);
   }

   @Override
   public Object getProperty(String var1) {
      return this.map.get(var1);
   }

   @Override
   public void clearProperty(String var1) {
      this.map.remove(var1);
   }

   @Override
   public Set getAllPropertyKeys() {
      return Collections.unmodifiableSet(this.map.keySet());
   }

   @Override
   public String toString() {
      return Strings.ff("cfg:size=%d,keys=%s", this.map.size(), this.map.keySet());
   }
}
