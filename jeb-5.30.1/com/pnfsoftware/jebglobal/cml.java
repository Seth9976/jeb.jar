package com.pnfsoftware.jebglobal;

class cml implements ckh {
   private String[] q = new String[]{
      "AF",
      "UN",
      "EQ",
      "UEQ",
      "LT",
      "ULT",
      "LE",
      "ULE",
      "SAF",
      "SUN",
      "SEQ",
      "SUEQ",
      "SLT",
      "SULT",
      "SLE",
      "SULE",
      null,
      "OR",
      "UNE",
      "NE",
      null,
      null,
      null,
      null,
      null,
      "SOR",
      "SUNE",
      "SNE",
      null,
      null,
      null,
      null
   };

   @Override
   public CharSequence q(byte[] var1) {
      int var2 = var1[3] & 31;
      boolean var3 = (var1[1] & 32) == 0;
      StringBuilder var4 = new StringBuilder();
      var4.append(".").append(this.q[var2]).append(var3 ? ".S" : ".D");
      return var4;
   }
}
