package com.pnfsoftware.jebglobal;

public class cjj extends cjk {
   public cjj() {
      super("eth-keccak-512");
   }

   @Override
   public cjg q() {
      return this.q(new cjj());
   }

   @Override
   public int engineGetDigestLength() {
      return 64;
   }

   @Override
   protected byte[] engineDigest() {
      return null;
   }

   @Override
   protected void engineUpdate(byte var1) {
   }

   @Override
   protected void engineUpdate(byte[] var1, int var2, int var3) {
   }
}
