package com.pnfsoftware.jeb.corei.parsers.ihex;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public class CU {
   public static final int q = 0;
   public static final int RF = 1;
   public static final int xK = 2;
   public static final int Dw = 3;
   public static final int Uv = 4;
   public static final int oW = 5;
   private int gO;
   private int nf;
   private byte[] gP;
   private boolean za;

   CU(int var1, int var2, byte[] var3, boolean var4) {
      this.gO = var1;
      this.nf = var2;
      this.gP = var3;
      this.za = var4;
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

   public boolean Dw() {
      return this.za;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%d/%Xh/%s", this.nf, this.gO, Formatter.byteArrayToHex(this.gP));
      if (!this.za) {
         var1 = var1 + "[CORRUPT]";
      }

      return var1;
   }
}
