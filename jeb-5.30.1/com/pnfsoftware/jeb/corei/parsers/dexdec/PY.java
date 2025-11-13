package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.pnfsoftware.jebglobal.bud;
import java.util.concurrent.TimeUnit;

public class PY implements EE {
   Cache q;

   public PY(int var1, int var2) {
      CacheBuilder var3 = CacheBuilder.newBuilder();
      if (var1 >= 0) {
         var3.maximumSize(var1);
      }

      if (var2 >= 0) {
         var3.expireAfterAccess(var2, TimeUnit.SECONDS);
      }

      this.q = var3.build();
   }

   @Override
   public int q() {
      return (int)this.q.size();
   }

   @Override
   public void RF() {
      this.q.invalidateAll();
   }

   @Override
   public bud q(String var1) {
      return (bud)this.q.getIfPresent(var1);
   }

   @Override
   public void q(String var1, bud var2) {
      this.q.put(var1, var2);
   }

   @Override
   public void RF(String var1) {
      this.q.invalidate(var1);
   }
}
