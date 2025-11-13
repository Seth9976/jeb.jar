package com.pnfsoftware.jebglobal;

class pp implements de.eo {
   @Override
   public CharSequence getDataType(byte[] var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = My.xK(var1);
      if (var3 != 32) {
         var2.append((char)(HS.RF(var1, 0) == 0 ? 'S' : 'U'));
      }

      return var2.append(var3);
   }
}
