package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class azx extends azv {
   @SerId(1)
   protected azv xK;

   public azx(azv var1) {
      super(var1.q, var1.RF);
      this.xK = var1;
   }

   @Override
   protected boolean q(axp var1) {
      return this.xK.q(var1);
   }
}
