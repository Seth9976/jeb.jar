package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Perflog {
   private static final ILogger logger = GlobalLog.getLogger();
   private static ReferenceCounter rc = new ReferenceCounter();
   private static Map rcfmap = new HashMap();

   private Perflog() {
   }

   public static Set getCounterNames() {
      return rc.keys();
   }

   public static ReferenceCounter getGlobalCounters() {
      return rc;
   }

   public static Map getFineGrainedCounters() {
      return rcfmap;
   }

   public static ReferenceCounter getFineGrainedCounter(String var0) {
      return (ReferenceCounter)rcfmap.get(var0);
   }

   public static boolean isEmpty() {
      return rc.size() == 0;
   }

   public static void reset() {
      rc.clear();
      rcfmap.clear();
   }

   public static void reset(String var0) {
      rc.remove(var0);
      rcfmap.remove(var0);
   }

   public static void count() {
   }
}
