package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class ben {
   @SerId(1)
   private Map pC = new HashMap();

   public bem pC(int var1, long var2) {
      Object var4 = (Map)this.pC.get(var1);
      if (var4 == null) {
         var4 = new HashMap();
         this.pC.put(var1, var4);
      }

      bem var5 = (bem)var4.get(var2);
      if (var5 == null) {
         var5 = new bem(var1, var2);
         var4.put(var2, var5);
      }

      return var5;
   }
}
