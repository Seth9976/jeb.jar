package com.pnfsoftware.jeb.util.reflect;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;

public class ReflectionHelper {
   private static final ILogger logger = GlobalLog.getLogger(ReflectionHelper.class);

   public static boolean hasNoArgConstructor(Class var0) {
      try {
         Constructor var1 = var0.getDeclaredConstructor();
         return var1 != null;
      } catch (NoSuchMethodException var2) {
         return false;
      }
   }

   public static Object newInstance2(Constructor var0) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      boolean var1 = var0.canAccess(null);
      if (!var1) {
         var0.setAccessible(true);
      }

      Object var2;
      try {
         var2 = var0.newInstance();
      } finally {
         if (!var1) {
            var0.setAccessible(false);
         }
      }

      return var2;
   }

   public static Object invoke2(Method var0, Object var1, Object... var2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      boolean var3 = var0.canAccess(var1);
      if (!var3) {
         var0.setAccessible(true);
      }

      Object var4;
      try {
         var4 = var0.invoke(var1, var2);
      } finally {
         if (!var3) {
            var0.setAccessible(false);
         }
      }

      return var4;
   }

   public static void set2(Field var0, Object var1, Object var2) throws IllegalAccessException, IllegalArgumentException {
      boolean var3 = var0.canAccess(var1);
      if (!var3) {
         var0.setAccessible(true);
      }

      try {
         var0.set(var1, var2);
      } finally {
         if (!var3) {
            var0.setAccessible(false);
         }
      }
   }

   public static Object get2(Field var0, Object var1) throws IllegalAccessException, IllegalArgumentException {
      boolean var2 = var0.canAccess(var1);
      if (!var2) {
         var0.setAccessible(true);
      }

      Object var3;
      try {
         var3 = var0.get(var1);
      } finally {
         if (!var2) {
            var0.setAccessible(false);
         }
      }

      return var3;
   }

   public static Class removeDimensions(Class var0) {
      while (var0.isArray()) {
         var0 = var0.getComponentType();
      }

      return var0;
   }

   public static Class reduceDimensions(Class var0) {
      while (var0.isArray()) {
         Class var1 = var0.getComponentType();
         if (!var1.isPrimitive()) {
            var0 = var1;
            continue;
         }
         break;
      }

      return var0;
   }

   public static Object instantiateSafe(Class var0) {
      try {
         return var0.getConstructor().newInstance();
      } catch (ReflectiveOperationException var2) {
         logger.catching(var2);
         return null;
      }
   }

   public static boolean isInstance(Object var0, Collection var1) {
      if (var0 == null) {
         return false;
      } else {
         for (Class var3 : var1) {
            if (var3.isInstance(var0)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean isInstance(Object var0, Class... var1) {
      if (var0 == null) {
         return false;
      } else {
         for (Class var5 : var1) {
            if (var5.isInstance(var0)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean subtypeOf(Class var0, Class var1) {
      if (var0 != null && var1 != null) {
         if (var0 != var1 && var0.getSuperclass() != var1) {
            ArrayDeque var2 = new ArrayDeque();
            var2.add(var0);
            HashSet var3 = new HashSet();

            while (!var2.isEmpty()) {
               var0 = (Class)var2.remove();
               if (var3.add(var0)) {
                  if (var0 == var1) {
                     return true;
                  }

                  for (Class var7 : var0.getInterfaces()) {
                     var2.add(var7);
                  }

                  if (var0.getSuperclass() != null) {
                     var2.add(var0.getSuperclass());
                  }
               }
            }

            return false;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }
}
