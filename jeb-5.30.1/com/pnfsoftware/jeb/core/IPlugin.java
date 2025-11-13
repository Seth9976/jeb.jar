package com.pnfsoftware.jeb.core;

public interface IPlugin {
   IPluginInformation getPluginInformation();

   void setData(Object var1, Object var2);

   Object getData(Object var1);

   void dispose();
}
