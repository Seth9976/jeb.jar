package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterDataX64;
import java.io.IOException;
import java.util.Map;

public class ix extends RegisterDataX64 implements Ht {
   kW q;
   RegisterLayoutBridge RF;

   public ix(kW var1, RegisterLayoutBridge var2) throws IOException {
      this.q = var1;
      this.RF = var2;
      Zi.q(this, var1, var2);
   }

   @Override
   public pF q() {
      return this.q.q();
   }

   @Override
   public byte[] RF() {
      return Zi.RF(this, this.q, this.RF);
   }

   @Override
   public Map q(boolean var1) {
      return Zi.q(this, this.q, this.RF, var1);
   }
}
