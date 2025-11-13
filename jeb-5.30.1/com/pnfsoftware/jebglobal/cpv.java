package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class cpv {
   public static final int q = 256;
   public static final int RF = 512;
   public static final int xK = 1;
   public static final int Dw = 2;
   public static final int Uv = 3;
   public static final int oW = 4;
   int gO;
   int nf;
   byte[] gP;

   public cpv(int var1, int var2, byte[] var3) {
      if (var3 == null) {
         throw new NullPointerException("Missing certificate data");
      } else {
         this.gO = var1;
         this.nf = var2;
         this.gP = var3;
      }
   }

   public int q() {
      return this.gO;
   }

   public int RF() {
      return this.nf;
   }

   public byte[] xK() {
      return this.gP;
   }

   public String Dw() {
      switch (this.gO) {
         case 256:
            return "1.0";
         case 512:
            return "2.0";
         default:
            return Strings.ff("UnknownRevision_%Xh", this.gO);
      }
   }

   public String Uv() {
      switch (this.nf) {
         case 1:
            return "X.509";
         case 2:
            return "PKCS SignedData";
         case 3:
         default:
            return Strings.ff("UnknownType_%d", this.nf);
         case 4:
            return "Terminal Server Protocol Stack Certificate Signing";
      }
   }

   @Override
   public String toString() {
      return Strings.ff("rev=%s,type=%s,data=%db", this.Dw(), this.Uv(), this.gP.length);
   }
}
