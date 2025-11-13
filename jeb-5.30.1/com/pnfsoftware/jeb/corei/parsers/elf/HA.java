package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class HA {
   @SerId(1)
   byte[] q;
   @SerId(2)
   Map RF = new HashMap();

   public HA(byte[] var1) {
      if (var1 != null && var1.length != 0 && var1[0] == 0) {
         this.q = var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public List q() {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (int var3 = 0; var3 < this.q.length; var3++) {
         if (this.q[var3] != 0) {
            var2++;
         } else {
            var1.add(Strings.decodeASCII(this.q, var3 - var2, var2));
            var2 = 0;
         }
      }

      return var1;
   }

   public String q(int var1) {
      String var2 = (String)this.RF.get(var1);
      if (var2 != null) {
         return var2;
      } else if (var1 >= 0 && var1 < this.q.length) {
         int var3 = 0;

         for (int var4 = var1; var4 < this.q.length && this.q[var4] != 0; var4++) {
            var3++;
         }

         var2 = Strings.decodeASCII(this.q, var1, var3);
         this.RF.put(var1, var2);
         return var2;
      } else {
         return null;
      }
   }
}
