package com.pnfsoftware.jeb.util.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Lists {
   public static int addSorted(List var0, Comparable var1) {
      int var2;
      for (var2 = 0; var2 < var0.size(); var2++) {
         if (var1.compareTo((Comparable)var0.get(var2)) <= 0) {
            var0.add(var2, var1);
            break;
         }
      }

      var0.add(var2, var1);
      return var2;
   }

   public static ArrayList createArrayList() {
      return new ArrayList();
   }

   public static ArrayList createArrayList(Object var0) {
      ArrayList var1 = new ArrayList();
      var1.add(var0);
      return var1;
   }

   @SafeVarargs
   public static ArrayList createArrayList(Collection... var0) {
      int var1 = 0;

      for (Collection var5 : var0) {
         var1 += var5.size();
      }

      ArrayList var7 = new ArrayList(var1);

      for (Collection var6 : var0) {
         var7.addAll(var6);
      }

      return var7;
   }

   @SafeVarargs
   public static ArrayList newArrayList(Object... var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (Object var5 : var0) {
         var1.add(var5);
      }

      return var1;
   }

   public static List add(List var0, List var1) {
      if (var1 != null) {
         var0.addAll(var1);
      }

      return var0;
   }

   public static List addNonNulls(List var0, List var1) {
      if (var1 != null) {
         for (Object var3 : var1) {
            if (var3 != null) {
               var0.add(var3);
            }
         }
      }

      return var0;
   }

   @SafeVarargs
   public static List addNonNulls(List var0, Object... var1) {
      for (Object var5 : var1) {
         if (var5 != null) {
            var0.add(var5);
         }
      }

      return var0;
   }

   public static List subList(List var0, int var1) {
      return var0.subList(var1, var0.size());
   }

   public static Object get(List var0, int var1, Object var2) {
      return var0 != null && var1 >= 0 && var1 < var0.size() ? var0.get(var1) : var2;
   }

   public static Object get(List var0, int var1) {
      return get(var0, var1, null);
   }

   public static Object getFirst(List var0) {
      return get(var0, 0);
   }

   public static ListIterator reverseIterator(List var0) {
      return var0.listIterator(var0.size());
   }

   public static List reverse(List var0) {
      int var1 = var0.size();
      int var2 = 0;

      for (int var3 = var1 - 1; var2 < var1 / 2; var3--) {
         Object var4 = var0.get(var2);
         var0.set(var2, var0.get(var3));
         var0.set(var3, var4);
         var2++;
      }

      return var0;
   }

   public static List map(Collection var0, Function var1) {
      return (List)var0.stream().map(var1).collect(Collectors.toList());
   }

   public static Object set(List var0, int var1, Object var2, Object var3) {
      if (var1 < 0) {
         throw new IndexOutOfBoundsException("Negative index: " + var1);
      } else {
         while (var1 >= var0.size()) {
            var0.add(var3);
         }

         return var0.set(var1, var2);
      }
   }

   public static Object set(List var0, int var1, Object var2) {
      return set(var0, var1, var2, null);
   }

   public static void add(List var0, int var1, Object var2, Object var3) {
      if (var1 < 0) {
         throw new IndexOutOfBoundsException("Negative index: " + var1);
      } else {
         while (var1 > var0.size()) {
            var0.add(var3);
         }

         var0.add(var1, var2);
      }
   }

   public static void add(List var0, int var1, Object var2) {
      add(var0, var1, var2, null);
   }

   public static List safe(List var0) {
      return var0 == null ? Collections.emptyList() : var0;
   }

   public static List fromIterator(Iterable var0) {
      return (List)StreamSupport.stream(var0.spliterator(), false).collect(Collectors.toList());
   }

   public static void insertIntoSortedList(List var0, Comparable var1) {
      insertIntoSortedList(var0, var1, null, false);
   }

   public static void insertIntoSortedList(List var0, Object var1, Comparator var2, boolean var3) {
      int var4 = 0;
      int var5 = 0;
      int var6 = var0.size();
      int var7 = 0;
      int var8 = var6;

      while (var7 < var8) {
         var4 = var7 + (var8 - var7) / 2;
         Object var9 = var0.get(var4);
         if (var2 == null) {
            var5 = ((Comparable)var1).compareTo(var9);
         } else {
            var5 = var2.compare(var1, var9);
         }

         if (var5 == 0) {
            break;
         }

         if (var3) {
            var5 = -var5;
         }

         if (var5 == 1) {
            var7 = var4 + 1;
         } else {
            var8 = var4;
         }
      }

      if (var5 > 0) {
         var0.add(var8, var1);
      } else if (var5 < 0) {
         var0.add(var7, var1);
      } else {
         var0.add(var4, var1);
      }
   }

   public static void rotate(List var0, boolean var1) {
      rotate(var0, var1, 0, var0.size() - 1);
   }

   public static void rotate(List var0, boolean var1, int var2, int var3) {
      if (var2 < var3) {
         if (var1) {
            Object var4 = var0.get(var3);

            for (int var5 = var3; var5 > var2; var5--) {
               var0.set(var5, var0.get(var5 - 1));
            }

            var0.set(var2, var4);
         } else {
            Object var6 = var0.get(var2);

            for (int var7 = var2; var7 < var3; var7++) {
               var0.set(var7, var0.get(var7 + 1));
            }

            var0.set(var3, var6);
         }
      }
   }

   public static Object last(List var0) {
      return var0 != null && !var0.isEmpty() ? var0.get(var0.size() - 1) : null;
   }
}
