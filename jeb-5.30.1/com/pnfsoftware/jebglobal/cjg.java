package com.pnfsoftware.jebglobal;

public interface cjg {
   void update(byte var1);

   void update(byte[] var1);

   void update(byte[] var1, int var2, int var3);

   byte[] digest();

   byte[] digest(byte[] var1);

   int digest(byte[] var1, int var2, int var3);

   int getDigestLength();

   void reset();

   cjg q();

   int RF();

   @Override
   String toString();
}
