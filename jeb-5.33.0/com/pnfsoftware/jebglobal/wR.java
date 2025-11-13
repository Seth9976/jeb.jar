package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

public abstract class wR {
   public abstract int pC();

   public abstract wR.Sv A();

   public abstract int kS();

   public static wR pC(ByteBuffer var0) {
      int var1 = var0.getShort() & '\uffff';
      var0.get();
      wR.Sv var2 = wR.Sv.pC(var0.get());
      int var3 = var0.getInt();
      return new wR.Av(var1, var2, var3);
   }

   public byte[] wS() {
      return this.pC(false);
   }

   public byte[] pC(boolean var1) {
      ByteBuffer var2 = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
      var2.putShort((short)this.pC());
      var2.put((byte)0);
      var2.put(this.A().pC());
      var2.putInt(this.kS());
      return var2.array();
   }

   static ks UT() {
      return new vF();
   }

   static class Av extends wR {
      int pC;
      wR.Sv A;
      int kS;

      public Av(int var1, wR.Sv var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      @Override
      public int pC() {
         return this.pC;
      }

      @Override
      public wR.Sv A() {
         return this.A;
      }

      @Override
      public int kS() {
         return this.kS;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.kS;
         var1 = 31 * var1 + this.pC;
         return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            wR.Av var2 = (wR.Av)var1;
            if (this.kS != var2.kS) {
               return false;
            } else {
               return this.pC != var2.pC ? false : this.A == var2.A;
            }
         }
      }
   }

   public static enum Sv {
      pC(0),
      A(1),
      kS(2),
      wS(3),
      UT(4),
      E(5),
      sY(6),
      ys(7),
      ld(16),
      gp(17),
      oT(18),
      fI(28),
      WR(29),
      NS(30),
      vP(31);

      private final byte xC;
      private static final Map ED;

      private Sv(int var3) {
         this.xC = UnsignedBytes.checkedCast(var3);
      }

      public byte pC() {
         return this.xC;
      }

      public static wR.Sv pC(byte var0) {
         return (wR.Sv)Preconditions.checkNotNull((wR.Sv)ED.get(var0));
      }

      static {
         Builder var0 = ImmutableMap.builder();

         for (wR.Sv var4 : values()) {
            var0.put(var4.pC(), var4);
         }

         ED = var0.build();
      }
   }
}
