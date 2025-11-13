package com.pnfsoftware.jeb.core.properties;

import com.pnfsoftware.jeb.core.properties.impl.PropertyChangeObject;
import com.pnfsoftware.jeb.util.events.IEventSource;

public interface IPropertyManager extends IEventSource {
   int DEPTH_BASIC = 1;
   int DEPTH_MASTER = 2;
   int DEPTH_FULL = 3;

   void dispose();

   IPropertyDefinitionManager getPropertyDefinitionManager();

   IConfiguration getConfiguration();

   boolean getBoolean(String var1);

   boolean getBoolean(String var1, boolean var2);

   Boolean getBooleanUnsafe(String var1);

   boolean setBoolean(String var1, Boolean var2);

   boolean setBoolean(String var1, Boolean var2, PropertyChangeObject var3);

   int getInteger(String var1);

   int getInteger(String var1, int var2);

   Integer getIntegerUnsafe(String var1);

   boolean setInteger(String var1, Integer var2);

   boolean setInteger(String var1, Integer var2, PropertyChangeObject var3);

   String getString(String var1);

   String getString(String var1, String var2);

   String getStringUnsafe(String var1);

   boolean setString(String var1, String var2);

   boolean setString(String var1, String var2, PropertyChangeObject var3);

   Object getValue(String var1);

   Object getValue(String var1, int var2, boolean var3, boolean var4);

   boolean setValue(String var1, Object var2);

   boolean setValue(String var1, Object var2, boolean var3, PropertyChangeObject var4);
}
