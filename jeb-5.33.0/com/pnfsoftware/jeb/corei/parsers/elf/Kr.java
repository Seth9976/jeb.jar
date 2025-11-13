package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class Kr {
   @SerId(1)
   byte[] pC;
   @SerId(2)
   Map A = new HashMap();

   public Kr(byte[] var1) {
      if (var1 != null && var1.length != 0 && var1[0] == 0) {
         this.pC = var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public String pC(int var1) {
      String var2 = (String)this.A.get(var1);
      if (var2 != null) {
         return var2;
      } else if (var1 >= 0 && var1 < this.pC.length) {
         int var3 = 0;

         for (int var4 = var1; var4 < this.pC.length && this.pC[var4] != 0; var4++) {
            var3++;
         }

         var2 = Strings.decodeASCII(this.pC, var1, var3);
         this.A.put(var1, var2);
         return var2;
      } else {
         return null;
      }
   }
}
