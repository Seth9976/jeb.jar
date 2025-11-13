package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

public class Maps {
   public static Object get(Map var0, Object var1) {
      return var0 == null ? null : var0.get(var1);
   }

   public static Object get(Map var0, Object var1, Object var2) {
      return var0 != null && var0.containsKey(var1) ? var0.get(var1) : var2;
   }

   public static HashMap toMap(Object var0, Object var1) {
      return (HashMap)toMap(var0, var1, null);
   }

   public static HashMap toMap(Object var0, Object var1, Object var2, Object var3) {
      HashMap var4 = (HashMap)toMap(var0, var1, null);
      var4.put(var2, var3);
      return var4;
   }

   public static HashMap toMap(Object var0, Object var1, Object var2, Object var3, Object var4, Object var5) {
      HashMap var6 = (HashMap)toMap(var0, var1, null);
      var6.put(var2, var3);
      var6.put(var4, var5);
      return var6;
   }

   public static Map toMap(Object var0, Object var1, Class var2) {
      Object var3 = null;
      if (var2 != null) {
         try {
            var3 = (Map)var2.getConstructor().newInstance();
         } catch (ReflectiveOperationException var4) {
         }
      }

      if (var3 == null) {
         var3 = new HashMap();
      }

      var3.put(var0, var1);
      return (Map)var3;
   }

   public static List getSortedValues(Map var0, boolean var1) {
      ArrayList var2 = new ArrayList(var0.values());
      Collections.sort(var2, new Maps$1(var1));
      return var2;
   }

   public static List getSortedValues(Map var0) {
      return getSortedValues(var0, true);
   }

   public static LinkedHashMap createSortedMapByValue(Map var0, boolean var1) {
      ArrayList var2 = new ArrayList(var0.entrySet());
      Collections.sort(var2, new Maps$2(var1));
      LinkedHashMap var3 = new LinkedHashMap(var2.size());

      for (Entry var5 : var2) {
         var3.put(var5.getKey(), (Comparable)var5.getValue());
      }

      return var3;
   }

   public static String putNoOverwrite(Map var0, String var1, Object var2) {
      String var3 = var1;
      int var4 = 0;

      while (var0.containsKey(var3)) {
         var3 = Strings.safe(var1, "null") + "." + ++var4;
      }

      var0.put(var3, var2);
      return var3;
   }

   public static boolean removeAll(Map var0, Collection var1) {
      return var0.keySet().removeAll(var1);
   }

   public static boolean retainAll(Map var0, Collection var1) {
      return var0.keySet().retainAll(var1);
   }

   public static List addMulti(Map var0, Object var1) {
      Object var2 = (List)var0.get(var1);
      if (var2 == null) {
         var2 = new ArrayList(1);
         var0.put(var1, var2);
      }

      return (List)var2;
   }

   public static List putMulti(Map var0, Object var1, Object var2) {
      Object var3 = (List)var0.get(var1);
      if (var3 == null) {
         var3 = new ArrayList(1);
         var0.put(var1, var3);
      }

      var3.add(var2);
      return (List)var3;
   }

   public static List putMulti(Map var0, Object var1, Collection var2) {
      Object var3 = (List)var0.get(var1);
      if (var3 == null) {
         var3 = new ArrayList(var2.size());
         var0.put(var1, var3);
      }

      var3.addAll(var2);
      return (List)var3;
   }

   public static boolean putMulti(Map var0, Object var1, Object var2, Supplier var3) {
      Collection var4 = (Collection)var0.get(var1);
      if (var4 == null) {
         var4 = (Collection)var3.get();
         var0.put(var1, var4);
      }

      return var4.add(var2);
   }

   public static Collection collectMulti(Map var0) {
      ArrayList var1 = new ArrayList(var0.size());

      for (Collection var3 : var0.values()) {
         var1.addAll(var3);
      }

      return var1;
   }

   public static boolean removeMulti(Map var0, Object var1, Object var2) {
      Collection var3 = (Collection)var0.get(var1);
      if (var3 == null) {
         return false;
      } else if (!var3.remove(var2)) {
         return false;
      } else {
         if (var3.isEmpty()) {
            var0.remove(var1);
         }

         return true;
      }
   }

   public static int removeForValue(Map var0, Object var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null value");
      } else {
         ArrayList var3 = new ArrayList();

         for (Entry var5 : var0.entrySet()) {
            if (var2 && var1 == var5.getValue()) {
               var3.add(var5.getKey());
            } else if (!var2 && var1.equals(var5.getValue())) {
               var3.add(var5.getKey());
            }
         }

         var0.keySet().removeAll(var3);
         return var3.size();
      }
   }

   public static List getSortedEntriesByValue(Map var0) {
      return getSortedEntriesByValue(var0, false);
   }

   public static List getSortedEntriesByValue(Map var0, boolean var1) {
      ArrayList var2 = new ArrayList(var0.entrySet());
      Collections.sort(var2, new Maps$3(var1));
      return var2;
   }
}
