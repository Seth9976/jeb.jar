package com.pnfsoftware.jebglobal;

class cmi implements ckh {
   private String[] q = new String[]{"F", "T", "FL", "TL"};

   @Override
   public CharSequence q(byte[] var1) {
      int var2 = var1[1] & 3;
      return this.q[var2];
   }
}
