package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import java.io.IOException;

class yt extends Sv.Sv {
   yt(Sv var1, boolean var2) {
      super(var2);
      this.pC = var1;
   }

   @Override
   protected Object pC(long var1) throws IOException, JebException {
      return this.pC().oT().A(var1);
   }
}
