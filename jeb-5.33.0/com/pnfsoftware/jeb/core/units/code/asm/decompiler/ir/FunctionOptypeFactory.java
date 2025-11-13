package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class FunctionOptypeFactory {
   @SerId(1)
   Map map = new HashMap();

   public FunctionOptype create(String var1, int var2, int var3, int var4) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         FunctionOptype var5 = (FunctionOptype)this.map.get(var1);
         if (var5 != null) {
            if (var1.equals(var5.name) && var2 == var5.flags && var3 == var5.opndCount && var4 == var5.resultBitsize) {
               return var5;
            } else {
               throw new IllegalStateException();
            }
         } else {
            var5 = new FunctionOptype(var1, var2, var3, var4);
            this.map.put(var1, var5);
            return var5;
         }
      }
   }

   public FunctionOptype get(String var1) {
      return (FunctionOptype)this.map.get(var1);
   }
}
