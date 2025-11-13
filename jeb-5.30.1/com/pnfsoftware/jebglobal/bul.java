package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class bul implements IDTarget {
   private int q;

   public bul(int var1) {
      this.q = var1;
   }

   public bul q(DCopyOptions var1) {
      return this.q();
   }

   public bul q() {
      return new bul(this.q);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.q;
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
         return var1 != null && this.getClass() == var1.getClass() ? this.q == ((bul)var1).q : false;
      }
   }

   @Override
   public int getOffset() {
      return this.q;
   }

   @Override
   public void setOffset(int var1) {
      this.q = var1;
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
      if (this.q < 0) {
         var1.appendFormat("->-%04X", -this.q);
      } else {
         var1.appendFormat("->%04X", this.q);
      }
   }
}
