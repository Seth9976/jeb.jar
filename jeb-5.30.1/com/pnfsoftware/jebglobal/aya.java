package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class aya {
   static final long q = 1099511627776L;
   @SerId(1)
   private long RF;
   @SerId(2)
   private Map xK = new HashMap();
   @SerId(3)
   private List Dw = new ArrayList();

   public aya(long var1) {
      this.RF = var1;
   }

   public axz q(long var1) {
      long var5 = var1 - this.RF;
      long var3;
      if (var5 >= -2147483648L && var5 <= 2147483647L) {
         var3 = -9151314442816847872L | var5 & 4294967295L;
      } else {
         Integer var7 = (Integer)this.xK.get(var1);
         if (var7 == null) {
            var7 = this.Dw.size();
            Assert.a(var7 < Integer.MAX_VALUE, "Too many address pseudo-items");
            this.Dw.add(var1);
            this.xK.put(var1, var7);
         }

         var3 = -9151313343305220096L | var7.intValue();
      }

      return new axz(var3, var1);
   }

   public axz RF(long var1) {
      if ((var1 & -72057594037927936L) != -9151314442816847872L) {
         return null;
      } else {
         long var3;
         if ((var1 & 1099511627776L) != 0L) {
            int var5 = (int)var1;
            Long var6 = (Long)this.Dw.get(var5);
            if (var6 == null) {
               return null;
            }

            var3 = var6;
         } else {
            long var7 = (int)var1;
            var3 = this.RF + var7;
         }

         return new axz(var1, var3);
      }
   }
}
