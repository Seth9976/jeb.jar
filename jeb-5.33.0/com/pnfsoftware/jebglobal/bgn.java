package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSField;

public class bgn implements IJLSField {
   String pC;
   String A;
   int kS;

   public bgn(String var1, String var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String getName() {
      return this.pC;
   }

   @Override
   public String getDescriptor() {
      return this.A;
   }

   @Override
   public int getAccessFlags() {
      return this.kS;
   }

   @Override
   public String toString() {
      return this.getSignature();
   }
}
