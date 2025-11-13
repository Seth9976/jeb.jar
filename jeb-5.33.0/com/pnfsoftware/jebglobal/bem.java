package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstructionParameter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bem implements IDalvikInstructionParameter {
   @SerId(1)
   private int pC;
   @SerId(2)
   private long A;

   public bem(int var1, long var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public int getType() {
      return this.pC;
   }

   @Override
   public long getValue() {
      return this.A;
   }

   @Override
   public String format(IInstruction var1, long var2) {
      StringBuilder var4 = new StringBuilder();
      long var5 = this.getValue();
      switch (this.getType()) {
         case 0:
            Strings.ff(var4, "v%d", var5);
            break;
         case 1:
            Strings.ff(var4, "#%X", var5);
            break;
         case 2:
            int var8 = (int)var5;
            Strings.ff(var4, "@%d", var8);
            break;
         case 3:
            int var7 = (int)(var2 + var5 * 2L);
            Strings.ff(var4, "%X", var7);
            break;
         case 4:
            Strings.ff(var4, "v%d-v%d", var5 & 65535L, var5 >> 32 & 65535L);
            break;
         case 5:
            Strings.ff(var4, "@@%d", var5);
            break;
         default:
            throw new JebRuntimeException("Unsupported parameter type: " + this.getType());
      }

      return var4.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
      return 31 * var1 + (int)(this.A ^ this.A >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bem var2 = (bem)var1;
         return this.pC != var2.pC ? false : this.A == var2.A;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("t=%d,v=%d", this.getType(), this.getValue());
   }
}
