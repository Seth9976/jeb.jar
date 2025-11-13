package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.ih;
import java.io.IOException;
import java.util.List;

class vb extends CU.CU {
   vb(CU var1, boolean var2) {
      super(var2);
      this.q = var1;
   }

   @Override
   protected Object q(long var1) throws IOException, JebException {
      List var3 = this.q().JY().q(var1, null, null);
      StringBuilder var4 = new StringBuilder();
      Strings.ff(var4, S.L("%d methods:"), var3.size());

      for (ih var6 : var3) {
         Strings.ff(var4, "\n- %s", var6);
      }

      return var4.toString();
   }
}
