package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awq extends avx {
   private static final StructuredLogger JY = aeg.q(awq.class);

   public awq() {
      super(avy.lm);
   }

   @Override
   protected boolean Dw() {
      aml var1 = (aml)this.nf;
      Long var2 = this.nf.getStackManager().getHighestVariableOffset();
      if (var2 != null && var2.intValue() > var1.gO().getValue()) {
         Object[] var10000 = new Object[]{var2.intValue()};
         var1.RF(new SPDC(var2.intValue(), 20, 20), false);
      }

      if (this.gP.getPrototype() == null) {
         aoy var3 = new aoy(this.nf);
         IWildcardPrototype var4 = var3.q();
         if (this.nf.getPrototype() == null || !var4.equals(this.nf.getPrototype())) {
            JY.iH("Updating wildcard prototype to: %s", var4);
            this.nf.setPrototype(var4);
            Object[] var6 = new Object[]{this.nf.getPrototype()};
            IEPrototypeHandler var5 = this.Uv.getPrototypeHandler(this.nf);
            var5.applyKnownPrototype(true);
         }
      }

      return true;
   }
}
