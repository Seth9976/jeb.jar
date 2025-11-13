package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;
import java.util.TreeMap;

public class bkr {
   private static bkr RF;
   public static final Map q = new TreeMap();

   public static bkr q() {
      if (RF == null) {
         synchronized (bkr.class) {
            if (RF == null) {
               RF = new bkr();
            }
         }
      }

      return RF;
   }

   private bkr() {
   }

   public String[] q(String var1) {
      String var2 = (String)q.get(var1);
      return var2 == null ? null : var2.split(",");
   }

   static {
      try {
         byte[] var0 = AssetManager.getAssetBytes(cvm.q(new byte[]{34, 9, 7, 9, 28, 71, 5, 1, 26}, 2, 102));
         if (var0 != null) {
            String var1 = cvm.q(new byte[]{115, 94, 66, 74, 70, 54, 82, 94, 67, 24, 17}, 2, 249);
            cvk.q(Strings.encodeASCII(var1), var0);
            String var2 = Strings.decodeASCII(var0);

            for (String var6 : Strings.splitLines(var2)) {
               var6 = var6.trim();
               if (!var6.isEmpty() && !var6.startsWith("#")) {
                  String[] var7 = var6.split("=");
                  if (var7.length == 2) {
                     String var8 = var7[0];
                     String var9 = var7[1];
                     q.put(var8, var9);
                  }
               }
            }
         }
      } catch (Exception var10) {
         q.clear();
      }
   }
}
