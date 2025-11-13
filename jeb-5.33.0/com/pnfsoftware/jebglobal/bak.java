package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bak {
   private static final ILogger pC = GlobalLog.getLogger(bak.class);
   private static HashMap A = new HashMap();

   public static boolean pC(String var0) {
      return Strings.isContainedIn(var0, "<init>", "<clinit>");
   }

   public static String A(String var0) {
      var0 = var0.replace('/', '.');
      if (var0.endsWith(";")) {
         Assert.a(var0.startsWith("L"), "Illegal typename: " + var0);
         var0 = var0.substring(1, var0.length() - 1);
      }

      return var0;
   }

   public static String kS(String var0) {
      var0 = var0.replace('.', '/');
      if (var0.endsWith(";")) {
         Assert.a(var0.startsWith("L"), "Illegal typename: " + var0);
         var0 = var0.substring(1, var0.length() - 1);
      }

      return var0;
   }

   public static String wS(String var0) {
      var0 = var0.replace('.', '/');
      if (!var0.endsWith(";")) {
         var0 = "L" + var0 + ";";
      }

      return var0;
   }

   public static String pC(bai var0) {
      int var1 = var0.wS();
      return pC(var0, var1);
   }

   public static String A(bai var0) {
      int var1 = var0.UT();
      return pC(var0, var1);
   }

   public static List kS(bai var0) {
      ArrayList var1 = new ArrayList();

      for (int var5 : var0.E()) {
         var1.add(pC(var0, var5));
      }

      return var1;
   }

   public static String pC(bai var0, int var1) {
      if (var1 == 0) {
         return "java/lang/Object";
      } else {
         azt var2 = (azt)var0.pC(var1);
         baf var3 = (baf)var0.pC(var2.A);
         return var3.A();
      }
   }

   static {
      A.put("Z", "boolean");
      A.put("B", "byte");
      A.put("C", "char");
      A.put("S", "short");
      A.put("I", "int");
      A.put("J", "long");
      A.put("F", "float");
      A.put("D", "double");
   }
}
