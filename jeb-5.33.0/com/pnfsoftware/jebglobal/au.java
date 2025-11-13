package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterDataArm64;
import java.io.IOException;
import java.util.Map;

public class au extends RegisterDataArm64 implements LD {
   Tl pC;
   RegisterLayoutBridge A;

   public au(Tl var1, RegisterLayoutBridge var2) throws IOException {
      super(var1.A());
      this.pC = var1;
      this.A = var2;
      aq.pC(this, var1, var2);
   }

   @Override
   public Ti pC() {
      return this.pC.pC();
   }

   @Override
   public byte[] A() {
      return aq.A(this, this.pC, this.A);
   }

   @Override
   public Map pC(boolean var1) {
      return aq.pC(this, this.pC, this.A, var1);
   }
}
