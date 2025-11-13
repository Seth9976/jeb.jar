package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class axw implements axy {
   byte[] pC;
   int A;
   auw kS;
   axv wS;
   GenericCodeFormatter UT;
   axz E;
   Deque sY;
   axy ys;
   int ld;
   List gp;
   int oT;

   public axw(byte[] var1, int var2, auw var3, axv var4, GenericCodeFormatter var5, axz var6, Deque var7) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.E = var6;
      this.sY = var7;
      this.gp = var3.UO();
      this.oT = this.gp.size() - 1;
   }

   @Override
   public boolean pC() {
      return this.ld < this.oT || this.ld == this.oT && (this.ys == null || this.ys.pC());
   }

   @Override
   public boolean A() {
      return this.ld == this.oT && (this.ys == null || this.ys.A());
   }

   @Override
   public int kS() {
      return (int)((INativeDataItem)this.gp.get(this.ld)).getMemorySize();
   }

   @Override
   public int wS() {
      if (this.ys != null && this.ys.pC()) {
         return this.ys.wS();
      } else {
         INativeDataItem var1 = (INativeDataItem)this.gp.get(this.ld);
         return this.A + (int)(var1.getMemoryAddress() - this.kS.getMemoryAddress());
      }
   }

   @Override
   public int UT() {
      while (this.ld <= this.oT) {
         INativeDataItem var1 = (INativeDataItem)this.gp.get(this.ld);
         int var2 = this.A + (int)(var1.getMemoryAddress() - this.kS.getMemoryAddress());
         if (this.ys == null) {
            ArrayDeque var3 = new ArrayDeque(this.sY);
            var3.push(new axx(this.kS, var2 - this.A, ((aum)var1).E()));
            this.ys = this.E.pC(this.pC, var2, var1, this.wS, var3);
         }

         if (this.ys.pC()) {
            int var4 = this.ys.UT();
            if (!this.ys.pC()) {
               this.ys = null;
               this.ld++;
            }

            return var4;
         }

         this.ys = null;
         this.ld++;
      }

      return 0;
   }
}
