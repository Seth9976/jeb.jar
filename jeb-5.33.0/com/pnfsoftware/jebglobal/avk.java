package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class avk {
   @SerId(1)
   private C pC;
   @SerId(2)
   private long A;
   @SerId(3)
   private Map kS = new HashMap();
   @SerId(4)
   private List wS = new ArrayList();

   public avk(C var1) {
      this.pC = var1;
   }

   public avj pC(int var1, int var2) {
      long var3;
      if (var1 >= 0 && var1 <= 65535) {
         var3 = -8863084066665136128L | (long)var1 << 32 | var2 & 4294967295L;
      } else {
         Long var5 = Longs.fromInts(var1, var2);
         Integer var6 = (Integer)this.kS.get(var5);
         if (var6 == null) {
            var6 = this.wS.size();
            Assert.a(var6 < Integer.MAX_VALUE, "Too many local address pseudo-items");
            this.wS.add(var5);
            this.kS.put(var5, var6);
         }

         var3 = -8862802591688425472L | var6.intValue();
      }

      return new avj(this.pC, var3, var1, var2);
   }

   public avj pC(long var1) {
      if ((var1 & -72057594037927936L) != -8863084066665136128L) {
         return null;
      } else {
         int var3;
         int var4;
         if ((var1 & 281474976710656L) != 0L) {
            int var5 = (int)var1;
            Long var6 = (Long)this.wS.get(var5);
            if (var6 == null) {
               return null;
            }

            int[] var7 = Longs.toInts(var6);
            var4 = var7[0];
            var3 = var7[1];
         } else {
            var4 = (int)var1;
            var3 = (int)(var1 >> 32) & 65535;
         }

         return new avj(this.pC, var1, var3, var4);
      }
   }
}
