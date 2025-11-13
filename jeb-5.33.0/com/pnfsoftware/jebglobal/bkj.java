package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bkj implements bkk {
   @SerId(1)
   private Map pC = new HashMap();

   @Override
   public String pC(boolean var1, IJavaType var2, int var3, int var4) {
      String var5;
      int var6;
      if (var4 >= 0) {
         if (var4 < 65536) {
            var5 = "v";
            var6 = var4;
         } else if (var4 < 131072) {
            var5 = "v";
            var6 = var4 - 65536;
         } else if (var4 < 589824) {
            var5 = "x";
            var6 = var4 - 131072;
         } else {
            var5 = "x";
            var6 = var4 - 589824;
         }
      } else if (var3 >= 1048576 && var3 < 2097152) {
         var5 = "a";
         var6 = var3 - 1048576;
      } else {
         var5 = "u";
         var6 = var3;
      }

      if (var1) {
         var5 = "arg";
      }

      return var5 + var6;
   }
}
