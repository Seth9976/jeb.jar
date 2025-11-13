package com.pnfsoftware.jeb.core.units.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DFAUtil {
   public static String formatChain(IDFA var0, Map var1) {
      StringBuilder var2 = new StringBuilder();
      ICFGOwnerContext var3 = var0.getVariableInformationProvider();
      Object var4;
      if (var1 instanceof HashMap) {
         var4 = new TreeMap(var1);
      } else {
         var4 = var1;
      }

      int var5 = 0;

      for (int var7 : var4.keySet()) {
         if (var5 > 0) {
            var2.append(" ");
         }

         var2.append("(");
         if (var3 != null) {
            var2.append(var3.getName(var7));
         } else {
            var2.append(var7 >= 0 ? Integer.toHexString(var7) : "-" + Integer.toHexString(-var7));
         }

         var2.append(")={");
         ArrayList var8 = new ArrayList((Collection)var4.get(var7));
         Collections.sort(var8, new DFAUtil$1());
         int var9 = 0;

         for (Number var11 : var8) {
            if (var9 >= 1) {
               var2.append(",");
            }

            if (var11 instanceof Integer) {
               String var12 = var11.intValue() == -1 ? "other_block" : Integer.toHexString(var11.intValue()).toUpperCase();
               var2.append(var12);
            } else if (var11 instanceof Long) {
               String var13 = var11.longValue() == -1L ? "init" : Long.toHexString(var11.longValue()).toUpperCase();
               var2.append("@").append(var13);
            }

            var9++;
         }

         var2.append("}");
         var5++;
      }

      return var2.toString();
   }

   public static String formatVars(IDFA var0, Collection var1) {
      return formatVars(var0, var1, false);
   }

   public static String formatVars(IDFA var0, Collection var1, boolean var2) {
      StringBuilder var3 = new StringBuilder();
      ICFGOwnerContext var4 = var0.getVariableInformationProvider();
      Object var5 = var1;
      if (var2) {
         var5 = new ArrayList();
         ((ArrayList)var5).sort(null);
      }

      int var6 = 0;

      for (int var8 : var5) {
         if (var6 > 0) {
            var3.append(", ");
         }

         if (var4 != null) {
            var3.append(var4.getName(var8));
         } else {
            var3.append(var8 >= 0 ? Integer.toHexString(var8) : "-" + Integer.toHexString(-var8));
         }

         var6++;
      }

      return var3.toString();
   }
}
