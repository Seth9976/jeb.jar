package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class PropertyUtil {
   public static String extractName(String var0) {
      int var1 = var0.lastIndexOf(46);
      return var1 < 0 ? null : var0.substring(var1 + 1);
   }

   public static String extractNs(String var0) {
      int var1 = var0.lastIndexOf(46);
      return var1 < 0 ? null : var0.substring(0, var1);
   }

   public static String levelUp(String var0) {
      int var1 = var0.lastIndexOf(46);
      if (var1 < 0) {
         return null;
      } else {
         int var2 = var0.lastIndexOf(46, var1 - 1);
         return var2 < 0 ? null : var0.substring(0, var2) + var0.substring(var1);
      }
   }

   public static IPropertyDefinitionManager getRoot(IPropertyDefinitionManager var0) {
      while (true) {
         IPropertyDefinitionManager var1 = var0.getParent();
         if (var1 == null) {
            return var0;
         }

         var0 = var1;
      }
   }

   public static IPropertyDefinitionManager getNamespace(IPropertyDefinitionManager var0, String var1) {
      if (var1.isEmpty()) {
         return getRoot(var0);
      } else {
         if (var1.startsWith(".")) {
            var0 = getRoot(var0);
            var1 = var1.substring(1);
         }

         for (String var5 : var1.split("\\.")) {
            var0 = var0.getChild(var5);
            if (var0 == null) {
               return null;
            }
         }

         return var0;
      }
   }

   public static IPropertyDefinition getDefinition(IPropertyDefinitionManager var0, String var1) {
      if (!var1.startsWith(".")) {
         throw new IllegalArgumentException("Illegal fully-qualified name for perperty: " + var1);
      } else {
         var0 = getRoot(var0);
         String[] var2 = Strings.splitall(var1.substring(1), "\\.");

         for (int var3 = 0; var3 < var2.length - 1; var3++) {
            var0 = var0.getChild(var2[var3]);
            if (var0 == null) {
               break;
            }
         }

         if (var0 == null) {
            return null;
         } else {
            String var5 = var2[var2.length - 1];
            return var0.getDefinition(var5);
         }
      }
   }

   public static String formatList(IPropertyDefinitionManager var0, boolean var1) {
      TextBuilder var2 = new TextBuilder(2);
      ArrayDeque var3 = new ArrayDeque();
      var3.add(var0);

      while (!var3.isEmpty()) {
         var0 = (IPropertyDefinitionManager)var3.remove();
         ArrayList var4 = new ArrayList(var0.getDefinitions());
         if (var1) {
            Collections.sort(var4, new PropertyUtil$1());
         }

         for (IPropertyDefinition var6 : var4) {
            String var7 = var0.getNamespace() + "." + var6.getName();
            Strings.ff(var2, "%s: %s\n", var7, var6.getType());
         }

         var3.addAll(var0.getChildren());
      }

      return var2.toString();
   }

   public static String formatTree(IPropertyDefinitionManager var0, boolean var1) {
      TextBuilder var2 = new TextBuilder(2);
      formatTreeRecurse(var2, var0, var1);
      return var2.toString();
   }

   private static void formatTreeRecurse(TextBuilder var0, IPropertyDefinitionManager var1, boolean var2) {
      String var3 = var1.getRegion();
      if (var1.isRoot()) {
         var3 = "<root>";
      }

      var0.appendLine(var3);
      var0.indent();
      ArrayList var4 = new ArrayList(var1.getDefinitions());
      if (var2) {
         Collections.sort(var4, new PropertyUtil$2());
      }

      for (IPropertyDefinition var6 : var4) {
         Strings.ff(var0, "%s: %s\n", var6.getName(), var6.getType());
      }

      for (IPropertyDefinitionManager var8 : var1.getChildren()) {
         formatTreeRecurse(var0, var8, var2);
      }

      var0.unindent();
   }

   public static Enum convertSelectionId(int var0, Class var1) {
      return convertSelectionId(var0, var1, null);
   }

   public static Enum convertSelectionId(int var0, Class var1, Enum var2) {
      if (var1.isEnum()) {
         Enum[] var3 = (Enum[])var1.getEnumConstants();
         if (var0 >= 0 && var0 < var3.length) {
            return var3[var0];
         }
      }

      return var2;
   }

   public static boolean hasDescendants(IPropertyDefinitionManager var0) {
      if (var0.hasDefinitions()) {
         return true;
      } else {
         for (IPropertyDefinitionManager var2 : var0.getChildren()) {
            if (hasDescendants(var2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean hasDescendants(IPropertyDefinitionManager var0, int var1, int var2) {
      for (IPropertyDefinition var4 : var0.getDefinitions()) {
         if ((var4.getFlags() & var1) == var1 && (var4.getFlags() & var2) == 0) {
            return true;
         }
      }

      for (IPropertyDefinitionManager var6 : var0.getChildren()) {
         if (hasDescendants(var6, var1, var2)) {
            return true;
         }
      }

      return false;
   }

   public static PropertyInputSizeHint getSizeHint(int var0) {
      if ((var0 & 16) != 0) {
         return PropertyInputSizeHint.TINY;
      } else if ((var0 & 32) != 0) {
         return PropertyInputSizeHint.SMALL;
      } else if ((var0 & 64) != 0) {
         return PropertyInputSizeHint.LARGE;
      } else {
         return (var0 & 128) != 0 ? PropertyInputSizeHint.EXTRA : PropertyInputSizeHint.MEDIUM;
      }
   }
}
