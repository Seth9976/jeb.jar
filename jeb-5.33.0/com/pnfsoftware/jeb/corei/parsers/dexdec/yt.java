package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.pnfsoftware.jebglobal.bpx;
import java.util.concurrent.TimeUnit;

public class yt implements zp {
   Cache pC;

   public yt(int var1, int var2) {
      CacheBuilder var3 = CacheBuilder.newBuilder();
      if (var1 >= 0) {
         var3.maximumSize(var1);
      }

      if (var2 >= 0) {
         var3.expireAfterAccess(var2, TimeUnit.SECONDS);
      }

      this.pC = var3.build();
   }

   @Override
   public int pC() {
      return (int)this.pC.size();
   }

   @Override
   public void A() {
      this.pC.invalidateAll();
   }

   @Override
   public bpx pC(String var1) {
      return (bpx)this.pC.getIfPresent(var1);
   }

   @Override
   public void pC(String var1, bpx var2) {
      this.pC.put(var1, var2);
   }

   @Override
   public void A(String var1) {
      this.pC.invalidate(var1);
   }
}
