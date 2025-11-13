package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aco;

public abstract class AbstractCElementOptimizer extends AbstractCBlockOptimizer {
   private static final StructuredLogger logger = aco.pC(AbstractCElementOptimizer.class);
   private boolean innerReplacement = false;

   public AbstractCElementOptimizer() {
      this(false);
   }

   public AbstractCElementOptimizer(boolean var1) {
      this.innerReplacement = var1;
   }

   @Override
   protected final int optimizeBlock(ICBlock var1) {
      return this.optimizeSubElements(var1);
   }

   private int optimizeSubElements(ICElement var1) {
      if (CUtil.isClassMethodField(var1)) {
         return 0;
      } else {
         int var2 = 0;

         for (ICElement var4 : var1.getSubElements()) {
            var2 += this.optimizeSubElements(var4);
            ICElement var5 = this.optimizeElement(var4, var1);
            if (var5 != null) {
               if (!this.innerReplacement && !var1.replaceSubElement(var4, var5)) {
                  logger.error(S.L("Error while replacing %s by %s"), var4, var5);
               } else {
                  var2++;
               }
            }
         }

         return var2;
      }
   }

   protected abstract ICElement optimizeElement(ICElement var1, ICElement var2);
}
