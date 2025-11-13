package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import java.util.ArrayList;

public class RX {
   private IEGeneric q;
   private final int RF;
   private int xK;
   private int Dw = 0;
   private boolean Uv = true;

   public RX(IEGeneric var1, RI var2) {
      this.q = var1;
      this.RF = xB.Dw(var2);
      this.xK = xB.xK(var2) * 8;
   }

   public RX(IEGeneric var1) {
      this.q = var1 instanceof IESlice ? ((IESlice)var1).getWholeExpression() : var1;
      this.RF = 1;
      this.xK = var1.getBitsize();
   }

   public RX(CZ var1) {
      this.q = var1.q();
      this.RF = 1;
      this.xK = var1.nf();
      this.Dw = var1.xK();
      this.Uv = false;
   }

   public RX q(RX var1) {
      return this.q(var1.xK());
   }

   public RX q(int var1) {
      if (this.q instanceof IEImm) {
         this.xK = var1;
      }

      return this;
   }

   public IEGeneric q() {
      return this.q;
   }

   public IEGeneric RF(int var1) {
      return this.q(var1, this.Dw, this.xK);
   }

   public IEGeneric q(int var1, int var2, int var3) {
      if (this.RF == 1) {
         return this.q.getBitsize() < var3 ? this.q.zeroExtend(var3) : this.q.slice(var2, var2 + var3);
      } else {
         return this.q.slice(var2 + var1 * var3, var2 + (var1 + 1) * var3);
      }
   }

   public int xK(int var1) {
      return var1 * this.xK();
   }

   public int Dw(int var1) {
      return (var1 + 1) * this.xK();
   }

   public int RF() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   public boolean Dw() {
      return this.Uv;
   }

   public IEGeneric q(IERoutineContext var1, int var2) {
      if (var2 <= this.xK) {
         return this.q.part(var2);
      } else {
         ArrayList var3 = new ArrayList();

         for (int var4 = 0; var4 * this.xK < var2; var4++) {
            var3.add(this.RF(var4));
         }

         return var1.createCompose(var3);
      }
   }
}
