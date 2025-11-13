package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;

public class OA {
   private IEGeneric pC;
   private final int A;
   private int kS;
   private int wS = 0;
   private boolean UT = true;

   public OA(IEGeneric var1, lw var2) {
      this.pC = var1;
      this.A = u.wS(var2);
      this.kS = u.kS(var2) * 8;
   }

   public OA(IEGeneric var1) {
      this.pC = var1 instanceof IESlice ? ((IESlice)var1).getWholeExpression() : var1;
      this.A = 1;
      this.kS = var1.getBitsize();
   }

   public OA(pY var1) {
      this.pC = var1.pC();
      this.A = 1;
      this.kS = var1.ys();
      this.wS = var1.kS();
      this.UT = false;
   }

   public OA pC(OA var1) {
      return this.pC(var1.wS());
   }

   public OA pC(int var1) {
      if (this.pC instanceof IEImm) {
         this.kS = var1;
      }

      return this;
   }

   public IEGeneric pC() {
      return this.pC;
   }

   public IEGeneric A() {
      return this.pC(0, 0, this.UT());
   }

   public IEGeneric A(int var1) {
      return this.pC(var1, this.wS, this.kS);
   }

   public IEGeneric pC(int var1, int var2, int var3) {
      if (this.A == 1) {
         return this.pC.getBitsize() < var3 ? this.pC.zeroExtend(var3) : this.pC.slice(var2, var2 + var3);
      } else {
         return this.pC.slice(var2 + var1 * var3, var2 + (var1 + 1) * var3);
      }
   }

   public int kS() {
      return this.A;
   }

   public int wS() {
      return this.kS;
   }

   public int UT() {
      return this.kS * this.A;
   }

   public boolean E() {
      return this.UT;
   }
}
