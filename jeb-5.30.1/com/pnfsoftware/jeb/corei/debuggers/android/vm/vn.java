package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import java.io.IOException;

class vn extends CU.CU {
   vn(CU var1, boolean var2) {
      super(var2);
      this.q = var1;
   }

   @Override
   protected Object q(long var1) throws IOException, JebException {
      return this.q().JY().Uv(var1);
   }
}
