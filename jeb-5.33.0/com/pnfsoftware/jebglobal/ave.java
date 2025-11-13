package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class ave {
   @SerId(1)
   private long pC;
   @SerId(2)
   private Map A = new HashMap();
   @SerId(3)
   private List kS = new ArrayList();

   public ave(long var1) {
      this.pC = var1;
   }

   public avd pC(long var1) {
      long var5 = var1 - this.pC;
      long var3;
      if (var5 >= -2147483648L && var5 <= 2147483647L) {
         var3 = -9151314442816847872L | var5 & 4294967295L;
      } else {
         Integer var7 = (Integer)this.A.get(var1);
         if (var7 == null) {
            var7 = this.kS.size();
            Assert.a(var7 < Integer.MAX_VALUE, "Too many address pseudo-items");
            this.kS.add(var1);
            this.A.put(var1, var7);
         }

         var3 = -9151313343305220096L | var7.intValue();
      }

      return new avd(var3, var1);
   }

   public avd A(long var1) {
      if ((var1 & -72057594037927936L) != -9151314442816847872L) {
         return null;
      } else {
         long var3;
         if ((var1 & 1099511627776L) != 0L) {
            int var5 = (int)var1;
            Long var6 = (Long)this.kS.get(var5);
            if (var6 == null) {
               return null;
            }

            var3 = var6;
         } else {
            long var7 = (int)var1;
            var3 = this.pC + var7;
         }

         return new avd(var1, var3);
      }
   }
}
