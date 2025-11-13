package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.List;

public class JvmGenericsUtils {
   public static boolean isTypeSig(String var0, int var1) {
      if (var1 >= var0.length()) {
         return false;
      } else {
         switch (var0.charAt(var1)) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'L':
            case 'S':
            case 'T':
            case 'Z':
            case '[':
               return true;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            default:
               return false;
         }
      }
   }

   static List parseTypeParameters(String var0, int var1, int[] var2) {
      if (var0.charAt(var1) != '<') {
         return null;
      } else {
         var1++;
         ArrayList var3 = new ArrayList();

         label40:
         while (true) {
            int var5 = DexUtil.parseSimpleName(var0, var1, 0);
            Assert.a(var5 > var1 && var5 < var0.length());
            String var6 = var0.substring(var1, var5);
            var1 += var6.length();
            char var4 = var0.charAt(var1);
            Assert.a(var4 == ':');
            var1++;
            JvmGenericsTypeSig var7 = null;
            if (isTypeSig(var0, var1)) {
               JvmGenericsTypeSig var8 = JvmGenericsTypeSig.parse(var0, var1);
               var1 += var8.raw.length();
               var7 = var8;
            }

            ArrayList var15 = new ArrayList();

            while (true) {
               var4 = var0.charAt(var1);
               if (var4 != ':') {
                  var3.add(new JvmGenericsClassSig.TypeParam(var6, var7, var15));
                  if (var4 == '>') {
                     break label40;
                  }
                  break;
               }

               JvmGenericsTypeSig var9 = JvmGenericsTypeSig.parse(var0, ++var1);
               var1 += var9.raw.length();
               var15.add(var9);
            }
         }

         var2[0] = ++var1;
         return var3;
      }
   }
}
