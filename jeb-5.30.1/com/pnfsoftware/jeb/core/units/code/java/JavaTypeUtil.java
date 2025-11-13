package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPrototype;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.SortedMap;
import java.util.TreeMap;

public class JavaTypeUtil {
   private static final ILogger logger = GlobalLog.getLogger(JavaTypeUtil.class);

   public static boolean isLegalInternalClassname(String var0) {
      return var0.length() >= 2 && var0.charAt(0) == 'L' && var0.charAt(var0.length() - 1) == ';' && var0.indexOf(46) == -1;
   }

   public static void checkLegalInternalClassname(String var0) {
      if (!isLegalInternalClassname(var0)) {
         throw new RuntimeException("Invalid internal class name: " + var0);
      }
   }

   public static String generateClassnameStandardRepresentation(String var0) {
      checkLegalInternalClassname(var0);
      return var0.substring(1, var0.length() - 1).replace('/', '.');
   }

   public static String generateClassnameWithoutPackage(String var0) {
      checkLegalInternalClassname(var0);
      String var1 = var0.substring(1, var0.length() - 1);
      int var2 = var1.lastIndexOf(47);
      return var2 < 0 ? var0 : "L" + var1.substring(var2 + 1) + ";";
   }

   public static String generatePackageNameStandardRepresentation(String var0) {
      checkLegalInternalClassname(var0);
      String var1 = var0.substring(1, var0.length() - 1);
      int var2 = var1.lastIndexOf(47);
      return var2 < 0 ? "" : var1.substring(0, var2).replace('/', '.');
   }

   public static IJavaType[] parseShortyPrototype(String var0, IJavaTypeFactory var1) {
      if (var0.length() < 1) {
         throw new RuntimeException();
      } else {
         int var2 = var0.length();
         IJavaType[] var3 = new IJavaType[var2];

         for (int var4 = 0; var4 < var2; var4++) {
            IJavaType var5 = var1.letterToType(var0.charAt(var4));
            if (var4 >= 1 && var5.isVoid()) {
               throw new RuntimeException();
            }

            var3[var4] = var5;
         }

         return var3;
      }
   }

   public static IJavaType[] parseFullPrototype(IDexPrototype var0, IJavaTypeFactory var1) {
      String[] var2 = var0.getParameterSignatures(false);
      int var3 = 1 + var2.length;
      String[] var4 = new String[var3];
      var4[0] = var0.getReturnTypeSignature(false);

      for (int var5 = 1; var5 <= var2.length; var5++) {
         var4[var5] = var2[var5 - 1];
      }

      IJavaType[] var13 = new IJavaType[var3];

      for (int var6 = 0; var6 < var3; var6++) {
         String var7 = var4[var6];
         int var8 = 0;
         char var9 = var7.charAt(var8++);

         int var10;
         for (var10 = 0; var9 == '['; var9 = var7.charAt(var8++)) {
            var10++;
         }

         IJavaType var11 = var1.letterToType(var9);
         if (var11 == var1.getGenericObjectWildcard()) {
            String var12 = var7.substring(var8 - 1);
            var11 = var1.createType(var12);
         }

         if (var10 >= 1) {
            var11 = var1.createArrayType(var11, var10);
         }

         if (var6 >= 1 && var11.isVoid()) {
            throw new RuntimeException();
         }

         var13[var6] = var11;
      }

      return var13;
   }

   public static SortedMap parseMethodParameters(IDexUnit var0, IDexMethod var1, IJavaTypeFactory var2) {
      TreeMap var3 = new TreeMap();
      IDexMethodData var4 = var1.getData();
      int var5 = var4.getCodeItem().getRegisterCount();
      IJavaType[] var6 = parseFullPrototype(var0.getMethod(var1.getIndex()).getPrototype(), var2);
      int var7 = 0;

      for (int var8 = var6.length - 1; var8 >= 1; var8--) {
         if (var6[var8].isSingleSlot()) {
            var7++;
         } else {
            if (!var6[var8].isDoubleSlot()) {
               throw new RuntimeException("Unknown type: " + var6[var8]);
            }

            var7 += 2;
         }
      }

      if (!var4.isStatic()) {
         var7++;
      }

      if (var5 < var7) {
         logger.warn(Strings.ff(S.L("Invalid frame size: %d (minimum size expected: %d)"), var5, var7));
      }

      int var10 = var5 - 1;

      for (int var9 = var6.length - 1; var9 >= 1; var9--) {
         if (var10 < 0) {
            throw new RuntimeException("method frame is too small");
         }

         if (var6[var9].isSingleSlot()) {
            var3.put(var10, var6[var9]);
         } else {
            if (!var6[var9].isDoubleSlot()) {
               throw new RuntimeException("unsupported type");
            }

            if (--var10 < 0) {
               throw new RuntimeException("method frame is too small");
            }

            var3.put(var10, var6[var9]);
         }

         var10--;
      }

      if (!var4.isStatic()) {
         if (var10 < 0) {
            throw new RuntimeException("method frame is too small");
         }

         String var11 = var0.getMethod(var4.getMethodIndex()).getClassTypeSignature(false);
         var3.put(var10, var2.parseType(var11));
      }

      var3.put(-1, var6[0]);
      return var3;
   }
}
