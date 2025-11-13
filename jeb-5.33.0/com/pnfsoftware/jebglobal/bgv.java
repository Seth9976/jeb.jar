package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;
import java.util.TreeMap;

public class bgv {
   private static bgv A;
   public static final Map pC = new TreeMap();

   public static bgv pC() {
      if (A == null) {
         synchronized (bgv.class) {
            if (A == null) {
               A = new bgv();
            }
         }
      }

      return A;
   }

   private bgv() {
   }

   public String[] pC(String var1) {
      String var2 = (String)pC.get(var1);
      return var2 == null ? null : var2.split(",");
   }

   static {
      try {
         byte[] var0 = AssetManager.getAssetBytes(ckx.pC(new byte[]{34, 9, 7, 9, 28, 71, 5, 1, 26}, 2, 5));
         if (var0 != null) {
            String var1 = ckx.pC(new byte[]{115, 94, 66, 74, 70, 54, 82, 94, 67, 24, 17}, 2, 8);
            ckv.pC(Strings.encodeASCII(var1), var0);
            String var2 = Strings.decodeASCII(var0);

            for (String var6 : Strings.splitLines(var2)) {
               var6 = var6.trim();
               if (!var6.isEmpty() && !var6.startsWith("#")) {
                  String[] var7 = var6.split("=");
                  if (var7.length == 2) {
                     String var8 = var7[0];
                     String var9 = var7[1];
                     pC.put(var8, var9);
                  }
               }
            }
         }
      } catch (Exception var10) {
         pC.clear();
      }
   }
}
