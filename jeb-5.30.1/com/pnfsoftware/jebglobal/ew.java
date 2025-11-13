package com.pnfsoftware.jebglobal;

class ew extends kT {
   public ew(dD var1) {
      super(var1);
   }

   @Override
   public CharSequence getDataType(byte[] var1) throws eK {
      switch (this.q(var1)) {
         case 0:
            return kE.Uv.getDataType(var1);
         case 1:
            return kE.Dw.getDataType(var1);
         case 2:
            return kE.RF.getDataType(var1);
         case 3:
            return kE.q.getDataType(var1);
         case 4:
            return "1Q";
         default:
            return "";
      }
   }
}
