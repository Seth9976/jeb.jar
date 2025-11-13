package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public class fj extends Yg {
   @SerId(1)
   private String[] A;
   @SerId(2)
   private String kS;

   @SerCustomInit
   private void pC() {
      if (this.A != null && this.getOperandValue() < this.A.length) {
         this.kS = this.A[(int)this.getOperandValue()];
         this.A = null;
      }
   }

   public fj(long var1, int var3, String var4) {
      super(6, 1, var1, var3 & 16777215);
      this.kS = var4;
   }

   public fj(String var1) {
      this(0L, 0, var1);
   }

   @Override
   public String getAlias(long var1) {
      return this.kS;
   }

   @Override
   public boolean A(int var1) {
      return false;
   }

   @Override
   public boolean kS(int var1) {
      return false;
   }

   @Override
   public boolean wS(int var1) {
      return false;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + Arrays.hashCode((Object[])this.A);
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         fj var2 = (fj)var1;
         if (!Arrays.equals((Object[])this.A, (Object[])var2.A)) {
            return false;
         } else {
            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            return true;
         }
      }
   }
}
