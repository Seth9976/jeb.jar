package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bih {
   @SerId(1)
   private Map q = new HashMap();

   public void q() {
      this.q.clear();
   }

   public big q(int var1, long var2) {
      Object var4 = (Map)this.q.get(var1);
      if (var4 == null) {
         var4 = new HashMap();
         this.q.put(var1, var4);
      }

      big var5 = (big)var4.get(var2);
      if (var5 == null) {
         var5 = new big(var1, var2);
         var4.put(var2, var5);
      }

      return var5;
   }
}
