package com.pnfsoftware.jebglobal;

public class bH implements IX {
   private CharSequence Ab;

   public bH(CharSequence var1) {
      this.Ab = var1;
   }

   @Override
   public CharSequence getDataType(byte[] var1) {
      return this.Ab;
   }
}
