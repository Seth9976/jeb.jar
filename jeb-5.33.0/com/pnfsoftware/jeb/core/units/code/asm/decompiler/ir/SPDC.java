package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class SPDC implements Comparable {
   public static final SPDC Unknown = new SPDC(0, 0, 0);
   public static final int GUARANTEE_UNKNOWN = 0;
   public static final int GUARANTEE_LOW = 10;
   public static final int GUARANTEE_MEDIUM = 20;
   public static final int GUARANTEE_HIGH = 30;
   public static final int ORIGIN_UNKNOWN = 0;
   public static final int ORIGIN_SIMULATION = 10;
   public static final int ORIGIN_ANALYSIS = 20;
   public static final int ORIGIN_NATIVE = 30;
   public static final int ORIGIN_USER = 40;
   @SerId(1)
   private int value;
   @SerId(2)
   private int guarantee;
   @SerId(3)
   private int origin;

   public static SPDC getBest(SPDC var0, SPDC var1) {
      if (var0 == null) {
         return var1;
      } else if (var1 == null) {
         return var0;
      } else {
         return var0.compareTo(var1) > 0 ? var0 : var1;
      }
   }

   public SPDC(int var1, int var2, int var3) {
      this.value = var1;
      this.guarantee = var2;
      this.origin = var3;
   }

   public int getValue() {
      return this.value;
   }

   public int getGuarantee() {
      return this.guarantee;
   }

   public int getOrigin() {
      return this.origin;
   }

   public SPDC clone() {
      return new SPDC(this.value, this.guarantee, this.origin);
   }

   public int compareTo(SPDC var1) {
      if (this.guarantee < var1.guarantee) {
         return -1;
      } else if (this.guarantee > var1.guarantee) {
         return 1;
      } else if (this.origin < var1.origin) {
         return -1;
      } else if (this.origin > var1.origin) {
         return 1;
      } else if (this.value < var1.value) {
         return -1;
      } else {
         return this.value > var1.value ? 1 : 0;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.guarantee;
      var1 = 31 * var1 + this.origin;
      return 31 * var1 + this.value;
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
         SPDC var2 = (SPDC)var1;
         if (this.guarantee != var2.guarantee) {
            return false;
         } else {
            return this.origin != var2.origin ? false : this.value == var2.value;
         }
      }
   }

   String guaranteeToString() {
      switch (this.guarantee) {
         case 0:
            return "unknown";
         case 10:
            return "low";
         case 20:
            return "medium";
         case 30:
            return "high";
         default:
            return "g=" + this.guarantee;
      }
   }

   String originToString() {
      switch (this.origin) {
         case 0:
            return "unknown";
         case 10:
            return "simulation";
         case 20:
            return "analysis";
         case 30:
            return "native";
         case 40:
            return "user";
         default:
            return "o=" + this.origin;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d{g=%s,o=%s}", this.value, this.guaranteeToString(), this.originToString());
      return var1.toString();
   }
}
