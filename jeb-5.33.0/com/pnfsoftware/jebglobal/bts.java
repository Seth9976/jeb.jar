package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalSandboxExecutionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

class bts implements IDSandboxHooks {
   int pC;

   bts(btr var1) {
      this.A = var1;
   }

   @Override
   public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) throws DexDecEvalSandboxExecutionException {
      switch (var4) {
         case "Landroid/app/MediaRouteButton;->setRouteTypes(I)V":
            this.pC = (Integer)var6.get(0);
            return Wrapper.wrap(null);
         case "Landroid/app/MediaRouteButton;->getRouteTypes()I":
            return Wrapper.wrap(this.pC);
         default:
            return null;
      }
   }
}
