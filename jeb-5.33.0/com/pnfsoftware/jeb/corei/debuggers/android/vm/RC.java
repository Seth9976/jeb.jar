package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.Nv;
import java.io.IOException;

class RC extends Sv.Sv {
   RC(Sv var1, boolean var2) {
      super(var2);
      this.pC = var1;
   }

   @Override
   protected Object pC(long var1) throws IOException, JebException {
      Nv[] var3 = this.pC().oT().pC(var1);
      StringBuilder var4 = new StringBuilder();
      Strings.ff(var4, S.L("%d fields:"), var3.length);

      for (Nv var8 : var3) {
         Strings.ff(var4, "\n- %s", var8);
      }

      return var4.toString();
   }
}
