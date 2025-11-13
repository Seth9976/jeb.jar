package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class JvmGenericsClassSig {
   public String raw;
   public List typeParams = new ArrayList();
   public JvmGenericsTypeSig superType;
   public List interfaceTypes = new ArrayList();

   public static JvmGenericsClassSig parse(String var0) {
      JvmGenericsClassSig var1 = parse(var0, 0);
      Assert.a(var1.raw.length() == var0.length());
      return var1;
   }

   public static JvmGenericsClassSig parse(String var0, int var1) {
      JvmGenericsClassSig var2 = new JvmGenericsClassSig();
      int var11 = var1;
      if (var0.charAt(var1) == '<') {
         var11 = var1 + 1;

         label51:
         while (true) {
            int var5 = DexUtil.parseSimpleName(var0, var11, 0);
            Assert.a(var5 > var11 && var5 < var0.length());
            String var6 = var0.substring(var11, var5);
            var11 += var6.length();
            char var4 = var0.charAt(var11);
            Assert.a(var4 == ':');
            var11++;
            JvmGenericsTypeSig var7 = null;
            if (JvmGenericsUtils.isTypeSig(var0, var11)) {
               JvmGenericsTypeSig var8 = JvmGenericsTypeSig.parse(var0, var11);
               var11 += var8.raw.length();
               var7 = var8;
            }

            ArrayList var16 = new ArrayList();

            while (true) {
               var4 = var0.charAt(var11);
               if (var4 != ':') {
                  var2.typeParams.add(new JvmGenericsClassSig.TypeParam(var6, var7, var16));
                  if (var4 == '>') {
                     var11++;
                     break label51;
                  }
                  break;
               }

               JvmGenericsTypeSig var9 = JvmGenericsTypeSig.parse(var0, ++var11);
               var11 += var9.raw.length();
               var16.add(var9);
            }
         }
      }

      var2.superType = JvmGenericsTypeSig.parse(var0, var11);
      var11 += var2.superType.raw.length();

      while (var11 < var0.length()) {
         Assert.a(var0.charAt(var11) == 'L');
         JvmGenericsTypeSig var15 = JvmGenericsTypeSig.parse(var0, var11);
         var2.interfaceTypes.add(var15);
         var11 += var15.raw.length();
      }

      var2.raw = var0.substring(var1, var11);
      return var2;
   }

   @Override
   public String toString() {
      return Strings.ff("%s %s %s", this.typeParams, this.superType, this.interfaceTypes);
   }

   public static class TypeParam {
      String ident;
      JvmGenericsTypeSig classBound;
      List interfaceBounds;

      public TypeParam(String var1, JvmGenericsTypeSig var2, List var3) {
         this.ident = var1;
         this.classBound = var2;
         this.interfaceBounds = var3;
      }

      @Override
      public String toString() {
         return Strings.ff("ident=%s cbound=%s ibounds=%s", this.ident, this.classBound, this.interfaceBounds);
      }
   }
}
