package com.pnfsoftware.jebglobal;

class LA extends vS {
   public LA(ZW var1) {
      super(var1);
   }

   @Override
   public CharSequence getDataType(byte[] var1) throws oJ {
      switch (this.pC(var1)) {
         case 0:
            return XW.UT.getDataType(var1);
         case 1:
            return XW.wS.getDataType(var1);
         case 2:
            return XW.A.getDataType(var1);
         case 3:
            return XW.pC.getDataType(var1);
         case 4:
            return "1Q";
         default:
            return "";
      }
   }
}
