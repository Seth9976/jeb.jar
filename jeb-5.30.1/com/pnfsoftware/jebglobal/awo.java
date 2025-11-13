package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awo extends avx {
   private static final StructuredLogger JY = aeg.q(awo.class);

   public awo() {
      super(avy.gP);
   }

   @Override
   protected boolean Dw() {
      apk var1 = new apk(this.nf, true);

      try {
         JY.beginSection("Analyzing stack pointer deltas...");
         var1.nf();
      } finally {
         JY.closeSection();
      }

      SPDC var2 = var1.q();
      SPDC var3 = ((aml)this.nf).q(var2, true);
      if (var3 != null) {
         JY.iH("A better SP-out was found by SA: %s (was: %s)", var2, var3);
      }

      SPDC var4 = var1.RF();
      SPDC var5 = ((aml)this.nf).RF(var4, true);
      if (var5 != null) {
         JY.iH("A better SP-highest was found by SA: %s (was: %s)", var4, var5);
      }

      Object[] var10000 = new Object[0];
      int var6 = var1.lm();
      var10000 = new Object[]{var6};
      var10000 = new Object[0];
      aps var7 = new aps(this.nf);
      var6 = var7.RF();
      var10000 = new Object[]{var6};
      this.q(JY, "Running optimizer");
      return true;
   }
}
