package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class JvmGenericsTypeSig {
   public String raw;
   public int arrayDim;
   public char baseType;
   public String typeVar;
   public List pkgElts = new ArrayList();
   public List classes = new ArrayList();

   public static JvmGenericsTypeSig parse(String var0) {
      JvmGenericsTypeSig var1 = parse(var0, 0);
      Assert.a(var1.raw.length() == var0.length());
      return var1;
   }

   public static JvmGenericsTypeSig parse(String var0, int var1) {
      JvmGenericsTypeSig var2 = new JvmGenericsTypeSig();
      char var4 = var0.charAt(var1);
      int var3 = var1 + 1;

      int var5;
      for (var5 = 0; var4 == '['; var3++) {
         var5++;
         var4 = var0.charAt(var3);
      }

      switch (var4) {
         case 'B':
         case 'C':
         case 'D':
         case 'F':
         case 'I':
         case 'J':
         case 'S':
         case 'Z':
            var2.baseType = var4;
            break;
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         default:
            if (var4 != 'L') {
               if (var4 == 'T') {
                  int var16 = DexUtil.parseSimpleName(var0, var3, 0);
                  Assert.a(var16 > var3 && var16 < var0.length());
                  var2.typeVar = var0.substring(var3, var16);
                  var4 = var0.charAt(var16);
                  var3 = var16 + 1;
                  Assert.a(var4 == ';');
               } else {
                  Assert.fail();
               }
            } else {
               while (var3 < var0.length()) {
                  int var6 = DexUtil.parseSimpleName(var0, var3, 0);
                  Assert.a(var6 > var3 && var6 < var0.length());
                  String var7 = var0.substring(var3, var6);
                  var4 = var0.charAt(var6);
                  var3 = var6 + 1;
                  if (var4 == '/') {
                     Assert.a(var2.classes.isEmpty());
                     var2.pkgElts.add(var7);
                  } else {
                     ArrayList var8 = new ArrayList();
                     if (var4 == '<') {
                        var4 = var0.charAt(var3);
                        var3++;
                        JvmGenericsTypeSig var9 = null;
                        if (var4 == '+' || var4 == '-') {
                           var9 = parse(var0, var3);
                           var3 += var9.raw.length();
                        } else if (var4 != '*') {
                           Assert.fail();
                        }

                        var8.add(new JvmGenericsTypeSig.TypeArg(var4, var9));
                        var4 = var0.charAt(var3);
                        var3++;
                        Assert.a(var4 == '>');
                        var4 = var0.charAt(var3);
                        var3++;
                     }

                     var2.classes.add(new Couple(var7, var8));
                     if (var4 != '.') {
                        Assert.a(var4 == ';');
                        break;
                     }
                  }
               }
            }
      }

      var2.arrayDim = var5;
      var2.raw = var0.substring(var1, var3);
      return var2;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.arrayDim > 0) {
         var1.append("Array ");
      }

      if (this.baseType != 0) {
         var1.append(this.baseType);
      } else if (this.typeVar != null) {
         Strings.ff(var1, "<%s>", this.typeVar);
      } else {
         Strings.ff(var1, "%s %s", Strings.join(" ", this.pkgElts), Strings.join(" ", this.classes));
      }

      return var1.toString();
   }

   public static class TypeArg {
      public int kind;
      public JvmGenericsTypeSig type;

      public TypeArg(int var1, JvmGenericsTypeSig var2) {
         this.kind = var1;
         this.type = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("%c%s", (char)this.kind, this.type);
      }
   }
}
