package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDCollectionOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class bwn extends AbstractDCollectionOptimizer {
   public bwn() {
      super(DOptimizerType.UNSAFE);
      this.addTag("slow");
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   private Map pC() {
      HashMap var1 = new HashMap();

      for (IDMethodContext var3 : this.ctxlist) {
         String var4 = var3.getMethodSignature();
         String var5 = var4.substring(0, var4.indexOf("->"));
         Maps.putMulti(var1, var5, var3);
      }

      return var1;
   }

   @Override
   public int performOnCollection() {
      Map var1 = this.pC();
      int var2 = 0;

      for (Entry var4 : var1.entrySet()) {
         String var5 = (String)var4.getKey();
         List var6 = (List)var4.getValue();
         bwk var7 = new bwk(this.g, var5, var6);
         var7.pC(true);
         var7.A(false);
         var7.kS(true);
         var7.wS(true);
         var2 += var7.pC();
         int var8 = bpl.pC(this);

         for (Entry var10 : var7.A().entrySet()) {
            IDMethodContext var11 = (IDMethodContext)var10.getKey();
            int var12 = (Integer)var10.getValue();
            if (var12 > 0) {
               var11.incrementDeobfuscationScore(var12 * var8);
               this.recordMethodOptimization(var11, var12);
            }
         }
      }

      return var2;
   }
}
