package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bok implements boh {
   @SerId(1)
   private Map q = new HashMap();

   @Override
   public void q() {
      this.q.clear();
   }

   @Override
   public String q(boolean var1, IJavaType var2, int var3, int var4) {
      Object var5 = "unk";
      boolean var6 = false;
      if (var2.isValidForRendering()) {
         int var7 = 0;
         if (var2.isArray()) {
            var7 = var2.getDimensions();
            var2 = var2.getArrayElementType();
         }

         if (var2.isJavaLangString()) {
            var5 = "s";
         } else if (var2.isPrimitive()) {
            if (var2.isBoolean()) {
               var5 = "z";
            } else if (var2.isByte()) {
               var5 = "b";
            } else if (var2.isChar()) {
               var5 = "c";
            } else if (var2.isShort()) {
               var5 = "v";
            } else if (var2.isInt()) {
               var5 = "v";
            } else if (var2.isLong()) {
               var5 = "v";
            } else if (var2.isFloat()) {
               var5 = "f";
            } else if (var2.isDouble()) {
               var5 = "f";
            }
         } else if (var2.isClassOrInterface()) {
            String var8 = JavaTypeUtil.generateClassnameWithoutPackage(var2.getName());
            if (var8.length() >= 3) {
               var5 = var8.substring(1, var8.length() - 1);
               char var9 = var5.charAt(0);
               if (Character.isUpperCase(var9)) {
                  var5 = Character.toLowerCase(var9) + var5.substring(1);
               }

               var6 = var7 == 0;
            }
         }

         if (var7 == 1) {
            var5 = Strings.ff("arr_%s", var5);
         } else if (var7 >= 2) {
            var5 = Strings.ff("arr%d_%s", var7, var5);
         }
      }

      Integer var10 = (Integer)this.q.get(var5);
      if (var10 == null) {
         this.q.put(var5, 0);
         return (String)(var6 ? var5 + "0" : var5);
      } else {
         Integer var11;
         this.q.put(var5, var11 = var10 + 1);
         return var5 + var11;
      }
   }
}
