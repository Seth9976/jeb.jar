package com.pnfsoftware.jebglobal;

class cgf implements com.pnfsoftware.jeb.corei.parsers.mips.Av {
   @Override
   public CharSequence pC(byte[] var1) {
      return (var1[2] & 4) != 0 ? ".HB" : null;
   }
}
