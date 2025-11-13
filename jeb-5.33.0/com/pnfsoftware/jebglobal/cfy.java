package com.pnfsoftware.jebglobal;

class cfy implements com.pnfsoftware.jeb.corei.parsers.mips.Av {
   private String[] pC = new String[]{
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
   public CharSequence pC(byte[] var1) {
      int var2 = var1[3] & 31;
      boolean var3 = (var1[1] & 32) == 0;
      StringBuilder var4 = new StringBuilder();
      var4.append(".").append(this.pC[var2]).append(var3 ? ".S" : ".D");
      return var4;
   }
}
