package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.events.EventSource;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class SimplePropertyManager extends EventSource implements IPropertyManager {
   private IConfiguration config;

   public SimplePropertyManager(IConfiguration var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.config = var1;
      }
   }

   @Override
   public void dispose() {
   }

   @Override
   public IConfiguration getConfiguration() {
      return this.config;
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return null;
   }

   @Override
   public boolean getBoolean(String var1) {
      return this.getBoolean(var1, false);
   }

   @Override
   public boolean getBoolean(String var1, boolean var2) {
      try {
         return this.getBooleanUnsafe(var1);
      } catch (Exception var3) {
         return var2;
      }
   }

   @Override
   public Boolean getBooleanUnsafe(String var1) {
      return (Boolean)this.config.getProperty(var1);
   }

   @Override
   public int getInteger(String var1) {
      return this.getInteger(var1, 0);
   }

   @Override
   public int getInteger(String var1, int var2) {
      try {
         return this.getIntegerUnsafe(var1);
      } catch (Exception var3) {
         return var2;
      }
   }

   @Override
   public Integer getIntegerUnsafe(String var1) {
      return (Integer)this.config.getProperty(var1);
   }

   @Override
   public String getString(String var1) {
      return this.getString(var1, "");
   }

   @Override
   public String getString(String var1, String var2) {
      try {
         return this.getStringUnsafe(var1);
      } catch (Exception var3) {
         return var2;
      }
   }

   @Override
   public String getStringUnsafe(String var1) {
      return (String)this.config.getProperty(var1);
   }

   @Override
   public Object getValue(String var1) {
      return this.config.getProperty(var1);
   }

   @Override
   public Object getValue(String var1, int var2, boolean var3, boolean var4) {
      return this.getValue(var1);
   }

   @Override
   public boolean setBoolean(String var1, Boolean var2) {
      this.config.setProperty(var1, var2);
      return true;
   }

   @Override
   public boolean setBoolean(String var1, Boolean var2, PropertyChangeObject var3) {
      this.config.setProperty(var1, var2);
      return true;
   }

   @Override
   public boolean setInteger(String var1, Integer var2) {
      this.config.setProperty(var1, var2);
      return true;
   }

   @Override
   public boolean setInteger(String var1, Integer var2, PropertyChangeObject var3) {
      this.config.setProperty(var1, var2);
      return true;
   }

   @Override
   public boolean setString(String var1, String var2) {
      this.config.setProperty(var1, var2);
      return true;
   }

   @Override
   public boolean setString(String var1, String var2, PropertyChangeObject var3) {
      this.config.setProperty(var1, var2);
      return true;
   }

   @Override
   public boolean setValue(String var1, Object var2) {
      this.config.setProperty(var1, var2);
      return true;
   }

   @Override
   public boolean setValue(String var1, Object var2, boolean var3, PropertyChangeObject var4) {
      return this.setValue(var1, var2);
   }
}
