package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class fQ {
   public static final int q = -1;
   public static final int RF = 0;
   public static final int xK = 1;
   boolean Dw;
   int Uv;
   private long oW;
   private byte[] gO;

   public fQ(long var1, int var3) {
      if (var3 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
         this.Uv = var3;
      }
   }

   public fQ(long var1, byte[] var3) {
      if (var3 != null && var3.length != 0) {
         this.oW = var1;
         this.Uv = -1;
         this.gO = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public long q() {
      return this.oW;
   }

   public int RF() {
      return this.Uv;
   }

   byte[] xK() {
      return this.gO;
   }

   void q(byte[] var1) {
      if (this.Uv == -1 && var1 != null && var1.length == this.gO.length) {
         this.gO = var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public String toString() {
      return Strings.ff("@%Xh[type=%d,activated=%b]", this.q(), this.RF(), this.Dw);
   }
}
