package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class ali extends axj {
   @SerId(1)
   protected amu q;

   public ali(amu var1) {
      super(1073741824, null);
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
         this.Uv(3);
         this.q(var1.RF().oW());
      }
   }

   public amu q() {
      return this.q;
   }
}
