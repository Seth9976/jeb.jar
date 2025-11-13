package com.pnfsoftware.jebglobal;

class cfv implements com.pnfsoftware.jeb.corei.parsers.mips.Av {
   private String[] pC = new String[]{"F", "T", "FL", "TL"};

   @Override
   public CharSequence pC(byte[] var1) {
      int var2 = var1[1] & 3;
      return this.pC[var2];
   }
}
