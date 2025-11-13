package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.Set;

@Ser
public class qV {
   @SerId(1)
   long q;
   @SerId(2)
   Set RF;

   qV(long var1, Set var3) {
      this.q = var1;
      this.RF = var3;
   }

   public int q() {
      return (int)(this.q & 7L);
   }

   public Set RF() {
      return this.RF == null ? Collections.emptySet() : Collections.unmodifiableSet(this.RF);
   }
}
