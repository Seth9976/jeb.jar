package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSType;

public class bgp implements IJLSType {
   String pC;
   int A;

   public bgp(String var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String getInternalName() {
      return this.pC;
   }

   @Override
   public int getAccessFlags() {
      return this.A;
   }

   @Override
   public String toString() {
      return this.getInternalName();
   }
}
