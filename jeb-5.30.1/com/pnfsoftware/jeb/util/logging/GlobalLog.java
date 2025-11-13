package com.pnfsoftware.jeb.util.logging;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class GlobalLog {
   public static final List LEVEL_NAMES = Collections.unmodifiableList(Arrays.asList("ALL", "TRACE", "DEBUG", "INFO", "WARN", "ERROR", "CATCHING"));
   public static final int LEVEL_ALL = 0;
   public static final int LEVEL_DISABLED = Integer.MAX_VALUE;
   public static final int LEVEL_ALWAYS_ON = 60;
   public static final int LEVEL_TRACE = 10;
   public static final int LEVEL_DEBUG = 20;
   public static final int LEVEL_INFO = 30;
   public static final int LEVEL_WARN = 40;
   public static final int LEVEL_ERROR = 50;
   public static final int LEVEL_INTERNAL = 19;
   public static final int LEVEL_CATCHING = 60;
   public static final int LEVEL_STATUS = 100;
   static List outStreams = new CopyOnWriteArrayList();
   static List outBuffers = new CopyOnWriteArrayList();
   static List outStatusSinks = new CopyOnWriteArrayList();
   private static Map map = new HashMap();
   public static final int STANDARD_CUTOFF_LEVEL = Licensing.isDebugBuild() ? 0 : 30;
   private static volatile int cutoffLevel = STANDARD_CUTOFF_LEVEL;
   private static volatile int levelForNewLoggers = 0;
   public static boolean prefixWithLevel = true;
   private static Map filtermap = new HashMap();

   public static synchronized StreamSink addDestinationStream(OutputStream var0) {
      StreamSink var1 = new StreamSink(var0);
      int var2 = outStreams.indexOf(var1);
      if (var2 >= 0) {
         return (StreamSink)outStreams.get(var2);
      } else {
         outStreams.add(var1);
         return var1;
      }
   }

   public static synchronized StreamSink removeDestinationStream(OutputStream var0) {
      StreamSink var1 = new StreamSink(var0);
      int var2 = outStreams.indexOf(var1);
      return var2 < 0 ? null : (StreamSink)outStreams.remove(var2);
   }

   public static synchronized List getDestinationStreams() {
      return outStreams;
   }

   public static synchronized BufferSink addDestinationBuffer(List var0) {
      BufferSink var1 = new BufferSink(var0);
      int var2 = outBuffers.indexOf(var1);
      if (var2 >= 0) {
         return (BufferSink)outBuffers.get(var2);
      } else {
         outBuffers.add(var1);
         return var1;
      }
   }

   public static synchronized BufferSink removeDestinationBuffer(List var0) {
      BufferSink var1 = new BufferSink(var0);
      int var2 = outBuffers.indexOf(var1);
      return var2 < 0 ? null : (BufferSink)outBuffers.remove(var2);
   }

   public static synchronized List getDestinationBuffers() {
      return outBuffers;
   }

   public static synchronized List getRegularSinks() {
      ArrayList var0 = new ArrayList(outBuffers);
      var0.addAll(outStreams);
      return var0;
   }

   public static synchronized void addStatusSink(LogStatusSink var0) {
      if (!outStatusSinks.contains(var0)) {
         outStatusSinks.add(var0);
      }
   }

   public static synchronized void removeStatusSink(LogStatusSink var0) {
      outStatusSinks.remove(var0);
   }

   public static synchronized List getStatusSinks() {
      return outStatusSinks;
   }

   public static synchronized int setCutoffLevel(int var0) {
      int var1 = cutoffLevel;
      cutoffLevel = var0;
      return var1;
   }

   public static int getCutoffLevel() {
      return cutoffLevel;
   }

   public static int setLevelForNewLoggers(int var0) {
      return setLevelForNewLoggers(var0, false);
   }

   public static synchronized int setLevelForNewLoggers(int var0, boolean var1) {
      int var2 = levelForNewLoggers;
      levelForNewLoggers = var0;
      if (var1) {
         for (ILogger var4 : map.values()) {
            var4.setEnabledLevel(var0);
         }
      }

      return var2;
   }

   public static synchronized void setGlobalFilter(boolean var0) {
      if (var0) {
         addGlobalFilter("", Integer.MAX_VALUE);
      } else {
         removeGlobalFilter("");
      }
   }

   public static synchronized void addGlobalFilter(String var0, int var1) {
      filtermap.put(var0, var1);
   }

   public static synchronized void removeGlobalFilter(String var0) {
      filtermap.remove(var0);
   }

   public static synchronized void clearGlobalFilters() {
      filtermap.clear();
   }

   public static synchronized boolean isGloballyDisabled(ILogger var0) {
      String var1 = Strings.safe(var0.getName());
      if (var1 != null) {
         for (String var3 : filtermap.keySet()) {
            String var4 = var3 + ".";
            if (var3.isEmpty() || var1.equals(var3) || var1.startsWith(var4)) {
               int var5 = (Integer)filtermap.get(var3);
               if (var0.getEnabledLevel() < var5) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static synchronized int getEnabledLevel() {
      return levelForNewLoggers;
   }

   public static synchronized ILogger getLogger() {
      return getLogger((String)null, levelForNewLoggers);
   }

   public static synchronized void status(String var0, Object... var1) {
      getLogger().status(var0, var1);
   }

   public static synchronized ILogger getLogger(Class var0) {
      return getLogger(var0, levelForNewLoggers);
   }

   public static synchronized ILogger getLogger(Class var0, int var1) {
      return getLogger(var0.getName(), var1);
   }

   public static synchronized ILogger getLogger(String var0, int var1) {
      Object var2 = (ILogger)map.get(var0);
      if (var2 == null) {
         var2 = new SimpleLogger(var0, var1);
         map.put(var0, var2);
      }

      return (ILogger)var2;
   }

   public static synchronized ILogger getInternalLogger(Class var0) {
      String var1 = var0.getName();
      Object var2 = (ILogger)map.get(var1);
      if (var2 == null) {
         var2 = new InternalLogger(var1);
         map.put(var1, var2);
      }

      return (ILogger)var2;
   }

   public static void catching(ILogger var0, Throwable var1) {
      catching(var0, var1, null);
   }

   public static void catching(ILogger var0, Throwable var1, String var2) {
      StringBuilder var3 = new StringBuilder();
      if (var2 != null) {
         var3.append(var2).append("\n");
      }

      sanitizeTrace(var1, var3);
      var0.log(60, false, var3.toString());
   }

   public static void catchingDebug(ILogger var0, Throwable var1) {
      catchingDebug(var0, var1, null);
   }

   public static void catchingDebug(ILogger var0, Throwable var1, String var2) {
      StringBuilder var3 = new StringBuilder();
      if (var2 != null) {
         var3.append(var2).append("\n");
      }

      sanitizeTrace(var1, var3);
      var0.log(20, false, var3.toString());
   }

   private static void sanitizeTrace(Throwable var0, StringBuilder var1) {
      String var2 = Throwables.formatStacktrace(var0);
      if (Licensing.isDebugBuild()) {
         var1.append(var2);
      } else {
         int var3 = 0;

         for (String var7 : Strings.splitLines(var2)) {
            if (var3 > 0) {
               if (var7.contains("com.pnfsoftware.jebglobal.")) {
                  break;
               }

               var1.append("\n");
            }

            var1.append(var7);
            var3++;
         }
      }
   }

   public static int parseLevel(String var0) {
      try {
         return Integer.parseInt(var0);
      } catch (NumberFormatException var3) {
         String var1 = var0.trim().toUpperCase();
         switch (var1) {
            case "ALL":
               return 0;
            case "INTERNAL":
               return 19;
            case "TRACE":
               return 10;
            case "DEBUG":
               return 20;
            case "INFO":
               return 30;
            case "WARN":
               return 40;
            case "ERROR":
               return 50;
            case "CATCHING":
               return 60;
            case "STATUS":
               return 100;
            case "DISABLED":
               return Integer.MAX_VALUE;
            default:
               return -1;
         }
      }
   }

   public static String levelToString(int var0) {
      switch (var0) {
         case 0:
            return "ALL";
         case 10:
            return "TRACE";
         case 19:
            return "INTERNAL";
         case 20:
            return "DEBUG";
         case 30:
            return "INFO";
         case 40:
            return "WARN";
         case 50:
            return "ERROR";
         case 60:
            return "CATCHING";
         case Integer.MAX_VALUE:
            return "DISABLED";
         default:
            return "LEVEL_" + var0;
      }
   }

   public static char getPrefixLetterForLevel(int var0) {
      switch (var0) {
         case 10:
            return 'T';
         case 19:
            return 'i';
         case 20:
            return 'D';
         case 30:
            return 'I';
         case 40:
            return 'W';
         case 50:
            return 'E';
         case 60:
            return 'C';
         default:
            return '\u0000';
      }
   }

   public static int getLevelFromPrefixLetter(char var0) {
      switch (var0) {
         case 'C':
            return 60;
         case 'D':
            return 20;
         case 'E':
            return 50;
         case 'I':
            return 30;
         case 'T':
            return 10;
         case 'W':
            return 40;
         case 'i':
            return 19;
         default:
            return -1;
      }
   }
}
