package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.eO;
import java.io.IOException;
import java.util.List;

class sy extends Sv.Sv {
   sy(Sv var1, boolean var2) {
      super(var2);
      this.pC = var1;
   }

   @Override
   protected Object pC(long var1) throws IOException, JebException {
      List var3 = this.pC().oT().pC(var1, null, null);
      StringBuilder var4 = new StringBuilder();
      Strings.ff(var4, S.L("%d methods:"), var3.size());

      for (eO var6 : var3) {
         Strings.ff(var4, "\n- %s", var6);
      }

      return var4.toString();
   }
}
