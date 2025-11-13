package com.pnfsoftware.jeb.util.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CollectionUtil {
   public static boolean containsNull(Iterable var0) {
      for (Object var2 : var0) {
         if (var2 == null) {
            return true;
         }
      }

      return false;
   }

   public static boolean containsNonNull(Iterable var0) {
      for (Object var2 : var0) {
         if (var2 != null) {
            return true;
         }
      }

      return false;
   }

   public static boolean containsReference(Collection var0, Object var1) {
      if (var0 == null) {
         return false;
      } else {
         for (Object var3 : var0) {
            if (var3 == var1) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean contains(Collection var0, Object var1) {
      return var0 != null && var0.contains(var1);
   }

   public static Set intersect(Collection var0, Collection var1) {
      HashSet var2 = new HashSet();
      if (!var0.isEmpty() && !var1.isEmpty()) {
         for (Object var4 : var0) {
            if (var1.contains(var4)) {
               var2.add(var4);
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   public static boolean hasIntersection(Collection var0, Collection var1) {
      if (!var0.isEmpty() && !var1.isEmpty()) {
         for (Object var3 : var0) {
            if (var1.contains(var3)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static Object binarySearch(List var0, Comparable var1, IExtractor var2) {
      int var3 = binarySearchEx(var0, var1, var2, null);
      return var3 < 0 ? null : var0.get(var3);
   }

   public static int binarySearchEx(List var0, Comparable var1, IExtractor var2, Comparator var3) {
      int var4 = 0;
      int var5 = var0.size() - 1;

      while (var4 <= var5) {
         int var6 = var4 + var5 >>> 1;
         Comparable var7 = (Comparable)var2.extract(var0.get(var6));
         int var8 = var3 != null ? var3.compare(var7, var1) : var7.compareTo(var1);
         if (var8 < 0) {
            var4 = var6 + 1;
         } else {
            if (var8 <= 0) {
               return var6;
            }

            var5 = var6 - 1;
         }
      }

      return -(var4 + 1);
   }

   public static boolean compareByReference(Collection var0, Collection var1, boolean var2) {
      if (var0 == null && var1 == null) {
         return true;
      } else if (var0 != null && var1 != null) {
         if (var0.size() != var1.size()) {
            return false;
         } else {
            if (var2) {
               Iterator var3 = var0.iterator();
               Iterator var4 = var1.iterator();

               while (var3.hasNext()) {
                  if (var3.next() != var4.next()) {
                     return false;
                  }
               }
            } else {
               IdentityHashMap var6 = new IdentityHashMap();

               for (Object var5 : var0) {
                  var6.put(var5, null);
               }

               for (Object var9 : var1) {
                  if (!var6.containsKey(var9)) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean compare(Collection var0, Collection var1, boolean var2) {
      if (var0 == null && var1 == null) {
         return true;
      } else if (var0 != null && var1 != null) {
         if (var0.size() != var1.size()) {
            return false;
         } else {
            if (!var2) {
               ArrayList var9 = new ArrayList(var1);

               for (Object var11 : var0) {
                  int var12 = 0;

                  for (Object var8 : var9) {
                     if (var11 == null && var8 == null || var11 != null && var11.equals(var8)) {
                        break;
                     }

                     var12++;
                  }

                  if (var12 >= var9.size()) {
                     return false;
                  }

                  var9.remove(var12);
               }
            } else {
               Iterator var3 = var0.iterator();
               Iterator var4 = var1.iterator();

               while (var3.hasNext()) {
                  Object var5 = var3.next();
                  Object var6 = var4.next();
                  if ((var5 != null || var6 != null) && (var5 == null && var6 != null || var5 != null && var6 == null || var5 != null && !var5.equals(var6))) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static int count(Collection var0, Object var1) {
      int var2 = 0;

      for (Object var4 : var0) {
         if (var4 == null) {
            if (var1 == null) {
               var2++;
            }
         } else if (var4.equals(var1)) {
            var2++;
         }
      }

      return var2;
   }

   public static int identityCount(Collection var0, Object var1) {
      int var2 = 0;

      for (Object var4 : var0) {
         if (var4 == var1) {
            var2++;
         }
      }

      return var2;
   }

   public static Collection addNonNulls(Collection var0, Collection var1) {
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
   public static Collection addNonNulls(Collection var0, Object... var1) {
      if (var1 != null) {
         for (Object var5 : var1) {
            if (var5 != null) {
               var0.add(var5);
            }
         }
      }

      return var0;
   }

   public static Iterable doubleCollectionIterable(Collection var0, Collection var1) {
      if (var0 != null && var1 != null) {
         return new CollectionUtil$1(var0, var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static Object first(Collection var0) {
      Object var1 = null;
      if (var0 != null) {
         Iterator var2 = var0.iterator();
         if (var2.hasNext()) {
            return var2.next();
         }
      }

      return var1;
   }

   public static Object last(Collection var0) {
      Object var1 = null;
      if (var0 != null) {
         for (Object var3 : var0) {
            var1 = var3;
         }
      }

      return var1;
   }

   private static class DoubleCollIterator implements Iterator {
      private Collection coll1;
      private Collection coll2;
      private Iterator it;

      private DoubleCollIterator(Collection var1, Collection var2) {
         this.coll1 = var1;
         this.coll2 = var2;
         this.it = var1.iterator();
      }

      @Override
      public boolean hasNext() {
         if (this.coll1 != null) {
            this.it = this.coll1.iterator();
            this.coll1 = null;
            if (this.it.hasNext()) {
               return true;
            } else {
               this.it = this.coll2.iterator();
               this.coll2 = null;
               return this.it.hasNext();
            }
         } else if (this.it.hasNext()) {
            return true;
         } else if (this.coll2 == null) {
            return false;
         } else {
            this.it = this.coll2.iterator();
            this.coll2 = null;
            return this.it.hasNext();
         }
      }

      @Override
      public Object next() {
         return this.it.next();
      }
   }
}
