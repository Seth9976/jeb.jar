package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.IVariable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class FineChainsConverter {
   private IVariableProvider varprv;

   public FineChainsConverter(IVariableProvider var1) {
      this.varprv = var1;
   }

   public Map convertToCoarseSimpleChains(Map var1) {
      return this.convertToCoarseChains(var1);
   }

   public Map convertToCoarseFullChains(Map var1) {
      return this.convertToCoarseChains(var1);
   }

   private Map convertToCoarseChains(Map var1) {
      HashMap var2 = new HashMap();

      for (int var4 : var1.keySet()) {
         IVariable var5 = this.varprv.getContaining(var4);
         if (var5 == null) {
            throw new RuntimeException("No variable contains the vbit " + var4);
         }

         int var6 = var5.getId();
         Object var7 = (Set)var2.get(var6);
         if (var7 == null) {
            var7 = new HashSet();
            var2.put(var6, var7);
         }

         var7.addAll((Collection)var1.get(var4));
      }

      return var2;
   }
}
