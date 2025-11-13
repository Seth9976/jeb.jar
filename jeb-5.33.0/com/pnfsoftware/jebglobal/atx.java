package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atx extends atf {
   private static final StructuredLogger WR = aco.pC(atx.class);

   public atx() {
      super(atg.fI);
   }

   @Override
   protected boolean kS() {
      ani var1 = new ani(this.ys);
      int var2 = var1.pC();
      Object[] var10000 = new Object[]{var2};
      this.kS.getExtensionsManager().applyAdditionalTypes(this.A, this.ys.getCfg());
      var10000 = new Object[0];
      this.pC(true, WR);
      WR.iH("Removing EStatement flag: FLAG_OPT_BLOCK_SUBSTITUTIONS");
      var2 = 0;

      for (IEStatement var4 : this.ys.getCfg().instructions()) {
         if ((var4.getFlags() & 2) != 0) {
            var4.removeFlags(2);
            var2++;
         }
      }

      if (var2 > 0) {
         this.pC(WR, "Running optimizer");
      }

      amk var6 = new amk(this.ys);
      var6.pC();
      return true;
   }
}
