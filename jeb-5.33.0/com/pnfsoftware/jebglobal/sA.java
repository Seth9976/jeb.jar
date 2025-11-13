package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class sA {
   public abstract int pC();

   public abstract int A();

   public abstract int kS();

   public abstract wR wS();

   public static sA pC(ByteBuffer var0, Rx var1) {
      int var2 = var0.getInt();
      int var3 = var0.getInt();
      int var4 = var0.getInt();
      wR var5 = wR.pC(var0);
      return new sA.Av(var2, var3, var4, var5, var1);
   }

   public byte[] pC(boolean var1) {
      ByteBuffer var2 = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN);
      var2.putInt(this.pC());
      var2.putInt(this.A());
      var2.putInt(this.kS());
      var2.put(this.wS().pC(var1));
      return var2.array();
   }

   static class Av extends sA {
      int pC;
      int A;
      int kS;
      wR wS;
      Rx UT;

      public Av(int var1, int var2, int var3, wR var4, Rx var5) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
      }

      @Override
      public int pC() {
         return this.pC;
      }

      @Override
      public int A() {
         return this.A;
      }

      @Override
      public int kS() {
         return this.kS;
      }

      @Override
      public wR wS() {
         return this.wS;
      }
   }
}
