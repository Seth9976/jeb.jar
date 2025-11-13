package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalSandboxExecutionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

class byh implements IDSandboxHooks {
   int q;

   byh(byg var1) {
      this.RF = var1;
   }

   @Override
   public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) throws DexDecEvalSandboxExecutionException {
      switch (var4) {
         case "Landroid/app/MediaRouteButton;->setRouteTypes(I)V":
            this.q = (Integer)var6.get(0);
            return Wrapper.wrap(null);
         case "Landroid/app/MediaRouteButton;->getRouteTypes()I":
            return Wrapper.wrap(this.q);
         default:
            return null;
      }
   }
}
