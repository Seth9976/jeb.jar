package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.Set;

@Ser
public class zp {
   @SerId(1)
   long pC;
   @SerId(2)
   Set A;

   zp(long var1, Set var3) {
      this.pC = var1;
      this.A = var3;
   }

   public int pC() {
      return (int)(this.pC & 7L);
   }

   public Set A() {
      return this.A == null ? Collections.emptySet() : Collections.unmodifiableSet(this.A);
   }
}
