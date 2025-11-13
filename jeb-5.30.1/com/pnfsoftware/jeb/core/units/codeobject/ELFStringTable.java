package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ELFStringTable {
   byte[] data;
   Map cache = new HashMap();

   public ELFStringTable(byte[] var1) {
      if (var1 != null && var1.length != 0 && var1[0] == 0) {
         this.data = var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public List getAllStrings() {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (int var3 = 0; var3 < this.data.length; var3++) {
         if (this.data[var3] != 0) {
            var2++;
         } else {
            var1.add(Strings.decodeASCII(this.data, var3 - var2, var2));
            var2 = 0;
         }
      }

      return var1;
   }

   public String getString(int var1) {
      String var2 = (String)this.cache.get(var1);
      if (var2 != null) {
         return var2;
      } else if (var1 >= 0 && var1 < this.data.length) {
         int var3 = 0;

         for (int var4 = var1; var4 < this.data.length && this.data[var4] != 0; var4++) {
            var3++;
         }

         var2 = Strings.decodeASCII(this.data, var1, var3);
         this.cache.put(var1, var2);
         return var2;
      } else {
         return null;
      }
   }
}
