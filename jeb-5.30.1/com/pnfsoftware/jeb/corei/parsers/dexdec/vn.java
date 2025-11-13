package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jebglobal.bud;
import java.util.concurrent.ConcurrentHashMap;

public class vn implements EE {
   ConcurrentHashMap q = new ConcurrentHashMap();

   @Override
   public int q() {
      return this.q.size();
   }

   @Override
   public void RF() {
      this.q.clear();
   }

   @Override
   public bud q(String var1) {
      return (bud)this.q.get(var1);
   }

   @Override
   public void q(String var1, bud var2) {
      this.q.put(var1, var2);
   }

   @Override
   public void RF(String var1) {
      this.q.remove(var1);
   }
}
