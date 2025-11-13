package com.pnfsoftware.jebglobal;

class cmm implements ckh {
   @Override
   public CharSequence q(byte[] var1) {
      int var2 = var1[3] & 7;
      switch (var2) {
         case 0:
            return ".S";
         case 1:
            return ".D";
         case 22:
            return ".PS";
         default:
            return null;
      }
   }
}
