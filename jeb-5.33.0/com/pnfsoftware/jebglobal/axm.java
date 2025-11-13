package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Arrays;

@Ser
public class axm extends axh {
   @SerId(1)
   private final byte[] pC;
   @SerTransient
   private volatile Integer A;

   public axm(byte[] var1) {
      this.pC = var1;
   }

   public byte[] pC() {
      return this.pC;
   }

   @Override
   public String getType() {
      return "RoutineCodeHash";
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof axm var2)) {
         return false;
      } else if (this.pC == null && var2.pC != null) {
         return false;
      } else {
         return this.hashCode() != var2.hashCode() ? false : Arrays.equals(this.pC, var2.pC());
      }
   }

   @Override
   public int hashCode() {
      if (this.A == null) {
         synchronized (this) {
            int var2 = 17;

            for (byte var6 : this.pC) {
               var2 = 31 * var2 + var6;
            }

            this.A = var2;
         }
      }

      return this.A;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType());
      var1.append(" -> ");
      var1.append(Formatter.byteArrayToHexString(this.pC()).replaceAll(" ", ""));
      return var1.toString();
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof axj ? var1.match(this) : this.equals(var1);
   }

   @Override
   public INativeFeature deepCopy() {
      return new axm(this.pC);
   }
}
