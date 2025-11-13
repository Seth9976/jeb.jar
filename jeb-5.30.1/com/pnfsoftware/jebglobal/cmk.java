package com.pnfsoftware.jebglobal;

class cmk implements ckh {
   private String[] q = new String[]{"F", "UN", "EQ", "UEQ", "OLT", "ULT", "OLE", "ULE", "SF", "NGLE", "SEQ", "NGL", "LT", "NGE", "LE", "NGT"};

   @Override
   public CharSequence q(byte[] var1) {
      int var2 = var1[3] & 15;
      StringBuilder var3 = new StringBuilder();
      var3.append(".").append(this.q[var2]).append(cmh.PV.q(var1));
      return var3;
   }
}
