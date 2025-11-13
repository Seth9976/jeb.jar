package com.pnfsoftware.jeb.corei.parsers.apk;

import java.util.HashMap;
import java.util.Map;

public class vb {
   private static Map q = new HashMap();
   private static Map RF = new HashMap();

   public static qV q(String var0) {
      qV var1 = (qV)q.get(var0);
      if (var1 == null) {
         for (String var3 : q.keySet()) {
            if (var3.contains(var0)) {
               if (var1 != null) {
                  return null;
               }

               var1 = (qV)q.get(var3);
            }
         }
      }

      return var1;
   }

   public static vn RF(String var0) {
      vn var1 = (vn)RF.get(var0);
      if (var1 == null) {
         for (String var3 : RF.keySet()) {
            if (var3.contains(var0)) {
               if (var1 != null) {
                  return null;
               }

               var1 = (vn)RF.get(var3);
            }
         }
      }

      return var1;
   }

   static {
      for (qV var3 : qV.values()) {
         q.put(var3.q(), var3);
      }

      for (vn var7 : vn.values()) {
         RF.put(var7.q(), var7);
      }
   }
}
