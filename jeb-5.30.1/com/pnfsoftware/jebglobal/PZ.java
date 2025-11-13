package com.pnfsoftware.jebglobal;

public abstract class PZ implements Dm {
   private final int xW;

   public PZ(int var1) {
      this.xW = var1;
   }

   protected abstract int q(byte[] var1) throws eK;

   @Override
   public CharSequence getDataType(byte[] var1) throws eK {
      int var2 = this.q(var1);
      switch (var2) {
         case -1:
            throw new eK("Illegal Data Type");
         case 0:
            return "B";
         case 1:
            return "H";
         case 2:
            return "S";
         case 3:
            return "D";
         default:
            return var2 == this.xW ? "Q" : "";
      }
   }
}
