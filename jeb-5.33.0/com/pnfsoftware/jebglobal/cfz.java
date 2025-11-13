package com.pnfsoftware.jebglobal;

class cfz implements com.pnfsoftware.jeb.corei.parsers.mips.Av {
   @Override
   public CharSequence pC(byte[] var1) {
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
