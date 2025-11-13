package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;

public class nA {
   private IEGeneric pC;
   private IEGeneric A;
   private boolean kS;

   public nA(IEGeneric var1, IEGeneric var2) {
      this(var1, var2, false);
   }

   public nA(IEGeneric var1, IEGeneric var2, boolean var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   public IEGeneric pC() {
      return this.pC;
   }

   public IEGeneric A() {
      return this.A;
   }

   public boolean kS() {
      return this.kS;
   }
}
