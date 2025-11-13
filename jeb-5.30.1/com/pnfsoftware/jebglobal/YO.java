package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;

public class YO {
   private IEGeneric q;
   private IEGeneric RF;
   private IEGeneric xK;
   private IEGeneric Dw;
   private IEGeneric Uv;

   public YO(IEGeneric var1, IEGeneric var2, IEGeneric var3, IEGeneric var4, IEGeneric var5) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
   }

   public IEGeneric q() {
      return this.q;
   }

   public IEGeneric RF() {
      return this.RF;
   }

   public IEGeneric xK() {
      return this.xK;
   }

   public IEGeneric Dw() {
      return this.Dw;
   }

   public IEGeneric Uv() {
      return this.Uv;
   }

   public boolean oW() {
      return this.Dw != null || this.Uv != null;
   }

   public boolean gO() {
      return this.Dw != null || this.Uv != null || this.RF != null || this.xK != null;
   }

   public int q(IEVar var1, IEGeneric var2) {
      int var3 = 0;
      if (this.RF != null) {
         var3 += this.RF.replaceVar(var1, var2);
      }

      if (this.xK != null) {
         var3 += this.xK.replaceVar(var1, var2);
      }

      if (this.Dw != null) {
         var3 += this.Dw.replaceVar(var1, var2);
      }

      if (this.Uv != null) {
         var3 += this.Uv.replaceVar(var1, var2);
      }

      return var3;
   }
}
