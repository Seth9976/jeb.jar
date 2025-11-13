package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;

public class bhn {
   public static void pC(JavaOutputSink var0, IJavaType var1) {
      pC(var0, var1, false, false, 0L);
   }

   public static void pC(JavaOutputSink var0, IJavaType var1, boolean var2, boolean var3, long var4) {
      if (!var1.isClassOrInterface() && (!var1.isArray() || !var1.getArrayElementType().isObject())) {
         IJavaType var9 = var1;
         int var7 = 0;
         if (var1.isArray()) {
            var9 = var1.getArrayElementType();
            var7 = var1.getDimensions();
         }

         if (var9.isBoolean()) {
            var0.appendKeyword(JavaKeyword.BOOLEAN);
         } else if (var9.isByte()) {
            var0.appendKeyword(JavaKeyword.BYTE);
         } else if (var9.isChar()) {
            var0.appendKeyword(JavaKeyword.CHAR);
         } else if (var9.isShort()) {
            var0.appendKeyword(JavaKeyword.SHORT);
         } else if (var9.isInt()) {
            var0.appendKeyword(JavaKeyword.INT);
         } else if (var9.isLong()) {
            var0.appendKeyword(JavaKeyword.LONG);
         } else if (var9.isFloat()) {
            var0.appendKeyword(JavaKeyword.FLOAT);
         } else if (var9.isDouble()) {
            var0.appendKeyword(JavaKeyword.DOUBLE);
         } else {
            if (!var9.isVoid()) {
               var0.append(var1.toString());
               throw new RuntimeException();
            }

            var0.appendKeyword(JavaKeyword.VOID);
         }

         for (int var8 = 0; var8 < var7; var8++) {
            var0.append("[]");
         }
      } else {
         IDynamicContentManager var6 = var0.getDynamicContentManager();
         if (var6 != null && var6.generateType(var0, var1, var2, var3, var4)) {
            return;
         }

         var0.append(var1.toString());
      }
   }

   public static String A(JavaOutputSink var0, IJavaType var1) {
      String var2 = null;
      IDynamicContentManager var3 = var0.getDynamicContentManager();
      if (var3 != null) {
         var2 = var3.generatePackageName(var0, var1);
      }

      if (var2 == null) {
         var2 = JavaTypeUtil.generatePackageNameStandardRepresentation(var1.getName());
         var0.append(var2);
      }

      return var2;
   }
}
