package com.pnfsoftware.jebglobal;

public abstract class kP implements Dm {
   protected abstract int q(byte[] var1) throws eK;

   @Override
   public CharSequence getDataType(byte[] var1) throws eK {
      switch (this.q(var1)) {
         case 0:
            return "8B";
         case 1:
            return "16B";
         case 2:
            return "4H";
         case 3:
            return "8H";
         case 4:
            return "2S";
         case 5:
            return "4S";
         case 6:
            return "1D";
         case 7:
            return "2D";
         default:
            return "";
      }
   }
}
