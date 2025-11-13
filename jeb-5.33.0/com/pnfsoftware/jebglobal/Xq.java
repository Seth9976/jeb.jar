package com.pnfsoftware.jebglobal;

class Xq implements YE {
   @Override
   public CharSequence getDataType(byte[] var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = kE.kS(var1);
      if (var3 != 32) {
         var2.append((char)(ZC.A(var1, 0) == 0 ? 'S' : 'U'));
      }

      return var2.append(var3);
   }
}
