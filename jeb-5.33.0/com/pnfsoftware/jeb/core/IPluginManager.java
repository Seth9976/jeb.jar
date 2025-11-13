package com.pnfsoftware.jeb.core;

import java.io.File;
import java.util.List;

public interface IPluginManager {
   IEnginesContext getEnginesContext();

   ClassLoader getClassloader();

   List load(File var1);

   Class load(String var1, String var2);

   List getPlugins(Class var1);

   List getPlugins(Class var1, boolean var2);

   List getPluginEntries(Class var1);

   List getPluginEntries(Class var1, boolean var2);
}
