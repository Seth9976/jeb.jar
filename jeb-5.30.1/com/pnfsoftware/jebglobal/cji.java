package com.pnfsoftware.jebglobal;

public class cji extends cjk {
   public cji() {
      super("eth-keccak-256");
   }

   @Override
   public cjg q() {
      return this.q(new cji());
   }

   @Override
   public int engineGetDigestLength() {
      return 32;
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
