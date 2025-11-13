package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.hw;
import java.io.IOException;

class PY extends CU.CU {
   PY(CU var1, boolean var2) {
      super(var2);
      this.q = var1;
   }

   @Override
   protected Object q(long var1) throws IOException, JebException {
      hw[] var3 = this.q().JY().q(var1);
      StringBuilder var4 = new StringBuilder();
      Strings.ff(var4, S.L("%d fields:"), var3.length);

      for (hw var8 : var3) {
         Strings.ff(var4, "\n- %s", var8);
      }

      return var4.toString();
   }
}
