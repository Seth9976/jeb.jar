package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSField;

public class bki implements IJLSField {
   String q;
   String RF;
   int xK;

   public bki(String var1, String var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public String getName() {
      return this.q;
   }

   @Override
   public String getDescriptor() {
      return this.RF;
   }

   @Override
   public int getAccessFlags() {
      return this.xK;
   }

   @Override
   public String toString() {
      return this.getSignature();
   }
}
