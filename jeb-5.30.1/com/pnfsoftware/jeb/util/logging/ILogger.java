package com.pnfsoftware.jeb.util.logging;

public interface ILogger {
   String getName();

   void setEnabledLevel(int var1);

   int getEnabledLevel();

   void log(int var1, boolean var2, String var3, Object... var4);

   void i(String var1, Object... var2);

   void trace(String var1, Object... var2);

   void fine(String var1, Object... var2);

   void debug(String var1, Object... var2);

   void info(String var1, Object... var2);

   void warn(String var1, Object... var2);

   void warning(String var1, Object... var2);

   void error(String var1, Object... var2);

   void severe(String var1, Object... var2);

   void catching(Throwable var1);

   void catching(Throwable var1, String var2);

   void catching(Throwable var1, String var2, Object... var3);

   void catchingSilent(Throwable var1);

   void status(String var1, Object... var2);
}
