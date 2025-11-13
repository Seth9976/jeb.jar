package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awr extends avx {
   private static final StructuredLogger JY = aeg.q(awr.class);

   public awr() {
      super(avy.zz);
   }

   @Override
   protected boolean Dw() {
      aps var1 = new aps(this.nf);
      int var2 = var1.q();
      Object[] var10000 = new Object[]{var2};
      this.xK.getExtensionsManager().applyAdditionalTypes(this.RF, this.nf.getCfg());
      var10000 = new Object[0];
      this.q(true, JY);
      JY.iH("Removing EStatement flag: FLAG_OPT_BLOCK_SUBSTITUTIONS");
      int var3 = 0;

      for (IEStatement var5 : this.nf.getCfg().instructions()) {
         if ((var5.getFlags() & 2) != 0) {
            var5.removeFlags(2);
            var3++;
         }
      }

      if (var3 > 0) {
         this.q(JY, "Running optimizer");
      }

      aor var6 = new aor(this.nf);
      var6.xK();
      return true;
   }
}
