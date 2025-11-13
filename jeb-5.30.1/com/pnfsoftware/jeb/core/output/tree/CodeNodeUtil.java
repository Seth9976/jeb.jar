package com.pnfsoftware.jeb.core.output.tree;

import com.pnfsoftware.jeb.core.units.code.ICodeClass;
import com.pnfsoftware.jeb.core.units.code.ICodeField;
import com.pnfsoftware.jeb.core.units.code.ICodeItem;
import com.pnfsoftware.jeb.core.units.code.ICodeMethod;
import com.pnfsoftware.jeb.core.units.code.ICodePackage;
import com.pnfsoftware.jeb.core.units.code.ICodeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackage;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CodeNodeUtil {
   public static final int IS_ARTIFICIAL = 16;
   public static final int IS_INTERNAL = 256;
   public static final int IS_PACKAGE = 32768;
   public static final int IS_TYPE = 65536;
   public static final int IS_CLASS = 131072;
   public static final int IS_FIELD = 262144;
   public static final int IS_METHOD = 524288;

   public static boolean meetsConditions(ICodeNode var0, int var1, int var2) {
      ICodeItem var3 = var0.getObject();
      int var4 = 0;
      if (var3 instanceof ICodePackage) {
         var4 |= 32768;
      }

      if (var3 instanceof ICodeType) {
         var4 |= 65536;
      }

      if (var3 instanceof ICodeClass) {
         var4 |= 131072;
      }

      if (var3 instanceof ICodeField) {
         var4 |= 262144;
      }

      if (var3 instanceof ICodeMethod) {
         var4 |= 524288;
      }

      if ((var3.getGenericFlags() & -2147483648) != 0) {
         var4 |= 256;
      }

      if ((var3.getGenericFlags() & 1073741824) != 0) {
         var4 |= 16;
      }

      return (var4 & var1) == var1 && (var4 & var2) == 0;
   }

   public static boolean mustBe(ICodeNode var0, int var1) {
      return meetsConditions(var0, var1, 0);
   }

   public static boolean cannotBe(ICodeNode var0, int var1) {
      return meetsConditions(var0, 0, var1);
   }

   public static List getChildren(ICodeNode var0, int var1, int var2) {
      ArrayList var3 = new ArrayList(var0.getChildren());
      Iterator var4 = var3.iterator();

      while (var4.hasNext()) {
         ICodeNode var5 = (ICodeNode)var4.next();
         if (!meetsConditions(var5, var1, var2)) {
            var4.remove();
         }
      }

      return var3;
   }

   public static boolean hasChildren(ICodeNode var0, int var1, int var2) {
      for (ICodeNode var5 : new ArrayList(var0.getChildren())) {
         if (meetsConditions(var5, var1, var2)) {
            return true;
         }
      }

      return false;
   }

   public static String getPackageNameFromHierarchy(ICodeNode var0) {
      String var1 = getPackageItemName(var0);
      if (var1 == null) {
         return null;
      } else {
         ArrayDeque var2 = new ArrayDeque();
         var2.push(var1);

         for (ICodeNode var3 = var0.getParent(); var3 != null; var3 = var3.getParent()) {
            String var4 = getPackageItemName(var3);
            if (var4 == null) {
               break;
            }

            var2.push(var4);
         }

         return var2.size() > 1 ? Strings.joinv("::", var2.toArray()) : var1;
      }
   }

   private static String getPackageItemName(ICodeNode var0) {
      if (var0 == null) {
         return null;
      } else {
         ICodeItem var1 = var0.getObject();
         return !(var1 instanceof IPackage) ? null : var1.getName();
      }
   }
}
