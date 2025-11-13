package com.pnfsoftware.jeb.core.units.code.android;

public class JvmFieldSig {
   public String csig;
   public String fname;
   public String ftype;

   public static JvmFieldSig parse(String var0) {
      return parse(var0, false);
   }

   public static JvmFieldSig parse(String var0, boolean var1) {
      JvmFieldSig var2 = parseSafe(var0, var1);
      if (var2 == null) {
         throw new IllegalArgumentException("Illegal field signature: " + var0);
      } else {
         return var2;
      }
   }

   public static JvmFieldSig parseSafe(String var0, boolean var1) {
      try {
         JvmFieldSig var2 = new JvmFieldSig();
         int var3 = var0.indexOf("->");
         if (var3 < 0) {
            return null;
         } else {
            var2.csig = var0.substring(0, var3);
            String var4 = var0.substring(var3 + 2);
            var3 = var4.indexOf(":");
            if (var3 < 0) {
               if (!var1) {
                  return null;
               }

               var2.fname = var4;
               var2.ftype = null;
            } else {
               var2.fname = var4.substring(0, var3);
               var2.ftype = var4.substring(var3 + 1);
            }

            return var2;
         }
      } catch (Exception var5) {
         return null;
      }
   }
}
