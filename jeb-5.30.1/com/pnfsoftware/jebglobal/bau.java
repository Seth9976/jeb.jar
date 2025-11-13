package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class bau implements bax {
   byte[] q;
   int RF;
   axr xK;
   bat Dw;
   GenericCodeFormatter Uv;
   bay oW;
   Deque gO;
   bax nf;
   int gP;
   List za;
   int lm;

   public bau(byte[] var1, int var2, axr var3, bat var4, GenericCodeFormatter var5, bay var6, Deque var7) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
      this.oW = var6;
      this.gO = var7;
      this.za = var3.sH();
      this.lm = this.za.size() - 1;
   }

   @Override
   public boolean q() {
      return this.gP < this.lm || this.gP == this.lm && (this.nf == null || this.nf.q());
   }

   @Override
   public boolean RF() {
      return this.gP == this.lm && (this.nf == null || this.nf.RF());
   }

   @Override
   public int xK() {
      return (int)((INativeDataItem)this.za.get(this.gP)).getMemorySize();
   }

   @Override
   public int Dw() {
      if (this.nf != null && this.nf.q()) {
         return this.nf.Dw();
      } else {
         INativeDataItem var1 = (INativeDataItem)this.za.get(this.gP);
         return this.RF + (int)(var1.getMemoryAddress() - this.xK.getMemoryAddress());
      }
   }

   @Override
   public int Uv() {
      while (this.gP <= this.lm) {
         INativeDataItem var1 = (INativeDataItem)this.za.get(this.gP);
         int var2 = this.RF + (int)(var1.getMemoryAddress() - this.xK.getMemoryAddress());
         if (this.nf == null) {
            ArrayDeque var3 = new ArrayDeque(this.gO);
            var3.push(new bav(this.xK, var2 - this.RF, ((axh)var1).oW()));
            this.nf = this.oW.q(this.q, var2, var1, this.Dw, var3);
         }

         if (this.nf.q()) {
            int var4 = this.nf.Uv();
            if (!this.nf.q()) {
               this.nf = null;
               this.gP++;
            }

            return var4;
         }

         this.nf = null;
         this.gP++;
      }

      return 0;
   }
}
