package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atw extends atf {
   private static final StructuredLogger WR = aco.pC(atw.class);

   public atw() {
      super(atg.oT);
   }

   @Override
   protected boolean kS() {
      aki var1 = (aki)this.ys;
      Long var2 = this.ys.getStackManager().getHighestVariableOffset();
      if (var2 != null && var2.intValue() > var1.UT().getValue()) {
         Object[] var10000 = new Object[]{var2.intValue()};
         var1.A(new SPDC(var2.intValue(), 20, 20), false);
      }

      if (this.ld.getPrototype() == null) {
         amr var3 = new amr(this.ys);
         IWildcardPrototype var4 = var3.pC();
         if (this.ys.getPrototype() == null || !var4.equals(this.ys.getPrototype())) {
            WR.iH("Updating wildcard prototype to: %s", var4);
            this.ys.setPrototype(var4);
            Object[] var6 = new Object[]{this.ys.getPrototype()};
            IEPrototypeHandler var5 = this.UT.getPrototypeHandler(this.ys);
            var5.applyKnownPrototype(true);
         }
      }

      return true;
   }
}
