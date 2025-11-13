package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayh extends axj implements axy {
   @SerId(1)
   private String q;

   public ayh(long var1, String var3) {
      super(0, null);
      this.Uv(3);
      this.q(var1);
      this.q = var3;
   }

   public String xK() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoMnemonic(\"%s\")", this.q);
   }
}
