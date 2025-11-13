package com.pnfsoftware.jeb.util.logging;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class StructuredLogger implements ILogger {
   private static final String thisClassName = StructuredLogger.class.getName();
   private boolean slEnabled;
   private boolean rlEnabled;
   long ts0 = System.currentTimeMillis();
   StructuredLogger.Entry rootEntry;
   StructuredLogger.Entry currentEntry;
   int entryCount;

   public StructuredLogger(String var1) {
      this(var1, true, true);
   }

   public StructuredLogger(String var1, boolean var2, boolean var3) {
      this.rootEntry = StructuredLogger.Entry.createSection(null, var1, true);
      this.currentEntry = this.rootEntry;
      this.slEnabled = var2;
      this.rlEnabled = var3;
   }

   public boolean isStructuredLoggingEnabled() {
      return this.slEnabled;
   }

   public boolean isRegularLoggingEnabled() {
      return this.rlEnabled;
   }

   public boolean hasStructuredContents() {
      return this.entryCount > 0;
   }

   public void beginSection(String var1, boolean var2) {
      if (this.slEnabled) {
         StructuredLogger.Entry var3 = StructuredLogger.Entry.createSection(this.currentEntry, var1, var2);
         this.currentEntry.sub.add(var3);
         this.currentEntry = var3;
         this.entryCount++;
      }
   }

   public void beginSection(String var1) {
      this.beginSection(var1, false);
   }

   public void closeSection() {
      if (this.slEnabled) {
         if (this.currentEntry.parent == null) {
            throw new IllegalStateException("The root entry cannot be closed");
         } else {
            this.currentEntry = this.currentEntry.parent;
         }
      }
   }

   public String generateHtml() {
      return new StructuredLoggerHtmlGenerator(this, true, true).generate();
   }

   public void generateHtml(FileOutputStream var1, Charset var2) {
      new StructuredLoggerHtmlGenerator(this, true, true).generate(var1, var2);
   }

   @Override
   public String getName() {
      return this.rootEntry.text;
   }

   public void setName(String var1) {
      this.rootEntry.text = var1;
   }

   @Override
   public void setEnabledLevel(int var1) {
   }

   @Override
   public int getEnabledLevel() {
      return 0;
   }

   @Override
   public void log(int var1, boolean var2, String var3, Object... var4) {
      this.log(0, var1, var2, var3, var4);
   }

   public void log(int var1, int var2, boolean var3, String var4, Object... var5) {
      if (this.slEnabled || this.rlEnabled) {
         String var6 = var5.length == 0 ? var4 : Strings.ff(var4, var5);
         if (this.slEnabled) {
            long var7 = System.currentTimeMillis();
            StackTraceElement[] var9 = Thread.currentThread().getStackTrace();
            int var10 = 1;

            for (boolean var11 = false; var10 < var9.length; var10++) {
               boolean var12 = Strings.equals(var9[var10].getClassName(), thisClassName);
               if (var12 && !var11) {
                  var11 = true;
               }

               if (!var12 && var11 && !var9[var10].getClassName().toLowerCase().endsWith("log")) {
                  break;
               }
            }

            String var17 = null;
            if (var10 < var9.length) {
               String var13 = var9[var10].getClassName();
               int var14 = var13.lastIndexOf(46);
               String var15 = var14 < 0 ? var13 : var13.substring(var14 + 1);
               int var16 = var9[var10].getLineNumber();
               var17 = Strings.ff("%s:%d", var15, var16);
            }

            StructuredLogger.Entry var18 = StructuredLogger.Entry.createLine(var1, var2, var6, var17, var7);
            this.currentEntry.sub.add(var18);
         }

         if (this.rlEnabled) {
            GlobalLog.getLogger().log(var2, var3, var4, var5);
         }
      }
   }

   @Override
   public void i(String var1, Object... var2) {
   }

   public void iH(String var1, Object... var2) {
   }

   public void iHH(String var1, Object... var2) {
   }

   public void iHHH(String var1, Object... var2) {
   }

   public void iHHHH(String var1, Object... var2) {
   }

   public void iHHHHH(String var1, Object... var2) {
   }

   public void iL(String var1, Object... var2) {
   }

   public void i(int var1, String var2, Object... var3) {
   }

   public void iBreak() {
   }

   public void iBreak(int var1) {
   }

   public void iSeparator() {
   }

   @Override
   public void trace(String var1, Object... var2) {
      this.log(10, false, var1, var2);
   }

   @Override
   public void fine(String var1, Object... var2) {
      this.trace(var1, var2);
   }

   @Override
   public void debug(String var1, Object... var2) {
      this.log(20, false, var1, var2);
   }

   @Override
   public void info(String var1, Object... var2) {
      this.log(30, false, var1, var2);
   }

   @Override
   public void warn(String var1, Object... var2) {
      this.log(40, false, var1, var2);
   }

   @Override
   public void warning(String var1, Object... var2) {
      this.warn(var1, var2);
   }

   @Override
   public void error(String var1, Object... var2) {
      this.log(50, false, var1, var2);
   }

   @Override
   public void severe(String var1, Object... var2) {
      this.error(var1, var2);
   }

   @Override
   public void catching(Throwable var1) {
      GlobalLog.catching(this, var1);
   }

   @Override
   public void catching(Throwable var1, String var2) {
      GlobalLog.catching(this, var1, var2);
   }

   @Override
   public void catching(Throwable var1, String var2, Object... var3) {
      GlobalLog.catching(this, var1, Strings.ff(var2, var3));
   }

   @Override
   public void catchingSilent(Throwable var1) {
      GlobalLog.catchingDebug(this, var1);
   }

   @Override
   public void status(String var1, Object... var2) {
      GlobalLog.status(var1, var2);
   }

   static class Entry {
      int importance;
      int level;
      String text;
      String caller;
      long timestamp;
      StructuredLogger.Entry parent;
      List sub;
      boolean open;

      public static StructuredLogger.Entry createLine(int var0, int var1, String var2, String var3, long var4) {
         StructuredLogger.Entry var6 = new StructuredLogger.Entry();
         var6.importance = var0;
         var6.level = var1;
         var6.text = var2;
         var6.caller = var3;
         var6.timestamp = var4;
         return var6;
      }

      public static StructuredLogger.Entry createSection(StructuredLogger.Entry var0, String var1, boolean var2) {
         StructuredLogger.Entry var3 = new StructuredLogger.Entry();
         var3.parent = var0;
         var3.text = var1;
         var3.sub = new ArrayList();
         var3.open = var2;
         return var3;
      }
   }
}
