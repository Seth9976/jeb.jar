package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class cin {
   int pC;
   int A;
   byte[] kS;

   public cin(int var1, int var2, byte[] var3) {
      if (var3 == null) {
         throw new NullPointerException("Missing certificate data");
      } else {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }

   public byte[] pC() {
      return this.kS;
   }

   public String A() {
      switch (this.pC) {
         case 256:
            return "1.0";
         case 512:
            return "2.0";
         default:
            return Strings.ff("UnknownRevision_%Xh", this.pC);
      }
   }

   public String kS() {
      switch (this.A) {
         case 1:
            return "X.509";
         case 2:
            return "PKCS SignedData";
         case 3:
         default:
            return Strings.ff("UnknownType_%d", this.A);
         case 4:
            return "Terminal Server Protocol Stack Certificate Signing";
      }
   }

   @Override
   public String toString() {
      return Strings.ff("rev=%s,type=%s,data=%db", this.A(), this.kS(), this.kS.length);
   }
}
