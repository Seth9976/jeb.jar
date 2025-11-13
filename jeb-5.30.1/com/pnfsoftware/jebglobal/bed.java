package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class bed {
   private static final ILogger q = GlobalLog.getLogger(bed.class);
   private static HashMap RF = new HashMap();

   public static boolean q(String var0) {
      return Strings.isContainedIn(var0, "<init>", "<clinit>");
   }

   public static boolean RF(String var0) {
      return var0.length() == 1 && RF.containsKey(var0);
   }

   public static String xK(String var0) {
      var0 = var0.replace('/', '.');
      if (var0.endsWith(";")) {
         Assert.a(var0.startsWith("L"), "Illegal typename: " + var0);
         var0 = var0.substring(1, var0.length() - 1);
      }

      return var0;
   }

   public static String Dw(String var0) {
      var0 = var0.replace('.', '/');
      if (var0.endsWith(";")) {
         Assert.a(var0.startsWith("L"), "Illegal typename: " + var0);
         var0 = var0.substring(1, var0.length() - 1);
      }

      return var0;
   }

   public static String Uv(String var0) {
      var0 = var0.replace('.', '/');
      if (!var0.endsWith(";")) {
         var0 = "L" + var0 + ";";
      }

      return var0;
   }

   public static String q(beb var0) {
      int var1 = var0.Dw();
      return q(var0, var1);
   }

   public static String RF(beb var0) {
      int var1 = var0.Uv();
      return q(var0, var1);
   }

   public static List xK(beb var0) {
      ArrayList var1 = new ArrayList();

      for (int var5 : var0.oW()) {
         var1.add(q(var0, var5));
      }

      return var1;
   }

   public static String q(beb var0, int var1) {
      if (var1 == 0) {
         return "java/lang/Object";
      } else {
         bdl var2 = (bdl)var0.RF(var1);
         bdy var3 = (bdy)var0.RF(var2.RF);
         return var3.xK();
      }
   }

   public static boolean q(bec var0, String var1, String var2) {
      var1 = Dw(var1);
      var2 = Dw(var2);
      HashSet var3 = new HashSet();
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var1);

      while (!var4.isEmpty()) {
         var1 = (String)var4.remove();
         if (var1.equals(var2)) {
            return true;
         }

         beb var5;
         try {
            var5 = var0.RF(var1);
         } catch (IOException var9) {
            JebCoreService.silentExcept(new RuntimeException("Error parsing classfile for type: " + var1, var9));
            return false;
         }

         if (var5 != null) {
            String var6 = var5.zz();
            if (var6.equals(var2)) {
               return true;
            }

            if (var3.add(var6)) {
               var4.add(var6);
            }

            for (String var8 : var5.JY()) {
               if (var8.equals(var2)) {
                  return true;
               }

               if (var3.add(var8)) {
                  var4.add(var8);
               }
            }
         }
      }

      return false;
   }

   public static List q(bec var0, String var1, String var2, String var3, boolean var4) {
      if (q(var2)) {
         return Collections.emptyList();
      } else {
         HashSet var5 = new HashSet();
         ArrayDeque var6 = new ArrayDeque();
         var1 = Dw(var1);
         beb var7 = null;

         try {
            var7 = var0.RF(var1);
         } catch (IOException var12) {
            JebCoreService.silentExcept(new RuntimeException("Error parsing classfile for type: " + var1, var12));
         }

         if (var7 == null) {
            return Collections.emptyList();
         } else if (q(var7, var2, var3) != 1) {
            return Collections.emptyList();
         } else {
            ArrayList var8 = new ArrayList();
            var6.addAll(var7.HF());

            while (!var6.isEmpty()) {
               String var9 = (String)var6.remove();
               if (var5.add(var9)) {
                  var7 = null;

                  try {
                     var7 = var0.RF(var9);
                  } catch (IOException var11) {
                     JebCoreService.silentExcept(new RuntimeException("Error parsing classfile for type: " + var9, var11));
                  }

                  if (var7 != null) {
                     int var10 = q(var7, var2, var3);
                     if (var10 == 1) {
                        var8.add(var9);
                     } else if (var10 == -1) {
                        continue;
                     }

                     if (var10 == 0 || !var4) {
                        var6.addAll(var7.HF());
                     }
                  }
               }
            }

            return var8;
         }
      }
   }

   private static int q(beb var0, String var1, String var2) {
      for (beg var4 : var0.gP()) {
         if (var1.equals(var4.q(var0)) && var2.equals(var4.RF(var0))) {
            return (var4.q() & 2) != 0 ? -1 : 1;
         }
      }

      return 0;
   }

   static {
      RF.put("Z", "boolean");
      RF.put("B", "byte");
      RF.put("C", "char");
      RF.put("S", "short");
      RF.put("I", "int");
      RF.put("J", "long");
      RF.put("F", "float");
      RF.put("D", "double");
   }
}
