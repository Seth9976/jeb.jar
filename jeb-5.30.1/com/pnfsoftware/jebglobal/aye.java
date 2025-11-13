package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class aye {
   static final long q = 281474976710656L;
   @SerId(1)
   private long RF;
   @SerId(2)
   private Map xK = new HashMap();
   @SerId(3)
   private List Dw = new ArrayList();

   public aye(long var1) {
      this.RF = var1;
   }

   public ayd q(long var1, int var3) {
      if (var3 >= 0 && var3 < 65536) {
         long var4 = (long)var3 << 32;
         long var8 = var1 - this.RF;
         long var6;
         if (var8 >= -2147483648L && var8 <= 2147483647L) {
            var6 = -8935141660703064064L | var4 | var8 & 4294967295L;
         } else {
            Integer var10 = (Integer)this.xK.get(var1);
            if (var10 == null) {
               var10 = this.Dw.size();
               Assert.a(var10 < Integer.MAX_VALUE, "Too many immediate pseudo-items");
               this.Dw.add(var1);
               this.xK.put(var1, var10);
            }

            var6 = -8934860185726353408L | var4 | var10.intValue();
         }

         return new ayd(var6, var1, var3);
      } else {
         return null;
      }
   }

   public ayd q(long var1) {
      if ((var1 & -72057594037927936L) != -8935141660703064064L) {
         return null;
      } else {
         int var5 = (int)(var1 >> 32) & 65535;
         long var3;
         if ((var1 & 281474976710656L) != 0L) {
            int var6 = (int)var1;
            Long var7 = (Long)this.Dw.get(var6);
            if (var7 == null) {
               return null;
            }

            var3 = var7;
         } else {
            long var8 = (int)var1;
            var3 = this.RF + var8;
         }

         return new ayd(var1, var3, var5);
      }
   }
}
