package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.format.Strings;

public class CZ {
   private final IEGeneric q;
   private int RF = -1;
   private int xK = -1;
   private int Dw = 0;

   public CZ(IEGeneric var1) {
      this.q = var1;
   }

   public CZ(IEGeneric var1, int var2, int var3) {
      this.q = var1;
      if (var2 != 0 || var3 != var1.getBitsize()) {
         if (var2 > var3) {
            throw new IllegalArgumentException(Strings.ff("Illegal slice %d-%d", var2, var3));
         }

         this.RF = var2;
         this.xK = var3;
      }
   }

   public CZ q(int var1) {
      this.Dw = var1;
      return this;
   }

   public IEGeneric q() {
      return this.q;
   }

   public IEGeneric RF() {
      IEGeneric var1 = this.q;
      if (!this.oW()) {
         var1 = var1.slice(this.RF, this.xK);
      }

      if (this.Dw != 0) {
         var1 = var1.zeroExtend(this.Dw);
      }

      return var1;
   }

   public int xK() {
      return this.RF;
   }

   public int Dw() {
      return this.xK;
   }

   public int Uv() {
      return this.Dw;
   }

   public boolean oW() {
      return this.RF == -1 && this.xK == -1;
   }

   public int gO() {
      return this.q.getBitsize();
   }

   public int nf() {
      if (this.Dw != 0) {
         return this.Dw;
      } else {
         return this.oW() ? this.q.getBitsize() : this.xK - this.RF;
      }
   }

   private boolean za() {
      return this.q instanceof IEVar;
   }

   public boolean gP() {
      return this.oW() && this.za();
   }
}
