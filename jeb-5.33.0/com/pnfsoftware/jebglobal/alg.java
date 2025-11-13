package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class alg {
   @SerId(1)
   AddressableInstruction pC;
   @SerId(2)
   INativeMethodItem A;
   @SerId(3)
   List kS;

   public alg(AddressableInstruction var1, INativeMethodItem var2, List var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = (List)(var3 == null ? new ArrayList() : var3);
   }

   public long pC() {
      return this.pC.getOffset();
   }

   public INativeMethodItem A() {
      return this.A;
   }

   public List kS() {
      return this.kS;
   }

   @Override
   public String toString() {
      return Strings.ff("{%s}=>%s(dyn:%s)", this.pC, this.A, this.kS);
   }
}
