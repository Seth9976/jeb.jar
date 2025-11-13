package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.List;

public class JvmGenericsMethodSig {
   public String raw;
   public List typeParams;
   public List paramTypes = new ArrayList();
   public JvmGenericsTypeSig returnType;
   public List thrownTypes = new ArrayList();

   public static JvmGenericsMethodSig parse(String var0) {
      JvmGenericsMethodSig var1 = parse(var0, 0);
      Assert.a(var1.raw.length() == var0.length());
      return var1;
   }

   public static JvmGenericsMethodSig parse(String var0, int var1) {
      JvmGenericsMethodSig var2 = new JvmGenericsMethodSig();
      int[] var4 = new int[1];
      var2.typeParams = JvmGenericsUtils.parseTypeParameters(var0, var1, var4);
      int var3 = var4[0];
      char var5 = var0.charAt(var3);
      var3++;
      Assert.a(var5 == '(');

      while (JvmGenericsUtils.isTypeSig(var0, var3)) {
         JvmGenericsTypeSig var6 = JvmGenericsTypeSig.parse(var0, var3);
         var2.paramTypes.add(var6);
         var3 += var6.raw.length();
      }

      var5 = var0.charAt(var3);
      var3++;
      Assert.a(var5 == ')');
      if (var0.charAt(var3) == 'V') {
         var3++;
      } else {
         Assert.a(JvmGenericsUtils.isTypeSig(var0, var3));
         var2.returnType = JvmGenericsTypeSig.parse(var0, var3);
         var3 += var2.returnType.raw.length();
      }

      while (var3 < var0.length() && var0.charAt(var3) == '^') {
         JvmGenericsTypeSig var12 = JvmGenericsTypeSig.parse(var0, ++var3);
         var2.thrownTypes.add(var12);
         var3 += var12.raw.length();
      }

      var2.raw = var0.substring(var1, var3);
      return var2;
   }
}
