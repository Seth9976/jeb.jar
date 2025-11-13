package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class avm {
   @SerId(1)
   private Map pC = new HashMap();
   @SerId(2)
   private List A = new ArrayList();

   public avl pC(String var1) {
      Integer var4 = (Integer)this.pC.get(var1);
      if (var4 == null) {
         var4 = this.A.size();
         Assert.a(var4 < Integer.MAX_VALUE, "Too many mnemonic pseudo-items");
         this.pC.put(var1, var4);
         this.A.add(var1);
      }

      long var2 = -9007199254740992000L | var4.intValue();
      return new avl(var2, var1);
   }

   public avl pC(long var1) {
      if ((var1 & -72057594037927936L) != -9007199254740992000L) {
         return null;
      } else {
         int var3 = (int)var1;
         String var4 = (String)this.A.get(var3);
         return var4 == null ? null : new avl(var1, var4);
      }
   }
}
