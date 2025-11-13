package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class bpt implements IDIndex {
   private int pC;

   bpt(int var1) {
      if (var1 < 0) {
         throw new RuntimeException();
      } else {
         this.pC = var1;
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.pC;
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, false);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bpt var3 = (bpt)var1;
         return this.pC == var3.pC;
      }
   }

   public bpt pC(DCopyOptions var1) {
      return this.pC();
   }

   public bpt pC() {
      return this;
   }

   @Override
   public int getValue() {
      return this.pC;
   }

   @Override
   public String toString(IDMethodContext var1) {
      DFormattingContext var2 = new DFormattingContext(var1);
      this.format(var2);
      return var2.toString();
   }

   @Override
   public String toString() {
      return this.toString(null);
   }

   @Override
   public String format(IInstruction var1, long var2) {
      return this.toString();
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.appendFormat("@%d", this.pC);
   }
}
