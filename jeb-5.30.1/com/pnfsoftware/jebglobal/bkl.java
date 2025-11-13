package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSType;

public class bkl implements IJLSType {
   String q;
   int RF;

   public bkl(String var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String getInternalName() {
      return this.q;
   }

   @Override
   public int getAccessFlags() {
      return this.RF;
   }

   @Override
   public String toString() {
      return this.getInternalName();
   }
}
