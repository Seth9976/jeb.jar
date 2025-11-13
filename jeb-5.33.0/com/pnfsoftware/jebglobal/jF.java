package com.pnfsoftware.jebglobal;

import com.google.common.primitives.Longs;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class jF {
   @SerId(1)
   private final LibraryID pC;
   @SerId(2)
   private final long A;
   @SerId(3)
   private long kS;
   @SerId(4)
   private long wS;
   @SerId(5)
   private long UT;

   public jF(LibraryID var1, long var2) {
      this.pC = var1;
      this.A = var2;
      this.kS = 0L;
   }

   public LibraryID pC() {
      return this.pC;
   }

   public void pC(INativeMethodItem var1) {
      this.kS = this.kS + var1.getData().getCFG().getEffectiveSize();
      if (this.wS == 0L) {
         this.wS = var1.getData().getCFG().getFirstAddress();
      } else {
         this.wS = Longs.min(new long[]{this.wS, var1.getData().getCFG().getFirstAddress()});
      }

      this.UT = Longs.max(new long[]{this.UT, var1.getData().getCFG().getEndAddress()});
   }

   public boolean pC(long var1) {
      return var1 >= this.wS && var1 <= this.UT;
   }

   public boolean A() {
      return this.kS >= this.A;
   }

   public boolean kS() {
      return this.kS >= Math.abs(this.A * 0.8);
   }

   @Override
   public String toString() {
      return Strings.ff("LibraryRange [id=%s, isConfirmed=%b, actualSize=%d, start_address=%x, end_address=%x]", this.pC, this.A(), this.kS, this.wS, this.UT);
   }
}
