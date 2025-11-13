package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class ma extends AbstractAnalyzerExtension {
   private static final ILogger pC = GlobalLog.getLogger(ma.class);

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      if (var1 == 0) {
         Assert.a(this.gca.getContainer() instanceof Hv);
         Hv var2 = (Hv)this.gca.getContainer();

         try {
            long var3 = var2.kS();
            INativeContinuousItem var5 = this.gca.getModel().getItemAt(var3);
            if (var5 == null) {
               this.gca.defineData(var3, this.gca.getTypeManager().getVoidReference());
            }
         } catch (Exception var6) {
            pC.catchingSilent(var6);
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }
}
