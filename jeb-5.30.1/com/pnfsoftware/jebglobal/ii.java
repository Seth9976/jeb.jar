package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;

public class ii {
   private IEGeneric q;
   private IEGeneric RF;
   private boolean xK;

   public ii(IEGeneric var1, IEGeneric var2) {
      this(var1, var2, false);
   }

   public ii(IEGeneric var1, IEGeneric var2, boolean var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public IEGeneric q() {
      return this.q;
   }

   public IEGeneric RF() {
      return this.RF;
   }

   public boolean xK() {
      return this.xK;
   }
}
