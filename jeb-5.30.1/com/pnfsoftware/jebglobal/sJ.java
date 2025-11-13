package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public class sJ extends CW {
   @SerId(1)
   private String[] q;
   @SerId(2)
   private String gP;

   @SerCustomInit
   private void q() {
      if (this.q != null && this.getOperandValue() < this.q.length) {
         this.gP = this.q[(int)this.getOperandValue()];
         this.q = null;
      }
   }

   public sJ(long var1, int var3, String var4) {
      super(6, 1, var1, var3 & 16777215);
      this.gP = var4;
   }

   public sJ(String var1) {
      this(0L, 0, var1);
   }

   @Override
   public String getAlias(long var1) {
      return this.gP;
   }

   @Override
   public boolean RF(int var1) {
      return false;
   }

   @Override
   public boolean xK(int var1) {
      return false;
   }

   @Override
   public boolean Dw(int var1) {
      return false;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + Arrays.hashCode((Object[])this.q);
      return 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         sJ var2 = (sJ)var1;
         if (!Arrays.equals((Object[])this.q, (Object[])var2.q)) {
            return false;
         } else {
            if (this.gP == null) {
               if (var2.gP != null) {
                  return false;
               }
            } else if (!this.gP.equals(var2.gP)) {
               return false;
            }

            return true;
         }
      }
   }
}
