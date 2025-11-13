package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Arrays;

@Ser
public class baj extends bae {
   @SerId(1)
   private final byte[] q;
   @SerTransient
   private volatile Integer RF;

   public baj(byte[] var1) {
      this.q = var1;
   }

   public byte[] q() {
      return this.q;
   }

   @Override
   public String getType() {
      return "RoutineCodeHash";
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof baj var2)) {
         return false;
      } else if (this.q == null && var2.q != null) {
         return false;
      } else {
         return this.hashCode() != var2.hashCode() ? false : Arrays.equals(this.q, var2.q());
      }
   }

   @Override
   public int hashCode() {
      if (this.RF == null) {
         synchronized (this) {
            int var2 = 17;

            for (byte var6 : this.q) {
               var2 = 31 * var2 + var6;
            }

            this.RF = var2;
         }
      }

      return this.RF;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType());
      var1.append(" -> ");
      var1.append(Formatter.byteArrayToHexString(this.q()).replaceAll(" ", ""));
      return var1.toString();
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof bag ? var1.match(this) : this.equals(var1);
   }

   @Override
   public INativeFeature deepCopy() {
      return new baj(this.q);
   }
}
