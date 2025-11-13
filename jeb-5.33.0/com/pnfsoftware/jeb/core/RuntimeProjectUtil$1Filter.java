package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.units.IUnit;
import java.util.ArrayList;
import java.util.List;

class RuntimeProjectUtil$1Filter implements IUnitFilter {
   private List contributions;

   RuntimeProjectUtil$1Filter(IUnit var1) {
      this.val$target = var1;
      this.contributions = new ArrayList();
   }

   @Override
   public boolean check(IUnit var1) {
      try {
         if (!var1.isProcessed()) {
            return false;
         } else {
            for (IUnitContribution var3 : var1.getContributions()) {
               if (!this.contributions.contains(var3) && var3.isTarget(this.val$target)) {
                  this.contributions.add(var3);
               }
            }

            return true;
         }
      } catch (Exception var4) {
         RuntimeProjectUtil.logger.catching(var4);
         return false;
      }
   }

   public List getContributions() {
      return this.contributions;
   }
}
