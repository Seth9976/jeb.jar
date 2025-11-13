package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class bkm {
   public static final String q = "Ljava/lang/Object;";

   public static boolean q(IJLSTypeAdapter var0, String var1, String var2) {
      if (!var2.equals("Ljava/lang/Object;") && !var1.equals(var2)) {
         ArrayDeque var3 = new ArrayDeque();
         var3.add(var1);
         HashSet var4 = new HashSet();
         var4.add("Ljava/lang/Object;");

         while (!var3.isEmpty()) {
            String var5 = (String)var3.remove();
            if (var4.add(var5)) {
               List var6 = var0.getParentTypes(var5);
               if (var6 == null) {
                  return false;
               }

               if (var6.contains(var2)) {
                  return true;
               }

               var3.addAll(var6);
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public static List q(IJLSTypeAdapter var0, String var1) {
      ArrayList var2 = new ArrayList();
      switch (var1) {
         case "Z":
         case "B":
         case "C":
         case "S":
         case "I":
         case "L":
         case "F":
         case "D":
         case "V":
            return var2;
         default:
            boolean var3 = (var0.getType(var1).getAccessFlags() & 512) != 0;
            ArrayDeque var14 = new ArrayDeque();
            var14.add(var1);
            HashSet var5 = new HashSet();
            HashSet var6 = new HashSet();
            int var7 = 0;

            while (!var14.isEmpty()) {
               String var8 = (String)var14.remove();
               if (var5.add(var8)) {
                  IJLSType var9 = var0.getType(var8);
                  if (var9 == null) {
                     return null;
                  }

                  for (IJLSMethod var11 : var0.getMethods(var8)) {
                     if (!Strings.isContainedIn(var11.getName(), "<init>", "<clinit>")
                        && (var11.getAccessFlags() & 1) != 0
                        && (!var3 || !var8.equals("Ljava/lang/Object;"))
                        && (var7 < 1 || (var11.getAccessFlags() & 8) == 0 || (var9.getAccessFlags() & 512) == 0)) {
                        String var12 = var11.getSignature();
                        String var13 = var12.substring(0, var12.indexOf(41) + 1);
                        if (var6.add(var13)) {
                           var2.add(var8 + "->" + var12);
                        }
                     }
                  }

                  var14.addAll(var0.getParentTypes(var8));
                  var7++;
               }
            }

            return var2;
      }
   }

   public static List RF(IJLSTypeAdapter var0, String var1) {
      throw new RuntimeException("TBI");
   }

   public static List xK(IJLSTypeAdapter var0, String var1) {
      throw new RuntimeException("TBI");
   }
}
