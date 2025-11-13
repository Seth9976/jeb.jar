package com.pnfsoftware.jebglobal;

import com.google.common.primitives.Longs;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class aas {
   @SerId(1)
   private final LibraryID q;
   @SerId(2)
   private final long RF;
   @SerId(3)
   private long xK;
   @SerId(4)
   private long Dw;
   @SerId(5)
   private long Uv;

   public aas(LibraryID var1, long var2) {
      this.q = var1;
      this.RF = var2;
      this.xK = 0L;
   }

   public LibraryID q() {
      return this.q;
   }

   public void q(INativeMethodItem var1) {
      this.xK = this.xK + var1.getData().getCFG().getEffectiveSize();
      if (this.Dw == 0L) {
         this.Dw = var1.getData().getCFG().getFirstAddress();
      } else {
         this.Dw = Longs.min(new long[]{this.Dw, var1.getData().getCFG().getFirstAddress()});
      }

      this.Uv = Longs.max(new long[]{this.Uv, var1.getData().getCFG().getEndAddress()});
   }

   public boolean q(long var1) {
      return var1 >= this.Dw && var1 <= this.Uv;
   }

   public long RF() {
      return this.xK;
   }

   public boolean xK() {
      return this.xK >= this.RF;
   }

   public boolean Dw() {
      return this.xK >= Math.abs(this.RF * 0.8);
   }

   @Override
   public String toString() {
      return Strings.ff("LibraryRange [id=%s, isConfirmed=%b, actualSize=%d, start_address=%x, end_address=%x]", this.q, this.xK(), this.xK, this.Dw, this.Uv);
   }
}
