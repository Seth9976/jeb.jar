package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Pointer {
   private static final int POINTER = 4;
   public static final int UNKNOWN = 0;
   public static final int CODE = 1;
   public static final int DATA = 2;
   public static final int PTRCODE = 5;
   public static final int PTRDATA = 6;
   @SerId(1)
   protected long address;
   @SerId(2)
   protected int size;
   @SerId(3)
   protected int type;

   public Pointer(long var1, int var3, int var4) {
      this.address = var1;
      this.size = var3;
      this.type = var4;
   }

   public Pointer(long var1) {
      this(var1, 0, 0);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.address ^ this.address >>> 32);
      var1 = 31 * var1 + this.size;
      return 31 * var1 + this.type;
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
         Pointer var2 = (Pointer)var1;
         if (this.address != var2.address) {
            return false;
         } else {
            return this.size != var2.size ? false : this.type == var2.type;
         }
      }
   }

   public long getAddress() {
      return this.address;
   }

   public int getSize() {
      return this.size;
   }

   public void setSize(int var1) {
      this.size = var1;
   }

   public int getType() {
      return this.type;
   }

   @Override
   public String toString() {
      return Strings.ff("%Xh/%d(%s)", this.getAddress(), this.getSize(), switch (this.getType()) {
         case 1 -> "CODE";
         case 2 -> "DATA";
         default -> this.getType() + "";
         case 5 -> "PTRCODE";
         case 6 -> "PTRDATA";
      });
   }
}
