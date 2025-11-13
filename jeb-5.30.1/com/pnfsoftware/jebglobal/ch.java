package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class ch {
   public byte q;
   public long RF;

   public ch(byte var1, long var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.q;
      return 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
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
         ch var2 = (ch)var1;
         return this.q != var2.q ? false : this.RF == var2.RF;
      }
   }

   public boolean q() {
      return Pi.RF(this.q);
   }

   public boolean RF() {
      return Pi.q(this.q);
   }

   @Override
   public String toString() {
      return Strings.ff("tag=%c,value=%d(%Xh)", (char)this.q, this.RF, this.RF);
   }
}
