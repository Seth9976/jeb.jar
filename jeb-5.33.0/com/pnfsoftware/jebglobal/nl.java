package com.pnfsoftware.jebglobal;

public abstract class nl implements IX {
   private final int Ab;

   public nl(int var1) {
      this.Ab = var1;
   }

   protected abstract int pC(byte[] var1) throws oJ;

   @Override
   public CharSequence getDataType(byte[] var1) throws oJ {
      int var2 = this.pC(var1);
      switch (var2) {
         case -1:
            throw new oJ("Illegal Data Type");
         case 0:
            return "B";
         case 1:
            return "H";
         case 2:
            return "S";
         case 3:
            return "D";
         default:
            return var2 == this.Ab ? "Q" : "";
      }
   }
}
