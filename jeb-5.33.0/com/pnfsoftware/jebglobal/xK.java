package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class xK {
   boolean pC;
   int A;
   private long kS;
   private byte[] wS;

   public xK(long var1, int var3) {
      if (var3 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
         this.A = var3;
      }
   }

   public xK(long var1, byte[] var3) {
      if (var3 != null && var3.length != 0) {
         this.kS = var1;
         this.A = -1;
         this.wS = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public long pC() {
      return this.kS;
   }

   public int A() {
      return this.A;
   }

   byte[] kS() {
      return this.wS;
   }

   void pC(byte[] var1) {
      if (this.A == -1 && var1 != null && var1.length == this.wS.length) {
         this.wS = var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public String toString() {
      return Strings.ff("@%Xh[type=%d,activated=%b]", this.pC(), this.A(), this.pC);
   }
}
