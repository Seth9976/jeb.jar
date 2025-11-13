package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class ayi {
   @SerId(1)
   private Map q = new HashMap();
   @SerId(2)
   private List RF = new ArrayList();

   public ayh q(String var1) {
      Integer var4 = (Integer)this.q.get(var1);
      if (var4 == null) {
         var4 = this.RF.size();
         Assert.a(var4 < Integer.MAX_VALUE, "Too many mnemonic pseudo-items");
         this.q.put(var1, var4);
         this.RF.add(var1);
      }

      long var2 = -9007199254740992000L | var4.intValue();
      return new ayh(var2, var1);
   }

   public ayh q(long var1) {
      if ((var1 & -72057594037927936L) != -9007199254740992000L) {
         return null;
      } else {
         int var3 = (int)var1;
         String var4 = (String)this.RF.get(var3);
         return var4 == null ? null : new ayh(var1, var4);
      }
   }
}
