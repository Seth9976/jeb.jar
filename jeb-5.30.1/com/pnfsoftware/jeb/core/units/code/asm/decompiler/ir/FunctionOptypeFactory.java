package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class FunctionOptypeFactory {
   @SerId(1)
   Map map = new HashMap();

   public FunctionOptype create(String var1, int var2, int var3, int var4, int var5) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         FunctionOptype var6 = (FunctionOptype)this.map.get(var1);
         if (var6 != null) {
            if (var1.equals(var6.name) && var2 == var6.flags && var3 == var6.minOpndCount && var4 == var6.maxOpndCount && var5 == var6.resultBitsize) {
               return var6;
            } else {
               throw new IllegalStateException();
            }
         } else {
            var6 = new FunctionOptype(var1, var2, var3, var4, var5);
            this.map.put(var1, var6);
            return var6;
         }
      }
   }

   public FunctionOptype get(String var1) {
      return (FunctionOptype)this.map.get(var1);
   }
}
