package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class bqf implements IDTarget {
   private int pC;

   public bqf(int var1) {
      this.pC = var1;
   }

   public bqf pC(DCopyOptions var1) {
      return this.pC();
   }

   public bqf pC() {
      return new bqf(this.pC);
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
      } else {
         return var1 != null && this.getClass() == var1.getClass() ? this.pC == ((bqf)var1).pC : false;
      }
   }

   @Override
   public int getOffset() {
      return this.pC;
   }

   @Override
   public void setOffset(int var1) {
      this.pC = var1;
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
      if (this.pC < 0) {
         var1.appendFormat("->-%04X", -this.pC);
      } else {
         var1.appendFormat("->%04X", this.pC);
      }
   }
}
