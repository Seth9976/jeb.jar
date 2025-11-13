package com.pnfsoftware.jeb.core;

import java.io.File;

public interface IPluginFileEntry {
   boolean isValidPlugin();

   boolean isScriptPlugin();

   boolean isPythonPlugin();

   File getFile();

   Class getPluginClass();

   IPlugin getPluginObject();

   IPlugin getPluginObject(boolean var1);
}
