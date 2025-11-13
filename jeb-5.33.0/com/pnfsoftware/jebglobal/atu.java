package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atu extends atf {
   private static final StructuredLogger WR = aco.pC(atu.class);

   public atu() {
      super(atg.ld);
   }

   @Override
   protected boolean kS() {
      ana var1 = new ana(this.ys, true);

      try {
         WR.beginSection("Analyzing stack pointer deltas...");
         var1.kS();
      } finally {
         WR.closeSection();
      }

      SPDC var2 = var1.pC();
      SPDC var3 = ((aki)this.ys).pC(var2, true);
      if (var3 != null) {
         WR.iH("A better SP-out was found by SA: %s (was: %s)", var2, var3);
      }

      SPDC var4 = var1.A();
      SPDC var5 = ((aki)this.ys).A(var4, true);
      if (var5 != null) {
         WR.iH("A better SP-highest was found by SA: %s (was: %s)", var4, var5);
      }

      Object[] var10000 = new Object[0];
      int var6 = var1.wS();
      var10000 = new Object[]{var6};
      var10000 = new Object[0];
      ani var7 = new ani(this.ys);
      var6 = var7.A();
      var10000 = new Object[]{var6};
      this.pC(WR, "Running optimizer");
      return true;
   }
}
