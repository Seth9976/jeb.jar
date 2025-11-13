package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Func;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class Sv {
   @SerId(1)
   Func pC;
   @SerId(2)
   Collection A;

   public Sv(Func var1) {
      this.pC = var1;
   }

   public Sv(Collection var1) {
      this.A = var1;
   }

   public Func pC() {
      return this.pC;
   }

   public Collection A() {
      return this.A;
   }
}
