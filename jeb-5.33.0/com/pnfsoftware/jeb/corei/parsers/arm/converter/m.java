package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;

public class m {
   private IEGeneric pC;
   private IEGeneric A;
   private IEGeneric kS;
   private IEGeneric wS;
   private IEGeneric UT;

   public m(IEGeneric var1, IEGeneric var2, IEGeneric var3, IEGeneric var4, IEGeneric var5) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
   }

   public IEGeneric pC() {
      return this.pC;
   }

   public IEGeneric A() {
      return this.A;
   }

   public IEGeneric kS() {
      return this.kS;
   }

   public IEGeneric wS() {
      return this.wS;
   }

   public IEGeneric UT() {
      return this.UT;
   }

   public boolean E() {
      return this.wS != null || this.UT != null || this.A != null || this.kS != null;
   }

   public int pC(IEVar var1, IEGeneric var2) {
      int var3 = 0;
      if (this.A != null) {
         var3 += this.A.replaceVar(var1, var2);
      }

      if (this.kS != null) {
         var3 += this.kS.replaceVar(var1, var2);
      }

      if (this.wS != null) {
         var3 += this.wS.replaceVar(var1, var2);
      }

      if (this.UT != null) {
         var3 += this.UT.replaceVar(var1, var2);
      }

      return var3;
   }
}
