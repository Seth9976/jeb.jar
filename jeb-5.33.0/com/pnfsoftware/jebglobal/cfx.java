package com.pnfsoftware.jebglobal;

class cfx implements com.pnfsoftware.jeb.corei.parsers.mips.Av {
   private String[] pC = new String[]{"F", "UN", "EQ", "UEQ", "OLT", "ULT", "OLE", "ULE", "SF", "NGLE", "SEQ", "NGL", "LT", "NGE", "LE", "NGT"};

   @Override
   public CharSequence pC(byte[] var1) {
      int var2 = var1[3] & 15;
      StringBuilder var3 = new StringBuilder();
      var3.append(".").append(this.pC[var2]).append(cfu.eP.pC(var1));
      return var3;
   }
}
