package com.pnfsoftware.jebglobal;

public abstract class Hs implements IX {
   protected abstract int pC(byte[] var1) throws oJ;

   @Override
   public CharSequence getDataType(byte[] var1) throws oJ {
      switch (this.pC(var1)) {
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
