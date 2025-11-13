package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.format.Strings;

public class pY {
   private final IEGeneric pC;
   private int A = -1;
   private int kS = -1;
   private int wS = 0;

   public pY(IEGeneric var1) {
      this.pC = var1;
   }

   public pY(IEGeneric var1, int var2, int var3) {
      this.pC = var1;
      if (var2 != 0 || var3 != var1.getBitsize()) {
         if (var2 > var3) {
            throw new IllegalArgumentException(Strings.ff("Illegal slice %d-%d", var2, var3));
         }

         this.A = var2;
         this.kS = var3;
      }
   }

   public pY pC(int var1) {
      this.wS = var1;
      return this;
   }

   public IEGeneric pC() {
      return this.pC;
   }

   public IEGeneric A() {
      IEGeneric var1 = this.pC;
      if (!this.E()) {
         var1 = var1.slice(this.A, this.kS);
      }

      if (this.wS != 0) {
         var1 = var1.zeroExtend(this.wS);
      }

      return var1;
   }

   public int kS() {
      return this.A;
   }

   public int wS() {
      return this.kS;
   }

   public int UT() {
      return this.wS;
   }

   public boolean E() {
      return this.A == -1 && this.kS == -1;
   }

   public int sY() {
      return this.pC.getBitsize();
   }

   public int ys() {
      if (this.wS != 0) {
         return this.wS;
      } else {
         return this.E() ? this.pC.getBitsize() : this.kS - this.A;
      }
   }

   private boolean gp() {
      return this.pC instanceof IEVar;
   }

   public boolean ld() {
      return this.E() && this.gp();
   }
}
