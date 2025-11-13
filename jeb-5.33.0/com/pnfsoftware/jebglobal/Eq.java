package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class Eq {
   public static boolean pC(int var0) {
      switch (var0) {
         case 3:
         case 256:
         case 257:
         case 258:
         case 259:
         case 260:
         case 384:
            return true;
         default:
            return false;
      }
   }

   public static String A(int var0) {
      switch (var0) {
         case 0:
            return "NULL_TYPE";
         case 1:
            return "STRING_POOL_TYPE";
         case 2:
            return "TABLE_TYPE";
         case 3:
            return "XML_TYPE";
         case 256:
            return "XML_START_NAMESPACE_TYPE";
         case 257:
            return "XML_END_NAMESPACE_TYPE";
         case 258:
            return "XML_START_ELEMENT_TYPE";
         case 259:
            return "XML_END_ELEMENT_TYPE";
         case 260:
            return "XML_CDATA_TYPE";
         case 384:
            return "XML_RESOURCE_MAP_TYPE";
         case 512:
            return "TABLE_PACKAGE_TYPE";
         case 513:
            return "TABLE_TYPE_TYPE";
         case 514:
            return "TABLE_TYPE_SPEC_TYPE";
         case 515:
            return "TABLE_LIBRARY_TYPE";
         case 516:
            return "TABLE_OVERLAYABLE_TYPE";
         case 517:
            return "TABLE_OVERLAYABLE_POLICY_TYPE";
         case 518:
            return "TABLE_STAGED_ALIAS_TYPE";
         default:
            return Strings.ff("%Xh(UNKNOWN)", var0);
      }
   }

   public static final String[] pC(String var0) {
      String var1 = null;
      String var2 = null;
      int var4 = var0.indexOf(58);
      if (var4 >= 0) {
         var1 = var0.substring(0, var4);
         var0 = var0.substring(var4 + 1);
      }

      var4 = var0.indexOf(47);
      if (var4 >= 0) {
         var2 = var0.substring(0, var4);
         var0 = var0.substring(var4 + 1);
      }

      return new String[]{var1, var2, var0};
   }
}
